	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  	jq(document).ready(function() {
  		//TODO
  	});
  	
  	function setBlockUI(element){
  	  jq.blockUI({ css: { fontSize: '22px' }, message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    }
    
  	
  	//Overlay on tab (to mark visually a delay...)
  	/*
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
  	  jq('#alinkInvoices').click(function() { 
  		setBlockUI();
	  });
  	  jq('#alinkItemLines').click(function() { 
  		setBlockUI();
  	  });
  	  jq('#alinkArchive').click(function() { 
  		setBlockUI();
	  });
  	  
  	  
    });
  	*/
  	
  	
  //-------------------
    //Datatables jquery
    //-------------------
    //private function
    function filterGlobal () {
      jq('#mainList').dataTable().search(
      	jq('#mainList_filter').val()
      ).draw();
    }

    jq(document).ready(function() {
  	//jq.fn.dataTable.moment( 'DDMMYY' );    
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
      jq('#mainList').dataTable( {
    	  //"dom": '<"top"f>t<"bottom"><"clear">',
        "searchHighlight": true,
    	  "dom": '<"top"f>rt<"bottom"lip><"clear">',
    	  "scrollY": "700px",
    	  "scrollCollapse":  true,
  	  "tabIndex": -1,
  	  "order": [[ 6, "asc" ]],
  	  "lengthMenu": [ 25, 50],
  	  "fnDrawCallback": function( oSettings ) {
      	jq('.dataTables_filter input').addClass("inputText12LightYellow");
      	}
      });
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
     
  	
    });
    
  	