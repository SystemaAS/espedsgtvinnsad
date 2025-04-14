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
		
	  //Party
      jq('#nameIdLink').click(function() {
	    	jq('#nameIdLink').attr('target','_blank');
	    	window.open('tvinnsadncts_childwindow_customer.do?action=doFind&sonavn=' + jq('#name').val() + '&ctype=name', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	  jq('#nameIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#nameIdLink').click();
		}
	  });
	  	

  });


	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#etknt').blur(function() {
			if(jq('#name').val()==''){
	    		fetchParty();	
			}
		});
		
	});
	  
  	function fetchParty(){
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
				jq('#name').val(customer.knavn); refreshCustomValidity(jq('#name')[0]);
				jq('#orgnr').val(customer.orgnr);refreshCustomValidity(jq('#orgnr')[0]);
				/*
				jq('#etpst').val(customer.adr3);refreshCustomValidity(jq('#etpst')[0]);
				jq('#etlkt').val(customer.syland);refreshCustomValidity(jq('#etlkt')[0]);
				jq('#etpnt').val(customer.postnr);
				jq('#etad1t').val(customer.adr1);
				jq('#ettppt').val("2"); //bedrift
				jq('#own_etemt_telephone').val(customer.tlf);
				jq('#own_etemt_email').val("");
				*/
			}else{
				//init fields
				jq('#etknt').val("");
				jq('#name').val("");
				jq('#orgnr').val("");
				/*
				jq('#etpst').val("");
				jq('#etlkt').val("");
				jq('#etpnt').val("");
				jq('#etad1t').val("");
				jq('#own_etemt_telephone').val("");
				jq('#own_etemt_email').val("");
				*/
			}
		});
  		
		}else{
			jq('#etknt').val("");
				jq('#name').val("");
				jq('#orgnr').val("");
				/*
				jq('#etpst').val("");
				jq('#etlkt').val("");
				jq('#etpnt').val("");
				jq('#etad1t').val("");
				jq('#own_etemt_telephone').val("");
				jq('#own_etemt_email').val("");
				*/
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
          "searchHighlight": true,
		  "tabIndex": -1,
	  	  "order": [[ 1, "asc" ]], //avd
		  "lengthMenu": [ 30, 50, 100, 100],
		  //"paging":   false,
          //"ordering": false,
          //"info":     false,
          //"searching":     false
          "fnDrawCallback": function( oSettings ) {
    		jq('.dataTables_filter input').addClass("inputText12LightYellow");
    	}
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



  

  
 
