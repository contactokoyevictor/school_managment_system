/*
 * SIVOTEK SOLUTIONS LTD COURSE GRADING SYSTEM. 
 * DEVELOPED BY SIVOTEK SOLUTION LTD.
 * ALL RIGHTS RESERVED 2017.
 */
package com.sivotek.school_management_system.app.pojo;
/**
 *
 * @author Administrator
 */
public class loginResponse {
    String login_status;
    String login_message;
    String login_type;
    long user_id;
    String url;

    public String getLogin_status() {
        return login_status;
    }

    public void setLogin_status(String login_status) {
        this.login_status = login_status;
    }

    public String getLogin_message() {
        return login_message;
    }

    public void setLogin_message(String login_message) {
        this.login_message = login_message;
    }

    public String getLogin_type() {
        return login_type;
    }

    public void setLogin_type(String login_type) {
        this.login_type = login_type;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
