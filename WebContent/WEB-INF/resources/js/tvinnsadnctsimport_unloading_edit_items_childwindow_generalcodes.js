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
			  if(callerType == 'nvctsk'){  
				  opener.jq('#nvctsk').val(kod);
				  opener.jq('#nvctsk').focus();
				  
			  }else if(callerType == 'nvmnsk'){  
				  opener.jq('#nvmnsk').val(kod);
				  opener.jq('#nvmnsk').focus();
				  
			  }else if(callerType == 'nvdsk'){  
				  opener.jq('#nvdsk').val(kod);
				  opener.jq('#nvdsk').focus(); 
				  
			  }else if(callerType == 'nvdosk'){  
				  opener.jq('#nvdosk').val(kod);
				  opener.jq('#nvdosk').focus();
				  
			  }else if(callerType == 'nvvtsk'){  
				  opener.jq('#nvvtsk').val(kod);
				  opener.jq('#nvvtsk').focus();
				  
			  }else if(callerType == 'nveh'){  
				  opener.jq('#nveh').val(kod);
				  opener.jq('#nveh').focus(); 
				  
			  }else if(callerType == 'nvdty'){  
				  opener.jq('#nvdty').val(kod);
				  opener.jq('#nvdty').focus();  
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
  	
  	
	