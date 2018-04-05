	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq('#kundensVareRegList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			  var varenr = record[0].replace("varenr", "");
			  var varebe = record[1].replace("varebe", "");
			  var tariffnr = record[2].replace("tariffnr", "");
			  var vekt = record[3].replace("vekt", "");
			  var fylk = record[4].replace("fylk", "");
			 
			  opener.jq('#svvnt').val(tariffnr);
			  opener.jq('#wd1').val(varebe);
			  opener.jq('#svvktb').val(vekt);
			  opener.jq('#svfyl').val(fylk);
			  //focus on	
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
    		jq('#kundensVareRegList').DataTable().search(
      		jq('#kundensVareRegList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#kundensVareRegList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.kundensVareRegList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	