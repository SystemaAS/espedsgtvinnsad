	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  	 
  	jq(function() {
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
		
	});
  	
    //======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filter() {
    		jq('#releasedCargoLinesList').DataTable().search(
      		jq('#releasedCargoLinesList_filter').val()
    		).draw();
    } 
  	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#releasedCargoLinesList').dataTable( {
    		  "searchHighlight": true,
    		  //"order": [[ 0, "desc" ]],
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500],
    		  "fnDrawCallback": function( oSettings ) {
    			  jq('.dataTables_filter input').addClass("inputText12LightYellow");
    		  }
    	  });
      //event on input field for search
      jq('input.releasedCargoLinesList_filter').on( 'keyup click', function () {
      	filter();
      });
      
      
    });  
    
    
    
  	
    
   
  	
	