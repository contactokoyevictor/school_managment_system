/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.app.controllers;
import com.sivotek.school_management_system.app.business.audit.AuditVaultRegistry;
import com.sivotek.school_management_system.app.crud.ClientDetails;
import com.sivotek.school_management_system.app.crud.CrudModel;
import com.sivotek.school_management_system.app.pojo.SystemInfo;
import com.sivotek.school_management_system.app.pojo.loginResponse;
import com.sivotek.school_management_system.entities.ActionType;
import com.sivotek.school_management_system.entities.Users;
import com.sivotek.school_management_system.entities.controllers.UsersJpaController;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author VICTOR_OKOYE
 */

@Controller
public class LoginController {
   private static final Logger log = Logger.getLogger(LoginController.class.getName());
   private loginResponse resp; 
   
   @RequestMapping(value = "/login", method = RequestMethod.GET)
   public String login(Model model){
       log.log(Level.INFO, "-------LOGIN LOADED------:{0}"+ new Date());
//       model.addAttribute("msg", "LOGIN PAGE LOADED");
       return "login";
   }
   
   @RequestMapping(value = "/logout", method = RequestMethod.GET)
   public String logout(Model model, HttpSession session){
       
       session.invalidate();
       return "redirect:/login";
   }
   
  
   @RequestMapping(value = "/processlogin/{username}/{password}", method = RequestMethod.GET)
   public ResponseEntity  processlogin(@PathVariable String username, @PathVariable String password, ModelMap model, HttpSession session, HttpServletRequest request) 
   {
      System.out.println("Client IP : "+request.getRemoteAddr());
      System.out.println("Client host : "+request.getRemoteHost());
      Enumeration emun = request.getHeaderNames();
      String url = "";
      
      
      
      InetAddress inetAddress;
                   StringBuilder sb = new StringBuilder();
                   String ipAddress="",macAddress="",hostName="";
                   int i=0;
                   try {
                       inetAddress=InetAddress.getLocalHost();
                       ipAddress=inetAddress.getHostAddress();
                       hostName=inetAddress.getHostName();
                       NetworkInterface network=NetworkInterface.getByInetAddress(inetAddress);
                       byte[] hw=network.getHardwareAddress();
                       for(i=0; i<hw.length; i++)
                         sb.append(String.format("%02X%s", hw[i], (i < hw.length - 1) ? "-" : ""));    
                         macAddress=sb.toString();
                    } catch(Exception e) { }
        
      Users users = new Users();
      resp = new loginResponse();
      UsersJpaController usersJpaController = new UsersJpaController();
      ActionType actionType;
      CrudModel crudModel;
      ClientDetails clientDetails = new ClientDetails(request);
      SystemInfo systemInfo = new SystemInfo();
      AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
      try{
        users = usersJpaController.findByUsername_Passwd(username, password);
        if(users.getId() > 0){
               crudModel = new CrudModel();
               actionType = new ActionType();
               actionType = crudModel.getActionType("login");
               systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
               systemInfo.setSessionId(clientDetails.getSessionId());
               auditVaultRegistry.LogAction(users, actionType, "Users", systemInfo);

               session.setAttribute("user_id", users.getId());
               session.setAttribute("branch_id", users.getBranchId().getBranchId());
               session.setAttribute("name", users.getUsername());
               session.setAttribute("login_type", users.getRole());
               session.setAttribute("account_type", users.getRole());
               session.setAttribute("user_type", users.getRole());
               session.setAttribute("page_name", "dashboard");
               session.setAttribute("page_title", "Dashboard");
               
               while(emun.hasMoreElements())
                {
                   String header = "";
                   header = emun.nextElement().toString();
                   if(header.equalsIgnoreCase("referer"))
                   {
                       if(!request.getHeader(header).contains("login") || !request.getHeader(header).contains("login")
                               && request.getHeader(header).contains("/"+users.getRole()+"/"))
                       {
                          url = request.getHeader(header); 
                       }
                   }
                   System.out.println(header + " : "+request.getHeader(header).toLowerCase());

                }

               resp.setLogin_message("ok");
               resp.setLogin_status("success");
               resp.setLogin_type(users.getRole());
               resp.setUser_id(users.getId());
               if(!url.isEmpty())
               {
                   resp.setUrl(url);
               }else{
                   resp.setUrl(request.getContextPath()+"/"+users.getRole()+"/dashboard/");
               }
            }
      }catch(Exception ex){
         model.addAttribute("user_id", 0);
         users = new Users(); 
         System.out.println("Exception Occoured :"+ex.getMessage());
         log.log(Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());     
       }
     return new ResponseEntity(resp, HttpStatus.OK);
   }
   
  
}
