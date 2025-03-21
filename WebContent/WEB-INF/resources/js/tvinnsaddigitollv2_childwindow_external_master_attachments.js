	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();

	
	jq(function() {
	  jq("#date").datepicker({ 
		  dateFormat: 'yymmdd' 	  
	  });
	});
	
	
	jq(function() {
		jq('#insertButton').click(function() { 
			//DEBUG -->alert(jq('#etlnrt').val() + " " + jq('#emdkm_insert').val() + jq('#emdkmt_insert').val());
			if(jq('#emdkm_insert').val() != '' && jq('#emdkmt_insert').val() != ''){
				setBlockUI();
				window.location = 'tvinnsaddigitollv2_edit_light_master_zadmomlf.do?etlnrt=' + jq('#etlnrt').val() + '&emdkm=' + jq('#emdkm_insert').val()+ '&emdkmt=' + jq('#emdkmt_insert').val();
				//jq.unblockUI();		 		
			}
	    });
	});
	
	
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".removeLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("removeLink","");
		  
		  jq('#dialogDeleteRecord'+counterIndex).dialog( "option", "title", "Slett record " + jq('#current_id1'+counterIndex).val());
		  //deal with buttons for this modal window
		  jq('#dialogDeleteRecord'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
							setBlockUI();
					 		jq('#deleteRecordForm'+counterIndex).submit();
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
		  jq('#dialogDeleteRecord'+counterIndex).dialog('open');
		 
	  });
  });

  jq(function() {
	  //because we don't want to send a path in href-GET. Must be POST and this is the only way...'
	  jq(".renderAttachmentLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("renderAttachmentLink","");
		  jq('#renderAttachmentForm'+counterIndex).submit();
	  });
  });




//Initialize <div> here for all clazz_dialog
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			maxWidth:500,
			maxHeight: 400,
			width: 300,
			height: 180,
			modal: true
		});
	  });
  });
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterTur () {
    		jq('#mainList').DataTable().search(
      		jq('#mainList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
	  jq('#mainList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',               
		  "searchHighlight": true,
		  "tabIndex": -1,
	  	  "order": [[ 1, "desc" ], [ 2, "desc" ]], //ETA
		  "lengthMenu": [ 50, 100, 200, 500],
		  "fnDrawCallback": function( oSettings ) {
	    	jq('.dataTables_filter input').addClass("inputText12LightYellow");
			
	    	}
	  });
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterTur();
      });
      
    });   
  	
  	
	