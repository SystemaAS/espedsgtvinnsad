	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
    
  	//--------
  	//Events
  	//--------
  	jq(function() {
  		/* TO BE DECIDED
		jq('#mainList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			  var id = record[0].replace("id", "");
			  var date = record[1].replace("date", "");
			  var time = record[2].replace("time", "");
			  
			  //var callerType = record[1].replace("ctype", "");
			  alert(id + "XX" + date + "XX" + time);
			  
		  });
		  */
	});
  	
  	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGeneral () {
    		jq('#mainList').DataTable().search(
      		jq('#mainList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#mainList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "columnDefs": [{ "type": "num", "targets": [0,1,2] }],
    		  "order": [[ 0, "asc" ], [ 1, 'asc' ], [ 2, 'asc' ]],
    		  "lengthMenu": [ 75, 100, 200, 500]
    		                  
    	  });
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGeneral();
      });
      
    });   
  	
  	
  	