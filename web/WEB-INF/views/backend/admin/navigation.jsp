
<!DOCTYPE html>
<%-- 
    Document   : navigation
    Created on : Mar 17, 2017, 3:28:35 PM
    Author     : VICTOR_OKOYE
--%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="nav_bean" scope="request" class="com.sivotek.school_management_system.app.crud.crud_bean" />
<jsp:setProperty name="nav_bean" property="*" />

<%@page import="com.sivotek.school_management_system.entities.AcademicYears;" %>
<%@page import="com.sivotek.school_management_system.entities.controllers.AcademicYearsJpaController;" %>
<%@page import="com.sivotek.school_management_system.entities.ClassCategory;" %>
<%@page import="com.sivotek.school_management_system.entities.controllers.ClassCategoryJpaController;" %>
<%@page import="com.sivotek.school_management_system.entities.Class;" %>
<%@page import="com.sivotek.school_management_system.entities.controllers.ClassJpaController;" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cpi" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<%! 
    
    Collection<ClassCategory> classCategoryList = null;
    Collection<com.sivotek.school_management_system.entities.Class> classList = null;
    long check_category = 0L;
    long current_academic_year_id = 0L;

%>
<div class="sidebar-menu">
    <header class="logo-env" >

        <!-- logo -->
        <div class="logo" style="">
            
            <a href="${pageContext.request.servletContext.contextPath}">
                <img src="${cpi}/resources/images/uploads/logo.png" alt="School logo"  style="max-height:50px;"/>
            </a>
        </div>

        <!-- logo collapse icon -->
        <div class="sidebar-collapse" style="">
            <a href="#" class="sidebar-collapse-icon with-animation">

                <i class="entypo-menu"></i>
            </a>
        </div>

        <!-- open/close menu icon (do not remove if you want to enable menu on mobile devices) -->
        <div class="sidebar-mobile-menu visible-xs">
            <a href="#" class="with-animation">
                <i class="entypo-menu"></i>
            </a>
        </div>
    </header>

    <div style=""></div>	
    <ul id="main-menu" class="auto-inherit-active-class">
        <!-- add class "multiple-expanded" to allow multiple submenus to open -->
        <!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->


        <!-- DASHBOARD -->
 
        <li class="<c:if test = "${page_name == 'dashboard'}"><c:out value="active"/></c:if>">
            <a href="${pageContext.request.contextPath}/${login_type}/dashboard/">
                <i class="entypo-gauge"></i>
                <span>Dashboard</span>
            </a>
        </li>
        
        
        <!-- CLASS -->
        <li class="<c:if test="${page_name == 'class'||
                                 page_name == 'manage_terms' ||
                                 page_name == 'section' ||
                                 page_name == 'subject' ||
                                 page_name == 'class_routine' ||
                                 page_name == 'class_management_dashboard' ||
                                 page_name == 'subject_information_landing' ||
                                 page_name == 'manage_class_categories' ||
                                 page_name == 'manage_subjects'}">
                    <c:out value="opened active has-sub root-level"/>
                  </c:if>">
            <a href="#">
                <i class="entypo-flow-tree"></i>
                <span>Class management</span>
            </a>
            <ul>
                
                <li class="<c:if test = "${page_name == 'manage_class_categories'}"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/manage_class_categories/">
                        <span><i class="fa fa-road"></i>Manage class categories</span>
                    </a>
                </li>
                
                <li class="<c:if test = "${page_name == 'class'}"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/classes/">
                        <span><i class="fa fa-chain"></i>Manage classes</span>
                    </a>
                </li>
                <li class="<c:if test = "${page_name == 'manage_terms'}"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/manage_terms">
                        <span><i class="glyphicon glyphicon-subtitles"></i>Manage terms</span>
                    </a>
                </li>
                <li class="<c:if test = "${page_name == 'section'}"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/section/">
                        <span><i class="fa fa-superscript"></i>Manage sections</span>
                    </a>
                </li>
                
                <li class="<c:if test = "${page_name == 'manage_subjects'}"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/manage_subjects/">
                       <span><i class="entypo-doc-text"></i>Manage subjects</span>
                    </a>
                </li> 
               
                
                <!-- SUBJECT -->
                <li class="<c:if test = "${page_name == 'subject'}"><c:out value="opened active has-sub"/></c:if>">
                    <a href="#">
                        <i class="entypo-docs"></i>
                        <span>Manage class subjects</span>
                    </a>
            
                <ul>
            <%
                try{
                classCategoryList = nav_bean.getClassCategories();
                current_academic_year_id = nav_bean.getCurrentAcademicYearId();
                if(classCategoryList.size() > 0 &&  current_academic_year_id > 0)
                {
                    for(ClassCategory classCategory : classCategoryList)
                    {
                        check_category = classCategory.getCategoryId();
                %>
                 <li class="<c:if test = "$(page_name == 'subject' && check_category > 0)">
                    <c:out value="opened active has-sub"/></c:if>">
                     <a href="#">
                         <span><i class="entypo-flow-tree"></i> <% out.write(classCategory.getName());%>
                         </span>
                     </a>
                         <!--<ul>  
                        <%
                            //classList = nav_bean.getClassesByCategoryAndYear(classCategory.getClassCategoryPK().getCategoryId(), current_academic_year_id);
                            //for(com.sivotek.school_management_system.entities.Class class1 : classList)
                            //{
                        %>
                             <li class="">
                                <a href="${pageContext.request.contextPath}/${login_type}/subject/<% //out.write(""+class1.getClassPK().getClassId());%>">
                                    <span><i class="fa fa-tint"></i><% //out.write(class1.getName());%></span>
                               </a>
                             </li>
                        <%
                            //}
                        %>
                         </ul>-->
                </li>
                    
            <%
                 }
               }
                }catch(Exception ex){}
            %>
         </ul>
        </li>
        
         <!-- CLASS ROUTINE -->
                <li class="<c:if test = "$(page_name == 'class_routine')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/class_routine/">
                        <i class="entypo-target"></i>
                        <span>Manage class routine</span>
                    </a>
                </li>
            </ul>
        </li>

        <!-- STUDENT -->
         <li class="<c:if test="${page_name == 'student_add' ||
                                 page_name == 'student_bulk_add' ||
                                 page_name == 'student_information' ||
                                 page_name == 'student_profile' ||
                                 page_name == 'student_marksheet' ||
                                 page_name == 'student_edit' ||
                                 page_name == 'class_promotion' ||
                                 page_name == 'student_management_dashboard' ||
                                 page_name == 'student_information_landing' ||
                                 page_name == 'fee_advanced_filter' ||
                                 page_name == 'student_advanced_search' ||
                                 page_name == 'daily_attendance_report' ||
                                 page_name == 'manage_attendance' ||
                                 page_name == 'manage_subject_attendace'}
                    ">
                    <c:out value="opened active has-sub root-level"/>
                  </c:if>
           ">
            <a href="#">
                <i class="entypo-shareable fa-spin"></i>
                <span>Student management</span>
            </a>
           
            <ul>
                
                <!-- STUDENT ADMISSION -->
                <li class="<c:if test = "$(page_name == 'student_add')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/student_add">
                      <span><i class="fa fa-smile-o"></i>Admit student</span>
                    </a>
                </li>

                <!-- STUDENT BULK ADMISSION -->
                <li class="<c:if test = "$(page_name == 'student_bulk_add')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/student_bulk_add">
                        <span><i class="glyphicon glyphicon-import"></i>Admit bulk student</span>
                    </a>
                </li>

                <!-- STUDENT INFORMATION -->
                <li class="<c:if test = "$(page_name == 'student_information' || page_name =='student_profile' || page_name=='student_edit' || page_name=='student_marksheet') || page_name=='student_information_landing'">
                        <c:out value="opened active has-sub"/></c:if>">
                    <a href="#">
                        <span><i class="fa fa-info"></i> Student information</span>
                    </a>
                 <ul>
                <%
                    classCategoryList = nav_bean.getClassCategories();
                    current_academic_year_id = nav_bean.getCurrentAcademicYearId();
                    for(ClassCategory classCategory : classCategoryList)
                    {
                        check_category = classCategory.getCategoryId();
                %>
                 <li class="<c:if test = "$(page_name == 'subject' && check_category > 0)">
                    <c:out value="opened active has-sub"/></c:if>">
                     <a href="#">
                         <span><i class="entypo-flow-tree"></i> <% out.write(classCategory.getName());%>
                         </span>
                     </a>
                         <!--<ul>  
                        <%
                            //if(current_academic_year_id > 0)
                           // {
                           // classList = nav_bean.getClassesByCategoryAndYear(classCategory.getClassCategoryPK().getCategoryId(), current_academic_year_id);
                           // for(com.sivotek.school_management_system.entities.Class class1 : classList)
                           // {
                        %>
                             <li class="">
                                <a href="${pageContext.request.contextPath}/${login_type}/student_information/<% //out.write(""+class1.getClassPK().getClassId());%>">
                                    <span><i class="fa fa-tint"></i><% //out.write(class1.getName());%></span>
                               </a>
                             </li>
                        <%
                           // }
                         // }
                        %>
                         </ul>-->
                </li>
                    
                <%
                    }
                %>
         </ul>
                </li>
                
                 <!-- STUDENT CLASS PROMOTION -->
                <li class="<c:if test = "$(page_name == 'class_promotion')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/class_promotion/">
                       <span><i class="fa fa-exchange"></i>Student class promotion</span>
                    </a>
                </li>
                
                 <!-- STUDENT ADVANCED SEARCH -->
                <li class="<c:if test = "$(page_name == 'student_advanced_search')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/student_advanced_search/">
                     <span><i class="entypo-search"></i>Student advanced search</span>
                    </a>
                </li>
                
                  
                      
                   <li class="<c:if test="${page_name == 'daily_attendance_report' ||
                                 page_name == 'manage_attendance'}">
                          <c:out value="opened active has-sub"/>
                    </c:if>">
                    <a href="#">
                        <span><i class="entypo-chart-area has-sub"></i>Manage student attendance</span>
                    </a>
                <ul>
                <li class="<c:if test = "$(page_name == 'manage_attendance')"><c:out value="active"/></c:if>">
                     <a href="${pageContext.request.contextPath}/${login_type}/manage_attendance/">
                            <span><i class="entypo-chart-area"></i>Daily attendance</span>
                     </a>
                </li>
                
                <li class="<c:if test = "$(page_name == 'daily_attendance_report')"><c:out value="active"/></c:if>">
                        
                        <a href="${pageContext.request.contextPath}/${login_type}/daily_attendance_report/">
                           <span><i class="fa fa-bar-chart-o"></i>Attendance report</span>
                        </a>
                    </li>
                </ul>
                </li>
                
                                
            </ul>
            
            
                    
                    
        </li>

        
       <!-- EXAMS -->
    <li class="<c:if test="${page_name == 'exam' ||
                              page_name == 'grade' ||
                              page_name == 'marks' ||
                              page_name == 'generate_class_position' ||
                              page_name == 'exam_marks_sms' ||
                              page_name == 'tabulation_sheet' ||
                              page_name == 'ca_marks' ||
                              page_name == 'exam_dashboard' ||
                              page_name == 'exam_list_landing' ||
                              page_name == 'exam_grade' ||
                              page_name == 'terminal_marksheet_report' ||
                              page_name == 'manage_behavioural_traits' ||
                              page_name == 'manage_behavioural_rating_key' ||
                              page_name == 'mass_result_print' ||
                              page_name == 'mass_result_print_view'}">
                 <c:out value="opened active has-sub root-level"/></c:if> ">

            <a href="#">
                <i class="entypo-graduation-cap"></i>
                <span>Manage exam</span>
            </a>
            <ul>
                <li class="<c:if test = "$(page_name == 'exam_list_landing')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/exam_list_landing">
                         <span><i class="glyphicon glyphicon-list-alt"></i>Exam list</span>
                    </a>
                </li>
                <li class="<c:if test = "$(page_name == 'exam_grade')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/exam_grade">
                        <span><i class="glyphicon glyphicon-sort-by-order-alt"></i>Exam grades</span>
                    </a>
                </li>
                <li class="<c:if test = "$(page_name == 'marks')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/marks">
                        <span><i class="glyphicon glyphicon-check"></i>Manage exam marks</span>
                    </a>
                </li>
                
                 
                
                <li class="<c:if test = "$(page_name == 'ca_marks')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/ca_marks">
                        <span><i class="glyphicon glyphicon-pencil"></i>Manage CA marks</span>
                    </a>
                </li>
                

                <li class="<c:if test = "$(page_name == 'generate_class_position')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/exam_class_position">
                        <span><i class="fa fa-sort-amount-desc"></i>Generate exam positions</span>
                    </a>
                </li>

                <li class="<c:if test = "$(page_name == 'tabulation_sheet')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/tabulation_sheet">
                        <span><i class="fa fa-table"></i>Tabulation sheet</span>
                    </a>
                </li>

                

                <li class="<c:if test = "$(page_name == 'terminal_marksheet_report')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/terminal_marksheet_report">
                       <span><i class="fa fa-bar-chart-o"></i>Terminal marksheet report</span>
                    </a>
                </li>

                <li class="<c:if test = "$(page_name == 'mass_result_print' || page_name =='mass_result_print_view')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/mass_result_print">
                        <span><i class="fa fa-print"></i>Mass result print</span>
                    </a>
                </li>

                <li class="<c:if test = "$(page_name == 'manage_behavioural_traits')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/manage_behavioural_trait">
                      <span><i class="fa fa-gavel"></i>Manage behavioural trait</span>
                </a>
                </li>

                <li class="<c:if test = "$(page_name == 'manage_behavioural_rating_key')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/manage_behavioural_rating_key">
                        <span><i class="fa fa-star-o fa-spin"></i>Behavioural rating</span>
                </a>
                </li>
            </ul>
        </li>


<!-- ACCOUNTING -->
          <li class="<c:if test="${page_name == 'manage_bank_privileges' ||
                              page_name == 'manage_bank_institutions' ||
                              page_name == 'manage_bank_accounts' ||
                              page_name == 'raise_invoice' ||
                              page_name == 'transaction_report_summary' ||
                              page_name == 'accounting_management_dashboard'}">
                 <c:out value="opened active has-sub root-level"/></c:if>">
              
          

                <a href="#">
                    <i class="entypo-suitcase"></i>
                    <span>Accounting</span>
                </a>

                <ul>

                <li class="<c:if test = "$(page_name == 'manage_bank_institutions')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/manage_bank_institutions">
                        <span><i class="glyphicon glyphicon-th-large"></i>Manage bank institutions</span>
                    </a>
                </li>
                
                <li class="<c:if test = "$(page_name == 'manage_bank_accounts' || page_name == 'manage_bank_accounts')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/manage_bank_accounts">
                        <span><i class="fa fa-credit-card"></i>Manage bank accounts</span>
                    </a>
                </li>
                
                <li class="<c:if test = "$(page_name == 'manage_bank_privileges')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/manage_bank_privileges">
                        <span><i class="fa fa-key"></i>Manage bank privileges</span>
                    </a>
                </li>

                <li class="<c:if test = "$(page_name == 'raise_invoice')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/raise_invoice">
                        <span><i class="fa fa-tag"></i>Invoice management</span>
                    </a>
                </li>

                
                
                <li class="<c:if test = "$(page_name == 'transaction_report_summary')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/transaction_report_summary">
                        <span><i class="fa fa-tachometer"></i>Transaction report summary</span>
                    </a>
                </li>
                              
                
                </ul>
            </li>


       
         <!-- HUMAN RESOURCE -->
         <li class="<c:if test="${page_name == 'add_emp' ||
                              page_name == 'add_employee' ||
                              page_name == 'add_department' ||
                              page_name == 'add_designation' ||
                              page_name == 'department_list' ||
                              page_name == 'employee_list' ||
                              page_name == 'view_employee' ||
                              page_name == 'edit_employee' ||
                              page_name == 'emp_attendance' ||
                              page_name == 'emp_attendance_report' ||
                              page_name == 'manage_salary_details' ||
                              page_name == 'emp_salary_list' ||
                              page_name == 'emp_make_payment' ||
                              page_name == 'emp_generate_slip' ||
                              page_name == 'emp_salary_details' ||
                              page_name == 'employee_award' ||
                              page_name == 'emp_generate_salary_invoice' ||
                              page_name == 'hr_management_dashboard' ||
                              page_name == 'employee_advanced_search'}">
                 <c:out value="opened active has-sub root-level"/></c:if>">
             
        
            <a href="#">
                <i class="entypo-user"></i>
                <span>HR management</span>
            </a>
            <ul>
               <li class="<c:if test="${page_name == 'add_department' ||
                              page_name == 'add_designation' ||
                              page_name == 'add_employee' ||
                              page_name == 'employee_list' ||
                              page_name == 'department_list' ||
                              page_name == 'employee_list' ||
                              page_name == 'view_employee' ||
                              page_name == 'edit_employee' ||
                              page_name == 'employee_award' ||
                              page_name == 'employee_advanced_search'}">
                 <c:out value="opened active has-sub root-level"/></c:if>">
                    <a href="#">
                        <span><i class="entypo-down"></i>Employee management</span>
                    </a>
                <ul>
                <li class="<c:if test = "$(page_name == 'add_department')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/add_department">
                        <span><i class="fa fa-building-o"></i>Add department</span>
                    </a>
                </li>
                
                <li class="<c:if test = "$(page_name == 'add_designation')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/add_designation">
                        <span><i class="fa fa-cog"></i>Add designation</span>
                    </a>
                </li>
                
                 <li class="<c:if test = "$(page_name == 'department_list')"><c:out value="active"/></c:if>">
                     <a href="${pageContext.request.contextPath}/${login_type}/department_list">
                        <span><i class="fa fa-list-alt"></i>Department list</span>
                    </a>
                </li>
                
                
                <li class="<c:if test = "$(page_name == 'add_employee')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/add_employee">
                        <span><i class="fa fa-user"></i>Add employee</span>
                    </a>
                </li>
                
                <li class="<c:if test = "$(page_name == 'employee_list' || page_name == 'view_employee' || page_name == 'edit_employee')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/employee_list">
                        <span><i class="fa fa-sort-numeric-asc"></i>Employee list</span>
                    </a>
                </li>
                <li class="<c:if test = "$(page_name == 'employee_award')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/employee_award">
                        <span><i class="fa fa-trophy"></i>Employee award</span>
                    </a>
                </li>
                
                 <!-- EMPLOYEE ADVANCED SEARCH -->
                <li class="<c:if test = "$(page_name == 'employee_advanced_search')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/employee_advanced_search">
                        <span><i class="entypo-search"></i>Employee advanced search</span>
                    </a>
                </li>
                </ul>
                </li>
               
                <li class="<c:if test="${page_name == 'emp_attendance' ||
                              page_name == 'emp_attendance_report'}">
                 <c:out value="opened active has-sub root-level"/></c:if>">
                    <a href="#">
                        <span><i class="entypo-down"></i>Attendance management</span>
                    </a>
                <ul>
                <li class="<c:if test = "$(page_name == 'emp_attendance')"><c:out value="active"/></c:if>">
                    <a href="">
                        <span><i class="entypo-chart-area"></i>Employee attendance</span>
                    </a>
                </li>
                
                <li class="<c:if test = "$(page_name == 'emp_attendance_report')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/emp_attendance_report">
                        <span><i class="fa fa-bar-chart-o"></i>Attendance report</span>
                    </a>
                </li>
                


                
                
                
                </ul>
                </li>
                
                
                
                    
                <li class="<c:if test="${page_name == 'manage_salary_details' ||
                              page_name == 'emp_salary_list' ||
                              page_name == 'emp_make_payment' ||
                              page_name == 'emp_generate_slip' ||
                              page_name == 'emp_salary_details' ||
                              page_name == 'emp_generate_salary_invoice' ||
                              page_name == 'emp_create_salary_invoice' ||
                              page_name == 'employee_salary_payment_history'}">
                 <c:out value="opened active has-sub"/></c:if>">
                    
                    <a href="#">
                        <span><i class="fa fa-dollar has-sub"></i> Payroll management</span>
                    </a>
                <ul>
                <li class="<c:if test = "$(page_name == 'manage_salary_details')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/manage_salary_details">
                        <span><i class="fa fa-edit"></i>Manage salary details</span>
                    </a>
                </li>
                
                <li class="<c:if test = "$(page_name == 'emp_salary_list' || page_name == 'emp_salary_details')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/emp_salary_list">
                        <span><i class="fa fa-sort-numeric-asc"></i>Employee salary list</span>
                    </a>
                </li>
                
                <li class="<c:if test = "$(page_name == 'employee_salary_payment_history')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/employee_salary_payment_history">
                        <span><i class="fa fa-archive"></i>Salary payment history</span>
                    </a>
                </li>
                 </ul>
                </li>
            </ul>
        </li>
        
        
         <li class="<c:if test="${page_name == 'add_employee_user' ||
                              page_name == 'edit_employee_user' ||
                              page_name == 'employee_user_list'}">
          <c:out value="opened active has-sub root-level"/></c:if>">
             
           <a href="#">
                <i class="entypo-users"></i>
                <span>Manage system users</span>
            </a>
             
            <ul>
          
             <li class="<c:if test = "$(page_name == 'employee_user_list')"><c:out value="active"/></c:if>">
                 <a href="${pageContext.request.contextPath}/${login_type}/employee_user_list">
                        <i class="entypo-dot"></i>
                        <span>Manage users</span>
                    </a>
            </li>
            </ul>
         </li>
        
        
        

        <!-- NOTICEBOARD -->
        <li class="<c:if test = "$(page_name == 'noticeboard')"><c:out value="active"/></c:if>">
            <a href="${pageContext.request.contextPath}/${login_type}/noticeboard">
                <i class="entypo-doc-text-inv"></i>
                <span>Noticeboard</span>
            </a>
        </li>
        
        
        
        
        
        <!-- MESSAGING -->
         <li class="<c:if test="${page_name == 'message' ||
                              page_name == 'sms'}">
          <c:out value="opened active has-sub"/></c:if>">
       
            <a href="#">
                <i class="entypo-mail"></i>
                <span>Messaging</span>
            </a>
            <ul>
                <li class="<c:if test = "$(page_name == 'message')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/message">
                        <i class="entypo-dot"></i>
                        <span>Message</span>
                    </a>
                </li>
                
              
            </ul>
        </li>
        
        
        

        <!-- SETTINGS -->
                   
          <li class="<c:if test="${page_name == 'set_working_days' ||
                              page_name == 'system_settings' ||
                              page_name == 'manage_language' ||
                              page_name == 'sms_settings' ||
                              page_name == 'academic_years' ||
                              page_name == 'holiday_list' ||
                              page_name == 'fee_categories' ||
                              page_name == 'system_settings_dashboard' ||
                              page_name == 'manage_profile'}">
                 <c:out value="opened active has-sub root-level"/></c:if>">
              
            <a href="#">
                <i class="entypo-tools"></i>
                <span>System settings</span>
            </a>
            <ul>
                <li class="<c:if test = "$(page_name == 'academic_years')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/academic_years_setting">
                        <span><i class="fa fa-th"></i>Academic years</span>
                    </a>
                </li>
                <li class="<c:if test = "$(page_name == 'system_settings')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/system_settings">
                        <span><i class="fa fa-wrench"></i>General settings</span>
                    </a>
                </li>
                
                
                <li class="<c:if test = "$(page_name == 'set_working_days')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/set_working_days">
                        <span><i class="fa fa-calendar-o"></i>Set workdays</span>
                    </a>
                </li>
                <li class="<c:if test = "$(page_name == 'holiday_list')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/holiday_list">
                        <span><i class="fa fa-calendar"></i>Holiday list</span>
                    </a>
                </li>

                
                
                <li class="<c:if test = "$(page_name == 'sms_settings')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/sms_settings">
                        <span><i class="glyphicon glyphicon-send"></i>SMS settings</span>
                    </a>
                </li>
                <!-- ACCOUNT -->
                <li class="<c:if test = "$(page_name == 'manage_profile')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/manage_profile">
                        <i class="entypo-lock"></i>
                        <span>Account</span>
                    </a>
                </li>
                
                
            </ul>
        </li>





        <!-- Data Management -->
        
        <li class="<c:if test="${page_name == 'backup' ||
                              page_name == 'restore' ||
                              page_name == 'data_management_dashboard'}">
                 <c:out value="opened active has-sub root-level"/></c:if>">
            
            <a href="#">
                <i class="glyphicon glyphicon-cloud-upload"></i>
                <span>Data management</span>
            </a>
            <ul>
                <li class="<c:if test = "$(page_name == 'backup')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/backup">
                        <span><i class="glyphicon glyphicon-save"></i>Data export</span>
                    </a>
                </li>
                <li class="<c:if test = "$(page_name == 'restore')"><c:out value="active"/></c:if>">
                    <a href="${pageContext.request.contextPath}/${login_type}/restore">
                        <span><i class="glyphicon glyphicon-import"></i>Data import</span>
                    </a>
                </li>
                
               
                
                
            </ul>
        </li>
        

        

    </ul>

</div>


  

