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
    jq("#searchForm").submit(function() {
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
	  jq("#etaDatum").datepicker({ 
		  dateFormat: 'ddmmy' 	  
	  });
	  
	  jq("#etaDatumt").datepicker({ 
		  dateFormat: 'ddmmy' 
	  });
	  
	  jq("#datum").datepicker({ 
		  dateFormat: 'ddmmy' 	  
	  });
	  
	  jq("#datumt").datepicker({ 
		  dateFormat: 'ddmmy' 
	  });
	  
	  
  });
 
jq(function() {
	  jq(".consolidateLink").click(function() {
		  var id = this.id;
		  var record = id.split('_');
		  var etlnrt = record[0];
		  var etpro = record[1];
		  
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_transports_consolidated.do?etlnrt=" + etlnrt+ "&etpro=" + etpro;
		  window.open(controllerUrl, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");	
			
	  });
  }); 
  
//Initialize <div> here for all clazz_dialog
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

 
  //----------------------------------------------------------------
  //START Model dialog: "Delete manifest" (implicit "Update status")
  //----------------------------------------------------------------
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".removeLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("removeLink","");
		  
		  jq('#dialogUpdateStatus'+counterIndex).dialog( "option", "title", "Slett Transport " );
		  //deal with buttons for this modal window
		  jq('#dialogUpdateStatus'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
					 		jq('#updateStatusForm'+counterIndex).submit();
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
		  jq('#dialogUpdateStatus'+counterIndex).dialog('open');
		 
	  });


	  jq(".entryLink").click(function() {
		  var tmp = this.id;
		  var idRecord = tmp.split('_');		
		  var id = idRecord[0]; //MRN
		  var apiType = idRecord[1]; //Type of mode 4= air, 2=rail, etc

		  jq("#"+id).attr(('target','_blank'));
		  var apiType = "";
		  //check if this is an AIR api record
		  if(apiType == 4) { 
			apiType = "air";	
		  }else if(apiType == 2) { 
			apiType = "rail";	
		  }	
		  //default url
		  var controllerUrl = "tvinnsaddigitollv2_childwindow_movroad_entryinfo.do?mrn=" + id;
		  if(apiType == "rail"){
			 controllerUrl = "tvinnsaddigitollv2_childwindow_movrail_entryinfo.do?mrn=" + id;
		  }else if(apiType == "air"){
			 controllerUrl = "tvinnsaddigitollv2_childwindow_movair_entryinfo.do?mrn=" + id;
		  } 

		  window.open(controllerUrl, "codeWin", "top=300px,left=700px,height=150px,width=800px,scrollbars=yes,status=no,location=no");	
			
	  });		



  });
  
  


  //Initialize <div> here for all clazz_dialog
  jq(function() { 
	  jq( ".clazz_dialog_mini" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			maxWidth:400,
			maxHeight: 200,
			width: 400,
			height: 200,
			modal: true
		});
	  });
  });

  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".reassignLink").click(function() {
		  //console.log("inside reassignLink...");
		  var id = this.id;
		  counterIndex = id.replace("reassignLink","");
		  
		  jq('#dialogReassignSign'+counterIndex).dialog( "option", "title", "Tildele ny signatur");
		  //deal with buttons for this modal window
		  jq('#dialogReassignSign'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
							if(jq('#current_sg'+counterIndex).val()!=''){
					 			jq('#reassignSignForm'+counterIndex).submit();
								setBlockUI();
							}
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
		  jq('#dialogReassignSign'+counterIndex).dialog('open');
		 
	  });
  });
  
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".cancelLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("cancelLink","");
		  
		  jq('#dialogUpdateInternalStatus'+counterIndex).dialog( "option", "title", "Kanseller Manifest " + jq('#currentUuid'+counterIndex).val() );
		  //deal with buttons for this modal window
		  jq('#dialogUpdateInternalStatus'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
					 		jq('#updateInternalStatusForm'+counterIndex).submit();
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
		  var apiType = "";
		  //check if this is an AIR api record
		  if(jq("#airplaneImg" + id).length > 0) { 
			apiType = "air";	
		  }	else if(jq("#railImg" + id).length > 0) { 
			apiType = "rail";	
		  }		
		  window.open('tvinnsaddigitollv2_childwindow_manifestinfo.do?id=' + id +'&level=t' + '&apiType=' + apiType, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");
	  });
  });
  
  //---------------------------------
  	//START Model dialog: "Logger"
  	//------------------------------
  	  //Initialize <div> here
  	  jq(function() { 
  		  jq("#dialogLoggerLocal").dialog({
  			  autoOpen: false,
  			  maxWidth:500,
  	          maxHeight: 400,
  	          width: 230,
  	          height: 250,
  			  modal: true
  		  });
  	  });
	 jq(function() {
  		  jq("#alinkLogsgLoggerApi").click(function() {
			  var url = "renderLocalLogsgExpft.do?user=" + jq('#applicationUser').val() + "&logLevel=" + jq("#logLevel").val();
  			  presentPwdDialogLocal(url);
  		  });
		  jq("#alinkLogsgLoggerSadService").click(function() {
			  var url = "renderLocalLogsgService.do?user=" + jq('#applicationUser').val() + "&logLevel=" + jq("#logLevel").val();
  			  presentPwdDialogLocal(url);
  		  });
		  jq("#alinkLogsgLoggerCatalina").click(function() {
			  var url = "renderLocalCatalina.do?user=" + jq('#applicationUser').val();
  			  presentPwdDialogLocal(url);
  		  });
  	  });
  	  
  	  
  	  //---------------------
  	  //PRESENT PWD DIALOG
  	  //---------------------
  	  function presentPwdDialogLocal(url){
  		  jq('#dialogLoggerLocal').dialog( "option", "title", "Logsg-api logger" );
  		  //deal with buttons for this modal window
  		  jq('#dialogLoggerLocal').dialog({
  			 buttons: [ 
  	            {
  				 id: "dialogSaveTU",	
  				 text: "Show",
  				 click: function(){
  					 		if(jq("#pwdLocal").val() == "straffe12"){
  					 			window.open(url , '_blank');
  					 			jq("#loggerStatusLocal").removeClass( "isa_error" );
  				  				jq("#loggerStatusLocal").addClass( "isa_success" );
  				  				jq("#loggerStatusLocal").text("");
	  				  			jq("#pwdLocal").val("");
				  				jq("#logLevelLocal").val("");
				  				jq( this ).dialog( "close" ); 
  					 		}else{
  					 			jq("#loggerStatusLocal").removeClass( "isa_success" );
  				  				jq("#loggerStatusLocal").addClass( "isa_error" );
  				  				jq("#loggerStatusLocal").text("...");
  				  				jq("#pwdLocal").val("");
  				  				jq("#logLevelLocal").val("");
  					 		}
  			 			}
  			 	 },
  	  			{
  			 	 id: "dialogCancelTU",
  			 	 text: "Cancel", 
  				 click: function(){
  					 		jq("#loggerStatusLocal").removeClass( "isa_success" );
  					 		jq("#loggerStatusLocal").removeClass( "isa_error" );
  					 		jq("#loggerStatusLocal").text("");
  					 		jq("#pwdLocal").val("");
  					 		jq("#logLevelLocal").val("");
  					 		
  			  				jq( this ).dialog( "close" ); 
  				 		} 
  	 	 		 } ] 
  		  });
  		  jq('#dialogLoggerLocal').dialog('open');
  	  }



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
  	  //"scrollCollapse":  true,
	  "tabIndex": -1,
	  "order": [[ 1, "asc" ]], //etlnrt
	  "lengthMenu": [ 60, 100, 100, 100],
	  //"lengthMenu": [ 30,  ],
	  "fnDrawCallback": function( oSettings ) {
    	jq('.dataTables_filter input').addClass("inputText12LightYellow");
    	}
    });
	jq("div.toolbar").html('<span class="text16">Transportliste</span>');
    
	//event on input field for search
    jq('input.mainList_filter').on( 'keyup click', function () {
    		filterGlobal();
    });
   	
	
  });
  
  
  
  
  
 
