<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>			
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadimport_edit_items.js?ver=${user.versionEspedsg}"></SCRIPT>
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	
	.ui-datepicker { font-size:9pt;}
	
	.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
	.ui-timepicker-div dl { text-align: left; }
	.ui-timepicker-div dl dt { float: left; clear:left; padding: 0 0 0 5px; }
	.ui-timepicker-div dl dd { margin: 0 10px 10px 40%; }
	.ui-timepicker-div td { font-size: 90%; }
	.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
	
	.ui-timepicker-rtl{ direction: rtl; }
	.ui-timepicker-rtl dl { text-align: right; padding: 0 5px 0 0; }
	.ui-timepicker-rtl dl dt{ float: right; clear: right; }
	.ui-timepicker-rtl dl dd { margin: 0 40% 10px 10px; }
	
	</style>
	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2">
			<td>
				<input type="hidden" name="modelStatus" id="modelStatus" value='${model.status}'>
			</td>
		</tr>
		<tr height="25"> 
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkTopicList" style="display:block;" href="tvinnsadimport.do?action=doFind&sg=${model.sign}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.import.list.tab"/></font>
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkHeader" style="display:block;" href="tvinnsadimport_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
						&sysg=${model.sign}&tuid=${refnr}&syst=${model.status}&sydt=${model.datum}&o2_sist=${ model.o2_sist}&o2_sidt=${ model.o2_sidt}&o2_simf=${ model.o2_simf}">
					
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.import.created.mastertopic.tab"/></font>
					<font class="text12MediumBlue">[${model.opd}]</font>
					<c:if test="${model.status == 'M' || empty model.status}">
						<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkInvoices" style="display:block;" href="tvinnsadimport_edit_finansopplysninger.do?action=doFetch&avd=${ model.avd}&sign=${ model.sign}
											&opd=${ model.opd}&status=${ model.status}&fabl=${recordTopicTvinnSad.sibel3}&o2_sist=${ model.o2_sist}&o2_sidt=${ model.o2_sidt}&o2_simf=${ model.o2_simf}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.import.finansopplys.createnew.tab"/>
					</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkNotis" style="display:block;" href="editNotisblock.do?action=doFetch&subsys=sadi&orig=topic&avd=${ model.avd}&sign=${ model.sign}
											&opd=${ model.opd}&o2_sist=${ model.o2_sist}&o2_sidt=${ model.o2_sidt}&o2_simf=${ model.o2_simf}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.import.notisblock.createnew.tab"/>
					</font>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.import.item.createnew.tab"/></font>
				<c:if test="${model.status == 'M' || empty model.status || model.status == '10' || model.status == '20'}">
					<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
				</c:if>
				
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkLogging" style="display:block;" href="tvinnsadimport_logging.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}
													&status=${model.status}&datum=${model.datum}&o2_sist=${ model.o2_sist}&o2_sidt=${ model.o2_sidt}&o2_simf=${ model.o2_simf}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.import.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkArchive" style="display:block;" href="tvinnsadimport_archive.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}
													&status=${model.status}&datum=${model.datum}&o2_sist=${ model.o2_sist}&o2_sidt=${ model.o2_sidt}&o2_simf=${ model.o2_simf}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.import.archive.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
				</a>
			</td>
			<%-- This tab should be hidden here ?
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkOmberegning" style="display:block;" href="tvinnsadimport_edit_omberegning.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
											&opd=${ model.opd}&status=${model.status}&o2_sist=${ model.o2_sist}&o2_sidt=${ model.o2_sidt}&o2_simf=${ model.o2_simf}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.import.omberegning.mastertopic.tab"/>
					</font>
				</a>
			</td>
			--%>
			<td width="20%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
		</tr>
	</table>
	<%-- -------------------------------- --%>	
 	<%-- tab area container MASTER TOPIC  --%>
	<%-- -------------------------------- --%>
 	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
		<tr height="15"><td colspan="2">&nbsp;</td></tr>	
		
		<tr>
		<td >
		<table border="0" width="95%" align="center">
			<tr>
	 			<td >		
	 				<%-- MASTER Topic header --%>
	 				<table width="100%" align="center" class="formFrameHeaderTransparent" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12MediumBlue">
				 				&nbsp;Avd&nbsp;<b>${model.avd}</b>
				 				&nbsp;Tolldeknr.&nbsp;<b>${model.opd}</b>
				 				&nbsp;Sign&nbsp;<b>${model.sign}</b>
				 				&nbsp;&nbsp;
				 				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				Status:&nbsp;<b>${model.status}</b>
				 				&nbsp;&nbsp;Dekl.:&nbsp;<b>${recordTopicTvinnSad.sidty}</b>
				 				
				 				<span style="position:absolute; left:850px; top:100px; width:250px; height:550px;" id="status_info" class="popupWithInputText"  >
					           		<div class="text11" align="left">
					           		<br/>
					           		 Bare status <b>M</b> (Fejl) eller <b>' '</b> kan redigeres.
				           		 	<ul>
					           			<li><b>' '</b>&nbsp;Deklarasjonen er åpen for endring.
				           				<li><b>+</b>&nbsp;Systemet lager nu utgående EDIFACT melding for å kunne sende deklarasjonen..</li>
				           				<li><b>A</b>&nbsp;Deklarasjonen ligger i en sending i påvente av å bli sendt.</li>
				           				<li><b>C</b>&nbsp;Sendingen er videresendt til TVINN.</li>
				           				<li><b>D</b>&nbsp;Melding om dokumentkontroll er mottatt fra Tollvesenet.</li>
				           				<li><b>E</b>&nbsp;Deklarasjonen blir endret av en saksbehandler.</li>
				           				<li><b>F</b>&nbsp;Edifacttekniske fejl oppdaget.</li>
				           				<li><b>K</b>&nbsp;Deklarasjonen er klar for utskrift men er ikke skrevet ut.</li>
				           				<li><b>L</b>&nbsp;Deklarasjonen er klar for utskrift men er ikke skrevet ut.</li>	
				           				<li><b>M</b>&nbsp;Tollteknisk fejl</li>
				           				<li><b>P</b>&nbsp;Deklarasjonen er skrevet ut. Hvis den er sendt på TVINN vil denne koden bety at tollkvittering er mottatt fra Tollvesenet og skrevet ut.</li>
				           				<li><b>Q</b>&nbsp;Deklarasjonen ligger i utgående postkasse for TVINN. men er ikke sendt.</li>
				           				<li><b>T</b>&nbsp;Informasjonsmelding om at deklarasjonen er lagt til manuell ekspedering hos Tollvesenet.</li>
				           				<li><b>U</b>&nbsp;Utleveringsattest er mottatt fra Tollvesenet.</li>
				           				<li><b>V</b>&nbsp;Melding om varekontroll er mottatt fra Tollvesenet.</li>
				           				<li><b>1</b>&nbsp;Melding fra Input-kontroll / Toller er mottatt og klar til å skrives ut.</li>	
				           				<li><b>2</b>&nbsp;Utleveringsattest er mottatt fra Tvinn og klar til å skrives ut.</li>	
				           				<li><b>3</b>&nbsp;Tollkvittering/tolldeklarasjon er mottatt fra Tvinn og klar til å skrives ut.</li>	
					           		</ul>
									</div>
								</span>	
			 				</td>
		 				</tr>
	 				</table>
					<%-- MASTER Topic information [it is passed through a session object: XX] --%>
				 	<table height="40" width="100%" align="center" class="formFrameTitaniumWhite" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="2"><td class="text" align="left" colspan="2"></td></tr>
				 		<tr>
					 		<td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text11Bold" align="left" >Eksportør</td>
							            <td class="text11" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Navn&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.sinas}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Adresse-1&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.siads1}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Adresse-2&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.siads2}</td>
							        </tr>
							        
									<tr>
							            <td width="30%" class="text11" align="left">Adresse-3&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.siads3}</td>
							        </tr>
							        <tr>
							        		<td width="30%" class="text11" align="left">&nbsp;</td>
							        </tr>						        
			        	        </table>
					        </td>
					        <td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text11Bold" align="left" >Mottaker</td>
							            <td class="text11" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Regnr.&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.sirg}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Navn&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.sinak}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Adresse-1&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.siadk1}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Adresse-2&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.siadk2}</td>
							        </tr>
							        
									<tr>
							            <td width="30%" class="text11" align="left">Adresse-3&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.siadk3}</td>
							        </tr>
							        <%--
									<tr>
							            <td width="30%" class="text11" align="left">Handläggare&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XX.svih_moha}</td>
							        </tr>
							         --%>
							        
			        	        </table>
					        </td>
				        </tr>
				        
					</table>          
            	</td>
           	</tr> 
           	<%-- ---------------------- --%>
           	<%-- LIST of existent ITEMs --%>
           	<%-- ---------------------- --%>
           	<tr>
				<td>
					<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    				<%-- separator --%>
	        			<tr height="10"><td></td></tr> 
						<tr >
							<td>
							<form name="createNewItemLine" id="createNewItemLine" method="post" action="tvinnsadimport_edit_items.do">
								<input type="hidden" name="action" id="action" value='doFetch'>
								<input type="hidden" name="renew" id="renew" value='1'>
				 				<input type="hidden" name="avd" id="avd" value='${model.avd}'>
				 				<input type="hidden" name="sign" id="sign" value='${model.sign}'>
								<input type="hidden" name="opd" id="opd" value='${model.opd}'>
				 				<input type="hidden" name="status" id="status" value='${model.status}'>
				 				<input type="hidden" name="datum" id="datum" value='${model.datum}'>
				 				<input type="hidden" name="fabl" id="fabl" value='${recordTopicTvinnSad.sibel3}'>
				 				<input type="hidden" name="totalGrossWeight" id="totalGrossWeight" value='${recordTopicTvinnSad.sivkb}'>
				 				<input type="hidden" name="receiverId" id="receiverId" value='${recordTopicTvinnSad.siknk}'>
				 										
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr>
										
										<td class="text12Bold">
											<c:if test="${model.status == 'M' || empty model.status}">
												<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submit" onclick="javascript: form.action='tvinnsadimport_edit_items.do';" value="<spring:message code="systema.tvinn.sad.import.item.line.init.createnew.submit"/>">
											</c:if>
											<button name="allItemsButton" class="inputFormSubmitStd" type="button" onClick="showPop('allItems');" >Vis alle</button> 
										        <span style="background-color:#EEEEEE; position:absolute; left:50px; top:200px; width:1200px; height:1000px;" id="allItems" class="popupWithInputTextThickBorder"  >
									           		
								           			<table id="containerdatatableTable" width="98%" align="left" >
									           			<tr>
										           			<td colspan="3" class="text14"><b>Varelinjer</b></td>
										           		</tr>
											           	<tr>	
															<td class="text12">
															<table id="tblItemLinesAll" class="display compact cell-border" >
																<thead>
																<tr style="background-color:#DDDDDD">
																    <th class="text12">&nbsp;Linjenr.&nbsp;</th>   
												                    <th class="text12" nowrap>&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svlk.opprLand"/>&nbsp;</th>
												                    <th class="text12" nowrap>&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svvnt.vareNr"/>&nbsp;</th>
												                    <th class="text12" nowrap>&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svtn.tn"/>&nbsp;</th>
												                    <th class="text12" nowrap>&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svpre.preference"/>&nbsp;</th>
												                    <th class="text12" nowrap>&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svbelt.varansPris"/>&nbsp;</th>
												                    <th class="text12">&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svvktn.nettov"/>&nbsp;</th>
												                    <th class="text12">&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svntm.mengde"/>&nbsp;</th>
												                    <th class="text12">&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svpva.enhetPVA"/></th>
												                    <th class="text12">&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svas.tollsats"/></th>
												                    <th class="text12">&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svmfr.mva"/>&nbsp;</th>
												                    <th class="text12">&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svkdaae.avgift"/>&nbsp;</th>
												                    <th class="text12">&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.wd1.vareDescription"/>&nbsp;</th>
												                    <th align="center" class="text12">&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.sverr.error"/>&nbsp;</th>
												                    <c:if test="${model.status == 'M' || empty model.status}">
												                    	<th align="center" class="text12" nowrap>Slett</th>
												                    </c:if>
												                    
											               		</tr> 
											               		</thead>
											               		<tbody>
										 						  <c:forEach items="${model.list}" var="record" varStatus="counter">    
														               <c:choose>           
														                   <c:when test="${counter.count%2==0}">
														                       <tr class="tableRow" height="20" >
														                   </c:when>
														                   <c:otherwise> 
														                       <tr class="tableOddRow" height="20" >
														                   </c:otherwise>
														               </c:choose>
														               <%-- <td class="tableCellFirst" align="center">&nbsp;${record.svln} --%>
														               <td class="text11" align="center">&nbsp;${record.svln}
														               
														               <td class="text11" >&nbsp;${record.svlk}</td>
														               <td class="text11" >&nbsp;${record.svvnt}</td>
														               <td class="text11" >&nbsp;${record.svtn}</td>
														               <td class="text11" >&nbsp;${record.svpre}</td>
														               <td class="text11" >&nbsp;${record.svbelt}</td>
														               <td class="text11" >&nbsp;${record.svvktn}</td>
														               <td class="text11" >&nbsp;${record.svntm}</td>
														               <td class="text11" >&nbsp;${record.svpva}</td>
														               <td class="text11" >&nbsp;${record.svas}</td>
														               <td class="text11" >&nbsp;${record.svmfr}</td>
														               <td class="text11">&nbsp;${record.wg1}</td>
														               <td class="text11">&nbsp;${record.wd1}</td>
														               <td align="center" class="text11">&nbsp;
														               		<c:if test="${not empty record.sverr}">
														               			<img valign="bottom" src="resources/images/redFlag.png" width="18px" height="18px" border="0" alt="remove">
														               		</c:if>
														               </td>
														               <c:if test="${model.status == 'M' || empty model.status}">	
															               <td class="text11" align="center" nowrap>
															               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadimport_edit_items.do?action=doDelete&sign=${model.sign}&avd=${record.svavd}&opd=${record.svtdn}&lin=${record.svli}&fabl=${recordTopicTvinnSad.sibel3}">
															               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
															               	</a>	
															               </td>
														               </c:if>
														               
														            </tr>
														            <%-- this param is used ONLY in this JSP --%>
															        <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" /> 
															        <%-- this param is used throughout the Controller --%>
															        <c:set var="numberOfItemLinesInTopic" value="${record.svln}" scope="request" /> 
														        </c:forEach>
														        </tbody>
													        </table>
															</td>											           		
												         </tr>
										   			</table>
										   			
										   			<div>
										   			<table >
													<%-- OK BUTTON --%>
							           				<tr align="left" >
														<td class="text11"><button name="allItemsButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('allItems');">&nbsp;Ok</button></td>
														<td class="text12">&nbsp;&nbsp;&nbsp;
											 	        		<a href="tvinnsadImportItemListExcelView.do" target="_new">
										                 		<img valign="bottom" id="itemListExcel" src="resources/images/excel.png" border="0" alt="excel">&nbsp;Excel
											 	        		</a>
											 	        		&nbsp;
												 		</td>
													</tr>
													</table>
													</div>
								   				</span>
											<c:if test="${model.status == 'M' || empty model.status}">		
												&nbsp;<button title="Kontrollera varelinjer" name="itemListControlButton" id="itemListControlButton" class="buttonGrayWithGreenFrame11" type="button" >Varelinje kontroll</button>
											</c:if>
										</td>
									</tr>
									<tr>
										<td>
											<table class="tableBorderWithRoundCornersGray" >
												<tr colspan="4">
													<td class="text11" nowrap>
														<input tabindex=-1 class="inputFormSubmitGrayFont11" type="submit" name="submitStartItem" onclick="javascript: form.action='tvinnsadimport_edit_items.do';" value="Hent">
														fra linjenr:
														<input tabindex=-1 align="left" type="text" class="inputText" size="4" maxlength="5" name="startItemLineNr" id="startItemLineNr" value='${model.recordItemContainerTopic.startItemLineNr}'>
														fra varenr:
														<input tabindex=-1 align="left" type="text" class="inputText" size="10" maxlength="8" name="tariffNr" id="tariffNr" value='${model.recordItemContainerTopic.tariffNr}'>
													</td>
											      </tr>
											</table>
						            		</td>
									</tr>
									<tr>
										<td class="text12Bold">&nbsp;Antall varelinjer&nbsp;&nbsp;<font class="text12MediumBlue"><b>${totalNumberOfItemLines}</b></font>
						            		</td>
										<td align="right" class="text11">Bruttovekt:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" maxlength=11" value='${recordTopicTvinnSad.sivkb}'>
										</td>
										<td align="right" class="text11">Fsum:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" maxlength=20" value='${recordTopicTvinnSad.sibel3}'>
											&nbsp;<font class="inputText11BlueBoldReadOnly"><b>${recordTopicTvinnSad.sival3}</b></font>
										</td>
										<td align="right" class="text11">Vsum&nbsp;(&Sigma;):&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" maxlength=20" value='${model.recordItemContainerTopic.calculatedItemLinesTotalAmount}'>
										</td>
										<td align="right" class="text11">Diff:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly
												<c:choose>
												<c:when test="${fn:contains(model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount,'-')}">
													class="inputText11RedBoldReadOnly" 
												</c:when>
												<c:otherwise>
													class="inputText11BlueBoldReadOnly"
												</c:otherwise>
												</c:choose>
												size="12" maxlength=20" value='${model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount}'>
										</td>
									</tr>
									
									<tr height="2"><td></td></tr>
								</table>
							</form>
							</td>
						</tr> 
						
						<tr>
							<td>
								<form name="formItemList" id="formItemList" method="POST" >
			               		<input type="hidden" name="opdItemList" id="opdItemList" value="${model.opd}">
		 						<input type="hidden" name="avdItemList" id="avdItemList" value="${model.avd}"> 
		 						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
		
								<table id="containerdatatableTable" cellspacing="2" align="left" width="100%">
								<tr>
								<td class="text11">
							
								<table id="tblItemLines" class="display compact cell-border">
									<thead>
									<tr style="background-color:#DDDDDD">
									    <th class="text12">&nbsp;Lin.&nbsp;</th> 
									    <th class="text12">&nbsp;Redig.&nbsp;</th> 
									    <th class="text12" nowrap>&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svlk.opprLand"/>&nbsp;</th>
					                    <th class="text12" nowrap>&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svvnt.vareNr"/>&nbsp;</th>
					                    <th class="text12" nowrap>&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svtn.tn"/>&nbsp;</th>
					                    <th class="text12" nowrap>&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svpre.preference"/>&nbsp;</th>
					                    <th class="text12" nowrap>&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svbelt.varansPris"/>&nbsp;</th>
					                    <th class="text12">&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svvktn.nettov"/>&nbsp;</th>
					                    <th class="text12">&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svntm.mengde"/>&nbsp;</th>
					                    <th class="text12">&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svpva.enhetPVA"/></th>
					                    <th class="text12">&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svas.tollsats"/></th>
					                    <th class="text12">&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svmfr.mva"/>&nbsp;</th>
					                    <th class="text12">&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.svkdaae.avgift"/>&nbsp;</th>
					                    <th class="text12">&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.wd1.vareDescription"/>&nbsp;</th>
					                    <th align="center" class="text12">&nbsp;<spring:message code="systema.tvinn.sad.import.item.list.label.sverr.error"/>&nbsp;</th>
					                    <c:if test="${model.status == 'M' || empty model.status}">
					                    		<th align="center" class="text12" nowrap>Slett</th>
					                    </c:if> 
					               </tr> 
					               </thead>
					               <tbody>
					                 <c:forEach items="${model.list}" var="record" varStatus="counter">    
							               <c:choose>           
							                   <c:when test="${counter.count%2==0}">
							                       <tr id="parentItemListTableRowNr_${counter.count}" class="tableRow" >
							                   </c:when>
							                   <c:otherwise> 
							                       <tr id="parentItemListTableRowNr_${counter.count}" class="tableOddRow" >
							                   </c:otherwise>
							               </c:choose>
							               <td width="4%" class="text11" align="center">${record.svln}</td>
							               <td width="4%" class="text11" align="center">&nbsp;
							               		<%-- Always svli to AS400. svln is only for presentation purposes --%>
							               		<a tabindex=-1 title="${counter.count}" id="recordUpdate_${record.svli}" href="#" onClick="getItemData(this);">
							               			<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;
							               		</a>
							               </td>
							               
							               
							               <td class="text11" >&nbsp;${record.svlk}</td>
							               <td class="text11" >&nbsp;${record.svvnt}&nbsp;&nbsp;
							               	  <img id="recordUpdate_${record.svli}" onClick="updateKundensVarReg(this);" src="resources/images/addOrder.png" width="12px" height="12px" border="0" title="Tilføy Kundens varereg.">
							               </td>
							               <td class="text11" >&nbsp;${record.svtn}</td>
							               <td class="text11" >&nbsp;${record.svpre}</td>
							               <td class="text11" >&nbsp;${record.svbelt}</td>
							               <td class="text11" >&nbsp;${record.svvktn}</td>
							               <td class="text11" >&nbsp;${record.svntm}</td>
							               <td class="text11" >&nbsp;${record.svpva}</td>
							               <td class="text11" >&nbsp;${record.svas}</td>
							               <td class="text11" >&nbsp;${record.svmfr}</td>
							               <td class="text11">&nbsp;${record.wg1}</td>
							               <td class="text11">&nbsp;${record.wd1}</td>
							               <td align="center" class="text11">&nbsp;
							               		<c:if test="${not empty record.sverr}">
							               			<img valign="bottom" src="resources/images/redFlag.png" width="18px" height="18px" border="0" alt="remove">
							               		</c:if>
							               </td>
							               <c:if test="${model.status == 'M' || empty model.status}">	
								               <td class="text11" align="center" nowrap>
								               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadimport_edit_items.do?action=doDelete&sign=${model.sign}&avd=${record.svavd}&opd=${record.svtdn}&lin=${record.svli}&fabl=${recordTopicTvinnSad.sibel3}">
								               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
								               	</a>	
								               </td>
							               </c:if> 
							            </tr>
								        <%-- <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" />  --%>
								        <%-- <c:set var="numberOfItemLinesInTopic" value="${record.svli}" scope="request" /> --%>
								        </c:forEach>
								      </tbody>  
						        </table>
								</td>
								</tr>
							</table>
						</form>
				</td>
			</tr>
			<tr height="3"><td></td></tr>
			<%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td>
	            	<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
	            	<tr>
					<td class="textError">					
			            <ul>
			            <c:forEach var="error" items="${errors.allErrors}">
			                <li >
			                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>
			                </li>
			            </c:forEach>
			            </ul>
					</td>
					</tr>
					</table>
				</td>
			</tr>
			</spring:hasBindErrors>
			<%-- ------------------------------------------------- --%>
           	<%-- DETAIL Section - Create Item line PRIMARY SECTION --%>
           	<%-- ------------------------------------------------- --%>
           	<tr>
	 			<td >
	 				<form name="tvinnSadImportEditTopicItemForm" id="tvinnSadImportEditTopicItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
				 	<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
				 	<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
				 	<input type="hidden" name="status" id="status" value="${model.status}"/>
				 	<input type="hidden" name="datum" id="datum" value="${model.datum}"/>
				 	<input type="hidden" name="fabl" id="fabl" value="${recordTopicTvinnSad.sibel3}"/>
					<input type="hidden" name="ekspedType" id="ekspedType" value="${recordTopicTvinnSad.sidp}">
				 	<input type="hidden" name="trType" id="trType" value="${recordTopicTvinnSad.sitst}">
				 	<%-- BEGIN avgift header variables --%>
				 	<input type="hidden" name="insibvnv" id="insibvnv" value="${recordTopicTvinnSad.insibvnv}"/>
				 	<input type="hidden" name="sibel3" id="sibel3" value="${recordTopicTvinnSad.sibel3}"/>
				 	<input type="hidden" name="sibel4" id="sibel4" value="${recordTopicTvinnSad.sibel4}"/>
				 	<input type="hidden" name="sibelr" id="sibelr" value="${recordTopicTvinnSad.sibelr}"/>
				 	<input type="hidden" name="sibels" id="sibels" value="${recordTopicTvinnSad.sibels}"/>
					<%-- END avgift header variables --%>					 	
				 	
				 	<input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" />
				 	<input type="hidden" name="lastSelectedItemLineNumber" id="lastSelectedItemLineNumber" value="${model.recordItemContainerTopic.lastSelectedItemLineNumber}" />
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="100%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White" align="left" >
				 				&nbsp;&nbsp;V<label onClick="showPop('debugPrintlnAjaxItemFetchAdmin');" >a</label>relinje&nbsp;
		 									<span style="position:absolute; left:150px; top:200px; width:800px; height:400px;" id="debugPrintlnAjaxItemFetchAdmin" class="popupWithInputText"  >
								           		<div class="text11" align="left">
								           			<label id="debugPrintlnAjaxItemFetchInfo"></label>
								           			<br/>
								           			&nbsp;&nbsp;
								           			<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('debugPrintlnAjaxItemFetchAdmin');">
								           			Close
								           			</button> 
								           		</div>
								        		</span>
				 				<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit">
				 				<span style="position:absolute; left:150px; top:200px; width:800px; height:400px;" id="updateInfo" class="popupWithInputText"  >
		           		   			<div class="text12" align="left" style="display:block;width:700px;word-break:break-all;">
		           		   				${activeUrlRPGUpdate_TvinnSad}<br/><br/>
		           		   				<button name="updateInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('updateInfo');">Close</button> 
		           		   			</div>
						        </span>  
				 				&nbsp;&nbsp;&nbsp;
				 				<c:choose>
					 				<c:when test="${not empty model.record.svln}">
						 				<input title="from model" tabindex=-1 style="text-align:center;" class="text12BoldLightGreenForItemLinenr" readonly type="text" name="lineSvln" id="lineSvln" size="4" value="${model.record.svln}">
									</c:when>
									<c:otherwise>
				 						<input title="from session" tabindex=-1 style="text-align:center;" class="text12BoldLightGreenForItemLinenr" readonly type="text" name="lineSvln" id="lineSvln" size="4" value="${svln_SESSION}">
									</c:otherwise>
								</c:choose>
				 				<c:choose>
					 				<c:when test="${not empty model.record.svli}">								
										<input title="from model" tabindex=-1 style="text-align:center;" class="text12BoldRedForItemLinenr" readonly type="text" name="lineSvli" id="lineSvli" size="4" value="${model.record.svli}">
									</c:when>
									<c:otherwise>
				 						<input title="from session" tabindex=-1 style="text-align:center;" class="text12BoldRedForItemLinenr" readonly type="text" name="lineSvli" id="lineSvli" size="4" value="${svli_SESSION}">
									</c:otherwise>
								</c:choose>
			 				</td>
			 				<td class="text12White" align="right">Forhold B/N-vekt:&nbsp;
			 					<c:choose>
					 				<c:when test="${not empty recordTopicTvinnSad.insibvnv}">
						 				<input style="text-align:center;" tabindex=-1 class="text12BoldLightGreenForItemLinenr" readonly type="text" name="grossNetFactor" id="grossNetFactor" size="5" value="${recordTopicTvinnSad.insibvnv}">
						 			</c:when>
						 			<c:otherwise>
										<input style="text-align:center;" tabindex=-1 class="text12BoldLightGreenForItemLinenr" readonly type="text" name="grossNetFactor" id="grossNetFactor" size="5" value="0,90">														 			
						 			</c:otherwise>
					 			</c:choose>	
						 	</td>
		 				</tr>
	 				</table>
					<table width="100%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
							 			<td>&nbsp;</td>
							 			<td class="text12" align="left">&nbsp;
							 			<img onMouseOver="showPop('svvf_info');" onMouseOut="hidePop('svvf_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>43.</b>&nbsp;<font class="text16RedBold" >*</font><span title="svvf">VF</span>
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="svvf_info" class="popupWithInputText text11">
						           			<b>VF kode</b>
						           			<br/><br/>
											Gyldige koder:
											<ul>
												<li><b>1</b> Tollverdien fastsatt på grunnlag av transaksjonsverdien</li>
												<li><b>2</b> Identiske varer</li>
												<li><b>3</b> Liknende varer</li>
												<li><b>4</b> Videresalgsprisen</li>
												<li><b>5</b> Beregnet verdi</li>
												<li><b>6</b> Skjønnsmessig fastsettelse</li>
											</ul>
											Feltet ligger med 1 som standard hvis det ikke er angitt en annen kode på hovedbildet.
										</span>
										</div>
										</td>
							            
							            <td class="text12" align="left">
							 			<img onMouseOver="showPop('svlk_info');" onMouseOut="hidePop('svlk_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<font class="text16RedBold" >*</font>
							            <span title="svlk">Oppr.land</span>
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="svlk_info" class="popupWithInputText text11">
							           		<div class="text11" align="left">
						           			<b>Opprinnelsesland</b>
						           			<br/><br/>
											Tast gyldig kode for opprinnelsesland
						           			</div>
										</span>
										</div>
										</td>
							            
							 			<td class="text12" align="left">
							            <img onMouseOver="showPop('varenr_info');" onMouseOut="hidePop('varenr_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							            <font class="text16RedBold" >*</font><span title="svvnt">Varenr.</span>
							            <div class="text11" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="varenr_info" class="popupWithInputText text11"  >
						           			<b>Varenr.</b>
						           			<br/><br/>
											Tast varens 8 siffrede trariffnummer. Gyldigheten testes mot toll-tariffen.
										</span>
										</div>
										
							            	<%--
							            	<img id="imgTaricVarukodSearch" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchTaricCodesDialog');">
							            	<%-- ======================================================== --%>
						            		<%-- Here we have the search Taric codes popup window --%>
						            		<%-- ========================================================
						            		<span style="position:absolute; left:400px; top:450px; width:500px; height:210px;" id="searchTaricCodesDialog" class="popupWithInputText"  >
							           		<div class="text10" align="left">
							           			<table>
								           			<tr>
								           			<td>
									           			<table>
									           				<tr>
																<td class="text11" style="vertical-align:bottom;">&nbsp;Varenr.</td>
																<td class="text11">&nbsp;<input type="text" class="inputText" name="search_svvnt" id="search_svvnt" size="10" maxlength="10" value="">
																	<a tabindex="-1" class="text12" target="_blank" href="${model.tolltariffenURL.value}" onclick="${model.tolltariffenURL.windowOpenDimensions}" >
					            											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tolltariffen 
					            											&nbsp;<img width="25px" height="35px" style="cursor:pointer;" src="resources/images/logoTollNorway.jpg" border="0" alt="search">
					            										</a>
																</td>
															</tr>
										           			<tr>
										           				<td align="right">&nbsp;<button name="searchTaricCode" id="searchTaricCode" class="buttonGray" type="button" onClick="searchTaricVarukod();"><spring:message code="systema.tvinn.sad.import.search"/></button></td>
										           				<td class="text11">&nbsp;</td>
											           		</tr>
											           		<tr height="4"><td ></td></tr>
										           		</table>
									           		</td>
									           		</tr>
													
													<tr>
								           			<td>
									           			<table>							           		
											           		<tr>
										           				<td class="text11">&nbsp;&nbsp;Liste</td>
											           			<td>&nbsp;</td>
											           		</tr>
											           		<tr>
																<td colspan="2">
																	<select class="text11" id="taricVarukodList" name="taricVarukodList" size="5" onDblClick="hidePop('searchTaricCodesDialog');">
					 													<option selected value="">-velg-</option>
					 							 					</select>
																</td>
															</tr>
									           			</table>
								           			</td>
								           			</tr>
														
													<tr>
								           			<td>								           			
														<table width="100%" align="left" border="0">
															<tr align="left" >
																<td >&nbsp;<button name="searchTaricCodesClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchTaricCodesDialog');"><spring:message code="systema.tvinn.sad.import.ok"/></button></td>
															</tr>
														</table>
													</td>
													</tr>
												</table>
											</div>
										</span>	
										 --%>
							            </td>
							            
							            <td class="text12" align="left">
							            <img onMouseOver="showPop('svtn_info');" onMouseOut="hidePop('svtn_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							            <span title="svtn">TN</span>
				 					
				 						<div class="text11" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="svtn_info" class="popupWithInputText text11"  >
						           			<b>TN - Tollnedsettelse</b>
						           			<br/><br/>
											Rundskriv av 31. Desember om visse tollettelser
												<ul>
													<li><b>E</b> Del E</li>
													<li><b>F</b> Del F</li>
													<li><b>H</b> Lover,internasjonale konvensjoner,overenskomster</li>
													<li><b>S</b> Individuelle skriv fra TAD eller Statens Kornforretning om tollfrihet/tollnedsettelse m.v</li>
								                    <li><b>R</b> Brev fra TAD om tollfrihet/tollnedsettelser m.v.</li>
												</ul>													                        
												(Disse kodene kan endre seg ved forskrift fra myndighetene.)
												<p> 
												TN feltet og Preferanse skal aldri være utfylt samtidig.
												(Systemet blanker ut evt. TN dersom preferanse er utfylt).
												Dersom den benyttede koden gir tollfrihet eller reduksjon iht. tolltariffen så må dette overstyres
												</p>
												<p>
												Er det tollfritt så må det tastes en F i PVA.(FRI). Skal det benyttes en annen tollsats så må denne tastes i rubr T.SATS.
												Ved kode S, skal det (Absolutt krav i TVINN) oppgis skrivnr som det ønskes fritak i henhold til i rubr.44 (T.O.). (Se hjelp til T.O.rubrikken).
												TN-koden skrives sist i rubrikk 33 (varenr) på SAD-blanketten.											
												</p>
										</span>
										</div>
							            </td>
							            
							            <td class="text12" align="left">
							            <img onMouseOver="showPop('svpre_info');" onMouseOut="hidePop('svpre_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info" >
				 						<b>36.</b><span title="svpre">Preferense</span>
				 						<div class="text11" style="position: relative;" align="left">
				 						<span style="position:absolute;top:2px; width:250px;"id="svpre_info" class="popupWithInputText text11"  >
						           			<b>36. Preferense</b>
						           			<br/><br/>
						           			 	<ul>
													<li><b>N</b> "NEI" hverken preferanse eller senere refusjon (blank=samme)</li>
													<li><b>J</b> "JA"- dokumentasjon for refusj. kan dukke opp senere</li>
													<li><b>A</b> EØS-avtalen</li>
													<li><b>B</b> Frihandelsavtalen EF/Norge</li>
													<li><b>C</b> EFTA-konvensjonen</li>
													<li><b>G</b> Preferanser for varer fra utviklingsland (GSP)</li>
													<li><b>P</b> Andre preferanseavtaler/-systemer</li>
												</ul>
											<br><br>	
											<p>Disse kodene kan endre seg ved foreskrift fra myndighetene
											Systemet vil selv automatisk på bakgrunn av opprinnelsesland foreslå preferansekode hvis ikke noe annet tastes manuellt.
											(Vedlikehold av preferansekoder/land: Se OPP). Når opprinnelsesland tilhører EF/EFTA-området vil systemet foreslå kode A med mindre tollsatsen for EF- respektive EFTA-avtalen er lavere enn under EØS-avtalen.
											Dette gjelder ikke for Sveits og Lichtenstein (CH / LI) hvor kun preferansekode C er gyldig.
											</p>
											<p>
											I TVINN MÅ rubr. 44 (og "SER" i Rf-feltet) fylles ut ved preferanse.
											Systemet styrer selv tollsatser ved bruk av kodene. Hvis preferansekode ikke skal benyttes må man endre koden til J dersom landkoden tilsier at preferanse kan være aktuelt.
											(EF/EFTA/GSP-land m.m) (Gir full toll men rett til senere refusjon.)"JA" blir da skrevet i pref.-rubrikk på SAD-blanketten.
											<br/><br/>
											OM EN VET AT PREFERANSE IKKE VIL/KAN FOREKOMME (F.eks GSP-land men varenr ikke i fritaksliste) MÅ EN HINDRE "JA" VED Å TASTE "N" I PREF.
											</p>
										</span>
				 						</div>
				 						</td>
							            <td class="text12" align="left">
							            <img onMouseOver="showPop('svbelt_info');" onMouseOut="hidePop('svbelt_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							            	<span title="svbelt">Tollverdi</span>
							            	<div class="text11" style="position: relative;" align="left">
							            	<span style="position:absolute;top:2px; width:250px;" id="svbelt_info" class="popupWithInputText text11"  >
												<b>Tollverdi</b>
							           			<br/><br/>
												Tast varelinjens beløp i valuta. Feks 1025,51
											</span>
											</div>
					            		</td>
						            		
							            
							        </tr>
							        <tr>
						        		<td>&nbsp;<button title="Kundens vareregister" name="kundensVaruregisterControlButton" id="kundensVaruregisterControlButton" class="buttonGrayWithGreenFrame" type="button" >Søk i kund.varereg.</button></td>
							        		<td align="left" nowrap>
							        			<c:choose>
							        			<c:when test="${ not empty model.record.svvf}">
									            	<select class="inputTextMediumBlueMandatoryField" name="svvf" id="svvf">
							 						<option value="">-velg-</option>
				  								  	<option value="1"<c:if test="${ model.record.svvf == '1'}"> selected </c:if> >1</option>		  
				  								  	<option value="2"<c:if test="${ model.record.svvf == '2'}"> selected </c:if> >2</option>		  
				  								  	<option value="3"<c:if test="${ model.record.svvf == '3'}"> selected </c:if> >3</option>		  
				  								  	<option value="4"<c:if test="${ model.record.svvf == '4'}"> selected </c:if> >4</option>		  
				  								  	<option value="5"<c:if test="${ model.record.svvf == '5'}"> selected </c:if> >5</option>		  
				  								  	<option value="6"<c:if test="${ model.record.svvf == '6'}"> selected </c:if> >6</option>		  
												</select>
											</c:when>
											<c:otherwise>
									            	<select class="inputTextMediumBlueMandatoryField" name="svvf" id="svvf">
							 						<option value="">-velg-</option>
				  								  	<option value="1"<c:if test="${ recordTopicTvinnSad.insivf == '1'}"> selected </c:if> >1</option>		  
				  								  	<option value="2"<c:if test="${ recordTopicTvinnSad.insivf == '2'}"> selected </c:if> >2</option>		  
				  								  	<option value="3"<c:if test="${ recordTopicTvinnSad.insivf == '3'}"> selected </c:if> >3</option>		  
				  								  	<option value="4"<c:if test="${ recordTopicTvinnSad.insivf == '4'}"> selected </c:if> >4</option>		  
				  								  	<option value="5"<c:if test="${ recordTopicTvinnSad.insivf == '5'}"> selected </c:if> >5</option>		  
				  								  	<option value="6"<c:if test="${ recordTopicTvinnSad.insivf == '6'}"> selected </c:if> >6</option>		  
												</select>
											</c:otherwise>
											</c:choose>
											
										</td>
							        		<td align="left" nowrap>
								            	<select class="inputTextMediumBlueMandatoryField" name="svlk" id="svlk">
						 						<option value="">-velg-</option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<c:choose>
								        					<c:when test="${ not empty model.record.svlk}">
									 				  		<option value="${country.zkod}"<c:if test="${model.record.svlk == country.zkod}"> selected </c:if> >${country.zkod}</option>
								 				  		</c:when>
								 				  		<c:otherwise>
															<option value="${country.zkod}"<c:if test="${recordTopicTvinnSad.silka == country.zkod}"> selected </c:if> >${country.zkod}</option>
								 				  		</c:otherwise>
							 				  		</c:choose>
												</c:forEach>  
											</select>
											<a tabindex="-1" id="svlkIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
											<%-- info span 
											<img onClick="showPop('opprlandInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
											<span style="position:absolute; left:320px; top:320px; width:350px; height:150px;" id="opprlandInfo" class="popupWithInputText"  >
								           		<div class="text10" align="left">
							           				<select class="text11" id="opprland" name="opprland" size="5" onDblClick="hidePop('opprlandInfo');">
								           				<c:forEach var="country" items="${model.countryCodeList}" >
								 				  			<option value="${country.zkod}">${country.zkod}&nbsp;${country.ztxt}</option>
														</c:forEach>
								           			</select>
								           			
													<table width="100%" align="left" border="0">
														<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
														<tr align="left" >
															<td class="text11">&nbsp;<button name="opprlandButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('opprlandInfo');">&nbsp;<spring:message code="systema.tvinn.sad.import.ok"/></button> 
															</td>
														</tr>
													</table>
												</div>
											</span>	
											--%>																 			
										</td>
							        		<td class="text12" align="left">
							            		<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField"  name="svvnt" id="svvnt" size="12" maxlength="8" value="${model.record.svvnt}">
							            		<a tabindex="-1" id="svvntIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
							            </td>
										<td align="left" nowrap>
											<select name="svtn" id="svtn">
						 						<option value="">-velg-</option>
												<option value="E"<c:if test="${ model.record.svtn == 'E'}"> selected </c:if> >E</option>
												<option value="F"<c:if test="${ model.record.svtn == 'F'}"> selected </c:if> >F</option>
												<option value="H"<c:if test="${ model.record.svtn == 'H'}"> selected </c:if> >H</option>
												<option value="S"<c:if test="${ model.record.svtn == 'S'}"> selected </c:if> >S</option>
												<option value="R"<c:if test="${ model.record.svtn == 'R'}"> selected </c:if> >R</option>		  
											</select>
										</td>
						 				<td class="text12" align="left" nowrap>
											<select name="svpre" id="svpre">
						        		    			<option value="">-velg-</option>
			  								  	<option value="N"<c:if test="${ model.record.svpre == 'N'}"> selected </c:if> >N</option>		
											  	<option value="J"<c:if test="${ model.record.svpre == 'J'}"> selected </c:if> >J</option>
											  	<option value="A"<c:if test="${ model.record.svpre == 'A'}"> selected </c:if> >A</option>
											  	<option value="B"<c:if test="${ model.record.svpre == 'B'}"> selected </c:if> >B</option>
											  	<option value="C"<c:if test="${ model.record.svpre == 'C'}"> selected </c:if> >C</option>
											  	<option value="G"<c:if test="${ model.record.svpre == 'G'}"> selected </c:if> >G</option>
											  	<option value="P"<c:if test="${ model.record.svpre == 'P'}"> selected </c:if> >P</option>
											</select>
										</td>
						 				<td nowrap class="text12" align="left">
						            		<c:choose>
												<%-- only when it is a new line  --%>
					           					<c:when test="${empty model.record.svbelt}">
						 				  			<c:choose>
														<c:when test="${fn:contains(model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount,'-')}">
															<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="svbelt" id="svbelt" size="10" maxlength="11" value="${fn:replace(model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount,'-','')}">
														</c:when>
														<c:otherwise>
															<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="svbelt" id="svbelt" size="10" maxlength="11" value="${model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount}">
														</c:otherwise>
						 				  			</c:choose>
						 				  		</c:when>		
					 				  			<c:otherwise>
					 				  				<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="svbelt" id="svbelt" size="10" maxlength="11" value="${model.record.svbelt}">
					 				  			</c:otherwise>
											</c:choose>
						            	</td>
						 				
							        </tr>
							        <tr height="10"><td class="text" align="left" colspan="12"><hr></td></tr>
							        
						            <tr>
						            	<td class="text12" align="left">
							            <img onMouseOver="showPop('svvktb_info');" onMouseOut="hidePop('svvktb_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							            <font class="text16RedBold" >*</font>
				 						<span title="svvktb">Bruttovekt(kg)</span>
				 						<div class="text11" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="svvktb_info" class="popupWithInputText text11"  >
												<b>Brutto/Nettovekt (kg)</b>
							           			<br/><br/>
												Dersom man ikke taster noe i feltene så beregner systemet vektene selv (ved å dele vekt på verdi) ved bruk av tasten F11.
												Om systemet skal beregne vektene automatisk så må man gjøre dette konsekvent på ALLE varelinjene. 
												<br/><br/>
												Dersom man taster brutto eller nettovekt så finner systemet automatisk den andre vekten. 
												(10% opp/ned eller angitt på hovedsiden)
										</span>
										</div>
										</td>
										
						            	<td class="text12" align="left">
										<img onMouseOver="showPop('nettovekt_info');" onMouseOut="hidePop('nettovekt_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<font class="text16RedBold" >*</font>
				 						<span title="svvktn">&nbsp;Nettovekt(kg)</span>
				 						<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="nettovekt_info" class="popupWithInputText text11"  >
												<b>Brutto/Nettovekt (kg)</b>
							           			<br/><br/>
												Dersom man ikke taster noe i feltene så beregner systemet vektene selv (ved å dele vekt på verdi) ved bruk av tasten F11.
												Om systemet skal beregne vektene automatisk så må man gjøre dette konsekvent på ALLE varelinjene. 
												<br/><br/>
												Dersom man taster brutto eller nettovekt så finner systemet automatisk den andre vekten. 
												(10% opp/ned eller angitt på hovedsiden)
										</span>
										</div>
										</td>
										
										<td class="text12" >
										<img onMouseOver="showPop('mengde_info');" onMouseOut="hidePop('mengde_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										
				 						<span title="svntm">Mengde</span>
				 						<div class="text11" style="position: relative;" align="left">
				 						<span style="position:absolute;top:2px; width:250px;" id="mengde_info" class="popupWithInputText text11"  >
							           		<b>Mengde</b>
						           			<br/><br/>
						           			Her tastes mengde i annen enhet dersom tolltariffen krever det.
						           			Systemet sjekker automatisk i tariffen og du får beskjed om å registrere antall eller annen enhet dersom du glemmer det.<br/>
											Følgende koder gjelder ved fortolling via Tvinn:
											<ul>
												<li><b>STK</b> = NMB Antall</li>
												<li><b>PAR</b> = NPP Par</li>
												<li><b>LIT</b> = LTR Liter</li>
												<li><b>M3</b> = MTO Kubikkmeter</li>
												<li><b>M2</b> = MTK Kvadratmeter</li>
												<li><b>GRA</b> = GRM Gram</li>
											</ul>						           			
											Disse kodene skal ligge definert i Tolltariffen og bruker behøver normalt ikke forholde seg til dette kodesettet.
											<p> 
											Skulle derimot felt for antall 'STK' i Tolltariffen ikke være utfyllt med kode J og Tollvesenet / Tvinn skulle kreve antall oppgitt må man gjøre følgende:
											Felt STK fylles ut med kode J og felt ENHET fylles ut med en av kodene som er vist over.
											<p>
											Systemet vil konvertere etter overstående tabell. Hvis andre enhetskoder benyttes vil systemet sende det som er blitt tastet med de muligheter for feilmelding fra Tvinn som dette medfører
										</span>
										</div>
										</td>
							            
										<td class="text12" >
										<img onMouseOver="showPop('svpva_info');" onMouseOut="hidePop('svpva_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<font class="text16RedBold" >*</font>	
										<span title="svpva">PVA</span>
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="svpva_info" class="popupWithInputText text11"  >
						           			<b>PVA</b>
											<ul>
												<li><b>P</b> =PROSENTTOLL</li>
												<li><b>V</b> =pr kg.</li>
												<li><b>W</b> =pr 100 kg</li>
												<li><b>A</b> =pr. liter</li>
												<li><b>F</b> =FRI</li>
											</ul>
											Fylles normalt ut automatisk.
											Systemet legger selv ut satsene. Skal det være <b>fritt</b> iht. kode i TN rubr. så må man taste en <b>F</b> her.
										</span>
										</div>
										</td>
							            
										<td class="text12" align="left">
								            <img onMouseOver="showPop('tollsats_info');" onMouseOut="hidePop('tollsats_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								            <span title="svas">Tollsats&nbsp;</span>
								            <div class="text11" style="position: relative;" align="left">
								            <span style="position:absolute;top:2px; width:250px;" id="tollsats_info" class="popupWithInputText text11"  >
							           			<br/>
							           			<b>Tollsats</b>
												<br/><br/>
							           			Systemet finner selv tollsatsen. Dersom satsen skal reduseres iht. kode i TN rubrikken så må ønsket sats tastes her.
											</span>
											</div>
										</td>
										<td class="text12" align="left">
								            <img onMouseOver="showPop('svmfr_info');" onMouseOut="hidePop('svmfr_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 						<font class="text16RedBold" >*</font>
								            <span title="svmfr">Momsfri&nbsp;</span>
								            <div class="text11" style="position: relative;" align="left">
								            <span style="position:absolute;top:2px; width:250px;" id="svmfr_info" class="popupWithInputText text11"  >
							           			<b>Momsfri/sats</b>
												<br/><br/>
							           			<ul>
							           				<li><b>F</b> = Momsfri</li>
													<li><b>1</b> = MVA høy sats</li>
													<li><b>2</b> = MVA lav sats</li>
												</ul>
												<p>
												Hvis tariffnummeret har mulighet for lav MVA, må du velge lav eller høy sats. 
												</p>
												<p>
												Om det ikke skal svares importMVA av varelinjen så tastes det en F her. 
												(Når ekspedisjonstype er 7 (prosedyre 70-79) legges automatisk F inn).
												</p>
											</span>
											</div>
										</td>
							        </tr>
									<tr>
										<td  class="text12" align="left"><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="svvktb" id="svvktb" size="13" maxlength="12" value="${model.record.svvktb}"></td>
										<td class="text12" align="left"><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField"  name="svvktn" id="svvktn" size="13" maxlength="12" value="${model.record.svvktn}"></td>
										<td class="text11" valign="bottom">
											&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="svntm" id="svntm" size="10" maxlength="9" value="${model.record.svntm}">
										</td>
										<td align="left" nowrap>
					            				<select class="inputTextMediumBlueMandatoryField" name="svpva" id="svpva">
				        		    			<option value="">-velg-</option>
			  								  	<option value="P"<c:if test="${ model.record.svpva == 'P'}"> selected </c:if> >P</option>		
											  	<option value="V"<c:if test="${ model.record.svpva == 'V'}"> selected </c:if> >V</option>
											  	<option value="W"<c:if test="${ model.record.svpva == 'W'}"> selected </c:if> >W</option>
											  	<option value="A"<c:if test="${ model.record.svpva == 'A'}"> selected </c:if> >A</option>
											  	<option value="F"<c:if test="${ model.record.svpva == 'F'}"> selected </c:if> >F</option>
											</select>
										</td> 
				            				
										<td class="text12" align="left" ><input type="text" class="inputText" name="svas" id="svas" size="8" maxlength="7" value="${model.record.svas}"></td>
										<td class="text12" align="left" >
											<select class="inputTextMediumBlueMandatoryField" name="svmfr" id="svmfr">
				        		    			<option value="">-velg-</option>
			  								  	<option value="F"<c:if test="${ model.record.svmfr == 'F'}"> selected </c:if> >F</option>		
											  	<option value="1"<c:if test="${ model.record.svmfr == '1' || empty model.record.svmfr}"> selected </c:if> >1</option>
											  	<option value="2"<c:if test="${ model.record.svmfr == '2'}"> selected </c:if> >2</option>
											</select>
										</td>
										<td align="left" >
												<c:choose>	
													<c:when test="${model.status == 'M' || empty model.status || model.status == '10' || model.status == '20'}">
														<input class="inputFormSubmit" type="submit" name="submit" id="submit" onclick="javascript: form.action='tvinnsadimport_edit_items.do';" value='<spring:message code="systema.tvinn.sad.import.item.createnew.submit"/>'>
														&nbsp;&nbsp;
														
														<%-- SEND button: is causing some issues in the Stat.värde calculation
															 We shall have the send button ONLY at one place (so far) and that is at the header level
									 				    	<c:if test="${not empty totalNumberOfItemLines && '0' != totalNumberOfItemLines}">
									 				    		<input tabindex=-1 class="inputFormSubmit" type="submit" name="send" id="send" onclick="javascript: form.action='skatimport_send.do';" value='<spring:message code="systema.skat.import.createnew.send"/>'/>
									 				    	</c:if>
									 				  	 --%>
													</c:when>
													<c:otherwise>
							 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='<spring:message code="systema.tvinn.sad.submit.not.editable"/>'/>
							 				    	</c:otherwise>	
						 				    	</c:choose>	
										</td>		
										
 							        </tr>
									<tr height="10"><td class="text" align="left" colspan="12"><hr></td></tr>
									<tr height="8"><td class="text" align="left"></td></tr>
									
							        <tr >
							        <td colspan="4" align="center" style="vertical-align:top;">
							        		<table class="tableBorderWithRoundCornersGray" style="width: 95%; margin-left:auto;margin-right:auto;">
											<tr >
									        <td class="text12Bold" >
									        		&nbsp;<img onMouseOver="showPop('varebeskrivelse_info');" onMouseOut="hidePop('varebeskrivelse_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									        		Varebeskrivelse
									        		<div class="text11" style="position: relative;" align="left">
										        <span style="position:absolute;top:2px; width:250px;" id="varebeskrivelse_info" class="popupWithInputText text11"  >
								           			<b>Varebeskrivelse</b>
													<ul>
														<li>Varetext</li>
														<li>Merke og nr</li>
														<li>Antall kolli</li>
														<li>Enhet</li>
													</ul>
													Tast inn merke og nummer.Dersom intet tastes, så skriver systemet ut <b>ADR</b>.<br/> 
													Tast deretter inn antall kolli og "forpakningskode". Dersom intet tastes, så skrives EX og samlet antall (fra side 1)
													ved flere linjer, eller samlet antall + KLL dersom kun 1 linje. Tast deretter ønsket tekst for varelinjen. 
													<p>
														Dersom intet tastes, så hentes tekst fra tolltariffen.
													</p>
												</span>
												</div>
									        </td>
									        </tr>
											<tr height="5"><td></td></tr>
											<tr>
												<td class="text12" align="left" >&nbsp;<span title="wd1-wd5(svvt)">Varetext</span></td>
												<td class="text12" align="left" >&nbsp;<span title="wa1-wa7(svft)">Merke og nr</span></td>
												<td class="text12" align="left" >&nbsp;<span title="wb1-wb7(svnt)">Antall kolli</span></td>
		               							<td class="text12" align="left" >&nbsp;<span title="wc1-wc7(sveh)">Enhet</span>
			               							<a tabindex="-1" id="svehWc1Wc7IdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
			               							
		               							</td>
		               						</tr>
		               						
											<tr>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wd1" id="wd1" size="20" maxlength="30" value="${model.record.wd1}">
												</td>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wa1" id="wa1" size="20" maxlength="28" value="${model.record.wa1}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="wb1" id="wb1" size="6" maxlength="6" value="${model.record.wb1}">
												</td>
												<td class="text12MediumBlue">
													<select name="wc1" id="wc1">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.enhetsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wc1 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
											<tr>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wd2" id="wd2" size="20" maxlength="30" value="${model.record.wd2}">
												</td>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wa2" id="wa2" size="20" maxlength="28" value="${model.record.wa2}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="wb2" id="wb2" size="6" maxlength="6" value="${model.record.wb2}">
												</td>
												<td class="text12MediumBlue">
													<select name="wc2" id="wc2">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.enhetsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wc2 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
											<tr>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wd3" id="wd3" size="20" maxlength="30" value="${model.record.wd3}">
												</td>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wa3" id="wa3" size="20" maxlength="28" value="${model.record.wa3}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="wb3" id="wb3" size="6" maxlength="6" value="${model.record.wb3}">
												</td>
												<td class="text12MediumBlue">
													<select name="wc3" id="wc3">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.enhetsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wc3 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
											<tr>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wd4" id="wd4" size="20" maxlength="30" value="${model.record.wd4}">
												</td>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wa4" id="wa4" size="20" maxlength="28" value="${model.record.wa4}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="wb4" id="wb4" size="6" maxlength="6" value="${model.record.wb4}">
												</td>
												<td class="text12MediumBlue">
													<select name="wc4" id="wc4">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.enhetsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wc4 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
											<tr>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wd5" id="wd5" size="20" maxlength="30" value="${model.record.wd5}">
												</td>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wa5" id="wa5" size="20" maxlength="28" value="${model.record.wa5}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="wb5" id="wb5" size="6" maxlength="6" value="${model.record.wb5}">
												</td>
												<td class="text12MediumBlue">
													<select name="wc5" id="wc5">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.enhetsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wc5 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
											<tr>
												<td class="text12MediumBlue">
													&nbsp;
												</td>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wa6" id="wa6" size="20" maxlength="28" value="${model.record.wa6}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="wb6" id="wb6" size="6" maxlength="6" value="${model.record.wb6}">
												</td>
												<td class="text12MediumBlue">
													<select name="wc6" id="wc6">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.enhetsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wc6 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
											<tr>
												<td class="text12MediumBlue">
													&nbsp;
												</td>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wa7" id="wa7" size="20" maxlength="28" value="${model.record.wa7}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="wb7" id="wb7" size="6" maxlength="6" value="${model.record.wb7}">
												</td>
												<td class="text12MediumBlue">
													<select name="wc7" id="wc7">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.enhetsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wc7 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
											<tr>
												<td class="text12MediumBlue">
													&nbsp;
												</td>
												<td class="text12MediumBlue">
													&nbsp;
												</td>
												<td class="text12MediumBlue">
													&nbsp;
												</td>
												<td class="text12MediumBlue">
													&nbsp;
												</td>
											</tr>
											
											<tr height="30"><td class="text" align="left"></td></tr>	
						        			</table>
						        			</td> 
										<td colspan="4" style="vertical-align:top;">
										<table class="tableBorderWithRoundCornersGray" style="width: 95%; margin-left:auto;margin-right:auto;">
									        <tr >
										        <td class="text12Bold">
											        <%-- Model fields needed for the AJAX calculation av Bilagda handlingar  --%>
											        <input type="hidden" name="model_avd" id="model_avd" value="${model.avd}">
											        <input type="hidden" name="model_opd" id="model_opd" value="${model.opd}">
							        	        
										        		&nbsp;<img onMouseOver="showPop('avgift_info');" onMouseOut="hidePop('avgift_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										        		Avg<label onClick="showPop('debugPrintlnAjaxAdmin');" >i</label>ft
														
														<span style="position:absolute; left:200px; top:500px; width:600px; height:180px;" id="debugPrintlnAjaxAdmin" class="popupWithInputText"  >
										           		<div class="text11" align="left">
										           			<label id="debugPrintlnAjaxInfo"></label>
										           			&nbsp;&nbsp;&nbsp;&nbsp;<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('debugPrintlnAjaxAdmin');">Close</button> 
										           		</div>
										        		</span>	
										        		<%--									        		
										        		<a tabindex="-1" class="text14" target="_blank" href="${model.taricAvgiftsberakningarCodesURL.value}" onclick="${model.taricAvgiftsberakningarCodesURL.windowOpenDimensions}" >
					            						<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
					            						</a>
					            						--%>
					            						<div class="text11" style="position: relative;" align="left"> 
					            						<span style="position:absolute;top:2px; width:250px;" id="avgift_info" class="popupWithInputText text11"  >
									           			<b>Avgift</b>
														<br/>
														<p>
															<b>(a) Kode </b>
															<br/>
															Avg.kode.
														</p>
														<p>
															<b>(b) Sekvens </b>
															<br/>
															Avg.Sekvensnr.
														</p>
														<p>
															<b>(c) Sats </b>
															<br/>
															Avg.Sats hentes fra avgiftstypen. Å legge fast sats på avgiften er kun fornuftig når den ALDRI varierer.
														</p>
														<p>
															<b>(d) Grunnlag </b>
															<br/>
															Blank i grunnlag:
															<br/><br/>
															Systemet setter grunnlag etter type avgift
															<ul>
																<li>Satstype = <b>%</b> statistisk verdi for linja – eks KA/NA.</li>
																<li>Satstype = <b>P</b> (promille) - netto vekt – eks TA.</li>
																<li>Satstype = <b>H</b> (pr/100) - netto vekt - eks UA/VA.</li>
																<li>Satstype = <b>P</b> (promille) - netto vekt - eks SJ.</li>
																<li>Satstype = <b>' '</b> (pr. stk.) - Antall fra linja – eks RA.</li>
															</ul>
														</p>
														<p>
															<b>(e) Beløp i NOK </b>
															<br/>
															Blank i beløp:
															<br/>
															AVg.beløp regnes utfra grunnlag,sats og type (%,P,H,K,blank).
														</p>
														<p>														
														<b>NB:</b>
															Vær oppmerksom på at man kan benytte særavgiftsfeltene til også å angi toll manuelt.<br/> 
															F.eks ved prosedyrekode 64/65 hvor toll, i flg TTIB § 17,skal beregnes via manuell utregning, må man angi tollen på varelinjen.<br/>
															F.eks kan man legge inn tollen som følger:<br/>
															TL 1 8,00 2500,00 200,00<br/>
															Man har her angitt avgiftskode TL (= toll) med sekvensnummer 1 (skal alltid benyttes for toll), selve tollsatsen som skal benyttes (8%), grunnlag (2500) og selve tollen (200).
														</p>
													</span>	
													</div>
										        </td>
												<%-- <c:if test="${empty model.record.svli}">  only with new lines? --%>
										        <td class="text11" colspan="5">
										        		<button style="white-space: nowrap;" name="avgCalculation" id="avgCalculation" class="buttonGrayWithGreenFrame" type="button" onClick="getAvgifterBeforeCalculation(); showPop('avgCalculationDialog');">Beregne Avg.</button>
										            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="vertical-align:middle;" >Slett Avg.</font>&nbsp;
										            	<img id="removeAvgifterImg" onClick="removeAllAvgifter();" style="vertical-align:middle;" width="16px" height="16px" src="resources/images/remove.png" border="0" alt="info">
										            	
										            	
										            	<%-- =====================================================  --%>
										            	<%-- Here we have the avgifts calculation popup window      --%>
										            	<%-- =====================================================  --%>
										            	<span style="position:absolute; left:400px; top:500px; width:700px; height:450px;" id="avgCalculationDialog" class="popupWithInputText"  >
										           		<div class="text10" align="left">
										           			<div class="ownScrollableSubWindow" style="width:680px; height:350px; margin:10px;">
									           				<nav>
										           			<table>
										           				<tr height="4"><td ></td></tr>
										           				<tr class="tableHeaderField" height="20" >
																	<td class="text12" class="tableHeaderFieldFirst">&nbsp;Kode</td>
																	<td class="text12" class="tableHeaderField">&nbsp;Beskr.</td>
																	<td class="text12" class="tableHeaderField">Sekvens</td>
																	<td align="right" class="text12" class="tableHeaderField">Sats&nbsp;</td>
																</tr>
																<tr class="tableRow">	
																	<td class="tableCellFirst"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_kode0" id="search_kode0" size="3" value=""></td>
																	<td class="tableCell"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_txt0" id="search_txt0" size="60" value=""></td>
																	<td class="tableCell">
																		<select class="text11 clazzAvgCalcSekvens" id="search_sekvensList_0" name="search_sekvensList_0">
						 													<option value="">-velg-</option>
						 							 					</select>
					 							 					</td>
					 							 					<td class="tableCell">
					 							 						<input tabindex=-1 style="text-align:right;" type="text" readonly class="inputTextNoBorder" name="search_sats0" id="search_sats0" size="10" value="">
					 							 					</td>
																</tr>	
																<tr class="tableRow">	
																	<td class="tableCellFirst"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_kode1" id="search_kode1" size="3" value=""></td>
																	<td class="tableCell"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_txt1" id="search_txt1" size="60" value=""></td>
																	<td class="tableCell">
																		<select class="text11 clazzAvgCalcSekvens" id="search_sekvensList_1" name="search_sekvensList_1">
						 													<option value="">-velg-</option>
						 							 					</select>
					 							 					</td>
					 							 					<td class="tableCell">
					 							 						<input tabindex=-1 style="text-align:right;" type="text" readonly class="inputTextNoBorder" name="search_sats1" id="search_sats1" size="10" value="">
					 							 					</td>
																</tr>
																<tr class="tableRow">	
																	<td class="tableCellFirst"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_kode2" id="search_kode2" size="3" value=""></td>
																	<td class="tableCell"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_txt2" id="search_txt2" size="60" value=""></td>
																	<td class="tableCell">
																		<select class="text11 clazzAvgCalcSekvens" id="search_sekvensList_2" name="search_sekvensList_2">
						 													<option value="">-velg-</option>
						 							 					</select>
					 							 					</td>
					 							 					<td class="tableCell">
																		<input tabindex=-1 style="text-align:right;" type="text" readonly class="inputTextNoBorder" name="search_sats2" id="search_sats2" size="10" value="">
					 							 					</td>
																</tr>
																<tr class="tableRow">	
																	<td class="tableCellFirst"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_kode3" id="search_kode3" size="3" value=""></td>
																	<td class="tableCell"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_txt3" id="search_txt3" size="60" value=""></td>
																	<td class="tableCell">
																		<select class="text11 clazzAvgCalcSekvens" id="search_sekvensList_3" name="search_sekvensList_3">
						 													<option value="">-velg-</option>
						 							 					</select>
					 							 					</td>
					 							 					<td class="tableCell">
																		<input tabindex=-1 style="text-align:right;" type="text" readonly class="inputTextNoBorder" name="search_sats3" id="search_sats3" size="10" value="">
					 							 					</td>
																</tr>
																<tr class="tableRow">	
																	<td class="tableCellFirst"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_kode4" id="search_kode4" size="3" value=""></td>
																	<td class="tableCell"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_txt4" id="search_txt4" size="52" value=""></td>
																	<td class="tableCell">
																		<select class="text11 clazzAvgCalcSekvens" id="search_sekvensList_4" name="search_sekvensList_4">
						 													<option value="">-velg-</option>
						 							 					</select>
					 							 					</td>
					 							 					<td class="tableCell">
																		<input tabindex=-1 style="text-align:right;" type="text" readonly class="inputTextNoBorder" name="search_sats4" id="search_sats4" size="10" value="">
					 							 					</td>
																</tr>
																<tr class="tableRow">	
																	<td class="tableCellFirst"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_kode5" id="search_kode5" size="3" value=""></td>
																	<td class="tableCell"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_txt5" id="search_txt5" size="52" value=""></td>
																	<td class="tableCell">
																		<select class="text11 clazzAvgCalcSekvens" id="search_sekvensList_5" name="search_sekvensList_5">
						 													<option value="">-velg-</option>
						 							 					</select>
					 							 					</td>
					 							 					<td class="tableCell">
																		<input tabindex=-1 style="text-align:right;" type="text" readonly class="inputTextNoBorder" name="search_sats5" id="search_sats5" size="10" value="">
					 							 					</td>
																</tr>
																<tr class="tableRow">	
																	<td class="tableCellFirst"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_kode6" id="search_kode6" size="3" value=""></td>
																	<td class="tableCell"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_txt6" id="search_txt6" size="60" value=""></td>
																	<td class="tableCell">
																		<select class="text11 clazzAvgCalcSekvens" id="search_sekvensList_6" name="search_sekvensList_6">
						 													<option value="">-velg-</option>
						 							 					</select>
					 							 					</td>
					 							 					<td class="tableCell">
																		<input tabindex=-1 style="text-align:right;" type="text" readonly class="inputTextNoBorder" name="search_sats6" id="search_sats6" size="10" value="">
					 							 					</td>
																</tr>
																<tr class="tableRow">	
																	<td class="tableCellFirst"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_kode7" id="search_kode7" size="3" value=""></td>
																	<td class="tableCell"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_txt7" id="search_txt7" size="60" value=""></td>
																	<td class="tableCell">
																		<select class="text11 clazzAvgCalcSekvens" id="search_sekvensList_7" name="search_sekvensList_7">
						 													<option value="">-velg-</option>
						 							 					</select>
					 							 					</td>
					 							 					<td class="tableCell">
																		<input tabindex=-1 style="text-align:right;" type="text" readonly class="inputTextNoBorder" name="search_sats7" id="search_sats7" size="10" value="">
					 							 					</td>
																</tr>
																<tr class="tableRow">	
																	<td class="tableCellFirst"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_kode8" id="search_kode8" size="3" value=""></td>
																	<td class="tableCell"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_txt8" id="search_txt8" size="60" value=""></td>
																	<td class="tableCell">
																		<select class="text11 clazzAvgCalcSekvens" id="search_sekvensList_8" name="search_sekvensList_8">
						 													<option value="">-velg-</option>
						 							 					</select>
					 							 					</td>
					 							 					<td class="tableCell">
																		<input tabindex=-1 style="text-align:right;" type="text" readonly class="inputTextNoBorder" name="search_sats8" id="search_sats8" size="10" value="">
					 							 					</td>
																</tr>
																<tr class="tableRow">	
																	<td class="tableCellFirst"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_kode9" id="search_kode9" size="3" value=""></td>
																	<td class="tableCell"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_txt9" id="search_txt9" size="60" value=""></td>
																	<td class="tableCell">
																		<select class="text11 clazzAvgCalcSekvens" id="search_sekvensList_9" name="search_sekvensList_9">
						 													<option value="">-velg-</option>
						 							 					</select>
					 							 					</td>
					 							 					<td class="tableCell">
																		<input tabindex=-1 style="text-align:right;" type="text" readonly class="inputTextNoBorder" name="search_sats9" id="search_sats9" size="10" value="">
					 							 					</td>
																</tr>
																<tr class="tableRow">	
																	<td class="tableCellFirst"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_kode10" id="search_kode10" size="3" value=""></td>
																	<td class="tableCell"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_txt10" id="search_txt9" size="60" value=""></td>
																	<td class="tableCell">
																		<select class="text11 clazzAvgCalcSekvens" id="search_sekvensList_10" name="search_sekvensList_10">
						 													<option value="">-velg-</option>
						 							 					</select>
					 							 					</td>
					 							 					<td class="tableCell">
																		<input tabindex=-1 style="text-align:right;" type="text" readonly class="inputTextNoBorder" name="search_sats10" id="search_sats10" size="10" value="">
					 							 					</td>
																</tr>
																<tr class="tableRow">	
																	<td class="tableCellFirst"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_kode11" id="search_kode11" size="3" value=""></td>
																	<td class="tableCell"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_txt11" id="search_txt11" size="60" value=""></td>
																	<td class="tableCell">
																		<select class="text11 clazzAvgCalcSekvens" id="search_sekvensList_11" name="search_sekvensList_11">
						 													<option value="">-velg-</option>
						 							 					</select>
					 							 					</td>
					 							 					<td class="tableCell">
																		<input tabindex=-1 style="text-align:right;" type="text" readonly class="inputTextNoBorder" name="search_sats11" id="search_sats11" size="10" value="">
					 							 					</td>
																</tr>
																<tr class="tableRow">	
																	<td class="tableCellFirst"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_kode12" id="search_kode12" size="3" value=""></td>
																	<td class="tableCell"><input tabindex=-1 type="text" readonly class="inputTextNoBorder" name="search_txt12" id="search_txt12" size="60" value=""></td>
																	<td class="tableCell">
																		<select class="text11 clazzAvgCalcSekvens" id="search_sekvensList_12" name="search_sekvensList_12">
						 													<option value="">-velg-</option>
						 							 					</select>
					 							 					</td>
					 							 					<td class="tableCell">
																		<input tabindex=-1 style="text-align:right;" type="text" readonly class="inputTextNoBorder" name="search_sats12" id="search_sats12" size="10" value="">
					 							 					</td>
																</tr>
																<tr height="4"><td ></td></tr>
										           			</table>
										           			</nav>
										           			</div>
															<table width="60%" align="left" border="0">
																<tr align="left" >
																	<td >&nbsp;
																		<button name="avgCalculationCloseOk" id="avgCalculationCloseOk" class="buttonGrayInsideDivPopup" type="button" onClick="setAvgifterAfterCalculation(); hidePop('avgCalculationDialog');"><spring:message code="systema.tvinn.sad.import.ok"/></button>
																		&nbsp;<button name="avgCalculationCloseCancel" id="avgCalculationCloseCancel" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('avgCalculationDialog');"><spring:message code="systema.tvinn.sad.import.cancel"/></button>
																	</td>
																</tr>
															</table>
														</div>
													</span>														        
										        </td>
										        <%-- </c:if> --%>
									        </tr>
											<tr height="5"><td></td></tr>
											 
											<tr>
												<td class="text12" align="left" >&nbsp;<span title="wg1-wg8(svkdaae)">Kode</span>
													<a tabindex="-1" id="svkdaaeWg1Wg8IdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
													<%--
													<img onClick="showPop('kodeInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
													<span style="position:absolute; left:220px; top:700px; width:450px; height:200px;" id="kodeInfo" class="popupWithInputText"  >
										           		<div class="text10" align="left">
									           				<select class="text11" id="kode" name="kode" size="8" onDblClick="hidePop('kodeInfo');">
										           				<c:forEach var="code" items="${model.avgiftsCodeList}" >
										 				  			<option value="${code.zskv}">${code.zskv}&nbsp;${code.zkod}&nbsp;${code.ztxt}</option>
																</c:forEach>
										           			</select>
										           			<table width="100%" align="left" border="0">
																<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
																<tr align="left" >
																	<td class="text11">&nbsp;<button name="kodeButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('kodeInfo');">&nbsp;<spring:message code="systema.tvinn.sad.import.ok"/></button> 
																	</td>
																</tr>
															</table>
														</div>
													</span>
													 --%>
													 
												</td>
												<td class="text12" align="left" >&nbsp;<span title="wh1-wh8(svkdsae)">Sekvens</span>
													<a tabindex="-1" id="svkdsaeWh1Wh8IdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
													<%--
													<img onClick="showPop('sekvInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
													<span style="position:absolute; left:220px; top:700px; width:450px; height:200px;" id="sekvInfo" class="popupWithInputText"  >
										           		<div class="text10" align="left">
									           				<select class="text11" id="sekv" name="sekv" size="8" onDblClick="hidePop('sekvInfo');">
										           				<c:forEach var="code" items="${model.avgiftsCodeListB}" >
										 				  			<option value="${code.zskv}">${code.zskv}&nbsp;${code.zkod}&nbsp;${code.ztxt}</option>
																</c:forEach>
										           			</select>
										           			<table width="100%" align="left" border="0">
																<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
																<tr align="left" >
																	<td class="text11">&nbsp;<button name="sekvButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('sekvInfo');">&nbsp;<spring:message code="systema.tvinn.sad.import.ok"/></button> 
																	</td>
																</tr>
															</table>
														</div>
													</span>	
													 --%>
												</td>
		               							<td class="text12" align="left" >&nbsp;<span title="wk1-wk8(svblsae)">Sats</span></td>
		               							<td class="text12" align="left" >&nbsp;<span title="wj1-wj8(svblgae)">Grunnlag</span></td>
		               							<td class="text12" align="left" >&nbsp;<span title="wi1-wi8(svblae)">Beløp (NOK)</span></td>
		               						</tr>
											<tr>
												<td class="text12MediumBlue">
													<select name="wg1" id="wg1">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.avgiftsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wg1 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
												<td class="text12MediumBlue">
													<select name="wh1" id="wh1">
										            		<option value="">-Velg-</option>
										            		<c:forEach var="code" items="${model.avgiftsCodeListB}" >
					                                	 		<option value="${code.zskv}"<c:if test="${model.record.wh1 == code.zskv}"> selected </c:if> >${code.zskv}</option>
														</c:forEach> 
													</select>
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wk1" id="wk1" size="10" maxlength="9" value="${model.record.wk1}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wj1" id="wj1" size="10" maxlength="11" value="${model.record.wj1}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wi1" id="wi1" size="10" maxlength="9" value="${model.record.wi1}">
												</td>
											</tr>
											<tr>
												<td class="text12MediumBlue">
													<select name="wg2" id="wg2">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.avgiftsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wg2 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
												<td class="text12MediumBlue">
													<select name="wh2" id="wh2">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.avgiftsCodeListB}" >
					                                	 		<option value="${code.zskv}"<c:if test="${model.record.wh2 == code.zskv}"> selected </c:if> >${code.zskv}</option>
														</c:forEach> 
													</select>
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wk2" id="wk2" size="10" maxlength="9" value="${model.record.wk2}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wj2" id="wj2" size="10" maxlength="11" value="${model.record.wj2}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wi2" id="wi2" size="10" maxlength="9" value="${model.record.wi2}">
												</td>
											</tr>
											<tr>
												<td class="text12MediumBlue">
													<select name="wg3" id="wg3">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.avgiftsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wg3 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
												<td class="text12MediumBlue">
													<select name="wh3" id="wh3">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.avgiftsCodeListB}" >
					                                	 		<option value="${code.zskv}"<c:if test="${model.record.wh3 == code.zskv}"> selected </c:if> >${code.zskv}</option>
														</c:forEach> 
													</select>
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wk3" id="wk3" size="10" maxlength="9" value="${model.record.wk3}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wj3" id="wj3" size="10" maxlength="11" value="${model.record.wj3}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wi3" id="wi3" size="10" maxlength="9" value="${model.record.wi3}">
												</td>
											</tr>
											<tr>
												<td class="text12MediumBlue">
													<select name="wg4" id="wg4">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.avgiftsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wg4 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
												<td class="text12MediumBlue">
													<select name="wh4" id="wh4">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.avgiftsCodeListB}" >
					                                	 		<option value="${code.zskv}"<c:if test="${model.record.wh4 == code.zskv}"> selected </c:if> >${code.zskv}</option>
														</c:forEach> 
													</select>
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wk4" id="wk4" size="10" maxlength="9" value="${model.record.wk4}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wj4" id="wj4" size="10" maxlength="11" value="${model.record.wj4}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wi4" id="wi4" size="10" maxlength="9" value="${model.record.wi4}">
												</td>
											</tr>
											<tr>
												<td class="text12MediumBlue">
													<select name="wg5" id="wg5">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.avgiftsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wg5 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
												<td class="text12MediumBlue">
													<select name="wh5" id="wh5">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.avgiftsCodeListB}" >
					                                	 		<option value="${code.zskv}"<c:if test="${model.record.wh5 == code.zskv}"> selected </c:if> >${code.zskv}</option>
														</c:forEach> 
													</select>
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wk5" id="wk5" size="10" maxlength="9" value="${model.record.wk5}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wj5" id="wj5" size="10" maxlength="11" value="${model.record.wj5}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wi5" id="wi5" size="10" maxlength="9" value="${model.record.wi5}">
												</td>
											</tr>
											<tr>
												<td class="text12MediumBlue">
													<select name="wg6" id="wg6">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.avgiftsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wg6 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
												<td class="text12MediumBlue">
													<select name="wh6" id="wh6">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.avgiftsCodeListB}" >
					                                	 		<option value="${code.zskv}"<c:if test="${model.record.wh6 == code.zskv}"> selected </c:if> >${code.zskv}</option>
														</c:forEach> 
													</select>
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wk6" id="wk6" size="10" maxlength="9" value="${model.record.wk6}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wj6" id="wj6" size="10" maxlength="11" value="${model.record.wj6}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wi6" id="wi6" size="10" maxlength="9" value="${model.record.wi6}">
												</td>
											</tr>
											<tr>
												<td class="text12MediumBlue">
													<select name="wg7" id="wg7">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.avgiftsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wg7 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
												<td class="text12MediumBlue">
													<select name="wh7" id="wh7">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.avgiftsCodeListB}" >
					                                	 		<option value="${code.zskv}"<c:if test="${model.record.wh7 == code.zskv}"> selected </c:if> >${code.zskv}</option>
														</c:forEach> 
													</select>
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wk7" id="wk7" size="10" maxlength="9" value="${model.record.wk7}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wj7" id="wj7" size="10" maxlength="11" value="${model.record.wj7}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wi7" id="wi7" size="10" maxlength="9" value="${model.record.wi7}">
												</td>
											</tr>
											<tr>
												<td class="text12MediumBlue">
													<select name="wg8" id="wg8">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.avgiftsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wg8 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
												<td class="text12MediumBlue">
													<select name="wh8" id="wh8">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.avgiftsCodeListB}" >
					                                	 		<option value="${code.zskv}"<c:if test="${model.record.wh8 == code.zskv}"> selected </c:if> >${code.zskv}</option>
														</c:forEach> 
													</select>
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wk8" id="wk8" size="10" maxlength="9" value="${model.record.wk8}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wj8" id="wj8" size="10" maxlength="11" value="${model.record.wj8}">
												</td>
												<td class="text12MediumBlue">
													<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="wi8" id="wi8" size="10" maxlength="9" value="${model.record.wi8}">
												</td>
											</tr>
											
											<tr>
												<td class="text12MediumBlue">
													&nbsp;
												</td>
												<td class="text12MediumBlue">
													&nbsp;
												</td>
												<td class="text12MediumBlue">
													&nbsp;
												</td>
												<td class="text12MediumBlue">
													&nbsp;
												</td>
												<%--
												<td class="text12MediumBlue">
													<input style="display: none;" class="inputFormSubmitStd" type="button" id="calcAvgifterAjax" value='Räkna om'>
													<input class="inputFormSubmitStd" type="button" id="calcAvgifterDeleteAll" value='Radera värden'>
													 
													<input class="inputFormSubmitStd" type="button" id="calcAvgifterAjax" value="Beräkna">
													
												</td>
												 --%>
											</tr>
										</table>
							    			</td>
										</tr>
										<tr height="10"><td class="text" align="left"></td></tr>
			
										<tr>
										<td colspan="4" style="vertical-align: top;">
										<table class="tableBorderWithRoundCornersGray" style="width: 95%; margin-left:auto;margin-right:auto;">
									        <tr >
										        <td class="text12Bold">
										        		&nbsp;<img onMouseOver="showPop('toa_info');" onMouseOut="hidePop('toa_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										        		<b>44.a</b>&nbsp;Tilleggsopplysn<label onClick="showPop('debugPrintlnAjaxAdmin');" >i</label>nger
													<span style="position:absolute; left:200px; top:500px; width:600px; height:180px;" id="debugPrintlnAjaxAdmin" class="popupWithInputText"  >
										           		<div class="text11" align="left">
										           			<label id="debugPrintlnAjaxInfo"></label>
										           			&nbsp;&nbsp;&nbsp;&nbsp;<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('debugPrintlnAjaxAdmin');">Close</button> 
										           		</div>
										        		</span>										        		
										        		
										        <div class="text11" style="position: relative;" align="left">
									         	<span style="position:absolute; top:2px; width:250px;" id="toa_info" class="popupWithInputText text11"  >
								           			<b>44.a Tilleggsopplysningar</b>
													<br/><br/>
													Her tastes lisensnummer,eurnr, "Fakturaerklæring" etc. Opplysningene kvalifiseres med kode i felt 'Rf'.
													<br/>
													Dersom en sender på TVINN eller benytter dataliste (i stedet for tillegsark), er max feltlengde 17. 
													<p>
													<b>TOLL:</b>
													<ul>
														<li>Når TOLLNEDSETTELSE har verdi <b>E,H,F</b> eller <b>R</b> (generelle tollnedsettelsesskriv), forlanges IKKE T.O.</li>
														<li>Når TOLLNEDSETTELSE har verdi <b>S</b> (BREV fra TAD om tollneds./fritak), forlanges T.O.</li>
														<li>Ved TVINN - UND(registrert i TVINN) eller PRD(ikke reg.) i Rf-feltet.</li>
														<li>Når PREFERANSE er oppgitt skal Tillegsoppl. fylles ut m.h.t. opprinnnelsesbevis. "EUR...", "Fakturaerklæring.",...</li>             
														<li>Ved TVINN settes da "SER" i Rf-feltet.</li>
														<li>Når PREFERANSE har verdi <b>J</b> eller <b>N</b> skal Tillegsoppl. <b>IKKE fylles ut</b> m.h.t. opprinnelsesbevis.</li>
													</ul>
													OBS! Kombinasjon av tollnedsettelse og preferanse kan IKKE FOREKOMME. Er TN-rubrikken utfylt skal det være "N" (blank) i preferanse.
													</p>
													<p>
													<b>SÆRAVGIFTER:</b>
													Når sats er redusert i forhold til normalen, skal skriv som gir grunnlag for reduksjonen oppgis. 
													I TVINN settes UND(reg.) /PRD(ureg.) i Rf-feltet.
													</p>
													<p>
													<b>UTSATT AVGIFTSBETALING:</b>
													Har vareeieren tillatelse til utsatt avgiftsinnbetaling, må det refereres til nummeret på dette skrivet. Nummeret skal begynne med bokstaven 'U'. I TVINN settes UTB i feltet Rf.
													</p>
													<p>
													<b>OPPHEVELSE AV RESTRIKSJONER OG/ELLER LISENSKRAV:</b>
													Ved opphevelse av restriksjoner/lisenkrav, må aktuelt skriv refereres til.
													I TVINN settes UND(reg.) / PRD(ureg.) i Rf.-feltet.
													</p>
												</span>
												</div>
												</td>
									        </tr>
											<tr height="5"><td></td></tr>
											
											<tr>
												<td class="text12" align="left" >&nbsp;<span title="wf1-wf5(svtoa)">Tilleggsoppl.</span></td>
			               							<td class="text12" align="left" >&nbsp;<span title="we1-we5(svcref)">Ref.</span>
			               								<a tabindex="-1" id="svcrefWe1We5IdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
														</a>
			               								<%--
				               							<img onClick="showPop('docSertImportRefInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
														<span style="position:absolute; left:520px; top:1000px; width:450px; height:180px;" id="docSertImportRefInfo" class="popupWithInputText"  >
											           		<div class="text10" align="left">
										           				<select class="text11" id="docSertImportRef" name="docSertImportRef" size="5" onDblClick="hidePop('docSertImportRefInfo');">
											           				<c:forEach var="code" items="${model.docSertImportCodeList}" >
											 				  			<option value="${code.zkod}">${code.zkod}&nbsp;${code.ztxt}</option>
																	</c:forEach>
											           			</select>
											           			<table width="100%" align="left" border="0">
																	<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
																	<tr align="left" >
																		<td class="text11">&nbsp;<button name="docSertImportRefInfoButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('docSertImportRefInfo');">&nbsp;<spring:message code="systema.tvinn.sad.import.ok"/></button> 
																		</td>
																	</tr>
																</table>
															</div>
														</span>	
			               								--%>
			               							</td>
			               						</tr>
												<tr>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wf1" id="wf1" size="46" maxlength="45" value="${model.record.wf1}">
												</td>
												<td class="text12MediumBlue">
													<select name="we1" id="we1">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertImportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we1 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
												<tr>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wf2" id="wf2" size="46" maxlength="45" value="${model.record.wf2}">
												</td>
												<td class="text12MediumBlue">
													<select name="we2" id="we2">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertImportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we2 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
												<tr>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wf3" id="wf3" size="46" maxlength="45" value="${model.record.wf3}">
												</td>
												<td class="text12MediumBlue">
													<select name="we3" id="we3">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertImportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we3 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
												<tr>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wf4" id="wf4" size="46" maxlength="45" value="${model.record.wf4}">
												</td>
												<td class="text12MediumBlue">
													<select name="we4" id="we4">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertImportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we4 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
											<tr>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wf5" id="wf5" size="46" maxlength="45" value="${model.record.wf5}">
												</td>
												<td class="text12MediumBlue">
													<select name="we5" id="we5">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertImportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we5 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
												<tr>
												<td class="text12MediumBlue">
													&nbsp;
												</td>
												<td class="text12MediumBlue">
													&nbsp;
												</td>
											</tr>
											 
										</table>
							    			</td>
							    			
							    			<td colspan="4" style="vertical-align: top;">
										<table class="tableBorderWithRoundCornersGray" style="width: 95%; margin-left:auto;margin-right:auto;">
									        <tr >
										        <td class="text12Bold">
										        		&nbsp;<img onMouseOver="showPop('tob_info');" onMouseOut="hidePop('tob_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										        		<b>44.b</b>&nbsp;Tilleggsopplysninger
										        <div class="text11" style="position: relative;" align="left">
									         	<span style="position:absolute;top:2px; width:250px;" id="tob_info" class="popupWithInputText text11"  >
								           			<b>44.b Tilleggsopplysningar</b>
													<br/><br/>
													Her tastes lisensnummer,eurnr, "Fakturaerklæring" etc. Opplysningene kvalifiseres med kode i felt 'Rf'.
													<br/>
													Dersom en sender på TVINN eller benytter dataliste (i stedet for tillegsark), er max feltlengde 17. 
													<p>
													<b>TOLL:</b>
													<ul>
														<li>Når TOLLNEDSETTELSE har verdi <b>E,H,F</b> eller <b>R</b> (generelle tollnedsettelsesskriv), forlanges IKKE T.O.</li>
														<li>Når TOLLNEDSETTELSE har verdi <b>S</b> (BREV fra TAD om tollneds./fritak), forlanges T.O.</li>
														<li>Ved TVINN - UND(registrert i TVINN) eller PRD(ikke reg.) i Rf-feltet.</li>
														<li>Når PREFERANSE er oppgitt skal Tillegsoppl. fylles ut m.h.t. opprinnnelsesbevis. "EUR...", "Fakturaerklæring.",...</li>             
														<li>Ved TVINN settes da "SER" i Rf-feltet.</li>
														<li>Når PREFERANSE har verdi <b>J</b> eller <b>N</b> skal Tillegsoppl. <b>IKKE fylles ut</b> m.h.t. opprinnelsesbevis.</li>
													</ul>
													OBS! Kombinasjon av tollnedsettelse og preferanse kan IKKE FOREKOMME. Er TN-rubrikken utfylt skal det være "N" (blank) i preferanse.
													</p>
													<p>
													<b>SÆRAVGIFTER:</b>
													Når sats er redusert i forhold til normalen, skal skriv som gir grunnlag for reduksjonen oppgis. 
													I TVINN settes UND(reg.) /PRD(ureg.) i Rf-feltet.
													</p>
													<p>
													<b>UTSATT AVGIFTSBETALING:</b>
													Har vareeieren tillatelse til utsatt avgiftsinnbetaling, må det refereres til nummeret på dette skrivet. Nummeret skal begynne med bokstaven 'U'. I TVINN settes UTB i feltet Rf.
													</p>
													<p>
													<b>OPPHEVELSE AV RESTRIKSJONER OG/ELLER LISENSKRAV:</b>
													Ved opphevelse av restriksjoner/lisenkrav, må aktuelt skriv refereres til.
													I TVINN settes UND(reg.) / PRD(ureg.) i Rf.-feltet.
													</p>
												</span>
												</div>
												</td>
									        </tr>
											<tr height="5"><td></td></tr>
											<tr>
												<td class="text12" align="left" >&nbsp;<span title="wf6-wf10(svtoa)">Tilleggsoppl.</span></td>
			               							<td class="text12" align="left" >&nbsp;<span title="we6-we10(svcref)">Ref.</span>
			               								<a tabindex="-1" id="svcrefWe6We10IdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
														</a>
														<%--
			               								<img onClick="showPop('docSertImportRefInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
			               								--%>
			               							</td>
			               						</tr>
												<tr>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wf6" id="wf6" size="46" maxlength="45" value="${model.record.wf6}">
												</td>
												<td class="text12MediumBlue">
													<select name="we6" id="we6">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertImportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we6 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
												<tr>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wf7" id="wf7" size="46" maxlength="45" value="${model.record.wf7}">
												</td>
												<td class="text12MediumBlue">
													<select name="we7" id="we7">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertImportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we7 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
												<tr>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wf8" id="wf8" size="46" maxlength="45" value="${model.record.wf8}">
												</td>
												<td class="text12MediumBlue">
													<select name="we8" id="we8">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertImportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we8 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
												<tr>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wf9" id="wf9" size="46" maxlength="45" value="${model.record.wf9}">
												</td>
												<td class="text12MediumBlue">
													<select name="we9" id="we9">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertImportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we9 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
											<tr>
												<td class="text12MediumBlue">
													<input type="text" class="inputText" name="wf10" id="wf10" size="46" maxlength="45" value="${model.record.wf10}">
												</td>
												<td class="text12MediumBlue">
													<select name="we10" id="we10">
										            		<option value="">-Velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertImportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we10 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
												<tr>
												<td class="text12MediumBlue">
													&nbsp;
												</td>
												<td class="text12MediumBlue">
													&nbsp;
												</td>
											</tr>
											
										</table>
							    			</td>
								    </tr>
						        </table>
					        </td>
				        </tr>
					    <tr height="10"><td colspan="2" ></td></tr>
        	        </table>
        	        </form>
		        </td>
		    </tr>
			<tr height="20"><td colspan="2" ></td></tr>
			
            <tr height="30"><td></td></tr>
		</table>
		</td>
		</tr>
		
		
		<tr>
		<td>
			<div id="dialogKundensVareregister" title="Dialog">
				<form  action="tvinnsadimport_edit_items_doUpdateKundensVareregister.do" name="updateKundensVareregisterForm" id="updateKundensVareregisterForm" method="post">
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="varebe" id="varebe" value=""/>
				 	<input type="hidden" name="w2tn" id="w2tn" value=""/>
				 	<input type="hidden" name="w2pre" id="w2pre" value=""/>
				 	<input type="hidden" name="w2belt" id="w2belt" value=""/>
				 	<input type="hidden" name="w2vktb" id="w2vktb" value=""/>
				 	<input type="hidden" name="w2vktn" id="w2vktn" value=""/>
				 	<input type="hidden" name="w2ntm" id="w2ntm" value=""/>
				 	<input type="hidden" name="w2pva" id="w2pva" value=""/>
				 	<input type="hidden" name="w2as" id="w2as" value=""/>
				 	<input type="hidden" name="w2mfr" id="w2mfr" value=""/>
				 	
					<table>
						<tr>
							<td colspan="6">Beskrivning:&nbsp;<label class="text12Bold" id="svvt" name="svvt"></label></td>
						</tr>
						<tr height="5"><td></td></tr>
						
						<tr>
							<td class="tableHeaderField" align="left" >&nbsp;VF</td>
          					<td class="tableHeaderFieldFirst" align="left" >&nbsp;Oppr.land</td>
          					<td class="tableHeaderField" align="left" >&nbsp;Varenr.</td>
          					<td class="tableHeaderField" align="left" >&nbsp;Kundens art.nr</td>
          					<td class="tableHeaderField" align="left" >&nbsp;Kundenr.</td>
          				</tr>
						<tr>
							<td class="tableCell11"><input readonly class="inputTextReadOnly" type="text"  id="w2vf" name="w2vf" size="10px" value=""></input></td>
							<td class="tableCellFirst11"><input class="inputTextReadOnly" type="text" id="w2lk" name="w2lk" size="10px" value=""></input></td>
							<td class="tableCell11"><input readonly class="inputTextReadOnly" type="text"  id="w2vnti" name="w2vnti" size="10px" value=""></input></td>
							<td class="tableCell11"><input  class="inputTextMediumBlue" type="text"  id="varenr" name="varenr" size="10px" value=""></input></td>
							<td class="tableCell11"><input  class="inputTextMediumBlue" type="text"  id="levenr" name="levenr" size="10px" value=""></input></td>
						</tr>
						
					</table>
				</form>
			</div>
		</td>
		</tr>
	</table>    
	
	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

