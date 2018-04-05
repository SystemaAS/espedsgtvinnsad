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
			  if(callerType == 'tvvtsk'){  
				  opener.jq('#tvvtsk').val(kod);
				  opener.jq('#tvvtsk').focus();
				  
			  }else if(callerType == 'tvmnsk'){ 
				  opener.jq('#tvmnsk').val(kod);
				  opener.jq('#tvmnsk').focus();
				  
			  }else if(callerType == 'tvdsk'){ 
				  opener.jq('#tvdsk').val(kod);
				  opener.jq('#tvdsk').focus();
				  
			  }else if(callerType == 'tvsks'){ 
				  opener.jq('#tvsks').val(kod);
				  opener.jq('#tvsks').focus();
				  
			  }else if(callerType == 'tvlks'){ 
				  opener.jq('#tvlks').val(kod);
				  opener.jq('#tvlks').focus();
				  
			  }else if(callerType == 'tvskk'){ 
				  opener.jq('#tvskk').val(kod);
				  opener.jq('#tvskk').focus();
				  
			  }else if(callerType == 'tvlkk'){ 
				  opener.jq('#tvlkk').val(kod);
				  opener.jq('#tvlkk').focus();
				  
			  }else if(callerType == 'tvalk'){ 
				  opener.jq('#tvalk').val(kod);
				  opener.jq('#tvalk').focus();
				  
			  }else if(callerType == 'tvblk'){ 
				  opener.jq('#tvblk').val(kod);
				  opener.jq('#tvblk').focus();
				  
			  }else if(callerType == 'tveh'){ //Kollislag
				  opener.jq('#tveh').val(kod);
				  opener.jq('#tveh').focus();
				  
			  }else if(callerType == 'tveh2'){ //Kollislag
				  opener.jq('#tveh2').val(kod);
				  opener.jq('#tveh2').focus();
				  
			  }else if(callerType == 'tveh3'){ //Kollislag
				  opener.jq('#tveh3').val(kod);
				  opener.jq('#tveh3').focus();
				  
			  }else if(callerType == 'tveh4'){ //Kollislag
				  opener.jq('#tveh4').val(kod);
				  opener.jq('#tveh4').focus();
				  
			  }else if(callerType == 'tveh5'){ //Kollislag
				  opener.jq('#tveh5').val(kod);
				  opener.jq('#tveh5').focus();
				  
			  }else if(callerType == 'tvsks'){ 
				  opener.jq('#tvsks').val(kod);
				  opener.jq('#tvsks').focus();
			
			  //SIKKERHET	  
			  }else if(callerType == 'tvlkss'){ 
				  opener.jq('#tvlkss').val(kod);
				  opener.jq('#tvlkss').focus();
				  
			  }else if(callerType == 'tvskss'){ 
				  opener.jq('#tvskss').val(kod);
				  opener.jq('#tvskss').focus();
				  
			  }else if(callerType == 'tvskks'){ 
				  opener.jq('#tvskks').val(kod);
				  opener.jq('#tvskks').focus();
				  
			  }else if(callerType == 'tvlkks'){ 
				  opener.jq('#tvlkks').val(kod);
				  opener.jq('#tvlkks').focus();  
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
  	
  	
	