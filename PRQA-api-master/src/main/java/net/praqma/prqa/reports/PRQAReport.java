/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.praqma.prqa.reports;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.praqma.prqa.PRQAApplicationSettings;
import net.praqma.prqa.PRQAContext;
import net.praqma.prqa.PRQAReportSettings;
import net.praqma.prqa.PRQAUploadSettings;
import net.praqma.prqa.QAVerifyServerSettings;
import net.praqma.prqa.exceptions.PrqaException;
import net.praqma.prqa.exceptions.PrqaUploadException;
import net.praqma.prqa.parsers.ComplianceReportHtmlParser;
import net.praqma.prqa.parsers.QualityReportHtmlParser;
import net.praqma.prqa.products.PRQACommandBuilder;
import net.praqma.prqa.products.QAV;
import net.praqma.prqa.status.PRQAComplianceStatus;
import net.praqma.prqa.status.PRQAQualityStatus;
import net.praqma.util.execute.AbnormalProcessTerminationException;
import net.praqma.util.execute.CmdResult;
import net.praqma.util.execute.CommandLine;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Praqma
 */
public class PRQAReport implements Serializable {
   
    public static final String XHTML = "xhtml";
    public static final String XML = "xml";
    public static final String HTML = "html";
    
    public static String XHTML_REPORT_EXTENSION = "Report."+PRQAReport.XHTML;
    public static String XML_REPORT_EXTENSION = "Report."+PRQAReport.XML;
    public static String HTML_REPORT_EXTENSION = "Report."+PRQAReport.HTML;
        
    private static final Logger log = Logger.getLogger(PRQAReport.class.getName());
    private PRQAReportSettings settings;
    private QAVerifyServerSettings qavSettings;
    private PRQAUploadSettings upSettings;
    private File workspace;
    private HashMap<String,String> environment;
    private PRQAApplicationSettings appSettings;
    
    public PRQAReport(PRQAReportSettings settings, QAVerifyServerSettings qavSettings, PRQAUploadSettings upSettings, PRQAApplicationSettings appSettings) {
        this.settings = settings;
        this.qavSettings = qavSettings;
        this.upSettings = upSettings;
        this.appSettings = appSettings;               
    }
    
    public PRQAReport(PRQAReportSettings settings, QAVerifyServerSettings qavSettings, PRQAUploadSettings upSettings, PRQAApplicationSettings appSettings, HashMap<String,String> environment) {
        this.settings = settings;
        this.environment = environment;
        this.qavSettings = qavSettings;
        this.upSettings = upSettings;
        this.appSettings = appSettings;
    }
    
    public static String getNamingTemplate(PRQAContext.QARReportType type, String extension) {
        return type.toString()+ " "+extension;
    }
    
    /**
     * Resolves the project file location. This can be either absolute or relative to the current workspace
     * @param workspaceRoot
     * @param projectFilePath
     * @return
     * @throws PrqaException 
     */
    public String resolveAbsOrRelativePath(File workspaceRoot, String projectFilePath) throws PrqaException {        
        File pFile = new File(projectFilePath);
        if(pFile.isAbsolute()) {
            if(!pFile.exists()) {
                throw new PrqaException( String.format("The project file %s does not exist.", projectFilePath) );
            } else {
                return projectFilePath;
            }
        } else {
           File relative = new File(workspaceRoot, projectFilePath);
           if(relative.exists()) {
                return relative.getPath();
           } else {
                throw new PrqaException( String.format("The project file %s does not exist.",relative.getPath()) );
           }
        }
    }
    
    public String createAnalysisCommand(boolean isUnix) throws PrqaException {        
        PRQACommandBuilder builder = new PRQACommandBuilder(appSettings != null ? PRQAApplicationSettings.resolveQawExe(isUnix) : "qaw");        
        builder.prependArgument(settings.product);
        builder.appendArgument(PRQACommandBuilder.getProjectFile(resolveAbsOrRelativePath(workspace, settings.projectFile)));        
        if(settings.enableDependencyMode) {
            builder.appendArgument("-mode depend");
        }
        builder.appendArgument(PRQACommandBuilder.getDataFlowAnanlysisParameter(settings.enableDataFlowAnalysis));        
        String pal = settings.performCrossModuleAnalysis ? "pal %Q %P+ %L+" : "";        
        if(!StringUtils.isEmpty(pal)) {
            builder.appendArgument(PRQACommandBuilder.getMaseq(pal));
        }        
        return builder.getCommand();
    }
    
    public CmdResult analyze(boolean isUnix) throws PrqaException {
        String finalCommand = createAnalysisCommand(isUnix);
        Map<String,String> systemVars = new HashMap<String, String>();
        systemVars.putAll(System.getenv());
        CmdResult res = null;
        try {            
            if(getEnvironment() == null) {
                PRQAReport._logEnv("Current analysis execution environment", systemVars);
                res = CommandLine.getInstance().run(finalCommand, workspace, true, false);
            } else {
                systemVars.putAll(getEnvironment());
                PRQAReport._logEnv("Current modified analysis execution environment", systemVars);
                res = CommandLine.getInstance().run(finalCommand, workspace, true, false, getEnvironment());
            }
        } catch (AbnormalProcessTerminationException abnex) {
            throw new PrqaException(String.format( "Failed to analyze, message was:\n %s",abnex.getMessage()), abnex);
        }
        return res;
    }
    
    public String createReportCommand(boolean isUnix) throws PrqaException {
        PRQACommandBuilder builder = new PRQACommandBuilder(appSettings != null ? PRQAApplicationSettings.resolveQawExe(isUnix) : "qar");        
        builder.prependArgument(settings.product);
        // Utilisation d'un *.prj
        if(!StringUtils.isBlank(settings.projectFile)) {
            builder.appendArgument(PRQACommandBuilder.getProjectFile(resolveAbsOrRelativePath(workspace, settings.projectFile)));
        // Utilisation d'un filelist.lst
        } else if(!StringUtils.isBlank(settings.fileList))  {
            //builder.appendArgument("-via "+PRQACommandBuilder.getProjectFile(resolveAbsOrRelativePath(workspace, settings.fileList)));            
            builder.appendArgument("-list "+PRQACommandBuilder.wrapInQuotationMarks(settings.fileList));
            // Utilisation d'un settings.via
            if(!StringUtils.isBlank(settings.settingsFile)) {
                builder.appendArgument("-via "+PRQACommandBuilder.getProjectFile(resolveAbsOrRelativePath(workspace, settings.settingsFile)));
            } 
        } else {
            throw new PrqaException("Report source not configured (Project File/File List)");
        }
        
        if(settings.enableDependencyMode) {
            builder.appendArgument("-mode depend");
        }
        builder.appendArgument(PRQACommandBuilder.getDataFlowAnanlysisParameter(settings.enableDataFlowAnalysis));
        builder.appendArgument(PRQACommandBuilder.getSfbaOption(true));
        
        String reports = "";
        String qar = appSettings != null ? PRQAApplicationSettings.resolveQarExe(isUnix) : "qar"; 
        for (PRQAContext.QARReportType type : settings.chosenReportTypes) {
            reports += qar +" %Q %P+ %L+ " + PRQACommandBuilder.getReportTypeParameter(type.toString(), true)+ " ";
            reports += PRQACommandBuilder.getViewingProgram("noviewer", false)+ " ";
            reports += PRQACommandBuilder.getReportFormatParameter(PRQAReport.XHTML, false)+ " ";
            reports += PRQACommandBuilder.getProjectName()+ " ";
            reports += PRQACommandBuilder.getOutputPathParameter(workspace.getPath(), true);
            reports += "#";
        }
        //Remove trailing #
        reports = reports.substring(0, reports.length()-1);
        String qarEmbedded = (settings.performCrossModuleAnalysis ? "pal %Q %P+ %L+#" : "")+reports;
        builder.appendArgument(PRQACommandBuilder.getMaseq(qarEmbedded));
        return builder.getCommand();
    }
    
    public static void _logEnv(String location, Map<String,String> env) {
        log.fine(String.format( "%s", location));
        log.fine("==========================================");
        if(env != null) {
            for(String key : env.keySet()) {
                log.fine(String.format("%s=%s",key, env.get(key)));
            }
        }
        log.fine("==========================================");
    }
    
    public CmdResult report(boolean isUnix) throws PrqaException {
        Map<String,String> systemVars = new HashMap<String, String>();
        systemVars.putAll(System.getenv());
        
        String finalCommand = createReportCommand(isUnix);
        try {            
            if(getEnvironment() == null) {                
                PRQAReport._logEnv("Current report generation execution environment", System.getenv());
                return CommandLine.getInstance().run(finalCommand, workspace, true, false);
            } else {
                systemVars.putAll(getEnvironment());
                PRQAReport._logEnv("Current modified report generation execution environment", systemVars);
                return CommandLine.getInstance().run(finalCommand, workspace, true, false, getEnvironment());
            }
        } catch (AbnormalProcessTerminationException abnex) {
            log.severe(String.format("Failed to execute report generation command: %s%n%s", finalCommand,abnex.getMessage()));
            log.logp(Level.SEVERE, this.getClass().getName(), "report()", "Failed to execute report generation command", abnex);
            throw new PrqaException(String.format("Failed to execute report generation command: %s%n%s", finalCommand,abnex.getMessage()), abnex);
        } 
    }
    
    public String createUploadCommand() throws PrqaException {
        if(settings.publishToQAV) {
            int availableProcessors = Runtime.getRuntime().availableProcessors();
            String importCommand = PRQACommandBuilder.escapeWhitespace("qaimport");
            
            importCommand += " " +  "%Q %P+ %L+ "+PRQACommandBuilder.getNumberOfThreads(availableProcessors)+" "+PRQACommandBuilder.getSop(StringUtils.isBlank(upSettings.sourceOrigin) ? workspace.getPath() : upSettings.sourceOrigin) + " ";
            importCommand += PRQACommandBuilder.getPrqaVcs(upSettings.codeUploadSetting, upSettings.vcsConfigXml)+" ";
            importCommand += PRQACommandBuilder.getQavOutPathParameter(workspace.getPath())+" ";
            importCommand += PRQACommandBuilder.getImportLogFilePathParameter(workspace.getPath()+QAV.QAV_IMPORT_LOG)+" ";
            importCommand += PRQACommandBuilder.getCodeAll(upSettings.codeUploadSetting);

            //Step2: The upload part
            String uploadPart ="#upload %P+ " + "-prqavcs "+PRQACommandBuilder.wrapInEscapedQuotationMarks(upSettings.vcsConfigXml);
            uploadPart +=" "+PRQACommandBuilder.getHost(qavSettings.host);
            uploadPart +=" "+PRQACommandBuilder.getPort(qavSettings.port);    
            uploadPart +=" "+PRQACommandBuilder.getUser(qavSettings.user);       
            uploadPart +=" "+PRQACommandBuilder.getPassword(qavSettings.password); 
            uploadPart +=" "+PRQACommandBuilder.getProjectDatabase(upSettings.qaVerifyProjectName);
            uploadPart +=" "+PRQACommandBuilder.getProd(upSettings.singleSnapshotMode);     
            uploadPart +=" "+PRQACommandBuilder.getLogFilePathParameter(workspace.getPath()+QAV.QAV_UPLOAD_LOG);
            uploadPart +=" "+PRQACommandBuilder.wrapInEscapedQuotationMarks(workspace.getPath());

            //Step3: Finalize
            String source = "";
            if(settings.projectFile != null) {
                source = PRQACommandBuilder.wrapInQuotationMarks(resolveAbsOrRelativePath(workspace, settings.projectFile));
            } else if(settings.fileList != null) {
                source = "-via "+PRQACommandBuilder.wrapInQuotationMarks(resolveAbsOrRelativePath(workspace, settings.fileList));
                if(settings.settingsFile != null) {
                    source += " " + "-via "+PRQACommandBuilder.wrapInQuotationMarks(resolveAbsOrRelativePath(workspace, settings.settingsFile));
                } 
            } else {
                throw new PrqaException("Neither filelist or project file has been set, this should not be happening");
            }
            
            
            String mainCommand = "qaw" + " " + settings.product +  " "+source;
            mainCommand += " "+ PRQACommandBuilder.getSfbaOption(true)+" ";
            mainCommand += PRQACommandBuilder.getDependencyModeParameter(true) + " ";

            String finalCommand = mainCommand + PRQACommandBuilder.getMaseq(PRQACommandBuilder.getCrossModuleAnalysisParameter(settings.performCrossModuleAnalysis)+importCommand+uploadPart);
            return finalCommand;
        }
        return null;
    }
    
    public CmdResult upload() throws PrqaUploadException,PrqaException {
        CmdResult res = null;
        String finalCommand = createUploadCommand();
        if(finalCommand == null) {
            return null;
        }
        
        try {
            if(getEnvironment() == null) {
                res = CommandLine.getInstance().run(finalCommand, workspace, true, false);
            } else {            
                res = CommandLine.getInstance().run(finalCommand, workspace, true, false, getEnvironment());
            }
        } catch (AbnormalProcessTerminationException abnex) {
            log.logp(Level.SEVERE, this.getClass().getName(), "upload()", "Logged error with upload", abnex);
            throw new PrqaUploadException(String.format("Upload failed with message:%n%s", abnex.getMessage()), abnex);
        }
        
        return res;
    }
    
    public PRQAComplianceStatus getComplianceStatus() throws PrqaException {                        
        ComplianceReportHtmlParser parser = new ComplianceReportHtmlParser(getWorkspace().getPath()+ System.getProperty("file.separator") + "Compliance Report.xhtml");
        PRQAComplianceStatus status = new PRQAComplianceStatus();
        Double fileCompliance = Double.parseDouble(parser.getResult(ComplianceReportHtmlParser.fileCompliancePattern));
        Double projectCompliance =  Double.parseDouble(parser.getResult(ComplianceReportHtmlParser.projectCompliancePattern));
        int messages = Integer.parseInt(parser.getResult(ComplianceReportHtmlParser.totalMessagesPattern));                
        
        for(int i=0; i<10; i++) {
            try {
                int result = Integer.parseInt(parser.getResult(ComplianceReportHtmlParser.levelNMessages[i]));
                status.getMessagesByLevel().put(i, result);
            } catch (NumberFormatException nfe) {
                status.getMessagesByLevel().put(i, 0);
            }
        }
        
        status.setFileCompliance(fileCompliance);
        status.setProjectCompliance(projectCompliance);
        status.setMessages(messages);
               
        return status;
    }           
    
    public PRQAQualityStatus getQualityStatus() throws PrqaException {                        
        QualityReportHtmlParser parser = new QualityReportHtmlParser(getWorkspace().getPath()+ System.getProperty("file.separator") + "Quality Report.xhtml");
        PRQAQualityStatus status = new PRQAQualityStatus();
        Double numberOfFiles = Double.parseDouble(parser.getResult(QualityReportHtmlParser.numberFilesPattern));
        Double linesOfCode = Double.parseDouble(parser.getResult(QualityReportHtmlParser.numberLinesOfCodePattern));
        Double functions =  Double.parseDouble(parser.getResult(QualityReportHtmlParser.numberOfFunctionsPattern));
        Double classes =  Double.parseDouble(parser.getResult(QualityReportHtmlParser.numberOfClassesPattern));
        Double functionMetrics =  Double.parseDouble(parser.getResult(QualityReportHtmlParser.numberOfFunctionMetricsPattern));
        Double classMetrics =  Double.parseDouble(parser.getResult(QualityReportHtmlParser.numberOfClassMetricsPattern));
        Double fileMetrics =  Double.parseDouble(parser.getResult(QualityReportHtmlParser.numberOfFileMetricsPattern));
                
        for(int i=0; i<5; i++) {
            try {
                int result = Integer.parseInt(parser.getResult(QualityReportHtmlParser.fileDetails[i]));
                status.getFileDetails().put(i, result);
                result = Integer.parseInt(parser.getResult(QualityReportHtmlParser.classDetails[i]));
                status.getClassDetails().put(i, result);
                result = Integer.parseInt(parser.getResult(QualityReportHtmlParser.functionDetails[i]));
                status.getFunctionDetails().put(i, result);
            } catch (NumberFormatException nfe) {
                status.getFileDetails().put(i, 0);
                status.getClassDetails().put(i, 0);
                status.getFunctionDetails().put(i, 0);
            }
        }
        
        status.setNumberOfFiles(numberOfFiles);
        status.setLinesOfCode(linesOfCode);
        status.setNumberOfFunctions(functions);
        status.setNumberOfClasses(classes);
        status.setNumberOfFunctionMetrics(functionMetrics);
        status.setNumberOfClassMetrics(classMetrics);
        status.setNumberOfFileMetrics(fileMetrics);
               
        return status;
    }          

    /**
     * @return the workspace
     */
    public File getWorkspace() {
        return workspace;
    }

    /**
     * @param workspace the workspace to set
     */
    public void setWorkspace(File workspace) {
        this.workspace = workspace;
    }

    /**
     * @return the settings
     */
    public PRQAReportSettings getSettings() {
        return settings;
    }

    /**
     * @param settings the settings to set
     */
    public void setSettings(PRQAReportSettings settings) {
        this.settings = settings;
    }

    /**
     * @return the environment
     */
    public HashMap<String,String> getEnvironment() {
        return environment;
    }

    /**
     * @param environment the environment to set
     */
    public void setEnvironment(HashMap<String,String> environment) {
        this.environment = environment;
    }

    /**
     * @return the appSettings
     */
    public PRQAApplicationSettings getAppSettings() {
        return appSettings;
    }

    /**
     * @param appSettings the appSettings to set
     */
    public void setAppSettings(PRQAApplicationSettings appSettings) {
        this.appSettings = appSettings;
    }
}


