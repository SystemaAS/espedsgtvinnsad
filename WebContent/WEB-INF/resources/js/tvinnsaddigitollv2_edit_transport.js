  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  //Overlay on tab (to mark visually a delay...)
  jq(function() {
	
	jq('#alinkTransportList').click(function() { 
    	setBlockUI();
    });
    jq('#alinkHeader').click(function() { 
    	setBlockUI();
    });
    jq('#alinkItems').click(function() { 
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
    
    jq('#sendButton').click(function() { 
    	var status = "S";
    	
    	setBlockUI();
		window.location = 'tvinnsadmanifest_send.do?efuuid=' + jq('#efuuid').val() + '&efpro=' + jq('#efpro').val() + '&efst2=' + status;
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
	  jq("#etetad").datepicker({ 
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
	  
		jq('#etdkm').focus(function() {
	    	if(jq('#etdkm').val()!=''){
	    		refreshCustomValidity(jq('#etdkm')[0]);
	  		}
	  	});
		jq('#etdkmt').focus(function() {
	    	if(jq('#etdkmt').val()!=''){
	    		refreshCustomValidity(jq('#etdkmt')[0]);
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
	  
	  //CHILD-WINDOWS
	  //Tollsted
	  jq('#eftsdIdLink').click(function() {
	  	jq('#eftsdIdLink').attr('target','_blank');
	  	window.open('tvinnsadmanifest_childwindow_tollstedcodes.do?action=doInit&type=2&ctype=silka', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#eftsdIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#eftsdIdLink').click();
			}
	  });
	  
	  //Customer
      jq('#efkndIdLink').click(function() {
		jq('#efkndIdLink').attr('target','_blank');
	    window.open('tvinnsad_childwindow_customer.do?action=doFind&sonavn=' + jq('#own_efkndName').val() + '&ctype=efknd', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#efkndIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#efkndIdLink').click();
		}
	  });
  });
  	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#efknd').blur(function() {
	    	fetchCustomer();	
		});
	});
	  
 
	jq(document).ready(function() {
		  //in order to get the customer name and orgnr
		  //fetchCustomer(); OBSOLETE ?
	  });
  
	
  function fetchCustomer(){
	  var customerNr = jq.trim(jq('#efknd').val());
		
		if(customerNr!=""){
  		jq.getJSON('searchCustomer_TvinnSad.do', {
			applicationUser : jq('#applicationUser').val(),
			customerName : "",
			customerNumber : jq('#efknd').val(),
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
				customer.postnr = data[i].sypoge;//data[i].postnr; DK=sypoge
				customer.kpers = data[i].kpers;
				customer.tlf = data[i].tlf;
				customer.syland = data[i].syland;
			  	//put the object in map now with customerNumber as key
				map[customer.kundnr] = customer;
			}
			if(len > 0){
				jq('#efknd').val(customer.kundnr);
				jq('#own_efkndName').val(customer.knavn);
				jq('#efrgd').val(customer.orgnr);
			}else{
				//init fields
				jq('#efknd').val("");
				jq('#own_efkndName').val("");
			}
		});
  		
		}else{
			jq('#efknd').val("");
			jq('#own_efkndName').val("");
			jq('#efrgd').val("");
		}

  }
  
  
//-------------------------------------------
  //START Model dialog ADMIN: "Update status"
  //-------------------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq("#dialogUpdateManifestStatus").dialog({
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
  //----------------------------
  //Present dialog box onClick 
  //----------------------------
  jq(function() {
	  jq("#updateManifestStatusLink").click(function() {
		  presentChangeManifestStatusDialog();
	  });
	  jq("#updateInternalStatusLink").click(function() {
		  presentChangeInternalStatusDialog();
	  });
	  
  });
  function presentChangeManifestStatusDialog(){
	//setters (add more if needed)
	  jq('#dialogUpdateManifestStatus').dialog( "option", "title", "Update Manifest Status" );
	  //deal with buttons for this modal window
	  jq('#dialogUpdateManifestStatus').dialog({
		 buttons: [ 
            {
			 id: "dialogSaveTU",	
			 text: "Ok",
			 click: function(){
				 		jq('#updateManifestStatusForm').submit();
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
	  jq('#dialogUpdateManifestStatus').dialog('open');
  }
  
  
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
		  
		  jq('#dialogUpdateStatus'+counterIndex).dialog( "option", "title", "Slette Master Consignment " + jq('#current_id1'+counterIndex).val() + "/" + jq('#current_id2'+counterIndex).val() );
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
	  jq(".uuidLink").click(function() {
		  var id = this.id;
		  jq("#"+id).attr(('target','_blank'));
		  //default url
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_manifestinfo.do?id=" + id +"&level=m";

		  if(id.length<35){ //meaning MRN and not LRN (only at Master Consignment level)
			 controllerUrl = "tvinnsaddigitollv2_childwindow_masterdocs_rec.do?id=" + id; 
		  }
	      
		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");	
			
	  });
  });

  jq(function() {
	  jq(".uuidLinkParent").click(function() {
		  var id = this.id;
		  jq("#"+id).attr(('target','_blank'));
		  //default url
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_manifestinfo.do?id=" + id +"&level=t";

		  if(id.length<35){ //meaning MRN and not LRN (only at Master Consignment level)
			 controllerUrl = "tvinnsaddigitollv2_childwindow_masterdocs_rec.do?id=" + id; 
		  }
		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");	
			
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
  	  "scrollCollapse":  true,
	  "tabIndex": -1,
	  //"order": [[ 2, "desc" ]], //turnr
	  "lengthMenu": [ 25, 50, 100],
	  "fnDrawCallback": function( oSettings ) {
    	jq('.dataTables_filter input').addClass("inputText12LightYellow");
    	}
    });
	jq("div.toolbar").html('<span class="text16">Master Consignments</span>');
    
//event on input field for search
    jq('input.mainList_filter').on( 'keyup click', function () {
    		filterGlobal();
    });
   
	
  });

  
 
