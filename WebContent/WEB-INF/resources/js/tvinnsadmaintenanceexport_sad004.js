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
			jq('#slalfa').val("");
			jq("#slalfa").prop("readonly", false);
			jq("#slalfa").removeClass("inputTextReadOnly");
			jq("#slalfa").addClass("inputTextMediumBlueUPPERCASEMandatoryField");
			//rest of the gang
			jq('#sltxt').val("");
			jq('#sloppl').val("");
			jq('#slvekt').val("");
			jq('#sltanr').val("");
			jq('#sltar').val("");
			jq('#slpva').val("");
			jq('#slsats').val("");
			jq('#sltn').val("");
			jq('#slkdae').val("");
			jq('#slkdse').val("");
			jq('#slto').val("");
			jq('#slcref').val("");
			
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
  	  url: 'getSpecificRecord_sad004.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : varenr,
  		  	  kundnr : levenr },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#slalfa').val("");jq('#slalfa').val(data[i].slalfa);
  			jq("#slalfa").prop("readonly", true);
  			jq("#slalfa").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#slalfa").addClass("inputTextReadOnly");
  			
  			//rest of the gang
  			jq('#sltxt').val("");jq('#sltxt').val(data[i].sltxt);
  			jq('#r31').val("");jq('#r31').val(data[i].r31);
			jq('#sloppl').val("");jq('#sloppl').val(data[i].sloppl);
			jq('#sltanr').val("");jq('#sltanr').val(data[i].sltanr);
			jq('#sltn').val("");jq('#sltn').val(data[i].sltn);
			jq('#pref').val("");jq('#pref').val(data[i].pref);
			jq('#slvekt').val("");jq('#slvekt').val(data[i].slvekt);
			jq('#slpva').val("");jq('#slpva').val(data[i].slpva);
			jq('#slsats').val("");jq('#slsats').val(data[i].slsats);
			jq('#mf').val("");jq('#mf').val(data[i].mf);
			jq('#slkdae').val("");jq('#slkdae').val(data[i].slkdae);
			jq('#slkdse').val("");jq('#slkdse').val(data[i].slkdse);
			jq('#slto').val("");jq('#slto').val(data[i].slto);
			jq('#slcref').val("");jq('#slcref').val(data[i].slcref);
			
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].slalfa);
  			
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
  