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
  	
  	  jq('#getFinansOpplSumButton').click(function() { 
  		if(jq('#finansOpplysningarTotSum').val()!='' && jq('#finansOpplysningarTotValidCurrency').val()!='' ){    
  			jq('#sibel3').val(jq('#finansOpplysningarTotSum').val());
  			jq('#sival3').val(jq('#finansOpplysningarTotValidCurrency').val());	
  			jq('#sivku').val(jq('#finansOpplysningarTotKurs').val());
  			//for backwards compatibility meaning: ref till fakturalista
  			jq('#sifif').val("F15  ER  BENYTTET");
  			
  			
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
	    jq('#sival3IdLink').click(function() {
	    	jq('#sival3IdLink').attr('target','_blank');
	    	window.open('tvinnsadimport_edit_childwindow_generalcodes.do?action=doInit&type=V&ctype=sival3', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sival3IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sival3IdLink').click();
			}
	    });
	    //Avs.utf land
	    jq('#silkaIdLink').click(function() {
	    	jq('#silkaIdLink').attr('target','_blank');
	    	window.open('tvinnsadimport_edit_childwindow_generalcodes.do?action=doInit&type=2&ctype=silka', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#silkaIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#silkaIdLink').click();
			}
	    });
	    //Transp.nationalitet
	    jq('#silktIdLink').click(function() {
	    	jq('#silktIdLink').attr('target','_blank');
	    	window.open('tvinnsadimport_edit_childwindow_generalcodes.do?action=doInit&type=2&ctype=silkt', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#silktIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#silktIdLink').click();
			}
	    });
	    //tollberek.frakt -valutakod
	    jq('#sival1IdLink').click(function() {
	    	jq('#sival1IdLink').attr('target','_blank');
	    	window.open('tvinnsadimport_edit_childwindow_generalcodes.do?action=doInit&type=V&ctype=sival1', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sival1IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sival1IdLink').click();
			}
	    });
	    //andre kostnader -valutakod
	    jq('#sival2IdLink').click(function() {
	    	jq('#sival2IdLink').attr('target','_blank');
	    	window.open('tvinnsadimport_edit_childwindow_generalcodes.do?action=doInit&type=V&ctype=sival2', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sival2IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sival2IdLink').click();
			}
	    });
	    //-----------------
	    //CUSTOMER search
	  	//-----------------
	    //SENDER
	    jq('#sinasIdLink').click(function() {
	    	jq('#sinasIdLink').attr('target','_blank');
	    	window.open('tvinnsad_childwindow_customer.do?action=doFind&sonavn=' + jq('#sinas').val() + '&ctype=sinas', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sinasIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sinasIdLink').click();
			}
	    });
	    //RECEIVER
	    jq('#sinakIdLink').click(function() {
	    	jq('#sinakIdLink').attr('target','_blank');
	    	window.open('tvinnsad_childwindow_customer.do?action=doFind&sonavn=' + jq('#sinak').val() + '&ctype=sinak', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sinakIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sinakIdLink').click();
			}
	    });
	    
  	});
    
  	//-----------------------------------------------------------------------------
  	//jQuery CALCULATOR (related to jquery.calculator.js and jquery.calculator.css
  	//-----------------------------------------------------------------------------
  	jq(function() {
  		jq('#sibel3').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  	});
  	
  	jq(function() {
	  jq("#sifid").datepicker({ 
		  dateFormat: 'ddmmy'  
	  });
    });
  	jq(function() {
  	  jq("#m2005b").datepicker({ 
  		  dateFormat: 'ddmmy'  
  	  });
      });
  	
  	//onChange avd list
	jq(function() { 
	    jq('#avd').change(function() {
	    		jq.getJSON('getSpecificTopicAvdData_SadImport.do', {
				applicationUser : jq('#applicationUser').val(),
				avd : jq('#avd').val(),
				ajax : 'true'
			}, function(data) {
				//alert("Hello");
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#sidty').val(data[i].sidty);
					jq('#sidp').val(data[i].sidp);
					jq('#sikns').val(data[i].sikns);
					jq('#sinas').val(data[i].sinas);
					jq('#siads1').val(data[i].siads1);
					jq('#siads2').val(data[i].siads2);
					jq('#siads3').val(data[i].siads3);
					jq('#sintk').val(data[i].sintk);
					jq('#siski').val(data[i].siski);
					jq('#sikddk').val(data[i].sikddk);
					jq('#sikdc').val(data[i].sikdc);
					jq('#siknk').val(data[i].siknk);
					jq('#sirg').val(data[i].sirg);
					jq('#sinak').val(data[i].sinak);
					jq('#siadk1').val(data[i].siadk1);
					jq('#siadk2').val(data[i].siadk2);
					jq('#siadk3').val(data[i].siadk3);
					jq('#sival1').val(data[i].sival1);
					jq('#sival2').val(data[i].sival2);
					jq('#silka').val(data[i].silka);
					jq('#sinad').val(data[i].sinad);
					jq('#sitlf').val(data[i].sitlf);
					jq('#silv').val(data[i].silv);
					jq('#silvt').val(data[i].silvt);
					jq('#sival').val(data[i].sival);
					jq('#sitst').val(data[i].sitst);
					jq('#sitrt').val(data[i].sitrt);
					jq('#sitrm').val(data[i].sitrm);
					jq('#si07').val(data[i].si07);
					jq('#sikta').val(data[i].sikta);
					jq('#siktb').val(data[i].siktb);
					jq('#sift1').val(data[i].sift1);
					jq('#sift2').val(data[i].sift2);
					jq('#sift3').val(data[i].sift3);
					jq('#sift4').val(data[i].sift4);
					jq('#sidst').val(data[i].sidst);
					jq('#sils').val(data[i].sils);
					jq('#sikdls').val(data[i].sikdls);
					jq('#sikdh').val(data[i].sikdh);
					jq('#sikdtr').val(data[i].sikdtr);
					jq('#silv2').val(data[i].silv2);
					jq('#sitarf').val(data[i].sitarf);
					jq('#sipkl').val(data[i].sipkl);
					jq('#sikdv').val(data[i].sikdv);
					jq('#s0035').val(data[i].s0035);
					
				}
			});
	    });
	});
	
	//----------------
	//onChange events
	//----------------
	  jq(function() { 
	    jq('#avsenderland').change(function() {
	    		jq('#silka').val(jq('#avsenderland').val());	
	    });
	  });	
	  
	  jq(function() { 
	    jq('#nasjonalitetSearch').change(function() {
			jq('#silkt').val(jq('#nasjonalitetSearch').val());	
	    });
	  });	
	  
	  jq(function() { 
	    jq('#sival1Search').change(function() {
			jq('#sival1').val(jq('#sival1Search').val());	
	    });
	  });	
	  jq(function() { 
	    jq('#sival2Search').change(function() {
			jq('#sival2').val(jq('#sival2Search').val());	
	    });
	  });
	  jq(function() { 
		    jq('#sival3Search').change(function() {
				jq('#sival3').val(jq('#sival3Search').val());	
		    });
	  });
	
	
	//-----------------------
  	//INIT CUSTOMER Object
  	//-----------------------
	var map = {};
  	//init the customer object in javascript (will be put into a map)
  	var customer = new Object();
  	//fields
  	customer.kundnr = "";customer.knavn = "";customer.eori = "";customer.adr1 = "";
  	customer.adr2 = "";customer.adr3 = "";customer.postnr = "";customer.syland = "";
  	customer.kpers = "";customer.tlf = "";customer.regnr = ""; customer.symvjn = "";
  	customer.tollkreditFieldA = "";customer.tollkreditFieldB = "";customer.tollkreditFieldC = "";
  	//---------------------------------------------------------
  	//FETCH CUSTOMER from SENDER  html area
  	//---------------------------------------------------------
	function searchSenderOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer_TvinnSad.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_sinas').val(),
				customerNumber : jq('#search_sikns').val(),
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
		    	var name = jq('#sinas').val();
			var adress = jq('#siads1').val();
	    		if(name=='' && adress==''){
		      //init fields
			  jq('#sinas').val("");
			  jq('#siads1').val("");
			  jq('#siads2').val("");
			  jq('#siads3').val("");
			  
			  //now populate (if applicable)
			  var key = jq('#senderList').val();
			  jq('#sikns').val(key);
			  customer = map[key];
			  jq('#sinas').val(customer.knavn);
			  jq('#siads1').val(customer.adr1);
			  jq('#siads2').val(customer.adr2);
			  jq('#siads3').val(customer.adr3);
	    		}  
	    });
	});
	
	//onClick for Sender dialog
	jq(function() { 
	    jq('#searchCustomerCloseCancel').click(function() {
	      //rescue the original fields
	      jq('#sikns').val(jq("#orig_sikns").val());	
		  jq('#sinas').val(jq("#orig_sinas").val());
		  jq('#siads1').val(jq("#orig_siads1").val());
		  jq('#siads2').val(jq("#orig_siads2").val());
		  jq('#siads3').val(jq("#orig_siads3").val());
	    });
	});
	//----------------------------------
	//Events Sender (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgCustomerSearch').click(function(){
    			jq("#search_sikns").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sikns').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchSenderOwnWindow);
			}			
    		});
		jq('#search_sinas').keypress(function(e){
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
	    jq('#sikns').blur(function() {
	    		var customerNr = jq('#sikns').val();
	    		var name = jq('#sinas').val();
			var address = jq('#siads1').val();
	    		if(customerNr!="" && (name=='' && address=='')){
		    		jq.getJSON('searchCustomer_TvinnSad.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#sikns').val(),
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
						jq('#sikns').val(customer.kundnr);
						jq('#sinas').val(customer.knavn);
						jq('#siads1').val(customer.adr1);
						jq('#siads2').val(customer.adr2);
						jq('#siads3').val(customer.adr3);
					}else{
						//init fields
						jq('#sikns').val("");
						jq('#sinas').val("");
						jq('#siads1').val("");
						jq('#siads2').val("");
						jq('#siads3').val("");
						
					}
				});
		    		//get free text
		    		setFreeTextSender(jq('#sikns').val());
	    		}
		});
	});
	//-----------------------------------
	//Sender free text info (read-only)
	//-----------------------------------
	jq(function() { 
	    jq('#senderFreeTextImg').click(function() {
	    		setFreeTextSender(jq('#sikns').val());
		});
	});
	//-------------------
	//free text (sender)
	//-------------------
	function setFreeTextSender(sikns) { 
		jq(function() {
			if(sikns!=null && sikns!=""){
		    		jq.getJSON('getCustomerInfoFreeText_TvinnSad.do', {
					applicationUser : jq('#applicationUser').val(),
					customerNumber : jq('#sikns').val(),
					delsystem : "K",
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
				customerName : jq('#search_sinak').val(),
				customerNumber : jq('#search_siknk').val(),
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
					customer.regnr = data[i].syrg;
					customer.tollkreditFieldC = data[i].wsktc;
					customer.tollkreditFieldA = data[i].wskta;
					customer.tollkreditFieldB = data[i].wsktb;
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
	    		var name = jq('#sinak').val();
			var regNr = jq('#sirg').val();
	    		if(name=='' && regNr==''){
			  //init all fields
			  jq('#sirg').val("");
			  jq('#sinak').val("");
			  jq('#siadk1').val("");
			  jq('#siadk2').val("");
			  jq('#siadk3').val("");
			  //tollkredit
			  jq('#siktc').val("");
			  jq('#sikta').val("");
			  jq('#siktb').val("");	
			  //now populate (if applicable)
			  var key = jq('#receiverList').val();
			  jq('#siknk').val(key);
			  customer = map[key];
			  jq('#sirg').val(customer.regnr);
			  jq('#sinak').val(customer.knavn);
			  jq('#siadk1').val(customer.adr1);
			  jq('#siadk2').val(customer.adr2);
			  jq('#siadk3').val(customer.adr3);
			  //tollkredit
			  jq('#siktc').val(customer.tollkreditFieldC);
			  jq('#sikta').val(customer.tollkreditFieldA);
			  jq('#siktb').val(customer.tollkreditFieldB);	
			  
			}
		});
	});
	//onClick for Receiver(Mottagare) dialog
	jq(function() { 
	    jq('#searchCustomer10CloseCancel').click(function() {
	      //rescue the original fields
		  jq('#siknk').val(jq("#orig_siknk").val());	
		  jq('#sirg').val(jq("#orig_sirg").val());
		  jq('#sinak').val(jq("#orig_sinak").val());
		  jq('#siadk1').val(jq("#orig_siadk1").val());
		  jq('#siadk2').val(jq("#orig_siadk2").val());
		  jq('#siadk3').val(jq("#orig_siadk3").val());
		  jq('#siktc').val("");
		  jq('#sikta').val("");
		  jq('#siktb').val("");	
	    });
	});
	
	//----------------------------------
	//Events Receiver (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgReceiverSearch').click(function(){
    			jq("#search_siknk").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_siknk').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchReceiverOwnWindow);
			}			
    		});
		jq('#search_sinak').keypress(function(e){
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
	    jq('#siknk').blur(function() {
	    		var customerNr = jq('#siknk').val();
	    		var name = jq('#sinak').val();
			var regNr = jq('#sirg').val();
				
	    		if(customerNr!='' && (name=='' && regNr=='') ){
		    		jq.getJSON('searchCustomer_TvinnSad.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#siknk').val(),
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
						customer.symvjn = data[i].symvjn;
					  	//put the object in map now with customerNumber as key
						map[customer.kundnr] = customer;
					}
					if(len > 0){
						jq('#siknk').val(customer.kundnr);
						jq('#sirg').val(customer.regnr);
						jq('#simva').val(customer.symvjn);
						jq('#sinak').val(customer.knavn);
						jq('#siadk1').val(customer.adr1);
						jq('#siadk2').val(customer.adr2);
						jq('#siadk3').val(customer.adr3);
						//tollkredit
						jq('#siktc').val(customer.tollkreditFieldC);
						jq('#sikta').val(customer.tollkreditFieldA);
						jq('#siktb').val(customer.tollkreditFieldB);
						
					}else{
						//init fields
						jq('#siknk').val("");
						jq('#sirg').val("");
						jq('#sinak').val("");
						jq('#siadk1').val("");
						jq('#siadk2').val("");
						jq('#siadk3').val("");
						//tollkredit
						jq('#siktc').val("");
						jq('#sikta').val("");
						jq('#siktb').val("");

					}
				});
		    		//get free text
		    		setFreeTextReceiver(jq('#siknk').val());
	    		}
		});
	});
		
	//-----------------------------------
	//Receiver free text info (read-only)
	//-----------------------------------
	jq(function() { 
	    jq('#receiverFreeTextImg').click(function() {
	    		setFreeTextReceiver(jq('#siknk').val());
		});
	});
	//free text
	function setFreeTextReceiver(siknk) { 
		jq(function() {
		if(siknk!=null && siknk!=""){
	    		jq.getJSON('getCustomerInfoFreeText_TvinnSad.do', {
				applicationUser : jq('#applicationUser').val(),
				customerNumber : jq('#siknk').val(),
				delsystem : "K",
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
	    jq('#sival3').change(function() {
	    		//In Norway we must use the current day (today) as currency date, 
	    		//therefore we send = null. The AjaxController will take care of the rest
	    		var currencyDate = null; 
	    		getCurrencyData(currencyDate);
	    });
	});
	jq(function() { 
	    jq('#sivku').blur(function() {
	    		var currencyRate = jq('#sivku').val();
	    		if(currencyRate==null || currencyRate==""){
	    			//In Norway we must use the current day (today) as currency date, 
	    			//therefore we send = null. The AjaxController will take care of the rest
	    			var currencyDate = null; 
	    			getCurrencyData(currencyDate);
	    		}
	    });
	});
	//22.Fakt.sum
	jq(function() { 
	    jq('#sibel3').blur(function() {
	    		var currencyRate = jq('#sivku').val();
	    		var currency = jq('#sival3').val();
	    		if(currency!=null && currency!=""){
		    		if(currencyRate==null || currencyRate==""){
		    			//In Norway we must use the current day (today) as currency date, 
		    			//therefore we send = null. The AjaxController will take care of the rest
		    			var currencyDate = null; 
		    			getCurrencyData(currencyDate);
		    		}
	    		}
	    		//Whenever the total amount is empty and we are able to get an external proposal based on Finans Opplysn.
	    		//we should do this
    			if(jq('#sibel3').val()==""){
    				getFinansData();
    			}
	    });
	});
	//private function
	function getCurrencyData(currencyDate) {
		jq.ajax({
			type: 'GET',
			url: 'getCurrencyRate_SadImport.do',
			data: { 	applicationUser : jq('#applicationUser').val(),
					currencyCode : jq('#sival3').val(),
					isoDate : currencyDate} ,
			dataType: 'json',
			success: function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#sivku').val(data[i].kvakrs);
					jq('#factor').val(data[i].kvaomr);
				}
			}
		});
	}
	//============================
	//END - Currency AJAX fetch
	//============================

	
	//private function
	function getFinansData() {
		jq.ajax({
			type: 'GET',
			url: 'getFinansTotalSumAndRelatedFields_SadImport.do',
			data: { 	applicationUser : jq('#applicationUser').val(),
					avd : jq('#avd').val(),
					opd : jq('#opd').val()} ,
			dataType: 'json',
			success: function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#sibel3').val(data[i].calculatedItemLinesTotalAmount);
					jq('#sival3').val(data[i].calculatedValidCurrency);
					//In SAD-Import we must use the current day (today) as currency date, 
					//therefore we send = null. The AjaxController will take care of the rest
    	    				var currencyDate = null; 
    	    				getCurrencyData(currencyDate);
				}
			}
		});
	}
	
	
	//--------------------------------------------------------------------------
  	//onChange-VALIDATION on-demand event
	//This section should always be exactly the same as in the Spring Validator
	//(mandatory fields section of the Validator)
  	//--------------------------------------------------------------------------
	/*
	jq(function() { 
	    jq('#dkih_aart').change(function() {
	    		var aart = jq('#dkih_aart').val();
	    		
	    		if("" == aart){ 
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			
	    		}else if("01" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkih_dtm1'));highlightElement(jq('#v_dkih_181'));
	    			
	    		}else if("02" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkih_r012'));highlightElement(jq('#v_dkih_25'));
	    			highlightElement(jq('#v_dkih_dtm1'));
	    			
	    		}else if("03" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkih_r012'));highlightElement(jq('#v_dkih_26'));
	    			highlightElement(jq('#v_dkih_221'));highlightElement(jq('#v_dkih_222'));
	    			highlightElement(jq('#v_dkih_02b'));highlightElement(jq('#v_dkih_02c'));
	    			highlightElement(jq('#v_dkih_02d'));highlightElement(jq('#v_dkih_02e'));
	    			highlightElement(jq('#v_dkih_02f'));
	    			
	    		}else if("04" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkih_r012'));highlightElement(jq('#v_dkih_25'));
	    			highlightElement(jq('#v_dkih_26'));highlightElement(jq('#v_dkih_221'));
	    			highlightElement(jq('#v_dkih_222'));highlightElement(jq('#v_dkih_dtm1'));
	    			highlightElement(jq('#v_dkih_02b'));highlightElement(jq('#v_dkih_02c'));
	    			highlightElement(jq('#v_dkih_02d'));highlightElement(jq('#v_dkih_02e'));
	    			highlightElement(jq('#v_dkih_02f'));
	    			
	    		}else if("05" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkih_r012'));highlightElement(jq('#v_dkih_dtm1'));
	    			
	    		}else if("07" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkih_r012'));highlightElement(jq('#v_dkih_26'));
	    			highlightElement(jq('#v_dkih_221'));highlightElement(jq('#v_dkih_222'));
	    			highlightElement(jq('#v_dkih_02b'));highlightElement(jq('#v_dkih_02c'));
	    			highlightElement(jq('#v_dkih_02d'));highlightElement(jq('#v_dkih_02e'));
	    			highlightElement(jq('#v_dkih_02f'));
	    			
	    		}else if("08" == aart){ 
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkih_r012'));
	    			
	    		}
 
	    });
	    //private
		function highlightElement(element){
			element.css({ 'color': 'red', 'font-size': '112%' });
		}
		function initValidationClass(element){
			element.css({ 'color': 'black', 'font-size': '100%' });
		}
	});
	*/
	
	
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
		//setters (add more if needed)
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
	  
	//-------------------------------------------------
	  //START Model dialog SEND: "Send with parameters"
	  //-----------------------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogSendWithParameters").dialog({
			  autoOpen: false,
			  maxWidth:500,
	          maxHeight: 400,
	          width: 360,
	          height: 360,
			  modal: true
		  });
	  });
	  //----------------------------
	  //Present dialog box onClick 
	  //----------------------------
	  jq(function() {
		  jq("#sendButton").click(function() {
			  presentSendWithParametersDialog();
		  });
		  
	  });
	  function presentSendWithParametersDialog(){
		//setters (add more if needed)
		  jq('#dialogSendWithParameters').dialog( "option", "title", "Send tolldeklaration" );
		  //deal with buttons for this modal window
		  jq('#dialogSendWithParameters').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		jq('#sendWithParamtersForm').submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Cancel", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		//jq("#dialogSaveSU").button("option", "disabled", true);
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  //jq("#dialogSaveSU").button("option", "disabled", true);
		  //open now
		  jq('#dialogSendWithParameters').dialog('open');
	  }
	  
	  
	  

	  //-----------------------------------------
	  //START Model dialog "Kopiera Omberegning
	  //-----------------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogOmberegningPaOmberegning").dialog({
			  autoOpen: false,
			  maxWidth:350,
	          maxHeight: 250,
	          width: 350,
	          height: 250,
			  modal: true
		  });
	  });
	  //Present dialog box onClick (href in parent JSP)
	  jq(function() {
		  jq("#alinkOmberegningPaOmberegning").click(function() {
			  //setters (add more if needed)
			  jq('#dialogOmberegningPaOmberegning').dialog( "option", "title", "Ny Omberegning" );
			  //deal with buttons for this modal window
			  jq('#dialogOmberegningPaOmberegning').dialog({
				 buttons: [ 
		            {
					 id: "dialogSaveOMB",	
					 text: "Gå vidare",
					 click: function(){
						 		jq('#copyOmberegningPaOmberegningForm').submit();
					 		}
				 	 },
		 	 		{
				 	 id: "dialogCancelOMB",
				 	 text: "Avbryt", 
					 click: function(){
						 		//back to initial state of form elements on modal dialog
						 		//jq("#dialogSaveOMB").button("option", "disabled", true);
						 		jq("#selectedOmb").val("");
						 		jq( this ).dialog( "close" ); 
					 		} 
		 	 		 } ] 
			  });
			  
			  //init values
			  //jq("#dialogSaveOMB").button("option", "disabled", true);
			  //open now
			  jq('#dialogOmberegningPaOmberegning').dialog('open');
			  
		  });
	  });	  
	
	
	  
	  //-------------------------------------------
	  //START Model dialog: "Print skilleark"
	  //-------------------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogPrintSkilleArk").dialog({
			  autoOpen: false,
			  maxWidth:400,
	          maxHeight: 300,
	          width: 280,
	          height: 180,
			  modal: true
		  });
	  });
	  //----------------------------
	  //Present dialog box onClick 
	  //----------------------------
	  jq(function() {
		  jq("#printSkilleArkImg").click(function() {
			  presentPrintSkilleArkDialog();
		  });
		  
	  });
	  function presentPrintSkilleArkDialog(){
		//setters (add more if needed)
		  jq('#dialogPrintSkilleArk').dialog( "option", "title", "Print Skilleark" );
		  //deal with buttons for this modal window
		  jq('#dialogPrintSkilleArk').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		jq('#skilleArkForm').submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Avbryt", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSaveTU").button("option", "disabled", true);
					 		jq("#selectedType").val("");
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveTU").button("option", "disabled", true);
		  //open now
		  jq('#dialogPrintSkilleArk').dialog('open');
	  }
	  //Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
	  jq(function() {
		  jq("#selectedType").change(function() {
			  if(jq("#selectedType").val()!=''){
				  jq("#dialogSaveTU").button("option", "disabled", false);
				  
			  }else{
				  jq("#dialogSaveTU").button("option", "disabled", true);
			  }
		  });
		  
	  });
	  //-------------------------------------------
	  //END Model dialog: "Print skilleark"
	  //-------------------------------------------
	
	  
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
	  
	  //Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
	  jq(function() {
		  jq("#fileUpload").change(function() {
			  uploadFile();
		  });
		  
	  });
	  //Upload file
	  function uploadFile(){
			//grab all form data  
			  var form = new FormData(document.getElementById('uploadFileForm'));
			  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
			  
			  jq.ajax({
			  	  type: 'POST',
			  	  url: 'uploadFileToArchive.do',
			  	  data: form,  
			  	  dataType: 'text',
			  	  cache: false,
			  	  processData: false,
			  	  contentType: false,
		  		  success: function(data) {
				  	  var len = data.length;
			  		  if(len>0){
			  			jq("#file").val("");
					  	//Check for errors or successfully processed
					  	var exists = data.indexOf("ERROR");
					  	if(exists>0){
					  		//ERROR on back-end
					  		jq("#file").addClass( "isa_error" );
					  		jq("#file").removeClass( "isa_success" );
					  	}else{
					  		//OK
					  		jq("#file").addClass( "isa_success" );
					  		jq("#file").removeClass( "isa_error" );
					  	}
					  	//response to end user 
					  	alert(data);
					  	if(data.indexOf('[OK') == 0) {
						  	var trip = '';
						  	var avd = jq("#wsavd").val();
						  	var opd = jq("#wsopd").val();
						  	var sign = jq("#sign").val();
						  	//reload
						  	window.location = "tvinnsadimport_edit.do?action=doFetch&avd=" + avd + "&opd=" + opd + "&sysg=" +  sign;
					  	}
					  	//unblock
					  	jq.unblockUI();
			  		  }
			  	  }, 
			  	  error: function() {
			  		  jq.unblockUI();
			  		  alert('Error loading ...');
			  		  jq("#file").val("");
			  		  //cosmetics
			  		  jq("#file").addClass( "isa_error" );
			  		  jq("#file").removeClass( "isa_success" );
				  }
			  });
			    
			  
		  }
	  
	  //-------------------------------------------
	  //END Model dialog: "File upload"
	  //-------------------------------------------
	
	