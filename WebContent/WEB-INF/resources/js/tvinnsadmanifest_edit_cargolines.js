  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  //Overlay on tab (to mark visually a delay...)
  jq(function() {
	jq('#alinkManifestList').click(function() { 
    	setBlockUI();
    });
    jq('#alinkHeader').click(function() { 
    	setBlockUI();
    });
    jq('#alinkItems').click(function() { 
    	setBlockUI();
    });
    
    //General Header Menus
    jq('#alinkTopicListMenuImp').click(function() { 
    	setBlockUI();
    });
    jq('#alinkTopicListMenuExp').click(function() { 
    	setBlockUI();
    });
    jq('#alinkTopicListMenuNctsExp').click(function() { 
    	setBlockUI();
    });
    jq('#alinkTopicListMenuNctsImp').click(function() { 
    	setBlockUI();
    });
    jq('#alinkTopicListMenuManifest').click(function() { 
    	setBlockUI();
    });
    jq('#cargoLineForm').submit(function() { 
    	setBlockUI();
    });
    
    jq('#cletyp').change(function() {
    	jq('#cletypt').val(jq(this).find("option:selected").attr("title"));
     });
    
    jq('#clpr').change(function() {
    	if(jq('#clpr').val() == "01"){
    		unblockImmediateReleaseFields();
    		blockTransitFields();
    	}else if (jq('#clpr').val() == "02"){
    		blockImmediateReleaseFields();
    		unblockTransitFields();
    	}else{
    		unblockImmediateReleaseFields();
    		unblockTransitFields();
    	}
     });
  });
  
  function blockTransitFields(){
	  jq(".toggleTransit").prop("disabled", true);
	  jq('#tblTransit').removeClass('tableBorderWithRoundCorners');
	  jq('#tblTransit').addClass('tableBorderWithRoundCornersLightGray');
	  
	  jq('#cltrnr').val("");
	  jq('#cltrnr').removeClass('inputTextMediumBlue');
	  jq('#cltrnr').addClass('inputTextReadOnly');
	  jq('#cltrnr').attr('readonly','readonly');
	  
	  jq('#clnas').val("");
	  jq('#clnas').removeClass('inputTextMediumBlue');
	  jq('#clnas').addClass('inputTextReadOnly');
	  jq('#clnas').attr('readonly','readonly');
	  
	  jq('#clnak').val("");
	  jq('#clnak').removeClass('inputTextMediumBlue');
	  jq('#clnak').addClass('inputTextReadOnly');
	  jq('#clnak').attr('readonly','readonly');
  }
  function unblockTransitFields(){
	  jq(".toggleTransit").prop("disabled", false);
	  jq('#tblTransit').removeClass('tableBorderWithRoundCornersLightGray');
	  jq('#tblTransit').addClass('tableBorderWithRoundCorners');
	  
	  jq('#cltrnr').removeClass('inputTextReadOnly');
	  jq('#cltrnr').removeAttr('readonly');
	  jq('#cltrnr').addClass('inputTextMediumBlue'); 
	  
	  jq('#clnas').removeClass('inputTextReadOnly');
	  jq('#clnas').removeAttr('readonly');
	  jq('#clnas').addClass('inputTextMediumBlue');
	  
	  jq('#clnak').removeClass('inputTextReadOnly');
	  jq('#clnak').removeAttr('readonly');
	  jq('#clnak').addClass('inputTextMediumBlue');
  }
  
  function blockImmediateReleaseFields(){
	  jq(".toggleDirektfortolling").prop("disabled", true);
	  jq('#tblDirektfortolling').removeClass('tableBorderWithRoundCorners');
	  jq('#tblDirektfortolling').addClass('tableBorderWithRoundCornersLightGray');
	  
	  jq('#clrg').val("");
	  jq('#clrg').removeClass('inputTextMediumBlue');
	  jq('#clrg').addClass('inputTextReadOnly');
	  jq('#clrg').attr('readonly','readonly');
	  
	  jq('#cl0068a').val("");
	  jq('#cl0068a').removeClass('inputTextMediumBlue');
	  jq('#cl0068a').addClass('inputTextReadOnly');
	  jq('#cl0068a').attr('readonly','readonly');
	  jq("#cl0068a" ).datepicker( "option", "disabled", true );
	  
	  jq('#cl0068b').val("");
	  jq('#cl0068b').removeClass('inputTextMediumBlue');
	  jq('#cl0068b').addClass('inputTextReadOnly');
	  jq('#cl0068b').attr('readonly','readonly');
	  
	  jq('#cletyp').val("");
	  jq("#cletyp").prop("disabled", true);
	  jq('#cletyp').removeClass('inputTextMediumBlue');
	  jq('#cletyp').addClass('inputTextReadOnly');
	  jq('#cletyp').attr('readonly','readonly');
	  
	  jq('#cleid').val("");
	  jq('#cleid').removeClass('inputTextMediumBlue');
	  jq('#cleid').addClass('inputTextReadOnly');
	  jq('#cleid').attr('readonly','readonly');
	  
	  
	  
  }
  function unblockImmediateReleaseFields(){
	  jq(".toggleDirektfortolling").prop("disabled", false);
	  jq('#tblDirektfortolling').removeClass('tableBorderWithRoundCornersLightGray');
	  jq('#tblDirektfortolling').addClass('tableBorderWithRoundCorners');
	  
	  jq('#clrg').removeClass('inputTextReadOnly');
	  jq('#clrg').addClass('inputTextMediumBlue');
	  jq('#clrg').removeAttr('readonly');
	  
	  jq('#cl0068a').removeClass('inputTextReadOnly');
	  jq('#cl0068a').addClass('inputTextMediumBlue');
	  jq('#cl0068a').removeAttr('readonly');
	  jq("#cl0068a" ).datepicker( "option", "disabled", false );
	  
	  jq('#cl0068b').removeClass('inputTextReadOnly');
	  jq('#cl0068b').addClass('inputTextMediumBlue');
	  jq('#cl0068b').removeAttr('readonly');
	  
	  jq("#cletyp").prop("disabled", false);
	  jq('#cletyp').removeClass('inputTextReadOnly');
	  jq('#cletyp').addClass('inputTextMediumBlue');
	  jq('#cletyp').removeAttr('readonly');
	  
	  jq('#cleid').removeClass('inputTextReadOnly');
	  jq('#cleid').addClass('inputTextMediumBlue');
	  jq('#cleid').removeAttr('readonly');
	  
	  
	  
  }
  //refresh CargoLines
  function refreshCargoLines(){
	  setBlockUI();
	  window.location = "tvinnsadmanifest_edit_cargolines.do?action=doFetch&efpro=" + jq('#efpro').val() + "&efsg=" + jq('#efsg').val() + "&efavd=" + jq('#efavd').val() + "&efuuid=" + jq('#efuuid').val();
  }
  
  //used and called only from a childwindow in order to reload this page with BlockUI() ...
  function callParent(){
	  refreshCargoLines(); 
  }
  
  jq(function() {
	  jq("#cl0068a").datepicker({ 
		  dateFormat: 'ddmmy' 	  
	  });
	  
	//CHILD-WINDOWS
	//Postnr/sted FROM
	  jq('#clsdfIdLink').click(function() {
	  	jq('#clsdfIdLink').attr('target','_blank');
	  	window.open('tvinnsadmanifest_childwindow_postalcodes_sted2.do?action=doFind&ctype=cllkf&direction=fra&st2lk=' + jq('#cllkf').val() + '&st2kod=', "postalcodeSted2Win", "top=300px,left=450px,height=600px,width=700px,scrollbars=no,status=no,location=no");
	  });
	  jq('#clsdfIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#clsdfIdLink').click();
			}
	  });
	//Postnr/sted TO
	  jq('#clsdtIdLink').click(function() {
	  	jq('#clsdtIdLink').attr('target','_blank');
	  	window.open('tvinnsadmanifest_childwindow_postalcodes_sted2.do?action=doFind&ctype=cllkt&direction=fra&st2lk=' + jq('#cllkt').val() + '&st2kod=', "postalcodeSted2Win", "top=300px,left=450px,height=600px,width=700px,scrollbars=no,status=no,location=no");
	  });
	  jq('#clsdtIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#clsdtIdLink').click();
			}
	  });
	  
	  //Plocka oppdrag
	  jq('#cnButton').click(function() {
	  	jq('#cnButton').attr('target','_blank');
	  	window.open('tvinnsadmanifest_childwindow_released_cargolines.do?action=doInit&ctype=cnButton&clpro=' + jq('#efpro').val(), "releasedCargolinesWin", "top=300px,left=450px,height=600px,width=700px,scrollbars=no,status=no,location=no");
	  });
	  /*jq('#cnButton').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#cnButton').click();
			}
	  });*/
	  
	  jq('#newButton').click(function() {
		  refreshCargoLines();
	  }); 
  });
  
  
//Initialize <div> here for all clazz_dialog
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			maxWidth:500,
			maxHeight: 400,
			width: 500,
			height: 280,
			modal: true
		});
	  });
  });
  //----------------------------------------------------------------
  //START Model dialog: "Delete cargo line" (implicit "Update status")
  //----------------------------------------------------------------
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".removeLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("removeLink","");
		  
		  jq('#dialogUpdateStatus'+counterIndex).dialog( "option", "title", "Slette oppdrag " + jq('#currentClpro'+counterIndex).val() );
		  //deal with buttons for this modal window
		  jq('#dialogUpdateStatus'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU"+counterIndex,	
				 text: "Ok",
				 click: function(){
					 		jq('#updateStatusForm'+counterIndex).submit();
					 		jq( this ).dialog( "close" );
					 		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU"+counterIndex,
			 	 text: "Cancel", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		//jq("#dialogSaveSU"+counterIndex).button("option", "disabled", true);
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  //jq("#dialogSaveSU"+counterIndex).button("option", "disabled", true);
		  //open now
		  jq('#dialogUpdateStatus'+counterIndex).dialog('open');
		 
	  });
  });
  
  
  
  
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
	 
	//jq.fn.dataTable.moment( 'DDMMYY' );    
    //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
    jq('#mainList').dataTable( {
  	  //"dom": '<"top"f>t<"bottom"><"clear">',
      "searchHighlight": true,	
  	  "dom": '<"top"f>t<"bottom"><"clear">',
  	  "scrollY": "300px",
  	  "scrollCollapse":  true,
	  "tabIndex": -1,
	  "order": [[ 2, "asc" ]], 
	  "lengthMenu": [ 25, 50, 100],
	  "fnDrawCallback": function( oSettings ) {
    	jq('.dataTables_filter input').addClass("inputText12LightYellow");
    	}
    });
    //event on input field for search
    jq('input.mainList_filter').on( 'keyup click', function () {
    		filterGlobal();
    });
    
  });
  
  
  function getItemData(record) {
	  	var applicationUserParam = jq('#applicationUser').val();
	  	var htmlValue = record.id;
	  	//alert(applicationUserParam + ' ' + htmlValue);
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getSpecificCargoLine_TvinnSadManifest.do',
	  	  data: { applicationUser : applicationUserParam, 
	  		  	  htmlParams : htmlValue },
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				jq('#clpro').val(""); jq('#clpro').val(data[i].clpro);
				jq('#clavd').val(""); jq('#clavd').val(data[i].clavd);
				jq('#cltdn').val(""); jq('#cltdn').val(data[i].cltdn);
				//The status is always in the html = O --->jq('#clst').val(""); jq('#clst').val(data[i].clst);
				
				jq('#clvt').val(""); jq('#clvt').val(data[i].clvt);
				jq('#cl0068a').val(""); jq('#cl0068a').val(data[i].cl0068a);
				jq('#cl0068b').val(""); jq('#cl0068b').val(data[i].cl0068b);
				jq('#clntk').val(""); jq('#clntk').val(data[i].clntk);
				jq('#clvkb').val(""); jq('#clvkb').val(data[i].clvkb);
				jq('#clrg').val(""); jq('#clrg').val(data[i].clrg);
				if(jq('#clrg').val()!=''){
					unblockImmediateReleaseFields();
		    		blockTransitFields();
				}
				//transit
				jq('#cltrnr').val(""); jq('#cltrnr').val(data[i].cltrnr);
				jq('#clnas').val(""); jq('#clnas').val(data[i].clnas);
				jq('#clnak').val(""); jq('#clnak').val(data[i].clnak);
				if(jq('#cltrnr').val()!=''){
					blockImmediateReleaseFields();
					unblockTransitFields();
				}
				
				//Loading
				jq('#cllkf').val(""); jq('#cllkf').val(data[i].cllkf);
				jq('#clsdf').val(""); jq('#clsdf').val(data[i].clsdf);
				jq('#clsdft').val(""); jq('#clsdft').val(data[i].clsdft);
				//Unloading
				jq('#cllkt').val(""); jq('#cllkt').val(data[i].cllkt);
				jq('#clsdt').val(""); jq('#clsdt').val(data[i].clsdt);
				jq('#clsdtt').val(""); jq('#clsdtt').val(data[i].clsdtt);
				jq('#clpr').val(""); jq('#clpr').val(data[i].clpr);
				//Export 
				jq('#cletyp').val(""); jq('#cletyp').val(data[i].cletyp);
				jq('#cletypt').val(""); jq('#cletypt').val(data[i].cletypt);
				jq('#cleid').val(""); jq('#cleid').val(data[i].cleid);
				jq('#cleser').val(""); jq('#cleser').val(data[i].cleser);
				
				//-----------------------------
  				//START populate ArchDocs list
  				//-----------------------------
  				var docslen = data[i].getdocs.length;
  				jq('#resultUploadedDocs').text("");
  				
  				var table = jq('<table></table>').addClass('foo');
  				var row = jq('<tr></tr>').addClass('tableHeaderField');
  				
  				var td_1 = jq('<th align="left"></th>').addClass('text14').text('Dok.type');
  				row.append(td_1);
  				var td_2 = jq('<th align="left"></th>').addClass('text14').text('Dok.navn');
  				row.append(td_2);
  				var td_3 = jq('<th align="left"></th>').addClass('text14').text('Dok.fil');
  				row.append(td_3);
  				var td_4 = jq('<th align="left"></th>').addClass('text14').text('Dato/kl');
  				row.append(td_4);
  				//TABLE APPEND row
  				table.append(row);
  				
  				//fill in table
  				for ( var j = 0; j < docslen; j++) {
  					/*
  					var documentText = data[i].getdocs[j].doctxt;
  					if(documentText==''){
  						documentText = data[i].getdocs[j].doclnk;
  					}*/
  					
  					var row = jq('<tr></tr>').addClass('tableRow');
	  				var td_1 = jq('<td ></td>').addClass('tableCellFirst');td_1.css('white-space','nowrap');
	  				var td_2 = jq('<td></td>').addClass('tableCell');td_2.css('white-space','nowrap');td_2.css('color','darkgray');
	  				var td_3 = jq('<td></td>').addClass('tableCell');td_3.css('white-space','nowrap');
	  				var td_4 = jq('<td></td>').addClass('tableCell');td_4.css('white-space','nowrap');
	  				
  					if(data[i].getdocs[j].doclnk.indexOf(".pdf")>=0 ||data[i].getdocs[j].doclnk.indexOf(".PDF")>=0){
			  			imgSrc="resources/images/pdf.png";
  					}else{
  						imgSrc="resources/images/jpg.png";
  					}
			
					//Archive
					var doclnkHref = data[i].getdocs[j].doclnk;
					var docText = doclnkHref;
					//console.log("A-######### doclnkHref:" + doclnkHref + "-->indexOf:" + doclnkHref.indexOf("http"));
					if(doclnkHref.indexOf("http")>=0 ){
						//Google Archive
						//console.log("B-######### doclnkHref:" + doclnkHref);
						var tmpIndex = doclnkHref.indexOf("&filename=");
						if (tmpIndex>=0){
							docText = doclnkHref.substring(tmpIndex);
							docText = docText.replace("&filename=", ".../");
							console.log("B-######### docText:" + docText);
						}
					}else{
						//To local Spring Controller (PDF on local machine)
						doclnkHref = 'tvinnsadmanifest_renderArchive.do?doclnk='+data[i].getdocs[j].doclnk;
						console.log("C-######### doclnkHref:" + doclnkHref);
					}
  					
  					//ROW APPEND TD_1 
  					td_1.text(data[i].getdocs[j].doctyp);
  					row.append(td_1);
  					
  					//ROW APPEND TD_2 
  					td_2.text(data[i].getdocs[j].doctxt);
  					row.append(td_2);
  					
  					//ROW APPEND TD_3 
  					jq('<img/>',{
  						src: imgSrc,
  						width: '14px',
  						height: '14px'
		  			  	}).appendTo(td_3);
  					
					
  					jq('<a>',{
		  			    text: docText,
		  			    target: '_blank',
		  			    href: doclnkHref,
		  			    click: function(){ BlahFunc( options.rowId );return false;}
  					}).appendTo(td_3);
  					row.append(td_3);
  					
  					//ROW APPEND TD_4 
  					td_4.text(data[i].getdocs[j].docdat + " " + data[i].getdocs[j].doctim);
  					row.append(td_4);
  					//
  					table.append(row);
			  	}
  				var row_last = jq('<tr></tr>').addClass('tableRow');
  				//----------------------------------------------------------------------------------------------------
  				//show button ONLY with "Direktefortolling". "Transiteringen" does not allow sending of docs via API.
  				//----------------------------------------------------------------------------------------------------
  				if(jq('#clrg').val()!=''){
	  				var sendButton = jq('<input/>'). attr({ type: 'button', name:'sbutton', id:'sbutton', value:'Send til toll.no' });
	  				jq(document).on("click", "#sbutton", function(){
	  				  //childwindow
	  				  openUploadFileToToll();
	  				});
	  				row_last.append(sendButton);
  				}
  				
  				
  				table.append(row_last);
  				
  				//append TABLE to DIV
  				jq('#resultUploadedDocs').append(table);
  				//--------------
  				//END ArchDocs
  				//--------------
  				
				
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
	  	
	}
  
  
  jq(function() {
		jq('#sttButton').click(function() { 
			if(jq('#clavd').val()!='' && jq('#cltdn').val()!=''){
				openUploadFileToToll();
			}
	    });
  });
  
  function openUploadFileToToll(){
	  window.open('tvinnsadmanifest_childwindow_uploadfile_to_toll.do?action=doInit&wsavd=' + jq('#clavd').val() + '&wsopd=' + jq('#cltdn').val() + '&clrg=' + jq('#clrg').val() + '&cl0068a=' + jq('#cl0068a').val() + '&cl0068b=' + jq('#cl0068b').val(), "releasedCargolinesWin", "top=300px,left=450px,height=450px,width=1000px,scrollbars=yes,status=no,location=no");
  }
  
	
  
 
  
 
