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
    jq('#alinkHouse').click(function() { 
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
    //Real Send to Api (POST or PUT)
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
							window.location = 'tvinnsaddigitollv2_api_send_master.do?emlnrt=' + jq('#emlnrt').val() + '&emlnrm=' + jq('#emlnrm').val()+ '&emmid=' + jq('#emmid').val()  + '&async=' + async_own;
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
							window.location = 'tvinnsaddigitollv2_api_delete_master.do?layer=1&current_id1=' + jq('#emlnrt').val() + '&current_id2=' + jq('#emlnrm').val()+ '&current_mrn=' + jq('#emmid').val() + '&action=doDelete' ;
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

	//Real Send to Api (POST or PUT)
    jq('#sendButtonAllHouses').click(function() { 
    	  jq('#dialogSendAllHouses').dialog( "option", "title", "Send alle underliggende houses til toll.no" );
		  //deal with buttons for this modal window
		  jq('#dialogSendAllHouses').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		setBlockUI();
							window.location = 'tvinnsaddigitollv2_api_send_allHouses.do?&level=m&lnrt=' + jq('#emlnrt').val() + '&lnrm=' + jq('#emlnrm').val();
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
		  jq('#dialogSendAllHouses').dialog('open');
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
  });
  

  jq(function() {
	  //custom validity
	    jq('#emavd').focus(function() {
	    	if(jq('#emavd').val()!=''){
	    		refreshCustomValidity(jq('#emavd')[0]);
	  		}
	  	});
	    jq('#empro').focus(function() {
	    	if(jq('#empro').val()!=''){
	    		refreshCustomValidity(jq('#empro')[0]);
	  		}
	  	});
		jq('#emvkb').focus(function() {
	    	if(jq('#emvkb').val()!=''){
	    		refreshCustomValidity(jq('#emvkb')[0]);
	  		}
	  	});
	    jq('#emdkm').focus(function() {
	    	if(jq('#emdkm').val()!=''){
	    		refreshCustomValidity(jq('#emdkm')[0]);
	  		}
	  	});
		jq('#emdkmt').focus(function() {
	    	if(jq('#emdkmt').val()!=''){
	    		refreshCustomValidity(jq('#emdkmt')[0]);
	  		}
	  	});
		jq('#emrgt').focus(function() {
	    	if(jq('#emrgt').val()!=''){
	    		refreshCustomValidity(jq('#emrgt')[0]);
	  		}
	  	});
	  
	  	//CHILD-WINDOWS
	 	//--------------------
  	  	//Tur - empro
  		//--------------------
	    jq('#emproIdLink').click(function() {
	    	jq('#emproIdLink').attr('target','_blank');
	    	window.open('tvinnsaddigitollv2_childwindow_tur.do?action=doInit&tudt=20200101' + '&tupro=' + jq('#empro').val() + '&tuavd=' + jq('#emavd').val()  + '&ctype=empro', "turWin", "top=300px,left=500px,height=600px,width=1000px,scrollbars=no,status=no,location=no");
	    });
	    jq('#emproIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#emproIdLink').click();
			}
	    });
	  
	  jq('#sendToPartIdLink').click(function() {
	    	jq('#sendToPartIdLink').attr('target','_blank');
	    	window.open('tvinnsaddigitollv2_childwindow_customer.do?action=doFind&ctype=ownReceiverOrgNr', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	   });	

	  //Avsender
      jq('#emnasIdLink').click(function() {
	    	jq('#emnasIdLink').attr('target','_blank');
	    	window.open('tvinnsaddigitollv2_childwindow_customer.do?action=doFind&sonavn=' + jq('#emnas').val() + '&ctype=emnas', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	  jq('#emnasIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#emnasIdLink').click();
		}
	  });
	  //Mottaker
      jq('#emnamIdLink').click(function() {
	    	jq('#emnamIdLink').attr('target','_blank');
	    	window.open('tvinnsaddigitollv2_childwindow_customer.do?action=doFind&sonavn=' + jq('#emnam').val() + '&ctype=emnam', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	  jq('#emnamIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#emnamIdLink').click();
		}
	  });
  });
  	//--------------------------------------------------------------------------------------
	//Extra behavior for Sender/Receiver ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#emknm').blur(function() {
			if(jq('#emnam').val()==''){
				fetchReceiver();	
			}
	    		
		});
		jq('#emkns').blur(function() {
			if(jq('#emnas').val()==''){
				fetchSender();	
			}
	    		
		});
		jq('#emknt').blur(function() {
			if(jq('#emrgt').val()==''){
				fetchCarrier();	
			}
	    		
		});
	});
	function fetchCarrier(){
	  var customerNr = jq.trim(jq('#emknt').val());
		
		if(customerNr!=""){
  		jq.getJSON('searchCustomer_Digitoll.do', {
			applicationUser : jq('#applicationUser').val(),
			customerName : "",
			customerNumber : jq('#emknt').val(),
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
				jq('#emknt').val(customer.kundnr);
				if('' != customer.orgnr){
					jq('#emrgt').val(customer.orgnr);
				}else{
					jq('#emrgt').val(customer.eori);
				}
			}else{
				//init fields
				jq('#emknt').val("");
				jq('#emrgt').val("");
				
			}
		});
		}else{
			//init fields
			jq('#emknt').val("");
			jq('#emrgt').val("");
		}

  }
	function fetchReceiver(){
	  var customerNr = jq.trim(jq('#emknm').val());
		
		if(customerNr!=""){
  		jq.getJSON('searchCustomer_Digitoll.do', {
			applicationUser : jq('#applicationUser').val(),
			customerName : "",
			customerNumber : jq('#emknm').val(),
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
				jq('#emknm').val(customer.kundnr);
				jq('#emnam').val(customer.knavn);
				if('' != customer.orgnr){
					jq('#emrgm').val(customer.orgnr);
				}else{
					jq('#emrgm').val(customer.eori);
				}
				jq('#empsm').val(customer.adr3);
				jq('#emlkm').val(customer.syland);
				jq('#empnm').val(customer.postnr);
				jq('#emad1m').val(customer.adr1);
				jq('#emtppm').val("2"); //bedrift
				jq('#own_ememm_telephone').val(customer.tlf);
				jq('#own_ememm_email').val("");
				
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
	  var customerNr = jq.trim(jq('#emkns').val());
		
		if(customerNr!=""){
  		jq.getJSON('searchCustomer_Digitoll.do', {
			applicationUser : jq('#applicationUser').val(),
			customerName : "",
			customerNumber : jq('#emkns').val(),
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
				jq('#emkns').val(customer.kundnr);
				jq('#emnas').val(customer.knavn);
				if('' != customer.orgnr){
					jq('#emrgs').val(customer.orgnr);
				}else{
					jq('#emrgs').val(customer.eori);
				}
				jq('#empss').val(customer.adr3);
				jq('#emlks').val(customer.syland);
				jq('#empns').val(customer.postnr);
				jq('#emad1s').val(customer.adr1);
				jq('#emtpps').val("2"); //bedrift
				jq('#own_emems_telephone').val(customer.tlf);
				jq('#own_emems_email').val("");
				
			}else{
				//init fields
				jq('#emkns').val("");
				jq('#emnas').val("");
				jq('#emrgs').val("");
				jq('#empss').val("");
				jq('#emlks').val("");
				jq('#empns').val("");
				jq('#emad1s').val("");
				jq('#own_emems_telephone').val("");
				jq('#own_emems_email').val("");
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
  

	//================================= 
	//Send master id to party
	//================================= 
	jq(function() {
		jq('#sendToPartButton').click(function() {
			
			if( jq('#ownReceiverName').val() != "" && jq('#ownReceiverOrgNr').val() != "" && jq('#emlnrt').val()!= "" && jq('#emlnrm').val()!= "" ){
				var	name = jq('#ownReceiverName').val();
				var	orgNr = jq('#ownReceiverOrgNr').val();
				var emlnrt = jq('#emlnrt').val();
				var emlnrm = jq('#emlnrm').val();
				jq.ajax({
			  	  type: 'GET',
			  	  url: 'tvinnsaddigitollv2_send_masterId_toExternalParty.do',
			  	  data: { applicationUser : jq('#applicationUser').val(),
						  emlnrt : emlnrt,
						  emlnrm : emlnrm,
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
						
					},
				  	  error: function() {
				  	    alert('Error loading ...');
						jq.unblockUI();
				  	}			
				});	
			}
			
		});
	});


  
//-------------------------------------------
  //START Model dialog ADMIN: "Update status"
  //-------------------------------------------
   //Initialize <div> here
  jq(function() { 
	  jq("#dialogStatus2").dialog({
		  autoOpen: false,
		  maxWidth:500,
          maxHeight: 400,
          width: 280,
          height: 220,
		  modal: true
	  });
  });
  jq(function() { 
	  jq("#dialogStatus3").dialog({
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
	  jq("#updateInternalStatus2Link").click(function() {
		 presentChangeInternalStatus2Dialog();
	  });
	  jq("#updateInternalStatus3Link").click(function() {
		 presentChangeInternalStatus3Dialog();
	  });
		/* NOT implemented on Controller
	  jq("#updateInternalStatusLink").click(function() {
		  presentChangeInternalStatusDialog();
	  });*/
	  
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
		  jq('#dialogStatus2').dialog( "option", "title", "Update Internal Status 2" );
		  //deal with buttons for this modal window
		  jq('#dialogStatus2').dialog({
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
		  jq('#dialogStatus2').dialog('open');
	  }
  
 	function presentChangeInternalStatus3Dialog(){
		//setters (add more if needed)
		  jq('#dialogStatus3').dialog( "option", "title", "Update Internal Status 3" );
		  //deal with buttons for this modal window
		  jq('#dialogStatus3').dialog({
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
		  jq('#dialogStatus3').dialog('open');
	  }

  //Initialize <div> here
  jq(function() { 
	  jq("#dialogMasterHouse").dialog({ 
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
			width: 500,
			height: 280,
			modal: true
		});
	  });
  });

 //----------------------------------------------------------------
  //START Model dialog: "Delete House" (Api and Db OR only Locally
  //----------------------------------------------------------------
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".removeLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("removeLink","");
		  
		  jq('#dialogUpdateStatus'+counterIndex).dialog( "option", "title", "Slett House Consignment " + jq('#current_id1'+counterIndex).val() + "/" + jq('#current_id2'+counterIndex).val() + "/" + jq('#current_id3'+counterIndex).val() );
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

	//Present dialog box onClick (href in parent JSP)
  	jq(function() {
	  jq(".deleteHouseLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("deleteLink","");
		  
		  jq('#dialogDeleteHouse'+counterIndex).dialog( "option", "title", "Fjerne i SYSPED " );
		  //deal with buttons for this modal window
		  jq('#dialogDeleteHouse'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
							setBlockUI();
					 		jq('#deleteHouseForm'+counterIndex).submit();
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
		  jq('#dialogDeleteHouse'+counterIndex).dialog('open');
		 
	  });
  	});

	jq(function() {
	  	//delete to SYSPED
 		jq('#deleteMasterButton').click(function() { 
    	  jq('#dialogDeleteMaster').dialog( "option", "title", "Fjerne fra Sysped " );
		  //deal with buttons for this modal window
		  jq('#dialogDeleteMaster').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		setBlockUI();
							window.location = 'tvinnsaddigitollv2_delete_master.do?current_id1=' + jq('#emlnrt').val() + '&current_id2=' + jq('#emlnrm').val();
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
		  jq('#dialogDeleteMaster').dialog('open');
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
  });

  jq(function() {
	  jq(".uuidLinkParent").click(function() {
		  var id = this.id;
		  jq("#"+id).attr(('target','_blank'));
		  var apiType = "";
		  //check if this is an AIR api record
		  if(jq("#airplaneImg").length > 0) { 
			apiType = "air";	
		  }	
		  //default url
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_manifestinfo.do?id=" + id +"&level=m" + "&apiType=" + apiType;

		 // Has been removed at toll.no in V2 ...	
		  if(id.length<35){ //meaning MRN and not LRN (only at Master Consignment level)
			 controllerUrl = "tvinnsaddigitollv2_childwindow_masterdocs_rec.do?id=" + id; 
		  }
		 
		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");	
			
	  });
	  	
  });
  jq(function() {
	  jq(".logLink").click(function() {
		  var tmp = this.id;
		  var idRecord = tmp.split('_');		
		  var id = idRecord[0];
		  var id2 = idRecord[1];

		  jq("#"+id).attr(('target','_blank'));
		  //default url
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_loginfo.do?id1=" + id + "&id2=" + id2 + "&level=m";
		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");	
			
	  });
  });


  	//TUR get std. info and/or default values (in case we restore the turFetchButton... 
	// ...it is done at the Controller since the avd/pro where make read-only)
	/*jq(function() { 
	    jq('#turFetchButton').click(function() {
			//==================================
			//(1) Tur values from TDIG001R.pgm
			//==================================
			jq.ajax({
		  	  type: 'GET',
		  	  url: 'searchTur_Digitoll.do',
		  	  data: { applicationUser : jq('#applicationUser').val(), 
					  avd : jq('#emavd').val(),	
		  		  	  turNr : jq('#empro').val(), 
		  		  	  fromDate : "20200101"}, 
		  	  dataType: 'json',
		  	  cache: false,
		  	  contentType: 'application/json',
		  	  success: function(data) {
				//alert("Hello");
				var len = data.length;
				if(len>0){
					  for ( var i = 0; i < len; i++) {
						//Bruttovekt
						jq('#emvkb').val(data[i].tutvkt);//brutto-vekt
						//kanske några till men helst inte ...
						
						//signatur
						/*if(data[i].tusg != ''){
							jq('#emsg').val(data[i].tusg);//signatur
						}else{
							jq('#emsg').val(jq('#applicationUserSign').val());//signatur from login
						}
					  }
				 }else{
					//jq('#emvkb').val("");//brutto-vekt
						
				 }	
				//get defaults
				//getDefaultValuesFromSadmoaf();
				
				},
			  	  error: function() {
			  	    alert('Error loading ...');
			  	  }
				});				
		});		
	});
	
	//================================= 
	//default-values from SADMOAF
	//// ...it is done at the Controller since the avd/pro where make read-only)
	//================================= 
	function getDefaultValuesFromSadmoaf(){
		jq(function() {
			
			var _avd = "0";
			if(jq('#emavd').val()!=''){
				_avd = jq('#emavd').val();
			}
			jq.ajax({
		  	  type: 'GET',
		  	  url: 'searchDefaultValues_Digitoll.do',
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
						//Ombud epost feedback
						jq('#emrcem1').val(data[i].emrcem1);//email 1
						jq('#emrcem2').val(data[i].emrcem2);//email 2
						jq('#emrcem3').val(data[i].emrcem3);//email 3
						//doktype (default is already N730 so ONLY if it is not empty... will then override the default)
						if(data[i].emdkmt != ''){
							jq('#emdkmt').val(data[i].emdkmt);
						}
						//container
						if(data[i].emcn != ''){
							jq('#emcn').val(data[i].emcn);
						}
						//Laste / losse /delivery 
						jq('#emsdlt').val(data[i].emsdlt);
						jq('#emlkl').val(data[i].emlkl);
						jq('#emsdl').val(data[i].emsdl);
						jq('#emsdut').val(data[i].emsdut);
						jq('#emlku').val(data[i].emlku);
						jq('#emsdu').val(data[i].emsdu);
						jq('#emsddt').val(data[i].emsddt);
						jq('#emlkd').val(data[i].emlkd);
						jq('#emsdd').val(data[i].emsdd);
						
						//Avsender
						jq('#emnas').val(data[i].emnas);
						jq('#emrgs').val(data[i].emrgs);
						jq('#emtpps').val(data[i].emtpps);
						jq('#empss').val(data[i].empss);
						jq('#emlks').val(data[i].emlks);
						jq('#emad1s').val(data[i].emad1s);
						jq('#empns').val(data[i].empns);
						if(data[i].ememst == 'TE'){
							jq('#own_emems_telephone').val(data[i].emems);//telephone
						}else if(data[i].ememst == 'EM'){
							jq('#own_emems_email').val(data[i].emems);//email
						}else{
							jq('#own_emems_telephone').val("");
							jq('#own_emems_email').val("");
						}
						//Mottaker
						jq('#emnam').val(data[i].emnam);
						jq('#emrgm').val(data[i].emrgm);
						jq('#emtppm').val(data[i].emtppm);
						jq('#empsm').val(data[i].empsm);
						jq('#emlkm').val(data[i].emlkm);
						jq('#emad1m').val(data[i].emad1m);
						jq('#empnm').val(data[i].empnm);
						if(data[i].ememmt == 'TE'){
							jq('#own_ememm_telephone').val(data[i].ememm);//telephone
						}else if(data[i].ememmt == 'EM'){
							jq('#own_ememm_email').val(data[i].ememm);//email
						}else{
							jq('#own_ememm_telephone').val("");
							jq('#own_ememm_email').val("");
						}
				 	}
				}else{
					jq('#emrcem1').val("");//email
					jq('#emrcem2').val("");//email
					jq('#emrcem3').val("");//email
					//Laste / losse /delivery 
					jq('#emsdlt').val("");
					jq('#emlkl').val("");
					jq('#emsdl').val("");
					jq('#emsdut').val("");
					jq('#emlku').val("");
					jq('#emsdu').val("");
					jq('#emsddt').val("");
					jq('#emlkd').val("");
					jq('#emsdd').val("");
				}
				},
			  	  error: function() {
			  	    alert('Error loading ...');
			  	}
				
			 });
		 });
	}
*/

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

	jq("div.toolbar").html('<span class="text16">House Consignments</span>');
    
	//event on input field for search
    jq('input.mainList_filter').on( 'keyup click', function () {
    		filterGlobal();
    });

	jq('#emsg').focus();
   
	
  });
  
  

  
 
