	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  //--------
    //Koder
    //--------
    jq(function() {
  		jq('#tollstedCodeList').on('click', 'td', function(){
  			  var id = this.id;
  			  var record = id.split('@');
  			  var kod = record[0].replace("kod", "");
  			  var callerType = record[1].replace("ctype", "");
  			  //alert(kod + " " + callerType);
  			  
  			  //addressing a parent field from this child window
  			  opener.jq('#eftsd').val(kod);
  			  
  			  
  			  /*
  			  if(callerType == 'sival3'){
  				  opener.jq('#sival3').val(kod);
  				  opener.jq('#sival3').change();
  				  opener.jq('#sival3').focus();
  				  
  			  }else if(callerType == 'silka'){
  				  opener.jq('#silka').val(kod);
  				  opener.jq('#silka').focus();
  				  
  			  }else if(callerType == 'silkt'){
  				  opener.jq('#silkt').val(kod);
  				  opener.jq('#silkt').focus();
  				  
  			  }else if(callerType == 'sival1'){
  				  opener.jq('#sival1').val(kod);
  				  opener.jq('#sival1').focus();
  				  
  			  }else if(callerType == 'sival2'){
  				  opener.jq('#sival2').val(kod);
  				  opener.jq('#sival2').focus();
  				  
  			  }else if(callerType == 'sfvk28'){
  				  //this belongs to the invoice JSP - finansopplysninger
  				  opener.jq('#sfvk28').val(kod);
  				  opener.jq('#sfvk28').focus();
  				  
  			  }
  			  */
  			  //close child window
  			  window.close();
  		  });
  	});
  	
  	
    //======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterTollstedCode () {
    		jq('#tollstedCodeList').DataTable().search(
      		jq('#tollstedCodeList_filter').val()
    		).draw();
    } 
  	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#tollstedCodeList').dataTable( {
    		  "searchHighlight": true,
    		  //"order": [[ 0, "desc" ]],
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500],
    		  "fnDrawCallback": function( oSettings ) {
    			  jq('.dataTables_filter input').addClass("inputText12LightYellow");
    		  }
    	  });
      //event on input field for search
      jq('input.tollstedCodeList_filter').on( 'keyup click', function () {
      	filterTollstedCode();
      });
      
    });   
  	
    
   
  	
	