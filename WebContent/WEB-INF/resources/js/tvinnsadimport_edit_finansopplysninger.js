	//this variable is a global jQuery var instead of using "$" all the time. Very handy
	var jq = jQuery.noConflict();
	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  	
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
	
	jq(function() {
		jq("#sfdt").datepicker({ 
			dateFormat: 'ddmmy'  
		});
    });
	
	jq(function() {
  	  	jq('#importInvoicesButton').click(function() {
  	  		window.open('tvinnsadimport_edit_childwindow_external_invoices.do?avd=' + jq("#avd").val() + "&opd=" + jq("#opd").val(), 'importInvoicesWin','top=120px,left=100px,height=600px,width=800px,scrollbars=no,status=no,location=no');
  	  	});
  	  	
  	  	//=====================================
	  	//START Child window for general codes
	  	//=====================================
  		//Valutakod
	    jq('#sfvk28IdLink').click(function() {
	    	jq('#sfvk28IdLink').attr('target','_blank');
	    	window.open('tvinnsadimport_edit_childwindow_generalcodes.do?action=doInit&type=V&ctype=sfvk28', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sfvk28IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sfvk28IdLink').click();
			}
	    });
	    //=====================================
	  	//END Child window for general codes
	  	//=====================================
  	});
	
	//-----------------------------------------------------------------------------
  	//jQuery CALCULATOR (related to jquery.calculator.js and jquery.calculator.css
  	//-----------------------------------------------------------------------------
  	jq(function() {
  		jq('#sfbl28').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  	});
  	
	//----------------
	//onChange events
	//----------------
	jq(function() { 
		jq('#currencySearch').change(function() {
			jq('#sfvk28').val(jq('#currencySearch').val());	
		});
	});
	  
	  	
  	/**
  	 * gets a specific item line
  	 * 
  	 * @param record
  	 */
  	
  	function getFinansOpplysningerItemData(record) {
	  	var htmlValue = record.id;
	  	var applicationUserParam = jq('#applicationUser').val();
	  	var avdParam = jq('#avdItemList').val();
	  	var opdParam = jq('#opdItemList').val();
	  	//alert(htmlValue);
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getSpecificTopicFinansOpplysningerItemChosenFromGuiElement_SadImport.do',
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
				//alert(data[i].sviv_vasl);
				//this lineId must be populated in order to know whether this is an UPDATE (and not a new line = CREATE)
				jq('#lineId').val(""); jq('#lineId').val(data[i].sftxt);
				jq('#sftxt').val(""); jq('#sftxt').val(data[i].sftxt);
				jq("#sftxt").prop("readonly",true);
				
				jq('#sfdt').val(""); jq('#sfdt').val(data[i].sfdt);
				jq('#sfbl28').val(""); jq('#sfbl28').val(data[i].sfbl28);
				jq('#sfvk28').val(""); jq('#sfvk28').val(data[i].sfvk28);
				jq('#sfkr28').val(""); jq('#sfkr28').val(data[i].sfkr28);
				jq('#factor').val(""); jq('#factor').val(data[i].sfom28);
				
				//debug information on Fetch item
				jq('#debugPrintlnAjaxItemFetchInfo').text(data[i].debugPrintlnAjax);
				
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
  	}
  	
  	//============================
	//START - Currency AJAX fetch
	//============================
	jq(function() { 
	    jq('#sfvk28').change(function() {
	    		//In Norway we must use the current day (today) as currency date, 
    			//therefore we send = null. The AjaxController will take care of the rest
    			var currencyDate = null; 
	    		getCurrencyData(currencyDate);
	    });
	});
	//fetch currency rate in date event (if applicable)
	jq(function() { 
	    jq('#sfdt').blur(function() {
	    		var currencyRate = jq('#sfkr28').val();
	    		if(currencyRate==null || currencyRate==""){
	    			//In Norway we must use the current day (today) as currency date, 
	    			//therefore we send = null. The AjaxController will take care of the rest
	    			var currencyDate = null;
	    			getCurrencyData(currencyDate);
	    		}
	    });
	});
	//private function to AJAX-Controller
	function getCurrencyData(currencyDate) {
		jq.ajax({
			type: 'GET',
			url: 'getCurrencyRate_SadImport.do',
			data: { 	applicationUser : jq('#applicationUser').val(),
					currencyCode : jq('#sfvk28').val(),
					isoDate : currencyDate} ,
			dataType: 'json',
			success: function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#sfkr28').val(data[i].kvakrs);
					jq('#factor').val(data[i].kvaomr);
				}
			}
		});
	}
	//============================
	//END - Currency AJAX fetch
	//============================

	//-------------------
    //Datatables jquery
    //-------------------
    //private function
    function filterGlobal () {
      jq('#tblInvoices').dataTable().search(
      	jq('#tblInvoices_filter').val()
      ).draw();
    }

    jq(document).ready(function() {
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
      jq('#tblInvoices').dataTable( {
    	  "dom": '<"top">t<"bottom"f><"clear">',
  		  "scrollY":    "150px",
  		  "scrollCollapse":  true,
  		  "lengthMenu": [ 25, 50]
  	  });
      //event on input field for search
      jq('input.tblInvoices_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
    });

  	
  	

	
	
	
	