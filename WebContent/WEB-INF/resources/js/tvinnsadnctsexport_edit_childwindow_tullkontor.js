	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//-----------
  	// Tullkontor
  	//-----------
	jq(function() {
		jq('#tullkontorList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			  var tkkode = record[0].replace("tkkode", "");
			  var tktxtn = record[1].replace("tktxtn", "");
			  var callerType = record[2].replace("ctype", "");
			  
			  //Tullkontor - thcats
			  if(callerType == 'thcats'){  
				  opener.jq('#thcats').val(tkkode);
				  opener.jq('#thcats').focus();
				  
			  }else if(callerType == 'thtsb'){ //Tullkontor - thtsb 
				  opener.jq('#thtsb').val(tkkode);
				  opener.jq('#thtsb').focus();
				  
			  }else if(callerType == 'thtsd1'){ //Tullkontor - thtsd1...8 
				  opener.jq('#thtsd1').val(tkkode);
				  opener.jq('#thtsd1').focus();
				  
			  }else if(callerType == 'thtsd2'){ //Tullkontor - thtsd1...8 
				  opener.jq('#thtsd2').val(tkkode);
				  opener.jq('#thtsd2').focus();
				  
			  }else if(callerType == 'thtsd3'){ //Tullkontor - thtsd1...8 
				  opener.jq('#thtsd3').val(tkkode);
				  opener.jq('#thtsd3').focus();
				  
			  }else if(callerType == 'thtsd4'){ //Tullkontor - thtsd1...8 
				  opener.jq('#thtsd4').val(tkkode);
				  opener.jq('#thtsd4').focus();
				  
			  }else if(callerType == 'thtsd5'){ //Tullkontor - thtsd1...8 
				  opener.jq('#thtsd5').val(tkkode);
				  opener.jq('#thtsd5').focus();
				  
			  }else if(callerType == 'thtsd6'){ //Tullkontor - thtsd1...8 
				  opener.jq('#thtsd6').val(tkkode);
				  opener.jq('#thtsd6').focus();
				  
			  }else if(callerType == 'thtsd7'){ //Tullkontor - thtsd1...8 
				  opener.jq('#thtsd7').val(tkkode);
				  opener.jq('#thtsd7').focus();
				  
			  }else if(callerType == 'thtsd8'){ //Tullkontor - thtsd1...8 
				  opener.jq('#thtsd8').val(tkkode);
				  opener.jq('#thtsd8').focus();
				  
			  }
			  //close child window
			  window.close();
			  
	    });
	});
	
	
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterTullkontor () {
    		jq('#tullkontorList').DataTable().search(
      		jq('#tullkontorList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#tullkontorList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.tullkontorList_filter').on( 'keyup click', function () {
      		filterTullkontor();
      });
      
    });   
  	
  	
	