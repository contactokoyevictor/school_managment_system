<%-- 
    Document   : activity_log
    Created on : 19-Apr-2017, 12:06:18
    Author     : Administrator
--%>
<%@page import="com.sivotek.course_grading_system.business.pojo.SystemInfo"%>
<%@page import="java.io.ObjectInputStream"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date" %>
<%@page import="com.sivotek.course_grading_system.business.entities.AuditVault" %>
<%@page import="java.util.Collection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="crud" scope="request" class="com.sivotek.course_grading_system.business.beans.CrudModel" />
<!DOCTYPE html>
<html>
    
            
    <%!
          Collection<AuditVault> auditVaultlist = null;
          SystemInfo systemInfo = null;
          protected final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
          protected final SimpleDateFormat datetimeFormat = new SimpleDateFormat("hh:mm:ss z", Locale.getDefault());
     %>
        
     <%
          auditVaultlist = crud.getAuditVaults();
     %>
    <div class="col-md-12">
            <form method="POST" action="/nti_course_grading_system/${login_type}/clear_audit_vault/" id="form" name="form" class="form-horizontal form-groups-bordered validate" enctype="multipart/form-data">     
                <% if(auditVaultlist != null){%>
                   <div class="form-group">
                    <div class="col-sm-5">
                        <input type="checkbox" name="selectAll" id="selectAll"> Select All &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   
                        <button type="submit" name="btn_submit" class="btn btn-red" id="btn_submit" onclick="return confirm('Are you sure you want to delete selected log?')">Clear selected log</button>
                    </div>
                    </div>
                <% }%>
                 <table class="table table-bordered datatable table-responsive table-striped table-hover" id="table_export">
                    <thead>
                        <tr>
                            <th><div>Select</div></th>
                            <th><div>Action Type</div></th>
                            <th><div>Table Name</div></th>
                            <th><div>Action By</div></th>
                            <th><div>IP Address</div></th>
                            <th><div>Mac Address</div></th>
                            <th><div>Machine Name </div></th>
                            <th><div>Log Time</div></th>
                            <th><div>Action</div></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for(AuditVault auditVault : auditVaultlist){ 
                                byte[] auditVaultByte = auditVault.getSystemInfo();
	                        if (auditVaultByte != null) {
                                    try{
		                       ObjectInputStream auditVaultObjectIn = new ObjectInputStream(new ByteArrayInputStream(auditVaultByte));
			               Object auditVaultObj = auditVaultObjectIn.readObject();
		                       systemInfo = (SystemInfo) auditVaultObj;
                                    }catch(Exception e){ }
                                }
                        %>
                        <tr>
                            <td > <% out.write("<input type='checkbox' name='audits[]' value='" + auditVault.getId() + "'/>");%></td>
                            <td><% out.write(auditVault.getActionTypeId().getName());%></td>
                            <td><% out.write(auditVault.getTableName());%></td>
                            <td><% out.write(auditVault.getUserId().getUsername());%></td>
                            <td><% out.write(systemInfo.getRemoteAddress());%></td>
                            <td><% out.write(systemInfo.getMacAddress());%></td>
                            <td><% out.write(systemInfo.getRemoteName());%></td>
                            <td><% out.write(dateFormat.format(auditVault.getLogTime()) + " at " + datetimeFormat.format(auditVault.getLogTime()));%></td>
                            <td>
                                
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
                                        Action <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu dropdown-default pull-right" role="menu">
                                        <li>
                                            <a href="#" onclick="showAjaxModal('/nti_course_grading_system/${login_type}/modal_view_table_data/<% out.write(auditVault.getId().toString());%>');">
                                            <i class="entypo-pencil"></i>
                                            View table data
                                            </a>
                                        </li>
                                        
                                    				
                                    </ul>
                                </div>
                                
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
</form>
        </div>
        
 <script>
    
    $('#selectAll').click(function(event) {
        if(this.checked) {
          // Iterate each checkbox
          
          $(':checkbox').each(function() {
             this.checked = true;
          });
      }
      else {
         $(':checkbox').each(function() {
             this.checked = false;
          });
      }
    });
         
 </script>


<!-----  DATA TABLE EXPORT CONFIGURATIONS ---->                      
<script type="text/javascript">

    jQuery(document).ready(function($)
    {
        

        var datatable = $("#table_export").dataTable({
            "sPaginationType": "bootstrap",
            "sDom": "<'row'<'col-xs-3 col-left'l><'col-xs-9 col-right'<'export-data'T>f>r>t<'row'<'col-xs-3 col-left'i><'col-xs-9 col-right'p>>",
            "oTableTools": {
                "aButtons": [
                    
                    {
                        "sExtends": "xls",
                        "mColumns": [1,2,3,4,5]
                    },
                    {
                        "sExtends": "pdf",
                        "mColumns": [1,2,3,4,5]
                    },
                    {
                        "sExtends": "print",
                        "fnSetText"    : "Press 'esc' to return",
                        "fnClick": function (nButton, oConfig) {
                            datatable.fnSetColumnVis(5, false);
                            
                            this.fnPrint( true, oConfig );
                            
                            window.print();
                            
                            $(window).keyup(function(e) {
                                  if (e.which == 27) {
                                      datatable.fnSetColumnVis(5, true);
                                  }
                            });
                        },
                        
                    },
                ]
            },
            
        });
        
        $(".dataTables_wrapper select").select2({
            minimumResultsForSearch: -1
        });
    });
        
</script>


</html>
