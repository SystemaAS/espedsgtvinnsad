	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
	
	//-------------------
  //Datatables jquery
  //-------------------
  //private function
  function filterGlobal () {
    jq('#mainList').dataTable().search(
    	jq('#mainList_filter').val()
    ).draw();
  }
//private function
  function filterGlobalWithDescendants () {
    jq('#mainListWithDescendants').dataTable().search(
    	jq('#mainListWithDescendants_filter').val()
    ).draw();
  }

  jq(document).ready(function() {
	//jq.fn.dataTable.moment( 'DDMMYY' );    
    //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
    jq('#mainList').dataTable( {
  	  "dom": 'f<"toolbar">rt<"bottom"ip><"clear">',
      "searchHighlight": true,
  	  "scrollCollapse":  true,
	  "tabIndex": -1,
	  "order": [[ 0, "asc" ]], //Linenr
	  "lengthMenu": [ 25, 50, 100],
	  "fnDrawCallback": function( oSettings ) {
    	jq('.dataTables_filter input').addClass("inputText12LightYellow");
    	}
    });

	//jq("div.toolbar").html('<span class="text16">Endre Transport</span>');
    
	//event on input field for search
    jq('input.mainList_filter').on( 'keyup click', function () {
    		filterGlobal();
    });
   

	jq('#mainListWithDescendants').dataTable( {
  	  "dom": 'f<"toolbar">rt<"bottom"ip><"clear">',
      "searchHighlight": true,
  	  "scrollCollapse":  true,
	  "tabIndex": -1,
	  "order": [[ 0, "asc" ]], 
	  "lengthMenu": [ 25, 50, 100],
	  "fnDrawCallback": function( oSettings ) {
    	jq('.dataTables_filter input').addClass("inputText12LightYellow");
    	}
    });

	//jq("div.toolbar").html('<span class="text16">Endre Transport</span>');
    
	//event on input field for search
    jq('input.mainListWithDescendants_filter').on( 'keyup click', function () {
    		filterGlobalWithDescendants();
    });


	
  });
  	
  	
	