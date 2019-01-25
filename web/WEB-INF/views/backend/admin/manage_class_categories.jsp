<%-- 
    Document   : manage_class_categories
    Created on : Apr 1, 2017, 3:28:38 AM
    Author     : VICTOR_OKOYE
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>

<jsp:useBean id="cate_bean" scope="request" class="com.sivotek.school_management_system.app.crud.crud_bean" />
<jsp:setProperty name="cate_bean" property="*" />
<%@page import="com.sivotek.school_management_system.entities.AcademicYears;" %>
<%@page import="com.sivotek.school_management_system.entities.controllers.AcademicYearsJpaController;" %>
<%@page import="com.sivotek.school_management_system.entities.ClassCategory;" %>
<%@page import="com.sivotek.school_management_system.entities.controllers.ClassCategoryJpaController;" %>
<%@page import="com.sivotek.school_management_system.entities.Class;" %>
<%@page import="com.sivotek.school_management_system.entities.controllers.ClassJpaController;" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cpi" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<%! 
    
    Collection<ClassCategory> classCategoryList = null;
    Collection<com.sivotek.school_management_system.entities.Class> classList = null;
    long check_category = 0L;
    Integer counter = 0;
    long current_academic_year_id = 0L;

%>

            <a href="javascript:;" onclick="showAjaxModal('${cpi}/modal/popup_page/modal_add_class_category');" 
                class="btn btn-primary pull-right">
                <i class="entypo-plus-circled"></i>
                Add class category
                </a> 
                <br><br>
               <table class="table-responsive table-bordered datatable table-hover table-striped" id="table_export">
                    <thead>
                       
                        <tr>
                            <th><div>#</div></th>
                            <th><div>Name</div></th>
                            <th><div>Nick name</div></th>
                            <th><div>Creation date</div></th>
                            <th><div>No of class attached (current_year)</div></th>
                            
                            <th><div>Action</div></th>
                        </tr>
                    </thead>
                    <tbody>
                        
                <%
                    classCategoryList = cate_bean.getClassCategories();
                    current_academic_year_id = cate_bean.getCurrentAcademicYearId();
                    counter = 0;
                    for(ClassCategory classCategory : classCategoryList)
                    { 
                        counter++;
                        check_category = classCategory.getCategoryId();
                %>
                
                        <tr>
                            <td><% out.write(counter.toString());%></td>
                            <td><% out.write(classCategory.getName());%></td>
                            <td><% out.write(classCategory.getNickName());%></td>
                            <td><% 
                                Date created_date  = classCategory.getCreateddate();
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 'at' hh:mm:ss a zzz");
                                String created = format.format(created_date);
                                out.write(created);%></td>
                            <td>
                                <% out.write(classCategory.getClassCollection().size()+"");%>
                             </td>
                            
                            
                            <td>
                                
                                <div class="btn-group">
                                    <button type="button" class="btn btn-info btn-xs dropdown-toggle" data-toggle="dropdown">
                                       <i class="entypo-feather"></i> Action <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu dropdown-default pull-right" role="menu">
                                        
                                        <li>
                                            
                                            <a href="#" onclick="showAjaxModal('${pageContext.request.servletContext.contextPath}/${login_type}/modal_edit_class_category/<% out.write(classCategory.getCategoryId()+"");%>');">
                                            <i class="entypo-pencil"></i>
                                            Edit
                                            </a>
                                            
                                        </li>
                                        <li class="divider"></li>
                                        
                                        <li> 
                                            <a href="#" onclick="confirm_modal('${pageContext.request.servletContext.contextPath}/${login_type}/modal_delete_class_category/<% out.write(classCategory.getCategoryId()+"");%>');">
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

