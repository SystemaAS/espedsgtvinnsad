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

		//Varukod
	    jq('#eivntIdLink').click(function() {
	    	jq('#eivntIdLink').attr('target','_blank');
	    	window.open('tvinnsaddigitollv2_childwindow_tolltariff.do?action=doInit&vkod=' + jq('#eivnt').val(), "codeWin", "top=350px,left=850px,height=400px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svvntIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#eivntIdLink').click();
			}
	    });
	});	


	function getItemData(record) {
	  	var id = record.id;
		var eili = id.replace("recordUpdate_", "");
	  	
		var ids = jq("#"+record.id).attr("title");
	  	var record = ids.split("_");
		//console.log = record;
	  	var applicationUserParam = jq('#applicationUser').val();
	  	var eilnrt = record[0];
	  	var eilnrm = record[1];
	  	var eilnrh = record[2];
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getSpecificGoodsItemVoec_Digitoll.do',
	  	  data: { applicationUser : applicationUserParam,
				  eili : eili,	 
	  		  	  eilnrt : eilnrt, 
	  		  	  eilnrm : eilnrm, 
	  		  	  eilnrh : eilnrh },
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				jq('#eili').val(""); jq('#eili').val(data[i].eili);
				jq('#eibl').val(""); jq('#eibl').val(data[i].eibl);
				jq('#eistk').val(""); jq('#eistk').val(data[i].eistk);
				jq('#eivnt').val(""); jq('#eivnt').val(data[i].eivnt);
				jq('#eirge').val(""); jq('#eirge').val(data[i].eirge);
						
			}
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
		  
		  jq('#dialogDeleteItem'+counterIndex).dialog( "option", "title", "Slett Varelinje " + jq('#current_id4'+counterIndex).val());
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
  	
  	
	