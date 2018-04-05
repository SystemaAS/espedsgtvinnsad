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
			  if(callerType == 'seval1'){
				  opener.jq('#seval1').val(kod);
				  opener.jq('#seval1').focus();
				  
			  }else if(callerType == 'selka'){
				  opener.jq('#selka').val(kod);
				  opener.jq('#selka').focus();
				  
			  }else if(callerType == 'selkb'){
				  opener.jq('#selkb').val(kod);
				  opener.jq('#selkb').focus();
				  
			  }else if(callerType == 'selkt'){
				  opener.jq('#selkt').val(kod);
				  opener.jq('#selkt').focus();
				  
			  }else if(callerType == 'seval2'){
				  opener.jq('#seval2').val(kod);
				  opener.jq('#seval2').focus();
				  
			  }else if(callerType == 'sfvk28'){
				  //this belongs to the invoice JSP - finansopplysninger
				  opener.jq('#sfvk28').val(kod);
				  opener.jq('#sfvk28').focus();
				  
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
  	
  	
	