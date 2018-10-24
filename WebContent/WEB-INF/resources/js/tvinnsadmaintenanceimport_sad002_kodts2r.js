  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  
  jq(function() {
		//Clean values for createing new record
		jq('#newRecordButton').click(function() {
			jq('#ks2lk').val("");
			jq("#ks2lk").prop("readonly", false);
			jq("#ks2lk").removeClass("inputTextReadOnly");
			jq("#ks2lk").addClass("inputTextMediumBlueMandatoryField");
			//
			jq('#ks2ftx').val("");
			jq('#ks2pre').val("");
			jq('#ks2mo').val("");
			jq('#ks2nas').val("");
  			
			//for update
			jq('#updateId').val("");
		});
}); 

  //-----------------------
  //GET specific db-record
  //-----------------------
  function getRecord(record){
	var rawId = record.id;
  	var applicationUserParam = jq('#applicationUser').val();
  	rawId = rawId.replace("recordUpdate_", "");
  	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_sad002_kodts2r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : rawId },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#ks2lk').val("");jq('#ks2lk').val(data[i].ks2lk);
  			jq("#ks2lk").prop("readonly", true);
  			jq("#ks2lk").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#ks2lk").addClass("inputTextReadOnly");
  			//editable fields
  			jq('#ks2ftx').val("");jq('#ks2ftx').val(data[i].ks2ftx);
  			jq('#ks2pre').val("");jq('#ks2pre').val(data[i].ks2pre);
  			jq('#ks2mo').val("");jq('#ks2mo').val(data[i].ks2mo);
  			jq('#ks2nas').val("");jq('#ks2nas').val(data[i].ks2nas);
  			
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].ks2lk);
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
  