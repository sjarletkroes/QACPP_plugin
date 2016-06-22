package net.praqma.prqa.parsers;

import java.util.regex.Pattern;

/**
 *
 * @author jes,
 *
 * Refactored the method. Now the parser is self contained.
 */
public class PRQAFComplianceReportHtmlParser extends ReportHtmlParser {

    public static final Pattern numberFilesPattern;
    public static final Pattern numberLinesOfCodePattern;
    public static final Pattern totalMessagesPattern;
    public static final Pattern fileCompliancePattern;
    public static final Pattern projectCompliancePattern;
    public static final Pattern levelNMessages;

    public PRQAFComplianceReportHtmlParser() { }

    public PRQAFComplianceReportHtmlParser(String fullReportPath) {
        super(fullReportPath);
    }

    static {
        // CAUTION!!!!
        // Use parseString(String str, Pattern pattern) to get a list of
        // messages (0 - 9 & 99) then fill status with them
        levelNMessages = Pattern.compile("<td><b>([0-9]+)</b></td>");
        // General
        numberFilesPattern = Pattern.compile("<td>Number of Files</td><td>([0-9]+)</td>");
        numberLinesOfCodePattern = Pattern.compile("<td>Lines of Code \\(source files only\\)</td><td>([0-9]+)</td>");
        totalMessagesPattern = Pattern.compile("<td>Rule Violation Count</td><td>(\\d*)</td>");
        fileCompliancePattern = Pattern.compile("<td>File Compliance Index</td><td>(\\S*)%</td>");
        projectCompliancePattern = Pattern.compile("<td>Project Compliance Index</td><td>(\\S*)%</td>");
    }
}
