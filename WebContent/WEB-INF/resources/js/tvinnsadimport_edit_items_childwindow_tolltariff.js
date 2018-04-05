	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq('#tolltariffList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			  var vkod = record[0].replace("vkod", "");
			  var text = record[1].replace("text", "");
			  //alert(kod + " " + text);
			  opener.jq('#svvnt').val(vkod);
			  opener.jq('#svvnt').focus();
			  //close child window
			  window.close();
		  });
	});
	
	jq(function() {
		jq('#vkod').blur(function(){
			if(jq('#vkod').val() != ""){
				jq('#tekst').val("");
			}
		});
		jq('#tekst').blur(function(){
			if(jq('#tekst').val() != ""){
				jq('#vkod').val("");
			}
		});
	});
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGeneralCode () {
    		jq('#tolltariffList').DataTable().search(
      		jq('#tolltariffList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#tolltariffList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.tolltariffList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	