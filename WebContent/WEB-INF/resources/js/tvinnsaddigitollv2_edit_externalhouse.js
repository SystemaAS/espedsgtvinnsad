  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  //Overlay on tab (to mark visually a delay...)
  jq(function() {
	jq('#alinkTransportList').click(function() { 
    	setBlockUI();
    });
    jq('#alinkTransport').click(function() { 
    	setBlockUI();
    });
    jq('#alinkMaster').click(function() { 
    	setBlockUI();
    });
    
    //General Header Menus
    jq('#alinkTopicListMenuImp').click(function() { 
    	setBlockUI();
    });
    jq('#alinkTopicListMenuExp').click(function() { 
    	setBlockUI();
    });
    jq('#alinkTopicListMenuNctsExp').click(function() { 
    	setBlockUI();
    });
    jq('#alinkTopicListMenuNctsImp').click(function() { 
    	setBlockUI();
    });
    jq('#alinkTopicListMenuManifest').click(function() { 
    	setBlockUI();
    });
    jq('#manifestForm').submit(function() { 
    	setBlockUI();
    });
	
	jq('#sendToPartIdLink').click(function() {
    	jq('#sendToPartIdLink').attr('target','_blank');
    	window.open('tvinnsaddigitollv2_childwindow_external_houses.do?action=doFind&orgnr=' + jq('#ownReceiverOrgNr').val() + '&ctype=ownReceiverOrgNr', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	});
	jq('#ftplogIdLink').click(function() {
    	jq('#ftplogIdLink').attr('target','_blank');
    	window.open('tvinnsaddigitollv2_childwindow_external_houses_logTODO.do?action=doFind&todo=' + jq('#TODOemdkm').val() + jq('#TODOown_emdkmUnique').val(), "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
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
							window.location = 'tvinnsaddigitollv2_api_send_house.do?ehlnrt=' + jq('#ehlnrt').val() + '&ehlnrm=' + jq('#ehlnrm').val() + '&ehlnrh=' + jq('#ehlnrh').val()
													+ '&ehmid=' + jq('#ehmid').val()  + '&async=' + async_own + '&ehavd=' + jq('#ehavd').val() + '&ehtdn=' + jq('#ehtdn').val()
													+ '&ehrg=' + jq('#ehrg').val() + '&eh0068a=' + jq('#eh0068a').val() + '&eh0068b=' + jq('#eh0068b').val() + '&ehdkh=' + jq('#ehdkh').val();
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
							window.location = 'tvinnsaddigitollv2_api_delete_house.do?layer=1&current_id1=' + jq('#ehlnrt').val() + '&current_id2=' + jq('#ehlnrm').val() + '&current_id3=' + jq('#ehlnrh').val() + '&current_mrn=' + jq('#ehmid').val() + '&action=doDelete' ;
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
	jq('#buttonInitVoec').click(function() { 
		jq('#buttonInitVoec').attr('target','_blank');
		window.open('tvinnsaddigitollv2_childwindow_goodsitem.do?action=doInit&ehlnrt=' + jq('#ehlnrt').val() + '&ehlnrm=' + jq('#ehlnrm').val() + 
						'&ehlnrh=' + jq('#ehlnrh').val() + '&ehavd=' + jq('#ehavd').val() + '&ehpro=' + jq('#ehpro').val() + '&ehtdn=' + jq('#ehtdn').val() , 
						"voecWin", "top=300px,left=500px,height=500px,width=800px,scrollbars=yes,location=no");
    });
    
  });
  
  jq(function() {
	  jq("#eh0068a").datepicker({ 
		  //dateFormat: 'yymmdd' 	
		  dateFormat: 'ddmmy' 	  
	  });
	  
	  //custom validity
	    jq('#ehavd').focus(function() {
	    	if(jq('#ehavd').val()!=''){
	    		refreshCustomValidity(jq('#ehavd')[0]);
	  		}
	  	});
	    jq('#ehpro').focus(function() {
	    	if(jq('#ehpro').val()!=''){
	    		refreshCustomValidity(jq('#ehpro')[0]);
	  		}
	  	});
		jq('#ehtdn').focus(function() {
	    	if(jq('#ehtdn').val()!=''){
	    		refreshCustomValidity(jq('#ehtdn')[0]);
	  		}
	  	});
		jq('#ehvkb').focus(function() {
	    	if(jq('#ehvkb').val()!=''){
	    		refreshCustomValidity(jq('#ehvkb')[0]);
	  		}
	  	});
	    jq('#ehntk').focus(function() {
	    	if(jq('#ehntk').val()!=''){
	    		refreshCustomValidity(jq('#ehntk')[0]);
	  		}
	  	});

		jq('#ehcnin').focus(function() {
	    	if(jq('#ehcnin').val()!=''){
	    		refreshCustomValidity(jq('#ehcnin')[0]);
	  		}
	  	});
		jq('#ehvt').focus(function() {
	    	if(jq('#ehvt').val()!=''){
	    		refreshCustomValidity(jq('#ehvt')[0]);
	  		}
	  	});
		jq('#ehprt').focus(function() {
	    	if(jq('#ehprt').val()!=''){
	    		refreshCustomValidity(jq('#ehprt')[0]);
	  		}
	  	});

		jq('#ehtpps').focus(function() {
	    	if(jq('#ehtpps').val()!=''){
	    		refreshCustomValidity(jq('#ehtpps')[0]);
	  		}
	  	});
		jq('#ehnas').focus(function() {
	    	if(jq('#ehnas').val()!=''){
	    		refreshCustomValidity(jq('#ehnas')[0]);
	  		}
	  	});
		jq('#ehtppm').focus(function() {
	    	if(jq('#ehtppm').val()!=''){
	    		refreshCustomValidity(jq('#ehtppm')[0]);
	  		}
	  	});
		jq('#ehlks').focus(function() {
	    	if(jq('#ehlks').val()!=''){
	    		refreshCustomValidity(jq('#ehlks')[0]);
	  		}
	  	});
		jq('#ehpss').focus(function() {
	    	if(jq('#ehpss').val()!=''){
	    		refreshCustomValidity(jq('#ehpss')[0]);
	  		}
	  	});
		jq('#ehpns').focus(function() {
	    	if(jq('#ehpns').val()!=''){
	    		refreshCustomValidity(jq('#ehpns')[0]);
	  		}
	  	});

		jq('#ehnam').focus(function() {
	    	if(jq('#ehnam').val()!=''){
	    		refreshCustomValidity(jq('#ehnam')[0]);
	  		}
	  	});
		jq('#ehlkm').focus(function() {
	    	if(jq('#ehlkm').val()!=''){
	    		refreshCustomValidity(jq('#ehlkm')[0]);
	  		}
	  	});
		jq('#ehpsm').focus(function() {
	    	if(jq('#ehpsm').val()!=''){
	    		refreshCustomValidity(jq('#ehpsm')[0]);
	  		}
	  	});
		jq('#ehpnm').focus(function() {
	    	if(jq('#ehpnm').val()!=''){
	    		refreshCustomValidity(jq('#ehpnm')[0]);
	  		}
	  	});
		jq('#ehdkht').focus(function() {
	    	if(jq('#ehdkht').val()!=''){
	    		refreshCustomValidity(jq('#ehdkht')[0]);
	  		}
	  	});
		jq('#ehrg').focus(function() {
	    	if(jq('#ehrg').val()!=''){
	    		refreshCustomValidity(jq('#ehrg')[0]);
	  		}
	  	});
		jq('#eh0068a').focus(function() {
	    	if(jq('#eh0068a').val()!=''){
	    		refreshCustomValidity(jq('#eh0068a')[0]);
	  		}
	  	});
	    
	  	jq('#eh0068b').focus(function() {
	    	if(jq('#eh0068b').val()!=''){
	    		refreshCustomValidity(jq('#eh0068b')[0]);
	  		}
	  	});
		
		jq('#ehrgs').focus(function() {
	    	if(jq('#ehrgs').val()!=''){
	    		refreshCustomValidity(jq('#ehrgs')[0]);
	  		}
	  	});
		jq('#ehrgm').focus(function() {
	    	if(jq('#ehrgm').val()!=''){
	    		refreshCustomValidity(jq('#ehrgm')[0]);
	  		}
	  	});
	  
	  	//CHILD-WINDOWS
		//Oppdrag
	  	jq('#ehtdnIdLink').click(function() {
			if(jq('#ehpro').val()!=''){
	    		jq('#ehtdnIdLink').attr('target','_blank');
	    		window.open('tvinnsaddigitollv2_childwindow_oppdrag.do?action=doFind&tur=' + jq('#ehpro').val() + '&lnrt=' + jq('#emlnrt').val() + '&lnrm=' + jq('#emlnrm').val() + '&ctype=ehtdn', "oppdWin", "top=300px,left=600px,height=600px,width=1000px,scrollbars=yes,location=no");
			}
	    });
	  	jq('#ehtdnIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#ehtdnIdLink').click();
			}
	  	});
		//OppdragRawJson (for support purposes...)
	  	jq('#ehtdnIdSpan').click(function() {
			if(jq('#ehpro').val()!=''){									
	    		jq('#ehtdnIdSpan').attr('target','_blank');
	    		window.open('tvinnsaddigitollv2_childwindow_oppdragJson.do?action=doFind&tur=' + jq('#ehpro').val() + '&lnrt=' + jq('#emlnrt').val() + '&lnrm=' + jq('#emlnrm').val() + '&ctype=ehtdnJson' + '&opd=' + jq('#ehtdn').val(), "oppdWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");
			}
	    });


	 	//--------------------
  	  	//Tur - ehpro
  		//--------------------
	    jq('#ehproIdLink').click(function() {
	    	jq('#ehproIdLink').attr('target','_blank');
	    	window.open('tvinnsaddigitollv2_childwindow_tur.do?action=doInit&tudt=20240801' + '&tupro=' + jq('#ehpro').val() + '&tuavd=' + jq('#ehavd').val() + '&ctype=ehpro', "turWin", "top=300px,left=500px,height=600px,width=1000px,scrollbars=no,status=no,location=no");
	    });
	    jq('#ehproIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#ehproIdLink').click();
			}
	    });

		//Oppdrag
	  	jq('#ehsadiIdLink').click(function() {
			if(jq('#ehpro').val()!=''){
	    		jq('#ehsadiIdLink').attr('target','_blank');
	    		window.open('tvinnsaddigitollv2_childwindow_sadi.do?action=doFind&tur=' + jq('#ehpro').val() + '&bil=' + jq('#bilnr').val() + '&lnrt=' + jq('#emlnrt').val() + '&lnrm=' + jq('#emlnrm').val() + '&ctype=ehsadi', "oppdWin", "top=300px,left=600px,height=600px,width=1100px,scrollbars=yes,location=no");
			}
	    });
	  	jq('#ehsadiIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#ehsadiIdLink').click();
			}
	  	});


	  //Avsender
      jq('#ehnasIdLink').click(function() {
	    	jq('#ehnasIdLink').attr('target','_blank');
	    	window.open('tvinnsaddigitollv2_childwindow_customer.do?action=doFind&sonavn=' + jq('#ehnas').val() + '&ctype=ehnas', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	  jq('#ehnasIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#ehnasIdLink').click();
		}
	  });
	  //Mottaker
      jq('#ehnamIdLink').click(function() {
	    	jq('#ehnamIdLink').attr('target','_blank');
	    	window.open('tvinnsaddigitollv2_childwindow_customer.do?action=doFind&sonavn=' + jq('#ehnam').val() + '&ctype=ehnam', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	  jq('#ehnamIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#ehnamIdLink').click();
		}
	  });
  });
  	//--------------------------------------------------------------------------------------
	//Extra behavior for Sender/Receiver ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#ehkns').blur(function() {
			if(jq('#ehnas').val()==''){
	    		fetchSender();	
			}
		});
		jq('#ehknm').blur(function() {
			if(jq('#ehnam').val()==''){
	    		fetchReceiver();	
			}
		});
	});
	  
  	function fetchReceiver(){
	  var customerNr = jq.trim(jq('#ehknm').val());
		
		if(customerNr!=""){
  		jq.getJSON('searchCustomer_Digitoll.do', {
			applicationUser : jq('#applicationUser').val(),
			customerName : "",
			customerNumber : jq('#ehknm').val(),
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
				customer.syland = data[i].syland;
			  	//put the object in map now with customerNumber as key
				map[customer.kundnr] = customer;
			}
			if(len > 0){
				jq('#ehknm').val(customer.kundnr);
				jq('#ehnam').val(customer.knavn);refreshCustomValidity(jq('#ehnam')[0]);
				if('' != customer.eori){
					jq('#ehrgm').val(customer.eori);refreshCustomValidity(jq('#ehrgm')[0]);
				}else{
					jq('#ehrgm').val(customer.orgnr);refreshCustomValidity(jq('#ehrgm')[0]);
				}
				
				jq('#ehpsm').val(customer.adr3);
				jq('#ehlkm').val(customer.syland);
				jq('#ehpnm').val(customer.postnr);
				jq('#ehad1m').val(customer.adr1);
				jq('#ehtppm').val("2"); //bedrift
				jq('#own_ehemm_telephone').val(customer.tlf);
				jq('#own_ehemm_email').val("");
			}else{
				//init fields
				jq('#emknm').val("");
				jq('#emnam').val("");
				jq('#emrgm').val("");
				jq('#empsm').val("");
				jq('#emlkm').val("");
				jq('#empnm').val("");
				jq('#emad1m').val("");
				jq('#own_ememm_telephone').val("");
				jq('#own_ememm_email').val("");
			}
		});
  		
		}else{
			jq('#emknm').val("");
				jq('#emnam').val("");
				jq('#emrgm').val("");
				jq('#empsm').val("");
				jq('#emlkm').val("");
				jq('#empnm').val("");
				jq('#emad1m').val("");
				jq('#own_ememm_telephone').val("");
				jq('#own_ememm_email').val("");
		}

  	}
	function fetchSender(){
	  var customerNr = jq.trim(jq('#ehkns').val());
		
		if(customerNr!=""){
  		jq.getJSON('searchCustomer_Digitoll.do', {
			applicationUser : jq('#applicationUser').val(),
			customerName : "",
			customerNumber : jq('#ehkns').val(),
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
				customer.syland = data[i].syland;
			  	//put the object in map now with customerNumber as key
				map[customer.kundnr] = customer;
			}
			if(len > 0){
				//console.log("eori:" + customer.eori);
				//console.log("orgnr:" + customer.orgnr);
				
				jq('#ehkns').val(customer.kundnr);
				jq('#ehnas').val(customer.knavn);refreshCustomValidity(jq('#ehnas')[0]);
				if('' != customer.eori){
					jq('#ehrgs').val(customer.eori);refreshCustomValidity(jq('#ehrgs')[0]);
				}else{
					jq('#ehrgs').val(customer.orgnr);refreshCustomValidity(jq('#ehrgs')[0]);
				}
				jq('#ehpss').val(customer.adr3);
				jq('#ehlks').val(customer.syland);
				jq('#ehpns').val(customer.postnr);
				jq('#ehad1s').val(customer.adr1);
				jq('#ehtpps').val("2"); //bedrift
				jq('#own_ehems_telephone').val(customer.tlf);
				jq('#own_ehems_email').val("");
			}else{
				//init fields
				jq('#ehkns').val("");
				jq('#ehnas').val("");
				jq('#ehrgs').val("");
				jq('#ehpss').val("");
				jq('#ehlks').val("");
				jq('#ehpns').val("");
				jq('#ehad1s').val("");
				jq('#own_ehems_telephone').val("");
				jq('#own_ehems_email').val("");
			}
		});
  		
		}else{
			jq('#ehkns').val("");
				jq('#ehnas').val("");
				jq('#ehrgs').val("");
				jq('#ehpss').val("");
				jq('#ehlks').val("");
				jq('#ehpns').val("");
				jq('#ehad1s').val("");
				jq('#own_ehems_telephone').val("");
				jq('#own_ehems_email').val("");
		}

  	}
  
	/** OBSOLETE
		This is an update in case the EORI nr of the transport is changed
		The user can save this new number if he/she wants. This is just a user help...
		
		Api error when changing an already send DocNr. you need to DELETE the House and send again with the updated DocNr if any
	*/
	function getDocNumber(thisRecord) {
	  	var rawId = thisRecord.id;
		var record = rawId.split("_");
		//console.log(id);
		var etlnrt = record[0];
	  	var applicationUser = jq('#applicationUser').val();
	  	//get the original docNr
		var originalDocRecord = record[1].split("-");
		var docSuffix = originalDocRecord[1] + "-" + originalDocRecord[2];
		console.log(docSuffix);
		
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getDocumentNrHouse_Digitoll.do',
	  	  data: { applicationUser : applicationUser,
				  etlnrt : etlnrt},
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				//console.log(data[i].etrgr)
				jq('#ehdkh').val("");jq('#ehdkh').val(data[i].etrgr + "-" + docSuffix); //OrgNr/Eori Ombud from Transport + origianal docNrSuffix (avd + opd))
					
			}
	  	  },
	  	  error: function() {
			alert('Error loading ...');
	  	  }
	  	});
		
  	}

  
//-------------------------------------------
  //START Model dialog ADMIN: "Update status"
  //-------------------------------------------
 //Initialize <div> here
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
	  jq("#dialogDeleteHouse").dialog({ 
		  autoOpen: false,
		  maxWidth:500,
          maxHeight: 400,
          width: 280,
          height: 220,
		  modal: true
	  });
  });

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
  
	jq(function() {
	  	//delete to SYSPED
 		jq('#deleteHouseButton').click(function() { 
    	  jq('#dialogDeleteHouse').dialog( "option", "title", "Fjerne fra Sysped " );
		  //deal with buttons for this modal window
		  jq('#dialogDeleteHouse').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		setBlockUI();
							window.location = 'tvinnsaddigitollv2_delete_house.do?current_id1=' + jq('#ehlnrt').val() + '&current_id2=' + jq('#ehlnrm').val() + '&current_id3=' + jq('#ehlnrh').val();
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
		  jq('#dialogDeleteHouse').dialog('open');
    	});
	});
 
	jq(function() {
	  jq(".uuidLink").click(function() {
		  var id = this.id;
		  jq("#"+id).attr(('target','_blank'));
		  //default url
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_manifestinfo.do?id=" + id +"&level=h";
		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");	
			
	  });
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
		  //default url
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_housedocs_rec.do?id=" + id;
		  if(apiType == "rail"){
			 controllerUrl = "tvinnsaddigitollv2_childwindow_housedocs_rec_rail.do?id=" + id;
		  }

		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");	
			
	  });
		
	  jq("#airplaneImg").click(function() {
		  var id = this.id;
		  jq("#"+id).attr(('target','_blank'));
		  //default url
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_routinginfo.do?level=h";
		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");	
			
	  });
	 jq("#railImg").click(function() {
		  var id = this.id;
		  jq("#"+id).attr(('target','_blank'));
		  //default url
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_routinginfo.do?level=h";
		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");	
			
	  });	
	 jq("#lorryImg").click(function() {
		  var id = this.id;
		  jq("#"+id).attr(('target','_blank'));
		  //default url
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_routinginfo.do?level=h";
		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");	
			
	  });	

  	});


  jq(function() {
	  jq(".logLink").click(function() {
		  var tmp = this.id;
		  console.log(tmp);
		  var idRecord = tmp.split('_');
				  console.log(idRecord);
		  var id = idRecord[0];
		  console.log(id);
		  var id2 = idRecord[1];
		  console.log(id);
		  var id3 = idRecord[2];
		  console.log(id);

		  jq("#"+id).attr(('target','_blank'));
		  //default url
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_loginfo.do?id1=" + id + "&id2=" + id2 + "&id3=" + id3 + "&level=h";
		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");	
			
	  });
  });

	jq(function() {
			jq('#sttButton').click(function() { 
				if(jq('#ehavd').val()!='' && jq('#ehtdn').val()!=''){
					if(jq('#ehrg').val()!='' && jq('#eh0068a').val()!='' && jq('#eh0068b').val()!=''){
						openUploadFileToToll();
					}
				}
		    });
	  });
  	
	function openUploadFileToToll(){
	  window.open('tvinnsadmanifest_childwindow_uploadfile_to_toll.do?action=doInit&wsavd=' + jq('#ehavd').val() + '&wsopd=' + jq('#ehtdn').val() + '&clrg=' + jq('#ehrg').val() + '&cl0068a=' + jq('#eh0068a').val() + '&cl0068b=' + jq('#eh0068b').val(), "releasedCargolinesWin", "top=300px,left=450px,height=450px,width=1000px,scrollbars=yes,status=no,location=no");
  	}


	//---------------------------------
  	//START Model dialog: "Logger"
  	//------------------------------
  	 //Initialize <div> here
  	 jq(function() { 
		  jq("#dialogLoggerLocal").dialog({
			  autoOpen: false,
			  maxWidth:500,
	          maxHeight: 400,
	          width: 230,
	          height: 250,
			  modal: true
		  });
  	 });

	 jq(function() {
  		  jq("#alinkLogsgLoggerApi").click(function() {
			  var url = "renderLocalLogsgExpft.do?user=" + jq('#applicationUser').val() + "&logLevel=" + jq("#logLevel").val();
  			  presentPwdDialogLocal(url);
  		  });
		  jq("#alinkLogsgLoggerSadService").click(function() {
			  var url = "renderLocalLogsgService.do?user=" + jq('#applicationUser').val() + "&logLevel=" + jq("#logLevel").val();
  			  presentPwdDialogLocal(url);
  		  });
		  jq("#alinkLogsgLoggerCatalina").click(function() {
			  var url = "renderLocalCatalina.do?user=" + jq('#applicationUser').val();
  			  presentPwdDialogLocal(url);
  		  });
  	  });
  	  
  	  
  	  //---------------------
  	  //PRESENT PWD DIALOG
  	  //---------------------
  	  function presentPwdDialogLocal(url){
  		  jq('#dialogLoggerLocal').dialog( "option", "title", "Logsg-api logger" );
  		  //deal with buttons for this modal window
  		  jq('#dialogLoggerLocal').dialog({
  			 buttons: [ 
  	            {
  				 id: "dialogSaveTU",	
  				 text: "Show",
  				 click: function(){
  					 		if(jq("#pwdLocal").val() == "straffe12"){
  					 			window.open(url , '_blank');
  					 			jq("#loggerStatusLocal").removeClass( "isa_error" );
  				  				jq("#loggerStatusLocal").addClass( "isa_success" );
  				  				jq("#loggerStatusLocal").text("");
	  				  			jq("#pwdLocal").val("");
				  				jq("#logLevelLocal").val("");
				  				jq( this ).dialog( "close" ); 
  					 		}else{
  					 			jq("#loggerStatusLocal").removeClass( "isa_success" );
  				  				jq("#loggerStatusLocal").addClass( "isa_error" );
  				  				jq("#loggerStatusLocal").text("...");
  				  				jq("#pwdLocal").val("");
  				  				jq("#logLevelLocal").val("");
  					 		}
  			 			}
  			 	 },
  	  			{
  			 	 id: "dialogCancelTU",
  			 	 text: "Cancel", 
  				 click: function(){
  					 		jq("#loggerStatusLocal").removeClass( "isa_success" );
  					 		jq("#loggerStatusLocal").removeClass( "isa_error" );
  					 		jq("#loggerStatusLocal").text("");
  					 		jq("#pwdLocal").val("");
  					 		jq("#logLevelLocal").val("");
  					 		
  			  				jq( this ).dialog( "close" ); 
  				 		} 
  	 	 		 } ] 
  		  });
  		  jq('#dialogLoggerLocal').dialog('open');
  	  }

	//====================================================== 
	//Send external house to party (owner of the transport)
	//====================================================== 
	jq(function() {
		jq('#sendToPartButton').click(function() {
			
			if( jq('#ownReceiverName').val() != "" && jq('#ownReceiverOrgNr').val() != "" && jq('#ehlnrt').val()!= "" && jq('#ehlnrm').val()!= "" && jq('#ehlnrh').val()!= ""){
				var	name = jq('#ownReceiverName').val();
				var	orgNr = jq('#ownReceiverOrgNr').val();
				var ehlnrt = jq('#ehlnrt').val();
				var ehlnrm = jq('#ehlnrm').val();
				var ehlnrh = jq('#ehlnrh').val();
				jq.ajax({
			  	  type: 'GET',
			  	  url: 'tvinnsaddigitollv2_send_externalHouse_toExternalParty.do',
			  	  data: { applicationUser : jq('#applicationUser').val(),
						  ehlnrt : ehlnrt,
						  ehlnrm : ehlnrm,
						  ehlnrh : ehlnrh,
						  receiverName : name,
				 		  receiverOrgnr : orgNr},	
			  	  beforeSend : function() {
		               jq.blockUI({ message: 'Wait' });
		          },
				  dataType: 'json',
			  	  cache: false,
			  	  contentType: 'application/json',
			  	  success: function(data) {
					jq.unblockUI();
						//alert("Hello");
						var len = data.length;
						if(len>0){
						  	for ( var i = 0; i < len; i++) {
								//return text (OK or ERROR)
								var resultText = data[i].own_resultAjaxText;
								if(resultText.indexOf('ERROR') >=0 ){
									jq('#sendToPartButton').removeClass("isa_success");
									jq('#sendToPartButton').addClass("isa_error");
									jq('#ajaxErrorTextExtParty').css('color', 'red');
								}else{
									jq('#sendToPartButton').removeClass("isa_error");
									jq('#sendToPartButton').addClass("isa_success");
									jq('#ajaxErrorTextExtParty').css('color', 'green');
								}
								jq('#ajaxErrorTextExtParty').text(resultText);
							}
						}
					},
				  	  error: function() {
				  	    alert('Error loading ...');
						jq.unblockUI();
				  	}			
				});	
			}
			
		});
	});


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
	  "order": [[ 1, "asc" ]], //Linenr
	  "lengthMenu": [ 25, 50, 100],
	  "fnDrawCallback": function( oSettings ) {
    	jq('.dataTables_filter input').addClass("inputText12LightYellow");
    	}
    });

	jq("div.toolbar").html('<span class="text16">Goods Item</span>');
    
//event on input field for search
    jq('input.mainList_filter').on( 'keyup click', function () {
    		filterGlobal();
    });
   
	
  });
  
  
  

  
 
