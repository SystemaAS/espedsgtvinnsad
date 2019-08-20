	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  	jq(function() {
  		jq('#svcnr').focus(function() {
  			if(jq('#svcnr').val()!=''){
  				refreshCustomValidity(jq('#svcnr')[0]);
  			}
  		});
  		
  		
  		
  	});
  	
  	jq(document).ready(function() {
		jq('#svcnr').focus();

  	});
  	
  	
  
  	
  	
	