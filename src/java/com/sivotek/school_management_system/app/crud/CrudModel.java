/*
 * SIVOTEK SOLUTIONS LTD COURSE GRADING SYSTEM. 
 * DEVELOPED BY SIVOTEK SOLUTION LTD.
 * ALL RIGHTS RESERVED 2017.
 */
package com.sivotek.school_management_system.app.crud;

import com.sivotek.school_management_system.entities.ActionType;
import com.sivotek.school_management_system.entities.controllers.ActionTypeJpaController;


/**
 *
 * @author Administrator
 */
public class CrudModel {
   
   public ActionType getActionType(String actionname){
     ActionType actionType = new ActionType();
     ActionTypeJpaController actionTypeJpaController = new ActionTypeJpaController();
     actionType = actionTypeJpaController.findByName(actionname);
     return actionType;
   }  
   
}
