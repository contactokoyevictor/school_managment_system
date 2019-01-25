<div class="row">
	<div class="col-md-12">
		<div class="panel panel-primary panel-shadow" data-collapsed="0">
        	<div class="panel-heading">
            	<div class="panel-title" >
            		<i class="entypo-plus-circled"></i>
			Add academic year 
            	</div>
                </div>
		<div class="panel-body">
		<form method="POST" action="${pageContext.request.contextPath}/${login_type}/add_academic_year/" id="form" class="form-horizontal form-groups-bordered validate" enctype="multipart/form-data">
                    <div class="form-group"> 
                        <label class="col-sm-3 control-label">Academic year</label> 
                            <div class="input-group col-sm-7"> 
                                <input type="text" class="form-control daterange"  name="academic_year" id="academic_year" placeholder="  e.g. YYYY-MM-DD / YYYY-MM-DD" 
                                       data-format="YYYY-MM-DD" data-start-date="2016-01-01" data-end-date="2017-01-01" data-separator=" / "
                                       data-validate="required" data-message-required="value required" >
                                
                                <div class="input-group-addon">
                                    <a href="#"><i class="entypo-calendar"></i></a>
                                </div>
                            </div> 
                     </div>
                    
                    
                    <div class="form-group">
                       <div class="checkbox col-sm-offset-4">
                       <label for="field-1" class="control-label">
                           <input type="checkbox" class="check"  name="Status" value="1" >Mark As Default</label>
                       </div>
                                            
                    </div>
                                       
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-5">
                         <button type="submit" name="submit_button" class="btn btn-info" id="submit">
                             Add record</button>
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

