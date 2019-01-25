/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.school_management_system.app.controllers;
import com.sivotek.school_management_system.app.business.audit.AuditVaultRegistry;
import com.sivotek.school_management_system.app.crud.ClientDetails;
import com.sivotek.school_management_system.app.crud.CrudModel;
import com.sivotek.school_management_system.app.crud.IdentityGen;
import com.sivotek.school_management_system.app.crud.crud_bean;
import com.sivotek.school_management_system.app.pojo.AcademicYearsPOJO;
import com.sivotek.school_management_system.app.pojo.ClassCategoryPOJO;
import com.sivotek.school_management_system.app.pojo.ClassPOJO;
import com.sivotek.school_management_system.app.pojo.SystemInfo;
import com.sivotek.school_management_system.app.pojo.TermPOJO;
import com.sivotek.school_management_system.entities.AcademicYears;
import com.sivotek.school_management_system.entities.ActionType;
import com.sivotek.school_management_system.entities.ClassCategory;
import com.sivotek.school_management_system.entities.CompanyBranch;
import com.sivotek.school_management_system.entities.Employee;
import com.sivotek.school_management_system.entities.Term;
import com.sivotek.school_management_system.entities.Users;
import com.sivotek.school_management_system.entities.Class;
import com.sivotek.school_management_system.entities.ClassSubjects;
import com.sivotek.school_management_system.entities.Section;
import com.sivotek.school_management_system.entities.Subject;
import com.sivotek.school_management_system.entities.controllers.AcademicYearsJpaController;
import com.sivotek.school_management_system.entities.controllers.ClassCategoryJpaController;
import com.sivotek.school_management_system.entities.controllers.ClassJpaController;
import com.sivotek.school_management_system.entities.controllers.CompanyBranchJpaController;
import com.sivotek.school_management_system.entities.controllers.EmployeeJpaController;
import com.sivotek.school_management_system.entities.controllers.SectionJpaController;
import com.sivotek.school_management_system.entities.controllers.SubjectJpaController;
import com.sivotek.school_management_system.entities.controllers.TermJpaController;
import com.sivotek.school_management_system.entities.controllers.UsersJpaController;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author okoyevictor
 */
@Controller
public class AdminController {
    private static final Logger log = Logger.getLogger(AdminController.class.getName());
    protected final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    protected final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    
   @RequestMapping(value = "/admin/", method = RequestMethod.GET)
   public String index(ModelMap map, HttpSession session)throws NullPointerException{
       if(session.getAttribute("login_type") != null && !session.getAttribute("login_type").toString().trim().equalsIgnoreCase("admin")){return "login";}
       session.setAttribute("page_name", "dashboard");
       session.setAttribute("page_title", "Admin dashboard");
       return "backend/admin/index";
   }
   
   @RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
   public String dashboard2(ModelMap map, HttpSession session)throws NullPointerException{
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       session.setAttribute("page_name", "dashboard");
       session.setAttribute("page_title", "Admin dashboard");
       return "backend/admin/index";
   }
   
     
//--------------------------------------------------------------------------------------------   
//class category mapping begins
//--------------------------------------------------------------------------------------------
   @RequestMapping(value = "/admin/manage_class_categories", method = RequestMethod.GET)
   public String manage_class_categories(ModelMap map, HttpSession session) throws NullPointerException{
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       session.setAttribute("page_name", "manage_class_categories");
       session.setAttribute("page_title", "Class Category Management ");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Class Category Management");
       map.addAttribute("subModuleUrl","/admin/manage_class_categories");
       return "backend/admin/index";
   }
   
   @RequestMapping(value = "/admin/add_class_category", method = RequestMethod.POST) 
    public String add_class_category(@ModelAttribute ClassCategoryPOJO classCategoryPOJO, RedirectAttributes model, HttpSession session, HttpServletRequest request)throws NullPointerException
    {
      if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
      Long branch_id = Long.parseLong(session.getAttribute("branch_id").toString().trim());
      Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
        //
       ClassCategory classCategory = new ClassCategory();
       ClassCategoryJpaController classCategoryJpaController = new ClassCategoryJpaController();
       
       Users users =  new Users();
       
      
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       
       classCategory.setCategoryId(IdentityGen.getGen());
       classCategory.setName(classCategoryPOJO.getName());
       classCategory.setNickName(classCategoryPOJO.getNickName());
       classCategory.setCreateddate(new Date());
       classCategory.setSynchStatus(Boolean.FALSE);
       users = usersJpaController.findUsers(user_id);
      
        try {
                classCategory.setBranchId(users.getBranchId());
                classCategoryJpaController.create(classCategory);
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("insert");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                
                auditVaultRegistry.LogAction(users, actionType, "ClassCategory", classCategory, systemInfo);
        } catch (Exception ex) {
             System.out.println("Exception Occoured :"+ex.getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
   
       session.setAttribute("page_name", "manage_class_categories");
       session.setAttribute("page_title", "Class Category Management ");
       model.addFlashAttribute("success_flash", "Class category was sucessfully added");
       model.addAttribute("moduleName","Class management dashboard");
       model.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       model.addAttribute("subModuleName","Class Category Management");
       model.addAttribute("subModuleUrl","/admin/manage_class_categories");
       return "redirect:/admin/manage_class_categories/";
       
    }
    
    @RequestMapping(value = "/admin/modal_edit_class_category/{categoryId}", method = RequestMethod.GET)
    public String modal_edit_class_category(@PathVariable("categoryId") long categoryId,Model model, HttpSession session) throws NullPointerException{
        if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
         Long branch_id = Long.parseLong(session.getAttribute("branch_id").toString().trim());
         Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
         
         ClassCategoryJpaController classCategoryJpaController = new ClassCategoryJpaController();
         ClassCategory classCategory = new ClassCategory();
         classCategory = classCategoryJpaController.findClassCategory(categoryId);
         ClassCategoryPOJO classCategoryPOJO = new ClassCategoryPOJO();
         classCategoryPOJO.setCategoryId(categoryId+"");
         classCategoryPOJO.setName(classCategory.getName());
         classCategoryPOJO.setNickName(classCategory.getNickName());
         model.addAttribute("ClassCategoryPOJO", classCategoryPOJO);
         model.addAttribute("moduleName","Class management dashboard");
         model.addAttribute("moduleUrl","/admin/class_management_dashboard/");
         model.addAttribute("subModuleName","Class Category Management");
         model.addAttribute("subModuleUrl","/admin/manage_class_categories");
         return "backend/admin/modal_edit_class_category";
    }
    
    @RequestMapping(value = "/admin/modal_edit_class_category", method = RequestMethod.POST)
    public String modal_edit_class_category(@ModelAttribute ClassCategoryPOJO classCategoryPOJO, RedirectAttributes model,HttpSession session, HttpServletRequest request)throws NullPointerException
    {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       Long branch_id = Long.parseLong(session.getAttribute("branch_id").toString().trim());
       Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       
       
       ClassCategory old_class_category_data = new ClassCategory();
       ClassCategoryJpaController classCategoryJpaController = new ClassCategoryJpaController();
       old_class_category_data = classCategoryJpaController.findClassCategory(Long.parseLong(classCategoryPOJO.getCategoryId()));
       Users users =  new Users();
       
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       users = usersJpaController.findUsers(user_id);
       
        try {
                classCategoryJpaController = new ClassCategoryJpaController();
                ClassCategory new_category = new ClassCategory();
                new_category = classCategoryJpaController.findClassCategory(Long.parseLong(classCategoryPOJO.getCategoryId()));
                new_category.setName(classCategoryPOJO.getName());
                new_category.setNickName(classCategoryPOJO.getNickName());
                new_category.setSynchStatus(Boolean.FALSE);
                classCategoryJpaController.edit(new_category);
       
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("edit");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                auditVaultRegistry.LogAction(users, actionType, "ClassCategory", classCategoryPOJO.getCategoryId(), old_class_category_data, systemInfo);
        } catch (Exception ex) {
             System.out.println("Exception occoured :"+ex.getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
       session.setAttribute("page_name", "manage_class_categories");
       session.setAttribute("page_title", "Class Category Management ");
       model.addFlashAttribute("success_flash", "Class category was sucessfully edited");
       model.addFlashAttribute("moduleName","Class management dashboard");
       model.addFlashAttribute("moduleUrl","/admin/class_management_dashboard/");
       model.addFlashAttribute("subModuleName","Class Category Management");
       model.addFlashAttribute("subModuleUrl","/admin/manage_class_categories");
       return "redirect:/admin/manage_class_categories/";
    }
    
    @RequestMapping(value = "/admin/modal_delete_class_category/{id}", method = RequestMethod.GET)
    public String modal_delete_class_category(@PathVariable("id") long id, RedirectAttributes model,HttpSession session, HttpServletRequest request)throws NullPointerException
    {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       Long branch_id = Long.parseLong(session.getAttribute("branch_id").toString().trim());
       Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       
       ClassCategory old_class_category_data = new ClassCategory();
       ClassCategoryJpaController classCategoryJpaController = new ClassCategoryJpaController();
       old_class_category_data = classCategoryJpaController.findClassCategory(id);
       Users users =  new Users();
       
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       users = usersJpaController.findUsers(user_id);
       
        try {
                classCategoryJpaController = new ClassCategoryJpaController();
                classCategoryJpaController.destroy(id); 
       
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("delete");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                auditVaultRegistry.LogAction(users, actionType, "ClassCategory", id, old_class_category_data, systemInfo);
        } catch (Exception ex) {
             System.out.println("Exception occoured :"+ex.getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
       session.setAttribute("page_name", "manage_class_categories");
       session.setAttribute("page_title", "Class Category Management ");
       model.addFlashAttribute("success_flash", "Class category was sucessfully deleted");
       model.addFlashAttribute("moduleName","Class management dashboard");
       model.addFlashAttribute("moduleUrl","/admin/class_management_dashboard/");
       model.addFlashAttribute("subModuleName","Class Category Management");
       model.addFlashAttribute("subModuleUrl","/admin/manage_class_categories");
       return "redirect:/admin/manage_class_categories/";
    }
    
//--------------------------------------------------------------------------------------------   
//class category mapping ends here
//--------------------------------------------------------------------------------------------
    
    
//--------------------------------------------------------------------------------------------   
//class mapping begins here
//--------------------------------------------------------------------------------------------  
   @RequestMapping(value = "/admin/class_management_dashboard", method = RequestMethod.GET)
   public String class_management_dashboard(ModelMap map, HttpSession session) throws NullPointerException{
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       session.setAttribute("page_name", "class_management_dashboard");
       session.setAttribute("page_title", "Class management dashboard");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/class_management_dashboard/");
       map.addAttribute("subModuleName","");
       map.addAttribute("subModuleUrl","");
       return "backend/admin/index";
      
   }
   
   @RequestMapping(value = "/admin/manage_terms", method = RequestMethod.GET)
   public String manage_terms(ModelMap map, HttpSession session) throws NullPointerException{
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       session.setAttribute("page_name", "manage_terms");
       session.setAttribute("page_title", "Manage term");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Term Management");
       map.addAttribute("subModuleUrl","/admin/manage_terms");
       return "backend/admin/index";
   }
   
   @RequestMapping(value = "/admin/modal_add_term", method = RequestMethod.POST)
   public String modal_add_term(
           @RequestParam(value = "name") String name, 
           @RequestParam(value = "nick_name", defaultValue="") String nick_name, 
           @RequestParam(value = "start_from") String start_from, 
           @RequestParam(value = "end_in") String end_in,
           @RequestParam(value = "academic_year") String academic_year,
           RedirectAttributes model,ModelMap map, HttpSession session, HttpServletRequest request) throws ParseException, NullPointerException
   {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       
       Long branch_id = Long.parseLong(session.getAttribute("branch_id").toString().trim());
       Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       
       CompanyBranch companyBranch = new CompanyBranch();
       CompanyBranchJpaController companyBranchJpaController = new CompanyBranchJpaController();
       companyBranch = companyBranchJpaController.findCompanyBranch(branch_id);
       
       AcademicYears academicYears = new AcademicYears();
       AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
       academicYears = academicYearsJpaController.findAcademicYears(Long.parseLong(academic_year));
       
       TermJpaController termJpaController = new TermJpaController();
       
       Users users =  new Users();
       
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       users = usersJpaController.findUsers(user_id);
       
        try {
                
                Term term = new Term();
                term.setTermId(IdentityGen.getGen());
                term.setBranchId(companyBranch);
                term.setAcademicYear(academicYears);
                term.setName(name);
                term.setNickName(nick_name);
                term.setStartFrom(dateFormat.parse(start_from.trim()));
                term.setEndIn(dateFormat.parse(end_in.trim()));
                term.setSynchStatus(Boolean.FALSE);
                term.setCreateddate(new Date());
                
                termJpaController = new TermJpaController();
                termJpaController.create(term);
                
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("insert");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                auditVaultRegistry.LogAction(users, actionType, "Term", term, systemInfo);
                
        } catch (Exception ex) {
             System.out.println("Exception occoured :"+ex.getCause().getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
       session.setAttribute("page_name", "manage_terms");
       session.setAttribute("page_title", "Manage term");
       model.addFlashAttribute("success_flash", "Term was sucessfully created");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Term Management");
       map.addAttribute("subModuleUrl","/admin/manage_terms");
       return "redirect:/admin/manage_terms/";
   }
    //hamdling term edit get
    @RequestMapping(value = "/admin/modal_edit_term/{id}", method = RequestMethod.GET)
    public String modal_edit_term(@PathVariable("id") long id,Model model, HttpSession session) throws NullPointerException{
        if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
         TermJpaController termJpaController = new TermJpaController();
         Term term = new Term();
         term = termJpaController.findTerm(id);
         
         TermPOJO termPOJO = new TermPOJO();
         termPOJO.setTerm_id(term.getTermId()+"");
         termPOJO.setName(term.getName());
         termPOJO.setNick_name(term.getNickName());
         
         Date startfrom_date  = term.getStartFrom();
         Date endIn_date  = term.getEndIn();
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         String startfrom = format.format(startfrom_date);
         String endIn = format.format(endIn_date);
         
         termPOJO.setStart_from(startfrom);
         termPOJO.setEnd_in(endIn);
         termPOJO.setAcademic_year(term.getAcademicYear().getYearId()+"");
                 
         model.addAttribute("TermPOJO", termPOJO);
         model.addAttribute("moduleName","Class management dashboard");
         model.addAttribute("moduleUrl","/admin/class_management_dashboard/");
         model.addAttribute("subModuleName","Term Management");
         model.addAttribute("subModuleUrl","/admin/manage_terms");
         return "backend/admin/modal_edit_term";
    }
    
    //handling term edit post
    @RequestMapping(value = "/admin/modal_edit_term", method = RequestMethod.POST)
    public String modal_edit_term(
            @RequestParam(value = "term_id") String term_id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "nick_name", defaultValue="") String nick_name,
            @RequestParam(value = "start_from") String start_from,
            @RequestParam(value = "end_in") String end_in,
            @RequestParam(value = "academic_year") String academic_year,
            RedirectAttributes model, ModelMap map, HttpSession session, HttpServletRequest request)throws NullPointerException {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       Term old_term_data = new Term();
       TermJpaController termJpaController = new TermJpaController();
       old_term_data = termJpaController.findTerm(Long.parseLong(term_id));
       AcademicYears academicYears = new AcademicYears();
       AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
       academicYears = academicYearsJpaController.findAcademicYears(Long.parseLong(academic_year));
       
       Users users =  new Users();
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       users = usersJpaController.findUsers(user_id);
        try {
                termJpaController = new TermJpaController();
                Term term = new Term();
                term = termJpaController.findTerm(Long.parseLong(term_id));
                term.setAcademicYear(academicYears);
                term.setName(name);
                term.setNickName(nick_name);
                term.setStartFrom(dateFormat.parse(start_from.trim()));
                term.setEndIn(dateFormat.parse(end_in.trim()));
                term.setSynchStatus(Boolean.FALSE);
                termJpaController.edit(term);
       
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("edit");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                auditVaultRegistry.LogAction(users, actionType, "Term", old_term_data.getTermId(), old_term_data, systemInfo);
        } catch (Exception ex) {
             System.out.println("Exception occoured :"+ex.getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
       session.setAttribute("page_name", "manage_terms");
       session.setAttribute("page_title", "Manage term");
       model.addFlashAttribute("success_flash", "Term was sucessfully edited");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Term Management");
       map.addAttribute("subModuleUrl","/admin/manage_terms");
       return "redirect:/admin/manage_terms/";
    }
   
   
 //Deleting...
 @RequestMapping(value = "/admin/modal_delete_term/{id}", method = RequestMethod.GET) 
    public String modal_delete_term(@PathVariable("id") long id, RedirectAttributes model, ModelMap map, HttpSession session, HttpServletRequest request) throws NullPointerException
    {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       Long branch_id = Long.parseLong(session.getAttribute("branch_id").toString().trim());
       Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       
       Term old_term_data = new Term();
       TermJpaController termJpaController = new TermJpaController();
       old_term_data = termJpaController.findTerm(id);
       
       Users users =  new Users();
       
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       users = usersJpaController.findUsers(user_id);
       
        try {
                termJpaController = new TermJpaController();
                termJpaController.destroy(id); 
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("delete");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                auditVaultRegistry.LogAction(users, actionType, "Term", id, old_term_data, systemInfo);
        } catch (Exception ex) {
             System.out.println("Exception occoured :"+ex.getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
       session.setAttribute("page_name", "manage_terms");
       session.setAttribute("page_title", "Manage term");
       model.addFlashAttribute("success_flash", "Term was sucessfully deleted");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Term Management");
       map.addAttribute("subModuleUrl","/admin/manage_terms");
       return "redirect:/admin/manage_terms/";
    }
  
 
  @RequestMapping(value = "/admin/manage_classes", method = RequestMethod.GET)
   public String manage_classes(ModelMap map, HttpSession session) throws NullPointerException{
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       session.setAttribute("page_name", "manage_classes");
       session.setAttribute("page_title", "Manage classes");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Class Management");
       map.addAttribute("subModuleUrl","/admin/manage_classes");
       return "backend/admin/index";
   }
  
 //handling class add post
 @RequestMapping(value = "/admin/modal_add_class", method = RequestMethod.POST)
   public String modal_add_class(
           @RequestParam(value = "name") String name, 
           @RequestParam(value = "name_numeric") String name_numeric, 
           @RequestParam(value = "teacher_id", defaultValue="") String teacher_id, 
           @RequestParam(value = "academic_year") String academic_year,
           @RequestParam(value = "class_category_id") String class_category_id,
           RedirectAttributes model,ModelMap map, HttpSession session, HttpServletRequest request) throws ParseException, NullPointerException
   {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       
       Long branch_id = Long.parseLong(session.getAttribute("branch_id").toString().trim());
       Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       
       CompanyBranch companyBranch = new CompanyBranch();
       CompanyBranchJpaController companyBranchJpaController = new CompanyBranchJpaController();
       companyBranch = companyBranchJpaController.findCompanyBranch(branch_id);
       
       AcademicYears academicYears = new AcademicYears();
       AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
       academicYears = academicYearsJpaController.findAcademicYears(Long.parseLong(academic_year));
       
       ClassCategory classCategory = new ClassCategory();
       ClassCategoryJpaController classCategoryJpaController = new ClassCategoryJpaController();
       classCategory = classCategoryJpaController.findClassCategory(Long.parseLong(class_category_id));
       
       
       com.sivotek.school_management_system.entities.controllers.ClassJpaController classJpaController = new com.sivotek.school_management_system.entities.controllers.ClassJpaController();
       
       Users users =  new Users();
       
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       users = usersJpaController.findUsers(user_id);
       
        try {
                
                com.sivotek.school_management_system.entities.Class class1 = new com.sivotek.school_management_system.entities.Class();
                class1.setClassId(IdentityGen.getGen());
                class1.setBranchId(companyBranch);
                class1.setAcademicYear(academicYears);
                class1.setName(name);
                class1.setNameNumeric(name_numeric);
                class1.setClassCategoryId(classCategory);
                class1.setSynchStatus(Boolean.FALSE);
                class1.setCreateddate(new Date());
                if(!teacher_id.isEmpty())
                {
                    Employee employee = new Employee();
                    EmployeeJpaController employeeJpaController = new EmployeeJpaController();
                    employee = employeeJpaController.findEmployee(Long.parseLong(teacher_id));
                    class1.setTeacherId(employee);
                }
                
                classJpaController = new com.sivotek.school_management_system.entities.controllers.ClassJpaController();
                classJpaController.create(class1);
                
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("insert");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                auditVaultRegistry.LogAction(users, actionType, "Class", class1, systemInfo);
                
        } catch (Exception ex) {
             System.out.println("Exception occoured :"+ex.getCause().getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
       session.setAttribute("page_name", "manage_classes");
       session.setAttribute("page_title", "Manage classes");
       model.addFlashAttribute("success_flash", "Class was sucessfully created");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Class Management");
       map.addAttribute("subModuleUrl","/admin/manage_classes");
       return "redirect:/admin/manage_classes/";
   }
  //handling class edit get
    @RequestMapping(value = "/admin/modal_edit_class/{id}", method = RequestMethod.GET)
    public String modal_edit_class(@PathVariable("id") long id, Model model, HttpSession session) throws NullPointerException{
        if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
         ClassJpaController classJpaController = new ClassJpaController();
         Class class1 = new Class();
         class1 = classJpaController.findClass(id);
         
         ClassPOJO classPOJO = new ClassPOJO();
         classPOJO.setClass_id(""+class1.getClassId());
         classPOJO.setName(class1.getName());
         classPOJO.setName_numeric(class1.getNameNumeric());
         classPOJO.setClass_category_id(class1.getClassCategoryId().getCategoryId().toString());
         classPOJO.setAcademic_year(""+class1.getAcademicYear().getYearId());
         if(class1.getTeacherId() != null)
         {
             classPOJO.setTeacher_id(""+class1.getTeacherId().getEmployeeId());
         }
         else
         {
             classPOJO.setTeacher_id("");
         }
        
         model.addAttribute("ClassPOJO", classPOJO);
         model.addAttribute("moduleName","Class management dashboard");
         model.addAttribute("moduleUrl","/admin/class_management_dashboard/");
         model.addAttribute("subModuleName","Class Management");
         model.addAttribute("subModuleUrl","/admin/manage_classes");
         return "backend/admin/modal_edit_class";
    }
    
   @RequestMapping(value = "/admin/modal_edit_class/", method = RequestMethod.POST)
    public String modal_edit_class(
           @RequestParam(value = "class_id") String class_id, 
           @RequestParam(value = "name") String name, 
           @RequestParam(value = "name_numeric") String name_numeric, 
           @RequestParam(value = "teacher_id", defaultValue="") String teacher_id, 
           @RequestParam(value = "academic_year") String academic_year,
           @RequestParam(value = "class_category_id") String class_category_id, 
           RedirectAttributes model,ModelMap map, HttpSession session, HttpServletRequest request) throws ParseException, NullPointerException
   {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       
       Long branch_id = Long.parseLong(session.getAttribute("branch_id").toString().trim());
       Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       
       CompanyBranch companyBranch = new CompanyBranch();
       CompanyBranchJpaController companyBranchJpaController = new CompanyBranchJpaController();
       companyBranch = companyBranchJpaController.findCompanyBranch(branch_id);
       
       Employee employee = new Employee();
       EmployeeJpaController employeeJpaController = new EmployeeJpaController();
       if(!teacher_id.isEmpty()){
       employee = employeeJpaController.findEmployee(Long.parseLong(teacher_id));
       }
       
       AcademicYears academicYears = new AcademicYears();
       AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
       academicYears = academicYearsJpaController.findAcademicYears(Long.parseLong(academic_year));
       
       ClassCategory classCategory = new ClassCategory();
       ClassCategoryJpaController classCategoryJpaController = new ClassCategoryJpaController();
       classCategory = classCategoryJpaController.findClassCategory(Long.parseLong(class_category_id));
       
       
       ClassJpaController classJpaController = new ClassJpaController();
       
       Users users =  new Users();
       
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       users = usersJpaController.findUsers(user_id);
       
        try {
                classJpaController = new ClassJpaController();
                Class new_class = new Class();
                Class old_class = new Class();
                old_class = classJpaController.findClass(Long.parseLong(class_id));
                new_class = old_class;
                new_class.setName(name);
                new_class.setNameNumeric(name_numeric);
                new_class.setBranchId(companyBranch);
                if(!teacher_id.isEmpty())
                {
                    new_class.setTeacherId(employee);
                }
                new_class.setClassCategoryId(classCategory);
                new_class.setAcademicYear(academicYears);
                new_class.setModifiedDate(new Date());
                new_class.setSynchStatus(Boolean.FALSE);
                
                classJpaController.edit(new_class);
       
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("edit");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                auditVaultRegistry.LogAction(users, actionType, "Class", Long.parseLong(class_id), old_class, systemInfo);
        } catch (Exception ex) {
             System.out.println("Exception occoured :"+ex.getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
        
       session.setAttribute("page_name", "manage_classes");
       session.setAttribute("page_title", "Manage classes");
       model.addFlashAttribute("success_flash", "Class was sucessfully edited");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Class Management");
       map.addAttribute("subModuleUrl","/admin/manage_classes");
       return "redirect:/admin/manage_classes/";
       
    } 
   
   
 //Deleting...
 @RequestMapping(value = "/admin/modal_delete_class/{id}", method = RequestMethod.GET) 
    public String modal_delete_class(@PathVariable("id") long id, RedirectAttributes model, ModelMap map, HttpSession session, HttpServletRequest request) throws NullPointerException
    {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       Long branch_id = Long.parseLong(session.getAttribute("branch_id").toString().trim());
       Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       
       Class old_class_data = new Class();
       ClassJpaController classJpaController = new ClassJpaController();
       old_class_data = classJpaController.findClass(id);
       
       Users users =  new Users();
       
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       users = usersJpaController.findUsers(user_id);
       
        try {
                classJpaController = new ClassJpaController();
                classJpaController.destroy(id); 
       
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("delete");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                auditVaultRegistry.LogAction(users, actionType, "Class", id, old_class_data, systemInfo);
        } catch (Exception ex) {
             System.out.println("Exception occoured :"+ex.getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
       session.setAttribute("page_name", "manage_classes");
       session.setAttribute("page_title", "Manage classes");
       model.addFlashAttribute("success_flash", "Class was sucessfully deleted");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Class Management");
       map.addAttribute("subModuleUrl","/admin/manage_classes");
       return "redirect:/admin/manage_classes/";
    }
    
 //manage section get
    @RequestMapping(value = "/admin/manage_sections", method = RequestMethod.GET)
    public String manage_sections(ModelMap map, HttpSession session) throws NullPointerException{
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       session.setAttribute("page_name", "manage_sections");
       session.setAttribute("page_title", "Manage sections");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Section Management");
       map.addAttribute("subModuleUrl","/admin/manage_sections");
       return "backend/admin/index";
    }
    
   //add section modal get
   @RequestMapping(value = "/admin/modal_add_section", method = RequestMethod.GET)
   public String modal_add_section(ModelMap map, HttpSession session) throws NullPointerException{
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       session.setAttribute("page_name", "manage_sections");
       session.setAttribute("page_title", "Manage sections");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Class Management");
       map.addAttribute("subModuleUrl","/admin/manage_sections");
       return "backend/admin/modal_add_section";
   }
   
   @RequestMapping(value = "/admin/modal_add_section", method = RequestMethod.POST)
   public String modal_add_section(
           @RequestParam(value = "name") String name, 
           @RequestParam(value = "class_category_id") long class_category_id, 
           @RequestParam(value = "academic_year") long academic_year, 
           @RequestParam(value = "term_id") long term_id,
           RedirectAttributes model,ModelMap map, HttpSession session, HttpServletRequest request) throws ParseException, NullPointerException
   {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       
       Long branch_id = Long.parseLong(session.getAttribute("branch_id").toString().trim());
       Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       
       CompanyBranch companyBranch = new CompanyBranch();
       CompanyBranchJpaController companyBranchJpaController = new CompanyBranchJpaController();
       companyBranch = companyBranchJpaController.findCompanyBranch(branch_id);
       
       ClassCategory classCategory = new ClassCategory();
       ClassCategoryJpaController classCategoryJpaController = new ClassCategoryJpaController();
       classCategory = classCategoryJpaController.findClassCategory(class_category_id);
       
       Term term = new Term();
       TermJpaController termJpaController = new TermJpaController();
       term = termJpaController.findTerm(term_id);
       
       Users users =  new Users();
       
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       users = usersJpaController.findUsers(user_id);
       
        try {
                
                SectionJpaController sectionJpaController = new SectionJpaController();
                Section section = new Section();
                
                section.setSectionId(IdentityGen.getGen());
                section.setBranchId(companyBranch);
                section.setTermId(term);
                section.setName(name);
                section.setClassCategoryId(classCategory);
                section.setSynchStatus(Boolean.FALSE);
                section.setCreationDate(new Date());
                
                
               sectionJpaController.create(section);
                
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("insert");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                auditVaultRegistry.LogAction(users, actionType, "Section", section, systemInfo);
                
        } catch (Exception ex) {
             System.out.println("Exception occoured :"+ex.getCause().getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
       session.setAttribute("page_name", "manage_sections");
       session.setAttribute("page_title", "Manage sections");
       model.addFlashAttribute("success_flash", "Section was sucessfully created");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Class Management");
       map.addAttribute("subModuleUrl","/admin/manage_sections");
       return "redirect:/admin/manage_sections/";
   }
  
   //handle section edit GET[METHOD] 
    @RequestMapping(value = "/admin/modal_edit_section/{id}", method = RequestMethod.GET)
    public String modal_edit_section(@PathVariable("id") long id, Model model, HttpSession session) throws NullPointerException{
        if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
         SectionJpaController sectionJpaController = new SectionJpaController();
         Section section = new Section();
         section = sectionJpaController.findSection(id);
         session.setAttribute("TermYearId", section.getTermId().getAcademicYear().getYearId());
         model.addAttribute("SectionPOJO", section);
         return "backend/admin/modal_edit_section";
    }
    
 //handle section edit GET[METHOD]
  @RequestMapping(value = "/admin/modal_edit_section/", method = RequestMethod.POST)
    public String modal_edit_section(
           @RequestParam(value = "section_id") long section_id, 
           @RequestParam(value = "name") String name, 
           @RequestParam(value = "class_category_id") long class_category_id, 
           @RequestParam(value = "academic_year") long academic_year, 
           @RequestParam(value = "term_id") long term_id,
           RedirectAttributes model,ModelMap map, HttpSession session, HttpServletRequest request) throws ParseException, NullPointerException
   {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       
       Long branch_id = Long.parseLong(session.getAttribute("branch_id").toString().trim());
       Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       
       CompanyBranch companyBranch = new CompanyBranch();
       CompanyBranchJpaController companyBranchJpaController = new CompanyBranchJpaController();
       companyBranch = companyBranchJpaController.findCompanyBranch(branch_id);
       
       
       
       AcademicYears academicYears = new AcademicYears();
       AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
       academicYears = academicYearsJpaController.findAcademicYears(academic_year);
       
       ClassCategory classCategory = new ClassCategory();
       ClassCategoryJpaController classCategoryJpaController = new ClassCategoryJpaController();
       classCategory = classCategoryJpaController.findClassCategory(class_category_id);
       
       Term term = new Term();
       TermJpaController termJpaController = new TermJpaController();
       term = termJpaController.findTerm(term_id);
       
       SectionJpaController sectionJpaController = new SectionJpaController();
       
       Users users =  new Users();
       
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       users = usersJpaController.findUsers(user_id);
       
        try {
                sectionJpaController = new SectionJpaController();
                Section new_section = new Section();
                Section old_section = new Section();
                old_section = sectionJpaController.findSection(section_id);
                new_section = old_section;
                new_section.setName(name);
                new_section.setBranchId(companyBranch);
                new_section.setClassCategoryId(classCategory);
                new_section.setTermId(term);
                new_section.setSynchStatus(Boolean.FALSE);
                
                sectionJpaController.edit(new_section);
       
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("edit");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                auditVaultRegistry.LogAction(users, actionType, "Section", section_id, old_section, systemInfo);
        } catch (Exception ex) {
             System.out.println("Exception occoured :"+ex.getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
        
       session.setAttribute("page_name", "manage_sections");
       session.setAttribute("page_title", "Manage sections");
       model.addFlashAttribute("success_flash", "Section was sucessfully edited");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Class Management");
       map.addAttribute("subModuleUrl","/admin/manage_sections");
       return "redirect:/admin/manage_sections/";
    }
    
    //Deleting...
 @RequestMapping(value = "/admin/modal_delete_section/{id}", method = RequestMethod.GET) 
    public String modal_delete_section(@PathVariable("id") long id, RedirectAttributes model, ModelMap map, HttpSession session, HttpServletRequest request) throws NullPointerException
    {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       
       Section old_section_data = new Section();
       SectionJpaController sectionJpaController = new SectionJpaController();
       old_section_data = sectionJpaController.findSection(id);
       
       Users users =  new Users();
       
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       users = usersJpaController.findUsers(user_id);
       
        try {
                sectionJpaController = new SectionJpaController();
                sectionJpaController.destroy(id); 
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("delete");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                auditVaultRegistry.LogAction(users, actionType, "Section", id, old_section_data, systemInfo);
        } catch (Exception ex) {
             System.out.println("Exception occoured :"+ex.getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
       session.setAttribute("page_name", "manage_sections");
       session.setAttribute("page_title", "Manage sections");
       model.addFlashAttribute("success_flash", "Section was sucessfully deleted");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Class Management");
       map.addAttribute("subModuleUrl","/admin/manage_sections");
       return "redirect:/admin/manage_sections/";
    }

    //-------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------
    // manage subjects handling...begins
    //------------------------------------------------------------------------------- 
    //-------------------------------------------------------------------------------

    //manage subjects get
    @RequestMapping(value = "/admin/manage_subjects", method = RequestMethod.GET)
    public String manage_subjects(ModelMap map, HttpSession session) throws NullPointerException{
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       session.setAttribute("page_name", "manage_subjects");
       session.setAttribute("page_title", "Manage subjects");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Subject management");
       map.addAttribute("subModuleUrl","/admin/manage_subjects");
       return "backend/admin/index";
    }
    //add section modal get
   @RequestMapping(value = "/admin/modal_add_subject", method = RequestMethod.GET)
   public String modal_add_subject(ModelMap map, HttpSession session) throws NullPointerException{
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       return "backend/admin/modal_add_subject";
   }
   
   @RequestMapping(value = "/admin/modal_add_subject", method = RequestMethod.POST)
   public String modal_add_subject(
           @RequestParam(value = "name") String name, 
           @RequestParam(value = "code", defaultValue="") String code, 
           @RequestParam(value = "nick_name", defaultValue="") String nick_name, 
           @RequestParam(value = "academic_year") long academic_year, 
           @RequestParam(value = "class_category_id") long class_category_id,
           RedirectAttributes model,ModelMap map, HttpSession session, HttpServletRequest request) throws ParseException, NullPointerException
   {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       
       Long branch_id = Long.parseLong(session.getAttribute("branch_id").toString().trim());
       Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       
       CompanyBranch companyBranch = new CompanyBranch();
       CompanyBranchJpaController companyBranchJpaController = new CompanyBranchJpaController();
       companyBranch = companyBranchJpaController.findCompanyBranch(branch_id);
       
       ClassCategory classCategory = new ClassCategory();
       ClassCategoryJpaController classCategoryJpaController = new ClassCategoryJpaController();
       classCategory = classCategoryJpaController.findClassCategory(class_category_id);

       AcademicYears academicYears = new AcademicYears();
       AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
       academicYears = academicYearsJpaController.findAcademicYears(academic_year);
       
       
       
       Users users =  new Users();
       
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       users = usersJpaController.findUsers(user_id);
       
        try {
                
                SubjectJpaController subjectJpaController = new SubjectJpaController();
                Subject subject = new Subject();
                
                subject.setSubjectId(IdentityGen.getGen());
                subject.setBranchId(companyBranch);
                subject.setName(name);
                subject.setCode(code);
                subject.setNickName(nick_name);
                subject.setAcademicYear(academicYears);
                subject.setClassCategoryId(classCategory);
                subject.setSynchStatus(Boolean.FALSE);
                subject.setCreateddate(new Date());
                
                
               subjectJpaController.create(subject);
                
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("insert");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                auditVaultRegistry.LogAction(users, actionType, "Subject", subject, systemInfo);
                
        } catch (Exception ex) {
             System.out.println("Exception occoured :"+ex.getCause().getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
       session.setAttribute("page_name", "manage_subjects");
       session.setAttribute("page_title", "Manage subjects");
       model.addFlashAttribute("success_flash", "Subject was sucessfully created");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Class Management");
       map.addAttribute("subModuleUrl","/admin/manage_subjects");
       return "redirect:/admin/manage_subjects/";
   }
  
   //handle subject edit GET[METHOD] 
    @RequestMapping(value = "/admin/modal_edit_subject/{id}", method = RequestMethod.GET)
    public String modal_edit_subject(@PathVariable("id") long id, Model model, HttpSession session) throws NullPointerException{
        if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
         SubjectJpaController subjectJpaController = new SubjectJpaController();
         Subject subject = new Subject();
         subject = subjectJpaController.findSubject(id);
         session.setAttribute("SubjectYearId", subject.getAcademicYear().getYearId());
         session.setAttribute("SubjectClassCategoryId", subject.getClassCategoryId().getCategoryId());
         model.addAttribute("SubjectPOJO", subject);
         return "backend/admin/modal_edit_subject";
    }
    
 //handle subject edit GET[METHOD]
  @RequestMapping(value = "/admin/modal_edit_subject/", method = RequestMethod.POST)
    public String modal_edit_subject(
           @RequestParam(value = "subject_id") long subject_id, 
           @RequestParam(value = "name") String name, 
           @RequestParam(value = "code", defaultValue="") String code, 
           @RequestParam(value = "nick_name", defaultValue="") String nick_name, 
           @RequestParam(value = "academic_year") long academic_year, 
           @RequestParam(value = "class_category_id") long class_category_id,
           RedirectAttributes model,ModelMap map, HttpSession session, HttpServletRequest request) throws ParseException, NullPointerException
   {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       
       Long branch_id = Long.parseLong(session.getAttribute("branch_id").toString().trim());
       Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       
       CompanyBranch companyBranch = new CompanyBranch();
       CompanyBranchJpaController companyBranchJpaController = new CompanyBranchJpaController();
       companyBranch = companyBranchJpaController.findCompanyBranch(branch_id);
       
       
       
       AcademicYears academicYears = new AcademicYears();
       AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
       academicYears = academicYearsJpaController.findAcademicYears(academic_year);
       
       ClassCategory classCategory = new ClassCategory();
       ClassCategoryJpaController classCategoryJpaController = new ClassCategoryJpaController();
       classCategory = classCategoryJpaController.findClassCategory(class_category_id);


       
       SubjectJpaController subjectJpaController = new SubjectJpaController();
       
       Users users =  new Users();
       
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       users = usersJpaController.findUsers(user_id);
       
        try {
                subjectJpaController = new SubjectJpaController();
                Subject new_subject = new Subject();
                Subject old_subject = new Subject();
                old_subject = subjectJpaController.findSubject(subject_id);
                new_subject = old_subject;

                new_subject.setName(name);
                new_subject.setCode(code);
                new_subject.setNickName(nick_name);
                new_subject.setAcademicYear(academicYears);
                new_subject.setClassCategoryId(classCategory);
                new_subject.setSynchStatus(Boolean.FALSE);

                subjectJpaController.edit(new_subject);
       
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("edit");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                auditVaultRegistry.LogAction(users, actionType, "Subject", subject_id, old_subject, systemInfo);
        } catch (Exception ex) {
             System.out.println("Exception occoured :"+ex.getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
        
       session.setAttribute("page_name", "manage_subjects");
       session.setAttribute("page_title", "Manage subjects");
       model.addFlashAttribute("success_flash", "Subject was sucessfully edited");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Class Management");
       map.addAttribute("subModuleUrl","/admin/manage_subjects");
       return "redirect:/admin/manage_subjects/";
    }
    
    //Deleting...
 @RequestMapping(value = "/admin/modal_delete_subject/{id}", method = RequestMethod.GET) 
    public String modal_delete_subject(@PathVariable("id") long id, RedirectAttributes model, ModelMap map, HttpSession session, HttpServletRequest request) throws NullPointerException
    {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       
       Subject old_subject_data = new Subject();
       SubjectJpaController subjectJpaController = new SubjectJpaController();
       old_subject_data = subjectJpaController.findSubject(id);
       
       Users users =  new Users();
       
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       users = usersJpaController.findUsers(user_id);
       
        try {
                subjectJpaController = new SubjectJpaController();
                subjectJpaController.destroy(id); 
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("delete");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                auditVaultRegistry.LogAction(users, actionType, "Subject", id, old_subject_data, systemInfo);
        } catch (Exception ex) {
             System.out.println("Exception occoured :"+ex.getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
       session.setAttribute("page_name", "manage_subjects");
       session.setAttribute("page_title", "Manage subjects");
       model.addFlashAttribute("success_flash", "Subject was sucessfully deleted");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Class Management");
       map.addAttribute("subModuleUrl","/admin/manage_subjects");
       return "redirect:/admin/manage_subjects/";
    }

  //subject_class_categories get method 
   @RequestMapping(value = "/admin/subject_class_categories", method = RequestMethod.GET)
    public String subject_class_categories(ModelMap map, HttpSession session) throws NullPointerException{
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       session.setAttribute("page_name", "subject_class_categories");
       session.setAttribute("page_title", "Subject information");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Subject class categories");
       map.addAttribute("subModuleUrl","/admin/subject_class_categories");
       return "backend/admin/index";
    }
    
    @RequestMapping(value = "/admin/manage_class_subjects/{id}", method = RequestMethod.GET) 
    public String manage_class_subjects(@PathVariable("id") String id, RedirectAttributes model, ModelMap map, HttpSession session, HttpServletRequest request) throws NullPointerException
    {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       crud_bean crud = new crud_bean();
       Class class1 = new Class();
       ClassJpaController classJpaController = new ClassJpaController();
       try{
           class1 = classJpaController.findClass(Long.parseLong(crud.url_safe_decryptdata(id)));
        }
       catch(Exception ex)
       {}
       map.addAttribute("class_id", Long.parseLong(crud.url_safe_decryptdata(id)));
       session.setAttribute("class_id", Long.parseLong(crud.url_safe_decryptdata(id)));
       session.setAttribute("page_name", "manage_class_subjects");
       session.setAttribute("page_title", "Manage class subjects");
       map.addAttribute("moduleName","Class management dashboard");
       map.addAttribute("moduleUrl","/admin/class_management_dashboard/");
       map.addAttribute("subModuleName","Manage class subjects");
       map.addAttribute("subModuleUrl","/admin/manage_class_subjects");
       return "backend/admin/index";
    }

//--------------------------------------------------------------------------------------------   
//class mapping ends here
//--------------------------------------------------------------------------------------------  
   
//--------------------------------------------------------------------------------------------   
//student mapping begins here
//--------------------------------------------------------------------------------------------  
   @RequestMapping(value = "/admin/student_management_dashboard", method = RequestMethod.GET)
   public String student_management_dashboard(ModelMap map, HttpSession session) throws NullPointerException
   {
      if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       session.setAttribute("page_name", "student_management_dashboard");
       session.setAttribute("page_title", "Student management dashboard");
       map.addAttribute("moduleName","Student management dashboard");
       map.addAttribute("moduleUrl","/admin/student_management_dashboard/");
       map.addAttribute("subModuleName","");
       map.addAttribute("subModuleUrl","");
       return "backend/admin/index";
   } 
//--------------------------------------------------------------------------------------------   
//student mapping ends here
//--------------------------------------------------------------------------------------------  
   
//--------------------------------------------------------------------------------------------   
//exam mapping begins here
//--------------------------------------------------------------------------------------------  
   @RequestMapping(value = "/admin/exam_management_dashboard", method = RequestMethod.GET)
   public String exam_management_dashboard(ModelMap map, HttpSession session) throws NullPointerException{
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       session.setAttribute("page_name", "exam_management_dashboard");
       session.setAttribute("page_title", "Exam management dashboard");
       map.addAttribute("moduleName","Exam management dashboard");
       map.addAttribute("moduleUrl","/admin/exam_management_dashboard/");
       map.addAttribute("subModuleName","");
       map.addAttribute("subModuleUrl","");
       return "backend/admin/index";
      
   } 
//--------------------------------------------------------------------------------------------   
//exam mapping ends here
//--------------------------------------------------------------------------------------------  
   
//--------------------------------------------------------------------------------------------   
//HR mapping begins here
//--------------------------------------------------------------------------------------------  
   @RequestMapping(value = "/admin/hr_management_dashboard", method = RequestMethod.GET)
   public String hr_management_dashboard(ModelMap map, HttpSession session) throws NullPointerException{
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       session.setAttribute("page_name", "hr_management_dashboard");
       session.setAttribute("page_title", "Human resource management dashboard");
       map.addAttribute("moduleName","HR management dashboard");
       map.addAttribute("moduleUrl","/admin/hr_management_dashboard/");
       map.addAttribute("subModuleName","");
       map.addAttribute("subModuleUrl","");
       return "backend/admin/index";
      
   } 
//--------------------------------------------------------------------------------------------   
//HR mapping ends here
//--------------------------------------------------------------------------------------------    

//--------------------------------------------------------------------------------------------   
//accounting mapping begins here
//--------------------------------------------------------------------------------------------  
   @RequestMapping(value = "/admin/accounting_management_dashboard", method = RequestMethod.GET)
   public String accounting_management_dashboard(ModelMap map, HttpSession session) throws NullPointerException{
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       session.setAttribute("page_name", "accounting_management_dashboard");
       session.setAttribute("page_title", "Accounting dashboard");
       map.addAttribute("moduleName","Accounting management dashboard");
       map.addAttribute("moduleUrl","/admin/accounting_management_dashboard/");
       map.addAttribute("subModuleName","");
       map.addAttribute("subModuleUrl","");
       return "backend/admin/index";
      
   } 
//--------------------------------------------------------------------------------------------   
//accounting mapping ends here
//--------------------------------------------------------------------------------------------  
   
  
//--------------------------------------------------------------------------------------------   
//settings mapping begins here
//--------------------------------------------------------------------------------------------  
   @RequestMapping(value = "/admin/system_settings_dashboard", method = RequestMethod.GET)
   public String system_settings_dashboard(ModelMap map, HttpSession session) throws NullPointerException{
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       session.setAttribute("page_name", "system_settings_dashboard");
       session.setAttribute("page_title", "System settings");
       map.addAttribute("moduleName","System settings dashboard");
       map.addAttribute("moduleUrl","/admin/system_settings_dashboard/");
       map.addAttribute("subModuleName","");
       map.addAttribute("subModuleUrl","");
       return "backend/admin/index";
      
   } 
   
   @RequestMapping(value = "/admin/academic_years_setting", method = RequestMethod.GET)
   public String academic_years_setting(ModelMap map, HttpSession session) throws NullPointerException{
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       session.setAttribute("page_name", "academic_years_setting");
       session.setAttribute("page_title", "Academic year management");
       map.addAttribute("moduleName","System settings dashboard");
       map.addAttribute("moduleUrl","/admin/system_settings_dashboard/");
       map.addAttribute("subModuleName","Academic year management");
       map.addAttribute("subModuleUrl","/admin/academic_years_setting/");
       return "backend/admin/index";
      
   }
   
   @RequestMapping(value = "/admin/add_academic_year/", method = RequestMethod.POST)
   public String add_academic_year(
           @RequestParam(value = "academic_year") String academic_year, 
           @RequestParam(value = "Status", defaultValue="0") String Status, 
           RedirectAttributes model,ModelMap map, HttpSession session, HttpServletRequest request) throws ParseException, NullPointerException
   {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
      
       String[] exploded = academic_year.split("/");
       String start_from = exploded[0];
       String end_in = exploded[1];
       
       Long branch_id = Long.parseLong(session.getAttribute("branch_id").toString().trim());
       Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       
       CompanyBranch companyBranch = new CompanyBranch();
       CompanyBranchJpaController companyBranchJpaController = new CompanyBranchJpaController();
       companyBranch = companyBranchJpaController.findCompanyBranch(branch_id);
       
     
       
       AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
       Users users =  new Users();
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       users = usersJpaController.findUsers(user_id);
       try {
                AcademicYears academicYears = new AcademicYears();
                academicYears.setYearId(IdentityGen.getGen());
                academicYears.setBranchId(companyBranch);
                academicYears.setName(academic_year);
                academicYears.setStartFrom(dateFormat.parse(start_from.trim()));
                academicYears.setEndIn(dateFormat.parse(end_in.trim()));
                if(Status.equalsIgnoreCase("1")){
                academicYears.setStatus("active");
                }else{academicYears.setStatus("inactive");}
                academicYears.setSynchStatus(Boolean.FALSE);
                academicYears.setCreateddate(new Date());
                academicYears.setModifiedDate(new Date());
                if(academicYears.getStatus().equalsIgnoreCase("active"))
                {
                    academicYearsJpaController = new AcademicYearsJpaController();
                    Collection<AcademicYears> academicYearsLst = new ArrayList<>();
                    academicYearsLst = academicYearsJpaController.findByBranchId(companyBranch);
                    for(AcademicYears academicYear : academicYearsLst)
                    {
                        if(academicYear.getStatus().equalsIgnoreCase("active"))
                        {
                            academicYear.setStatus("inactive");
                            academicYear.setModifiedDate(new Date());
                            academicYearsJpaController.edit(academicYear);
                        }
                    }
                }
                academicYearsJpaController = new AcademicYearsJpaController();
                academicYearsJpaController.create(academicYears);
                
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("insert");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                auditVaultRegistry.LogAction(users, actionType, "AcademicYears", academicYears, systemInfo);
                
        } catch (Exception ex) {
             System.out.println("Exception occoured :"+ex.getCause().getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
       session.setAttribute("page_name", "academic_years_setting");
       session.setAttribute("page_title", "Academic year management");
       model.addFlashAttribute("success_flash", "Academic year was sucessfully created");
       map.addAttribute("moduleName","System settings dashboard");
       map.addAttribute("moduleUrl","/admin/system_settings_dashboard/");
       map.addAttribute("subModuleName","Academic year management");
       map.addAttribute("subModuleUrl","/admin/academic_years_setting/");
       return "redirect:/admin/academic_years_setting/";
   }
  
   
 //load edit modal
  @RequestMapping(value = "/admin/modal_edit_academic_year/{yearId}", method = RequestMethod.GET)
    public String modal_edit_academic_year(@PathVariable("yearId") long yearId,Model model, HttpSession session) throws NullPointerException{
        if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
         Long branch_id = Long.parseLong(session.getAttribute("branch_id").toString().trim());
         Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       
         AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
         AcademicYears academicYears = new AcademicYears();
         academicYears = academicYearsJpaController.findAcademicYears(yearId);
         AcademicYearsPOJO academicYearsPOJO = new AcademicYearsPOJO();
         academicYearsPOJO.setYearId(yearId+"");
         academicYearsPOJO.setName(academicYears.getName());
         academicYearsPOJO.setStatus(academicYears.getStatus());
         model.addAttribute("AcademicYearsPOJO", academicYearsPOJO);
         model.addAttribute("moduleName","System settings dashboard");
         model.addAttribute("moduleUrl","/admin/system_settings_dashboard/");
         model.addAttribute("subModuleName","Academic year management");
         model.addAttribute("subModuleUrl","/admin/academic_years_setting/");
         return "backend/admin/modal_academic_year_edit";
    }
    
    @RequestMapping(value = "/admin/modal_edit_academic_year/", method = RequestMethod.POST)
    public String modal_edit_academic_year(
           @RequestParam(value = "yearId") String yearId,         
           @RequestParam(value = "academic_year") String academic_year, 
           @RequestParam(value = "Status", defaultValue="0") String Status, 
           RedirectAttributes model,ModelMap map, HttpSession session, HttpServletRequest request) throws ParseException, NullPointerException
   {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       String[] exploded = academic_year.split("/");
       String start_from = exploded[0];
       String end_in = exploded[1];
       
       Long branch_id = Long.parseLong(session.getAttribute("branch_id").toString().trim());
       Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       
       CompanyBranch companyBranch = new CompanyBranch();
       CompanyBranchJpaController companyBranchJpaController = new CompanyBranchJpaController();
       companyBranch = companyBranchJpaController.findCompanyBranch(branch_id);
       
     
       AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
       
       Users users =  new Users();
       
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       users = usersJpaController.findUsers(user_id);
       
        try {
                academicYearsJpaController = new AcademicYearsJpaController();
                AcademicYears new_academicYears = new AcademicYears();
                AcademicYears old_academicYears = new AcademicYears();
                old_academicYears = academicYearsJpaController.findAcademicYears(Long.parseLong(yearId));
                new_academicYears = old_academicYears;
                new_academicYears.setName(academic_year);
                new_academicYears.setBranchId(companyBranch);
                if(Status.trim().equalsIgnoreCase("1")){
                new_academicYears.setStatus("active");
                }else{new_academicYears.setStatus("inactive");}
                new_academicYears.setStartFrom(dateFormat.parse(start_from.trim()));
                new_academicYears.setEndIn(dateFormat.parse(end_in.trim()));
                new_academicYears.setModifiedDate(new Date());
                new_academicYears.setSynchStatus(Boolean.FALSE);
                
                if(new_academicYears.getStatus().trim().equalsIgnoreCase("active"))
                {
                    academicYearsJpaController = new AcademicYearsJpaController();
                    Collection<AcademicYears> academicYearsLst = new ArrayList<>();
                    academicYearsLst = academicYearsJpaController.findByBranchId(companyBranch);
                    for(AcademicYears academicYear : academicYearsLst)
                    {
                        if(academicYear.getStatus().trim().equalsIgnoreCase("active") && academicYear.getYearId() != new_academicYears.getYearId())
                        {
                            academicYear.setStatus("inactive");
                            academicYear.setModifiedDate(new Date());
                            academicYearsJpaController.edit(academicYear);
                        }
                    }
                }
                
                academicYearsJpaController.edit(new_academicYears);
       
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("edit");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                auditVaultRegistry.LogAction(users, actionType, "AcademicYears", Long.parseLong(yearId), old_academicYears, systemInfo);
        } catch (Exception ex) {
             System.out.println("Exception occoured :"+ex.getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
        
       session.setAttribute("page_name", "academic_years_setting");
       session.setAttribute("page_title", "Academic year management");
       model.addFlashAttribute("success_flash", "Academic year was sucessfully edited");
       map.addAttribute("moduleName","System settings dashboard");
       map.addAttribute("moduleUrl","/admin/system_settings_dashboard/");
       map.addAttribute("subModuleName","Academic year management");
       map.addAttribute("subModuleUrl","/admin/academic_years_setting/");
       return "redirect:/admin/academic_years_setting/";
       
    } 
   
   
 //Deleting...
 @RequestMapping(value = "/admin/modal_academic_year/{id}", method = RequestMethod.GET) 
    public String modal_academic_year(@PathVariable("id") long id, RedirectAttributes model, ModelMap map, HttpSession session, HttpServletRequest request) throws NullPointerException
    {
       if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return "login";}
       Long user_id = Long.parseLong(session.getAttribute("user_id").toString().trim());
       
       AcademicYears old_academicYears_data = new AcademicYears();
       AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
       old_academicYears_data = academicYearsJpaController.findAcademicYears(id);
       
       Users users =  new Users();
       
       UsersJpaController usersJpaController =  new UsersJpaController();
       SystemInfo systemInfo = new SystemInfo();
       ClientDetails clientDetails = new ClientDetails(request);
       AuditVaultRegistry auditVaultRegistry = new AuditVaultRegistry();
       
       users = usersJpaController.findUsers(user_id);
       
        try {
                academicYearsJpaController = new AcademicYearsJpaController();
                academicYearsJpaController.destroy(id); 
                CrudModel crudModel = new CrudModel();
                ActionType actionType = new ActionType();
                actionType = crudModel.getActionType("delete");
                systemInfo.setRemoteAddress(clientDetails.getRemoteAddress());
                systemInfo.setSessionId(clientDetails.getSessionId());
                auditVaultRegistry.LogAction(users, actionType, "AcademicYears", id, old_academicYears_data, systemInfo);
        } catch (Exception ex) {
             System.out.println("Exception occoured :"+ex.getMessage());
             log.log(org.apache.log4j.Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
        }
       session.setAttribute("page_name", "academic_years_setting");
       session.setAttribute("page_title", "Academic year management");
       model.addFlashAttribute("success_flash", "Academic year was sucessfully deleted");
       map.addAttribute("moduleName","System settings dashboard");
       map.addAttribute("moduleUrl","/admin/system_settings_dashboard/");
       map.addAttribute("subModuleName","Academic year management");
       map.addAttribute("subModuleUrl","/admin/academic_years_setting/");
       return "redirect:/admin/academic_years_setting/";
    }
//--------------------------------------------------------------------------------------------   
//settings mapping ends here
//--------------------------------------------------------------------------------------------   
@RequestMapping(value = "/admin/get_terms_by_academic_year/{year_id}", method = RequestMethod.GET)
public @ResponseBody List<String> get_terms_by_academic_year(@PathVariable("year_id") long year_id, HttpSession session){
    if((session.getAttribute("login_type")+"").equals("null") || !(session.getAttribute("login_type")+"").equals("admin")){return new ArrayList<>();}
    List<String> terms = new ArrayList<String>();
    crud_bean c_bean = new crud_bean();
    terms = c_bean.getTermListByAcademicYear(year_id);
    return terms;
}
}
