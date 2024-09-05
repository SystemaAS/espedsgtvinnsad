id="alinkTransport"  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";

  	jq(function() {
	  
	  jq("#dateLocal").datepicker({ 
		  dateFormat: 'yy-mm-dd' 	  
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
  	          maxHeight: 500,
  	          width: 230,
  	          height: 280,
  			  modal: true
  		  });
  	  });
	 jq(function() {
  		  jq("#alinkLogsgLoggerApi").click(function() {
			  var url = "renderLocalLogsgExpft.do?user=" + jq('#applicationUser').val() + "&logLevel=" + jq("#logLevelLocal").val();
  			  presentPwdDialogLocal(url);
  		  });
		  jq("#alinkLogsgLoggerSadService").click(function() {
			  var url = "renderLocalLogsgService.do?user=" + jq('#applicationUser').val() + "&logLevel=" + jq("#logLevelLocal").val();
  			  presentPwdDialogLocal(url);
  		  });
		  jq("#alinkLogsgLoggerRoadEntry").click(function() {
			  var url = "renderLocalLogsgRoadEntry.do?user=" + jq('#applicationUser').val() + "&logLevel=" + jq("#logLevelLocal").val();
  			  presentPwdDialogLocal(url);
  		  });
		  jq("#alinkLogsgLoggerCatalina").click(function() {
			  var url = "renderLocalCatalina.do?user=" + jq('#applicationUser').val();
  			  presentPwdDialogLocal(url);
  		  });
		  jq("#alinkLogsgLoggerFremHouseFtplog").click(function() {
			  jq('#alinkLogsgLoggerFremHouseFtplog').attr('target','_blank');
	    	  window.open('tvinnsaddigitollv2_childwindow_external_houses_log.do?action=doFind&date=20240901', "codeWin", "top=300px,left=500px,height=600px,width=1070px,scrollbars=no,status=no,location=no");
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
								
								if(jq("#dateLocal").val() != ''){
									url = url + "&date=" + jq("#dateLocal").val();
								}
  					 			window.open(url , '_blank');
  					 			//jq("#loggerStatusLocal").removeClass( "isa_error" );
  				  				//jq("#loggerStatusLocal").addClass( "isa_success" );
  				  				//jq("#loggerStatusLocal").text("");
	  				  			jq("#pwdLocal").val("");
				  				jq("#logLevelLocal").val("");
				  				jq( this ).dialog( "close" ); 
  					 		}else{
  					 			//jq("#loggerStatusLocal").removeClass( "isa_success" );
  				  				//jq("#loggerStatusLocal").addClass( "isa_error" );
  				  				//jq("#loggerStatusLocal").text("...");
  				  				jq("#pwdLocal").val("");
  				  				jq("#logLevelLocal").val("");
  					 		}
  			 			}
  			 	 },
  	  			{
  			 	 id: "dialogCancelTU",
  			 	 text: "Cancel", 
  				 click: function(){
  					 		//jq("#loggerStatusLocal").removeClass( "isa_success" );
  					 		//jq("#loggerStatusLocal").removeClass( "isa_error" );
  					 		jq("#loggerStatusLocal").text("");
  					 		jq("#pwdLocal").val("");
  					 		jq("#dateLocal").val("");
							jq("#logLevelLocal").val("");
  					 		
  			  				jq( this ).dialog( "close" ); 
  				 		} 
  	 	 		 } ] 
  		  });
  		  jq('#dialogLoggerLocal').dialog('open');
  	  }


  
 
