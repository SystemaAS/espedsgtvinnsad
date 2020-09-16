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
    jq('#cargoLineForm').submit(function() { 
    	setBlockUI();
    });
    
  });
  
  //used and called only from a childwindow in order to reload this page with BlockUI() ...
  function callParent(){
	  setBlockUI();
	  window.location.reload(false); 
  }
  
  jq(function() {
	  jq("#cl0068a").datepicker({ 
		  dateFormat: 'ddmmy' 	  
	  });
	  
	//CHILD-WINDOWS
	//Postnr/sted FROM
	  jq('#clsdfIdLink').click(function() {
	  	jq('#clsdfIdLink').attr('target','_blank');
	  	window.open('tvinnsadmanifest_childwindow_postalcodes_sted2.do?action=doFind&ctype=clsdf&direction=fra&st2lk=' + jq('#cllkf').val() + '&st2kod=' + jq('#clsdf').val() + '&caller=clsdf', "postalcodeSted2Win", "top=300px,left=450px,height=600px,width=700px,scrollbars=no,status=no,location=no");
	  });
	  jq('#clsdfIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#clsdfIdLink').click();
			}
	  });
	//Postnr/sted TO
	  jq('#clsdtIdLink').click(function() {
	  	jq('#clsdtIdLink').attr('target','_blank');
	  	window.open('tvinnsadmanifest_childwindow_postalcodes_sted2.do?action=doFind&ctype=clsdt&direction=fra&st2lk=' + jq('#cllkt').val() + '&st2kod=' + jq('#clsdt').val() + '&caller=clsdt', "postalcodeSted2Win", "top=300px,left=450px,height=600px,width=700px,scrollbars=no,status=no,location=no");
	  });
	  jq('#clsdtIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#clsdtIdLink').click();
			}
	  });
	  
	//Plocka oppdrag
	  jq('#cnButton').click(function() {
	  	jq('#cnButton').attr('target','_blank');
	  	window.open('tvinnsadmanifest_childwindow_released_cargolines.do?action=doInit&ctype=cnButton&clpro=' + + jq('#efpro').val(), "releasedCargolinesWin", "top=300px,left=450px,height=600px,width=700px,scrollbars=no,status=no,location=no");
	  });
	  jq('#cnButton').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#cnButton').click();
			}
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
  //START Model dialog: "Delete cargo line" (implicit "Update status")
  //----------------------------------------------------------------
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".removeLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("removeLink","");
		  
		  jq('#dialogUpdateStatus'+counterIndex).dialog( "option", "title", "Slette oppdrag " + jq('#currentClpro'+counterIndex).val() );
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
  	  "dom": '<"top"f>t<"bottom"><"clear">',
  	  "scrollY": "300px",
  	  "scrollCollapse":  true,
	  "tabIndex": -1,
	  "order": [[ 2, "asc" ]], 
	  "lengthMenu": [ 25, 50, 100],
	  "fnDrawCallback": function( oSettings ) {
    	jq('.dataTables_filter input').addClass("inputText12LightYellow");
    	}
    });
    //event on input field for search
    jq('input.mainList_filter').on( 'keyup click', function () {
    		filterGlobal();
    });
    
  });
  
  
  function getItemData(record) {
	  	var applicationUserParam = jq('#applicationUser').val();
	  	var htmlValue = record.id;
	  	//alert(applicationUserParam + ' ' + htmlValue);
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getSpecificCargoLine_TvinnSadManifest.do',
	  	  data: { applicationUser : applicationUserParam, 
	  		  	  htmlParams : htmlValue },
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				jq('#clpro').val(""); jq('#clpro').val(data[i].clpro);
				jq('#clavd').val(""); jq('#clavd').val(data[i].clavd);
				jq('#cltdn').val(""); jq('#cltdn').val(data[i].cltdn);
				//The status is always in the html = O --->jq('#clst').val(""); jq('#clst').val(data[i].clst);
				
				jq('#clvt').val(""); jq('#clvt').val(data[i].clvt);
				jq('#cl0068a').val(""); jq('#cl0068a').val(data[i].cl0068a);
				jq('#cl0068b').val(""); jq('#cl0068b').val(data[i].cl0068b);
				jq('#clntk').val(""); jq('#clntk').val(data[i].clntk);
				jq('#clvkb').val(""); jq('#clvkb').val(data[i].clvkb);
				jq('#clrg').val(""); jq('#clrg').val(data[i].clrg);
				//Loading
				jq('#cllkf').val(""); jq('#cllkf').val(data[i].cllkf);
				jq('#clsdf').val(""); jq('#clsdf').val(data[i].clsdf);
				jq('#clsdft').val(""); jq('#clsdft').val(data[i].clsdft);
				//Unloading
				jq('#cllkt').val(""); jq('#cllkt').val(data[i].cllkt);
				jq('#clsdt').val(""); jq('#clsdt').val(data[i].clsdt);
				jq('#clsdtt').val(""); jq('#clsdtt').val(data[i].clsdtt);
				jq('#clpr').val(""); jq('#clpr').val(data[i].clpr);
				//Export 
				jq('#cletyp').val(""); jq('#cletyp').val(data[i].cletyp);
				jq('#cleid').val(""); jq('#cleid').val(data[i].cleid);
				jq('#cleser').val(""); jq('#cleser').val(data[i].cleser);
				
				//debug information on Fetch item
				//jq('#debugPrintlnAjaxItemFetchInfo').text(data[i].debugPrintlnAjax);
				
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
	  	
	}
	
 
  
 
