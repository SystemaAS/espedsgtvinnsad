  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  //Overlay on tab (to mark visually a delay...)
  jq(function() {
    jq('#alinkHeaderNew').click(function() { 
    	jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    });
    jq('#alinkHeader').click(function() { 
    	jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    });
    jq( "#submit" ).click(function( event ) {
    	jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
	});
    //General Header Menus
    jq('#alinkTopicListMenuImp').click(function() { 
    	jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    });
    jq('#alinkTopicListMenuExp').click(function() { 
    	jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    });
    jq('#alinkTopicListMenuNctsExp').click(function() { 
    	jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    });
    jq('#alinkTopicListMenuNctsImp').click(function() { 
    	jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    });
  });
  
  
  
  
  jq(function() {
	  jq("#opd").focus();
	  
  });
  
  jq(function() {
	  jq("#datum").datepicker({ 
		  dateFormat: 'ddmmy' 	  
	  });
	  
	  jq("#datumt").datepicker({ 
		  dateFormat: 'ddmmy' 
	  });
	  
	  jq("#datumFr").datepicker({ 
		  dateFormat: 'ddmmy' 
	  });
	  
  });
  
  
  //-----------------------------------
  //START Model dialog "Kopiera Ärende
  //-----------------------------------
  //Initialize <div> here
  /*
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
		  jq('#dialog'+counterIndex).dialog( "option", "title", "Kopiera Ärende " + jq('#originalOpd'+counterIndex).val() );
		  
		  //deal with buttons for this modal window
		  jq('#dialog'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSave"+counterIndex,	
				 text: "Gå vidare",
				 click: function(){
					 		jq('#copyForm'+counterIndex).submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancel"+counterIndex,
			 	 text: "Avbryt", 
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
	  
 
