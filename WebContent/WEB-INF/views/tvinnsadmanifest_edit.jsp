<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmanifest_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
	
<table width="100%" cellspacing="0" border="0" cellpadding="0">

 <tr>
 <td>	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<c:choose> 
			    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkManifestList" tabindex=-1 style="display:block;" href="tvinnsadmanifest.do?action=doFind&avd=${model.record.tiavd}&sign=${model.record.tisg}&opd=${model.record.titdn}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.manifest.list.tab"/></font>
							<img src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;<spring:message code="systema.tvinn.sad.manifest.created.header.tab"/>
						</font>
						<font class="text14MediumBlue">[${Xmodel.record.titdn}]</font>
						<img src="resources/images/update.gif" border="0" alt="edit">
						
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 style="display:block;" href="tvinnsadmanifest_edit_items.do?action=doFetch&avd=${Xmodel.record.tiavd}&sign=${Xmodel.record.tisg}
													&opd=${Xmodel.record.titdn}">
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
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<tr height="10"><td colspan="10">&nbsp;</td></tr>
 		
		<%-- --------------- --%>
		<%-- CONTENT --%>
		<%-- --------------- --%>
		<tr>
		<td >
		<table align="center" width="100%" border="0" cellspacing="1" cellpadding="0">
			
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
					 			<td class="text14White">&nbsp;&nbsp;Transport&nbsp;</td>
					 			<td class="text14" align="right">Transportør - Systema&nbsp;</td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
		            <tr >
					 	<td>
						<table style="width:100%" class="formFrame" border="0" cellspacing="1" cellpadding="0">
			 				<tr >
								<td class="text14">&nbsp;<span title="titin">Transportmåte</span><font class="text16RedBold" >*</font></td>
			 				</tr>
			 				<tr >
					 			<td class="text14">
					 				<select class="inputTextMediumBlueMandatoryField" name="svlk" id="svlk" style="width:100px;">
				 						<option value="">Velg</option>
		 		 				  		<option value="30">30 Bil</option>
									</select>
					 			</td>
			 				</tr>
			 				<tr >
								<td class="text14">&nbsp;<span title="titin">ID-type kjøretøyeier</span><font class="text16RedBold" >*</font></td>
			 				</tr>
			 				<tr >
					 			<td class="text14">
					 				<select class="inputTextMediumBlueMandatoryField" name="svlk" id="svlk" style="width:100px;">
				 						<option value="">Velg</option>
		 		 				  		<option value="O">Org.nr</option>
		 		 				  		<option value="E">EORI</option>
									</select>
									&nbsp;&nbsp;<input type="text" class="inputTextMediumBlueMandatoryField" name="titin" id="titin" size="20" maxlength="25" value="${Xmodel.record.titin}">
								</td>
			 				</tr>
			 				<tr >
								<td class="text14">&nbsp;<span title="titin">Kjøretøyeier</span><font class="text16RedBold" >*</font></td>
			 				</tr>
			 				<tr >
					 			<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="titin" id="titin" size="45" maxlength="35" value="${Xmodel.record.titin}"></td>
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
					 			<td class="text14">&nbsp;<span title="tikn">Kjøretøytype</span></td>
					 			<td class="text14">&nbsp;</td>
			 				</tr>
			 				<tr >
					 			<td class="text14">
					 				<select class="inputTextMediumBlueMandatoryField" name="svlk" id="svlk" >
				 						<option value="">Velg</option>
		 		 				  		<option value="1">Kjøretøy med henger</option>
		 		 				  		<option value="2">Kjøretøy med semitrailer og henger</option>
		 		 				  		<option value="3">Kjøretøy uten henger</option>
									</select>
					 			</td>
			 				</tr>
			 				<tr >
								<td class="text14">&nbsp;<span title="titin">Kjøretøy nasjonalitet</span><font class="text16RedBold" >*</font></td>
			 				</tr>
			 				<tr >
					 			<td class="text14">
					 				<select class="inputTextMediumBlueMandatoryField" name="svlk" id="svlk" style="width:100px;">
				 						<option value="">Velg</option>
		 		 				  		<option value="SE">Sverige</option>
		 		 				  		<option value="NO">Norge</option>
									</select>
					 			</td>
			 				</tr>
			 				<tr >
								<td class="text14">&nbsp;<span title="titin">Kjøretøy kjennemerke</span><font class="text16RedBold" >*</font></td>
			 				</tr>
			 				<tr >
					 			<td colspan="2" class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="titin" id="titin" size="15" maxlength="20" value="${Xmodel.record.titin}"></td>
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
					 			<td class="text14">&nbsp;<span title="tikn">Fornavn</span><font class="text16RedBold" >*</font></td>
			 				</tr>
			 				<tr >
					 			<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="tikn" id="tikn" size="35" maxlength="35" value="${Xmodel.record.tikn}"></td>
			 				</tr>
			 				<tr >
								<td class="text14">&nbsp;<span title="titin">Etternavn</span><font class="text16RedBold" >*</font></td>
			 				</tr>
			 				<tr >
					 			<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="titin" id="titin" size="35" maxlength="35" value="${Xmodel.record.titin}"></td>
			 				</tr>
			 				<tr >
								<td class="text14">&nbsp;<span title="titin">Statsborger i</span><font class="text16RedBold" >*</font></td>
			 				</tr>
			 				<tr >
					 			<td class="text14">
					 				<select class="inputTextMediumBlueMandatoryField" name="svlk" id="svlk" style="width:100px;">
				 						<option value="">Velg</option>
		 		 				  		<option value="SE">Sverige</option>
		 		 				  		<option value="NO">Norge</option>
									</select>
					 			</td>
			 				</tr>
			 				<tr height="15"><td colspan="2">&nbsp;</td></tr>
			 				<tr >
								<td class="text14">&nbsp;<span title="titin">Kort beskrivelse</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text14"><input type="text" class="inputTextMediumBlue" name="titin" id="titin" size="50" maxlength="50" value="${Xmodel.record.titin}"></td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
	            </table>
            </td>			 
		</tr>
		<tr height="10"><td></td></tr>
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
		<tr height="20"><td colspan="2">&nbsp;</td></tr>
	
</table>
</td>
</tr>
		
</table> 
</form>
</td>
</tr>
</table>
 
 	
 
	