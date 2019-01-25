<%-- 
    Document   : modal_add_section
    Created on : Jun 14, 2017, 3:40:39 PM
    Author     : acer
--%>

<%@page import="com.sivotek.school_management_system.entities.ClassCategory"%>
<%@page import="com.sivotek.school_management_system.entities.Employee"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cpk" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<jsp:useBean id="cate_bean" scope="request" class="com.sivotek.school_management_system.app.crud.crud_bean" />
<%@page import="com.sivotek.school_management_system.entities.AcademicYears;" %>
<%@page import="com.sivotek.school_management_system.entities.controllers.AcademicYearsJpaController;" %>
<jsp:setProperty name="cate_bean" property="*" />
<%! 
    
    Collection<AcademicYears> academicYearList = null;
    Collection<Employee> teacherList = null;
    Collection<ClassCategory> classCategoryList = null;
    Integer counter = 0;
    long branch = 0L;

%>
<!DOCTYPE html>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-primary" data-collapsed="0">
            <div class="panel-heading">
                <div class="panel-title" >
                    <i class="entypo-plus-circled"></i>
                    Add new subject
                </div>
            </div>
            <div class="panel-body">
                <form method="POST" action="${cpk}/${login_type}/modal_add_subject/" id="form" class="form-horizontal form-groups-bordered validate" enctype="multipart/form-data">
                    
                    <div class="form-group">
                        <label for="field-2" class="col-sm-3 control-label">Academic year<span class="text-danger"> *</span></label>
                        
                        <div class="col-sm-6 controls">
                            <select name="academic_year" class="form-control  selectboxit-text" data-validate="required" data-message-required="value required">
                              <option value="">Select</option>
                              <%
                                  academicYearList = cate_bean.findAcademicYearsByBranchId(Long.parseLong(""+session.getAttribute("branch_id")));
                                  for(AcademicYears academicYear : academicYearList)
                                  {
                                  
                                  
                                %>
                             
                                <option value="<% out.write(academicYear.getYearId()+"");%>" <%if(academicYear.getStatus().equalsIgnoreCase("active")){out.write("selected='true'");}%>>
                                           <% out.write(academicYear.getName()+"");%>
                                        </option>
                                    <%
                                      }
                                    %>
                             </select>
                        </div> 
                    </div>

                    <div class="form-group">
                        <label for="field-2" class="col-sm-3 control-label">Class categories<span class="text-danger"> *</span></label>
                        
                        <div class="col-sm-6 controls">
                        <select name="class_category_id" class="form-control  selectboxit-text" id="class_category_id" data-validate="required" data-message-required="value required">
                         <option value="">Select</option>   
                            <%
                                classCategoryList = cate_bean.getClassCategories();
                                if(classCategoryList.size() > 0 )
                                {
                                    for(ClassCategory classCategory : classCategoryList)
                                    {
                             %>
                             <option value="<% out.write(classCategory.getCategoryId()+"");%>"><% out.write(classCategory.getName());%></option>
                             <%
                                    }
                                }
                            %>
                        </select>
                        </div> 
                    </div>

                    <div class="form-group">
                        <label for="field-1" class="col-sm-3 control-label">Subject name<span class="text-danger"> *</span></label>
                        
                        <div class="col-sm-6 controls">
                            <input type="text" class="form-control" name="name" data-validate="required" data-message-required="value required">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="field-1" class="col-sm-3 control-label">Subject code<span class="text-danger"> *</span></label>
                        
                        <div class="col-sm-6 controls">
                            <input type="text" class="form-control" name="code" data-validate="required" data-message-required="value required">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="field-1" class="col-sm-3 control-label">Nick name<span class="text-danger"> *</span></label>
                        
                        <div class="col-sm-6 controls">
                            <input type="text" class="form-control" name="nick_name" data-validate="required" data-message-required="value required">
                        </div>
                    </div>
                    
                    
                    
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-5">
                            <button type="submit" id="submit" class="btn btn-info">Add subject</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>



<script src="${cpk}/resources/js/neon-custom-ajax.js" type="text/javascript"></script>
<script src="${cpk}/resources/js/modal_custom_js.js" type="text/javascript"></script>

