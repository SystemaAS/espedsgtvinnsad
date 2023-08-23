<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_edit_transport.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
			 
			 		<%-- TEMP --%>
			 
			 		<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkTransportList" style="display:block;" href="tvinnsaddigitollv2.do?action=doFind">
							<font class="tabDisabledLink">&nbsp;Transportlist</font>
							<img src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			
					<td title="${Xmodel.record.efuuid}" width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;Transport
						</font>
						<font class="text14MediumBlue">[${model.record.etlnrt}]</font>
						<img src="resources/images/update.gif" border="0" alt="edit">
						
					</td>
					
					<%-- <td width="50%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>  --%>
			 		<td width="80%" class="tabFantomSpace" align="right" nowrap><font class="tabDisabledLink">&nbsp;</font>
						<img id="imgInfoRpgJarStart" style="cursor:pointer;" onClick="showPop('jarStartCmd');" src="resources/images/info4.png" width="12" height="12" border="0" alt="info">
						<div class="text12" style="position: relative;display: inline;" align="left">
						<span style="position:absolute; left:-580px; top:3px;" id="jarStartCmd" class="popupWithInputText"  >
			           		<div class="text11" align="left">
			           			<p>
				           			<a class="text11" target="_blank" id="alinkHeader" href="renderLocalLogsgExpft.do?user=${user.user}">
				           				logsg_syjservicestn-expft.log
				           			</a>
			           			</p>
			           			
			           			<p>
				           			<a class="text11" target="_blank" id="alinkHeader" href="renderLocalCatalina.do?user=${user.user}">
				           				catalina.out
				           			</a>
			           			</p>
			           			<br/>
			           			<button name="_ButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('jarStartCmd');">Close</button> 
			           		</div>
			           	</span>
			           	</div>
					</td>
			 
			<%--
				<c:choose> 
			    <c:when test="${not empty model.record.efuuid}">
		    	
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkManifestList" style="display:block;" href="tvinnsadmanifest.do?action=doFind&avd=${model.record.efavd}&sign=${model.record.efsg}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.manifest.list.tab"/></font>
							<img src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			
					<td title="${model.record.efuuid}" width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;<spring:message code="systema.tvinn.sad.manifest.created.header.tab"/>
						</font>
						<font class="text14MediumBlue">[${model.record.efpro}]</font>
						<img src="resources/images/update.gif" border="0" alt="edit">
						
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkItems" style="display:block;" href="tvinnsadmanifest_edit_cargolines.do?action=doFetch&efpro=${model.record.efpro}&efsg=${model.record.efsg}
													&efavd=${model.record.efavd}&efuuid=${model.record.efuuid}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.manifest.createnew.last.tab"/>
							</font>
							<img src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
							
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkItems" style="display:block;" href="tvinnsadmanifest_logging.do?efpro=${model.record.efpro}&efsg=${model.record.efsg}
													&efavd=${model.record.efavd}&efuuid=${model.record.efuuid}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.manifest.created.header.logging.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					
					<td width="50%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:when>
				<c:otherwise>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkManifestList" tabindex=-1 style="display:block;" href="tvinnsadmanifest.do?action=doFind&sign=${user.tvinnSadSign}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.manifest.list.tab"/></font>
							<img src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.createnew"/></font>
						<img src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
						
					</td>
					<td width="fossilf%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:otherwise>
				
			</c:choose> --%>
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
			<%--
			<c:if test="${not empty model.record.efuuid}">
				<input type="hidden" name="efuuid" id=efuuid value="${model.record.efuuid}">
				<input type="hidden" name="efavd" id=efavd value="${model.record.efavd}">
				<input type="hidden" name="efsg" id=efsg value="${model.record.efsg}">
				<input type="hidden" name="efpro" id=efpro value="${model.record.efpro}">
				<input type="hidden" name="efst" id=efst value="${model.record.efst}">	
				<input type="hidden" name="efst2" id=efst2 value="${model.record.efst2}">		
			</c:if>
			 --%>
			 
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
			<%--
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
			 --%>
 		<tr>
			<td style="width:33%" class="text14" valign="top">
				<table style="width:95%" align="left" border="0" cellspacing="1" cellpadding="0">
				 	<tr >
					 	<td >
						<table class="formFrameHeader" style="width:100%;"  border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<c:choose>
						 			<c:when test="${not empty model.record.etuuid}">
						 				<td class="text14White">
						 						&nbsp;&nbsp;Løp.nr.&nbsp;${model.record.etlnrt}
						 						&nbsp;&nbsp;Turnr&nbsp;${model.record.etpro}
						 						&nbsp;&nbsp;Avd&nbsp;${model.record.etavd}  
						 						
						 				</td>
						 				<td class="text14White" align="right">
						 						Stat<a tabindex=-1 id="updateInternalStatusLink" name="updateInternalStatusLink" runat="server" href="#"><font class="text14White">u</font></a>s:&nbsp;${model.record.etst2}
						 						&nbsp;&nbsp;&nbsp;<b>Manif stat<a tabindex=-1 id="updateManifestStatusLink" name="updateManifestStatusLink" runat="server" href="#"><font class="text14White">u</font></a>s:&nbsp;</b>
						 						<c:choose>
						 						<c:when test="${model.record.etst2 == 'S' || model.record.etst2 == 'R' || model.record.etst2 == 'D'}">
						 							<c:if test="${model.record.etst2 == 'S'}">
						 								<img src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
						 								<font style="color:#FFFFCC;">SUBMITTED</font>
						 							</c:if>
						 							<c:if test="${model.record.etst2 == 'D'}">
						 								<img src="resources/images/bulletRed.png" width="10" height="10" border="0" >
						 								<font style="color:red;">SLETTET</font>
						 							</c:if>
						 							<c:if test="${model.record.etst2 == 'R'}">
						 								<font style="color:#FFFFFF;">REOPENED/DRAFT</font>
						 							</c:if>
						 						</c:when>
						 						<c:otherwise>
						 							<font style="color:#606060;">${model.record.etst2}</font>
						 						</c:otherwise>
						 						</c:choose>
						 				</td>
						 			</c:when>
						 			<c:otherwise>
						 				<td class="text14White">&nbsp;&nbsp;Transportinfo.</td>
						 			</c:otherwise>
					 			</c:choose>
			 				</tr>
			            </table>
			            </td>
		            </tr>
		            <tr >
					 	<td>
						<table class="formFrame" style="width:100%;" border="0" cellspacing="1" cellpadding="0">
			 				<c:choose>
					 			<c:when test="${not empty model.record.etuuid}">
					 				<tr >
										
										<td colspan="4" class="text12" ><span class="text14SkyBlue">
						               		<a style="display: block; cursor:pointer" class="uuidLinkParent text12SkyBlue" id="${model.record.etuuid}">
												Id&nbsp;${model.record.etuuid}
											</a>
						               </td>
										
					 				</tr>
					 				<tr height="2"><td>&nbsp;</td></tr>
			 					</c:when>
				 				<c:otherwise>
									
									<tr>
					 					<td class="text12" title="etavd">&nbsp;Avd&nbsp;</td>
					 					<td class="text12" title="etsg">&nbsp;Sign&nbsp;</td>
					 					<td class="text12" title="etpro">&nbsp;Tur&nbsp;</td>
					 					
				 					</tr>				 				
				 					<tr>
					 					<td><input type="text12" readonly class="inputTextReadOnly" name="etavd" id="etavd" size="5" maxlength="4" value="${model.record.etavd}"></td>
					 					<td><input type="text12" readonly class="inputTextReadOnly" name="etsg" id="etsg" size="4" maxlength="3" value="${model.record.etsg}"></td>
					 					<td>
					 						<c:choose>
							 				<c:when test="${model.record.etpro != '0'}">
							 					<input type="text12" readonly class="inputTextReadOnly" name="etpro" id="etpro" size="9" maxlength="8" value="${model.record.etpro}">
							 				</c:when>	
							 				<c:otherwise>
							 					<input type="text12" readonly class="inputTextReadOnly" name="etpro" id="etpro" size="9" maxlength="8" value="">
							 				</c:otherwise>
							 				</c:choose>
					 					</td>
				 					</tr>
				 					<tr height="10"></tr>
				 				</c:otherwise>
			 				</c:choose>
			 					<tr >
				 					<td class="text14">&nbsp;<span title="etdkm">Doknr.</span></td>
				 					<td class="text14" colspan="4" >
										<input readonly type="text" class="inputTextMediumBlue" name="etdkm" id="etdkm" size="30" maxlength="50" value="${model.record.etdkm}">
										&nbsp;Type&nbsp;<input readonly type="text" class="inputTextMediumBlue" name="etdkmt" id="etdkmt" size="6" maxlength="4" value="${model.record.etdkmt}">
									</td>
				 				</tr>
				 				<tr height="2"><td>&nbsp;</td></tr>	
				 				<tr >
				 					<td class="text14">&nbsp;<span title="etktkd">Mode of Transport</span></td>
				 					<td class="text14">&nbsp;<span title="etktyp">Type of Identification</span></td>
									<td class="text14">&nbsp;<span title="etktm">Type of Means of Transport</span></td>
									<td class="text14">&nbsp;<span title="etklk">Land code</span></td>
				 				</tr>
				 				<tr >
				 					<td class="text14">
										<input readonly type="text" class="inputTextMediumBlue" name="etktkd" id="etktkd" size="2" maxlength="1" value="${model.record.etktkd}">
									</td>
									<td>
										<input readonly type="text" class="inputTextMediumBlue" name="etktyp" id="etktyp" size="9" maxlength="35" value="${model.record.etktyp}">
									</td>
									
						 			<td class="text14">
						 				<input readonly type="text" class="inputTextMediumBlue" name="etktm" id="etktm" size="5" maxlength="4" value="${model.record.etktm}">
										
						 			</td>
						 			<td class="text14">
						 				<input readonly type="text" class="inputTextMediumBlue" name="etklk" id="etklk" size="5" maxlength="4" value="${model.record.etklk}">
						 				
						 			</td>
						 			
				 				</tr>
				 				
			 				
			 				<tr height="2"><td>&nbsp;</td></tr>
			 				<tr>
			 					<td colspan="2" class="text14">&nbsp;<span title="etsjaf">Fører-navn</span></td>
								<td colspan="2" class="text14">&nbsp;<span title="etems">Fører-epost / Telefon</span></td>
								
			 				</tr>
			 				<tr >
					 			<td colspan="2" class="text14">
					 				<input readonly type="text" class="inputTextMediumBlue" name="etsjaf" id="etsjaf" size="30" maxlength="50" value="${model.record.etsjaf}">
					 			</td>
								<td colspan="2" class="text14">
									<input readonly type="text" class="inputTextMediumBlue" name="etems" id="etems" size="30" maxlength="50" value="${model.record.etems}">
								</td>
			 				</tr>
			 				
			 				
			 				
			 				
			 				<tr>
			 					<td class="text14">&nbsp;<span title="etetad">ETA</span></td>
								<td class="text14">&nbsp;<span title="etetat">ETA-Tid</span></td>
								<td class="text14">&nbsp;<span title="ettsd">Pass.tollsted</span></td>
			 				</tr>
			 				<tr >
			 					
					 			<td class="text14">
					 				<c:choose>
					 				<c:when test="${model.record.etetad != '0'}">
					 					<input readonly type="text" class="inputTextMediumBlue" name="etetad" id="etetad" size="7" maxlength="6" value="${model.record.etetad}">
					 				</c:when>	
					 				<c:otherwise>
					 					<input readonly type="text" class="inputTextMediumBlue" name="etetad" id="etetad" size="7" maxlength="6" value="">
					 				</c:otherwise>
					 				</c:choose>
					 			</td>
								<td>
									<c:choose>
					 				<c:when test="${model.record.etetat != '0'}">
					 					<input readonly type="text" class="inputTextMediumBlue" name="etetat" id="etetat" size="7" maxlength="6" value="${model.record.etetat}">
					 				</c:when>
					 				<c:otherwise>
					 					<input readonly type="text" class="inputTextMediumBlue" name="etetat" id="etetat" size="7" maxlength="6" value="">
					 				</c:otherwise>
					 				</c:choose>
									
								</td>
			 					<%-- 
					 			<td class="text14">
					 				<c:choose>
					 				<c:when test="${Xmodel.record.eftsd != '0'}">
					 					<input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="eftsd" id="eftsd" size="5" maxlength="4" value="${Xmodel.record.eftsd}">
					 				</c:when>
					 				<c:otherwise>
					 					<input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="eftsd" id="eftsd" size="5" maxlength="4" value="">
					 				</c:otherwise>
					 				</c:choose>
					 				<a tabindex="-1" id="eftsdIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
					 			</td>
					 			--%>
					 			<%-- 
								<td>
									<input size="14" maxlength="8" class="selectMediumBlueE2" list="todo_list" id="todo" name="todo" value="${model.record.ettsd}">
				 					<datalist id="todo_list">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="record" items="${model.custOfficeList}" >
					 				  		<option title="${record.ztxt}" value="${record.zkod}"<c:if test="${model.record.ettsd == record.zkod}"> selected </c:if> >${record.zkod}</option>
										</c:forEach>  
									</datalist>
					 				
								</td>
								--%>
								<td>
									<input readonly type="text" class="inputTextMediumBlue" name="ettsd" id="ettsd" size="9" maxlength="8" value="${model.record.ettsd}">
								</td>
			 				</tr>
			 				<tr height="2"><td>&nbsp;</td></tr>
			 				
			 				
			            </table>
			            </td>
		            </tr>
	            
	            </table>
            </td>
            
           	<td style="width:33%;" class="text14" valign="top">
				<table style="width:95%;" align="left" border="0" cellspacing="1" cellpadding="0">
				 	<tr >
					 	<td >
						<table class="formFrameHeader" style="width:100%; border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<td class="text14White">&nbsp;&nbsp;Transportør&nbsp;</td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
		            <tr >
					 	<td>
						<table style="width:100%;" class="formFrame" border="0" cellspacing="1" cellpadding="0">
					 		<tr>
				 				<td>
				 				<table>
				 				<tr >
									<td class="text14">&nbsp;<span title="etnat">Navn</span></td>
									<td class="text14">&nbsp;<span title="efkmrk">Kjøretøy kjennemerke</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="etnat" id="etnat" size="35" maxlength="30" value="${model.record.etnat}"></td>
									<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="etkmrk" id="etkmrk" size="15" maxlength="17" value="${model.record.etkmrk}"></td>
									
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="etpst">Sted</span></td>
									<td class="text14">&nbsp;<span title="efkmrk">Landkode</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="etpst" id="etpst" size="25" maxlength="24" value="${model.record.etpst}"></td>
									<td class="text14">
										<input readonly type="text" class="inputTextMediumBlue" name="etlkt" id="etlkt" size="3" maxlength="2" value="${model.record.etlkt}">
										<%-- 
						 				<select readonly class="inputTextMediumBlue" name="etlkt" id="etlkt">
					 						<option value="">-velg-</option>
						 				  	<c:forEach var="country" items="${Xmodel.countryCodeList}" >
						 				  		<option title="${country.ztxt}" value="${country.zkod}"<c:if test="${Xmodel.record.etlkt == country.zkod}"> selected </c:if> >${country.zkod}</option>
											</c:forEach>  
										</select>
										--%>
						 			</td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="etemt">E-post</span></td>
									<td class="text14">&nbsp;<span title="etemt">Telefon</span></td>
									
				 				</tr>
				 				<tr >
				 					<c:choose>
				 					<c:when test="${model.record.etemtt == 'EM'}">
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="etemt" id="etemt" size="35" maxlength="50" value="${model.record.etemt}"></td>
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="empty" id="empty" size="35" maxlength="50" value=""></td>
				 					</c:when>
				 					<c:otherwise>
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="empty" id="empty" size="35" maxlength="50" value=""></td>
										<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="etemt" id="etemt" size="35" maxlength="50" value="${model.record.etemt}"></td>
									</c:otherwise>
									</c:choose>
				 				</tr>
				 				<tr height="2"><td>&nbsp;</td></tr>
				 				
				 				</table>
				 				</td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
	            </table>
            </td>
            
            
            <td style="width:33%" class="text14" valign="top">
				<table style="width:95%" align="left" border="0" cellspacing="1" cellpadding="0">
				 	<tr >
					 	<td >
						<table class="formFrameHeader" style="width:100%;" border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<td class="text14White">&nbsp;&nbsp;Representant / Ombud&nbsp;</td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
		            <tr >
					 	<td>
						<table style="width:100%;" class="formFrame" border="0" cellspacing="1" cellpadding="0">
					 		<tr>
				 				<td>
				 				<table>
				 				<tr >
									<td class="text14">&nbsp;<span title="etnar">Navn</span></td>
									<td class="text14">&nbsp;<span title="etrgr">Orgnr / EORI</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="etnar" id="etnar" size="25" maxlength="30" value="${model.record.etnar}"></td>
									<td class="text14"><input readonly type="text" class="inputTextReadOnly" name="etrgr" id="etrgr" size="20" maxlength="17" value="${model.record.etrgr}"></td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="etpsr">Sted</span></td>
									<td class="text14">&nbsp;<span title="etlkr">Landkode</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14">
										<input type="text" class="inputTextMediumBlue" name="etpsr" id="etpsr" size="25" maxlength="24" value="${model.record.etpsr}">
									</td>
									<td class="text14">
						 				<input type="text" class="inputTextMediumBlue" name="etlkr" id="etlkr" size="4" maxlength="2" value="${model.record.etlkr}">
						 			</td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="etemr">E-post</span></td>
									<td class="text14">&nbsp;<span title="etemr">Telefon</span></td>
									
				 				</tr>
				 				<tr >
									<c:choose>
				 					<c:when test="${model.record.etemrt == 'EM'}">
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="etemr" id="etemr" size="35" maxlength="50" value="${model.record.etemr}"></td>
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="empty" id="empty" size="35" maxlength="50" value=""></td>
				 					</c:when>
				 					<c:otherwise>
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="empty" id="empty" size="35" maxlength="50" value=""></td>
										<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="etemr" id="etemr" size="35" maxlength="50" value="${model.record.etemr}"></td>
									</c:otherwise>
									</c:choose>
				 				</tr>
				 				<tr height="2"><td>&nbsp;</td></tr>
				 				
				 				</table>
				 				</td>
			 				</tr>
		 				</table>
			            </td>
		            </tr>
	            </table>
            </td>			 
		</tr>
		<tr height="10"><td></td></tr>
		<%--
		<c:if test="${Xmodel.record.efst != 'S'}">
			<tr>
				<td colspan="3" class="text14" valign="top">
					<table style="width:96%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td align="right" >
							<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'>
							<c:if test="${not empty Xmodel.record.efuuid && empty Xmodel.invalidManifest}">
								<%-- &nbsp;<input class="inputFormSubmit" type="button" name="sendButton" id="sendButton" value='Send'> 
							</c:if>
							<%-- Due to emergencies ... we remove validations
							<c:choose>
								<c:when test="${model.record.own_editable > 0}">
									<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'>
									<c:if test="${not empty model.record.efuuid && empty model.invalidManifest}">
										&nbsp;<input class="inputFormSubmit" type="button" name="sendButton" id="sendButton" value='Send'>
										
									</c:if>
								</c:when>
								<c:otherwise>
									<input title="Status combination or date = blocked" class="inputFormSubmitStd isa_info" type="button" name="fakeButton" id="fakeButton" value='<spring:message code="systema.tvinn.sad.manifest.disabled.button"/>'>
								</c:otherwise>
							</c:choose>
							 
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</c:if>
		<tr height="20"><td colspan="2">&nbsp;</td></tr>
		 --%>
		 
	</table>
	</td>
	</tr>	
	</table> 
	</form>
</td>
</tr>


	<%-- list component --%>
	<tr>
		<td>		
		<table style="width:100%;" border="0" >
	    	<%-- separator --%>
	        <tr height="1"><td></td></tr> 
			<tr>
				<td>
				<table style="width:100%;" id="containerdatatableTable" cellspacing="2" align="left" >
				<tr>
				<td class="text11">
							
				<table id="mainList" class="display compact cell-border" >
					<thead>
					<tr class="tableHeaderField" height="20" >
                    	<th width="2%" class="tableHeaderFieldFirst" ><img title="Update" style="vertical-align:middle;" src="resources/images/update.gif" border="0" alt="edit"></th>
                    	<th width="2%" class="tableHeaderField" >Lnr</th>
                    	<th width="2%" class="tableHeaderField" >Avd</th>
                		<th width="2%" class="tableHeaderField" >Turnr</th>
                		<th width="2%" class="tableHeaderField" >Sig</th>
                		<th title="S=SLETTET" width="2%" class="tableHeaderField" >St.</th>
                		<th width="2%" class="tableHeaderField" >Br.vekt</th>
                		<th width="2%" class="tableHeaderField" >Doknr.</th>
                		<th width="2%" class="tableHeaderField" >Dokt.</th>
                		<th width="2%" class="tableHeaderField" >Mottaker</th>
                		<th width="2%" class="tableHeaderField" >Avsender</th>
                		<th width="2%" class="tableHeaderField" >Reg.dato</th>
                		<th width="2%" class="tableHeaderField" >Sen.dato</th>
                		<th width="2%" class="tableHeaderField" >MRN-Api</th>
                		<th width="2%" class="tableHeaderField" >Req.id</th>
                		<th title="S=SUBMITTED,R=REOPENED/DRAFT,D=SLETTET,C=COMPLETED" width="2%" class="tableHeaderField" >Manif.st</th>
                		<th width="2%" class="tableHeaderField" title="Fjerner manifest fra Tollvesenet" >Slett</th>
                		<th width="2%" class="tableHeaderField" title="Fjerner manifest lokalt (SYSPED)">Kans.</th>
                		</tr>
                	</thead>
                	<tbody> 
                	<c:forEach items="${model.record.listMasters}" var="masterConsignmentRecord" varStatus="counter">    
		              <c:choose> 
		              	  <%-- if the manifest is correct with all cargo lines OR the manifest has been SUBMITTED(S) or DELETED(D) don´t show it as a warning-line --%>	   
			              <c:when test="${XmasterConsignmentRecord.own_valid > 0 || XmasterConsignmentRecord.efst2 == 'S' || XmasterConsignmentRecord.efst2 == 'D' }">
			              	<tr class="tableRow" height="20" >
			          	  </c:when>
			          	  <c:otherwise>
			          	  	<%-- <tr class="tableRow" style="background-color: #FEEFB3;color:#9F6000;" height="20" >  --%>
			          	  	<tr class="tableRow" height="20" >
			          	  </c:otherwise>
		          	  </c:choose>	
		          
		          	   <td width="2%" class="tableCellFirst" align="center">
		          	   		<a style="display: block; width: 100%; height: 100%;"  href="tvinnsaddigitollv2_edit_master.do?action=doFind&emlnrt=${masterConsignmentRecord.emlnrt}&emlnrm=${masterConsignmentRecord.emlnrm}" onClick="setBlockUI();">
               					<c:choose>
		               				<c:when test="${XmasterConsignmentRecord.own_editable > 0}">
		               					<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="edit">
		               				</c:when>
		               				<c:otherwise>
		               					<img title="Read" style="vertical-align:bottom;" src="resources/images/eye.png" height="18px" width="18px" border="0" alt="read">
		               				</c:otherwise>
	               				</c:choose>
               				</a>
               				
	               	   </td>
	               	   <td width="2%" align="center" class="tableCell" >${masterConsignmentRecord.emlnrm}</td>
		               <td width="2%" align="center" class="tableCell" >${masterConsignmentRecord.emavd}</td>
		               <td width="2%" align="center" class="tableCell" ><c:if test="${masterConsignmentRecord.empro > 0}">${masterConsignmentRecord.empro}</c:if></td>
		               <td width="2%" align="center" class="tableCell" >${masterConsignmentRecord.emsg}</td>
		               <td width="2%" align="center" class="tableCell" >
		               	  <c:choose>
		               		<c:when test="${masterConsignmentRecord.emst == 'S'}">
		               			<font class="inputFormSubmit isa_error">KANSELLERT</font>
		               		</c:when>
		               		<c:otherwise>
		               			${masterConsignmentRecord.emst}
		               		</c:otherwise>
		               	   </c:choose>
		              	</td>
		               <td align="right" class="tableCell" >${masterConsignmentRecord.emvkb}</td>
		               <td align="right" class="tableCell" >${masterConsignmentRecord.emdkm}</td>
		               <td align="right" class="tableCell" >${masterConsignmentRecord.emdkmt}</td>
		               
		               <td align="center" class="tableCell" >${masterConsignmentRecord.emnam}&nbsp;-&nbsp;${masterConsignmentRecord.empsm}&nbsp;${masterConsignmentRecord.emlkm}</td>
		               <td align="center" class="tableCell" >${masterConsignmentRecord.emnas}&nbsp;-&nbsp;${masterConsignmentRecord.empss}&nbsp;${masterConsignmentRecord.emlks}</td>
		               <td class="tableCell" ><c:if test="${masterConsignmentRecord.emdtr > 0}">${masterConsignmentRecord.emdtr}</c:if></td>
		               <td class="tableCell" ><c:if test="${masterConsignmentRecord.emdtin > 0}">${masterConsignmentRecord.emdtin}</c:if></td>
		               
		               <%--
		               <td width="2%" class="tableCell" ><font style="font-size:11px;">${record.efuuid}</font></td>
		                --%>
		               <td class="tableCell" ><span class="text14SkyBlue">
		               		<a style="display: block; width: 100%; height: 100%; cursor:pointer" class="uuidLink text12SkyBlue" id="${masterConsignmentRecord.emmid}">
								${masterConsignmentRecord.emmid}
							</a>
		               </td>
		               		
		               <td class="tableCell" title="check status in toll.no">
		               		<a style="display: block; width: 100%; height: 100%; cursor:pointer" class="uuidLink text12SkyBlue" id="${masterConsignmentRecord.emuuid}">
								${masterConsignmentRecord.emuuid}
							</a>  
		               </td>
		               
		               <td align="center" class="tableCell" >
		               		<c:choose>
		               		<c:when test="${masterConsignmentRecord.emst2 == 'S' || masterConsignmentRecord.emst2 == 'R' || masterConsignmentRecord.emst2 == 'D' || masterConsignmentRecord.emst2 == 'C'}">
		               			<c:if test="${masterConsignmentRecord.emst2 == 'S'}">
		               				<img src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
		               				<span title="S" >SUBMITTED</span>
		               			</c:if>
		               			<c:if test="${masterConsignmentRecord.emst2 == 'R'}">
		               				<span title="R" >REOPENED/DRAFT</span>
		               			</c:if>
		               			<c:if test="${masterConsignmentRecord.emst2 == 'D'}">
		               				<font title="D" color="red">SLETTET</font>
		               			</c:if>
		               			<c:if test="${masterConsignmentRecord.emst2 == 'C'}">
		               				<img style="vertical-align:middle;" title="Completed tolldekl at toll.no" src="resources/images/complete-icon.png" width="14px" height="12px" border="0" alt="completion">
		               				<font title="C" color="green">COMPLETED</font>
		               			</c:if>
		               			
		               		</c:when>
		               		<c:otherwise>
		               			${masterConsignmentRecord.emst2}
		               		</c:otherwise>
		               		</c:choose>
		               </td>

		               <td width="2%" class="tableCell" align="center"> 
		               		  		
				   				<c:if test="${XmasterConsignmentRecord.own_editable > 0}">
		              				<a style="display: block; width: 100%; height: 100%;" class="removeLink" id="removeLink${counter.count}" runat="server" href="#">
										<img src="resources/images/delete.gif" border="0" alt="remove">
									</a>
									<div style="display: none;" class="clazz_dialog" id="dialogUpdateStatus${counter.count}" title="Dialog">
										<form action="tvinnsadmanifest_edit_delete.do" name="updateStatusForm${counter.count}" id="updateStatusForm${counter.count}" method="post">
										 	<input type="hidden" name="currentUuid${counter.count}" id="currentUuid${counter.count}" value="${XmasterConsignmentRecord.efuuid}">
										 	<input type="hidden" name="selectedStatus${counter.count}" id="selectedStatus${counter.count}" value="D">
										 	<input type="hidden" name="selectedPro${counter.count}" id="selectedPro${counter.count}" value="${XmasterConsignmentRecord.efpro}">
											<p class="text14" >Er du sikker på at du vil slette Turnr. <b>${Xrecord.efpro}</b> fra <b>Tollvesenet</b> ?</p>
											
										</form>
									</div>
	              				</c:if>
              				
	               	   </td>
	               	   <td width="2%" class="tableCell" align="center">
	               	   		<c:if test="${XmasterConsignmentRecord.efst == 'M' || empty XmasterConsignmentRecord.efst}">   		
				   				<a style="display: block; width: 100%; height: 100%;" class="cancelLink" id="cancelLink${counter.count}" runat="server" href="#">
									<img src="resources/images/remove.png" width="16" height="16" border="0" alt="remove">
								</a> 
								<div id="dialogUpdateInternalStatus${counter.count}" class="clazz_dialog" title="Dialog">
									<form action="tvinnsadmanifest_updateInternalStatus.do" name="updateInternalStatusForm${counter.count}" id="updateInternalStatusForm${counter.count}" method="post">
									 	<input type="hidden" name="currentUuid${counter.count}" id="currentUuid${counter.count}" value="${XmasterConsignmentRecord.efuuid}">
									 	<input type="hidden" name="currentSign${counter.count}" id="currentSign${counter.count}" value="${XmasterConsignmentRecord.efsg}">
									 	<input type="hidden" name="selectedStatus${counter.count}" id="selectedStatus${counter.count}" value="S">
									 	<p class="text14" >Er du sikker på at du vil kansellere Turnr. <b>${XmasterConsignmentRecord.efpro}</b> fra <b>SYSPED</b> ?</p>
											
									</form>
								</div>
							</c:if>
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










<%-- Dialog update manifest status --%>		
<tr>
	<td>
		<div id="dialogUpdateManifestStatus" title="Dialog">
			
			<form action="tvinnsadmanifest_updateManifestStatus.do" name="updateManifestStatusForm" id="updateManifestStatusForm" method="post">
			 	<input type="hidden" name="efuuid" id="efuuid" value="${Xmodel.record.efuuid}">
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
			 	<input type="hidden" name="efuuid" id="efuuid" value="${Xmodel.record.efuuid}">
			 	<p class="text14" >Change Internal status as needed.</p>
				<table>
					<tr>
						<td class="text14" align="left" >&nbsp;Status</td>
						<td class="text14MediumBlue">
							<select class="selectMediumBlueE2" name="efst" id="efst">
			            		  	<option value=" ">-velg-</option>
			            		  	<option value="B">B</option>
		            		  		<option value="C">C</option>
		            		  		<option value="M">M</option>
		            		  		<option value="S">SLETTET</option>
							  	<option value="X">X</option>
							  	<option value="Z">Z</option>
							  	
							  	
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</td>
</tr> 


</table>
 
 	
 
	