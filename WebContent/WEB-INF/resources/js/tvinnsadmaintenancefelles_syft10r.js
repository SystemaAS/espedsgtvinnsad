  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  
  jq(function() {
		//Clean values for createing new record
		jq('#newRecordButton').click(function() {
			jq('#ksisig').val("");
			jq("#ksisig").prop("readonly", false);
			jq("#ksisig").removeClass("inputTextReadOnly");
			jq("#ksisig").addClass("inputTextMediumBlueUPPERCASEMandatoryField");
			//rest of the gang
			jq('#ksinav').val("");
			jq('#ksovl').val("");
			jq('#ksuser').val("");
			jq('#ksixxx').val("");
			jq('#klbfrk').val("");
			//for update
			jq('#updateId').val("");
		});
  }); 
  
  //-----------------------
  //GET specific db-record
  //-----------------------
  function getRecord(record){
	var id = record.id;
  	var applicationUserParam = jq('#applicationUser').val();
  	id = id.replace("recordUpdate_", "");
	  	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_syft10r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : id },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#ksisig').val("");jq('#ksisig').val(data[i].ksisig);
  			jq("#ksisig").prop("readonly", true);
  			jq("#ksisig").removeClass("inputTextMediumBlueUPPERCASEMandatoryField");
  			jq("#ksisig").addClass("inputTextReadOnly");
  			
  			//rest of the gang
  			jq('#ksinav').val("");jq('#ksinav').val(data[i].ksinav);
  			jq('#ksovl').val("");jq('#ksovl').val(data[i].ksovl);
  			jq('#ksuser').val("");jq('#ksuser').val(data[i].ksuser);
  			jq('#ksixxx').val("");jq('#ksixxx').val(data[i].ksixxx);
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].ksisig);
  			
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
	  var lang = jq('#language').val();
	  jq('#mainList').dataTable( {
    	  "dom": '<"top">t<"bottom"flip><"clear">',
    	  "scrollY": "250px",
    	  "scrollCollapse":  false,
    	  "columnDefs": [{ "type": "num", "targets": 0 }],
    	  "lengthMenu": [ 75, 100],
  		  "language": {
  			  "url": getLanguage(lang)
           }     	  
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  