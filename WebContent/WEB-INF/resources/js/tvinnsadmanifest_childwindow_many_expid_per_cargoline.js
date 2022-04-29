	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  	
  	jq(function() {
		jq('#newButton').click(function() {
		  	jq('#cmli').val(jq('#newLineCounter').val());
			jq('#cmavde').val("");	
			jq('#cmtdne').val("");	
			jq('#cmetyp').val("");	
			jq('#cmeid').val("");	
			jq('#cmeser').val("");	
	  	});
 		jq('#cmavde').focus(function() {
    	if(jq('#cmavde').val()!=''){
    		refreshCustomValidity(jq('#cmavde')[0]);
  		}
        });
		jq('#cmtdne').focus(function() {
    	if(jq('#cmtdne').val()!=''){
    		refreshCustomValidity(jq('#cmtdne')[0]);
  		}
        });
		jq('#cmetyp').focus(function() {
    	if(jq('#cmetyp').val()!=''){
    		refreshCustomValidity(jq('#cmetyp')[0]);
  		}
        });
		jq('#cmeid').focus(function() {
    	if(jq('#cmeid').val()!=''){
    		refreshCustomValidity(jq('#cmeid')[0]);
  		}
        });
		jq('#cmeser').focus(function() {
    	if(jq('#cmeser').val()!=''){
    		refreshCustomValidity(jq('#cmeser')[0]);
  		}
        });

			
		jq( "#mainForm" ).submit(function( event ) {
  			setBlockUI();
	  	 }); 
		
		
  		/*
		jq('#buttonCloseOk').click(function(){
			setBlockUI();
			
			jq( ".clazzPickAware" ).each(function( i ) {
				  var id = this.id;
				  var record = id.split('_');
				  var avd = record[0].replace("avd", "");
				  var tdn = record[1].replace("tdn", "");
				  var counter = i + 1;
				  var requestParams = "&clavd=" + avd + "&cltdn=" + tdn + "&mode=B" + "&clpro=" + jq('#parentClpro').val();
				  //alert("PARAMS:" + requestParams);
				  
				  if(jq('#avd' + avd + '_tdn' + tdn).prop('checked')){
					  //alert("CHECKED:" + jq('#avd' + avd + '_tdn' + tdn).prop('checked'));
					  
					  jq.ajax({
				  	  	  type: 'GET',
				  	  	  url: 'bindOppdragToTur_TvinnSadManifest.do',
				  	  	  data: { applicationUser : jq('#applicationUser').val(),
						  			requestParams : requestParams },
				  	  	  dataType: 'json',
				  	  	  cache: false,
				  	  	  async: false,
				  	  	  contentType: 'application/json',
				  	  	  success: function(data) {
				  	  		var len = data.length;
				  	  		for ( var i = 0; i < len; i++) {
				  	  			//Update has been done successfully
				  	  		}
				  	  	  },
					  	  error: function() {
				  	  	    //alert('Error loading ...');
				  	  	  }
				  	  });
					  
			  	  }
			});
			
			//we must reload the parent window since the use case updates the invoice list (if the end-user has selected some invoices to import)
			//window.opener.location.reload();
			opener.callParent(); //since this is the only way to activate blockUI over there ... window.opener...reload() won't do...
			window.close();
		});
		//abort
		jq('#cancel').click(function(){
			window.close();
		});
		*/
	});
  	
  	//refreshes all html 5 CustomValidity functions from jQuery
    function refreshCustomValidity(element){
  	  element.setCustomValidity('');
    }



	//Initialize <div> here for all clazz_dialog
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			maxWidth:400,
			maxHeight: 300,
			width: 300,
			height: 180,
			modal: true
		});
	  });
  });
  //----------------------------------------------------------------
  //START Model dialog: "Delete cargo line" (implicit "Update status")
  //----------------------------------------------------------------
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".removeLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("removeLink","");
		  jq('#dialogRemove'+counterIndex).dialog( "option", "title", "Slette Eksport id " + jq('#currentCmtdn'+counterIndex).val() );
		  //deal with buttons for this modal window
		  jq('#dialogRemove'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
					 		jq('#deleteForm'+counterIndex).submit();
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
		  jq('#dialogRemove'+counterIndex).dialog('open');
		 
	  });
  });
  



 
  	
  	
   
    
    
  	
    
   
  	
	