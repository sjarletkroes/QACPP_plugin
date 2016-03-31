package net.praqma.prqa;

import java.io.Serializable;
import java.util.EnumSet;

/**
 *
 * @author Praqma
 */
public class PRQAReportSettings implements Serializable, ReportSettings {    
    public final String chosenServer;
    public final EnumSet<PRQAContext.QARReportType> chosenReportTypes;

    public final String projectFile;
    public final String settingsFile;
    public final String fileList;
    
    public final boolean performCrossModuleAnalysis;
    public final boolean publishToQAV;
    
    //Analysis options
    public final boolean enableDependencyMode;
    public final boolean enableDataFlowAnalysis;
    // Report options
    public final boolean generateCodeReviewReport;
    public final boolean generateSuppressionReport;
    
    public final String product;
    
    public PRQAReportSettings(final String chosenServer, final String projectFile, final boolean performCrossModuleAnalysis,
            final boolean publishToQAV, final boolean enableDependencyMode, final boolean enableDataFlowAnalysis,
            final boolean generateCodeReviewReport, final boolean generateSuppressionReport,
            final EnumSet<PRQAContext.QARReportType> chosenReportTypes, final String product) {
        this.chosenServer = chosenServer;
        this.projectFile = projectFile;
        this.performCrossModuleAnalysis = performCrossModuleAnalysis;
        this.publishToQAV = publishToQAV;
        this.enableDependencyMode = enableDependencyMode;
        this.enableDataFlowAnalysis = enableDataFlowAnalysis;
        this.generateCodeReviewReport = generateCodeReviewReport;
        this.generateSuppressionReport = generateSuppressionReport;
        this.chosenReportTypes = chosenReportTypes;
        this.product = product;
        this.settingsFile = null;
        this.fileList = null;
    }
    
    public PRQAReportSettings(final String chosenServer, final String fileList, final boolean performCrossModuleAnalysis,
            final boolean publishToQAV, final boolean enableDependencyMode, final boolean enableDataFlowAnalysis,
            final boolean generateCodeReviewReport, final boolean generateSuppressionReport, 
            final EnumSet<PRQAContext.QARReportType> chosenReportTypes, final String product, final String settingsFile) {
        this.chosenServer = chosenServer;
        this.projectFile = null;
        this.fileList = fileList;
        this.performCrossModuleAnalysis = performCrossModuleAnalysis;
        this.publishToQAV = publishToQAV;
        this.enableDependencyMode = enableDependencyMode;
        this.enableDataFlowAnalysis = enableDataFlowAnalysis;
        this.generateCodeReviewReport = generateCodeReviewReport;
        this.generateSuppressionReport = generateSuppressionReport;
        this.chosenReportTypes = chosenReportTypes;
        this.product = product;
        this.settingsFile = settingsFile;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( String.format( "Chosen qaverify server: %s%n", chosenServer) );
        builder.append( String.format( "Project file: %s%n", projectFile) );
        builder.append( String.format( "Perform CMA: %s%n", performCrossModuleAnalysis) );
        builder.append( String.format( "Publish to QAVerify: %s%n", publishToQAV) );
        builder.append( String.format( "Dependency Analysis: %s%n", enableDependencyMode) );
        builder.append( String.format( "Data flow analysis: %s%n", enableDataFlowAnalysis) );
        builder.append( String.format( "Generate Code Review Report: %s%n", generateCodeReviewReport) );
        builder.append( String.format( "Generate Suppression Report: %s%n", generateSuppressionReport) );
        return builder.toString();
    }

    public String getProduct() {
        return this.product;
    }

    public boolean publishToQAV() {
        return this.publishToQAV;
    }
}

