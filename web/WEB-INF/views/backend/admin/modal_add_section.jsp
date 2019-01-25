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
                    Add new section
                </div>
            </div>
            <div class="panel-body">
                <form method="POST" action="${cpk}/${login_type}/modal_add_section/" id="form" class="form-horizontal form-groups-bordered validate" enctype="multipart/form-data">
                
                    <div class="form-group">
                        <label for="field-1" class="col-sm-3 control-label">Name<span class="text-danger"> *</span></label>
                        
                        <div class="col-sm-6 controls">
                            <input type="text" class="form-control" name="name" data-validate="required" data-message-required="value required" value="" autofocus>
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
                        <label for="field-2" class="col-sm-3 control-label">Academic year<span class="text-danger"> *</span></label>
                        
                        <div class="col-sm-6 controls">
                            <select name="academic_year" class="form-control  selectboxit-text" data-validate="required" data-message-required="value required"
                                     onchange="return get_academic_year_terms(this.value);">
                              <option value="">Select</option>
                              <%
                                  academicYearList = cate_bean.findAcademicYearsByBranchId(Long.parseLong(""+session.getAttribute("branch_id")));
                                  for(AcademicYears academicYear : academicYearList)
                                  {
                                  
                                  
                                %>
                             
                                        <option value="<% out.write(academicYear.getYearId()+"");%>">
                                           <% out.write(academicYear.getName()+"");%>
                                        </option>
                                    <%
                                      }
                                    %>
                             </select>
                        </div> 
                    </div>
                            
                           
                                        
                    <div class="form-group">
                    <label class="col-sm-3 control-label">Term</label>
                    <div class="col-sm-6 controls">
                        <select name="term_id" class="form-control selectboxit-text" style="width:100%;" id="term_selection_holder" data-validate="required" data-message-required="value required">
                            <option value="">Select year first</option>
                        </select>
                       

                    </div>
                </div>
                    
                    
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-5">
                            <button type="submit" id="submit" class="btn btn-info">Add section</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>



<script src="${cpk}/resources/js/neon-custom-ajax.js" type="text/javascript"></script>
<script src="${cpk}/resources/js/modal_custom_js.js" type="text/javascript"></script>

<script type="text/javascript">

 function get_academic_year_terms(year_id)
        {
            //$('#term_selection_holder').html('<option>select year first</option>');
            $.ajax({
            url: '${cpk}/${login_type}/get_terms_by_academic_year/' + year_id,
            success: function(response)
            {
                $('#term_selection_holder').html(response);
            }
    });
        }
        
        
</script>

