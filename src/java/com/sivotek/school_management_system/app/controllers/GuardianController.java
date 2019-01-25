/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.app.controllers;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author VICTOR_OKOYE
 */
@Controller
public class GuardianController {
   private static final Logger log = Logger.getLogger(GuardianController.class.getName());

   @RequestMapping(value = "/guardian/", method = RequestMethod.GET)
   public String index(ModelMap map) {
       map.put("msg", "SIVOTEK SCHOOL MANAGEMENT SYSTEM: LOADED");
       return "backend/guardian/index";
   }
}
