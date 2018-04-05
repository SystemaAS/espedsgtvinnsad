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
			jq('#ks5tln').val("");
			jq("#ks5tln").prop("readonly", false);
			jq("#ks5tln").removeClass("inputTextReadOnly");
			jq("#ks5tln").addClass("inputTextMediumBlueMandatoryField");
			//
			jq('#ks5ftx').val("");
  			
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
  	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_sad002_kodts5r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : rawId },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#ks5tln').val("");jq('#ks5tln').val(data[i].ks5tln);
  			jq("#ks5tln").prop("readonly", true);
  			jq("#ks5tln").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#ks5tln").addClass("inputTextReadOnly");
  			//editable fields
  			jq('#ks5ftx').val("");jq('#ks5ftx').val(data[i].ks5ftx);
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].ks5tln);
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
  