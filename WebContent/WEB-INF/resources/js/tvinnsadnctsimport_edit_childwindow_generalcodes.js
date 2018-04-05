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
			  
			  //addressing a parent field from this child window
			  if(callerType == 'tilk'){  
				  opener.jq('#tilk').val(kod);
				  opener.jq('#tilk').focus();
				  
			  }else if(callerType == 'tisk'){  
				  opener.jq('#tisk').val(kod);
				  opener.jq('#tisk').focus();
				  
			  }else if(callerType == 'tialk'){  
				  opener.jq('#tialk').val(kod);
				  opener.jq('#tialk').focus();
				  
			  }else if(callerType == 'tignsk'){  
				  opener.jq('#tignsk').val(kod);
				  opener.jq('#tignsk').focus();
				  
			  }else if(callerType == 'tialss'){  
				  opener.jq('#tialss').val(kod);
				  opener.jq('#tialss').focus();
				  
			  }else if(callerType == 'tiskb'){  
				  opener.jq('#tiskb').val(kod);
				  opener.jq('#tiskb').focus();
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
  	
  	
	