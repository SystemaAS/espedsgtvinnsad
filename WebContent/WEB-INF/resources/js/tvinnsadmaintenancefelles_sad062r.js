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
			jq('#tariff').val("");
			jq("#tariff").prop("readonly", false);
			jq("#tariff").removeClass("inputTextReadOnly");
			jq("#tariff").addClass("inputTextMediumBlueUPPERCASEMandatoryField");
			//rest of the gang
			jq('#beskr1').val("");
			jq('#beskr1Orig').val("");
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
	var tariff = record[0];
	var beskr1 = record[1];
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_sad062r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : tariff,
  		  	  alfa : beskr1 },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#tariff').val("");jq('#tariff').val(data[i].tariff);
  			jq("#tariff").prop("readonly", true);
  			jq("#tariff").removeClass("inputTextMediumBlueUPPERCASEMandatoryField");
  			jq("#tariff").addClass("inputTextReadOnly");
  			
  			//rest of the gang
  			jq('#beskr1').val("");jq('#beskr1').val(data[i].beskr1);
  			jq('#beskr1Orig').val("");jq('#beskr1Orig').val(data[i].beskr1);
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].tariff);
  			
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
  