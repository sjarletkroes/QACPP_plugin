package net.praqma.prqa.status;

import java.util.HashMap;
import net.praqma.prqa.QaFrameworkVersion;
import static net.praqma.prqa.status.PRQAStatus.logger;

/**
 * This class represent a compliance status readout. 3 values, file compliance,
 * project compliance and number of messages
 *
 * @author jes, man
 */
public class PRQAFrameworkComplianceStatus extends PRQAComplianceStatus implements Comparable<PRQAComplianceStatus> {

    private int messages;
    private int messagesWithinThreshold = -1;
    private int numberOfFiles;
    private int linesOfCode;
    private Double fileCompliance;
    private Double projectCompliance;
    private HashMap<Integer, Integer> messagesByLevel = new HashMap<Integer, Integer>();
    private QaFrameworkVersion qaFrameworkVersion;

    public PRQAFrameworkComplianceStatus() {
        super();
        messagesByLevel.put(99, 0);
    }

    public PRQAFrameworkComplianceStatus(int numberOfFiles, int linesOfCode, int messages, Double fileCompliance, Double projectCompliance) {
        super();
        messagesByLevel.put(99, 0);
        logger.finest(String.format("Constructor called for class PRQAFrameworkComplianceStatus"));
        logger.finest(String.format("Ending execution of constructor - PRQAFrameworkComplianceStatus"));
    }

    /**
     * *
     * Implemented to provide a good reading
     *
     * @return
     */
    @Override
    public String toString() {
        String out = "\n";out += "\nTotal Number of Files : " + numberOfFiles + System.getProperty("line.separator");
        out += "Lines of Code : " + linesOfCode + System.getProperty("line.separator");
        out += "Project Compliance Index : " + projectCompliance + "%" + System.getProperty("line.separator");
        out += "File Compliance Index : " + fileCompliance + "%" + System.getProperty("line.separator");
        out += "Messages : " + messages + System.getProperty("line.separator");

        if (getMessagesByLevel() != null) {
            out += "\nMessages by level:\n";
            for (int i = 0; i < 10; i++) {
                if (getMessagesByLevel().containsKey(i)) {
                    out += String.format("Level %s messages (%s)%n", i, getMessagesByLevel().get(i));
                }
            }
            out += String.format("Level %s messages (%s)%n", 99, getMessagesByLevel().get(99));
        }

        if (notifications != null) {
            for (String note : notifications) {
                out += "Notify: " + note + System.getProperty("line.separator");
            }
        }

        return out;
    }

}
