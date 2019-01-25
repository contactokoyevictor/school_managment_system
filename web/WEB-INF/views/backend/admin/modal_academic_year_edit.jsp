<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
	<div class="col-md-12">
		<div class="panel panel-primary" data-collapsed="0">
        	<div class="panel-heading">
            	<div class="panel-title" >
            		<i class="entypo-plus-circled"></i>
					Edit academic year
            	</div>
            </div>
			<div class="panel-body">
				
                
                
                <form method="POST" commandName="AcademicYearsPOJO" action="${pageContext.request.servletContext.contextPath}/${login_type}/modal_edit_academic_year/" id="form" class="form-horizontal form-groups-bordered validate" enctype="multipart/form-data">
                <input type="hidden" name="yearId" value="${AcademicYearsPOJO.getYearId()}"/>
                
                                       
                   <div class="form-group"> 
                        <label class="col-sm-3 control-label">Academic year</label> 
                            <div class="input-group col-sm-7"> 
                                
                                <input type="text" class="form-control daterange required" name="academic_year" id="academic_year" placeholder="  e.g. YYYY-MM-DD / YYYY-MM-DD" 
                                       data-format="YYYY-MM-DD" data-start-date="2016-01-01" data-end-date="2017-01-01" data-separator=" / "
                                       value="${AcademicYearsPOJO.getName()}" data-validate="required" data-message-required="value required" />
                                
                                
                                
                                <div class="input-group-addon">
                                    <a href="#"><i class="entypo-calendar"></i></a>
                                </div>
                            </div> 
                                              
                     </div>
                                       
                     
                                       
                                       
                    <div class="form-group">
                       <div class="checkbox col-sm-offset-4">
                            <label>
                                
                                <c:if test="${AcademicYearsPOJO.getStatus() eq 'active'}">
                                    <c:set var="check_attribute" value="true" scope="request"/>
                                </c:if>
                                <c:if test="${AcademicYearsPOJO.getStatus() eq 'inactive'}">
                                    <c:set var="check_attribute" value="false" scope="request"/>
                                </c:if>
                                
                                <input type="checkbox" class="check"  name="Status" id="Status"/>
                                Mark As Default
                            </label>
                        </div>
                    </div>
                    
                    
                                       
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-5">
                                     <button type="submit" name="submit_button" class="btn btn-info" id="submit">Update Record</button>
                        </div>
                    </div>
                 </form>
                            
            </div>
        </div>
    </div>
</div>
           
<script src="<% out.write(request.getContextPath());%>/resources/js/neon-custom-ajax.js" type="text/javascript"></script>
<script src="<% out.write(request.getContextPath());%>/resources/js/modal_custom_js.js" type="text/javascript"></script>


<script type="text/javascript">

     $(document).ready(function(){
        var status = "${check_attribute}";
        
        if(status === "true")
        {
            //var chk = jQuery('.check');
            //chk.checked = true;
            //jQuery('.check').checked = true;
            jQuery('#Status').attr('checked', true); 
            jQuery('#Status').val("1");
            
        }
        if(status === "false")
        {
            //var chk = $('.check');
            //chk.checked = false;
            //jQuery('.check').checked = false;
            jQuery('#Status').attr('checked', false); 
            jQuery("#Status").val("1");
        }
         
          
    });
    
	
</script>

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