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
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadexport_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
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
	

<table width="100%" cellspacing="0" border="0" cellpadding="0">
 <tr>
 <td>	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2">
			<td>
				<input type="hidden" name="modelStatus" id="modelStatus" value='${model.record.sest}'>
			</td>
		</tr>
		<tr height="25"> 
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkTopicList" style="display:block;" 
					<c:choose>
						<c:when test="${empty model.record.sesg}">href="tvinnsadexport.do?action=doFind&sg=${user.tvinnSadSign}"</c:when>
						<c:otherwise>href="tvinnsadexport.do?action=doFind&sg=${model.record.sesg}"</c:otherwise>
					</c:choose> >
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.export.list.tab"/></font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<c:choose> 
			    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
					<td width="12%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;<spring:message code="systema.tvinn.sad.export.created.mastertopic.tab"/>
						</font>
						<font class="text12MediumBlue">[${model.record.setdn}]</font>
						<c:if test="${ model.record.sest == 'M' || empty  model.record.sest}">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkInvoices" style="display:block;" href="tvinnsadexport_edit_finansopplysninger.do?action=doFetch&avd=${ model.record.seavd}&sign=${ model.record.sesg}
									&opd=${ model.record.setdn}&status=${ model.record.sest}&fabl=${ model.record.sebel1}&o2_sest=${ model.record.o2_sest}&o2_sedt=${ model.record.o2_sedt}&o2_semf=${ model.record.o2_semf}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.export.finansopplys.createnew.tab"/>
							</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkNotis" style="display:block;" href="editNotisblock.do?action=doFetch&subsys=sade&orig=topic&avd=${ model.record.seavd}&sign=${ model.record.sesg}
													&opd=${model.record.setdn}&o2_sest=${ model.record.o2_sest}&o2_sedt=${ model.record.o2_sedt}&o2_semf=${ model.record.o2_semf}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.export.notisblock.createnew.tab"/>
							</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkItemLines" style="display:block;" href="tvinnsadexport_edit_items.do?action=doFetch&avd=${ model.record.seavd}&sign=${ model.record.sesg}
													&opd=${ model.record.setdn}&status=${model.record.sest}&datum=${model.record.sedt}&fabl=${ XX.dkih_222}&o2_sest=${ model.record.o2_sest}&o2_sedt=${ model.record.o2_sedt}&o2_semf=${ model.record.o2_semf}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.export.item.createnew.tab"/>
							</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkLogging" style="display:block;" href="tvinnsadexport_logging.do?avd=${ model.record.seavd}&sign=${ model.record.sesg}
													&opd=${model.record.setdn}&status=${model.record.sest}&datum=${model.record.sedt}&o2_sest=${ model.record.o2_sest}&o2_sedt=${ model.record.o2_sedt}&o2_semf=${ model.record.o2_semf}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.export.logging.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkArchive" style="display:block;" href="tvinnsadexport_archive.do?avd=${model.record.seavd}&sign=${model.record.sesg}
							&opd=${model.record.setdn}&status=${model.record.sest}&datum=${model.record.sedt}&o2_sest=${ model.record.o2_sest}&o2_sedt=${ model.record.o2_sedt}&o2_semf=${ model.record.o2_semf}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.export.archive.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
						</a>
					</td>
					
					<%-- We must check if this tolddkl. qualifies for omberegning 
					<c:if test="${ ( not empty model.record.setll && 
								   ( (empty model.record.sest || model.record.sest == 'P' || model.record.sest == 'U'))  || ( empty model.record.o2_sest  || model.record.o2_sest == 'M' || model.record.o2_sest == 'Z' || model.record.o2_sest == 'Q' || model.record.o2_sest == 'P')  )}">
					--%>
					<c:if test="${ not empty model.record.setll }">
						<c:if test="${ (empty model.record.sest || model.record.sest == 'P' || model.record.sest == 'U')  || ( empty model.record.o2_sest  || model.record.o2_sest == 'M' || model.record.o2_sest == 'Z' || model.record.o2_sest == 'Q' || model.record.o2_sest == 'P')  }">
						<%-- ------------------------------------------------------------------------------------------ --%>
						<%-- We must redirect to different behaviors if there is an existent omberegning already or not --%>
						<%-- ------------------------------------------------------------------------------------------ --%>
						<c:choose> 
							<c:when test="${ (model.record.o2_sest == 'P' || model.record.o2_sest == 'Z' ) &&  not empty model.record.o2_semf }">
								<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
								<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
									<a id="alinkOmberegningPaOmberegning" style="display:block;" runat="server" href="#">
										<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.export.omberegning.mastertopic.tab"/></font>
									</a>
								</td>
							</c:when>
							<c:otherwise>
								<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
								<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
									<a id="alinkOmberegning" style="display:block;" href="tvinnsadexport_edit_omberegning.do?action=doFetch&avd=${ model.record.seavd}&sign=${ model.record.sesg}
																&opd=${ model.record.setdn}&status=${ model.record.sest}&fabl=${ model.record.sebel1}&o2_sest=${ model.record.o2_sest}&o2_sedt=${ model.record.o2_sedt}&o2_semf=${ model.record.o2_semf}">
										<font class="tabDisabledLink">
											&nbsp;<spring:message code="systema.tvinn.sad.export.omberegning.mastertopic.tab"/>
										</font>
									</a>
								</td>
							</c:otherwise>
						</c:choose>
						</c:if>	
					</c:if>
					
					<td width="25%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:when>
				<c:otherwise>
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.export.createnew.tab"/></font>
						<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
						
					</td>
					<td width="60%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:otherwise>
			</c:choose>
			
			
		</tr>
	</table>
	</td>
 </tr>
 
 <tr>
 	<td>
	<%-- --------------------------- --%>	
 	<%-- tab area container PRIMARY  --%>
	<%-- --------------------------- --%>
	<form name="sadExportSaveNewTopicForm" id="sadExportSaveNewTopicForm" method="post">
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
			<%-- --- HIDDEN FORM FIELDS (not visible in form but important with an UPDATE ----- --%>			
			<%-- general (from user profile) --%>
			<input type="hidden" name="action" id="action" value='doUpdate'>
			<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
			<input type="hidden" name="opd" id="opd" value='${model.record.setdn}'>
			<%-- topic specific (setdn and refnr) --%>
			<input type="hidden" name="seavd" id="seavd" value='${model.record.seavd}'>
			<input type="hidden" name="setdn" id="setdn" value='${model.record.setdn}'>
			<input type="hidden" name="sest" id="sest" value='${model.record.sest}'>
			<input type="hidden" name="sedt" id="sedt" value='${model.record.sedt}'>
			<input type="hidden" name="sedst" id="sedst" value='${model.record.sedst}'>
			<input type="hidden" name="setarf" id="setarf" value='${model.record.setarf}'>
			
		<tr height="4">
			<td colspan="2">&nbsp;
				<%-- test indicator /per avdelning --%> 
				<c:forEach var="record" items="${avdListSessionTestFlag}" >
					<c:if test="${record.avd == model.record.seavd}">	
						<c:if test="${record.tst == '2'}">&nbsp;&nbsp;	
							<c:set var="isTestAvd" value="2" scope="request" />
						</c:if>
					</c:if>
				</c:forEach>		
			</td>
		</tr>
		
		<c:choose>
		<%-- UPDATE MODE --%> 
	    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
	    		<input type="hidden" name="avd" id="avd" value='${model.record.seavd}'>
			<input type="hidden" name="sesg" id="sesg" value='${model.record.sesg}'>
			<tr >
				<td align="left" class="text12MediumBlue" >
					&nbsp;&nbsp;&nbsp;&nbsp;<span title="seavd">Avdeling:</span>&nbsp;<b>${model.record.seavd}</b>&nbsp;&nbsp;<span title="setdn">Tolldeknr:&nbsp;</span><b>${model.record.setdn}</b>
					&nbsp;&nbsp;<span title="sesg">Sign:</span>&nbsp;<b>${model.record.sesg}</b>
					&nbsp;&nbsp;
					<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					Stat<a tabindex=-1 id="updateStatusLink" name="updateStatusLink" runat="server" href="#"><font class="text12MediumBlue">u</font></a>s:
					<b>
						<c:choose>
							<c:when test="${empty model.record.sest}">
								&nbsp;
							</c:when>
							<c:otherwise>
								${model.record.sest}
							</c:otherwise>
						</c:choose>
					</b>
					&nbsp;<span title="sedt">Reg.dato:</span>&nbsp;<b>${model.record.sedt}</b>
					<div class="text11" style="position: relative;" align="left">
					<span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
		           		Bare status <b>M</b> (Fejl) eller <b>' '</b> kan redigeres. 
		           			<ul>
		           				<li><b>' '</b>&nbsp;Deklarasjonen er åpen for endring.</li>
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
					</span>	
					</div>											
				</td>
				<td align="right" valign="top" >
					<input tabindex=-1 type="checkbox" name="semi" id="semi" value="I" <c:if test="${model.record.semi == 'I'}"> checked </c:if> ><font class="text12MediumBlue"><b>Foreløpig</b></font>&nbsp;&nbsp;&nbsp;
					<c:if test="${'2' != isTestAvd}">
						<input tabindex=-1 type="checkbox" name="se0035" id="se0035" value="2" <c:if test="${model.record.se0035 == '2'}"> checked </c:if> ><font class="text12MediumBlue"><b>TEST flag</b></font>&nbsp;&nbsp;&nbsp;						
					</c:if>
					<a tabindex=-1 href="tvinnsadexport_edit_printTopic.do?avd=${model.record.seavd}&opd=${model.record.setdn}">
					 	<img title="Print" style="vertical-align: bottom;cursor:pointer;" src="resources/images/printer.png" width="30px" height="30px" border="0" alt="Print">
					</a>
					&nbsp;&nbsp;<img title="Print skilleark" style="vertical-align: bottom;cursor: pointer;" id="printSkilleArkImg" width="30px" height="30px" src="resources/images/printer2.png" border="0" alt="Print skilleark">
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;<img title="Upload dokument" style="vertical-align: bottom;cursor: pointer;" id="uploadFileImg" width="25px" height="25px" src="resources/images/upload.png" border="0" alt="Upload dokument">
					&nbsp;&nbsp;&nbsp;
					
				</td>
			</tr>
			<tr >
				<td align="left" class="text12MediumBlue" >
					&nbsp;&nbsp;&nbsp;<b>54.</b>&nbsp;<span title="sedst">Tarifferingsted:</span>&nbsp;<b>${model.record.sedst}</b>
					&nbsp;&nbsp;<span title="setarf">Tariffør:</span>&nbsp;<b>${model.record.setarf}</b>
					
				</td>
			</tr>
			
			<%-- test indicator /per avdelning --%> 
			<c:if test="${'2' == isTestAvd}">
				<tr height="10"><td colspan="2"></td></tr>
				<tr>
					<td align="left" class="text14Red" >
						&nbsp;&nbsp;<b>[ TEST Avdeling ]</b>
					</td>
				</tr>
			</c:if>
		</c:when>
		<%-- CREATE MODE --%> 
		<c:otherwise>
			<tr >
				<td align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title="seavd">Avdeling:&nbsp;
           			<select name="avd" id="avd" TABINDEX=1>
	            		<option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                             <option value="${record.avd}"<c:if test="${model.record.seavd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '2'}">&nbsp;(test)</c:if></option>
						</c:forEach> 
					</select>
					&nbsp;<span title="sesg">Sign:</span>&nbsp;
           			<select name="sesg" id="sesg" TABINDEX=2>
	            		<option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                           	 	<c:choose>
								<c:when test="${empty model.record.sesg}">
									<option value="${record.sign}"<c:if test="${user.tvinnSadSign == record.sign}"> selected </c:if> >${record.sign}</option>
								</c:when>
								<c:otherwise>
									<option value="${record.sign}"<c:if test="${model.record.sesg == record.sign}"> selected </c:if> >${record.sign}</option>
								</c:otherwise>
								</c:choose>
						</c:forEach> 
						
						
					</select>
				</td>
				<td>&nbsp;</td>
			</tr>	
		</c:otherwise>
		</c:choose>

		<tr height="10"><td colspan="2">&nbsp;</td></tr>
		<%-- --------------- --%>
		<%-- LEFT SIDE CELL --%>
		<%-- --------------- --%>
		<tr>
		<td width="50%" valign="top">
		<table width="96%" border="0" cellspacing="0" cellpadding="0">
			<tr>
	            <td width="5">&nbsp;</td>
	            <td >
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td class="text12">
				 				<img onMouseOver="showPop('1_1_info');" onMouseOut="hidePop('1_1_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>1</b><font class="text16RedBold" >*</font><span title="sedty">Dekl.type&nbsp;</span>
			 					<div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute;top:2px; width:250px;" id="1_1_info" class="popupWithInputText text11"  >
					           			<b>1 Dekl.type</b>
					           			<ul>
					           				<li><b>EU</b>ved utførsel til et EØS,EFTA eller EU-land</li>
					           				<li><b>EX</b> ved utførsel til et land som ikke er tilknyttet EØS, EFTA eller EU (tredjeland).
					           				</li>
					           			</ul>
					           			<p>
					           			KODEN KAN LEGGES INN SOM STANDARDVERDI PR.AVDELING.<br/> 
					           			KAN OVERSTYRES UNDER DEKLARERINGEN.
					           			</p>
								</span>	
								</div>	
			 					
				 			</td>
				 			<td>
				 				<select class="inputTextMediumBlueMandatoryField" name="sedty" id="sedty" >
				 				  <option value="">-velg-</option>
								  <option value="EU"<c:if test="${model.record.sedty == 'EU'}"> selected </c:if> >EU</option>
								  <option value="EX"<c:if test="${model.record.sedty == 'EX'}"> selected </c:if> >EX</option>
								</select>
			 				</td>
			 				<td class="text12">&nbsp;
				 				<img onMouseOver="showPop('ekspedtype_info');" onMouseOut="hidePop('ekspedtype_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<font class="text16RedBold" >*</font><span title="sedp">Eksped.type&nbsp;</span>
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute;top:2px; width:350px;" id="ekspedtype_info" class="popupWithInputText text11"  >
				           			<ul>
					           			<c:forEach var="record" items="${model.ekspedisjonstyperExportCodeList}" >
					           			<li><b>${record.zkod}</b>&nbsp;${record.ztxt}</li>
					           			</c:forEach>
					           		</ul>
								</span>
				 				</div>
			 				</td>
				 			<td>
				 				<select class="inputTextMediumBlueMandatoryField" name="sedp" id="sedp" >
				 				  <option value="">-velg-</option>
					 				  	<c:forEach var="record" items="${model.ekspedisjonstyperExportCodeList}" >
					 				  		<option value="${record.zkod}"<c:if test="${model.record.sedp == record.zkod}"> selected </c:if> >${record.zkod}</option>
										</c:forEach>  
								</select>
				 			</td>
				 			
		 				</tr>
		 				<tr>
		 					<td class="text12">
		 						<img onMouseOver="showPop('tollMva_info');" onMouseOut="hidePop('tollMva_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="seski" id="v_seski" class="validation">&nbsp;Avgifter S/K/I&nbsp;</span>
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute;top:2px; width:300px;" id="tollMva_info" class="popupWithInputText text11" >
					           			<b>Avgifter S/K/I</b>
					           			<br/><br/>
					           			Dersom systemet ikke finner tollkreditt på det tastede kundenummeret, så må man angi hvem som skal betale TOLL/MVA.
						           		<ul>
						           			<li><b>S</b> = på faktura mot selger/avsender siden (flyttes autom. over på faktura)</li>
					           				<li><b>K</b> = på faktura mot kjøper</li>
						           			<li><b>I</b> = Ingen (Kunden kommer selv med sjekk på utlegget</li>
						           		</ul>
						           		<p>
						           		KRAV TIL FELTET dersom kunde ikke har tollkreditt.
						           		</p>
								</span>
								</div>
				 			</td>
				 			<td>
				 				<select name="seski" id="seski" >
				 					<option value="">-velg-</option>
								  <option value="S"<c:if test="${ model.record.seski == 'S'}"> selected </c:if> >S</option>
								  <option value="K"<c:if test="${ model.record.seski == 'K'}"> selected </c:if> >K</option>
								  <option value="I"<c:if test="${ model.record.seski == 'I'}"> selected </c:if> >I</option>
								</select>
			 				</td>
			 				<td class="text12">&nbsp;
			 					<img onMouseOver="showPop('ens_flag_info');" onMouseOut="hidePop('ens_flag_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="sekddk">Dagsopp./Kontant&nbsp;</span>
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute;top:2px; width:250px;" id="ens_flag_info" class="popupWithInputText text11"  >
						           		<b>Dagsoppgjør/Kontant</b><br/><br/>
						           		Kodevalg for hvilke tekst man ønsker på deklarasj. dersom S/K/I er brukt.
						           		<ul>
						           			<li><b>D</b> = Dagsoppgjør</li>
						           			<li><b>K</b> = Kontant</li>						           			
						           		</ul>
								</span>
								</div>
			 				</td>
			 				<td>
				 				<select name="sekddk" id="sekddk" >
				 				  <option selected value="">-velg-</option>
								  <option value="D"<c:if test="${model.record.sekddk == 'D'}"> selected </c:if> >D</option>
								  <option value="K"<c:if test="${model.record.sekddk == 'K'}"> selected </c:if> >K</option>
								</select>
			 				</td>
		 				</tr>
		 				
		 				<tr height="10"><td></td></tr>
		 				<tr>
		 					<td align="left" class="text12">&nbsp;
		 						<span title="h_xref">Ext.ref.nr.&nbsp;</span>
				 			</td>
				 			<td class="text12" align="left" colspan="2">
				 				<input type="text" class="inputText" name="h_xref" id="h_xref" size="15" maxlength="35" value="${model.record.h_xref}">
			 				</td>
			 					
				 			<td class="text12">&nbsp;
		 						<span title="setll" id="v_setll" >Løpenr.&nbsp;</span>
			 				</td>
			 				<td class="text12" align="left">
		            			<input readonly type="text" class="inputTextReadOnly" name="setll" id="setll" size="12" maxlength="10" value="${model.record.setll}">
	            			</td>
				 		</tr>
	 				</table>
 				</td>
	 		</tr>
		 	<tr height="5"><td></td></tr>	

			<%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td width="5">&nbsp;</td>
				<td>
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
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
			
			<%-- Other errors (none validation errors) --%>
			<c:if test="${not empty model.errorMessage}">
			<tr>
				<td width="5">&nbsp;</td>
				<td >
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td class="textError">
				 				<ul>
                                    <li>
                                    		Fel vid uppdatering/kopiering. [ERROR/WARN] ${model.errorMessage} 
                                    </li>
                                    <li>
                                      	[META-INFO: ${model.errorInfo}]  
                                    </li>
                                </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
			
 			<tr>
	 			<td width="5">&nbsp;</td>
	            <td >		
	 				<%-- SENDER --%>
	 				<table width="90%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
								&nbsp;<img onMouseOver="showPop('2_info');" onMouseOut="hidePop('2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 			<b>&nbsp;2.</b>Avsender / Eksportør&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute;top:2px; width:250px;" id="2_info" class="popupWithInputText text11"  >
					           		<b>Avsender / Eksportør</b>
					           		<p>
					           		Oppgi selgerens kundenr. eller søk i kunderegisteret.
									Opplysningene overføres fra oppdragsregistreringen.<br/>
									Regnr. (Foretaksnr.) hentes fra kunderegister, eller fylles ut. 
									</p>
									<p>
									KRAV TIL FELTET
									</p>
								</span>
								</div>
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
	 		</tr>
	 		<tr>	
	 			<td width="5">&nbsp;</td>
	            <td >	
					<%-- create record --%>
				 	<table width="90%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
					 		<td width="100%">
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="10"><td ></td></tr>
							        
							        <tr>
							        	<%-- ================================================================================== --%>
							        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
							        		 These original values will be used when the user clicks "Cancel" buttons (puttting
							        		 back original value)																--%> 
							        	<%-- ================================================================================== --%>
							        	<input type="hidden" name="orig_seknk" id="orig_seknk" value='${model.record.seknk}'>
							        	<input type="hidden" name="orig_senak" id="orig_senak" value='${model.record.senak}'>
							        	<input type="hidden" name="orig_seadk1" id="orig_seadk1" value='${model.record.seadk1}'>
							        	<input type="hidden" name="orig_seadk2" id="orig_seadk2" value='${model.record.seadk2}'>
							        	<input type="hidden" name="orig_seadk3" id="orig_seadk3" value='${model.record.seadk3}'>
							        	
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="seknk">Kundenummer</span>
											<img title="Spørring på fritekster for kunder" id="senderFreeTextImg" onMouseOver="showPop('senderFtxtinfo');" onMouseOut="hidePop('senderFtxtinfo');" onClick="showPop('senderInfoFreeTextDialog');" style="vertical-align:top;" width="18px" height="18px" src="resources/images/largeTextContent.png" border="0" alt="info">
								 			<span style="position:absolute; left:300px; top:300px; width:400px; height:500px;" id="senderInfoFreeTextDialog" class="popupWithInputText"  >
								           		<div class="text11" align="left">
								           		Spørring på fritekster for kunder
								           		<p>
								           			<textarea rows="20" cols="40" class="inputText" name="senderInfoTextArea" id="senderInfoTextArea" maxlength="1000"></textarea>
								           		</p>
								           		<p>
								           			&nbsp;&nbsp;<button name="senderInfoFreeTextDialogCloseOk" id="senderInfoFreeTextDialogCloseOk" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('senderInfoFreeTextDialog');"><spring:message code="systema.tvinn.sad.export.ok"/></button>
								           		</p>
								           		</div>
											</span>
											
											<div class="text11" style="position: relative;" align="left">
											<span style="position: absolute; top:0px; left:0px;" id="senderFtxtinfo" class="popupWithInputText"  >
								           		<font class="text11" >Spørring på fritekster for kunder</font>
											</span>
											</div>							            
							            </td>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="senak" id="v_senak" class="validation">Navn&nbsp;</span>
							            	<a tabindex="-1" id="senakIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>			
							            </td>
							        </tr>
							        <tr>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue" name="seknk" id="seknk" size="9" maxlength="8" value="${model.record.seknk}"></td>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue" name="senak" id="senak" size="31" maxlength="30" value="${model.record.senak}"></td>
							        </tr>
							        <tr height="10"><td></td></tr>
									<tr>
							            <td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font>
							            		<span title="serg" id="v_serg" class="validation">Regnr</span>
							            </td>
							            <td class="text12" >
					 					<img onMouseOver="showPop('48_info');" onMouseOut="hidePop('48_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				&nbsp;<b>48.&nbsp;</b><span title="segkd/sekta/sektb">Kontonr.Tollkredit&nbsp;</span>
						 				<span style="position:absolute; left:800px; top:500px; width:250px; height:200px;" id="48_info" class="popupWithInputText"  >
							           		<div class="text11" align="left">
							           			<b>48. Kontonr.Tollkredit</b>&nbsp;
							           			<p>
							           			Eksportørens kontonr. for tollkreditt. Hentes fra kunderegister, kundenr. for avsender. 
							           			Dersom det er en kunde som tidligere har vært uten kred vil kontonr. da bli lagret i kunderegisteret.
							           			</p>
						           			</div>
										</span>
										</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="serg" id="serg" size="20" maxlength="11" value="${model.record.serg}"></td>
							            	<td align="left">
											<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="segkd" id="segkd" size="1" maxlength="1" value="${model.record.segkd}">
											<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="sekta" id="sekta" size="5" maxlength="5" value="${model.record.sekta}">
											<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="sektb" id="sektb" size="2" maxlength="2" value="${model.record.sektb}">
										</td>
							        </tr>
							         
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="seadk1" id="v_seadk1" class="validation">Adresse-1</span></td>
							            <td>&nbsp;</td>
							        </tr>
							        <tr>
							            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="seadk1" id="seadk1" size="40" maxlength="30" value="${model.record.seadk1}"></td>
    							            
							        </tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="seadk2" id="v_seadk2" class="validation">Adresse-2</span></td>
    							            <td>&nbsp;</td>
							        </tr>
							        <tr>
							            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="seadk2" id="seadk2" size="40" maxlength="30" value="${model.record.seadk2}"></td>
   							            
							        </tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="seadk3" id="v_seadk3" class="validation">Adresse-3</span></td>
   							            <td>&nbsp;</td>							            
							        </tr>
							        <tr>
							            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="seadk3" id="seadk3" size="40" maxlength="30" value="${model.record.seadk3}"></td>
   							        </tr>
							        <tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
   							            <td>&nbsp;</td>							            
							        </tr>
						        </table>
					        </td>
				        </tr>
					</table>          
            	</td>
           	</tr> 
           	<tr height="10"><td></td></tr>
           	
           	<%-- RECEIVER --%>
	 		<tr>
	 			<td width="5">&nbsp;</td>
	            <td >		
	 				<table width="90%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
				 				&nbsp;<img onMouseOver="showPop('8_info');" onMouseOut="hidePop('8_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>&nbsp;8.</b><font class="text16RedBold" >*</font>Mottaker&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute; top:2px; width:250px;" id="8_info" class="popupWithInputText text11"  >
					           		<b>Mottaker</b>
					           		<p>
					           		Oppgi navn og adresse på den person/firma varen fysisk skal leveres til i utlandet. 
					           		Dersom ekspedisjonen omfatter fakturaer til flere varemottakere enn det er plass til i rubrikken, skrives
					           		<b>'se vedlagte fakturaer'</b>
									</p>
									<p>
									Opplysningene overføres automatisk fra oppdraget. Kan også skrives inn direkte på bildet.
									</p>
								</span>
								</div>				 				
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
	 		</tr>
	 		<tr>	
	 			<td width="5">&nbsp;</td>
	            <td >	
					<%-- create record --%>
				 	<table width="90%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
					 		<td width="100%">
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="10"><td ></td></tr>
							        
							        <tr>
							        	<%-- ================================================================================== --%>
							        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
							        		 These original values will be used when the user clicks "Cancel" buttons (puttting
							        		 back original value)																--%> 
							        	<%-- ================================================================================== --%>
							        	<input type="hidden" name="orig_sekns" id="orig_sekns" value='${model.record.sekns}'>
							        	<input type="hidden" name="orig_senas" id="orig_senas" value='${model.record.senas}'>
							        	<input type="hidden" name="orig_seads1" id="orig_seads1" value='${model.record.seads1}'>
							        	<input type="hidden" name="orig_seads2" id="orig_seads2" value='${model.record.seads2}'>
							        	<input type="hidden" name="orig_seads3" id="orig_seads3" value='${model.record.seads3}'>
							        	
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="sekns">Kundenummer</span>
							            		<img id="receiverFreeTextImg" onMouseOver="showPop('receiverFtxtinfo');" onMouseOut="hidePop('receiverFtxtinfo');" onClick="showPop('receiverInfoFreeTextDialog');" style="vertical-align:top;" width="18px" height="18px" src="resources/images/largeTextContent.png" border="0" alt="info" title="Spørring på fritekster for kunder" >
								 			<span style="position:absolute; left:300px; top:450px; width:400px; height:500px;" id="receiverInfoFreeTextDialog" class="popupWithInputText"  >
								           		<div class="text11" align="left">
								           		Spørring på fritekster for kunder
								           		<p>
								           			<textarea rows="20" cols="40" class="inputText" name="receiverInfoTextArea" id="receiverInfoTextArea" maxlength="1000"></textarea>
								           		</p>
								           		<p>
								           			&nbsp;&nbsp;<button name="receiverInfoFreeTextDialogCloseOk" id="receiverInfoFreeTextDialogCloseOk" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('receiverInfoFreeTextDialog');"><spring:message code="systema.tvinn.sad.export.ok"/></button>
								           		</p>
								           		</div>
											</span>
											
											<div class="text11" style="position: relative;" align="left">
											<span style="position: absolute; top:0px; left:0px;" id="receiverFtxtinfo" class="popupWithInputText"  >
								           		<font class="text11" >Spørring på fritekster for kunder</font>
											</span>
											</div>
							            </td>
							            <td class="text12" align="left" ><font class="text16RedBold" >*</font>
							            <span title="senas" id="v_senas" class="validation">Navn&nbsp;</span>
							            	<a tabindex="-1" id="senasIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>	
							            </td>
							        </tr>
							        <tr>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue" name="sekns" id="sekns" size="9" maxlength="8" value="${model.record.sekns}"></td>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="senas" id="senas" size="31" maxlength="30" value="${model.record.senas}"></td>
							            
							        </tr>
							        
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="seads1" id="v_seads1" class="validation">Adresse-1</span></td>
							            <td>&nbsp;</td>
							        </tr>
							        <tr>
							            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="seads1" id="seads1" size="40" maxlength="30" value="${model.record.seads1}"></td>
    							            
							        </tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="seads2" id="v_seads2" class="validation">Adresse-2</span></td>
    							            <td>&nbsp;</td>
							        </tr>
							        <tr>
							            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="seads2" id="seads2" size="40" maxlength="30" value="${model.record.seads2}"></td>
   							            
							        </tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="seads3" id="v_seads3" class="validation">Adresse-3</span></td>
   							            <td>&nbsp;</td>							            
							        </tr>
							        <tr>
							            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="seads3" id="seads3" size="40" maxlength="30" value="${model.record.seads3}"></td>
   							        </tr>
							        <tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
   							            <td>&nbsp;</td>							            
							        </tr>
						        </table>
					        </td>
				        </tr>
					</table>          
            	</td>
           	</tr> 
           	<tr height="10"><td></td></tr>
           	<tr>
	 			<td width="5">&nbsp;</td>
	            <td >		
	 				<%-- DEKLARANT --%>
	 				<table width="90%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="18px">
				 			<td class="text12White">
				 				&nbsp;<img onMouseOver="showPop('14_b_info');" onMouseOut="hidePop('14_b_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		 						<b>&nbsp;14.</b><font class="text16RedBold" >*</font>Deklarant&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute;top:2px; width:250px;" id="14_b_info" class="popupWithInputText text11" >
				           			<b>14.&nbsp;Deklarant</b> 
				           			<p>
									Oppgi navn og telefonnummer. Dette ligger som standardverdier pr. avdeling. Blir tildelt hver
									gang man deklarerer. 
									</p>
									<p>
									Ønsker man annen tekst enn den som kommer automatisk så skrives denne oppå den andre.
									</p>
								</span>
								</div>
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td width="5">&nbsp;</td>
	            <td >	
					<%-- create record --%>
				 	<table width="90%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
					 		<td>
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="5"><td></td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
							            <span title="senad">Navn</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
							            <span title="setlf">Telefon</span></td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" class="inputTextMediumBlueMandatoryField" name="senad" id="senad" size="35" maxlength="30" value="${model.record.senad}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlue" class="inputTextMediumBlueMandatoryField" name="setlf" id="setlf" size="15" maxlength="12" value="${model.record.setlf}"></td>
							        </tr>
						        </table>
					      	</td>
						 </tr>
						 <tr height="15"><td></td></tr>
				 	</table>
            		</td>
           	</tr>
        		<tr height="20"><td></td></tr>
           	<tr>
				<td width="5">&nbsp;</td>
	            <td >
					<%-- Special section --%>
					<table width="90%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
				 				&nbsp;<img onMouseOver="showPop('28_info');" onMouseOut="hidePop('28_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		 						<b>&nbsp;28.</b>&nbsp;<font class="text16RedBold" >*</font>Finans<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
		 						<div class="text11" style="position: relative;" align="left">
		 						<span style="position:absolute;top:2px; width:250px;" id="28_info" class="popupWithInputText text11" >
				           			<b>28.&nbsp;Finans</b><br/><br/>
									Oppgi fakturanummer og dato. Fakturaer med fortløpende nummer kan oppgis med første og siste nummer. Feks. 270-275. 
									Hvis fakturaen ikke er nummerert,oppgis bare dato
								</span>
								</div>
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
 			</tr>
 			<tr>
	 			<td width="5">&nbsp;</td>
	            <td >
	 				<%-- create record --%>
				 	<table width="90%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
					 		<td>
						 		<table align="left" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							        </tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font>
							            <span title="sefif">Fakt.nr.&nbsp;</span>
							            <input type="text" class="inputTextMediumBlueMandatoryField" name="sefif" id="sefif" size="18" maxlength="17" value='${ model.record.sefif}'></td>
							            <td class="text12">&nbsp;&nbsp;&nbsp;&nbsp;<font class="text16RedBold" >*</font>
							            		<span title="sefid">Fakt.dato</span>
			 								<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="sefid" id="sefid" size="9" maxlength="6" value="${model.record.sefid}">
			 							</td>
							        </tr>
							        <tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							        </tr>
							        <tr>
							            <td colspan="2" class="text12" align="left" >&nbsp;
							            <span title="finansOpplysningarTotSum/finansOpplysningarTotValidCurrency"></span>Fakturasum. fra Finans.oppl.&nbsp;</span>
							            <input type="text" class="inputTextReadOnly"  name="finansOpplysningarTotSum" id="finansOpplysningarTotSum" size="15" value='${ model.record.finansOpplysningarTotSum}'>
							            &nbsp;&nbsp;
							            <input type="text" class="inputTextReadOnly"  name="finansOpplysningarTotValidCurrency" id="finansOpplysningarTotValidCurrency" size="5" value='${ model.record.finansOpplysningarTotValidCurrency}'>
							            &nbsp;<button title="Hente summen fra Finans.oppl." name="getFinansOpplSumButton" id="getFinansOpplSumButton" class="buttonGrayWithGreenFrame" type="button" >Hente summen</button>
							            <input type="hidden" name="finansOpplysningarTotKurs" id="finansOpplysningarTotKurs" value='${ model.record.finansOpplysningarTotKurs}'>
							            </td>
							        </tr>
							        <tr height="5">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							        </tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
		  	</tr>
           	
           	<%-- INVOICE AMOUNT Fields --%>
			<tr height="10"><td></td></tr>
            <tr>
	            <td width="5">&nbsp;</td>
	            <td >
	                <table width="90%" align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td class="text12">
				 				<b>&nbsp;22.</b>&nbsp;<font class="text16RedBold" >*</font>
				 				<span title="sebel1" id="v_sebel1" class="validation">Fakturasum&nbsp;</span>
				 			</td>
				 			<td align="left" >
				 				<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="sebel1" id="sebel1" size="20" maxlength="13" value="${model.record.sebel1}">
				 			</td>
				 			<td class="text12" align="left">
				 				&nbsp;<font class="text16RedBold" >*</font>
				 				<span title="seval1" id="v_seval1" class="validation">Valuta</span>
				 				<%-- Note: onChange event in jQuery for this currency list --%>
				 				<select class="inputTextMediumBlueMandatoryField" name="seval1" id="seval1" >
				 				  <option value="">-velg-</option>	
				 				  <c:forEach var="currency" items="${model.currencyCodeList}" >
			 				  		<option value="${currency.zkod}"<c:if test="${ model.record.seval1 == currency.zkod}"> selected </c:if> >${currency.zkod}</option>
								  </c:forEach>  
								</select>
								<a tabindex="-1" id="seval1IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								</a>
								
				 			<%-- this field is only used via Ajax since there is no database field. It is used to disclosed a factor when changing the currency --%>
				 			<td class="text12Grey" align="left" ><input readonly type="text" class="inputTextReadOnly" name="factor" id="factor" size="6" value=""></td>
			 			</tr>		 				
		 				<tr height="5"><td></td></tr>
		 				<tr height="1"><td colspan="4" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> </tr>
					</table>
					</td>
			</tr>
			</table>
		</td>
		<%-- --------------- --%>
		<%-- RIGHT SIDE CELL --%>
		<%-- --------------- --%>
		<td width="50%" align="center" valign="top">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="2">&nbsp;</td>
					<td valign="top">
			 			<table border="0" cellspacing="0" cellpadding="0">
					 		<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('15_info');" onMouseOut="hidePop('15_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>15.</b><span title="selka">&nbsp;Avs/utf.land</span>&nbsp;
			 					<div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute;top:2px; width:250px;" id="15_info" class="popupWithInputText text11" >
					           			<b>15. Avs/utf.land</b>
					           			<p>
					           			Vil som regel være Norge, hvis ikke prosedyren tilsier noe annet. 
					           			</p>
								</span>	
								</div>	
					            </td>
					            
					            <td >
				            		<select name="selka" id="selka">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					 				  		<option value="${country.zkod}"<c:if test="${model.record.selka == country.zkod}"> selected </c:if> >${country.zkod}</option>
										</c:forEach>  
									</select>
									<a tabindex="-1" id="selkaIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									<%-- info span 
									<img onClick="showPop('avsenderlandInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
									<span style="position:absolute; left:850px; top:150px; width:350px; height:150px;" id="avsenderlandInfo" class="popupWithInputText"  >
						           		<div class="text10" align="left">
					           				<select class="text11" id="avsenderland" name="avsenderland" size="5" onDblClick="hidePop('avsenderlandInfo');">
						           				<option value="">-velg-</option>
						           				<c:forEach var="country" items="${model.countryCodeList}" >
						 				  			<option value="${country.zkod}">${country.zkod}&nbsp;${country.ztxt}</option>
												</c:forEach>
						           			</select>
						           			
											<table width="100%" align="left" border="0">
												<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
												<tr align="left" >
													<td class="text11">&nbsp;<button name="avsenderlandButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('avsenderlandInfo');">&nbsp;<spring:message code="systema.tvinn.sad.export.ok"/></button> 
													</td>
												</tr>
											</table>
										</div>
									</span>	
									--%>																									 			
								</td>
							</tr>
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('17_info');" onMouseOut="hidePop('17_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>17.</b><font class="text16RedBold" >*</font><span title="selkb">&nbsp;Best.land</span>&nbsp;
			 					<div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute;top:2px; width:250px;" id="17_info" class="popupWithInputText text11"  >
					           			<b>17. Bestemmelsesland</b>
										<p>					           			
										Tast gyldig landkode. F.eks. DE=Tyskland. Oppgi det endelige bestemmelseslandet for varen.
										</p>
										<ul>
										<li>Ved overføring av varer til Svalbard og Jan Mayen samt ved utførsel til skip, luftfartøyer og oljeplattformer registrert i Norge i utenriks fart oppgis Norge som bestemmelsesland. 
										Det samme gjelder ved utførsel til norske stasjoner i utlandet (ambassader o.l.).</li>
										<li>Ved utførsel av varer til skip, luftfartøyer og oljeplattformer registrert i utlandet, blir bestemmelsesland det land hvor skipet, luftfartøyet eller oljeplattformen er registrert.</li>
										<li>Ved utførsel av varer til skip, luftfartøyer og oljeplattformer under bygging i utlandet for norsk regning, oppgis det land hvor byggingen finner sted som bestemmelsesland.</li>
										</ul>
								</span>	
								</div>	
					            </td>
					            <td >
					            	<select class="inputTextMediumBlueMandatoryField" name="selkb" id="selkb">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					 				  		<option value="${country.zkod}"<c:if test="${model.record.selkb == country.zkod}"> selected </c:if> >${country.zkod}</option>
										</c:forEach>  
									</select>
									<a tabindex="-1" id="selkbIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									<%-- info span
									<img onClick="showPop('belandInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
									<span style="position:absolute; left:850px; top:150px; width:350px; height:150px;" id="belandInfo" class="popupWithInputText"  >
						           		<div class="text10" align="left">
					           				<select class="text11" id="beland" name="beland" size="5" onDblClick="hidePop('belandInfo');">
						           				<option value="">-velg-</option>
						           				<c:forEach var="country" items="${model.countryCodeList}" >
						 				  			<option value="${country.zkod}">${country.zkod}&nbsp;${country.ztxt}</option>
												</c:forEach>
						           			</select>
						           			
											<table width="100%" align="left" border="0">
												<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
												<tr align="left" >
													<td class="text11">&nbsp;<button name="belandButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('belandInfo');">&nbsp;<spring:message code="systema.tvinn.sad.export.ok"/></button> 
													</td>
												</tr>
											</table>
										</div>
									</span>
									--%>																										 			
								</td>
							</tr>
							<tr height="8"><td class="text"></td> </tr>
							
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('19_info');" onMouseOut="hidePop('19_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>19.</b><font class="text16RedBold" >*</font><span title="sekdc" id="v_sekdc" class="validation">Container&nbsp;</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="19_info" class="popupWithInputText text11"  >
					           			<b>19. Container</b>
					           			<br/><br/>
					           			Oppgi med følgende koder om varene blir transportert i containere eller ikke ved utpassering fra Norge.
										<br>
										<ul>
										<li><b>0</b> = Varer som ikke transporteres i containere.</li>
										<li><b>1</b> = Varer som transporteres ie.</li>
										</ul>
										Kan defineres som standardverdier pr. avdeling. 
										<p>
										KRAV TIL FELTET
										</p>
								</span>	
								</div>	
								</td>
					            <td class="text12" >
			           				<select class="inputTextMediumBlueMandatoryField" name="sekdc" id="sekdc">
				 						<option value="0" <c:if test="${model.record.sekdc == '0'}"> selected </c:if> >0</option>
				 						<option value="1" <c:if test="${model.record.sekdc == '1'}"> selected </c:if> >1</option>								 				  	  
									</select>
			           			</td>
							</tr>
							<tr height="10"><td class="text"></td> </tr>
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('21_1_info');" onMouseOut="hidePop('21_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>21.1</b><span title="setrid" id="v_setrid" class="validation">Transp.ID</span>
			 					<div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute;top:2px; width:250px;" id="21_1_info" class="popupWithInputText text11"  >
					           			<b>21.1 Transportmidlets identitet ved ankomsten</b>
					           			<br/><br/>
					           			Identiteten for Transportmidlets.
								</span>	
								</div>
								</td>	
					            
				                 <td >
						            	<input type="text" class="inputTextMediumBlue" name="setrid" id="setrid" size="21" maxlength="20" value="${model.record.setrid}">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
						<table border="0" cellspacing="1" cellpadding="0">	
					 		<tr>
					            <td class="text12" align="left">
					            <img onMouseOver="showPop('21_2_info');" onMouseOut="hidePop('21_2_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>21.2</b><font class="text16RedBold" >*</font><span title="selkt">Aktive transp. nasjonalitet ved grense&nbsp;</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="21_2_info" class="popupWithInputText text11"  >
					           			<b>21.2 Aktive transp. nasjonalitet ved grense</b>
					           			<br/><br/>
					           			Oppgi det aktive transportmidlets nasjonalitet ved utpassering fra Norge. 
								</span>	
								</div>
								</td>
								<td>
					            		<select class="inputTextMediumBlueMandatoryField" name="selkt" id="selkt">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					 				  		<option value="${country.zkod}"<c:if test="${model.record.selkt == country.zkod}"> selected </c:if> >${country.zkod}</option>
										</c:forEach>  
									</select>
									<a tabindex="-1" id="selktIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									<%-- info span
									<img onClick="showPop('nasjonalitetSearchInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
									<span style="position:absolute; left:800px; top:150px; width:350px; height:150px;" id="nasjonalitetSearchInfo" class="popupWithInputText"  >
						           		<div class="text10" align="left">
					           				<select class="text11" id="nasjonalitetSearch" name="nasjonalitetSearch" size="5" onDblClick="hidePop('nasjonalitetSearchInfo');">
						           				<option value="">-velg-</option>
						           				<c:forEach var="country" items="${model.countryCodeList}" >
						 				  			<option value="${country.zkod}">${country.zkod}&nbsp;${country.ztxt}</option>
												</c:forEach>
						           			</select>
						           			
											<table width="100%" align="left" border="0">
												<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
												<tr align="left" >
													<td class="text11">&nbsp;<button name="nasjonalitetSearchButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('nasjonalitetSearchInfo');">&nbsp;<spring:message code="systema.tvinn.sad.export.ok"/></button> 
													</td>
												</tr>
											</table>
										</div>
									</span>	
									--%>																									 			
					            	</td>
					        </tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
			 			<table border="0" cellspacing="1" cellpadding="0">
			 				<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('25_info');" onMouseOut="hidePop('25_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>25.</b><font class="text16RedBold" >*</font><span title="setrm" id="v_setrm" class="validation">Transportmåte&nbsp;</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="25_info" class="popupWithInputText text11"  >
					           			<b>25. Transportmåte</b>
					           			<br/><br/>
					           			Oppgi med kode transportmåten utpassering fra Norge.
										Koden kan legges inn som standardverdi for avdelingen
										<ul>
				           				<c:forEach var="record" items="${model.transportmaterCodeList}" >
				           					<li><b>${record.zkod}</b>&nbsp;${record.ztxt}</li>
				           				</c:forEach>				           									           									           									           									           									           				
				           				</ul>
								</span>
								</div>
								</td>		
					            <td class="text12" >
			           				<select class="inputTextMediumBlueMandatoryField" name="setrm" id="setrm">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="record" items="${model.transportmaterCodeList}" >
					 				  		<option value="${record.zkod}"<c:if test="${model.record.setrm == record.zkod}"> selected </c:if> >${record.zkod}</option>
										</c:forEach>  
									</select>
			           			</td>
							</tr>
							<tr height="5"><td class="text"></td></tr>
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('49_info');" onMouseOut="hidePop('49_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            &nbsp;<b>49.&nbsp;<span title="segn">Godsnr</span>
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px; width:250px;" id="49_info" class="popupWithInputText text11"  >
					           			<b>49. Godsnr</b><br/>
					           			<p>
						           			Skal oppgis ved utførsel av godsregistrerte varer.
						           			God snr og posisjonsnr overføres fra oppdraget.
					           			</p>
										<p>
											Oppbygging av godsnr:
											<b>1.- 4.</b> karakter: Årstall for registrering
											<b>5.- 6.</b> karakter: Tolldistriktskode
											<b>7.- 9.</b> karakter: Tollagerkode
											<b>10.-12.</b> karakter: Lossedag, kalenderens dagnr.
											<b>13.-15.</b> karakter: lossenr, fortløpende listenr, pr. tollager og dag
										</p>
								</span>	
								</div>
								</td>
					            <td>
					            		<input type="text" class="inputTextMediumBlue" name="segn" id="segn" size="20" maxlength="15" value="${model.record.segn}">
					            </td>
					        </tr>
					        <tr>
					            <td class="text12" align="left" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title="sepos">Posisjon</span></td>
					            <td >
					            		<input type="text" class="inputTextMediumBlue" name="sepos" id="sepos" size="15" maxlength="9" value="${model.record.sepos}">
					            </td>
					        </tr>
					        
					        
							<tr height="5"><td class="text"></td></tr>
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('29_info');" onMouseOut="hidePop('29_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>29.</b><font class="text16RedBold" >*</font>
					            <span title="sekdh/sekdft" id="v_sekdh" class="validation"><font class="text12">Utpass.sted&nbsp;</font></span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="29_info" class="popupWithInputText text11"  >
					           			<b>29. Utpasseringsted</b>
					           			<p>
					           			Oppgi kode for utpasseringssted.
					           			</p>
								</span>
								</div>
								</td>		
					            <td class="text12" >
			           				<select class="inputTextMediumBlueMandatoryField" name="sekdh" id="sekdh">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="record" items="${model.havnCodeList}" >
					 				  		<c:choose>
					 				  			<c:when test="${fn:contains(model.record.sekdh,'@')}">
					 				  				<%-- Usually when a validation error occur --%>
					 				  				<option value="${record.zkod}@${record.ztxt}"<c:if test='${fn:substringBefore(model.record.sekdh, "@") == record.zkod && fn:substringAfter(model.record.sekdh, "@") == record.ztxt}'> selected </c:if> >${record.ztxt}&nbsp;${record.zkod}</option>
					 				  			</c:when>
					 				  			<c:otherwise>
					 				  				<%-- Usually when a normal doFetch occur --%>
					 				  				<option value="${record.zkod}@${record.ztxt}"<c:if test='${model.record.sekdh == record.zkod && (model.record.sekdft == record.ztxt)}'> selected </c:if> >${record.ztxt}&nbsp;${record.zkod}</option>
					 				  			</c:otherwise>
					 				  		</c:choose>
										</c:forEach>  
									</select>
									&nbsp;<input readonly type="text" class="inputTextReadOnly" name="sekdft" id="sekdft" size="17" maxlength="17" value="${model.record.sekdft}">
									<%-- Not necessary ? 
									<img onClick="showPop('havnCodesSearchInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
									<span style="position:absolute; left:850px; top:180px; width:250px; height:150px;" id="havnCodesSearchInfo" class="popupWithInputText"  >
						           		<div class="text10" align="left">
					           				<select class="text11" id="havnCodesSearch" name="havnCodesSearch" size="5" onDblClick="hidePop('havnCodesSearchInfo');">
						           				<option value="">-velg-</option>
						           				<c:forEach var="record" items="${model.havnCodeList}" >
						 				  			<option value="${record.zkod}@${record.ztxt}">${record.zkod}&nbsp;${record.ztxt}</option>
												</c:forEach>
						           			</select>
						           			<table width="100%" align="left" border="0">
												<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
												<tr align="left" >
													<td class="text11">&nbsp;<button name="havnCodesSearchButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('havnCodesSearchInfo');">&nbsp;<spring:message code="systema.tvinn.sad.export.ok"/></button> 
													</td>
												</tr>
											</table>
										</div>
									</span>
									--%>
			           			</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="2">&nbsp;</td>
					<td valign="top">
			 			<table border="0" cellspacing="0" cellpadding="0">
					 		<tr>
				            <td class="text12" align="left" >
				            <img onMouseOver="showPop('20_1_info');" onMouseOut="hidePop('20_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		 					<b>20.1</b><font class="text16RedBold" >*</font><span title="selv">&nbsp;Leveringksvilkår kode</span>
				            <div class="text11" style="position: relative;" align="left">
				            <span style="position:absolute;top:2px; width:250px;" id="20_1_info" class="popupWithInputText text11"  >
				           			<b>Leveringksvilkår kode</b>
				           			<br/><br/>
				           			Kode for leveringsvilkår:
				           			<br/><br/>
				           			<ul>
				           				<c:forEach var="record" items="${model.incotermsCodeList}" >
				           					<li><b>${record.zkod}</b>&nbsp;${record.ztxt}</li>
				           				</c:forEach>				           									           									           									           									           									           				
				           			</ul>
							</span>	
							</div>
							</td>
				            <td >&nbsp;
					            	<select class="inputTextMediumBlueMandatoryField" name="selv" id="selv">
			 						<option value="">-velg-</option>
					 				  	<c:forEach var="record" items="${model.incotermsCodeList}" >
					 				  		<option value="${record.zkod}"<c:if test="${model.record.selv == record.zkod}"> selected </c:if> >${record.zkod}</option>
										</c:forEach>  
								</select>
							</td>
							</tr>
			 			
			 				<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('20_2_info');" onMouseOut="hidePop('20_2_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>20.2</b><font class="text16RedBold" >*</font><span title="selvt">&nbsp;Leveringksvilkår sted</span>&nbsp;
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="20_2_info" class="popupWithInputText text11"  >
					           			<b>Leveringksvilkår sted</b>
					           			<br/><br/>
					           			Sted, hvor forsendelsesbetingelser og leveringsbetingelser opfyldes. 
					           			<br/><br/>
					           		
								</span>	
								</div>
								</td>
					            <td >
				            		&nbsp;<input type="text" class="inputTextMediumBlueMandatoryField" name="selvt" id="selvt" size="20" maxlength="17" value="${model.record.selvt}">
								</td>
							</tr>
							<tr height="5"><td></td></tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
			 			<table border="0" cellspacing="1" cellpadding="0">
			 				<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> </tr>
			 				<tr height="5"><td class="text">&nbsp;</td> </tr>
			 				<tr>
				            	<td class="text12" align="left" >
					            <img onMouseOver="showPop('30_1_info');" onMouseOut="hidePop('30_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>30.1&nbsp;</b><span title="sekdls">Lagringssted kode</span>
  					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="30_1_info" class="popupWithInputText text11"  >
				           			<b>30. Lagringssted kode</b>
				           			<br/><br/>
				           			Varenes lagringssted oppgis med kode. Rubrikken skal fylles ut med gyldig kode for lagringssted.
				           			<br/><br/>
				           			<ul>
				           				<c:forEach var="record" items="${model.lagringsstedCodeList}" >
				           					<li><b>${record.zkod}</b>&nbsp;${record.ztxt}</li>
				           				</c:forEach>				           									           									           									           									           									           				
				           			</ul>
								</span>	
								</div>
					            </td>
					            <td >
						            <select name="sekdls" id="sekdls">
						            	<option value="">-velg-</option>
					 				  	<c:forEach var="record" items="${model.lagringsstedCodeList}" >
					 				  		<option value="${record.zkod}"<c:if test="${model.record.sekdls == record.zkod}"> selected </c:if> >${record.zkod}</option>
										</c:forEach>
									</select>
					            </td>
        					</tr>
        					<tr>
			            		<td class="text12" align="left" >
			            		<img onMouseOver="showPop('30_2_info');" onMouseOut="hidePop('30_2_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>30.2&nbsp;</b><span title="sels">Lagringssted tekst</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="30_2_info" class="popupWithInputText text11"  >
					           			<b>30. Lagringssted tekst</b>
					           			<br/><br/>
					           			Selve tekstfeltet oppdateres automatisk når man taster / plukker en gyldig kode.
								</span>	
								</div>
								
					            </td>
					            <td >
					            	<input type="text" class="inputTextMediumBlue" name="sels" id="sels" size="20" maxlength="16" value="${model.record.sels}">
					            </td>
        					</tr>
        					<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> </tr>
				            <tr height="20"><td class="text">&nbsp;</td> </tr>
			 				<tr>
			 					<td class="text12" colspan="2" >
			 					<img onMouseOver="showPop('12_info');" onMouseOut="hidePop('12_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>12.&nbsp;Verdiopplysninger&nbsp;</b>
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute;top:2px; width:250px;" id="12_info" class="popupWithInputText text11"  >
					           			<b>12. Verdiopplysninger</b>&nbsp;
					           			<br/><br/>
					           			Oppgi fraktomkostningene (til norsk grense) for hele ekspedisjonen når disse ikke inngår i fakturaprisen.
										<br/><br/>
										OVERFØRES AUTOMATISK FRA OPPDRAGET DERSOM DET ER KRAV TIL FRAKT PÅ
										TASTET FRANKATUR. 
										<br/><br/>
										BELØPET KAN TASTES I ØNSKET VALUTA. OMREGNES OG SKRIVES UT I NOK
								</span>
								</div>
								</td>
							</tr> 
							
							<tr>
								<td class="text12" align="left" >
								<img onMouseOver="showPop('andraKostPM_info');" onMouseOut="hidePop('andraKostPM_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="siftg2">+/-&nbsp;(P/M)</span>
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute;top:2px; width:300px;" id="andraKostPM_info" class="popupWithInputText text11"  >
					           			<b>Andre kostnader +/- (P/M)</b>&nbsp;
					           			<p>
											HVIS MAN IKKE ØNSKER AT RUBR. 22 SKAL PÅVIRKES KAN MAN BENYTTE KODENE
											<b>M</b>(-) ELLER <b>P</b>(+)
											<br/><br/>
											Når man taster M eller P i feltet vil totalt fakturabeløp i rubr. 22 ikke påvirkes / endres, men det blir gjort fratrekk i "Statistisk verdi" som er grunnlaget for beregning av toll og avgifter.
										</p>
								</span>
								</div>
				 				</td>
			 					<td class="text12">
			 						<input type="text" class="inputTextMediumBlue" name="seftg2" id="seftg2" size="1" maxlength="1" value="${model.record.seftg2}">
			 					</td>
							</tr>
							
							<tr>
								<td class="text12" >
								<img onMouseOver="showPop('andraKost_info');" onMouseOut="hidePop('andraKost_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="sebel2/seval2">Beløp andre kost.</span>
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:300px;" id="andraKost_info" class="popupWithInputText text11"  >
				           			<b>Beløp andre kostnader +/- (P/M)</b>&nbsp;
				           			<br/><br/>
									Oppgi summen av eventuelle tillegg og/eller fradrag. Dersom det er mer å trekke fra enn å legge til fakturabeløpet, blir det et negativt beløp her.
									<br/><br/>
									<b>Eksempel på tillegg:</b> Provisjon,royalty,Innpakk..
									<br/><br/>
									<b>Eksempel på fradrag:</b> Diverse rabatter som er gitt i tilknyttning til de innførte varer.
									<br/><br/>
									HER KAN DET TASTES I ØNSKET VALUTA MED <b>+</b> ELLER <b>-</b>. DET TASTEDE BELØP
									GJELDER FOR HELE SENDINGEN. SYSTEMET BESØRGER FORDELINGEN PR.VARELINJE.
									<p>	
										Vær oppmerksom på at verdien i rubr. 22 "Fakturert valuta og totalbeløp" vil bli justert tilsvarende når man benytter +/-.
										Dette vises ikke i selve skjermbildet, men vil være den verdien som blir skrevet i rubr. 22 ved sending til TVINN og også ved utskrift av deklarasjonen.
									</p>
									<p>	
										Verdien man taster i rubr. 22 i SKJERMBILDET vil være grunnlaget man deklarer mot på varelinjenivå (i neste skjermbilde ).
										For å unngå differanse mellom vareverdi og deklarert verdi ved avsluttning av deklarasjonen, justeres ikke rubr. 22 med andre kostnader før ved sending/utskrift.
									</p>
									<p>
										HVIS MAN IKKE ØNSKER AT RUBR. 22 SKAL PÅVIRKES KAN MAN BENYTTE KODENE
										<b>M</b>(-) ELLER <b>P</b>(+)
										<br/><br/>
										Når man taster M eller P i feltet vil totalt fakturabeløp i rubr. 22 ikke påvirkes / endres, men det blir gjort fratrekk i "Statistisk verdi" som er grunnlaget for beregning av toll og avgifter.
									</p>					        
								</span>
								</div>
								</td>
			 					
			 					<td class="text12">
			 						<input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="sebel2" id="sebel2" size="12" maxlength="11" value="${model.record.sebel2}">
			 						<select name="seval2" id="seval2">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="currency" items="${model.currencyCodeList}" >
					 				  		<option value="${currency.zkod}"<c:if test="${model.record.seval2 == currency.zkod}"> selected </c:if> >${currency.zkod}</option>
										</c:forEach>  
									</select>
									<a tabindex="-1" id="seval2IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									<%-- info span 
									<img onClick="showPop('seval2SearchInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
									<span style="position:absolute; left:920px; top:400px; width:350px; height:150px;" id="seval2SearchInfo" class="popupWithInputText"  >
						           		<div class="text10" align="left">
					           				<select class="text11" id="seval2Search" name="seval2Search" size="5" onDblClick="hidePop('seval2SearchInfo');">
						           				<option value="">-velg-</option>
						           				<c:forEach var="currency" items="${model.currencyCodeList}" >
						 				  			<option value="${currency.zkod}">${currency.zkod}&nbsp;${currency.ztxt}</option>
												</c:forEach>
						           			</select>
						           			
											<table width="100%" align="left" border="0">
												<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
												<tr align="left" >
													<td class="text11">&nbsp;<button name="sebel2SearchButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('seval2SearchInfo');">&nbsp;<spring:message code="systema.tvinn.sad.export.ok"/></button> 
													</td>
												</tr>
											</table>
										</div>
									</span>
									--%>
			 					</td>
							</tr>
												        
							<tr height="25"><td class="text">&nbsp;</td> </tr>	
				            
					        <tr height="10"><td></td> </tr>
	        					<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('brut_info');" onMouseOut="hidePop('brut_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <font class="text16RedBold" >*</font><span title="sevkb">Brutto vekt</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="brut_info" class="popupWithInputText text11"  >
					           			<b>Bruttovekt</b> 
					           			<p>
					           			Bruttovekt for sendingen. Overføres fra oppdraget.</b>
					           			</p>
								</span>
								</div>
								</td>	
					            <td >
					            		<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlueMandatoryField" name="sevkb" id="sevkb" size="10" maxlength="9" value="${model.record.sevkb}">
					            </td>
					        </tr>
				            <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('6_info');" onMouseOut="hidePop('6_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>6.</b><font class="text16RedBold" >*</font><span title="sentk">Antall Kolli</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="6_info" class="popupWithInputText text11"  >
				           			<b>6. Antall kolli</b>
				           			<p>Opplysningene overføres fra oppdragsregistreringen.</p>
				           			<p>KRAV TIL FELTET</p>
								</span>
								</div>
								</td>	
					            <td >
				            		<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlueMandatoryField" name="sentk" id="sentk" size="8" maxlength="7" value="${model.record.sentk}">
					            </td>
					        </tr>
					        <tr height="10"><td class="text">&nbsp;</td> </tr>
					        
					        <tr>
		 						<td class="text12" colspan="2" >
			 					<img onMouseOver="showPop('31_info');" onMouseOut="hidePop('31_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				&nbsp;<b>31.&nbsp;</b><span title="seft1/seft2">Beskrivelse</span>&nbsp;</b>
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute;top:2px; width:250px;" id="31_info" class="popupWithInputText text11"  >
				           			<b>31. Beskrivelse</b>&nbsp;
				           			<br/><br/>
				           			Benyttes ved Foreløpig deklarasjon(”Innstikk”) og dataliste
				           			<br/><br/>
				           			<ol>
									<li><b>Foreløpig deklarasjon(”Innstikk”)</b><br/>
										Skriv inn godsmerking antall,kollitype og vareslag.
										<br/>	
										Ved TVINN er det kun VARESLAG som skal skriver her (max 15 første bokstaver).
										(Som merking sendes ADR, Kolli hentes fra SAD, i type sendes KLL).
									</li>
									<li><b>Dataliste</b><br/>
										Når en har flere varelinjer på dataliste, kan en her taste en samlende varebeskrivelse for rubrikk 31 på hovedarket.
										(R.31 skal ikke rapporteres på hver linje på datalista.)
									</li>
				           			</ol>
								</span>
								</div>
								</td>
							</tr>
							<tr>
								<td colspan="2" class="text12">
			 						<input type="text" class="inputTextMediumBlue" name="seft1" id="seft1" size="55" maxlength="45" value="${model.record.seft1}">
			 					</td>
							</tr>	
							<tr>
								<td colspan="2" class="text12">
			 						<input type="text" class="inputTextMediumBlue" name="seft2" id="seft2" size="55" maxlength="45" value="${model.record.seft2}">
			 					</td>
							</tr>
							<tr height="10"><td class="text"></td></tr>	 
							<tr>
			 					<td class="text12" colspan="2" >
			 					<img onMouseOver="showPop('44_info');" onMouseOut="hidePop('44_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				&nbsp;<b>44.&nbsp;</b><span title="seft3">Fritekst/Tillegsopplysning</span>&nbsp;</b>
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute;top:2px; width:250px;" id="44_info" class="popupWithInputText text11"  >
				           			<b>44. Fritekst/Tillegsopplysning</b>&nbsp;
				           			<br/><br/>
				           			Benyttes ved Foreløpig deklarasjon(”Innstikk”) og dataliste
				           			<br/><br/>
									Nytt fra 1.12.01:<br/>
									Ved innførsel kreves tillatelse dersom frist for oppgjør er på over 10 dager. Ved foreløpig deklarasjon som ikke krever tillatelse skal ikke referansekoden FOR oppgis.
									Hvis tillatelse kreves, skriv FOR <tillatelsen journalnr.>
									<br/><br/>
									Ved <b>Dataliste</b>
									Når en har flere varelinjer på listepapir er det enkelte tollsteder som ønsker/krever tekst "Se dataliste".
									(Spesielt dersom det forekommer preferanse e.l. på linjene).				           			
								</span>
								</div>
								</td>
							</tr> 
							<tr>
								<td colspan="2" class="text12">
			 						<input type="text" class="inputTextMediumBlue" name="seft3" id="seft3" size="55" maxlength="45" value="${model.record.seft3}">
			 					</td>
							</tr>	
							<tr height="25"><td>&nbsp;</td></tr>
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('slaSammen_info');" onMouseOut="hidePop('slaSammen_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <span title="sefvk">Slå sammen:</span>
					            	<div class="text11" style="position: relative;" align="left">
					            	<span style="position:absolute;top:2px; width:250px;" id="slaSammen_info" class="popupWithInputText text11"  >
					           			<b>Slå sammen</b>
					           			<br/>
					           			<p>
				           				TODO
					           			</p>
									</span>
									</div>
				            	</td>
					            <td >
				            		<select name="sesam" id="sesam" >
				 				  		<option value="">-velg-</option>
								  		<option value="J"<c:if test="${model.record.sesam == 'J'}"> selected </c:if> >Ja</option>
								  		<option value="N"<c:if test="${model.record.sesam == 'N'}"> selected </c:if> >Nej</option>
									</select>
					            </td>
					        </tr>
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('forholdBN_info');" onMouseOut="hidePop('forholdBN_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <span title="sefvk">Forhold B/N-vekt:</span>
					            	<div class="text11" style="position: relative;" align="left">
					            	<span style="position:absolute;top:2px; width:250px;" id="forholdBN_info" class="popupWithInputText text11"  >
					           			<b>Forhold B/N-vekt</b>
					           			<br/>
					           			<p>
				           				Oppgi faktor for nettovekt hvis bare bruttovekt oppgis på varelinje. Standard forslag er 0,90.	
					           			</p>
									</span>
									</div>
				            	</td>
					            <td >
					            		<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sefvk" id="sefvk" size="5" maxlength="6" value="${model.record.sefvk}">
					            </td>
					        </tr>
					        <tr height="2"><td></td></tr>
					        <c:if test="${ model.record.sest == 'E' || model.record.sest == 'K' || model.record.sest == 'Å' || empty  model.record.sest }"> 
						   		<c:if test="${ empty model.record.setll }">
							        <tr>
							        	<td valign="top" class="text12" colspan="2">
							        		<img onMouseOver="showPop('changeStatusUser_info');" onMouseOut="hidePop('changeStatusUser_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            			Endre Status&nbsp;
							        		<img style="vertical-align: bottom;cursor: pointer;" id="updateStatusByUserImg" width="20px" height="20px" src="resources/images/changeStatus.png" border="0" alt="change status">
							        		<div class="text11" style="position: relative;" align="left">
							            	<span style="position:absolute; top:2px;" id="changeStatusUser_info" class="popupWithInputText text11"  >
							           			<b>Endre Status</b>
							           			<br/>
							           			<p>
							           				Statusen kan bare endres når:
							           				<ol>
							           				<li>Status = E, K, Å eller blank</li>
							           				<li>Løpenr er blank</li>
							           				
						           					</ol>	
							           			</p>
											</span>
											</div>	
							        	</td>
							        </tr>
						       </c:if>
					        </c:if>
					        
					        
					        
							<tr height="20"><td>&nbsp;</td>&nbsp;</tr>
							<tr><td class="text12" colspan="2"><b>Varelinje totaler&nbsp;</b></td></tr>
							<tr>
				        		<td class="text12Gray" align="left" >Kolli&nbsp;</td>
					        	<td >
				            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumOfAntalKolliInItemLines" id="sumOfAntalKolliInItemLines" size="10" maxlength="7" value="${ model.record.sumOfAntalKolliInItemLinesStr}">
				            		<c:if test="${not empty ( model.record.sumOfAntalKolliInItemLinesStr &&  model.record.sentk)}">
					            		<c:if test="${ model.record.sentk !=  model.record.sumOfAntalKolliInItemLinesStr}">
							            <img onMouseOver="showPop('itemsSumKolli_info');" onMouseOut="hidePop('itemsSumKolli_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="kolliantal warning">	
							            <div class="text11" style="position: relative;" align="left">
							            <span style="position:absolute; left:10px; top:0px;" id="itemsSumKolli_info" class="popupWithInputText"  >
							           		<font class="text11">
							           			<p>	
							           			Summen av antall pakker på produktnivå ikke er lik det angitte antall pakker i posten. Vi anbefaler at du leser hva som kunne være galt ved å sjekke varelinjene.
							           			Hvis nummeret til venstre er = <b>-1</b> betyr, at det er mere enn 0-varelinjer, og summen av varelinjer kolli antall er = 0 (som er fejl).
												</p>
											</font>	
										</span>
										</div>
					            		</c:if>
				            		</c:if>
					            </td>
					        </tr>
					        <tr>
				        		<td class="text12Gray" align="left" >Varelinjer &nbsp;</td>
					        	<td >
				            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumOfAntalItemLines" id="sumOfAntalItemLines" size="10" value="${ model.record.sumOfAntalItemLinesStr}">
				            		<c:if test="${not empty ( model.record.sumOfAntalItemLinesStr)}">
					            		<c:if test="${ model.record.sumOfAntalItemLines <= 0 }">
							            <img onMouseOver="showPop('itemsSum_info');" onMouseOut="hidePop('itemsSum_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="varelinjerantal warning">	
							            <div class="text11" style="position: relative;" align="left">
							            <span style="position:absolute; left:10px; top:0px;" id="itemsSum_info" class="popupWithInputText"  >
						           			<font class="text11" >Summen av ​​antallet varelinjer må vare > 0</font>
										</span>
										</div>	
					            		</c:if>
				            		</c:if>
					            </td>
					        </tr>
					        <tr>
					        		<td class="text12Gray" align="left" >Beløp&nbsp;</td>
						        	<td >
					            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumTotalAmountItemLines" id="sumTotalAmountItemLines" size="10" value="${ model.record.sumTotalAmountItemLinesStr}">
						            	<c:if test="${model.record.sumTotalAmountItemLines != model.record.sebel1Dbl}">
						            		<img onMouseOver="showPop('itemsAmountSum_info');" onMouseOut="hidePop('itemsAmountSum_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="vare-sum warning">	
							            <div class="text11" style="position: relative;" align="left">
							            <span style="position:absolute; left:10px; top:0px;" id="itemsAmountSum_info" class="popupWithInputText"  >
						           			<font class="text11" >Summen av Tollverdi(Σ) <b>matcher ikke</b> 22 Fakturasum</font>
										</span>	
										</div>
					            		</c:if>
					            </td>
					        </tr>
					        <tr>
					        		<td class="text12Gray" align="left" >Bruttovekt&nbsp;</td>
						        	<td >
					            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumTotalBruttoViktItemLines" id="sumTotalBruttoViktItemLines" size="10" value="${ model.record.sumTotalBruttoViktItemLinesStr}">
						            	<c:if test="${model.record.sumTotalBruttoViktItemLines != model.record.sevkbDbl}">
						            		<img onMouseOver="showPop('itemsBruttoVektSum_info');" onMouseOut="hidePop('itemsBruttoVektSum_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="bruttovekt warning">	
							            <div class="text11" style="position: relative;" align="left">
							            <span style="position:absolute; left:10px; top:0px;" id="itemsBruttoVektSum_info" class="popupWithInputText"  >
						           			<font class="text11" >Summen av Bruttovekt(Σ) <b>matcher ikke</b> Bruttovekt (hodenivå)</font>
										</span>	
										</div>
					            		</c:if>
					            </td>
					        </tr> 						        
						</table>
					</td>
				</tr>
				<tr height="20"><td class="text"></td></tr>
				        
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
			 			<table width="80%" align="left" border="0" cellspacing="0" cellpadding="0">
			 				<tr height="5">
			 					<td class="text">&nbsp;</td> 
			 					<td class="text">&nbsp;</td> 
			 				</tr>
			 				<tr >
				            	<td class="text">&nbsp;</td> 
		 						<td class="text">&nbsp;</td> 
			 				</tr>
				            <tr >	
			            		<td class="text">&nbsp;</td> 
			 				    <td class="text9BlueGreen" valign="bottom" align="right" >
	
			 				    <%-- only status = M or emtpy status is allowed --%>
			 				    <c:choose>
				 				    <c:when test="${ model.record.sest == 'M' || empty  model.record.sest }">
					 				    	<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" id="submit" onclick="javascript: form.action='tvinnsadexport_edit.do';" value='<spring:message code="systema.tvinn.sad.export.createnew.submit"/>'/>
					 				    	&nbsp;&nbsp;
					 				    	<c:if test="${not empty  model.record.setdn && model.record.validUpdate}">
					 				    		<input tabindex=-2 class="inputFormSubmit" type="submit" name="send" id="send" onclick="javascript: form.action='tvinnsadexport_send.do';" value='<spring:message code="systema.tvinn.sad.export.createnew.send"/>'/>
					 				    	</c:if>
				 				    </c:when>
				 				    <c:otherwise>
				 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='<spring:message code="systema.tvinn.sad.submit.not.editable"/>'/>
				 				    </c:otherwise>	
			 				    </c:choose>
		 				    
                				</td>
					        </tr>
				            
						</table>
					</td>
				</tr>
				
			</table>
		</td>
		</tr>
		<tr height="20"><td colspan="2">&nbsp;</td></tr>
		
		<tr height="20"><td colspan="2">&nbsp;</td></tr>
		<%-- suplerende info here (if needed) --%>		
	</table>  
	</form>
	</td>
 </tr>
 <tr>
	<td>
		<%-- change status admin dialog --%>	
		<div id="dialogUpdateStatus" title="Dialog">
			<form action="tvinnsadexport_updateStatus.do" name="updateStatusForm" id="updateStatusForm" method="post">
			 	<input type="hidden" name="currentAvd" id="currentAvd" value="${model.record.seavd}">
			 	<input type="hidden" name="currentOpd" id="currentOpd" value="${model.record.setdn}">
				<p class="text12" >Change status as needed.</p>
				<table>
					<tr>
						<td class="text12" align="left" >&nbsp;Status</td>
						<td class="text12MediumBlue">
							<select name="selectedStatus" id="selectedStatus">
			            		<option value=" ">-velg-</option>
		            		  	<option value="A">A</option>
							  	<option value="C">C</option>
							  	<option value="D">D</option> 
							  	<option value="E">E</option>
							  	<option value="F">F</option>
							  	<option value="K">K</option> 
							  	<option value="L">L</option>
							  	<option value="M">M</option>
							  	<option value="P">P</option> 
							  	<option value="Q">Q</option>
							  	<option value="T">T</option>
							  	<option value="U">U</option>
							  	<option value="V">V</option>
							  	<option value="1">1</option>
							  	<option value="2">2</option>
							  	<option value="3">3</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</td>
</tr> 

<%-- --------------------------- --%>	
 <%-- Ombereg. på Ombereg. dialog --%>	
 <%-- --------------------------- --%>
 <tr>
	<td>
		<div id="dialogOmberegningPaOmberegning" title="Dialog">
			<form  action="tvinnsadexport_edit_omberegning.do" name="copyOmberegningPaOmberegningForm" id="copyOmberegningPaOmberegningForm" method="post">
			 	<input type="hidden" name="action" id="action" value='doFetch'/>
				<input type="hidden" name="avd" id="avd" value='${ model.record.seavd}'/>
				<input type="hidden" name="sign" id="sign" value='${ model.record.sesg}'/>
				<input type="hidden" name="opd" id="opd" value='${ model.record.setdn}'/>
				<input type="hidden" name="status" id="status" value='${ model.record.sest}'/>
				<input type="hidden" name="fabl" id="fabl" value='${ model.record.sebel1}'/>
				<input type="hidden" name="o2_sest" id="o2_sest" value='${ model.record.o2_sest}'/>
				<input type="hidden" name="o2_sedt" id="o2_sedt" value='${ model.record.o2_sedt}'/>
				<input type="hidden" name="o2_semf" id="o2_semf" value='${ model.record.o2_semf}'/>
				
				<p class="text14" >Denne fortollingen er <b>allerede omberegnet</b>
				<br><br>
				Vad vill du velge?
				</p>
									
				<table>
					<tr height="5"><td ></td></tr>
					<tr>
						<td class="text12MediumBlue">
							<select name="selectedOmb" id="selectedOmb">
								<option value="">Vis omberegning</option>
			            		<option value="NYO">Ny omb. basert på original fortolling</option>
			 				  	<option value="NYS">Ny omb. basert på siste ombereg.</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</td>
</tr>

<%-- -------------------------- --%>	
 <%-- print skilleark dialog    --%>	
 <%-- -------------------------- --%>	
 <tr>
	<td>
		<div id="dialogPrintSkilleArk" title="Dialog">
			<form action="tvinnsadexport_edit_printSkilleArkTopic.do" name="skilleArkForm" id="skilleArkForm" method="post">
			 	<input type="hidden" name="currentAvd" id="currentAvd" value="${model.record.seavd}">
			 	<input type="hidden" name="currentOpd" id="currentOpd" value="${model.record.setdn}">
				<table>
					<tr>
						<td class="text12" align="left" >&nbsp;Type</td>
						<td class="text12MediumBlue">
							<select name="selectedType" id="selectedType">
			            		<option value="">-velg-</option>
			            		<c:forEach var="record" items="${model.typeArchiveCodeList}" >
			 				  		<option value="${record.artype}">${record.artype}&nbsp;${record.artxt}</option>
								</c:forEach>  
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</td>
</tr> 
	
 
 <%-- -------------------------- --%>	
 <%-- upload file dialog         --%>	
 <%-- -------------------------- --%>	
	<tr>
		<td valign="bottom" >
			<div id="dialogUploadArchiveDocument" title="Dialog">
				<table align="left" class="popupFloatingWithRoundCorners3D">
				    <tr height="2"><td></td></tr>
			    	<tr>
					<td valign="top">
					<form name="uploadFileForm" id="uploadFileForm" method="post" enctype="multipart/form-data">
						<input type="hidden" name="applicationUserUpload" id="applicationUserUpload" value='${user.user}'>
						<input type="hidden" name="wsavd" id="wsavd" value='${model.record.seavd}'>
						<input type="hidden" name="wsopd" id="wsopd" value='${model.record.setdn}'>
						<input type="hidden" name="userDate" id="userDate" value=''>
						<input type="hidden" name="userTime" id="userTime" value=''>
						
							<table id="containerdatatableTable" cellspacing="2" align="left">
								<tr>
									<td colspan="3" class="text12Bold">&nbsp;
										<img style="vertical-align:bottom;" src="resources/images/upload.png" border="0" width="20" height="20" alt="upload">
										&nbsp;File Upload&nbsp;							
									</td>
								</tr>
								<tr>
								<tr height="5"><td></td></tr>
								<tr>
								<td>
									<table>
									<%--
									<tr>
										<td class="text11">&nbsp;Nytt filnavn:</td>
										<td class="text11">&nbsp;<input tabindex=-1 type="text" class="inputText" name="fileNameNew" id="fileNameNew" size="20" maxlength="20" value=""></td>
									</tr>
									 --%>
									<tr>
										<td class="text11">&nbsp;Arkiv typen:</td>
										<td class="text11">&nbsp;
											<select tabindex=-1 name="wstype" id="wstype">
												<c:forEach var="record" items="${user.arkivKodOpdList}" >
						                       	 	<option value="${record.arkKod}">${record.arkKod}-${record.arkTxt}</option>
												</c:forEach> 
											</select>	
										</td>
									</tr>
									<tr height="5"><td></td></tr>
									<tr>	
										<td class="text11">&nbsp;Fil:</td>
										<td class="text11">
			           						&nbsp;<input type="file" name="fileUpload" id="fileUpload" />
			       						</td>
					           		</tr>
					           		</table>
								</td>
								</tr>
								<tr height="5"><td></td></tr>
			       			</table>
					</form>	
					</td>
					</tr>
				</table>
		</div>		
		</td>
	</tr>

	

	