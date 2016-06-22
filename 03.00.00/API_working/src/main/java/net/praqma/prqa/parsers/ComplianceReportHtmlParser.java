package net.praqma.prqa.parsers;

import java.util.regex.Pattern;

/**
 *
 * @author jes,
 *
 * Refactored the method. Now the parser is self contained.
 */
public class ComplianceReportHtmlParser extends ReportHtmlParser {

    public static final Pattern numberFilesPattern;
    public static final Pattern numberLinesOfCodePattern;
    public static final Pattern totalMessagesPattern;
    public static final Pattern fileCompliancePattern;
    public static final Pattern projectCompliancePattern;
    public static final Pattern[] levelNMessages;
    // File name parser for the html file containing the messages to parse
    public static final Pattern messagesFilePattern;

    public ComplianceReportHtmlParser() { }

    public ComplianceReportHtmlParser(String fullReportPath) {
        super(fullReportPath);
    }

    static {
        // Get file name
        messagesFilePattern = Pattern.compile("<a href=\"#(.*)\">Messages per Levels</a>");
        // Get messages
        levelNMessages = new Pattern[10];
        for(int i=0; i<10; i++) {
            levelNMessages[i] = Pattern.compile(String.format("Level %s \\(([0-9]+)\\)",i));
        }
        // General
        numberFilesPattern = Pattern.compile("<td align=\"left\">Number of Files</td>\\r?\\n<td align=\"right\">([0-9]+)</td>");
        numberLinesOfCodePattern = Pattern.compile("<td align=\"left\">Lines of Code</td>\\r?\\n<td align=\"right\">([0-9]+)</td>");
        totalMessagesPattern = Pattern.compile("<td align=\"left\">Total Number of Messages</td>\\r?\\n<td align=\"right\">(\\d*)</td>");
        fileCompliancePattern = Pattern.compile("<td align=\"left\">File Compliance Index</td>\\r?\\n<td align=\"right\">(\\S*)%</td>");
        projectCompliancePattern = Pattern.compile("<td align=\"left\">Project Compliance Index</td>\\r?\\n<td align=\"right\">(\\S*)%</td>");
    }
}

