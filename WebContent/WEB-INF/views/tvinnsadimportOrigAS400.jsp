<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadimport.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
		.ui-dialog{font-size:10pt;}
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
				<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.import.list.tab"/></font>
				<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
				&nbsp;&nbsp;${listSize}
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a style="display:block;" id="copyFromTransportUppdragLink" runat="server" href="#">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.import.createnew.tab"/></font>
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
		<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
		
 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 	        <tr height="3"><td></td></tr>
 	        <form name="tvinnsadImportSearchForm" id="searchForm" action="tvinnsadimport?action=doFind" method="post" >
 	        
 	        <tr>	
                <td class="text14" align="left" title="avd">&nbsp;&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.avd"/></td>
                <td class="text14" align="left" title="sg">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.signatur"/></td>
                <td class="text14" align="left" title="opd">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.arende"/>
                <td class="text14" align="left" title="h_xref">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.extrefnr"/></td>
                		<%--
                		<a href="tvinnsadimport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&sign=${topic.sg}">
            				temp
    					</a>
    					--%>
                </td>
                <td class="text14" align="left" title="datum">
				<img onMouseOver="showPop('datum_info');" onMouseOut="hidePop('datum_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                <spring:message code="systema.tvinn.sad.import.list.search.label.fdatum"/>
                <div class="text11" style="position: relative;" align="left">
                <span style="position:absolute;top:2px; width:250px;" id="datum_info" class="popupWithInputText text11"  >
                	Fra Dato
                	<br/>
	           		Standardsøk (blank dato) er <b>7 dager på etterskudd</b> på den tiden. 
	           		<br/>
					Hvis du ønsker å se lenger tilbake i tid, angi datoen.<br/>
					For eksempel 010116 på utkikk etter en 1-Jan-2016 til i dag. 
				</span>	
				</div>
                </td>
                <td class="text14" align="left" title="datumt"><spring:message code="systema.tvinn.sad.import.list.search.label.tdatum"/></td>
                <td class="text14" align="left" title="sitle">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.lopenr"/></td>
                
                <td class="text14" align="left" title="status">
                	<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
	            	<spring:message code="systema.tvinn.sad.import.list.search.label.status"/>
                	<div class="text11" style="position: relative;" align="left">
	                <span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
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
		           				<li><b>S</b>&nbsp;SLETTET</li>
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
                <td class="text14" align="left" title="avsNavn">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.avsandare"/></td>
                <td class="text14" align="left" title="motNavn">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.mottagare"/></td>
                <td class="text14" align="left" title="sign">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.godsnr"/></td>
                <td class="text14" align="left" title="simi">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.innstikk"/></td>
                <td class="text14" align="left" title="inv">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.faknr"/></td>
                <td class="text14" align="left" title="r31">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.r31"/></td>
                <td class="text14" align="left" title="eta">&nbsp;&nbsp;
                <img onMouseOver="showPop('eta_info');" onMouseOut="hidePop('eta_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
	            	<spring:message code="systema.tvinn.sad.import.list.search.label.eta"/>
                	<div class="text11" style="position: relative;" align="left">
	                <span style="position:absolute;top:2px; width:250px;" id="eta_info" class="popupWithInputText text11"  >
			           		<b>ETA: Estimated Time of Arrival</b>
		           		 	<p>Bare status <b>T</b>(manuell ekspedering hos Tollvesenet) vises.</p> 
					</span>	
					</div>
                
                </td>
                
                <td>&nbsp;</td>
			</tr>
 	        <tr>
				<td align="left" >&nbsp;
           			<select class="selectMediumBlueE2" name="avd" id="avd">
	            		<option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                        	 	<option value="${record.avd}"<c:if test="${searchFilterSadImport.avd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst== '2'}">&nbsp;(test)</c:if></option>
						</c:forEach> 
					</select>
				</td>
				<td align="left" >
           			<select class="selectMediumBlueE2" name="sg" id="sg">
	            		<option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"
                             	 		<c:if test="${searchFilterSadImport.sg == record.sign}"> selected </c:if> >
                             	 		${record.sign}</option>
						</c:forEach> 
					</select>
				</td>
				<td align="left" ><input type="text" class="inputText" name="opd" id="opd" size="8" maxlength="7" value='${searchFilterSadImport.opd}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="xref" id="xref" size="10" maxlength="35" value='${searchFilterSadImport.xref}'>&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datum" id="datum" size="7" maxlength="6" value='${searchFilterSadImport.datum}'>&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datumt" id="datumt" size="7" maxlength="6" value='${searchFilterSadImport.datumt}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="sitll" id="sitll" size="10" maxlength="10" value='${searchFilterSadImport.sitll}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="status" id="status" size="1" maxlength="1" value='${searchFilterSadImport.status}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="avsNavn" id="avsNavn" size="9" maxlength="35" value='${searchFilterSadImport.avsNavn}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="motNavn" id="motNavn" size="9" maxlength="35" value='${searchFilterSadImport.motNavn}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="godsnr" id="godsnr" size="10" maxlength="15" value='${searchFilterSadImport.godsnr}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="innstikk" id="innstikk" size="1" maxlength="1" value='${searchFilterSadImport.innstikk}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="inv" id="inv" size="10" maxlength="17" value='${searchFilterSadImport.inv}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="r31" id="r31" size="10" maxlength="30" value='${searchFilterSadImport.r31}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="eta" id="eta" size="10" maxlength="30" value='${searchFilterSadImport.eta}'>&nbsp;</td>
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
		<td width="100%" >
	     <table width="100%" >
	     	<tr >
			<td class="text14" align="right">
	        		<a href="tvinnsadImportMainListExcelView.do" target="_blank">
               		<img id="itemListExcel" src="resources/images/excel.png" border="0" alt="excel">&nbsp;Excel
	        		</a>&nbsp;
	        	</td>	
		</tr>
		</table>
		</td>
	</tr>
	
	
	<tr>
		<td>		
		<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    	<%-- separator --%>
	        <tr height="1"><td></td></tr> 
			<tr>
				<td>
				
				<table id="containerdatatableTable" width="100%" cellspacing="2" align="left" >
				<tr>
				<td class="text11">
							
				
				<table id="mainList" class="display compact cell-border" >
					<thead>
					<tr class="tableHeaderField" height="20" >
	                    <th class="tableHeaderFieldFirst"><spring:message code="systema.tvinn.sad.import.list.search.label.avd"/></th>   
	                    <th class="tableHeaderField" nowrap><spring:message code="systema.tvinn.sad.import.list.search.label.signatur"/></th>
	                    <th class="tableHeaderField" nowrap><spring:message code="systema.tvinn.sad.import.list.search.label.update"/></th>
	                    <th class="tableHeaderField"><spring:message code="systema.tvinn.sad.import.list.search.label.arende"/></th>
	                    <th class="tableHeaderField" nowrap><spring:message code="systema.tvinn.sad.import.list.search.label.extrefnr"/></th>
	                    <th class="tableHeaderField" nowrap><spring:message code="systema.tvinn.sad.import.list.search.label.datum"/></th>
	                    <th class="tableHeaderField" nowrap><spring:message code="systema.tvinn.sad.import.list.search.label.lopenr"/></th>
	                    <th class="tableHeaderField" nowrap><spring:message code="systema.tvinn.sad.import.list.search.label.ekspnr"/></th>
	                    <th class="tableHeaderField"><spring:message code="systema.tvinn.sad.import.list.search.label.status"/></th>
	                    <th class="tableHeaderField"><spring:message code="systema.tvinn.sad.import.list.search.label.avsandare"/></th>
	                    <th class="tableHeaderField"><spring:message code="systema.tvinn.sad.import.list.search.label.mottagare"/></th>
	                    <th class="tableHeaderField"><spring:message code="systema.tvinn.sad.import.list.search.label.vikt"/></th>
	                    <th class="tableHeaderField"><spring:message code="systema.tvinn.sad.import.list.search.label.godsnr"/></th>
	                    <th class="tableHeaderField" ><spring:message code="systema.tvinn.sad.import.list.search.label.innstikk"/></th>
	                    <th class="tableHeaderField" ><spring:message code="systema.tvinn.sad.import.list.search.label.email"/></th>
						<th class="tableHeaderField" ><spring:message code="systema.tvinn.sad.import.list.search.label.eta"/></th>
	                    	                    
	                    <%-- START Omberegning --%>
	                    <th class="tableHeaderFieldOmberegning" ><spring:message code="systema.tvinn.sad.import.list.search.label.omber.omber"/></th>
	                    <th class="tableHeaderFieldOmberegning" ><spring:message code="systema.tvinn.sad.import.list.search.label.omber.status"/></th>
	                    <th class="tableHeaderFieldOmberegning" ><spring:message code="systema.tvinn.sad.import.list.search.label.omber.datum"/></th>
	                    <th class="tableHeaderFieldOmberegning" ><spring:message code="systema.tvinn.sad.import.list.search.label.omber.lopenr"/></th>
	                    <%-- END Omberegning --%>
	                    <th class="tableHeaderField" ><spring:message code="systema.tvinn.sad.import.list.search.label.notisblock"/></th>	                    
	                    <th class="tableHeaderField" nowrap><spring:message code="systema.tvinn.sad.import.list.search.label.kopieraArende"/></th>
	                    <th class="tableHeaderField" nowrap>Slett</th>
	                    
	                    
	                </tr>   
	                </thead>
	                <tbody>  
		            <c:forEach items="${list}" var="topic" varStatus="counter">
		            	   <tr class="tableRow" height="20" >
		               
		               <td class="tableCellFirst" align="center" width="1%">&nbsp;${topic.avd}</td>
		               <td class="tableCell" align="center" >&nbsp;${topic.sg}</td>
		               <td width="1%" class="tableCell" align="center">
	               	   		<a id="alinkCurrentHeaderId_${counter.count}" onClick="setBlockUI(this);" href="tvinnsadimport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&sysg=${topic.sg}&sitll=${topic.sitll}&syst=${topic.status}&sydt=${topic.datum}&o2_sist=${topic.o2_sist}&o2_sidt=${topic.o2_sidt}&o2_simf=${topic.o2_simf}">
	               				<c:choose>
		               				<c:when test="${ topic.status == 'M' || empty  topic.status }">
		               					<img title="Uppdatera ärende" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="edit">
		               				</c:when>
		               				<c:otherwise>
		               					<img title="Read" style="vertical-align:bottom;" src="resources/images/eye.png" height="18px" width="18px" border="0" alt="read">
		               				</c:otherwise>
	               				</c:choose>
           					</a>
               		   </td>
               		   <td class="tableCell" align="center" >
               		   		<a id="alinkCurrentHeaderOpdId_${counter.count}" onClick="setBlockUI(this);" href="tvinnsadimport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&sysg=${topic.sg}&sitll=${topic.sitll}&syst=${topic.status}&sydt=${topic.datum}&o2_sist=${topic.o2_sist}&o2_sidt=${topic.o2_sidt}&o2_simf=${topic.o2_simf}">
               		   			${topic.opd}
		               		</a>
		               </td>
		               <td class="tableCell" align="left"  >${topic.h_xref}</td>
		               <td class="tableCell" align="center" >${topic.datum}</td>
		               <td class="tableCell" align="center" >${topic.sitll}</td>
		               <td class="tableCell" align="center" >${topic.sitle}</td>
		               <td width="1%" class="tableCell" align="center" ><b>${topic.status}</b></td>
		               <td class="tableCell" align="left" >&nbsp;${topic.avsNavn}</td>
		               <td class="tableCell" align="left" >&nbsp;${topic.motNavn}</td>
		               <td class="tableCell" align="right" >${topic.sivkb}</td>
		               <td class="tableCell" align="center" >${topic.sign}</td>
		               <td width="1%" class="tableCell" align="center"><b>${topic.simi}</b></td>
		               <td width="1%" class="tableCell" align="center" >${topic.epjn}</td>
		               <td class="tableCell" align="center" >${topic.detaout}</td>
		               <td class="tableCellOmberegning" align="center" >${topic.o2_simf}</td>
		               <td width="1%" class="tableCellOmberegning" align="center" >${topic.o2_sist}</td>
		               <td class="tableCellOmberegning" align="center" >${topic.o2_sidt}</td>
		               <td class="tableCellOmberegning" align="center" >${topic.o2_sitll}</td>

		               <td class="tableCell" align="left">&nbsp;
	               		 <a href="editNotisblock.do?action=doFetch&subsys=sadi&avd=${topic.avd}&opd=${topic.opd}&sign=${topic.sg}">
							<img title="Notisblokk til oppdrag" src="resources/images/largeTextContent.png" width="14px" height="15px" border="0" alt="notisblock">
							<font class="text14MediumBlue" style="font-style: italic;">${topic.opd}</font>
						 </a>
	               	  </td>	
   		               <td align="center" class="tableCell" >&nbsp;
   		               		<%--
							<a class="copyLink" id="copyLink${counter.count}" runat="server" href="#">
								<img src="resources/images/copy.png" border="0" alt="copy">
							</a>
							<div style="display: none;" class="clazz_dialog" id="dialog${counter.count}" title="Dialog">
								<form  action="tvinnsadimport_copyTopic.do" name="copyForm${counter.count}" id="copyForm${counter.count}" method="post">
								 	<input type="hidden" name="action${counter.count}" id="action${counter.count}" value='doUpdate'/>
									<input type="hidden" name="originalAvd${counter.count}" id="originalAvd${counter.count}" value='${topic.avd}'/>
				 					<input type="hidden" name="originalOpd${counter.count}" id="originalOpd${counter.count}" value='${topic.opd}'/>
					 					
									<p class="text14" >Du må velge ny&nbsp;<code><b>Avdeling</b></code>&nbsp;og ny&nbsp;
										<code><b>Signatur</b></code>&nbsp;for å kunne kopiere en Tolldeklarasjon.
									</p>
									<p class="text14" >En ny Tolldekl.nr vil bli opprettet automatisk.
									</p>
									<table>
										<tr>
											<td class="text14" align="left" >&nbsp;Avdeling</td>
	                							<td class="text14" align="left" >&nbsp;Signatur</td>
	                						</tr>
	 									<tr>
											<td class="text14MediumBlue">
												<select class="newAvd" name="newAvd${counter.count}" id="newAvd${counter.count}">
								            		<option value="">-velg-</option>
								 				  	<c:forEach var="record" items="${model.avdList}" >
							                             <option value="${record.avd}">${record.avd}</option>
													</c:forEach> 
												</select>
											</td>
											<td class="text14MediumBlue">
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
							 --%>
		               </td>
		               
		               <td align="center" class="tableCell" >
		               <%--	
		               <c:if test="${ topic.status == 'M' || empty  topic.status }">
							<a class="removeLink" id="removeLink${counter.count}" runat="server" href="#">
								<img src="resources/images/delete.gif" border="0" alt="remove">
							</a>
							<div style="display: none;" class="clazz_dialog" id="dialogUpdateStatus${counter.count}" title="Dialog">
								<form action="tvinnsadimport_updateStatus_deleteWithNotis.do" name="updateStatusForm${counter.count}" id="updateStatusForm${counter.count}" method="post">
								 	<input type="hidden" name="currentAvd${counter.count}" id="currentAvd${counter.count}" value="${topic.avd}">
								 	<input type="hidden" name="currentOpd${counter.count}" id="currentOpd${counter.count}" value="${topic.opd}">
								 	<input type="hidden" name="selectedStatus${counter.count}" id="selectedStatus${counter.count}" value="S">
									<p class="text14" >Er du sikker på at du vil slette denne?</p>
									<p class="text14"> Tekst </p>
									<input type="text" class="inputText" name="currentText${counter.count}" id="currentText${counter.count}" size="45" maxlength="70" value=''>&nbsp;</td>
									
								</form>
							</div>
						</c:if>
						 --%>	
		               </td>
		               
		            </tr>
		            </c:forEach>
		            </tbody>
	            </table>
	            </td>
	            </tr>
	            </table>
	            
			</td>	
			</tr>
		</table>
		</td>
		</tr>
    </c:if> 
   		<tr>
		<td>
			<div id="dialogCopyFromTransportUppdrag" title="Dialog">
				<form  action="tvinnsadimport_doFetchTopicFromTransportUppdrag.do" name="copyFromTransportUppdragForm" id="copyFromTransportUppdragForm" method="post">
				 	<input type="hidden" name="actionGS" id="actionGS" value='doUpdate'/>

					<p class="text14" ><b>Ved å taste</b> oppdragnummer eller Ext.ref.nr. lages det en ny deklarasjon med utgangspunkt i data fra SYSPED Transportoppdrag.</p>
					<p class="text14">eller</p>
					<p class="text14" ><b>Ved å IKKE taste</b> oppdragnummer eller Ext.ref.nr. lages det en ny deklarasjon med utgangspunkt i avdelingens standardopplysninger.</p>
					<p class="text14">Klikk deretter Fortsett.</p>
										
					<table>
						<tr>
							<td class="text14" align="left" ><font class="text16RedBold" >*</font>&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.avd"/></td>
   							<td class="text14" align="left" ><font class="text16RedBold" >*</font>&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.signatur"/></td>
   							<td class="text14" align="left" >&nbsp;Oppdragsnr.</td>
   							<td class="text14" align="left" >&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.extrefnr"/></td>
   						</tr>
   						<tr>
   							<td align="left" class="text14" >
								<select class="inputTextMediumBlueMandatoryField" name="selectedAvd" id="selectedAvd">
				            		<c:forEach var="record" items="${model.avdList}" >
	                             	 	<option value="${record.avd}"<c:if test="${searchFilterSadImport.avd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst== '2'}">&nbsp;(test)</c:if></option>
									</c:forEach> 
								</select>
							</td>
							<td align="left" class="text14" >
			           			<select class="inputTextMediumBlueMandatoryField" name="selectedSign" id="selectedSign">
				            		<c:forEach var="record" items="${model.signList}" >
			                             	 	<option value="${record.sign}"<c:if test="${searchFilterSadImport.sg == record.sign}"> selected </c:if> >${record.sign}</option>
									</c:forEach> 
								</select>
							</td>
							<td class="text14MediumBlue">
								<input type="text" class="inputText" id="selectedOpd" name="selectedOpd" size="10" maxlength="35" value=''>&nbsp;
							</td>
							<td class="text14MediumBlue">
								<input type="text" class="inputText" id="selectedExtRefNr" name="selectedExtRefNr" size="25" maxlength="35" value=''>&nbsp;
							</td>
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

