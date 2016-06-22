/*
 * The MIT License
 *
 * Copyright 2014 RINF.
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
package net.praqma.jenkins.plugin.prqa;

import hudson.FilePath.FileCallable;
import hudson.model.BuildListener;
import hudson.remoting.VirtualChannel;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Map;

import net.praqma.prqa.PRQAApplicationSettings;
import net.praqma.prqa.PRQaFrameworkVersion;
import net.praqma.prqa.QaFrameworkVersion;
import net.praqma.prqa.exceptions.PrqaException;
import net.praqma.prqa.products.PRQA_QACli;
import net.praqma.prqa.products.QACli;
import net.praqma.prqa.reports.PRQAFrameworkReport;
import net.praqma.prqa.reportsettings.PRQaFrameworkReportSettings;
import net.praqma.prqa.status.PRQAComplianceStatus;
import net.praqma.util.execute.CmdResult;

import org.apache.commons.lang.StringUtils;
import org.jdom2.JDOMException;
import org.jenkinsci.remoting.RoleChecker;

public class PRQAFrameworkRemoteReport implements FileCallable<PRQAComplianceStatus> {

    private static final long serialVersionUID = 1L;

    private PRQAFrameworkReport report;
    private BuildListener listener;
    boolean isUnix;

    public PRQAFrameworkRemoteReport(PRQAFrameworkReport report, BuildListener listener, boolean isUnix) {
        this.report = report;
        this.listener = listener;
        this.isUnix = isUnix;
    }

    private Map<String, String> expandEnvironment(Map<String, String> environment, PRQAApplicationSettings appSettings,
            PRQaFrameworkReportSettings reportSetting) {
        if (environment == null) {
            return Collections.emptyMap();
        }
        String delimiter = System.getProperty("file.separator");
        environment.put(PRQA_QACli.PRQAF_BIN_PATH,
                PRQAApplicationSettings.addSlash(environment.get(PRQA_QACli.PRQAF_INSTALL_PATH), delimiter) + "common"
                + delimiter + "bin");
        return environment;
    }

    @Override
    public PRQAComplianceStatus invoke(File f, VirtualChannel channel) throws IOException, InterruptedException {

        Map<String, String> expandedEnvironment = expandEnvironment(report.getEnvironment(), report.getAppSettings(),
                report.getSettings());

        report.setEnvironment(expandedEnvironment);
        report.setWorkspace(f);
        PrintStream out = listener.getLogger();

        //out.println("Workspace form invoke:"+f);
        out.println("Workspace form invoke:" + f.getAbsolutePath());
        //out.println("Workspace form invoke:"+f.getCanonicalPath());

        try {
            if (StringUtils.isBlank(report.getSettings().getPRQaInstallation())) {
                throw new PrqaException("Incorrect configuration!");
            }

            CmdResult analyzeResult = report.analyzeQacli(isUnix, out);
            logCmdResult(analyzeResult, out);

            CmdResult cmaAnalysisResult = report.cmaAnalysisQacli(isUnix, out);
            logCmdResult(cmaAnalysisResult, out);

            CmdResult reportsGenerationResult = report.reportQacli(isUnix, out);
            logCmdResult(reportsGenerationResult, out);

            CmdResult addConfigurationFilesToProjectResult;
            try {
                addConfigurationFilesToProjectResult = report.addUploadConfigurationFilesToProject(out);
            } catch (JDOMException e) {
                throw new IOException(e.getMessage());
            }
            logCmdResult(addConfigurationFilesToProjectResult, out);

            CmdResult uploadResult = report.uploadQacli(out);
            logCmdResult(uploadResult, out);

            return report.getComplianceStatus(out);
        } catch (PrqaException exception) {
            throw new IOException(exception.getMessage(), exception);
        } catch (Exception ex) {
            throw new IOException(ex.getMessage());
        }
    }

    private void logCmdResult(CmdResult result, PrintStream out) {
        if (result == null) {
            return;
        }
        out.println(result.stdoutBuffer.toString());
    }

    public void setPRQaFrameworkVersion(PRQaFrameworkVersion prqaFrameworkVersion) {
        report.setPRQaFrameworkVersion(prqaFrameworkVersion);
    }

    @Override
    public void checkRoles(RoleChecker rc) throws SecurityException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
