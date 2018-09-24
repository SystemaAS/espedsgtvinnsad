  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  
  //Overlay on tab (to mark visually a delay...)
  jq(function() {
    jq('#alinkHeaderNew').click(function() { 
    	setBlockUI();
    });
    jq('#alinkHeader').click(function() { 
    	setBlockUI();
    });
    jq( "#submit" ).click(function( event ) {
    	setBlockUI();
	});
    //General Header Menus
    jq('#alinkTopicListMenuImp').click(function() { 
    	setBlockUI();
    });
    jq('#alinkTopicListMenuExp').click(function() { 
    	setBlockUI();
    });
    jq('#alinkTopicListMenuNctsExp').click(function() { 
    	setBlockUI();
    });
    jq('#alinkTopicListMenuNctsImp').click(function() { 
    	setBlockUI();
    });
    jq('#alinkTopicListMenuManifest').click(function() { 
    	setBlockUI();
    });
  });
 
  
 
