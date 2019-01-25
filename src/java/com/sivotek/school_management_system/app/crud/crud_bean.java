/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.app.crud;

import com.sivotek.school_management_system.app.security.Encryptor;
import com.sivotek.school_management_system.entities.AcademicYears;
import com.sivotek.school_management_system.entities.ClassCategory;
import com.sivotek.school_management_system.entities.CompanyBranch;
import com.sivotek.school_management_system.entities.Employee;
import com.sivotek.school_management_system.entities.Subject;
import com.sivotek.school_management_system.entities.Term;
import com.sivotek.school_management_system.entities.controllers.AcademicYearsJpaController;
import com.sivotek.school_management_system.entities.controllers.ClassCategoryJpaController;
import com.sivotek.school_management_system.entities.controllers.ClassJpaController;
import com.sivotek.school_management_system.entities.controllers.CompanyBranchJpaController;
import com.sivotek.school_management_system.entities.controllers.EmployeeJpaController;
import com.sivotek.school_management_system.entities.controllers.SubjectJpaController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author VICTOR_OKOYE
 */
public class crud_bean implements Serializable{
    private String class_name;
    private int class_id;
    private HashMap<String, Object> page_data;


    
    /***ADMIN DASHBOAR
     * @return D***/
    public HashMap<String, Object> admin_dashboard()
    {
        page_data.put("page_name", "dashboard");
        page_data.put("page_title", "Admin Dashboard");
        page_data.put("crum_bread_main_title", "dashboard");
        page_data.put("crum_bread_main_url", "#");
        page_data.put("crum_bread_module_title", "");
        page_data.put("crum_bread_module_url", "#");
        page_data.put("page_title", "Admin Dashboard");
        return page_data;
    }
    
    public Collection<ClassCategory> getClassCategories()
    {
        Collection<ClassCategory> classCategoryList = null;
        ClassCategoryJpaController classCategoryJpaController = new ClassCategoryJpaController();
        classCategoryList = classCategoryJpaController.findClassCategoryEntities();
        if (classCategoryList == null) {
            classCategoryList.removeAll(classCategoryList);
            classCategoryList.addAll(new ArrayList<ClassCategory>());
        }
        return classCategoryList;
    }
    
    public Collection<com.sivotek.school_management_system.entities.Class> getClassesByAcademicYearAndCategoryId(long academic_year_id, long class_category_id)
    {
       Collection<com.sivotek.school_management_system.entities.Class> classList = null;
       ClassJpaController classJpaController = new ClassJpaController();
       classList = classJpaController.getClassesByAcademicYearAndCategoryId(academic_year_id, class_category_id);
       return classList;
    }
    
    public long getCurrentAcademicYearId(){
        long result = 0L;
         AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
         Collection<AcademicYears> academicYearsList = academicYearsJpaController.findAcademicYearsEntities();
         if (academicYearsList == null) {
             academicYearsList.removeAll(academicYearsList);
            academicYearsList.addAll(new ArrayList<AcademicYears>());
            result = 0;
         }
         else{
         for(AcademicYears academicYears : academicYearsList){
            if(academicYears.getStatus().equalsIgnoreCase("active")){
                result = academicYears.getYearId();
                break;
            }}
         }
        return result;
    }
    
    public AcademicYears getCurrentAcademicObject(long branch_id){
        
        CompanyBranch companyBranch = new CompanyBranch();
        CompanyBranchJpaController companyBranchJpaController = new CompanyBranchJpaController();
        companyBranch = companyBranchJpaController.findCompanyBranch(branch_id);
        
        AcademicYears academicYears = new AcademicYears();
        Collection<AcademicYears> academicYearsList = new ArrayList<>();
        AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
        academicYearsList = academicYearsJpaController.findByBranchId(companyBranch);
        
         
         for(AcademicYears academicYear : academicYearsList){
            if(academicYear.getStatus().equalsIgnoreCase("active")){
                academicYears = academicYear;
                break;
            }}
         
        return academicYears;
    }
    
    public Collection<AcademicYears> findByBranchId(long branch_id)
    {
        CompanyBranch companyBranch = new CompanyBranch();
        CompanyBranchJpaController companyBranchJpaController = new CompanyBranchJpaController();
        companyBranch = companyBranchJpaController.findCompanyBranch(branch_id);
        
        Collection<AcademicYears> academicYears = new ArrayList<>();
        AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
        academicYears = academicYearsJpaController.findByBranchId(companyBranch);
        return academicYears;
    }        
     
    
    public Collection<Term> getTermList(Long branch_id)
    {
        CompanyBranch companyBranch = new CompanyBranch();
        CompanyBranchJpaController companyBranchJpaController = new CompanyBranchJpaController();
        companyBranch = companyBranchJpaController.findCompanyBranch(branch_id);
       
        AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
        AcademicYears academicYear = new AcademicYears();
        Collection<AcademicYears> academicYearsLst = new ArrayList<>();
        academicYearsLst = academicYearsJpaController.findByBranchId(companyBranch);
        for(AcademicYears academicYears : academicYearsLst)
        {
            if(academicYears.getStatus().equalsIgnoreCase("active"))
            {
                academicYear = academicYears;
                break;
            }
        }
        return academicYear.getTermCollection();
    }
    
    public List<String> getTermListByAcademicYear(long year_id)
    {
        AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
        AcademicYears academicYear = new AcademicYears();
        academicYear = academicYearsJpaController.findAcademicYears(year_id);
        
        Collection<Term> termList = new ArrayList<>();
        List<String> terms = new ArrayList<>();
        termList = academicYear.getTermCollection();
        
        terms.add("<option value='' selected='true'>---Select Term---</option>");
        for(Term t : termList)
        {
            terms.add("<option value='" + t.getTermId() + "'>" + t.getName() + "</option>");
        }
        return terms;
    }
   
    public Collection<Subject> getSubjectsByClassCategory_year(long classCategory, long academicYear)
    {
        Subject subject = new Subject();
        SubjectJpaController subjectJpaController = new SubjectJpaController();
        subject = subjectJpaController.findSubject(Long.MIN_VALUE);
        return null;
    }
    
    public Collection<AcademicYears> findAcademicYearsByBranchId(long branch_id)
    {
        CompanyBranch companyBranch = new CompanyBranch();
        CompanyBranchJpaController companyBranchJpaController = new CompanyBranchJpaController();
        companyBranch = companyBranchJpaController.findCompanyBranch(branch_id);
        AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
        Collection<AcademicYears> academicYearsLst = new ArrayList<>();
        academicYearsLst = academicYearsJpaController.findByBranchId(companyBranch);
        return academicYearsLst;
    }
    
    
    public String get_number_of_classes_attached(long classCategoryId, long academicYearId)
    {
        ClassJpaController classJpaController = new ClassJpaController();
        return "";
        //return "" + classJpaController.countByCategoryId(classCategoryId, academicYearId);
    }
    
    public Collection<Employee> get_list_of_teachers(long branch_id)
    {
        EmployeeJpaController employeeJpaController = new EmployeeJpaController();
        return employeeJpaController.get_list_of_teachers(branch_id);
    }
    
           
    public String Encrypt_data(String data)
    {
        return this.encryptdata(data);
    }   
    
    public String Decrypt_data(String data)
    {
        return this.decryptdata(data);
    }  
    
    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public HashMap<String, Object> getPage_data() {
        return page_data;
    }

    public void setPage_data(HashMap<String, Object> page_data) {
        this.page_data = page_data;
    }
    
    
    /**
    * Encrypt CRM Data
    */
   private String encryptdata(String data){
       Encryptor encryptor = new Encryptor("SIVOTekSMIS.1.0");
       //Encrypt
       String token = encryptor.encrypt(data);
       encryptor = null;
       return token;
   }
   
     /**
     * Decrypt TRANSCEND-CRM data
     * @param data
     * @return generated token as a string 
     */
    private String decryptdata(String data) {
        Encryptor encryptor = new Encryptor("SIVOTekSMIS.1.0");
        // Dencrypt
        String token = encryptor.decrypt(data);
        encryptor = null;
        return token;
    }
    
    /**
    * Encrypt CRM Data
    */
   public String url_safe_encryptdata(String data){
       Encryptor encryptor = new Encryptor("SIVOTekSMIS.1.0");
       //Encrypt
       String token = encryptor.encrypt(data.trim());
       encryptor = null;
       // escapes for url
       token = token.replace('+', '-').replace('/', '_').replace("%", "%25").replace("\n", "%0A");

       return token;
   }
   
     /**
     * Decrypt TRANSCEND-CRM data
     * @param data
     * @return generated token as a string 
     */
    public String url_safe_decryptdata(String data) {
        Encryptor encryptor = new Encryptor("SIVOTekSMIS.1.0");
        // Dencrypt
        String input = data.replace("%0A", "\n").replace("%25", "%").replace('_', '/').replace('-', '+');
        String token = encryptor.decrypt(input);
        encryptor = null;
        return token;
    }
}

