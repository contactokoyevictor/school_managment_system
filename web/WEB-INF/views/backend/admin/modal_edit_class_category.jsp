<%-- 
    Document   : modal_edit_class_category
    Created on : May 6, 2017, 3:44:32 PM
    Author     : acer
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
    <div class="col-md-12">
        <div class="panel panel-primary panel-shadow" data-collapsed="0">
            <div class="panel-heading">
                <div class="panel-title" >
                    <i class="entypo-plus-circled"></i>
            Edit Class Category
                </div>
                </div>
        <div class="panel-body">
                  
            <form:form method="POST" commandName="ClassCategoryPOJO" action="${pageContext.request.servletContext.contextPath}/${login_type}/modal_edit_class_category/" id="form" class="form-horizontal form-groups-bordered validate" enctype="multipart/form-data">
                <form:hidden path="categoryId" value="${ClassCategoryPOJO.getCategoryId()}"/>
                
                
                   <div class="form-group">
                            <label for="field-1" class="col-sm-3 control-label">Category Name</label>
                            <div class="col-sm-5">
                                    <form:input type="text" class="form-control" path="name" value="${StatePOJO.name}" data-validate="required" data-message-required="value required" />
                            </div>
                    </div>
                    
                    <div class="form-group">
                            <label for="field-1" class="col-sm-3 control-label">Short Name</label>

                            <div class="col-sm-5">
                                    <form:input type="text" class="form-control" path="nickName" value="${StatePOJO.code}" data-validate="required" data-message-required="value required" />
                            </div>
                    </div>
                    
                                       
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-5">
                                     <button type="submit" name="submit_button" class="btn btn-info" id="submit">Update Record</button>
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


