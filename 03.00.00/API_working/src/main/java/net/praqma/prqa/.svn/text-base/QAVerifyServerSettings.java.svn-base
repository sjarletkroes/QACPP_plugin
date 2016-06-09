/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.praqma.prqa;

import java.io.Serializable;

/**
 *
 * @author Praqma
 */
public class QAVerifyServerSettings implements Serializable{
        //QAVerify server information:
    private static final long serialVersionUID = 1L;
    public final String host;
    public final int port;
    public final String protocol;
    public final String password;
    public final String user;
    
    public QAVerifyServerSettings(final String host, final int port, final String protocol, final String password, final String user ) {
        this.host = host;
        this.port = port;
        this.protocol = protocol;
        this.password = password;
        this.user = user;
    }    

    public QAVerifyServerSettings() {
        this.host = "localhost";
        this.port = 8080;
        this.protocol = "http";
        this.password = "";
        this.user = "upload";
    }
}

