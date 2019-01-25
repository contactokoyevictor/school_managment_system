<%-- 
    Document   : modal_edit_term
    Created on : Jun 5, 2017, 10:06:16 AM
    Author     : acer
--%>

<%@page import="java.util.Date"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sivotek.school_management_system.app.pojo.AcademicYearsPOJO"%>
<%@page import="java.util.List"%>
<%@page import="com.sivotek.school_management_system.entities.Term"%>
<%@page import="java.util.Collection"%>
<%@page import="com.sivotek.school_management_system.entities.AcademicYears"%>
<%@page import="com.sivotek.school_management_system.entities.controllers.AcademicYearsJpaController"%>
<%@page import="com.sivotek.school_management_system.app.pojo.AcademicYearsPOJO"%>
<%@ page language="java" contentType="text/html" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cpk" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<jsp:useBean id="cate_bean" scope="request" class="com.sivotek.school_management_system.app.crud.crud_bean" />
<jsp:setProperty name="cate_bean" property="*" />
<%! 
    AcademicYearsPOJO academicYearsPOJO;
    AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
     Collection<AcademicYears> academicYearList = null;
    ArrayList academicYearsPOJOList = new ArrayList();
   
%>
<%
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
    
%>



<div class="row">
    <div class="col-md-12">
        <div class="panel panel-primary panel-shadow" data-collapsed="0">
            <div class="panel-heading">
                <div class="panel-title" >
                    <i class="entypo-plus-circled"></i>
            Edit Term
                </div>
                </div>
        <div class="panel-body">
                  
            <form method="POST" commandName="TermPOJO" action="${pageContext.request.servletContext.contextPath}/${login_type}/modal_edit_term/" id="form" class="form-horizontal form-groups-bordered validate" enctype="multipart/form-data">
                <input type="hidden" name="term_id" value="${TermPOJO.getTerm_id()}"/>
                
                
                   <div class="form-group">
                            <label for="field-1" class="col-sm-3 control-label">Name<span class="text-danger"> *</span></label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" name="name" value="${TermPOJO.getName()}" data-validate="required" data-message-required="value required" autofocus>
                            </div>
                    </div>
                    
                    <div class="form-group">
                            <label for="field-1" class="col-sm-3 control-label">Nick name</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control" name="nick_name" value="${TermPOJO.getNick_name()}">
                            </div>
                     </div>
                    
                    
                     <div class="form-group">
                            <label for="field-1" class="col-sm-3 control-label">Start from<span class="text-danger"> *</span></label>
                            <div class="col-sm-5 input-group">
                                <input type="text" class="form-control datepicker" value="${TermPOJO.getStart_from()}" data-format="yyyy-mm-dd" name="start_from" id="start_from" data-validate="required" data-message-required="value required">
                                 <div class="input-group-addon"><a href="#"><i class="entypo-calendar"></i></a></div>
			    </div> 
                     </div>
                                 
                     <div class="form-group">
                            <label for="field-2" class="col-sm-3 control-label">End on<span class="text-danger"> *</span></label>

                            <div class="col-sm-5 input-group">
                                <input type="text" class="form-control datepicker" value="${TermPOJO.getEnd_in()}" data-format="yyyy-mm-dd" name="end_in" id="end_on" data-validate="required" data-message-required="value required">
                                    <div class="input-group-addon"><a href="#"><i class="entypo-calendar"></i></a></div>
                            </div> 
                    </div>
                     
                                    
                    <div class="form-group">
                    <label class="col-sm-3 control-label">Academic year<span class="text-danger"> *</span></label>
                    <div class="col-sm-5 controls">
                        
                        <select name="academic_year" class="form-control" data-validate="required" data-message-required="value required">
                              <option value="">Select</option>
                              <c:forEach items="${academicYearList}" var="academicYr">
                                <option value="${academicYr.year_id}"  <c:if test="${academicYr.year_id == TermPOJO.getAcademic_year()}">selected="true"</c:if>>
                                    <c:out value="${academicYr.name}" />
                                </option>
                                </c:forEach>
                              
                              
                          </select>

                    </div>
                </div>

                    
                <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-5">
                                <button type="submit" id="submit" class="btn btn-info">Update Record</button>
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
