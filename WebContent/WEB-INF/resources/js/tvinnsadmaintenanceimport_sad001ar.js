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
			jq('#varenr').val("");
			jq("#varenr").prop("readonly", false);
			jq("#varenr").removeClass("inputTextReadOnly");
			jq("#varenr").addClass("inputTextMediumBlueUPPERCASEMandatoryField");
			//rest of the gang
			jq('#varebe').val("");
			
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
	var varenr = record[0];
	var levenr = record[1];
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_sad001ar.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : varenr,
  		  	  kundnr : levenr },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#varenr').val("");jq('#varenr').val(data[i].varenr);
  			jq("#varenr").prop("readonly", true);
  			jq("#varenr").removeClass("inputTextMediumBlueUPPERCASEMandatoryField");
  			jq("#varenr").addClass("inputTextReadOnly");
  			
  			//rest of the gang
  			jq('#varebe').val("");jq('#varebe').val(data[i].varebe);
  			jq('#levenr').val("");jq('#levenr').val(data[i].levenr);
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].varenr);
  			
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
  