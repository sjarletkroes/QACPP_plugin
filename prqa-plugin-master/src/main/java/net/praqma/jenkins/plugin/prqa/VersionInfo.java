package net.praqma.jenkins.plugin.prqa;

import jenkins.model.Jenkins;

/**
 * @author jes
 */
public class VersionInfo {

    public static final String WIKI_PAGE = "https://wiki.jenkins-ci.org/display/JENKINS/PRQA+Plugin";

    public static String getPluginVersion() {
        return String.format("Programming Research Quality Assurance Plugin version %s", Jenkins.getInstance().getPlugin("prqa-plugin").getWrapper().getVersion());
    }
}
