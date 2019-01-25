<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>

<jsp:useBean id="cate_bean" scope="request" class="com.sivotek.school_management_system.app.crud.crud_bean" />
<jsp:setProperty name="cate_bean" property="*" />
<%@page import="com.sivotek.school_management_system.entities.AcademicYears;" %>

<%! 
    Collection<AcademicYears> academicYearsList = null;
    long check_category = 0L;
    Integer counter = 0;
    long branch_id = 0L;

%>
            <a href="javascript:;" onclick="showAjaxModal('/school_management_system/modal/popup_page/modal_academic_year_add');" 
              class="btn btn-primary pull-right">
                <i class="entypo-plus-circled"></i>
                Add academic year
                </a> 
                <br><br>
               <table class="table table-bordered datatable" id="table_export">
                    <thead>
                        <tr>
                            <th><div>#</div></th>
                            <th><div>Year title</div></th>
                            <th><div>Year status</div></th>
                            <th><div>Options</div></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            branch_id = Long.parseLong(session.getAttribute("branch_id")+"");
                            academicYearsList = cate_bean.findByBranchId(branch_id);
                            counter = 0;
                            for(AcademicYears academicYears : academicYearsList)
                            { 
                                counter++;
                        %>
                        <tr>
                            
                            <td><% out.write(counter.toString());%></td>
                            <td><% out.write(academicYears.getName());%></td>
                            <td><% out.write(academicYears.getStatus());%></td>
                            <td>
                                
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
                                        Action <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu dropdown-default pull-right" role="menu">
                                        
                                        <!-- EDITING LINK -->
                                        <li>
                                                <a href="#" onclick="showAjaxModal('${pageContext.request.servletContext.contextPath}/${login_type}/modal_edit_academic_year/<% out.write(academicYears.getYearId()+"");%>');">
                                        	<i class="entypo-pencil"></i>
						Edit
                                               	</a>
                                        </li>
                                       <li class="divider"></li>
                                        
                                        <!-- DELETION LINK -->
                                        
                                        <li> 
                                            <a href="#" onclick="confirm_modal('${pageContext.request.servletContext.contextPath}/${login_type}/modal_academic_year/<% out.write(academicYears.getYearId()+"");%>');">
                                            <i class="entypo-trash"></i>
                                            Delete
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

