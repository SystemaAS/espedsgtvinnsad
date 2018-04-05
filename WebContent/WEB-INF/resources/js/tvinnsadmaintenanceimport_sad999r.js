  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
  	  jq("#sddtfNO").datepicker({
  		  dateFormat: 'ddmmy' 
  	  });
  	  jq("#sddttNO").datepicker({ 
  		  dateFormat: 'ddmmy' 
	  });
  	  
  	  jq('#sdkdaeIdLink').click(function() {
  		  jq('#sdkdaeIdLink').attr('target','_blank');
  		  window.open('tvinnsadmaintenanceimport_sad002_kodts8_childwindow_avgiftcodes.do?action=doFind&sdkdae=' + jq('#sdkdae').val() + '&ctype=sdkdae', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	  });
  });
  

  jq(function() {
	
	//Clean values for createing new record
	jq('#newRecordButton').click(function() {
			jq('#sdtnrf').val("");
			jq("#sdtnrf").prop("readonly", false);
			jq("#sdtnrf").removeClass("inputTextReadOnly");
			jq("#sdtnrf").addClass("inputTextMediumBlueMandatoryField");
			
			//rest of the gang
			jq('#taalfa').val("");jq('#taalfaOrig').val("");jq('#sddtfOrig').val("");jq('#sddttOrig').val("");
			jq('#sdkdae').val(""); jq('#sdkdse').val(""); jq('#sddtfNO').val(""); jq('#sddttNO').val("");
  			jq('#sdblse').val(""); jq('#sdaktk').val("");
  			
			//for update
			jq('#updateId').val("");
	});
	
  }); 
	
  //Varekod
  //-----------------------
  //GET specific db-record
  //-----------------------
  
  function getRecord(record){
	var rawId = record.id;
  	var applicationUserParam = jq('#applicationUser').val();
  	rawId = rawId.replace("recordUpdate_", "");
  	var record = rawId.split('_');
	var sdtnrf = record[0];
	var taalfa = record[1];
	var fdate = record[2];
	var tdate = record[3];
	
	    	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_sad999r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : sdtnrf,
  		  	  alfa : taalfa,
  		  	  fdate : fdate, 
  		  	  tdate : tdate },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#sdtnrf').val("");jq('#sdtnrf').val(data[i].sdtnrf);
  			jq("#sdtnrf").prop("readonly", true);
  			jq("#sdtnrf").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#sdtnrf").addClass("inputTextReadOnly");
  			
  			//rest of the gang
  			jq('#taalfa').val("");jq('#taalfa').val(data[i].taalfa);
  			jq('#taalfaOrig').val("");jq('#taalfaOrig').val(data[i].taalfa);
  			jq('#sddtfOrig').val("");jq('#sddtfOrig').val(data[i].sddtf);
  			jq('#sddttOrig').val("");jq('#sddttOrig').val(data[i].sddtt);
  			
  			jq('#sdkdae').val("");jq('#sdkdae').val(data[i].sdkdae);
  			jq('#sdkdse').val("");jq('#sdkdse').val(data[i].sdkdse);
  			jq('#sddtfNO').val("");jq('#sddtfNO').val(data[i].sddtfNO);
  			jq('#sddttNO').val("");jq('#sddttNO').val(data[i].sddttNO);
  			jq('#sdblse').val("");jq('#sdblse').val(data[i].sdblse);
  			jq('#sdaktk').val("");jq('#sdaktk').val(data[i].sdaktk);
  			
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].sdtnrf);
  			
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
    	  "scrollY": "180px",
    	  "scrollCollapse":  false,
    	  "columnDefs": [{ "type": "num", "targets": 0 }],
    	  "lengthMenu": [ 75, 100]
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  