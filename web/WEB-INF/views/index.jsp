<%@page import="java.lang.String;"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<%! String  user_id = "", xmlres = "", user_type="", page_name=""; %>
<%
    user_id =  session.getAttribute("user_id")+"";
    if((session.getAttribute("user_id") + "").equals("null") || (session.getAttribute("user_id") + "").equals("0")
            || (session.getAttribute("user_id") + "").equals("")) 
    {
 %>
 
<script language="javascript">location.href="<%= "/school_management_system/login" %>";</script>
<%
    }  
%>

<c:set var="user_id" value="<%= user_id %>"  scope="session" />

<%String sid = request.getSession().getAttribute("user_id")+""; %>
<%String base_address = request.getLocalAddr(); %>
<%int base_port = request.getLocalPort(); %>
<% user_type = (String) session.getAttribute("user_type");
 page_name = (String) session.getAttribute("page_name");%>


<html lang="en" dir="ltr">
   
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SIVOTEK School management system</title>
       
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="School management system" />
	<meta name="author" content="Okoye Victor" />
        <%@ include file="includes_top.jsp" %>
        <style type="text/css"></style>
    </head>
    <body id="body" class="page-body skin-default">
     <!--sidebar-collapsed-->
	<div class="page-container left-sidebar sidebar-collapsed" >
                
                 <%@ include file="backend/admin/navigation.jsp" %>
		<div class="main-content">
                    <%@ include file="header.jsp" %>
                    <div class="row">
                    <div class="col-md-12">
                    <h4 style="margin-top:0px;" class="pull-left">
                        <i class="entypo-right-circled"></i> Dashboard
                    </h4>
                    
                    </div>
                    </div>                   

           <hr style="margin-top:0px;" />
                   <%@ include file="backend/admin/dashboard.jsp" %>
                   <%@ include file="footer.jsp" %>
                            

		</div>
        	
	</div>
  <%@ include file="modal.jsp" %>
  <%@ include file="includes_bottom.jsp" %>
    
</body>
</html>
