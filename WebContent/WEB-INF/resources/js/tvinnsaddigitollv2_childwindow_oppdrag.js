	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
	
	jq(function() {
		jq('#buttonCheckAll').click(function(){
			jq( ".clazzCreateHouseAware" ).each(function(  ) {
				var id = this.id;
				var record = id.split('_');
				var avd = record[0].replace("avd", "");
				var opd = record[1].replace("opd", "");
				var tur = record[2].replace("tur", "");
				
				if (jq(this).is(":checked")) {	  
					jq(this).prop('checked', false);
				}else{
					jq(this).prop('checked', true);
				}
				
			});
		});
	});
	
	
	jq(function() {
		jq('#buttonCreateHousesOk').click(function(){
			var params = "";
			var turParam = "";	  
			jq( ".clazzCreateHouseAware" ).each(function(  ) {
				
				  var id = this.id;
				  var record = id.split('_');
				  var avd = record[0].replace("avd", "");
				  var opd = record[1].replace("opd", "");
				  var tur = record[2].replace("tur", "");
				  //var counter = i + 1;
				  //alert(avd + "-" + opd);
					  
				  if(jq('#avd' + avd + '_opd' + opd + '_tur' + tur).prop('checked')){
					 var tmp = "avd" + avd + "_opd" + opd + "#";
					 params = params + tmp;
					 turParam = tur; 
				  }	
			});
			
			if(params != ""){
			  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
			  jq.ajax({
		  	  	  type: 'GET',
		  	  	  url: 'createHousesFromOppdrag_Digitoll.do',
		  	  	  data: { applicationUser : jq('#applicationUser').val(),
							params : params,
							tur: turParam,
							lnrt : jq('#lnrt').val(),
							lnrm : jq('#lnrm').val(),
							mode : 'A'},
							
		  	  	  dataType: 'json',
		  	  	  cache: false,
		  	  	  //async: false,
		  	  	  contentType: 'application/json',
		  	  	  success: function(data) {
					jq.unblockUI(); //must have async: true (default) to work
		  	  		var len = data.length;
		  	  		for ( var i = 0; i < len; i++) {
		  	  			//Update has been done successfully
				        
		  	  		}
					
		  	  	  },
			  	  error: function() {
					jq.unblockUI();
		  	  	    //alert('Error loading ...');
		  	  	  }
		  	  });
			}	  
				
			window.setTimeout(function(){
                 //we must reload the parent master window since the use case updates the invoice list (if the end-user has selected some invoices to import)
				  window.opener.setBlockUI();
				  window.opener.location.href="tvinnsaddigitollv2_edit_master.do?action=doFind&emlnrt=" + jq('#lnrt').val() + "&emlnrm=" + jq('#lnrm').val();
				  window.close();     
             }, 800); //milliseconds: in order to avoid a refresh in master due to the above Ajax create house. It could take more time to be finished on the background...
			
		});
		//abort
		jq('#buttonCancel').click(function(){
			window.close();
		});
		
	});
	
  	/*
	jq(function() {
		jq('#buttonCreateHousesOk').click(function(){
				  
			jq( ".clazzCreateHouseAware" ).each(function(  ) {
				
				  var id = this.id;
				  var record = id.split('_');
				  var avd = record[0].replace("avd", "");
				  var opd = record[1].replace("opd", "");
				  var tur = record[2].replace("tur", "");
				  //var counter = i + 1;
				  //alert(avd + "-" + opd);
				  
				  if(jq('#avd' + avd + '_opd' + opd + '_tur' + tur).prop('checked')){
					  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
					  jq.ajax({
				  	  	  type: 'GET',
				  	  	  url: 'createHousesFromOppdrag_Digitoll.do',
				  	  	  data: { applicationUser : jq('#applicationUser').val(),
									avd : avd,
									opd : opd,
									tur : tur,
									lnrt : jq('#lnrt').val(),
									lnrm : jq('#lnrm').val(),
									mode : 'A'},
									
				  	  	  dataType: 'json',
				  	  	  cache: false,
				  	  	  //async: false,
				  	  	  contentType: 'application/json',
				  	  	  success: function(data) {
							jq.unblockUI(); //must have async: true (default) to work
				  	  		var len = data.length;
				  	  		for ( var i = 0; i < len; i++) {
				  	  			//Update has been done successfully
						        
				  	  		}
							
				  	  	  },
					  	  error: function() {
							jq.unblockUI();
				  	  	    //alert('Error loading ...');
				  	  	  }
				  	  });
					  
				   }
				  
			});
			window.setTimeout(function(){
                 //we must reload the parent master window since the use case updates the invoice list (if the end-user has selected some invoices to import)
				  window.opener.setBlockUI();
				  window.opener.location.href="tvinnsaddigitollv2_edit_master.do?action=doFind&emlnrt=" + jq('#lnrt').val() + "&emlnrm=" + jq('#lnrm').val();
				  window.close();     
             }, 800); //milliseconds: in order to avoid a refresh in master due to the above Ajax create house. It could take more time to be finished on the background...
			
		});
		//abort
		jq('#buttonCancel').click(function(){
			window.close();
		});
		
	});
	*/

	function getItemData(record) {
	  	var id = record.id;
		var opd = id.replace("recordUpdate_", "");
	  	
		var ids = jq("#"+record.id).attr("title");
	  	var record = ids.split("_");
		//console.log = record;
	  	var applicationUser = jq('#applicationUser').val();
	  	var tur = record[0];
	  	var avd = record[1];
	  	//var opd = record[2];
	  	//DEBUG alert(applicationUser + " " + tur + " " + avd + " " + opd);
		
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getSpecificOppdrag_Digitoll.do',
	  	  data: { applicationUser : applicationUser,
				  tur : tur,	 
	  		  	  avd : avd, 
	  		  	  opd : opd},
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				console.log(data[i].silka)
				console.log(data[i].sitlf)
				opener.jq('#ehavd').val("");opener.jq('#ehavd').val(data[i].siavd); //Avd
				opener.jq('#ehtdn').val("");opener.jq('#ehtdn').val(data[i].sitdn); //Opp
				
				//---------------------------------------------------------------------------------------------------
				//EXP eller TRA beroende av några villkor (se på exempel on: https://toll.github.io/api/mo-eksempler)
				//---------------------------------------------------------------------------------------------------
				if(data[i].wfssokmrn == ""){
					//console.log("A")
					//No transit-MRN means: Eksempel 1 – Tolldeklarasjon for overgang til fri disponering og svensk/EU eksport (kun relevant på vei)
					opener.jq('#ehprt').val("");opener.jq('#ehprt').val("IMMEDIATE_RELEASE_IMPORT");
					opener.jq('#ehupr').val("");opener.jq('#ehupr').val("EXP"); 		
				}else{
					//transit-MRN exists only as clean transit: Eksempel 3 – Transittering som er startet opp utenfor Norge og som bare skal grensepasseres ved ankomst til grensen
					if(data[i].wehrg == "" && data[i].weh0068a == "" && data[i].weh0068b == ""){
						//console.log("B")
						opener.jq('#ehprt').val("");opener.jq('#ehprt').val("TRANSIT_IMPORT");
						opener.jq('#ehupr').val("");opener.jq('#ehupr').val("TRA");
						
					}else{
						//console.log("C")
						//MRN exists and CUDE also: Eksempel 2 – Tolldeklarasjon for overgang til fri disponering og transittering som skal fullføres ved grensepassering
						opener.jq('#ehprt').val("");opener.jq('#ehprt').val("IMMEDIATE_RELEASE_IMPORT");
						opener.jq('#ehupr').val("");opener.jq('#ehupr').val("TRA"); //eller "TRE" ???
						if(data[i].wfssokexp != ""){
							//if this Ekp.id exists then we might use "TRE"
							opener.jq('#ehupr').val("");opener.jq('#ehupr').val("TRE");
						}
					}
				}
				
				
				opener.jq('#ehextref').val("");opener.jq('#ehextref').val(data[i].fssok); //ExtRef
				opener.jq('#ehvkb').val("");opener.jq('#ehvkb').val(data[i].sivkb); //bruttovikt
				opener.jq('#ehntk').val("");opener.jq('#ehntk').val(data[i].sintk); //Kolli
				opener.jq('#ehcnin').val("");opener.jq('#ehcnin').val(data[i].sikdc); //Container
				//Previous Docs
				opener.jq('#ehrg').val("");opener.jq('#ehrg').val(data[i].wehrg); //Dekl.nr
				opener.jq('#eh0068a').val("");opener.jq('#eh0068a').val(data[i].weh0068a); //Dekl.dato
				opener.jq('#eh0068b').val("");opener.jq('#eh0068b').val(data[i].weh0068b); //Dekl.sekv
				//Mrn.nr transit
				opener.jq('#ehtrnr').val("");opener.jq('#ehtrnr').val(data[i].wfssokmrn); //Mrn.nr transit
				opener.jq('#ehtrnr2').val("");opener.jq('#ehtrnr2').val(data[i].wfssokmrn2); //Mrn.nr transit
				opener.jq('#ehtrnr3').val("");opener.jq('#ehtrnr3').val(data[i].wfssokmrn3); //Mrn.nr transit
				opener.jq('#ehtrnr4').val("");opener.jq('#ehtrnr4').val(data[i].wfssokmrn4); //Mrn.nr transit
				opener.jq('#ehtrnr5').val("");opener.jq('#ehtrnr5').val(data[i].wfssokmrn5); //Mrn.nr transit
				opener.jq('#ehtrnr6').val("");opener.jq('#ehtrnr6').val(data[i].wfssokmrn6); //Mrn.nr transit
				opener.jq('#ehtrnr7').val("");opener.jq('#ehtrnr7').val(data[i].wfssokmrn7); //Mrn.nr transit
				opener.jq('#ehtrnr8').val("");opener.jq('#ehtrnr8').val(data[i].wfssokmrn8); //Mrn.nr transit
				opener.jq('#ehtrnr9').val("");opener.jq('#ehtrnr9').val(data[i].wfssokmrn9); //Mrn.nr transit
				
				//Eksp.id
				opener.jq('#eheid').val("");opener.jq('#eheid').val(data[i].wfssokexp); //Eksp.id
				opener.jq('#eheid2').val("");opener.jq('#eheid2').val(data[i].wfssokexp2); //Eksp.id
				opener.jq('#eheid3').val("");opener.jq('#eheid3').val(data[i].wfssokexp3); //Eksp.id
				opener.jq('#eheid4').val("");opener.jq('#eheid4').val(data[i].wfssokexp4); //Eksp.id
				opener.jq('#eheid5').val("");opener.jq('#eheid5').val(data[i].wfssokexp5); //Eksp.id
				opener.jq('#eheid6').val("");opener.jq('#eheid6').val(data[i].wfssokexp6); //Eksp.id
				opener.jq('#eheid7').val("");opener.jq('#eheid7').val(data[i].wfssokexp7); //Eksp.id
				opener.jq('#eheid8').val("");opener.jq('#eheid8').val(data[i].wfssokexp8); //Eksp.id
				opener.jq('#eheid9').val("");opener.jq('#eheid9').val(data[i].wfssokexp9); //Eksp.id
				//Sender
				opener.jq('#ehkns').val("");opener.jq('#ehkns').val(data[i].sikns); //Kundnr
				opener.jq('#ehnas').val("");opener.jq('#ehnas').val(data[i].sinas); //Namn
				opener.jq('#ehrgs').val("");opener.jq('#ehrgs').val(data[i].ehrgs); //Orgnr
				opener.jq('#ehad1s').val("");opener.jq('#ehad1s').val(data[i].siads1); //Adress
				var ad2Avs = data[i].siads2 + " " + data[i].siads3; 
				opener.jq('#ehpbs').val("");opener.jq('#ehpbs').val(ad2Avs); //Adress2+3
				if(data[i].ehems != ""){ opener.jq('#own_ehems_email').val(data[i].ehems) } //email
				//Postnr and City
				var postnrAvs = ""; 
				var cityAvs = "";
				var landAvs = "";
				if(data[i].ehpns != ""){ postnrAvs = data[i].ehpns;}
				if(data[i].ehpss != ""){ cityAvs = data[i].ehpss;}
				if(data[i].ehlks != ""){ landAvs = data[i].ehlks;}
				opener.jq('#ehpns').val(postnrAvs);
				opener.jq('#ehpss').val(cityAvs);
				opener.jq('#ehlks').val(landAvs);
				
				
				//Receiver
				opener.jq('#ehknm').val("");opener.jq('#ehknm').val(data[i].siknk); //Kundnr
				opener.jq('#ehnam').val("");opener.jq('#ehnam').val(data[i].sinak); //Namn
				opener.jq('#ehrgm').val("");opener.jq('#ehrgm').val(data[i].ehrgm); //Orgnr
				opener.jq('#ehad1m').val("");opener.jq('#ehad1m').val(data[i].siadk1); //Adress
				var ad2Mot = data[i].siadk2 + " " + data[i].siadk3; 
				opener.jq('#ehpbm').val("");opener.jq('#ehpbm').val(ad2Mot); //Adress2+3
				if(data[i].ehemm != ""){ opener.jq('#own_ehemm_email').val(data[i].ehemm) } //email
				//Postnr and City
				var postnrMot = ""; 
				var cityMot = "";
				var landMot = "";
				if(data[i].ehpnm != ""){ postnrMot = data[i].ehpnm;}
				if(data[i].ehpsm != ""){ cityMot = data[i].ehpsm;}
				if(data[i].ehlkm != ""){ landMot = data[i].ehlkm;}
				opener.jq('#ehpnm').val(postnrMot);
				opener.jq('#ehpsm').val(cityMot);
				opener.jq('#ehlkm').val(landMot);
				
				
				//finish with child window now
				//opener.callParent(targetTransportId);
				opener.jq('#ehvt').focus();
				window.close();		
			}
	  	  },
	  	  error: function() {
			alert('Error loading ...');
	  	  }
	  	});
		
  	}


//Initialize <div> here for all clazz_dialog
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			maxWidth:500,
			maxHeight: 400,
			width: 300,
			height: 220,
			modal: true
		});
	  });
  });

	jq(function() {
	  jq(".removeLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("removeLink","");
		  
		  jq('#dialogDeleteItem'+counterIndex).dialog( "option", "title", "Slette Varelinje " + jq('#current_id4'+counterIndex).val());
		  //deal with buttons for this modal window
		  jq('#dialogDeleteItem'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
							setBlockUI();
					 		jq('#deleteItemForm'+counterIndex).submit();
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
		  jq('#dialogDeleteItem'+counterIndex).dialog('open');
		 
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

	jq("div.toolbar").html('<span class="text16">Oppdrag</span>');
    
	//event on input field for search
    jq('input.mainList_filter').on( 'keyup click', function () {
    		filterGlobal();
    });
   
	
  });
  	
  	
	