<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadnctsimport_unloading_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
 
 	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
<form name="nctsImportSaveNewTopicForm" id="nctsImportSaveNewTopicForm" method="post">

<table width="100%" cellspacing="0" border="0" cellpadding="0">
	
 <tr>
 <td>	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport.do?action=doFind&&avd=${recordTopicTvinnSad.tiavd}&sign=${recordTopicTvinnSad.tisg}&opd=${recordTopicTvinnSad.titdn}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.tab"/></font>
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_edit.do?action=doFetch&avd=${recordTopicTvinnSad.tiavd}&opd=${recordTopicTvinnSad.titdn}
						&sysg=${recordTopicTvinnSad.tisg}&syst=${recordTopicTvinnSad.tist}&sydt=${recordTopicTvinnSad.tidt}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.created.mastertopic.tab"/></font>
					<font class="text12MediumBlue">[${recordTopicTvinnSad.titdn}]</font>
					<c:if test="${ recordTopicTvinnSad.tist == 'F' || recordTopicTvinnSad.tist == 'M' || empty recordTopicTvinnSad.tist}">
						<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_edit_items.do?action=doFetch&avd=${recordTopicTvinnSad.tiavd}&sign=${recordTopicTvinnSad.tisg}
											&opd=${recordTopicTvinnSad.titdn}&mrnNr=${recordTopicTvinnSad.titrnr}&godsNr=${recordTopicTvinnSad.tign}
											&status=${recordTopicTvinnSad.tist}&datum=${recordTopicTvinnSad.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.item.createnew.tab"/>
					</font>
					<c:if test="${ recordTopicTvinnSad.tist == 'F' || recordTopicTvinnSad.tist == 'M' || empty recordTopicTvinnSad.tist}">
						<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
					</c:if>
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">
					&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.unloading.createnew.tab"/>
				</font>
				<img style="vertical-align: bottom" src="resources/images/unloading.png" width="16" hight="16" border="0" alt="show log">
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_unloading_edit_items.do?action=doFetch&avd=${recordTopicTvinnSad.tiavd}&sign=${recordTopicTvinnSad.tisg}	
											&opd=${recordTopicTvinnSad.titdn}&mrnNr=${recordTopicTvinnSad.titrnr}&godsNr=${recordTopicTvinnSad.tign}
											&status=${recordTopicTvinnSad.tist}&datum=${recordTopicTvinnSad.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.unloading.item.createnew.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="item lines">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_logging.do?avd=${recordTopicTvinnSad.tiavd}&sign=${recordTopicTvinnSad.tisg}
									&opd=${recordTopicTvinnSad.titdn}&mrnNr=${recordTopicTvinnSad.titrnr}&godsNr=${recordTopicTvinnSad.tign}
									&status=${recordTopicTvinnSad.tist}&datum=${recordTopicTvinnSad.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_archive.do?avd=${recordTopicTvinnSad.tiavd}&sign=${recordTopicTvinnSad.tisg}
									&opd=${recordTopicTvinnSad.titdn}&mrnNr=${recordTopicTvinnSad.titrnr}&godsNr=${recordTopicTvinnSad.tign}
									&status=${recordTopicTvinnSad.tist}&datum=${recordTopicTvinnSad.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.archive.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
				</a>
			</td>
			<td width="13%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
		</tr>
	</table>
	</td>
 </tr>
 
 <tr>
 	<td>
	<%-- --------------------------- --%>	
 	<%-- tab area container PRIMARY  --%>
	<%-- --------------------------- --%>
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<tr height="3"><td colspan="2">&nbsp;</td></tr>
 		<%-- GENERAL HIDDEN --%> 
	    <input type="hidden" name="thmf" id="thmf" value="015">
			
		<%-- --- HIDDEN FORM FIELDS (not visible in form but important with an UPDATE ----- --%>			
		<%-- general (from user profile) --%>
		<input type="hidden" name="action" id="action" value='doUpdate'>
		<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
		<input type="hidden" name="opd" id="opd" value='${recordTopicTvinnSad.titdn}'>
		<%-- topic specific (syop and tuid) --%>
		<input type="hidden" name="tiavd" id="tiavd" value='${recordTopicTvinnSad.tiavd}'>
		<input type="hidden" name="titdn" id="titdn" value='${recordTopicTvinnSad.titdn}'>
		<input type="hidden" name="tisg" id="tisg" value='${recordTopicTvinnSad.tisg}'>
		<input type="hidden" name="tist" id="tist" value='${recordTopicTvinnSad.tist}'>
		<input type="hidden" name="tidt" id="tidt" value='${recordTopicTvinnSad.tidt}'>
    		<input type="hidden" name="avd" id="avd" value='${recordTopicTvinnSad.tiavd}'>
		<input type="hidden" name="sign" id="sign" value='${recordTopicTvinnSad.tisg}'>
		<tr >
			<td colspan="3" align="left" class="text12MediumBlue">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Avd:&nbsp;<b>${recordTopicTvinnSad.tiavd}</b>&nbsp;&nbsp;<span title="titdn">Tolldekl.:</span>&nbsp;<b>${recordTopicTvinnSad.titdn}</b>
			</td>
		</tr>
		<tr >
			<td colspan="3" align="left" class="text12MediumBlue">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title="titrnr">MRN-nr:</span>&nbsp;<b>${recordTopicTvinnSad.titrnr}</b>&nbsp;&nbsp;Gods-nr:&nbsp;<b>${recordTopicTvinnSad.tign}</b>
			</td>
		</tr>
		<tr height="3"><td>&nbsp;</td></tr>
		<tr >
			<td align="left" class="text11MediumBlue">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title="tisg">Sign:</span>&nbsp;<b>${recordTopicTvinnSad.tisg}</b>,&nbsp;&nbsp;<span title="tidt">Dato:</span>&nbsp;<b>${recordTopicTvinnSad.tidt}</b>
				&nbsp;
				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
           		<span title="tist">Status:</span>&nbsp;<b>${recordTopicTvinnSad.tist}</b>
				&nbsp;&nbsp;
				<span title="tienkl">Type prosedyre:</span>&nbsp;
				<c:if test="${recordTopicTvinnSad.tienkl == 'J'}"><b>Forenklet</b></c:if>
				<c:if test="${recordTopicTvinnSad.tienkl == 'N'}"><b>Normal</b></c:if>  
		
				<div class="text11" style="position: relative;" align="left">
				<span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
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

		<tr height="10"><td colspan="2">&nbsp;</td></tr>
		<%-- --------------- --%>
		<%-- CONTENT --%>
		<%-- --------------- --%>
		<tr>
		<td >
		<table align="center" width="98%" border="0" cellspacing="1" cellpadding="0">
		<%-- Other errors (none validation errors) --%>
		<c:if test="${not empty model.errorMessage}">
		<tr>
			<td width="5">&nbsp;</td>
			<td colspan="10">
            	<table align="left" border="0" cellspacing="0" cellpadding="0">
			 		<tr>
			 			<td class="textError">
			 				<ul>
                                   <li>
                                     	Fel vid uppdatering ${model.errorMessage}]  
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
					 			<td class="text12White">
					 				&nbsp;Næringsliv - Ansvarlig&nbsp;
				 				</td>
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
					 			<td class="text12"><input readonly type="text" class="inputTextMediumBlue" name="tikn" id="tikn" size="8" maxlength="8" value="${recordTopicTvinnSad.tikn}"></td>
					 			<td class="text12">&nbsp;</td>
			 				</tr>
			 				<tr >
					 			<td class="text12">&nbsp;<span title="titin">TIN</span></td>
					 			<td class="text12">&nbsp;<span title="tina">Navn</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text12"><input readonly type="text" class="inputTextMediumBlue" name="titin" id="titin" size="17" maxlength="17" value="${recordTopicTvinnSad.titin}"></td>
					 			<td class="text12"><input readonly type="text" class="inputTextMediumBlue" name="tina" id="tina" size="30" maxlength="30" value="${recordTopicTvinnSad.tina}"></td>
			 				</tr>
			 				<tr >
					 			<td class="text12">&nbsp;<span title="tiad1">Adresse</span></td>
					 			<td class="text12">&nbsp;<span title="tisk">Språkkode</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text12"><input readonly type="text" class="inputTextMediumBlue" name="tiad1" id="tiad1" size="30" maxlength="30" value="${recordTopicTvinnSad.tiad1}"></td>
					 			<td ><input readonly type="text" class="inputTextMediumBlue" name="tisk" id="tisk" size="2" maxlength="2" value="${recordTopicTvinnSad.tisk}"></td>
			 				</tr>
			 				<tr >
					 			<td class="text12">&nbsp;<span title="tips">Postadresse</span></td>
					 			<td class="text12">&nbsp;<span title="tipn">Postnr</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text12"><input readonly type="text" class="inputTextMediumBlue" name="tips" id="tips" size="24" maxlength="24" value="${recordTopicTvinnSad.tips}"></td>
					 			<td class="text12"><input readonly type="text" class="inputTextMediumBlue" name="tipn" id="tipn" size="9" maxlength="9" value="${recordTopicTvinnSad.tipn}"></td>
			 				</tr>
			 				<tr >
					 			<td class="text12">&nbsp;<span title="tilk">Landkode</span></td>
					 			<td class="text12">&nbsp</td>
			 				</tr>
			 				<tr >
			 					<td ><input readonly type="text" class="inputTextMediumBlue" name="tilk" id="tilk" size="2" maxlength="2" value="${recordTopicTvinnSad.tilk}"></td>
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
				            <td class="text12" ><span title="tign">Godsnr</span></td>
				            <td colspan="3" ><input readonly type="text" class="inputText" name="tign" id="tign" size="36" maxlength="35" value="${recordTopicTvinnSad.tign}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><span title="tignsk">Språkkode</span>&nbsp;</td>
				            <td ><input readonly type="text" class="inputText" name="tignsk" id="tignsk" size="2" maxlength="2" value="${recordTopicTvinnSad.tignsk}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><span title="titrnr">MRN-nr</span></td>
				            <td colspan="3"><input readonly type="text" class="inputText" name="titrnr" id="titrnr" size="36" maxlength="35" value="${recordTopicTvinnSad.titrnr}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><span title="tialk">Avs.land</span>&nbsp;</td>
				            <td ><input readonly type="text" class="inputText" name="tialk" id="tialk" size="2" maxlength="2" value="${recordTopicTvinnSad.tialk}"></td>
				            <td >&nbsp;</td>
			            </tr>
			            <tr height="2"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><span title="titsb">Freml.tollsted</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="titsb" id="titsb" size="10" maxlength="8" value="${recordTopicTvinnSad.titsb}"></td>
				            <td class="text12" ><span title="tiskb">Språkkode&nbsp;</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tiskb" id="tiskb" size="2" maxlength="2" value="${recordTopicTvinnSad.tiskb}"></td>
				            
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><span title="tidtf">Frigivelsesdato</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tidtf" id="tidtf" size="10" maxlength="8" value="${recordTopicTvinnSad.tidtf}"></td>
			            </tr>
			            
			            <tr height="5"><td></td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><span title="tinaa">Hovedans.</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tinaa" id="tinaa" size="20" maxlength="35" value="${model.record.tinaa}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><span title="tinas">Avsender</span>&nbsp;</td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tinas" id="tinas" size="20" maxlength="35" value="${model.record.tinas}"></td>
				            <td class="text12" ><span title="tinak">Mottaker</span>&nbsp;</td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tinak" id="tinak" size="20" maxlength="35" value="${model.record.tinak}"></td>
			            </tr>
			            
			            <tr height="10"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><span title="tialsk">Avt.lag.sted (kode)</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tialsk" id="tialsk" size="20" maxlength="17" value="${recordTopicTvinnSad.tialsk}"></td>
				            <td class="text12" ><span title="tialss">Språkkode</span>&nbsp;</td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tialss" id="tialss" size="2" maxlength="2" value="${recordTopicTvinnSad.tialss}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><span title="tials">Avt.lag.sted</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tials" id="tials" size="20" maxlength="35" value="${recordTopicTvinnSad.tials}"></td>
			            </tr>
			            <tr height="2"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><span title="tiglsk">Godkj.lag.sted (kode)</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tiglsk" id="tiglsk" size="20" maxlength="17" value="${recordTopicTvinnSad.tiglsk}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td class="text12" ><span title="tiacts">Contr.sted (kode)</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tiacts" id="tiacts" size="20" maxlength="17" value="${recordTopicTvinnSad.tiacts}"></td>
			            </tr>
			            
			            <tr height="2"><td>&nbsp;</td></tr>
			            </table>
				        </td>
			        </tr>
	            </table>
            </td>
		</tr>

		<tr height="15"><td colspan="2">&nbsp;</td></tr>
		
		
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
		
		<%-- ------------------------------------------ --%>
		<%-- SUB-SECTION that allows editable fields    --%>
		<%-- ------------------------------------------ --%>
	 	<tr >
		 	<td colspan="2">
			<table width="100%" class="formFrameHeader" border="0" cellspacing="1" cellpadding="0">
		 		<tr height="15">
		 			<td class="text12White">
		 				&nbsp;Lossemerknader&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
	 				</td>
 				</tr>
            </table>
            </td>
        </tr>
	 	<tr >
		 	<td width="50%">
				<table width="100%" class="formFrame" border="0" cellspacing="1" cellpadding="0">
			 		<tr height="15">
			 			<td class="text12">
			 				&nbsp;<span title="tivpos">Samlet antal varelinjer [NCTS]</span>
		 				</td>
			 			<td class="text12">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="tivpos" id="tivpos" size="5" maxlength="5" value="${model.record.tivpos}">
		 				</td>
	 				</tr>
			 		<tr height="15">
			 			<td class="text12">
			 				&nbsp;<span title="tintk">Samlet kollital [NCTS]</span>
		 				</td>
			 			<td class="text12">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="tintk" id="tintk" size="5" maxlength="4" value="${model.record.tintk}">
		 				</td>
	 				</tr>
			 		<tr height="15">
			 			<td class="text12">
			 				&nbsp;<span title="tivkb">Samlet bruttovekt [NCTS]</span>
		 				</td>
			 			<td class="text12">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="tivkb" id="tivkb" size="11" maxlength="11" value="${model.record.tivkb}">
		 				</td>
	 				</tr>
	 				<tr height="5"><td></td></tr>
	            </table>
            </td>
		 	<td width="50%">
				<table width="100%" class="formFrame" border="0" cellspacing="1" cellpadding="0">
			 		<tr height="15">
			 			<td class="text12">
			 				&nbsp;<span title="nivpos">Samlet antal varelinjer [CTL]</span>
		 				</td>
			 			<td class="text12">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="nivpos" id="nivpos" size="5" maxlength="5" value="${model.record.nivpos}">
		 				</td>
	 				</tr>
			 		<tr height="15">
			 			<td class="text12">
			 				&nbsp;<span title="nintk">Samlet kollital [CTL]</span>
		 				</td>
			 			<td class="text12">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="nintk" id="nintk" size="5" maxlength="4" value="${model.record.nintk}">
		 				</td>
	 				</tr>
			 		<tr height="15">
			 			<td class="text12">
			 				&nbsp;<span title="nivkb">Samlet bruttovekt [CTL]</span>
		 				</td>
			 			<td class="text12">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="nivkb" id="nivkb" size="11" maxlength="11" value="${model.record.nivkb}">
		 				</td>
	 				</tr>
	 				<tr height="5"><td></td></tr>
	            </table>
            </td>
        </tr>
        
        
		<tr>			
			<td colspan="2">
			<table class="formFrame" width="100%" align="left" border="0" cellspacing="1" cellpadding="0">
 				<tr height="5"><td></td></tr>
				<tr>
				<td width="50%" class="text12" valign="bottom">
					<table width="100%" align="left" border="0" cellspacing="1" cellpadding="0">
					 	<tr >
						 	<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0">
				 				<tr>		
									<td class="text12">&nbsp;
									<img onMouseOver="showPop('konform_info');" onMouseOut="hidePop('konform_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									<font class="text12Red" >*</font><span title="nikonf">Konform</span>
						 				<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="konform_info" class="popupWithInputText text11"  >
						           			<b>Konform</b>
											 <ul>
											 	<li><b>0</b> = Losses med merknad.</li>
											 	<li><b>1</b> = Losses uten merknad.</li>
											 </ul>
										</span>
										</div>
									</td>			 			
						 			<td class="text12">
										<select class="inputTextMediumBlueMandatoryField" name="nikonf" id="nikonf">
						            			<option value="0"<c:if test="${model.record.nikonf == '0' || empty model.record.nikonf}}"> selected </c:if> >0</option>
					 					  	<option value="1"<c:if test="${model.record.nikonf == '1'}"> selected </c:if> >1</option>
										</select>
									</td>
									<td class="text12">&nbsp;
									<img onMouseOver="showPop('afsluttet_info');" onMouseOut="hidePop('afsluttet_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									<font class="text12Red" >*</font><span title="nifulf">Fullført</span>
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="afsluttet_info" class="popupWithInputText text11"  >
						           			<b>Avsluttet</b>
											 <ul>
											 	<li><b>Nej</b> = Lossning er ikke avsluttet.</li>
											 	<li><b>Ja</b> = Lossning er avsluttet.</li>
											 </ul>
										</span>
										</div>
									</td>
						 			<td class="text12">
										<select class="inputTextMediumBlueMandatoryField" name="nifulf" id="nifulf">
					            			<option value=""<c:if test="${model.record.nifulf == '0' || empty model.record.nifulf}"> selected </c:if> >Nej</option>
					            			<option value="1"<c:if test="${model.record.nifulf == '1'}"> selected </c:if> >Ja</option>
										</select>
						 			</td>
						 			<td class="text12">&nbsp;<font class="text12Red" >*</font><span title="nidtl">Losse dato</span></td>
						 			<td class="text12">
						 				<input type="text" class="inputTextMediumBlueMandatoryField" name="nidtl" id="nidtl" size="9" maxlength="6" value="${model.record.nidtl}">
					 				</td>
						 		</tr>
						 	</table>
						 	</td>	
						 </tr>
						 <tr height="5"><td></td></tr>
						 <tr>
						 	<td>
						 	<table class="tableBorderWithRoundCornersGray" width="100%" border="0" cellspacing="1" cellpadding="0">		
				 				<tr >
						 			<td colspan="2" class="text12">&nbsp;<b>CTL resultat(OT/DI)</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span title="nictsk">Språkkode</span>&nbsp;
							            <select name="nictsk" id="nictsk">
							            		<option value="">-velg-</option>
							 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
						                             <option value="${code.tkkode}"<c:if test="${model.record.nictsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
										</select>
										<a tabindex="-1" id="nictskIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
										</a>
					            		</td>					 			
						 		</tr>
						 		<tr>	
						 			<td class="text12" >&nbsp;&nbsp;
						 			<img onMouseOver="showPop('control_OT_code_info');" onMouseOut="hidePop('control_OT_code_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 			<span title="nictb">OT:</span>&nbsp;
						 				<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="control_OT_code_info" class="popupWithInputText text11"  >
							           			<br/>
							           			<b>CTL resultat</b>
												<br/><br/>
												 (Hvis losse resultat = 0)
												 <ul>
												 	<li><b>DI</b> = Forskjellen i verdi.</li>
												 	<li><b>OT</b> = Andre ting å rapportere</li>
												 </ul>
										</span>
										</div>						 			
						 			</td>
						 			<td class="text12">
										<input type="text" class="inputTextMediumBlue" name="nictb" id="nictb" size="40" maxlength="70" value="${model.record.nictb}">
									</td>
								</tr>
								<tr>	
						 			<td class="text12" >&nbsp;&nbsp;
						 			<img onMouseOver="showPop('control_DI_code_info');" onMouseOut="hidePop('control_DI_code_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 			<span title="nictb2">DI:</span>
						 				<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="control_DI_code_info" class="popupWithInputText text11"  >
							           			<br/>
							           			<b>CTL resultat</b>
												<br/><br/>
												 (Hvis losse resultat = 0)
												 <ul>
												 	<li><b>DI</b> = Forskjellen i verdi.</li>
												 	<li><b>OT</b> = Andre ting å rapportere</li>
												 </ul>
										</span>	
										</div>					 			
						 			</td>
						 			<td class="text12">
										<input type="text" class="inputTextMediumBlue" name="nictb2" id="nictb2" size="40" maxlength="70" value="${model.record.nictb2}">
									</td>
								</tr>
								<tr>	
						 			<td class="text12" >&nbsp;&nbsp;
						 			<img onMouseOver="showPop('pointer_info');" onMouseOut="hidePop('pointer_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 			<span title="nictp">Peker:</span>
						 				<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="pointer_info" class="popupWithInputText text11"  >
							           			<br/>
							           			<b>Peker</b>
												<p>
													Indtast rubrikknr ledsagedokumentet med fejl. Hvis der er afvigelser i den sidste post, pakker eller vægt, automatisk sendes point og kode.
												</p>
										</span>	
										</div>					 			
						 			</td>
						 			<td class="text12">
										<input type="text" class="inputTextMediumBlue" name="nictp" id="nictp" size="30" maxlength="35" value="${model.record.nictp}">
									</td>
								</tr>
								<tr>	
						 			<td class="text12" >&nbsp;&nbsp;
						 			<img onMouseOver="showPop('correctedValue_info');" onMouseOut="hidePop('correctedValue_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 			<span title="nictnv">Korrigeret verdi:</span>
						 				<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="correctedValue_info" class="popupWithInputText text11"  >
							           			<br/>
							           			<b>Korrigeret verdi</b>
												<p>
													Indtast afvigende værdien i feltet er angivet i <b>peker</b>.
												</p>
										</span>	
										</div>					 			
						 			</td>
						 			<td class="text12">
										<input type="text" class="inputTextMediumBlue" name="nictnv" id="nictnv" size="20" maxlength="15" value="${model.record.nictnv}">
									</td>
								</tr>
								<%-- finns på grön skärm men används inte längre. Vi gömmer dessa här tills vidare besked
								<tr>	
									<td class="text12" >&nbsp;&nbsp;&nbsp;<span title="nictp">Pekare:</span>&nbsp;</td>
						 			<td class="text12">
										<input type="text" class="inputTextMediumBlue" name="nictp" id="nictp" size="6" maxlength="5" value="${model.record.nictp}">
										&nbsp;<span title="nictnv">Nytt värde:</span>&nbsp;
										<input type="text" class="inputTextMediumBlue" name="nictnv" id="nictnv" size="16" maxlength="15" value="${model.record.nictnv}">
									</td>
						 		</tr>
						 		 --%>
						 		<tr height="5"><td></td></tr>
				            </table>
				            </td>
			            </tr>
					 	<tr height="5"><td></td></tr>
						<tr>
						 	<td>
						 	<table class="tableBorderWithRoundCornersGray" width="100%" border="0" cellspacing="1" cellpadding="0">		
				 				<tr >
						 			<td colspan="2" class="text12">&nbsp;<span title""><b>Forsegling</b></span></td>					 			
						 		</tr>
						 		<tr>	
						 			<td class="text12" >&nbsp;&nbsp;&nbsp;<span title="nidfst">Status:</span>&nbsp;</td>
						 			<td class="text12">
					           			<select class="text11" name="nidfst" id="nidfst">
					           				<option value="">-velg-</option>
					           				<option value="0"<c:if test="${model.record.nidfst == '0'}"> selected </c:if> >Ikke Ok</option>
					           				<option value="1"<c:if test="${model.record.nidfst == '1'}"> selected </c:if> >Ok</option>
										  	
										</select>
									</td>
								</tr>
								<tr>	
						 			<td class="text12" >&nbsp;&nbsp;&nbsp;<span title="nidant">Antall:</span>&nbsp;</td>
						 			<td class="text12">
										<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="nidant" id="nidant" size="4" maxlength="4" value="${model.record.nidant}">
									</td>
								</tr>
								<tr>	
									<td class="text12" >&nbsp;&nbsp;&nbsp;<span title="nidfkd">Id:</span>&nbsp;</td>
						 			<td class="text12">
										<input type="text" class="inputTextMediumBlue" name="nidfkd" id="nidfkd" size="20" maxlength="20" value="${model.record.nidfkd}">
									</td>
						 		</tr>
						 		<tr>
						 			<td class="text12" >
						 				<span title="nidfsk">&nbsp;&nbsp;&nbsp;Språkkode</span>&nbsp;
						 			</td>
						 			<td 	class="text12" >
							            <select name="nidfsk" id="nidfsk">
							            		<option value="">-velg-</option>
							 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
						                             <option value="${code.tkkode}"<c:if test="${model.record.nidfsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
										</select>
										<a tabindex="-1" id="nidfskIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
										</a>
						 			</td>
						 		</tr>
						 		<tr height="5"><td></td></tr>
				            </table>
				            </td>
		 				    
			            </tr>
			            
		            </table>
	            	</td>
				<td width="50%" class="text12" valign="Top">
					<table width="100%" align="left" border="0" cellspacing="1" cellpadding="0">
						 <tr height="35"><td>&nbsp;</td></tr>
						 <tr>
						 	<td >
						 	<table class="tableBorderWithRoundCornersGray" width="85%" border="0" cellspacing="1" cellpadding="0">
								
								<tr>	
						 			<td class="text12">&nbsp;&nbsp;&nbsp;&nbsp;<b><span title="nimn1/nimn2">Merknader</span></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			<span title="nimnsk">Språkkode</span>&nbsp;
							            <select name="nimnsk" id="nimnsk">
							            		<option value="">-velg-</option>
							 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
						                             <option value="${code.tkkode}"<c:if test="${model.record.nimnsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
										</select>
										<a tabindex="-1" id="nimnskIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
										</a>
					            		</td>		
								</tr>
								<tr>	
						 			<td class="text12"><span title="nimn1">&nbsp;&nbsp;</span>
										<input type="text" class="inputTextMediumBlue" name="nimn1" id="nimn1" size="70" maxlength="70" value="${model.record.nimn1}">
									</td>
								</tr>
								<tr>	
						 			<td class="text12"><span title="nimn2">&nbsp;&nbsp;</span>
										<input type="text" class="inputTextMediumBlue" name="nimn2" id="nimn2" size="70" maxlength="70" value="${model.record.nimn2}">
									</td>
						 		</tr>
						 		<tr height="5"><td></td></tr>
				            </table>
				            </td>
			            </tr>
			            <tr height="5"><td></td></tr>
			            <tr>
			            		<td align="center" >
						 	<table>
						 		<tr>
					            		<%-- only status = U,H are allowed  --%>
				 				    <c:choose>
					 				    <c:when test="${ recordTopicTvinnSad.tist == 'U' || recordTopicTvinnSad.tist == 'H' }">
						 				    <td class="text9BlueGreen" valign="bottom"  >
							 				    &nbsp;<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='tvinnsadnctsimport_unloading_edit.do';" value="<spring:message code="systema.tvinn.sad.ncts.import.unloading.createnew.submit"/>"/>
							 				
							 					<%-- NOTE: we use the same routine as for the Topic ... --%>
						 				    		<input tabindex=-1 class="inputFormSubmit" type="submit" name="send" onclick="javascript: form.action='tvinnsadnctsimport_unloading_send.do';" value='<spring:message code="systema.tvinn.sad.ncts.import.unloading.createnew.send"/>'/>
						 				    	</td>	
					 				    </c:when>
					 				    <c:otherwise>
						 				    <td  align="center" class="text9BlueGreen" valign="bottom"  >
						 				    		&nbsp;&nbsp;&nbsp;<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value="<spring:message code="systema.tvinn.sad.submit.not.editable"/>"/>
						 				    	</td>	
					 				    </c:otherwise>	
				 				    </c:choose>
		 				    		</tr>
		 				    </table>
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
</td>
</tr>
</table>
</form>	