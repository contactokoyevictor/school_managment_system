<%-- 
    Document   : manage_class
    Created on : Jun 8, 2017, 9:09:14 AM
    Author     : acer
--%>
<%@page import="java.util.Collection"%>
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<a href="#" onclick="showAjaxModal('${cpi}/modal/popup_page/modal_add_class/');"
    class="btn btn-primary pull-right">
        <i class="entypo-plus-circled"></i>
        Add class
    </a> 
<br>


<div class="row">
	<div class="col-md-12">
	
		<div class="tabs-vertical-env">
			<ul class="nav tabs-vertical toggle-click">
                    <%
                        classCategoryList = cate_bean.getClassCategories();
                        
                        counter = 0;
                        for(ClassCategory classCategory : classCategoryList)
                        { 
                            counter++;
                            check_category = classCategory.getCategoryId();
                        
                    %>
			
                        <li>
                            <a data-toggle="tab" href="#<% out.write(""+classCategory.getCategoryId());%>">
                              <i class="entypo-flow-tree"></i> <% out.write(""+classCategory.getName());%> 
                            </a>                
                        </li>
                    <%
                        }
                    %>
			</ul>
                    
	<div class="tab-content">
            <%
                for(ClassCategory classCategory2 : classCategoryList)
                   { 
            %>
                
                    <div id="<% out.write(""+classCategory2.getCategoryId());%>" class="tab-pane">
                        <div class="wrap-fpanel">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <div class="panel-title">
                                        <strong><i class="entypo-flow-tree"></i><% out.write(classCategory2.getName());%></strong>
                                    </div>
                                    <div class="panel-options"> 
                                            <a href="#" class="panel-fullscreen"><i class="fa fa-expand"></i></a>
                                            <a href="#" data-rel="collapse"><i class="entypo-down-open">
                                            </i></a> <a href="#" data-rel="reload"><i class="entypo-arrows-ccw"></i>
                                        </a>  
                                    </div>

                                </div> 
                                <div class="panel-body">
                                <!-- Table -->
                                <table style="width:100%;" class="table-responsive table-bordered table-striped table-condensed table-hover">
                                    <thead>
                                        <tr>
                                        <th><div>#</div></th>
                                        <th><div>Class name</div></th>
                                        <th><div>Numeric name</div></th>
                                        <th><div>Teacher (form master)</div></th>
                                        <th><div>Academic year</div></th>
                                        <th><div>Options</div></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                             
                         
                            <%
                                current_academic_year_id = cate_bean.getCurrentAcademicYearId();
                                classList = cate_bean.getClassesByAcademicYearAndCategoryId(current_academic_year_id, classCategory2.getCategoryId());
                                
                                if(classList.size() > 0)
                                {
                                    int counter2 = 1;
                                    for(Class class1 : classList){
                                
                            %>
                            <tr>
                            <td><% out.write(""+counter2);%></td>
                            <td><%out.write(class1.getName());%></td>
                            <td><%out.write(class1.getNameNumeric());%></td>
                            <td><%
                                if(class1.getTeacherId() != null && class1.getTeacherId().getEmployeeId() > 0)
                                {
                                  out.write(class1.getTeacherId().getFirstName()+" "+class1.getTeacherId().getLastName()+" (<strong>"+class1.getTeacherId().getDesignationId().getName()+"</strong>)");  
                                }
                                
                            %>
                            </td>
                            <td><%out.write(class1.getAcademicYear().getName());%></td>
                            <td>
                                
                            <div class="btn-group">
                                <button type="button" class="btn btn-info btn-xs dropdown-toggle" data-toggle="dropdown">
                                    Action <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu dropdown-default pull-right" role="menu">
                                    
                                    <!-- EDITING LINK -->
                                    <li>
                                        <a href="#" onclick="showAjaxModal('${cpi}/${login_type}/modal_edit_class/<% out.write(class1.getClassId()+"");%>');">
                                            <i class="entypo-pencil"></i>
                                                Edit
                                        </a>
                                    </li>
                                    
                                   
                                    <li class="divider"></li>
                                    
                                    <!-- DELETION LINK -->
                                    <li>
                                        <a href="#" onclick="confirm_modal('${cpi}/${login_type}/modal_delete_class/<% out.write(class1.getClassId()+"");%>');">
                                            <i class="entypo-trash"></i>
                                                Delete
                                        </a>
                                    </li>
                                                    
                                   
                                </ul>
                            </div>
        		</td>
                        </tr>
                         <% counter2++;
                            }  
                            }else{ %>
                            <td colspan="6">
                                <strong>There is no data to display</strong>
                            </td> 
                        <%
                            }
                        %>
                                    </tbody>
                                </table>  
                                </div>
                            </div>
                        </div>
                    </div> 
                   
                    <%
                        }
                    %>
            </div>
			
		</div>	
            
            
	
	</div>
</div>








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
