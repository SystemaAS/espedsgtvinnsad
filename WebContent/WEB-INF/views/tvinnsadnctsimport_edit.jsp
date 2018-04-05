<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadnctsimport_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
	
<table width="100%" cellspacing="0" border="0" cellpadding="0">

 <tr>
 <td>	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<c:choose> 
			    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport.do?action=doFind&avd=${model.record.tiavd}&sign=${model.record.tisg}&opd=${model.record.titdn}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.tab"/></font>
							<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.created.mastertopic.tab"/>
						</font>
						<font class="text12MediumBlue">[${model.record.titdn}]</font>
						<c:if test="${model.record.tist == 'F' || model.record.tist == 'M' || empty model.record.tist}">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
						
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_edit_items.do?action=doFetch&avd=${model.record.tiavd}&sign=${model.record.tisg}
													&opd=${model.record.titdn}&mrnNr=${model.record.titrnr}&godsNr=${model.record.tign}
													&status=${model.record.tist}&datum=${model.record.tidt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.item.createnew.tab"/>
							</font>
							<c:if test="${ model.record.tist == 'F' || model.record.tist == 'M' || empty model.record.tist}">
								<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
							</c:if>
							
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_unloading_edit.do?avd=${model.record.tiavd}&sign=${model.record.tisg}
													&opd=${model.record.titdn}&mrnNr=${model.record.titrnr}&godsNr=${model.record.tign}
													&status=${model.record.tist}&datum=${model.record.tidt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.unloading.createnew.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/unloading.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_unloading_edit_items.do?action=doFetch&avd=${model.record.tiavd}&sign=${model.record.tisg}
											&opd=${model.record.titdn}&mrnNr=${model.record.titrnr}&godsNr=${model.record.tign}
											&status=${model.record.tist}&datum=${model.record.tidt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.unloading.item.createnew.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="item lines">
						</a>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_logging.do?avd=${model.record.tiavd}&sign=${model.record.tisg}
											&opd=${model.record.titdn}&mrnNr=${model.record.titrnr}&godsNr=${model.record.tign}
											&status=${model.record.tist}&datum=${model.record.tidt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.logging.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_archive.do?avd=${model.record.tiavd}&sign=${model.record.tisg}
											&opd=${model.record.titdn}&mrnNr=${model.record.titrnr}&godsNr=${model.record.tign}
											&status=${model.record.tist}&datum=${model.record.tidt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.archive.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
						</a>
					</td>
					<td width="13%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:when>
				<c:otherwise>
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport.do?action=doFind&sign=${user.tvinnSadSign}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.tab"/></font>
							<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>

					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.createnew.tab"/></font>
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
	<form name="nctsImportSaveNewTopicForm" id="nctsImportSaveNewTopicForm" method="post">
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<tr height="3">
 			<td colspan="2">&nbsp;
		 		<%-- test indicator /per avdelning --%> 
				<c:forEach var="record" items="${avdListSessionTestFlag}" >
					<c:if test="${record.avd == model.record.tiavd}">	
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
			<input type="hidden" name="opd" id="opd" value='${model.record.titdn}'>
			<%-- topic specific --%>
			<input type="hidden" name="tiavd" id="tiavd" value='${model.record.tiavd}'>
			<input type="hidden" name="titdn" id="titdn" value='${model.record.titdn}'>
			<input type="hidden" name="tisg" id="tisg" value='${model.record.tisg}'>
			<input type="hidden" name="tist" id="tist" value='${model.record.tist}'>
			<input type="hidden" name="tidt" id="tidt" value='${model.record.tidt}'>
	    		<input type="hidden" name="avd" id="avd" value='${model.record.tiavd}'>
			<input type="hidden" name="sign" id="sign" value='${model.record.tisg}'>
			
			<tr >
				<td colspan="3" align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Avd:&nbsp;${model.record.tiavd}&nbsp;&nbsp;<span title="titdn">Tolldekl.:</span>&nbsp;<b>${model.record.titdn}</b>
				</td>
				<c:if test="${'1' != isTestAvd}">		
					<td align="right" >
						<%--This checkbox appears only in real production. Otherwise use the Testavdelning --%>
						<input type="checkbox" name="dknh_0035" id="dknh_0035" value="1" <c:if test="${Xmodel.record.dknh_0035 == '1'}"> checked </c:if>  ><font class="text12MediumBlue"><b>TEST flagg</b></font>&nbsp;&nbsp;&nbsp;
					</td>
				</c:if>
			</tr>
			<tr height="2"><td></td></tr>
			<tr >
				<td align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title="tign">Godsnr:</span>&nbsp;<b>${model.record.tign}</b>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title="titrnr">MRN-nr:</span>&nbsp;<b>${model.record.titrnr}</b>
				</td>
			</tr>
			<tr height="5"><td></td></tr>
			<tr >
				<td align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title="tisg">Sign:</span>&nbsp;<b>${model.record.tisg}</b>,&nbsp;&nbsp;<span title="tidt">Dato:</span>&nbsp;<b>${model.record.tidt}</b>,
					&nbsp;
					<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
           			<span title="tist">Stat<a id="updateStatusLink" runat="server" href="#"><font class="text11MediumBlue">u</font></a>s:</span>&nbsp;<b>${model.record.tist}</b>
					&nbsp;&nbsp;
					<font class="text16RedBold" >*</font><span title="tienkl">Type prosedyre</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select class="text11" name="tienkl" id="tienkl">
	            			<option value="J"<c:if test="${model.record.tienkl == 'J'}"> selected </c:if> >Forenklet</option>
					  	<option value="N"<c:if test="${model.record.tienkl == 'N'}"> selected </c:if> >Normal</option>
					</select>
				
				<div class="text11" style="position: relative;" align="left">
				<span style="position:absolute; left:150; top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
					Denne statuskoden forteller hvilken status deklarasjonen har i det øyeblikket man foretar spørringen.
			        Følgende koder er i bruk:
                    Kun status <b>M</b>, <b>(Fejl)</b> eller <b>' '</b> kan redigeres.
	           			<ul>
			            <li><b>' '</b>&nbsp;(Blank) Åpen for endring.</li>
                        <li><b>E</b>&nbsp;Noen arbeider med (Endrer) den aktuelle TET nå. Hvis man arbeider med en
									deklarasjon og strømbrudd eller lignende inntreffer vil deklarasjonen bli
									"hengende" i status 'E'.
                                   	Man må i slike tilfeller endre status på deklarasjonen (MENU TET, punkt 22.) fra
									'E' til ' ' manuelt.</li>
                        <li><b>Q</b>&nbsp;Intern postkasse" (Venter på at program skal lage en sending av denne og andre)</li>
                        <li><b>A</b>&nbsp;Ligger i sendekø (ER lagt i sendings-fil) for sending til TAD</li>
                        <li><b>B</b>&nbsp;Kommet til IBM, men ennå ikke til TAD (kun ved nettverk IGN)</li>
                        <li><b>C</b>&nbsp;Kommet til TAD (egentlig "hentet TIL TAD's postkasse)</li>
                        <li><b>F</b>&nbsp;IE44 er sendt (egentlig "hentet TIL TAD's postkasse)</li>
                        <li><b>M</b>&nbsp;Avvisning av ankomstmelding (har mottatt IE08)</li>
                        <li><b>N</b>&nbsp;Avvisning av lossemerknader (har mottatt IE58)</li>
                        <li><b>U</b>&nbsp;Har fått lossetillatelse (IE43)</li>
                        <li><b>P</b>&nbsp;Transittering er avsluttet (Har mottat IE25)</li>
                        <li><b>K</b>&nbsp;Venter på PRINT (midlertidig kortvarig status)</li>
                        <li><b>+</b>&nbsp;Midlertidig arbeidsstatus. Alle som hadde Q på et gitt tidspunkt (=klar for sending)</li>
                        <li><b>Å</b>&nbsp;Midlertidig arbeidstatus. (Mellom mottak av melding og printing av feilmelding)</li>
	           			</ul>
				</span>	 
				</div> 
				</td>                
			</tr>	
			<%-- test indicator /per avdelning --%> 
			<c:if test="${'1' == isTestAvd}">
				<tr>
					<td align="left" class="text14Red" >
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>[ TEST Avdeling ]</b>
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
			<input type="hidden" name="opd" id="opd" value='${model.record.titdn}'>
			<%-- topic specific (syop and tuid) --%>
			<input type="hidden" name="titdn" id="titdn" value='${model.record.titdn}'>
			<input type="hidden" name="tist" id="tist" value='${model.record.tist}'>
			<input type="hidden" name="tidt" id="tidt" value='${model.record.tidt}'>
			
			<tr >
				<td align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;<font class="text16RedBold" >*</font><span title="avd(tiavd)">Avd:</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thavd) --%>
           			<select name="avd" id="avd">
	            		<option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                       	 	<option value="${record.avd}"<c:if test="${model.record.tiavd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>
						</c:forEach> 
					</select>
					&nbsp;
					<font class="text16RedBold" >*</font><span title="sign(tisg)">Sign:</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select name="sign" id="sign">
	            		<option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
	 				  		<c:choose>
		 				  		<c:when test="${empty model.record.tisg}">
		                        	 	<option value="${record.sign}"<c:if test="${user.skatSign == record.sign}"> selected </c:if> >${record.sign}</option>
		 				  		</c:when>
		 				  		<c:otherwise>
		                        	 	<option value="${record.sign}"<c:if test="${model.record.tisg == record.sign}"> selected </c:if> >${record.sign}</option>
	                        	 	</c:otherwise>
                        	 	</c:choose>
						</c:forEach> 
					</select>
					<font class="text16RedBold" >*</font><span title="tienkl">Type procedure</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select name="tienkl" id="tienkl">
	            			<option value="J"<c:if test="${model.record.tienkl == 'J'}"> selected </c:if> >Forenklet</option>
					  	<option value="N"<c:if test="${model.record.tienkl == 'N'}"> selected </c:if> >Normal</option>
					</select>
				</td>
				<td>&nbsp;</td>
			</tr>	
		</c:otherwise>
		</c:choose>

		<tr height="10"><td colspan="2">&nbsp;</td></tr>
		<%-- --------------- --%>
		<%-- CONTENT --%>
		<%-- --------------- --%>
		<tr>
		<td >
		<table align="center" width="98%" border="0" cellspacing="1" cellpadding="0">
			
			<%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td colspan="10">
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
				<td colspan="10">
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td class="textError">
				 				<ul>
                                    <li>
                                      	Fel vid uppdatering:${model.errorMessage}]  
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
			<td width="50%"class="text12" valign="top">
				<table width="90%" align="left" border="0" cellspacing="1" cellpadding="0">
				 	<tr >
					 	<td >
						<table width="100%" class="formFrameHeader" border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<td class="text12White">&nbsp;&nbsp;Næringsliv - Ansvarlig&nbsp;</td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
		            <tr >
					 	<td>
						<table width="100%" class="formFrame" border="0" cellspacing="2" cellpadding="1">
					 		<tr >
					 			<td class="text12">&nbsp;<span title="tikn">Kundenr</span></td>
					 			<td class="text12">&nbsp;</td>
			 				</tr>
			 				<tr >
					 			<td class="text12"><input type="text" class="inputTextMediumBlue" name="tikn" id="tikn" size="8" maxlength="8" value="${model.record.tikn}"></td>
					 			<td class="text12">&nbsp;</td>
			 				</tr>
			 				<tr >
								<%-- ================================================================================== --%>
						        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
						        		 These original values will be used when the user clicks "Cancel" buttons (puttting
						        		 back original value)																--%> 
						        	<%-- ================================================================================== --%>
						        	<input type="hidden" name="orig_tikn" id="orig_tikn" value='${model.record.tikn}'>
						        	<input type="hidden" name="orig_tina" id="orig_tina" value='${model.record.tina}'>
						        	<input type="hidden" name="orig_titin" id="orig_titin" value='${model.record.titin}'>
						        	<input type="hidden" name="orig_tiad1" id="orig_tiad1" value='${model.record.tiad1}'>
						        	<input type="hidden" name="orig_tipn" id="orig_tipn" value='${model.record.tipn}'>
						        	<input type="hidden" name="orig_tips" id="orig_tips" value='${model.record.tips}'>
						        	<input type="hidden" name="orig_tilk" id="orig_tilk" value='${model.record.tilk}'>
						        	<input type="hidden" name="orig_tisk" id="orig_tisk" value='${model.record.tisk}'>
			 				
					 			<td class="text12">&nbsp;<font class="text16RedBold" >*</font><span title="titin">TIN</span></td>
					 			<td class="text12">&nbsp;<font class="text16RedBold" >*</font><span title="tina">Navn</span>
						            <a tabindex="-1" id="tinaIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
									</a>
					 			</td>
			 				</tr>
			 				<tr >
					 			<td class="text12"><input type="text" class="inputTextMediumBlueMandatoryField" name="titin" id="titin" size="17" maxlength="17" value="${model.record.titin}"></td>
					 			<td class="text12"><input type="text" class="inputTextMediumBlueMandatoryField" name="tina" id="tina" size="30" maxlength="30" value="${model.record.tina}"></td>
			 				</tr>
			 				<tr >
					 			<td class="text12">&nbsp;<span title="tiad1">Adresse</span></td>
					 			<td class="text12">&nbsp;<span title="tisk">Språkkode</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text12"><input type="text" class="inputTextMediumBlue" name="tiad1" id="tiad1" size="30" maxlength="30" value="${model.record.tiad1}"></td>
					 			<td class="text12">
					 				<select name="tisk" id="tisk">
						            		<option value="">-velg-</option>
						 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
					                             <option value="${code.tkkode}"<c:if test="${model.record.tisk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
											</c:forEach> 
									</select>
									<a tabindex="-1" id="tiskIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
					 			</td>
			 				</tr>
			 				<tr >
					 			<td class="text12">&nbsp;<span title="tips">Postadresse</span></td>
					 			<td class="text12">&nbsp;<span title="tipn">Postnr</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text12"><input type="text" class="inputTextMediumBlue" name="tips" id="tips" size="24" maxlength="24" value="${model.record.tips}"></td>
					 			<td class="text12"><input type="text" class="inputTextMediumBlue" name="tipn" id="tipn" size="9" maxlength="9" value="${model.record.tipn}"></td>
			 				</tr>
			 				<tr >
					 			<td class="text12">&nbsp;<span title="tilk">Landkode</span>
					 			
					 			</td>
					 			<td class="text12">&nbsp</td>
			 				</tr>
			 				<tr >
					 			<td>
					 				<select name="tilk" id="tilk">
						            		<option value="">-velg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 		<option value="${country.zkod}"<c:if test="${model.record.tilk == country.zkod}"> selected </c:if> >${country.zkod}</option>
										</c:forEach> 
									</select>
									<a tabindex="-1" id="tilkIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
				 				</td>
					 			<td class="text12">
					 				&nbsp;
					 			</td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
	            
	            </table>
            </td>
            
            	<td width="50%" class="text12" valign="top">
				<table width="100%" align="center" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td valign="top">
						<table border="0" cellspacing="1" cellpadding="0">
						<tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><font class="text16RedBold" >*</font><span title="tign">Godsnr</span></td>
				            <td colspan="3" ><input type="text" class="inputTextMediumBlueMandatoryField" name="tign" id="tign" size="36" maxlength="35" value="${model.record.tign}"></td>
			            </tr>
						<tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><font class="text16RedBold" >*</font><span title="tignsk">Språkkode</span>&nbsp;</td>
				            <td >
					            <select class="inputTextMediumBlueMandatoryField" name="tignsk" id="tignsk">
					            		<option value="">-velg-</option>
					 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                             <option value="${code.tkkode}"<c:if test="${model.record.tignsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
								</select>
								<a tabindex="-1" id="tignskIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								</a>
					 								
				            </td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><font class="text16RedBold" >*</font><span title="titrnr">MRN-nr</span></td>
				            <td colspan="3" ><input type="text" class="inputTextMediumBlueMandatoryField" name="titrnr" id="titrnr" size="36" maxlength="18" value="${model.record.titrnr}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><font class="text16RedBold" >*</font><span title="tialk">Avs.land</span>&nbsp;</td>
				            <td >
					            <select class="inputTextMediumBlueMandatoryField" name="tialk" id="tialk">
					            		<option value="">-velg-</option>
				 				  	<c:forEach var="country" items="${model.countryCodeList}" >
		                              	 	<option value="${country.zkod}"<c:if test="${model.record.tialk == country.zkod}"> selected </c:if> >${country.zkod}</option>
									</c:forEach> 
								</select>
								<a tabindex="-1" id="tialkIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								</a>
					 										
				            </td>
			            </tr>
			            <tr height="2"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><font class="text16RedBold" >*</font><span title="titsb">Freml.tollsted</span></td>
				            <td ><input type="text" class="inputTextMediumBlueMandatoryField" name="titsb" id="titsb" size="9" maxlength="8" value="${model.record.titsb}">
				            	<a tabindex="-1" id="titsbIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								</a>
					        </td>
				            <td class="text12" ><span title="tiskb">Språkkode</span>&nbsp;</td>
				            <td >
					            <select name="tiskb" id="tiskb">
					            		<option value="">-velg-</option>
					 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                             <option value="${code.tkkode}"<c:if test="${model.record.tiskb == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
								</select>
								<a tabindex="-1" id="tiskbIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								</a>
				 				
				            </td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><span title="tidtf">Frigivelsesdato</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tidtf" id="tidtf" size="8" maxlength="8" value="${model.record.tidtf}"></td>
			            </tr>
			            
			            <tr height="10"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><span title="tialsk">Avt.lag.sted (kode)</span></td>
				            <td ><input type="text" class="inputText" name="tialsk" id="tialsk" size="17" maxlength="17" value="${model.record.tialsk}"></td>
				            <td class="text12" ><span title="tialss">Språkkode</span>&nbsp;</td>
				            <td >
					            <select name="tialss" id="tialss">
					            		<option value="">-velg-</option>
					 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                             <option value="${code.tkkode}"<c:if test="${model.record.tialss == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
								</select>
								<a tabindex="-1" id="tialssIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								</a>
								
				            </td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><span title="tials">Avt.lag.sted</span></td>
				            <td ><input type="text" class="inputText" name="tials" id="tials" size="20" maxlength="35" value="${model.record.tials}"></td>
			            </tr>
			            
		                
			            
			            <tr height="2"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><span title="tiglsk">Godk.lag.sted (kode)</span></td>
				            <td ><input type="text" class="inputText" name="tiglsk" id="tiglsk" size="17" maxlength="17" value="${model.record.tiglsk}"></td>
				            
				            
				                
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
				           				</ol>	
				           			</p>
								</span>
								</div>	
				        	</td>

			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><span title="tiacts">Contr.sted (kode)</span></td>
				            <td ><input type="text" class="inputText" name="tiacts" id="tiacts" size="17" maxlength="17" value="${model.record.tiacts}"></td>
			            </tr>
			            
			            <tr height="2"><td>&nbsp;</td></tr>
			            <tr >	
			            		<td class="text">&nbsp;</td>
			            		<td class="text">&nbsp;</td>
			            		 
		 				    	<%-- only status = M or emtpy status is allowed --%>
		 				    <c:choose>
			 				    <c:when test="${ model.record.tist == 'F' || model.record.tist == 'M' || empty model.record.tist}">
			 				    <td class="text9BlueGreen" valign="bottom"  >
				 				    	<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='tvinnsadnctsimport_edit.do';" value='<spring:message code="systema.tvinn.sad.ncts.import.createnew.submit"/>'/>
				 				</td>    	
				 				    	&nbsp;&nbsp;
				 				    	<c:if test="${not empty model.record.titdn}">
					 				    	<td colspan="2" class="text9BlueGreen" valign="bottom"  >
					 				    		<input tabindex=-2 class="inputFormSubmit" type="submit" name="send" onclick="javascript: form.action='tvinnsadnctsimport_send.do';" value='<spring:message code="systema.tvinn.sad.ncts.import.createnew.send"/>'/>
					 				    	</td>	
				 				    	</c:if>
			 				    </c:when>
			 				    <c:otherwise>
				 				    <td colspan="2" class="text9BlueGreen" valign="bottom"  >
				 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value="<spring:message code="systema.tvinn.sad.submit.not.editable"/>"/>
				 				    	</td>	
			 				    </c:otherwise>	
		 				    </c:choose>
				        </tr>
				        </table>
				        </td>
			        </tr>
			        
	            </table>
            </td>
		</tr>

		<tr height="20"><td colspan="2">&nbsp;</td></tr>
	</table> 
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
			<form action="tvinnsadnctsimport_updateStatus.do" name="updateStatusForm" id="updateStatusForm" method="post">
			 	<input type="hidden" name="currentAvd" id="currentAvd" value="${model.record.tiavd}">
			 	<input type="hidden" name="currentOpd" id="currentOpd" value="${model.record.titdn}">
			 	<input type="hidden" name="currentSign" id="currentSign" value="${model.record.tisg}">
			 		
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
							  	<option value="F">F</option>
							  	<option value="M">M</option>
							  	<option value="N">N</option> 
							  	<option value="U">U</option>
							  	<option value="P">P</option> 
							  	<option value="K">K</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</td>
	</tr> 
 
 
	