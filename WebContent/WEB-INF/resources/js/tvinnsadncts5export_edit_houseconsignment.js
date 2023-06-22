  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  
  //Overlay on tab (to mark visually a delay...)
  jq(function() {
    jq('#alinkHeaderNew').click(function() { 
    	setBlockUI();
    });
    jq('#alinkHeader').click(function() { 
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
  });	
   
  jq(function() {
	  jq("#opd").focus();
  });
  
  jq(function() {
	  jq("#datum").datepicker({ 
		//dateFormat: 'yymmdd',
		  dateFormat: 'ddmmy' 	  
	  });
	  jq("#datumt").datepicker({ 
		  dateFormat: 'ddmmy' 
	  });
  });


function getItemData(record) {
  		
	  	var htmlValue = record.id;
	  	var applicationUserParam = jq('#applicationUser').val();
	  	var avdParam = jq('#avdItemList').val();
	  	var opdParam = jq('#opdItemList').val();
	  	//alert(htmlValue);
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getSpecificHouseConsignmentChosenFromGuiElement_TvinnSadNcts5Export.do',
	  	  data: { applicationUser : applicationUserParam, 
	  		  	  elementValue : htmlValue, 
	  		  	  avd : avdParam, 
	  		  	  opd : opdParam },
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				jq('#tcvktb').val(data[i].tcvktb)
				/*
				jq('#lineNr').val(data[i].tvli)
				jq('#tvli').val(data[i].tvli); 
				jq('#tvvnt').val(data[i].tvvnt);jq('#tvvt').val(data[i].tvvt);
				jq('#tvvt2').val(data[i].tvvt2);jq('#tvvt3').val(data[i].tvvt3);
				jq('#tvvt4').val(data[i].tvvt4);jq('#tvvt5').val(data[i].tvvt5);
				
				jq('#tvtdr').val(data[i].tvtdr);jq('#tvdk').val(data[i].tvdk); 
				jq('#tvvktb').val(data[i].tvvktb); jq('#tvvktn').val(data[i].tvvktn); 
				jq('#tvnt').val(data[i].tvnt);jq('#tvdref').val(data[i].tvdref);
				jq('#tvdsk').val(data[i].tvdsk);
				
				jq('#tvdty').val(data[i].tvdty); 
				jq('#tvalk').val(data[i].tvalk);
				jq('#tvblk').val(data[i].tvblk);
				jq('#tveh').val(data[i].tveh);
				
				//Extra section
				jq('#tvvtsk').val(data[i].tvvtsk);jq('#tvtdt').val(data[i].tvtdt);
				jq('#tvtdsk').val(data[i].tvtdsk);jq('#tvtdo').val(data[i].tvtdo);
				jq('#tvtdos').val(data[i].tvtdos);
				jq('#tvdosk').val(data[i].tvdosk);jq('#tvmtxt').val(data[i].tvmtxt);
				jq('#tvmsk').val(data[i].tvmsk);jq('#tvtlo').val(data[i].tvtlo);
				jq('#tvmsk').val(data[i].tvmsk);jq('#tvtlo').val(data[i].tvtlo);
				jq('#tvexkd').val(data[i].tvexkd);jq('#tvexlk').val(data[i].tvexlk);
				jq('#tvavd2').val(data[i].tvavd2);jq('#tvtdn2').val(data[i].tvtdn2);
				jq('#tvcnr').val(data[i].tvcnr);jq('#tvmn').val(data[i].tvmn);
				jq('#tvmnsk').val(data[i].tvmnsk);jq('#tvtdn2').val(data[i].tvtdn2);
				jq('#tvnteh').val(data[i].tvnteh);
				jq('#tvcnr2').val(data[i].tvcnr2);jq('#tvcnr3').val(data[i].tvcnr3);
				jq('#tvcnr4').val(data[i].tvcnr4);jq('#tvcnr5').val(data[i].tvcnr5);
				jq('#tvcnr6').val(data[i].tvcnr6);
				jq('#tvfv').val(data[i].tvfv);jq('#tvfvnt').val(data[i].tvfvnt);
				jq('#tvkns').val(data[i].tvkns);
				jq('#tvnas').val(data[i].tvnas);
				jq('#tvads1').val(data[i].tvads1);jq('#tvpns').val(data[i].tvpns);
				jq('#tvpss').val(data[i].tvpss);jq('#tvlks').val(data[i].tvlks);
				jq('#tvsks').val(data[i].tvsks);jq('#tvtins').val(data[i].tvtins);
				jq('#tvknk').val(data[i].tvknk);
				jq('#tvnak').val(data[i].tvnak);
				jq('#tvadk1').val(data[i].tvadk1);jq('#tvpnk').val(data[i].tvpnk);
				jq('#tvpsk').val(data[i].tvpsk);jq('#tvlkk').val(data[i].tvlkk);
				jq('#tvskk').val(data[i].tvskk);jq('#tvtink').val(data[i].tvtink);
				jq('#tvln').val(data[i].tvln);jq('#tvrefl').val(data[i].tvrefl);
				
				//rubrik 31 (extra Godsmärke)
				jq('#tvmn2').val(data[i].tvmn2);jq('#tveh2').val(data[i].tveh2);
				jq('#tvnt2').val(data[i].tvnt2);jq('#tvnteh2').val(data[i].tvnteh2);
				jq('#tvmn3').val(data[i].tvmn3);jq('#tveh3').val(data[i].tveh3);
				jq('#tvnt3').val(data[i].tvnt3);jq('#tvnteh3').val(data[i].tvnteh3);
				jq('#tvmn4').val(data[i].tvmn4);jq('#tveh4').val(data[i].tveh4);
				jq('#tvnt4').val(data[i].tvnt4);jq('#tvnteh4').val(data[i].tvnteh4);
				jq('#tvmn5').val(data[i].tvmn5);jq('#tveh5').val(data[i].tveh5);
				jq('#tvnt5').val(data[i].tvnt5);jq('#tvnteh5').val(data[i].tvnteh5);
				//end rubrik 31
				
				//rubrik 44 (Supplerende Opplysninger) - Multilines
				jq('#tvdty2').val(data[i].tvdty2);jq('#tvdref2').val(data[i].tvdref2);
				jq('#tvdsk2').val(data[i].tvdsk2);jq('#tvdo2').val(data[i].tvdo2);
				jq('#tvdosk2').val(data[i].tvdosk2);
				jq('#tvmtxt2').val(data[i].tvmtxt2);jq('#tvmsk2').val(data[i].tvmsk2);
				jq('#tvtlo2').val(data[i].tvtlo2);jq('#tvexkd2').val(data[i].tvexkd2);
				jq('#tvexlk2').val(data[i].tvexlk2);
				
				jq('#tvdty3').val(data[i].tvdty3);jq('#tvdref3').val(data[i].tvdref3);
				jq('#tvdsk3').val(data[i].tvdsk3);jq('#tvdo3').val(data[i].tvdo3);
				jq('#tvdosk3').val(data[i].tvdosk3);
				jq('#tvmtxt3').val(data[i].tvmtxt3);jq('#tvmsk3').val(data[i].tvmsk3);
				jq('#tvtlo3').val(data[i].tvtlo3);jq('#tvexkd3').val(data[i].tvexkd3);
				jq('#tvexlk3').val(data[i].tvexlk3);
				
				jq('#tvdty4').val(data[i].tvdty4);jq('#tvdref4').val(data[i].tvdref4);
				jq('#tvdsk4').val(data[i].tvdsk4);jq('#tvdo4').val(data[i].tvdo4);
				jq('#tvdosk4').val(data[i].tvdosk4);
				jq('#tvmtxt4').val(data[i].tvmtxt4);jq('#tvmsk4').val(data[i].tvmsk4);
				jq('#tvtlo4').val(data[i].tvtlo4);jq('#tvexkd4').val(data[i].tvexkd4);
				jq('#tvexlk4').val(data[i].tvexlk4);
				
				
				jq('#tvdty_readonly').val(data[i].tvdty);
				jq('#tvdref_readonly').val(data[i].tvdref);
				jq('#tvdsk_readonly').val(data[i].tvdsk);
				jq('#tvdo').val(data[i].tvdo);
				// End rubrik 44
				
				
				//Sikkerhet - Security section
				jq('#tvtkbm').val(data[i].tvtkbm);
				jq('#tvkref').val(data[i].tvkref);
				jq('#tvfgkd').val(data[i].tvfgkd);
				jq('#tvknss').val(data[i].tvknss);
				jq('#tvnass').val(data[i].tvnass);
				jq('#tvadss1').val(data[i].tvadss1);
				jq('#tvknss').val(data[i].tvknss);
				jq('#tvpnss').val(data[i].tvpnss);
				jq('#tvpsss').val(data[i].tvpsss);
				jq('#tvlkss').val(data[i].tvlkss);
				jq('#tvskss').val(data[i].tvskss);
				jq('#tvtinss').val(data[i].tvtinss);
				jq('#tvknks').val(data[i].tvknks);
				jq('#tvnaks').val(data[i].tvnaks);
				jq('#tvadks1').val(data[i].tvadks1);
				jq('#tvknks').val(data[i].tvknks);
				jq('#tvpnks').val(data[i].tvpnks);
				jq('#tvpsks').val(data[i].tvpsks);
				jq('#tvlkks').val(data[i].tvlkks);
				jq('#tvskks').val(data[i].tvskks);
				jq('#tvtinks').val(data[i].tvtinks);
				
				
				//debug information on Fetch item
				jq('#debugPrintlnAjaxItemFetchInfo').text(data[i].debugPrintlnAjax);
				*/
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
	  	
  	}
  		





  
  /** TODO
  //-----------------------------------
  //START Model dialog "Kopiera Ärende
  //-----------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			modal: true
		});
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".copyLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("copyLink","");
		  //setters (add more if needed)
		  jq('#dialog'+counterIndex).dialog( "option", "title", "Kopi Angivelse " + jq('#originalOpd'+counterIndex).val() );
		  
		  //deal with buttons for this modal window
		  jq('#dialog'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSave"+counterIndex,	
				 text: "Fortsæt",
				 click: function(){
					 		jq('#copyForm'+counterIndex).submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancel"+counterIndex,
			 	 text: "Annullér", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSave"+counterIndex).button("option", "disabled", true);
					 		jq("#newAvd"+counterIndex).val("");
					 		jq("#newSign"+counterIndex).val("");
							jq( this ).dialog( "close" ); 
					 		  
				 		} 
	 	 		 } ] 
			  
		  });
		  //init values
		  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  
		  //open now
		  jq('#dialog'+counterIndex).dialog('open');
		 
	  });
  });
  //Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
  jq(function() {
	  jq(".newAvd").change(function() {
		  if(jq("#dialog"+counterIndex).find('.newAvd').val()!='' && jq("#dialog"+counterIndex).find('.newSign').val()!=''){
			  jq("#dialogSave"+counterIndex).button("option", "disabled", false);
			  
		  }else{
			  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  }
	  });
	  jq(".newSign").change(function() {
		  if(jq("#dialog"+counterIndex).find('.newAvd').val()!='' && jq("#dialog"+counterIndex).find('.newSign').val()!=''){
			  jq("#dialogSave"+counterIndex).button("option", "disabled", false);
			  
		  }else{
			  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  }
	  });
	  
	  
  });
  //---------------------------------
  //END Model dialog "Kopiera Ärende
  //---------------------------------
	  
  */
  
//-------------------
  //Datatables jquery
  //-------------------
  //private function
  function filterGlobal () {
    jq('#tblHcLines').dataTable().search(
    	jq('#tblHcLines_filter').val()
    ).draw();
  }

  jq(document).ready(function() {
	  jq.fn.dataTable.moment( 'DDMMYY' );  
    //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
    jq('#tblHcLines').dataTable( {
	    	  "dom": '<"top">t<"bottom"flip><"clear">',
	    	  "scrollY":    "180px",
	    	  "deferRender": true, //to speed the table load
	    	  "scrollCollapse":  true,
	  		  "columnDefs": [{ "type": "num", "targets": 0 }],
	  		  "lengthMenu": [ 75, 100, 300, 400, 900]
  	});
    //event on input field for search
    jq('input.tblHcLines_filter').on( 'keyup click', function () {
    		filterGlobal();
    });
   
	
  });
  
  
  
  
  