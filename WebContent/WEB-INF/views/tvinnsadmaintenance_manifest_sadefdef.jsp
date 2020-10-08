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
	               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >   		
			   				
              				<a style="display:block;" class="removeLink" id="removeLink${counter.count}" runat="server" href="#">
								<img src="resources/images/delete.gif" border="0" alt="remove">
							</a>
							<div style="display: none;" class="clazz_dialog" id="dialogDelete${counter.count}" title="Dialog">
								<form action="tvinnsadmaintenance_manifest_sadefdef_delete.do" name="deleteForm${counter.count}" id="deleteForm${counter.count}" method="post">
								 	<input type="hidden" name="currentEfavd${counter.count}" id="currentEfavd${counter.count}" value="${record.efavd}">
								 	<input type="hidden" name="selectedStatus${counter.count}" id="selectedStatus${counter.count}" value="D">
								 	<p class="text14" >Er du sikker på at du vil slette Avd&nbsp;<b>${record.efavd}</b></p>
									
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
		 	<td>
		 		<input class="inputFormSubmitStd" type="button" name="newButton" id="newButton" value='Lage ny'>
			</td>
		</tr>	
		<tr>
		<td >
		<form name="manifestForm" id="manifestForm" action="tvinnsadmaintenance_manifest_sadefdef_edit.do" method="post">
			<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
			<input type="hidden" name="updateId" id="updateId" value=""> <%-- this value is set in AJAX in order to know if the SAVE = ADD or UPDATE --%>
			<input type="hidden" name="actionU" id="actionU" value="doUpdate">
			
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
						<table class="formFrameHeader" style="width:100%;"  border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<td class="text14White">&nbsp;&nbsp;Transport</td>
						 			
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
	</form>
	</td>
	</tr>	
	</table> 
</td>
</tr>



</table>
 
 	
 
	