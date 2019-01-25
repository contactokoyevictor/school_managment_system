<%-- 
    Document   : modal_add_class
    Created on : Jun 8, 2017, 10:54:56 AM
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
<div class="row">
<div class="col-md-12">
<div class="panel panel-primary" data-collapsed="0">
        	<div class="panel-heading">
            	<div class="panel-title">
            	<i class="entypo-plus-circled"></i>
			Add class
            	</div>
            </div>
			<div class="panel-body">
<!----CREATION FORM STARTS---->
                    <form method="POST" action="${cpk}/${login_type}/modal_add_class/" id="form" class="form-horizontal form-groups-bordered validate" enctype="multipart/form-data">
                      <div class="padded form-horizontal form-groups-bordered">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Name<span class="text-danger"> *</span></label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="name" data-validate="required" data-message-required="value required"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Name numeric<span class="text-danger"> *</span></label>
                                <div class="col-sm-6">
                                    <input type="number" placeholder="only numbers allowed" class="form-control" name="name_numeric" data-validate="required" data-message-required="value required"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Teacher (form master)</label>
                                <div class="col-sm-6">
                                    <select name="teacher_id" class="form-control select2" style="width:100%;">
                                        <option value="">Select</option>
                                        <%
                                            teacherList = cate_bean.get_list_of_teachers(Long.parseLong(session.getAttribute("branch_id")+""));
                                            if(teacherList.size() > 0)
                                            {
                                                for(Employee employee : teacherList)
                                                {
                                         %>
                                        <option value="<% out.write(employee.getEmployeeId()+"");%>">
                                            <% out.write(employee.getFirstName()+" "+employee.getLastName()+" ("+employee.getDesignationId().getName()+")");%>
                                        </option>
                                        <%       
                                                }
                                            }
                                        %>
                                        
                                    </select>
                                </div>
                            </div>
                            
                            
                    <div class="form-group">
                    <label class="col-sm-4 control-label">Academic year<span class="text-danger"> *</span></label>
                    <div class="col-sm-6 controls">
                        <select name="academic_year"  class="form-control selectboxit" data-validate="required" data-message-required="value required">
                              <option value="">Select</option>
                              <%
                                  academicYearList = cate_bean.findAcademicYearsByBranchId(Long.parseLong(""+session.getAttribute("branch_id")));
                                  String selected = "false";
                                  for(AcademicYears academicYear : academicYearList)
                                  {
                                     if(academicYear.getStatus().equalsIgnoreCase("active"))
                                     {
                                         selected = "true";
                                     
                                  
                              %>
                             
                                    <option value="<% out.write(academicYear.getYearId()+"");%>" selected="<% out.write(selected);%>">
                                    <% out.write(academicYear.getName()+"");%>
                                    </option>
                                    <%
                                     }else{
                                    %>
                                        <option value="<% out.write(academicYear.getYearId()+"");%>">
                                           <% out.write(academicYear.getName()+"");%>
                                        </option>
                                    <%
                                           }
                                        }
                                    %>
                         </select>

                    </div>
                </div>
                         
                <div class="form-group">
                    <label class="col-sm-4 control-label">Class category<span class="text-danger"> *</span></label>
                    <div class="col-sm-6 controls">
                        <select name="class_category_id" class="form-control selectboxit" id="class_category_id" data-validate="required" data-message-required="value required">
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
            </div>
            <div class="form-group">
                  <div class="col-sm-offset-5 col-sm-5">
                      <button type="submit" id="submit" class="btn btn-info">Add class</button>
                  </div>
            </div>
                    </form>
                        </div>
            <!----CREATION FORM ENDS-->
        </div>
</div>



<script src="${cpk}/resources/js/neon-custom-ajax.js" type="text/javascript"></script>
<script src="${cpk}/resources/js/modal_custom_js.js" type="text/javascript"></script>

<script type="text/javascript">   
        $("form").submit(function(e) {
            var $form = $(this);
            if($form.data('submitted')){
                    $("#submit", this).attr("disabled", "disabled");
                    $(":submit", this).attr("disabled", "disabled");
                    e.preventDefault();
                    $("#submit", this).removeAttr("disabled");
                    $(":submit", this).removeAttr("disabled");
                }
                else{
                    $("#submit", this).removeAttr("disabled");
                    $(":submit", this).removeAttr("disabled");
                }
        });

</script>



