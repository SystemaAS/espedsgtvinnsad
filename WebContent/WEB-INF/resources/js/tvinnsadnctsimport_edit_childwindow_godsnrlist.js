	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//-----------
  	// Tullkontor
  	//-----------
	jq(function() {
		jq('#mainList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			  var gogn = record[0].replace("gogn", "");
			  //var callerType = record[1].replace("ctype", "");
			  opener.jq('#tign').val(gogn);
			  opener.jq('#tign').focus();
				  
			 
			  //close child window
			  window.close();
			  
	    });
	});
	
	
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filter () {
    		jq('#mainList').DataTable().search(
      		jq('#mainList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [Main List]
  	  //-----------------------
    	  jq('#mainList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filter();
      });
      
    });   
  	
  	
	