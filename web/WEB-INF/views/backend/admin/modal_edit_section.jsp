<%-- 
    Document   : modal_edit_section
    Created on : Jun 14, 2017, 3:41:33 PM
    Author     : acer
--%>

<%@page import="com.sivotek.school_management_system.entities.Term"%>
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
    pageContext.setAttribute("termPOJOList","");
%>
<%! 
    
    Collection<AcademicYears> academicYearList = null;
    Collection<ClassCategory> classCategoryList = null;
    AcademicYears academicYear = new AcademicYears();
    AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
    
    Integer counter = 0;
    long branch = 0L;
    ArrayList academicYearsPOJOList;
    ArrayList classCategoryPOJOList;
    ArrayList termPOJOList;
%>

<%
    academicYearsPOJOList = new ArrayList();
    classCategoryPOJOList = new ArrayList();
    termPOJOList = new ArrayList();
    
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
    
    
   academicYear = academicYearsJpaController.findAcademicYears(Long.parseLong(""+session.getAttribute("TermYearId")));
    for(Term term : academicYear.getTermCollection())
    {
       Map ter = new HashMap();
       ter.put("name", term.getName());
       ter.put("term_id", term.getTermId());
       termPOJOList.add(ter);
    }

    pageContext.setAttribute("termPOJOList", termPOJOList);
   
    
%>

<div class="row">
<div class="col-md-12">
<div class="panel panel-primary" data-collapsed="0">
        	<div class="panel-heading">
            	<div class="panel-title">
            	<i class="entypo-plus-circled"></i>
			Edit section
            	</div>
            </div>
			<div class="panel-body">
<!----CREATION FORM STARTS---->
                    <form method="POST" commandName="SectionPOJO" action="${cpk}/${login_type}/modal_edit_section/" id="form" class="form-horizontal form-groups-bordered validate" enctype="multipart/form-data">
                      <input type="hidden" name="section_id" value="${SectionPOJO.getSectionId()}"/>  
                      <div class="padded form-horizontal form-groups-bordered">
                          <div class="form-group">
                                <label for="field-1" class="col-sm-3 control-label">Name<span class="text-danger"> *</span></label>

                                <div class="col-sm-6 controls">
                                    <input type="text" class="form-control" name="name" data-validate="required" data-message-required="value required" value="${SectionPOJO.getName()}" autofocus>
                                </div>
                           </div>
                          
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Class category<span class="text-danger"> *</span></label>
                                <div class="col-sm-6 controls">
                                    <select name="class_category_id"  class="form-control" id="class_category_id" data-validate="required" data-message-required="value required">
                                          <option value="">Select</option>
                                          <c:forEach items="${classCategoryPOJOList}" var="category">
                                            <option value="${category.category_id}"  <c:if test="${category.category_id == SectionPOJO.getClassCategoryId().getCategoryId()}">selected="true"</c:if>>
                                                <c:out value="${category.name}" />
                                            </option>
                                            </c:forEach>
                                    </select>
                                </div>
                            </div>
                            
                            
                    <div class="form-group">
                    <label class="col-sm-3 control-label">Academic year<span class="text-danger"> *</span></label>
                    <div class="col-sm-6 controls">
                        <select name="academic_year" class="form-control" data-validate="required" data-message-required="value required" onchange="return get_academic_year_terms(this.value);">
                              <option value="">Select</option>
                              <c:forEach items="${academicYearList}" var="academicYr">
                                <option value="${academicYr.year_id}"  <c:if test="${academicYr.year_id == SectionPOJO.getTermId().getAcademicYear().getYearId()}">selected="true"</c:if>>
                                    <c:out value="${academicYr.name}" />
                                </option>
                                </c:forEach>
                        </select>

                    </div>
                    </div>
                                
                    <div class="form-group">
                    <label class="col-sm-3 control-label">Term</label>
                    <div class="col-sm-6 controls">
                        <select name="term_id" class="form-control selectboxit-text" style="width:100%;" id="term_selection_holder" data-validate="required" data-message-required="value required">
                             <option value="">Select</option>
                             <c:forEach items="${termPOJOList}" var="term">
                              <option value="${term.term_id}"  <c:if test="${term.term_id == SectionPOJO.getTermId().getTermId()}">selected="true"</c:if>>
                                  <c:out value="${term.name}" />
                              </option>
                              </c:forEach>
                        </select>
                    </div>
                    </div>
                         
                
            </div>
            <div class="form-group">
                  <div class="col-sm-offset-3 col-sm-5">
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
<script type="text/javascript">

 function get_academic_year_terms(year_id)
        {
            $.ajax({
            url: '${cpk}/${login_type}/get_terms_by_academic_year/' + year_id,
            success: function(response)
            {
                $('#term_selection_holder').html(response);
            }
    });
        }
        
        
</script>