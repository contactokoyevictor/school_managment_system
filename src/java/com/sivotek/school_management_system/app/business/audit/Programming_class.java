
package com.sivotek.school_management_system.app.business.audit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Programming_class {
    public String student_name;
    public String address;
    public Date DOB;
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public Programming_class(){}
    
    public void setStudentInfo(String name, String s_address, Date s_DOB)
    {
        this.student_name = name;
        this.address = s_address;
        this.DOB = s_DOB;
    }
    
    public void getStudentInfo()
    {
        System.out.println("Student name :"+this.student_name);
        System.out.println("Student address :"+this.address);
        System.out.println("Student Date of birth :"+this.DOB);
    }

    public static void main(String args []) throws ParseException
    {
        Programming_class p_class = new Programming_class();
        Date temp_date = new Date();
        temp_date = dateFormat.parse("1993-05-10");
        p_class.setStudentInfo("Blessing Godwin", "Kaduna", temp_date);
        
        p_class.getStudentInfo();
    }
}
