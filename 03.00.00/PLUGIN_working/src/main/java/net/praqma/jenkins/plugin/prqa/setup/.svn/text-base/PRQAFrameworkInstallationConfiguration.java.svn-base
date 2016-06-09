package net.praqma.jenkins.plugin.prqa.setup;

import hudson.EnvVars;
import hudson.Extension;
import hudson.tools.ToolDescriptor;
import hudson.tools.ToolInstallation;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jenkins.model.Jenkins;
import net.praqma.prqa.products.QACli;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import com.google.common.base.Strings;

public class PRQAFrameworkInstallationConfiguration extends ToolInstallation implements PRQAToolSuite {

    private static final long serialVersionUID = 1L;
    public final String prqafHome;
    public final String prqafName;
    public final String toolType;
    public final String tool;

    @DataBoundConstructor
    public PRQAFrameworkInstallationConfiguration(final String prqafName, final String prqafHome, final String tool, final String toolType) {
        super(prqafName, prqafHome, null);

        this.prqafHome = prqafHome;
        this.prqafName = prqafName;
        this.toolType = toolType;
        this.tool = tool;
    }

    @Override
    public HashMap<String, String> createEnvironmentVariables(String workspaceRoot) {
        HashMap<String, String> environment = new HashMap<String, String>();
        environment.put(QACli.PRQAF_INSTALL_PATH, getHome());
        environment.put(QACli.WORKSPACE_PATH, workspaceRoot);
        return environment;
    }

    public HashMap<String, String> convert(EnvVars vars) {
        HashMap<String, String> varsMap = new HashMap<String, String>();
        for (String s : vars.keySet()) {
            varsMap.put(s, vars.get(s));
        }
        return varsMap;
    }

    public static PRQAFrameworkInstallationConfiguration getInstallationByName(String prqafName) {
//        if (StringUtils.isBlank(prqafName)) {
//            return null;
//        } else {
//            PRQAFrameworkInstallationConfiguration[] installations = Jenkins.getInstance()
//                    .getDescriptorByType(PRQAFrameworkInstallationConfiguration.DescriptorImpl.class).getInstallations();
//            for (PRQAFrameworkInstallationConfiguration install : installations) {
//                if (install.getName().equals(prqafName)) {
//                    return install;
//                }
//            }
//        }
        return null;
    }

    @Extension
    public static final class DescriptorImpl extends ToolDescriptor<PRQAFrameworkInstallationConfiguration> {

        public DescriptorImpl() {
            super();
            load();
        }

        @Override
        public String getDisplayName() {
            return "PRQA·Framework";
        }

        @Override
        public PRQAFrameworkInstallationConfiguration newInstance(StaplerRequest req, JSONObject formData) throws FormException {
            PRQAFrameworkInstallationConfiguration suite = req.bindJSON(PRQAFrameworkInstallationConfiguration.class, formData);

            save();
            return suite;
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
            String prqafHome = "prqafHome";
            String prqafName = "prqafName";
            String tool = "tool";

            if (!json.isEmpty()) {
                List<String> qaInstallationNames = new ArrayList<String>();
                if (json.get(tool) instanceof JSONArray) {
                    JSONArray array = json.getJSONArray(tool);
                    Iterator it = array.iterator();
                    while (it.hasNext()) {
                        JSONObject jsonObject = (JSONObject) it.next();
                        String qaName = jsonObject.getString(prqafName);
                        String qaHome = jsonObject.getString(prqafHome);
                        if (Strings.isNullOrEmpty(qaName) || Strings.isNullOrEmpty(qaHome) || qaInstallationNames.contains(qaName) || !isValidString(qaName)
                                || !isValidString(qaHome)) {
                            it.remove();
                        } else {
                            qaInstallationNames.add(qaName);
                        }
                    }
                    if (array.isEmpty()) {
                        json = new JSONObject();
                    }
                } else {
                    JSONObject jsonObject = json.getJSONObject(tool);
                    String prqaName = null;
                    String prqaHome = null;
                    if (jsonObject.has(prqafName)) {
                        prqaName = jsonObject.getString(prqafName);
                    }
                    if (jsonObject.has(prqafHome)) {
                        prqaHome = jsonObject.getString(prqafHome);
                    }
                    if (Strings.isNullOrEmpty(prqaName) || Strings.isNullOrEmpty(prqaHome) || !isValidString(prqaName) || !isValidString(prqaHome)) {
                        json = new JSONObject();
                    }
                }
            }
            save();
            return super.configure(req, json);
        }

        private boolean isValidString(String valid) {
            boolean isValid = true;
//            String trim = valid.trim();
//            if (Strings.isNullOrEmpty(trim) || !valid.equals(trim) || trim.length() < 1) {
//                isValid = false;
//            }
            return isValid;
        }

        public ListBoxModel doFillPRQafNameItems() {
            ListBoxModel model = new ListBoxModel();
            for (PRQAFrameworkInstallationConfiguration suite : getQaInstallations()) {
                model.add(suite.getName());
            }
            return model;
        }

        public PRQAFrameworkInstallationConfiguration[] getQaInstallations() {
            PRQAFrameworkInstallationConfiguration[] installations = Jenkins.getInstance()
                    .getDescriptorByType(PRQAFrameworkInstallationConfiguration.DescriptorImpl.class).getInstallations();
            return installations;
        }

        public FormValidation doCheckPRQafHome(@QueryParameter String prqafHome) {
            if (StringUtils.isBlank(prqafHome)) {
                return FormValidation.errorWithMarkup("PRQA·Framework Installation path should not be empty!");
            }
            if (prqafHome.startsWith(" ")) {
                return FormValidation.errorWithMarkup("PRQA·Framework Installation path should not be begin with an empty space!");
            }
            return FormValidation.ok();
        }

        public FormValidation doCheckPRQafName(@QueryParameter String prqafName) {
            if (StringUtils.isBlank(prqafName)) {
                return FormValidation.errorWithMarkup("The name shall not be empty and shall be unique in the set of PRQA·Framework installations!");
            }
            if (prqafName.startsWith(" ")) {
                return FormValidation.errorWithMarkup("PRQA·Framework Installation name should not be begin with an empty space!");
            }
            return FormValidation.ok();
        }
    }
}
