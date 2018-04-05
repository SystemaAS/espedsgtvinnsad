  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  
  jq(function() {
	  jq("#todo").focus();
  });
  
  jq(function() {
	  jq("#fromDate").datepicker({ 
		  //dateFormat: 'yymmdd'
		  dateFormat: 'ddmmy'
	  });
	  jq("#toDate").datepicker({ 
		  //dateFormat: 'yymmdd' 
		  dateFormat: 'ddmmy'
	  });
	  
	  
	//-----------------
    //CUSTOMER search
  	//-----------------
    //SENDER
    jq('#avggCustomerIdLink').click(function() {
    	jq('#avggCustomerIdLink').attr('target','_blank');
    	window.open('tvinnsad_childwindow_customer.do?action=doFind&knr=' + jq('#avggCustomerId').val() + '&ctype=avggcustid', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#avggCustomerIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#avggCustomerIdLink').click();
		}
    });
	  
  });
  
 

  