<%@page import="java.lang.String;"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<%! String user_type="", page_name=""; %>

 

<c:set var="user_id" value="${user_id}"  scope="session" />
<%! 
    String file_name  = "";
%>

<% user_type = (String) session.getAttribute("user_type");
 file_name  = (String) session.getAttribute("page_name")+".jsp";
%>

 
<html lang="en" dir="ltr">
   
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${page_title} | SIVOTEK School management system</title>
       
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="School management system" />
	<meta name="author" content="Okoye Victor" />
        <%@ include file="../../includes_top.jsp" %>
        <style type="text/css"></style>
    </head>
    <body id="body" class="page-body skin-default">
     <!--sidebar-collapsed-->
	<div class="page-container left-sidebar sidebar-collapsed" >
                
                 <%@ include file="navigation.jsp" %>
		<div class="main-content">
                    <%@ include file="../../header.jsp" %>
                    <div class="row">
                    <div class="col-md-5">
                    <h4 style="margin-top:0px;" class="pull-left">
                        <i class="entypo-right-circled"></i> ${page_title}
                    </h4>
                    
                    </div>
                    
                        <div class="col-md-7 pull-right">
                             <c:if test="${not empty page_name}">
                                <ul class="breadcrumb btn-white btn-sm pull-right">
                                <li><i class="fa fa-dashboard"></i><a href="<c:url value='/${login_type}/' />"><small>${userType} Dashboard</small></a></li>
                                 <c:if test="${not empty moduleUrl}">
                                                <li> <a href="<c:url value='${moduleUrl}' />"><small>${moduleName}</small></a></li>
                                 </c:if>
                                  <c:if test="${not empty subModuleUrl}">
                                       <li> <a href="<c:url value='${subModuleUrl}' />"><small>${subModuleName}</small></a></li>
                                  </c:if>


                               </ul> 
                            </c:if>
                        </div>
                    </div>                   

           <hr style="margin-top:0px;" />
                   <jsp:include page="<%= file_name %>" flush="true" />
                   <%@ include file="../../footer.jsp" %>
                            

		</div>
        	
	</div>
  <%@ include file="../../modal.jsp" %>
  <%@ include file="../../includes_bottom.jsp" %>
    
</body>
<script type="text/javascript">  

  $(document).ready(function(){

    $("a[href^='${pageContext.request.contextPath}/']").click(function(){
            showAjaxBusyModal()
      });

      $(".panel-fullscreen").on("click",function(){
        panel_fullscreen($(this).parents(".panel"));
        return false;
      });

      $(".panel-refresh").on("click",function(){
        var panel = $(this).parents(".panel");
        panel_refresh(panel);

        setTimeout(function(){
            panel_refresh(panel);
        },3000);
        
        $(this).parents(".dropdown").removeClass("open");
        return false;
    });

      
      jQuery("[name='my-checkbox']").bootstrapSwitch();
          jQuery(document).ajaxStart(function(){
              showAjaxBusyModal();
          }).ajaxStop(function(){
              jQuery('#modal_small').modal('hide');
          }).ajaxSuccess(function(){
              jQuery('#modal_small').modal('hide');
          }).ajaxError(function(){
               jQuery('#modal_small').modal('hide');
          });
    });

    
    
    $("form").submit(function(e) {
         var $form = $(this);
         if(!$(this).valid()){
            return false;
            }
         if($(this).valid()){
          showAjaxBusyModal();
        }
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
    
    
    function showAjaxBusyModal()
    {
        // SHOWING AJAX PRELOADER IMAGE
        jQuery('#modal_small .modal-body').html('<div style="text-align:center;margin-top:10px;">please wait..<br/><div class="icon"> <i class="fa fa-spinner  fa-spin"></i> </div></div>');
        
        // LOADING THE AJAX MODAL
        jQuery('#modal_small').modal('show', {backdrop: 'true'});
    }
  
   function showAjaxMSGModal(message)
    {
        // SHOWING AJAX PRELOADER IMAGE
        jQuery('#modal_small_msg .modal-body').html('<div style="text-align:center;margin-top:10px;">'+message+'<br/></div>');
        // LOADING THE AJAX MODAL
        jQuery('#modal_small_msg').modal('show', {backdrop: 'true'});
    }
    
    /* PANEL FUNCTIONS */
function panel_fullscreen(panel){    
    
    if(panel.hasClass("panel-fullscreened")){
        panel.removeClass("panel-fullscreened").unwrap();
        panel.find(".panel-body,.chart-holder").css("height","");
        panel.find(".panel-fullscreen .fa").removeClass("fa-compress").addClass("fa-expand");        
        
        $(window).resize();
    }else{
        var head    = panel.find(".panel-heading");
        var body    = panel.find(".panel-body");
        var footer  = panel.find(".panel-footer");
        var hplus   = 30;
        
        if(body.hasClass("panel-body-table") || body.hasClass("padding-0")){
            hplus = 0;
        }
        if(head.length > 0){
            hplus += head.height()+21;
        } 
        if(footer.length > 0){
            hplus += footer.height()+21;
        } 

        panel.find(".panel-body,.chart-holder").height($(window).height() - hplus);
        
        
        panel.addClass("panel-fullscreened").wrap('<div class="panel-fullscreen-wrap"></div>');        
        panel.find(".panel-fullscreen .fa").removeClass("fa-expand").addClass("fa-compress");
        
        $(window).resize();
    }
}


function panel_refresh(panel,action,callback){        
    if(!panel.hasClass("panel-refreshing")){
        panel.append('<div class="panel-refresh-layer"><img src="img/loaders/default.gif"/></div>');
        panel.find(".panel-refresh-layer").width(panel.width()).height(panel.height());
        panel.addClass("panel-refreshing");
        
        if(action && action === "shown" && typeof callback === "function") 
            callback();
    }else{
        panel.find(".panel-refresh-layer").remove();
        panel.removeClass("panel-refreshing");
        
        if(action && action === "hidden" && typeof callback === "function") 
            callback();        
    }       
    onload();
}
</script>
</html>
