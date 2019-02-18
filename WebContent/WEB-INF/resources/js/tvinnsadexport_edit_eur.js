	//this variable is a global jQuery var instead of using "$" all the time. Very handy
	var jq = jQuery.noConflict();
	
  	//Overlay on tab (to mark visually a delay...)
    jq(function() {
	  jq('#alinkTopicList').click(function() { 
		  setBlockUI();
  	  });	
  	  jq('#alinkHeader').click(function() { 
  		setBlockUI();
	  });
      jq('#alinkOmberegning').click(function() { 
    	  setBlockUI();
  	  });  	  
  	  jq('#alinkItemLines').click(function() { 
  		setBlockUI();
  	  });
  	  jq('#alinkLogging').click(function() { 
  		setBlockUI();
	  });
  	  jq('#alinkArchive').click(function() { 
  		setBlockUI();
	  });
    });
  	
	jq(function() {
		/*jq("#sfdt").datepicker({ 
			dateFormat: 'ddmmy'  
        });
        */
    });
	
	