/*
 * The MIT License
 *
 * Copyright 2016 T0166941.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.praqma.prqa.reports;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import net.praqma.prqa.PRQAApplicationSettings;
import net.praqma.prqa.PRQAContext;
import net.praqma.prqa.PRQaFrameworkVersion;
import net.praqma.prqa.QAVerifyServerSettings;
import net.praqma.prqa.reportsettings.ReportSettings;
import net.praqma.prqa.exceptions.PrqaException;
import net.praqma.prqa.exceptions.PrqaUploadException;
import net.praqma.prqa.parsers.MessageGroup;
import net.praqma.prqa.products.PRQACommandBuilder;
import net.praqma.prqa.reportsettings.PRQaFrameworkReportSettings;
import net.praqma.prqa.status.PRQAComplianceStatus;
import net.praqma.util.execute.CmdResult;
import org.apache.commons.lang.StringUtils;
import org.jdom2.JDOMException;

/**
 *
 * @author T0166941
 */
public class PRQAFrameworkReport implements Serializable, ReportSettings {

    private static final long serialVersionUID = 1L;
    public static final String XHTML = "xhtml";
    public static final String XML = "xml";
    public static final String HTML = "html";
    private static final String FILE_SEPARATOR = "";
    private static final String QUOTE = "\"";
    public static String XHTML_REPORT_EXTENSION;
    public static String XML_REPORT_EXTENSION;
    public static String HTML_REPORT_EXTENSION;
    private static final Logger log = Logger.getLogger(QAFrameworkReport.class.getName());
    private PRQaFrameworkReportSettings settings;
    private QAVerifyServerSettings qaVerifySettings;
    private File workspace;
    private Map<String, String> environment;
    private PRQAApplicationSettings appSettings;
    private PRQaFrameworkVersion prqaFrameworkVersion;
    
    public PRQAFrameworkReport(PRQaFrameworkReportSettings settings, QAVerifyServerSettings qaVerifySettings, 
            PRQAApplicationSettings appSettings) {
        this.settings = settings;
        this.qaVerifySettings = qaVerifySettings;
        this.appSettings = appSettings;
    }

    public PRQAFrameworkReport(PRQaFrameworkReportSettings settings, QAVerifyServerSettings qaVerifySettings, 
            PRQAApplicationSettings appSettings, HashMap<String, String> environmentVariables) {
        this.settings = settings;
        this.qaVerifySettings = qaVerifySettings;
        this.appSettings = appSettings;
        this.environment = environmentVariables;
    }

    public CmdResult analyzeQacli(boolean isUnix, PrintStream out) throws PrqaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String createAnalysisCommandForQacli(boolean isUnix, PrintStream out) throws PrqaException {
        PRQACommandBuilder builder = new PRQACommandBuilder("qacli ");

        // we want to analyse
        builder.prependArgument("analyse ");
        
        // project path
        builder.appendArgument("-P " + settings.prqaProject);
        builder.appendArgument(this.resolveAbsOrRelativePath(workspace, settings.prqaProject, out));
        // Type of analysis
        builder.appendArgument("--file-based-analysis ");
        if (settings.isPRQaEnableDependencyMode()) {
            builder.appendArgument("");
        }
        return builder.getCommand();
    }

    public CmdResult cmaAnalysisQacli(boolean isUnix, PrintStream out) throws PrqaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String createCmaAnalysisCommand(boolean isUnix, PrintStream out) throws PrqaException {
        PRQACommandBuilder builder = new PRQACommandBuilder("qacli ");

        // we want to analyse
        builder.prependArgument("analyze ");
        
        // project path
        builder.appendArgument("-P " + this.resolveAbsOrRelativePath(workspace, settings.prqaProject, out));
        // Type of analysis
        builder.appendArgument("--project-based-analysis ");
        if (settings.isPRQaEnableDependencyMode()) {
            builder.appendArgument("");
        }
        return builder.getCommand();
    }

    public CmdResult reportQacli(boolean isUnix, PrintStream out) throws PrqaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String createReportCommandForQacli(boolean isUnix, PrintStream out) throws PrqaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String createReportCommand(String projectLocation, PrintStream out) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void removeOldReports(String projectLocation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String createUploadCommandQacli(PrintStream out) throws PrqaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String createAddUploadConfigurationFilesToProjectCommand(PrintStream out) throws PrqaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public CmdResult uploadQacli(PrintStream out) throws PrqaUploadException, PrqaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public CmdResult addUploadConfigurationFilesToProject(PrintStream out) throws PrqaException, JDOMException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean checkIfCanUpload() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setQaVerifyServerSettings() throws PrqaException, JDOMException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String formatQacliPath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static String getNamingTemplate(PRQAContext.QARReportType type, String extension) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String resolveAbsOrRelativePath(File workspaceRoot, String projectFilePath, PrintStream outStream) throws PrqaException {
        File pFile = new File(projectFilePath);
        if (pFile.isAbsolute()) {
            if (!pFile.exists()) {
                throw new PrqaException(String.format("The project file %s does not exist.", projectFilePath));
            } else {
                return projectFilePath;
            }
        } else {
            File relative = new File(workspaceRoot, projectFilePath);
            if (relative.exists()) {
                return relative.getPath();
            } else {
                throw new PrqaException(String.format("The project file %s does not exist.", relative.getPath()));
            }
        }
    }

    public static void _logEnv(String location, Map<String, String> env) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PRQAComplianceStatus getComplianceStatus(PrintStream out) throws PrqaException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getResultsDataFileRelativePath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void sortViolatedRulesByRuleID(List<MessageGroup> messagesGroups) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setWorkspace(File workspace) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PRQaFrameworkReportSettings getSettings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setSettings(PRQaFrameworkReportSettings settings) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Map<String, String> getEnvironment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setEnvironment(Map<String, String> environment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PRQAApplicationSettings getAppSettings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPRQaFrameworkVersion(PRQaFrameworkVersion prqaFrameworkVersion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getProduct() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean publishToQAV() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
