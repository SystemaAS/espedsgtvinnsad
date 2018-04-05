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
			jq('#ktspnr').val("");
			jq("#ktspnr").prop("readonly", false);
			jq("#ktspnr").removeClass("inputTextReadOnly");
			jq("#ktspnr").addClass("inputTextMediumBlueMandatoryField");
			//
			jq('#ktsnav').val("");
			jq("#ktsnav").prop("readonly", false);
			jq("#ktsnav").removeClass("inputTextReadOnly");
			jq("#ktsnav").addClass("inputTextMediumBlueMandatoryField");
			
			//rest of the gang
			jq('#ktxpnr').val("");
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
	var ktspnr = record[0];
	var ktxpnr = record[1];
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_syft04r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : ktspnr },  		  	  
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#ktspnr').val("");jq('#ktspnr').val(data[i].ktspnr);
  			jq("#ktspnr").prop("readonly", true);
  			jq("#ktspnr").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#ktspnr").addClass("inputTextReadOnly");
  			//
  			jq('#ktsnav').val("");jq('#ktsnav').val(data[i].ktsnav);
  			jq("#ktsnav").prop("readonly", true);
  			jq("#ktsnav").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#ktsnav").addClass("inputTextReadOnly");
  			
  			//rest of the gang
  			jq('#ktxpnr').val("");jq('#ktxpnr').val(data[i].ktxpnr);
  			
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].ktspnr);
  			
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
  