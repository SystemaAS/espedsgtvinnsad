	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq('#customerList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('_');
			 
			  var knr = record[0].replace("knr", "");
			  var knavn = record[1].replace("knavn", "");
			  var adr1 = record[2].replace("kadr1", "");
			  var adr3 = record[3].replace("kadr3", "");
			  var postnr = record[4].replace("kpostnr", "");
			  var land = record[5].replace("kland", "");
			  var eori = record[6].replace("keori", "");
			  var callerType = record[7].replace("ctype", "");
			  var syrg = record[8].replace("ksyrg", ""); 
			  var tlf = record[9].replace("ktlf", ""); 
			  var syepos = record[10].replace("ksyepos", ""); 	
			  //DEBUG -> alert(knr + " " + knavn + " " + callerType)
			  			  
			  
			  //=========================
			  //DIGITOLL -- TRANSPORT
			  //=========================  
			  if(callerType == 'etnat'){
				  opener.jq('#etknt').val(knr);
				  opener.jq('#etnat').val(knavn);refreshCustomValidity(opener.jq('#etnat')[0]);
				  opener.jq('#etrgt').val(syrg);refreshCustomValidity(opener.jq('#etrgt')[0]);
				  opener.jq('#etad1t').val(adr1);
				  opener.jq('#etpst').val(adr3);refreshCustomValidity(opener.jq('#etpst')[0]);
				  opener.jq('#etpnt').val(postnr);
				  opener.jq('#etlkt').val(land);refreshCustomValidity(opener.jq('#etlkt')[0]);
				  if('' != tlf){
					opener.jq('#own_etemt_telephone').val(tlf);	
				  }else{
					opener.jq('#own_etemt_email').val(syepos);
				  }	
				  opener.jq('#etnat').focus();	
			  }else if(callerType == 'etnar'){
				  opener.jq('#etknr').val(knr);
				  opener.jq('#etnar').val(knavn);refreshCustomValidity(opener.jq('#etnar')[0]);
				  opener.jq('#etrgr').val(syrg);refreshCustomValidity(opener.jq('#etrgr')[0]);
				  opener.jq('#etad1r').val(adr1);
				  opener.jq('#etpsr').val(adr3);refreshCustomValidity(opener.jq('#etpsr')[0]);
				  opener.jq('#etpnr').val(postnr);
				  opener.jq('#etlkr').val(land);refreshCustomValidity(opener.jq('#etlkr')[0]);
				  if('' != tlf){
					opener.jq('#own_etemr_telephone').val(tlf);	
				  }else{
					opener.jq('#own_etemr_email').val(syepos);
				  }	
		
				  opener.jq('#etnar').focus();
			  //=========================
			  //DIGITOLL -- MASTER
			  //========================= 
			  }else if(callerType == 'todo'){
				  opener.jq('#todo').val(syrg);
	 
			  }else if(callerType == 'emnas'){
				  opener.jq('#emkns').val(knr);
				  opener.jq('#emnas').val(knavn);
				  opener.jq('#emrgs').val(syrg);
				  opener.jq('#emad1s').val(adr1);
				  opener.jq('#empss').val(adr3);
				  opener.jq('#empns').val(postnr);
				  opener.jq('#emlks').val(land);
				  opener.jq('#emnas').focus();	
			  }else if(callerType == 'emnam'){
				  opener.jq('#emknm').val(knr);
				  opener.jq('#emnam').val(knavn);
				  opener.jq('#emrgm').val(syrg);
				  opener.jq('#emad1m').val(adr1);
				  opener.jq('#empsm').val(adr3);
				  opener.jq('#empnm').val(postnr);
				  opener.jq('#emlkm').val(land);
				  opener.jq('#emnar').focus();	
			  //=========================
			  //DIGITOLL -- HOUSE
			  //=========================  
			  }else if(callerType == 'ehnas'){
				  opener.jq('#ehkns').val(knr);
				  opener.jq('#ehnas').val(knavn);refreshCustomValidity(opener.jq('#ehnas')[0]);
				  opener.jq('#ehrgs').val(syrg);refreshCustomValidity(opener.jq('#ehrgs')[0]);
				  opener.jq('#ehad1s').val(adr1);
				  opener.jq('#ehpss').val(adr3);
				  opener.jq('#ehpns').val(postnr);
				  opener.jq('#ehlks').val(land);
				  opener.jq('#ehnas').focus();	
			  }else if(callerType == 'ehnam'){
				  opener.jq('#ehknm').val(knr);
				  opener.jq('#ehnam').val(knavn);refreshCustomValidity(opener.jq('#ehnam')[0]);
				  opener.jq('#ehrgm').val(syrg);refreshCustomValidity(opener.jq('#ehrgm')[0]);
				  opener.jq('#ehad1m').val(adr1);
				  opener.jq('#ehpsm').val(adr3);
				  opener.jq('#ehpnm').val(postnr);
				  opener.jq('#ehlkm').val(land);
				  opener.jq('#ehnar').focus();	
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
  	
  	
	