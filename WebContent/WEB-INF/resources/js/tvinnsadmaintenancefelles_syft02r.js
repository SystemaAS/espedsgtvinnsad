  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
	  jq("#formRecord").submit(function() {
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT}); 
	  });
  });
  
  jq(function() {
	  jq("#kvadtNO").datepicker({ 
		  dateFormat: 'ddmmy'
	  });
	  
  });
  
  
  jq(function() {
		//Clean values for createing new record
		jq('#newRecordButton').click(function() {
			jq('#kvakod').val("");
			jq("#kvakod").prop("readonly", false);
			jq("#kvakod").removeClass("inputTextReadOnly");
			jq("#kvakod").addClass("inputTextMediumBlueMandatoryField");
			//
			jq('#kvadtNO').val("");
			jq("#kvadtNO").prop("readonly", false);
			jq("#kvadtNO").removeClass("inputTextReadOnly");
			jq("#kvadtNO").addClass("inputTextMediumBlueMandatoryField"); 
 			 			
			//rest of the gang
			jq('#kvaxxx').val("");
			jq('#kvakrs').val("");
			jq('#kvaomr').val("");
			jq('#kvagv').val("");
			
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
	var kvakod = record[0];
	var kvadt = record[1];
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_syft02r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : kvakod,
  		  	  date : kvadt},
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#kvakod').val("");jq('#kvakod').val(data[i].kvakod);
  			jq("#kvakod").prop("readonly", true);
  			jq("#kvakod").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#kvakod").addClass("inputTextReadOnly");
  			//
  			jq('#kvadtNO').val("");jq('#kvadtNO').val(data[i].kvadtNO);
  			jq("#kvadtNO").prop("readonly", true);
  			jq("#kvadtNO").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#kvadtNO").addClass("inputTextReadOnly"); 			
 			
  			//rest of the gang
  			jq('#kvaxxx').val("");jq('#kvaxxx').val(data[i].kvaxxx);
  			jq('#kvakrs').val("");jq('#kvakrs').val(data[i].kvakrs);
  			jq('#kvaomr').val("");jq('#kvaomr').val(data[i].kvaomr);
  			jq('#kvagv').val("");jq('#kvagv').val(data[i].kvagv);
  			
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].kvakod);
  			
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
    	  "lengthMenu": [ 75, 100]
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  