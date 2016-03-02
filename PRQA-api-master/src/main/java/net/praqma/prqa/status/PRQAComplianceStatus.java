package net.praqma.prqa.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.praqma.prqa.exceptions.PrqaException;
import net.praqma.prqa.exceptions.PrqaReadingException;
import net.praqma.prqa.parsers.MessageGroup;
import static net.praqma.prqa.status.PRQAStatus.logger;

/**
 * This class represent a compliance status readout. 3 values, file compliance, project compliance and number of messages
 * @author jes, man
 */
public class PRQAComplianceStatus extends PRQAStatus implements Comparable<PRQAComplianceStatus> {

    private int messages;
    private int messagesWithinThreshold = -1;
    private Double fileCompliance;
    private Double projectCompliance;
    private HashMap<Integer,Integer> messagesByLevel = new HashMap<Integer,Integer>();
    
    public PRQAComplianceStatus() {
        for(int i=0; i<10; i++) {
            messagesByLevel.put(i,0);
        }
    }
    
    public PRQAComplianceStatus(int messages, Double fileCompliance, Double projectCompliance) {
        this();
    	logger.finest(String.format("Constructor called for class PRQAComplianceStatus"));
    	logger.finest(String.format("Input parameter messages type: %s; value: %s", "int", messages));
    	logger.finest(String.format("Input parameter fileCompliance type: %s; value: %s", fileCompliance.getClass(), fileCompliance));
    	logger.finest(String.format("Input parameter projectCompliance type: %s; value: %s", projectCompliance.getClass(), projectCompliance));
    	
        this.messages = messages;
        this.fileCompliance = fileCompliance;
        this.projectCompliance = projectCompliance;
        
        logger.finest(String.format("Ending execution of constructor - PRQAComplianceStatus"));
    }

    public HashMap<StatusCategory, Number> getThresholds() {
        if(thresholds != null) {
            logger.finest(String.format("Starting execution of method - getThresholds"));
            logger.finest(String.format("Returning HashMap<StatusCategory, Number> thresholds:"));
            for (Map.Entry<StatusCategory, Number> entry : thresholds.entrySet()) {
                logger.finest(String.format("    StatusCategory: %s, Number: %s", entry.getKey(), entry.getValue()));
            }

            return thresholds;
        } else {
            return new HashMap<StatusCategory, Number>();
        }
    }

    public void setThresholds(HashMap<StatusCategory, Number> thresholds) {
        logger.finest(String.format("Starting execution of method - setThresholds"));
        logger.finest(String.format("Input parameter thresholds type: %s, values:", thresholds.getClass()));
        for (Map.Entry<StatusCategory, Number> entry : thresholds.entrySet()) {
            logger.finest(String.format("    StatusCategory: %s, Number: %s", entry.getKey(), entry.getValue()));
        }

        this.thresholds = thresholds;

        logger.finest(String.format("Ending execution of method - setThresholds"));
    }

    public int getMessages() {
    	logger.finest(String.format("Starting execution of method - getMessages"));
    	logger.finest(String.format("Returning value: %s", messages));
    	
        return messages;
    }

    public void setMessages(int messages) {
    	logger.finest(String.format("Starting execution of method - setMessages"));
		logger.finest(String.format("Input parameter messages type: %s; value: %s", "int", messages));
		
        this.messages = messages;
        
        logger.finest(String.format("Ending execution of method - setMessages"));
    }
    
    public Double getFileCompliance() {
    	logger.finest(String.format("Starting execution of method - getFileCompliance"));
    	logger.finest(String.format("Returning value: %s", this.fileCompliance));
    	
        return this.fileCompliance;
    }
    
    public void setFileCompliance(Double fileCompliance) {
    	logger.finest(String.format("Starting execution of method - setFileCompliance"));
		logger.finest(String.format("Input parameter fileCompliance type: %s; value: %s", fileCompliance.getClass(), fileCompliance));
		
        this.fileCompliance = fileCompliance;
        
        logger.finest(String.format("Ending execution of method - setFileCompliance"));
    }
    
    public Double getProjectCompliance() {
    	logger.finest(String.format("Starting execution of method - getProjectCompliance"));
    	logger.finest(String.format("Returning value: %s", this.projectCompliance));
    	
        return this.projectCompliance;
    }
    
    public void setProjectCompliance(Double projCompliance) {
    	logger.finest(String.format("Starting execution of method - setProjectCompliance"));
		logger.finest(String.format("Input parameter projCompliance type: %s; value: %s", projCompliance.getClass(), projCompliance));
		
        this.projectCompliance = projCompliance;
        
        logger.finest(String.format("Ending execution of method - setProjectCompliance"));
    }
    
    @Override
    public Number getReadout(StatusCategory cat) throws PrqaException {
    	logger.finest(String.format("Starting execution of method - getReadout"));
    	logger.finest(String.format("Input parameter cat type: %s; value: %s", cat.getClass(), cat));
    	
    	Number output;
        switch(cat) {
            case ProjectCompliance:
                output = this.getProjectCompliance();
                
                logger.finest(String.format("Returning value: %s", output));

                return output;
            case Messages:
                output = this.getMessages();
                
                logger.finest(String.format("Returning value: %s", output));
                
                return output;
            case FileCompliance:
                output = this.getFileCompliance();
                
                logger.finest(String.format("Returning this.getFileCompliance(): %s", this.getFileCompliance()));
                
                return output;
            default:
            	PrqaReadingException exception = new PrqaReadingException(String.format("Didn't find category %s for class %s", cat, this.getClass()));
    			
    			logger.severe(String.format("Exception thrown type: %s; message: %s", exception.getClass(), exception.getMessage()));
    			
    			throw exception;
        }
    }   
    
    @Override
    public void setReadout(StatusCategory category, Number value) throws PrqaException {
    	logger.finest(String.format("Starting execution of method - setReadout"));
    	logger.finest(String.format("Input parameter category type: %s; value: %s", category.getClass(), category));
    	logger.finest(String.format("Input parameter value type: %s; value: %s", value.getClass(), value));
    	
        switch(category) {
            case ProjectCompliance:
            	double prjCompliance = value.doubleValue();
            	
            	logger.finest(String.format("Setting projectCompliance to: %s.", prjCompliance));
            	
                setProjectCompliance(prjCompliance);
                
                logger.finest(String.format("Ending execution of method - setReadout"));
                
                break;
            case Messages:
            	int msgs = value.intValue();
            	
            	logger.finest(String.format("Setting messages to: %s.", msgs));
            	
                setMessages(msgs);
                
                logger.finest(String.format("Ending execution of method - setReadout"));
                
                break;
            case FileCompliance:
            	double fileCompl = value.doubleValue();
            	
            	logger.finest(String.format("Setting fileCompliance to: %s.", fileCompl));
            	
                setFileCompliance(fileCompl);
                
                logger.finest(String.format("Ending execution of method - setReadout"));
                
                break;
            default:
            	PrqaReadingException exception = new PrqaReadingException(String.format("Could not set value of %s for category %s in class %s",value,category,this.getClass()));
    			
    			logger.severe(String.format("Exception thrown type: %s; message: %s", exception.getClass(), exception.getMessage()));
    			
    			throw exception;
        }
    }
    
    /***
     * Implemented to provide a good reading 
     * @return 
     */
    @Override
    public String toString() {
        String out = "";       
        out += "Project Compliance Index : " + projectCompliance + "%" + System.getProperty("line.separator");
        out += "File Compliance Index : " + fileCompliance + "%" + System.getProperty("line.separator");
        out += "Messages : " + messages + System.getProperty("line.separator") ;
        
        if(getMessagesByLevel() != null ) {
            out += "Messages by level:\n";
            for(int i=0; i<10; i++) {
                if(getMessagesByLevel().containsKey(i)) {
                    out += String.format("Level %s messages (%s)%n", i, getMessagesByLevel().get(i)); 
                }
            }
        }
        
        if(notifications != null) {
            for(String note : notifications) {
                out += "Notify: " + note + System.getProperty("line.separator");
            }
        }
        
        return out;       
    }
    
    
    /***
     * Implemented this to decide which one is 'better than last'.
     * @param o
     * @return 
     */
    @Override
    public int compareTo(PRQAComplianceStatus o) {
        if(this == o) {
            return 0;
        }
        
        if(o == null) {
            return 1;
        }
        
        if(this.projectCompliance < o.getProjectCompliance() || this.fileCompliance < o.getProjectCompliance() || this.messages > o.getMessages()) {
           return -1; 
        } else if (this.projectCompliance > o.getProjectCompliance() || this.fileCompliance > o.getFileCompliance() || this.messages < o.getMessages()) {
            return 1;
        } else {
            return 0;
        }       
    }
    
    @Override
    public boolean isValid() {
    	logger.finest(String.format("Starting execution of method - isValid"));
    	
    	boolean result = this.fileCompliance != null && this.projectCompliance != null;
    	
    	logger.finest(String.format("Returning value: %s", result));
    	
        return result;
    }
    
    public static PRQAComplianceStatus createEmptyResult() {
    	logger.finest(String.format("Starting execution of method - createEmptyResult"));
    	
    	PRQAComplianceStatus output = new PRQAComplianceStatus(0, new Double(0), new Double(0));
    	
    	logger.finest(String.format("Returning value: %s", output));
    	
        return output;
    }

    @Override
    public String toHtml() {        
        StringBuilder sb = new StringBuilder();
        sb.append("<table width=\"100%\" cellspacing=\"0\" cellpadding=\"5\" border=\"0\" align=\"center\">");
        sb.append("<tbody>");
        sb.append("<tr>");
        sb.append("<td width=\"50%\" valign=\"top\">");
        sb.append("<div align=\"center\">");
        sb.append("<div align=\"left\" style=\"border: 1px solid #999999; background-color: #F0F0F0; padding: 4px; font-weight: bold;\">");
        sb.append("Compliance Summary");
        sb.append("</div>");
        sb.append("<table class=\"pane\" style=\"margin-top: 0px; border-top: none;\">");
        sb.append("<tr>");
        sb.append("<td>");
        sb.append("<div align=\"center\">");
        sb.append("<table class=\"pane\" style=\"margin-top: 0px;\">");
        sb.append("<tr style=\"border: 1px solid #BBB\">");
        sb.append("<th style=\"background-color: #F0F0F0;\" align=\"left\">Messages within threshold</th>");
        sb.append(String.format("<td>%s</td>", this.getMessagesWithinThreshold()));
        sb.append("<tr style=\"border: 1px solid #BBB\">");
        sb.append("<th style=\"background-color: #F0F0F0;\" align=\"left\">All messages</th>");
        sb.append(String.format("<td>%s</td>", this.getMessages()));
        sb.append("<tr style=\"border: 1px solid #BBB\">");
        sb.append("<th style=\"background-color: #F0F0F0;\" align=\"left\">Project Compliance</th>");
        sb.append(String.format("<td>%s</td>", this.getProjectCompliance()));
        sb.append("<tr style=\"border: 1px solid #BBB\">");
        sb.append("<th style=\"background-color: #F0F0F0;\" align=\"left\">File Compliance</th>");
        sb.append(String.format("<td>%s</td>", this.getFileCompliance()));
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td width=\"50%\" valign=\"top\">");
        sb.append("</td>");
        sb.append("</tr>");
        sb.append("</tbody>");
        sb.append("</table>");
        
        if(getMessagesByLevel() != null && getMessagesByLevel().size() > 0) {
            sb.append("<table width=\"100%\" cellspacing=\"0\" cellpadding=\"5\" border=\"0\" align=\"center\">");
            sb.append("<tbody>");
            sb.append("<tr>");
            sb.append("<td width=\"50%\" valign=\"top\">");
            sb.append("<div align=\"center\">");
            sb.append("<div align=\"left\" style=\"border: 1px solid #999999; background-color: #F0F0F0; padding: 4px; font-weight: bold;\">");
            sb.append("Messages Summary");
            sb.append("</div>");
            sb.append("<table class=\"pane\" style=\"margin-top: 0px; border-top: none;\">");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<div align=\"center\">");
            sb.append("<table id=\"statistics\" class=\"pane\" style=\"margin-top: 0px;\">");
            sb.append("<tr style=\"background-color: #F0F0F0; border: 1px solid #BBB\">");
            sb.append("<th align=\"left\">Level</th>");
            sb.append("<th align=\"left\">Number of messages</th>");
            sb.append("</tr>");

            for(int i : getMessagesByLevel().keySet()) {
                sb.append("<tr style=\"border: 1px solid #BBB\">");
                sb.append(String.format("<th align=\"left\">%s</th>", i));
                sb.append(String.format("<td>%s</td>", this.getMessagesByLevel().get(i)));
                sb.append("</tr>");
            }
            sb.append("</table>");
            sb.append("</div>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</div>");
            sb.append("</td>");
            sb.append("<td width=\"50%\" valign=\"top\">");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</tbody>");
            sb.append("</table>");
        }
        return sb.toString();
    }   

    /**
     * @return the messagesByLevel
     */
    public HashMap<Integer,Integer> getMessagesByLevel() {
        return messagesByLevel;
    }

    /**
     * @param messagesByLevel the messagesByLevel to set
     */
    public void setMessagesByLevel(HashMap<Integer,Integer> messagesByLevel) {
        this.messagesByLevel = messagesByLevel;
    }
    
    public boolean allEmpty() {
        return getMessagesByLevel() == null || getMessageCount(0) == 0;
    }
    
    public int getMessageCount(int threshold) {
        int cnt = 0;
        
        for (int i=threshold; i<=9; i++) {
            if(getMessagesByLevel() != null && getMessagesByLevel().containsKey(i)) {
                cnt += getMessagesByLevel().get(i);
            }
        }
        return cnt;
    }

    /**
     * @return the messagesWithinThreshold
     */
    public int getMessagesWithinThreshold() {
        if(messagesWithinThreshold == -1 || messagesWithinThreshold  == 0) {
            return messages;
        }
        return messagesWithinThreshold;
    }
    
    /**
     * @param messagesWithinThreshold the messagesWithinThreshold to set
     */
    public void setMessagesWithinThreshold(int messagesWithinThreshold) {        
        this.messagesWithinThreshold = messagesWithinThreshold;
    }

    public List<MessageGroup> getMessagesGroups() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setMessagesWithinThresholdForEachMessageGroup(int messagesWithinThreshold) {     
        this.messagesWithinThreshold = messagesWithinThreshold;//à faire?
    }
}
