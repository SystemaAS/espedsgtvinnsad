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
		jq('#klbkod').val("");
			jq("#klbkod").prop("readonly", false);
			jq("#klbkod").removeClass("inputTextReadOnly");
			jq("#klbkod").addClass("inputTextMediumBlueUPPERCASEMandatoryField");
			
			//rest of the gang
			jq('#klbnav').val("");
			jq('#klbkt').val("");
			jq('#klbfok').val("");
			jq('#klbprm').val("");
			jq('#klbfrk').val("");
			//virtual fields
			jq('#klbxxx_avs').val("");
			jq('#klbxxx_mot').val("");
			jq('#klbxxx_andrek').val("");
			//for update
			jq('#updateId').val("");
	});
}); 
	
  //Varekod
  //-----------------------
  //GET specific db-record
  //-----------------------
  function getRecord(record){
	var id = record.id;
  	var applicationUserParam = jq('#applicationUser').val();
  	id = id.replace("recordUpdate_", "");
	  	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_sad012r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : id },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#klbkod').val("");jq('#klbkod').val(data[i].klbkod);
  			jq("#klbkod").prop("readonly", true);
  			jq("#klbkod").removeClass("inputTextMediumBlueUPPERCASEMandatoryField");
  			jq("#klbkod").addClass("inputTextReadOnly");
  			
  			//rest of the gang
  			jq('#klbnav').val("");jq('#klbnav').val(data[i].klbnav);
  			jq('#klbkt').val("");jq('#klbkt').val(data[i].klbkt);
  			jq('#klbfok').val("");jq('#klbfok').val(data[i].klbfok);
  			jq('#klbprm').val("");jq('#klbprm').val(data[i].klbprm);
  			jq('#klbfrk').val("");jq('#klbfrk').val(data[i].klbfrk);
  			//virtual fields
  			jq('#klbxxx_avs').val("");
  			if(data[i].klbxxx_avs!=' '){
  				jq('#klbxxx_avs').val(data[i].klbxxx_avs);
  			}
  			jq('#klbxxx_mot').val("");
  			if(data[i].klbxxx_mot!=' '){
  				jq('#klbxxx_mot').val(data[i].klbxxx_mot);
  			}
  			jq('#klbxxx_andrek').val("");
  			if(data[i].klbxxx_andrek!=' '){
  				jq('#klbxxx_andrek').val(data[i].klbxxx_andrek);
  			}
  			
  			
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].klbkod);
  			
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
  