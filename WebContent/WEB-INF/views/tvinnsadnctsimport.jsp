<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->
<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadnctsimport.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.tab"/></font>
				<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
				&nbsp;&nbsp;${listSize}
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a style="display:block;" href="tvinnsadnctsimport_edit.do?action=doPrepareCreate&user=${user.user}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.createnew.tab"/></font>
					<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
				</a>
			</td>
			<td width="60%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
		</tr>
	</table>
	</td>
</tr>

<tr>
	<td>
	<%-- search filter component --%>
		
 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 	        <tr height="3"><td></td></tr>
 	        <form name="nctsImportSearchForm" id="searchForm" action="tvinnsadnctsimport?action=doFind" method="post" >
 	        <tr>	
                <td class="text12" align="left" title="avd" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.avd"/></td>
                <td class="text12" align="left" title="sign" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.signatur"/></td>
                <td class="text12" align="left" title="opd" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.arende"/></td>
                <td class="text12" align="left" title="mrnNr" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.mrnNr"/></td>
                <td class="text12" align="left" >
				<img onMouseOver="showPop('datum_info');" onMouseOut="hidePop('datum_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                <span title="datum"><spring:message code="systema.tvinn.sad.ncts.import.list.search.label.fdatum"/></span>
               	<div class="text11" style="position: relative;" align="left">
				<span style="position:absolute;top:2px; width:250px;" id="datum_info" class="popupWithInputText text11"  >
	           		Standardsøg (blank dato) gælder <b> 7 dager bagud </b> på det tidspunkt. 
	           		<br/>
					Hvis du ønsker at se længere tilbage i tiden, type, skal du angive fra dato. <br/>
					For eksempel 20131001 leder efter en 1-Okt-2013 til i dag.
				</span>	
				</div>
                </td>
                <td class="text12" align="left" ><span title="datumt"><spring:message code="systema.tvinn.sad.ncts.import.list.search.label.tdatum"/></span></td>
                <td class="text12" align="left" >
				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                <span title="status"><spring:message code="systema.tvinn.sad.ncts.import.list.search.label.status"/></span>
                <div class="text11" style="position: relative;" align="left">
				<span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
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
                <td class="text12" align="left" title="forenklad" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.type"/></td>
                <td class="text12" align="left" title="ansNavn" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.ansvarig"/></td>
                <td class="text12" align="left" title="godsNr" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.godsnr"/></td>
                <td class="text12" align="left" title="datumFr" ><img onMouseOver="showPop('datumFr_info');" onMouseOut="hidePop('datumFr_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                	<span title="datumFr"><spring:message code="systema.tvinn.sad.ncts.import.list.search.label.frigivningsDatum"/></span>
                	<div class="text11" style="position: relative;" align="left">
					<span style="position:absolute;top:2px; width:200px;" id="datumFr_info" class="popupWithInputText text11"  >
	           		Frigivningsdatum
	           		</span>	
					</div>
                </td>
                <td>&nbsp;</td>
			</tr>
 	        <tr>
				<td align="left" >&nbsp;
           			<select name="avd" id="avd">
	            		<option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                        	 	<option value="${record.avd}"<c:if test="${searchFilterSadImportNcts.avd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>                       	 	
						</c:forEach> 
					</select>
				</td>
				<td align="left" >
           			<select name="sign" id="sign">
	            		<option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${searchFilterSadImportNcts.sign == record.sign}"> selected </c:if> >${record.sign}</option>
						</c:forEach> 
					</select>
				</td>
				<td align="left" ><input type="text" class="inputText" name="opd" id="opd" size="8" maxlength="10" value="${searchFilterSadImportNcts.opd}">&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="mrnNr" id="mrnNr" size="19" maxlength="18" value="${searchFilterSadImportNcts.mrnNr}">&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datum" id="datum" size="6" maxlength="6" value="${searchFilterSadImportNcts.datum}">&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datumt" id="datumt" size="6" maxlength="6" value="${searchFilterSadImportNcts.datumt}">&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="status" id="status" size="2" maxlength="1" value="${searchFilterSadImportNcts.status}">&nbsp;</td>
				<td align="left" >
					<select name="forenklad" id="forenklad">
		            		<option value="">-velg-</option>
		            		<option value="J" <c:if test="${searchFilterSadImportNcts.forenklad == 'J'}"> selected </c:if> >Forenklet</option>
		            		<option value="N" <c:if test="${searchFilterSadImportNcts.forenklad == 'N'}"> selected </c:if> >Normal</option>
					</select>
				</td>
				<td align="left" ><input type="text" class="inputText" name="ansNavn" id="ansNavn" size="12" maxlength="50" value="${searchFilterSadImportNcts.ansNavn}">&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="godsNr" id="godsNr" size="19" maxlength="35" value="${searchFilterSadImportNcts.godsNr}">&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datumFr" id="datumFr" size="6" maxlength="6" value="${searchFilterSadImportNcts.datumFr}">&nbsp;</td>
				<td valign="top" align="left" >
                   &nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.tvinn.sad.search"/>'>
                   
                </td>
			</tr>
			</form>
			<tr height="10"><td></td></tr>
		</table>
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
	<%-- list component --%>
	<c:if test="${not empty list}">
	<tr>
		<td>		
		<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    	<%-- separator --%>
	        <tr height="1"><td></td></tr> 
			<tr>
				<td>
				<table width="100%" cellspacing="0" border="0" cellpadding="0">
					<tr class="tableHeaderField" height="20" valign="left">
                    <td class="tableHeaderFieldFirst" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.avd"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.signatur"/></td>
                		<td class="tableHeaderField" align="center" >&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.update"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.arende"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.mrnNr"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.datum"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.status"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.type"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.ansvarig"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.godsnr"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.search.label.frigivningsDatum"/></td>
                		<%--
                		<td class="tableHeaderField">&nbsp;Kopiera Ärende&nbsp;</td>
	                 --%>    
                	</tr> 
		           	<c:forEach items="${list}" var="record" varStatus="counter">    
		               <c:choose>           
		                   <c:when test="${counter.count%2==0}">
		                       <tr class="tableRow" height="20" >
		                   </c:when>
		                   <c:otherwise>   
		                       <tr class="tableOddRow" height="20" >
		                   </c:otherwise>
		               </c:choose>
		               <td class="tableCellFirst" width="5%">&nbsp;${record.avd}</td>
		               <td class="tableCell" >&nbsp;${record.sign}</td>
		               <td class="tableCell" align="center" >
		               		<c:choose>
		               		<c:when test="${empty record.status || record.status == 'M' || record.status == 'F'}">
	              	   	   		<span title="Angivelse er opdaterbar"></span>
	            	   	   			<a href="tvinnsadnctsimport_edit.do?action=doFetch&avd=${record.avd}&opd=${record.opd}&status=${record.status}">
	               					<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
	               				</a>
               				</c:when>
               				<c:otherwise>
               					<c:choose>
	               					<c:when test="${record.status == 'U' || record.status == 'H'}">
		              	   	   			<span title="Losning er opdaterbar">
		              	   	   			<a href="tvinnsadnctsimport_unloading_edit.do?action=doFetch&origo=list&avd=${record.avd}&sign=${record.sign}&opd=${record.opd}&status=${record.status}&datum=${record.datum}">
											<img valign="bottom" src="resources/images/unloading.png" width="16" hight="16" border="0" alt="lossning - editable">					
										</a>
		              	   	   			</span>
		               				</c:when>
		               				<c:otherwise>
												               				
		               				</c:otherwise>
	               				</c:choose>									
               				</c:otherwise>
	               			</c:choose>
	               	   </td>
               		   <td class="tableCell" width="10%" >&nbsp;
               		   		<c:choose>
		               		<c:when test="${empty record.status || record.status == 'M' ||  record.status == 'F'}">
	              	   	   		<span title="Angivelse er opdaterbar"></span>
	            	   	   			<a href="tvinnsadnctsimport_edit.do?action=doFetch&avd=${record.avd}&opd=${record.opd}&status=${record.status}">
	               					&nbsp;&nbsp;${record.opd}
	               				</a>
               				</c:when>
               				<c:otherwise>
               					<c:choose>
	               					<c:when test="${record.status == 'U' || record.status == 'H'}">
		              	   	   			<span title="Lossning är uppdaterbar">
		              	   	   			<a href="tvinnsadnctsimport_unloading_edit.do?action=doFetch&origo=list&avd=${record.avd}&sign=${record.sign}&opd=${record.opd}&status=${record.status}&datum=${record.datum}">
											&nbsp;&nbsp;${record.opd}
										</a>
		              	   	   			</span>
		               				</c:when>
		               				<c:otherwise>
										<a href="tvinnsadnctsimport_edit.do?action=doFetch&avd=${record.avd}&opd=${record.opd}&status=${record.status}">
											&nbsp;&nbsp;${record.opd}
										</a>		               				
		               				</c:otherwise>
	               				</c:choose>									
               				</c:otherwise>
	               			</c:choose>
               		   </td>
		               <td class="tableCell" >&nbsp;${record.mrnNr}</td>
		               <td class="tableCell" >&nbsp;${record.datum}</td>
		               <td class="tableCell" >&nbsp;<b>${record.status}</b></td>
		               <td class="tableCell" >&nbsp;
               				<c:if test="${record.forenklad == 'J'}">Forenklet</c:if>
		               		<c:if test="${record.forenklad == 'N'}">Normal</c:if>
		               </td>
		               <td class="tableCell" >&nbsp;${record.ansNavn}</td>
		               <td class="tableCell" >&nbsp;${record.godsNr}</td>
		               <td class="tableCell" >&nbsp;${record.datumFr}</td>
		            </tr> 
		            </c:forEach>
	            </table>
			</td>	
			</tr>
		</table>
		</td>
		</tr>
    </c:if> 
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

