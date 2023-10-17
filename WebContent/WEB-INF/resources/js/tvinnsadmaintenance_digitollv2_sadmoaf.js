  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  //Overlay on tab (to mark visually a delay...)
  jq(function() {
	jq('#alinkManifestList').click(function() { 
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
    
    
    
    
    
  });
  
  jq(function() {
	  jq("#efeta").datepicker({ 
		  dateFormat: 'ddmmy' 	  
	  });
	  jq("#efsjadt").datepicker({ 
		  dateFormat: 'ddmmy' 	  
	  });
	  
	  //custom validity
	    jq('#efeta').focus(function() {
	    	if(jq('#efeta').val()!=''){
	    		refreshCustomValidity(jq('#efeta')[0]);
	  		}
	  	});
	    jq('#efsjadt').focus(function() {
	    	if(jq('#efsjadt').val()!=''){
	    		refreshCustomValidity(jq('#efsjadt')[0]);
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
  

//Initialize <div> here for all clazz_dialog
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			maxWidth:500,
			maxHeight: 400,
			width: 280,
			height: 280,
			modal: true
		});
	  });
  });
  //------------------------------------
  //START Model dialog: "Delete record" 
  //------------------------------------
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".removeLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("removeLink","");
		  
		  jq('#dialogDelete'+counterIndex).dialog( "option", "title", "Slette Avd " + jq('#currentEfavd'+counterIndex).val() );
		  //deal with buttons for this modal window
		  jq('#dialogDelete'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
					 		jq('#deleteForm'+counterIndex).submit();
					 		jq( this ).dialog( "close" );
					 		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU"+counterIndex,
			 	 text: "Cancel", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		//jq("#dialogSaveSU"+counterIndex).button("option", "disabled", true);
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  //jq("#dialogSaveSU"+counterIndex).button("option", "disabled", true);
		  //open now
		  jq('#dialogDelete'+counterIndex).dialog('open');
		 
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
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
      jq('#mainList').dataTable( {
    	  "dom": '<"top">t<"bottom"flip><"clear">',
          "paging":   false,
          "ordering": false,
          "info":     false,
          "searching":     false
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  
  
  function getItemData(record) {
	  	var applicationUserParam = jq('#applicationUser').val();
	  	var htmlValue = record.id;
	  	//alert(applicationUserParam + ' ' + htmlValue);
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getSpecificDefaultValue_TvinnSadManifest.do',
	  	  data: { applicationUser : applicationUserParam, 
	  		  	  htmlParams : htmlValue },
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				//update
				jq('#updateId').val(""); jq('#updateId').val("U");
				
				jq('#efpro').val(""); jq('#efpro').val(data[i].efpro);
				jq('#efavd').val(""); jq('#efavd').val(data[i].efavd);
				jq('#efsg').val(""); jq('#efsg').val(data[i].efsg);
				
				jq('#efrgd').val(""); jq('#efrgd').val(data[i].efrgd);
				jq('#eftm').val(""); jq('#eftm').val(data[i].eftm);
				jq('#efeta').val(""); jq('#efeta').val(data[i].efeta);
				jq('#efetm').val(""); jq('#efetm').val(data[i].efetm);
				jq('#eftsd').val(""); jq('#eftsd').val(data[i].eftsd);
				jq('#ef3039e').val(""); jq('#ef3039e').val(data[i].ef3039e);
				
				jq('#efktyp').val(""); jq('#efktyp').val(data[i].efktyp);
				jq('#efkmrk').val(""); jq('#efkmrk').val(data[i].efkmrk);
				jq('#efklk').val(""); jq('#efklk').val(data[i].efklk);
				jq('#efpmrk').val(""); jq('#efpmrk').val(data[i].efpmrk);
				jq('#efplk').val(""); jq('#efplk').val(data[i].efplk);
				
				jq('#efsjaf').val(""); jq('#efsjaf').val(data[i].efsjaf);
				jq('#efsjae').val(""); jq('#efsjae').val(data[i].efsjae);
				jq('#efsjalk').val(""); jq('#efsjalk').val(data[i].efsjalk);
				
				jq('#efsjadt').val(""); jq('#efsjadt').val(data[i].efsjadt);
				jq('#efbekr').val(""); jq('#efbekr').val(data[i].efbekr);
				
				
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
	  	
	}

  jq(function() {
	  jq('#newButton').click(function() {
		  setBlockUI();
		  window.location = "tvinnsadmaintenance_manifest_sadefdef.do?id=SADEFDEF";
	  }); 
  });



  

  
 
