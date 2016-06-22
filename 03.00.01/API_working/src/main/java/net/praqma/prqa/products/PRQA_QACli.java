/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.praqma.prqa.products;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;
import net.praqma.prqa.exceptions.PrqaSetupException;

/**
 *
 * @author T0166941
 */
public class PRQA_QACli implements Product {
    
    private static final long serialVersionUID = 1L;
    private static final Logger logger  = Logger.getLogger(QACli.class.getName());
    public static final String PRQAF_BIN_PATH = "QAFBINPATH";
    public static final String WORKSPACE_PATH = "WORKSPACEPATH";
    public static final String PRQAF_INSTALL_PATH = "QAFINSTALLPATH";

    public PRQA_QACli() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getProductVersion(HashMap<String, String> environment, File currentDirectory, boolean isUnix) throws PrqaSetupException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
