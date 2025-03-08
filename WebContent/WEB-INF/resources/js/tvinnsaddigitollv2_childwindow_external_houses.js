	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq('#partyList').on('click', 'td', function(){
			  var id = this.id;
			  // file via ajax
			  console.log("click on td... id:" + id);
			  
			  //only these td's are of interest!!
			  if(id.indexOf("singlePick") != -1){
				  var record = id.split('_');
				  /* OLD 
				  if(record.length >= 2){
					  var orgnr = record[0].replace("orgnr", "");
					  var name = record[1].replace("name", "");
					  //var commtype = record[2].replace("commtype", "");
					  //var format = record[3].replace("format", "");
					   			  
				  	  opener.jq('#ownReceiverOrgNr').val(orgnr);
					  opener.jq('#ownReceiverName').val(name);
				  }
	 			  
				  opener.jq('#ehrgs').val(syrg);refreshCustomValidity(opener.jq('#ehrgs')[0]);
				  opener.jq('#ehad1s').val(adr1);
				  opener.jq('#ehpss').val(adr3);
				  opener.jq('#ehpns').val(postnr);
				  opener.jq('#ehlks').val(land);
				  opener.jq('#ehnas').focus();	
			  	  
				  //close child window
				  window.close();
			      */
			   }

			   //upload file	
			   if(id.indexOf("file") != -1){
				  
				  console.log("click on td:" + id);
				  var fileId = id.replace("TD", "");
				  console.log("click on id for file-field:" + fileId);
				 	
				  
				  jq('#' + fileId).change(function(){
				  	var file = jq('#' + fileId)[0].files;
					console.log("file:" + file);
					
					//if(file!=null && file.length > 0){
						//DEBUG
						console.log("applicationUser:" + jq('#applicationUser').val());
						
						var formData = new FormData();
						formData.append('applicationUser', jq('#applicationUser').val());
						
						if(file!=null && file.length > 0){
							formData.append('file', file[0]);
							//DEBUG
							console.log("file:" + file[0]);
							console.log("file-size:" + file.length);
							console.log("file-name:" + file[0].name);
							console.log("file-size:" + file[0].size);
							console.log("file-type:" + file[0].type);

						}else{
							formData.append('file', null);
						}
						
						jq.ajax({
					        type: "POST",
					        url: "tvinnsaddigitollv2_saveAttachmentTempOnMaster.do",
					        //async: true,
							data: formData,
							dataType: 'json',
							enctype: 'multipart/form-data',
					        contentType: false,
					        processData: false,
					        success: function (data) {
					            //console.log(data);
								console.log("SUCCESS");
					        }
	    				});
						
					//}
					
				  });
				  	
			   }
	
		  });
	});
	
	
	jq(function() {
		jq('#buttonCheckAll').click(function(){
			jq( ".clazzSendDocIdToExternalPartyAware" ).each(function(  ) {
				var id = this.id;
				var record = id.split('_');
				//orgnr${record.orgnr}_name${record.name}_commtype${record.commtype}_format${record.format}
				//var orgnr = record[0].replace("orgnr", "");
				//var name = record[1].replace("name", "");
				//var commtype = record[2].replace("commtype", "");
				//var format = record[3].replace("format", "");
				
				
				if (jq(this).is(":checked")) {	  
					jq(this).prop('checked', false);
				}else{
					jq(this).prop('checked', true);
				}
				
			});
		});
		
		//abort
		jq('#buttonCancel').click(function(){
			window.close();
		});
		
		
		jq('#buttonCreateFilesOK').click(function(){
			var params = "";  
			jq( ".clazzSendDocIdToExternalPartyAware" ).each(function(  ) {
				
				var id = this.id;
				console.log("orgnr:" + id);
				var record = id.split('_');
				var orgnr = record[0].replace("orgnr", "");
				//var name = record[1].replace("name", "");
				//var commtype = record[2].replace("commtype", "");
				//var format = record[3].replace("format", "");
				  //var counter = i + 1;
				  //alert(orgnr + "-" + name + "-" + commtype + "-" + format);
				  console.log("orgnr:" + orgnr);
		  		  
				  if(jq('#orgnr' + orgnr).prop('checked')){
					 console.log("checked!!");
					 //there is a problem with spaces in a field id (std rule: do not use spaces in an id)
					 var name = jq('#orgnr' + orgnr).attr('title');	
					 var tmp = "orgnr" + orgnr + "_name" + name + "#";
					 params = params + tmp; 
				  }	
				  console.log("params:" + params);
			});
			
			
			
			if(params != ""){
			  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
			  jq.ajax({
		  	  	  type: 'GET',
		  	  	  url: 'tvinnsaddigitollv2_send_masterId_toExternalParties.do',
		  	  	  data: { applicationUser : jq('#applicationUser').val(),
							params : params,
							emlnrt : jq('#emlnrt').val(),
							emlnrm : jq('#emlnrm').val()},

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
				  window.opener.location.href="tvinnsaddigitollv2_edit_master.do?action=doFind&emlnrt=" + jq('#emlnrt').val() + "&emlnrm=" + jq('#emlnrm').val();
				  window.close();     
             }, 800); //milliseconds: in order to avoid a refresh in master due to the above Ajax create house. It could take more time to be finished on the background...
			
		});
		
		
	});
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGeneralCode () {
    		jq('#partyList').DataTable().search(
      		jq('#partyList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#partyList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
			  "searchHighlight": true,
			  "order": [[ 2, "asc" ]], //Name
    		  "lengthMenu": [ 75, 100, 200, 500],
			  "fnDrawCallback": function( oSettings ) {
    			jq('.dataTables_filter input').addClass("inputText12LightYellow");
    		  }
    	  });
      //event on input field for search
      jq('input.partyList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	