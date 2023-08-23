<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_edit_master.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
						<a tabindex=-1 id="alinkManifestList" style="display:block;" href="tvinnsaddigitollv2.do?action=doFind">
							<font class="tabDisabledLink">&nbsp;Transportlist</font>
							<img src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkItems" style="display:block;" href="tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=${model.record.emlnrt}">													
							<font class="tabDisabledLink">
								&nbsp;Transport&nbsp;[${model.record.emlnrt}]
							</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td title="${model.record.emlnrm}" width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;Master
						</font>
						<font class="text14MediumBlue">[${model.record.emlnrm}]</font>
						<img src="resources/images/update.gif" border="0" alt="edit">
						
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkItems" style="display:block;" href="tvinnsaddigitollv2_edit_house.do?action=doFetch&efpro=${Xmodel.record.efpro}&efsg=${Xmodel.record.efsg}
													&efavd=${Xmodel.record.efavd}&efuuid=${Xmodel.record.efuuid}">
							<font class="tabDisabledLink">
								&nbsp;House
							</font>
							
						</a>
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
					
					<%-- <td width="50%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>  --%>
			 		<td width="50%" class="tabFantomSpace" align="right" nowrap><font class="tabDisabledLink">&nbsp;</font>
						<img id="imgInfoRpgJarStart" style="cursor:pointer;" onClick="showPop('jarStartCmd');" src="resources/images/info4.png" width="12" height="12" border="0" alt="info">
						<div class="text12" style="position: relative;display: inline;" align="left">
						<span style="position:absolute; left:-580px; top:3px;" id="jarStartCmd" class="popupWithInputText"  >
			           		<div class="text11" align="left">
			           			<b>Communication API</b>&nbsp;upload-engine-expressmanif-client.jar<br/>
			           			<p><b>Stop</b> go tvinmnu --> 2 ...</p>
			           			<p><b>Start</b> SBMJOB CMD(CALL PGM(SADEMSNDC)) JOB(EXPMANSND) JOBQ(SYJOBQNMAX) USER(SY400USR)
			           			</p>
			           			<p>
				           			<a class="text11" target="_blank" id="alinkHeader" style="display:block;" href="renderLocalLogsgExpft.do?user=${user.user}">
				           				logsg_syjservicestn-expft.log
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
						<td class="text14">&nbsp;<span title="emavd">Avd</span></td>
						<td class="text14">&nbsp;<span title="empro">Tur</span></td>
						<td class="text14">&nbsp;<span title="emvkb">Bruttovekt</span></td>
						<td class="text14">&nbsp;<span title="emvkb">Container</span></td>
						<td class="text14">&nbsp;<span title="emdkm">Dok.nr</span></td>
						<td class="text14">&nbsp;<span title="emdkmt">Dok.type</span></td>
						<td class="text14">&nbsp;<span title="emmid">MRN Api</span></td>
						<td class="text14">&nbsp;<span title="emuuid">Request id</span></td>
						<td class="text14">&nbsp;<span title="emst">St.</span></td>
						<td class="text14">&nbsp;<span title="emst2">St.2</span></td>
						<td class="text14">&nbsp;<span title="emst3">St.3</span></td>
						<td class="text14">&nbsp;<span title="emst3">Reg.dato</span></td>
						<td class="text14">&nbsp;<span title="emst3">Send.dato</span></td>
						
					</tr>
					<tr>	
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emavd" id="emavd" size="5" maxlength="4" value="${model.record.emavd}">									
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="empro" id="empro" size="10" maxlength="8" value="${model.record.empro}">									
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emvkb" id="emvkb" size="10" maxlength="9" value="${model.record.emvkb}">									
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emcn" id="emcn" size="2" maxlength="1" value="${model.record.emcn}">									
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emdkm" id="emdkm" size="25" maxlength="50" value="${model.record.emdkm}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emdkmt" id="emdkmt" size="5" maxlength="4" value="${model.record.emdkmt}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emmid" id="emmid" size="20" maxlength="18" value="${model.record.emmid}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emuuid" id="emuuid" size="38" maxlength="36" value="${model.record.emuuid}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emst" id="emst" size="2" maxlength="1" value="${model.record.emst}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emst2" id="emst2" size="2" maxlength="1" value="${model.record.emst2}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emst3" id="emst3" size="2" maxlength="1" value="${model.record.emst3}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emdtr" id="emdtr" size="10" maxlength="8" value="${model.record.emdtr}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emdtin" id="emdtin" size="10" maxlength="8" value="${model.record.emdtin}">		
						</td>
						
					</tr>
					<tr height="2"><td>&nbsp;</td></tr>
					
					<tr>
						<td colspan="3" class="text14">&nbsp;<span title="emsdlt">Lastested</span></td>
						<td class="text14">&nbsp;<span title="emlkl">Land</span></td>
						<td colspan="2" class="text14">&nbsp;<span title="emsdut">Lossested</span></td>
						<td class="text14">&nbsp;<span title="emlku">Land</span></td>
					</tr>
					<tr>	
						<td colspan="3" class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emsdlt" id="emsdlt" size="25" maxlength="30" value="${model.record.emsdlt}">								
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emlkl" id="emlkl" size="4" maxlength="2" value="${model.record.emlkl}">												
						</td>
						<td colspan="2" class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emsdut" id="emsdut" size="25" maxlength="30" value="${model.record.emsdut}">											
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emlku" id="emlku" size="4" maxlength="2" value="${model.record.emlku}">												
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
									<td class="text14">&nbsp;<span title="emnas">Navn</span></td>
									<td class="text14">&nbsp;<span title="emrgs">Org.nr /EORI</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="emnas" id="emnas" size="25" maxlength="30" value="${model.record.emnas}"></td>
									<td class="text14"><input readonly type="text" class="inputTextReadOnly" name="emrgs" id="emrgs" size="20" maxlength="17" value="${model.record.emnas}"></td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="empss">Sted</span></td>
									<td class="text14">&nbsp;<span title="emlks">Landkode</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="empss" id="empss" size="25" maxlength="24" value="${model.record.empss}"></td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="emlks" id="emlks" size="4" maxlength="2" value="${model.record.emlks}"></td>
									
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="emems">E-post</span></td>
									<td class="text14">&nbsp;<span title="emems">Telefon</span></td>
									
				 				</tr>
				 				<tr >
									<c:choose>
				 					<c:when test="${model.record.ememst == 'EM'}">
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="emems" id="emems" size="35" maxlength="50" value="${model.record.emems}"></td>
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="empty" id="empty" size="35" maxlength="50" value=""></td>
				 					</c:when>
				 					<c:otherwise>
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="empty" id="empty" size="35" maxlength="50" value=""></td>
										<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="emems" id="emems" size="35" maxlength="50" value="${model.record.emems}"></td>
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
 
 	
 
	