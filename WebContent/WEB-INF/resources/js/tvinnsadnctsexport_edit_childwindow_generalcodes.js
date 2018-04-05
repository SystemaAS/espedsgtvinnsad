	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq('#generalCodeList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			  var kod = record[0].replace("kod", "");
			  var callerType = record[1].replace("ctype", "");
			  //alert(kod + " " + callerType);
			  
			  //addressing a parent field from this child window
			  if(callerType == 'thsks'){  //Avsender
				  opener.jq('#thsks').val(kod);
				  opener.jq('#thsks').focus();
				  
			  }else if(callerType == 'thlks'){ //Avsender
				  opener.jq('#thlks').val(kod);
				  opener.jq('#thlks').focus();
				  
			  }else if(callerType == 'thskk'){ //Mottaker
				  opener.jq('#thskk').val(kod);
				  opener.jq('#thskk').focus();
				  
			  }else if(callerType == 'thlkk'){ //Mottaker
				  opener.jq('#thlkk').val(kod);
				  opener.jq('#thlkk').focus();
				  
			  }else if(callerType == 'thska'){ //Ansvarlig
				  opener.jq('#thska').val(kod);
				  opener.jq('#thska').focus();
				  
			  }else if(callerType == 'thlka'){ //Ansvarlig
				  opener.jq('#thlka').val(kod);
				  opener.jq('#thlka').focus();
				  
			  }else if(callerType == 'thskss'){  //Avsender (Sikkerhet)
				  opener.jq('#thskss').val(kod);
				  opener.jq('#thskss').focus();
				  
			  }else if(callerType == 'thlkss'){ //Avsender (Sikkerhet)
				  opener.jq('#thlkss').val(kod);
				  opener.jq('#thlkss').focus();
				  
			  }else if(callerType == 'thskks'){ //Mottaker (Sikkerhet)
				  opener.jq('#thskks').val(kod);
				  opener.jq('#thskks').focus();
				  
			  }else if(callerType == 'thlkks'){ //Mottaker (Sikkerhet)
				  opener.jq('#thlkks').val(kod);
				  opener.jq('#thlkks').focus();
				  
			  }else if(callerType == 'thskt'){ //Transportør (Sikkerhet)
				  opener.jq('#thskt').val(kod);
				  opener.jq('#thskt').focus();
				  
			  }else if(callerType == 'thlkt'){ //Transportør (Sikkerhet)
				  opener.jq('#thlkt').val(kod);
				  opener.jq('#thlkt').focus();
				  
			  }else if(callerType == 'thalk'){
				  opener.jq('#thalk').val(kod);
				  opener.jq('#thalk').focus();
				  
			  }else if(callerType == 'thblk'){
				  opener.jq('#thblk').val(kod);
				  opener.jq('#thblk').focus();
				  
			  }else if(callerType == 'thtalk'){
				  opener.jq('#thtalk').val(kod);
				  opener.jq('#thtalk').focus();
				  
			  }else if(callerType == 'thtask'){
				  opener.jq('#thtask').val(kod);
				  opener.jq('#thtask').focus();
				  
			  }else if(callerType == 'thtglk'){
				  opener.jq('#thtglk').val(kod);
				  opener.jq('#thtglk').focus();
				  
			  }else if(callerType == 'thtgsk'){
				  opener.jq('#thtgsk').val(kod);
				  opener.jq('#thtgsk').focus();
				  
			  }else if(callerType == 'thskfd'){
				  opener.jq('#thskfd').val(kod);
				  opener.jq('#thskfd').focus();
				  
			  }else if(callerType == 'thdsk'){
				  opener.jq('#thdsk').val(kod);
				  opener.jq('#thdsk').focus();
				  
			  }else if(callerType == 'thlosdsk'){
				  opener.jq('#thlosdsk').val(kod);
				  opener.jq('#thlosdsk').focus();
				  
			  }
			  
			  
			  //close child window
			  window.close();
		  });
	});
	
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGeneralCode () {
    		jq('#generalCodeList').DataTable().search(
      		jq('#generalCodeList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#generalCodeList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.generalCodeList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	