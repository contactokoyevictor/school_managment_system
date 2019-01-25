<%-- 
    Document   : modal_add_class
    Created on : Jun 8, 2017, 10:54:56 AM
    Author     : acer
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
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
<%
    pageContext.setAttribute("teacherPOJOList","");
    pageContext.setAttribute("academicYearList","");
    pageContext.setAttribute("classCategoryPOJOList","");
%>
<%! 
    
    Collection<AcademicYears> academicYearList = null;
    Collection<Employee> teacherList = null;
    Collection<ClassCategory> classCategoryList = null;
    Integer counter = 0;
    long branch = 0L;
    ArrayList teacherPOJOList;
    ArrayList academicYearsPOJOList;
    ArrayList classCategoryPOJOList;
%>

<%
    teacherPOJOList = new ArrayList();
    academicYearsPOJOList = new ArrayList();
    classCategoryPOJOList = new ArrayList();
    
    teacherList = cate_bean.get_list_of_teachers(Long.parseLong(session.getAttribute("branch_id")+""));
    for(Employee employee : teacherList)
    {
       Map teacher = new HashMap();
       teacher.put("employee_id", employee.getEmployeeId());
       teacher.put("first_name", employee.getFirstName());
       teacher.put("last_name", employee.getLastName());
       teacher.put("designation", employee.getDesignationId().getName());
       teacherPOJOList.add(teacher);
    }
    
    pageContext.setAttribute("teacherPOJOList", teacherPOJOList);
    
    academicYearList = cate_bean.findByBranchId(Long.parseLong(""+session.getAttribute("branch_id")));
    for(AcademicYears academicYears : academicYearList)
    {
       Map academicYear = new HashMap();
       academicYear.put("name", academicYears.getName());
       academicYear.put("year_id", academicYears.getYearId());
       academicYear.put("status", academicYears.getStatus());
       academicYearsPOJOList.add(academicYear);
    }
    
    pageContext.setAttribute("academicYearList", academicYearsPOJOList);
    
    classCategoryList = cate_bean.getClassCategories();
    for(ClassCategory classCategory : classCategoryList)
    {
       Map Category = new HashMap();
       Category.put("category_id", classCategory.getCategoryId());
       Category.put("name", classCategory.getName());
       Category.put("nick_name", classCategory.getNickName());
       classCategoryPOJOList.add(Category);
    }
    
    pageContext.setAttribute("classCategoryPOJOList", classCategoryPOJOList);
    
%>

<div class="row">
<div class="col-md-12">
<div class="panel panel-primary" data-collapsed="0">
        	<div class="panel-heading">
            	<div class="panel-title">
            	<i class="entypo-plus-circled"></i>
			Edit class
            	</div>
            </div>
			<div class="panel-body">
<!----CREATION FORM STARTS---->
                    <form method="POST" commandName="ClassPOJO" action="${cpk}/${login_type}/modal_edit_class/" id="form" class="form-horizontal form-groups-bordered validate" enctype="multipart/form-data">
                      <input type="hidden" name="class_id" value="${ClassPOJO.getClass_id()}"/>  
                      <div class="padded form-horizontal form-groups-bordered">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Name<span class="text-danger"> *</span></label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="name" value="${ClassPOJO.getName()}" data-validate="required" data-message-required="value required"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Name numeric<span class="text-danger"> *</span></label>
                                <div class="col-sm-6">
                                    <input type="number" placeholder="only numbers allowed" value="${ClassPOJO.getName_numeric()}" class="form-control" name="name_numeric" data-validate="required" data-message-required="value required"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Teacher (form master)</label>
                                <div class="col-sm-6">
                                <select name="teacher_id" class="form-control select2" style="width:100%;">
                                      <option value="">Select</option>
                                      <c:forEach items="${teacherPOJOList}" var="teacher">
                                        <option value="${teacher.employee_id}"  <c:if test="${teacher.employee_id == ClassPOJO.getTeacher_id()}">selected="true"</c:if>>
                                            <c:out value="${teacher.first_name +' '+teacher.last_name +' ('+teacher.designation+')'}" />
                                        </option>
                                      </c:forEach>
                                </select>
                                </div>
                            </div>
                            
                            
                    <div class="form-group">
                    <label class="col-sm-4 control-label">Academic year<span class="text-danger"> *</span></label>
                    <div class="col-sm-6 controls">
                        <select name="academic_year" class="form-control" data-validate="required" data-message-required="value required">
                              <option value="">Select</option>
                              <c:forEach items="${academicYearList}" var="academicYr">
                                <option value="${academicYr.year_id}"  <c:if test="${academicYr.year_id == ClassPOJO.getAcademic_year()}">selected="true"</c:if>>
                                    <c:out value="${academicYr.name}" />
                                </option>
                                </c:forEach>
                        </select>

                    </div>
                </div>
                         
                <div class="form-group">
                    <label class="col-sm-4 control-label">Class category<span class="text-danger"> *</span></label>
                    <div class="col-sm-6 controls">
                        <select name="class_category_id"  class="form-control" id="class_category_id" data-validate="required" data-message-required="value required">
                              <option value="">Select</option>
                              <c:forEach items="${classCategoryPOJOList}" var="category">
                                <option value="${category.category_id}"  <c:if test="${category.category_id == ClassPOJO.getClass_category_id()}">selected="true"</c:if>>
                                    <c:out value="${category.name}" />
                                </option>
                                </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                  <div class="col-sm-offset-5 col-sm-5">
                      <button type="submit" id="submit" class="btn btn-info">Update record</button>
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



