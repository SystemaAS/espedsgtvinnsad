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
			  var adr2 = record[8].replace("kadr2", "");
			  var syrg = record[9].replace("ksyrg", "");
			  //tollkredit
			  var wskta = record[10].replace("wskta", "");
			  var wsktb = record[11].replace("wsktb", "");
			  var wsktc = record[12].replace("wsktc", "");
			  //momsregistrert
			  var symvjn = record[13].replace("symvjn", "");
			  //addressing a parent field from this child window
			  //=========================
			  //TVINN SAD Export Module 
			  //=========================
			  if(callerType == 'senak'){
				  opener.jq('#seknk').val(knr);
				  opener.jq('#senak').val(knavn);
				  opener.jq('#serg').val(syrg);
				  opener.jq('#seadk1').val(adr1);
				  opener.jq('#seadk2').val(adr2);
				  opener.jq('#seadk3').val(adr3);
				  //tollkredit
				  opener.jq('#segkd').val(wsktc);
				  opener.jq('#sekta').val(wskta);
				  opener.jq('#sektb').val(wsktb);
				  opener.jq('#senak').focus();
				  
					 
			  }else if(callerType == 'senas'){
				  opener.jq('#sekns').val(knr);
				  opener.jq('#senas').val(knavn);
				  opener.jq('#seads1').val(adr1);
				  opener.jq('#seads2').val(adr2);
				  opener.jq('#seads3').val(adr3);
				  opener.jq('#senas').focus();
			  //=========================
			  //TVINN SAD Import Module 
			  //=========================  
			  }else if(callerType == 'sinas'){
				  opener.jq('#sikns').val(knr);
				  opener.jq('#sinas').val(knavn);
				  opener.jq('#siads1').val(adr1);
				  opener.jq('#siads2').val(adr2);
				  opener.jq('#siads3').val(adr3);
				  opener.jq('#sinas').focus();
				
				
			  }else if(callerType == 'sinak'){
				  opener.jq('#siknk').val(knr);
				  opener.jq('#sinak').val(knavn);
				  opener.jq('#sirg').val(syrg);
				  opener.jq('#simva').val(symvjn);
				  opener.jq('#siadk1').val(adr1);
				  opener.jq('#siadk2').val(adr2);
				  opener.jq('#siadk3').val(adr3);
				  //tollkredit
				  opener.jq('#siktc').val(wsktc);
				  opener.jq('#sikta').val(wskta);
				  opener.jq('#siktb').val(wsktb);
				  opener.jq('#sinak').focus();

			  //=========================
			  //TVINN SAD Admin Module 
			  //=========================  
			  }else if(callerType == 'avggcustid'){
				  opener.jq('#avggCustomerId').val(knr);
				  opener.jq('#avggCustomerName').val(knavn);
				  opener.jq('#avggCustomerId').focus();
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
  	
  	
	