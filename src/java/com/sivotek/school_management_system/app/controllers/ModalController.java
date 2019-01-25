/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.app.controllers;

import com.sivotek.school_management_system.app.pojo.AcademicYearsPOJO;
import com.sivotek.school_management_system.app.pojo.ClassCategoryPOJO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author MY USER
 */

@Controller
public class ModalController {

   private static final Logger log = Logger.getLogger(ModalController.class.getName());
   @RequestMapping(value = "/modal/popup_page/{page_name}", method = RequestMethod.GET)
   public String popup_page(@PathVariable String page_name, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception{
       model.addAttribute("page_name", page_name);
       
       if(page_name.equalsIgnoreCase("modal_add_class_category"))
       {
           model.addAttribute("ClassCategoryPOJO", new ClassCategoryPOJO());
       }
       return "backend/admin/"+page_name;
   }
   
   @RequestMapping(value = "/modal/popup/{page_name}/{param2}/{param3}/{param4}/{param5}", method = RequestMethod.GET)
   public String popup(@PathVariable String page_name, @PathVariable String param2, @PathVariable String param3, @PathVariable String param4, @PathVariable String param5, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception{
       model.addAttribute("page_name", page_name);
       model.addAttribute("param2", param2);
       model.addAttribute("param3", param3);
       model.addAttribute("param4", param4);
       model.addAttribute("param5", param5);
       
       if(page_name.equalsIgnoreCase("modal_add_class_category"))
       {
           model.addAttribute("ClassCategoryPOJO", new ClassCategoryPOJO());
       }
       
       
       return "backend/admin/"+page_name;
   }
}
