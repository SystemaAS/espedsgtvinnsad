<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_edit_house.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
						<a tabindex=-1 id="alinkManifestList" style="display:block;" href="tvinnsaddigitollv2.do?action=doFind&avd=${Xmodel.record.efavd}&sign=${Xmodel.record.efsg}">
							<font class="tabDisabledLink">&nbsp;Transportlist</font>
							<img src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkItems" style="display:block;" href="tvinnsaddigitollv2_edit_transport.do?action=doFetch&efpro=${Xmodel.record.efpro}&efsg=${Xmodel.record.efsg}
													&efavd=${Xmodel.record.efavd}&efuuid=${Xmodel.record.efuuid}">
							<font class="tabDisabledLink">
								&nbsp;Transport
							</font>
							
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkItems" style="display:block;" href="tvinnsaddigitollv2_edit_manifest.do?action=doFetch&efpro=${Xmodel.record.efpro}&efsg=${Xmodel.record.efsg}
													&efavd=${Xmodel.record.efavd}&efuuid=${Xmodel.record.efuuid}">
							<font class="tabDisabledLink">
								&nbsp;Manifest
							</font>
							
						</a>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td title="${Xmodel.record.efuuid}" width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;House
						</font>
						<font class="text14MediumBlue">[${XXmodel.record.efpro}]</font>
						<img src="resources/images/update.gif" border="0" alt="edit">
						
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkItems" style="display:block;" href="tvinnsadmanifest_logging.do?efpro=${Xmodel.record.efpro}&efsg=${Xmodel.record.efsg}
													&efavd=${Xmodel.record.efavd}&efuuid=${Xmodel.record.efuuid}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.manifest.created.header.logging.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					
					<td width="50%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			 
			 
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
					<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
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
			<td colspan="3" style="width:100%" class="text14" valign="top">
				<table style="width:60%" align="left" border="0" cellspacing="1" cellpadding="0">
					<tr height="4"><td>&nbsp;</td></tr>
					<tr>
						<td class="text14">&nbsp;<span title="todo">Total Bruttovekt</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="todo">Antall kolli</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="todo">Varebeskriv.</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="efkmrk">Dok.Id - fraktbrev</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="todo">Container</span><font class="text16RedBold" >*</font></td>
						
					</tr>
					<tr>	
						<td class="text14">
							<input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efkmrk" id="efkmrk" size="17" maxlength="17" value="${Xmodel.record.efkmrk}">									
						</td>
						
						<td class="text14">
							<input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efkmrk" id="efkmrk" size="10" maxlength="10" value="${Xmodel.record.efkmrk}">									
						</td>
						<td class="text14">
							<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efkmrk" id="efkmrk" size="25" maxlength="70" value="${Xmodel.record.efkmrk}">									
						</td>
						<td class="text14">
							<select required class="inputTextMediumBlueMandatoryField" name="todo" id="todo">
		 						<option value="N730"<c:if test="${Xmodel.record.efklk == 'N730'}"> selected </c:if> >N730</option>
		 						<option value="N741"<c:if test="${Xmodel.record.efklk == 'N741'}"> selected </c:if> >N741</option>
								 
							</select>
							<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efrgd" id="efrgd" size="20" maxlength="70" value="">
						</td>
						<td class="text14">
							<select required class="inputTextMediumBlueMandatoryField" name="todo" id="todo">
		 						<option value="0"<c:if test="${Xmodel.record.efklk == 0}"> selected </c:if> >0</option>
		 						<option value="1"<c:if test="${Xmodel.record.efklk == 1}"> selected </c:if> >1</option>
								 
							</select>										
						</td>
					</tr>
					<tr height="2"><td>&nbsp;</td></tr>
					
					<tr>
						<td colspan="2" class="text14">&nbsp;<span title="todo">Prosedyre - import</span></td>
						
					</tr>
					<tr>	
						<td colspan="2" >
							<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="clpr" id="clpr" >
		 						<option value="">-velg-</option>
				 				  	<c:forEach var="record" items="${model.prTypeList}" >
				 				  		<%--filter the goodsregistrering (03) so far --%>
				 				  		<c:if test="${record.kfkod != '03'}">
			                       	 		<option title="${record.kftxt}" value="${record.kfkod}" <c:if test="${Xmodel.record.clpr == record.kfkod}"> selected </c:if> >${record.kfkod}&nbsp;${record.kftxt}</option>
			                       	 	</c:if>
									</c:forEach>
							</select>
						</td>
						
						
					</tr>		
				</table>
			</td>
		</tr>
		
		<tr height="4"><td>&nbsp;</td></tr> 
 		<tr>
			<td style="width:33%" class="text14" valign="top">
				<table style="width:95%" align="left" border="0" cellspacing="1" cellpadding="0">
				 	<tr >
					 	<td >
						<table class="formFrameHeader" style="width:100%;" border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<td class="text14White">&nbsp;&nbsp;Avsender&nbsp;</td>
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
									<td class="text14">&nbsp;<span title="efkmrk">Navn</span></td>
									<td class="text14">&nbsp;<span title="efkmrk">ID-type</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="efkmrk" id="efkmrk" size="25" maxlength="70" value="${Xmodel.record.efkmrk}"></td>
									<td class="text14">
										<select class="inputTextMediumBlueMandatoryField" name="todo" id="todo" style="width:100px;">
					 						<option value="O">Org.nr</option>
					 						<option value="E">EORI</option>
					 						
										</select>
										&nbsp;<input type="text" class="inputTextMediumBlueMandatoryField" name="efrgd" id="efrgd" size="20" maxlength="35" value="">
									</td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="efkmrk">Sted</span></td>
									<td class="text14">&nbsp;<span title="efkmrk">Landkode</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="efkmrk" id="efkmrk" size="30" maxlength="35" value="${Xmodel.record.efkmrk}"></td>
									<td class="text14">
						 				<select required class="inputTextMediumBlue" name="efklk" id="efklk">
					 						<option value="">-velg-</option>
						 				  	<c:forEach var="country" items="${Xmodel.countryCodeList}" >
						 				  		<option title="${country.ztxt}" value="${country.zkod}"<c:if test="${Xmodel.record.efklk == country.zkod}"> selected </c:if> >${country.zkod}</option>
											</c:forEach>  
										</select>
						 			</td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="efkmrk">E-post</span></td>
									<td class="text14">&nbsp;<span title="efkmrk">Telefon</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="efkmrk" id="efkmrk" size="30" maxlength="35" value="${Xmodel.record.efkmrk}"></td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="efkmrk" id="efkmrk" size="30" maxlength="35" value="${Xmodel.record.efkmrk}"></td>
									
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
					 			<td class="text14White">&nbsp;&nbsp;Mottaker&nbsp;</td>
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
									<td class="text14">&nbsp;<span title="efkmrk">Navn</span></td>
									<td class="text14">&nbsp;<span title="efkmrk">ID-type</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efkmrk" id="efkmrk" size="25" maxlength="70" value="${Xmodel.record.efkmrk}"></td>
									<td class="text14">
										<select class="inputTextMediumBlueMandatoryField" name="todo" id="todo" style="width:100px;">
					 						<option value="O">Org.nr</option>
					 						<option value="E">EORI</option>
					 						
										</select>
										&nbsp;<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efrgd" id="efrgd" size="20" maxlength="35" value="">
									</td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="efkmrk">Sted</span></td>
									<td class="text14">&nbsp;<span title="efkmrk">Landkode</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efkmrk" id="efkmrk" size="30" maxlength="35" value="${Xmodel.record.efkmrk}"></td>
									<td class="text14">
						 				<select required class="inputTextMediumBlueMandatoryField" name="efklk" id="efklk">
					 						<option value="">-velg-</option>
						 				  	<c:forEach var="country" items="${Xmodel.countryCodeList}" >
						 				  		<option title="${country.ztxt}" value="${country.zkod}"<c:if test="${Xmodel.record.efklk == country.zkod}"> selected </c:if> >${country.zkod}</option>
											</c:forEach>  
										</select>
						 			</td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="efkmrk">E-post</span></td>
									<td class="text14">&nbsp;<span title="efkmrk">Telefon</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="efkmrk" id="efkmrk" size="30" maxlength="35" value="${Xmodel.record.efkmrk}"></td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="efkmrk" id="efkmrk" size="30" maxlength="35" value="${Xmodel.record.efkmrk}"></td>
									
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
									<td class="text14">&nbsp;<span title="efkmrk">Navn</span></td>
									<td class="text14">&nbsp;<span title="efkmrk">ID-type</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="efkmrk" id="efkmrk" size="25" maxlength="70" value="${Xmodel.record.efkmrk}"></td>
									<td class="text14">
										<select class="inputTextMediumBlue" name="todo" id="todo" style="width:100px;">
					 						<option value="O">Org.nr</option>
					 						<option value="E">EORI</option>
					 						
										</select>
										&nbsp;<input readonly type="text" class="inputTextReadOnly" name="efrgd" id="efrgd" size="20" maxlength="35" value="">
									</td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="efkmrk">Sted</span></td>
									<td class="text14">&nbsp;<span title="efkmrk">Landkode</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="efkmrk" id="efkmrk" size="30" maxlength="35" value="${Xmodel.record.efkmrk}"></td>
									<td class="text14">
						 				<select required class="inputTextMediumBlue" name="efklk" id="efklk">
					 						<option value="">-velg-</option>
						 				  	<c:forEach var="country" items="${Xmodel.countryCodeList}" >
						 				  		<option title="${country.ztxt}" value="${country.zkod}"<c:if test="${Xmodel.record.efklk == country.zkod}"> selected </c:if> >${country.zkod}</option>
											</c:forEach>  
										</select>
						 			</td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="efkmrk">E-post</span></td>
									<td class="text14">&nbsp;<span title="efkmrk">Telefon</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="efkmrk" id="efkmrk" size="30" maxlength="35" value="${Xmodel.record.efkmrk}"></td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="efkmrk" id="efkmrk" size="30" maxlength="35" value="${Xmodel.record.efkmrk}"></td>
									
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
		<c:if test="${Xmodel.record.efst != 'S'}">
			<tr>
				<td colspan="3" class="text14" valign="top">
					<table style="width:96%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td align="right" >
							<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'>
							<c:if test="${not empty Xmodel.record.efuuid && empty Xmodel.invalidManifest}">
								&nbsp;<input class="inputFormSubmit" type="button" name="sendButton" id="sendButton" value='Send'>
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
							 --%>
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
 
 	
 
	