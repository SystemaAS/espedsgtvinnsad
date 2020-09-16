<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmanifest_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
<table style="width:100%;" cellspacing="0" border="0" cellpadding="0">

 <tr>
 <td>	
	<%-- tab container component --%>
	<table style="width:100%;"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<c:choose> 
			    <c:when test="${not empty model.record.efuuid}">
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkManifestList" style="display:block;" href="tvinnsadmanifest.do?action=doFind&avd=${model.record.efavd}&sign=${model.record.efsg}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.manifest.list.tab"/></font>
							<img src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			
					<td title="${model.record.efuuid}" width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;<spring:message code="systema.tvinn.sad.manifest.created.header.tab"/>
						</font>
						<font class="text14MediumBlue">[${model.record.efpro}]</font>
						<img src="resources/images/update.gif" border="0" alt="edit">
						
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkItems" style="display:block;" href="tvinnsadmanifest_edit_cargolines.do?action=doFetch&clpro=${model.record.efpro}&efsg=${model.record.efsg}
													&efavd=${model.record.efavd}&efuuid=${model.record.efuuid}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.manifest.createnew.last.tab"/>
							</font>
							<img src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
							
						</a>
					</td>
					
					<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:when>
				<c:otherwise>
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkManifestList" tabindex=-1 style="display:block;" href="tvinnsadmanifest.do?action=doFind&sign=${user.tvinnSadSign}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.manifest.list.tab"/></font>
							<img src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.createnew"/></font>
						<img src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
						
					</td>
					<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
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
	<form name="manifestForm" id="manifestForm" method="post">
			<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
			<input type="hidden" name="updateId" id="updateId" value=""> <%-- this value is set in AJAX in order to know if the SAVE = ADD or UPDATE --%>
			<input type="hidden" name="actionU" id="actionU" value="doUpdate">
			<c:if test="${not empty model.record.efuuid}">
				<input type="hidden" name="efavd" id=efavd value="${model.record.efavd}">
				<input type="hidden" name="efsg" id=efsg value="${model.record.efsg}">
				<input type="hidden" name="efpro" id=efpro value="${model.record.efpro}">
								
			</c:if>
			
	<table style="width:100%;" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<tr height="10"><td colspan="10">&nbsp;</td></tr>
 		
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
 		<tr>
			<td style="width:30%" class="text14" valign="top">
				<table style="width:90%" align="left" border="0" cellspacing="1" cellpadding="0">
				 	<tr >
					 	<td >
						<table style="width:100%" class="formFrameHeader" border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<c:choose>
						 			<c:when test="${not empty model.record.efuuid}">
						 				<td class="text14White">
						 						&nbsp;&nbsp;Turnr:&nbsp;${model.record.efpro}
						 						&nbsp;&nbsp;Avd:&nbsp;${model.record.efavd}</td>
						 				<td class="text14" align="right">		
						 						<font style="color:#606060;">Manif.status:</font>&nbsp;
						 						<c:choose>
						 						<c:when test="${model.record.efst2 == 'S' || model.record.efst2 == 'D'}">
						 							<c:if test="${model.record.efst2 == 'S'}">
						 								<font style="color:#606060;">SUBMITTED</font>
						 							</c:if>
						 							<c:if test="${model.record.efst2 == 'D'}">
						 								<font style="color:#606060;">DRAFT</font>
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
						<table style="width:100%" class="formFrame" border="0" cellspacing="1" cellpadding="0">
			 				<c:choose>
					 			<c:when test="${not empty model.record.efuuid}">
					 				<tr >
										<td class="text14">&nbsp;Manifestid:&nbsp;<font class="text14SkyBlue">${model.record.efuuid}</font></td>
					 				</tr>
					 				<tr height="2"><td></td></tr>
			 					</c:when>
				 				<c:otherwise>
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
				 				</c:otherwise>
			 				</c:choose>
			 				
			 				<tr >
								<td class="text14">&nbsp;<span title="efknd">Kundenr.</span><font class="text16RedBold" >*</font></td>
			 				</tr>
			 				<tr >
					 			<td class="text14">
					 				&nbsp;<input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efknd" id="efknd" size="8" maxlength="8" value="${model.record.efknd}">
					 				<a tabindex="-1" id="efkndIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
					 				&nbsp;&nbsp;<input class="inputTextReadOnly" name="own_efkndName" id="own_efkndName" size="25" maxlength="30" value="${Xmodel.record.efkndName}">
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
									&nbsp;&nbsp;<input type="text" class="inputTextReadOnly" name="efrgd" id="efrgd" size="10" maxlength="9" value="${model.record.efrgd}">
								</td>
			 				</tr>
			 				<tr height="3"><td></td></tr>
			 				<tr >
								<td class="text14">&nbsp;<span title="eftm">Transportmåte</span><font class="text16RedBold" >*</font></td>
			 				</tr>
			 				<tr >
					 			<td class="text14">
					 				<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="eftm" id="eftm">
				 						<option value="">Velg</option>
		 		 				  		<option value="BIL" <c:if test="${model.record.eftm == 'BIL'}"> selected </c:if> >(30) Bil (veitransport)</option>
									</select>
									
					 			</td>
			 				</tr>
			 				<%-- this is replaced with the customer name 
			 				<tr >
								<td class="text14">&nbsp;<span title="todo">Kjøretøyeier</span><font class="text16RedBold" >*</font></td>
			 				</tr>
			 				<tr >
					 			<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="todo" id="todo" size="45" maxlength="35" value="${Xmodel.record.titin}"></td>
			 				</tr>
			 				--%>
			 				<tr>
			 				<td>
			 				<table width="80%" >
								<td class="text14">&nbsp;<span title="efeta">ETA</span><font class="text16RedBold" >*</font></td>
								<td class="text14">&nbsp;<span title="efetm">Tid</span><font class="text16RedBold" >*</font></td>
								<td class="text14">&nbsp;<span title="eftsd">Pass.tollsted</span><font class="text16RedBold" >*</font></td>
								<td class="text14">&nbsp;<span title="ef3039e">Eksped.enhet</span><font class="text16RedBold" >*</font></td>
			 				</tr>
			 				<tr >
					 			<td class="text14">
					 				<c:choose>
					 				<c:when test="${record.efeta != '0'}">
					 					<input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efeta" id="efeta" size="7" maxlength="6" value="${model.record.efeta}">
					 				</c:when>
					 				<c:otherwise>
					 					<input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efeta" id="efeta" size="7" maxlength="6" value="">
					 				</c:otherwise>
					 				</c:choose>
					 			</td>
								<td>
									<c:choose>
					 				<c:when test="${record.efetm != '0'}">
					 					<input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efetm" id="efetm" size="7" maxlength="6" value="${model.record.efetm}">
					 				</c:when>
					 				<c:otherwise>
					 					<input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efetm" id="efetm" size="7" maxlength="6" value="">
					 				</c:otherwise>
					 				</c:choose>
									
								</td>
			 				
					 			<td class="text14">
					 				<c:choose>
					 				<c:when test="${record.eftsd != '0'}">
					 					<input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="eftsd" id="eftsd" size="5" maxlength="4" value="${model.record.eftsd}">
					 				</c:when>
					 				<c:otherwise>
					 					<input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="eftsd" id="eftsd" size="5" maxlength="4" value="">
					 				</c:otherwise>
					 				</c:choose>
					 				<a tabindex="-1" id="eftsdIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
					 			</td>
								<td>
									<c:choose>
					 				<c:when test="${record.ef3039e != '0'}">
					 					<input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="ef3039e" id="ef3039e" size="7" maxlength="6" value="${model.record.ef3039e}">
					 				</c:when>
					 				<c:otherwise>
					 					<input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="ef3039e" id="ef3039e" size="7" maxlength="6" value="">
					 				</c:otherwise>
					 				</c:choose>
									
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
						<table style="width:100%" class="formFrameHeader" border="0" cellspacing="1" cellpadding="0">
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
					 			<td class="text14">&nbsp;<span title="efktyp">Kjøretøytype</span><font class="text16RedBold" >*</font></td>
					 			<td class="text14">&nbsp;</td>
			 				</tr>
			 				<tr >
					 			<td class="text14">
					 				<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="efktyp" id="efktyp">
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
									<td class="text14">&nbsp;<span title="efkmrk">Kjøretøy kjennemerke</span><font class="text16RedBold" >*</font></td>
									<td class="text14">&nbsp;<span title="efklk">Kjøretøy nasjonalitet</span><font class="text16RedBold" >*</font></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efkmrk" id="efkmrk" size="15" maxlength="30" value="${model.record.efkmrk}"></td>
									<td class="text14">
						 				<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="efklk" id="efklk">
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
									<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efpmrk" id="efpmrk" size="15" maxlength="30" value="${model.record.efpmrk}"></td>
									<td class="text14">
						 				<select  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="efplk" id="efplk">
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
						<table style="width:100%" class="formFrameHeader" border="0" cellspacing="1" cellpadding="0">
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
					 			<td class="text14">&nbsp;<span title="efsjaf">Fornavn</span><font class="text16RedBold" >*</font></td>
			 				</tr>
			 				<tr >
					 			<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efsjaf" id="efsjaf" size="31" maxlength="30" value="${model.record.efsjaf}"></td>
			 				</tr>
			 				<tr >
								<td class="text14">&nbsp;<span title="efsjae">Etternavn</span><font class="text16RedBold" >*</font></td>
			 				</tr>
			 				<tr >
					 			<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efsjae" id="efsjae" size="31" maxlength="30" value="${model.record.efsjae}"></td>
			 				</tr>
			 				
			 				<tr>
			 				<td>
			 				<table width="70%">
			 				<tr >
								<td class="text14">&nbsp;<span title="efsjalk">Statsborger i</span><font class="text16RedBold" >*</font>
								</td>
								<td class="text14">&nbsp;<span title="efsjadt">Fødselsdato</span><font class="text16RedBold" >*</font>
								</td>
								<td class="text14">&nbsp;<span title="efbekr">Bekreftelse</span><font class="text16RedBold" >*</font>
								</td>
			 				</tr>
			 				<tr >
					 			<td class="text14">
					 				<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="efsjalk" id="efsjalk">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					 				  		<option title="${country.ztxt}" value="${country.zkod}"<c:if test="${model.record.efsjalk == country.zkod}"> selected </c:if> >${country.zkod}</option>
										</c:forEach>  
									</select>
								</td>
								<td class="text14">
									<c:choose>
					 				<c:when test="${record.efsjadt != '0'}">
					 					<input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efsjadt" id="efsjadt" size="7" maxlength="6" value="${model.record.efsjadt}">
					 				</c:when>
					 				<c:otherwise>
					 					<input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efsjadt" id="efsjadt" size="7" maxlength="6" value="${model.record.efsjadt}">
					 				</c:otherwise>
					 				</c:choose>
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
</table>
 
 	
 
	