	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq('#customerList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('_');
			 
			  var orgnr = record[0].replace("orgnr", "");
			  var name = record[1].replace("name", "");
			  //var commtype = record[2].replace("commtype", "");
			  //var format = record[3].replace("format", "");
			   			  
		  	  opener.jq('#ownReceiverOrgNr').val(orgnr);
			  opener.jq('#ownReceiverName').val(name);
			  
 			  /*
			  opener.jq('#ehrgs').val(syrg);refreshCustomValidity(opener.jq('#ehrgs')[0]);
			  opener.jq('#ehad1s').val(adr1);
			  opener.jq('#ehpss').val(adr3);
			  opener.jq('#ehpns').val(postnr);
			  opener.jq('#ehlks').val(land);
			  opener.jq('#ehnas').focus();	
		  	  */
	
	
			  //close child window
			  window.close();
		  });
	});
	
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGeneralCode () {
    		jq('#customerList').DataTable().search(
      		jq('#customerList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#customerList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
			  "searchHighlight": true,
    		  "lengthMenu": [ 75, 100, 200, 500],
			  "fnDrawCallback": function( oSettings ) {
    			jq('.dataTables_filter input').addClass("inputText12LightYellow");
    		  }
    	  });
      //event on input field for search
      jq('input.customerList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	