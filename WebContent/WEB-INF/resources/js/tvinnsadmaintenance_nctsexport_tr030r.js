  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  
  jq(function() {
		//Clean values for createing new record
		jq('#newRecordButton').click(function() {
			jq('#tggnr').val("");
			jq("#tggnr").prop("readonly", false);
			jq("#tggnr").removeClass("inputTextReadOnly");
			jq("#tggnr").addClass("inputTextMediumBlueMandatoryField");
			
			//rest of the gang
			jq('#tgkna').val("");
			jq('#tgtina').val("");
			jq('#tgnaa').val("");
			jq('#tgada1').val("");
			jq('#tgpna').val("");
			jq('#tgpsa').val("");
			jq('#tglka').val("");
			//Garanti
			jq('#tgtsd').val("");
			jq('#tggty').val("");
			jq('#tggfv').val("");
			jq('#tgakny').val("");
			jq('#tgakgm').val("");
			jq('#tggbl').val("");
			jq('#tggblb').val("");
			jq('#tggvk').val("");
			jq('#tgprm').val("");
			jq('#tgst').val("");
			
			//for update
			jq('#updateId').val("");
			
	  		jq("#editRowTable").find("*").attr("disabled", false);

		});
  }); 

  
  //-----------------------
  //GET specific db-record
  //-----------------------
  function getRecord(record){
	var rawId = record.id;
  	var applicationUserParam = jq('#applicationUser').val();
  	
  	rawId = rawId.replace("recordUpdate_", "");
  	var record = rawId.split('_');
	var tggnr = record[0];
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_tr030r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : tggnr },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
	  	var status;
  		for ( var i = 0; i < len; i++) {
 				
	  			jq('#tggnr').val("");jq('#tggnr').val(data[i].tggnr);
	  			jq("#tggnr").prop("readonly", true);
	  			jq("#tggnr").removeClass("inputTextMediumBlueMandatoryField");
	  			jq("#tggnr").addClass("inputTextReadOnly");
	  			
	  			//rest of the gang
	  			jq('#tgkna').val("");jq('#tgkna').val(data[i].tgkna);
				jq('#tgtina').val("");jq('#tgtina').val(data[i].tgtina);
				jq('#tgnaa').val("");jq('#tgnaa').val(data[i].tgnaa);
				jq('#tgada1').val("");jq('#tgada1').val(data[i].tgada1);
				jq('#tgpna').val("");jq('#tgpna').val(data[i].tgpna);
				jq('#tgpsa').val("");jq('#tgpsa').val(data[i].tgpsa);
				jq('#tglka').val("");jq('#tglka').val(data[i].tglka);
				//Garanti
				jq('#tgtsd').val("");jq('#tgtsd').val(data[i].tgtsd);
				jq('#tggty').val("");jq('#tggty').val(data[i].tggty);
				jq('#tggfv').val("");jq('#tggfv').val(data[i].tggfv);
				jq('#tgakny').val("");jq('#tgakny').val(data[i].tgakny);
				jq('#tgakgm').val("");jq('#tgakgm').val(data[i].tgakgm);
				jq('#tggbl').val("");jq('#tggbl').val(data[i].tggbl);
				jq('#tggblb').val("");jq('#tggblb').val(data[i].tggblb);
				jq('#tggvk').val("");jq('#tggvk').val(data[i].tggvk);
				jq('#tgprm').val("");jq('#tgprm').val(data[i].tgprm);
				jq('#tgst').val("");jq('#tgst').val(data[i].tgst);
				
				status = data[i].tgst;
				
	  			//for a future update
	  			jq('#updateId').val("");jq('#updateId').val(data[i].tggnr);
 				
  		}
  		
		if(status =='S'){  //Soft deleted
 	  			
	  		jq("#editRowTable").find("*").attr("disabled", true);
	  				
	  	} else {
	  
	  		jq("#editRowTable").find("*").attr("disabled", false);
	  				
	  	}
 		
  	  }, 
 
  	  error: function() {
  		  alert('Error loading ...');
  	  }

	});
	
  }
 

jq(function() { 
    jq('#tgkna').blur(function() {
    		var customerNr = jq('#tgkna').val();
    		var orgNr =  jq('#tgtina').val();
    		var name = jq('#tgnaa').val();
		    var address = jq('#tgada1').val();
		    
    		if(customerNr!="" && (orgNr=='' && name=='' && address=='')){
	    		jq.getJSON('searchCustomer_TvinnSadNcts.do', {
				applicationUser : jq('#applicationUser').val(),
				customerNumber : jq('#tgkna').val(),
				ajax : 'true'
			}, function(data) {
				//alert("Hello, data fetched");
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
					customer.postnr = data[i].postnr;
					customer.syrg = data[i].syrg;
					customer.tlf = data[i].tlf;
					customer.syland = data[i].syland;
				  	//put the object in map now with customerNumber as key
					//map[customer.kundnr] = customer;
				}
				if(len > 0){
					jq('#tgtina').val(customer.syrg);
					jq('#tgnaa').val(customer.knavn);
					jq('#tgada1').val(customer.adr1);
					jq('#tgpna').val(customer.postnr);
					jq('#tgpsa').val(customer.adr3);
					jq('#tglka').val(customer.syland);
				}else{
					//init fields
					jq('#tgtina').val("");
					jq('#tgnaa').val("");
					jq('#tgada1').val("");
					jq('#tgpna').val("");
					jq('#tgpsa').val("");
					jq('#tglka').val("");
					
				}
			});
    		}
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
    	  "scrollY": "250px",
    	  "scrollCollapse":  false,
    	  "lengthMenu": [ 75, 100]
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  