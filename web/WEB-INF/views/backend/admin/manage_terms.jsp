<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>

<jsp:useBean id="cate_bean" scope="request" class="com.sivotek.school_management_system.app.crud.crud_bean" />
<jsp:setProperty name="cate_bean" property="*" />
<%@page import="com.sivotek.school_management_system.entities.Term;" %>
<%@page import="com.sivotek.school_management_system.entities.controllers.TermJpaController;" %>
<%@page import="com.sivotek.school_management_system.entities.AcademicYears;" %>
<%@page import="com.sivotek.school_management_system.entities.controllers.AcademicYearsJpaController;" %>
<%! 
    protected final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    protected final SimpleDateFormat datetimeFormat = new SimpleDateFormat("hh:mm:ss z", Locale.getDefault());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    Collection<Term> termList = null;
    Collection<AcademicYears> academicYearList = null;
    Integer counter = 0;
    long branch = 0L;
String from = "";
String to ="";

%>

<div class="row">
	<div class="col-md-12">
    
    	<!------CONTROL TABS START------>
		<ul class="nav nav-tabs bordered">
			<li class="active">
                        <a href="#list" data-toggle="tab"><i class="entypo-menu"></i> 
			Term list
                    	</a></li>
			<li>
            	<a href="#add" data-toggle="tab"><i class="entypo-plus-circled"></i>
			Add new term
                    	</a></li>
		</ul>
    	<!------CONTROL TABS END------>
        
		<div class="tab-content">
            <!----TABLE LISTING STARTS-->
            <div class="tab-pane box active" id="list">
				
                <table class="table-responsive table-striped table-hover table-bordered datatable" id="table_export">
                	<thead>
                		<tr>
                    		<th><div>#</div></th>
                    		<th><div>Term name</div></th>
                    		<th><div>Nick name</div></th>
                    		<th><div>Start from</div></th>
                                <th><div>End on</div></th>
                                <th><div>Academic year</div></th>
                    		<th><div>Options</div></th>
				</tr>
			</thead>
                    <tbody>
                  <%
                   
                    branch = Long.parseLong(""+session.getAttribute("branch_id"));
                    termList = cate_bean.getTermList(branch);
                    counter = 0;
                    for(Term term : termList)
                    { 
                        
                        counter++;
                  %>
                        
                        <tr>
                            <%
                                Date startfrom_date  = term.getStartFrom();
                                Date endIn_date  = term.getEndIn();
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// FOrmat in This Format or you change Change as well 
                                String startfrom = format.format(startfrom_date);
                                String endIn = format.format(endIn_date);
                            %>
                            <td><% out.write(counter.toString());%></td>
                            <td><% out.write(term.getName());%></td>
                            <td><% out.write(term.getNickName());%></td>
                            <td><% out.write(startfrom);%></td>
                            <td><% out.write(endIn);%></td>
                            <td><% out.write(term.getAcademicYear().getName()+"");%></td>
                            <td>
                                
                            <div class="btn-group">
                                <button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
                                    Action <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu dropdown-default pull-right" role="menu">
                                    
                                    <!-- EDITING LINK -->
		                                    <li>
		                                        <a href="#" onclick="showAjaxModal('${pageContext.request.servletContext.contextPath}/${login_type}/modal_edit_term/<% out.write(term.getTermId()+"");%>');">
                                                            <i class="entypo-pencil"></i>
		                                                Edit
		                                        </a>
		                                    </li>
		                                    
                                                    <li class="divider"></li>
		                                    <!-- DELETION LINK -->
		                                    <li>
                                                        <a href="#" onclick="confirm_modal('${pageContext.request.servletContext.contextPath}/${login_type}/modal_delete_term/<% out.write(term.getTermId()+"");%>');">
		                                            <i class="entypo-trash"></i>
		                                                Delete
		                                        </a>
		                                    </li>
                                </ul>
                            </div>
        		    </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
			</div>
            <!----TABLE LISTING ENDS--->
            
            
			<!----CREATION FORM STARTS---->
			<div class="tab-pane box" id="add" style="padding: 5px">
                <div class="box-content">
                        <form method="POST" action="${pageContext.request.contextPath}/${login_type}/modal_add_term/" id="form" class="form-horizontal form-groups-bordered validate" enctype="multipart/form-data">
                	
					<div class="form-group">
						<label for="field-1" class="col-sm-3 control-label">Name<span class="text-danger"> *</span></label>
                        
						<div class="col-sm-4">
							<input type="text" class="form-control" name="name" data-validate="required" data-message-required="value required" value="" autofocus>
						</div>
					</div>
					
					<div class="form-group">
						<label for="field-2" class="col-sm-3 control-label">Nick name<span class="text-danger"> *</span></label>
                        
						<div class="col-sm-4">
							<input type="text" class="form-control" name="nick_name" value="" >
						</div> 
					</div>

					
                                        <div class="form-group">
						<label for="field-1" class="col-sm-3 control-label">Start from<span class="text-danger"> *</span></label>
                        
						
                                                <div class="col-sm-4 input-group">
                                                    <input type="text" class="form-control datepicker" data-format="yyyy-mm-dd" name="start_from" id="start_from" data-validate="required" data-message-required="value required">
                                                        <div class="input-group-addon"><a href="#"><i class="entypo-calendar"></i></a></div>
						</div> 
					</div>
					
					<div class="form-group">
						<label for="field-2" class="col-sm-3 control-label">End on<span class="text-danger"> *</span></label>
                        
						<div class="col-sm-4 input-group">
                                                    <input type="text" class="form-control datepicker" data-format="yyyy-mm-dd" name="end_in" id="end_on" data-validate="required" data-message-required="value required">
                                                        <div class="input-group-addon"><a href="#"><i class="entypo-calendar"></i></a></div>
						</div> 
					</div>
                            
                            
                            
                            
                           
                            
                            
                    <div class="form-group">
                    <label class="col-sm-3 control-label">Academic year<span class="text-danger"> *</span></label>
                    <div class="col-sm-4 controls">
                        <select name="academic_year" class="form-control selectboxit" data-validate="required" data-message-required="value required">
                              <option value="">Select</option>
                              <%
                                  AcademicYearsJpaController academicYearsJpaController = new AcademicYearsJpaController();
                                  academicYearList = academicYearsJpaController.findAcademicYearsEntities();
                                  String selected = "false";
                                  for(AcademicYears academicYear : academicYearList)
                                  {
                                     if(academicYear.getStatus().equalsIgnoreCase("active"))
                                     {
                                         selected = "true";
                                     
                                  
                              %>
                             
                                    <option value="<% out.write(academicYear.getYearId()+"");%>" selected="<% out.write(selected);%>">
                                    <% out.write(academicYear.getName()+"");%>
                                    </option>
                                    <%
                                     }else{
                                    %>
                                        <option value="<% out.write(academicYear.getYearId()+"");%>">
                                           <% out.write(academicYear.getName()+"");%>
                                        </option>
                                    <%
                                           }
                                        }
                                    %>
                          </select>

                    </div>
                </div>

                    
                    <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-5">
                                    <button type="submit" id="submit" class="btn btn-info">Add term</button>
                            </div>
		    </div>
                 </form>              
                </div>                
			</div>
			<!----CREATION FORM ENDS-->
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