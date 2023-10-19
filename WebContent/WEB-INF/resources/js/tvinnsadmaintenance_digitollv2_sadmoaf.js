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
	  	jq("#etetad").datepicker({ 
		  dateFormat: 'ddmmy' 	  
	  	});
	 	jq("#etshed").datepicker({ 
		  dateFormat: 'ddmmy' 	  
	  	});
    	jq('#etavd').focus(function() {
			if(jq('#etavd').val()!=''){
	    		refreshCustomValidity(jq('#etavd')[0]);
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
		
		//Carrier
      jq('#etnatIdLink').click(function() {
	    	jq('#etnatIdLink').attr('target','_blank');
	    	window.open('tvinnsadncts_childwindow_customer.do?action=doFind&sonavn=' + jq('#etnat').val() + '&ctype=etnat', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	  jq('#etnatIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#etnatIdLink').click();
		}
	  });
	  //Representative
      jq('#etnarIdLink').click(function() {
	    	jq('#etnarIdLink').attr('target','_blank');
	    	window.open('tvinnsadncts_childwindow_customer.do?action=doFind&sonavn=' + jq('#etnar').val() + '&ctype=etnar', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	  jq('#etnarIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#etnarIdLink').click();
		}
	  });	

  });


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
  		jq.getJSON('searchCustomer_TvinnSad.do', {
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
				customer.syland = data[i].syland;
			  	//put the object in map now with customerNumber as key
				map[customer.kundnr] = customer;
			}
			if(len > 0){
				jq('#etknt').val(customer.kundnr);
				jq('#etnat').val(customer.knavn); refreshCustomValidity(jq('#etnat')[0]);
				if('' != customer.orgnr){
					jq('#etrgt').val(customer.orgnr);refreshCustomValidity(jq('#etrgt')[0]);
				}else{
					jq('#etrgt').val(customer.eori);refreshCustomValidity(jq('#etrgt')[0]);
				}
				jq('#etpst').val(customer.adr3);refreshCustomValidity(jq('#etpst')[0]);
				jq('#etlkt').val(customer.syland);refreshCustomValidity(jq('#etlkt')[0]);
				jq('#etpnt').val(customer.postnr);
				jq('#etad1t').val(customer.adr1);
				jq('#ettppt').val("2"); //bedrift
				jq('#own_etemt_telephone').val(customer.tlf);
				jq('#own_etemt_email').val("");
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
  		jq.getJSON('searchCustomer_TvinnSad.do', {
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
				customer.syland = data[i].syland;
			  	//put the object in map now with customerNumber as key
				map[customer.kundnr] = customer;
			}
			if(len > 0){
				jq('#etknr').val(customer.kundnr);
				jq('#etnar').val(customer.knavn); refreshCustomValidity(jq('#etnar')[0]);
				if('' != customer.orgnr){
					jq('#etrgr').val(customer.orgnr);refreshCustomValidity(jq('#etrgr')[0]);
				}else{
					jq('#etrgr').val(customer.eori);refreshCustomValidity(jq('#etrgr')[0]);
				}
				
				jq('#etpsr').val(customer.adr3);refreshCustomValidity(jq('#etpsr')[0]);
				jq('#etlkr').val(customer.syland);refreshCustomValidity(jq('#etlkr')[0]);
				jq('#etpnr').val(customer.postnr);
				jq('#etad1r').val(customer.adr1);
				jq('#ettppr').val("2"); //bedrift
				jq('#own_etemr_telephone').val(customer.tlf);
				jq('#own_etemr_email').val("");
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



  

  
 
