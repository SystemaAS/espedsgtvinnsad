	//this variable is a global jQuery var instead of using "$" all the time. Very handy
	var jq = jQuery.noConflict();
	
  	//Overlay on tab (to mark visually a delay...)
    jq(function() {
      jq('#alinkTopicList').click(function() { 
    	  setBlockUI();
	  });	
  	  jq('#alinkHeader').click(function() { 
  		setBlockUI();
  	  });
  	  jq('#alinkOmberegning').click(function() { 
  		setBlockUI();
	  });
  	  jq('#alinkItemLines').click(function() { 
  		setBlockUI();
  	  });
  	  jq('#alinkLogging').click(function() { 
  		setBlockUI();
	  });
  	  jq('#alinkArchive').click(function() { 
  		setBlockUI();
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
  		  "tabIndex": -1,
  		  "lengthMenu": [ 25, 50]
  	  });
      //event on input field for search
      jq('input.tblInvoices_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
      
      jq('#sftxt').focus();
      
  	
    });

    
  //-------------------------------------------
	  //START Model dialog: "File upload"
	  //-------------------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogUploadArchiveDocument").dialog({
			  autoOpen: false,
			  maxWidth:400,
	          maxHeight: 300,
	          width: 400,
	          height: 300,
			  modal: true
		  });
	  });
	  //----------------------------
	  //Present dialog box onClick 
	  //----------------------------
	  jq(function() {
		  jq("#uploadFileImg").click(function() {
			  presentUploadFileDialog();
		  });
		  
	  });
	  function presentUploadFileDialog(){
		//setters (add more if needed)
		  jq('#dialogUploadArchiveDocument').dialog( "option", "title", "Upload dokument" );
		  //deal with buttons for this modal window
		  jq('#dialogUploadArchiveDocument').dialog({
			 buttons: [ 
			     /* N/A (look at file-change event instead     
	            {
	             	
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		jq('#uploadFileForm').submit();
				 		}
			 	 },*/
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Avbryt", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		//jq("#dialogSaveTU").button("option", "disabled", true);
					 		//jq("#wstype").val("");
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  //jq("#dialogSaveTU").button("option", "disabled", false);
		  //open now
		  jq('#dialogUploadArchiveDocument').dialog('open');
	  }
	  
	//----------------
	// UPLOAD FILE 
	//----------------
	  function myFileUploadDragEnter(e){
		  jq("#fileUpload").addClass( "isa_blue" );
	  }
  	  function myFileUploadDragLeave(e){
		  jq("#fileUpload").removeClass( "isa_blue" );
	  }
	  
	  //Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
	  jq(function() {
		//Triggers drag-and-drop
		  jq('#fileUpload').hover(function(){
			  jq("#fileUpload").removeClass( "isa_success" );
			  jq("#fileUpload").removeClass( "isa_error" );
		  }); 
		  
		  jq("#fileUpload").change(function() {
			  jq("#fileUpload").removeClass( "isa_blue" );
			  uploadFile();
		  });
		  
	  });
	  //Upload file
	  function uploadFile(){
			//grab all form data  
			  var form = new FormData(document.getElementById('uploadFileForm'));
			  setBlockUI();
			  
			  jq.ajax({
			  	  type: 'POST',
			  	  url: 'uploadFileToArchiveInvoice.do',
			  	  data: form,  
			  	  dataType: 'text',
			  	  cache: false,
			  	  processData: false,
			  	  contentType: false,
		  		  success: function(data) {
				  	  var len = data.length;
			  		  if(len>0){
			  			jq("#fileUpload").val("");
					  	//Check for errors or successfully processed
					  	var exists = data.indexOf("ERROR");
					  	if(exists>0){
					  		//ERROR on back-end
					  		jq("#fileUpload").addClass( "isa_error" );
					  		jq("#fileUpload").removeClass( "isa_success" );
					  	}else{
					  		//OK
					  		jq("#fileUpload").addClass( "isa_success" );
					  		jq("#fileUpload").removeClass( "isa_error" );
					  	}
					  	//response to end user 
					  	alert(data);
					  	if(data.indexOf('[OK') == 0) {
						  	var trip = '';
						  	var avd = jq("#avd").val();
						  	var opd = jq("#opd").val();
						  	var sign = jq("#sign").val();
						  	//reload
						  	window.location = "tvinnsadimport_edit_finansopplysninger.do?action=doFetch&avd=" + avd + "&opd=" + opd + "&sign=" +  sign;
					  	}
					  	//unblock
					  	jq.unblockUI();
			  		  }
			  	  }, 
			  	  error: function() {
			  		  jq.unblockUI();
			  		  alert('Error loading ...');
			  		  jq("#fileUpload").val("");
			  		  //cosmetics
			  		  jq("#fileUpload").addClass( "isa_error" );
			  		  jq("#fileUpload").removeClass( "isa_success" );
				  }
			  });
			    
			  
		  }
	  
	  //-------------------------------------------
	  //END UPLOAD --> Model dialog: "File upload"
	  //-------------------------------------------
  	
  	

	
	
	
	