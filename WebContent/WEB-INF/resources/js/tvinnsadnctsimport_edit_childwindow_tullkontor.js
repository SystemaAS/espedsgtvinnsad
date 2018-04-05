	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//-----------
  	// Tullkontor
  	//-----------
	jq(function() {
		jq('#tullkontorList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			  var tkkode = record[0].replace("tkkode", "");
			  var tktxtn = record[1].replace("tktxtn", "");
			  var callerType = record[2].replace("ctype", "");
			  
			  //Tullkontor - thcats
			  if(callerType == 'titsb'){  
				  opener.jq('#titsb').val(tkkode);
				  opener.jq('#titsb').focus();
				  
			  }
			  //close child window
			  window.close();
			  
	    });
	});
	
	
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterTullkontor () {
    		jq('#tullkontorList').DataTable().search(
      		jq('#tullkontorList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#tullkontorList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.tullkontorList_filter').on( 'keyup click', function () {
      		filterTullkontor();
      });
      
    });   
  	
  	
	