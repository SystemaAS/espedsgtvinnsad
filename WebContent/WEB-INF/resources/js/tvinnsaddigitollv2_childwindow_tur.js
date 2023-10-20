	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//-----------
  	// Tullkontor
  	//-----------
	jq(function() {
		jq('#turList').on('click', 'td', function(){
			  var id = this.id;
			  //id="tupro${record.tupro}@tuavd${record.tuavd}@ctype${model.callerType}
			  var record = id.split('@');
			  var tupro = record[0].replace("tupro", "");
			  var tuavd = record[1].replace("tuavd", "");
			  var callerType = record[2].replace("ctype", "");
			  
			  //tur - etpro (transport)
			  if(callerType == 'etpro'){ 
				 //console.log("Bingo!!") 
				  opener.jq('#etpro').val(tupro);
				  opener.jq('#etpro').focus();
				
			  //tur - empro (master)	  
			  }else if (callerType == 'empro'){  
				  opener.jq('#empro').val(tupro);
				  opener.jq('#empro').focus();
				  
			  }
			  //close child window
			  window.close();
			  
	    });
	});
	
	
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterTur () {
    		jq('#turList').DataTable().search(
      		jq('#turList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#turList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.turList_filter').on( 'keyup click', function () {
      		filterTur();
      });
      
    });   
  	
  	
	