	//===========================================
	//General functions for this JSP side - AJAX
	//===========================================
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
  	  jq('#alinkOmberegningItemLinesSubTab').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  
  	  jq('#getFinansOpplSumButton').click(function() { 
  		if(jq('#finansOpplysningarTotSum').val()!='' && jq('#finansOpplysningarTotValidCurrency').val()!='' ){  
	  		jq('#sebel1').val(jq('#finansOpplysningarTotSum').val());
	  		jq('#seval1').val(jq('#finansOpplysningarTotValidCurrency').val());	
	  		//jq('#KURS???').val(jq('#finansOpplysningarTotKurs').val());
	  		
	  		//for backwards compatibility meaning: ref till fakturalista
  			jq('#sefif').val("F15  ER  BENYTTET");
  			
  		}
	  });
    });
    
  //General functions
  	jq(function() {
  		jq( "#submit" ).click(function( event ) {
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
	  	});
  		
  		//=====================================
	  	//START Child window for general codes
	  	//=====================================
  		//Valutakod
	    jq('#seval1IdLink').click(function() {
	    	jq('#seval1IdLink').attr('target','_blank');
	    	window.open('tvinnsadexport_edit_childwindow_generalcodes.do?action=doInit&type=V&ctype=seval1', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#seval1IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#seval1IdLink').click();
			}
	    });
	    //landkod
	    jq('#selkaIdLink').click(function() {
	    	jq('#selkaIdLink').attr('target','_blank');
	    	window.open('tvinnsadexport_edit_childwindow_generalcodes.do?action=doInit&type=2&ctype=selka', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#selkaIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#selkaIdLink').click();
			}
	    });
	    //landkod
	    jq('#selkbIdLink').click(function() {
	    	jq('#selkbIdLink').attr('target','_blank');
	    	window.open('tvinnsadexport_edit_childwindow_generalcodes.do?action=doInit&type=2&ctype=selkb', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#selkbIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#selkbIdLink').click();
			}
	    });
	    //landkod
	    jq('#selktIdLink').click(function() {
	    	jq('#selktIdLink').attr('target','_blank');
	    	window.open('tvinnsadexport_edit_childwindow_generalcodes.do?action=doInit&type=2&ctype=selkt', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#selktIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#selktIdLink').click();
			}
	    });
	    //Valutakod
	    jq('#seval2IdLink').click(function() {
	    	jq('#seval1IdLink').attr('target','_blank');
	    	window.open('tvinnsadexport_edit_childwindow_generalcodes.do?action=doInit&type=V&ctype=seval2', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#seval1IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#seval1IdLink').click();
			}
	    });
	    //----------------
	  	//CUSTOMER search
	  	//----------------
	    //SENDER
	    jq('#senakIdLink').click(function() {
	    	jq('#senakIdLink').attr('target','_blank');
	    	window.open('tvinnsad_childwindow_customer.do?action=doFind&sonavn=' + jq('#senak').val() + '&ctype=senak', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#senakIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#senakIdLink').click();
			}
	    });
	    //RECEIVER
	    jq('#senasIdLink').click(function() {
	    	jq('#senasIdLink').attr('target','_blank');
	    	window.open('tvinnsad_childwindow_customer.do?action=doFind&sonavn=' + jq('#senas').val() + '&ctype=senas', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#senasIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#senasIdLink').click();
			}
	    });
  		
  	});
  	
  	//-----------------------------------------------------------------------------
  	//jQuery CALCULATOR (related to jquery.calculator.js and jquery.calculator.css
  	//-----------------------------------------------------------------------------
  	jq(function() {
  		jq('#sebel1').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  	});
	
  	 
  	jq(function() {
	  jq("#sefid").datepicker({ 
		  dateFormat: 'ddmmy'  
	  });
    });
  	
  	//onChange avd list
  	jq(function() { 
	    jq('#avd').change(function() {
	    		jq.getJSON('getSpecificTopicAvdData_SadExport.do', {
				applicationUser : jq('#applicationUser').val(),
				avd : jq('#avd').val(),
				ajax : 'true'
			}, function(data) {
				//alert("Hello");
				var len = data.length;
				for ( var i = 0; i < len; i++) {
				  jq('#sedty').val(data[i].sedty);
			      jq('#sedp').val(data[i].sedp);
			      jq('#sekns').val(data[i].sekns);
			      jq('#senas').val(data[i].senas);
			      jq('#seads1').val(data[i].seads1);
			      jq('#seads2').val(data[i].seads2);
			      jq('#seads3').val(data[i].seads3);
			      jq('#sentk').val(data[i].sentk);
			      jq('#sevkb').val(data[i].sevkb);
			      jq('#sekdc').val(data[i].sekdc);
			      jq('#seknk').val(data[i].seknk);
			      jq('#serg').val(data[i].serg);
			      jq('#senak').val(data[i].senak);
			      jq('#seadk1').val(data[i].seadk1);
			      jq('#seadk2').val(data[i].seadk2);
			      jq('#seadk3').val(data[i].seadk3);
			      jq('#selka').val(data[i].selka);
			      jq('#setlf').val(data[i].setlf);
			      jq('#senad').val(data[i].senad);
			      jq('#selv').val(data[i].selv);
			      jq('#selvt').val(data[i].selvt);
			      jq('#setrid').val(data[i].setrid);
			      jq('#selkt').val(data[i].selkt);
			      jq('#seval1').val(data[i].seval1);
			      jq('#sebel1').val(data[i].sebel1);
			      jq('#sevku').val(data[i].sevku);
			      jq('#sekdh').val(data[i].sekdh);
			      jq('#setst').val(data[i].setst);
			      jq('#seftg2').val(data[i].seftg2);
			      jq('#setrm').val(data[i].setrm);
			      jq('#sefif').val(data[i].sefif);
			      jq('#sefid').val(data[i].sefid);
			      jq('#sekta').val(data[i].sekta);
			      jq('#sektb').val(data[i].sektb);
			      jq('#segn').val(data[i].segn);
			      jq('#seft1').val(data[i].seft1);
			      jq('#seft2').val(data[i].seft2);
			      jq('#seft3').val(data[i].seft3);
			      jq('#sedst').val(data[i].sedst);
			      jq('#sedtg').val(data[i].sedtg);
			      jq('#setll').val(data[i].setll);
			      jq('#setle').val(data[i].setle);
			      jq('#seski').val(data[i].seski);
			      jq('#sels').val(data[i].sels);
			      jq('#sekdls').val(data[i].sekdls);
			      jq('#sedt').val(data[i].sedt);
			      jq('#selv2').val(data[i].selv2);
			      jq('#sekddk').val(data[i].sekddk);
			      jq('#segkd').val(data[i].segkd);
			      jq('#segft1').val(data[i].segft1);
			      jq('#segft2').val(data[i].segft2);
			      jq('#sepos').val(data[i].sepos);
			      jq('#seftb').val(data[i].seftb);
			      jq('#selkb').val(data[i].selkb);
			      jq('#seftm').val(data[i].seftm);
			      jq('#selkm').val(data[i].selkm);
			      jq('#sekdft').val(data[i].sekdft);
			      jq('#selkat').val(data[i].selkat);
			      jq('#seval2').val(data[i].seval2);
			      jq('#sebel3').val(data[i].sebel3);
			      jq('#sedl').val(data[i].sedl);
			      jq('#setolk').val(data[i].setolk);
			      jq('#sea4').val(data[i].sea4);
			      jq('#s0004').val(data[i].s0004);
			      jq('#s0010').val(data[i].s0010);
			      jq('#s0035').val(data[i].s0035);
			      jq('#s0026').val(data[i].s0026);
			      jq('#sektc').val(data[i].sektc);
			      jq('#seekst').val(data[i].seekst);
				}
			});
	    });
	});
  	 			
  	
	//----------------
	//onChange events
	//----------------
	  jq(function() { 
	    jq('#avsenderland').change(function() {
	    		jq('#selka').val(jq('#avsenderland').val());	
	    });
	    jq('#avsenderland').keypress(function(e) {
		    	if(e.which == 13) {
				e.preventDefault();
		    	}
	    });
	    jq('#beland').change(function() {
    			jq('#selkb').val(jq('#beland').val());	
	    });
	    jq('#beland').keypress(function(e) {
		    	if(e.which == 13) {
				e.preventDefault();
		    	}
	    });
	  });	
	  
	  jq(function() { 
	    jq('#nasjonalitetSearch').change(function() {
			jq('#selkt').val(jq('#nasjonalitetSearch').val());	
	    });
	    jq('#nasjonalitetSearch').keypress(function(e) {
		    	if(e.which == 13) {
				e.preventDefault();
		    	}
	    });
	  });	
	  
	  jq(function() { 
	    jq('#seval1Search').change(function() {
			jq('#seval1').val(jq('#seval1Search').val());	
	    });
	  });	
	  jq(function() { 
	    jq('#seval2Search').change(function() {
			jq('#seval2').val(jq('#seval2Search').val());	
	    });
	  });
	  
	  jq(function() { 
		    jq('#havnCodesSearch').change(function() {
		    	  setHavnCode(jq('#havnCodesSearch').val());
			});
		    jq('#sekdh').change(function() {
		    	  setHavnCode(jq('#sekdh').val());	
		    });
		});
	  	  //private function for the change events on havnCodes
		  function setHavnCode(value) {
			  var key = value;
			  var separator = key.indexOf("@");
			  //alert(key);
			  if(separator>0){
				  //alert("combo: separator == 0");
				  jq('#sekdh').val(key);
				  //jq('#sekdh').val(key.substring(0,separator));
				  jq('#sekdft').val(key.substring(separator+1));
				  
			  }else{
				  //alert("combo: separator == 0");
				  jq('#sekdh').val(key);
				  jq('#sekdft').val("");
			  }
		  }
	
	
	//-----------------------
  	//INIT CUSTOMER Object
  	//-----------------------
	var map = {};
  	//init the customer object in javascript (will be put into a map)
  	var customer = new Object();
  	//fields
  	customer.kundnr = "";customer.knavn = "";customer.eori = "";customer.adr1 = "";
  	customer.adr2 = "";customer.adr3 = "";customer.postnr = "";customer.syland = "";
  	customer.kpers = "";customer.tlf = "";customer.regnr = "";
  	customer.tollkreditFieldA = "";customer.tollkreditFieldB = "";customer.tollkreditFieldC = "";
  	//---------------------------------------------------------
  	//FETCH CUSTOMER from SENDER  html area
  	//---------------------------------------------------------
	function searchSenderOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer_TvinnSad.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_senak').val(),
				customerNumber : jq('#search_seknk').val(),
				ajax : 'true'
			}, function(data) {
				//alert("Hello");
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
					customer = new Object();
					customer.kundnr = data[i].kundnr;
					customer.knavn = data[i].knavn;
					customer.eori = data[i].eori;
					customer.adr1 = data[i].adr1;
					customer.adr2 = data[i].adr2;
					customer.adr3 = data[i].adr3;
					customer.postnr = data[i].postnr;
					customer.kpers = data[i].kpers;
					customer.tlf = data[i].tlf;
					customer.syland = data[i].syland;
					customer.regnr = data[i].syrg;
					customer.tollkreditFieldC = data[i].wsktc;
					customer.tollkreditFieldA = data[i].wskta;
					customer.tollkreditFieldB = data[i].wsktb;
				  	
					//put the object in map now with customerNumber as key
					map[customer.kundnr] = customer;
				}
				//now that we have our options, give them to our select
				jq('#senderList').html(html);
			});
		});
	}
	//onChange sender list
	jq(function() { 
	    jq('#senderList').change(function() {
	    	  var name = jq('#senak').val();
	      var regNr = jq('#serg').val();
	    	  if(name=='' && regNr==''){	
		      //init fields
		    	  jq('#serg').val("");
			  jq('#senak').val("");
			  jq('#seadk1').val("");
			  jq('#seadk2').val("");
			  jq('#seadk3').val("");
			  //tollkredit
			  jq('#segkd').val("");
			  jq('#sekta').val("");
			  jq('#sektb').val("");
			  //now populate (if applicable)
			  var key = jq('#senderList').val();
			  jq('#seknk').val(key);
			  customer = map[key];
			  jq('#serg').val(customer.regnr);
			  jq('#senak').val(customer.knavn);
			  jq('#seadk1').val(customer.adr1);
			  jq('#seadk2').val(customer.adr2);
			  jq('#seadk3').val(customer.adr3);
			  //tollkredit
			  jq('#segkd').val(customer.tollkreditFieldC);
			  jq('#sekta').val(customer.tollkreditFieldA);
			  jq('#sektb').val(customer.tollkreditFieldB);
	    	  }
	    });
	});
	
	//onClick for Sender dialog
	jq(function() { 
	    jq('#searchCustomerCloseCancel').click(function() {
	      //rescue the original fields
	      jq('#seknk').val(jq("#orig_seknk").val());	
		  jq('#senak').val(jq("#orig_senak").val());
		  jq('#seadk1').val(jq("#orig_seadk1").val());
		  jq('#seadk2').val(jq("#orig_seadk2").val());
		  jq('#seadk3').val(jq("#orig_seadk3").val());
		  jq('#segkd').val("");
		  jq('#sekta').val("");
		  jq('#sektb').val("");
	    });
	});
	//----------------------------------
	//Events Sender (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgCustomerSearch').click(function(){
    			jq("#search_seknk").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_seknk').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchSenderOwnWindow);
			}			
    		});
		jq('#search_senak').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchSenderOwnWindow);
			}			
    		});
	});
	
	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#seknk').blur(function() {
	    		var customerNr = jq('#seknk').val();
	    		var name = jq('#senak').val();
	    		var regNr = jq('#serg').val();
			
	    		if(customerNr!='' && (name=='' && regNr=='') ){
	    			jq.getJSON('searchCustomer_TvinnSad.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#seknk').val(),
					ajax : 'true'
				}, function(data) {
					//alert("Hello");
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						//html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
						customer = new Object();
						customer.kundnr = data[i].kundnr;
						customer.knavn = data[i].knavn;
						customer.eori = data[i].eori;
						customer.adr1 = data[i].adr1;
						customer.adr2 = data[i].adr2;
						customer.adr3 = data[i].adr3;
						customer.postnr = data[i].sypoge;//data[i].postnr; DK=sypoge
						customer.kpers = data[i].kpers;
						customer.tlf = data[i].tlf;
						customer.syland = data[i].syland;
						customer.regnr = data[i].syrg;
						customer.tollkreditFieldC = data[i].wsktc;
						customer.tollkreditFieldA = data[i].wskta;
						customer.tollkreditFieldB = data[i].wsktb;
					  	//put the object in map now with customerNumber as key
						map[customer.kundnr] = customer;
					}
					if(len > 0){
						jq('#seknk').val(customer.kundnr);
						jq('#serg').val(customer.regnr);
						jq('#senak').val(customer.knavn);
						jq('#seadk1').val(customer.adr1);
						jq('#seadk2').val(customer.adr2);
						jq('#seadk3').val(customer.adr3);
						//tollkredit
						jq('#segkd').val(customer.tollkreditFieldC);	
						jq('#sekta').val(customer.tollkreditFieldA);
						jq('#sektb').val(customer.tollkreditFieldB);
					}else{
						//init fields
						jq('#seknk').val("");
						jq('#senak').val("");
						jq('#seadk1').val("");
						jq('#seadk2').val("");
						jq('#seadk3').val("");
						//tollkredit
						jq('#segkd').val("");
						jq('#sekta').val("");
						jq('#sektb').val("");
					}
				});
	    			//free text
		    		setFreeTextSender(jq('#seknk').val());
	    		}
		});
	});
	//-----------------------------------
	//Sender free text info (read-only)
	//-----------------------------------
	jq(function() { 
	    jq('#senderFreeTextImg').click(function() {
	    		setFreeTextSender(jq('#seknk').val());
		});
	});
	//-------------------
	//free text (sender)
	//-------------------
	function setFreeTextSender(seknk) { 
		jq(function() {
			if(seknk!=null && seknk!=""){
		    		jq.getJSON('getCustomerInfoFreeText_TvinnSad.do', {
					applicationUser : jq('#applicationUser').val(),
					customerNumber : jq('#seknk').val(),
					delsystem : "L",
					ajax : 'true'
				}, function(data) {
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						buffer = data[i].fxtxt;
					}
					jq('#senderInfoTextArea').val(buffer);
				});
    			}else{
    				jq('#senderInfoTextArea').val("");
    			}
		});
	}
	
  	//---------------------------------------------------------
	//FETCH CUSTOMER from RECEIVER [MOTTAGARE] html area
  	//---------------------------------------------------------
	function searchReceiverOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer_TvinnSad.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_senas').val(),
				customerNumber : jq('#search_sekns').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
					customer = new Object();
					customer.kundnr = data[i].kundnr;
					customer.knavn = data[i].knavn;
					customer.eori = data[i].eori;
					customer.adr1 = data[i].adr1;
					customer.adr2 = data[i].adr2;
					customer.adr3 = data[i].adr3;
					customer.postnr = data[i].postnr;
					customer.kpers = data[i].kpers;
					customer.tlf = data[i].tlf;
					customer.syland = data[i].syland;
				  	
					//put the object in map now with customerNumber as key
					map[customer.kundnr] = customer;
				}
				//now that we have our options, give them to our select
				jq('#receiverList').html(html);
			});
		});
	}
	//Sets receiver values after user selection
	jq(function() { 
	    jq('#receiverList').change(function() {
		  //init all fields
		  jq('#senas').val("");
		  jq('#seads1').val("");
		  jq('#seads2').val("");
		  jq('#seads3').val("");
		  //now populate (if applicable)
		  var key = jq('#receiverList').val();
		  jq('#sekns').val(key);
		  customer = map[key];
		  jq('#senas').val(customer.knavn);
		  jq('#seads1').val(customer.adr1);
		  jq('#seads2').val(customer.adr2);
		  jq('#seads3').val(customer.adr3);
		});
	});
	//onClick for Receiver(Mottagare) dialog
	jq(function() { 
	    jq('#searchCustomer10CloseCancel').click(function() {
	      //rescue the original fields
		  jq('#sekns').val(jq("#orig_sekns").val());	
		  jq('#senas').val(jq("#orig_senas").val());
		  jq('#seads1').val(jq("#orig_seads1").val());
		  jq('#seads2').val(jq("#orig_seads2").val());
		  jq('#seads3').val(jq("#orig_seads3").val());
	    });
	});
	
	//----------------------------------
	//Events Receiver (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgReceiverSearch').click(function(){
    			jq("#search_sekns").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sekns').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchReceiverOwnWindow);
			}			
    		});
		jq('#search_sekns').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchReceiverOwnWindow);
			}			
    		});
	});
	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#sekns').blur(function() {
	    		var sekns = jq('#sekns').val();
	    		var senas = jq('#senas').val();
	    		if(	(sekns!=null && sekns!="") && (senas=='')){
		    		jq.getJSON('searchCustomer_TvinnSad.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#sekns').val(),
					ajax : 'true'
				}, function(data) {
					//alert("Hello");
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						//html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
						customer = new Object();
						customer.kundnr = data[i].kundnr;
						customer.knavn = data[i].knavn;
						customer.eori = data[i].eori;
						customer.adr1 = data[i].adr1;
						customer.adr2 = data[i].adr2;
						customer.adr3 = data[i].adr3;
						customer.postnr = data[i].sypoge;//data[i].postnr; DK=sypoge
						customer.kpers = data[i].kpers;
						customer.tlf = data[i].tlf;
						customer.syland = data[i].syland;
					  	//put the object in map now with customerNumber as key
						map[customer.kundnr] = customer;
					}
					if(len > 0){
						jq('#sekns').val(customer.kundnr);
						jq('#senas').val(customer.knavn);
						jq('#seads1').val(customer.adr1);
						jq('#seads2').val(customer.adr2);
						jq('#seads3').val(customer.adr3);
						
					}else{
						//init fields
						jq('#sekns').val("");
						jq('#senas').val("");
						jq('#seads1').val("");
						jq('#seads2').val("");
						jq('#seads3').val("");
					}
				});
		    		//free text
		    		setFreeTextReceiver(jq('#sekns').val());
	    		}
		});
	});
	
	//-----------------------------------
	//Receiver free text info (read-only)
	//-----------------------------------
	jq(function() { 
	    jq('#receiverFreeTextImg').click(function() {
	    		setFreeTextReceiver(jq('#sekns').val());
		});
	});
	//free text
	function setFreeTextReceiver(sekns) { 
		jq(function() {
			if(sekns!=null && sekns!=""){
		    		jq.getJSON('getCustomerInfoFreeText_TvinnSad.do', {
					applicationUser : jq('#applicationUser').val(),
					customerNumber : jq('#sekns').val(),
					delsystem : "L",
					ajax : 'true'
				}, function(data) {
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						buffer = data[i].fxtxt;
					}
					jq('#receiverInfoTextArea').val(buffer);
				});
	    		}else{
	    			jq('#receiverInfoTextArea').val("");
	    		}
		});
	}

	//============================
	//START - Currency AJAX fetch
	//============================
	jq(function() { 
	    jq('#seval1').change(function() {
	    		//alert('Hej');
	    		//this parameters must match the AJAX controller parameter names in Spring exactly...
	    		var isoDate = ""; 
	    		var fakturaDato = jq('#sefid').val();
	    		var deklarationsDato = jq('#sedt').val();//reg.dato
	    		if(fakturaDato!=""){
	    			isoDate =fakturaDato; 
	    		}else{
	    			isoDate =deklarationsDato;
	    		}
	    		getCurrencyData(isoDate);
	    });
	});
	jq(function() { 
	    jq('#sevku').blur(function() {
	    		//alert('Hej');
	    		//this parameters must match the AJAX controller parameter names in Spring exactly...
	    		var sevku = jq('#sevku').val();
	    		var isoDate = ""; 
	    		var fakturaDato = jq('#sefid').val();
	    		var deklarationsDato = jq('#sedt').val();//reg.dato
	    		if(fakturaDato!=""){
	    			isoDate =fakturaDato; 
	    		}else{
	    			isoDate =deklarationsDato;
	    		}
	    		if(sevku==null || sevku==""){
	    			getCurrencyData(isoDate);
	    		}	
	    });
	});
	//private function
	function getCurrencyData(isoDate) {
		jq.ajax({
			type: 'GET',
			url: 'getCurrencyRate_SadExport.do',
			data: { 	applicationUser : jq('#applicationUser').val(),
					currencyCode : jq('#seval1').val(),
					isoDate : isoDate} ,
			dataType: 'json',
			success: function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#sevku').val(data[i].kvakrs);
					jq('#factor').val(data[i].kvaomr);
				}
			}
		});
	}
	//============================
	//END - Currency AJAX fetch
	//============================

	
	//-------------------------------------------
	  //START Model dialog ADMIN: "Update status"
	  //-------------------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogUpdateStatus").dialog({
			  autoOpen: false,
			  maxWidth:500,
	          maxHeight: 400,
	          width: 280,
	          height: 220,
			  modal: true
		  });
		  
	  });
	  
	  //----------------------------
	  //Present dialog box onClick 
	  //----------------------------
	  jq(function() {
		  jq("#updateStatusLink").click(function() {
			  presentChangeStatusDialog();
		  });
		  jq("#updateStatusByUserImg").click(function() {
			  presentChangeStatusDialog();
		  });
		  
	  });
	  function presentChangeStatusDialog(){
		  jq('#dialogUpdateStatus').dialog( "option", "title", "Update Status" );
		  //deal with buttons for this modal window
		  jq('#dialogUpdateStatus').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		jq('#updateStatusForm').submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Cancel", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSaveSU").button("option", "disabled", true);
					 		jq("#selectedStatus").val("");
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveSU").button("option", "disabled", true);
		  //open now
		  jq('#dialogUpdateStatus').dialog('open');
		  
	  }
	  	