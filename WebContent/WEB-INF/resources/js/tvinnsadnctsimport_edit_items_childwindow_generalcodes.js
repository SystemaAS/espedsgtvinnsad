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
			  if(callerType == 'tvstlk'){  
				  opener.jq('#tvstlk').val(kod);
				  opener.jq('#tvstlk').focus();

			  } else if(callerType == 'tvgmlk'){  
				  opener.jq('#tvgmlk').val(kod);
				  opener.jq('#tvgmlk').focus();
				  
			  } else if(callerType == 'tvtalk'){  
				  opener.jq('#tvtalk').val(kod);
				  opener.jq('#tvtalk').focus();

			  } else if(callerType == 'tvomlk'){  
				  opener.jq('#tvomlk').val(kod);
				  opener.jq('#tvomlk').focus();
			  } //SPRÅK
			  	else if(callerType == 'tvstsk'){  
				  opener.jq('#tvstsk').val(kod);
				  opener.jq('#tvstsk').focus();
				  
			  } else if(callerType == 'tvgmsk'){  
				  opener.jq('#tvgmsk').val(kod);
				  opener.jq('#tvgmsk').focus();
				  
			  } else if(callerType == 'tvgmss'){  
				  opener.jq('#tvgmss').val(kod);
				  opener.jq('#tvgmss').focus();
				  
			  } else if(callerType == 'tvdfsk'){  
				  opener.jq('#tvdfsk').val(kod);
				  opener.jq('#tvdfsk').focus();
				  
			  } else if(callerType == 'tvtask'){  
				  opener.jq('#tvtask').val(kod);
				  opener.jq('#tvtask').focus();
				  
			  } else if(callerType == 'tvomsk'){  
				  opener.jq('#tvomsk').val(kod);
				  opener.jq('#tvomsk').focus();
				  
			  } else if(callerType == 'tvomss'){  
				  opener.jq('#tvomss').val(kod);
				  opener.jq('#tvomss').focus();
				  
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
  	
  	
	