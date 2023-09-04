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
								&nbsp;<spring:message code="systema.tvinn.sad.digitoll.list.tab.master"/>
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
							&nbsp;<spring:message code="systema.tvinn.sad.digitoll.list.tab.house"/>
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
	<form name="manifestForm" id="manifestForm" action="tvinnsaddigitollv2_edit_house.do" method="post">
			<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
			<input type="hidden" name="ehuuid" id="ehuuid" value="${model.record.ehuuid}"> 
			<input type="hidden" name="ehmid" id="ehmid" value="${model.record.ehmid}">
			<input type="hidden" name="ehlnrt" id="ehlnrt" value="${model.record.ehlnrt}">
			<input type="hidden" name="ehlnrm" id="ehlnrm" value="${model.record.ehlnrm}">
			<input type="hidden" name="action" id="action" value="doUpdate">
			
			<c:if test="${model.record.ehlnrh > 0}">
				<input type="hidden" name="ehlnrh" id="ehlnrh" value="${model.record.ehlnrh}"> 
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
			<tr height="5">	
  			<td  colspan="10" class="text14 formFrame" >
			<table style="width:100%">
			<tr >
				<td class="text14" align="left" >
		    		MRN-Api&nbsp;<span class="text14SkyBlue" id="${model.record.ehmid}">${model.record.ehmid}</span>
		    		&nbsp;&nbsp;<font style="font-weight: bold;color: lightgray;">|</font>&nbsp;&nbsp;
		    		Id&nbsp;<a class="uuidLink text14SkyBlue" id="${model.record.ehuuid}">${model.record.ehuuid}</a>
		    		&nbsp;&nbsp;<font style="font-weight: bold;color: lightgray;">|</font>&nbsp;&nbsp;
		    		
		    		<a title="lese logg" tabindex=-1 id="${model.record.ehlnrt}_${model.record.ehlnrm}_${model.record.ehlnrh}" class="logLink" runat="server" href="#"><font class="text14 ">House.st - log</font>&nbsp;
						<c:choose>
						<c:when test="${model.record.ehst2 == 'S' || model.record.ehst2 == 'R' || model.record.ehst2 == 'D' || model.record.ehst2 == 'C'}">
							<c:if test="${model.record.ehst2 == 'S'}">
								<img src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
								<font style="color:gray;">SUBMITTED</font>
							</c:if>
							<c:if test="${model.record.ehst2 == 'C'}">
								<img src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
								<font style="color:gray;">COMPLETED</font>
							</c:if>
							<c:if test="${model.record.ehst2 == 'D'}">
								<img src="resources/images/bulletRed.png" width="10" height="10" border="0" >
								<font style="color:red;">SLETTET</font>
							</c:if>
							<c:if test="${model.record.ehst2 == 'R'}">
								<font style="color:brown;">REOPENED/DRAFT</font>
							</c:if>
						</c:when>
						<c:otherwise>
							<c:choose>
							<c:when test="${model.record.ehst2 == 'M'}">
								<img src="resources/images/bulletRed.png" width="10" height="10" border="0" >
								<font style="color:red">ERROR&nbsp;</font>
							</c:when>
							<c:otherwise>
								<font style="color:gray;">${model.record.ehst2}&nbsp;</font>
							</c:otherwise>
							</c:choose>
						</c:otherwise>
						</c:choose>
					</a>&nbsp;
		   		</td>
	   		</tr>
	   		</table>
	   		</td>
	   		
		</tr>
		<tr height="5"><td></td></tr>	 
			 
		<tr>
			<td colspan="3" style="width:100%" class="text14" valign="top">
				<table style="width:90%" align="left" border="0" cellspacing="1" cellpadding="0">
					<tr height="5"><td>&nbsp;</td></tr>
					
					<tr>
					<td>
					<table>
					<tr>
						<td class="text14">&nbsp;<span title="ehavd">Avd</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="ehpro">Tur</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="ehtdn">Opd</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="ehvkb">Bruttovekt</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="ehntk">Ant.kolli</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="ehvt">Varebesk</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="ehcnin">Container</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="ehdkh">Dok.nr</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="ehdkht">Dok.type</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="ehst">St.</span></td>
						<td class="text14">&nbsp;<span title="ehst2">St.2</span></td>
						<td class="text14">&nbsp;<span title="ehst3">St.3</span></td>
						<td class="text14">&nbsp;<span title="ehdts">Send.dato</span></td>
						
					</tr>
					<tr>	
						<td class="text14">
							<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="ehavd" id="ehavd" size="5" maxlength="4" value="${model.record.ehavd}">									
						</td>
						<td class="text14">
							<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="ehpro" id="ehpro" size="10" maxlength="8" value="${model.record.ehpro}">									
						</td>
						<td class="text14">
							<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="ehtdn" id="ehtdn" size="10" maxlength="7" value="${model.record.ehtdn}">									
						</td>
						<td class="text14">
							<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="ehvkb" id="ehvkb" size="10" maxlength="9" value="${model.record.ehvkb}">									
						</td>
						<td class="text14">
							<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="ehntk" id="ehntk" size="10" maxlength="7" value="${model.record.ehntk}">									
						</td>
						<td class="text14">
							<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="ehvt" id="ehvt" size="35" maxlength="50" value="${model.record.ehvt}">									
						</td>
						<td class="text14">
							<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="ehcnin" id="ehcnin" size="2" maxlength="1" value="${model.record.ehcnin}">									
						</td>
						<td class="text14">
							<input  type="text" class="inputTextReadOnly" name="ehdkh" id="ehdkh" size="25" maxlength="50" value="${model.record.ehdkh}">		
						</td>
						<td class="text14">
							<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="ehdkht" id="ehdkht" size="5" maxlength="4" value="${model.record.ehdkht}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextReadOnly" name="ehst" id="ehst" size="2" maxlength="1" value="${model.record.ehst}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextReadOnly" name="ehst2" id="ehst2" size="2" maxlength="1" value="${model.record.ehst2}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextReadOnly" name="ehst3" id="ehst3" size="2" maxlength="1" value="${model.record.ehst3}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextReadOnly" name="ehdts" id="ehdts" size="10" maxlength="8" value="${model.record.ehdts}">		
						</td>
					</tr>
					</table>
					</td>
					</tr>
	
					<tr height="2"><td>&nbsp;</td></tr>
					
					<tr>
					<td>
					<table>
						<tr>
							<td colspan="3" class="text14">&nbsp;<span title="ehprt">Prosedyr</span><font class="text16RedBold" >*</font></td>
							<td class="text14">&nbsp;<span title="ehuprt">Eksp.prosedyr</span></td>
						</tr>
						<tr>
							<td colspan="3" class="text14">
								<select class="inputTextMediumBlueMandatoryField" id="ehprt" name="ehprt">
								  	<option title="Overgang til fri disponering" value="IMMEDIATE_RELEASE_IMPORT" <c:if test="${model.record.ehprt == 'IMMEDIATE_RELEASE_IMPORT'}"> selected </c:if> >IMMEDIATE_RELEASE_IMPORT</option> 
									<option title="Transittering" value="TRANSIT_IMPORT" <c:if test="${model.record.ehprt == 'TRANSIT_IMPORT'}"> selected </c:if> >TRANSIT_IMPORT</option> 
									<option title="Oppstart transittering" value="TRANSIT_RELEASE" <c:if test="${model.record.ehprt == 'TRANSIT_RELEASE'}"> selected </c:if> >TRANSIT_RELEASE</option> 
									<option title="Samlefortolling" value="WAREHOUSE_RELEASE" <c:if test="${model.record.ehprt == 'WAREHOUSE_RELEASE'}"> selected </c:if> >WAREHOUSE_RELEASE</option> 
									<option title="Innlegg på tollager" value="WAREHOUSE_RELEASE_RELAXATIONS" <c:if test="${model.record.ehprt == 'WAREHOUSE_RELEASE_RELAXATIONS'}"> selected </c:if> >WAREHOUSE_RELEASE_RELAXATIONS</option>
									<option title="VOEC-sendinger" value="IMMEDIATE_RELEASE_VOEC" <c:if test="${model.record.ehprt == 'IMMEDIATE_RELEASE_VOEC'}"> selected </c:if> >IMMEDIATE_RELEASE_VOEC</option> 
								</select>
								
								<%--
								<input size="30" maxlength="30" class="selectMediumBlueE2" list="ehprt_list" id="ehprt" name="ehprt" value="${model.record.ehprt}">
								<datalist id="ehprt_list">
								  	<option value="">-Välj-</option>
				 				  	<option title="Overgang til fri disponering" value="IMMEDIATE_RELEASE_IMPORT" <c:if test="${model.record.ehprt == 'IMMEDIATE_RELEASE_IMPORT'}"> selected </c:if> >${model.record.ehprt}</option> 
									<option title="Transittering" value="TRANSIT_IMPORT" <c:if test="${model.record.ehprt == 'TRANSIT_IMPORT'}"> selected </c:if> >${model.record.ehprt}</option> 
									<option title="Oppstart transittering" value="TRANSIT_RELEASE" <c:if test="${model.record.ehprt == 'TRANSIT_RELEASE'}"> selected </c:if> >${model.record.ehprt}</option> 
									<option title="VOEC-sendinger" value="IMMEDIATE_RELEASE_VOEC" <c:if test="${model.record.ehprt == 'IMMEDIATE_RELEASE_VOEC'}"> selected </c:if> >${model.record.ehprt}</option> 
									<option title="Samlefortolling" value="WAREHOUSE_RELEASE" <c:if test="${model.record.ehprt == 'WAREHOUSE_RELEASE'}"> selected </c:if> >${model.record.ehprt}</option> 
									<option title="Innlegg på tollager" value="WAREHOUSE_RELEASE_RELAXATIONS" <c:if test="${model.record.ehprt == 'WAREHOUSE_RELEASE_RELAXATIONS'}"> selected </c:if> >${model.record.ehprt}</option> 	
								</datalist>
								--%>	
							</td>
							<td class="text14">
								<select class="inputTextMediumBlue" id="ehupr" name="ehupr">
									<option value="">-Velg-</option>	
								  	<option title="Direktefortolling - Eksport" value="EXP" <c:if test="${model.record.ehupr == 'EXP'}"> selected </c:if> >EXP</option>
								  	<option title="Eksportene er avsluttet i avgangslandet. " value="FALSE" <c:if test="${model.record.ehupr == 'FALSE'}"> selected </c:if> >FALSE</option> 
									<option title="Direktefortolling - Transittering" value="TRA" <c:if test="${model.record.ehupr == 'TRA'}"> selected </c:if> >TRA</option> 
									<option title="Direktefortolling - Transittering og Eksport" value="TRE" <c:if test="${model.record.ehupr == 'TRE'}"> selected </c:if> >TRE</option> 
									 	
								</select>
							</td>
						</tr>
						
					</table>
					</td>
					</tr>
					
					<tr>
					<td >
					<table>
					<tr>
						<td style="width:80%" valign="top">
						<table id="tblDirektfortolling" style="width:100%"  class="tableBorderWithRoundCorners" border="0" cellspacing="1" cellpadding="0">
			 			<tr >
							<td class="text16"><b>&nbsp;Tidligere dokumenter</b></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							
						<tr >
			 			<tr>
			 				<td class="text14">&nbsp;<span title="ehtrty">Ref.type</span></td>
			 				<td class="text14">&nbsp;<span title="ehrg">Deklarantnr.</span></td>
							<td class="text14">&nbsp;<span title="eh0068a">Dato</span></td>
							<td class="text14">&nbsp;<span title="eh0068b">Sekvensnr.</span></td>
							<td class="text14">&nbsp;<span title="ehtrnr">MRN/LRN - Transitering</span></td>
							
						</tr>
						<tr>
							<td class="text14">
			 					<select class="inputTextMediumBlue" id="ehtrty" name="ehtrty">
									<option value="">-Velg-</option>	
							  		<option title="Tolldeklarasjon" value="CUDE" <c:if test="${model.record.ehtrty == 'CUDE'}"> selected </c:if> >CUDE</option>
							  		<option title="Transitteringsdeklarasjon" value="N820" <c:if test="${model.record.ehtrty == 'N820'}"> selected </c:if> >N820</option>
							  		<option title="Oppstart transittering (på grensen). Brukes dersom det er en LRN" value="RETR" <c:if test="${model.record.ehtrty == 'RETR'}"> selected </c:if> >RETR</option> 	
								</select>
		 					</td>
							<td class="text14"><input type="text" class="inputTextMediumBlue toggleDirektfortolling" name="ehrg" id="ehrg" size="12" maxlength="11" value="${model.record.ehrg}"></td>
			 				<td class="text14"><input type="text" class="inputTextMediumBlue toggleDirektfortolling" name="eh0068a" id="eh0068a" size="10" maxlength="8" value='<c:if test="${model.record.eh0068a!='0'}">${model.record.eh0068a}</c:if>'></td>
			 				<td class="text14"><input type="text" class="inputTextMediumBlue toggleDirektfortolling" name="eh0068b" id="eh0068b" size="8" maxlength="6" value='<c:if test="${model.record.eh0068b!='0'}">${model.record.eh0068b}</c:if>'></td>
			 				<td class="text14"><input type="text" class="inputTextMediumBlue toggleTransit" name="ehtrnr" id="ehtrnr" size="20" maxlength="18" value="${model.record.ehtrnr}"></td>
						</tr>
						<tr height="5"><td></td></tr>
						
						<tr >
							<td class="text14">&nbsp;<span title="ehetypt">Eksporttype</span></td>
							<td class="text14">&nbsp;<span title="eheid">Eksp.id</span></td>	
							<td>&nbsp;</td>						
						</tr>
						
						<tr>
							<td class="text14">
								<select class="inputTextMediumBlue" id="ehetypt" name="ehetypt">
									<option value="">-Velg-</option>	
								  	<option title="Eksport fra Sverige som på forhånd er klarert" value="UGE_EXPORT" <c:if test="${model.record.ehetypt == 'UGE_EXPORT'}"> selected </c:if> >UGE_EXPORT</option>
								  	<option title="Eksport fra Sverige. Ingen videre oppfølging for Tolletaten ifm ankomst og innpassering" value="EUEIR_EXPORT" <c:if test="${model.record.ehetypt == 'EUEIR_EXPORT'}"> selected </c:if> >EUEIR_EXPORT</option> 
									<option title="Eksport fra Sverige. Ingen videre oppfølging for Tolletaten ifm ankomst og innpassering" <c:if test="${model.record.ehetypt == 'ALE_EXPORT'}"> selected </c:if> >ALE_EXPORT</option> 
									<option title="Eksport fra Sverige. Medfører behov for manuell ekspedisjon på grensen." value="UNU_EXPORT" <c:if test="${model.record.ehetypt == 'UNU_EXPORT'}"> selected </c:if> >UNU_EXPORT</option> 
									<option title="Eksport fra EU. Medfører behov for manuell ekspedisjon på grensen." value="ECS_EXPORT" <c:if test="${model.record.ehetypt == 'ECS_EXPORT'}"> selected </c:if> >ECS_EXPORT</option> 	
								</select>
								<%--
				 				<select class="inputTextMediumBlue toggleDirektfortolling" name="ehetypt" id="ehetypt" >
			 						<option value="">-velg-</option>
				 				  	<c:forEach var="record" items="${Xmodelmodel.etTypeList}" >
			                       	 	<option title="${Xrecord.kftxt}" value="${Xrecord.kfkod}" <c:if test="${Xmodelmodel.record.ehetypt == Xrecord.kfkod}"> selected </c:if> >${Xrecord.kfkod}&nbsp;${Xrecord.kftxt}</option>
									</c:forEach>
								</select>
								 --%>
				 			</td>
				 			<td class="text14">
				 					<input type="text" class="inputTextMediumBlue toggleDirektfortolling" name="eheid" id="eheid" size="21" maxlength="18" value="${model.record.eheid}">
				 			</td>
				 			<td class="text14">		
				 					<input class="inputFormSubmitStd" type="button" name="manyExpIdButton" id="manyExpIdButton" value='Lage flere Eksp.id'>
				 					<img style="cursor:pointer;vertical-align: middle;" src="resources/images/add.png" width="12px" height="12px" border="0" alt="create new" >
				 			</td>
				 								 			
						</tr>
						<tr height="5"><td></td></tr>
						
						</table>
						</td>
					</tr>
					</table>
					</td>
					</tr>
					<tr height="2"><td>&nbsp;</td></tr>
					
					<tr>
					<td>
					<table>
						<tr>
							<td class="text14">&nbsp;<span title="ehsda">Place of Acceptance</span></td>
							<td class="text14">&nbsp;<span title="ehlka">Land</span></td>
							<td class="text14">&nbsp;<span title="ehsddt">Place of Delivery</span></td>
							<td class="text14">&nbsp;<span title="ehlkd">Land</span></td>
						</tr>
						<tr>	
							<td class="text14">
								<input  type="text" class="inputTextMediumBlue" name="ehsda" id="ehsda" size="25" maxlength="30" value="${model.record.ehsda}">								
							</td>
							<td class="text14">
								<input  type="text" class="inputTextMediumBlue" name="ehlka" id="ehlka" size="4" maxlength="2" value="${model.record.ehlka}">												
							</td>
							<td class="text14">
								<input  type="text" class="inputTextMediumBlue" name="ehsddt" id="ehsddt" size="25" maxlength="30" value="${model.record.ehsddt}">											
							</td>
							<td class="text14">
								<input  type="text" class="inputTextMediumBlue" name="ehlkd" id="ehlkd" size="4" maxlength="2" value="${model.record.ehlkd}">												
							</td>
						</tr>
					</table>
					</td>
					</tr>
	
		
				</table>
			</td>
		</tr>
		
		<tr height="4"><td>&nbsp;</td></tr> 
		
		
		<tr>
		<td>
		<table style="width:85%;">
 		<tr>
			<td >
			<table>
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
			 					<td class="text14">&nbsp;<span title="ehkns">Kundnr</span></td>
								<td class="text14">&nbsp;<span title="ehtpps">Typ.person</span><font class="text16RedBold" >*</font></td>
								<td class="text14">&nbsp;<span title="ehnas">Navn</span><font class="text16RedBold" >*</font></td>
								<td class="text14">&nbsp;<span title="ehrgs">Org.nr /EORI</span></td>
								
			 				</tr>
			 				<tr >
			 					<td class="text14"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="ehkns" id="ehkns" size="10" maxlength="8" value="${model.record.ehkns}"></td>
								<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="ehtpps" id="ehtpps" size="2" maxlength="1" value="${model.record.ehtpps}"></td>
								
								<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="ehnas" id="ehnas" size="25" maxlength="30" value="${model.record.ehnas}"></td>
								<td class="text14"><input  type="text" class="inputTextMediumBlue" name="ehrgs" id="ehrgs" size="20" maxlength="17" value="${model.record.ehrgs}"></td>
			 				</tr>
			 				
			 				
			 				<tr >
			 					<td class="text14">&nbsp;</td>
			 					<td class="text14">&nbsp;</td>
								<td class="text14">&nbsp;<span title="ehpss">Sted</span></td>
								<td class="text14">&nbsp;<span title="ehlks">Landkode</span></td>
								
			 				</tr>
			 				<tr >
			 					<td class="text14">&nbsp;</td>
			 					<td class="text14">&nbsp;</td>
								<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehpss" id="ehpss" size="25" maxlength="24" value="${model.record.ehpss}"></td>
								<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehlks" id="ehlks" size="4" maxlength="2" value="${model.record.ehlks}"></td>
								
			 				</tr>
			 				<tr >
			 					<td class="text14">&nbsp;</td>
			 					<td class="text14">&nbsp;</td>
								<td class="text14">&nbsp;<span title="ehad1s">Adress</span></td>
								<td class="text14">&nbsp;<span title="ehpns">Postnr</span></td>
								
			 				</tr>
			 				<tr >
			 					<td class="text14">&nbsp;</td>
			 					<td class="text14">&nbsp;</td>
								<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehad1s" id="ehad1s" size="25" maxlength="30" value="${model.record.ehad1s}"></td>
								<td class="text14"><input  type="text" class="inputTextMediumBlue" name="ehpns" id="ehpns" size="12" maxlength="9" value="${model.record.ehpns}"></td>
			 				</tr>
			 				
			 				<tr >
			 					<td class="text14">&nbsp;</td>
			 					<td class="text14">&nbsp;</td>
								<td class="text14">&nbsp;<span title="ehems">E-post</span></td>
								<td class="text14">&nbsp;<span title="ehems">Telefon</span></td>
								
			 				</tr>
			 				<tr >
			 					<td class="text14">&nbsp;</td>
			 					<td class="text14">&nbsp;</td>
								<c:choose>
			 					<c:when test="${model.record.ehemst == 'EM'}">
			 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_ehems_email" id="own_ehems_email" size="35" maxlength="50" value="${model.record.ehems}"></td>
			 						<td class="text14"><input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="own_ehems_telephone" id="own_ehems_telephone" size="15" maxlength="50" value=""></td>
			 					</c:when>
			 					<c:otherwise>
			 						<c:choose>
					 					<c:when test="${empty model.record.ehemst}">
					 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_ehems_email" id="own_ehems_email" size="35" maxlength="50" value=""></td>
					 						<td class="text14"><input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="own_ehems_telephone" id="own_ehems_telephone" size="15" maxlength="50" value=""></td>
					 					</c:when>
					 					<c:otherwise>
					 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_ehems_email" id="own_ehems_email" size="35" maxlength="50" value=""></td>
											<td class="text14"><input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="own_ehems_telephone" id="own_ehems_telephone" size="15" maxlength="50" value="${model.record.ehems}"></td>
										</c:otherwise>
									</c:choose>
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
				<table align="left" border="0" cellspacing="1" cellpadding="0">
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
				 					<td class="text14">&nbsp;<span title="ehknm">Kundnr</span></td>
									<td class="text14">&nbsp;<span title="ehtppm">Typ.person</span><font class="text16RedBold" >*</font></td>
									<td class="text14">&nbsp;<span title="ehnam">Navn</span><font class="text16RedBold" >*</font></td>
									<td class="text14">&nbsp;<span title="ehrgm">Org.nr /EORI</span></td>
									
				 				</tr>
				 				<tr >
				 					<td class="text14"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="ehknm" id="ehknm" size="10" maxlength="8" value="${model.record.ehknm}"></td>
									<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="ehtppm" id="ehtppm" size="2" maxlength="1" value="${model.record.ehtppm}"></td>
									<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlue" name="ehnam" id="ehnam" size="25" maxlength="30" value="${model.record.ehnam}"></td>
									<td class="text14"><input  type="text" class="inputTextMediumBlue" name="ehrgm" id="ehrgm" size="20" maxlength="17" value="${model.record.ehrgm}"></td>
				 				</tr>
				 				
				 				<tr >
				 					<td class="text14">&nbsp;</td>
				 					<td class="text14">&nbsp;</td>
									<td class="text14">&nbsp;<span title="ehpsm">Sted</span></td>
									<td class="text14">&nbsp;<span title="ehlkm">Landkode</span></td>
									
				 				</tr>
				 				<tr >
				 					<td class="text14">&nbsp;</td>
				 					<td class="text14">&nbsp;</td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehpsm" id="ehpsm" size="25" maxlength="24" value="${model.record.ehpsm}"></td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehlkm" id="ehlkm" size="4" maxlength="2" value="${model.record.ehlkm}"></td>
									
				 				</tr>
				 				
				 				<tr >
				 					<td class="text14">&nbsp;</td>
				 					<td class="text14">&nbsp;</td>
									<td class="text14">&nbsp;<span title="ehad1m">Adress</span></td>
									<td class="text14">&nbsp;<span title="ehpnm">Postnr</span></td>
									
				 				</tr>
				 				<tr >
				 					<td class="text14">&nbsp;</td>
				 					<td class="text14">&nbsp;</td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehad1m" id="ehad1m" size="25" maxlength="30" value="${model.record.ehad1m}"></td>
									<td class="text14"><input  type="text" class="inputTextMediumBlue" name="ehpnm" id="ehpnm" size="12" maxlength="9" value="${model.record.ehpnm}"></td>
				 				</tr>
				 				
				 				<tr >
				 					<td class="text14">&nbsp;</td>
				 					<td class="text14">&nbsp;</td>
									<td class="text14">&nbsp;<span title="ehemm">E-post</span></td>
									<td class="text14">&nbsp;<span title="ehemm">Telefon</span></td>
									
				 				</tr>
				 				<tr >
				 					<td class="text14">&nbsp;</td>
				 					<td class="text14">&nbsp;</td>
									<c:choose>
				 					<c:when test="${model.record.ehemmt == 'EM'}">
				 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_ehemm_email" id="own_ehemm_email" size="35" maxlength="50" value="${model.record.ehemm}"></td>
				 						<td class="text14"><input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="own_ehemm_telephone" id="own_ehemm_telephone" size="15" maxlength="50" value=""></td>
				 					</c:when>
				 					<c:otherwise>
				 						<c:choose>
						 					<c:when test="${empty model.record.ehemmt}">
						 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_ehemm_email" id="own_ehemm_email" size="35" maxlength="50" value=""></td>
						 						<td class="text14"><input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="own_ehemm_telephone" id="own_ehemm_telephone" size="15" maxlength="50" value=""></td>
						 					</c:when>
						 					<c:otherwise>
						 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_ehemm_email" id="own_ehemm_email" size="35" maxlength="50" value=""></td>
												<td class="text14"><input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="own_ehemm_telephone" id="own_ehemm_telephone" size="15" maxlength="50" value="${model.record.ehemm}"></td>
											</c:otherwise>
										</c:choose>
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
            </table>
            </td>	
            
		</tr>
		<tr height="10"><td></td></tr>
		<tr>
			<td align="left" >
			<c:if test="${model.record.ehst != 'S'}"> <%-- CANCELED(S) --%>
				&nbsp;&nbsp;<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'>
				<c:if test="${model.record.ehlnrh > 0}">
					<c:choose>
						<c:when test="${model.record.ehst2 == 'C' }"> <%--COMPLETED(C) --%>
							<%-- not possible --%>
						</c:when>
						<c:otherwise>
							&nbsp;<input class="inputFormSubmit" type="button" name="sendButton" id="sendButton" value='Send'>
						</c:otherwise>
					</c:choose>
				</c:if>
			</c:if>
			</td>
		</tr> 
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
 
 	
 
	