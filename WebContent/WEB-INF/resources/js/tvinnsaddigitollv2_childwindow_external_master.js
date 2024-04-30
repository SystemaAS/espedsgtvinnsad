	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//-----------
  	// Tullkontor
  	//-----------
	jq(function() {
		jq('#mainList').on('click', 'td', function(){
			  var id = this.id;
			  //id="tupro${record.tupro}@tuavd${record.tuavd}@ctype${model.callerType}
			  var record = id.split('@');
			  var emdkm_ff = record[0].replace("emdkm", "");
			  var emrgt_ff = record[1].replace("trreforg", "");
		  	  var callerType = record[2].replace("ctype", "");
			  var emdkmt_ff = record[3].replace("emdkmt", "");
			  var emrgr_ff = record[4].replace("avsid", "");	
			  	//console.log(emdkm_ff + " " + emrgt_ff + " caller:" + callerType);

			  	if(callerType == 'emdkm'){ 
				  opener.jq('#emdkm_ff').prop('readonly',false);					
				  opener.jq('#emdkm_ff').val(emdkm_ff);
				  opener.jq('#emdkm_ff').prop('readonly',true);
				  //
				  opener.jq('#emdkmt_ff').prop('readonly',false);					
				  opener.jq('#emdkmt_ff').val(emdkmt_ff);
				  opener.jq('#emdkmt_ff').prop('readonly',true);
				  //	
				  opener.jq('#emrgt_ff').prop('readonly',false);
				  opener.jq('#emrgt_ff').val(emrgt_ff);	
				  opener.jq('#emrgt_ff').prop('readonly',true);
				  //
				  opener.jq('#emrgr_ff').prop('readonly',false);
				  opener.jq('#emrgr_ff').val(emrgr_ff);	
				  opener.jq('#emrgr_ff').prop('readonly',true);
				  //focus
				  opener.jq('#emdkm_ff').focus();
			  	}
			  //close child window
			  window.close();
			  
	    });
	});
	
	
	jq(function() {
	  jq("#date").datepicker({ 
		  dateFormat: 'yymmdd' 	  
	  });
	});
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterTur () {
    		jq('#mainList').DataTable().search(
      		jq('#mainList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
	  jq('#mainList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',               
		  "searchHighlight": true,
		  "tabIndex": -1,
	  	  "order": [[ 3, "desc" ]], //ETA
		  "lengthMenu": [ 50, 100, 200, 500],
		  "fnDrawCallback": function( oSettings ) {
	    	jq('.dataTables_filter input').addClass("inputText12LightYellow");
			
	    	}
	  });
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterTur();
      });
      
    });   
  	
  	
	