	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//-----------
  	// Tullkontor
  	//-----------
	jq(function() {
		jq('#tullkontorList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			  var tkkode = record[0].replace("tkkode", "");
			  var tktxtn = record[1].replace("tktxtn", "");
			  var callerType = record[2].replace("ctype", "");
			  
			  //Tullkontor - ettsd
			  if(callerType == 'ettsd'){ 
				console.log("Bingo!!") 
				  opener.jq('#ettsd').val(tkkode);
				  opener.jq('#ettsd').focus();
				  
			  }else if (callerType == 'xxxx'){  
				  opener.jq('#xxxx').val(tkkode);
				  opener.jq('#xxxx').focus();
				  
			  }
			  //close child window
			  window.close();
			  
	    });
	});
	
	
	jq(function() {
	    jq('#eibl').focus(function() {
	    	if(jq('#eibl').val()!=''){
	    		refreshCustomValidity(jq('#eibl')[0]);
	  		}
	  	});
	    jq('#eistk').focus(function() {
	    	if(jq('#eistk').val()!=''){
	    		refreshCustomValidity(jq('#eistk')[0]);
	  		}
	  	});
		jq('#eivnt').focus(function() {
	    	if(jq('#eivnt').val()!=''){
	    		refreshCustomValidity(jq('#eivnt')[0]);
	  		}
	  	});
	    jq('#eirge').focus(function() {
	    	if(jq('#eirge').val()!=''){
	    		refreshCustomValidity(jq('#eirge')[0]);
	  		}
	  	});
	});	


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
				opener.jq('#ehvkb').val("");opener.jq('#ehvkb').val(data[i].sivkb); //bruttovikt
				opener.jq('#ehntk').val("");opener.jq('#ehntk').val(data[i].sintk); //Kolli
				opener.jq('#ehcnin').val("");opener.jq('#ehcnin').val(data[i].sikdc); //Container
				//Previous Docs
				opener.jq('#ehrg').val("");opener.jq('#ehrg').val(data[i].wehrg); //Dekl.nr
				opener.jq('#eh0068a').val("");opener.jq('#eh0068a').val(data[i].weh0068a); //Dekl.dato
				opener.jq('#eh0068b').val("");opener.jq('#eh0068b').val(data[i].weh0068b); //Dekl.sekv
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
				//window.close();		
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
  	
  	
	