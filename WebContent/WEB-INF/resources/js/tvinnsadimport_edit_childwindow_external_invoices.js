	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
    
  	jq(function() {
		jq('#buttonCloseOk').click(function(){
			jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
			var ID_RECORD_SEPARATOR = "__";
			var checkedCounterTillaggs = 0; //since the opener ONLY can receive max: 2 alternatives. This is the ultimate counter for knowing which receiver (1,2 or 3)
			jq( ".clazzInvoiceAware" ).each(function( i ) {
				var id = this.id;
				var record = id.split(ID_RECORD_SEPARATOR);
				var id = record[0].replace("id", "");
				//escape space if applicable (otherwise jquery do not validate the space-character in id-fields (html)
				if(id.indexOf(" ")>-1){ id = id.replace(" ", "\\ ");  }
				
				var unik = record[1].replace("unik", "");
				var counter = i + 1;
				 
				if(jq('#id' + id + ID_RECORD_SEPARATOR + 'unik' + unik).prop('checked')){
					  //alert(id + '_' + unik + "-XX-" + jq('#id' + id + '_' + 'unik' + unik).prop('checked'));
					  id = id.replace("\\", "");
					  var requestParams = "&levnr=" + jq('#siknk').val() + "&avd=" + jq('#avd').val() + "&opd=" + jq('#opd').val() + "&mode=U" + "&reff=" + id + "&unik=" + unik;
					  //alert(requestParams);	
					  
					  jq.ajax({
				  	  	  type: 'GET',
				  	  	  url: 'updateExternalInvoiceLine_SadImport.do',
				  	  	  data: { applicationUser : jq('#applicationUser').val(),
						  			requestParams : requestParams },
				  	  	  dataType: 'json',
				  	  	  cache: false,
				  	  	  async: false,
				  	  	  contentType: 'application/json',
				  	  	  success: function(data) {
				  	  		var len = data.length;
				  	  		for ( var i = 0; i < len; i++) {
				  	  			//Nothing
				  	  		}
				  	  	  },
					  	  error: function() {
				  	  	    //alert('Error loading ...');
				  	  	  }
				  	  });
				  	  
				}
			});
			//we must reload the parent window since the use case updates the invoice list (if the end-user has selected some invoices to import)
			window.opener.location.reload();
			window.close();
		});
		//abort
		jq('#cancel').click(function(){
			window.close();
		});
		
	});
  	
  	//-------------------
    //Datatables jquery
    //-------------------
    //private function
    function filterGlobal () {
      jq('#tblInvoices').dataTable().search(
      	jq('#tblInvoices_filter').val()
      ).draw();
    }

    jq(document).ready(function() {
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
      jq('#tblInvoices').dataTable( {
    	  "dom": '<"top"fli>rt<"bottom"p><"clear">',
  		  "scrollY":    "380px",
  		  "scrollCollapse":  true,
  		  "lengthMenu": [ 75, 100]
  	  });
      //event on input field for search
      jq('input.tblInvoices_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
    });

  	
  	
	
  	
  	
	