/*
 * The MIT License
 *
 * Copyright 2013 Praqma.
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
package net.praqma.jenkins.plugin.prqa.notifier;

import hudson.Extension;
import hudson.model.Hudson;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;

import java.util.Arrays;
import java.util.List;

import net.praqma.jenkins.plugin.prqa.globalconfig.PRQAGlobalConfig;
import net.praqma.jenkins.plugin.prqa.globalconfig.QAVerifyServerConfiguration;
import net.praqma.jenkins.plugin.prqa.setup.PRQAFrameworkInstallationConfiguration;
import net.praqma.jenkins.plugin.prqa.threshold.AbstractThreshold;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

public class PRQAFrameworkPostBuildActionSetup extends PostBuildActionSetup {

    public String prqaInstallation;
    public String prqaProject;
    public boolean performCrossModuleAnalysis;
    public String CMAProjectName;
    public boolean enableDependencyMode;
    public boolean generateReport;
    public boolean publishToQAV;
    public String chosenServer;
    public String qaVerifyConfigFile;
    public String vcsConfigXml;
    public String qaVerifyProjectName;

    @DataBoundConstructor
    public PRQAFrameworkPostBuildActionSetup(
            String prqaInstallation, String prqaProject, boolean performCrossModuleAnalysis, 
            String CMAProjectName, boolean enableDependencyMode, boolean generateReport,
            boolean publishToQAV, String chosenServer, String qaVerifyConfigFile, 
            String vcsConfigXml, String qaVerifyProjectName) {

        this.prqaInstallation = prqaInstallation;
        this.prqaProject = prqaProject;
        this.performCrossModuleAnalysis = performCrossModuleAnalysis;
        this.CMAProjectName = CMAProjectName;
        this.enableDependencyMode = enableDependencyMode;
        this.generateReport = generateReport;
        this.publishToQAV = publishToQAV;
        this.chosenServer = chosenServer;
        this.qaVerifyConfigFile = qaVerifyConfigFile;
        this.vcsConfigXml = vcsConfigXml;
        this.qaVerifyProjectName = qaVerifyProjectName;
    }

    public String getChosenServer() {
        return chosenServer;
    }

    public void setChosenServer(String chosenServer) {
        this.chosenServer = chosenServer;
    }

    public String getQaVerifyConfigFile() {
        return qaVerifyConfigFile;
    }

    public void setQaVerifyConfigFile(String qaVerifyConfigFile) {
        this.qaVerifyConfigFile = qaVerifyConfigFile;
    }

    public String getVcsConfigXml() {
        return vcsConfigXml;
    }

    public void setVcsConfigXml(String vcsConfigXml) {
        this.vcsConfigXml = vcsConfigXml;
    }

    public boolean isPublishToQAV() {
        return publishToQAV;
    }

    public void setPublishToQAV(boolean publishToQAV) {
        this.publishToQAV = publishToQAV;
    }

    public void setPRQaInstallation(String prqaInstallation) {
        this.prqaInstallation = prqaInstallation;
    }

    public void setPRQaProject(String prqaProject) {
        this.prqaProject = prqaProject;
    }

    public void setPerformCrossModuleAnalysis(boolean performCrossModuleAnalysis) {
        this.performCrossModuleAnalysis = performCrossModuleAnalysis;
    }

    public void setEnableDependencyMode(boolean enableDependencyMode) {
        this.enableDependencyMode = enableDependencyMode;
    }

    public String getPRQaInstallation() {
        return prqaInstallation;
    }

    public String getPRQaProject() {
        return prqaProject;
    }

    public String getCMAProjectName() {
        return CMAProjectName;
    }

    public void setCMAProjectName(String cMAProjectName) {
        CMAProjectName = cMAProjectName;
    }

    public boolean isPerformCrossModuleAnalysis() {
        return performCrossModuleAnalysis;
    }

    public boolean isEnableDependencyMode() {
        return enableDependencyMode;
    }

    public boolean isGenerateReport() {
        return generateReport;
    }

    public void setGenerateReport(boolean generateReport) {
        this.generateReport = generateReport;
    }

    @Extension
    public final static class DescriptorImpl extends PRQAReportSourceDescriptor<PRQAFrameworkPostBuildActionSetup> {

        public boolean enabled = false;

        @Override
        public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
            save();
            return super.configure(req, json);
        }

        public DescriptorImpl() {
            super();
            load();
        }

        @Override
        public String getDisplayName() {
            return " PRQA·Framework";
        }

        public FormValidation doCheckPRQAInstallation(@QueryParameter String value) {
            if (StringUtils.isBlank(value)) {
                return FormValidation.error("Error");
            } else {
                return FormValidation.ok();
            }
        }

        public FormValidation doCheckCMAProjectName(@QueryParameter String CMAProjectName) {
            if (StringUtils.isBlank(CMAProjectName)) {
                return FormValidation.errorWithMarkup("CMA project name should not be empty!");
            }
            if (CMAProjectName.startsWith(" ")) {
                return FormValidation.errorWithMarkup("CMA project name should not be begin with an empty space!");
            }
            return FormValidation.ok();
        }

        public FormValidation doCheckPRQaVerifyConfigFile(@QueryParameter String prqaVerifyConfigFile) {
            if (StringUtils.isBlank(prqaVerifyConfigFile)) {
                return FormValidation.errorWithMarkup("This field is mandatory and should not be empty!");
            }
            return FormValidation.ok();
        }

        public FormValidation doCheckVcsConfigXml(@QueryParameter String vcsConfigXml) {
            if (StringUtils.isBlank(vcsConfigXml)) {
                return FormValidation.errorWithMarkup("This field is mandatory and should not be empty!");
            }
            return FormValidation.ok();
        }

        public ListBoxModel doFillPRQaInstallationItems() {
            ListBoxModel model = new ListBoxModel();

            for (PRQAFrameworkInstallationConfiguration suitePRQAFramework : getPRQAFrameworkTools()) {
                model.add(suitePRQAFramework.getName());
            }
            return model;
        }

        public List<PRQAFrameworkInstallationConfiguration> getPRQAFrameworkTools() {
            PRQAFrameworkInstallationConfiguration[] prqaInstallations = Hudson.getInstance()
                    .getDescriptorByType(PRQAFrameworkInstallationConfiguration.DescriptorImpl.class).getInstallations();
            return Arrays.asList(prqaInstallations);
        }

        public List<ThresholdSelectionDescriptor<?>> getThresholdSelections() {
            return AbstractThreshold.getDescriptors();
        }

        public List<QAVerifyServerConfiguration> getServers() {
            return PRQAGlobalConfig.get().getServers();
        }

        public List<PRQAFileProjectSourceDescriptor<?>> getFileProjectSources() {
            return PRQAFileProjectSource.getDescriptors();
        }
    }
}
