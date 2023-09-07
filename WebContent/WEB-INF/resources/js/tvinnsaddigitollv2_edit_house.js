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
    
    jq('#sendButton').click(function() { 
    	setBlockUI();
		window.location = 'tvinnsaddigitollv2_api_send_house.do?ehlnrt=' + jq('#ehlnrt').val() + '&ehlnrm=' + jq('#ehlnrm').val() + '&ehlnrh=' + jq('#ehlnrh').val()+ '&ehmid=' + jq('#ehmid').val();
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
	  jq("#eh0068a").datepicker({ 
		  dateFormat: 'yymmdd' 	  
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
		jq('#ehnam').focus(function() {
	    	if(jq('#ehnam').val()!=''){
	    		refreshCustomValidity(jq('#ehnam')[0]);
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
  		jq.getJSON('searchCustomer_TvinnSad.do', {
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
				customer.postnr = data[i].sypoge;//data[i].postnr; DK=sypoge
				customer.kpers = data[i].kpers;
				customer.tlf = data[i].tlf;
				customer.syland = data[i].syland;
			  	//put the object in map now with customerNumber as key
				map[customer.kundnr] = customer;
			}
			if(len > 0){
				jq('#ehknm').val(customer.kundnr);
				jq('#ehnam').val(customer.knavn);
				jq('#ehrgm').val(customer.orgnr);
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
  		jq.getJSON('searchCustomer_TvinnSad.do', {
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
				customer.postnr = data[i].sypoge;//data[i].postnr; DK=sypoge
				customer.kpers = data[i].kpers;
				customer.tlf = data[i].tlf;
				customer.syland = data[i].syland;
			  	//put the object in map now with customerNumber as key
				map[customer.kundnr] = customer;
			}
			if(len > 0){
				jq('#ehkns').val(customer.kundnr);
				jq('#ehnas').val(customer.knavn);
				jq('#ehrgs').val(customer.orgnr);
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
  
  
  

  
 
