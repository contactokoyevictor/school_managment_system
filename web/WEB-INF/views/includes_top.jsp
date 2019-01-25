<%-- 
    Document   : includes_top
    Created on : Mar 17, 2017, 3:53:34 AM
    Author     : VICTOR_OKOYE
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cpi" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<!-- Finish adding tags -->



<link rel="stylesheet" href="${cpi}/resources/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css">
<link rel="stylesheet" href="${cpi}/resources//css/font-icons/entypo/css/entypo.css">
<link rel="stylesheet" href="${cpi}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${cpi}/resources/css/neon-core.css">
<link rel="stylesheet" href="${cpi}/resources/css/neon-theme.css">
<link rel="stylesheet" href="${cpi}/resources/css/neon-forms.css">

<link rel="stylesheet" href="${cpi}/resources/css/custom.css">
 <link rel="stylesheet" href="${cpi}/resources/css/skins/default.css">



<script src="${cpi}/resources/js/jquery-1.11.0.min.js"></script>

<link rel="shortcut icon" href="${cpi}/resources/images/favicon.png">
<link rel="stylesheet" href="${cpi}/resources/css/wizardcss.css">

<link rel="stylesheet" href="${cpi}/resources/css/font-icons/font-awesome/css/font-awesome.min.css">

<link rel="stylesheet" href="${cpi}/resources/js/vertical-timeline/css/component.css">
<link rel="stylesheet" href="${cpi}/resources/js/datatables/responsive/css/datatables.responsive.css">
<link rel="stylesheet" href="${cpi}/resources/js/daterangepicker/daterangepicker-bs3.css">
<link rel="stylesheet" href="${cpi}/resources/css/bootstrap-switch.css">
<link rel="stylesheet" href="${cpi}/resources/js/icheck/skins/all.css">
<link rel="stylesheet" href="${cpi}/resources/js/icheck/skins/line/_all.css">


<link rel="stylesheet" href="${cpi}/resources/js/icheck/skins/minimal/_all.css" id="style-resource-1">
<link rel="stylesheet" href="${cpi}/resources/js/icheck/skins/square/_all.css" id="style-resource-2">
<link rel="stylesheet" href="${cpi}/resources/js/icheck/skins/flat/_all.css" id="style-resource-3">
<link rel="stylesheet" href="${cpi}/resources/js/icheck/skins/futurico/futurico.css" id="style-resource-4">
<link rel="stylesheet" href="${cpi}/resources/js/icheck/skins/polaris/polaris.css" id="style-resource-5">
<link rel="stylesheet" href="${cpi}/resources/js/icheck/skins/line/_all.css" id="style-resource-6">

<link rel="stylesheet" href="${cpi}/resources/css/bootstrap-combined.min.css">
<link rel="stylesheet" href="${cpi}/resources/css/jquery.mCustomScrollbar.css">


<script src="${cpi}/resources/js/mcustomscrollbar/jquery.mCustomScrollbar.min.js" type="text/javascript"></script>

<!--Amcharts-->
<script src="${cpi}/resources/js/amcharts/amcharts.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/amcharts/pie.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/amcharts/serial.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/amcharts/gauge.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/amcharts/funnel.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/amcharts/radar.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/amcharts/exporting/amexport.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/amcharts/exporting/rgbcolor.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/amcharts/exporting/canvg.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/amcharts/exporting/jspdf.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/amcharts/exporting/filesaver.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/amcharts/exporting/jspdf.plugin.addimage.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/jquery.bootstrap.wizard.min.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/bootstrap-switch.min.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/icheck/icheck.min.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/daterangepicker/daterangepicker.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/daterangepicker/moment.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/daterangepicker/moment.min.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/bootstrap-timepicker.min.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/select2/select2.min.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/select2/select2.js" type="text/javascript"></script>
<!--<script src="${cpi}/resources/js/neon-notes.js" type="text/javascript"></script>-->



<script src="${cpi}/resources/js/gsap/jquery.gsap.min.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/gsap/main-gsap.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/gsap/TimelineLite.min.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/gsap/TimelineMax.min.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/gsap/TweenLite.min.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/gsap/TweenMax.min.js" type="text/javascript"></script>

<script src="${cpi}/resources/js/chartjs/chart.min.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/echart/echarts-all.js" type="text/javascript"></script>
<script src="${cpi}/resources/js/echart/green.js" type="text/javascript"></script>


<script>
    function checkDelete()
    {
        var chk=confirm("Are You Sure To Delete This !");
        if(chk)
        {
          return true;  
        }
        else{
            return false;
        }
    }
</script>
