package net.praqma.jenkins.plugin.prqa.graphs;

import hudson.util.ChartUtil;
import hudson.util.DataSetBuilder;
import java.io.IOException;
import net.praqma.prqa.exceptions.PrqaException;
import net.praqma.prqa.PRQAContext;
import net.praqma.prqa.PRQAStatusCollection;
import net.praqma.prqa.status.StatusCategory;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

/**
 *
 * @author Praqma
 */
public class ComplianceIndexGraphs extends PRQAGraph {

    public ComplianceIndexGraphs() {
        super("Compliance Levels per Build", PRQAContext.QARReportType.Compliance, StatusCategory.FileCompliance, StatusCategory.ProjectCompliance);
    }

    @Override
    public void setData(PRQAStatusCollection data) {
        Number minF = null;
        Number minP = null;
        super.setData(data);
        try {
            /* If the minimum file compliance is lower than 2 we set
            the minimum of the graph at 0 */
            if(data.getMin(StatusCategory.FileCompliance).intValue()-2 < 0) {
                minF = 0; 
            /* Otherwise we set the minimum of the graph at 2 less than the
            minimum */     
            } else {
                minF = (data.getMin(StatusCategory.FileCompliance).intValue()-2);
            }
            /* If the minimum project compliance is lower than 2 we set
            the minimum of the graph at 0 */
            if(data.getMin(StatusCategory.ProjectCompliance).intValue()-2 < 0) {
                minP = 0; 
            /* Otherwise we set the minimum of the graph at 2 less than the
            minimum */       
            } else {
                minP = (data.getMin(StatusCategory.ProjectCompliance).intValue()-2);
            }
            
        } catch (PrqaException iex) {
        }
        this.data.overrideMax(StatusCategory.FileCompliance, 100);
        this.data.overrideMin(StatusCategory.FileCompliance, minF);
        this.data.overrideMax(StatusCategory.ProjectCompliance, 100);
        this.data.overrideMin(StatusCategory.ProjectCompliance, minP);
    }

    @Override
    public void drawGraph(StaplerRequest req, StaplerResponse rsp, DataSetBuilder<String, ChartUtil.NumberOnlyBuildLabel> dsb, Double tMax) throws IOException {
        Number max = null;
        Number min = null;
        int width = Integer.parseInt(req.getParameter("width"));
        int height = Integer.parseInt(req.getParameter("height"));

        for (StatusCategory category : categories) {
            try {
                max = data.getMax(category);
                min = data.getMin(category);
            } catch (PrqaException iex) {
                continue;
            }
        }
        if (max != null && min != null) {
            ChartUtil.generateGraph(req, rsp, createChart(dsb.build(), getTitle(), null, max.intValue(), min.intValue()), width, height);
        }
    }
}
