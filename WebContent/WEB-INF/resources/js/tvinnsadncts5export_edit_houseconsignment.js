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

		jq( "#submitNewLine" ).click(function( event ) {
  			setBlockUI();
		});
		
		jq( "#submit" ).click(function( event ) {
  			setBlockUI();
		});
		
		
  });	

	function deleteLine(event){
		setBlockUI();
	
	}
   
  jq(function() {
	  jq("#opd").focus();
  });
  
  jq(function() {
	  jq("#datum").datepicker({ 
		//dateFormat: 'yymmdd',
		  dateFormat: 'ddmmy' 	  
	  });
	  jq("#datumt").datepicker({ 
		  dateFormat: 'ddmmy' 
	  });
  });


function getItemData(record) {
  		
	  	var htmlValue = record.id;
	  	var applicationUserParam = jq('#applicationUser').val();
	  	var avdParam = jq('#avdItemList').val();
	  	var opdParam = jq('#opdItemList').val();
	  	//alert(htmlValue);
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getSpecificHouseConsignmentChosenFromGuiElement_TvinnSadNcts5Export.do',
	  	  data: { applicationUser : applicationUserParam, 
	  		  	  elementValue : htmlValue, 
	  		  	  avd : avdParam, 
	  		  	  opd : opdParam },
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				jq('#lineNr').val(data[i].tcli)
				
				jq('#tcln').val(data[i].tcln)
				jq('#tcdk').val(data[i].tcdk)
				jq('#tcalk').val(data[i].tcalk)
				jq('#tcblk').val(data[i].tcblk)
				jq('#tcvktb').val(data[i].tcvktb)
				jq('#tcucr').val(data[i].tcucr)
				jq('#tcavd2').val(data[i].tcavd2)
				jq('#tctdn2').val(data[i].tctdn2)
				jq('#tcxext').val(data[i].tcxext)
				jq('#tctaty').val(data[i].tctaty)
				jq('#tctaid').val(data[i].tctaid)
				jq('#tctalk').val(data[i].tctalk)
				jq('#tcpdty').val(data[i].tcpdty)
				
				jq('#tcpdrf').val(data[i].tcpdrf)
				jq('#tcpdin').val(data[i].tcpdin)
				jq('#tcsdty').val(data[i].tcsdty)
				jq('#tcsdrf').val(data[i].tcsdrf)
				jq('#tcsdln').val(data[i].tcsdln)
				jq('#tcsdin').val(data[i].tcsdin)
				jq('#tctdty').val(data[i].tctdty)
				jq('#tctdrf').val(data[i].tctdrf)
				jq('#tcadty').val(data[i].tcadty)
				jq('#tcadrf').val(data[i].tcadrf)
				jq('#tcaicd').val(data[i].tcaicd)
				jq('#tcaitx').val(data[i].tcaitx)
				jq('#tctrch').val(data[i].tctrch)
				
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
	  	
  	}
  		





  
  /** TODO
  //-----------------------------------
  //START Model dialog "Kopiera Ärende
  //-----------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			modal: true
		});
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".copyLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("copyLink","");
		  //setters (add more if needed)
		  jq('#dialog'+counterIndex).dialog( "option", "title", "Kopi Angivelse " + jq('#originalOpd'+counterIndex).val() );
		  
		  //deal with buttons for this modal window
		  jq('#dialog'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSave"+counterIndex,	
				 text: "Fortsæt",
				 click: function(){
					 		jq('#copyForm'+counterIndex).submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancel"+counterIndex,
			 	 text: "Annullér", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSave"+counterIndex).button("option", "disabled", true);
					 		jq("#newAvd"+counterIndex).val("");
					 		jq("#newSign"+counterIndex).val("");
							jq( this ).dialog( "close" ); 
					 		  
				 		} 
	 	 		 } ] 
			  
		  });
		  //init values
		  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  
		  //open now
		  jq('#dialog'+counterIndex).dialog('open');
		 
	  });
  });
  //Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
  jq(function() {
	  jq(".newAvd").change(function() {
		  if(jq("#dialog"+counterIndex).find('.newAvd').val()!='' && jq("#dialog"+counterIndex).find('.newSign').val()!=''){
			  jq("#dialogSave"+counterIndex).button("option", "disabled", false);
			  
		  }else{
			  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  }
	  });
	  jq(".newSign").change(function() {
		  if(jq("#dialog"+counterIndex).find('.newAvd').val()!='' && jq("#dialog"+counterIndex).find('.newSign').val()!=''){
			  jq("#dialogSave"+counterIndex).button("option", "disabled", false);
			  
		  }else{
			  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  }
	  });
	  
	  
  });
  //---------------------------------
  //END Model dialog "Kopiera Ärende
  //---------------------------------
	  
  */
  
//-------------------
  //Datatables jquery
  //-------------------
  //private function
  function filterGlobal () {
    jq('#tblHcLines').dataTable().search(
    	jq('#tblHcLines_filter').val()
    ).draw();
  }

  jq(document).ready(function() {
	  jq.fn.dataTable.moment( 'DDMMYY' );  
    //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
    jq('#tblHcLines').dataTable( {
	    	  "dom": '<"top">t<"bottom"flip><"clear">',
	    	  "scrollY":    "180px",
	    	  "deferRender": true, //to speed the table load
	    	  "scrollCollapse":  true,
	  		  "columnDefs": [{ "type": "num", "targets": 0 }],
	  		  "lengthMenu": [ 75, 100, 300, 400, 900]
  	});
    //event on input field for search
    jq('input.tblHcLines_filter').on( 'keyup click', function () {
    		filterGlobal();
    });
   
	
  });
  
  
  
  
  