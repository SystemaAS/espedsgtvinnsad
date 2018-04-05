<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
	
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadnctsexport_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
 
 	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
	

<table width="100%" cellspacing="0" border="0" cellpadding="0">
	
 <tr>
 <td>	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkTopicList" tabindex=-1 style="display:block;" 
					<c:choose>
						<c:when test="${empty model.record.thsg}">href="tvinnsadnctsexport.do?action=doFind&sign=${user.tvinnSadSign}"</c:when>
						<c:otherwise>href="tvinnsadnctsexport.do?action=doFind&sign=${model.record.thsg}"</c:otherwise>
					</c:choose> >
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.tab"/></font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<c:choose> 
			    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.created.mastertopic.tab"/>
						</font>
						<font class="text12MediumBlue">[${model.record.thtdn}]</font>
						<c:if test="${ model.record.thst == 'G' ||  model.status=='F' || model.record.thst == 'M' || empty model.record.thst}">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
						
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkItemLines" tabindex=-1 style="display:block;" href="tvinnsadnctsexport_edit_items.do?action=doFetch&avd=${model.record.thavd}&sign=${model.record.thsg}
													&opd=${model.record.thtdn}&tullId=${model.record.thtuid}&mrnNr=${model.record.thtrnr}
													&status=${model.record.thst}&datum=${model.record.thdt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.item.createnew.tab"/>
							</font>
							<c:if test="${ model.record.thst == 'G' ||  model.status=='F' || model.record.thst == 'M' || empty model.record.thst}">
								<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
							</c:if>
							
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkLogging" tabindex=-1 style="display:block;" href="tvinnsadnctsexport_logging.do?avd=${model.record.thavd}&sign=${model.record.thsg}
													&opd=${model.record.thtdn}&tullId=${model.record.thtuid}&mrnNr=${model.record.thtrnr}
													&status=${model.record.thst}&datum=${model.record.thdt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.logging.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkArchive" tabindex=-1 style="display:block;" href="tvinnsadnctsexport_archive.do?avd=${model.record.thavd}&sign=${model.record.thsg}
													&opd=${model.record.thtdn}&tullId=${model.record.thtuid}&mrnNr=${model.record.thtrnr}
													&status=${model.record.thst}&datum=${model.record.thdt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.archive.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
						</a>
					</td>
					<td width="20%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:when>
				<c:otherwise>
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.createnew.tab"/></font>
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
	<form name="nctsExportSaveNewTopicForm" id="nctsExportSaveNewTopicForm" method="post">
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<tr height="3">
 			<td colspan="2">&nbsp;
				<%-- test indicator /per avdelning --%> 
				<c:forEach var="record" items="${avdListSessionTestFlag}" >
					<c:if test="${record.avd == model.record.thavd}">	
						<c:if test="${record.tst == '1'}">
							<c:set var="isTestAvd" value="1" scope="request" />
						</c:if>
					</c:if>
				</c:forEach>
 			</td>
		</tr>
 		<%-- GENERAL HIDDEN --%> 
	    <input type="hidden" name="thmf" id="thmf" value="015">
			
		<c:choose>
		<%-- UPDATE MODE --%> 
	    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
	    	<%-- --- HIDDEN FORM FIELDS (not visible in form but important with an UPDATE ----- --%>			
			<%-- general (from user profile) --%>
			<input type="hidden" name="action" id="action" value='doUpdate'>
			<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
			<input type="hidden" name="opd" id="opd" value='${model.record.thtdn}'>
			<%-- topic specific (syop and tuid) --%>
			<input type="hidden" name="thavd" id="thavd" value='${model.record.thavd}'>
			<input type="hidden" name="thtdn" id="thtdn" value='${model.record.thtdn}'>
			<input type="hidden" name="thsg" id="thsg" value='${model.record.thsg}'>
			<input type="hidden" name="thst" id="thst" value='${model.record.thst}'>
			<input type="hidden" name="thdt" id="thdt" value='${model.record.thdt}'>
			<input type="hidden" name="thtuid" id="thtuid" value='${model.record.thtuid}'>
			<input type="hidden" name="lrnNr" id="lrnNr" value='${model.record.thtuid}'>
	    		<input type="hidden" name="avd" id="avd" value='${model.record.thavd}'>
			<input type="hidden" name="sign" id="sign" value='${model.record.thsg}'>
			
			<tr >
				<td align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;Avdeling:&nbsp;${model.record.thavd}&nbsp;&nbsp;<span title="thtdn">Tolldek.nr:</span>&nbsp;<b>${model.record.thtdn}</b>
					
				</td>
				<td>
					<table width="100%">
						<tr>
						<td class="text12MediumBlue" align="right">
							<table>
							<tr>
								<td class="text12MediumBlue" valign="bottom">
									<c:if test="${'1' != isTestAvd}">
										<%--This checkbox appears only in real production. Otherwise use the Testavdelning --%>
										<input type="checkbox" name="dkxh_0035" id="dkxh_0035" value="1" <c:if test="${model.record.dkxh_0035 == '1'}"> checked </c:if>  ><b>TEST flagg</b></font>&nbsp;&nbsp;&nbsp;
									</c:if>
								</td>
								<td class="text12MediumBlue">
									<a href="tvinnsadnctsexport_edit_printTopic.do?avd=${model.record.thavd}&opd=${model.record.thtdn}">
									 	<img style="cursor:pointer;" src="resources/images/printer.png" width="30" hight="30" border="0" alt="Print">
										&nbsp;&nbsp;&nbsp;
									</a>
								</td>
							</tr>
							</table>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr >
				<td colspan="2" class="text12MediumBlue" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;<span title="thtuid">LRN-nr:</span>&nbsp;<b>${model.record.thtuid}</b>
					&nbsp;&nbsp;<span title="thtrnr">MRN-nr:</span>&nbsp;<b>${model.record.thtrnr}</b>
				</td>
			</tr>
			<tr height="3"><td></td></tr>
			<tr >
				<td colspan="2" align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;<span title="thsg">Sign:</span>&nbsp;<b>${model.record.thsg}</b>,&nbsp;&nbsp;<span title="thdt">Dato:</span>&nbsp;<b>${model.record.thdt}</b>,
					<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					<span title="thst">Stat<a id="updateStatusLink" runat="server" href="#"><font class="text11MediumBlue">u</font></a>s:</span>&nbsp;<b>${model.record.thst}</b>
					&nbsp;&nbsp;
					<font class="text16RedBold" >*</font><span title="thenkl">Type av prosedyre</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select class="text11" name="thenkl" id="thenkl">
	            		<option value="J"<c:if test="${model.record.thenkl == 'J'}"> selected </c:if> >Forenklet</option>
					  	<option value="N"<c:if test="${model.record.thenkl == 'N'}"> selected </c:if> >Normal</option>
					</select>
						<div class="text11" style="position: relative;" align="left">
						<span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
		           		<br/>
		           		Kun status <b>M</b>, <b>(Feil)</b> eller <b>' '</b> kan redigeres.
		           			<ul>
								<li><b>' '</b>(Blank) Åpen for endring.</li>
								<li><b>E</b>&nbsp;Noen arbeider med (Endrer) den aktuelle TET nå. Hvis man arbeider med en deklarasjon og strømbrudd eller lignende inntreffer vil deklarasjonen bli "hengende" i status'E'.
								Man må i slike tilfeller endre status på deklarasjonen (MENU TET, punkt 2.) fra 'E' til ' '
								manuelt.</li>
								<li><b>Q</b>&nbsp;Intern postkasse" (Venter på at program skal lage en sending av denne og andre)</li>
								<li><b>+</b>&nbsp;Midlertidig "arbeidsstatus" - Alle som hadde Q på et gitt tidspunkt.(=klar til sending)</li>
								<li><b>A</b>&nbsp;Ligger i sendekø (ER lagt i sendings-fil) for sending til TAD</li>
								<li><b>B</b>&nbsp;Kommet til IBM, men ennå ikke til TAD (kun ved nettverk IGN)</li>
								<li><b>C</b>&nbsp;Kommet til TAD (egentlig "hentet TIL TAD's postkasse)</li>
								<li><b>M</b>&nbsp;Feilmelding (har fått IE16)</li>
								<li><b>Å</b>&nbsp;En midlertidig ("arbeids-status") som den har kun en kort periode (mellom mottak av melding og printing av feilmelding)</li>
								<li><b>U</b>&nbsp;Har fått MRN-nr. (IE28)</li>
								<li><b>P</b>&nbsp;Har fått frigivelse (dokument ER printet) (IE29)</li>
								<li><b>K</b>&nbsp;Venter på PRINT av "T-dokument" (midlertidig kortvarig status) Benyttes også ved manuell print (før sending) eller ved kopi-print (valg 6)</li>
								<li><b>V</b>&nbsp;Varekontroll (IE60)</li>
								<li><b>F</b>&nbsp;Ingen frigivelse (gjerne etter en varekontroll (IE51)</li>
								<li><b>S</b>&nbsp;Frigivelse avvist (Endelig!, Etter å ha fått "Ingen frigivelse" OG "Bedt om frigivelse") (IE62)</li>
								<li><b>D</b>&nbsp;Kansellering innvilget (IE09)</li>
								<li><b>Z</b>&nbsp;Transitering avsluttet  (IE45 mottatt)</li>
		           			</ul>
						</span>
						</div>
				</td>
				
			</tr>
			<c:if test="${'1' == isTestAvd}">
				<tr>
					<td colspan="2" align="left" class="text14Red">
						&nbsp;&nbsp;&nbsp;<b>[ TEST Avdeling ]</b>
					</td>
				</tr>	
			</c:if> 
		</c:when>
		<%-- CREATE MODE --%> 
		<c:otherwise>
			<%-- --- HIDDEN FORM FIELDS (not visible in form but important with an UPDATE ----- --%>			
			<%-- general (from user profile) --%>
			<input type="hidden" name="action" id="action" value='doUpdate'>
			<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
			<input type="hidden" name="opd" id="opd" value='${model.record.thtdn}'>
			<%-- topic specific (syop and tuid) --%>
			<input type="hidden" name="thtdn" id="thtdn" value='${model.record.thtdn}'>
			<input type="hidden" name="thst" id="thst" value='${model.record.thst}'>
			<input type="hidden" name="thdt" id="thdt" value='${model.record.thdt}'>
			<input type="hidden" name="thtuid" id="thtuid" value='${model.record.thtuid}'>
			<input type="hidden" name="lrnNr" id="lrnNr" value='${model.record.thtuid}'>
			
			<tr >
				<td align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;<font class="text16RedBold" >*</font>Avdeling:&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thavd) --%>
           			<select name="avd" id="avd">
	            		<option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                            <option value="${record.avd}"<c:if test="${model.record.thavd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>
						</c:forEach> 
					</select>
					&nbsp;
					<font class="text16RedBold" >*</font><span title="thsg">Sign:</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select name="sign" id="sign">
	            		<option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${model.record.thsg == record.sign}"> selected </c:if> >${record.sign}</option>
						</c:forEach> 
					</select>
					<font class="text16RedBold" >*</font><span title="thenkl">Type av prosedyre</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select name="thenkl" id="thenkl">
	            		<option value="J"<c:if test="${model.record.thenkl == 'J'}"> selected </c:if> >Forenklet</option>
					  	<option value="N"<c:if test="${model.record.thenkl == 'N'}"> selected </c:if> >Normal</option>
								   
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
		<td width="55%">
		<table border="0" cellspacing="1" cellpadding="0">
			<tr>
	            <td width="5">&nbsp;</td>
	            <td >
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
	            		
				 		<tr>
				 			<td class="text12">
				 				<img onMouseOver="showPop('deklTyp_info');" onMouseOut="hidePop('deklTyp_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>1.</b><font class="text16RedBold" >&nbsp;*</font><span title="thdk">Dekl.&nbsp;</span>
			 					
			 					<div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="deklTyp_info" class="popupWithInputText text11"  >
			 							<b>1. Dekl</b>
					           			<p>
										Status : Oppgi transitteringens status (T1, T2 etc).
										Koden kan legges inn som standardverdi pr.avdeling.Kan overstyres under deklareringen.
 										</p>
			 							
					           			<ul>
					           				<li>
					           				 	<b>SS</b>&nbsp;SAFETY AND SECURITY (FORHÅNDSVARSL)
					           				</li>
					           				<li>
					           				 	<b>T-</b>&nbsp;BÅDE T1 OG T2
					           				</li>	
											<li>
					           				 	<b>T1</b>&nbsp;FELLES TRANSITTERING (=IKKE EF) 
					           				</li>
					           				<li>
					           				 	<b>T2</b>&nbsp;INTERN FELLESTRANSITTERING (=EF)
					           				</li>
					           				<li>
					           				 	<b>T2F</b>&nbsp;T2 VARER MELLOM FORSKJELLIGE
					           				</li>
					           				
					           				<li>
					           				 	<b>T2SM</b>&nbsp;SE T2 + SAN MARINO
					           				</li>
					           				
					           				<li>
					           				 	<b>T2L</b>TEST
					           				</li>
					           				
					           			</ul>
					           			Note: where the symbols T1, T2, T2F, T1bis, T2bis, T2Fbis, as appropriate, have been
										omitted the goods shall be deemed to have been placed under the T1 procedure.
								</span>	
								</div>	
				 			</td>
				 			<td>
				 				<select name="thdk" id="thdk" TABINDEX=1>
				 				  <option value="">-velg-</option>
				 				  	<c:forEach var="code" items="${model.ncts031_DeklType_CodeList}" >
                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thdk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
									</c:forEach> 
								</select>
			 				</td>
			 				<td class="text12">
			 					&nbsp;<img onMouseOver="showPop('4_info');" onMouseOut="hidePop('4_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>4.</b><span title="thlstl">L.liste.&nbsp;</span>
			 					<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="4_info" class="popupWithInputText text11"  >
					           			<b>4. Lasteliste</b>
					           			<p>
										1 = PapirLasteListe (<b>PLL</b>). Hvis feltet er blankt, skriver systemet ut Vareliste.
							            PLL tillates ikke benyttet lengre.
										</p>
					           			<ul>
					           				<li>
					           				The total number of loading lists or descriptive commercial lists attached, if any, will
											be entered		
											</li>	
										</ul>
								</span>
								</div>
								
				 			</td>
				 			<td>	
				 				<input type="text" class="inputTextMediumBlue"  name="thlstl" id="thlstl" size="8" maxlength="4" value='${model.record.thlstl}'>
			 				</td>
			 				
			 				<td class="text12">&nbsp;&nbsp;
			 					&nbsp;<img onMouseOver="showPop('5_info');" onMouseOut="hidePop('5_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>5.</b><span title="thvpos">V.poster&nbsp;</span>
			 					<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="5_info" class="popupWithInputText text11"  >
					           			<b>5. Varelinjer</b>
					           			<p>
										Antall varelinjer. Feltet er sperret. Summeres fra varelinjer.
										</p>
								</span>
								</div>
				 			</td>
				 			<td>	
				 				<input type="text" class="inputTextReadOnly" readonly name="thvpos" id="thvpos" size="8" maxlength="8" value='${model.record.thvpos}'>
			 				</td>
			 			</tr>
		 				<tr height="10"><td></td></tr>
	 				</table>
 				</td>
			</tr>

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
                                      	Fel vid uppdatering. [ERROR:${model.errorMessage}]  
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
	 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
								&nbsp;<img onMouseOver="showPop('2_info');" onMouseOut="hidePop('2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 			<b>&nbsp;2.</b><font class="text16RedBold" >*</font>Avsender&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="2_info" class="popupWithInputText text11"  >
					           		<b>2. Avsender</b>
						           		<ul>
					           			<li>Kundenr. Overføres fra tolldekl./oppdrag hvis TET basert på tolldeklarasjon/oppdrag.</li>
										<li>Avsenders TIN (Trader Identification Number)<br/>
										Benytt organisasjonsnr.,evt. bedriftsnummer for avdeling av sentralt registrert enhet. TIN er valgfritt å oppgi.</li>
										</ul>
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
				 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
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
							        	<input type="hidden" name="orig_thkns" id="orig_thkns" value='${model.record.thkns}'>
							        	<input type="hidden" name="orig_thnas" id="orig_thnas" value='${model.record.thnas}'>
							        	<input type="hidden" name="orig_thtins" id="orig_thtins" value='${model.record.thtins}'>
							        	<input type="hidden" name="orig_thads1" id="orig_thads1" value='${model.record.thads1}'>
							        	<input type="hidden" name="orig_thpns" id="orig_thpns" value='${model.record.thpns}'>
							        	<input type="hidden" name="orig_thpss" id="orig_thpss" value='${model.record.thpss}'>
							        	<input type="hidden" name="orig_thlks" id="orig_thlks" value='${model.record.thlks}'>
							        	<input type="hidden" name="orig_thsks" id="orig_thsks" value='${model.record.thsks}'>
							        	
							        	
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thkns">Kundenummer</span></td>
							            <td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thnas">Navn&nbsp;</span>
							            	<a tabindex="-1" id="thnasIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
							            </td>
							        </tr>
							        <tr>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue" name="thkns" id="thkns" size="8" maxlength="8" value="${model.record.thkns}"></td>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thnas" id="thnas" size="30" maxlength="35" value="${model.record.thnas}"></td>
							            
							        </tr>
							        
							        <tr>
							            <td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thtins">TIN</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thtins" id="thtins" size="20" maxlength="17" value="${model.record.thtins}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thads1">Adresse</span></td>
							            <td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thsks">Språkkode</span>
							            </td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thads1" id="thads1" size="30" maxlength="35" value="${model.record.thads1}"></td>
							            <td align="left" >
					            		&nbsp;<select class="inputTextMediumBlueMandatoryField" name="thsks" id="thsks">
						            		<option value="">-velg-</option>
						 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
		                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thsks == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
											</c:forEach> 
											</select>
											<a tabindex="-1" id="thsksIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
										</td>
							        </tr>
							        <tr>
							        		<td>
								        		<table>
								        		<tr>
								            		<td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thpss">Postadresse</span></td>
								            		<td align="left">&nbsp;</td>
								            	</tr>
								        		<tr>
								            		<td align="left">
								       				<input type="text" class="inputTextMediumBlueMandatoryField" name="thpss" id="thpss" size="30" maxlength="35" value="${model.record.thpss}">
							            			</td> 
								            		<td align="left">&nbsp;</td>
								        		</tr>    	
								            	</table>
							            </td>
							            <td >
								            	<table>
								        		<tr>
								        			<td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thpns">Postnummer</span></td>
								            		<td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thlks">Land</span>
								            		
								            		</td>
								            	</tr>
								        		<tr >
								        			<td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thpns" id="thpns" size="10" maxlength="9" value="${model.record.thpns}"></td> 
								            		<td align="left">
								            			<select class="inputTextMediumBlueMandatoryField" name="thlks" id="thlks">
										            		<option value="">-velg-</option>
									 				  		<c:forEach var="country" items="${model.countryCodeList}" >
					                                	 		<option value="${country.zkod}"<c:if test="${model.record.thlks == country.zkod}"> selected </c:if> >${country.zkod}</option>
															</c:forEach> 
														</select>
														<a tabindex="-1" id="thlksIdLink">
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
														</a>
								            		</td> 
								        		</tr>  
								            	</table>
							            </td>
						            	</tr>
							        <tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
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
	 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
				 				&nbsp;<img onMouseOver="showPop('8_info');" onMouseOut="hidePop('8_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>8.</b><font class="text16RedBold" >*</font>Mottaker&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="8_info" class="popupWithInputText text11"  >
									<b>8. Mottaker</b>
				           			<br>
					           			<ul>
					           				<li>Kundenr.</li>
											<li>Mottakers TIN (Trader Identification number). <br/>
											Dette er et valgfritt felt som benyttes dersom TIN til den autoriserte mottaker i bestemmelseslandet er kjent.</li>
						           		</ul>
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
				 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
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
							        	<input type="hidden" name="orig_thknk" id="orig_thknk" value='${model.record.thknk}'>
							        	<input type="hidden" name="orig_thnak" id="orig_thnak" value='${model.record.thnak}'>
							        	<input type="hidden" name="orig_thtink" id="orig_thtink" value='${model.record.thtink}'>
							        	<input type="hidden" name="orig_thadk1" id="orig_thadk1" value='${model.record.thadk1}'>
							        	<input type="hidden" name="orig_thpnk" id="orig_thpnk" value='${model.record.thpnk}'>
							        	<input type="hidden" name="orig_thpsk" id="orig_thpsk" value='${model.record.thpsk}'>
							        	<input type="hidden" name="orig_thlkk" id="orig_thlkk" value='${model.record.thlkk}'>
							        	<input type="hidden" name="orig_thskk" id="orig_thskk" value='${model.record.thskk}'>
							        	
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thknk">Kundenummer</span></td>
							            <td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thnak">Navn&nbsp;</span>
							            	<a tabindex="-1" id="thnakIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
										</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="thknk" id="thknk" size="8" maxlength="8" value="${model.record.thknk}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thnak" id="thnak" size="30" maxlength="35" value="${model.record.thnak}"></td>
							        </tr>

							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thtink">TIN</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="thtink" id="thtink" size="20" maxlength="17" value="${model.record.thtink}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thadk1">Adresse</span></td>
							            <td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thskk">Språkkode</span>
						            		
							            </td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thadk1" id="thadk1" size="30" maxlength="35" value="${model.record.thadk1}"></td>
							            <td class="text12" align="left" >
				            				&nbsp;<select class="inputTextMediumBlueMandatoryField" name="thskk" id="thskk">
							            		<option value="">-velg-</option>
							 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thskk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="thskkIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>	
										</td>
							        </tr>
							        <tr>
							        		<td>
								        		<table>
								        		<tr>
								            		<td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thpsk">Postadresse</span></td>
								            		<td align="left">&nbsp;</td>
								            	</tr>
								        		<tr>
								            		<td align="left">
								       				<input type="text" class="inputTextMediumBlueMandatoryField" name="thpsk" id="thpsk" size="30" maxlength="35" value="${model.record.thpsk}">
							            			</td> 
								            		<td align="left">&nbsp;</td>
								        		</tr>    	
								            	</table>
							            </td>
							            <td >
								            	<table>
								        		<tr>
								        			<td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thpnk">Postnummer</span></td>
								            		<td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thlkk">Land</span>
								            		
								            		</td>
								            	</tr>
								        		<tr >
								        			<td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thpnk" id="thpnk" size="10" maxlength="9" value="${model.record.thpnk}"></td> 
								            		<td align="left">
								            			<select class="inputTextMediumBlueMandatoryField" name="thlkk" id="thlkk">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					                                	 	<option value="${country.zkod}"<c:if test="${model.record.thlkk == country.zkod}"> selected </c:if> >${country.zkod}</option>
														</c:forEach> 
													</select>
													<a tabindex="-1" id="thlkkIdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
								            		</td> 
								        		</tr>  
								            	</table>
							            </td>
						            	</tr>
							        
							        <tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
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
					<%-- Special section --%>
					<table align="left" class="formFrameHeader" width="100%" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
				 				&nbsp;Spesifikke detaljer om reiserute og garanti&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
 			</tr>
 			<tr>
	 			<td width="5">&nbsp;</td>
	            <td >
	 				<%-- create record --%>
				 	<table align="left" class="formFrame" width="100%" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
					 		<td>
						 		<table align="left" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							            
							        </tr>
							        <tr>
							        	<td class="text12" align="left" >
							        	&nbsp;<img onMouseOver="showPop('51_info');" onMouseOut="hidePop('51_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							        
							        	<b>51.</b><font class="text16RedBold" >*</font><span title="thtsd1/thtsd2...">Planlagte transitteringstollsteder - i rute rekkefølge&nbsp;</span>
							        	<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="51_info" class="popupWithInputText text11"  >
						           			<b>51. Planlagte transitteringstollsteder</b>
						           			<p>
							           			Oppgi kodene for de planlagte innpasseringstollstedene for hver avtalepart som skal passeres under transporten, f.eks. SE603340 for Svinesund (SE).
												EU anses som én avtalepart. Hvis transporten skal passere andre områder enn det som tilhører avtalepartene, oppgis utpasseringstollstedet fra avtalepartenes område som
												transitteringstollsted.
											</p>
											<p>
												Hvis avgangstollsted og bestemmelsestollsted tilhører forskjellige avtaleparter, er det obligatorisk å deklarere minst ett transitteringstollsted.
												Oppgi inntil 8 transittsteder varene skal innom. (Utskriften har bare plass for 6 steder.)
											</p>
											<p>
												Det viser seg at når nye land/områder knyttes til NCTS, blir det endringer i koder og navn. Det kan også lønne seg å bruke beskrivende navn på tollstedene, for å vite hvilke man
												skal benytte til forskjellige lossesteder.
											</p>
							           		
										</span>	
										</div>	
							        	</td>
							            <td class="text12" align="left" >&nbsp;</td>
							        </tr>
							        
							        <tr>
							        	<td>&nbsp;&nbsp;
							            	<table align="left" border="0" cellspacing="0" cellpadding="0">
							            		<tr>
							            			
						            			<td>
						            				<input type="text" class="inputTextMediumBlueMandatoryField" name="thtsd1" id="thtsd1" size="10" maxlength="8" value="${model.record.thtsd1}">
							            			<a tabindex="-1" id="thtsd1IdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
												</td>
												<td>
							            			&nbsp;
							            			&nbsp;
							            			<input type="text" class="inputTextMediumBlue" name="thtsd2" id="thtsd2" size="10" maxlength="8" value='${model.record.thtsd2}'>
							            			<a tabindex="-1" id="thtsd2IdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
							            			</td>
							            			
							            			
							            			<td>
							            			&nbsp;
							            			&nbsp;
							            			<input type="text" class="inputTextMediumBlue" name="thtsd3" id="thtsd3" size="10" maxlength="8" value='${model.record.thtsd3}'>
							            			<a tabindex="-1" id="thtsd3IdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
							            			</td>
							            		</tr>
							            		
							            		
							            		<tr>
							            			<td>
							            			<input type="text" class="inputTextMediumBlue" name="thtsd4" id="thtsd4" size="10" maxlength="8" value='${model.record.thtsd4}'>
							            			<a tabindex="-1" id="thtsd4IdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
							            			</td>
							            			
							            			<td>
							            			&nbsp;
							            			&nbsp;
							            			<input type="text" class="inputTextMediumBlue" name="thtsd5" id="thtsd5" size="10" maxlength="8" value='${model.record.thtsd5}'>
							            			<a tabindex="-1" id="thtsd5IdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
							            			</td>
							            			
							            			<td>
							            			&nbsp;
							            			&nbsp;
							            			<input type="text" class="inputTextMediumBlue" name="thtsd6" id="thtsd6" size="10" maxlength="8" value='${model.record.thtsd6}'>
							            			<a tabindex="-1" id="thtsd6IdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
							            			</td>					            			
							            		</tr>
							            		<tr>
							            			<td>
							            			<input type="text" class="inputTextMediumBlue" name="thtsd7" id="thtsd7" size="10" maxlength="8" value='${model.record.thtsd7}'>
							            			<a tabindex="-1" id="thtsd7IdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
							            			</td>
							            			
							            			
							            			<td>
							            			&nbsp;
							            			&nbsp;
							            			<input type="text" class="inputTextMediumBlue" name="thtsd8" id="thtsd8" size="10" maxlength="8" value='${model.record.thtsd8}'>
							            			<a tabindex="-1" id="thtsd8IdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
							            			</td>
							            		</tr>
							            	</table>
							            </td>     
							        </tr>
							        
							        <tr height="10"><td>&nbsp;</td></tr>

							        <tr>
							        	<td>&nbsp;&nbsp;
							        	<table align="left" border="0" cellspacing="0" cellpadding="0">
								        	<tr>
								        	<td class="text12" align="left" >
								        	
								        	&nbsp;<img onMouseOver="showPop('53_info');" onMouseOut="hidePop('53_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								        	<b>53.</b><font class="text16RedBold" >*</font><span title="thtsb">Bestemmelsestollsted</span>&nbsp;
								        	
								        	<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="53_info" class="popupWithInputText text11"  >
							           			<br/>
							           			<b>53. Bestemmelsestollsted</b>
						           				<p>
							           			Oppgi kode for tollstedet hvor varen skal fremlegges for å fullføre transitteringen.<br/> 
							           			Hvis mottaker av varene er hjemmehørende i et land utenfor Transitteringskonvensjonens avtaleområde, oppgis
												utpasseringstollstedet fra avtaleområdet som bestemmelsestollsted.
												</p>
												<p>
												Det viser seg at når nye land/områder knyttes til <b>NCTS</b>, blir det endringer i koder og navn.<br/> 
												Det kan også lønne seg å bruke beskrivende navn på tollstedene, for å vite hvilke man skal benytte til forskjellige lossesteder.
												<p>
											</span>
											</div>
											</td>		
								        	
						            		<td >
						            			<input type="text" class="inputTextMediumBlueMandatoryField" name="thtsb" id="thtsb" size="10" maxlength="8" value="${model.record.thtsb}">
												<a tabindex="-1" id="thtsbIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
											</td>								            
								            </tr>
							            </table>
							            </td>
							            <td>&nbsp;</td>
							        </tr>
							        	<tr height="18"><td>&nbsp;</td></tr>
							        <tr >
							        	<td >&nbsp;&nbsp;
							            	<table width="90%" class="tableBorderWithRoundCornersGray" align="left" border="0" cellspacing="2" cellpadding="0">
							            		<tr height="2"><td ></td></tr>
									        	<tr>
										        	<td  colspan="3" class="text12" align="left" >
										        	&nbsp;<img onMouseOver="showPop('52_info');" onMouseOut="hidePop('52_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										        	<b>52.</b><b>Garanti-kode</b>&nbsp;
										        	
													<div class="text11" style="position: relative;" align="left">
														<span style="position:absolute;top:2px; width:250px;" id="52_info" class="popupWithInputText text11"  >
										           			<br/>
										           			<b>52. Garanti-kode</b>
										           			<ul>
											           			<li>
											           				<b>0</b>&nbsp;Garantifritak.
											           			</li>
											           			<li>
											           				<b>1</b>&nbsp;Universalgaranti.
											           			</li>
											           			<li>
											           				<b>2</b>&nbsp;Enkelgaranti/Garantist.
											           			</li>
											           			<li>
											           				<b>3</b>&nbsp;Enkelgaranti/Kontant.
											           			</li>
											           			<li>
											           				<b>4</b>&nbsp;Enkelgarantiblanketter.
											           			</li>
											           			<li>
											           				<b>6</b>&nbsp;Unntak fra garanti (båt, fly, osv) i henhold til Transitteringskonvensjonens Vedlegg I, Artikkel 7.
											           			</li>
											           			<li>
											           				<b>7</b>&nbsp;Forhåndsvarsel
											           			</li>
											           		</ul>
											           		Ved bruk av forenklet prosedyre, kan bare garantitype 1 (Universalgaranti) brukes.
													</span>	
													</div>
													</td>	
									        	</tr>
									        	<tr height="5"><td></td></tr>
							            		<tr>
							            			<td class="text12">&nbsp;<span title="thgkd">Kode</span></td>
							            			<td class="text12">&nbsp;<span title="thgft1">Garantinummer</span></td>
							            			<td class="text12">&nbsp;<span title="thgadk">Tilg.kode</span></td>
							            		</tr>
							            		<tr>
							            			<td>
							            				<select class="inputTextMediumBlue" name="thgkd" id="thgkd" >
										 				  <option value="">-velg-</option>
														  <option value="0"<c:if test="${model.record.thgkd == '0'}"> selected </c:if> >0</option>
														  <option value="1"<c:if test="${model.record.thgkd == '1'}"> selected </c:if> >1</option>
														  <option value="2"<c:if test="${model.record.thgkd == '2'}"> selected </c:if> >2</option>
														  <option value="3"<c:if test="${model.record.thgkd == '3'}"> selected </c:if> >3</option>
														  <option value="4"<c:if test="${model.record.thgkd == '4'}"> selected </c:if> >4</option>
														  <option value="6"<c:if test="${model.record.thgkd == '6'}"> selected </c:if> >6</option>
														  <option value="7"<c:if test="${model.record.thgkd == '7'}"> selected </c:if> >7</option>
														  
														</select>
							            			</td>
							            			<td><input type="text" class="inputTextMediumBlue" name="thgft1" id="thgft1" size="24" maxlength="24" value="${model.record.thgft1}"></td>
							            			<td><input type="text" class="inputTextMediumBlue" name="thgadk" id="thgadk" size="5" maxlength="4" value="${model.record.thgadk}"></td>
							            		</tr>
							            		<tr>
							            			
							            			<td class="text12">&nbsp;<span title="thgbgi">Ikke gyl.EU</span></td>
							            			<td class="text12">&nbsp;<span title="thgbgu">Garant. gjelder ikke [land]</span></td>
							            			<td>&nbsp;</td>
							            		</tr>
							            		<tr>
							            			<td>
								            			<select name="thgbgi" id="thgbgi" >
									 				  <option value="0"<c:if test="${model.record.thgbgi == '0'}"> selected </c:if> >0</option>
													  <option value="1"<c:if test="${model.record.thgbgi == '1'}"> selected </c:if> >1</option>
													</select>
							            			</td>
							            			<td align="left">
								            			<select name="thgbgu" id="thgbgu">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					                                	 	<option value="${country.zkod}"<c:if test="${model.record.thgbgu == country.zkod}"> selected </c:if> >${country.zkod}</option>
														</c:forEach> 
													</select>
									            	</td>
									            	<td>&nbsp;</td> 
							            		</tr>
							            		<tr height="5"><td></td></tr>
							            		<tr>
							            			<td colspan="3" class="text12">&nbsp;<span title="thgkd">Annen Garanti</span></td>
							            		</tr>
							            		<tr>
							            			<td colspan="3"><input type="text" class="inputTextMediumBlue" name="thgft2" id="thgft2" size="35" maxlength="35" value="${model.record.thgft2}"></td>
							            		</tr>
							            		<tr height="2"><td></td></tr>
							            	</table>
							            </td>  
							            <td>&nbsp;</td>
							        </tr>
							        <tr height="10">
							        <%-- Garantibeløp --%>
						            <tr>
							            <td colspan="2">
							                <table align="left" border="0" cellspacing="0" cellpadding="0">
										 		<tr>
										 			<td class="text12"><b><span title="thgbl">Garantibeløp&nbsp;</span></b></td>
										 			<td align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="thgbl" id="thgbl" size="20" maxlength="20" value="${model.record.thgbl}"></td>
										 			<td class="text12">&nbsp;<span title="thgvk">Valuta</span>
										 				<%-- Note: onChange event in jQuery for this currency list --%>
										 				<select name="thgvk" id="thgvk" >
										 				  <option value="">-velg-</option>	
										 				  	<c:forEach var="code" items="${model.currencyCodeList}" >
						                                	 	<option value="${code.zkod}"<c:if test="${model.record.thgvk == code.zkod}"> selected </c:if> >${code.zkod}</option>
															</c:forEach> 
														</select>
														<a tabindex="-1" id="thgvkIdLink">
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
														</a>								
									 				</td>
								 				</tr>
								 				<%--
								 				<tr>
									 				<td class="text12">&nbsp;Kurs&nbsp;</td>
										 			<td class="text12" align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sveh_vaku" id="sveh_vaku" size="20" maxlength="20" value='${vaku}'></td>
										 			
								 				</tr>
								 				--%> 
								 				 
								 				<tr height="15"><td></td></tr>
											</table>
											</td>
									</tr>
							        <tr height="10">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							        </tr>
								</table>
							</td>
						</tr>
						
					</table>
					  
				</td>
		  	</tr>
           	</table>
		</td>
		<%-- --------------- --%>
		<%-- RIGHT SIDE CELL --%>
		<%-- --------------- --%>
		<td width="45%" align="center" valign="top">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="2">&nbsp;</td>
					<td valign="top">
			 			<table border="0" cellspacing="0" cellpadding="0">
					 		<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('15_info');" onMouseOut="hidePop('15_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>15.</b><font class="text16RedBold" >*</font><span title="thalk">Avs./utf.land&nbsp;</span>
			 					<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="15_info" class="popupWithInputText text11"  >
					           			<b>15. Avs./utf.land</b>
					           			<ul>
					           				<li>The name of the country from which goods are to be dispatched/exported shall be
												entered.
											</li>
					           			</ul>
									
								</span>	
								</div>	
					            </td>
					            <td >
					            	<select class="inputTextMediumBlueMandatoryField" name="thalk" id="thalk">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.zkod}"<c:if test="${model.record.thalk == country.zkod}"> selected </c:if> >${country.zkod}</option>
										</c:forEach> 
									</select>
					            	<a tabindex="-1" id="thalkIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>	
								</td>
							</tr>
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('17_info');" onMouseOut="hidePop('17_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>17.</b><font class="text16RedBold" >*</font><span title="thblk">Best.land&nbsp;</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="17_info" class="popupWithInputText text11"  >
				           			<b>17. Best.land</b>
				           			<p>
				           			The name of the country of destination shall be entered.
				           			</p>
								</span>	
								</div>
					            </td>
					            
					            <td >
					            	<select class="inputTextMediumBlueMandatoryField" name="thblk" id="thblk">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.zkod}"<c:if test="${model.record.thblk == country.zkod}"> selected </c:if> >${country.zkod}</option>
										</c:forEach> 
									</select>
					            	<a tabindex="-1" id="thblkIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>	
								</td>
					        </tr>
				            
						</table>
					</td>
				</tr>
				
				<tr height="20"><td colspan="2">&nbsp;</td></tr>
		
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
			 			<table border="0" cellspacing="2" cellpadding="0">
			 				<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('18_info');" onMouseOut="hidePop('18_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>18.</b>
					            <font class="text16RedBold" >*</font><span title="thtaid">Tr.middelets identitet ved avgang </span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="18_info" class="popupWithInputText text11"  >
					           			<b>18.Tr.middelets identitet ved avgang</b>
					           			<p>
										Oppgi transportmiddelets <b>identitet</b> ved avgang, f.eks. registreringsnr. for trekkenheten og eventuell tilhenger/semitrailer. 
										Oppgi med fastsatt landkode transportmiddelets <b>nasjonalitet</b> med tillhørende <b>språkkode</b>.
										</p>
										<p>
										I de tilfeller hvor f.eks. trekkenheten og tilhengeren/semitraileren har forskjellig nasjonalitet, oppgis
										trekkenhetens nasjonalitet i feltet.
										</p>
										<ol>
											<li><b>Identitet</b> = reg.nr., containernr. etc.</li>
											<li><b>Nasjonalitet</b> = Landkode</li>
											<li><b>Språk</b> = Språkkode</li>									
										</ol>
								</span>
								</div>
								</td>
								<td class="text">&nbsp;</td>	
					        </tr>
					        <tr>
				            	<td >
				            		<input type="text" class="inputTextMediumBlueMandatoryField" name="thtaid" id="thtaid" size="25" maxlength="35" value="${model.record.thtaid}">
				            	</td>
				            	<td class="text">&nbsp;</td>
	        				</tr>
	        				<tr>
				            	<td class="text12">
				            		<font class="text16RedBold" >*</font><span title="thtalk">Nasjonalitet</span>
				            		
				            		<select class="inputTextMediumBlueMandatoryField" name="thtalk" id="thtalk">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.zkod}"<c:if test="${model.record.thtalk == country.zkod}"> selected </c:if> >${country.zkod}</option>
										</c:forEach> 
									</select>
									<a tabindex="-1" id="thtalkIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									&nbsp;<span title="thtask">Språk</span>
									<select name="thtask" id="thtask">
			            				<option value="">-velg-</option>
			 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thtask == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
									</select>
									<a tabindex="-1" id="thtaskIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
				            	</td>
				            	<td class="text">&nbsp;</td>
	        				</tr>
						</table>
					</td>
				</tr>
				<tr height="10"><td class="text">&nbsp;</td> </tr>
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
						<table border="0" cellspacing="2" cellpadding="0">	
					 		<tr>
					            <td class="text12" align="left">
					            <img onMouseOver="showPop('21_info');" onMouseOut="hidePop('21_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>21.</b>
					            <font class="text16RedBold" >*</font><span title="thtgid">Tr.middelets identitet ved grense</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="21_info" class="popupWithInputText text11"  >
					           			<b>21. Tr.middelets identitet ved grense</b>
					           			<p>
					           			Oppgi transportmiddelets identitet ved grensepassering, f.eks.registringsnr.for trekkenheten og eventuell tilhenger/semitrailer. Oppgi med fastsatt landkode transportmiddelets nasjonalitet med tillhørende språkkode.I de tilfeller hvor f.eks. trekkenheten og tilhengeren/semitraileren har forskjellig nasjonalitet, oppgis trekkenhetens nasjonalitet i feltet.<br/>
										</p>
										<p>
										Når det dreier seg om kombinert transport, eller det benyttes flere transportmidler, oppgis det transportmiddelet som sørger for fremdriften (f.eks. lastebil på ferge; fergen vil være det aktive transportmiddelet.
										Hvis første siffer i "Transportmåte ved grensepassering"(felt 25) er annet enn "2", "5" eller "7", er feltet obligatorisk.
										</p>
										<ol>
											<li><b>Identitet</b> = reg.nr., containernr. etc.</li>
											<li><b>Nasjonalitet</b> = Landkode</li>
											<li><b>Språk</b> = Språkkode</li>									
										</ol>
								
								</span>	
								</div>
								</td>
					            <td class="text">&nbsp;</td>
					        </tr>
				            <tr>
				            	<td >
				            		<input type="text" class="inputTextMediumBlueMandatoryField" name="thtgid" id="thtgid" size="25" maxlength="35" value="${model.record.thtgid}">
				            	</td>
				            	<td class="text">&nbsp;</td>
	        				</tr>
	        				<tr>
				            	<td class="text12">
				            		<font class="text16RedBold" >*</font><span title="thtglk">Nasjonalitet</span>
				            		<select class="inputTextMediumBlueMandatoryField" name="thtglk" id="thtglk">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.zkod}"<c:if test="${model.record.thtglk == country.zkod}"> selected </c:if> >${country.zkod}</option>
										</c:forEach> 
									</select>
									<a tabindex="-1" id="thtglkIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									&nbsp;<span title="thtgsk">Språk</span>
						            		
									<select name="thtgsk" id="thtgsk">
			            				<option value="">-velg-</option>
			 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thtgsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
									</select>
									<a tabindex="-1" id="thtgskIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									
				            	</td>
				            	<td class="text">&nbsp;</td>
	        				</tr>
 		       				<tr>
				            	<td class="text12">
				            		<img onMouseOver="showPop('thtrm_info');" onMouseOut="hidePop('thtrm_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		            				<b>25.</b>
				            		<font class="text16RedBold" >*</font><span title="thtrm">Transp.måte ved utpass.&nbsp;</span>
				            		<select class="inputTextMediumBlueMandatoryField" name="thtrm" id="thtrm">
			 						<option value="">-velg-</option>
				 				  	<c:forEach var="code" items="${model.transportmaterCodeList}" >
				 				  		<option value="${code.zkod}"<c:if test="${model.record.thtrm == code.zkod}"> selected </c:if> >${code.zkod}</option>
									</c:forEach> 
								</select>
				            	<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="thtrm_info" class="popupWithInputText text11"  >
				           			<b>25. Transp.måte ved utpass.</b>
				           			<p>
									Oppgi med kode transportmåten ved utpassering fra Norge.<br/>
									Koden kan legges inn som standardverdi for avdelingen.
									</p>		
									<ul>
				           				<c:forEach var="code" items="${model.transportmaterCodeList}" >
					 				  		<li><b>${code.zkod}</b>&nbsp;${code.ztxt}</li>
				 				  		</c:forEach>	
				           			</ul>
								</span>
								</div>
							</td>
							<td class="text">&nbsp;</td>
	        				</tr>
	        					
						</table>
					</td>
				</tr>
				<tr height="15"><td class="text">&nbsp;</td> </tr>
				
				<tr>
					<td width="2">&nbsp;</td>
		 			<td class="text12">
		 				<table align="left" border="0" cellspacing="0" cellpadding="0">
			 				<tr>
				 				<td class="text12">
				 				<img onMouseOver="showPop('19_info');" onMouseOut="hidePop('19_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>19.</b>&nbsp;<font class="text16RedBold" >*</font><span title="thkdc">Container&nbsp;&nbsp;</span>
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="19_info" class="popupWithInputText text11"  >
				           			<b>19. Container</b>
				           			<p>
									Oppgi, med fastsatte koder, om varene blir transportert i containere ved utpassering fra Norge.
									</p>
				           			<ul>
					           			<li><b>0</b>&nbsp;-&nbsp;Varer som ikke transporteres i containere.</li>
					           			<li><b>1</b>&nbsp;-&nbsp;Varer som transporteres i containere.</li>
					           		</ul>
					           		<p>Kan defineres som standardverdier pr. avdeling.</p>
					           	</span>
					           	</div>
					           	</td>
				 				<td class="text12" >
		 							<select class="inputTextMediumBlueMandatoryField" name="thkdc" id="thkdc">
				 						<option value="0"<c:if test="${model.record.thkdc == 0}"> selected </c:if> >0</option>
								  		<option value="1"<c:if test="${model.record.thkdc == 1}"> selected </c:if> >1</option>
								  	</select>
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
				            <img onMouseOver="showPop('lastplats_info');" onMouseOut="hidePop('lastplats_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
				            <b>27.</b>&nbsp;<span title="thlsd">Lastested</span>
				            
				            <div class="text11" style="position: relative;" align="left">
							<span style="position:absolute;top:2px; width:250px;" id="lastplats_info" class="popupWithInputText text11"  >
				           			<b>27. Lastested</b>
				           			<p>
									Dette er ikke et obligatorisk felt. Rent tekstfelt. Fyll ut med f.eks. terminalnavn etc.								
									</p>
							</span>
							</div>
							</td>
							<td class="text12" >
						    	<input type="text" class="inputTextMediumBlue" name="thlsd" id="thlsd" size="17" maxlength="17" value="${model.record.thlsd}">
						    </td>
						</tr>
	        				<tr height="10"><td class="text">&nbsp;</td> </tr>
			 				<tr>
					            <td class="text12" align="left">
					            <img onMouseOver="showPop('tullkontor_info');" onMouseOut="hidePop('tullkontor_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>C.</b><font class="text16RedBold" >*</font><span title="thcats">Avg.tollsted&nbsp;</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="tullkontor_info" class="popupWithInputText text11"  >
					           		<b>C. Avg.tollsted</b>
						           		<p>
						           		Kode for avgangstollsted.<br/>
										Merk at dette er tollstedskoden som er knyttet opp mot Ansvarlig's organisasjonsnr., ikke nødvendigvis stedet varene går ut fra.
										<br/><br/>
										Legges inn som standardverdi på avdelingen.
						           		</p>
								
								</span>	
								</div>
					            </td>
							    <td class="text">
							    <input type="text" class="inputTextMediumBlueMandatoryField" name="thcats" id="thcats" size="10" maxlength="8" value='${model.record.thcats}'>
							    <a tabindex="-1" id="thcatsIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								</a>
								</td>	
					        </tr>
					        <tr height="2"><td>&nbsp;</td></tr>
					        <tr>
					            <td class="text12" align="left">
					            <img onMouseOver="showPop('sprakkod_foljedok_info');" onMouseOut="hidePop('sprakkod_foljedok_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <font class="text16RedBold" >*</font><span title="thskfd">Språkkode på følgedok.&nbsp;</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
					           			<b>Språkkode på følgedok</b>
					           			<p>
					           			Følgedokumenter (T-papir, lasteliste/vareliste) utstedt i Sysped er på norsk.<br/>
					           			Språkkode = <b>NO</b> legges inn som standardverdi på avdelingen.
					           			</p>
					           	</span>	
								</div>
								</td>
					            <td align="left">
	            					<select class="inputTextMediumBlueMandatoryField" name="thskfd" id="thskfd">
			            				<option value="">-velg-</option>
			 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thskfd == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
								</select>
			            		<a tabindex="-1" id="thskfdIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								</a>
							
								</td> 
							</tr>
							<tr height="2"><td>&nbsp;</td></tr>
							
					        <tr>
					            <td class="text12" align="left" ><span title="thtrdt">Transittdato</span></td>
					            <td class="text12" >
					            	<input readonly type="text" class="inputTextReadOnly" name="thtrdt" id="thtrdt" size="8" value="${model.record.thtrdt}">
					            	&nbsp;
					            </td>
					        </tr>
					        
					        <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('deklarantplats_info');" onMouseOut="hidePop('deklarantplats_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <font class="text16RedBold" >*</font><span title="thdst">Dekl.sted</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="deklarantplats_info" class="popupWithInputText text11"  >
					           			<b>Dekl.sted</b>
					           			<p>Plats (inklusive landkod)</p>
								</span>	
								</div>
								</td>
					            
					            <td class="text12" align="left" >
					            	<input type="text" class="inputTextMediumBlueMandatoryField" name="thdst" id="thdst" size="10" maxlength="15" value="${model.record.thdst}">
					            	&nbsp;<font class="text16RedBold" >*</font><span title="thdsk">Dekl.språk</span>
					            	<select class="inputTextMediumBlueMandatoryField" name="thdsk" id="thdsk">
			            				<option value="">-velg-</option>
			 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thdsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
									</select>
					            	<a tabindex="-1" id="thdskIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
					            </td>
					        </tr>
					        <%--
					        <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('deklarant_info');" onMouseOut="hidePop('deklarant_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="thtarf">Deklarant</span></td>
					            <span style="position:absolute; left:	px; top:550px; width:250px; height:240px;" id="deklarant_info" class="popupWithInputText"  >
					           		<div class="text11" align="left">
					           			Feltet er blokeret. Hentes automatisk.
									</div>
								</span>
					            <td colspan="3" class="text12" align="left" >
					            		<input readonly type="text" class="inputTextReadOnly" name="thtarf" id="thtarf" size="25" value="${model.record.thtarf}">
					            </td>
					        </tr>
					         --%>	
							<tr height="15"><td>&nbsp;</td></tr>	 
								    
					        <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('6_info');" onMouseOut="hidePop('6_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>6.&nbsp;</b><span title="thntk">Antal Kolli</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="6_info" class="popupWithInputText text11"  >
				           			<b>6. Antall Kolli</b>
				           			<p>
									Antall kolli. Feltet er sperret. Summeres fra varelinjer.<br/>
									Beregnes automatisk fra varelinjer.
									</p>
				           			Feltet er blokeret. 
								</span>
								</div>
								</td>	
					            <td ><input readonly onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextReadOnly" name="thntk" id="thntk" size="8" maxlength="7" value="${model.record.thntk}"></td>
					        </tr>
				            <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('bruttovikt_info');" onMouseOut="hidePop('bruttovikt_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="thvkb">Bruttovekt</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="bruttovikt_info" class="popupWithInputText text11"  >
					           	
					           			<b>Bruttovekt</b>
					           			<p>
					           			Summeres fra varelinjer. 
					           			Feltet er blokeret. 
					           			</p>
								</span>	
								</div>
								</td>
					            
					            <td ><input readonly onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextReadOnly" name="thvkb" id="thvkb" size="10" maxlength="9" value="${model.record.thvkb}"></td>
					        </tr>
					        
					        <tr height="2"><td></td></tr> 
					        <%-- <c:if test="${ model.record.thst == 'G' ||  model.status=='F' || model.record.thst == 'M' || empty model.record.thst}"> --%>
					        <c:choose>
					        <c:when test="${ model.record.thblk != 'NO' }" >
					        	<c:if test="${ model.record.thdk == 'SS' || model.record.thdk == 'ENTRY' || model.record.thdk == 'EXIT' }" >
							        <tr height="10"><td></td></tr>
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
							           				<li>MRN er blank</li>
						           					</ol>	
							           			</p>
											</span>
											</div>	
							        	</td>
							        </tr>
							      </c:if> 
					        </c:when>
					        <c:otherwise>
					        	<c:if test="${ model.record.thdk != 'SS' && model.record.thdk != 'ENTRY' && model.record.thdk != 'EXIT' }" >
							        <tr height="10"><td></td></tr>
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
							           				<li>MRN er blank</li>
						           					</ol>	
							           			</p>
											</span>
											</div>	
							        	</td>
							        </tr>
							       </c:if> 
					        </c:otherwise>
					        </c:choose>
					        
					     	<tr height="10"><td>&nbsp;</td></tr>
		     	            	<tr>
					            <td class="text12" align="left">
					            <img onMouseOver="showPop('kontrollresultat_info');" onMouseOut="hidePop('kontrollresultat_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
 								<b>D.</b>&nbsp;<span title="thdkr">Kontrollresultat.&nbsp;</span>
 								<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="kontrollresultat_info" class="popupWithInputText text11"  >
					           	
						           		<b>D. Kontrollresultat</b>
						           		<ul>
						           			<li><b>A1</b>&nbsp;TILLFREDSSTILLENDE</li>
						           			<li><b>A2</b>&nbsp;ANSETT TILFREDSSTILLENDE</li>
						           			<li><b>A3</b>&nbsp;FORENKLET PROSEDYRE</li>
						           			<li><b>A4</b>&nbsp;INGEN REAKSJON VED MINDRE UREGELMES</li>
						           			<li><b>A5</b>&nbsp;BELØP INNKREVD</li>
						           			<li><b>B1</b>&nbsp;IKKE TILLFREDSSTILLENDE</li>
						           		</ul>
						           		<p>
						           		Frist er dato godset senest må sendes for å omfattes av den gitte frigivelse.
						           		</p>
						           		<p>
						           		a) Ved <b>Forenklet Prosedyre</b> (Autorisert avsender) må feltet være fylt ut med <b>A3</b><br/>  
						           		Systemet foreslår frist til dagens dato + 8 dager.<br/>
						           		</p>
						           		<p>
										b) Ved <b>Normal Prosedyre</b> (ikke autorisert avsender), gis verdiene av tollvesenet (kommer i retur i <b>IE29</b>)
						           		</p>
						           		
								</span>
								</div>
								</td>
					           	<td class="text12" align="left">
					           		
					           		<select name="thdkr" id="thdkr" >
					           			<option value="">-velg-</option>
					 				  <option value="A1"<c:if test="${model.record.thdkr == 'A1'}"> selected </c:if> >A1</option>
					 				  <option value="A2"<c:if test="${model.record.thdkr == 'A2'}"> selected </c:if> >A2</option>
									  <option value="A3"<c:if test="${model.record.thdkr == 'A3'}"> selected </c:if> >A3</option>
									  <option value="A4"<c:if test="${model.record.thdkr == 'A4'}"> selected </c:if> >A4</option>
									  <option value="A5"<c:if test="${model.record.thdkr == 'A5'}"> selected </c:if> >A5</option>
									  <option value="B1"<c:if test="${model.record.thdkr == 'B1'}"> selected </c:if> >B1</option>													  
									</select>
					           	</td>
				           	</tr>
				           	<tr>
					            <td class="text12" align="left">
					            <img onMouseOver="showPop('frist_info');" onMouseOut="hidePop('frist_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
 								<b>D.</b>&nbsp;<span title="thddt">Frist&nbsp;</span>
 								<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="frist_info" class="popupWithInputText text11"  >
					           			<b>Frist</b>
						           		<p>
						           		Frist er dato godset senest må sendes for å omfattes av den gitte frigivelse.
						           		</p>
						           		<p>
						           		a) Ved <b>Forenklet Prosedyre</b> (Autorisert avsender) må feltet være fylt ut med <b>A3</b><br/>  
						           		Systemet foreslår frist til dagens dato + 8 dager.<br/>
						           		</p>
						           		<p>
										b) Ved <b>Normal Prosedyre</b> (ikke autorisert avsender), gis verdiene av tollvesenet (kommer i retur i <b>IE29</b>)
						           		</p>
								</span>
								</div>
								</td>
					           	<td class="text12" align="left">
					           		<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="thddt" id="thddt" size="8" maxlength="6" value="${model.record.thddt}">
					           		&nbsp;
					           	</td>
				           	</tr>
						</table>
					</td>
				</tr>
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
			 				    	<%-- only some status are allowed --%>
				 				    <c:choose>
					 				    <c:when test="${ model.record.thst == 'G' ||  model.status=='F' || model.record.thst == 'M' || empty model.record.thst}">
						 				    	<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='tvinnsadnctsexport_edit.do';" value='<spring:message code="systema.tvinn.sad.ncts.export.createnew.submit"/>'/>
						 				    	&nbsp;&nbsp;
						 				    	<c:if test="${not empty model.record.thtdn}">
						 				    		<input tabindex=-2 class="inputFormSubmit" type="submit" name="send" onclick="javascript: form.action='tvinnsadnctsexport_send.do';" value='<spring:message code="systema.tvinn.sad.ncts.export.createnew.send"/>'/>
						 				    	</c:if>
					 				    </c:when>
					 				    <c:otherwise>
					 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='<spring:message code="systema.tvinn.sad.submit.not.editable"/>'/>
					 				    </c:otherwise>	
				 				    </c:choose>
	                				</td>
					        </tr>
					        <tr height="25"><td colspan="2">&nbsp;</td></tr>
					        
					        <c:if test="${model.record.warrantyAlarm}">
					        <tr>
						        <td colspan="2" >&nbsp;&nbsp;
						            	<table style="border-color:red;border-width: 2px 2px 2px 2px;" class="tableBorderWithRoundCorners" align="left" border="0" cellspacing="2" cellpadding="1">
								    		<tr>
									    		<td class="text14" colspan="2">
									            <img width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="emergency">	
										    		<b>Brugte garantibeløp</b>&nbsp;<font class="text14RedBold">overskrider</font>&nbsp;<b>alarmgrensen på</b>&nbsp; 
										    		<b>${model.record.tgprm}&nbsp;%</b>
										    		<ul class="text14">
										    			<li>
										    				Totalt garantibeløp er: <font class="text14GreenBold">${model.record.tggbl}</font>
										    			</li>
										    			<li>
										    				Totalt brugte garantibeløp (herunder dette angivelse) er: <font class="text14RedBold">${model.record.tggblb}</font>
										    			</li>
										    		</ul>
								    			</td>	
								    		</tr>
								    		 	   
						        		</table>
						        </td>
					        </tr>
					        </c:if>
					        
						</table>
					</td>
				</tr>
				
			</table>
		</td>
		</tr>

		<tr height="20"><td colspan="2">&nbsp;</td></tr>
		
		<tr>
		    <td colspan="2">		
			<%-- ------------------------------- --%>
			<%-- tab area container DIVISON AREA --%>
			<%-- ------------------------------- --%>
			<table border="0" cellspacing="0" cellpadding="0">
				<tr >
					<td height="10" class="tabNoBorderSeparatorWhite" align="left" > 
			   			 <table border="0" cellspacing="0" cellpadding="0">
						 	<tr >
						 		<%--
						 		<td class="divisionLine">
					    			&nbsp;
					    		</td>
					    		 --%>
					        </tr>
					     </table> 
					</td>
				</tr>
			</table>
			</td>
	 	</tr>
		<tr height="20"><td colspan="2">&nbsp;</td></tr>
		<tr>
		    <td colspan="2" >
			<%-- ---------------------------- --%>
			<%-- tab area container SECONDARY --%>
			<%-- ---------------------------- --%>
			<table width="100%" class="secondarySectionHeader" border="0" cellspacing="0" cellpadding="0">
		 		<tr height="18">
					<td class="text14WhiteBold">
		 				<b>&nbsp;&nbsp;&nbsp;&nbsp;Supplerende oplysninger&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<%--EXTRAORDINÄRA --%>
		<tr>
			<td colspan="2" >
			<table width="100%" class="secondarySectionFrame" border="0" cellspacing="0" cellpadding="0">
				<tr height="10"><td colspan="2"></td></tr>
				<tr>
				<td width="50%" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
			 			<td width="5">&nbsp;</td>
			            <td >		
			 				<%-- ANSVARIG --%>
			 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="18px">
						 			<td class="text12White">
						 				&nbsp;
						 				<img onMouseOver="showPop('14_info');" onMouseOut="hidePop('14_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						
				 						<b>&nbsp;50.</b><font class="text16RedBold" >*</font>Ansvarlig&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 						
						 				<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="14_info" class="popupWithInputText text11"  >
					           	
						           			<br>
						           			Ansvarlig. 
						           			<ul>
							           			<li>The principal’s name (full name of the person or company) and full address shall be
													entered as well as the identification number, if any, allocated by the competent
													authorities. 
													<br/><br/>If appropriate, the full name (person or company) of the authorised
													representative who signs on behalf of the principal shall be entered.
													Subject to any specific provisions on the use of computerised systems, the original of
													the handwritten signature of the person concerned must appear on the SAD copy no.1,
													which is to be kept at the office of departure. If this is a legal person, the signatory
													shall add after his signature his full name and the capacity in which he is signing.
							           			</li>
							           			
							           		</ul>
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
						 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
						 		<tr>
							 		<td>
								 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
									 		<tr height="15">
									            <td class="text12" align="left">&nbsp;</td> 
									        </tr>
									        <tr>
									        	<%-- ================================================================================== --%>
									        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
									        		 These original values will be used when the user clicks "Cancel" buttons (puttting
									        		 back original value)																--%> 
									        	<%-- ================================================================================== --%>
									        	<input type="hidden" name="orig_sveh_dkkn" id="orig_sveh_dkkn" value='${dkkn}'>
									        	<input type="hidden" name="orig_thnaa" id="orig_thnaa" value='${model.record.thnaa}'>
									        	<input type="hidden" name="orig_thtina" id="orig_thtina" value='${model.record.thtina}'>
									        	<input type="hidden" name="orig_thada1" id="orig_thada1" value='${model.record.thada1}'>
									        	<input type="hidden" name="orig_thpna" id="orig_thpna" value='${model.record.thpna}'>
									        	<input type="hidden" name="orig_thpsa" id="orig_thpsa" value='${model.record.thpsa}'>
									        	<input type="hidden" name="orig_thlka" id="orig_thlka" value='${model.record.thlka}'>
									        	<input type="hidden" name="orig_thska" id="orig_thska" value='${model.record.thska}'>
							        				<td class="text12" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="thtina">TIN</span></td>
									            <td class="text12" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="thnaa">Navn</span>
									            	<a tabindex="-1" id="thnaaIdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
													</a>
									            </td>
									            
									        </tr>
									        <tr>
									        		<td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thtina" id="thtina" size="20" maxlength="17" value="${model.record.thtina}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thnaa" id="thnaa" size="30" maxlength="35" value="${model.record.thnaa}"></td>
									            
									        </tr>

									        <tr height="4"><td>&nbsp;</td></tr>
									        <tr>
									            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thada1">Adresse</span></td>
									            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thska">Språkkode</span>
								            		
									            </td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="thada1" id="thada1" size="30" maxlength="35" value="${model.record.thada1}"></td>
									            <td class="text12" align="left" >
									            		&nbsp;<select name="thska" id="thska">
										            		<option value="">-velg-</option>
										 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
						                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thska == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
															</c:forEach> 
													</select>
													<a tabindex="-1" id="thskaIdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
												</td>
									        </tr>
									        <tr>
									        		<td>
										        		<table>
										        		<tr>
										            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpsa">By</span></td>
										            		<td align="left">&nbsp;</td>
										            	</tr>
										        		<tr>
										            		<td align="left">
										       				<input type="text" class="inputTextMediumBlue" name="thpsa" id="thpsa" size="30" maxlength="35" value="${model.record.thpsa}">
									            			</td> 
										            		<td align="left">&nbsp;</td>
										        		</tr>    	
										            	</table>
									            </td>
									            <td >
										            	<table>
										        		<tr>
										        			<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpna">Postnummer</span></td>
										            		<td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thlka">Land</span>
										            		
										            		</td>
										            	</tr>
										        		<tr >
										        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpna" id="thpna" size="10" maxlength="9" value="${model.record.thpna}"></td> 
										            		<td align="left">
										            			<select class="inputTextMediumBlueMandatoryField" name="thlka" id="thlka">
												            		<option value="">-velg-</option>
											 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							                                	 	<option value="${country.zkod}"<c:if test="${model.record.thlka == country.zkod}"> selected </c:if> >${country.zkod}</option>
																</c:forEach> 
															</select>
															<a tabindex="-1" id="thlkaIdLink">
																<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
															</a>
										            		</td> 
										        		</tr>  
										            	</table>
									            </td>
								            	</tr>
									        
									        <tr height="15">
							            		<td class="text12Bold" align="left" >&nbsp;</td>
							            		<td class="text12Bold" align="left" >&nbsp;</td> 
									        </tr>  
									        
								        </table>
							        </td>
						        </tr>
							</table>          
		            		</td>
		           	</tr> 
		           	<tr height="20"><td></td></tr>
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
						 			<td class="text12" >
						 				<table align="left" border="0" cellspacing="2" cellpadding="0">
						 					<tr>
									            <td class="text12" align="left">
									            <img onMouseOver="showPop('forsegling_info');" onMouseOut="hidePop('forsegling_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								&nbsp;<span title="thdfkd">Forseglings Id&nbsp;</span>
				 								<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="forsegling_info" class="popupWithInputText text11"  >
					           						<br>
								           				Må angi alle verdier (Antall/kode/språk) eller ingen.
								           			<br/>
												</span>
												</div>
												</td>
									           	<td class="text12" align="left">
									           		<input type="text" class="inputTextMediumBlue" name="thdfkd" id="thdfkd" size="20" maxlength="20" value="${model.record.thdfkd}">
									           	</td>
								           	</tr>
								           	<tr>
									            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									            		<span title="thdant">Forseglings-antall&nbsp;</span>
									            </td>
				 								<td class="text12" align="left">
									           		<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="thdant" id="thdant" size="4" maxlength="4" value="${model.record.thdant}">
									           	</td>
								           	</tr>
								           	<tr>
									            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									            		<span title="thdfsk">Forseglings-språkkode</span>
								            		</td>
				 								<td class="text12" align="left">
									           		<select name="thdfsk" id="thdfsk">
							            				<option value="">-velg-</option>
							 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thdfsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
														</c:forEach> 
													</select>
									           		&nbsp;
									           	</td>
								           	</tr>
											<tr height="20"><td></td></tr>						 				
							 				<tr>
									            <td class="text12" align="left">
									            <img onMouseOver="showPop('kontrolltullplats_info');" onMouseOut="hidePop('kontrolltullplats_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								&nbsp;<span title="thdats/thddtk">Kontroll tollsted og dato&nbsp;</span>
				 								<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="kontrolltullplats_info" class="popupWithInputText text11"  >
					           	
								           			<br>
								           				...
								           			<br/>
								           			<ul>
									           			<li>?</li>
									           		</ul>
									           	</span>
												</div>
												</td>
												
									           	<td class="text12" align="left">
									           		<input readonly type="text" class="inputTextReadOnly" name="thdats" id="thdats" size="20" maxlength="8" value="${model.record.thdats}">
									           	</td>
									           	<td class="text12" align="left">	
									           		<input readonly type="text" class="inputTextReadOnly" name="thddtk" id="thddtk" size="20" maxlength="8" value="${model.record.thddtk}">
									           		&nbsp;
									           	</td>
								           	</tr>
							 				<tr>
								 				<td nowrap class="text12">
								 				<img onMouseOver="showPop('returadress_info');" onMouseOut="hidePop('returadress_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 			<b>&nbsp;9.</b>&nbsp;<span title="/thtsn2/thtslk">Returadresse&nbsp;</span>
								 				<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="returadress_info" class="popupWithInputText text11"  >
					           		           			Pending...
												</span>
												</div>
												</td>
						 			
								 				<td class="text12">
								 					<input readonly type="text" class="inputTextReadOnly" name="thtsn1" id="thtsn1" size="20" maxlength="35" value="${model.record.thtsn1}">
								 				</td>
								 				<td class="text12">
								 					<input readonly type="text" class="inputTextReadOnly" name="thtsn2" id="thtsn2" size="20" maxlength="35" value="${model.record.thtsn2}">
								 				</td>
							 				</tr>
							 				<tr>
								 				<td nowrap class="text12">&nbsp;</td>
					 							<td class="text12">
								 					<input readonly type="text" class="inputTextMediumBlue" name="thtslk" id="thtslk" size="2" maxlength="2" value="${model.record.thtslk}">
								 				</td>
							 				</tr>
						 				</table>
						 			</td>
						 		</tr>
						 		</table>
							</td>
						</tr>
					</table>
				 </td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;&nbsp;
		            	<table width="99%" align="center" class="tableBorderWithRoundCornersDarkRed" border="0" cellspacing="2" cellpadding="0">
		            		<tr>
							<td width="50%" align="left" valign="top" class="text12" align="left" >
								<table width="99%" align="left" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="18px">
							 			<td class="text14">
							 				&nbsp;<img onMouseOver="showPop('sikkerhet_info');" onMouseOut="hidePop('sikkerhet_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									        	<b>Sikkerhet</b>&nbsp;Arbeid med forhåndsvarsling
									        <div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="sikkerhet_info" class="popupWithInputText text11"  >
					           					TODO
											</span>
											</div>	
						 				</td>
					 				</tr>
					 				<tr>
					 					
					            			<td class="text12">&nbsp;<span title="thsik">
					            				<img onMouseOver="showPop('sikkerhetIndicator_info');" onMouseOut="hidePop('sikkerhetIndicator_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            				Sikkerhet - Indikator</span>&nbsp;
					            				<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="sikkerhetIndicator_info" class="popupWithInputText text11"  >
					           						Indikator for specifik omstendighet (0 eller 1)	
												</span>	
												</div>
											
					            				<select name="thsik" id="thsik" >
							 				  <option value="0"<c:if test="${model.record.thsik == '0'}"> selected </c:if> >0</option>
											  <option value="1"<c:if test="${model.record.thsik == '1'}"> selected </c:if> >1</option>
											</select>
											&nbsp;&nbsp;<span title="thdta">Ank.dato</span>&nbsp;<input onKeyPress="return numberKey(event) type="text" class="inputTextMediumBlue" name="thdta" id="thdta" size="9" maxlength="8" value="${model.record.thdta}">
											&nbsp;<span title="thtma">Tid</span>&nbsp;<input onKeyPress="return numberKey(event) type="text" class="inputTextMediumBlue" name="thtma" id="thtma" size="5" maxlength="4" value="${model.record.thtma}">
										</td>
					            		</tr>
					            		<tr>
					            			<td class="text12">&nbsp;<span title="thkref">Kommersielt ref.nr</span></td>
					            		</tr>
					            		<tr>
					            			<td><input type="text" class="inputTextMediumBlue" name="thkref" id="thkref" size="70" maxlength="70" value="${model.record.thkref}"></td>
					            		</tr>
					            		<tr>
					            			<td class="text12">&nbsp;<span title="thtref">Transportør ref.nr</span></td>
					            		</tr>
					            		<tr>
										<td><input type="text" class="inputTextMediumBlue" name="thtref" id="thtref" size="70" maxlength="35" value="${model.record.thtref}"></td>
					            		</tr>
					        		</table>
					        </td>
				            <td width="50%" align="left" valign="top" class="text12" align="left" >   
				            		<table width="99%" align="left" border="0" cellspacing="0" cellpadding="0">
				            			<tr height="18px">
					            			<td class="text12">&nbsp;</td>
					            		</tr>
		            					<tr>
					            			<td class="text12">
					            				<img onMouseOver="showPop('spesOmstand_info');" onMouseOut="hidePop('spesOmstand_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            				<span title="thspom">Spes.omstendighet</span>&nbsp;
					            				<select name="thspom" id="thspom">
					            				<option value="">-velg-</option>
					 				  			<c:forEach var="code" items="${model.ncts096_SpecOmst_CodeList}" >
	                             	 				<option value="${code.tkkode}"<c:if test="${model.record.thspom == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>
											<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="spesOmstand_info" class="popupWithInputText text11"  >
								           			<ul>
								           				<c:forEach var="code" items="${model.ncts096_SpecOmst_CodeList}" >
									 				  		<li><b>${code.tkkode}</b>&nbsp;${code.tktxtn}</li>
								 				  		</c:forEach>	
								           			</ul>
												</span>
											</div>
				            				</td>
					            			<td class="text12">
					            				<img onMouseOver="showPop('betalmade_info');" onMouseOut="hidePop('betalmade_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            				<span title="thtkbm">Transp.kost/Betal.måte</span>&nbsp;
					            				<select name="thtkbm" id="thtkbm">
					            				<option value="">-velg-</option>
					 				  			<c:forEach var="code" items="${model.ncts116_BetalningTransport_CodeList}" >
	                             	 				<option value="${code.tkkode}"<c:if test="${model.record.thtkbm == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>
											<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="betalmade_info" class="popupWithInputText text11"  >
								           			<ul>
								           				<c:forEach var="code" items="${model.ncts116_BetalningTransport_CodeList}" >
									 				  		<li><b>${code.tkkode}</b>&nbsp;${code.tktxtn}</li>
								 				  		</c:forEach>	
								           			</ul>
											</span>
											</div>
					            			</td>
					            		</tr>
					            		<tr>
					            			<td class="text12">&nbsp;&nbsp;<span title="thlosd">Lossested</span>
					            				<input type="text" class="inputTextMediumBlue" name="thlosd" id="thlosd" size="30" maxlength="35" value="${model.record.thlosd}">
					            			</td>
					            			<td class="text12">&nbsp;&nbsp;<span title="thlosdsk">Lossested språkkode</span>
					            				<select name="thlosdsk" id="thlosdsk">
					            				<option value="">-velg-</option>
					 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                             	 			<option value="${code.tkkode}"<c:if test="${model.record.thlosdsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="thlosdskIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
											
					            			</td>
					            		</tr>
					            		<tr>
					            			<td class="text12">&nbsp;
					            				<table>
					            				<tr>
					            					<td colspan="4" class="text12">&nbsp;
					            					<span title="thlkr1-8">Landkode for reiserute</span>
					            					
					            					</td>
					            				</tr>
					            				<tr>
						            				<td>
						            				<select name="thlkr1" id="thlkr1">
									            		<option value="">-velg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.zkod}"<c:if test="${model.record.thlkr1 == country.zkod}"> selected </c:if> >${country.zkod}</option>
													</c:forEach> 
												</select>
												</td>
												<td>
						            				<select name="thlkr2" id="thlkr2">
									            		<option value="">-velg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.zkod}"<c:if test="${model.record.thlkr2 == country.zkod}"> selected </c:if> >${country.zkod}</option>
													</c:forEach> 
												</select>
												</td>
												<td>
						            				<select name="thlkr3" id="thlkr3">
									            		<option value="">-velg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.zkod}"<c:if test="${model.record.thlkr3 == country.zkod}"> selected </c:if> >${country.zkod}</option>
													</c:forEach> 
												</select>
												</td>
												<td>
												<select name="thlkr4" id="thlkr4">
									            		<option value="">-velg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.zkod}"<c:if test="${model.record.thlkr4 == country.zkod}"> selected </c:if> >${country.zkod}</option>
													</c:forEach> 
												</select>
						            				</td>
					            				</tr>
					            				<tr>	
						            				<td>
						            				<select name="thlkr5" id="thlkr5">
									            		<option value="">-velg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.zkod}"<c:if test="${model.record.thlkr5 == country.zkod}"> selected </c:if> >${country.zkod}</option>
													</c:forEach> 
												</select>
						            				</td>
						            				<td>
						            				<select name="thlkr6" id="thlkr6">
									            		<option value="">-velg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.zkod}"<c:if test="${model.record.thlkr6 == country.zkod}"> selected </c:if> >${country.zkod}</option>
													</c:forEach> 
												</select>
						            				</td>
						            				<td>
						            				<select name="thlkr7" id="thlkr7">
									            		<option value="">-velg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.zkod}"<c:if test="${model.record.thlkr7 == country.zkod}"> selected </c:if> >${country.zkod}</option>
													</c:forEach> 
												</select>
												</td>
												<td>
						            				<select name="thlkr8" id="thlkr8">
									            		<option value="">-velg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.zkod}"<c:if test="${model.record.thlkr8 == country.zkod}"> selected </c:if> >${country.zkod}</option>
													</c:forEach> 
												</select>
												</td>
											</tr>
											</table>
					            			</td>
					            		</tr>
			 					</table>
							</td>
						</tr>
		            		<tr height="3"><td>&nbsp;</td></tr>
				        	<tr>
				        		<td width="50%" align="center" valign="top">
								<table width="99%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="18px">
							 			<td class="text12White">
							 				&nbsp;
					 						&nbsp;Avsender - Sikkerhet&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						 				</td>
					 				</tr>
			 					</table>
			 					<table width="99%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		<tr>	
							 			<td width="5">&nbsp;</td>
							            <td >	
											<%-- create record --%>
										 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
										 		<tr>
											 		<td>
												 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
											 				<%-- ================================================================================== --%>
													        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
													        		 These original values will be used when the user clicks "Cancel" buttons (puttting
													        		 back original value)																--%> 
													        	<%-- ================================================================================== --%>
													        	<input type="hidden" name="orig_thknss" id="orig_thknss" value='${model.record.thknss}'>
													        	<input type="hidden" name="orig_thnass" id="orig_thnass" value='${model.record.thnass}'>
													        	<input type="hidden" name="orig_thtinss" id="orig_thtinss" value='${model.record.thtinss}'>
													        	<input type="hidden" name="orig_thadss1" id="orig_thadss1" value='${model.record.thadss1}'>
													        	<input type="hidden" name="orig_thpnss" id="orig_thpnss" value='${model.record.thpnss}'>
													        	<input type="hidden" name="orig_thpsss" id="orig_thpsss" value='${model.record.thpsss}'>
													        	<input type="hidden" name="orig_thlkss" id="orig_thlkss" value='${model.record.thlkss}'>
													        	<input type="hidden" name="orig_thskss" id="orig_thskss" value='${model.record.thskss}'>
													        	
													        	<tr height="10">
													            <td class="text12" align="left">&nbsp;</td> 
													        </tr>
													 		<tr height="15">
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thknss">Kundenummer</span></td>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thnass">Navn</span>
													            	<a tabindex="-1" id="thnassIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																	</a>
											            		</td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thknss" id="thknss" size="10" maxlength="8" value="${model.record.thknss}"></td>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thnass" id="thnass" size="30" maxlength="35" value="${model.record.thnass}"></td>
													        </tr>
													        <tr>
											        				<td class="text12" align="left" >&nbsp;&nbsp;<span title="thtinss">TIN</span></td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thtinss" id="thtinss" size="20" maxlength="17" value="${model.record.thtinss}"></td>
													        </tr>
				
													        <tr height="4"><td>&nbsp;</td></tr>
													        <tr>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thadss1">Adresse</span></td>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thskss">Språkkode</span>
												            		
													            </td>
													        </tr>
													        <tr>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thadss1" id="thadss1" size="30" maxlength="30" value="${model.record.thadss1}"></td>
													            <td class="text12" align="left" >
													            		&nbsp;<select name="thskss" id="thskss">
														            		<option value="">-velg-</option>
														 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
										                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thskss == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																			</c:forEach> 
																	</select>
																	<a tabindex="-1" id="thskssIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																	</a>
																</td>
													        </tr>
													        <tr>
													        		<td>
														        		<table>
														        		<tr>
														            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpsss">Postadresse</span></td>
														            		<td align="left">&nbsp;</td>
														            	</tr>
														        		<tr>
														            		<td align="left">
														       				<input type="text" class="inputTextMediumBlue" name="thpsss" id="thpsss" size="30" maxlength="24" value="${model.record.thpsss}">
													            			</td> 
														            		<td align="left">&nbsp;</td>
														        		</tr>    	
														            	</table>
													            </td>
													            <td >
														            	<table>
														        		<tr>
														        			<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpnss">Postnummer</span></td>
														            		<td class="text12" align="left" >&nbsp;<span title="thlkss">Land</span>
														            		
														            		</td>
														            	</tr>
														        		<tr >
														        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpnss" id="thpnss" size="10" maxlength="8" value="${model.record.thpnss}"></td> 
														            		<td align="left">
														            			<select name="thlkss" id="thlkss">
																            		<option value="">-velg-</option>
															 				  	<c:forEach var="country" items="${model.countryCodeList}" >
											                                	 	<option value="${country.zkod}"<c:if test="${model.record.thlkss == country.zkod}"> selected </c:if> >${country.zkod}</option>
																				</c:forEach> 
																			</select>
																			<a tabindex="-1" id="thlkssIdLink">
																				<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																			</a>
														            		</td> 
														        		</tr>  
														            	</table>
													            </td>
												            	</tr>
													        
													        <tr height="15">
											            		<td class="text12Bold" align="left" >&nbsp;</td>
											            		<td class="text12Bold" align="left" >&nbsp;</td> 
													        </tr>  
													        
												        </table>
											        </td>
										        </tr>
											</table>          
						            		</td>
						           	</tr> 
		 						</table>
			 					<table width="99%" align="center" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="5"><td>&nbsp;</td></tr>
			 					</table>	
								<table width="99%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="18px">
							 			<td class="text12White">
							 				&nbsp;
					 						&nbsp;Transportør - Sikkerhet&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						 				</td>
					 				</tr>
			 					</table>
			 					<table width="99%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		<tr>	
							 			<td width="5">&nbsp;</td>
							            <td >	
											<%-- create record --%>
										 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
										 		<tr>
											 		<td>
												 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
											 				<%-- ================================================================================== --%>
													        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
													        		 These original values will be used when the user clicks "Cancel" buttons (puttting
													        		 back original value)																--%> 
													        	<%-- ================================================================================== --%>
													        	<input type="hidden" name="orig_thknt" id="orig_thknt" value='${model.record.thknt}'>
													        	<input type="hidden" name="orig_thnat" id="orig_thnat" value='${model.record.thnat}'>
													        	<input type="hidden" name="orig_thtint" id="orig_thtint" value='${model.record.thtint}'>
													        	<input type="hidden" name="orig_thadt1" id="orig_thadt1" value='${model.record.thadt1}'>
													        	<input type="hidden" name="orig_thpnt" id="orig_thpnt" value='${model.record.thpnt}'>
													        	<input type="hidden" name="orig_thpst" id="orig_thpst" value='${model.record.thpst}'>
													        	<input type="hidden" name="orig_thlkt" id="orig_thlkt" value='${model.record.thlkt}'>
													        	<input type="hidden" name="orig_thskt" id="orig_thskt" value='${model.record.thskt}'>
													        	
													        	<tr height="10">
													            <td class="text12" align="left">&nbsp;</td> 
													        </tr>
													 		<tr height="15">
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thknt">Kundenummer</span></td>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thnat">Navn</span>
													            	<a tabindex="-1" id="thnatIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																	</a>
											            		</td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thknt" id="thknt" size="10" maxlength="8" value="${model.record.thknt}"></td>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thnat" id="thnat" size="30" maxlength="35" value="${model.record.thnat}"></td>
													        </tr>
													        <tr>
											        				<td class="text12" align="left" >&nbsp;&nbsp;<span title="thtint">TIN</span></td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thtint" id="thtint" size="20" maxlength="17" value="${model.record.thtint}"></td>
													        </tr>
				
													        <tr height="4"><td>&nbsp;</td></tr>
													        <tr>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thadt1">Adresse</span></td>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thskt">Språkkode</span>
												            		
													            </td>
													        </tr>
													        <tr>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thadt1" id="thadt1" size="30" maxlength="30" value="${model.record.thadt1}"></td>
													            <td class="text12" align="left" >
													            		&nbsp;<select name="thskt" id="thskt">
														            		<option value="">-velg-</option>
														 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
										                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thskt == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																			</c:forEach> 
																	</select>
																	<a tabindex="-1" id="thsktIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																	</a>
																</td>
													        </tr>
													        <tr>
													        		<td>
														        		<table>
														        		<tr>
														            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpst">Postadresse</span></td>
														            		<td align="left">&nbsp;</td>
														            	</tr>
														        		<tr>
														            		<td align="left">
														       				<input type="text" class="inputTextMediumBlue" name="thpst" id="thpst" size="30" maxlength="24" value="${model.record.thpst}">
													            			</td> 
														            		<td align="left">&nbsp;</td>
														        		</tr>    	
														            	</table>
													            </td>
													            <td >
														            	<table>
														        		<tr>
														        			<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpnt">Postnummer</span></td>
														            		<td class="text12" align="left" >&nbsp;<span title="thlkt">Land</span>
														            		
														            		</td>
														            	</tr>
														        		<tr >
														        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpnt" id="thpnt" size="10" maxlength="8" value="${model.record.thpnt}"></td> 
														            		<td align="left">
													            			<select name="thlkt" id="thlkt">
															            		<option value="">-velg-</option>
														 					  	<c:forEach var="country" items="${model.countryCodeList}" >
										                                	 	<option value="${country.zkod}"<c:if test="${model.record.thlkt == country.zkod}"> selected </c:if> >${country.zkod}</option>
																				</c:forEach> 
																			</select>
																			<a tabindex="-1" id="thlktIdLink">
																				<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																			</a>
														            		</td> 
														        		</tr>  
														            	</table>
													            </td>
												            	</tr>
													        
													        <tr height="15">
											            		<td class="text12Bold" align="left" >&nbsp;</td>
											            		<td class="text12Bold" align="left" >&nbsp;</td> 
													        </tr>  
													        
												        </table>
											        </td>
										        </tr>
											</table>          
						            		</td>
						           	</tr> 
		 						</table>
			        			</td>
			        			<td width="50%" align="center" valign="top">
								<table width="99%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="18px">
							 			<td class="text12White">
							 				&nbsp;
					 						&nbsp;Mottaker - Sikkerhet&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						 				</td>
					 				</tr>
			 					</table>
			 					<table width="99%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		<tr>	
							 			<td width="5">&nbsp;</td>
							            <td >	
											<%-- create record --%>
										 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
										 		<tr>
											 		<td>
												 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
													 		<%-- ================================================================================== --%>
													        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
													        		 These original values will be used when the user clicks "Cancel" buttons (puttting
													        		 back original value)																--%> 
													        	<%-- ================================================================================== --%>
													        	<input type="hidden" name="orig_thknks" id="orig_thknks" value='${model.record.thknks}'>
													        	<input type="hidden" name="orig_thnaks" id="orig_thnaks" value='${model.record.thnaks}'>
													        	<input type="hidden" name="orig_thtinks" id="orig_thtinks" value='${model.record.thtinks}'>
													        	<input type="hidden" name="orig_thadks1" id="orig_thadks1" value='${model.record.thadks1}'>
													        	<input type="hidden" name="orig_thpnks" id="orig_thpnks" value='${model.record.thpnks}'>
													        	<input type="hidden" name="orig_thpsks" id="orig_thpsks" value='${model.record.thpsks}'>
													        	<input type="hidden" name="orig_thlkks" id="orig_thlkks" value='${model.record.thlkks}'>
													        	<input type="hidden" name="orig_thskks" id="orig_thskks" value='${model.record.thskks}'>
													 		<tr height="10">
													            <td class="text12" align="left">&nbsp;</td> 
													        </tr>
													        <tr height="15">
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thknks">Kundenummer</span></td>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thnaks">Navn</span>
													            	<a tabindex="-1" id="thnaksIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																	</a>	
													            </td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thknks" id="thknks" size="10" maxlength="8" value="${model.record.thknks}"></td>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thnaks" id="thnaks" size="30" maxlength="35" value="${model.record.thnaks}"></td>
													        </tr>
													        <tr>
											        				<td class="text12" align="left" >&nbsp;&nbsp;<span title="thtinks">TIN</span></td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thtinks" id="thtinks" size="20" maxlength="17" value="${model.record.thtinks}"></td>
													        </tr>
				
													        <tr height="4"><td>&nbsp;</td></tr>
													        <tr>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thadks1">Adresse</span></td>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thskks">Språkkode</span>
												            		
													            </td>
													        </tr>
													        <tr>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thadks1" id="thadks1" size="30" maxlength="30" value="${model.record.thadks1}"></td>
													            <td class="text12" align="left" >
													            		&nbsp;<select name="thskks" id="thskks">
														            		<option value="">-velg-</option>
														 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
										                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thskks == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																			</c:forEach> 
																	</select>
																	<a tabindex="-1" id="thskksIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																	</a>
																</td>
													        </tr>
													        <tr>
													        		<td>
														        		<table>
														        		<tr>
														            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpsks">Postadresse</span></td>
														            		<td align="left">&nbsp;</td>
														            	</tr>
														        		<tr>
														            		<td align="left">
														       				<input type="text" class="inputTextMediumBlue" name="thpsks" id="thpsks" size="30" maxlength="24" value="${model.record.thpsks}">
													            			</td> 
														            		<td align="left">&nbsp;</td>
														        		</tr>    	
														            	</table>
													            </td>
													            <td >
														            	<table>
														        		<tr>
														        			<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpnks">Postnummer</span></td>
														            		<td class="text12" align="left" >&nbsp;<span title="thlkks">Land</span>
														            		
														            		</td>
														            	</tr>
														        		<tr >
														        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpnks" id="thpnks" size="10" maxlength="8" value="${model.record.thpnks}"></td> 
														            		<td align="left">
														            			<select name="thlkks" id="thlkks">
																            		<option value="">-velg-</option>
															 				  	<c:forEach var="country" items="${model.countryCodeList}" >
											                                	 	<option value="${country.zkod}"<c:if test="${model.record.thlkks == country.zkod}"> selected </c:if> >${country.zkod}</option>
																				</c:forEach> 
																			</select>
																			<a tabindex="-1" id="thlkksIdLink">
																				<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																			</a>
														            		</td> 
														        		</tr>  
														            	</table>
													            </td>
												            	</tr>
													        
													        <tr height="15">
											            		<td class="text12Bold" align="left" >&nbsp;</td>
											            		<td class="text12Bold" align="left" >&nbsp;</td> 
													        </tr>  
												        </table>
											        </td>
										        </tr>
											</table>          
						            		</td>
						           	</tr> 
		 						</table>
			        			</td>
				        	</tr>
				        	<tr height="10"><td>&nbsp;</td></tr>
		            	</table>
		            </td>  
		            <td>&nbsp;</td>
		        </tr>
		        	<tr height="20"><td>&nbsp;</td></tr>
			</table> <%-- END to the wrapper table for EXTRAORDINARY data --%>	
			</td>			
		</tr>		         
	</table> 
	</form> 
	</td>
 </tr>
 
 
 <tr>
	<td>
		<%-- change status admin dialog --%>	
		<div id="dialogUpdateStatus" title="Dialog">
			<form action="tvinnsadnctsexport_updateStatus.do" name="updateStatusForm" id="updateStatusForm" method="post">
			 	<input type="hidden" name="currentAvd" id="currentAvd" value="${model.record.thavd}">
			 	<input type="hidden" name="currentOpd" id="currentOpd" value="${model.record.thtdn}">
			 		
				<p class="text12" >Change status as needed.</p>
				<table>
					<tr>
						<td class="text12" align="left" >&nbsp;Status</td>
						<td class="text12MediumBlue">
							<select name="selectedStatus" id="selectedStatus">
		            			<option value="">-velg-</option>
		 				  		<option value="E">E</option>
						  		<option value="Q">Q</option>
							  	<option value="A">A</option>
						  		<option value="B">B</option>
							  	<option value="C">C</option> 
							  	<option value="T">T</option>
							  	<option value="M">M</option>
							  	<option value="D">D</option> 
							  	<option value="V">V</option>
							  	<option value="U">U</option>
							  	<option value="P">P</option> 
							  	<option value="K">K</option>
							  	<option value="L">L</option>
							  	<option value="A">A</option>
							  	<option value="V">V</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</td>
</tr> 
	