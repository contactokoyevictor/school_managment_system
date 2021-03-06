
<%-- 
    Document   : manage_subjects
    Created on : Jun 18, 2017, 3:19:14 AM
    Author     : acer
--%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sivotek.school_management_system.entities.Subject"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.sivotek.school_management_system.entities.Section"%>
<%@page import="com.sivotek.school_management_system.entities.Term"%>
<%@page import="org.springframework.web.util.*"%>
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
<%
    pageContext.setAttribute("classCategoryPOJOList","");
%>
<%! 
    
    Collection<ClassCategory> classCategoryList = null;
    Collection<com.sivotek.school_management_system.entities.Class> classList = null;
    Collection<Term> termList = null;
    Collection<Section> sectionList = null;
    Collection<Subject> subjectList = null;
    AcademicYears academicYears = null;
    long check_category = 0L;
    Integer counter = 0;
    long current_academic_year_id = 0L;
    ArrayList classCategoryPOJOList;
    String URLEncoded_data;

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<div class="row">
	<div class="col-md-12">
	
		<div class="tabs-vertical-env">
			<ul class="nav tabs-vertical toggle-click">
                    <%
                        classCategoryPOJOList = new ArrayList();
                        
                        classCategoryList = cate_bean.getClassCategories();
                        for(ClassCategory classCategory : classCategoryList)
                        {
                           Map Category = new HashMap();
                           Category.put("category_id", classCategory.getCategoryId());
                           Category.put("name", classCategory.getName());
                           Category.put("nick_name", classCategory.getNickName());
                           classCategoryPOJOList.add(Category);
                        }

                        pageContext.setAttribute("classCategoryPOJOList", classCategoryPOJOList);

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
                                <table  class="table-responsive table-bordered table-striped table-condensed table-hover datatable" id="table_export<%out.write(""+classCategory2.getCategoryId());%>">
                                    <thead>
                                        <tr>
                                        <th>#</th>
                                        <th>Class name</th>
                                        <th>Numeric name</th>
                                        <th>Academic year</th>
                                        <th>Number of subjects</th>
                                        <th>Form master</th>
                                        <th>Created date</th>
                                        <th>Options</th>
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
                                    pageContext.setAttribute("classId",""); 
                                    pageContext.setAttribute("classId", class1.getClassId());
                            %>
                            <tr>
                            <td><% out.write(""+counter2);%></td>
                            <td><%out.write(class1.getName());%></td>
                            <td><%out.write(class1.getNameNumeric());%></td>
                            <td><%out.write(class1.getAcademicYear().getName());%></td>
                            <td>
                                <input class="btn-xs" type="text" size="5" maxlength="5" 
                                       value="<% if(class1.getClassSubjectsCollection().size() > 0){out.write(class1.getClassSubjectsCollection().size());}%>" readonly/>
                            </td>
                            <td><%
                                if(class1.getTeacherId() != null && class1.getTeacherId().getEmployeeId() > 0)
                                {
                                  out.write(class1.getTeacherId().getFirstName()+" "+class1.getTeacherId().getLastName()+" (<strong>"+class1.getTeacherId().getDesignationId().getName()+"</strong>)");  
                                }
                                
                            %>
                            </td>
                             <%
                                Date created_date  = class1.getCreateddate();
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 'at' hh:mm:ss a zzz");// FOrmat in This Format or you change Change as well 
                                String created = format.format(created_date);
                            %>
                            <td><%out.write(created);%></td>

                            <td>
                                <a href="${cpi}/${login_type}/manage_class_subjects/<% out.write(cate_bean.url_safe_encryptdata(class1.getClassId()+""));%>">
                              <button type="button" class="btn btn-info btn-xs">Manage / View</button>
                            
                                
                                </a>
                                
                                
                </td>
                        </tr>
                         <% counter2++;
                            }  
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








<!--DATA TABLE EXPORT CONFIGURATIONS -->                      
<script type="text/javascript">
    
    jQuery(document).ready(function($)
	{
          <c:forEach items="${classCategoryPOJOList}" var="category">
          var datatable_${category.category_id} = $("#table_export${category.category_id}").dataTable({
			"sPaginationType": "bootstrap",
			"sDom": "<'row'<'col-xs-3 col-left'l><'col-xs-9 col-right'<'export-data'T>f>r>t<'row'<'col-xs-3 col-left'i><'col-xs-9 col-right'p>>",
			"oTableTools": {
				"aButtons": [
                                                
                       {
						"sExtends": "print",
						"fnSetText"	   : "Press 'esc' to return",
						"fnClick": function (nButton, oConfig) {
							datatable_${category.category_id}.fnSetColumnVis(1, false);
							datatable_${category.category_id}.fnSetColumnVis(5, false);
							
							this.fnPrint( true, oConfig );
							
							window.print();
							
							$(window).keyup(function(e) {
								  if (e.which == 27) {
									  datatable_${category.category_id}.fnSetColumnVis(1, true);
									  datatable_${category.category_id}.fnSetColumnVis(5, true);
								  }
							});
						},
						
					},
				]
			},
			
		});
                          
                
                </c:forEach>
		
		$(".dataTables_wrapper select").select2({
			minimumResultsForSearch: -1
		});
               
	});
</script>
