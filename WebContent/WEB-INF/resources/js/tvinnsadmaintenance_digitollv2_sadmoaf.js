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
    jq('#manifestForm').submit(function() { 
    	setBlockUI();
    });
    
  });
  jq(function() {
	  	jq("#etetad").datepicker({ 
		  dateFormat: 'ddmmy' 	  
	  	});
	 	jq("#etshed").datepicker({ 
		  dateFormat: 'ddmmy' 	  
	  	});
    	jq('#etavd').focus(function() {
			if(jq('#etavd').val()!=''){
	    		refreshCustomValidity(jq('#etavd')[0]);
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
			width: 280,
			height: 280,
			modal: true
		});
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
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
      jq('#mainList').dataTable( {
    	  "dom": '<"top">t<"bottom"flip><"clear">',
          "paging":   false,
          "ordering": false,
          "info":     false,
          "searching":     false
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
	  	  url: 'getSpecificDefaultValue_TvinnSadManifest.do',
	  	  data: { applicationUser : applicationUserParam, 
	  		  	  htmlParams : htmlValue },
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				//update
				jq('#updateId').val(""); jq('#updateId').val("U");
				
				jq('#efpro').val(""); jq('#efpro').val(data[i].efpro);
				jq('#efavd').val(""); jq('#efavd').val(data[i].efavd);
				jq('#efsg').val(""); jq('#efsg').val(data[i].efsg);
				
				jq('#efrgd').val(""); jq('#efrgd').val(data[i].efrgd);
				jq('#eftm').val(""); jq('#eftm').val(data[i].eftm);
				jq('#efeta').val(""); jq('#efeta').val(data[i].efeta);
				jq('#efetm').val(""); jq('#efetm').val(data[i].efetm);
				jq('#eftsd').val(""); jq('#eftsd').val(data[i].eftsd);
				jq('#ef3039e').val(""); jq('#ef3039e').val(data[i].ef3039e);
				
				jq('#efktyp').val(""); jq('#efktyp').val(data[i].efktyp);
				jq('#efkmrk').val(""); jq('#efkmrk').val(data[i].efkmrk);
				jq('#efklk').val(""); jq('#efklk').val(data[i].efklk);
				jq('#efpmrk').val(""); jq('#efpmrk').val(data[i].efpmrk);
				jq('#efplk').val(""); jq('#efplk').val(data[i].efplk);
				
				jq('#efsjaf').val(""); jq('#efsjaf').val(data[i].efsjaf);
				jq('#efsjae').val(""); jq('#efsjae').val(data[i].efsjae);
				jq('#efsjalk').val(""); jq('#efsjalk').val(data[i].efsjalk);
				
				jq('#efsjadt').val(""); jq('#efsjadt').val(data[i].efsjadt);
				jq('#efbekr').val(""); jq('#efbekr').val(data[i].efbekr);
				
				
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
	  	
	}

  jq(function() {
	  jq('#newButton').click(function() {
		  setBlockUI();
		  window.location = "tvinnsadmaintenance_manifest_sadefdef.do?id=SADEFDEF";
	  }); 
  });



  

  
 
