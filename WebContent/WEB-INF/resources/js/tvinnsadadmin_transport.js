  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  
  jq(function() {
	  jq("#todo").focus();
  });
  
  jq(function() {
	  jq("#datum").datepicker({ 
		  dateFormat: 'yymmdd', 
		  defaultDate: "-6m"	  
	  });
	  
  });
  
 

  