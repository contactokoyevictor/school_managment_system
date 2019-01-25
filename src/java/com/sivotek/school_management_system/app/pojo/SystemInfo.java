/*
 * SIVOTEK SOLUTIONS LTD COURSE GRADING SYSTEM. 
 * DEVELOPED BY SIVOTEK SOLUTION LTD.
 * ALL RIGHTS RESERVED 2017.
 */
package com.sivotek.school_management_system.app.pojo;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class SystemInfo  implements Serializable{
    private String remoteAddress;
    private String sessionId;

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
