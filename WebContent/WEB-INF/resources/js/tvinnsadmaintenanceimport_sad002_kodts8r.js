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
			jq('#ks8avg').val("");
			jq("#ks8avg").prop("readonly", false);
			jq("#ks8avg").removeClass("inputTextReadOnly");
			jq("#ks8avg").addClass("inputTextMediumBlueMandatoryField");
			//
			jq('#ks8skv').val("");
			jq("#ks8skv").prop("readonly", false);
			jq("#ks8skv").removeClass("inputTextReadOnly");
			jq("#ks8skv").addClass("inputTextMediumBlue");
			//rest of the gang
			jq('#ks8ftx').val("");
			jq('#ore').val("");
			jq('#mil').val("");
			jq('#ks8sat').val("");
  			jq('#ks8sty').val("");
			
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
	var avgId = record[0];
	var skvId = record[1];
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_sad002_kodts8r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  avgId : avgId,
  		  	  skvId : skvId },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#ks8avg').val("");jq('#ks8avg').val(data[i].ks8avg);
  			jq("#ks8avg").prop("readonly", true);
  			jq("#ks8avg").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#ks8avg").addClass("inputTextReadOnly");
  			//sekv.
  			jq('#ks8skv').val("");jq('#ks8skv').val(data[i].ks8skv);
  			jq("#ks8skv").prop("readonly", true);
  			jq("#ks8skv").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#ks8skv").addClass("inputTextReadOnly");
  			//editable fields
  			jq('#ks8ftx').val("");jq('#ks8ftx').val(data[i].ks8ftx);
  			jq('#ore').val("");jq('#ore').val(data[i].ore);
  			jq('#mil').val("");jq('#mil').val(data[i].mil);
  			jq('#ks8sat').val("");jq('#ks8sat').val(data[i].ks8sat);
  			jq('#ks8sty').val("");jq('#ks8sty').val(data[i].ks8sty);
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].ks8avg + "_" +  data[i].ks8skv);
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
  