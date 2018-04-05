	//===========================================
	//General functions for this JSP side - AJAX
	//===========================================
	
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
 	
  //date fields
    jq(function() {
  	  jq("#nidtl").datepicker({ 
  		  dateFormat: 'ddmmy' 
  	  });
  	  
  	  //=====================================
  	  //START Child window for general codes
  	  //=====================================
  	  //språk 
		jq('#nictskIdLink').click(function() {
	    	jq('#nictskIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_unloading_edit_childwindow_generalcodes.do?action=doInit&type=012&ctype=nictsk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#nictskIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#nictskIdLink').click();
			}
	    });
	  //språk 
		jq('#nimnskIdLink').click(function() {
	    	jq('#nimnskIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_unloading_edit_childwindow_generalcodes.do?action=doInit&type=012&ctype=nimnsk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#nimnskIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#nimnskIdLink').click();
			}
	    });
	  //språk 
		jq('#nidfskIdLink').click(function() {
	    	jq('#nidfskIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_unloading_edit_childwindow_generalcodes.do?action=doInit&type=012&ctype=nidfsk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#nidfskIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#nidfskIdLink').click();
			}
	    });
  	  
    });
  	
	

	
	
	
	

