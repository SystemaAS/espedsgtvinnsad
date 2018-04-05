  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
  	  jq("#agdtfNO").datepicker({
  		dateFormat: 'ddmmy' 
  	  });
  	  jq("#agdttNO").datepicker({ 
  		dateFormat: 'ddmmy' 
	  });
  	  
  });
  

  jq(function() {
	
	//Clean values for createing new record
	jq('#newRecordButton').click(function() {
			jq('#agtanr').val("");
			jq("#agtanr").prop("readonly", false);
			jq("#agtanr").removeClass("inputTextReadOnly");
			jq("#agtanr").addClass("inputTextMediumBlueMandatoryField");
			
			//rest of the gang
			jq('#taalfa').val("");
			jq('#agakd').val("FF");
			jq('#agskv').val("");			
			jq("#agskv").prop("readonly", false);
			jq("#agskv").removeClass("inputTextReadOnly");
			jq("#agskv").addClass("inputTextMediumBlueMandatoryField");						
			jq('#agdtfNO').val("");
			jq('#agdttNO').val(""); 
			jq('#agkd').val(""); 
			jq('#agpp').val(""); 
			jq('#agsats').val("");
  			jq('#agaktk').val(""); 
  			
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
	var agtanr = record[0];
	var agakd = record[1];
	var agskv = record[2];

	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_sad015.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		      agtanr : agtanr,
  		  	  agakd : agakd, 
  		      agskv : agskv },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#agtanr').val("");jq('#agtanr').val(data[i].agtanr);
  			jq("#agtanr").prop("readonly", true);
  			jq("#agtanr").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#agtanr").addClass("inputTextReadOnly");
  			
  			//rest of the gang
  			jq('#taalfa').val("");jq('#taalfa').val(data[i].taalfa);
  			jq('#agakd').val("");jq('#agakd').val(data[i].agakd);
  			jq('#agskv').val("");jq('#agskv').val(data[i].agskv);
  			jq("#agskv").prop("readonly", true);
  			jq("#agskv").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#agskv").addClass("inputTextReadOnly");
  			jq('#agdtfNO').val("");jq('#agdtfNO').val(data[i].agdtfNO);
  			jq('#agdttNO').val("");jq('#agdttNO').val(data[i].agdttNO);
  			jq('#agkd').val("");jq('#agkd').val(data[i].agkd);
  			jq('#agpp').val("");jq('#agpp').val(data[i].agpp);
  			jq('#agsats').val("");jq('#agsats').val(data[i].agsats);
  			jq('#agaktk').val("");jq('#agaktk').val(data[i].agaktk);
  			
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].agtanr);
  			
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
  
  
  
  
  
  