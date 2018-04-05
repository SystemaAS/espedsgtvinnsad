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
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadimport_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
				<input type="hidden" name="modelStatus" id="modelStatus" value='${model.record.sist}'>
			</td>
		</tr>
		<tr height="25"> 
			<c:choose> 
			    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
			    	<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkTopicList" style="display:block;" 
							<c:choose>
								<c:when test="${empty model.record.sisg}">href="tvinnsadimport.do?action=doFind&sg=${user.tvinnSadSign}"</c:when>
								<c:otherwise>href="tvinnsadimport.do?action=doFind&sg=${model.record.sisg}"</c:otherwise>
							</c:choose> > 
							<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.import.list.tab"/></font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;<spring:message code="systema.tvinn.sad.import.created.mastertopic.tab"/>
						</font>
						<font class="text12MediumBlue">[${model.record.sitdn}]</font>
						<c:if test="${ model.record.sist == 'M' || empty  model.record.sist}">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkInvoices" style="display:block;" href="tvinnsadimport_edit_finansopplysninger.do?action=doFetch&avd=${ model.record.siavd}&sign=${ model.record.sisg}
													&opd=${ model.record.sitdn}
													&status=${ model.record.sist}&fabl=${ model.record.sibel3}&o2_sist=${ model.record.o2_sist}&o2_sidt=${ model.record.o2_sidt}&o2_simf=${ model.record.o2_simf}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.import.finansopplys.createnew.tab"/>
							</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkNotis" style="display:block;" href="editNotisblock.do?action=doFetch&subsys=sadi&orig=topic&avd=${ model.record.siavd}&sign=${ model.record.sisg}
													&opd=${ model.record.sitdn}&o2_sist=${ model.record.o2_sist}&o2_sidt=${ model.record.o2_sidt}&o2_simf=${ model.record.o2_simf}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.import.notisblock.createnew.tab"/>
							</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkItemLines" style="display:block;" href="tvinnsadimport_edit_items.do?action=doFetch&avd=${ model.record.siavd}&sign=${ model.record.sisg}
													&opd=${ model.record.sitdn}&status=${ model.record.sist}&fabl=${model.record.sibel3}&o2_sist=${ model.record.o2_sist}&o2_sidt=${ model.record.o2_sidt}&o2_simf=${ model.record.o2_simf}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.import.item.createnew.tab"/>
							</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkLogging" style="display:block;" href="tvinnsadimport_logging.do?avd=${ model.record.siavd}&sign=${ model.record.sisg}
													&opd=${model.record.sitdn}&status=${model.record.sist}&o2_sist=${ model.record.o2_sist}&o2_sidt=${ model.record.o2_sidt}&o2_simf=${ model.record.o2_simf}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.import.logging.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkArchive" style="display:block;" href="tvinnsadimport_archive.do?avd=${model.record.siavd}&sign=${model.record.sisg}
													&opd=${model.record.sitdn}&status=${model.record.sist}&o2_sist=${ model.record.o2_sist}&o2_sidt=${ model.record.o2_sidt}&o2_simf=${ model.record.o2_simf}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.import.archive.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
						</a>
					</td>
					
					<%-- We must check if this tolddkl. qualifies for omberegning --%>
					<c:if test="${ not empty model.record.sitll }"> 
					 	<c:if test="${ (empty model.record.sist || model.record.sist == 'P' || model.record.sist == 'U')  || (empty model.record.o2_sist  || model.record.o2_sist == 'M' || model.record.o2_sist == 'Z' || model.record.o2_sist == 'Q' || model.record.o2_sist == 'P' )}">
						<%-- ------------------------------------------------------------------------------------------ --%>
						<%-- We must redirect to different behaviors if there is an existent omberegning already or not --%>
						<%-- ------------------------------------------------------------------------------------------ --%>
						<c:choose> 
							<c:when test="${ (model.record.o2_sist == 'P' || model.record.o2_sist == 'Z' ) &&  not empty model.record.o2_simf }">
								<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
								<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
									<a id="alinkOmberegningPaOmberegning" style="display:block;" runat="server" href="#">
										<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.import.omberegning.mastertopic.tab"/></font>
									</a>
								</td>
							</c:when>
							<c:otherwise>
								<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
								<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
									<a id="alinkOmberegning" style="display:block;" href="tvinnsadimport_edit_omberegning.do?action=doFetch&avd=${ model.record.siavd}&sign=${ model.record.sisg}
																&opd=${ model.record.sitdn}&status=${ model.record.sist}&fabl=${ model.record.sibel3}&o2_sist=${ model.record.o2_sist}&o2_sidt=${ model.record.o2_sidt}&o2_simf=${ model.record.o2_simf}">
										<font class="tabDisabledLink">
											&nbsp;<spring:message code="systema.tvinn.sad.import.omberegning.mastertopic.tab"/>
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
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkTopicList" style="display:block;" 
							<c:choose>
								<c:when test="${empty model.record.sisg}">href="tvinnsadimport.do?action=doFind&sg=${user.tvinnSadSign}"</c:when>
								<c:otherwise>href="tvinnsadimport.do?action=doFind&sg=${model.record.sisg}"</c:otherwise>
							</c:choose> > 
							<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.import.list.tab"/></font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.import.createnew.tab"/></font>
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
	<form name="sadImportSaveNewTopicForm" id="sadImportSaveNewTopicForm" method="post">
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
			<%-- --- HIDDEN FORM FIELDS (not visible in form but important with an UPDATE ----- --%>			
			<%-- general (from user profile) --%>
			<input type="hidden" name="action" id="action" value='doUpdate'>
			<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
			<input type="hidden" name="opd" id="opd" value='${model.record.sitdn}'>
			<%-- topic specific (syop and refnr) --%>
			<input type="hidden" name="siavd" id="siavd" value='${model.record.siavd}'>
			<input type="hidden" name="sitdn" id="sitdn" value='${model.record.sitdn}'>
			<input type="hidden" name="sist" id="sist" value='${model.record.sist}'>
			<input type="hidden" name="sidt" id="sidt" value='${model.record.sidt}'>
			<input type="hidden" name="sidst" id="sidst" value='${model.record.sidst}'>
			<input type="hidden" name="sitarf" id="sitarf" value='${model.record.sitarf}'>
			
			
		<tr height="4">
			<td colspan="2">&nbsp;
				<%-- test indicator /per avdelning --%> 
				<c:forEach var="record" items="${avdListSessionTestFlag}" >
					<c:if test="${record.avd == model.record.siavd}">	
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
	    	<input type="hidden" name="avd" id="avd" value='${model.record.siavd}'>
			<input type="hidden" name="sisg" id="sisg" value='${model.record.sisg}'>
			<input type="hidden" name="sibel4" id="sibel4" value='${model.record.sibel4}'>
			<input type="hidden" name="sibelr" id="sibelr" value='${model.record.sibelr}'>
			<input type="hidden" name="sibels" id="sibels" value='${model.record.sibels}'>
			
			<tr >
				<td align="left" class="text12MediumBlue" >
					&nbsp;&nbsp;&nbsp;&nbsp;<span title="siavd">Avdeling:</span>&nbsp;<b>${model.record.siavd}</b>&nbsp;&nbsp;<span title="sitdn">Tolldeknr:&nbsp;</span><b>${model.record.sitdn}</b>
					&nbsp;&nbsp;<span title="sisg">Sign:</span>&nbsp;<b>${model.record.sisg}</b>
					&nbsp;&nbsp;
					<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					Stat<a tabindex=-1 id="updateStatusLink" name="updateStatusLink" runat="server" href="#"><font class="text12MediumBlue">u</font></a>s:
					<b>
						<c:choose>
							<c:when test="${empty model.record.sist}">
								&nbsp;
							</c:when>
							<c:otherwise>
								${model.record.sist}
							</c:otherwise>
						</c:choose>
					</b>
					&nbsp;<span title="sidt">Opprettelsesdato:</span>&nbsp;<b>${model.record.sidt}</b>
					<div class="text11" style="position: relative;" align="left">
					<span style="position:absolute; top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
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
					</span>	
					</div>						
					
				</td>
				<td align="right" valign="top" >
					<input tabindex=-1 type="checkbox" name="simi" id="simi" value="I" <c:if test="${model.record.simi == 'I'}"> checked </c:if> ><font class="text12MediumBlue"><b>Foreløpig</b></font>&nbsp;&nbsp;&nbsp;
					<c:if test="${'2' != isTestAvd}">
						<input tabindex=-1 type="checkbox" name="si0035" id="si0035" value="2" <c:if test="${model.record.si0035 == '2'}"> checked </c:if> ><font class="text12MediumBlue"><b>TEST flagg</b></font>&nbsp;&nbsp;&nbsp;
					</c:if>
					<a tabindex=-1 href="tvinnsadimport_edit_printTopic.do?avd=${model.record.siavd}&opd=${model.record.sitdn}">
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
					&nbsp;&nbsp;&nbsp;&nbsp;<span title="sidst">Tarifferingsted:</span>&nbsp;<b>${model.record.sidst}</b>
					&nbsp;&nbsp;<span title="sitarf">Tariffør:</span>&nbsp;<b>${model.record.sitarf}</b>
					
				</td>
			</tr>
			
			<%-- test indicator /per avdelning --%> 
			<c:if test="${'2' == isTestAvd}">
				<tr height="10"><td colspan="2"></td></tr>
				<tr>
					<td align="left" class="text14Red" >
						&nbsp;&nbsp;<b>[ TEST Avdelning ]</b>
					</td>
				</tr>
			</c:if>
		</c:when>
		<%-- CREATE MODE --%> 
		<c:otherwise>
			<tr >
				<td align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title="siavd"><font class="text16RedBold" >*</font>Avdeling:&nbsp;
           			<select name="avd" id="avd">
	            		<option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                             <option value="${record.avd}"<c:if test="${model.record.siavd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '2'}">&nbsp;(test)</c:if></option>
						</c:forEach> 
					</select>
					&nbsp;<span title="sisg"><font class="text16RedBold" >*</font>Sign:</span>&nbsp;
           			<select name="sisg" id="sisg">
	            		<option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                           	 	<c:choose>
								<c:when test="${empty model.record.sisg}">
									<option value="${record.sign}"<c:if test="${user.tvinnSadSign == record.sign}"> selected </c:if> >${record.sign}</option>
								</c:when>
								<c:otherwise>
									<option value="${record.sign}"<c:if test="${model.record.sisg == record.sign}"> selected </c:if> >${record.sign}</option>
								</c:otherwise>
								</c:choose>
						</c:forEach> 
					</select>
				</td>
				<td align="right"><input type="checkbox" name="simi" id="simi" value="I" <c:if test="${model.record.simi == 'I'}"> checked </c:if> >
				<font class="text12MediumBlue"><b>Foreløpig</b></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
				</td>
			</tr>	
		</c:otherwise>
		</c:choose>

		<tr height="10"><td colspan="2">&nbsp;</td></tr>
		<%-- --------------- --%>
		<%-- LEFT SIDE CELL --%>
		<%-- --------------- --%>
		<tr>
		<td width="53%" valign="top">
		<table width="96%" border="0" cellspacing="0" cellpadding="0">
			<tr>
	            <td width="5">&nbsp;</td>
	            <td >
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td class="text12" align="left">
				 				<img onMouseOver="showPop('1_1_info');" onMouseOut="hidePop('1_1_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>1.1</b><font class="text16RedBold" >*</font><span title="sidty">Dekl.type&nbsp;</span>
			 					<div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute; top:2px; width:250px;" id="1_1_info" class="popupWithInputText text11"  >
					           			<b>1.1 Dekl.type</b>
					           			<ul>
					           				<li><b>EU</b> innførsel fra et EØS,EFTA eller EU-land</li>
					           				<li><b>IM</b> innførsel fra land som ikke er tilknyttet EØS, EFTA eller EU (tredjeland).
					           				</li>
					           			</ul>
					           			<p>
					           			KODEN KAN LEGGES INN SOM STANDARDVERDI PR.AVDELING. 
					           			KAN OVERSTYRES UNDER DEKLARERINGEN.
					           			</p>
								</span>	
								</div>	
				 			</td>
				 			<td>
				 				<select class="inputTextMediumBlueMandatoryField" name="sidty" id="sidty" >
				 				  <option value="">-velg-</option>
								  <option value="EU"<c:if test="${model.record.sidty == 'EU'}"> selected </c:if> >EU</option>
								  <option value="IM"<c:if test="${model.record.sidty == 'IM'}"> selected </c:if> >IM</option>
								</select>
			 				</td>
			 				<td class="text12">
			 				<img onMouseOver="showPop('prosedyr_info');" onMouseOut="hidePop('prosedyr_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 				<div class="text11" style="position: relative;display:inline;" align="left">
			 				<span style="position:absolute; top:2px; width:250px;" id="prosedyr_info" class="popupWithInputText text11">
				           		<ul>
				           			<c:forEach var="record" items="${model.ekspedisjonstyperImportCodeList}" >
				           			<li><b>${record.zkod}</b>&nbsp;${record.ztxt}</li>
				           			</c:forEach>
				           		</ul>
							</span>
							</div>
			 				<b>1.2</b><font class="text16RedBold" >*</font><span title="sidp">&nbsp;Eksped.type</span>
			 				</td>
				 			<td>
				 				<select class="inputTextMediumBlueMandatoryField" name="sidp" id="sidp" >
				 				  <option value="">-velg-</option>
					 				  	<c:forEach var="record" items="${model.ekspedisjonstyperImportCodeList}" >
					 				  		<option value="${record.zkod}"<c:if test="${model.record.sidp == record.zkod}"> selected </c:if> >${record.zkod}</option>
										</c:forEach>  
								</select>
				 			</td>
		 				</tr>
		 				<tr>
		 					<td class="text12">
		 						<table>
		 						<tr>
		 						<td align="left">
			 						<img onMouseOver="showPop('tollMva_info');" onMouseOut="hidePop('tollMva_info'); "style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		 						</td>
		 						<td class="text12" align="center">
					 				<span title="siski" id="v_siski" class="validation">Toll/Mva</span>
					 				<div class="text11" style="position: relative;" align="left">
					 				<span style="position:absolute; top:2px; width:250px;" id="tollMva_info" class="popupWithInputText text11" >
					           			<b>Toll/Mva</b>
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
								</tr>
				 				</table>
				 			</td>
				 			<td>
				 				<select name="siski" id="siski" >
				 					<option value="">-velg-</option>
								  <option value="S"<c:if test="${ model.record.siski == 'S'}"> selected </c:if> >S</option>
								  <option value="K"<c:if test="${ model.record.siski == 'K'}"> selected </c:if> >K</option>
								  <option value="I"<c:if test="${ model.record.siski == 'I'}"> selected </c:if> >I</option>
								</select>
			 				</td>
			 				<td class="text12">
			 					<img onMouseOver="showPop('ens_flag_info');" onMouseOut="hidePop('ens_flag_info'); "style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<span title="sikddk">&nbsp;Dagsopp./Kontant</span>
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute; top:2px; width:250px;" id="ens_flag_info" class="popupWithInputText text11"  >
						           		<b>Dagsoppgjør/Kontant</b>
						           		<p>
						           		Kodevalg for hvilke tekst man ønsker på deklarasj. dersom S/K/I er brukt.
						           		</p>
						           		<ul>
						           			<li><b>D</b> = Dagsoppgjør</li>
						           			<li><b>K</b> = Kontant</li>						           			
						           		</ul>
								</span>
								</div>
			 				</td>
			 				<td>
				 				<select name="sikddk" id="sikddk" >
				 				  <option selected value="">-velg-</option>
								  <option value="D"<c:if test="${model.record.sikddk == 'D'}"> selected </c:if> >D</option>
								  <option value="K"<c:if test="${model.record.sikddk == 'K'}"> selected </c:if> >K</option>
								</select>
			 				</td>
		 				</tr>
		 				<tr height="10"><td></td></tr>
		 				<tr>
		 					<td align="left" class="text12">&nbsp;
		 						<span title="h_xref">Ext.referanse&nbsp;</span>
				 			</td>
				 			<td class="text12" align="left">
				 				<input type="text" class="inputText" name="h_xref" id="h_xref" size="15" maxlength="35" value="${model.record.h_xref}">
			 				</td>
				 			<td class="text12" >&nbsp;
		 						<span title="sidtg" id="v_sidtg" class="validation">Dekl. godj.dato&nbsp;</span>
				 			</td>
			 				<td class="text12" align="left">
			 					<input readonly type="text" class="inputTextReadOnly" name="sidtg" id="sidtg" size="12" maxlength="8" value="${model.record.sidtg}">
		            			</td>
				 		</tr>
				 		<tr>	
				 			<td class="text12">&nbsp;
		 						<span title="sitll" id="v_sitll" >Løpenr.&nbsp;</span>
			 				</td>
			 				<td class="text12" align="left">
			            			<input readonly type="text" class="inputTextReadOnly" name="sitll" id="sitll" size="12" maxlength="10" value="${model.record.sitll}">
		            			</td>
		            			<td class="text12">&nbsp;
		 						<span title="sitle" id="v_sitle" >Ekspsted&nbsp;</span>
			 				</td>
			 				<td class="text12" align="left">
			            			<input readonly type="text" class="inputTextReadOnly" name="sitle" id="sitle" size="12" maxlength="6" value="${model.record.sitle}">
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
					 			<b>&nbsp;2.</b>&nbsp;Avsender / Eksportør&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute; top:2px; width:250px;" id="2_info" class="popupWithInputText text11"  >
					           		<b>Avsender / Eksportør</b>
					           		<p>
					           		Oppgi selgerens navn og adresse. 
					           		Dersom ekspedisjonen omfatter fakturaer fra flere selgere enn det er plass til i rubrikken, skrives "se vedlagte fakturaer". 
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
							        	<input type="hidden" name="orig_sikns" id="orig_sikns" value='${model.record.sikns}'>
							        	<input type="hidden" name="orig_sinas" id="orig_sinas" value='${model.record.sinas}'>
							        	<input type="hidden" name="orig_siads1" id="orig_siads1" value='${model.record.siads1}'>
							        	<input type="hidden" name="orig_siads2" id="orig_siads2" value='${model.record.siads2}'>
							        	<input type="hidden" name="orig_siads3" id="orig_siads3" value='${model.record.siads3}'>
							        	
							            <td class="text12" align="left" >&nbsp;&nbsp;
								            <span title="sikns">Kundenummer</span>
								            <img title="Spørring på fritekster for kunder" id="senderFreeTextImg" onMouseOver="showPop('senderFtxtinfo');" onMouseOut="hidePop('senderFtxtinfo');" onClick="showPop('senderInfoFreeTextDialog');" style="vertical-align:top;" width="18px" height="18px" src="resources/images/largeTextContent.png" border="0" alt="info">
								 			<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; top:2px; width:250px;" id="senderFtxtinfo" class="popupWithInputText"  >
								           		<font class="text11" >Spørring på fritekster for kunder</font>
											</span>
											</div>
											
								 			<span style="position:absolute; left:300px; top:300px; width:400px; height:500px;" id="senderInfoFreeTextDialog" class="popupWithInputText"  >
								           		<div class="text11" align="left">
								           		Spørring på fritekster for kunder
								           		<p>
								           			<textarea rows="20" cols="40" class="inputText" name="senderInfoTextArea" id="senderInfoTextArea" maxlength="1000"></textarea>
								           		</p>
								           		<p>
								           			&nbsp;&nbsp;<button name="senderInfoFreeTextDialogCloseOk" id="senderInfoFreeTextDialogCloseOk" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('senderInfoFreeTextDialog');"><spring:message code="systema.tvinn.sad.import.ok"/></button>
								           		</p>
								           		</div>
											</span>
											
											
											
							            </td>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="sinas" id="v_sinas" class="validation"><font class="text16RedBold" >*</font>Navn&nbsp;</span>
							            	<a tabindex="-1" id="sinasIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>				
							            </td>
							        </tr>
							        <tr>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue" name="sikns" id="sikns" size="9" maxlength="8" value="${model.record.sikns}"></td>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlueMandatoryField"  name="sinas" id="sinas" size="31" maxlength="30" value="${model.record.sinas}"></td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="siads1" id="v_siads1" class="validation"><font class="text16RedBold" >*</font>Adresse-1</span></td>
							            <td>&nbsp;</td>
							        </tr>
							        <tr>
							            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlueMandatoryField"  name="siads1" id="siads1" size="40" maxlength="30" value="${model.record.siads1}"></td>
    							            
							        </tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="siads2" id="v_siads2" class="validation">Adresse-2</span></td>
    							            <td>&nbsp;</td>
							        </tr>
							        <tr>
							            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="siads2" id="siads2" size="40" maxlength="30" value="${model.record.siads2}"></td>
   							            
							        </tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="siads3" id="v_siads3" class="validation">Adresse-3</span></td>
   							            <td>&nbsp;</td>							            
							        </tr>
							        <tr>
							            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="siads3" id="siads3" size="40" maxlength="30" value="${model.record.siads3}"></td>
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
				 				<b>&nbsp;8.</b><font class="text16RedBold" >*</font>&nbsp;Mottaker&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute; top:2px; width:250px;" id="8_info" class="popupWithInputText text11"  >
						           		<b>Mottaker</b>
						           		<p>
						           		Oppgi mottakers/vareeiers navn og adresse.
						           		</p>
						           		<p> 
						           		I henhold til tollovens paragraf 1, pkt 3, er vareeier den som overfor tollvesenet er legitimert til å råde over varene. 
						           		Her oppgis også vareeierens 8-sifrede organisasjonsnr.
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
							        	<input type="hidden" name="orig_siknk" id="orig_siknk" value='${model.record.siknk}'>
							        	<input type="hidden" name="orig_sinak" id="orig_sinak" value='${model.record.sinak}'>
							        	<input type="hidden" name="orig_sirg" id="orig_sirg" value='${model.record.sirg}'>
							        	<input type="hidden" name="orig_siadk1" id="orig_siadk1" value='${model.record.siadk1}'>
							        	<input type="hidden" name="orig_siadk2" id="orig_siadk2" value='${model.record.siadk2}'>
							        	<input type="hidden" name="orig_siadk3" id="orig_siadk3" value='${model.record.siadk3}'>
							        	
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="siknk">Kundenummer</span>
							            		<img id="receiverFreeTextImg" onMouseOver="showPop('receiverFtxtinfo');" onMouseOut="hidePop('receiverFtxtinfo');" onClick="showPop('receiverInfoFreeTextDialog');" style="vertical-align:top;" width="18px" height="18px" src="resources/images/largeTextContent.png" border="0" alt="info" title="Spørring på fritekster for kunder" >
								 			<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; top:2px; width:250px;" id="receiverFtxtinfo" class="popupWithInputText"  >
								           		<font class="text11" >Spørring på fritekster for kunder</font>
											</span>
											</div>
											
											<span style="position:absolute; left:300px; top:450px; width:400px; height:500px;" id="receiverInfoFreeTextDialog" class="popupWithInputText"  >
								           		<div class="text11" align="left">
								           		Spørring på fritekster for kunder
								           		<p>
								           			<textarea rows="20" cols="40" class="inputText" name="receiverInfoTextArea" id="receiverInfoTextArea" maxlength="1000"></textarea>
								           		</p>
								           		<p>
								           			&nbsp;&nbsp;<button name="receiverInfoFreeTextDialogCloseOk" id="receiverInfoFreeTextDialogCloseOk" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('receiverInfoFreeTextDialog');"><spring:message code="systema.tvinn.sad.import.ok"/></button>
								           		</p>
								           		</div>
											</span>
											
											
							            </td>
							            <td class="text12" align="left" >&nbsp;
							            <span title="sinak" id="v_sinak" class="validation"><font class="text16RedBold" >*</font>Navn&nbsp;</span>
							            	<a tabindex="-1" id="sinakIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>			
							            </td>
							        </tr>
							        <tr>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue" name="siknk" id="siknk" size="9" maxlength="8" value="${model.record.siknk}"></td>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlueMandatoryField"  name="sinak" id="sinak" size="31" maxlength="30" value="${model.record.sinak}"></td>
							        </tr>
							        <tr height="10"><td></td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="sirg" id="v_sirg" class="validation">Regnr</span></td>
							            <td class="text12" ><font class="text16RedBold" >*</font><span title="simva">Momregistrert&nbsp;</span></td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField"  name="sirg" id="sirg" size="20" maxlength="11" value="${model.record.sirg}"></td>
							            <td align="left">
							            	<select class="inputTextMediumBlueMandatoryField" name="simva" id="simva" >
							 				  <option value="J"<c:if test="${ model.record.simva == 'J' || empty model.record.simva}"> selected </c:if> >Ja</option>
						 				  	  <option value="N"<c:if test="${ model.record.simva == 'N'}"> selected </c:if> >Nei</option>
											</select>
										</td>
							        </tr>
							        
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text12" >
						 					<img onMouseOver="showPop('48_info');" onMouseOut="hidePop('48_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				&nbsp;<b>48.&nbsp;</b><span title="siktc/sikta/siktb">Kontonr.Tollkredit&nbsp;</span>
							 				<div class="text11" style="position: relative;" align="left">
							 				<span style="position:absolute; top:2px; width:250px;" id="48_info" class="popupWithInputText text11"  >
							           			<b>48. Kontonr.Tollkredit</b>&nbsp;
												<p>
							           			Hentes fra kunderegister. Dersom det er en kunde som tidligere har vært uten kred, så kan man taste inn krednr.
							           			</p>
											</span>
											</div>
										</td>
							        </tr>
							        <tr>
							            <td align="left">
							            		<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="siktc" id="siktc" size="1" maxlength="1" value="${model.record.siktc}">
											<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="sikta" id="sikta" size="5" maxlength="5" value="${model.record.sikta}">
											<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="siktb" id="siktb" size="2" maxlength="2" value="${model.record.siktb}">
										</td>	
							        </tr>
							         
							         
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="siadk1" id="v_siadk1" class="validation">Adresse-1</span></td>
							            <td>&nbsp;</td>
							        </tr>
							        <tr>
							            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="siadk1" id="siadk1" size="40" maxlength="30" value="${model.record.siadk1}"></td>
    							            
							        </tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="siadk2" id="v_siadk2" class="validation">Adresse-2</span></td>
    							            <td>&nbsp;</td>
							        </tr>
							        <tr>
							            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="siadk2" id="siadk2" size="40" maxlength="30" value="${model.record.siadk2}"></td>
   							            
							        </tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="siadk3" id="v_siadk3" class="validation">Adresse-3</span></td>
   							            <td>&nbsp;</td>							            
							        </tr>
							        <tr>
							            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="siadk3" id="siadk3" size="40" maxlength="30" value="${model.record.siadk3}"></td>
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
		 						<b>&nbsp;14.</b><font class="text16RedBold" >*</font>&nbsp;Deklarant&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute; top:2px; width:250px;" id="14_b_info" class="popupWithInputText text11"  >
					           		<b>14.Deklarant</b> 
				           			<p>
				           				Hentes automatisk fra standardverdiene for AVDELINGEN
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
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="sinad"><font class="text16RedBold" >*</font>Navn</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="sitlf"><font class="text16RedBold" >*</font>Telefon</span></td>
							        </tr>
							        <tr>
							            <td align="left"><input readonly type="text" class="inputTextReadOnly" name="sinad" id="sinad" size="35" maxlength="30" value="${model.record.sinad}"></td>
							            <td align="left"><input readonly type="text" class="inputTextReadOnly" name="sitlf" id="sitlf" size="15" maxlength="12" value="${model.record.sitlf}"></td>
							            
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
		 						<b>&nbsp;28.<font class="text16RedBold" >*</font></b>&nbsp;Finans<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
		 						<div class="text11" style="position: relative;" align="left">
		 						<span style="position:absolute; top:2px; width:250px;" id="28_info" class="popupWithInputText text11"  >
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
							            <td class="text12" align="left" >&nbsp;
							            <font class="text16RedBold" >*</font><span title="sifif">Fakt.nr.&nbsp;</span>
							            <input type="text" class="inputTextMediumBlueMandatoryField"  name="sifif" id="sifif" size="18" maxlength="17" value='${ model.record.sifif}'></td>
							            <td class="text12">&nbsp;&nbsp;&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sifid">Fakt.dato</span>
			 								<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField"  name="sifid" id="sifid" size="9" maxlength="6" value="${model.record.sifid}">
			 							</td>
							        </tr>
							        <tr height="5">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							        </tr>
							        <tr>
							            <td colspan="2" class="text12" align="left" >&nbsp;
							            <span title="finansOpplysningarTotSum/finansOpplysningarTotValidCurrency"></span>Fakturasum. fra Finans.oppl.&nbsp;</span>
							            <input readonly type="text" class="inputTextReadOnly"  name="finansOpplysningarTotSum" id="finansOpplysningarTotSum" size="15" value='${ model.record.finansOpplysningarTotSum}'>
							            &nbsp;&nbsp;
							            <input readonly type="text" class="inputTextReadOnly"  name="finansOpplysningarTotValidCurrency" id="finansOpplysningarTotValidCurrency" size="5" value='${ model.record.finansOpplysningarTotValidCurrency}'>
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
				 				<b>&nbsp;22.</b><font class="text16RedBold" >*</font>
				 				<span title="sibel3" id="v_sibel3" class="validation">Fakturasum&nbsp;</span>
				 			</td>
				 			<td align="left" >
				 				<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField"  name="sibel3" id="sibel3" size="20" maxlength="13" value="${model.record.sibel3}">				 				
				 				<%--
				 				<c:choose>
					 				<c:when test="${model.record.finansOpplysningarExist==true}">
						 				<input readonly type="text" class="inputTextReadOnly" name="sibel3" id="sibel3" size="20" maxlength="13" value="${model.record.sibel3}">
					 				</c:when>
					 				<c:otherwise>
						 				<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sibel3" id="sibel3" size="20" maxlength="13" value="${model.record.sibel3}">				 				
					 				</c:otherwise>
				 				</c:choose>
				 				 --%>
				 			</td>
				 			<td class="text12" align="left">
				 				&nbsp;
				 				<span title="sival3" id="v_sival3" class="validation"><font class="text16RedBold" >*</font>Valuta</span>
				 				<%-- Note: onChange event in jQuery for this currency list --%>
				 				<select class="inputTextMediumBlueMandatoryField" name="sival3" id="sival3" >
				 				  <option value="">-velg-</option>	
				 				  <c:forEach var="currency" items="${model.currencyCodeList}" >
			 				  		<option value="${currency.zkod}"<c:if test="${ model.record.sival3 == currency.zkod}"> selected </c:if> >${currency.zkod}</option>
								  </c:forEach>  
								</select>
								<a tabindex="-1" id="sival3IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								</a>
								<%-- info span 
								<img onClick="showPop('valutaSearchInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
								<span style="position:absolute; left:500px; top:850px; width:350px; height:150px;" id="valutaSearchInfo" class="popupWithInputText"  >
					           		<div class="text10" align="left">
				           				<select class="text11" id="sival3Search" name="sival3Search" size="5" onDblClick="hidePop('valutaSearchInfo');">
					           				<c:forEach var="currency" items="${model.currencyCodeList}" >
					 				  			<option value="${currency.zkod}">${currency.zkod}&nbsp;${currency.ztxt}</option>
											</c:forEach>
					           			</select>
					           			<table width="100%" align="left" border="0">
											<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
											<tr align="left" >
												<td class="text11">&nbsp;<button name="valutaSearchButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('valutaSearchInfo');">&nbsp;<spring:message code="systema.tvinn.sad.import.ok"/></button> 
												</td>
											</tr>
										</table>
									</div>
								</span>
								--%>
			 				</td>
		 				</tr>
		 				<tr>
			 				<td class="text12">
			 					<b>&nbsp;23.</b><font class="text16RedBold" >*</font><span title="sivku">Kurs&nbsp;</span>
				 			</td>
				 			<td class="text12" align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField"  name="sivku" id="sivku" size="10" maxlength="7" value="${model.record.sivku}"></td>
				 			
				 			<td class="text12" align="left" >&nbsp;
				 				<img onMouseOver="showPop('24_info');" onMouseOut="hidePop('24_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>24.</b><font class="text16RedBold" >*</font><span title="sitst">Tr.type</span>
				 				<select class="inputTextMediumBlueMandatoryField" name="sitst" id="sitst" >
				 				  <option value="">-velg-</option>	
				 				  <option value="1" <c:if test="${model.record.sitst == '1'}"> selected </c:if> >1</option>	
				 				  <option value="2" <c:if test="${model.record.sitst == '2'}"> selected </c:if> >2</option>	
				 				  <option value="3" <c:if test="${model.record.sitst == '3'}"> selected </c:if> >3</option>	
				 				  <option value="9" <c:if test="${model.record.sitst == '9'}"> selected </c:if> >9</option>	
				 				</select>
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute; top:2px; width:250px;" id="24_info" class="popupWithInputText text11"  >
					           		<b>24.&nbsp;Transaksjonstype</b><br/><br/>
									Oppgi med kode:
									<ul>
										<li><b>1</b>&nbsp;Kjøp i fast regning</li>
										<li><b>2</b>&nbsp;Konsignasjon/Kommisjon</li>
										<li><b>3</b>&nbsp;Leie (herunder leasing)Lån</li>
										<li><b>9</b>&nbsp;Annet</li>
									</ul>
								</span>
								</div>
							</td>
		 				</tr>
		 				<tr height="5"><td></td></tr>
		 				<tr>
			 				<td class="text12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 					<span title="factor">Faktor&nbsp;</span>
				 			</td>
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
			 					<b>15.</b><font class="text16RedBold" >*</font>&nbsp;<span title="silka">Avs/utf.land</span>&nbsp;
					            <div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute; top:2px; width:250px;" id="15_info" class="popupWithInputText text11"  >
					           			<b>15. Avs/utf.land</b>
					           			<br/><br/>
					           			Med avsenderland forstås det land hvorfra varen er sendt til Norge uten mellomliggende handelstransaksjon (omlasting undderveis endrer ikke forholdet)
										<p>
					           			KRAV TIL FELTET
					           			</p>
								</span>	
								</div>
									
					             </td>
					            <td >
				            		<select class="inputTextMediumBlueMandatoryField" name="silka" id="silka">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					 				  		<option value="${country.zkod}"<c:if test="${model.record.silka == country.zkod}"> selected </c:if> >${country.zkod}</option>
										</c:forEach>  
									</select>
									<a tabindex="-1" id="silkaIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									<%-- info span 
									<img onClick="showPop('avsenderlandInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
									<span style="position:absolute; left:920px; top:150px; width:350px; height:150px;" id="avsenderlandInfo" class="popupWithInputText"  >
						           		<div class="text10" align="left">
					           				<select class="text11" id="avsenderland" name="avsenderland" size="5" onDblClick="hidePop('avsenderlandInfo');">
						           				<c:forEach var="country" items="${model.countryCodeList}" >
						 				  			<option value="${country.zkod}">${country.zkod}&nbsp;${country.ztxt}</option>
												</c:forEach>
						           			</select>
						           			
											<table width="100%" align="left" border="0">
												<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
												<tr align="left" >
													<td class="text11">&nbsp;<button name="avsenderlandButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('avsenderlandInfo');">&nbsp;<spring:message code="systema.tvinn.sad.import.ok"/></button> 
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
					            <b>19.</b><span title="sikdc" id="v_sikdc" class="validation">Container&nbsp;</span>
					            
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="19_info" class="popupWithInputText text11">
					           			<b>19. Container</b>
					           			<br/><br/>
					           			Oppgi,med fastsatte koder, om varene blir transportert i containere ved innpassering til Norge.
										<br>
										<ul>
										<li><b>0</b> = Varer som ikke transporteres i containere.</li>
										<li><b>1</b> = Varer som transporteres i containere.</li>
										</ul>
										Kan defineres som standardverdier pr. avdeling. 
										<p>
					           			KRAV TIL FELTET
					           			</p>
								</span>	
								</div>
								
								
								
								</td>
									
					            <td class="text12" >
			           				<select name="sikdc" id="sikdc">
				 						<option value="0" <c:if test="${model.record.sikdc == '0'}"> selected </c:if> >0</option>
				 						<option value="1" <c:if test="${model.record.sikdc == '1'}"> selected </c:if> >1</option>								 				  	  
									</select>
			           			</td>
							</tr>
							<tr height="10"><td class="text"></td> </tr>
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('21_1_info');" onMouseOut="hidePop('21_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>21.1</b><span title="sitrid" id="v_sitrid" class="validation">Transp.ID</span>
			 					<div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute; top:2px; width:250px;" id="21_1_info" class="popupWithInputText text11"  >
					           			<b>21.1 Det aktive Transportmidlets identitet</b>
					           			<p>
					           			Oppgi det aktive transportmidlets identitet ved innpassering til Norge. 
					           			</p>
										<p>
										KRAV ved de fleste transportmåter.
										</p>
								</span>		
					            </div>
					            </td>
					            
				                 <td >
						            	<input type="text" class="inputTextMediumBlue" name="sitrid" id="sitrid" size="21" maxlength="20" value="${model.record.sitrid}">
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
					            <b>21.2</b>
					            <span title="silkt">Aktive transp. nasjonalitet&nbsp;</span>
					          	<div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="21_2_info" class="popupWithInputText text11"  >
					           			<b>21.2 Aktive transp. nasjonalitet ved grense</b>
					           			<p>
					           			Oppgi det aktive transportmidlets nasjonalitet ved innpassering til Norge. 
					           			Feks. Norge = NO Tyskland = DE.
					           			</p>
					           			<p>
										KRAV ved de fleste transportmåter.
										</p>
								</span>
								</div>
								</td>
									
								<td>
					            		<select name="silkt" id="silkt">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					 				  		<option value="${country.zkod}"<c:if test="${model.record.silkt == country.zkod}"> selected </c:if> >${country.zkod}</option>
										</c:forEach>  
									</select>
									<a tabindex="-1" id="silktIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									<%-- info span 
									<img onClick="showPop('nasjonalitetSearchInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
									<span style="position:absolute; left:920px; top:150px; width:350px; height:150px;" id="nasjonalitetSearchInfo" class="popupWithInputText"  >
						           		<div class="text10" align="left">
					           				<select class="text11" id="nasjonalitetSearch" name="nasjonalitetSearch" size="5" onDblClick="hidePop('nasjonalitetSearchInfo');">
						           				<c:forEach var="country" items="${model.countryCodeList}" >
						 				  			<option value="${country.zkod}">${country.zkod}&nbsp;${country.ztxt}</option>
												</c:forEach>
						           			</select>
						           			
											<table width="100%" align="left" border="0">
												<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
												<tr align="left" >
													<td class="text11">&nbsp;<button name="nasjonalitetSearchButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('nasjonalitetSearchInfo');">&nbsp;<spring:message code="systema.tvinn.sad.import.ok"/></button> 
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
					            <b>25.</b>
					            <span title="sitrm" id="v_sitrm" class="validation">Transportmåte ved grensen</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="25_info" class="popupWithInputText text11"  >
					           			<b>25. Transportmåte ved grensen</b>
					           			<p>
					           			Oppgi med kode transportmåten ved innpassering til Norge.
										Koden kan legges inn som standardverdi for avdelingen
										</p>
										<ul>
				           				<c:forEach var="record" items="${model.transportmaterCodeList}" >
				           					<li><b>${record.zkod}</b>&nbsp;${record.ztxt}</li>
				           				</c:forEach>				           									           									           									           									           									           				
				           				</ul>
								</span>	
								</div>
								
								</td>	
					            <td class="text12" >
			           				<select name="sitrm" id="sitrm">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="record" items="${model.transportmaterCodeList}" >
					 				  		<option value="${record.zkod}"<c:if test="${model.record.sitrm == record.zkod}"> selected </c:if> >${record.zkod}</option>
										</c:forEach>  
									</select>
			           			</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr height="10"><td class="text"></td></tr>
				<tr>
					<td width="2">&nbsp;</td>
		            <td class="text12" align="left" >
		            <img onMouseOver="showPop('49_info');" onMouseOut="hidePop('49_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
		            <b>49.&nbsp;</b><span title="sign"><font class="text16RedBold" >*</font>Godsnr</span>
		            &nbsp;<input type="text" class="inputTextMediumBlueMandatoryField"  name="sign" id="sign" size="20" maxlength="15" value="${model.record.sign}">
		            <div class="text11" style="position: relative;" align="left">
		            <span style="position:absolute; top:2px; width:250px;" id="49_info" class="popupWithInputText text11"  >
		           			<b>49. Godsnr</b><br/>
		           			Godsnr og posisjonsnr/konnossementnr overføres fra oppdraget.
							<br/><br/>
							Oppbygging av godsnr.:
							<ul>
								<li><b>1.- 4.</b> karakter: Årstall for registrering</li>
								<li><b>5.- 6.</b> karakter: Tolldistriktskode</li>
								<li><b>7.- 9.</b> karakter: Tollagerkode</li>
								<li><b>10.-12.</b> karakter: Lossedag, kalenderens dagnr.<li>
								<li><b>13.-15.</b> karakter: lossenr, fortløpende listenr, pr. tollager og dag</li>
							</ul>
							NB: Disse godsnummerkodene er definert i systemet med følgende funksjon:
							<ul>
								<li><b>P</b> = Postsending. Hvis denne koden benyttes vil rubrikk 25 Transportmåte automatisk bli    oppdatert med kode 50. Dette gjelder selv om annen kode allerede er tastet.
								Hvis Transportmåte = 50 er tastet og annen godsnummerkode enn P
								er benyttet, vil systemet gi feilmelding om dette.
								Ved godsnummerkode P er det ikke tillatt å taste noe i felt for posisjonsnr/konnossementnr.</li>
								<li><b>R</b> = Reisegods. Ved godsnummerkode R er det ikke tillatt å taste noe i felt for posisjonsnr/konnossementnr.
								<li><b>D</b> = Grensefortollet. Ved godsnummerkode D er det ikke tillatt å taste noe i felt for posisjonsnr/konnossementnr.
								<br/><br/>
								Andre gyldige koder:
								<li><b>J</b> = Jernbane.</li>
								<li><b>E</b> = Jernbane Express.</li>
								<li><b>F</b> = Fortolling fra frilager.</li>
								<li><b>T</b> = Fortolling fra transittlager.</li>
							</ul>
							Ved disse kodene er det valgfritt for bruker å taste noe i felt for posisjonsnr/konnossementnr.
					</span>	
					</div>
					
					</td>
		        </tr>
		        <tr>
		        		<td width="2">&nbsp;</td>
		            <td class="text12" align="left" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title="sipos">Posisjon</span>
		            		&nbsp;<input type="text" class="inputTextMediumBlue" name="sipos" id="sipos" size="15" maxlength="9" value="${model.record.sipos}">
		            </td>
		        </tr>
				<tr height="5"><td class="text"></td></tr>
				<tr>
					<td width="2">&nbsp;</td>
					<td valign="top">
			 			<table border="0" cellspacing="0" cellpadding="0">
					 		<tr>
				            <td class="text12" align="left" >
				            <img onMouseOver="showPop('20_1_info');" onMouseOut="hidePop('20_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		 					<b>20.1</b><span title="silv"><font class="text16RedBold" >*</font>Leveringksvilkår kode</span>
				            <div class="text11" style="position: relative;" align="left">
				            <span style="position:absolute; top:2px; width:250px;" id="20_1_info" class="popupWithInputText text11"  >
				           			<b>Leveringksvilkår kode</b>
				           			<p>
				           			Kode for leveringsvilkår:
				           			</p>
				           			<ul>
				           				<c:forEach var="record" items="${model.incotermsCodeList}" >
				           					<li><b>${record.zkod}</b>&nbsp;${record.ztxt}</li>
				           				</c:forEach>				           									           									           									           									           									           				
				           			</ul>
							</span>
							</div>	
							</td>	
				            <td >&nbsp;
					            	<select class="inputTextMediumBlueMandatoryField" name="silv" id="silv">
			 						<option value="">-velg-</option>
					 				  	<c:forEach var="record" items="${model.incotermsCodeList}" >
					 				  		<option value="${record.zkod}"<c:if test="${model.record.silv == record.zkod}"> selected </c:if> >${record.zkod}</option>
										</c:forEach>  
								</select>
							</td>
							</tr>
			 			
			 				<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('20_2_info');" onMouseOut="hidePop('20_2_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>20.2</b>
			 					<span title="silvt"><font class="text16RedBold" >*</font>Leveringksvilkår sted</span>&nbsp;
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="20_2_info" class="popupWithInputText text11"  >
					           			<b>Leveringksvilkår sted</b>
					           			<p>
					           			Stedet leveringsvilkåret gjelder til/fra. 
					           			</p>
								</span>	
								</div>
								</td>
					            <td >
					            		&nbsp;<input type="text" class="inputTextMediumBlueMandatoryField"  name="silvt" id="silvt" size="20" maxlength="17" value="${model.record.silvt}">
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
					            <b>30.1&nbsp;</b><span title="sikdls">Lagringssted kode</span></td>
					            <td >
						            <select name="sikdls" id="sikdls">
					 				  	<c:forEach var="record" items="${model.lagringsstedCodeList}" >
					 				  		<option value="${record.zkod}"<c:if test="${model.record.sikdls == record.zkod}"> selected </c:if> >${record.zkod}</option>
										</c:forEach>
									</select>
					            
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="30_1_info" class="popupWithInputText text11"  >
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
	        					</tr>
	        					
	        					<tr>
				            		<td class="text12" align="left" >
				            		<img onMouseOver="showPop('30_2_info');" onMouseOut="hidePop('30_2_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>30.2&nbsp;</b><span title="sils">Lagringssted tekst</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="30_2_info" class="popupWithInputText text11"  >
					           			<b>30. Lagringssted tekst</b>
					           			<br/><br/>
					           			Selve tekstfeltet oppdateres automatisk når man taster / plukker en gyldig kode.
								</span>	
								</div>
								</td>
					            <td ><input type="text" class="inputTextMediumBlue" name="sils" id="sils" size="20" maxlength="16" value="${model.record.sils}"></td>
					            
					            
	        					</tr>
	        					<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> </tr>
				            <tr height="20"><td class="text">&nbsp;</td> </tr>
			 				<tr>
			 					<td class="text12" colspan="2" >
			 					<img onMouseOver="showPop('12_info');" onMouseOut="hidePop('12_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>12.&nbsp;Verdiopplysninger&nbsp;</b>
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute; top:2px; width:250px;" id="12_info" class="popupWithInputText text11"  >
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
				 				<span style="position:absolute; top:2px; width:250px;" id="andraKostPM_info" class="popupWithInputText text11"  >
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
			 						<input type="text" class="inputTextMediumBlue" name="siftg2" id="siftg2" size="1" maxlength="1" value="${model.record.siftg2}">
			 					</td>
							</tr>
							<tr>
								<td class="text12" ><span title="sibel1/sival1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Beløp tollb.frakt</span></td>
			 					<td class="text12">
			 						<input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="sibel1" id="sibel1" size="12" maxlength="11" value="${model.record.sibel1}">
			 						<select name="sival1" id="sival1">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="currency" items="${model.currencyCodeList}" >
					 				  		<option value="${currency.zkod}"<c:if test="${model.record.sival1 == currency.zkod}"> selected </c:if> >${currency.zkod}</option>
										</c:forEach>  
									</select>
									<a tabindex="-1" id="sival1IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									<%-- info span 
									<img onClick="showPop('sival1SearchInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
									<span style="position:absolute; left:920px; top:400px; width:350px; height:150px;" id="sival1SearchInfo" class="popupWithInputText"  >
						           		<div class="text10" align="left">
					           				<select class="text11" id="sival1Search" name="sival1Search" size="5" onDblClick="hidePop('sival1SearchInfo');">
						           				<c:forEach var="currency" items="${model.currencyCodeList}" >
						 				  			<option value="${currency.zkod}">${currency.zkod}&nbsp;${currency.ztxt}</option>
												</c:forEach>
						           			</select>
						           			
											<table width="100%" align="left" border="0">
												<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
												<tr align="left" >
													<td class="text11">&nbsp;<button name="sibel1SearchButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('sival1SearchInfo');">&nbsp;<spring:message code="systema.tvinn.sad.import.ok"/></button> 
													</td>
												</tr>
											</table>
										</div>
									</span>
									--%>
			 					</td>
							</tr>	
							<tr>
								<td class="text12" >
								<img onMouseOver="showPop('andraKost_info');" onMouseOut="hidePop('andraKost_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="sibel2/sival2">Beløp andre kost.</span>
				 				
								<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute; top:2px; width:250px;" id="andraKost_info" class="popupWithInputText text11">
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
			 						<input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="sibel2" id="sibel2" size="12" maxlength="11" value="${model.record.sibel2}">
			 						<select name="sival2" id="sival2">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="currency" items="${model.currencyCodeList}" >
					 				  		<option value="${currency.zkod}"<c:if test="${model.record.sival2 == currency.zkod}"> selected </c:if> >${currency.zkod}</option>
										</c:forEach>  
									</select>
									<a tabindex="-1" id="sival2IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									<%-- info span 
									<img onClick="showPop('sival2SearchInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
									<span style="position:absolute; left:920px; top:400px; width:350px; height:150px;" id="sival2SearchInfo" class="popupWithInputText"  >
						           		<div class="text10" align="left">
					           				<select class="text11" id="sival2Search" name="sival2Search" size="5" onDblClick="hidePop('sival2SearchInfo');">
						           				<c:forEach var="currency" items="${model.currencyCodeList}" >
						 				  			<option value="${currency.zkod}">${currency.zkod}&nbsp;${currency.ztxt}</option>
												</c:forEach>
						           			</select>
						           			
											<table width="100%" align="left" border="0">
												<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
												<tr align="left" >
													<td class="text11">&nbsp;<button name="sibel2SearchButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('sival2SearchInfo');">&nbsp;<spring:message code="systema.tvinn.sad.import.ok"/></button> 
													</td>
												</tr>
											</table>
										</div>
									</span>
									--%>
			 					</td>
							</tr>
							
							<tr>						
								<td class="text12" ><span title="sirab">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rabatt</span></td>
			 					<td class="text12">
			 						<input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="sirab" id="sirab" size="12" maxlength="5" value="${model.record.sirab}">
								</td>
							</tr>					        
							<tr height="25"><td class="text">&nbsp;</td> </tr>	
				            
					        <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('brut_info');" onMouseOut="hidePop('brut_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="sivkb"><font class="text16RedBold" >*</font>Bruttovekt</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="brut_info" class="popupWithInputText text11"  >
					           			<b>Bruttovekt</b>
								</span>	
								</div>
								</td>
					            <td >
					            		<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlueMandatoryField"  name="sivkb" id="sivkb" size="10" maxlength="9" value="${model.record.sivkb}">
					            </td>
					        </tr>
				            <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('6_info');" onMouseOut="hidePop('6_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>&nbsp;6.&nbsp;</b><span title="sintk"><font class="text16RedBold" >*</font>Antall Kolli</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="6_info" class="popupWithInputText text11"  >
					           			<b>6. Antall Kolli</b>
					           			<br/><br/>
					           			Opplysningene overføres fra oppdragsregistreringen. 
					           			<p>
					           			KRAV TIL FELTET
					           			</p>
								</span>	
								</div>
								</td>
					            <td >
					            	<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlueMandatoryField"  name="sintk" id="sintk" size="8" maxlength="7" value="${model.record.sintk}">
					            </td>
					        </tr>
   					        <tr height="10"><td class="text">&nbsp;</td> </tr>
					        
					        <tr>
			 					<td class="text12" colspan="2" >
			 					<img onMouseOver="showPop('31_info');" onMouseOut="hidePop('31_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				&nbsp;<b>31.&nbsp;</b><span title="sift1/sift2">Beskrivelse</span>&nbsp;</b>
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute; top:2px; width:250px;" id="31_info" class="popupWithInputText text11"  >
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
			 						<input type="text" class="inputTextMediumBlue" name="sift1" id="sift1" size="55" maxlength="45" value="${model.record.sift1}">
			 					</td>
							</tr>	
							<tr>
								<td colspan="2" class="text12">
			 						<input type="text" class="inputTextMediumBlue" name="sift2" id="sift2" size="55" maxlength="45" value="${model.record.sift2}">
			 					</td>
							</tr>
							<tr height="10"><td class="text"></td></tr>	 
							<tr>
			 					<td class="text12" colspan="2" >
			 					<img onMouseOver="showPop('44_info');" onMouseOut="hidePop('44_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				&nbsp;<b>44.&nbsp;</b><span title="sift3/sift4">Fritekst/Tillegsopplysning</span>&nbsp;</b>
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute; top:2px; width:250px;" id="44_info" class="popupWithInputText text11"  >
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
			 						<input type="text" class="inputTextMediumBlue" name="sift3" id="sift3" size="55" maxlength="45" value="${model.record.sift3}">
			 					</td>
							</tr>	
							<tr>
								<td colspan="2" class="text12">
			 						<input type="text" class="inputTextMediumBlue" name="sift4" id="sift4" size="55" maxlength="45" value="${model.record.sift4}">
			 					</td>
							</tr>
							<tr height="15"><td>&nbsp;</td>&nbsp;</tr>
				            <tr>
					            <td class="text12" align="left" >
    			 					<img onMouseOver="showPop('vf_info');" onMouseOut="hidePop('vf_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <span title="insivf">Default VF:</span>
					            	<div class="text11" style="position: relative;" align="left">
					            	<span style="position:absolute; top:2px; width:250px;" id="vf_info" class="popupWithInputText text11"  >
					           			<b>Default VF</b>
					           			<br/>
					           			<p>
					           			Sett inn ønsket verdifastsettelseskode for varelinjene. Standard forslag er 1.
					           			</p>
									</span>
									</div>	
					            	</td>
					            <td >
					            		<input type="text" class="inputTextMediumBlue" name="insivf" id="insivf" size="2" maxlength="1" value="${model.record.insivf}">
					            </td>
					        </tr>
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('forholdBN_info');" onMouseOut="hidePop('forholdBN_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <span title="insibvnv">Forhold B/N-vekt:</span>
					            	<div class="text11" style="position: relative;" align="left">
					            	<span style="position:absolute; top:2px; width:250px;" id="forholdBN_info" class="popupWithInputText text11"  >
					           			<b>Forhold B/N-vekt</b>
					           			<br/>
					           			<p>
				           				Oppgi faktor for nettovekt hvis bare bruttovekt oppgis på varelinje. Standard forslag er 0,90.	
					           			</p>
									</span>
									</div>
				            	</td>
					            <td >
					            		<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="insibvnv" id="insibvnv" size="5" maxlength="5" value="${model.record.insibvnv}">
					            </td>
					        </tr>
					       <tr height="2"><td></td></tr> 
					       <c:if test="${ model.record.sist == 'E' || model.record.sist == 'K' || model.record.sist == 'Å' || empty  model.record.sist }"> 
						   		<c:if test="${ empty model.record.sitll }"> 
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
					        
					        
							<tr height="20"><td>&nbsp;</td></tr>
							<tr><td class="text12" colspan="2"><b>Varelinje totaler&nbsp;</b></td></tr>
   					        <tr>
				        		<td class="text12Gray" align="left" ><span title="sumOfAntalKolliInItemLines" >Kolli&nbsp;</span></td>
					        	<td >
				            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumOfAntalKolliInItemLines" id="sumOfAntalKolliInItemLines" size="10" maxlength="7" value="${ model.record.sumOfAntalKolliInItemLinesStr}">
				            		<c:if test="${not empty ( model.record.sumOfAntalKolliInItemLinesStr &&  model.record.sintk)}">
					            		<c:if test="${ model.record.sintk !=  model.record.sumOfAntalKolliInItemLinesStr}">
							            <img onMouseOver="showPop('itemsSumKolli_info');" onMouseOut="hidePop('itemsSumKolli_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="kolliantall warning">	
							            <div class="text11" style="position: relative;" align="left">
							            <span style="position:absolute; top:2px; width:250px;" id="itemsSumKolli_info" class="popupWithInputText"  >
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
				        		<td class="text12Gray" align="left" ><span title="sumOfAntalItemLines" >Varelinjer&nbsp;</span></td>
					        	<td >
				            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumOfAntalItemLines" id="sumOfAntalItemLines" size="10" value="${ model.record.sumOfAntalItemLinesStr}">
				            		<c:if test="${not empty ( model.record.sumOfAntalItemLinesStr)}">
					            		<c:if test="${ model.record.sumOfAntalItemLines <= 0 }">
							            <img onMouseOver="showPop('itemsSum_info');" onMouseOut="hidePop('itemsSum_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="varelinjerantall warning">	
							            <div class="text11" style="position: relative;" align="left">
							            <span style="position:absolute; top:2px; width:250px;" id="itemsSum_info" class="popupWithInputText"  >
						           			<font class="text11" >Summen av ​​antallet varelinjer må vare > 0</font>
										</span>
										</div>	
					            		</c:if>
				            		</c:if>
					            </td>
					        </tr>
					        <tr>
				        		<td class="text12Gray" align="left" ><span title="sumTotalAmountItemLines" >Beløp&nbsp;</span></td>
					        	<td >
				            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumTotalAmountItemLines" id="sumTotalAmountItemLines" size="10" value="${ model.record.sumTotalAmountItemLinesStr}">
					            	<c:if test="${model.record.sumTotalAmountItemLines != model.record.sibel3Dbl}">
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
				        		<td class="text12Gray" align="left" ><span title="sumTotalBruttoViktItemLines">Bruttovekt&nbsp;</span></td>
					        	<td >
				            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumTotalBruttoViktItemLines" id="sumTotalBruttoViktItemLines" size="10" value="${ model.record.sumTotalBruttoViktItemLinesStr}">
					            	<c:if test="${model.record.sumTotalBruttoViktItemLines != model.record.sivkbDbl}">
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
				 				    <c:when test="${ model.record.sist == 'M' || empty  model.record.sist }">
					 				    	<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" id="submit" onclick="javascript: form.action='tvinnsadimport_edit.do';" value='<spring:message code="systema.tvinn.sad.import.createnew.submit"/>'/>
					 				    	&nbsp;&nbsp;
					 				    	<c:if test="${not empty  model.record.sitdn && model.record.validUpdate}">
					 				    		<input tabindex=-2 class="inputFormSubmit" type="button" name="sendButton" id="sendButton" onclick="javascript: form.action='tvinnsadimport_send.do';" value='<spring:message code="systema.tvinn.sad.import.createnew.send"/>'/>
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
 
 <%-- -------------------------- --%>	
 <%-- change status admin dialog --%>	
 <%-- -------------------------- --%>	
 <tr>
	<td>
		<div id="dialogUpdateStatus" title="Dialog">
			
			<form action="tvinnsadimport_updateStatus.do" name="updateStatusForm" id="updateStatusForm" method="post">
			 	<input type="hidden" name="currentAvd" id="currentAvd" value="${model.record.siavd}">
			 	<input type="hidden" name="currentOpd" id="currentOpd" value="${model.record.sitdn}">
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
			<form  action="tvinnsadimport_edit_omberegning.do" name="copyOmberegningPaOmberegningForm" id="copyOmberegningPaOmberegningForm" method="post">
			 	<input type="hidden" name="action" id="action" value='doFetch'/>
				<input type="hidden" name="avd" id="avd" value='${ model.record.siavd}'/>
				<input type="hidden" name="sign" id="sign" value='${ model.record.sisg}'/>
				<input type="hidden" name="opd" id="opd" value='${ model.record.sitdn}'/>
				<input type="hidden" name="status" id="status" value='${ model.record.sist}'/>
				<input type="hidden" name="fabl" id="fabl" value='${ model.record.sibel3}'/>
				<input type="hidden" name="o2_sist" id="o2_sist" value='${ model.record.o2_sist}'/>
				<input type="hidden" name="o2_sidt" id="o2_sidt" value='${ model.record.o2_sidt}'/>
				<input type="hidden" name="o2_simf" id="o2_simf" value='${ model.record.o2_simf}'/>
				
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
			<form action="tvinnsadimport_edit_printSkilleArkTopic.do" name="skilleArkForm" id="skilleArkForm" method="post">
			 	<input type="hidden" name="currentAvd" id="currentAvd" value="${model.record.siavd}">
			 	<input type="hidden" name="currentOpd" id="currentOpd" value="${model.record.sitdn}">
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
						<input type="hidden" name="wsavd" id="wsavd" value='${model.record.siavd}'>
						<input type="hidden" name="wsopd" id="wsopd" value='${model.record.sitdn}'>
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

   	 <%-- -------------------------- --%>	
	 <%-- Send button's extra info   --%>	
	 <%-- -------------------------- --%>	
	 <tr>
		<td>
			<div id="dialogSendWithParameters" title="Dialog">
			<form action="tvinnsadimport_send.do" name="sendWithParamtersForm" id="sendWithParamtersForm" method="post">
			 	<input type="hidden" name="avd" id="avd" value="${model.record.siavd}">
			 	<input type="hidden" name="opd" id="opd" value="${model.record.sitdn}">
			 	<input type="hidden" name="sign" id="sign" value="${model.record.sign}">
				<table>
					<tr>
						<td class="text12" align="left" title="m1N07">Meldings funksjon</td>
						<td class="text12MediumBlue">
							<select name="m1N07" id="m1N07">
			            		<option value="">-velg-</option>
			            		<option value="DFU" <c:if test="${model.record.sendParametersRecord.m1N07 == 'DFU'}"> selected </c:if> >DFU</option>
			            		<option value="DMA" <c:if test="${model.record.sendParametersRecord.m1N07 == 'DMA'}"> selected </c:if> >DMA</option>
			            		<option value="DFO" <c:if test="${model.record.sendParametersRecord.m1N07 == 'DFO'}"> selected </c:if> >DFO</option>
			            		<option value="DEN" <c:if test="${model.record.sendParametersRecord.m1N07 == 'DEN'}"> selected </c:if> >DEN</option>
			            		<option value="DKO" <c:if test="${model.record.sendParametersRecord.m1N07 == 'DKO'}"> selected </c:if> >DKO</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="text12" align="left" title="m3039e">Til ekspedisjonsenhet</td>
						<td class="text12MediumBlue">
							<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue"  name="m3039e" id="m3039e" size="8" maxlength="6" value="${model.record.sendParametersRecord.m3039e}">
						</td>
					</tr>
					<tr>
						<td class="text12" align="left" title="m2005b">Ønsket behandlingsdato</td>
						<td class="text12MediumBlue">
							<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue"  name="m2005b" id="m2005b" size="8" maxlength="6" value="${model.record.sendParametersRecord.m2005b}">
						</td>
					</tr>
					<tr>
						<td class="text12" align="left" title="m5004d">Depositum beløp</td>
						<td class="text12MediumBlue">
							<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue"  name="m5004d" id="m5004d" size="12" maxlength="10" value="${model.record.sendParametersRecord.m5004d}">
						</td>
					</tr>
					<tr>
						<td class="text12" align="left" title="mven">Ventegrupe</td>
						<td class="text12MediumBlue">
							<select name="mven" id="mven">
			            		<option value="" <c:if test="${empty model.record.sendParametersRecord.mven}"> selected </c:if> >Nej</option>
			            		<option value="1" <c:if test="${model.record.sendParametersRecord.mven == '1'}"> selected </c:if> >Ja</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="text12" align="left" title="m0035">Testekode</td>
						<td class="text12MediumBlue">
							<select name="m0035" id="m0035">
			            		<option value="1" <c:if test="${model.record.sendParametersRecord.m0035 == '1'}"> selected </c:if> >Test</option>
			            		<option value="" <c:if test="${empty model.record.sendParametersRecord.m0035}"> selected </c:if> >Prod</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="text12" align="left" title="m9n01">Eksped.prioritet</td>
						<td class="text12MediumBlue">
							<select name="m9n01" id="m9n01">
			            		<option value="1" <c:if test="${empty model.record.sendParametersRecord.m9n01 || model.record.sendParametersRecord.m9n01 == '1'}"> selected </c:if> >Express</option>
			            		<option value="2" <c:if test="${not empty model.record.sendParametersRecord.m9n01 && model.record.sendParametersRecord.m9n01 != '1'}"> selected </c:if> >Annen</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
			</div>
		</td>
	</tr> 
		

	
 

	