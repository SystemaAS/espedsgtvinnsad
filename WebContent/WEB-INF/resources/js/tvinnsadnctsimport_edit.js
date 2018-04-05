	//===========================================
	//General functions for this JSP side - AJAX
	//===========================================
	
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var map = {};
  	
  	//date fields
    jq(function() {
    	//=====================================
	  	//START Child window for general codes
	  	//=====================================
    	//språk - tisk
		jq('#tiskIdLink').click(function() {
	    	jq('#tiskIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_edit_childwindow_generalcodes.do?action=doInit&type=012&ctype=tisk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tiskIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tiskIdLink').click();
			}
	    });
	    //språk - tignsk
	    jq('#tignskIdLink').click(function() {
	    	jq('#tignskIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_edit_childwindow_generalcodes.do?action=doInit&type=012&ctype=tignsk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tignskIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tignskIdLink').click();
			}
	    });
	    //språk - tialss
	    jq('#tialssIdLink').click(function() {
	    	jq('#tialssIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_edit_childwindow_generalcodes.do?action=doInit&type=012&ctype=tialss', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tialssIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tialssIdLink').click();
			}
	    });
	    //språk - tialss
	    jq('#tiskbIdLink').click(function() {
	    	jq('#tiskbIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_edit_childwindow_generalcodes.do?action=doInit&type=012&ctype=tiskb', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tiskbIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tiskbIdLink').click();
			}
	    });
	    
		//landkod
		jq('#tilkIdLink').click(function() {
	    	jq('#tilkIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_edit_childwindow_generalcodes.do?action=doInit&type=2&ctype=tilk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tilkIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tilkIdLink').click();
			}
	    });
	    //landkod - tialk
		jq('#tialkIdLink').click(function() {
	    	jq('#tialkIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_edit_childwindow_generalcodes.do?action=doInit&type=2&ctype=tialk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tialkIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tialkIdLink').click();
			}
	    });
	    
	    //--------------------
	  	//Tullkontor - titsb
	  	//--------------------
	    jq('#titsbIdLink').click(function() {
	    	jq('#titsbIdLink').attr('target','_blank');
	    	window.open('tvinnsadnctsimport_edit_childwindow_tullkontor.do?action=doInit&tkkode=' + jq('#titsb').val() + '&ctype=titsb', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#titsbIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#titsbIdLink').click();
			}
	    });
	    //----------------
	  	//CUSTOMER search
	  	//----------------
	    //SENDER
	    jq('#tinaIdLink').click(function() {
	    	jq('#tinaIdLink').attr('target','_blank');
	    	window.open('tvinnsadncts_childwindow_customer.do?action=doFind&sonavn=' + jq('#tina').val() + '&ctype=tina', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tinaIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tinaIdLink').click();
			}
	    });
	    
    });
    
    
    
  	//----------------
	//onChange events
	//----------------
  	  //country	
	  jq(function() { 
	    jq('#avsenderland').change(function() {
	    		jq('#tialk').val(jq('#avsenderland').val());	
	    });
	  });
	  jq(function() { 
	    jq('#ansvarligland').change(function() {
	    		jq('#tilk').val(jq('#ansvarligland').val());	
	    });
	  });
	  //language
	  jq(function() { 
	    jq('#tignskSprakCode').change(function() {
	    		jq('#tignsk').val(jq('#tignskSprakCode').val());	
	    });
	  });
	  jq(function() { 
	    jq('#tiskSprakCode').change(function() {
	    		jq('#tisk').val(jq('#tiskSprakCode').val());	
	    });
	  });
	  jq(function() { 
	    jq('#tialssSprakCode').change(function() {
	    		jq('#tialss').val(jq('#tialssSprakCode').val());	
	    });
	  });
	  jq(function() { 
	    jq('#tiskbSprakCode').change(function() {
	    		jq('#tiskb').val(jq('#tiskbSprakCode').val());	
	    });
	  });	
	  
  	//init the customer object in javascript (will be put into a map)
  	var customer = new Object();
  	//fields
  	customer.kundnr = "";customer.knavn = "";customer.eori = "";customer.adr1 = "";
  	customer.adr2 = "";customer.adr3 = "";customer.postnr = "";customer.syland = "";
  	customer.kpers = "";customer.tlf = "";

  	//-----------------------------------------
	//FETCH CUSTOMER from ANSVARIG html area
  	//-----------------------------------------
	function searchAnsvarigOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer_TvinnSad.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_sveh_dkna').val(),
				customerNumber : jq('#search_sveh_dkkn').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
					customer = new Object();
					customer.kundnr = data[i].kundnr;
					customer.knavn = data[i].knavn;
					customer.adr1 = data[i].adr1;
					customer.adr2 = data[i].adr2;
					customer.adr3 = data[i].adr3;
					customer.postnr = data[i].postnr;
					customer.kpers = data[i].kpers;
					customer.tlf = data[i].tlf;
					customer.syland = data[i].syland;
					
					//put the object in map now with customerNumber as key
					map[customer.kundnr] = customer;
				}
				//now that we have our options, give them to our select
				jq('#ansvarigList').html(html);
			});
		});
	}
	//Sets ansvarig values after user selection
	jq(function() { 
	    jq('#ansvarigList').change(function() {
	      //init fields
		  jq('#tikn').val("");
		  jq('#tina').val("");
		  jq('#titin').val("");
		  jq('#tiad1').val("");
		  jq('#tipn').val("");
		  jq('#tips').val("");
		  jq('#tilk').val("");
		  jq('#tisk').val("");
		  
		  //now populate (if applicable)
		  var key = jq('#ansvarigList').val();
		  jq('#tikn').val(key);
		  customer = map[key];
		  jq('#tina').val(customer.knavn);
		  jq('#titin').val(customer.eori);
		  jq('#tiad1').val(customer.adr1);
		  jq('#tipn').val(customer.postnr);
		  jq('#tips').val(customer.adr3);
		  jq('#tilk').val(customer.syland);
		  jq('#tisk').val("");			  
		});
	});
	//onClick for Ansvarig dialog
	jq(function() { 
	    jq('#searchCustomer10CloseCancel').click(function() {
	      //rescue the original fields (in an update)
	    	  var name = jq("#orig_tina").val();	
	    	  if(name!=""){	
		      jq('#tikn').val(jq("#orig_tikn").val());	
			  jq('#tina').val(jq("#orig_tina").val());
			  jq('#titin').val(jq("#orig_titin").val());
			  jq('#tiad1').val(jq("#orig_tiad1").val());
			  jq('#tipn').val(jq("#orig_tipn").val());
			  jq('#tips').val(jq("#orig_tips").val());
			  jq('#tilk').val(jq("#orig_tilk").val());
			  jq('#tisk').val(jq("#orig_tisk").val());
	    	  }
	    });
	});
	
	//----------------------------------
	//Events Ansvarig (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgAnsvarigSearch').click(function(){
    			jq("#tikn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_dkkn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchAnsvarigOwnWindow);
			}			
    		});
		jq('#search_sveh_dkna').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchAnsvarigOwnWindow);
			}			
    		});
	});
	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#tikn').blur(function() {
	    		var customerNumberVal = jq('#tikn').val();
	    		if(customerNumberVal!=""){
		    		jq.getJSON('searchCustomer_TvinnSad.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : customerNumberVal,
					ajax : 'true'
				}, function(data) {
					//alert("Hello");
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						//html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
						customer = new Object();
						customer.kundnr = data[i].kundnr;
						customer.knavn = data[i].knavn;
						customer.eori = data[i].eori;
						customer.adr1 = data[i].adr1;
						customer.adr2 = data[i].adr2;
						customer.adr3 = data[i].adr3;
						customer.postnr = data[i].sypoge;//data[i].postnr; DK=sypoge
						if("" == customer.postnr){
							customer.postnr = data[i].postnr;
						}
						customer.kpers = data[i].kpers;
						customer.tlf = data[i].tlf;
						customer.syland = data[i].syland;
					  	//put the object in map now with customerNumber as key
						map[customer.kundnr] = customer;
					}
					if(len > 0){
						jq('#tikn').val(customer.kundnr);
						jq('#titin').val(customer.eori);
						jq('#tina').val(customer.knavn);
						jq('#tiad1').val(customer.adr1);
						jq('#tipn').val(customer.postnr);
						jq('#tips').val(customer.adr3);
						jq('#tilk').val(customer.syland);
						jq('#tisk').val("");	
					}else{
						//init fields
						jq('#titin').val("");
						jq('#tina').val("");
						jq('#tiad1').val("");
						jq('#tipn').val("");
						jq('#tips').val("");
						jq('#tilk').val("");
						jq('#tisk').val("");
					}
				});
	    		}
		});
	});
	
	//------------------------------------------------------------------------------------------
	//Init Topic with CREATE NEW TOPIC since some values are fetched depending on the Avdelning
	//------------------------------------------------------------------------------------------	
	jq(function() { 
	    jq('#avd').change(function() {
	    		//alert('Hej');
	    		//this parameters must match the AJAX controller parameter names in Spring exactly...
	    		jq.getJSON('initCreateNewTopic_TvinnSadNctsImport.do', {
	    			applicationUser : jq('#applicationUser').val(),
	    			avd : jq('#avd').val(),
	    			ajax : 'true'
			}, 
			function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#tienkl').val(data[i].tienkl);
					//jq('#sign').val(data[i].tisg);
					//jq('#tisg').val(data[i].tisg);
					jq('#tist').val(data[i].tist);
					jq('#tidt').val(data[i].tidt);
					
					jq('#tikn').val(data[i].tikn);
					jq('#tina').val(data[i].tina);
					jq('#tiad1').val(data[i].tiad1);
					jq('#tips').val(data[i].tips);
					jq('#tipn').val(data[i].tipn);
					jq('#tilk').val(data[i].tilk);
					jq('#tisk').val(data[i].tisk);
					jq('#titin').val(data[i].titin);
					
					jq('#tign').val(data[i].tign);
					jq('#titrnr').val(data[i].titrnr);
					//jq('#tignsk').val(data[i].tignsk);
					//jq('#tialk').val(data[i].tialk);
					jq('#tignsk').val("NO");//default
					jq('#tialk').val("NO");//default
					jq('#tialsk').val(data[i].tialsk);
					
					jq('#tials').val(data[i].tials);
					jq('#tialss').val(data[i].tialss);
					
					jq('#tiglsk').val(data[i].tiglsk);
					jq('#tiacts').val(data[i].tiacts);
					jq('#tiskb').val(data[i].tiskb);
					jq('#titsb').val(data[i].titsb);
					jq('#tidtf').val(data[i].tidtf);
					
				}
				
			});
	    });
	});
	
	//-------------------------------------------
	//Mellanligande-Tullkontor AJAX [NCTS Import]
	//-------------------------------------------
	//FETCH Tullkontor
	function searchAvgangTullkontorOwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	var KONTOR_TYPE_AVGANG = "avg";
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor_TvinnSad.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code').val(),
				kontorType : KONTOR_TYPE_AVGANG,
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorList').html(html);
			});
		});
		
	}
	//BestämmelseTullkontor list
	jq(function() { 
	    jq('#tullkontorList').change(function() {
	    	jq('#titsb').val(""); //ncts import
	    	
		//now populate (if applicable)
	    	var key = jq('#tullkontorList').val();
	    	jq('#titsb').val(key); //ncts import
	    	
	    });
	});
	//----------------------------------------
	//Events Avgångstullkontor (SEARCH window)
	//----------------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontor').click(function(){
    			jq("#search_sveh_utfa").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchAvgangTullkontorOwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchAvgangTullkontorOwnWindow);
			}			
    		});
	});

	
	  //-------------------------------------------
	  //START Model dialog ADMIN: "Update status"
	  //-------------------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogUpdateStatus").dialog({
			  autoOpen: false,
			  maxWidth:500,
	          maxHeight: 400,
	          width: 280,
	          height: 220,
			  modal: true
		  });
	  });
	  
	//----------------------------
	  //Present dialog box onClick 
	  //----------------------------
	  jq(function() {
		  jq("#updateStatusLink").click(function() {
			  presentChangeStatusDialog();
		  });
		  jq("#updateStatusByUserImg").click(function() {
			  presentChangeStatusDialog();
		  });
		  
	  });
	  //Present dialog box onClick (href in parent JSP)
	  function presentChangeStatusDialog(){
		  //setters (add more if needed)
		  jq('#dialogUpdateStatus').dialog( "option", "title", "Update Status" );
		  //deal with buttons for this modal window
		  jq('#dialogUpdateStatus').dialog({
				 buttons: [ 
		            {
					 id: "dialogSaveTU",	
					 text: "Ok",
					 click: function(){
						 		jq('#updateStatusForm').submit();
					 		}
				 	 },
		 	 		{
				 	 id: "dialogCancelTU",
				 	 text: "Cancel", 
					 click: function(){
						 		//back to initial state of form elements on modal dialog
						 		jq("#dialogSaveSU").button("option", "disabled", true);
						 		jq("#selectedStatus").val("");
						 		jq( this ).dialog( "close" ); 
					 		} 
		 	 		 } ] 
			  });
		  //init values
		  jq("#dialogSaveSU").button("option", "disabled", true);
		  //open now
		  jq('#dialogUpdateStatus').dialog('open');
	  };

	
	
	
	
	
	
	

