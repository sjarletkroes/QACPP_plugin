/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.praqma.prqa.status;

import java.util.HashMap;
import net.praqma.prqa.exceptions.PrqaException;
import net.praqma.prqa.exceptions.PrqaReadingException;

/**
 *
 * @author T0166941
 */
public class PRQAQualityStatus extends PRQAStatus implements Comparable<PRQAQualityStatus> {

    public final int BAD = 0, POOR = 1, AVERAGE = 2, GOOD = 3, EXCELLENT = 4;
    
    private Double numberOfFiles;
    private Double linesOfCode;
    private Double numberOfFunctions;
    private Double numberOfClasses;
    private Double numberOfFunctionMetrics;
    private Double numberOfClassMetrics;
    private Double numberOfFileMetrics;
    
    private HashMap<Integer,Integer> fileDetails = new HashMap<Integer,Integer>();
    private HashMap<Integer,Integer> classDetails = new HashMap<Integer,Integer>();
    private HashMap<Integer,Integer> functionDetails = new HashMap<Integer,Integer>();
    
    public PRQAQualityStatus() {
        fileDetails.put(BAD,0);
        fileDetails.put(POOR,0);
        fileDetails.put(AVERAGE,0);
        fileDetails.put(GOOD,0);
        fileDetails.put(EXCELLENT,0);
        
        classDetails.put(BAD,0);
        classDetails.put(POOR,0);
        classDetails.put(AVERAGE,0);
        classDetails.put(GOOD,0);
        classDetails.put(EXCELLENT,0);
        
        functionDetails.put(BAD,0);
        functionDetails.put(POOR,0);
        functionDetails.put(AVERAGE,0);
        functionDetails.put(GOOD,0);
        functionDetails.put(EXCELLENT,0);
    }

    public PRQAQualityStatus(Double numberOfFiles, Double linesOfCode, Double numberOfFunctions, Double numberOfClasses, Double numberOfFunctionMetrics, Double numberOfClassMetrics, Double numberOfFileMetrics, Double numberOfFilesBad) {
        this();
    	logger.finest(String.format("Constructor called for class PRQAQualityStatus"));
    	logger.finest(String.format("Input parameter numberOfFiles type: %s; value: %s", numberOfFiles.getClass(), numberOfFiles));
    	logger.finest(String.format("Input parameter linesOfCode type: %s; value: %s", linesOfCode.getClass(), linesOfCode));
    	logger.finest(String.format("Input parameter numberOfFunctions type: %s; value: %s", numberOfFunctions.getClass(), numberOfFunctions));
    	logger.finest(String.format("Input parameter numberOfClasses type: %s; value: %s", numberOfClasses.getClass(), numberOfClasses));
    	logger.finest(String.format("Input parameter numberOfFunctionMetrics type: %s; value: %s", numberOfFunctionMetrics.getClass(), numberOfFunctionMetrics));
    	logger.finest(String.format("Input parameter numberOfClassMetrics type: %s; value: %s", numberOfClassMetrics.getClass(), numberOfClassMetrics));
    	logger.finest(String.format("Input parameter numberOfFileMetrics type: %s; value: %s", numberOfFileMetrics.getClass(), numberOfFileMetrics));
    	logger.finest(String.format("Input parameter numberOfFilesBad type: %s; value: %s", numberOfFilesBad.getClass(), numberOfFilesBad));
    	
        this.numberOfFiles = numberOfFiles;
        this.linesOfCode = linesOfCode;
        this.numberOfFunctions = numberOfFunctions;
        this.numberOfClasses = numberOfClasses;
        this.numberOfFunctionMetrics = numberOfFunctionMetrics;
        this.numberOfClassMetrics = numberOfClassMetrics;
        this.numberOfFileMetrics = numberOfFileMetrics;
        
        logger.finest(String.format("Ending execution of constructor - PRQAQualityStatus"));
    }

    public Double getLinesOfCode() {
    	logger.finest(String.format("Starting execution of method - getLinesOfCode"));
    	logger.finest(String.format("Returning value: %s", linesOfCode));
        return linesOfCode;
    }

    public void setLinesOfCode(Double linesOfCode) {
    	logger.finest(String.format("Starting execution of method - setLinesOfCode"));
        logger.finest(String.format("Input parameter linesOfCode type: %s; value: %s", linesOfCode.getClass(), linesOfCode));
        this.linesOfCode = linesOfCode;
        logger.finest(String.format("Ending execution of method - setLinesOfCode"));
    }

    public Double getNumberOfFunctions() {
    	logger.finest(String.format("Starting execution of method - getFunctions"));
    	logger.finest(String.format("Returning value: %s", numberOfFunctions));
        return numberOfFunctions;
    }

    public void setNumberOfFunctions(Double numberOfFunctions) {
    	logger.finest(String.format("Starting execution of method - setFunctions"));
        logger.finest(String.format("Input parameter functions type: %s; value: %s", numberOfFunctions.getClass(), numberOfFunctions));
        this.numberOfFunctions = numberOfFunctions;
        logger.finest(String.format("Ending execution of method - setFunctions"));
    }

    public Double getNumberOfClasses() {
    	logger.finest(String.format("Starting execution of method - getClasses"));
    	logger.finest(String.format("Returning value: %s", numberOfClasses));
        return numberOfClasses;
    }

    public void setNumberOfClasses(Double numberOfClasses) {
    	logger.finest(String.format("Starting execution of method - setClasses"));
        logger.finest(String.format("Input parameter classes type: %s; value: %s", numberOfClasses.getClass(), numberOfClasses));
        this.numberOfClasses = numberOfClasses;
        logger.finest(String.format("Ending execution of method - setClasses"));
    }

    public Double getNumberOfFunctionMetrics() {
    	logger.finest(String.format("Starting execution of method - getFunctionMetrics"));
    	logger.finest(String.format("Returning value: %s", numberOfFunctionMetrics));
        return numberOfFunctionMetrics;
    }

    public void setNumberOfFunctionMetrics(Double numberOfFunctionMetrics) {
    	logger.finest(String.format("Starting execution of method - setFunctionMetrics"));
        logger.finest(String.format("Input parameter functionMetrics type: %s; value: %s", numberOfFunctionMetrics.getClass(), numberOfFunctionMetrics));
        this.numberOfFunctionMetrics = numberOfFunctionMetrics;
        logger.finest(String.format("Ending execution of method - setFunctionMetrics"));
    }

    public Double getNumberOfClassMetrics() {
    	logger.finest(String.format("Starting execution of method - getClassMetrics"));
    	logger.finest(String.format("Returning value: %s", numberOfClassMetrics));
        return numberOfClassMetrics;
    }

    public void setNumberOfClassMetrics(Double numberOfClassMetrics) {
    	logger.finest(String.format("Starting execution of method - setClassMetrics"));
        logger.finest(String.format("Input parameter classMetrics type: %s; value: %s", numberOfClassMetrics.getClass(), numberOfClassMetrics));
        this.numberOfClassMetrics = numberOfClassMetrics;
        logger.finest(String.format("Ending execution of method - setClassMetrics"));
    }

    public Double getNumberOfFileMetrics() {
    	logger.finest(String.format("Starting execution of method - getFileMetrics"));
    	logger.finest(String.format("Returning value: %s", numberOfFileMetrics));
        return numberOfFileMetrics;
    }

    public void setNumberOfFileMetrics(Double numberOfFileMetrics) {
    	logger.finest(String.format("Starting execution of method - setFileMetrics"));
        logger.finest(String.format("Input parameter fileMetrics type: %s; value: %s", numberOfFileMetrics.getClass(), numberOfFileMetrics));
        this.numberOfFileMetrics = numberOfFileMetrics;
        logger.finest(String.format("Ending execution of method - setFileMetrics"));
    }

    public Double getNumberOfFiles() {
    	logger.finest(String.format("Starting execution of method - getNumberOfFiles"));
    	logger.finest(String.format("Returning value: %s", numberOfFiles));
        return numberOfFiles;
    }

    public void setNumberOfFiles(Double numberOfFiles) {
    	logger.finest(String.format("Starting execution of method - setNumberOfFiles"));
        logger.finest(String.format("Input parameter files type: %s; value: %s", numberOfFiles.getClass(), numberOfFiles));
        this.numberOfFiles = numberOfFiles;
        logger.finest(String.format("Ending execution of method - setNumberOfFiles"));
    }

    public HashMap<Integer, Integer> getFileDetails() {
        return fileDetails;
    }

    public void setFileDetails(HashMap<Integer, Integer> fileDetails) {
        this.fileDetails = fileDetails;
    }

    public HashMap<Integer, Integer> getClassDetails() {
        return classDetails;
    }

    public void setClassDetails(HashMap<Integer, Integer> classDetails) {
        this.classDetails = classDetails;
    }

    public HashMap<Integer, Integer> getFunctionDetails() {
        return functionDetails;
    }

    public void setFunctionDetails(HashMap<Integer, Integer> functionDetails) {
        this.functionDetails = functionDetails;
    }
    
    @Override
    public Number getReadout(StatusCategory cat) throws PrqaException {
    	logger.finest(String.format("Starting execution of method - getReadout"));
    	logger.finest(String.format("Input parameter cat type: %s; value: %s", cat.getClass(), cat));
    	
    	Number output;
        switch(cat) {
            case TotalNumberOfFiles:
                output = this.getNumberOfFiles();
                
                logger.finest(String.format("Returning value: %s", output));

                return output;
            case LinesOfCode:
                output = this.getLinesOfCode();
                
                logger.finest(String.format("Returning value: %s", output));

                return output;
            case NumberOfFunctions:
                output = this.getNumberOfFunctions();
                
                logger.finest(String.format("Returning value: %s", output));

                return output;
            case NumberOfClasses:
                output = this.getNumberOfClasses();
                
                logger.finest(String.format("Returning value: %s", output));

                return output;
            case NumberOfFunctionMetrics:
                output = this.getNumberOfFunctionMetrics();
                
                logger.finest(String.format("Returning value: %s", output));

                return output;
            case NumberOfClassMetrics:
                output = this.getNumberOfClassMetrics();
                
                logger.finest(String.format("Returning value: %s", output));

                return output;
            case NumberOfFileMetrics:
                output = this.getNumberOfFileMetrics();
                
                logger.finest(String.format("Returning value: %s", output));

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
            case TotalNumberOfFiles:
                double nbFiles = value.doubleValue();
            	
            	logger.finest(String.format("Setting NumberOfFiles to: %s.", nbFiles));
            	
                this.setNumberOfFiles(nbFiles);
                
                logger.finest(String.format("Ending execution of method - setReadout"));
                
                break;
            case LinesOfCode:
                double nbLinesOfCode = value.doubleValue();
            	
            	logger.finest(String.format("Setting LinesOfCode to: %s.", nbLinesOfCode));
            	
                this.setLinesOfCode(nbLinesOfCode);
                
                logger.finest(String.format("Ending execution of method - setReadout"));
                
                break;
            case NumberOfFunctions:
                double nbNumberOfFunctions = value.doubleValue();
            	
            	logger.finest(String.format("Setting NumberOfFunctions to: %s.", nbNumberOfFunctions));
            	
                this.setLinesOfCode(nbNumberOfFunctions);
                
                logger.finest(String.format("Ending execution of method - setReadout"));
                
                break;
            case NumberOfClasses:
                double nbNumberOfClasses = value.doubleValue();
            	
            	logger.finest(String.format("Setting NumberOfClasses to: %s.", nbNumberOfClasses));
            	
                this.setLinesOfCode(nbNumberOfClasses);
                
                logger.finest(String.format("Ending execution of method - setReadout"));
                
                break;
            case NumberOfFunctionMetrics:
                double nbNumberOfFunctionMetrics = value.doubleValue();
            	
            	logger.finest(String.format("Setting NumberOfFunctionMetrics to: %s.", nbNumberOfFunctionMetrics));
            	
                this.setLinesOfCode(nbNumberOfFunctionMetrics);
                
                logger.finest(String.format("Ending execution of method - setReadout"));
                
                break;
            case NumberOfClassMetrics:
                double nbNumberOfClassMetrics = value.doubleValue();
            	
            	logger.finest(String.format("Setting NumberOfClassMetrics to: %s.", nbNumberOfClassMetrics));
            	
                this.setLinesOfCode(nbNumberOfClassMetrics);
                
                logger.finest(String.format("Ending execution of method - setReadout"));
                
                break;
            case NumberOfFileMetrics:
                double nbNumberOfFileMetrics = value.doubleValue();
            	
            	logger.finest(String.format("Setting NumberOfFileMetrics to: %s.", nbNumberOfFileMetrics));
            	
                this.setLinesOfCode(nbNumberOfFileMetrics);
                
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
        out += "Total Number of Files : " + numberOfFiles + System.getProperty("line.separator") ;
        out += "Lines of Code : " + linesOfCode + System.getProperty("line.separator") ;
        out += "Number of File Metrics : " + numberOfFileMetrics + System.getProperty("line.separator") ;
        out += "Number of Classes : " + numberOfClasses + System.getProperty("line.separator") ;
        out += "Number of Class Metrics : " + numberOfClassMetrics + System.getProperty("line.separator") ;
        out += "Number of Functions : " + numberOfFunctions + System.getProperty("line.separator") ;
        out += "Number of Function Metrics : " + numberOfFunctionMetrics + System.getProperty("line.separator") ;
        
        if(getFileDetails() != null ) {
            out += "\nNumber of Files per Quality Level:\n";
            if(getFileDetails().containsKey(BAD)) out += "\tBad (0% - 19%): " + getFileDetails().get(BAD) + System.getProperty("line.separator");
            if(getFileDetails().containsKey(POOR)) out += "\tPoor (20% - 39%): " + getFileDetails().get(POOR) + System.getProperty("line.separator");
            if(getFileDetails().containsKey(AVERAGE)) out += "\tAverage (40% - 59%): " + getFileDetails().get(AVERAGE) + System.getProperty("line.separator");
            if(getFileDetails().containsKey(GOOD)) out += "\tGood (60% - 79%): " + getFileDetails().get(GOOD) + System.getProperty("line.separator");
            if(getFileDetails().containsKey(EXCELLENT)) out += "\tExcellent (80% - 100%): " + getFileDetails().get(EXCELLENT) + System.getProperty("line.separator");
        }    
        
        if(getClassDetails() != null ) {
            out += "\nNumber of Classes per Quality Level:\n";
            if(getClassDetails().containsKey(BAD)) out += "\tBad (0% - 19%): " + getClassDetails().get(BAD) + System.getProperty("line.separator");
            if(getClassDetails().containsKey(POOR)) out += "\tPoor (20% - 39%): " + getClassDetails().get(POOR) + System.getProperty("line.separator");
            if(getClassDetails().containsKey(AVERAGE)) out += "\tAverage (40% - 59%): " + getClassDetails().get(AVERAGE) + System.getProperty("line.separator");
            if(getClassDetails().containsKey(GOOD)) out += "\tGood (60% - 79%): " + getClassDetails().get(GOOD) + System.getProperty("line.separator");
            if(getClassDetails().containsKey(EXCELLENT)) out += "\tExcellent (80% - 100%): " + getClassDetails().get(EXCELLENT) + System.getProperty("line.separator");
        }   
        
        if(getFunctionDetails() != null ) {
            out += "\nNumber of Functions per Quality Level:\n";
            if(getFunctionDetails().containsKey(BAD)) out += "\tBad (0% - 19%): " + getFunctionDetails().get(BAD) + System.getProperty("line.separator");
            if(getFunctionDetails().containsKey(POOR)) out += "\tPoor (20% - 39%): " + getFunctionDetails().get(POOR) + System.getProperty("line.separator");
            if(getFunctionDetails().containsKey(AVERAGE)) out += "\tAverage (40% - 59%): " + getFunctionDetails().get(AVERAGE) + System.getProperty("line.separator");
            if(getFunctionDetails().containsKey(GOOD)) out += "\tGood (60% - 79%): " + getFunctionDetails().get(GOOD) + System.getProperty("line.separator");
            if(getFunctionDetails().containsKey(EXCELLENT)) out += "\tExcellent (80% - 100%): " + getFunctionDetails().get(EXCELLENT) + System.getProperty("line.separator");
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
    public int compareTo(PRQAQualityStatus o) {
        if(this == o) {
            return 0;
        }
        
        if(o == null) {
            return 1;
        }        
        
        if (this.fileDetails.size() != o.fileDetails.size() ||
                this.functionDetails.size() != o.functionDetails.size() ||
                this.classDetails.size() != o.classDetails.size()) {
            return -1;
        }
        for (Integer key : this.fileDetails.keySet()) {
            if (this.fileDetails.get(key).compareTo(o.fileDetails.get(key)) > 0 ||
                    this.functionDetails.get(key).compareTo(o.functionDetails.get(key)) > 0 ||
                    this.classDetails.get(key).compareTo(o.classDetails.get(key)) > 0) {
                return -1;
            }
        }
        return 1;     
    }
    
    @Override
    public boolean isValid() {
    	logger.finest(String.format("Starting execution of method - isValid"));
    	
    	boolean result = this.numberOfClassMetrics != null && this.numberOfClasses != null &&
                this.numberOfFunctionMetrics != null && this.numberOfFunctions != null &&
                this.numberOfFileMetrics != null && this.linesOfCode != null &&
                this.fileDetails != null && this.functionDetails != null && this.classDetails != null;
    	
    	logger.finest(String.format("Returning value: %s", result));
    	
        return result;
    }
    
    public static PRQAQualityStatus createEmptyResult() {
    	logger.finest(String.format("Starting execution of method - createEmptyResult"));
    	
    	PRQAQualityStatus output = new PRQAQualityStatus(new Double(0), new Double(0), new Double(0), new Double(0), new Double(0), new Double(0), new Double(0), new Double(0));
    	
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
                                    sb.append("General Information");
                                sb.append("</div>");
                                sb.append("<table class=\"pane\" style=\"margin-top: 0px; border-top: none;\">");
                                    sb.append("<tr>");
                                        sb.append("<td>");
                                            sb.append("<div align=\"center\">");
                                                sb.append("<table class=\"pane\" style=\"margin-top: 0px;\">");
                                                    sb.append("<tr style=\"border: 1px solid #BBB\">");
                                                        sb.append("<th style=\"background-color: #F0F0F0;\" align=\"left\">Total Number of Files</th>");
                                                        sb.append(String.format("<td>%s</td>", this.getNumberOfFiles()));
                                                    sb.append("<tr style=\"border: 1px solid #BBB\">");
                                                        sb.append("<th style=\"background-color: #F0F0F0;\" align=\"left\">Lines of Code</th>");
                                                        sb.append(String.format("<td>%s</td>", this.getLinesOfCode()));
                                                    sb.append("<tr style=\"border: 1px solid #BBB\">");
                                                        sb.append("<th style=\"background-color: #F0F0F0;\" align=\"left\">Number of Functions</th>");
                                                        sb.append(String.format("<td>%s</td>", this.getNumberOfFunctions()));
                                                    sb.append("<tr style=\"border: 1px solid #BBB\">");
                                                        sb.append("<th style=\"background-color: #F0F0F0;\" align=\"left\">Number of Classes</th>");
                                                        sb.append(String.format("<td>%s</td>", this.getNumberOfClasses()));
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
        
        if(this.getFileDetails() != null && this.getFileDetails().size() > 0 &&
                this.getClassDetails() != null && this.getClassDetails().size() > 0 && 
                this.getFunctionDetails() != null && this.getFunctionDetails().size() > 0) {
            
            sb.append("<table width=\"100%\" cellspacing=\"0\" cellpadding=\"5\" border=\"0\" align=\"center\">");
                sb.append("<tbody>");
                    sb.append("<tr>");
                        sb.append("<td width=\"50%\" valign=\"top\">");
                            sb.append("<div align=\"center\">");
                                sb.append("<div align=\"left\" style=\"border: 1px solid #999999; background-color: #F0F0F0; padding: 4px; font-weight: bold;\">");
                                    sb.append("Quality Summary");
                                sb.append("</div>");
                                sb.append("<table class=\"pane\" style=\"margin-top: 0px; border-top: none;\">");
                                    sb.append("<tr>");
                                        sb.append("<td>");
                                            sb.append("<div align=\"center\">");
                                                sb.append("<table id=\"statistics\" class=\"pane\" style=\"margin-top: 0px;\">");
                                                    sb.append("<tr style=\"background-color: #F0F0F0; border: 1px solid #BBB\">");
                                                        sb.append("<th align=\"left\"></th>");
                                                        sb.append("<th align=\"left\">File</th>");
                                                        sb.append("<th align=\"left\">Function</th>");
                                                        sb.append("<th align=\"left\">Class</th>");
                                                    sb.append("</tr>");
                                                    
            for(int i : getFileDetails().keySet()) {
                String level = "";
                switch(i) {
                    case 0: level = "Bad"; 
                    break;
                    case 1: level = "Poor";
                    break;
                    case 2: level = "Average";
                    break;
                    case 3: level = "Good";
                    break;
                    case 4: level = "Excellent";
                }
                                                    sb.append("<tr style=\"border: 1px solid #BBB\">");
                                                        sb.append(String.format("<th align=\"left\">%s</th>", level));
                                                        sb.append(String.format("<td>%s</td>", this.getFileDetails().get(i)));
                                                        sb.append(String.format("<td>%s</td>", this.getFunctionDetails().get(i)));
                                                        sb.append(String.format("<td>%s</td>", this.getClassDetails().get(i)));
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
}
