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
 	        <form name="tvinnsadImportSearchForm" id="searchForm" action="tvinnsadimport?action=doFindWithDigitoll" method="post" >
 	        
 	        <tr>	
                <td class="text14" align="left" title="avd">&nbsp;&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.avd"/></td>
                <td class="text14" align="left" title="sg">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.signatur"/></td>
                <td class="text14" align="left" title="opd">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.arende"/></td>
                <td class="text14" align="left" title="datum"><spring:message code="systema.tvinn.sad.import.list.search.label.fdatum"/></td>
                <td class="text14" align="left" title="datumt"><spring:message code="systema.tvinn.sad.import.list.search.label.tdatum"/></td>
                <td class="text14" align="left" title="sitll">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.lopenr"/></td>
                <td class="text14" align="left" title="sitle">&nbsp;&nbsp;Ekspnr.</td>
                <td class="text14" align="left" title="sitll">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.import.list.search.label.transpId"/></td>
                
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
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datum" id="datum" size="7" maxlength="6" value='${searchFilterSadImport.datum}'>&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datumt" id="datumt" size="7" maxlength="6" value='${searchFilterSadImport.datumt}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="sitll" id="sitll" size="12" maxlength="10" value='${searchFilterSadImport.sitll}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="sitle" id="sitle" size="12" maxlength="10" value='${searchFilterSadImport.sitle}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="sitrid" id="sitrid" size="14" maxlength="35" value='${searchFilterSadImport.sitrid}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="status" id="status" size="1" maxlength="1" value='${searchFilterSadImport.status}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="avsNavn" id="avsNavn" size="9" maxlength="35" value='${searchFilterSadImport.avsNavn}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="motNavn" id="motNavn" size="9" maxlength="35" value='${searchFilterSadImport.motNavn}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="godsnr" id="godsnr" size="10" maxlength="15" value='${searchFilterSadImport.godsnr}'>&nbsp;</td>
				
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
	                    <th class="tableHeaderField" nowrap><spring:message code="systema.tvinn.sad.import.list.search.label.datum"/></th>
	                    <th class="tableHeaderField" nowrap><spring:message code="systema.tvinn.sad.import.list.search.label.lopenr"/></th>
	                    <th class="tableHeaderField" nowrap><spring:message code="systema.tvinn.sad.import.list.search.label.ekspnr"/></th>
	                    <th class="tableHeaderField" nowrap><spring:message code="systema.tvinn.sad.import.list.search.label.transpId"/></th>
	                    
	                    <th class="tableHeaderField"><spring:message code="systema.tvinn.sad.import.list.search.label.status"/></th>
	                    <th class="tableHeaderField"><spring:message code="systema.tvinn.sad.import.list.search.label.avsandare"/></th>
	                    <th class="tableHeaderField"><spring:message code="systema.tvinn.sad.import.list.search.label.mottagare"/></th>
	                    <th class="tableHeaderField"><spring:message code="systema.tvinn.sad.import.list.search.label.vikt"/></th>
	                    <th class="tableHeaderField"><spring:message code="systema.tvinn.sad.import.list.search.label.godsnr"/></th>
	                    	                    
	                    <%-- START Digitoll --%>
	                    <th class="tableHeaderFieldOmberegning" ><spring:message code="systema.tvinn.sad.import.list.search.label.digitoll.lnrt"/></th>
	                    <th class="tableHeaderFieldOmberegning" ><spring:message code="systema.tvinn.sad.import.list.search.label.digitoll.eta"/></th>
	                    <th class="tableHeaderFieldOmberegning" ><spring:message code="systema.tvinn.sad.import.list.search.label.digitoll.bilnr"/></th>
	                    <th class="tableHeaderFieldOmberegning" ><spring:message code="systema.tvinn.sad.import.list.search.label.digitoll.vikt"/></th>
	                    <th class="tableHeaderFieldOmberegning" ><spring:message code="systema.tvinn.sad.import.list.search.label.digitoll.vikthouse"/></th>
	                    <%-- 
	                    <th class="tableHeaderFieldOmberegning" ><spring:message code="systema.tvinn.sad.import.list.search.label.digitoll.masterDocNr"/></th>
	                    <th class="tableHeaderFieldOmberegning" ><spring:message code="systema.tvinn.sad.import.list.search.label.digitoll.houseTur"/></th>
	                    <th class="tableHeaderFieldOmberegning" ><spring:message code="systema.tvinn.sad.import.list.search.label.digitoll.houseOpd"/></th>
	                    <%-- END Digitoll --%>
	                    
	                    
	                    
	                </tr>   
	                </thead>
	                <tbody>  
		            <c:forEach items="${list}" var="topic" varStatus="counter">
		            	   <tr class="tableRow" height="20" >
		               
		               <td class="tableCellFirst" align="center" width="1%">&nbsp;${topic.siavd}</td>
		               <td class="tableCell" align="center" >&nbsp;${topic.sisg}</td>
		               <td width="1%" class="tableCell" align="center">
	               	   		<a id="alinkCurrentHeaderId_${counter.count}" onClick="setBlockUI(this);" href="tvinnsadimport_edit.do?action=doFetch&avd=${topic.siavd}&opd=${topic.sitdn}&sysg=${topic.sisg}&sitll=${topic.sitll}&syst=${topic.sist}&sydt=${topic.sidt}&o2_sist=${Xtopic.o2_sist}&o2_sidt=${Xtopic.o2_sidt}&o2_simf=${Xtopic.o2_simf}">
	               				<c:choose>
		               				<c:when test="${ topic.sist == 'M' || empty  topic.sist }">
		               					<img title="Uppdatera ärende" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="edit">
		               				</c:when>
		               				<c:otherwise>
		               					<img title="Read" style="vertical-align:bottom;" src="resources/images/eye.png" height="18px" width="18px" border="0" alt="read">
		               				</c:otherwise>
	               				</c:choose>
           					</a>
               		   </td>
               		   <td class="tableCell" align="center" >
               		   		<a id="alinkCurrentHeaderOpdId_${counter.count}" onClick="setBlockUI(this);" href="tvinnsadimport_edit.do?action=doFetch&avd=${topic.siavd}&opd=${topic.sitdn}&sysg=${topic.sisg}&sitll=${topic.sitll}&syst=${topic.sist}&sydt=${topic.sidt}&o2_sist=${Xtopic.o2_sist}&o2_sidt=${Xtopic.o2_sidt}&o2_simf=${Xtopic.o2_simf}">
               		   			${topic.sitdn}
		               		</a>
		               </td>
		               <td class="tableCell" align="center" >${topic.sidtno}</td>
		               <td class="tableCell" align="center" >${topic.sitll}</td>
		               <td class="tableCell" align="center" >${topic.sitle}</td>
		               <td class="tableCell" align="center" >${topic.sitrid}</td>
		               <td width="1%" class="tableCell" align="center" ><b>${topic.sist}</b></td>
		               <td class="tableCell" align="left" >&nbsp;${topic.sinas}</td>
		               <td class="tableCell" align="left" >&nbsp;${topic.sinak}</td>
		               <td class="tableCell" align="right" >${topic.sivkb}</td>
		               <td class="tableCell" align="center" >${topic.sign}</td>
		               <td class="tableCellOmberegning" align="center" >	               		
		               		<a id="alinkCurrentLnr_${counter.count}" onClick="setBlockUI(this);" href="tvinnsaddigitollv2.do?action=doFind&lnr=${topic.etlnrt}&sadi=1">
		               			${topic.etlnrt}
		               		</a>		               		
		               </td>
		               <td class="tableCellOmberegning" align="center" >
		               		<c:if test="${not empty topic.etetadno && topic.etetadno != 'null'}">
		               		${topic.etetadno}
		               		</c:if>
		               </td>
		               <td class="tableCellOmberegning" align="center" >
		               		<c:if test="${not empty topic.etkmrk && topic.etkmrk != 'null'}">
		               		${topic.etkmrk}
		               		</c:if>
		               </td>
		               <td class="tableCellOmberegning" align="center" >
		               		<c:if test="${not empty topic.emvkb && topic.emvkb != 'null'}">
		               		${topic.emvkb}
		               		</c:if>
		               </td>
		               <td class="tableCellOmberegning" align="center" >
		               		<c:if test="${not empty topic.ehvkb && topic.ehvkb != 'null'}">
		               		${topic.ehvkb}
		               		</c:if>
		               </td>
		               <%-- 
		               <td class="tableCellOmberegning" align="left" >
		               		<c:if test="${not empty topic.emdkm && topic.emdkm != 'null'}">
		               		${topic.emdkm}
		               		</c:if>
		               </td>
		               <td class="tableCellOmberegning" align="left" >
		               		${topic.ehpro}
		               		
		               	</td>
		               <td class="tableCellOmberegning" align="left" >
		               		${topic.ehtdn}
		               		
		               </td>
						--%>
		               
		               
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

