	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//-----------
  	// Tullkontor
  	//-----------
	jq(function() {
		jq('#tullkontorList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			  var tkkode = record[0].replace("tkkode", "");
			  var tktxtn = record[1].replace("tktxtn", "");
			  var callerType = record[2].replace("ctype", "");
			  
			  //Tullkontor - ettsd
			  if(callerType == 'ettsd'){ 
				console.log("Bingo!!") 
				  opener.jq('#ettsd').val(tkkode);
				  opener.jq('#ettsd').focus();
				  
			  }else if (callerType == 'xxxx'){  
				  opener.jq('#xxxx').val(tkkode);
				  opener.jq('#xxxx').focus();
				  
			  }
			  //close child window
			  window.close();
			  
	    });
	});
	
	
	jq(function() {
	    jq('#eibl').focus(function() {
	    	if(jq('#eibl').val()!=''){
	    		refreshCustomValidity(jq('#eibl')[0]);
	  		}
	  	});
	    jq('#eistk').focus(function() {
	    	if(jq('#eistk').val()!=''){
	    		refreshCustomValidity(jq('#eistk')[0]);
	  		}
	  	});
		jq('#eivnt').focus(function() {
	    	if(jq('#eivnt').val()!=''){
	    		refreshCustomValidity(jq('#eivnt')[0]);
	  		}
	  	});
	    jq('#eirge').focus(function() {
	    	if(jq('#eirge').val()!=''){
	    		refreshCustomValidity(jq('#eirge')[0]);
	  		}
	  	});
	});	


	function changeTransport(record) {
	  	var id = record.id;
		
		var targetTransportId = id.replace("recordUpdate_", "");
		//DEBUG-->alert(targetTransportId + " sources:" + jq('#applicationUser').val() + "-->" + jq('#fromEmlnrt').val() + "-" + jq('#fromEmlnrm').val() + "-" + jq('#fromEtktyp').val())
	  			
	  	var applicationUser = jq('#applicationUser').val();
		var fromEmlnrt = jq('#fromEmlnrt').val();
	  	var fromEmlnrm = jq('#fromEmlnrm').val();
	  	var fromEtktyp = jq('#fromEtktyp').val();
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'changeTransport_Digitoll.do',
	  	  data: { applicationUser : applicationUser,
				  targetTransportId : targetTransportId,	 
	  		  	  fromEmlnrt : fromEmlnrt, 
	  		  	  fromEmlnrm : fromEmlnrm, 
	  		  	  fromEtktyp : fromEtktyp },
		  beforeSend : function() {
               jq.blockUI({ message: 'Wait' });
          }, 
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		jq.unblockUI();
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				console.log("result:" + data[i])
			}
			//finish with child window now
			opener.callParent(targetTransportId);
			window.close();
			
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
		
  	}


//Initialize <div> here for all clazz_dialog
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			maxWidth:500,
			maxHeight: 400,
			width: 300,
			height: 220,
			modal: true
		});
	  });
  });

	jq(function() {
	  jq(".removeLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("removeLink","");
		  
		  jq('#dialogDeleteItem'+counterIndex).dialog( "option", "title", "Slette Varelinje " + jq('#current_id4'+counterIndex).val());
		  //deal with buttons for this modal window
		  jq('#dialogDeleteItem'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
							setBlockUI();
					 		jq('#deleteItemForm'+counterIndex).submit();
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
		  jq('#dialogDeleteItem'+counterIndex).dialog('open');
		 
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
	  "order": [[ 1, "asc" ]], //Linenr
	  "lengthMenu": [ 25, 50, 100],
	  "fnDrawCallback": function( oSettings ) {
    	jq('.dataTables_filter input').addClass("inputText12LightYellow");
    	}
    });

	jq("div.toolbar").html('<span class="text16">Goods Item</span>');
    
//event on input field for search
    jq('input.mainList_filter').on( 'keyup click', function () {
    		filterGlobal();
    });
   
	
  });
  	
  	
	