	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//-----------
  	// Tullkontor
  	//-----------
	jq(function() {
		
		jq('#turList').on('click', 'td', function(){
			  var id = this.id;
			  //id="tupro${record.tupro}@tuavd${record.tuavd}@ctype${model.callerType}
			  var record = id.split('@');
			  var tupro = record[0].replace("tupro", "");
			  var tuavd = record[1].replace("tuavd", "");
			  
			  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
			  jq.ajax({
		  	  	  type: 'GET',
		  	  	  url: 'createAutomationFromSysped_Digitoll.do',
		  	  	  data: { applicationUser : jq('#applicationUser').val(),
							tur: tupro,
							avd : tuavd,
							sign : jq('#sign').val()},
							
		  	  	  dataType: 'json',
		  	  	  cache: false,
		  	  	  //async: false,
		  	  	  contentType: 'application/json',
		  	  	  success: function(data) {
					jq.unblockUI(); //must have async: true (default) to work
		  	  		var len = data.length;
		  	  		for ( var i = 0; i < len; i++) {
		  	  			//Update has been done successfully
				        
		  	  		}
					
		  	  	  },
			  	  error: function() {
					jq.unblockUI();
		  	  	    //alert('Error loading ...');
		  	  	  }
			  })
			  window.setTimeout(function(){
                 //we must reload the parent master window since the use case updates the invoice list (if the end-user has selected some invoices to import)
				  window.opener.setBlockUI();
				  window.opener.location.href="tvinnsaddigitollv2.do?action=doFind";
				  window.close();     
             }, 800); //milliseconds: in order to avoid a refresh in master due to the above Ajax create house. It could take more time to be finished on the background...
			 //}, 1500); //milliseconds: in order to avoid a refresh in master due to the above Ajax create house. It could take more time to be finished on the background...
			  
	    });
		
	});
	
	
	jq(function() {
	  jq("#tudt").datepicker({ 
		  dateFormat: 'yymmdd' 	  
	  });
	});
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterTur () {
    		jq('#turList').DataTable().search(
      		jq('#turList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
	  jq('#turList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',               
		  "searchHighlight": true,
		  "tabIndex": -1,
	  	  "order": [[ 0, "asc" ]], //Tur
		  "lengthMenu": [ 50, 100, 200, 500],
		  "fnDrawCallback": function( oSettings ) {
	    	jq('.dataTables_filter input').addClass("inputText12LightYellow");
			
	    	}
	  });
      //event on input field for search
      jq('input.turList_filter').on( 'keyup click', function () {
      		filterTur();
      });
      
    });   
  	
  	
	