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
		  	  var tutvkt = record[2].replace("tutvkt", "");	
			  var callerType = record[3].replace("ctype", "");
			  
			  	//tur - etpro (transport)
			  	if(callerType == 'etpro'){ 
				 //console.log("Bingo!!") 
				  opener.jq('#etpro').val(tupro);
				  opener.jq('#etpro').focus();
				
			  	//tur - empro (master)	  
			  	}else if (callerType == 'empro'){  
				  opener.jq('#empro').val(tupro);
				  if(tutvkt != ''){
				  	opener.jq('#emvkb').val(tutvkt);
				  }	
				  opener.jq('#emvkb').focus();
				  
			  	//tur - ehpro (house)	
			  	}else if (callerType == 'ehpro'){
		  		  opener.jq('#ehpro').val(tupro);
				  opener.jq('#ehpro').focus();
				  
			  	}
			  //close child window
			  window.close();
			  
	    });
	});
	
	
	jq(function() {
	  jq("#tudt").datepicker({ 
		  dateFormat: 'yymmdd' 	  
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
		  "searchHighlight": true,
		  "tabIndex": -1,
	  	  "order": [[ 0, "asc" ]], //Tur
		  "lengthMenu": [ 50, 100, 200, 500],
		  "fnDrawCallback": function( oSettings ) {
	    	jq('.dataTables_filter input').addClass("inputText12LightYellow");
			
	    	}
	  });
      //event on input field for search
      jq('input.turList_filter').on( 'keyup click', function () {
      		filterTur();
      });
      
    });   
  	
  	
	