/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.praqma.prqa;

import java.io.Serializable;
import java.util.HashMap;
import net.praqma.prqa.exceptions.PrqaException;
import net.praqma.prqa.status.StatusCategory;

/**
 * Abstracting readings. This means that we can now have a single object for each build.
 * 
 * You can 
 * 
 * @author Praqma
 */
public interface PRQAReading extends Serializable {
    Number getReadout(StatusCategory category) throws PrqaException;
    void setReadout(StatusCategory category, Number value) throws PrqaException;
    void addNotification(String notificaction);
    HashMap<StatusCategory, Number> getReadouts(StatusCategory... categories) throws PrqaException;
}

