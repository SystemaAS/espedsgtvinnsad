	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq('#customerList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			 
			  var knr = record[0].replace("knr", "");
			  var knavn = record[1].replace("knavn", "");
			  var adr1 = record[2].replace("kadr1", "");
			  var adr3 = record[3].replace("kadr3", "");
			  var postnr = record[4].replace("kpostnr", "");
			  var land = record[5].replace("kland", "");
			  var eori = record[6].replace("keori", "");
			  var callerType = record[7].replace("ctype", "");
			  var syrg = record[8].replace("ksyrg", ""); 
			  //DEBUG -> alert(knr + " " + knavn + " " + callerType)
			  			  
			  //addressing a parent field from this child window
			  //=========================
			  //TVINN NCTS Export Module 
			  //=========================
			  if(callerType == 'thnas'){
				  opener.jq('#thkns').val(knr);
				  opener.jq('#thnas').val(knavn);
				  opener.jq('#thtins').val(syrg);
				  opener.jq('#thads1').val(adr1);
				  opener.jq('#thpss').val(adr3);
				  opener.jq('#thpns').val(postnr);
				  opener.jq('#thlks').val(land);
				  opener.jq('#thnas').focus();
				  
			  }else if(callerType == 'thnak'){
				  opener.jq('#thknk').val(knr);
				  opener.jq('#thnak').val(knavn);
				  opener.jq('#thtink').val(syrg);
				  opener.jq('#thadk1').val(adr1);
				  opener.jq('#thpsk').val(adr3);
				  opener.jq('#thpnk').val(postnr);
				  opener.jq('#thlkk').val(land);
				  opener.jq('#thnak').focus();
				  
			  }else if(callerType == 'thnaa'){
				  opener.jq('#thnaa').val(knavn);
				  opener.jq('#thtina').val(syrg);
				  opener.jq('#thada1').val(adr1);
				  opener.jq('#thpsa').val(adr3);
				  opener.jq('#thpna').val(postnr);
				  opener.jq('#thlka').val(land);
				  opener.jq('#thnaa').focus();
				  
			  }else if(callerType == 'thnass'){ //SENDER SIKKERHET
				  opener.jq('#thknss').val(knr);
				  opener.jq('#thnass').val(knavn);
				  opener.jq('#thtinss').val(syrg);
				  opener.jq('#thadss1').val(adr1);
				  opener.jq('#thpsss').val(adr3);
				  opener.jq('#thpnss').val(postnr);
				  opener.jq('#thlkss').val(land);
				  opener.jq('#thnass').focus();
				  
			  }else if(callerType == 'thnaks'){ //RECEIVER SIKKERHET
				  opener.jq('#thknks').val(knr);
				  opener.jq('#thnaks').val(knavn);
				  opener.jq('#thtinks').val(syrg);
				  opener.jq('#thadks1').val(adr1);
				  opener.jq('#thpsks').val(adr3);
				  opener.jq('#thpnks').val(postnr);
				  opener.jq('#thlkks').val(land);
				  opener.jq('#thnaks').focus();
				  
			  }else if(callerType == 'thnat'){ //TRANSPORT SIKKERHET
				  opener.jq('#thknt').val(knr);
				  opener.jq('#thnat').val(knavn);
				  opener.jq('#thtint').val(syrg);
				  opener.jq('#thadt1').val(adr1);
				  opener.jq('#thpst').val(adr3);
				  opener.jq('#thpnt').val(postnr);
				  opener.jq('#thlkt').val(land);
				  opener.jq('#thnat').focus();
		      //--------------------------	  
			  //NCTS Export ITEMS
			  //--------------------------		  
			  }else if(callerType == 'tvnas'){
				  opener.jq('#tvkns').val(knr);
				  opener.jq('#tvnas').val(knavn);
				  opener.jq('#tvtins').val(syrg);
				  opener.jq('#tvads1').val(adr1);
				  opener.jq('#tvpss').val(adr3);
				  opener.jq('#tvpns').val(postnr);
				  opener.jq('#tvlks').val(land);
				  opener.jq('#tvnas').focus();
				  
			  }else if(callerType == 'tvnak'){
				  opener.jq('#tvknk').val(knr);
				  opener.jq('#tvnak').val(knavn);
				  opener.jq('#tvtink').val(syrg);
				  opener.jq('#tvadk1').val(adr1);
				  opener.jq('#tvpsk').val(adr3);
				  opener.jq('#tvpnk').val(postnr);
				  opener.jq('#tvlkk').val(land);
				  opener.jq('#tvnak').focus();
			  //--------------------------------
			  //NCTS Export ITEMS	- SIKKERHET 
			  //--------------------------------  
			  }else if(callerType == 'tvnass'){
				  opener.jq('#tvknss').val(knr);
				  opener.jq('#tvnass').val(knavn);
				  opener.jq('#tvtinss').val(syrg);
				  opener.jq('#tvadss1').val(adr1);
				  opener.jq('#tvpsss').val(adr3);
				  opener.jq('#tvpnss').val(postnr);
				  opener.jq('#tvlkss').val(land);
				  opener.jq('#tvnass').focus();
				  
			  }else if(callerType == 'tvnaks'){
				  opener.jq('#tvknks').val(knr);
				  opener.jq('#tvnaks').val(knavn);
				  opener.jq('#tvtinks').val(syrg);
				  opener.jq('#tvadks1').val(adr1);
				  opener.jq('#tvpsks').val(adr3);
				  opener.jq('#tvpnks').val(postnr);
				  opener.jq('#tvlkks').val(land);
				  opener.jq('#tvnaks').focus();
					  
			  //=========================
			  //TVINN NCTS Import Module 
			  //=========================  
			  }else if(callerType == 'tina'){
				  opener.jq('#tikn').val(knr);
				  opener.jq('#tvnak').val(knavn);
				  opener.jq('#titin').val(syrg);
				  opener.jq('#tiad1').val(adr1);
				  opener.jq('#tips').val(adr3);
				  opener.jq('#tipn').val(postnr);
				  opener.jq('#tilk').val(land);
				  opener.jq('#tina').focus();
			  }
			  
			  //close child window
			  window.close();
		  });
	});
	
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGeneralCode () {
    		jq('#customerList').DataTable().search(
      		jq('#customerList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#customerList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.customerList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	