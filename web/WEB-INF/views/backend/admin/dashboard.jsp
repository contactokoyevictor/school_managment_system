<%-- 
    Document   : dashboard
    Created on : Mar 17, 2017, 3:28:35 PM
    Author     : VICTOR_OKOYE
--%>

<div class="row">

    
    
    
<div class="col-md-12">
    <div class="row">

        <div class="panel panel-primary panel-shadow"  data-collapsed="0">
            
            <div class="panel-heading">
                <div class="panel-title pull-left"><small>System main modules</small></div> 
                <div class="panel-options"> 
                        <a href="#" data-rel="collapse"><i class="entypo-down-open">
                        </i></a> <a href="#" data-rel="reload"><i class="entypo-arrows-ccw"></i>
                    </a> 

                </div>
            </div>
            <div class="panel-body"> 

                <div class="col-md-12">
                    <div class="row">
                    <div class="col-md-4">
                      <a href="${pageContext.request.contextPath}/${login_type}/class_management_dashboard#main-content">
                        <div class="tile-title tile-blue">
                        <div class="icon"> <i class="entypo-flow-tree"></i> </div> 
                        <div class="title"> 
                            <h3><strong>Manage class </strong></h3> 
                            <p>Class management modeule</p>

                        </div> 
                    </div>
                    </a>
                </div>



                <div class="col-md-4">
                    <a href="${pageContext.request.contextPath}/${login_type}/student_management_dashboard#main-content">
                    <div class="tile-title tile-purple">
                        <div class="icon"> <i class="entypo-shareable"></i> </div> 
                        <div class="title"> 
                            <h3><strong>Manage students</strong></h3> 
                            <p>Student management module</p>
                        </div> 
                    </div>
                    </a>
                </div>


             

                 <div class="col-md-4">
                    <a href="${pageContext.request.contextPath}/${login_type}/exam_management_dashboard#main-content">
                    <div class="tile-title tile-pink">
                        <div class="icon"> <i class="fa fa-edit"></i> </div> 
                        <div class="title"> 
                            <h3><strong>Manage exam</strong></h3> 
                            <p>Exam and CA management module</p>

                       </div> 
                    </div>
                    </a>
                </div>

                   
                   </div>  
                </div>


                

                <div class="col-md-12">
                <div class="row">
                 <div class="col-md-4">
                       <a href="${pageContext.request.contextPath}/${login_type}/hr_management_dashboard#main-content">
                        <div class="tile-title tile-cyan">
                            <div class="icon"> <i class="entypo-user"></i> </div> 
                            <div class="title"> 
                                <h3><strong>Human resource management</strong></h3> 
                                <p>Human resource management module</p>

                           </div> 
                        </div>
                        </a>
                    </div>

                     <div class="col-md-4">
                                    <a href="${pageContext.request.contextPath}/${login_type}/accounting_management_dashboard#main-content">
                                    <div class="tile-title tile-brown">
                                        <div class="icon"> <i class="entypo-suitcase"></i> </div> 
                                        <div class="title"> 
                                            <h3><strong>accounting</strong></h3> 
                                            <p>System accounting packages</p>

                                       </div> 
                                    </div>
                                    </a>
                            </div> 


                      <div class="col-md-4">
                                    <a href="">
                                    <div class="tile-title tile-green">
                                        <div class="icon"> <i class="fa fa-envelope-o"></i> </div> 
                                        <div class="title"> 
                                            <h3><strong>Message</strong></h3> 
                                            <p>System messaging module</p>

                                       </div> 
                                    </div>
                                    </a>
                            </div>  

                    </div>
                    </div>
                         
                          

                    <div class="col-md-12">
                    <div class="row">
                       
                     
                      <div class="col-md-4">
                                    <a href="${pageContext.request.contextPath}/${login_type}/system_settings_dashboard#main-content">
                                    <div class="tile-title tile-red">
                                        <div class="icon"> <i class="entypo-tools"></i> </div> 
                                        <div class="title"> 
                                            <h3><strong>System settings</strong></h3> 
                                            <p>System settings module</p>

                                       </div> 
                                    </div>
                                    </a>
                            </div>

                            <div class="col-md-4">
                               <a href="">
                                <div class="tile-title tile-aqua">
                                    <div class="icon"> <i class="glyphicon glyphicon-cloud-upload"></i> </div> 
                                    <div class="title"> 
                                        <h3><strong>Backup / Restore</strong></h3> 
                                        <p>Data Import/Export module</p>

                                   </div> 
                                </div>
                                </a>
                            </div>
                            
                        </div>
                    </div>

                

            </div>
        </div>
        
</div>
</div>
                        
<div class="col-md-12">
    <div class="row">

        <div class="panel panel-primary panel-shadow" data-collapsed="0">
            
            <div class="panel-heading">
                <div class="panel-title pull-left"><small>Statistical information</small></div> 
                <div class="panel-options"> 
                        <a href="#" data-rel="collapse"><i class="entypo-down-open">
                        </i></a> <a href="#" data-rel="reload"><i class="entypo-arrows-ccw"></i>
                    </a> 

                </div>
            </div>
            <div class="panel-body"> 

                <div class="col-md-12">
                <div class="col-md-4">
                <div class="tile-stats tile-red">
                    <div class="icon"><i class="fa fa-group"></i></div>
                    <div class="num" data-start="0" data-end="<?php echo $result->num_rows();?>" 
                            data-postfix="" data-duration="1500" data-delay="0">0</div>
                    <h3>Student</h3>
                    <p>Total students (Current academic year)</p>
                </div>
                </div>
                
                <div class="col-md-4">
                <div class="tile-stats tile-green">
                    <div class="icon"><i class="entypo-users"></i></div>
                    <div class="num" data-start="0" data-end="<?php echo $this->crud_model->get_list_of_teachers_count();?>" 
                            data-postfix="" data-duration="800" data-delay="0">0</div>
                    <h3>Teacher</h3>
                   <p>Total teachers</p>
                </div>
                </div>
                
                <div class="col-md-4">
                <div class="tile-stats tile-blue">
                    <div class="icon"><i class="entypo-chart-bar"></i></div>
                   <div class="num" data-start="0" data-end="<?php echo $present_today;?>" 
                            data-postfix="" data-duration="500" data-delay="0">0</div>
                    <h3>Student attendance</h3>
                   <p>Total present student today</p>
                </div>
                </div>

                </div>
                 
         </div>


            
        </div>
        
    </div>
</div>
    
    
<div class="row">
    <div class="col-md-12">
    <div class="row">
        
         
   
        
   <div class="col-md-12"> 
    <div class="panel panel-primary" data-collapsed="0"> 
        <!-- panel head --> 
        <div class="panel-heading"> 
            <div class="panel-title">
                <i class="fa fa-calendar"></i>
                <?php echo get_phrase('event_schedule');?>
            </div>
            <div class="panel-options"> 
                <a href="#" data-rel="collapse"><i class="entypo-down-open">
                </i></a> <a href="#" data-rel="reload"><i class="entypo-arrows-ccw"></i>
            </a> <a href="#" data-rel="close">
            <i class="entypo-cancel"></i></a> 
        </div>
         </div> 
            <!-- panel body --> 
            <div class="panel-body"> 
                <div class="calendar-env">
                            <div class="calendar-body">
                                <div id="notice_calendar"></div>
                            </div>
                        </div>
        </div> 
    </div> 
</div>
    
     
        
	
</div>
</div>
</div>    
    

<script src="assets/js/amcharts/amcharts.js"></script>
<script src="assets/js/amcharts/serial.js"></script>
       
<script>
  $(document).ready(function() {
	  
	  var calendar = $('#notice_calendar');
				
				$('#notice_calendar').fullCalendar({
					header: {
						left: 'title',
						right: 'today prev,next'
					},
					
					//defaultView: 'basicWeek',
					
					editable: false,
					firstDay: 1,
					height: 530,
					droppable: false,
					
					events: [
						<?php 
						$notices	=	$this->db->get('noticeboard')->result_array();
						foreach($notices as $row):
						?>
						{
							title: "<?php echo $row['notice_title'];?>",
							start: new Date(<?php echo date('Y',$row['create_timestamp']);?>, <?php echo date('m',$row['create_timestamp'])-1;?>, <?php echo date('d',$row['create_timestamp']);?>),
							end:	new Date(<?php echo date('Y',$row['create_timestamp']);?>, <?php echo date('m',$row['create_timestamp'])-1;?>, <?php echo date('d',$row['create_timestamp']);?>) 
						},
						<?php 
						endforeach
						?>
						
					]
				});
	});
  </script>

      
 
 

  

