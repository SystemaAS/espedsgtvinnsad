  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
	  jq("#sidtg").datepicker({ 
		  dateFormat: 'yymmdd' 	  
	  });
  });
  
  //-----------------------
  //GET specific db-record
  //-----------------------
  function getRecord(record){
	var rawId = record.id;
  	var applicationUserParam = jq('#applicationUser').val();
  	
  	rawId = rawId.replace("recordUpdate_", "");
  	var record = rawId.split('_');
	var avd = record[0];
	var opd = record[1];
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_sad006r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  avd : avd,
  		  	  opd : opd },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#siavd').val("");jq('#siavd').val(data[i].siavd);
  			//rest of the gang
  			jq('#sitdn').val("");jq('#sitdn').val(data[i].sitdn);
  			jq('#sitll').val("");jq('#sitll').val(data[i].sitll);
  			jq('#sitle').val("");jq('#sitle').val(data[i].sitle);
  			jq('#sidtg').val("");jq('#sidtg').val(data[i].sidtg);
  			jq('#sinak').val("");jq('#sinak').val(data[i].sinak);
  			
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].sitdn);
  			//enable submit
  			jq("#submit").prop("disabled", false);
  			
  		}
  	  }, 
  	  error: function() {
  		  alert('Error loading ...');
  	  }
	});
		
  }
  
			
  //-------------------
  //Datatables jquery
  //-------------------
  //private function
  function filterGlobal () {
    jq('#mainList').dataTable().search(
    	jq('#mainList_filter').val()
    ).draw();
  }
  
  jq(document).ready(function() {
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
      jq('#mainList').dataTable( {
    	  "dom": '<"top">t<"bottom"flip><"clear">',
    	  "scrollY": "250px",
    	  "scrollCollapse":  false,
    	  "columnDefs": [{ "type": "num", "targets": 0 }],
    	  "lengthMenu": [ 75, 100]
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  