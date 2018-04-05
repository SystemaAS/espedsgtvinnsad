  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
  	  jq("#tadatoNO").datepicker({ 
  		  dateFormat: 'ddmmy'
  	  });
  	  jq("#tadtsNO").datepicker({ 
  		  dateFormat: 'ddmmy'
	  });
  });

  jq(function() {
	
	//Clean values for createing new record
	jq('#newRecordButton').click(function() {
		jq('#tatanr').val("");
			jq("#tatanr").prop("readonly", false);
			jq("#tatanr").removeClass("inputTextReadOnly");
			jq("#tatanr").addClass("inputTextMediumBlueMandatoryField");
			
			//rest of the gang
			jq('#taalfa').val(""); jq('#taalfaOrig').val(""); jq('#tadatoNO').val(""); jq('#tadtr').val("");
			jq('#tadtsNO').val(""); jq('#taordb').val(""); jq('#taordk').val(""); jq('#taeftb').val("");
  			jq('#taeftk').val(""); jq('#taefb').val(""); jq('#taefk').val("");
  			//
  			jq('#tastk').val(""); jq('#tatxt').val(""); jq('#taenhe').val("");
  			//countries
  			jq('#taeosb').val(""); jq('#taeosk').val(""); jq('#tatsjb').val(""); jq('#tatsjk').val("");
  			jq('#tatyrb').val(""); jq('#tatyrk').val(""); jq('#taisrb').val(""); jq('#taisrk').val("");
  			jq('#taellb').val(""); jq('#taellk').val(""); jq('#tabulb').val(""); jq('#tabulk').val("");
  			jq('#tapolb').val(""); jq('#tapolk').val(""); jq('#taromb').val(""); jq('#taromk').val("");
  			jq('#tan05b').val(""); jq('#tan05k').val(""); jq('#tan06b').val(""); jq('#tan06k').val("");
  			jq('#tan07b').val(""); jq('#tan07k').val(""); jq('#taungb').val(""); jq('#taungk').val("");
  			//
  			jq('#taslob').val(""); jq('#taslok').val(""); jq('#tamulb').val(""); jq('#tamulk').val("");
  			jq('#taoulb').val(""); jq('#taoulk').val(""); jq('#tagrlb').val(""); jq('#tagrlk').val("");
  			jq('#taferb').val(""); jq('#taferk').val(""); jq('#taistb').val(""); jq('#taistk').val("");
  			jq('#tamarb').val(""); jq('#tamark').val(""); jq('#tan08b').val(""); jq('#tan08k').val("");
  			jq('#tan09b').val(""); jq('#tan09k').val(""); jq('#tan10b').val(""); jq('#tan10k').val("");
  			jq('#tamexb').val(""); jq('#tamexk').val(""); jq('#tavgab').val(""); jq('#tavgak').val("");
  			jq('#tan01b').val(""); jq('#tan01k').val(""); jq('#tan02b').val(""); jq('#tan02k').val("");
  			jq('#tan03b').val(""); jq('#tan03k').val(""); jq('#tan04b').val(""); jq('#tan04k').val("");
  			jq('#tan11b').val(""); jq('#tan11k').val(""); jq('#tan12b').val(""); jq('#tan12b').val("");
  			//
  			jq('#tan13b').val(""); jq('#tan13k').val(""); jq('#tan14b').val(""); jq('#tan14k').val("");
  			jq('#tan15b').val(""); jq('#tan15k').val(""); jq('#tarest').val(""); jq('#takapa').val("");
  			
			//for update
			jq('#updateId').val("");
	});
	
  }); 
	
  //Varekod
  //-----------------------
  //GET specific db-record
  //-----------------------
  
  function getRecord(record){
	var rawId = record.id;
  	var applicationUserParam = jq('#applicationUser').val();
  	rawId = rawId.replace("recordUpdate_", "");
  	var record = rawId.split('_');
	var tatanr = record[0];
	var taalfa = record[1];
	    	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_sad010r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : tatanr,
  		  	  alfa : taalfa },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#tatanr').val("");jq('#tatanr').val(data[i].tatanr);
  			jq("#tatanr").prop("readonly", true);
  			jq("#tatanr").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#tatanr").addClass("inputTextReadOnly");
  			
  			//rest of the gang
  			jq('#taalfa').val("");jq('#taalfa').val(data[i].taalfa);
  			jq('#taalfaOrig').val("");jq('#taalfaOrig').val(data[i].taalfa);
  			jq('#tadatoNO').val("");jq('#tadatoNO').val(data[i].tadatoNO);
  			jq('#tadtrNO').val("");jq('#tadtrNO').val(data[i].tadtrNO);
  			jq('#tadtsNO').val("");jq('#tadtsNO').val(data[i].tadtsNO);
  			
  			jq('#taordb').val("");jq('#taordb').val(data[i].taordb);
  			jq('#taordk').val("");jq('#taordk').val(data[i].taordk);
  			jq('#taeftb').val("");jq('#taeftb').val(data[i].taeftb);
  			jq('#taeftk').val("");jq('#taeftk').val(data[i].taeftk);
  			jq('#taefb').val("");jq('#taefb').val(data[i].taefb);
  			jq('#taefk').val("");jq('#taefk').val(data[i].taefk);
  			//
  			jq('#tastk').val("");jq('#tastk').val(data[i].tastk);
  			jq('#tatxt').val("");jq('#tatxt').val(data[i].tatxt);
  			jq('#taenhe').val("");jq('#taenhe').val(data[i].taenhe);
  			//countries
  			jq('#taeosb').val("");jq('#taeosb').val(data[i].taeosb);
  			jq('#taeosk').val("");jq('#taeosk').val(data[i].taeosk);
  			jq('#tatsjb').val("");jq('#tatsjb').val(data[i].tatsjb);
  			jq('#tatsjk').val("");jq('#tatsjk').val(data[i].tatsjk);
  			jq('#tatyrb').val("");jq('#tatyrb').val(data[i].tatyrb);
  			jq('#tatyrk').val("");jq('#tatyrk').val(data[i].tatyrk);
  			jq('#taisrb').val("");jq('#taisrb').val(data[i].taisrb);
  			jq('#taisrk').val("");jq('#taisrk').val(data[i].taisrk);
  			jq('#taellb').val("");jq('#taellb').val(data[i].taellb);
  			jq('#taellk').val("");jq('#taellk').val(data[i].taellk);
  			//
  			jq('#tabulb').val("");jq('#tabulb').val(data[i].tabulb);
  			jq('#tabulk').val("");jq('#tabulk').val(data[i].tabulk);
  			jq('#tapolb').val("");jq('#tapolb').val(data[i].tapolb);
  			jq('#tapolk').val("");jq('#tapolk').val(data[i].tapolk);
  			jq('#taromb').val("");jq('#taromb').val(data[i].taromb);
  			jq('#taromk').val("");jq('#taromk').val(data[i].taromk);
  			jq('#tan05b').val("");jq('#tan05b').val(data[i].tan05b);
  			jq('#tan05k').val("");jq('#tan05k').val(data[i].tan05k);
  			jq('#tan06b').val("");jq('#tan06b').val(data[i].tan06b);
  			jq('#tan06k').val("");jq('#tan06k').val(data[i].tan06k);
  			jq('#tan07b').val("");jq('#tan07b').val(data[i].tan07b);
  			jq('#tan07k').val("");jq('#tan07k').val(data[i].tan07k);
  			jq('#taungb').val("");jq('#taungb').val(data[i].taungb);
  			jq('#taungk').val("");jq('#taungk').val(data[i].taungk);
  			//
  			jq('#taslob').val("");jq('#taslob').val(data[i].taslob);
  			jq('#taslok').val("");jq('#taslok').val(data[i].taslok);
  			jq('#tamulb').val("");jq('#tamulb').val(data[i].tamulb);
  			jq('#tamulk').val("");jq('#tamulk').val(data[i].tamulk);
  			jq('#taoulb').val("");jq('#taoulb').val(data[i].taoulb);
  			jq('#taoulk').val("");jq('#taoulk').val(data[i].taoulk);
  			jq('#tagrlb').val("");jq('#tagrlb').val(data[i].tagrlb);
  			jq('#tagrlk').val("");jq('#tagrlk').val(data[i].tagrlk);
  			jq('#taferb').val("");jq('#taferb').val(data[i].taferb);
  			jq('#taferk').val("");jq('#taferk').val(data[i].taferk);
  			jq('#taistb').val("");jq('#taistb').val(data[i].taistb);
  			jq('#taistk').val("");jq('#taistk').val(data[i].taistk);
  			//
  			jq('#tamarb').val("");jq('#tamarb').val(data[i].tamarb);
  			jq('#tamark').val("");jq('#tamark').val(data[i].tamark);
  			jq('#tan08b').val("");jq('#tan08b').val(data[i].tan08b);
  			jq('#tan08k').val("");jq('#tan08k').val(data[i].tan08k);
  			jq('#tan09b').val("");jq('#tan09b').val(data[i].tan09b);
  			jq('#tan09k').val("");jq('#tan09k').val(data[i].tan09k);
  			jq('#tan10b').val("");jq('#tan10b').val(data[i].tan10b);
  			jq('#tan10k').val("");jq('#tan10k').val(data[i].tan10k);
  			jq('#tamexb').val("");jq('#tamexb').val(data[i].tamexb);
  			jq('#tamexk').val("");jq('#tamexk').val(data[i].tamexk);
  			jq('#tavgab').val("");jq('#tavgab').val(data[i].tavgab);
  			jq('#tavgak').val("");jq('#tavgak').val(data[i].tavgak);
  			//
  			jq('#tan01b').val("");jq('#tan01b').val(data[i].tan01b);
  			jq('#tan01k').val("");jq('#tan01k').val(data[i].tan01k);
  			jq('#tan02b').val("");jq('#tan02b').val(data[i].tan02b);
  			jq('#tan02k').val("");jq('#tan02k').val(data[i].tan02k);
  			jq('#tan03b').val("");jq('#tan03b').val(data[i].tan03b);
  			jq('#tan03k').val("");jq('#tan03k').val(data[i].tan03k);
  			jq('#tan04b').val("");jq('#tan04b').val(data[i].tan04b);
  			jq('#tan04k').val("");jq('#tan04k').val(data[i].tan04k);
  			jq('#tan11b').val("");jq('#tan11b').val(data[i].tan11b);
  			jq('#tan11k').val("");jq('#tan11k').val(data[i].tan11k);
  			jq('#tan12b').val("");jq('#tan12b').val(data[i].tan12b);
  			jq('#tan12b').val("");jq('#tan12b').val(data[i].tan12b);
  			//
  			jq('#tan13b').val("");jq('#tan13b').val(data[i].tan13b);
  			jq('#tan13k').val("");jq('#tan13k').val(data[i].tan13k);
  			jq('#tan14b').val("");jq('#tan14b').val(data[i].tan14b);
  			jq('#tan14k').val("");jq('#tan14k').val(data[i].tan14k);
  			jq('#tan15b').val("");jq('#tan15b').val(data[i].tan15b);
  			jq('#tan15k').val("");jq('#tan15k').val(data[i].tan15k);
  			jq('#tarest').val("");jq('#tarest').val(data[i].tarest);
  			jq('#takapa').val("");jq('#takapa').val(data[i].takapa);
  			
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].tatanr);
  			
  		}
  	  }, 
  	  error: function() {
  		  alert('Error loading ...');
  	  }
	});
		
  }
  		
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
    	  "columnDefs": [{ "type": "num", "targets": 0 }],
    	  "lengthMenu": [ 75, 100]
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  