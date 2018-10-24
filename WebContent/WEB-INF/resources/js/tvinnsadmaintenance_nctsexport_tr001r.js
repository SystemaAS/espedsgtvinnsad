  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  
  jq(function() {
		//Clean values for createing new record
		jq('#newRecordButton').click(function() {
			jq('#tkkode').val("");
			jq("#tkkode").prop("readonly", false);
			jq("#tkkode").removeClass("inputTextReadOnly");
			jq("#tkkode").addClass("inputTextMediumBlueMandatoryField");
			
			//rest of the gang
			jq('#tktxtn').val("");
			jq('#tktxte').val("");
			jq('#tkavg').val("");			
			jq('#tkank').val("");			
			jq('#tktrs').val("");			
			
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
  	var record = rawId.split('_');
  	var searchTkunik = record[0];
	var tkkode = record[1];
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_tr001r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  tkunik : searchTkunik,
  		  	  tkkode : tkkode },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
 			
	  			jq('#tkkode').val("");jq('#tkkode').val(data[i].tkkode);
	  			jq("#tkkode").prop("readonly", true);
	  			jq("#tkkode").removeClass("inputTextMediumBlueMandatoryField");
	  			jq("#tkkode").addClass("inputTextReadOnly");
	  			jq('#tktxtn').val("");jq('#tktxtn').val(data[i].tktxtn);
				jq('#tktxte').val("");jq('#tktxte').val(data[i].tktxte);
				jq('#tkavg').val("");jq('#tkavg').val(data[i].tkavg);
				jq('#tkank').val("");jq('#tkank').val(data[i].tkank);
				jq('#tktrs').val("");jq('#tktrs').val(data[i].tktrs);
				
	  			//for a future update
	  			jq('#updateId').val("");jq('#updateId').val(data[i].tkkode);
 				
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
  