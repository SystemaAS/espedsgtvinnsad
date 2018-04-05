	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  	
  	
  	jq(function() {
		jq("#nidtl").datepicker({ 
		  dateFormat: 'yymmdd' 
		});
		//=====================================
		//START Child window for general codes
		//=====================================
		//språk 
		jq('#nvctskIdLink').click(function() {
	    	jq('#nvctskIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_unloading_edit_items_childwindow_generalcodes.do?action=doInit&type=012&ctype=nvctsk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#nvctskIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#nvctskIdLink').click();
			}
	    });
	  //språk 
		jq('#nvdskIdLink').click(function() {
	    	jq('#nvdskIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_unloading_edit_items_childwindow_generalcodes.do?action=doInit&type=012&ctype=nvdsk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#nvdskIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#nvdskIdLink').click();
			}
	    });
	  //språk 
		jq('#nvmnskIdLink').click(function() {
	    	jq('#nvmnskIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_unloading_edit_items_childwindow_generalcodes.do?action=doInit&type=012&ctype=nvmnsk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#nvmnskIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#nvmnskIdLink').click();
			}
	    });
	    //språk 
		jq('#nvdoskIdLink').click(function() {
	    	jq('#nvdoskIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_unloading_edit_items_childwindow_generalcodes.do?action=doInit&type=012&ctype=nvdosk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#nvdoskIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#nvdoskIdLink').click();
			}
	    });
	    //språk 
		jq('#nvvtskIdLink').click(function() {
	    	jq('#nvvtskIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_unloading_edit_items_childwindow_generalcodes.do?action=doInit&type=012&ctype=nvvtsk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#nvvtskIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#nvvtskIdLink').click();
			}
	    });
	    //kollislag 
		jq('#nvehIdLink').click(function() {
	    	jq('#nvehIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_unloading_edit_items_childwindow_generalcodes.do?action=doInit&type=017&ctype=nveh', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#nvehIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#nvehIdLink').click();
			}
	    });
	    //doctype 
		jq('#nvdtyIdLink').click(function() {
	    	jq('#nvdtyIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_unloading_edit_items_childwindow_generalcodes.do?action=doInit&type=013&ctype=nvdty', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#nvdtyIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#nvdtyIdLink').click();
			}
	    });
	    
 		//Varunr.
	    jq('#nvvntIdLink').click(function() {
	    	jq('#nvvntIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_unloading_edit_items_childwindow_tolltariff.do?action=doInit&vkod=' + jq('#nvvnt').val(), "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#nvvntIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#nvvntIdLink').click();
			}
	    });
  	});
  	/**
  	 * gets a specific item line
  	 * 
  	 * @param record
  	 */
  	function getItemData(record) {
	  	var htmlValue = record.id;
	  	var applicationUserParam = jq('#applicationUser').val();
	  	var avdParam = jq('#avdItemList').val();
	  	var opdParam = jq('#opdItemList').val();
	  	//alert(htmlValue);
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getSpecificTopicUnloadingItemChosenFromGuiElement_TvinnNctsImport.do',
	  	  data: { applicationUser : applicationUserParam, 
	  		  	  elementValue : htmlValue, 
	  		  	  avd : avdParam, 
	  		  	  opd : opdParam },
	  	  dataType: 'json',
	  	  success: function(data) {
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				//Editable fields
				jq('#nvct').val(""); jq('#nvct').val(data[i].nvct);
				jq('#nvctsk').val(""); jq('#nvctsk').val(data[i].nvctsk);
				jq('#nvctb').val(""); jq('#nvctb').val(data[i].nvctb);
				jq('#nvctp').val(""); jq('#nvctp').val(data[i].nvctp);
				jq('#nvvt').val(""); jq('#nvvt').val(data[i].nvvt);
				jq('#nvvnt').val(""); jq('#nvvnt').val(data[i].nvvnt);
				jq('#nvvtsk').val(""); jq('#nvvtsk').val(data[i].nvvtsk);
				jq('#nvvktb').val(""); jq('#nvvktb').val(data[i].nvvktb);
				jq('#nvvktn').val(""); jq('#nvvktn').val(data[i].nvvktn);
				jq('#nvdty').val(""); jq('#nvdty').val(data[i].nvdty);
				jq('#nvdref').val(""); jq('#nvdref').val(data[i].nvdref);
				jq('#nvdsk').val(""); jq('#nvdsk').val(data[i].nvdsk);
				jq('#nvdo').val(""); jq('#nvdo').val(data[i].nvdo);
				jq('#nvdosk').val(""); jq('#nvdosk').val(data[i].nvdosk);
				jq('#nvmn').val(""); jq('#nvmn').val(data[i].nvmn);
				jq('#nvmnsk').val(""); jq('#nvmnsk').val(data[i].nvmnsk);
				jq('#nvcnr').val(""); jq('#nvcnr').val(data[i].nvcnr);
				jq('#nveh').val(""); jq('#nveh').val(data[i].nveh);
				jq('#nvnt').val(""); jq('#nvnt').val(data[i].nvnt);
				jq('#nvnteh').val(""); jq('#nvnteh').val(data[i].nvnteh);
				jq('#nvfv').val(""); jq('#nvfv').val(data[i].nvfv);
				jq('#nvfvnt').val(""); jq('#nvfvnt').val(data[i].nvfvnt);
				
				//Read only fields
				jq('#lineNr').val(data[i].tvli);
				jq('#tvli').val(""); jq('#tvli').val(data[i].tvli); 
				jq('#tvvnt').val(""); jq('#tvvnt').val(data[i].tvvnt);
				jq('#tvvtsk').val(""); jq('#tvvtsk').val(data[i].tvvtsk);
				jq('#tvvktb').val(""); jq('#tvvktb').val(data[i].tvvktb);
				jq('#tvvktn').val(""); jq('#tvvktn').val(data[i].tvvktn);
				jq('#tvvt').val(""); jq('#tvvt').val(data[i].tvvt);
				jq('#tvdty').val(""); jq('#tvdty').val(data[i].tvdty);
				jq('#tvdref').val(""); jq('#tvdref').val(data[i].tvdref);
				jq('#tvdsk').val(""); jq('#tvdsk').val(data[i].tvdsk);
				jq('#tvdo').val(""); jq('#tvdo').val(data[i].tvdo);
				jq('#tvdosk').val(""); jq('#tvdosk').val(data[i].tvdosk);
				jq('#tvmn').val(""); jq('#tvmn').val(data[i].tvmn);
				jq('#tvmnsk').val(""); jq('#tvmnsk').val(data[i].tvmnsk);
				jq('#tvcnr').val(""); jq('#tvcnr').val(data[i].tvcnr);
				jq('#tveh').val(""); jq('#tveh').val(data[i].tveh);
				jq('#tvnt').val(""); jq('#tvnt').val(data[i].tvnt);
				jq('#tvnteh').val(""); jq('#tvnteh').val(data[i].tvnteh);
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
  	}
  	
  	//taric varukod search (same function as in TDS EXPORT. Therefore the name: search_svvs_vata in the search field name)
  	/*
  	function searchTaricVarukod() {
		jq(function() {
			jq.getJSON('searchTaricVarukodNcts_SkatNctsImport.do', {
				applicationUser : jq('#applicationUser').val(),
				taricVarukod : jq('#search_svvs_vata').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].dktara02 + '">' + data[i].dktara02 + ' ' + data[i].dktara64 +  '</option>';
				}
				//now that we have our options, give them to our select
				jq('#taricVarukodList').html(html);
			});
		});
	}
  	//set the taric varukod in target input text field
  	jq(function() { 
	    jq('#taricVarukodList').change(function() {
		  //init field(s)
		  jq('#nvvnt').val("");
		  //and populate (if applicable)
		  var key = jq('#taricVarukodList').val();
		  jq('#nvvnt').val(key.substring(0,6));
		  			  
		});
	});
  	
  	//----------------------------------
	//Events Varukod (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTaricVarukodSearch').click(function(){
    			jq("#search_svvs_vata").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_svvs_vata').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTaricVarukod);
			}			
    		});
	});

	//On Keypress (13)
	jq(function() { 
	    jq('#taricVarukodList').keypress(function() {
		    	if(e.which == 13) {
				//alert("hej till publiken");
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#svev_vata').val(""); 
				//now populate (if applicable)
			    	var key = jq('#taricVarukodList').val();
			    	jq('#svev_vata').val(key); 
		    	}
	    });
	    
	});
	
	//calculate a net weight from the gross weight
  	jq(function() { 
	    jq('#nvvktb').blur(function() {
		  //init field(s)
	    	  var grossWeight = jq('#nvvktb').val().replace(".","");
	    	  grossWeight = grossWeight.replace(",",".");
	    	  
		  var netWeight = jq('#nvvktn').val("");
		  if(netWeight!=null){
			  var netWeightRaw = Math.round(grossWeight * 0.8);
			  jq('#nvvktn').val(netWeightRaw);
		  }
		});
	});
	*/
	
	