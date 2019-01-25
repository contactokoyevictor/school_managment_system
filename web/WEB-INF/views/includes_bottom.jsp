<%-- 
    Document   : includes_bottom
    Created on : Mar 17, 2017, 3:53:18 AM
    Author     : VICTOR_OKOYE
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cpj" value="${pageContext.request.servletContext.contextPath}" scope="request" />

	
   
    
    

	<link rel="stylesheet" href="${cpj}/resources/js/datatables/responsive/css/datatables.responsive.css">
	<link rel="stylesheet" href="${cpj}/resources/js/select2/select2-bootstrap.css">
	<link rel="stylesheet" href="${cpj}/resources/js/select2/select2.css">
        
        
	<link rel="stylesheet" href="${cpj}/resources/js/selectboxit/jquery.selectBoxIt.css">
        

   	<!-- Bottom Scripts -->
	<script src="${cpj}/resources/js/gsap/main-gsap.js"></script>
	<script src="${cpj}/resources/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js"></script>
	<script src="${cpj}/resources/js/bootstrap.js"></script>
	<script src="${cpj}/resources/js/joinable.js"></script>
	<script src="${cpj}/resources/js/resizeable.js"></script>
	<script src="${cpj}/resources/js/neon-api.js"></script>
	<script src="${cpj}/resources/js/toastr.js"></script>
        <script src="${cpj}/resources/js/jquery.validate.min.js"></script>
        <script src="${cpj}/resources/js/jquery.validate.js"></script>
	<script src="${cpj}/resources/js/fullcalendar/fullcalendar.min.js"></script>
        <script src="${cpj}/resources/js/bootstrap-datepicker.js"></script>
        <script src="${cpj}/resources/js/fileinput.js"></script>
    
        <script src="${cpj}/resources/js/jquery.dataTables.min.js"></script>
	<script src="${cpj}/resources/js/datatables/TableTools.min.js"></script>
	<script src="${cpj}/resources/js/dataTables.bootstrap.js"></script>
	<script src="${cpj}/resources/js/datatables/jquery.dataTables.columnFilter.js"></script>
	<script src="${cpj}/resources/js/datatables/lodash.min.js"></script>
	<script src="${cpj}/resources/js/datatables/responsive/js/datatables.responsive.js"></script>
        <script src="${cpj}/resources/js/select2/select2.min.js"></script>
        <script src="${cpj}/resources/js/selectboxit/jquery.selectBoxIt.min.js"></script>

    
	<script src="${cpj}/resources/js/neon-calendar.js"></script>
	<script src="${cpj}/resources/js/neon-chat.js"></script>
	<script src="${cpj}/resources/js/neon-custom.js"></script>
	<script src="${cpj}/resources/js/neon-demo.js"></script>
        
        
       

<!-- SHOW TOASTR NOTIFIVATION -->
<script type="text/javascript">
        var success_flash = '${success_flash}';
        var error_flash = '${error_flash}';
        
        if(success_flash !=='')
        {
	   toastr.success(success_flash);
        }
        if(error_flash !=='')
        {
	   toastr.error(error_flash);
        }
</script>

<!-----  DATA TABLE EXPORT CONFIGURATIONS ---->                      
<script type="text/javascript">

	jQuery(document).ready(function($)
	{
		

		var datatable = $("#table_export").dataTable();
		
		$(".dataTables_wrapper select").select2({
			minimumResultsForSearch: -1
		});
	});
		
</script>
