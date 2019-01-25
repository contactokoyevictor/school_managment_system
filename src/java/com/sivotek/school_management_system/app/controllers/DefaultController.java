/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.app.controllers;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Date;
import org.apache.log4j.Logger;
/**
 *
 * @author VICTOR_OKOYE
 */
@Controller
public class DefaultController {
   
   private static final Logger log = Logger.getLogger(DefaultController.class.getName());
   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String index(ModelMap map, HttpSession session) {
       
       log.info("--------SIVOTEK SCHOOL MANAGEMENT SYSTEM: LOADED!------:{0}"+new Date());
      String result = "login";
    try{
          String user_type = (String) session.getAttribute("user_type");
          String page_name = (String) session.getAttribute("page_name");
        if(user_type !=null && !"".equals(user_type))
        {
            session.setAttribute("page_name", page_name);
            result = "backend/"+user_type+"/"+"index";
        }
       else{
           result = "login";
       }
       
       }
       catch(Exception ex){
         log.log(Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause()); 
       }
        
       return result;
   }
   
   
   @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
   public String dashboard(ModelMap model, HttpSession session) 
   {
       String result = "login";
    try{
          String user_type = (String) session.getAttribute("user_type");
          String page_name = (String) session.getAttribute("page_name");
        if(user_type !=null && !"".equals(user_type))
        {
            result = "backend/"+user_type+"/"+page_name;
        }
       else{
           result = "login";
       }
       
       }
       catch(Exception ex){
         log.log(Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause()); 
       }
        
       return result;
   }
    
}
