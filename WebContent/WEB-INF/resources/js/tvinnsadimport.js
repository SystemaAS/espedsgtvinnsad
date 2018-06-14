  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ css: { fontSize: '22px' }, message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
	  jq("#opd").focus();
  });
  
  jq(function() {
	  jq("#datum").datepicker({ 
		  //dateFormat: 'yymmdd', 
		  dateFormat: 'ddmmy' 
		  //defaultDate: "-6m"	  
	  });
	  jq("#datumt").datepicker({ 
		  //dateFormat: 'yymmdd', 
		  dateFormat: 'ddmmy' 
		  //defaultDate: "-6m"	  
	  });
	  jq( "#submit" ).click(function( event ) {
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
  });
 
  
  
  //-----------------------------------
  //START Model dialog "Kopiera Ärende
  //-----------------------------------
  //Initialize <div> here
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
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".copyLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("copyLink","");
		  //setters (add more if needed)
		  jq('#dialog'+counterIndex).dialog( "option", "title", "Kopier Tolldeklarasjon " + jq('#originalOpd'+counterIndex).val() );
		  
		  //deal with buttons for this modal window
		  jq('#dialog'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSave"+counterIndex,	
				 text: "Fortsett",
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
	
  
  //----------------------------------------------------------------
  //START Model dialog: "Delete tolldekl" (implicit "Update status")
  //----------------------------------------------------------------
  
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".removeLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("removeLink","");
		  
		  jq('#dialogUpdateStatus'+counterIndex).dialog( "option", "title", "Slette Tolldeklarasjon " + jq('#currentOpd'+counterIndex).val() );
		  //deal with buttons for this modal window
		  jq('#dialogUpdateStatus'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
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
  
  //----------------------------------------------------------------------------------
  //START Model dialog "Kopiera Ärende from template (norsk export/transport order)
  //--------------------------------------------------------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq("#dialogCopyFromTransportUppdrag").dialog({
		  autoOpen: false,
		  maxWidth:700,
          maxHeight: 500,
          width: 650,
          height: 450,
		  modal: true
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq("#copyFromTransportUppdragLink").click(function() {
		  //setters (add more if needed)
		  jq('#dialogCopyFromTransportUppdrag').dialog( "option", "title", "Lage Ny Tolldeklarasjon" );
		  
		  //deal with buttons for this modal window
		  jq('#dialogCopyFromTransportUppdrag').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Fortsett",
				 click: function(){
					 		jq('#copyFromTransportUppdragForm').submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Avbryt", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSaveSU").button("option", "disabled", false);
					 		jq("#selectedAvd").val("");
					 		jq("#selectedSign").val("");
					 		jq("#selectedOpd").val("");
					 		jq("#selectedExtRefNr").val("");
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveTU").button("option", "disabled", false);
		  //open now
		  jq('#dialogCopyFromTransportUppdrag').dialog('open');
	  });
  });
  //Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
  jq(function() {
	  jq("#selectedAvd").change(function() {
		  /* deprecated
		  if(jq('#selectedAvd').val()!=''){
			  if(jq('#selectedOpd').val()!='' || jq('#selectedExtRefNr').val()!=''){
				  jq("#dialogSaveTU").button("option", "disabled", false);
			  }else{
				  jq("#dialogSaveTU").button("option", "disabled", true);
			  }
		  }else{
			  applyRuleOnDialogTranspUppdragNullAvd();
		  }*/
	  });
	  jq("#selectedOpd").blur(function() {
		  /* deprecated
		  if(jq('#selectedAvd').val()!=''){
			  if(jq("#selectedOpd").val()!=''){
				  jq('#selectedExtRefNr').val("");
				  jq("#dialogSaveTU").button("option", "disabled", false);
			  }else{
				  if(jq("#selectedExtRefNr").val()==''){
					  jq("#dialogSaveTU").button("option", "disabled", true);
				  }
			  }
		  }else{
			  applyRuleOnDialogTranspUppdragNullAvd();
		  }*/
	  });
	  jq("#selectedExtRefNr").blur(function() {
		  //applyRuleOnDialogTranspUppdragExtRef();
		  
	  });
  });
  function applyRuleOnDialogTranspUppdragNullAvd(){
	  if(jq('#selectedOpd').val()=='' && jq('#selectedExtRefNr').val()==''){
		  jq("#dialogSaveTU").button("option", "disabled", false);
	  }else{
		  jq("#dialogSaveTU").button("option", "disabled", true);
	  }
  }
  function applyRuleOnDialogTranspUppdragExtRef(){
	  if(jq('#selectedExtRefNr').val()!=''){
		  jq("#dialogSaveTU").button("option", "disabled", false);
	  }else{
		  jq("#dialogSaveTU").button("option", "disabled", true);
	  }
  }
  //---------------------------------------------------
  //END Model dialog "Kopiera Ärende from Trans.Uppdr.
  //---------------------------------------------------
  
  
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
	jq.fn.dataTable.moment( 'DDMMYY' );  
    //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
    jq('#mainList').dataTable( {
  	  //"dom": '<"top"f>t<"bottom"><"clear">',
  	  "dom": '<"top"f>rt<"bottom"lip><"clear">',
  	  "scrollY":        	"700px",
  	  "scrollCollapse":  true,
	  "tabIndex": -1,
	  "order": [[ 5, "desc" ]], //date
	  "lengthMenu": [ 25, 50, 100, 200]
    });
    //event on input field for search
    jq('input.mainList_filter').on( 'keyup click', function () {
    		filterGlobal();
    });
   
	
  });

  
  


  