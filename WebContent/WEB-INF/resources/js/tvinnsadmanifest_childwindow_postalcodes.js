	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  	//--------
    //Koder
    //--------
    jq(function() {
    	jq('#postalCodeList').on('click', 'td', function(){
			var id = this.id;
			  var record = id.split('@');
			  var postalCode = record[0].replace("postalcode_","");
			  var countryCode = record[1].replace("country_","");
			  var city = record[2].replace("city_","");
			  var caller= jq("#ctype").val();
			  
			  if(opener.jq('#cllkf').length && caller =='cllkf'){ 
				  opener.jq('#cllkf').val(countryCode);
				  //opener.jq('#clsdf').val(postalCode);
				  opener.jq('#clsdft').val(city);
				  
			  }else if(opener.jq('#cllkt').length && caller =='cllkt'){
				  opener.jq('#cllkt').val(countryCode);
				  //opener.jq('#clsdt').val(postalCode);
				  opener.jq('#clsdtt').val(city);
			  }
			  
			  //close child window
			  window.close();
		});
  	});
  	
  	
    //======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filter() {
    		jq('#postalCodeList').DataTable().search(
      		jq('#postalCodeList_filter').val()
    		).draw();
    } 
  	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#postalCodeList').dataTable( {
    		  "searchHighlight": true,
    		  //"order": [[ 0, "desc" ]],
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500],
    		  "fnDrawCallback": function( oSettings ) {
    			  jq('.dataTables_filter input').addClass("inputText12LightYellow");
    		  }
    	  });
      //event on input field for search
      jq('input.postalCodeList_filter').on( 'keyup click', function () {
      	filter();
      });
      
    });   
  	
    
   
  	
	