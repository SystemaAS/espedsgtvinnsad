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
			  var tlf = record[9].replace("ktlf", ""); 
			  var syepos = record[10].replace("ksyepos", ""); 	
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
		
			  //=========================
			  //DIGITOLL -- TRANSPORT
			  //=========================  
			  }else if(callerType == 'etnat'){
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
			  
			  //========================================
			  //Digitoll MasterId external houses header
			  //========================================
			  }else if(callerType == 'name'){
				  opener.jq('#etknt').val(knr);
				  opener.jq('#name').val(knavn);
				  opener.jq('#orgnr').val(syrg);
				  opener.jq('#orgnr').focus();
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
  	
  	
	