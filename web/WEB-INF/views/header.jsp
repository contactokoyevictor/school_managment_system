<%-- 
    Document   : header
    Created on : Mar 17, 2017, 3:52:52 AM
    Author     : VICTOR_OKOYE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cpi" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<div class="row">
    <div class="col-md-12 col-sm-12 clearfix login-content" style="text-align:center; margin:0px;">
            <a href="http://www.sivoteksolutions.com" target="_blank" class="logo">
                    <img src="${cpi}/resources/images/SIVOTEK.png" height="30" alt="www.sivoteksolutions.com" />
            </a>
    </div>
    
	<div class="col-md-12 col-sm-12 clearfix" style="text-align:center;">
		<h5 style="font-weight:100; margin:0px;">SIVOTEK School Management System</h5>
    </div>
    <div id="main-content">
	<!-- Raw Links -->
	<div class="col-md-12 col-sm-12 clearfix ">
		
        <ul class="list-inline links-list pull-left">
        <!-- Language Selector -->			
           <li class="dropdown language-selector">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-close-others="true">
                        	<i class="entypo-user"></i> 
                                ${login_type}
                                
                    </a>

				
			</li>
        </ul>
        
        
		<ul class="list-inline links-list pull-right">
		
			
			<li>
                                  <a href="${pageContext.request.contextPath}/logout/">
					Log Out <i class="entypo-logout right"></i>
				</a>
			</li>
		</ul>
	</div>
	</div>
</div>

<hr style="margin-top:0px;" />



