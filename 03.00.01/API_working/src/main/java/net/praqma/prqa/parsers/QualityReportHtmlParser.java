package net.praqma.prqa.parsers;

import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author T0166941
 */
public class QualityReportHtmlParser extends ReportHtmlParser {

    private static final int BAD = 4, POOR = 3, AVERAGE = 2, GOOD = 1, EXCELLENT = 0;

    public static final Pattern numberOfFunctionsPattern;
    public static final Pattern numberOfClassesPattern;
    
    public static final Pattern[] fileDetails;
    public static final Pattern[] classDetails;
    public static final Pattern[] functionDetails;
    // File names parsers for the html files containing the details to parse
    public static final Pattern fileDetailsFilePattern;
    public static final Pattern classDetailsFilePattern;
    public static final Pattern functionDetailsFilePattern;
        
    private static final int NB_LEVELS = 5;

    public QualityReportHtmlParser() { }

    public QualityReportHtmlParser(String fullReportPath) {
        super(fullReportPath);
    }

    static { 
        
        // Get file names
        fileDetailsFilePattern = Pattern.compile("<a href=\"#(.*)\">Number of Files per Quality Level</a>");
        classDetailsFilePattern = Pattern.compile("<a href=\"#(.*)\">Number of Classes per Quality Level</a>");
        functionDetailsFilePattern = Pattern.compile("<a href=\"#(.*)\">Number of Functions per Quality Level</a>");     
        // General
        numberOfClassesPattern = Pattern.compile("<td align=\"left\">Number of Classes</td>\\r?\\n<td align=\"right\">([0-9]+)</td>");
        numberOfFunctionsPattern = Pattern.compile("<td align=\"left\">Number of Functions</td>\\r?\\n<td align=\"right\">([0-9]+)</td>");

        // File details
        fileDetails = new Pattern[NB_LEVELS];
        fileDetails[BAD] = Pattern.compile("Number of Files per Quality Level\\p{ASCII}* Bad \\(0% - 19%\\) \\(([0-9]+)\\)");
        fileDetails[POOR] = Pattern.compile("Number of Files per Quality Level\\p{ASCII}* Poor \\(20% - 39%\\) \\(([0-9]+)\\)");
        fileDetails[AVERAGE] = Pattern.compile("Number of Files per Quality Level\\p{ASCII}* Average \\(40% - 59%\\) \\(([0-9]+)\\)");
        fileDetails[GOOD] = Pattern.compile("Number of Files per Quality Level\\p{ASCII}* Good \\(60% - 79%\\) \\(([0-9]+)\\)");
        fileDetails[EXCELLENT] = Pattern.compile("Number of Files per Quality Level\\p{ASCII}* Excellent \\(80% - 100%\\) \\(([0-9]+)\\)");

        // Classes details
        classDetails = new Pattern[NB_LEVELS];
        classDetails[BAD] = Pattern.compile("Number of Classes per Quality Level\\p{ASCII}* Bad \\(0% - 19%\\) \\(([0-9]+)\\)");
        classDetails[POOR] = Pattern.compile("Number of Classes per Quality Level\\p{ASCII}* Poor \\(20% - 39%\\) \\(([0-9]+)\\)");
        classDetails[AVERAGE] = Pattern.compile("Number of Classes per Quality Level\\p{ASCII}* Average \\(40% - 59%\\) \\(([0-9]+)\\)");
        classDetails[GOOD] = Pattern.compile("Number of Classes per Quality Level\\p{ASCII}* Good \\(60% - 79%\\) \\(([0-9]+)\\)");
        classDetails[EXCELLENT] = Pattern.compile("Number of Classes per Quality Level\\p{ASCII}* Excellent \\(80% - 100%\\) \\(([0-9]+)\\)");

        // Function details
        functionDetails = new Pattern[NB_LEVELS];
        functionDetails[BAD] = Pattern.compile("Number of Functions per Quality Level\\p{ASCII}* Bad \\(0% - 19%\\) \\(([0-9]+)\\)");
        functionDetails[POOR] = Pattern.compile("Number of Functions per Quality Level\\p{ASCII}* Poor \\(20% - 39%\\) \\(([0-9]+)\\)");
        functionDetails[AVERAGE] = Pattern.compile("Number of Functions per Quality Level\\p{ASCII}* Average \\(40% - 59%\\) \\(([0-9]+)\\)");
        functionDetails[GOOD] = Pattern.compile("Number of Functions per Quality Level\\p{ASCII}* Good \\(60% - 79%\\) \\(([0-9]+)\\)");
        functionDetails[EXCELLENT] = Pattern.compile("Number of Functions per Quality Level\\p{ASCII}* Excellent \\(80% - 100%\\) \\(([0-9]+)\\)");
    }
}