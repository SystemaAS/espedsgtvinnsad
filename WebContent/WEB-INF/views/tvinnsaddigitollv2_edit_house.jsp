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
	
<table style="width:90%;" cellspacing="0" border="0" cellpadding="0">

 <tr>
 <td>	
	<%-- tab container component --%>
	<table  style="width:100%;"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25">
			 
			 		<%-- TEMP --%>
			 
			 		<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkManifestList" style="display:block;" href="tvinnsaddigitollv2.do?action=doFind">
							<font class="tabDisabledLink">&nbsp;Transportliste</font>
							<img src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkItems" style="display:block;" href="tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=${model.record.ehlnrt}">													
							<font class="tabDisabledLink">
								&nbsp;Transport&nbsp;
								<c:if test="${model.record.ehlnrt > 0}">
									<font class="text14MediumBlue">&nbsp;${model.record.ehlnrt}</font>
								</c:if>
								
							</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td title="${model.record.ehlnrm}" width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkManifestList" style="display:block;" href="tvinnsaddigitollv2_edit_master.do?action=doFind&emlnrt=${model.record.ehlnrt}&emlnrm=${model.record.ehlnrm}">
							<font class="tabDisabledLink">
								&nbsp;Master
							</font>
							<img src="resources/images/update.gif" border="0" alt="edit">
							<c:if test="${model.record.ehlnrm > 0}">
								<font class="text14MediumBlue">&nbsp;${model.record.ehlnrm}</font>
							</c:if>
						</a>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td title="${model.record.ehlnrh}" width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;House
						</font>
						<img src="resources/images/update.gif" border="0" alt="edit">
						<c:if test="${model.record.ehlnrh > 0}">
							<font class="text14MediumBlue">&nbsp;${model.record.ehlnrh}</font>
						</c:if>
					</td>
					
					<td width="60%" class="tabFantomSpace" align="right" nowrap><font class="tabDisabledLink">&nbsp;</font>
						<img id="imgInfoRpgJarStart" style="cursor:pointer;" onClick="showPop('jarStartCmd');" src="resources/images/info4.png" width="12" height="12" border="0" alt="info">
						<div class="text12" style="position: relative;display: inline;" align="left">
						<span style="position:absolute; left:-580px; top:3px;" id="jarStartCmd" class="popupWithInputText"  >
			           		<div class="text11" align="left">
			           			<p>
				           			<a class="text11" target="_blank" href="renderLocalLogsgExpft.do?user=${user.user}">
				           				logsg_syjservicestn-expft.log
				           			</a>
			           			</p>
			           			
			           			<p>
				           			<a class="text11" target="_blank" href="renderLocalCatalina.do?user=${user.user}">
				           				catalina.out
				           			</a>
			           			</p>
			           			<br/>
			           			<button name="_ButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('jarStartCmd');">Close</button> 
			           		</div>
			           	</span>
			           	</div>
					</td>
			 
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
				<table style="width:90%" align="left" border="0" cellspacing="1" cellpadding="0">
					<tr height="4"><td>&nbsp;</td></tr>
					<tr>	
						<td nowrap colspan="10" class="text14 formFrame" >
		               		MRN-Api:&nbsp;${model.record.ehmid}&nbsp;&nbsp;&nbsp;
		               		Id:&nbsp;<a class="uuidLink text14SkyBlue" id="${model.record.ehuuid}">${model.record.ehuuid}</a>
	               		</td>
	         
					</tr>
					<tr height="1"><td>&nbsp;</td></tr>
					<tr>
						<td class="text14">&nbsp;<span title="ehavd">Avd</span></td>
						<td class="text14">&nbsp;<span title="ehpro">Tur</span></td>
						<td class="text14">&nbsp;<span title="ehvkb">Bruttovekt</span></td>
						<td class="text14">&nbsp;<span title="ehvt">Varebesk</span></td>
						<td class="text14">&nbsp;<span title="ehcnin">Container</span></td>
						<td class="text14">&nbsp;<span title="ehdkh">Dok.nr</span></td>
						<td class="text14">&nbsp;<span title="ehdkht">Dok.type</span></td>
						<td class="text14">&nbsp;<span title="ehst">St.</span></td>
						<td class="text14">&nbsp;<span title="ehst2">St.2</span></td>
						<td class="text14">&nbsp;<span title="ehst3">St.3</span></td>
						<td class="text14">&nbsp;<span title="ehdts">Send.dato</span></td>
						
						
						
						
						
					</tr>
					<tr>	
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emavd" id="emavd" size="5" maxlength="4" value="${model.record.ehavd}">									
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehpro" id="ehpro" size="10" maxlength="8" value="${model.record.ehpro}">									
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehvkb" id="ehvkb" size="10" maxlength="9" value="${model.record.ehvkb}">									
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehvt" id="ehvt" size="35" maxlength="50" value="${model.record.ehvt}">									
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehcnin" id="ehcnin" size="2" maxlength="1" value="${model.record.ehcnin}">									
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehdkh" id="ehdkh" size="25" maxlength="50" value="${model.record.ehdkh}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehdkht" id="ehdkht" size="5" maxlength="4" value="${model.record.ehdkht}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehst" id="ehst" size="2" maxlength="1" value="${model.record.ehst}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehst2" id="ehst2" size="2" maxlength="1" value="${model.record.ehst2}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehst3" id="ehst3" size="2" maxlength="1" value="${model.record.ehst3}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehdts" id="ehdts" size="10" maxlength="8" value="${model.record.ehdts}">		
						</td>
						
						
						
					</tr>
					
					<tr height="2"><td>&nbsp;</td></tr>
					<tr>
						<td colspan="3" class="text14">&nbsp;<span title="ehrg">Orgnr</span></td>
						<td class="text14">&nbsp;<span title="ehtrnr">Mrn-nr</span></td>
						<td colspan="2" class="text14">&nbsp;<span title="ehtrty">Type - CUDE</span></td>
						<td class="text14">&nbsp;<span title="eh0068a-eh0068b">S.dato-sekv.</span></td>
					</tr>
					<tr>
						<td colspan="3" class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehrg" id="ehrg" size="13" maxlength="11" value="${model.record.ehrg}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehtrnr" id="ehtrnr" size="22" maxlength="18" value="${model.record.ehtrnr}">		
						</td>
						<td colspan="2" class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehtrty" id="ehtrty" size="6" maxlength="4" value="${model.record.ehtrty}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="eh0068a-eh0068b" id="eh0068a-eh0068b" size="16" maxlength="15" value="${model.record.eh0068a}-${model.record.eh0068b}">		
						</td>
					</tr>
				
					<tr height="2"><td>&nbsp;</td></tr>
					<tr>
						<td colspan="3" class="text14">&nbsp;<span title="ehprt">Prosedyr</span></td>
						<td class="text14">&nbsp;<span title="ehuprt">Out.prosedyr</span></td>
						<td colspan="2" class="text14">&nbsp;<span title="ehetypt">Type</span></td>
						<td class="text14">&nbsp;<span title="eheid">Eksport id</span></td>
					</tr>
					<tr>
						<td colspan="3" class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehprt" id="ehprt" size="30" maxlength="30" value="${model.record.ehprt}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehuprt" id="ehuprt" size="30" maxlength="30" value="${model.record.ehuprt}">		
						</td>
						<td colspan="2" class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehetypt" id="ehetypt" size="30" maxlength="30" value="${model.record.ehetypt}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="eheid" id="eheid" size="25" maxlength="18" value="${model.record.eheid}">		
						</td>
					</tr>
					
					<tr height="2"><td>&nbsp;</td></tr>
					<tr>
						<td colspan="3" class="text14">&nbsp;<span title="ehsda">Place of Acceptance</span></td>
						<td class="text14">&nbsp;<span title="ehlka">Land</span></td>
						<td colspan="2" class="text14">&nbsp;<span title="ehsddt">Place of Delivery</span></td>
						<td class="text14">&nbsp;<span title="ehlkd">Land</span></td>
					</tr>
					<tr>	
						<td colspan="3" class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehsda" id="ehsda" size="25" maxlength="30" value="${model.record.ehsda}">								
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehlka" id="ehlka" size="4" maxlength="2" value="${model.record.ehlka}">												
						</td>
						<td colspan="2" class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehsddt" id="ehsddt" size="25" maxlength="30" value="${model.record.ehsddt}">											
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="ehlkd" id="ehlkd" size="4" maxlength="2" value="${model.record.ehlkd}">												
						</td>
						
					</tr>		
				</table>
			</td>
		</tr>
		
		<tr height="4"><td>&nbsp;</td></tr> 
 		<tr>
			<td class="text14" valign="top">
				<table style="width:85%" align="left" border="0" cellspacing="1" cellpadding="0">
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
									<td class="text14">&nbsp;<span title="ehnas">Navn</span></td>
									<td class="text14">&nbsp;<span title="ehrgs">Org.nr /EORI</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehnas" id="ehnas" size="25" maxlength="30" value="${model.record.ehnas}"></td>
									<td class="text14"><input readonly type="text" class="inputTextReadOnly" name="ehrgs" id="ehrgs" size="20" maxlength="17" value="${model.record.ehnas}"></td>
				 				</tr>
				 				
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="ehpss">Sted</span></td>
									<td class="text14">&nbsp;<span title="ehlks">Landkode</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehpss" id="ehpss" size="25" maxlength="24" value="${model.record.ehpss}"></td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehlks" id="ehlks" size="4" maxlength="2" value="${model.record.ehlks}"></td>
									
				 				</tr>
				 				<tr >
									<td class="text14">&nbsp;<span title="ehad1s">Adress</span></td>
									<td class="text14">&nbsp;<span title="ehpns">Postnr</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehad1s" id="ehad1s" size="25" maxlength="30" value="${model.record.ehad1s}"></td>
									<td class="text14"><input readonly type="text" class="inputTextReadOnly" name="ehpns" id="ehpns" size="12" maxlength="9" value="${model.record.ehpns}"></td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="ehems">E-post</span></td>
									<td class="text14">&nbsp;<span title="ehems">Telefon</span></td>
									
				 				</tr>
				 				<tr >
									<c:choose>
				 					<c:when test="${model.record.ehemst == 'EM'}">
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="ehems" id="ehems" size="35" maxlength="50" value="${model.record.ehems}"></td>
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="empty" id="empty" size="35" maxlength="50" value=""></td>
				 					</c:when>
				 					<c:otherwise>
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="empty" id="empty" size="35" maxlength="50" value=""></td>
										<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="ehems" id="ehems" size="35" maxlength="50" value="${model.record.ehems}"></td>
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
            
           	<td class="text14" valign="top">
				<table style="width:85%" align="left" border="0" cellspacing="1" cellpadding="0">
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
									<td class="text14">&nbsp;<span title="ehnam">Navn</span></td>
									<td class="text14">&nbsp;<span title="ehrgm">Org.nr /EORI</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehnam" id="ehnam" size="25" maxlength="30" value="${model.record.ehnam}"></td>
									<td class="text14"><input readonly type="text" class="inputTextReadOnly" name="ehrgm" id="ehrgm" size="20" maxlength="17" value="${model.record.ehnam}"></td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="ehpsm">Sted</span></td>
									<td class="text14">&nbsp;<span title="ehlkm">Landkode</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehpsm" id="ehpsm" size="25" maxlength="24" value="${model.record.ehpsm}"></td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehlkm" id="ehlkm" size="4" maxlength="2" value="${model.record.ehlkm}"></td>
									
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="ehad1m">Adress</span></td>
									<td class="text14">&nbsp;<span title="ehpnm">Postnr</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehad1m" id="ehad1m" size="25" maxlength="30" value="${model.record.ehad1m}"></td>
									<td class="text14"><input readonly type="text" class="inputTextReadOnly" name="ehpnm" id="ehpnm" size="12" maxlength="9" value="${model.record.ehpnm}"></td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="ehemm">E-post</span></td>
									<td class="text14">&nbsp;<span title="ehemm">Telefon</span></td>
									
				 				</tr>
				 				<tr >
									<c:choose>
				 					<c:when test="${model.record.ehemmt == 'EM'}">
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="ehemm" id="ehemm" size="35" maxlength="50" value="${model.record.ehemm}"></td>
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="empty" id="empty" size="35" maxlength="50" value=""></td>
				 					</c:when>
				 					<c:otherwise>
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="empty" id="empty" size="35" maxlength="50" value=""></td>
										<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="ehemm" id="ehemm" size="35" maxlength="50" value="${model.record.ehemm}"></td>
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
	
	</table>
	</td>
	</tr>	
	</table> 
	</form>
</td>
</tr>


<c:if test="${not empty model.record.listItemLines}">
<%-- list component --%>
	<tr>
		<td>		
		<table style="width:90%;" border="0" >
	    	<%-- separator --%>
	        <tr height="2"><td>&nbsp;</td></tr> 
			<tr>
				<td>
				<table style="width:90%;" id="containerdatatableTable" cellspacing="2" align="left" >
				<tr>
				<td class="text11">
							
				<table id="mainList" class="compact" >
					<thead>
					<tr class="tableHeaderField" height="20" >
                    	<th width="2%" class="tableHeaderFieldFirst" >Lin</th>
                    	<th width="2%" class="tableHeaderField" >Status</th>
                    	<th width="2%" class="tableHeaderField" >Stk</th>
                		<th width="2%" class="tableHeaderField" >Vrd</th>
                		<th width="2%" class="tableHeaderField" >Tariffnr</th>
                		<th width="2%" class="tableHeaderField" >Selger id (VOEC)</th>
                		<th width="2%" class="tableHeaderField" >Role</th>
                		
                		</tr>
                	</thead>
                	<tbody> 
                	<c:forEach items="${model.record.listItemLines}" var="itemLinesRecord" varStatus="counter">    
		             <tr class="tableRow" height="20" >
			          
		          	   <td width="2%" class="tableCellFirst" align="center">${itemLinesRecord.eili}</td>
		          	   <td width="2%" align="center" class="tableCell" >${itemLinesRecord.eist}</td>
	               	   <td width="2%" align="right" class="tableCell" >${itemLinesRecord.eistk}&nbsp;</td>
		               <td width="2%" align="right" class="tableCell" >${itemLinesRecord.eibl}&nbsp;</td>
		               <td width="2%" align="center" class="tableCell" >${itemLinesRecord.eivnt}</td>
		               <td width="2%" align="center" class="tableCell" >${itemLinesRecord.eirge}</td>
		               <td width="2%" align="center" class="tableCell" >${itemLinesRecord.eiroe}</td> 		               	
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
 
 	
 
	