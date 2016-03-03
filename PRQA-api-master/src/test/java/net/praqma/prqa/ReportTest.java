/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.praqma.prqa;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Praqma
 */
public class ReportTest {
    @Test public void testReportFileNotFoundProject() {
    }
    
    @Test public void testConstants() {
        assertTrue(PRQAContext.QARReportType.values().length == 4);
        assertTrue(PRQAContext.QARReportType.REQUIRED_TYPES.size() == 2);
        assertTrue(PRQAContext.QARReportType.OPTIONAL_TYPES.size() == 2);
        assertTrue(PRQAContext.QARReportType.REQUIRED_TYPES.contains(PRQAContext.QARReportType.Compliance));
        assertTrue(PRQAContext.QARReportType.REQUIRED_TYPES.contains(PRQAContext.QARReportType.Quality));
    }
       
}
