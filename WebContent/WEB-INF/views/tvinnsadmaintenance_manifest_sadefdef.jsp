<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadMaintenance.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmaintenance_manifest_sadefdef.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
<table style="width:100%;" cellspacing="0" border="0" cellpadding="0">

 <tr>
 <td>	
	<%-- tab container component --%>
	<%-- tab container component --%>
		<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkSadMaintNctsExportGate" tabindex=-1 style="display:block;" href="tvinnsadmaintenance_manifest.do">  
						<font class="tabDisabledLink">&nbsp;TVINN - Vedlikehold</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center">
						<font class="tabLink">Avd. default</font>
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tvinnsadmaintenance_manifest_sadefdef.do?id=${model.dbTable}">
							<img style="vertical-align: middle;"  src="resources/images/bulletGreen.png" border="0" width="8px" height="8px" alt="db table">
						</a>
					</td>
					<td width="70%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
				</tr>
		</table>
	</td>
 </tr>
 
 <tr>
 	<td>
	<%-- --------------------------- --%>	
 	<%-- tab area container PRIMARY  --%>
	<%-- --------------------------- --%>
	<form name="manifestForm" id="manifestForm" action="tvinnsadmanifest_edit.do" method="post">
			<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
			<input type="hidden" name="updateId" id="updateId" value=""> <%-- this value is set in AJAX in order to know if the SAVE = ADD or UPDATE --%>
			<input type="hidden" name="actionU" id="actionU" value="doUpdate">
			<c:if test="${not empty model.record.efuuid}">
				<input type="hidden" name="efuuid" id=efuuid value="${model.record.efuuid}">
				<input type="hidden" name="efavd" id=efavd value="${model.record.efavd}">
				<input type="hidden" name="efsg" id=efsg value="${model.record.efsg}">
				<input type="hidden" name="efpro" id=efpro value="${model.record.efpro}">
				<input type="hidden" name="efst" id=efst value="${model.record.efst}">	
				<input type="hidden" name="efst2" id=efst2 value="${model.record.efst2}">		
			</c:if>
			
	<table style="width:100%;" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<tr height="5"><td colspan="10">&nbsp;</td></tr>
 		
 		<tr>
			<td class="text14">
			<table id="mainList" class="display compact cell-border" >
				<thead>
				<tr>
					<th class="tableHeaderFieldFirst" >&nbsp;Endre</th>
                    <th class="tableHeaderField" >&nbsp;Avd&nbsp;</th>
                    <th class="tableHeaderField" >&nbsp;Sign&nbsp;</th>
                    <th class="tableHeaderField" >&nbsp;Turnr.&nbsp;</th>
                    <th class="tableHeaderField" >&nbsp;Orgnr.&nbsp;</th>
                    <th class="tableHeaderField" >&nbsp;Transportmåte&nbsp;</th>
                    <th class="tableHeaderField" >&nbsp;Pass.tollsted&nbsp;</th>
                    <th class="tableHeaderField" >&nbsp;Eksped.enhet&nbsp;</th>
                    <th class="tableHeaderField" >&nbsp;Slett&nbsp;</th>
                </tr>  
                </thead> 
                <tbody >  
	            <c:forEach var="record" items="${model.list}" varStatus="counter">   
	               <tr class="tableRow" height="20" >
	              
	               <td align="center" width="2%" class="tableCell" style="border-style:solid; border-width:0px 1px 1px 0px; border-color:#FAEBD7;" >
	        	       		<a style="display:block;width:100%; height:100%;" id="efavd_${record.efavd}" href="#" onClick="getItemData(this);">
  							<img src="resources/images/update.gif" border="0" alt="edit">
               			</a>
	               </td>
	               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.efavd}
	               </td>
	               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.efsg}
	               </td>
	               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.efpro}
	               </td>
	               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.efrgd}
	               </td>
	               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.eftm}
	               </td>
	               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.eftsd}
	               </td>
	               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.ef3039e}
	               </td>
	               <td align="center" width="2%" class="tableCell" >   		
			   				
              				<a style="display:block; width:100%; height:100%;" class="removeLink" id="removeLink${counter.count}" runat="server" href="#">
								<img src="resources/images/delete.gif" border="0" alt="remove">
							</a>
							<div style="display: none;" class="clazz_dialog" id="dialogUpdateStatus${counter.count}" title="Dialog">
								<form action="tvinnsadmaintenance_manifest_sadefdef_delete.do" name="updateStatusForm${counter.count}" id="updateStatusForm${counter.count}" method="post">
								 	<input type="hidden" name="currentUuid${counter.count}" id="currentUuid${counter.count}" value="${record.efuuid}">
								 	<input type="hidden" name="selectedStatus${counter.count}" id="selectedStatus${counter.count}" value="D">
								 	<input type="hidden" name="selectedPro${counter.count}" id="selectedPro${counter.count}" value="${record.efpro}">
									<p class="text14" >Er du sikker på at du vil slette Turnr. <b>${record.efpro}</b></p>
									<p class="text14"> Tekst </p>
									<input type="text" class="inputText" name="currentText${counter.count}" id="currentText${counter.count}" size="45" maxlength="70" value=''>&nbsp;</td>
									
								</form>
							</div>	
	               	   </td> 
	            </tr> 
	            </c:forEach>
	            </tbody>
            </table>
		</td>	
		</tr>
 		
 		<tr height="5"><td colspan="10">&nbsp;</td></tr>
 		
		<%-- --------------- --%>
		<%-- CONTENT --%>
		<%-- --------------- --%>
		<tr>
		<td >
		<table align="center" style="width:100%;"  border="0" cellspacing="1" cellpadding="0">
			
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
                                      	${model.errorMessage} 
                                    </li>
                                    
                                </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
			
			<c:if test="${not empty model.invalidManifest}">
				<tr>
					<td colspan="10">
		            	<table align="left" border="0" cellspacing="0" cellpadding="0">
		            	<tr>
					<td class="text14 tableCellGray" style="color: #9F6000;">
	           			<font class="inputText" style="background-color: #FEEFB3;color: #9F6000;">
	           				&nbsp;Lasten er ikke gyldig.&nbsp;&nbsp;Manifestet kan derfor ikke sendes.
	           				&nbsp;&nbsp;Kontroller at alle linjene i manifestet har status=OK, og at det finnes minst en linje.
	           			</font>
	           		</td>           			
	           		</tr>
	           		</table>
	           		</td>
				</tr>
			</c:if>
 		<tr>
			<td style="width:30%" class="text14" valign="top">
				<table style="width:90%" align="left" border="0" cellspacing="1" cellpadding="0">
				 	<tr >
					 	<td >
						<table class="formFrameHeader" style="width:100%;"  border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<c:choose>
						 			<c:when test="${not empty model.record.efuuid}">
						 				<td class="text14White">
						 						&nbsp;&nbsp;Turnr:&nbsp;${model.record.efpro}
						 						&nbsp;&nbsp;Avd:&nbsp;${model.record.efavd}  
						 						
						 				</td>
						 				<td class="text14" align="right">
						 						Stat<a tabindex=-1 id="updateInternalStatusLink" name="updateInternalStatusLink" runat="server" href="#"><font class="text14">u</font></a>s:&nbsp;${model.record.efst}
						 						&nbsp;&nbsp;&nbsp;<b>Manifest stat<a tabindex=-1 id="updateManifestStatusLink" name="updateManifestStatusLink" runat="server" href="#"><font class="text14">u</font></a>s:&nbsp;</b>
						 						<c:choose>
						 						<c:when test="${model.record.efst2 == 'S' || model.record.efst2 == 'R' || model.record.efst2 == 'D'}">
						 							<c:if test="${model.record.efst2 == 'S'}">
						 								<img src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
						 								<font style="color:#FFFFCC;">SUBMITTED</font>
						 							</c:if>
						 							<c:if test="${model.record.efst2 == 'D'}">
						 								<img src="resources/images/bulletRed.png" width="10" height="10" border="0" >
						 								<font style="color:red;">SLETTET</font>
						 							</c:if>
						 							<c:if test="${model.record.efst2 == 'R'}">
						 								<font style="color:#FFFFFF;">REOPENED/DRAFT</font>
						 							</c:if>
						 						</c:when>
						 						<c:otherwise>
						 							<font style="color:#606060;">${model.record.efst2}</font>
						 						</c:otherwise>
						 						</c:choose>
						 				</td>
						 			</c:when>
						 			<c:otherwise>
						 				<td class="text14White">&nbsp;&nbsp;Transport</td>
						 			</c:otherwise>
					 			</c:choose>
			 				</tr>
			            </table>
			            </td>
		            </tr>
		            <tr >
					 	<td>
						<table class="formFrame" style="width:100%;" border="0" cellspacing="1" cellpadding="0">
			 				
							<tr>
								<td>
								<table width="80%">
								<tr>
				 					<td class="text14">&nbsp;Avd&nbsp;</td>
				 					<td class="text14">&nbsp;Sign&nbsp;</td>
				 					<td class="text14">&nbsp;Tur&nbsp;</td>
				 					
			 					</tr>				 				
			 					<tr>
				 					<td><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efavd" id="efavd" size="5" maxlength="4" value=""></td>
				 					<td><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efsg" id="efsg" size="4" maxlength="3" value=""></td>
				 					<td><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efpro" id="efpro" size="9" maxlength="8" value=""></td>
			 					</tr>
			 					</table>
			 					</td>
		 					</tr>

			 				<tr >
								<td class="text14">&nbsp;<span title="efrgd">ID-type kjøretøyeier</span><font class="text16RedBold" >*</font></td>
			 				</tr>
			 				<tr >
					 			<td class="text14">
					 				<select class="inputTextMediumBlueMandatoryField" name="todo" id="todo" style="width:100px;">
				 						<option value="O">Org.nr</option>
				 						<%--
		 		 				  		<option value="E">EORI</option>
		 		 				  		 --%>
									</select>
									&nbsp;&nbsp;<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efrgd" id="efrgd" size="10" maxlength="9" value="${model.record.efrgd}">
								</td>
			 				</tr>
			 				<tr height="3"><td></td></tr>
			 				<tr >
								<td class="text14">&nbsp;<span title="eftm">Transportmåte</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text14">
					 				<select class="inputTextMediumBlue" name="eftm" id="eftm">
				 						<option value="">Velg</option>
		 		 				  		<option value="BIL" <c:if test="${model.record.eftm == 'BIL'}"> selected </c:if> >(30) Bil (veitransport)</option>
									</select>
									
					 			</td>
			 				</tr>
			 				
			 				<tr>
			 				<td>
			 				<table width="80%" >
			 				<tr>
								<td class="text14">&nbsp;<span title="efeta">ETA</span></td>
								<td class="text14">&nbsp;<span title="efetm">Tid</span></td>
								<td class="text14">&nbsp;<span title="eftsd">Pass.tollsted</span></td>
								<td class="text14">&nbsp;<span title="ef3039e">Eksped.enhet</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text14">
					 				<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="efeta" id="efeta" size="7" maxlength="6" value="${model.record.efeta}">
					 				
					 			</td>
								<td>
									<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="efetm" id="efetm" size="6" maxlength="4" value="${model.record.efetm}">
					 				
								</td>
			 				
					 			<td class="text14">
					 				<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="eftsd" id="eftsd" size="5" maxlength="4" value="${model.record.eftsd}">
					 				<a tabindex="-1" id="eftsdIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
					 			</td>
								<td>
									<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="ef3039e" id="ef3039e" size="7" maxlength="6" value="${model.record.ef3039e}">
								</td>
			 				</tr>
			 				<%-- Avvakta med denna Eksport id på denna nivå
			 				<tr >
								<td colspan="4" class="text14">&nbsp;<span title="efeid">EksportId</span></td>
								
			 				</tr>
			 				<tr >
					 			<td colspan="2" class="text14">
					 				<input type="text" class="inputTextMediumBlue" name="efeid" id="efeid" size="20" maxlength="18" value="${model.record.efeid}">
					 			</td>
								
			 				</tr>
			 				 --%>
			 				</table>
			 				</td>
			 				</tr>
			 				
			            </table>
			            </td>
		            </tr>
	            
	            </table>
            </td>
            
           	<td style="width:30%;" class="text14" valign="top">
				<table style="width:90%;" align="left" border="0" cellspacing="1" cellpadding="0">
				 	<tr >
					 	<td >
						<table class="formFrameHeader" style="width:100%; border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<td class="text14White">&nbsp;&nbsp;Kjøretøy&nbsp;</td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
		            <tr >
					 	<td>
						<table style="width:100%;" class="formFrame" border="0" cellspacing="1" cellpadding="0">
					 		<tr >
					 			<td class="text14">&nbsp;<span title="efktyp">Kjøretøytype</span></td>
					 			<td class="text14">&nbsp;</td>
			 				</tr>
			 				<tr >
					 			<td class="text14">
					 				<select class="inputTextMediumBlue" name="efktyp" id="efktyp">
					            		<option value="">-select-</option>
					 				  	<c:forEach var="record" items="${model.ktTypeList}" >
				                       	 	<option title="${record.kftxt}" value="${record.kfkod}" <c:if test="${model.record.efktyp == record.kfkod}"> selected </c:if> >${record.kfkod}&nbsp;${record.kftxt}</option>
										</c:forEach> 
									</select>
					 			</td>
			 				</tr>
			 				
			 				<tr>
				 				<td>
				 				<table>
				 				<tr >
									<td class="text14">&nbsp;<span title="efkmrk">Kjøretøy kjennemerke</span></td>
									<td class="text14">&nbsp;<span title="efklk">Kjøretøy nasjonalitet</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="efkmrk" id="efkmrk" size="15" maxlength="30" value="${model.record.efkmrk}"></td>
									<td class="text14">
						 				<select class="inputTextMediumBlue" name="efklk" id="efklk">
					 						<option value="">-velg-</option>
						 				  	<c:forEach var="country" items="${model.countryCodeList}" >
						 				  		<option title="${country.ztxt}" value="${country.zkod}"<c:if test="${model.record.efklk == country.zkod}"> selected </c:if> >${country.zkod}</option>
											</c:forEach>  
										</select>
						 			</td>
				 				</tr>
				 				<tr height="2"><td>&nbsp;</td></tr>
				 				<tr >
									<td class="text14">&nbsp;<span title="efpmrk">Passiv kjøretøy kjennemerke</span></td>
									<td class="text14">&nbsp;<span title="efplk">Passiv kjøretøy nasjonalitet</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="efpmrk" id="efpmrk" size="15" maxlength="30" value="${model.record.efpmrk}"></td>
									<td class="text14">
						 				<select class="inputTextMediumBlue" name="efplk" id="efplk">
					 						<option value="">-velg-</option>
						 				  	<c:forEach var="country" items="${model.countryCodeList}" >
						 				  		<option title="${country.ztxt}" value="${country.zkod}"<c:if test="${model.record.efplk == country.zkod}"> selected </c:if> >${country.zkod}</option>
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
	            </table>
            </td>
            
            
            <td style="width:30%" class="text14" valign="top">
				<table style="width:90%" align="left" border="0" cellspacing="1" cellpadding="0">
				 	<tr >
					 	<td >
						<table class="formFrameHeader" style="width:100%;" border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<td class="text14White">&nbsp;&nbsp;Fører&nbsp;</td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
		            <tr >
					 	<td>
						<table style="width:100%" class="formFrame" border="0" cellspacing="1" cellpadding="0">
					 		<tr >
					 			<td class="text14">&nbsp;<span title="efsjaf">Fornavn</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text14"><input type="text" class="inputTextMediumBlue" name="efsjaf" id="efsjaf" size="31" maxlength="30" value="${model.record.efsjaf}"></td>
			 				</tr>
			 				<tr >
								<td class="text14">&nbsp;<span title="efsjae">Etternavn</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text14"><input type="text" class="inputTextMediumBlue" name="efsjae" id="efsjae" size="31" maxlength="30" value="${model.record.efsjae}"></td>
			 				</tr>
			 				
			 				<tr>
			 				<td>
			 				<table width="70%">
			 				<tr >
								<td class="text14">&nbsp;<span title="efsjalk">Statsborger i</span>
								</td>
								<td class="text14">&nbsp;<span title="efsjadt">Fødselsdato</span>
								</td>
								<td class="text14">&nbsp;<span title="efbekr">Bekreftelse</span>
								</td>
			 				</tr>
			 				<tr >
					 			<td class="text14">
					 				<select class="inputTextMediumBlue" name="efsjalk" id="efsjalk">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					 				  		<option title="${country.ztxt}" value="${country.zkod}"<c:if test="${model.record.efsjalk == country.zkod}"> selected </c:if> >${country.zkod}</option>
										</c:forEach>  
									</select>
								</td>
								<td class="text14">
									<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="efsjadt" id="efsjadt" size="7" maxlength="6" value="${model.record.efsjadt}">
					 				
					 			</td>
					 			<td class="text14">
					 				<select class="inputTextMediumBlue" name="efbekr" id="efbekr">
				 						<option value="N"<c:if test="${model.record.efbekr == 'N'}"> selected </c:if> >Nei</option>
										<option value="J"<c:if test="${model.record.efbekr == 'J'}"> selected </c:if> >Ja</option>
									</select>
								</td>
			 				</tr>
			 				</table>
			 				</td>
			 				</tr>
			 				
			 				<tr height="15"><td colspan="2">&nbsp;</td></tr>
			 				<%-- Avvakta med denna. Finns bara i Tolls web
			 				<tr >
								<td class="text14">&nbsp;<span title="titin">Kort beskrivelse</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text14"><input type="text" class="inputTextMediumBlue" name="titin" id="titin" size="50" maxlength="50" value="${Xmodel.record.titin}"></td>
			 				</tr>
			 				 --%>
			            </table>
			            </td>
		            </tr>
	            </table>
            </td>			 
		</tr>
		<tr height="10"><td></td></tr>
		<c:if test="${model.record.efst != 'S'}">
			<tr>
				<td colspan="3" class="text14" valign="top">
					<table style="width:96%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td align="right" >
								<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</c:if>
		<tr height="20"><td colspan="2">&nbsp;</td></tr>
	
	</table>
	</td>
	</tr>	
	</table> 
	</form>
</td>
</tr>

<%-- Dialog update manifest status --%>		
<tr>
	<td>
		<div id="dialogUpdateManifestStatus" title="Dialog">
			
			<form action="tvinnsadmanifest_updateManifestStatus.do" name="updateManifestStatusForm" id="updateManifestStatusForm" method="post">
			 	<input type="hidden" name="efuuid" id="efuuid" value="${model.record.efuuid}">
			 	<p class="text14" >Change Manifest status as needed.</p>
				<table>
					<tr>
						<td class="text14" align="left" >&nbsp;Status</td>
						<td class="text14MediumBlue">
							<select class="selectMediumBlueE2" name="efst2" id="efst2">
			            		  	<option value=" ">-velg-</option>
		            		  		<option value="R">REOPENED/DRAFT</option>
							  	<option value="D">SLETTET</option>
							  	<option value="S">SUBMITTED</option>
							  	
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</td>
</tr> 


<%-- Dialog update manifest status --%>		
<tr>
	<td>
		<div id="dialogUpdateInternalStatus" title="Dialog">
			
			<form action="tvinnsadmanifest_updateInternalStatus.do" name="updateInternalStatusForm" id="updateInternalStatusForm" method="post">
			 	<input type="hidden" name="efuuid" id="efuuid" value="${model.record.efuuid}">
			 	<p class="text14" >Change Internal status as needed.</p>
				<table>
					<tr>
						<td class="text14" align="left" >&nbsp;Status</td>
						<td class="text14MediumBlue">
							<select class="selectMediumBlueE2" name="efst" id="efst">
			            		  	<option value=" ">-velg-</option>
			            		  	<option value="B">B</option>
		            		  		<option value="M">M</option>
							  	<option value="S">SLETTET</option>
							  	
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</td>
</tr> 


</table>
 
 	
 
	