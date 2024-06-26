	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//-----------
  	// Tullkontor
  	//-----------
	jq(function() {
		jq('#mainList').on('click', 'td', function(){
			  var id = this.id;
			  //id="tupro${record.tupro}@tuavd${record.tuavd}@ctype${model.callerType}
			  var record = id.split('@');
			  var emdkm_ff = record[0].replace("emdkm", "");
			  var emrgt_ff = record[1].replace("trreforg", "");
		  	  var callerType = record[2].replace("ctype", "");
			  var emdkmt_ff = record[3].replace("emdkmt", "");
			  var emrgr_ff = record[4].replace("avsid", "");	
			  	//console.log(emdkm_ff + " " + emrgt_ff + " caller:" + callerType);

			  	if(callerType == 'emdkm'){ 
				  opener.jq('#emdkm_ff').prop('readonly',false);					
				  opener.jq('#emdkm_ff').val(emdkm_ff);
				  opener.jq('#emdkm_ff').prop('readonly',true);
				  //
				  opener.jq('#emdkmt_ff').prop('readonly',false);					
				  opener.jq('#emdkmt_ff').val(emdkmt_ff);
				  opener.jq('#emdkmt_ff').prop('readonly',true);
				  //	
				  opener.jq('#emrgt_ff').prop('readonly',false);
				  opener.jq('#emrgt_ff').val(emrgt_ff);	
				  opener.jq('#emrgt_ff').prop('readonly',true);
				  //
				  opener.jq('#emrgr_ff').prop('readonly',false);
				  opener.jq('#emrgr_ff').val(emrgr_ff);	
				  opener.jq('#emrgr_ff').prop('readonly',true);
				  //focus
				  opener.jq('#emdkm_ff').focus();
				  //close child window
			  	  window.close();

			  	}else if(callerType == 'etlnrt'){
					var params = "emdkm" + emdkm_ff + "_emdkmt" + emdkmt_ff;
					//DEBUG --> alert(params + "applicationUser:" + jq('#applicationUser').val() + " etlnrt:" + jq('#etlnrt').val());
					if(params != ""){
						
						jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
						  jq.ajax({
					  	  	  type: 'GET',
					  	  	  url: 'createMasterFromZadmomlf_Digitoll.do',
					  	  	  data: { applicationUser : jq('#applicationUser').val(),
										params : params,
										lnrt : jq('#etlnrt').val(),
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
						  window.opener.location.href="tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=" + jq('#etlnrt').val();
						  window.close();     
		             }, 800); //milliseconds: in order to avoid a refresh in transport due to the above Ajax create master. It could take more time to be finished on the background...
					
				}

	    });
	});
	
	
	jq(function() {
	  jq("#date").datepicker({ 
		  dateFormat: 'yymmdd' 	  
	  });
	});
	
	
	jq(function() {
		jq('#insertButton').click(function() { 
			//DEBUG -->alert(jq('#etlnrt').val() + " " + jq('#emdkm_insert').val() + jq('#emdkmt_insert').val());
			if(jq('#emdkm_insert').val() != '' && jq('#emdkmt_insert').val() != ''){
				setBlockUI();
				window.location = 'tvinnsaddigitollv2_edit_light_master_zadmomlf.do?etlnrt=' + jq('#etlnrt').val() + '&emdkm=' + jq('#emdkm_insert').val()+ '&emdkmt=' + jq('#emdkmt_insert').val();
				//jq.unblockUI();		 		
			}
	    });
	});
	
	
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".removeLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("removeLink","");
		  
		  jq('#dialogDeleteRecord'+counterIndex).dialog( "option", "title", "Slett record " + jq('#current_id2'+counterIndex).val());
		  //deal with buttons for this modal window
		  jq('#dialogDeleteRecord'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
							setBlockUI();
					 		jq('#deleteRecordForm'+counterIndex).submit();
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
		  jq('#dialogDeleteRecord'+counterIndex).dialog('open');
		 
	  });
  });

//Initialize <div> here for all clazz_dialog
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			maxWidth:500,
			maxHeight: 400,
			width: 300,
			height: 180,
			modal: true
		});
	  });
  });
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterTur () {
    		jq('#mainList').DataTable().search(
      		jq('#mainList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
	  jq('#mainList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',               
		  "searchHighlight": true,
		  "tabIndex": -1,
	  	  "order": [[ 3, "desc" ]], //ETA
		  "lengthMenu": [ 50, 100, 200, 500],
		  "fnDrawCallback": function( oSettings ) {
	    	jq('.dataTables_filter input').addClass("inputText12LightYellow");
			
	    	}
	  });
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterTur();
      });
      
    });   
  	
  	
	