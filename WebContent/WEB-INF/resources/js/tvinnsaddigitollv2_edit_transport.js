id="alinkTransport"  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  //Overlay on tab (to mark visually a delay...)
  jq(function() {
	
	jq('#alinkTransportList').click(function() { 
    	setBlockUI();
    });
    jq('#alinkMaster').click(function() { 
    	setBlockUI();
    });
	jq('#alinkTransportList').click(function() { 
    	setBlockUI();
    });
    jq('.clazz_gotoHouse').click(function() { 
    	setBlockUI();
    });

    
    jq('#transportForm').submit(function() { 
    	setBlockUI();
    });
    
    jq('#sendButton').click(function() {
		var async_own = "";
		if (jq("#async").length){ //check if it exists 
			if (jq('#async').is(':checked')) {
				async_own = jq('#async').val();
			}
		}
		jq('#dialogSend').dialog( "option", "title", "Send til toll.no" );
		  //deal with buttons for this modal window
		  jq('#dialogSend').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		setBlockUI();
							window.location = 'tvinnsaddigitollv2_api_send_transport.do?etlnrt=' + jq('#etlnrt').val() + '&etmid=' + jq('#etmid').val() + '&async=' + async_own;
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Cancel", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSaveSU").button("option", "disabled", true);
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveSU").button("option", "disabled", true);
		  //open now
		  jq('#dialogSend').dialog('open');
		  	
    });

	//Real delete to Api (DELETE)
 	jq('#deleteButton').click(function() { 
    	  jq('#dialogDelete').dialog( "option", "title", "Slett fra toll.no" );
		  //deal with buttons for this modal window
		  jq('#dialogDelete').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		setBlockUI();
							window.location = 'tvinnsaddigitollv2_api_delete_transport.do?layer=1&current_id1=' + jq('#etlnrt').val() + '&current_mrn=' + jq('#etmid').val() + '&action=doDelete' ;
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Cancel", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSaveSU").button("option", "disabled", true);
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveSU").button("option", "disabled", true);
		  //open now
		  jq('#dialogDelete').dialog('open');
    });
	
	//Update Mrn with orignal Mrn ... this is used when toll.no had some problem with the MRN) 
 	jq('#updateMrnWithOriginalMrnButton').click(function() { 
    	  jq('#dialogReset').dialog( "option", "title", "Oppdater MRN i databasen" );
		  //deal with buttons for this modal window
		  jq('#dialogReset').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		setBlockUI();
							window.location = 'tvinnsaddigitollv2_resetMrn_transport.do?layer=1&etlnrt=' + jq('#etlnrt').val() + '&etmid_own=' + jq('#etmid_own').val() + '&action=doReset' ;
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Cancel", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSaveSU").button("option", "disabled", true);
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveSU").button("option", "disabled", true);
		  //open now
		  jq('#dialogReset').dialog('open');
    });    

	//Reset Mrn manually (when toll.no never delivered the MRN despite having created it)
 	jq('#updateMrnManuallyWithOriginalMrnButton').click(function() { 
		//bara vid input value
		if(jq('#etmid_manual').val()){
    	  jq('#dialogResetManually').dialog( "option", "title", "Oppdater MRN i databasen med egen verdi" );
		  //deal with buttons for this modal window
		  jq('#dialogResetManually').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		setBlockUI();
							window.location = 'tvinnsaddigitollv2_resetMrnManually_transport.do?layer=1&etlnrt=' + jq('#etlnrt').val() + '&etmid_own=' + jq('#etmid_manual').val() + '&action=doResetManually' ;
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Cancel", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSaveSU").button("option", "disabled", true);
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveSU").button("option", "disabled", true);
		  //open now
		  jq('#dialogResetManually').dialog('open');
		};
    });    

	

	//Refresh
    jq('#alinkRefreshButton').click(function() { 
    	setBlockUI();
    });



    jq('#imgManifestIdInfo').click(function() { 
    	jq('#imgManifestIdInfo').attr('target','_blank');
	  	window.open('tvinnsadmanifest_childwindow_manifestinfo.do?id=' + jq('#efuuid').val(), "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");

    });
    jq('#alinkManifestRawIdInfo').click(function() { 
    	jq('#alinkManifestRawIdInfo').attr('target','_blank');
	  	window.open('tvinnsadmanifest_childwindow_manifestinfo.do?id=' + jq('#efuuid').val() + "&raw=1", "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");

    });

	//External Doc.nr import (MasterId import). When an external party owns its master id and must be imported by the tranport owner (special UC)
	jq('#importMasterLink').click(function() {
    	jq('#importMasterLink').attr('target','_blank');
    	window.open('tvinnsaddigitollv2_childwindow_external_master.do?action=doInit&date=20240101' + '&ctype=etlnrt' + '&etlnrt=' + jq('#etlnrt').val(), "masterffWin", "top=300px,left=500px,height=600px,width=1400px,scrollbars=no,status=no,location=no");
    });
    jq('#importMasterLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#emdkm_ffIdLink').click();
		}
    });
    
	//--------------------
  	//Tullkontor - ettsd
  	//--------------------
    jq('#ettsdIdLink').click(function() {
    	jq('#ettsdIdLink').attr('target','_blank');
    	window.open('tvinnsaddigitollv2_childwindow_tullkontor.do?action=doInit&tkkode=' + jq('#ettsd').val() + '&ctype=ettsd', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#ettsdIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#ettsdIdLink').click();
		}
    });

	//--------------------
  	//Tur - etpro
  	//--------------------
    jq('#etproIdLink').click(function() {
    	jq('#etproIdLink').attr('target','_blank');
    	window.open('tvinnsaddigitollv2_childwindow_tur.do?action=doInit&tudt=20240801' + '&tupro=' + jq('#etpro').val() + '&tuavd=' + jq('#etavd').val()  + '&ctype=etpro', "turWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#etproIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#etproIdLink').click();
		}
    });
    //Fejk for looking
	jq('#etproIdLinkFejk').click(function() {
    	jq('#etproIdLinkFejk').attr('target','_blank');
    	window.open('tvinnsaddigitollv2_childwindow_tur.do?action=doInit&tudt=20240801' + '&tupro=' + jq('#etpro').val() + '&tuavd=' + jq('#etavd').val()  + '&ctype=etproFEJK', "turWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    
    
  });
  
  jq(function() {
	  jq("#etetad").datepicker({ 
		  dateFormat: 'ddmmy' 	  
	  });
	  jq("#etshed").datepicker({ 
		  dateFormat: 'ddmmy' 	  
	  });

	  
	  //custom validity
	    jq('#ettsd').focus(function() {
	    	if(jq('#ettsd').val()!=''){
	    		refreshCustomValidity(jq('#ettsd')[0]);
	  		}
	  	});
	    jq('#etkmrk').focus(function() {
	    	if(jq('#etkmrk').val()!=''){
	    		refreshCustomValidity(jq('#etkmrk')[0]);
	  		}
	  	});
		//Transportør
		jq('#etnat').focus(function() {
	    	if(jq('#etnat').val()!=''){
	    		refreshCustomValidity(jq('#etnat')[0]);
	  		}
	  	});
		jq('#etrgt').focus(function() {
	    	if(jq('#etrgt').val()!=''){
	    		refreshCustomValidity(jq('#etrgt')[0]);
	  		}
	  	});
		jq('#etpst').focus(function() {
	    	if(jq('#etpst').val()!=''){
	    		refreshCustomValidity(jq('#etpst')[0]);
	  		}
	  	});
		jq('#etlkt').focus(function() {
	    	if(jq('#etlkt').val()!=''){
	    		refreshCustomValidity(jq('#etlkt')[0]);
	  		}
	  	});
		//Represent./Ombud
		jq('#etnar').focus(function() {
	    	if(jq('#etnar').val()!=''){
	    		refreshCustomValidity(jq('#etnar')[0]);
	  		}
	  	});
		jq('#etrgr').focus(function() {
	    	if(jq('#etrgr').val()!=''){
	    		refreshCustomValidity(jq('#etrgr')[0]);
	  		}
	  	});
		jq('#etpsr').focus(function() {
	    	if(jq('#etpsr').val()!=''){
	    		refreshCustomValidity(jq('#etpsr')[0]);
	  		}
	  	});
		jq('#etlkr').focus(function() {
	    	if(jq('#etlkr').val()!=''){
	    		refreshCustomValidity(jq('#etlkr')[0]);
	  		}
	  	});

		//		
		jq('#etktkd').focus(function() {
	    	if(jq('#etktkd').val()!=''){
	    		refreshCustomValidity(jq('#etktkd')[0]);
	  		}
	  	});
		jq('#etktyp').focus(function() {
	    	if(jq('#etktyp').val()!=''){
	    		refreshCustomValidity(jq('#etktyp')[0]);
	  		}
	  	});
		jq('#etktm').focus(function() {
	    	if(jq('#etktm').val()!=''){
	    		refreshCustomValidity(jq('#etktm')[0]);
	  		}
	  	});
		jq('#etklk').focus(function() {
	    	if(jq('#etklk').val()!=''){
	    		refreshCustomValidity(jq('#etklk')[0]);
	  		}
	  	});

		jq('#etsjaf').focus(function() {
	    	if(jq('#etsjaf').val()!=''){
	    		refreshCustomValidity(jq('#etsjaf')[0]);
	  		}
	  	});
		jq('#etems').focus(function() {
	    	if(jq('#etems').val()!=''){
	    		refreshCustomValidity(jq('#etems')[0]);
	  		}
	  	});
	  
		
		
		jq('#etavd').focus(function() {
	    	if(jq('#etavd').val()!=''){
	    		refreshCustomValidity(jq('#etavd')[0]);
	  		}
	  	});
		jq('#etsg').focus(function() {
	    	if(jq('#etsg').val()!=''){
	    		refreshCustomValidity(jq('#etsg')[0]);
	  		}
	  	});
		jq('#etpro').focus(function() {
	    	if(jq('#etpro').val()!=''){
	    		refreshCustomValidity(jq('#etpro')[0]);
	  		}
	  	});
		jq('#etetad').focus(function() {
	    	if(jq('#etetad').val()!=''){
	    		refreshCustomValidity(jq('#etetad')[0]);
	  		}
	  	});
		jq('#etetat').focus(function() {
	    	if(jq('#etetat').val()!=''){
	    		refreshCustomValidity(jq('#etetat')[0]);
	  		}
	  	});
	  
	  //Carrier
      jq('#etnatIdLink').click(function() {
	    	jq('#etnatIdLink').attr('target','_blank');
	    	window.open('tvinnsaddigitollv2_childwindow_customer.do?action=doFind&sonavn=' + jq('#etnat').val() + '&ctype=etnat', "codeWin", "top=300px,left=500px,height=600px,width=950px,scrollbars=no,status=no,location=no");
	    });
	  jq('#etnatIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#etnatIdLink').click();
		}
	  });
	  //Representative
      jq('#etnarIdLink').click(function() {
	    	jq('#etnarIdLink').attr('target','_blank');
	    	window.open('tvinnsaddigitollv2_childwindow_customer.do?action=doFind&sonavn=' + jq('#etnar').val() + '&ctype=etnar', "codeWin", "top=300px,left=500px,height=600px,width=950px,scrollbars=no,status=no,location=no");
	    });
	  jq('#etnarIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#etnarIdLink').click();
		}
	  });	
  });

	//================================= 
	//Default-values from SADMOAF
	//================================= 
	jq(function() {
		jq('#etavd').blur(function() {
			
			if(jq('#etavd').val() != ""){
				var	_avd = jq('#etavd').val();
				
				jq.ajax({
			  	  type: 'GET',
			  	  url: 'searchDefaultValuesTransport_Digitoll.do',
			  	  data: { applicationUser : jq('#applicationUser').val(),
						  avd : _avd}, 
			  	  dataType: 'json',
			  	  cache: false,
			  	  contentType: 'application/json',
			  	  success: function(data) {
					//alert("Hello");
					var len = data.length;
					if(len>0){
						for ( var i = 0; i < len; i++) {
							if(data[i].etpro < 0){
								jq('#etpro').val(data[i].etpro);//Turnr
								jq('#etsg').val(data[i].etsg);//sign
							}
							//From default values: etktkd etktyp etktm etklk
							if(data[i].etktkd != ''){
								jq('#etktkd').val(data[i].etktkd);//Mode.Tr
							}
							if(data[i].etktyp != ''){
								jq('#etktyp').val(data[i].etktyp);//Kjør.Typ.
							}
							if(data[i].etktm != ''){
								jq('#etktm').val(data[i].etktm);//Tr.midd.typ.
							}
							if(data[i].etklk != ''){
								jq('#etklk').val(data[i].etklk);//Land
							}
							if(data[i].ettsd != ''){
								jq('#ettsd').val(data[i].ettsd);//pass.tollstedettsd
							}
							if(data[i].etsjaf != ''){
								jq('#etsjaf').val(data[i].etsjaf);//Fører-navn
							}
							jq('#etnar').val(data[i].etnar);//Ombud navn
							jq('#etrgr').val(data[i].etrgr);//Ombud Orgnr
							jq('#etpsr').val(data[i].etpsr);//Ombud Sted
							jq('#etlkr').val(data[i].etlkr);//Ombud landkod
							jq('#etad1r').val(data[i].etad1r);//Ombud adress
							jq('#etpnr').val(data[i].etpnr);//Ombud Postnr
							//alert(data[i].etemrt + data[i].etemr);
							if(data[i].etemrt == 'TE'){
								jq('#own_etemr_telephone').val(data[i].etemr);//Ombud telephone
							}else if(data[i].etemrt == 'EM'){
								jq('#own_etemr_email').val(data[i].etemr);//Ombud epost
							}
					 	}
					}else{
						jq('#etnar').val("");//Ombud navn
						jq('#etrgr').val("");//Ombud Orgnr
						jq('#etpsr').val("");//Ombud Sted
						jq('#etlkr').val("");//Ombud landkod
						jq('#etad1r').val("");//Ombud adress
						jq('#etpnr').val("");//Ombud Postnr
						jq('#own_etemr_email').val("");//Ombud epost
						jq('#own_etemr_telephone').val("");//Ombud telephone
					}
					},
				  	  error: function() {
				  	    alert('Error loading ...');
				  	}			
				});	
			}
		});
	});


	//==================================
	//Tur values from TDIG001R.pgm
	//==================================
	jq(function() { 
	    jq('#etpro').blur(function() {
			if(jq('#etpro').val() != ""){
				
				jq.ajax({
			  	  type: 'GET',
			  	  url: 'searchTur_Digitoll.do',
			  	  data: { applicationUser : jq('#applicationUser').val(),
 						  avd : jq('#etavd').val(),
			  		  	  turNr : jq('#etpro').val(), 
			  		  	  fromDate : "20240801"}, 
			  	  dataType: 'json',
			  	  cache: false,
			  	  contentType: 'application/json',
			  	  success: function(data) {
					//alert("Hello");
					var len = data.length;
					if(len>0){
						  for ( var i = 0; i < len; i++) {
							if(data[i].own_ErrMsg != ''){
								jq('#etpro').addClass("isa_warning");
								//initTurFields(); har removats pga av Schenker som inte har turer... den är på prov eftersom de klagade som fan	
							}else{
								jq('#etpro').removeClass("isa_warning");
								
								if(data[i].tusg != ''){
									jq('#etsg').val(data[i].tusg);//signatur
								}else{
									jq('#etsg').val(jq('#applicationUserSign').val());//signatur from login
								}
								//From Tur
								if(data[i].tuavd != '' && jq('#etavd').val()==''){
									jq('#etavd').val(data[i].tuavd);//avd
								}
								jq('#etkmrk').val(data[i].tubiln);//Bilnr.
								if(data[i].tusjn1 != ''){
									jq('#etsjaf').val(data[i].tusjn1);//Fører-navn
								}
								if(data[i].tutrma != ''){
									jq('#etktyp').val(data[i].tutrma); //transportmåte
								}
								if(data[i].tulk != ''){
									jq('#etklk').val(data[i].tulk); //Landkode bil
								}
								if(data[i].tueta != ''){
									jq('#etetad').val(data[i].tueta); //eta
								}
								if(data[i].tuto1a != ''){
									jq('#ettsd').val(data[i].tuto1a); //pass.tollsted
								}
								//CARRIER (either from HEADf (customer) or from tur-pgm)
								if(data[i].tuknt != ''){
									jq('#etknt').val(data[i].tuknt);//cust.nr.
									fetchCarrier();
								}else{
									jq('#etknt').val("");//cust.nr.
									jq('#etnat').val(data[i].tunat);//transp. navn
									if(data[i].tuad1t != ''){	
										jq('#etad1t').val(data[i].tuad1t);//transp.adress
									}else{
										jq('#etad1t').val(data[i].tuad2t);//transp.adress
									}
									//Sted transp.
									jq('#etpst').val(data[i].tuad3t);//varies depending on customer. We just print out what comes inside tuad3t...
									//Landkode transp.
									jq('#etlkt').val(data[i].tulk);
									
								}
							}
						  }
					 }else{
						jq('#etpro').removeClass("isa_error");
						jq('#etpro').removeClass("isa_warning");
						initTurFields();	
					 }	
					 
					},
				  	  error: function() {
				  	    alert('Error loading ...');
				  	  }
					});	

				}
		});	
		
			
	});
	
 function initTurFields(){
	jq(function() { 
		//jq('#etavd').val("");//avd.
		jq('#etsg').val("");//signatur
		jq('#etkmrk').val("");//Bilnr.
		jq('#etsjaf').val("");//Fører-navn
		jq('#etetad').val(""); //eta
		//jq('#ettsd').val(""); //pass.tollsted
		
		//CARRIER (either from HEADf (customer) or from tur-pgm)
		jq('#etknt').val("");//cust.nr.
		jq('#etnat').val("");//transp. navn
		jq('#etad1t').val("");//transp.adress
		//Sted transp.
		jq('#etpst').val("");//varies depending on customer. We just print out what comes inside tuad3t...
		//Landkode transp.
		jq('#etlkt').val("");
					
	});
}

  	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#etknt').blur(function() {
			if(jq('#etnat').val()==''){
	    		fetchCarrier();	
			}
		});
		jq('#etknr').blur(function() {
			if(jq('#etnar').val()==''){
	    		fetchRepresentative();	
			}
		});
	});
	  
  	function fetchCarrier(){
	  var customerNr = jq.trim(jq('#etknt').val());
		
		if(customerNr!=""){
  		jq.getJSON('searchCustomer_Digitoll.do', {
			applicationUser : jq('#applicationUser').val(),
			customerName : "",
			customerNumber : jq('#etknt').val(),
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
				customer.orgnr = data[i].syrg;
				customer.adr1 = data[i].adr1;
				customer.adr2 = data[i].adr2;
				customer.adr3 = data[i].adr3;
				customer.postnr = data[i].postnr;//data[i].postnr; DK=sypoge
				customer.kpers = data[i].kpers;
				customer.tlf = data[i].tlf;
				customer.syepos = data[i].syepos;
				customer.syland = data[i].syland;
			  	//put the object in map now with customerNumber as key
				map[customer.kundnr] = customer;
			}
			if(len > 0){
				jq('#etknt').val(customer.kundnr);
				jq('#etnat').val(customer.knavn); refreshCustomValidity(jq('#etnat')[0]);
				if('' != customer.eori){
					jq('#etrgt').val(customer.eori);refreshCustomValidity(jq('#etrgt')[0]);
				}else{
					jq('#etrgt').val(customer.orgnr);refreshCustomValidity(jq('#etrgt')[0]);
				}
				jq('#etpst').val(customer.adr3);refreshCustomValidity(jq('#etpst')[0]);
				jq('#etlkt').val(customer.syland);refreshCustomValidity(jq('#etlkt')[0]);
				jq('#etpnt').val(customer.postnr);
				jq('#etad1t').val(customer.adr1);
				jq('#ettppt').val("2"); //bedrift
				if('' != customer.tlf){
					jq('#own_etemt_telephone').val(customer.tlf);
				}else{
					jq('#own_etemt_email').val(customer.syepos);
				}
			}else{
				//init fields
				jq('#etknt').val("");
				jq('#etnat').val("");
				jq('#etrgt').val("");
				jq('#etpst').val("");
				jq('#etlkt').val("");
				jq('#etpnt').val("");
				jq('#etad1t').val("");
				jq('#own_etemt_telephone').val("");
				jq('#own_etemt_email').val("");
			}
		});
  		
		}else{
			jq('#etknt').val("");
				jq('#etnat').val("");
				jq('#etrgt').val("");
				jq('#etpst').val("");
				jq('#etlkt').val("");
				jq('#etpnt').val("");
				jq('#etad1t').val("");
				jq('#own_etemt_telephone').val("");
				jq('#own_etemt_email').val("");
		}
  	}
	function fetchRepresentative(){
	  var customerNr = jq.trim(jq('#etknr').val());
		
		if(customerNr!=""){
  		jq.getJSON('searchCustomer_Digitoll.do', {
			applicationUser : jq('#applicationUser').val(),
			customerName : "",
			customerNumber : jq('#etknr').val(),
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
				customer.orgnr = data[i].syrg;
				customer.adr1 = data[i].adr1;
				customer.adr2 = data[i].adr2;
				customer.adr3 = data[i].adr3;
				customer.postnr = data[i].postnr;//data[i].postnr; DK=sypoge
				customer.kpers = data[i].kpers;
				customer.tlf = data[i].tlf;
				customer.syepos = data[i].syepos;
				customer.syland = data[i].syland;
			  	//put the object in map now with customerNumber as key
				map[customer.kundnr] = customer;
			}
			if(len > 0){
				jq('#etknr').val(customer.kundnr);
				jq('#etnar').val(customer.knavn); refreshCustomValidity(jq('#etnar')[0]);
				if('' != customer.eori){
					jq('#etrgr').val(customer.eori);refreshCustomValidity(jq('#etrgr')[0]);
				}else{
					jq('#etrgr').val(customer.orgnr);refreshCustomValidity(jq('#etrgr')[0]);
				}
				
				jq('#etpsr').val(customer.adr3);refreshCustomValidity(jq('#etpsr')[0]);
				jq('#etlkr').val(customer.syland);refreshCustomValidity(jq('#etlkr')[0]);
				jq('#etpnr').val(customer.postnr);
				jq('#etad1r').val(customer.adr1);
				jq('#ettppr').val("2"); //bedrift
				
				if('' != customer.tlf){
					jq('#own_etemr_telephone').val(customer.tlf);
				}else{
					jq('#own_etemr_email').val(customer.syepos);
				}
				
			}else{
				//init fields
				jq('#etknr').val("");
				jq('#etnar').val("");
				jq('#etrgr').val("");
				jq('#etpsr').val("");
				jq('#etlkr').val("");
				jq('#etpnr').val("");
				jq('#etad1r').val("");
				jq('#own_etemr_telephone').val("");
				jq('#own_etemr_email').val("");
			}
		});
  		
		}else{
			jq('#etknr').val("");
			jq('#etnar').val("");
			jq('#etrgr').val("");
			jq('#etpsr').val("");
			jq('#etlkr').val("");
			jq('#etpnr').val("");
			jq('#etad1r').val("");
			jq('#own_etemr_telephone').val("");
			jq('#own_etemr_email').val("");
		}
  }
  
/*
  jq(function() { 
	    jq('#etrgt').blur(function() {
			if(jq('#etrgt').val() != ""){
				//only with EORI and not with OrgNr
				var eoriStart = new RegExp('[A-Z]{2}');
				if(eoriStart.test(jq('#etrgt').val())){
					//var field = jq('#etrgt');
						validateEORI(jq('#etrgt'))
						
						jq.ajax({
					  	  type: 'GET',
					  	  url: 'getEORIValidation_Digitoll.do',
					  	  data: { applicationUser : jq('#applicationUser').val(),
		 						  eori : jq('#etrgt').val()}, 
					  	  dataType: 'json',
					  	  cache: false,
					  	  contentType: 'application/json',
					  	  
						  success: function(data) {
							console.log("Hello eori validation...");
							var len = data.length;
							if(len>0){
								  for ( var i = 0; i < len; i++) {
									if(data[i].status == 0){ //Valid
										jq('#etrgt').removeClass("isa_error");
										//jq('#etrgt').addClass("isa_success");
											
									}else{
										jq('#etrgt').addClass("isa_error");
									}
								  }
							 }else{
								jq('#etrgt').addClass("isa_error");
							 }	
							 
							},
						  	  error: function() {
						  	    alert('Error loading ...');
						  	 }	
						});
						
				}else{
					jq('#etrgt').removeClass("isa_error");
				}	
			}else{
				jq('#etrgt').removeClass("isa_error");
				//jq('#etrgt').removeClass("isa_success");
			}

		});
  });
*/
jq(function() { 
	jq('#etrgt').blur(function() {
		validateEORI(jq('#etrgt'))
				
 	});

	jq('#etrgr').blur(function() {
		validateEORI(jq('#etrgr'))
				
 	});
	//SYS should be seen!
	jq('#etsg').change(function() {
		if(jq('#etsg').val()=='SYS'){
			jq('#etsg').addClass("isa_error");
		}else{
			jq('#etsg').removeClass("isa_error");
		}
				
 	});
	

 });	
function validateEORI(field){
	console.log("eoriValidation:" + jq('#eoriValidationActive').val());
	if(field.val() != "" && jq('#eoriValidationActive').val() >0){
	  	//only with EORI and not with OrgNr
	  	var eoriStart = new RegExp('[A-Z]{2}');
	  	if(eoriStart.test(field.val())){
	  
				jq.ajax({
			  	  type: 'GET',
			  	  url: 'getEORIValidation_Digitoll.do',
			  	  data: { applicationUser : jq('#applicationUser').val(),
						  eori : field.val()}, 
			  	  dataType: 'json',
			  	  cache: false,
			  	  contentType: 'application/json',
			  	  
				  success: function(data) {
					console.log("success(1) eori validation...");
					
					var len = data.length;
					if(len>0){
						  console.log("level-2 eori validation..." + len);
					
						  for ( var i = 0; i < len; i++) {
							console.log("level-2.1 eori validation...");
							if(data[i].status == 0){ //Valid
								console.log("level-3 eori validation...");
								field.removeClass("isa_error");
								//jq('#etrgt').addClass("isa_success");
									
							}else{
								field.addClass("isa_error");
								console.log("addClass-isa_error_ok");
							}
						  }
					 }else{
						field.addClass("isa_error");
						console.log("addClass-isa_error_ok on len<0");
					 }	
					 
					},
				  	  error: function() {
				  	    console.log("ERROR on service... check logs ...");
				  	 }	
				});
		}else{
			field.removeClass("isa_error");
		}	
	}else{
		field.removeClass("isa_error");
		//field.removeClass("isa_success");
	}
}


  

  //-------------------------------------------
  //START Model dialog ADMIN: "Update status"
  //-------------------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq("#dialogUpdateInternalStatus").dialog({
		  autoOpen: false,
		  maxWidth:500,
          maxHeight: 400,
          width: 280,
          height: 220,
		  modal: true
	  });
  });
//Initialize <div> here
  jq(function() { 
	  jq("#dialogUpdateInternalStatus2").dialog({
		  autoOpen: false,
		  maxWidth:500,
          maxHeight: 400,
          width: 280,
          height: 220,
		  modal: true
	  });
  });
  jq(function() { 
	  jq("#dialogUpdateInternalStatus3").dialog({
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
	 jq("#updateInternalStatus3Link").click(function() {
		  presentChangeInternalStatus3Dialog();
	  });
	  jq("#updateInternalStatus2Link").click(function() {
		  presentChangeInternalStatus2Dialog();
	  });
	  jq("#updateInternalStatusLink").click(function() {
		  presentChangeInternalStatusDialog();
	  });
	  
  });
  function presentChangeInternalStatusDialog(){
		//setters (add more if needed)
		  jq('#dialogUpdateInternalStatus').dialog( "option", "title", "Update Internal Status" );
		  //deal with buttons for this modal window
		  jq('#dialogUpdateInternalStatus').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		jq('#updateInternalStatusForm').submit();
					 		setBlockUI();
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
		  jq('#dialogUpdateInternalStatus').dialog('open');
	  }
  
  
  function presentChangeInternalStatus2Dialog(){
		//setters (add more if needed)
		  jq('#dialogUpdateInternalStatus2').dialog( "option", "title", "Update Internal Status 2" );
		  //deal with buttons for this modal window
		  jq('#dialogUpdateInternalStatus2').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		jq('#updateInternalStatusForm2').submit();
					 		setBlockUI();
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
		  jq('#dialogUpdateInternalStatus2').dialog('open');
	  }
  
 	function presentChangeInternalStatus3Dialog(){
		//setters (add more if needed)
		  jq('#dialogUpdateInternalStatus3').dialog( "option", "title", "Update Internal Status 3" );
		  //deal with buttons for this modal window
		  jq('#dialogUpdateInternalStatus3').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		jq('#updateInternalStatusForm3').submit();
					 		setBlockUI();
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
		  jq('#dialogUpdateInternalStatus3').dialog('open');
	  }
  
 

//Initialize <div> here for all clazz_dialog
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			maxWidth:500,
			maxHeight: 400,
			width: 300,
			height: 180,
			modal: true
		});
	  });
  });

  //----------------------------------------------------------------
  //START Model dialog: "Delete Master" (Api and Db OR only Locally
  //----------------------------------------------------------------
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".removeLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("removeLink","");
		  
		  jq('#dialogUpdateStatus'+counterIndex).dialog( "option", "title", "Slett Master Consignment " + jq('#current_id1'+counterIndex).val() + "/" + jq('#current_id2'+counterIndex).val() );
		  //deal with buttons for this modal window
		  jq('#dialogUpdateStatus'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
							setBlockUI();
					 		jq('#updateStatusForm'+counterIndex).submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU"+counterIndex,
			 	 text: "Cancel", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSaveSU"+counterIndex).button("option", "disabled", true);
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveSU"+counterIndex).button("option", "disabled", true);
		  //open now
		  jq('#dialogUpdateStatus'+counterIndex).dialog('open');
		 
	  });
  });

  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".cancelLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("cancelLink","");
		  
		  jq('#dialogUpdateInternalStatus'+counterIndex).dialog( "option", "title", "Kansellere i SYSPED " );
		  //deal with buttons for this modal window
		  jq('#dialogUpdateInternalStatus'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
							setBlockUI();
					 		jq('#updateInternalStatusForm'+counterIndex).submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU"+counterIndex,
			 	 text: "Cancel", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSaveSU"+counterIndex).button("option", "disabled", true);
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveSU"+counterIndex).button("option", "disabled", true);
		  //open now
		  jq('#dialogUpdateInternalStatus'+counterIndex).dialog('open');
		 
	  });
  });

  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".grantLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("grantLink","");
		  
		  jq('#dialogUpdateInternalStatusGrant'+counterIndex).dialog( "option", "title", "Gjøre tilgjengelig igjen " );
		  //deal with buttons for this modal window
		  jq('#dialogUpdateInternalStatusGrant'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
							setBlockUI();
					 		jq('#updateInternalStatusGrantForm'+counterIndex).submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU"+counterIndex,
			 	 text: "Cancel", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSaveSU"+counterIndex).button("option", "disabled", true);
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveSU"+counterIndex).button("option", "disabled", true);
		  //open now
		  jq('#dialogUpdateInternalStatusGrant'+counterIndex).dialog('open');
		 
	  });
  });


	jq(function() {
	  	//delete to SYSPED
 		jq('#deleteTransportButton').click(function() { 
    	  jq('#dialogDeleteTransport').dialog( "option", "title", "Fjerne fra Sysped " );
		  //deal with buttons for this modal window
		  jq('#dialogDeleteTransport').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		setBlockUI();
							window.location = 'tvinnsaddigitollv2_delete_transport.do?current_id1=' + jq('#etlnrt').val();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Cancel", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSaveSU").button("option", "disabled", true);
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveSU").button("option", "disabled", true);
		  //open now
		  jq('#dialogDeleteTransport').dialog('open');
    	});
	});
 

 jq(function() {
	  jq(".uuidLink").click(function() {
		  var id = this.id;
		  jq("#"+id).attr(('target','_blank'));
		  
		  var apiType = "";
		  //check if this is an AIR api record
		  if(jq("#airplaneImg" + id).length > 0) { 
			apiType = "air";	
		  }else if(jq("#railImg" + id).length > 0) { 
			apiType = "rail";	
		  }			
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_manifestinfo.do?id=" + id +"&level=m" + "&apiType=" + apiType ;

		  if(id.length<35){ //meaning MRN and not LRN (only at Master Consignment level)
			 controllerUrl = "tvinnsaddigitollv2_childwindow_masterdocs_rec.do?id=" + id;
			 if(apiType == "rail"){
			 	controllerUrl = "tvinnsaddigitollv2_childwindow_masterdocs_rec_rail.do?id=" + id;
			 }
		  }
	      
		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");	
			
	  });
  });

  jq(function() {
	  jq(".uuidLinkParent").click(function() {
		  var id = this.id;
		  jq("#"+id).attr(('target','_blank'));
		  var apiType = "";
		  //check if this is an AIR api record
		  if(jq("#airplaneImg").length > 0) { 
			apiType = "air";	
		  }else if(jq("#railImg").length > 0) { 
			apiType = "rail";	
		  }	
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_manifestinfo.do?id=" + id +"&level=t" + "&apiType=" + apiType;

		  if(id.length<35){ //meaning MRN and not LRN (only at Master Consignment level)
			 controllerUrl = "tvinnsaddigitollv2_childwindow_transportdocs_rec.do?id=" + id; 
			 if(apiType == "rail"){
			 	controllerUrl = "tvinnsaddigitollv2_childwindow_transportdocs_rec_rail.do?id=" + id;
			 }else if(apiType == "air"){
			 	controllerUrl = "tvinnsaddigitollv2_childwindow_transportdocs_rec_air.do?id=" + id;
			 } 
		
		
		  }
		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");	
			
	  });

	 jq("#airplaneImg").click(function() {
		if(jq("#etmid").val()!='') { 
		  var id = this.id;
		  jq("#"+id).attr(('target','_blank'));
		  //default url
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_routinginfo.do?level=t";
		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=1200px,scrollbars=yes,status=no,location=no");	
		 }	
	  });	
	 jq("#railImg").click(function() {
		if(jq("#etmid").val()!='') { 
		  var id = this.id;
		  jq("#"+id).attr(('target','_blank'));
		  //default url
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_routinginfo.do?level=t";
		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=1200px,scrollbars=yes,status=no,location=no");	
		}	
	  });	
	 jq("#lorryImg").click(function() {
		if(jq("#etmid").val()!='') { 
		  var id = this.id;
		  jq("#"+id).attr(('target','_blank'));
		  //default url
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_routinginfo.do?level=t&uuid=" + jq("#movementRoutingId").val();
		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=1200px,scrollbars=yes,status=no,location=no");	
		}	
	  });	
	  jq(".entryLink").click(function() {
		  var id = this.id;
		  jq("#"+id).attr(('target','_blank'));
		  var apiType = "";
		  //check if this is an AIR api record
		  if(jq("#airplaneImg").length > 0) { 
			apiType = "air";	
		  }else if(jq("#railImg").length > 0) { 
			apiType = "rail";	
		  }	
		  //default url
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_movroad_entryinfo.do?mrn=" + id;
		  if(apiType == "rail"){
			 controllerUrl = "tvinnsaddigitollv2_childwindow_movrail_entryinfo.do?mrn=" + id;
		  }else if(apiType == "air"){
			 controllerUrl = "tvinnsaddigitollv2_childwindow_movair_entryinfo.do?mrn=" + id;
		  } 

		  window.open(controllerUrl, "codeWin", "top=300px,left=700px,height=150px,width=800px,scrollbars=yes,status=no,location=no");	
			
	  });	

	 jq(".ics2Link").click(function() {
		  //var id = this.id;
		  jq("#"+id).attr(('target','_blank'));
		  //default url
		  //var controllerUrl = "tvinnsaddigitollv2_childwindow_ics2_presentation_ensinfo.do?mrn=" + id;
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_ics2_presentation_ensinfo.do"
		  window.open(controllerUrl, "codeWin", "top=300px,left=700px,height=450px,width=1000px,scrollbars=yes,status=no,location=no");	
			
	  });	

  });


  jq(function() {
	  jq(".logLink").click(function() {
		  var id = this.id;
		  jq("#"+id).attr(('target','_blank'));
		  //default url
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_loginfo.do?id1=" + id +"&level=t";
		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");	
			
	  });
  });

  jq(function() {
	  jq(".transformLink").click(function() {
		  var id = this.id;
		  jq("#"+id).attr(('target','_blank'));
		  
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_transports.do?id=" + id;
		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");	
			
	  });
  });

  //refresh Transport with the newly updated (changed Masters/Houses)
  function refreshTransport(id){
	  setBlockUI();
	  window.location = "tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=" + id;
  }
  
  //used and called only from a childwindow in order to reload this page with BlockUI() ...
  function callParent(id){
	  refreshTransport(id);
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
	//jq.fn.dataTable.moment( 'DDMMYY' );    
    //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
    jq('#mainList').dataTable( {
  	  "dom": 'f<"toolbar">rt<"bottom"ip><"clear">',
      "searchHighlight": true,
  	  //"dom": '<"top"f>rt<"bottom"lip><"clear">',
  	  //"scrollY": "700px",
  	  //"scrollCollapse":  true,
	  "tabIndex": -1,
	  "order": [[ 1, "asc" ]], //Lnr
	  "lengthMenu": [ 25, 50, 100],
	  "fnDrawCallback": function( oSettings ) {
    	jq('.dataTables_filter input').addClass("inputText12LightYellow");
		
    	}
    });
	jq("div.toolbar").html('<span class="text16">Hovedforsendelser - Master Consignments</span>');
    
	//event on input field for search
    jq('input.mainList_filter').on( 'keyup click', function () {
    		filterGlobal();
    });
   
	//SYS-signature warning
	if (jq("#etsg").val()=='SYS') { 
		jq('#etsg').addClass("isa_error");
	}
	
  });

  
 
