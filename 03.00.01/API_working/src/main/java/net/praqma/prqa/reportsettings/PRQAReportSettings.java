package net.praqma.prqa.reportsettings;

import net.praqma.prqa.reportsettings.ReportSettings;
import java.io.Serializable;
import java.util.EnumSet;
import net.praqma.prqa.PRQAContext;

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
    public final boolean generateWarningSummary;

    public final String product;

    /**
     * creates the report settings with project file
     */
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
        this.generateWarningSummary = false;
        this.chosenReportTypes = chosenReportTypes;
        this.product = product;
        this.settingsFile = null;
        this.fileList = null;
    }

    /**
     * creates the report settings with filelist
     */
    public PRQAReportSettings(final String chosenServer, final String fileList, final boolean performCrossModuleAnalysis,
            final boolean publishToQAV, final boolean enableDependencyMode, final boolean enableDataFlowAnalysis,
            final boolean generateCodeReviewReport, final boolean generateSuppressionReport, final boolean generateWarningSummary,
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
        this.generateWarningSummary = generateWarningSummary;
        this.chosenReportTypes = chosenReportTypes;
        this.product = product;
        this.settingsFile = settingsFile;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Chosen qaverify server: %s%n", chosenServer));
        builder.append(String.format("Project file: %s%n", projectFile));
        builder.append(String.format("Settings file: %s%n", settingsFile));
        builder.append(String.format("Perform CMA: %s%n", performCrossModuleAnalysis));
        builder.append(String.format("Publish to QAVerify: %s%n", publishToQAV));
        builder.append(String.format("Dependency Analysis: %s%n", enableDependencyMode));
        builder.append(String.format("Data flow analysis: %s%n", enableDataFlowAnalysis));
        builder.append(String.format("Generate Code Review Report: %s%n", generateCodeReviewReport));
        builder.append(String.format("Generate Suppression Report: %s%n", generateSuppressionReport));
        builder.append(String.format("Generate Warning Summary: %s%n", generateWarningSummary));
        return builder.toString();
    }

    public String getProduct() {
        return this.product;
    }

    public boolean publishToQAV() {
        return this.publishToQAV;
    }
}
