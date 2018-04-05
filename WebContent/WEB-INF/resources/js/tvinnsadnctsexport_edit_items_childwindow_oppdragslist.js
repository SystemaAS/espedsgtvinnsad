	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq("#datum").datepicker({ 
			  dateFormat: 'ddmmy' 
			  //defaultDate: "-6m"	  
		});
		
		//put values from childwindow into opener fields
		jq('#oppdragsList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			  var avd = record[0].replace("avd", "");
			  var opd = record[1].replace("opd", "");
			  //alert(avd + " " + opd);
			  opener.jq('#tvavd2').val(avd);
			  opener.jq('#tvtdn2').val(opd);
			  opener.jq('#tvtdn2').focus();
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
    function filterOppdragsList () {
    		jq('#oppdragsList').DataTable().search(
      		jq('#oppdragsList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#oppdragsList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.oppdragsList_filter').on( 'keyup click', function () {
    	  filterOppdragsList();
      });
      
    });   
  	
  	
	