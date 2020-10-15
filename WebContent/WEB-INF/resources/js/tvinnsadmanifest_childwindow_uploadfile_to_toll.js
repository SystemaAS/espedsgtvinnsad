	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  	 
  	jq(function() {
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
  	
  	
  	function sendFile(element) {
	  	var applicationUserParam = jq('#applicationUser').val();
	  	var rowCounter = element.id;
	  	var title = jq("#"+element.id).attr("title");
	  	
	  	var record = title.split('_');
	  	var docType = record[0].replace("doctyp", "");
	  	var docPath = record[1].replace("doclnk", "");
		
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'sendFileToToll_TvinnSadManifest.do',
	  	  data: { applicationUser : applicationUserParam, 
	  		  	  docType : docType,
	  		  	  docPath : docPath,
	  		  	  declNr :  jq('#clrg').val(),
	  		  	  declDate : jq('#cl0068a').val(),
	  		  	  declSekvens : jq('#cl0068b').val(),
		  	  },
	  	    	  
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
	  		if(len>0){
	  			if(data[0]!=null && data[0].toUpperCase().indexOf('ERROR') == -1){
	  				console.log(data[0]);
	  				jq("#"+element.id).removeClass("isa_error");
	  				jq("#"+element.id).removeClass("isa_warning");
	  				jq("#"+element.id).addClass("isa_success");
	  				//error message
	  				jq("#tollErrorMessage").text("");
	  				jq("#tollErrorMessage").removeClass("inputFormSubmitStd");
	  				jq("#tollErrorMessage").removeClass("isa_error");
	  			}else{
	  				console.log("ERROR:" + data[0]);
	  				jq("#"+element.id).removeClass("isa_warning");
	  				jq("#"+element.id).removeClass("isa_success");
	  				jq("#"+element.id).addClass("isa_error");
	  				//error message
	  				jq("#tollErrorMessage").text("");
	  				jq("#tollErrorMessage").addClass("inputFormSubmitStd");
	  				jq("#tollErrorMessage").addClass("isa_error");
	  				jq("#tollErrorMessage").text(data[0]);
	  			}
	  		}else{
	  			var errMsg = "ERROR ... no data ?";
	  			console.log(errMsg);
  				jq("#"+element.id).removeClass("isa_warning");
  				jq("#"+element.id).removeClass("isa_success");
  				jq("#"+element.id).addClass("isa_error");
  				//error message
  				jq("#tollErrorMessage").text("");
  				jq("#tollErrorMessage").addClass("inputFormSubmitStd");
  				jq("#tollErrorMessage").addClass("isa_error");
  				jq("#tollErrorMessage").text(errMsg);
	  		}
			
	  	  },
	  	  error: function() {
	  	    alert('Error on send ...');
	  	  }
	  	});
	  	
  	}
  	
   
    
    
  	
    
   
  	
	