	//this variable is a global jQuery var instead of using "$" all the time. Very handy
	var jq = jQuery.noConflict();
	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
    
	jq(function() {
		jq("#frtdt").datepicker({ 
			dateFormat: 'yymmdd'  
		});
    });
	
	//Overlay on tab (to mark visually a delay...)
    jq(function() {
      jq('#alinkTopicList').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });	    	
  	  jq('#alinkHeader').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
  	  jq('#alinkOmberegning').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  jq('#alinkInvoices').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  jq('#alinkItemLines').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
  	  jq('#alinkLogging').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  jq('#alinkArchive').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
    });
	 
	//------------------------
	//Get list for Notisblock
	//------------------------
	function getNotisblockItemData(record) {
	  	var htmlValue = record.id;
	  	var applicationUserParam = jq('#applicationUser').val();
	  	var avdParam = jq('#frtavd').val();
	  	var opdParam = jq('#frtopd').val();
	  	//alert(htmlValue);
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getSpecificNotisblockItemChosenFromGuiElement.do',
	  	  data: { applicationUser : applicationUserParam, 
	  		  	  elementValue : htmlValue, 
	  		  	  avd : avdParam, 
	  		  	  opd : opdParam },
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				//this frtli(line-id) must be populated in order to know whether this is an UPDATE (and not a new line = CREATE)
				jq('#frtli').val(""); jq('#frtli').val(data[i].frtli);
				jq('#frtdt').val(""); jq('#frtdt').val(data[i].frtdt);
				jq('#frtkod').val(""); jq('#frtkod').val(data[i].frtkod);
				jq('#frttxt').val(""); jq('#frttxt').val(data[i].frttxt);
				//jq("#sftxt").prop("readonly",true);
				
				
				//debug information on Fetch item
				//TODO jq('#debugPrintlnAjaxItemFetchInfo').text(data[i].debugPrintlnAjax);
				
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
	}
	  
	