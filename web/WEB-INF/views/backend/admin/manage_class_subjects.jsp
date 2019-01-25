<%-- 
    Document   : manage_class_subjects
    Created on : Jun 29, 2017, 9:00:03 PM
    Author     : okoyevictor
--%>
<%@page import="com.sivotek.school_management_system.entities.controllers.SubjectJpaController"%>
<%@page import="com.sivotek.school_management_system.entities.Subject"%>
<%@page import="com.sivotek.school_management_system.entities.controllers.ClassSubjectsJpaController"%>
<%@page import="com.sivotek.school_management_system.entities.ClassSubjects"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.sivotek.school_management_system.entities.Term"%>
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
    Integer counter = 0;
   
    String id = ""; 
    long class_id = 0L;
    Class class1;
    
    ClassJpaController classJpaController =  new ClassJpaController();
    AcademicYears academicYears = new AcademicYears();
    Collection<Term> termList;
    Collection<ClassSubjects> classSubjects;
    Collection<Subject> subjectList;
    Collection<Subject> subject;
    SubjectJpaController subjectJpaController =  new SubjectJpaController();
    
    Collection<ClassSubjects> TermclassSubjectsList;
    
    Collection<AcademicYears> academicYearsList;
    AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
                            
%>

<%
  
  id = session.getAttribute("class_id").toString().trim();
  class_id = Long.parseLong(id.trim());
  class1 = new Class();
  class1 = classJpaController.findClass(class_id);
  academicYears = class1.getAcademicYear();
  termList = academicYears.getTermCollection();
  System.out.println("Class Subject Size :"+class1.getClassCategoryId().getSubjectCollection().size());
  for(Term term : termList)
    {
        System.out.println("Term ID :"+term.getTermId());
        if(term.getClassSubjectsCollection() !=null)
        {
         TermclassSubjectsList = term.getClassSubjectsCollection();
         try{
         if(TermclassSubjectsList !=null)
         {
            for(ClassSubjects clsubject : TermclassSubjectsList)
            {
                classSubjects.add(clsubject);
            }
         }
         }catch(NullPointerException npe)
         {
             System.out.println("Null Pointer Exception Encountered...");
         }
      }
        
    }
%>
<div class="row">
	<div class="col-md-12">
    
    	<!------CONTROL TABS START------>
		<ul class="nav nav-tabs bordered">
			<li class="active">
                        <a href="#list" data-toggle="tab"><i class="entypo-menu"></i> 
                        <% out.write("All "+class1.getName()+" subject list");%>
			</a></li>
                        <%
                            for(Term term2 : termList)
                            {
                        %>
                        <li>
                            <a href="#<% out.write(term2.getTermId()+"");%>" data-toggle="tab">
                                <span class="visible-xs"><i class="entypo-user"></i></span>
                                <span class="hidden-xs"><% out.write(term2.getName());%></span>
                            </a>
                         </li>
                        <%
                            }
                        %>
                        
                        <li>
                        <a href="#add" data-toggle="tab"><i class="entypo-plus-circled"></i> 
                         Assign subject
                        </a></li>
			
		</ul>
    	<!------CONTROL TABS END------>
		<div class="tab-content">            
            <!----TABLE LISTING STARTS-->
            <div class="tab-pane box active" id="list">
				
                <table class="table-responsive table-bordered table-striped table-hover datatable" id="table_export">
                	<thead>
                		<tr>
                                <th><div>SN</div></th>
                    		<th><div>Class</div></th>
                                <th><div>Term</div></th>
                    		<th><div>Subject name</div></th>
                    		<th><div>Teacher</div></th>
                                <th><div>Subject assign date</div></th>
                                </tr>
                        </thead>
                    <tbody>
                        <%
                            counter = 1;
                            try{
                            if(class1.getClassCategoryId().getSubjectCollection().size() > 0)
                            {
                            subjectList = class1.getClassCategoryId().getSubjectCollection();
                            for(Subject subject12 : subjectList)
                            {
                        %>
                            <tr>
                            <td><% out.write(counter+"");%></td>
                            <td><% out.write(class1.getName()+"");%></td>
                            <td><% out.write("");%></td>
                            <td><% out.write(subject12.getName()+"");%></td>
                            
                            
                             <td>
                             <%
                                Date created_date  = subject12.getCreateddate();
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 'at' hh:mm:ss a zzz");// FOrmat in This Format or you change Change as well 
                                String created = format.format(created_date);
                            %>
                            <td><%out.write(created.trim());%></td>
                            
                        </tr>
                        <%
                            counter++;
                            } 
                            }
                        }catch(NullPointerException npe)
                        {
                            System.out.println("Second Exception...");
                        }
                        %>
                    </tbody>
                </table>
			</div>
            <!--TABLE LISTING ENDS-->
            
            
                <div class="tab-pane box" id="add" style="padding: 5px">
                <div class="box-content">
                	<table class="table-responsive table-bordered table-striped table-hover datatable" id="table_export_2">
                	<thead>
                		<tr>
                                <th><div>#</div></th>
                    		<th><div>Academic year</div></th>
                    		<th><div>Subject name</div></th>
                    		<th><div>Subject code</div></th>
                                <th><div>Nick name</div></th>
                                <th><div>Creation date</div></th>
                                <th><div>Classes attached</div></th>
                    		<th><div>Options</div></th>
                                </tr>
                        </thead>
                    <tbody>
                        <%
                            counter = 1;
                            try{
                            if(classSubjects !=null){
                            for(ClassSubjects classSubject3 : classSubjects)
                            {
                        %>
                        <tr>
                            <td><% out.write(counter+"");%></td>
                            <td><% out.write(classSubject3.getSubjectId().getAcademicYear().getName()+"");%></td>
                            <td><% out.write(classSubject3.getSubjectId().getName()+"");%></td>
                            <td><% out.write(classSubject3.getSubjectId().getCode()+"");%></td>
                            <td><% out.write(classSubject3.getSubjectId().getNickName()+"");%></td>
                            <%
                                Date created_date  = classSubject3.getCreateddate();
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 'at' hh:mm:ss a zzz");// FOrmat in This Format or you change Change as well 
                                String created = format.format(created_date);
                            %>
                            <td><%out.write(created.trim());%></td>
                            <td>
                                <input class="btn-xs" type="text" size="5" maxlength="10" value="<% out.write(classSubject3.getSubjectId().getClassSubjectsCollection().size()+"");%>" readonly="">
                            </td>
                            
                            <td>
                               <div class="btn-group">
                                    <a href="#" onclick="showAjaxModal('${cpi}/${login_type}/<% out.write(cate_bean.url_safe_encryptdata(classSubject3.getSubjectId().getSubjectId()+""));%>');" class="btn btn-success btn-xs"> 
                                        <i class="entypo-plus-circled"></i>
                                        <strong>
                                            <% out.write("Assign "+classSubject3.getSubjectId().getName()+" ("+classSubject3.getSubjectId().getCode()+") to "+classSubject3.getSubjectId().getName());%>
                                        </strong>
                                    </a>
                                </div>
        		    </td>
                        </tr>
                        <%
                            counter++;
                            } 
                            }
                        }catch(NullPointerException npe)
                        {
                            System.out.println("Second Exception...");
                        }
                        %>
                        
                    </tbody>
                </table>                
                </div>                
		</div>
                <%
                    try{
                    if(termList.size() > 0)
                    {
                        for(Term term3 : termList)
                        {
                %>
                <div class="tab-pane" id="<% out.write(term3.getTermId()+"");%>">
                    <table class="table-responsive table-bordered table-striped" id="table_export<% out.write(term3.getTermId()+"");%>">
                    <thead>
                            <tr>
                            <th><div>SN</div></th>
                            <th><div>Class</div></th>
                            <th><div>Term</div></th>
                            <th><div>Subject name</div></th>
                            <th><div>Teacher</div></th>
                            <th><div>Assign date</div></th>
                            <th><div>Subject action</div></th>
                            <th><div>Action</div></th>
                            </tr>
                    </thead>
                    <tbody>
                     <% 
                        counter = 1;
                        try{
                        if(classSubjects !=null){
                        for(ClassSubjects classSubject4 : classSubjects)
                        {
                            if(classSubject4.getTermId().getTermId() == term3.getTermId())
                            {
                       %>
                        <tr>
                            <td><% out.write(counter+"");%></td>
                            <td><% out.write(classSubject4.getClassId().getName()+"");%></td>
                            <td><% out.write(classSubject4.getTermId().getName()+"");%></td>
                            <td><% out.write(classSubject4.getSubjectId().getName()+"");%></td>
                            <td>
                                <% if(classSubject4.getTeacherId() != null && classSubject4.getTeacherId().getEmployeeId() > 0)
                                    {
                                      out.write(classSubject4.getTeacherId().getLastName()+" "+classSubject4.getTeacherId().getFirstName() +"(<strong> "+classSubject4.getTeacherId().getDesignationId().getName()+"</strong>)");
                                    }
                                %>
                            </td>
                            <%
                                Date created_date  = classSubject4.getCreateddate();
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 'at' hh:mm:ss a zzz");// FOrmat in This Format or you change Change as well 
                                String created = format.format(created_date);
                            %>
                            <td><%out.write(created.trim());%></td>
                            <td>
                                <%!
                                    int check_camark = 0;
                                    int check_exammark = 0;
                                %>
                                <%
                                    check_camark = classSubject4.getSubjectId().getCaMarkCollection().size();
                                    check_exammark = classSubject4.getSubjectId().getExamMarkCollection().size();
                                %>
                                <div class="btn-group">
                                    <a href="#" 
                                       <%
                                          if(check_camark == 0 && check_exammark == 0)
                                          {
                                       %>
                                       onclick="confirm_modal('${cpi}/${login_type}/modal_delete_class_subject/<% out.write(cate_bean.url_safe_encryptdata(""+classSubject4.getClassSubjectId()));%>/<% out.write(cate_bean.url_safe_encryptdata(""+classSubject4.getTermId().getTermId()));%>');">
                                       <%
                                          } else{
                                            out.write("#");
                                           }
                                       %>
                                       
                                            <button type="button" class="btn <% if(check_camark == 0 && check_exammark == 0){out.write("btn-default");}else{out.write("btn-danger");} %> btn-xs entypo-minus-circled"  
                                                <% if(check_camark == 0 && check_exammark == 0){out.write("disabled");} %> 
                                                <% out.write("Remove "+classSubject4.getSubjectId().getName()+" ("+classSubject4.getSubjectId().getCode()+")"+" from "+classSubject4.getClassId().getName()+" ("+term3.getName()+")");%></button>
                                    </a>
                                    
                                    
                                </div>


                                
                            </td>
                            <td>
                                <div class="panel-title">
                                    <a href="#" onclick="showAjaxModal('${cpi}/${login_type}/change_class_subject_teacher/<% out.write(cate_bean.url_safe_encryptdata(classSubject4.getClassSubjectId()+""));%>');">
                                        <button type="button" class="btn btn-info btn-xs fa fa-edit">Change teacher</button>
                                    </a>
                                      
                                </div>

                            </td>
                            
                        </tr>
                        <%  
                            counter++;
                            }
                        }
                        }
                        }catch(NullPointerException npe)
                        {
                            System.out.println("before third Exception...");
                        }
                     %>
                     
                    </tbody>
                </table>
                </div>   
                <%            
                        }
                    }
                }catch(NullPointerException npe)
                        {
                            System.out.println("Third Exception...");
                        }
                %>
                        
                        
               </div>
	</div>
</div>


<!--  DATA TABLE EXPORT CONFIGURATIONS -->                      
<script type="text/javascript">
    jQuery(document).ready(function($)
	{
            <%
              for(Term values : termList)
                {
             %>
                 var datatable_<%out.write(""+values.getTermId());%> = $("#table_export<%out.write(""+values.getTermId());%>").dataTable({
			"sPaginationType": "bootstrap",
			"sDom": "<'row'<'col-xs-3 col-left'l><'col-xs-9 col-right'<'export-data'T>f>r>t<'row'<'col-xs-3 col-left'i><'col-xs-9 col-right'p>>",
			"oTableTools": {
				"aButtons": [
					
					{
						"sExtends": "print",
						"fnSetText"	   : "Press 'esc' to return",
						"fnClick": function (nButton, oConfig) {
							datatable_<%out.write(""+values.getTermId());%>.fnSetColumnVis(1, false);
							datatable_<%out.write(""+values.getTermId());%>.fnSetColumnVis(5, false);
							
							this.fnPrint( true, oConfig );
							
							window.print();
							
							$(window).keyup(function(e) {
								  if (e.which == 27) {
									  datatable_<%out.write(""+values.getTermId());%>.fnSetColumnVis(1, true);
									  datatable_<%out.write(""+values.getTermId());%>.fnSetColumnVis(5, true);
								  }
							});
						},
						
					},
				]
			},
			
		});     
              <%
                }
              %>  
               
                        
                            
                        
                          
                

		var datatable = $("#table_export").dataTable();
		
		$(".dataTables_wrapper select").select2({
			minimumResultsForSearch: -1
		});
                
                var datatable_2 = $("#table_export_2").dataTable();
		
		$(".dataTables_wrapper select").select2({
			minimumResultsForSearch: -1
		});
	});
		
</script>
