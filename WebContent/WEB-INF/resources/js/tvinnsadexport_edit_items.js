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
  	  jq('#alinkInvoices').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
  	  jq('#alinkLogging').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  jq('#alinkArchive').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
    });
  	
  	//Auto control - autoförtullning
  	jq(function() { 
  		jq( "#submit" ).click(function( event ) {
  			jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
	  	});
  		
  		jq('#itemListControlButton').click(function() {
			jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
			window.location = 'tvinnsadexport_edit_items_autocontrol.do?svavd='+ jq('#avd').val() + '&svtdn=' + jq('#opd').val();
		});
  		
  		//=====================================
	  	//START Child window for general codes
	  	//=====================================
  		//LK eller Fylkeskod
  		jq('#svfylIdLink').click(function() {
	    	jq('#svfylIdLink').attr('target','_blank');
	    	window.open('tvinnsadexport_edit_items_childwindow_generalcodes.do?action=doInit&type=2&ctype=svfyl', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svfylIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svfylIdLink').click();
			}
	    });
	    jq('#svfylId2Link').click(function() {
	    	jq('#svfylId2Link').attr('target','_blank');
	    	window.open('tvinnsadexport_edit_items_childwindow_generalcodes.do?action=doInit&type=E&ctype=svfyl', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svfylId2Link').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svfyl2IdLink').click();
			}
	    });
	    //Varukod
	    jq('#svvntIdLink').click(function() {
	    	jq('#svvntIdLink').attr('target','_blank');
	    	window.open('tvinnsadexport_edit_items_childwindow_tolltariff.do?action=doInit&vkod=' + jq('#svvnt').val(), "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svvntIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svvntIdLink').click();
			}
	    });
	    //Kundens vareregister
	    jq('#kundensVaruregisterControlButton').click(function() {
	    	jq('#kundensVaruregisterControlButton').attr('target','_blank');
	    	window.open('tvinnsadexport_edit_items_childwindow_kundensvarereg.do?action=doInit&senId=' + jq('#senderId').val(), "codeKundVareRegWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#kundensVaruregisterControlButton').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#kundensVaruregisterControlButton').click();
			}
	    });
	    //Avg.koder B
	    jq('#svavtsIdLink').click(function() {
	    	jq('#svavtsIdLink').attr('target','_blank');
	    	window.open('tvinnsadexport_edit_items_childwindow_generalcodes.do?action=doInit&type=FF&ctype=svavts', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svavtsIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svavtsIdLink').click();
			}
	    });
  		//Landkod Oppr.
	    jq('#svlkIdLink').click(function() {
	    	jq('#svlkIdLink').attr('target','_blank');
	    	window.open('tvinnsadexport_edit_items_childwindow_generalcodes.do?action=doInit&type=2&ctype=svlk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svlkIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svlkIdLink').click();
			}
	    });
	    //Enhet
	    jq('#svehWc1Wc7IdLink').click(function() {
	    	jq('#svehWc1Wc7IdLink').attr('target','_blank');
	    	window.open('tvinnsadexport_edit_items_childwindow_generalcodes.do?action=doInit&type=A&ctype=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svehWc1Wc7IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svehWc1Wc7IdLink').click();
			}
	    });
	    //Ref
	    jq('#svcrefWe1We5IdLink').click(function() {
	    	jq('#svcrefWe1We5IdLink').attr('target','_blank');
	    	window.open('tvinnsadexport_edit_items_childwindow_generalcodes.do?action=doInit&type=C&ctype=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svcrefWe1We5IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svcrefWe1We5IdLink').click();
			}
	    });
	    //Ref 2
	    jq('#svcrefWe6We10IdLink').click(function() {
	    	jq('#svcrefWe6We10IdLink').attr('target','_blank');
	    	window.open('tvinnsadexport_edit_items_childwindow_generalcodes.do?action=doInit&type=C&ctype=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svcrefWe6We10IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svcrefWe6We10IdLink').click();
			}
	    });
	    
	    
	    //=====================================
	  	//END Child window for general codes
	  	//=====================================
  	});
  	
  	//----------------
	//onChange events
	//----------------
  	jq(function() { 
	    jq('#fylke').change(function() {
	    		jq('#svfyl').val(jq('#fylke').val());	
	    });
	    jq('#fylke').keypress(function(e) {
		    	if(e.which == 13) {
				e.preventDefault();
		    	}
	    });
	    //Sekvens
	    jq('#sekv').change(function() {
	    		jq('#svavts').val(jq('#sekv').val());	
	    });
	    jq('#sekv').keypress(function(e) {
		    	if(e.which == 13) {
				e.preventDefault();
		    	}
	    });
	    //Oppr.land
	    jq('#opprland').change(function() {
    			jq('#svlk').val(jq('#opprland').val());	
	    });
	    jq('#opprland').keypress(function(e) {
		    	if(e.which == 13) {
				e.preventDefault();
		    	}
	    });	
	    //Oppr.land (Fylke)
	    jq('#opprlandFylke').change(function() {
    			jq('#svfyl').val(jq('#opprlandFylke').val());	
	    });
	    jq('#opprlandFylke').keypress(function(e) {
		    	if(e.which == 13) {
				e.preventDefault();
		    	}
	    });	 
  	});
  	
  	
  	//-----------------------------------------------------------------------------
  	//jQuery CALCULATOR (related to jquery.calculator.js and jquery.calculator.css
  	//-----------------------------------------------------------------------------
  	jq(function() {
  		jq('#svbelt').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  		jq('#svvktb').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  		jq('#svvktn').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  		jq('#svntm').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  		
  	});
  	
  	
  	
  	/**
  	 * gets a specific item line
  	 * 
  	 * @param record
  	 */
  	
  	function getItemData(record) {
	  	var htmlValue = record.id;
	  	var rowCounter = jq("#"+record.id).attr("title");
	  	
	  	var applicationUserParam = jq('#applicationUser').val();
	  	var avdParam = jq('#avdItemList').val();
	  	var opdParam = jq('#opdItemList').val();
	  	//alert(htmlValue);
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getSpecificTopicItemChosenFromGuiElement_SadExport.do',
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
				//This line counter (lastSelectedItemLineNumber) is used in order to have a serial counter for the row lines. It is the only serial counter...
				//It is used ONLY for aspect/behavior purposes on GUI (scroll, bgColor, etc) in the specific row.
				jq('#lastSelectedItemLineNumber').val(""); jq('#lastSelectedItemLineNumber').val(rowCounter);
				//back-end data
				jq('#lineSvln').val(""); jq('#lineSvln').val(data[i].svln);
				jq('#lineSvli').val(""); jq('#lineSvli').val(data[i].svli);
				
				jq('#svvnt').val(""); jq('#svvnt').val(data[i].svvnt);
				jq('#svbelt').val(""); jq('#svbelt').val(data[i].svbelt);
				jq('#svvktb').val(""); jq('#svvktb').val(data[i].svvktb);
				jq('#svvktn').val(""); jq('#svvktn').val(data[i].svvktn);
				jq('#svntm').val(""); jq('#svntm').val(data[i].svntm);
				jq('#svavt').val(""); jq('#svavt').val(data[i].svavt);
				jq('#svavtp').val(""); jq('#svavtp').val(data[i].svavtp);
				jq('#svavts').val(""); jq('#svavts').val(data[i].svavts);
				jq('#svfyl').val(""); jq('#svfyl').val(data[i].svfyl);
				jq('#svlk').val(""); jq('#svlk').val(data[i].svlk);
				
				//Varetext (1)
				jq('#wd1').val(""); jq('#wd1').val(data[i].wd1); //svvt
				jq('#wc1').val(""); jq('#wc1').val(data[i].wc1); //sveh
				jq('#wb1').val(""); jq('#wb1').val(data[i].wb1); //svnt
				jq('#wa1').val(""); jq('#wa1').val(data[i].wa1); //svft
				//Varetext (2)
				jq('#wd2').val(""); jq('#wd2').val(data[i].wd2); //svvt
				jq('#wc2').val(""); jq('#wc2').val(data[i].wc2); //sveh
				jq('#wb2').val(""); jq('#wb2').val(data[i].wb2); //svnt
				jq('#wa2').val(""); jq('#wa2').val(data[i].wa2); //svft
				//Varetext (3)
				jq('#wd3').val(""); jq('#wd3').val(data[i].wd3); //svvt
				jq('#wc3').val(""); jq('#wc3').val(data[i].wc3); //sveh
				jq('#wb3').val(""); jq('#wb3').val(data[i].wb3); //svnt
				jq('#wa3').val(""); jq('#wa3').val(data[i].wa3); //svft
				//Varetext (4)
				jq('#wd4').val(""); jq('#wd4').val(data[i].wd4); //svvt
				jq('#wc4').val(""); jq('#wc4').val(data[i].wc4); //sveh
				jq('#wb4').val(""); jq('#wb4').val(data[i].wb4); //svnt
				jq('#wa4').val(""); jq('#wa4').val(data[i].wa4); //svft
				//Varetext (5)
				jq('#wd5').val(""); jq('#wd5').val(data[i].wd5); //svvt
				jq('#wc5').val(""); jq('#wc5').val(data[i].wc5); //sveh
				jq('#wb5').val(""); jq('#wb5').val(data[i].wb5); //svnt
				jq('#wa5').val(""); jq('#wa5').val(data[i].wa5); //svft
				//Varetext (6) (without wd6 since it does not exist)
				jq('#wc6').val(""); jq('#wc6').val(data[i].wc6); //sveh
				jq('#wb6').val(""); jq('#wb6').val(data[i].wb6); //svnt
				jq('#wa6').val(""); jq('#wa6').val(data[i].wa6); //svft
				//Varetext (7) (without wd6 since it does not exist)
				jq('#wc7').val(""); jq('#wc7').val(data[i].wc7); //sveh
				jq('#wb7').val(""); jq('#wb7').val(data[i].wb7); //svnt
				jq('#wa7').val(""); jq('#wa7').val(data[i].wa7); //svft

				
				//Tilleggsopplys (1)
				jq('#we1').val(""); jq('#we1').val(data[i].we1); //svcref
				jq('#wf1').val(""); jq('#wf1').val(data[i].wf1); //svtoa
				//Tilleggsopplys (2)
				jq('#we2').val(""); jq('#we2').val(data[i].we2); //svcref
				jq('#wf2').val(""); jq('#wf2').val(data[i].wf2); //svtoa
				//Tilleggsopplys (3)
				jq('#we3').val(""); jq('#we3').val(data[i].we3); //svcref
				jq('#wf3').val(""); jq('#wf3').val(data[i].wf3); //svtoa
				//Tilleggsopplys (4)
				jq('#we4').val(""); jq('#we4').val(data[i].we4); //svcref
				jq('#wf4').val(""); jq('#wf4').val(data[i].wf4); //svtoa
				//Tilleggsopplys (5)
				jq('#we5').val(""); jq('#we5').val(data[i].we5); //svcref
				jq('#wf5').val(""); jq('#wf5').val(data[i].wf5); //svtoa
				//Tilleggsopplys (6)
				jq('#we6').val(""); jq('#we6').val(data[i].we6); //svcref
				jq('#wf6').val(""); jq('#wf6').val(data[i].wf6); //svtoa
				//Tilleggsopplys (7)
				jq('#we7').val(""); jq('#we7').val(data[i].we7); //svcref
				jq('#wf7').val(""); jq('#wf7').val(data[i].wf7); //svtoa
				//Tilleggsopplys (8)
				jq('#we8').val(""); jq('#we8').val(data[i].we8); //svcref
				jq('#wf8').val(""); jq('#wf8').val(data[i].wf8); //svtoa
				//Tilleggsopplys (9)
				jq('#we9').val(""); jq('#we9').val(data[i].we9); //svcref
				jq('#wf9').val(""); jq('#wf9').val(data[i].wf9); //svtoa
				//Tilleggsopplys (10)
				jq('#we10').val(""); jq('#we10').val(data[i].we10); //svcref
				jq('#wf10').val(""); jq('#wf10').val(data[i].wf10); //svtoa

				
				//debug information on Fetch item
				jq('#debugPrintlnAjaxItemFetchInfo').text(data[i].debugPrintlnAjax);
				

			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
  	}
  	
  	
  	//taric varukod search
  	function searchTaricVarukod() {
		jq(function() {
			jq.getJSON('searchTolltariffVarukod_SadExport.do', {
				applicationUser : jq('#applicationUser').val(),
				taricVarukod : jq('#search_svvnt').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					var optionValue = data[i].tatanr;
					if(data[i].taalfa.length > 0){
						//this is done in order to take care of the Varebeskrivelse (wd1=Varetext) through a string split (when taricVarukodList.change)
						optionValue = optionValue + "@" + data[i].taalfa;
					}
					html += '<option value="' + optionValue + '">' + data[i].tatanr + ' ' + data[i].beskr1 +  '</option>';
					//html += '<option value="' + data[i].dktara02 + '">' + data[i].dktara02 + '</option>';
					//alert(optionValue);
				}
				//now that we have our options, give them to our select
				jq('#taricVarukodList').html(html);
				
			});
		});
	}
  	
  	//set the taric varukod in target input text field
  	jq(function() { 
	    jq('#taricVarukodList').change(function() {
		  //init field(s)
		  jq('#svvnt').val("");
		  //and populate (if applicable)
		  var key = jq('#taricVarukodList').val();
		  var separator = key.indexOf("@");
		  //alert("will alert here...");
		  if(separator>0){
			  jq('#svvnt').val(key.substring(0,separator));
			  //alert("wd1:" + key.substring(separator+1));
			  /*bug prone... if(""==jq('#lineNr').val()){
				  jq('#wd1').val(key.substring(separator+1));
			  }*/
			  jq('#wd1').val(key.substring(separator+1));
		  }else{
			  //alert("combo: separator == 0");
			  jq('#svvnt').val(key);
			  if(""==jq('#lineNr').val()){
				  jq('#wd1').val("");
			  }
		  }
		  			  
		});
	});
  	//----------------------------------
	//Events Varukod (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTaricVarukodSearch').click(function(){
    			jq("#search_svvnt").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_svvnt').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTaricVarukod);
			}			
    		});
	});

	//On Keypress (13)
	jq(function() { 
	    jq('#taricVarukodList').keypress(function() {
		    	if(e.which == 13) {
				//alert("hej till publiken");
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#svvnt').val(""); 
				//now populate (if applicable)
			    	var key = jq('#taricVarukodList').val();
			    	jq('#svvnt').val(key); 
		    	}
	    });
	    //prevent form-submit on enter key (to avoid unwanted keyboard)
	    //Note: this will catch the enter in every single element of the form, be aware of that!
	    jq('#tvinnSadExportEditTopicItemForm').on("keyup keypress", function(e) {
	    	  var code = e.keyCode || e.which; 
	    	  //alert(code);
	    	  if (code  == 13) {               
	    	    e.preventDefault();
	    	    return false;
	    	  }
	    	});
	    
	});
	
	//This blur function is used when the end-user does not uses the taricVarukodlist.change (jQuery) due to his/her knowledge of the varukod
	//In this case the Ajax function must be called when the user leaves the varukod field...
	jq(function() {	    
		jq('#svvnt').blur(function(e){
			jq.getJSON('searchTolltariffVarukod_SadExport.do', {
				applicationUser : jq('#applicationUser').val(),
				taricVarukod : jq('#svvnt').val(),
				selkbCountryCode : jq('#selkbCountryCode').val(),
				ajax : 'true'
			}, function(data) {
				var len = data.length;
				var taalfa = "";
				var svavt = ""; var svavts = ""; var svavtp = ""; 
				for ( var i = 0; i < len; i++) {
					if(data[i].taalfa.length > 0){
						//this is done in order to take care of the Varebeskrivelse (wd1=Varetext) 
						taalfa = data[i].taalfa;
						svavt = data[i].ffsvavt;
						svavtp = data[i].ffsvavtp;
						svavts = data[i].ffsvavts;
					}
				}
				//Use the value or change a previous value into blank
				if(taalfa != ""){
					if(jq('#wd1').val()==''){ jq('#wd1').val(taalfa); }
					
					//fiskeavgift-relaterade fält
					if(svavt != ""){
						if(jq('#svavt').val()==''){ jq('#svavt').val(svavt); }
					}
					if(svavtp != ""){
						if(jq('#svavtp').val()==''){ jq('#svavtp').val(svavtp); }
					}
					if(svavts != ""){
						if(jq('#svavts').val()==''){ jq('#svavts').val(svavts); }
					}
				}
			});		
		});
	});
  	
	//calculate a net weight from the gross weight and vice-versa
  	jq(function() { 
  		jq('#svvktb').blur(function() {
	    	  var grossNetFactor = 0.9;
	    	  var grossNetFactorHeader = jq('#grossNetFactor').val();
	    	  if(grossNetFactorHeader!=""){
	    		  grossNetFactor = grossNetFactorHeader.replace("," , ".");
	    	  }
	    	  //init field(s)
	    	  var grossWeight = jq('#svvktb').val();
	    	  grossWeight = grossWeight.replace(",",".");
	    	  var netWeight = jq('#svvktn').val();
		  if(""==netWeight){
			  var netWeightRaw = grossWeight * grossNetFactor;
			  netWeightRaw = netWeightRaw.toFixed(3);
			  netWeightRaw = netWeightRaw.replace(".",",");
			  jq('#svvktn').val(netWeightRaw);
		  }
		});
  		//Net to Gross
  		jq('#svvktn').blur(function() {
	    	  var grossNetFactor = 0.9;
	    	  var grossNetFactorHeader = jq('#grossNetFactor').val();
	    	  if(grossNetFactorHeader!=""){
	    		  grossNetFactor = grossNetFactorHeader.replace("," , ".");
	    	  }
		  //init field(s)
	    	  var netWeight = jq('#svvktn').val();
	    	  netWeight = netWeight.replace(",",".");
	    	  var grossWeight = jq('#svvktb').val();
		  if(""==grossWeight){
			  var grossWeightRaw = netWeight / grossNetFactor;
			  grossWeightRaw = grossWeightRaw.toFixed(3);
			  grossWeightRaw = grossWeightRaw.replace(".",",");
			  jq('#svvktb').val(grossWeightRaw);
		  }
		});

	});
  	jq(function() { 
	  	jq('#svbelt').blur(function() {
	  		//Only with CREATE NEW LINE
			if(jq('#lineSvln').val()==''){
		  		//(1) Calc. aprox Gross weight (proposal to the user) based on a factor (unitPrice/invoiceAmount) * totalGrossWeight
		  		var unitPrice = jq('#svbelt').val().replace(".",""); //must clean all US notation
				unitPrice = unitPrice.replace(",","."); //now we replace EU notation with US in order to use Math
				var invoiceAmount = jq('#fabl').val().replace(".","");
				invoiceAmount = invoiceAmount.replace(",",".");
				if(unitPrice != "" && invoiceAmount != ""){
					if(invoiceAmount>0 && jq('#svvktb').val()==''){
						var totalGross = jq('#totalGrossWeight').val().replace(".","");
						totalGross = totalGross.replace(",",".");
						var proposedGross = (unitPrice/invoiceAmount) * totalGross;
						//final value proposed
						proposedGross = proposedGross.toFixed(3);
						jq('#svvktb').val(proposedGross.replace(".",",")); //final EU-format (for decimals)
					}
				}
			}
	  	});
  	});	
  	
  	
  	//Grid aspects on behavior usually required when updating more than 10-rows. 
	//All this helps to high-light the next-row to update...after a newly row update has taken place.
	jq(document).ready(function(){
		var indx = 1;
		try{
			indx = parseInt(jq('#lastSelectedItemLineNumber').val());
			indx++;
		}catch(err){ 
			//alert("err:" + err.message)
		}
		var row = document.getElementById("tblItemLines").rows;
		//do the rest ONLY if lineNr is empty (since there could be validadtion errors and in this case the code should not execute further)
		var lineNr = jq('#lineNr').val();
		if (indx > 1 && ""==lineNr){
			//alert(indx);
			row[indx].scrollIntoView(false);
			var id = "#"+row[indx].id;
			//jq(id).css("background-color","#F0F0F0");
			jq(id).css("background-color","#A3D098");
			row[indx].focus();
			
		}else{
			//focus on
			//jq('#svfyl').focus(); //causing unexpected behavior on html rewrite after  a big item lines set is loaded
			jq('#startItemLineNr').focus();
		}
	});
	
	jq(document).ready(function() {
	      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
	      jq('#tblItemLinesAll').dataTable( {
	    	  "dom": '<"top">t<"bottom"flip><"clear">',
	    	  "scrollY":    "800px",
	  		  "scrollCollapse":  true,
	  		  "order": [[ 9, "desc" ]],
	  		  "lengthMenu": [ 75, 100, 300, 400, 900]
	  	  });
	      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
	      jq('#tblItemLines').dataTable( {
	    	  "dom": '<"top">t<"bottom"flip><"clear">',
	    	  "scrollY":    "180px",
	  		  "scrollCollapse":  true,
	  		  "order": [[ 10, "desc" ], [ 2, 'asc' ]],
	  		  "lengthMenu": [ 75, 100, 300, 400, 900]
	  	  });
	      
	      //event on input field for search
	      jq('input.tblItemLines_filter').on( 'keyup click', function () {
	      		filterGlobal();
	      });
	      //event on input field for search
	      jq('input.tblItemLinesAll_filter').on( 'keyup click', function () {
	      		filterGlobal();
	      });
	      
	      
	      //Initialize Dialog for KundensVareregister here
	  		jq(function() { 
	  		  jq("#dialogKundensVareregister").dialog({
	  			  autoOpen: false,
	  			  maxWidth:600,
	  	          maxHeight: 250,
	  	          width: 600,
	  	          height: 250,
	  			  modal: true
	  		  });
	  		});
	  	
	});
	
	//set item line into kundensvarereg.
	function updateKundensVarReg(record) {
	  	var htmlValue = record.id;
	  	var rowCounter = jq("#"+record.id).attr("title");
	  	
	  	var applicationUserParam = jq('#applicationUser').val();
	  	var avdParam = jq('#avdItemList').val();
	  	var opdParam = jq('#opdItemList').val();
	  	//alert(htmlValue);
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getSpecificTopicItemChosenFromGuiElement_SadExport.do',
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
				//back-end data
				jq('#slknr').val(jq('#senderId').val());
				jq('#sloppl').val(data[i].svfyl);
				jq('#sltanr').val(data[i].svvnt);
				jq('#slvekt').val(data[i].svvktb);
				jq('#description_sltxt').text(data[i].wd1);
				//hidden fields
				jq('#sltxt').val(data[i].wd1);
				/*
				jq('#sltar').val(data[i].?);
				jq('#slpva').val(data[i].?);
				jq('#slsats').val(data[i].?);
				jq('#sltn').val(data[i].?);
				*/
				
				//Start dialog
				//deal with buttons and attributes for this modal window
				jq('#dialogKundensVareregister').dialog( "option", "title", "LAGRE i kundens vareregister" );
				jq('#dialogKundensVareregister').dialog({
					 buttons: [ 
			            {
						 id: "dialogSaveTU",	
						 text: "Spara",
						 click: function(){
			            			jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
			 		 		 		jq('#updateKundensVareregisterForm').submit();
						 		}
					 	 },
			 	 		{
					 	 id: "dialogCancelTU",
					 	 text: "Avbryt", 
						 click: function(){
					 		 		jq( this ).dialog( "close" ); 
					 	 		} 
			 	 		 } ] 
				  });
				  //init values
				  jq("#dialogSaveTU").button("option", "disabled", true);
				  //open
				  jq('#dialogKundensVareregister').dialog('open');
				

			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
  	}
	
	//Events on dialog fields
    jq(function() {
  	  jq("#slalfa").blur(function() {
  		  if(jq("#slalfa").val()!='' && jq("#slknr").val()!=''){
  			  jq("#dialogSaveTU").button("option", "disabled", false);
  		  }else{
  			  jq("#dialogSaveTU").button("option", "disabled", true);
  		  }
  	  });
  	  jq("#slknr").blur(function() {
		  if(jq("#slknr").val()!='' && jq("#slalfa").val()!=''){
			  jq("#dialogSaveTU").button("option", "disabled", false);
		  }else{
			  jq("#dialogSaveTU").button("option", "disabled", true);
		  }
	  });
  	  
    });
	
	
	

  	
	