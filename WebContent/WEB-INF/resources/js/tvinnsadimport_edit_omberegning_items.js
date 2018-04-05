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
  	  jq('#alinkItemLines').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  jq('#alinkLogging').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  jq('#alinkArchive').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  jq('#alinkOmberegningSubTab').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
    });
	
	
	
  	//General functions
  	jq(function() {
  		jq( "#submit" ).click(function( event ) {
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
  	  	});
  		
  		jq('#itemListControlButton').click(function() {
			jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
			window.location = 'tvinnsadimport_edit_items_autocontrol.do?svavd='+ jq('#avd').val() + '&svtdn=' + jq('#opd').val();
		});
  		jq('#itemListAngreOmbButton').click(function() {
			jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
			window.location = 'tvinnsadimport_edit_omberegning_items.do?action=doReverse&user=' + jq('#applicationUser').val() + '&avd='+ jq('#avd').val() + '&opd=' + jq('#opd').val() + '&o2_sist=' + jq('#o2_sist').val() + '&o2_sidt=' + jq('#o2_sidt').val()+ '&o2_simf=' + jq('#o2_simf').val();
		});
  		
  		//=====================================
	  	//START Child window for general codes
	  	//=====================================
  		//Avs.utf land
	    jq('#svlkIdLink').click(function() {
	    	jq('#svlkIdLink').attr('target','_blank');
	    	window.open('tvinnsadimport_edit_items_childwindow_generalcodes.do?action=doInit&type=2&ctype=svlk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svlkIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svlkIdLink').click();
			}
	    });
	    //Varukod
	    jq('#svvntIdLink').click(function() {
	    	jq('#svvntIdLink').attr('target','_blank');
	    	window.open('tvinnsadimport_edit_items_childwindow_tolltariff.do?action=doInit&vkod=' + jq('#svvnt').val(), "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svvntIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svvntIdLink').click();
			}
	    });
	    //Kundens vareregister
	    jq('#kundensVaruregisterControlButton').click(function() {
	    	jq('#kundensVaruregisterControlButton').attr('target','_blank');
	    	window.open('tvinnsadimport_edit_items_childwindow_kundensvarereg.do?action=doInit&recId=' + jq('#receiverId').val(), "codeKundVareRegWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#kundensVaruregisterControlButton').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#kundensVaruregisterControlButton').click();
			}
	    });
	    //Enhet
	    jq('#svehWc1Wc7IdLink').click(function() {
	    	jq('#svehWc1Wc7IdLink').attr('target','_blank');
	    	window.open('tvinnsadimport_edit_items_childwindow_generalcodes.do?action=doInit&type=A&ctype=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svehWc1Wc7IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svehWc1Wc7IdLink').click();
			}
	    });
	    //Avgift kode
	    jq('#svkdaaeWg1Wg8IdLink').click(function() {
	    	jq('#svkdaaeWg1Wg8IdLink').attr('target','_blank');
	    	window.open('tvinnsadimport_edit_items_childwindow_generalcodes.do?action=doInit&type=8&ctype=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svkdaaeWg1Wg8IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svkdaaeWg1Wg8IdLink').click();
			}
	    });
	    //Avgift sekvens
	    jq('#svkdsaeWh1Wh8IdLink').click(function() {
	    	jq('#svkdsaeWh1Wh8IdLink').attr('target','_blank');
	    	window.open('tvinnsadimport_edit_items_childwindow_generalcodes.do?action=doInit&type=8B&ctype=svkdsae', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svkdsaeWh1Wh8IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svkdsaeWh1Wh8IdLink').click();
			}
	    });
	    //Ref 01
	    jq('#svcrefWe1We5IdLink').click(function() {
	    	jq('#svcrefWe1We5IdLink').attr('target','_blank');
	    	window.open('tvinnsadimport_edit_items_childwindow_generalcodes.do?action=doInit&type=B&ctype=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svcrefWe1We5IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svcrefWe1We5IdLink').click();
			}
	    });
	    //Ref 02
	    jq('#svcrefWe6We10IdLink').click(function() {
	    	jq('#svcrefWe6We10IdLink').attr('target','_blank');
	    	window.open('tvinnsadimport_edit_items_childwindow_generalcodes.do?action=doInit&type=B&ctype=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
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
	    jq('#opprland').change(function() {
    		jq('#svlk').val(jq('#opprland').val());	
	    });
	});
	
	
	//Avgift sequence AJAX fetch
	jq(function() { 
	    jq('#svkdaae').change(function() {
	    		//alert('Hej');
	    		//These parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('getAvgiftSequence_SadImport.do', {
				applicationUser : jq('#applicationUser').val(),
				code : jq('#svkdaae').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Velg-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].zskv + '">' + data[i].zskv + '</option>';
				}
				jq('#svkdsae').html(html);
			});
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
	  	  url: 'getSpecificTopicItemChosenFromGuiElement_SadImport.do',
	  	  data: { applicationUser : applicationUserParam, 
	  		  	  elementValue : htmlValue, 
	  		  	  avd : avdParam, 
	  		  	  opd : opdParam },
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		//Avg.Button must be shown ONLY with new records and not with updates  
	  		//jq('#avgCalculation').hide(); OBSOLETE ? (CB)
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				//This line counter (lastSelectedItemLineNumber) is used in order to have a serial counter for the row lines. It is the only serial counter...
				//It is used ONLY for aspect/behavior purposes on GUI (scroll, bgColor, etc) in the specific row.
				jq('#lastSelectedItemLineNumber').val(""); jq('#lastSelectedItemLineNumber').val(rowCounter);
				//back-end data
				jq('#lineSvln').val(""); jq('#lineSvln').val(data[i].svln);
				jq('#lineSvli').val(""); jq('#lineSvli').val(data[i].svli);
				//alert("X:" + data[i].svvf);
				jq('#svvf').val(""); jq('#svvf').val(data[i].svvf);
				jq('#svlk').val(""); jq('#svlk').val(data[i].svlk);
				jq('#svvnt').val(""); jq('#svvnt').val(data[i].svvnt);
				jq('#svtn').val(""); jq('#svtn').val(data[i].svtn);
				jq('#svpre').val(""); jq('#svpre').val(data[i].svpre);
				jq('#svbelt').val(""); jq('#svbelt').val(data[i].svbelt);
				jq('#svvktb').val(""); jq('#svvktb').val(data[i].svvktb);
				jq('#svvktn').val(""); jq('#svvktn').val(data[i].svvktn);
				jq('#svntm').val(""); jq('#svntm').val(data[i].svntm);
				jq('#svpva').val(""); jq('#svpva').val(data[i].svpva);
				jq('#svas').val(""); jq('#svas').val(data[i].svas);
				jq('#svmfr').val(""); jq('#svmfr').val(data[i].svmfr);
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
				
				//Avgifter(1)
				jq('#wg1').val(""); jq('#wg1').val(data[i].wg1); //svkdaae
				jq('#wh1').val(""); jq('#wh1').val(data[i].wh1); //svkdsae
				jq('#wj1').val(""); jq('#wj1').val(data[i].wj1); //svblsae
				jq('#wk1').val(""); jq('#wk1').val(data[i].wk1); //svblgae
				jq('#wi1').val(""); jq('#wi1').val(data[i].wi1); //svblae
				//Avgifter(2)
				jq('#wg2').val(""); jq('#wg2').val(data[i].wg2); //svkdaae
				jq('#wh2').val(""); jq('#wh2').val(data[i].wh2); //svkdsae
				jq('#wj2').val(""); jq('#wj2').val(data[i].wj2); //svblsae
				jq('#wk2').val(""); jq('#wk2').val(data[i].wk2); //svblgae
				jq('#wi2').val(""); jq('#wi2').val(data[i].wi2); //svblae
				//Avgifter(3)
				jq('#wg3').val(""); jq('#wg3').val(data[i].wg3); //svkdaae
				jq('#wh3').val(""); jq('#wh3').val(data[i].wh3); //svkdsae
				jq('#wj3').val(""); jq('#wj3').val(data[i].wj3); //svblsae
				jq('#wk3').val(""); jq('#wk3').val(data[i].wk3); //svblgae
				jq('#wi3').val(""); jq('#wi3').val(data[i].wi3); //svblae
				//Avgifter(4)
				jq('#wg4').val(""); jq('#wg4').val(data[i].wg4); //svkdaae
				jq('#wh4').val(""); jq('#wh4').val(data[i].wh4); //svkdsae
				jq('#wj4').val(""); jq('#wj4').val(data[i].wj4); //svblsae
				jq('#wk4').val(""); jq('#wk4').val(data[i].wk4); //svblgae
				jq('#wi4').val(""); jq('#wi4').val(data[i].wi4); //svblae
				//Avgifter(5)
				jq('#wg5').val(""); jq('#wg5').val(data[i].wg5); //svkdaae
				jq('#wh5').val(""); jq('#wh5').val(data[i].wh5); //svkdsae
				jq('#wj5').val(""); jq('#wj5').val(data[i].wj5); //svblsae
				jq('#wk5').val(""); jq('#wk5').val(data[i].wk5); //svblgae
				jq('#wi5').val(""); jq('#wi5').val(data[i].wi5); //svblae
				//Avgifter(6)
				jq('#wg6').val(""); jq('#wg6').val(data[i].wg6); //svkdaae
				jq('#wh6').val(""); jq('#wh6').val(data[i].wh6); //svkdsae
				jq('#wj6').val(""); jq('#wj6').val(data[i].wj6); //svblsae
				jq('#wk6').val(""); jq('#wk6').val(data[i].wk6); //svblgae
				jq('#wi6').val(""); jq('#wi6').val(data[i].wi6); //svblae
				//Avgifter(7)
				jq('#wg7').val(""); jq('#wg7').val(data[i].wg7); //svkdaae
				jq('#wh7').val(""); jq('#wh7').val(data[i].wh7); //svkdsae
				jq('#wj7').val(""); jq('#wj7').val(data[i].wj7); //svblsae
				jq('#wk7').val(""); jq('#wk7').val(data[i].wk7); //svblgae
				jq('#wi7').val(""); jq('#wi7').val(data[i].wi7); //svblae
				//Avgifter(8)
				jq('#wg8').val(""); jq('#wg8').val(data[i].wg8); //svkdaae
				jq('#wh8').val(""); jq('#wh8').val(data[i].wh8); //svkdsae
				jq('#wj8').val(""); jq('#wj8').val(data[i].wj8); //svblsae
				jq('#wk8').val(""); jq('#wk8').val(data[i].wk8); //svblgae
				jq('#wi8').val(""); jq('#wi8').val(data[i].wi8); //svblae
				
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
			jq.getJSON('searchTolltariffVarukod_SadImport.do', {
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
		  if(separator>0){
			  jq('#svvnt').val(key.substring(0,separator));
			  /*bug prone... if(""==jq('#lineNr').val()){
				  jq('#wd1').val(key.substring(separator+1));
			  }*/
			  jq('#wd1').val(key.substring(separator+1));
		  }else{
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
	    jq('#taricVarukodList').keypress(function(e) {
		    	if(e.which == 13) {
				//alert("hej till publiken");
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#search_svvnt').val(""); 
				//now populate (if applicable)
			    	var key = jq('#taricVarukodList').val();
			    	jq('#search_svvnt').val(key); 
		    	}
	    });
	    
	    //prevent form-submit on enter key (to avoid unwanted keyboard)
	    //Note: this will catch the enter in every single element of the form, be aware of that!
	    jq('#tvinnSadImportEditTopicItemForm').on("keyup keypress", function(e) {
	    	  var code = e.keyCode || e.which; 
	    	  //alert(code);
	    	  if (code  == 13) {               
	    	    e.preventDefault();
	    	    return false;
	    	  }
	    	});
	    
	});
	//====================================
	//Avgift Calculation Dialog popup
	//====================================
  	function getAvgifterBeforeCalculation() {
  		//alert("Debug...");
  		//init
  		//jq("#avgCalculationCloseOk").CLICKTODO();
  		
  		jq(function() {
			jq.getJSON('getAvgiftDataBeforeCalculation_SadImport.do', {
				applicationUser : jq('#applicationUser').val(),
				svvnt : jq('#svvnt').val(),
				siknk_receiverId : jq('#receiverId').val(),
				ajax : 'true'
			}, function(data) {
				//payload
				var len = data.length;
				//Avgifter(1) - clear all 
				var htmlSekvensInit = '<option selected value="">-velg-</option>';
				jq('#search_kode1').val(""); jq('#search_txt1').val(""); jq('#search_sekvensList_1').html(htmlSekvensInit); jq('#search_sats1').val("");
				jq('#search_kode2').val(""); jq('#search_txt2').val(""); jq('#search_sekvensList_2').html(htmlSekvensInit); jq('#search_sats2').val("");
				jq('#search_kode3').val(""); jq('#search_txt3').val(""); jq('#search_sekvensList_3').html(htmlSekvensInit); jq('#search_sats3').val("");
				jq('#search_kode4').val(""); jq('#search_txt4').val(""); jq('#search_sekvensList_4').html(htmlSekvensInit); jq('#search_sats4').val("");
				jq('#search_kode5').val(""); jq('#search_txt5').val(""); jq('#search_sekvensList_5').html(htmlSekvensInit); jq('#search_sats5').val("");
				jq('#search_kode6').val(""); jq('#search_txt6').val(""); jq('#search_sekvensList_6').html(htmlSekvensInit); jq('#search_sats6').val("");
				jq('#search_kode7').val(""); jq('#search_txt7').val(""); jq('#search_sekvensList_7').html(htmlSekvensInit); jq('#search_sats7').val("");
				jq('#search_kode8').val(""); jq('#search_txt8').val(""); jq('#search_sekvensList_8').html(htmlSekvensInit); jq('#search_sats8').val("");
				
				
				for ( var i = 0; i < len; i++) {
					var htmlSekvens = '<option selected value="">-velg-</option>';
					
					//Sekvens
					if(""!=data[i].awc1){
						htmlSekvens += '<option value="' + data[i].awc1 + '@' + data[i].awd1 + '">' + data[i].awc1 +  '</option>';
					}
					if(""!=data[i].awc2){
						htmlSekvens += '<option value="' + data[i].awc2 + '@' + data[i].awd2 + '">' + data[i].awc2 +  '</option>';
					}
					if(""!=data[i].awc3){
						htmlSekvens += '<option value="' + data[i].awc3 + '@' + data[i].awd3 + '">' + data[i].awc3 +  '</option>';
					}
					if(""!=data[i].awc4){
						htmlSekvens += '<option value="' + data[i].awc4 + '@' + data[i].awd4 + '">' + data[i].awc4 +  '</option>';
					}
					if(""!=data[i].awc5){
						htmlSekvens += '<option value="' + data[i].awc5 + '@' + data[i].awd5 + '">' + data[i].awc5 +  '</option>';
					}
					if(""!=data[i].awc6){
						htmlSekvens += '<option value="' + data[i].awc6 + '@' + data[i].awd6 + '">' + data[i].awc6 +  '</option>';
					}
					if(""!=data[i].awc7){
						htmlSekvens += '<option value="' + data[i].awc7 + '@' + data[i].awd7 + '">' + data[i].awc7 +  '</option>';
					}
					if(""!=data[i].awc8){
						htmlSekvens += '<option value="' + data[i].awc8 + '@' + data[i].awd8 + '">' + data[i].awc8 +  '</option>';
					}
					if(""!=data[i].awc9){
						htmlSekvens += '<option value="' + data[i].awc9 + '@' + data[i].awd9 + '">' + data[i].awc9 +  '</option>';
					}
					//now put the payload on targets
					
					//Init and populate when applicable
					jq('#search_kode'+i).val("");
					jq('#search_kode'+i).val(data[i].awa);
					jq('#search_txt'+i).val("");
					jq('#search_txt'+i).val(data[i].awb);
					//clear sats
					jq('#search_sats'+i).val("");
					//list
					jq('#search_sekvensList_'+i).html(htmlSekvens);
				}
			});
		});
	
	}
  	//Avgift sats AJAX fetch when an arbitrary sekvens-value from a list is chosen
	jq(function() { 
	    jq('.clazzAvgCalcSekvens').change(function() {
	    		var id = this.id;
	    		//alert(id);
	    		var idRecord = id.split('_');//example: search_sekvens_1
	    		var counter = idRecord[2];//always last string with the counter
	    		var key = jq('#search_sekvensList_' + counter).val();
	    		//alert(key);
			var record = key.split('@');
			var sats = record[1];
			jq('#search_sats' + counter).val(sats);
	    		
	    });
	});
	//====================================
	//Avgift Calculation result to lend
	//====================================
	function setAvgifterAfterCalculation() {
  		jq(function() {
  			jq.getJSON('setAvgiftDataAfterCalculation_SadImport.do', {
				applicationUser: jq('#applicationUser').val(), svvnt: jq('#svvnt').val(), 
				omrakningsFaktor: jq('#insibvnv').val(), fakturaSum: jq('#sibel3').val(), belCifSum: jq('#sibel4').val(), bearbKost: jq('#sibelr').val(), cifsum: jq('#sibels').val(),
				tollvardi: jq('#svbelt').val(), bruttoVekt: jq('#svvktb').val(), nettoVekt: jq('#svvktn').val(), mengde: jq('#svntm').val(),
				kode0: jq('#search_kode0').val(), sekvensList_0: jq('#search_sekvensList_0').val(), sats0: jq('#search_sats0').val(),
				kode1: jq('#search_kode1').val(), sekvensList_1: jq('#search_sekvensList_1').val(), sats1: jq('#search_sats1').val(),
				kode2: jq('#search_kode2').val(), sekvensList_2: jq('#search_sekvensList_2').val(), sats2: jq('#search_sats2').val(),
				kode3: jq('#search_kode3').val(), sekvensList_3: jq('#search_sekvensList_3').val(), sats3: jq('#search_sats3').val(),
				kode4: jq('#search_kode4').val(), sekvensList_4: jq('#search_sekvensList_4').val(), sats4: jq('#search_sats4').val(),
				kode5: jq('#search_kode5').val(), sekvensList_5: jq('#search_sekvensList_5').val(), sats5: jq('#search_sats5').val(),
				kode6: jq('#search_kode6').val(), sekvensList_6: jq('#search_sekvensList_6').val(), sats6: jq('#search_sats6').val(),
				kode7: jq('#search_kode7').val(), sekvensList_7: jq('#search_sekvensList_7').val(), sats7: jq('#search_sats7').val(),
				kode8: jq('#search_kode8').val(), sekvensList_8: jq('#search_sekvensList_8').val(), sats8: jq('#search_sats8').val(),
				kode9: jq('#search_kode9').val(), sekvensList_9: jq('#search_sekvensList_9').val(), sats9: jq('#search_sats9').val(),
				kode10: jq('#search_kode10').val(), sekvensList_10: jq('#search_sekvensList_10').val(), sats10: jq('#search_sats10').val(),
				kode11: jq('#search_kode11').val(), sekvensList_11: jq('#search_sekvensList_11').val(), sats11: jq('#search_sats11').val(),
				kode12: jq('#search_kode12').val(), sekvensList_12: jq('#search_sekvensList_12').val(), sats12: jq('#search_sats12').val(),
				ajax : 'true'
			}, function(data) {
				//payload
				var len = data.length;
				//remove any previous population
				for ( var j = 1; j <= 8; j++) {
					jq('#wg'+ j).val(""); jq('#wh'+ j).val(""); jq('#wk'+ j).val(""); 
					jq('#wj'+ j).val(""); jq('#wi'+ j).val("");
				}
				//now populate with new values
				var j = 0;
				for ( var i = 0; i < len; i++) {
					//Since the target records are 1-based and not 0-based as the array we use an own counter
					j++;
					//now put the payload on targets
					jq('#wg'+ j).val(data[i].wg); jq('#wh'+ j).val("");jq('#wh'+ j).val(data[i].wh); jq('#wk'+ j).val("");jq('#wk'+ j).val(data[i].wk);
					jq('#wj'+ j).val("");jq('#wj'+ j).val(data[i].wj); jq('#wi'+ j).val("");jq('#wi'+ j).val(data[i].wi);
				}
				//focus after click
				jq('#wg1').focus();
			});
			
		});
	
	}
	
	function removeAllAvgifter() {
  		for ( var j = 1; j <= 8; j++) {
			jq('#wg'+ j).val(""); jq('#wh'+ j).val(""); jq('#wk'+ j).val(""); 
			jq('#wj'+ j).val(""); jq('#wi'+ j).val("");
		}
	}
	
	
	//This blur function is used when the end-user does not uses the taricVarukodlist.change (jQuery) due to his/her knowledge of the varukod
	//In this case the Ajax function must be called when the user leaves the varunr. field...
	jq(function() {	    
		jq('#svvnt').blur(function(e){
			var svvntLENGTH = 8;
			var svvnt = jq('#svvnt').val();
			if(svvnt!="" && svvnt.length==svvntLENGTH){
				jq.ajax({
				  	  type: 'GET',
				  	  url: 'searchTolltariffVarukod_SadImport.do',
				  	  data: { applicationUser : jq('#applicationUser').val(),
							  taricVarukod : jq('#svvnt').val()},
				  	  dataType: 'json',
				  	  cache: false,
				  	  contentType: 'application/json',
				  	  success: function(data) {
						  var len = data.length;
							var taalfa = "";
							for ( var i = 0; i < len; i++) {
								if(data[i].taalfa.length > 0){
									//this is done in order to take care of the Varebeskrivelse (wd1=Varetext) 
									taalfa = data[i].taalfa;
									//PVA if tolltariff exists
									var svpva = jq('#svpva').val();
						    		if(svpva==''){
						    			changePVA();
						    		}
								}
							}
							//Use the value or change a previous value into blank
							if(taalfa != ""){
								if(jq('#wd1').val()==''){
									jq('#wd1').val(taalfa);
								}
							}
										  
				
					  }, error: function() {
						  alert('Error loading ...');
					  }
					}); 
			}
		});
		
	}); 
	
	
	//Preferences drop down
  	jq(function() { 
	    jq('#svtn').blur(function() {
	    		jq.getJSON('getPreferenceCode_SadImport.do', {
				applicationUser : jq('#applicationUser').val(),
				sidp : jq('#ekspedType').val(),
				sitst : jq('#trType').val(),
				svvnt : jq('#svvnt').val(),
				svlk : jq('#svlk').val(),
				svtn : jq('#svtn').val(),
				ajax : 'true'
			}, function(data) {
				var len = data.length;
				var svpre = "";
				for ( var i = 0; i < len; i++) {
					if(data[i].svpre.length > 0){
						svpre = data[i].svpre;
					}
				}
				//result
				jq('#svpre').val(svpre);
			});
		});
	});
  	//Pva drop down - CHANGE to svvnt.blur instead (tolltariff)
  	/*jq(function() {
  		jq('#svpre').blur(function() {
  			var svpva = jq('#svpva').val();
    		if(svpva==''){
    			changePVA();
    		}
  		});
	    jq('#svpre').change(function() {
    		var svpva = jq('#svpva').val();
    		if(svpva==''){
    			changePVA();
    		}
		});
	});*/
  	function changePVA(){
  		jq.getJSON('getPvaCode_SadImport.do', {
			applicationUser : jq('#applicationUser').val(),
			svvnt : jq('#svvnt').val(),
			svlk : jq('#svlk').val(),
			svpre : jq('#svpre').val(),
			ajax : 'true'
		}, function(data) {
			var len = data.length;
			var svpva = "";
			var svas = "";
			for ( var i = 0; i < len; i++) {
				if(data[i].svpva.length > 0){
					svpva = data[i].svpva;
					svas = data[i].svas;
				}
			}
			//result
			jq('#svpva').val(svpva);
			jq('#svas').val(svas);
		});
  	}
	
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
		  if(""==netWeight || "0"==netWeight){
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
		  if(""==grossWeight || "0"==grossWeight){
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
			//Activate Avgift popup in order to simulate the avgCalculaton button click (to help the end-user)
			jq.getJSON('getAvgiftDataBeforeCalculation_SadImport.do', {
				applicationUser : jq('#applicationUser').val(),
				svvnt : jq('#svvnt').val(),
				siknk_receiverId : jq('#receiverId').val(),
				ajax : 'true'
			}, function(data) {
				//payload
				var len = data.length;
				//only if there is a return array AND if previous values do not exist
				if (len>0 && jq('#wg1').val()==''){
					jq('#avgCalculation').click();
				}
			});
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
			//jq('#svvf').focus(); //causing unexpected behavior on html rewrite after  a big item lines set is loaded
			jq('#startItemLineNr').focus();
		}
		
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
	  	  url: 'getSpecificTopicItemChosenFromGuiElement_SadImport.do',
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
				jq('#levenr').val(jq('#receiverId').val());
				jq('#w2vf').val(data[i].svvf);
				jq('#w2lk').val(data[i].svlk);
				jq('#w2vnti').val(data[i].svvnt);
				jq('#svvt').text(data[i].wd1);
				//hidden fields
				jq('#varebe').val(data[i].wd1);
				jq('#w2tn').val(data[i].svtn);
				jq('#w2pre').val(data[i].svpre);
				jq('#w2belt').val(data[i].svbelt);
				jq('#w2vktb').val(data[i].svvktb);
				jq('#w2vktn').val(data[i].svvktn);
				jq('#w2ntm').val(data[i].svntm);
				jq('#w2pva').val(data[i].svpva);
				jq('#w2as').val(data[i].svas);
				jq('#w2mfr').val(data[i].svmfr);
				
				
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
  	  jq("#varenr").blur(function() {
  		  if(jq("#varenr").val()!='' && jq("#levenr").val()!=''){
  			  jq("#dialogSaveTU").button("option", "disabled", false);
  		  }else{
  			  jq("#dialogSaveTU").button("option", "disabled", true);
  		  }
  	  });
  	  jq("#levenr").blur(function() {
		  if(jq("#levenr").val()!='' && jq("#varenr").val()!=''){
			  jq("#dialogSaveTU").button("option", "disabled", false);
		  }else{
			  jq("#dialogSaveTU").button("option", "disabled", true);
		  }
	  }); 
    });
  	
	//-------------------
    //Datatables jquery
    //-------------------
    //private function
    function filterGlobal () {
      jq('#tblItemLinesAll').dataTable().search(
      	jq('#tblItemLinesAll_filter').val()
      ).draw();
      
      jq('#tblItemLines').dataTable().search(
      	jq('#tblItemLines_filter').val()
      ).draw();
    }
   
    jq(document).ready(function(){
    	//init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
	      jq('#tblItemLinesAll').dataTable( {
	    	  "dom": '<"top">t<"bottom"flip><"clear">',
	    	  "scrollY":    "800px",
	    	  "scrollCollapse":  true,
	  		  "order": [[ 13, "desc" ], [ 0, 'asc' ]],
	  		  "lengthMenu": [ 75, 100, 300, 400, 900]
	  	  });
	      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
	      jq('#tblItemLines').dataTable( {
	    	  "dom": '<"top">t<"bottom"flip><"clear">',
	    	  "scrollY":    "180px",
	  		  "scrollCollapse":  true,
	  		  "order": [[ 14, "desc" ], [ 0, 'asc' ]],
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
    });
	