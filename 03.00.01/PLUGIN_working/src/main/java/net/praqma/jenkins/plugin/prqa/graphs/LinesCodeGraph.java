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
package net.praqma.jenkins.plugin.prqa.graphs;

import hudson.model.Run;
import hudson.util.ChartUtil;
import hudson.util.ColorPalette;
import hudson.util.DataSetBuilder;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.praqma.jenkins.plugin.prqa.notifier.PRQABuildAction;
import net.praqma.prqa.PRQAContext;
import net.praqma.prqa.PRQAReading;
import net.praqma.prqa.PRQAStatusCollection;
import net.praqma.prqa.exceptions.PrqaException;
import net.praqma.prqa.status.PRQAComplianceStatus;
import net.praqma.prqa.status.StatusCategory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleInsets;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

/**
 *
 * @author T0166941
 */
public class LinesCodeGraph extends PRQAGraph {

    public LinesCodeGraph() {
        super("General Information per Build", PRQAContext.QARReportType.Quality, StatusCategory.TotalNumberOfFiles, StatusCategory.NumberOfClasses, StatusCategory.NumberOfFunctions);
    }

    @Override
    public void drawGraph(StaplerRequest req, StaplerResponse rsp, DataSetBuilder<String, ChartUtil.NumberOnlyBuildLabel> dsb, Double tMax) throws IOException {
        super.drawGraph(req, rsp, dsb, tMax);
    }

    @Override
    public void setData(PRQAStatusCollection data) {
        Number min = null;
        Number max = null;
        super.setData(data);
        try {
            /* If the minimum number of lines of code is lower than 500 we set
            the minimum of the graph at 0 */
            if(data.getMin(StatusCategory.TotalNumberOfFiles).intValue()-500 < 0) {
                min = 0;
            /* Otherwise we set the minimum of the graph at 5000 less than the
            minimum */
            } else {
                min = (data.getMin(StatusCategory.TotalNumberOfFiles).intValue()-500);
            }
            max = data.getMax(StatusCategory.NumberOfFunctions);
        } catch (PrqaException iex) {
        }
        this.data.overrideMin(StatusCategory.TotalNumberOfFiles, min);
        this.data.overrideMin(StatusCategory.NumberOfFunctions, max);
    }

    @Override
    protected JFreeChart createChart(CategoryDataset dataset, String title, String yaxis, int max, int min) {

        JFreeChart chart = super.createChart(dataset, title, yaxis, max, min);

        final CategoryPlot plot = chart.getCategoryPlot();
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();

        // plot2
        final CategoryPlot plot2 = chart.getCategoryPlot();
        final NumberAxis axis2 = new NumberAxis("Lines of code axis");
        axis2.setLabelPaint(Color.decode("#cc8400"));
        axis2.setAutoRangeIncludesZero(false);
        axis2.setPlot(plot2);
        axis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        plot2.setRangeAxis(1, axis2);
        
        DataSetBuilder<String, ChartUtil.NumberOnlyBuildLabel> dsbFiles =
                new DataSetBuilder<String, ChartUtil.NumberOnlyBuildLabel>();
        List<ChartUtil.NumberOnlyBuildLabel> set = dataset.getColumnKeys();
        // Remove the empty columns
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i) == null) {
                set.remove(i);
            }
        }
        // Remove the doubled values
        PRQAStatusCollection p = super.getData();
        for (int i = 0; i < p.size()-1; i++) {
            if (p.get(i) == p.get(i + 1)) {
                p.remove(i+1);
                i--;
            }
        }
        for (int i = 0; i < set.size(); i++) {
            ChartUtil.NumberOnlyBuildLabel label = null;
            try {
                label = set.get(set.size() - i - 1);
                dsbFiles.add(p.get(i).getReadout(StatusCategory.LinesOfCode),
                        "Lines Of Code", label);
            } catch (PrqaException ex) {
                Logger.getLogger(LinesCodeGraph.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        plot2.setDataset(1, dsbFiles.build());
        plot2.mapDatasetToRangeAxis(1, 1);
        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        plot2.setRenderer(1, renderer2);

        ColorPalette.apply(renderer);
        renderer2.setSeriesPaint(0, Color.decode("#cc8400"));
        renderer2.setBaseStroke(new BasicStroke(2.0f));
        renderer2.setSeriesShapesVisible(0, false);
        plot.setInsets(new RectangleInsets(5.0, 0, 0, 5.0));
        return chart;
    }

}

