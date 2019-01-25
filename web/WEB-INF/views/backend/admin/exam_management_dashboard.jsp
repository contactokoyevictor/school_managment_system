<div class="row">
    <div class="col-md-12">
    <div class="row">
         <div class="col-md-2">
            <a href="${pageContext.request.contextPath}/${login_type}/exam_list_landing#main-content">
            <div class="tile-title tile-brown">
                <div class="icon"> <i class="glyphicon glyphicon-list-alt"></i> </div> 
                <div class="title"> 
                    <p><strong>Exam list</strong></p> </div> 
            </div>
            </a>
        </div>
        
        
        <div class="col-md-2">
              <a href="${pageContext.request.contextPath}/${login_type}/exam_grade#main-content">
                <div class="tile-title tile-aqua">
                <div class="icon"> <i class="glyphicon glyphicon-sort-by-order-alt"></i> </div> 
                <div class="title"> 
                    <p><strong>Manage grades</strong></p> </div> 
                </div>
               </a>
             </div>
        
        <div class="col-md-2">
              <a href="${pageContext.request.contextPath}/${login_type}/ca_marks#main-content">
                <div class="tile-title tile-red">
                <div class="icon"> <i class="glyphicon glyphicon-pencil"></i> </div> 
                <div class="title"> 
                    <p><strong><small>Assessments</small></strong></p> </div> 
            </div>
            </a>
        </div>
        
        <div class="col-md-2">
              <a href="${pageContext.request.contextPath}/${login_type}/marks#main-content">
                <div class="tile-title tile-pink">
                <div class="icon"> <i class="glyphicon glyphicon-check"></i> </div> 
                <div class="title"> 
                    <p><strong><small>Manage exam marks</small></strong></p> </div> 
            </div>
            </a>
        </div>
        
        <div class="col-md-2">
            <a href="${pageContext.request.contextPath}/${login_type}/exam_class_position#main-content">
            <div class="tile-title tile-orange">
                <div class="icon"> <i class="fa fa-sort-amount-desc"></i> </div> 
                <div class="title"> 
                    <p><strong>Class position</strong></p> </div> 
            </div>
            </a>
        </div>
     
        <div class="col-md-2">
              <a href="<?php echo base_url(); ?>index.php?<?php echo $this->session->userdata('login_type');?>/tabulation_sheet#main-content">
                <div class="tile-title tile-blue">
                <div class="icon"> <i class="fa fa-table"></i> </div> 
                <div class="title"> 
                    <p><strong><?php echo get_phrase('tabulation_sheet');?></strong></p> </div> 
                </div>
               </a>
             </div>
        
        
        
        
        
    </div>
    
  </div>
    
    <div class="col-md-12">
        <div class="row">
           

             
            
            <div class="col-md-2">
              <a href="${pageContext.request.contextPath}/${login_type}/manage_behavioural_trait#main-content">
                <div class="tile-title tile-cyan">
                <div class="icon"> <i class="fa fa-gavel"></i> </div> 
                <div class="title"> 
                    <p><strong><small>Behavioural traits</small></strong></p> </div> 
            </div>
            </a>
        </div>              
                
        <div class="col-md-2">
              <a href="${pageContext.request.contextPath}/${login_type}/manage_behavioural_rating_key#main-content">
                <div class="tile-title tile-purple">
                <div class="icon"> <i class="fa fa-star-o fa-spin"></i> </div> 
                <div class="title"> 
                    <p><strong><small>Behavioural rating</small></strong></p> </div> 
            </div>
            </a>
        </div>

        <div class="col-md-2">
              <a href="${pageContext.request.contextPath}/${login_type}/terminal_marksheet_report#main-content">
                <div class="tile-title tile-aqua">
                <div class="icon"> <i class="fa fa-bar-chart-o"></i> </div> 
                <div class="title"> 
                    <p><strong>Terminal report</strong></p> </div> 
            </div>
                </a>
            </div>

         <div class="col-md-2">
              <a href="${pageContext.request.contextPath}/${login_type}/mass_result_print#main-content">
                <div class="tile-title tile-green">
                <div class="icon"> <i class="fa fa-print"></i> </div> 
                <div class="title"> 
                    <p><strong>Mass result print</strong></p> </div> 
                </div>
               </a>
             </div>

        </div>
    </div> 
   
                      
	
</div>


  
