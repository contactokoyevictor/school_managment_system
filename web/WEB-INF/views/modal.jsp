<%-- 
    Document   : modal
    Created on : Mar 17, 2017, 3:54:48 AM
    Author     : VICTOR_OKOYE
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />


    <script type="text/javascript">
	function showAjaxModal(url)
	{
		// SHOWING AJAX PRELOADER IMAGE
		jQuery('#modal-3 .modal-body').html('<div style="text-align:center;margin-top:absolute;"><div class="icon"> <i class="fa fa-spinner icon-lg fa-spin orange bigger-125"></i> </div></div>');
		
		// LOADING THE AJAX MODAL
		jQuery('#modal-3').modal('show', {backdrop: 'true'});
		
		// SHOW AJAX RESPONSE ON REQUEST SUCCESS
		$.ajax({
			url: url,
			success: function(response)
			{
				jQuery('#modal-3 .modal-body').html(response);
			}
		});
	}

    function showAjaxModal2(url)
    {
        // SHOWING AJAX PRELOADER IMAGE
        jQuery('#modal-5 .modal-body').html('<div style="text-align:center;margin-top:absolute;"><div class="icon"> <i class="fa fa-spinner icon-lg fa-spin orange bigger-125"></i> </div></div>');
        
        // LOADING THE AJAX MODAL
        jQuery('#modal-5').modal('show', {backdrop: 'true'});
        
        // SHOW AJAX RESPONSE ON REQUEST SUCCESS
        $.ajax({
            url: url,
            success: function(response)
            {
                jQuery('#modal-5 .modal-body').html(response);
            }
        });
    }
    </script>
    
    <!-- (Ajax Modal)-->
    <div class="modal fade" id="modal_ajax">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">SIVOtek School Management Information System</h4>
                </div>

                <div class="modal-body" style="height:500px; overflow:auto;">
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>
    
    
    <!-- Modal 3 (Confirm)--> 
    <div class="modal fade" id="modal-3" data-backdrop="static" style="margin-top:absolute;"> 
        <div class="modal-dialog"> 
            <div class="modal-content" style="transform: matrix(1, 0, 0, 1, 0, 0);"> 
                <div class="modal-header"> 
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">SIVOtek School Management Information System</h4> 
                </div> 
                  <div class="modal-body" style="height:auto; overflow:auto;">
                  </div> 
                <div class="modal-footer"> 
                <button type="button" class="btn btn-sm btn-info" data-dismiss="modal">Close</button> 
                </div> 
            </div> 
        </div> 
    </div>


    <!-- (Normal Modal)-->
    <div class="modal fade" id="modal-4">
        <div class="modal-dialog">
            <div class="modal-content" style="margin-top:100px;">
                
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" style="text-align:center;">Are you sure to delete this information ?</h4>
                </div>
                
                
                <div class="modal-footer" style="margin:0px; border-top:0px; text-align:center;">
                    <a href="#" class="btn btn-danger" id="delete_link">Delete</a>
                    <button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>
    


     <!-- Modal 5 (large)--> 
    <div class="modal fade" id="modal-5" data-backdrop="static" style="display: none;"> 
        <div class="modal-dialog" style="width: 70%;"> 
            <div class="modal-content" style="transform: matrix(1, 0, 0, 1, 0, 0);"> 
                <div class="modal-header"> 
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">SIVOtek School Management Information System</h4> 
                </div> 
                  <div class="modal-body" style="height:auto; overflow:auto;">
                  </div> 
                <div class="modal-footer"> 
                <button type="button" class="btn btn-info" data-dismiss="modal">Close</button> 
                </div> 
            </div> 
        </div> 
    </div>
    
    <script type="text/javascript">
            function confirm_modal(delete_url)
            {
                jQuery('#modal-4').modal('show', {backdrop: 'static'});
                document.getElementById('delete_link').setAttribute('href' , delete_url);
            }
            
            function ajaxPageRequest(page_url)
            {
                 $.ajax({
                       url: page_url
                  });
            }
            
            function modal_small_confirm_msg(message)
            {
                jQuery('#modal_small_confirm_msg .modal-body').html('<div style="text-align:center;margin-top:10px;">'+message+'<br/></div>');
                // LOADING THE AJAX MODAL
                jQuery('#modal_small_confirm_msg').modal('show', {backdrop: 'true'});
            }
            
            function change_status()
            {
                jQuery('#confirmed').val('1');
                jQuery('#modal_small_confirm_msg').modal('hide');
                $('#form').submit();
            }
    </script>
    
    <!-- (Normal Modal)-->
    <div class="modal fade" id="modal-4">
        <div class="modal-dialog">
            <div class="modal-content" style="margin-top:100px;">
                
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" style="text-align:center;">Are you sure to delete this information ?</h4>
                </div>
                
                
                <div class="modal-footer" style="margin:0px; border-top:0px; text-align:center;">
                    <a href="#" class="btn btn-danger" id="delete_link">Delete</a>
                    <button type="button" class="btn btn-info" data-dismiss="modal">Cancel
                    </button>
                </div>
            </div>
        </div>
    </div>
    
    
   <div class="modal fade" id="modal_small" tabindex="-1" role="dialog" aria-labelledby="smallModalHead" aria-hidden="true">
            <div class="modal-dialog modal-xs" style="width: 12%;">
                <div class="modal-content" style="margin-top:160px;">
                    <div class="modal-body">
                        please wait..
                    </div>
                </div>
            </div>
    </div>
    

    
    
    
<div class="modal fade" id="modal_small_msg">
        <div class="modal-dialog modal-sm">
            <div class="modal-content" style="margin-top:150px;">

                <div class="modal-header btn-default">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h5 class="modal-title"><span class="fa fa-exclamation-circle">&nbsp;</span>
                    </h5>
                </div>

                <div class="modal-body">
                    please wait..
                </div>


            </div>
        </div>
    </div>
    
  
 <div class="modal fade" id="modal_small_confirm_msg">
        <div class="modal-dialog modal-sm">
            <div class="modal-content" style="margin-top:150px;">
                
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h5 class="modal-title"><span class="fa fa-exclamation-circle">&nbsp;</span>
                    </h5>
                </div>
                
                <div class="modal-body">
                    
                </div>
                <div class="modal-footer" style="margin:0px; border-top:0px; text-align:center;">
                    <button type="button" onclick="return change_status();" name="submit_confirm" class="btn btn-danger btn-xs" id="submit_confirm">Continue</button>
                    <!--<a href="#" class="btn btn-danger btn-xs" id="transaction_url"><?php echo get_phrase('continue');?></a>-->
                    <button type="button" class="btn btn-info btn-xs" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>
