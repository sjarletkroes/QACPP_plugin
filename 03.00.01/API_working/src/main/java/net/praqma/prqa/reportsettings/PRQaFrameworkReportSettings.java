/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.praqma.prqa.reportsettings;

/**
 *
 * @author T0166941
 */
public class PRQaFrameworkReportSettings implements ReportSettings {

    public String prqaInstallation;
    public String prqaProject;
    public boolean prqaEnableDependencyMode;
    public boolean prqaCrossModuleAnalysis;
    public String cmaProjectName;
    public boolean generateReport;
    public boolean publishToQAV;
    public String qaVerifyConfigFile;
    public String vcsConfigXml;
    public String product;
    public String qaVerifyProjectName;

    public PRQaFrameworkReportSettings(String prqaInstallation, String prqaProject, 
            boolean prqaEnableDependencyMode, boolean prqaCrossModuleAnalysis, String cmaProjectName, 
            boolean generateReport, boolean publishToQAV, String qaVerifyConfigFile, 
            String vcsConfigXml, String product, String qaVerifyProjectName) {
        this.prqaInstallation = prqaInstallation;
        this.prqaProject = prqaProject;
        this.prqaEnableDependencyMode = prqaEnableDependencyMode;
        this.prqaCrossModuleAnalysis = prqaCrossModuleAnalysis;
        this.cmaProjectName = cmaProjectName;
        this.generateReport = generateReport;
        this.publishToQAV = publishToQAV;
        this.qaVerifyConfigFile = qaVerifyConfigFile;
        this.vcsConfigXml = vcsConfigXml;
        this.product = product;
        this.qaVerifyProjectName = qaVerifyProjectName;
    }

    public String getPRQaInstallation() {
        return this.prqaInstallation;
    }

    @Override
    public String getProduct() {
        return this.product;
    }

    @Override
    public boolean publishToQAV() {
        return this.publishToQAV;
    }

    public String getPRQaProject() {
        return this.prqaProject;
    }

    public boolean isPRQaEnableDependencyMode() {
        return this.prqaEnableDependencyMode;
    }

    public boolean isPRQaCrossModuleAnalysis() {
        return this.prqaCrossModuleAnalysis;
    }

    public String getCmaProjectName() {
        return this.cmaProjectName;
    }

    public boolean isGenerateReport() {
        return this.generateReport;
    }

    public boolean isPublishToQAV() {
        return this.publishToQAV;
    }

    public String getQaVerifyConfigFile() {
        return this.qaVerifyConfigFile;
    }

    public String getVcsConfigXml() {
        return this.vcsConfigXml;
    }

    public String getQaVerifyProjectName() {
        return this.qaVerifyProjectName;
    }

    public void setQaVerifyProjectName(String qaVerifyProjectName) {
        this.qaVerifyProjectName = qaVerifyProjectName;
    }
    
}
