  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  
  jq(function() {
	  //General Header Menus
	  jq('#alinkTopicListMenuExp').click(function() { 
		  setBlockUI();
	  });
	  jq('#alinkTopicListMenuImp').click(function() { 
		  setBlockUI();
	  });
	  jq('#alinkTopicListMenuNctsExp').click(function() { 
		  setBlockUI();
	  });
	  jq('#alinkTopicListMenuNctsImp').click(function() { 
		  setBlockUI();
	  });
  });
  