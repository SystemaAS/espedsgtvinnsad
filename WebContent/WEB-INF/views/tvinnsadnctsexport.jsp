<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->
<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadnctsexport.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
				<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.tab"/></font>
				<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
				&nbsp;&nbsp;${listSize}
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkHeaderNew" style="display:block;" href="tvinnsadnctsexport_edit.do?action=doPrepareCreate&user=${user.user}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.createnew.tab"/></font>
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
 	        <form name="tvinnSadNctsExportSearchForm" id="searchForm" action="tvinnsadnctsexport?action=doFind" method="post" >
 	        <tr>	
                <td class="text12" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.search.label.avd"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.search.label.signatur"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.search.label.arende"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.search.label.lrnNr"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.search.label.mrnNr"/></td>
                <td class="text12" align="left" >
				<img onMouseOver="showPop('datum_info');" onMouseOut="hidePop('datum_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                <spring:message code="systema.tvinn.sad.ncts.export.list.search.label.fdatum"/>
                <div class="text11" style="position: relative;" align="left">
					<span style="position:absolute;top:2px; width:250px;" id="datum_info" class="popupWithInputText text11"  >
	           		Standardsøg (blank dato) gælder <b> 7 dager bagud </b> på det tidspunkt. 
	           		<br/>
					Hvis du ønsker at se længere tilbage i tiden, type, skal du angive fra dato. <br/>
					For eksempel 20131001 leder efter en 1-Okt-2013 til i dag.
	           		</span>	
				</div>
                </td>
                <td class="text12" align="left" ><span title="datumt"><spring:message code="systema.tvinn.sad.ncts.export.list.search.label.tdatum"/></span></td>
                <td class="text12" align="left" >
				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                <spring:message code="systema.tvinn.sad.ncts.export.list.search.label.status"/>
                	<div class="text11" style="position: relative;" align="left">
					<span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
		           		Kun status <b>M</b>, <b>(Fejl)</b> eller <b>' '</b> kan redigeres.
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
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.search.label.mottagare"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.search.label.bruttovikt"/></td>
                <td>&nbsp;</td>
			</tr>
 	        <tr>
				<td align="left" >&nbsp;
           			<select name="avd" id="avd">
	            		<option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                            <option value="${record.avd}"<c:if test="${searchFilterSadExportNcts.avd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>                       	 	
						</c:forEach> 
					</select>
				</td>
				<td align="left" >
           			<select name="sign" id="sign">
	            		<option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${searchFilterSadExportNcts.sign == record.sign}"> selected </c:if> >${record.sign}</option>
						</c:forEach> 
					</select>
				</td>
				<td align="left" ><input type="text" class="inputText" name="opd" id="opd" size="8" maxlength="7" value='${searchFilterSadExportNcts.opd}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="lrnNr" id="lrnNr" size="14" maxlength="35" value='${searchFilterSadExportNcts.lrnNr}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="mrnNr" id="mrnNr" size="14" maxlength="35" value='${searchFilterSadExportNcts.mrnNr}'>&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datum" id="datum" size="7" maxlength="6" value='${searchFilterSadExportNcts.datum}'>&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datumt" id="datumt" size="7" maxlength="6" value='${searchFilterSadExportNcts.datumt}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="status" id="status" size="1" maxlength="1" value='${searchFilterSadExportNcts.status}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="motNavn" id="motNavn" size="10" maxlength="50" value='${searchFilterSadExportNcts.motNavn}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="bruttoVikt" id="bruttoVikt" size="10" maxlength="50" value='${searchFilterSadExportNcts.bruttoVikt}'>&nbsp;</td>
				
				<td valign="top" align="left" >
                   &nbsp;<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.tvinn.sad.search"/>'>
                   
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
	                    <td class="tableHeaderFieldFirst" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.search.label.avd"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.search.label.signatur"/></td>
                		<td class="tableHeaderField" align="center" >&nbsp;<spring:message code="systema.tvinn.sad.export.list.search.label.update"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.search.label.arende"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.search.label.lrnNr"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.search.label.mrnNr"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.search.label.datum"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.search.label.status"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.search.label.mottagare"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.search.label.bruttovikt"/></td>
                		<%--
                		<td class="tableHeaderField">&nbsp;Kopiera Ärende&nbsp;</td>
	                     --%>
	                     
                	</tr>     
		           	<c:forEach items="${list}" var="topic" varStatus="counter">    
		               <c:choose>           
		                   <c:when test="${counter.count%2==0}">
		                       <tr class="tableRow" height="20" >
		                   </c:when>
		                   <c:otherwise>   
		                       <tr class="tableOddRow" height="20" >
		                   </c:otherwise>
		               </c:choose>
		               <td class="tableCellFirst" width="5%">&nbsp;${topic.avd}</td>
		               <td class="tableCell" >&nbsp;${topic.sign}</td>
		               <td class="tableCell" align="center" >
              	   	   		<c:if test="${empty topic.status || topic.status=='M' ||  topic.status=='G' ||  topic.status=='F'}">
              	   	   			<%-- only M, null or G (garantifel) are editable in NCTS --%>
              	   	   			<a id="alinkHeader" href="tvinnsadnctsexport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&sysg=${topic.sign}&tuid=${topic.lrnNr}&syst=${topic.status}&sydt=${topic.datum}">
	               					<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
	               				</a>	
	               			</c:if>
	               	   </td>
               		   <td class="tableCell" width="10%" >&nbsp;
	               	   		<a id="alinkHeader" href="tvinnsadnctsexport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&sysg=${topic.sign}&tuid=${topic.lrnNr}&syst=${topic.status}&sydt=${topic.datum}">
	               	   			&nbsp;&nbsp;${topic.opd}
	               			</a>
               		   </td>
		               <td class="tableCell" >&nbsp;${topic.lrnNr}</td>
		               <td class="tableCell" >&nbsp;${topic.mrnNr}</td>
		               <td class="tableCell" >&nbsp;${topic.datum}</td>
		               <td class="tableCell" >&nbsp;<b>${topic.status}</b></td>
		               <td class="tableCell" >&nbsp;${topic.motNavn}</td>
		               <td class="tableCell" >&nbsp;${topic.bruttoVikt}</td>
    		           <%--
    		           <td class="tableCell" width="10%">&nbsp;
		               		
		               		<a class="copyLink" id="copyLink${counter.count}" runat="server" href="#">
								<img src="resources/images/copy.png" border="0" alt="copy">
								&nbsp;Kopiere
							</a>
							 
							<div style="display: none;" class="clazz_dialog" id="dialog${counter.count}" title="Dialog">
								<form  action="tvinnsadnctsexport_copyTopic.do" name="copyForm${counter.count}" id="copyForm${counter.count}" method="post">
								 	<input type="hidden" name="action${counter.count}" id="action${counter.count}" value='doFetch'/>
									<input type="hidden" name="originalAvd${counter.count}" id="originalAvd${counter.count}" value='${topic.avd}'/>
				 					<input type="hidden" name="originalOpd${counter.count}" id="originalOpd${counter.count}" value='${topic.opd}'/>
					 					
									<p class="text12" >Du skal velga nye&nbsp;<code>Afdeling</code>&nbsp;og nye&nbsp;
										<code>Signatur</code> or at kunne kopiere en angivelse
									</p>
									<p class="text12" >Et nyt angivelsesnummer vil blive oprettet automatisk.
									</p>
									
									<table>
										<tr>
											<td class="text12" align="left" >&nbsp;Afdeling</td>
	                							<td class="text12" align="left" >&nbsp;Signatur</td>
	                						</tr>
	 									<tr>
											<td class="text12MediumBlue">
												<select class="newAvd" name="newAvd${counter.count}" id="newAvd${counter.count}">
								            		<option value="">-velg-</option>
								 				  	<c:forEach var="record" items="${model.avdList}" >
							                             	 	<option value="${record.avd}">${record.avd}</option>
													</c:forEach> 
												</select>
											</td>
											<td class="text12MediumBlue">
												<select class="newSign" name="newSign${counter.count}" id="newSign${counter.count}">
								            		<option value="">-velg-</option>
								 				  	<c:forEach var="record" items="${model.signList}" >
							                             	 	<option value="${record.sign}">${record.sign}</option>
													</c:forEach> 
												</select>
											</td>
										</tr>
									</table>
								</form>
							</div>
							
		               </td>
		                --%>
		               
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

