  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  
  jq(function() {
		//Clean values for createing new record
		jq('#newRecordButton').click(function() {
			jq('#e9705').val("");
			jq("#e9705").prop("readonly", false);
			jq("#e9705").removeClass("inputTextReadOnly");
			jq("#e9705").addClass("inputTextMediumBlueUPPERCASEMandatoryField");
			//rest of the gang
			jq('#e4440').val("");
			
			//for update
			jq('#updateId').val("");
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
	var e9705 = record[0];
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_tvi99d.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : e9705
  		  	 },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#e9705').val("");jq('#e9705').val(data[i].e9705);
  			jq("#e9705").prop("readonly", true);
  			jq("#e9705").removeClass("inputTextMediumBlueUPPERCASEMandatoryField");
  			jq("#e9705").addClass("inputTextReadOnly");
  			
  			//rest of the gang
  			jq('#e4440').val("");jq('#e4440').val(data[i].e4440);
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].e9705);
  			
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
    	  "order": [[ 1, "asc" ]],
    	  "lengthMenu": [ 75, 100]
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  