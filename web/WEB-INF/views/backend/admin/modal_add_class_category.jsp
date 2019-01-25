<%-- 
    Document   : modal_add_class_category
    Created on : 01-May-2017, 01:35:56
    Author     : MY USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="crud" scope="request" class="com.sivotek.school_management_system.app.crud.CrudModel" />
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-primary panel-shadow" data-collapsed="0">
            <div class="panel-heading">
                <div class="panel-title" >
                    <i class="entypo-plus-circled"></i>
                    Add Class Category
                </div>
                </div>
        <div class="panel-body">
                  
            <form:form method="POST" commandName="ClassCategoryPOJO" action="/school_management_system/${login_type}/add_class_category/" id="form" class="form-horizontal form-groups-bordered validate" enctype="multipart/form-data">
                <form:hidden path="categoryId" value="${ClassCategoryPOJO.getCategoryId()}"/>
                   <div class="form-group">
                            <label for="field-1" class="col-sm-3 control-label">Category Name<span class="text-danger"> *</span></label>

                            <div class="col-sm-5">
                                    <form:input type="text" class="form-control" path="name" data-validate="required" data-message-required="value required" />
                            </div>
                    </div>
                    
                    <div class="form-group">
                           <label for="field-1" class="col-sm-3 control-label">Short Name<span class="text-danger"> *</span></label>

                            <div class="col-sm-5">
                                    <form:input type="text" class="form-control" path="nickName" data-validate="required" data-message-required="value required" />
                            </div>
                    </div>
                    
                                       
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-5">
                                     <button type="submit" name="submit_button" class="btn btn-info" id="submit">Add Record</button>
                        </div>
                    </div>
                 </form:form>
            </div>
        </div>
            
        </div>
    
    
</div>    
            
            
            
            
            

<script src="<% out.write(request.getContextPath());%>/resources/js/neon-custom-ajax.js" type="text/javascript"></script>
<script src="<% out.write(request.getContextPath());%>/resources/js/modal_custom_js.js" type="text/javascript"></script>

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

