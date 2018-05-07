	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq('#kundensVareRegList').on('click', 'td', function(){
			 
			  var id = this.id;
			  var record = id.split('@');
			  var varenr = record[0].replace("varenr", "");
			  var varebe = record[1].replace("varebe", "");
			  var tariffnr = record[2].replace("w2vnti", "");
			  var tollverdi = record[3].replace("w2belt", "");
			  var w2vktb = record[4].replace("w2vktb", "");
			  var w2vktn = record[5].replace("w2vktn", "");
			  //TilleggsOppl
			  var w2top1 = record[6].replace("w2top1", "");
			  var w2cre1 = record[7].replace("w2cre1", "");
			  var w2top2 = record[8].replace("w2top2", "");
			  var w2cre2 = record[9].replace("w2cre2", "");
			  var w2top3 = record[10].replace("w2top3", "");
			  var w2cre3 = record[11].replace("w2cre3", "");
			  var w2top4 = record[12].replace("w2top4", "");
			  var w2cre4 = record[13].replace("w2cre4", "");
			  var w2top5 = record[14].replace("w2top5", "");
			  var w2cre5 = record[15].replace("w2cre5", "");
			  var w2top6 = record[16].replace("w2top6", "");
			  var w2cre6 = record[17].replace("w2cre6", "");
			  var w2top7 = record[18].replace("w2top7", "");
			  var w2cre7 = record[19].replace("w2cre7", "");
			  var w2top8 = record[20].replace("w2top8", "");
			  var w2cre8 = record[21].replace("w2cre8", "");
			  var w2top9 = record[22].replace("w2top9", "");
			  var w2cre9 = record[23].replace("w2cre9", "");
			  var w2top10 = record[24].replace("w2top10", "");
			  var w2cre10 = record[25].replace("w2cre10", "");
			  //Avgifter
			  var w2akd1 = record[26].replace("w2akd1", "");
			  var w2asv1 = record[27].replace("w2asv1", "");
			  var w2asa1 = record[28].replace("w2asa1", "");
			  var w2agr1 = record[29].replace("w2agr1", "");
			  var w2abl1 = record[30].replace("w2abl1", "");
			  //
			  var w2akd2 = record[31].replace("w2akd2", "");
			  var w2asv2 = record[32].replace("w2asv2", "");
			  var w2asa2 = record[33].replace("w2asa2", "");
			  var w2agr2 = record[34].replace("w2agr2", "");
			  var w2abl2 = record[35].replace("w2abl2", "");
			  //
			  var w2akd3 = record[36].replace("w2akd3", "");
			  var w2asv3 = record[37].replace("w2asv3", "");
			  var w2asa3 = record[38].replace("w2asa3", "");
			  var w2agr3 = record[39].replace("w2agr3", "");
			  var w2abl3 = record[40].replace("w2abl3", "");
			  //
			  var w2akd4 = record[41].replace("w2akd4", "");
			  var w2asv4 = record[42].replace("w2asv4", "");
			  var w2asa4 = record[43].replace("w2asa4", "");
			  var w2agr4 = record[44].replace("w2agr4", "");
			  var w2abl4 = record[45].replace("w2abl4", "");
			  //
			  var w2akd5 = record[46].replace("w2akd5", "");
			  var w2asv5 = record[47].replace("w2asv5", "");
			  var w2asa5 = record[48].replace("w2asa5", "");
			  var w2agr5 = record[49].replace("w2agr5", "");
			  var w2abl5 = record[50].replace("w2abl5", "");
			  //
			  var w2akd6 = record[51].replace("w2akd6", "");
			  var w2asv6 = record[52].replace("w2asv6", "");
			  var w2asa6 = record[53].replace("w2asa6", "");
			  var w2agr6 = record[54].replace("w2agr6", "");
			  var w2abl6 = record[55].replace("w2abl6", "");
			  //
			  var w2akd7 = record[56].replace("w2akd7", "");
			  var w2asv7 = record[57].replace("w2asv7", "");
			  var w2asa7 = record[58].replace("w2asa7", "");
			  var w2agr7 = record[59].replace("w2agr7", "");
			  var w2abl7 = record[60].replace("w2abl7", "");
			  //
			  var w2akd8 = record[61].replace("w2akd8", "");
			  var w2asv8 = record[62].replace("w2asv8", "");
			  var w2asa8 = record[63].replace("w2asa8", "");
			  var w2agr8 = record[64].replace("w2agr8", "");
			  var w2abl8 = record[65].replace("w2abl8", "");
			  //
			  //Varebeskrivelse matrix
			  var w2ft01 = record[66].replace("w2ft01", "");
			  var w2nt01 = record[67].replace("w2nt01", "");
			  var w2eh01 = record[68].replace("w2eh01", "");
			  var w2vt01 = record[69].replace("w2vt01", "");
			  var w2ft02 = record[70].replace("w2ft02", "");
			  var w2nt02 = record[71].replace("w2nt02", "");
			  var w2eh02 = record[72].replace("w2eh02", "");
			  var w2vt02 = record[73].replace("w2vt02", "");
			  var w2ft03 = record[74].replace("w2ft03", "");
			  var w2nt03 = record[75].replace("w2nt03", "");
			  var w2eh03 = record[76].replace("w2eh03", "");
			  var w2vt03 = record[77].replace("w2vt03", "");
			  var w2ft04 = record[78].replace("w2ft04", "");
			  var w2nt04 = record[79].replace("w2nt04", "");
			  var w2eh04 = record[80].replace("w2eh04", "");
			  var w2vt04 = record[81].replace("w2vt04", "");
			  var w2ft05 = record[82].replace("w2ft05", "");
			  var w2nt05 = record[83].replace("w2nt05", "");
			  var w2eh05 = record[84].replace("w2eh05", "");
			  var w2vt05 = record[85].replace("w2vt05", "");
			  var w2ft06 = record[86].replace("w2ft06", "");
			  var w2nt06 = record[87].replace("w2nt06", "");
			  var w2eh06 = record[88].replace("w2eh06", "");
			  var w2ft07 = record[89].replace("w2ft07", "");
			  var w2nt07 = record[90].replace("w2nt07", "");
			  var w2eh07 = record[91].replace("w2eh07", "");
			  
			  opener.jq('#svvnt').val(tariffnr);
			  opener.jq('#wd1').val(varebe);
			  opener.jq('#svbelt').val(tollverdi);
			  opener.jq('#svvktb').val(w2vktb);
			  opener.jq('#svvktn').val(w2vktn);
			  //Varebesk.matrix
			  opener.jq('#wd1').val(jq.trim(w2vt01));
			  opener.jq('#wa1').val(jq.trim(w2ft01));
			  opener.jq('#wb1').val(jq.trim(w2nt01));
			  opener.jq('#wc1').val(jq.trim(w2eh01));
			  opener.jq('#wd2').val(jq.trim(w2vt02));
			  opener.jq('#wa2').val(jq.trim(w2ft02));
			  opener.jq('#wb2').val(jq.trim(w2nt02));
			  opener.jq('#wc2').val(jq.trim(w2eh02));
			  opener.jq('#wd3').val(jq.trim(w2vt03));
			  opener.jq('#wa3').val(jq.trim(w2ft03));
			  opener.jq('#wb3').val(jq.trim(w2nt03));
			  opener.jq('#wc3').val(jq.trim(w2eh03));
			  opener.jq('#wd4').val(jq.trim(w2vt04));
			  opener.jq('#wa4').val(jq.trim(w2ft04));
			  opener.jq('#wb4').val(jq.trim(w2nt04));
			  opener.jq('#wc4').val(jq.trim(w2eh04));
			  opener.jq('#wd5').val(jq.trim(w2vt05));
			  opener.jq('#wa5').val(jq.trim(w2ft05));
			  opener.jq('#wb5').val(jq.trim(w2nt05));
			  opener.jq('#wc5').val(jq.trim(w2eh05));
			  //alert("w2eh06:"+ w2eh06 + "X");
			  //alert("w2eh07:"+ w2eh07 + "X");
			  opener.jq('#wa6').val(jq.trim(w2ft06));
			  opener.jq('#wb6').val(jq.trim(w2nt06));
			  opener.jq('#wc6').val(jq.trim(w2eh06));
			  opener.jq('#wa7').val(jq.trim(w2ft07));
			  opener.jq('#wb7').val(jq.trim(w2nt07));
			  opener.jq('#wc7').val(jq.trim(w2eh07));
			  
			  //TilleggsOpp
			  opener.jq('#wf1').val(jq.trim(w2top1));
			  opener.jq('#we1').val(jq.trim(w2cre1));
			  opener.jq('#wf2').val(jq.trim(w2top2));
			  opener.jq('#we2').val(jq.trim(w2cre2));
			  opener.jq('#wf3').val(jq.trim(w2top3));
			  opener.jq('#we3').val(jq.trim(w2cre3));
			  opener.jq('#wf4').val(jq.trim(w2top4));
			  opener.jq('#we4').val(jq.trim(w2cre4));
			  opener.jq('#wf5').val(jq.trim(w2top5));
			  opener.jq('#we5').val(jq.trim(w2cre5));
			  opener.jq('#wf6').val(jq.trim(w2top6));
			  opener.jq('#we6').val(jq.trim(w2cre6));
			  opener.jq('#wf7').val(jq.trim(w2top7));
			  opener.jq('#we7').val(jq.trim(w2cre7));
			  opener.jq('#wf8').val(jq.trim(w2top8));
			  opener.jq('#we8').val(jq.trim(w2cre8));
			  opener.jq('#wf9').val(jq.trim(w2top9));
			  opener.jq('#we9').val(jq.trim(w2cre9));
			  opener.jq('#wf10').val(jq.trim(w2top10));
			  opener.jq('#we10').val(jq.trim(w2cre10));
			  
			  //Avgifter
			  opener.jq('#wg1').val(jq.trim(w2akd1));
			  opener.jq('#wh1').val(jq.trim(w2asv1));
			  opener.jq('#wk1').val(jq.trim(w2asa1));
			  opener.jq('#wj1').val(jq.trim(w2agr1));
			  opener.jq('#wi1').val(jq.trim(w2abl1));
			  opener.jq('#wg2').val(jq.trim(w2akd2));
			  opener.jq('#wh2').val(jq.trim(w2asv2));
			  opener.jq('#wk2').val(jq.trim(w2asa2));
			  opener.jq('#wj2').val(jq.trim(w2agr2));
			  opener.jq('#wi2').val(jq.trim(w2abl2));
			  opener.jq('#wg3').val(jq.trim(w2akd3));
			  opener.jq('#wh3').val(jq.trim(w2asv3));
			  opener.jq('#wk3').val(jq.trim(w2asa3));
			  opener.jq('#wj3').val(jq.trim(w2agr3));
			  opener.jq('#wi3').val(jq.trim(w2abl3));
			  opener.jq('#wg4').val(jq.trim(w2akd4));
			  opener.jq('#wh4').val(jq.trim(w2asv4));
			  opener.jq('#wk4').val(jq.trim(w2asa4));
			  opener.jq('#wj4').val(jq.trim(w2agr4));
			  opener.jq('#wi4').val(jq.trim(w2abl4));
			  opener.jq('#wg5').val(jq.trim(w2akd5));
			  opener.jq('#wh5').val(jq.trim(w2asv5));
			  opener.jq('#wk5').val(jq.trim(w2asa5));
			  opener.jq('#wj5').val(jq.trim(w2agr5));
			  opener.jq('#wi5').val(jq.trim(w2abl5));
			  opener.jq('#wg6').val(jq.trim(w2akd6));
			  opener.jq('#wh6').val(jq.trim(w2asv6));
			  opener.jq('#wk6').val(jq.trim(w2asa6));
			  opener.jq('#wj6').val(jq.trim(w2agr6));
			  opener.jq('#wi6').val(jq.trim(w2abl6));
			  opener.jq('#wg7').val(jq.trim(w2akd7));
			  opener.jq('#wh7').val(jq.trim(w2asv7));
			  opener.jq('#wk7').val(jq.trim(w2asa7));
			  opener.jq('#wj7').val(jq.trim(w2agr7));
			  opener.jq('#wi7').val(jq.trim(w2abl7));
			  opener.jq('#wg8').val(jq.trim(w2akd8));
			  opener.jq('#wh8').val(jq.trim(w2asv8));
			  opener.jq('#wk8').val(jq.trim(w2asa8));
			  opener.jq('#wj8').val(jq.trim(w2agr8));
			  opener.jq('#wi8').val(jq.trim(w2abl8));
			  
			  
			  //focus on	
			  opener.jq('#svvnt').focus();
			  
			  //close child window
			  window.close();
		  });
	});
	
	jq(function() {
		jq('#vkod').blur(function(){
			if(jq('#vkod').val() != ""){
				jq('#tekst').val("");
			}
		});
		jq('#tekst').blur(function(){
			if(jq('#tekst').val() != ""){
				jq('#vkod').val("");
			}
		});
	});
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGeneralCode () {
    		jq('#kundensVareRegList').DataTable().search(
      		jq('#kundensVareRegList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#kundensVareRegList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.kundensVareRegList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	