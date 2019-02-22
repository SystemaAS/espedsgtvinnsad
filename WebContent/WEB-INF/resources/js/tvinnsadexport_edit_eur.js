	//this variable is a global jQuery var instead of using "$" all the time. Very handy
	var jq = jQuery.noConflict();
	
  	//Overlay on tab (to mark visually a delay...)
    jq(function() {
	  jq('#alinkTopicList').click(function() { 
		  setBlockUI();
  	  });	
  	  jq('#alinkHeader').click(function() { 
  		setBlockUI();
	  });
      jq('#alinkOmberegning').click(function() { 
    	  setBlockUI();
  	  });  	  
  	  jq('#alinkItemLines').click(function() { 
  		setBlockUI();
  	  });
  	  jq('#alinkLogging').click(function() { 
  		setBlockUI();
	  });
  	  jq('#alinkArchive').click(function() { 
  		setBlockUI();
	  });
    });
  	
	jq(function() {
       
          jq( "#sadExportEurForm" ).submit(function( event ) {
  			setBlockUI();
	  	  });
		

        jq('#eur01a').focus(function() {
    	if(jq('#eur01a').val()!=''){
    		refreshCustomValidity(jq('#eur01a')[0]);
  		}
        });

        jq('#eur01b').focus(function() {
    	if(jq('#eur01b').val()!=''){
    		refreshCustomValidity(jq('#eur01b')[0]);
  		}
        });

        jq('#eur01c').focus(function() {
    	if(jq('#eur01c').val()!=''){
    		refreshCustomValidity(jq('#eur01c')[0]);
  		}
        });

        jq('#eur01d').focus(function() {
    	if(jq('#eur01d').val()!=''){
    		refreshCustomValidity(jq('#eur01d')[0]);
  		}
        });
          
        jq('#eur04').focus(function() {
    	if(jq('#eur04').val()!=''){
    		refreshCustomValidity(jq('#eur04')[0]);
  		}
        });
          
        jq('#eur05').focus(function() {
    	if(jq('#eur05').val()!=''){
    		refreshCustomValidity(jq('#eur05')[0]);
  		}
        });

        jq('#eur12a').focus(function() {
    	if(jq('#eur12a').val()!=''){
    		refreshCustomValidity(jq('#eur12a')[0]);
  		}
        });

        jq('#eur12b').focus(function() {
    	if(jq('#eur12b').val()!=''){
    		refreshCustomValidity(jq('#eur12b')[0]);
  		}
        });

        jq('#eur12c').focus(function() {
    	if(jq('#eur12c').val()!=''){
    		refreshCustomValidity(jq('#eur12c')[0]);
  		}
        });
          

    });
    
    jq(function() {
	  jq("#eur12b").datepicker({ 
		  dateFormat: 'ddmmy'  
	  });
    });
    
    
    jq(document).ready(function() {
       /* 
      jq('#tblItems').dataTable( {
          "dom": '<"top">t<"bottom"><"clear">',
          "tabIndex": -1,
  		  "scrollY":    "100px",
  		  "scrollCollapse":  true
      });*/
        
      
    });
    
