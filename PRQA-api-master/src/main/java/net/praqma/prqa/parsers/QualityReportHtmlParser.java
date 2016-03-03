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
    
    public static final Pattern numberFilesPattern;
    public static final Pattern numberLinesOfCodePattern;
    public static final Pattern numberOfFunctionsPattern;
    public static final Pattern numberOfClassesPattern;    
    public static final Pattern numberOfFunctionMetricsPattern;   
    public static final Pattern numberOfClassMetricsPattern;   
    public static final Pattern numberOfFileMetricsPattern; 
    public static final Pattern[] fileDetails;
    public static final Pattern[] classDetails;
    public static final Pattern[] functionDetails;

    
    public QualityReportHtmlParser() { }
           
    public QualityReportHtmlParser(String fullReportPath) {
        super(fullReportPath);
    }
    
    static {      
        // General
        numberFilesPattern = Pattern.compile("<td align=\"left\">Total Number of Files</td>\\r?\\n<td align=\"right\">([0-9]+)</td>");  
        numberLinesOfCodePattern = Pattern.compile("<td align=\"left\">Lines of Code</td>\\r?\\n<td align=\"right\">([0-9]+)</td>");  
        numberOfFileMetricsPattern = Pattern.compile("<td align=\"left\">Number of File Metrics</td>\\r?\\n<td align=\"right\">([0-9]+)</td>");    
        numberOfClassesPattern = Pattern.compile("<td align=\"left\">Number of Classes</td>\\r?\\n<td align=\"right\">([0-9]+)</td>");          
        numberOfClassMetricsPattern = Pattern.compile("<td align=\"left\">Number of Class Metrics</td>\\r?\\n<td align=\"right\">([0-9]+)</td>");      
        numberOfFunctionsPattern = Pattern.compile("<td align=\"left\">Number of Functions</td>\\r?\\n<td align=\"right\">([0-9]+)</td>"); 
        numberOfFunctionMetricsPattern = Pattern.compile("<td align=\"left\">Number of Function Metrics</td>\\r?\\n<td align=\"right\">([0-9]+)</td>"); 
        
        // File detail
        fileDetails = new Pattern[5];
        fileDetails[0] = Pattern.compile("Number of Files per Quality Level\\p{ASCII}* Bad \\(0% - 19%\\) \\(([0-9]+)\\)"); 
        fileDetails[1] = Pattern.compile("Number of Files per Quality Level\\p{ASCII}* Poor \\(20% - 39%\\) \\(([0-9]+)\\)"); 
        fileDetails[2] = Pattern.compile("Number of Files per Quality Level\\p{ASCII}* Average \\(40% - 59%\\) \\(([0-9]+)\\)"); 
        fileDetails[3] = Pattern.compile("Number of Files per Quality Level\\p{ASCII}* Good \\(60% - 79%\\) \\(([0-9]+)\\)"); 
        fileDetails[4] = Pattern.compile("Number of Files per Quality Level\\p{ASCII}* Excellent (80% - 100%) \\(([0-9]+)\\)"); 
        
        // Classes detail
        classDetails = new Pattern[5];
        classDetails[0] = Pattern.compile("Number of Classes per Quality Level\\p{ASCII}* Bad \\(0% - 19%\\) \\(([0-9]+)\\)"); 
        classDetails[1] = Pattern.compile("Number of Classes per Quality Level\\p{ASCII}* Poor \\(20% - 39%\\) \\(([0-9]+)\\)"); 
        classDetails[2] = Pattern.compile("Number of Classes per Quality Level\\p{ASCII}* Average \\(40% - 59%\\) \\(([0-9]+)\\)"); 
        classDetails[3] = Pattern.compile("Number of Classes per Quality Level\\p{ASCII}* Good \\(60% - 79%\\) \\(([0-9]+)\\)"); 
        classDetails[4] = Pattern.compile("Number of Classes per Quality Level\\p{ASCII}* Excellent \\(80% - 100%\\) \\(([0-9]+)\\)"); 
        
        // Function detail
        functionDetails = new Pattern[5];
        functionDetails[0] = Pattern.compile("Number of Functions per Quality Level\\p{ASCII}* Bad \\(0% - 19%\\) \\(([0-9]+)\\)"); 
        functionDetails[1] = Pattern.compile("Number of Functions per Quality Level\\p{ASCII}* Poor \\(20% - 39%\\) \\(([0-9]+)\\)"); 
        functionDetails[2] = Pattern.compile("Number of Functions per Quality Level\\p{ASCII}* Average \\(40% - 59%\\) \\(([0-9]+)\\)"); 
        functionDetails[3] = Pattern.compile("Number of Functions per Quality Level\\p{ASCII}* Good \\(60% - 79%\\) \\(([0-9]+)\\)"); 
        functionDetails[4] = Pattern.compile("Number of Functions per Quality Level\\p{ASCII}* Excellent \\(80% - 100%\\) \\(([0-9]+)\\)"); 
    }
}