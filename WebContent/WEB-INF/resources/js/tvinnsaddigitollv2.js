  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  //Overlay on tab (to mark visually a delay...)
  jq(function() {
    jq('#alinkManifestList').click(function() { 
    	setBlockUI();
    });
    jq('#alinkHeader').click(function() { 
    	setBlockUI();
    });
    jq('#alinkItems').click(function() { 
    	setBlockUI();
    });
    jq("#searchForm").submit(function() {
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
  
  jq(function() {
	  jq("#etaDatum").datepicker({ 
		  dateFormat: 'ddmmy' 	  
	  });
	  
	  jq("#etaDatumt").datepicker({ 
		  dateFormat: 'ddmmy' 
	  });
	  
	  jq("#datum").datepicker({ 
		  dateFormat: 'ddmmy' 	  
	  });
	  
	  jq("#datumt").datepicker({ 
		  dateFormat: 'ddmmy' 
	  });
	  
	  
  });
  
  
//Initialize <div> here for all clazz_dialog
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			maxWidth:500,
			maxHeight: 400,
			width: 500,
			height: 280,
			modal: true
		});
	  });
  });
  //----------------------------------------------------------------
  //START Model dialog: "Delete manifest" (implicit "Update status")
  //----------------------------------------------------------------
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".removeLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("removeLink","");
		  
		  jq('#dialogUpdateStatus'+counterIndex).dialog( "option", "title", "Slette Manifest " + jq('#currentUuid'+counterIndex).val() );
		  //deal with buttons for this modal window
		  jq('#dialogUpdateStatus'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
					 		jq('#updateStatusForm'+counterIndex).submit();
					 		jq( this ).dialog( "close" );
					 		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU"+counterIndex,
			 	 text: "Cancel", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		//jq("#dialogSaveSU"+counterIndex).button("option", "disabled", true);
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  //jq("#dialogSaveSU"+counterIndex).button("option", "disabled", true);
		  //open now
		  jq('#dialogUpdateStatus'+counterIndex).dialog('open');
		 
	  });
  });
  
  
  
//Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".cancelLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("cancelLink","");
		  
		  jq('#dialogUpdateInternalStatus'+counterIndex).dialog( "option", "title", "Kanseller Manifest " + jq('#currentUuid'+counterIndex).val() );
		  //deal with buttons for this modal window
		  jq('#dialogUpdateInternalStatus'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
					 		jq('#updateInternalStatusForm'+counterIndex).submit();
					 		jq( this ).dialog( "close" );
					 		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU"+counterIndex,
			 	 text: "Cancel", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		//jq("#dialogSaveSU"+counterIndex).button("option", "disabled", true);
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  //jq("#dialogSaveSU"+counterIndex).button("option", "disabled", true);
		  //open now
		  jq('#dialogUpdateInternalStatus'+counterIndex).dialog('open');
		 
	  });
  });
  
  	//Present dialog box onClick (href in parent JSP)
  	jq(function() {
	  jq(".grantLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("grantLink","");
		  
		  jq('#dialogUpdateInternalStatusGrant'+counterIndex).dialog( "option", "title", "Gjøre tilgjengelig igjen " );
		  //deal with buttons for this modal window
		  jq('#dialogUpdateInternalStatusGrant'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
							setBlockUI();
					 		jq('#updateInternalStatusGrantForm'+counterIndex).submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU"+counterIndex,
			 	 text: "Cancel", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSaveSU"+counterIndex).button("option", "disabled", true);
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveSU"+counterIndex).button("option", "disabled", true);
		  //open now
		  jq('#dialogUpdateInternalStatusGrant'+counterIndex).dialog('open');
		 
	  });
  	});
  
  
  jq(function() {
	  jq(".uuidLink").click(function() {
		  var id = this.id;
		  jq("#"+id).attr(('target','_blank'));
		  window.open('tvinnsaddigitollv2_childwindow_manifestinfo.do?id=' + id +'&level=t', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=yes,status=no,location=no");
	  });
  });
  
  
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
  	  "dom": 'f<"toolbar">rt<"bottom"ip><"clear">',
      "searchHighlight": true,
  	  //"dom": '<"top"f>rt<"bottom"lip><"clear">',
  	  //"scrollY": "700px",
  	  "scrollCollapse":  true,
	  "tabIndex": -1,
	  "order": [[ 1, "asc" ]], //etlnrt
	  "lengthMenu": [ 25, 50, 100],
	  "fnDrawCallback": function( oSettings ) {
    	jq('.dataTables_filter input').addClass("inputText12LightYellow");
    	}
    });
	jq("div.toolbar").html('<span class="text16">Transportliste</span>');
    
//event on input field for search
    jq('input.mainList_filter').on( 'keyup click', function () {
    		filterGlobal();
    });
   
	
  });
  
  
  
  
  
 
