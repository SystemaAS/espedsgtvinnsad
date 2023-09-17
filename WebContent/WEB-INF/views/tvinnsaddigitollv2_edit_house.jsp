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
	
<table style="width:80%;" cellspacing="0" border="0" cellpadding="0">

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
						<c:choose>
						<c:when test="${model.record.ehlnrh > 0}">
							<td title="${model.record.ehlnrh}" width="15%" valign="bottom" class="tab" align="center" nowrap>
						</c:when>
						<c:otherwise>
							<%-- Meaning CreateNew --%>
							<td width="15%" valign="bottom" class="tab" style="background-color:lightyellow;" align="center" nowrap>
						</c:otherwise>
						</c:choose>
						
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
						<span style="position:absolute; left:-100px; top:15px;" id="jarStartCmd" class="popupWithInputText"  >
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

			<tr height="5">	
  			<td  colspan="10" class="text14 formFrame" >
			<table style="width:100%">
			<tr >
				<td class="text14" align="left" >
					<c:choose>
               		<c:when test="${ not empty model.record.transportDto.etktyp && fn:startsWith(model.record.transportDto.etktyp,'4') }">
						<img style="vertical-align:middle;" id="airplaneImg" src="resources/images/airplaneBlue.png" width="25" height="25"border="0" >&nbsp;
					</c:when>
					<c:otherwise>
						<img style="vertical-align:middle;" id="lorryImg" src="resources/images/lorry_green.png" width="20" height="20"border="0" >&nbsp;
					</c:otherwise>
					</c:choose>
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
		<tr height="2"><td></td></tr>	 
			 
		<tr>
			<td colspan="3" style="width:100%" class="text14" valign="top">
				<table style="width:90%" align="left" border="0" cellspacing="1" cellpadding="0">					
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
							<c:choose>
								<c:when test="${model.record.ehavd > 0}">
									<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" size="8" maxlength="4" class="inputTextMediumBlueMandatoryField" list="ehavd_list" id="ehavd" name="ehavd" value="${model.record.ehavd}">	
								</c:when>
								<c:otherwise>
									<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" size="8" maxlength="4" class="inputTextMediumBlueMandatoryField" list="ehavd_list" id="ehavd" name="ehavd" value="">								
								</c:otherwise>
							</c:choose>
							<datalist id="ehavd_list">
							  <option value="">-Välj-</option>
			 				  	<c:forEach var="record" items="${model.avdList}" >
			 				  		<option value="${record.avd}"<c:if test="${model.record.ehavd == record.avd}"> selected </c:if> >${record.avd}</option> 
								</c:forEach>  
							</datalist>					
						</td>
						<td class="text14">
							<c:choose>
								<c:when test="${model.record.ehpro > 0}">
									<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="ehpro" id="ehpro" size="10" maxlength="8" value="${model.record.ehpro}">	
								</c:when>
								<c:otherwise>
									<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="ehpro" id="ehpro" size="10" maxlength="8" value="">								
								</c:otherwise>
							</c:choose>									
						</td>
						<td class="text14">
							<c:choose>
								<c:when test="${model.record.ehtdn > 0}">
									<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="ehtdn" id="ehtdn" size="10" maxlength="7" value="${model.record.ehtdn}">	
								</c:when>
								<c:otherwise>
									<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="ehtdn" id="ehtdn" size="10" maxlength="7" value="">								
								</c:otherwise>
							</c:choose>								
						</td>
						<td class="text14">
							<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="ehvkb" id="ehvkb" size="10" maxlength="9" value="${model.record.ehvkb}">									
						</td>
						<td class="text14">
							<c:choose>
								<c:when test="${model.record.ehntk > 0}">
									<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="ehntk" id="ehntk" size="10" maxlength="7" value="${model.record.ehntk}">	
								</c:when>
								<c:otherwise>
									<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="ehntk" id="ehntk" size="10" maxlength="7" value="">								
								</c:otherwise>
							</c:choose>						
						</td>
						<td class="text14">
							<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="ehvt" id="ehvt" size="35" maxlength="50" value="${model.record.ehvt}">									
						</td>
						<td class="text14">
							<select class="inputTextMediumBlueMandatoryField" id="ehcnin" name="ehcnin">
					  			<option title="Varer ikke transportert i beholder" value="0" <c:if test="${empty model.record.ehcnin || model.record.ehcnin == '0'}"> selected </c:if> >0</option>
	 							<option title="Varer transportert i beholder" value="1" <c:if test="${model.record.ehcnin == '1'}"> selected </c:if> >1</option>
							</select>																		
						</td>
						<td class="text14">
							<input  type="text" class="inputTextReadOnly" name="ehdkh" id="ehdkh" size="25" maxlength="50" value="${model.record.ehdkh}">		
						</td>
						<td class="text14">
							<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  class="inputTextMediumBlueMandatoryField" name="ehdkht" id="ehdkht" >
		 						<option value="">-velg-</option>
			 				  	<c:forEach var="dto" items="${model.previousDocumentsDto}" >
		                       	 	<option title="${dto.txt1}&nbsp;-&nbsp;${dto.txt2}" value="${dto.code}" <c:if test="${model.record.ehdkht == dto.code}"> selected </c:if> >${dto.code}</option>
								</c:forEach>
							</select>
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
							<input readonly type="text" class="inputTextReadOnly" name="ehdts" id="ehdts" size="10" maxlength="8" value="${model.record.ehdtsStr}">		
						</td>
					</tr>
					</table>
					</td>
					</tr>
	
					<tr height="2"><td></td></tr>
					
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
						<td style="width:65%" valign="top">
						<table id="tblDirektfortolling" style="width:100%"  class="tableBorderWithRoundCorners" border="0" cellspacing="1" cellpadding="0">
			 			<tr >
							<td class="text16"><b>&nbsp;Tidligere dokumenter</b>
							&nbsp;
							<a title="Aktuelle eksempler..." target="_blank" href="https://toll.github.io/api/mo-eksempler">
								<img src="resources/images/info4.png" width="12" height="12" border="0" alt="info">
							</a>
							</td>
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
								<select class="inputTextMediumBlue" name="ehetypt" id="ehetypt" >
			 						<option value="">-velg-</option>
				 				  	<c:forEach var="dto" items="${model.exportTypesDto}" >
			                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.ehetypt == dto.code}"> selected </c:if> >${dto.code}</option>
									</c:forEach>
								</select>
								 
				 			</td>
				 			<td class="text14">
				 				<input type="text" class="inputTextMediumBlue toggleDirektfortolling" name="eheid" id="eheid" size="21" maxlength="18" value="${model.record.eheid}">
				 			</td>
				 			<td class="text14">		
				 				<input class="inputFormSubmitStd" type="button" name="manyExpIdButton" id="manyExpIdButton" onClick="showPop('more_exports');" value='Lage flere Eksp.id'>
				 				<img style="cursor:pointer;vertical-align: middle;" src="resources/images/add.png" width="12px" height="12px" border="0" alt="create new" >
				 					<div class="text14" style="position: relative;" align="right" >
			 						<span style="position:absolute;top:-200px; width:700px;" id="more_exports" class="popupWithInputText"  >
							           		<div class="text10" align="left">
							           			<table border="0" cellspacing="1" cellpadding="0">
							           			<tr>
							           			<td>
							           				<table class="lightGrayBg" >
							           				<tr>
									           			<td class="text14" colspan="2">
									           				<b>Flere Eksp.id</b>
									           			</td>
								        			</tr>
													<tr>
														<td class="text14">&nbsp;<span title="ehetypt2" >2.Eksporttype</span></td>
									           			<td class="text14">&nbsp;<span title="eheid2" >2.Eksp.id</span></td>
									           			<td class="text14">&nbsp;<span title="ehetypt3" >3.Eksporttype</span></td>
									           			<td class="text14">&nbsp;<span title="eheid3" >3.Eksp.id</span></td>
									           			
													</tr>
								           			<tr>
									           			<td class="text14" nowrap >&nbsp;
									            			<select class="inputTextMediumBlue" name="ehetypt2" id="ehetypt2" >
										 						<option value="">-velg-</option>
											 				  	<c:forEach var="dto" items="${model.exportTypesDto}" >
										                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.ehetypt2 == dto.code}"> selected </c:if> >${dto.code}</option>
																</c:forEach>
															</select>
										            		
									           			</td>
									           			<td class="text14" nowrap >
									           				&nbsp;<input type="text" class="inputTextMediumBlue" name="eheid2" id="eheid2" size="21" maxlength="18" value="${model.record.eheid2}">										           			
									           			</td>
									           			<td class="text14" nowrap >&nbsp;
									            			<select class="inputTextMediumBlue" name="ehetypt3" id="ehetypt3" >
										 						<option value="">-velg-</option>
											 				  	<c:forEach var="dto" items="${model.exportTypesDto}" >
										                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.ehetypt3 == dto.code}"> selected </c:if> >${dto.code}</option>
																</c:forEach>
															</select>
										            		
									           			</td>
									           			<td class="text14" nowrap >
									           				&nbsp;<input type="text" class="inputTextMediumBlue" name="eheid3" id="eheid3" size="21" maxlength="18" value="${model.record.eheid3}">										           			
									           			</td>
									           			
				           							</tr>
				           							<tr>
														<td class="text14">&nbsp;<span title="ehetypt4" >4.Eksporttype</span></td>
									           			<td class="text14">&nbsp;<span title="eheid4" >4.Eksp.id</span></td>
									           			<td class="text14">&nbsp;<span title="ehetypt5" >5.Eksporttype</span></td>
									           			<td class="text14">&nbsp;<span title="eheid5" >5.Eksp.id</span></td>
									           			
													</tr>
								           			<tr>
									           			<td class="text14" nowrap >&nbsp;
									            			<select class="inputTextMediumBlue" name="ehetypt4" id="ehetypt4" >
										 						<option value="">-velg-</option>
											 				  	<c:forEach var="dto" items="${model.exportTypesDto}" >
										                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.ehetypt4 == dto.code}"> selected </c:if> >${dto.code}</option>
																</c:forEach>
															</select>
										            		
									           			</td>
									           			<td class="text14" nowrap >
									           				&nbsp;<input type="text" class="inputTextMediumBlue" name="eheid4" id="eheid4" size="21" maxlength="18" value="${model.record.eheid4}">										           			
									           			</td>
									           			<td class="text14" nowrap >&nbsp;
									            			<select class="inputTextMediumBlue" name="ehetypt5" id="ehetypt5" >
										 						<option value="">-velg-</option>
											 				  	<c:forEach var="dto" items="${model.exportTypesDto}" >
										                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.ehetypt5 == dto.code}"> selected </c:if> >${dto.code}</option>
																</c:forEach>
															</select>
										            		
									           			</td>
									           			<td class="text14" nowrap >
									           				&nbsp;<input type="text" class="inputTextMediumBlue" name="eheid5" id="eheid5" size="21" maxlength="18" value="${model.record.eheid5}">										           			
									           			</td>
				           							</tr>
				           							<tr>
														<td class="text14">&nbsp;<span title="ehetypt6" >6.Eksporttype</span></td>
									           			<td class="text14">&nbsp;<span title="eheid6" >6.Eksp.id</span></td>
									           			<td class="text14">&nbsp;<span title="ehetypt7" >7.Eksporttype</span></td>
									           			<td class="text14">&nbsp;<span title="eheid7" >7.Eksp.id</span></td>
									           			
													</tr>
								           			<tr>
									           			<td class="text14" nowrap >&nbsp;
									            			<select class="inputTextMediumBlue" name="ehetypt6" id="ehetypt6" >
										 						<option value="">-velg-</option>
											 				  	<c:forEach var="dto" items="${model.exportTypesDto}" >
										                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.ehetypt6 == dto.code}"> selected </c:if> >${dto.code}</option>
																</c:forEach>
															</select>
										            		
									           			</td>
									           			<td class="text14" nowrap >
									           				&nbsp;<input type="text" class="inputTextMediumBlue" name="eheid6" id="eheid6" size="21" maxlength="18" value="${model.record.eheid6}">										           			
									           			</td>
									           			<td class="text14" nowrap >&nbsp;
									            			<select class="inputTextMediumBlue" name="ehetypt7" id="ehetypt7" >
										 						<option value="">-velg-</option>
											 				  	<c:forEach var="dto" items="${model.exportTypesDto}" >
										                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.ehetypt7 == dto.code}"> selected </c:if> >${dto.code}</option>
																</c:forEach>
															</select>
										            		
									           			</td>
									           			<td class="text14" nowrap >
									           				&nbsp;<input type="text" class="inputTextMediumBlue" name="eheid7" id="eheid7" size="21" maxlength="18" value="${model.record.eheid7}">										           			
									           			</td>
				           							</tr>
				           							<tr>
														<td class="text14">&nbsp;<span title="ehetypt8" >8.Eksporttype</span></td>
									           			<td class="text14">&nbsp;<span title="eheid8" >8.Eksp.id</span></td>
									           			<td class="text14">&nbsp;<span title="ehetypt9" >9.Eksporttype</span></td>
									           			<td class="text14">&nbsp;<span title="eheid9" >9.Eksp.id</span></td>
									           			
													</tr>
								           			<tr>
									           			<td class="text14" nowrap >&nbsp;
									            			<select class="inputTextMediumBlue" name="ehetypt8" id="ehetypt8" >
										 						<option value="">-velg-</option>
											 				  	<c:forEach var="dto" items="${model.exportTypesDto}" >
										                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.ehetypt8 == dto.code}"> selected </c:if> >${dto.code}</option>
																</c:forEach>
															</select>
										            		
									           			</td>
									           			<td class="text14" nowrap >
									           				&nbsp;<input type="text" class="inputTextMediumBlue" name="eheid8" id="eheid8" size="21" maxlength="18" value="${model.record.eheid8}">										           			
									           			</td>
									           			<td class="text14" nowrap >&nbsp;
									            			<select class="inputTextMediumBlue" name="ehetypt9" id="ehetypt9" >
										 						<option value="">-velg-</option>
											 				  	<c:forEach var="dto" items="${model.exportTypesDto}" >
										                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.ehetypt9 == dto.code}"> selected </c:if> >${dto.code}</option>
																</c:forEach>
															</select>
										            		
									           			</td>
									           			<td class="text14" nowrap >
									           				&nbsp;<input type="text" class="inputTextMediumBlue" name="eheid9" id="eheid9" size="21" maxlength="18" value="${model.record.eheid9}">										           			
									           			</td>
				           							</tr>
				           							
				           							<tr>
														<td class="text14">&nbsp;<span title="ehetypt10" >10.Eksporttype</span></td>
									           			<td class="text14">&nbsp;<span title="eheid10" >10.Eksp.id</span></td>
									           			
													</tr>
								           			<tr>
									           			<td class="text14" nowrap >&nbsp;
									            			<select class="inputTextMediumBlue" name="ehetypt10" id="ehetypt10" >
										 						<option value="">-velg-</option>
											 				  	<c:forEach var="dto" items="${model.exportTypesDto}" >
										                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.ehetypt10 == dto.code}"> selected </c:if> >${dto.code}</option>
																</c:forEach>
															</select>
										            		
									           			</td>
									           			<td class="text14" nowrap >
									           				&nbsp;<input type="text" class="inputTextMediumBlue" name="eheid10" id="eheid10" size="21" maxlength="18" value="${model.record.eheid10}">										           			
									           			</td>
	
				           							</tr>
				           										           															           			
				           							</table>
				           						</td>
				           						</tr>
				           						
				           						<tr height="4"><td class="text" align="left"></td></tr>
				           						</table>
												<table width="100%" align="left" border="0">
													<tr align="left" >
														<td class="text14"><button name="more_exports" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('more_exports');">&nbsp;Ok</button> 
														</td>
													</tr>
												</table>
											</div>
									</span>
				 				</td>
				 								 			
						</tr>
						<tr height="5"><td></td></tr>
						
						</table>
						</td>
						
						<td valign="top">
						<table style="width:100%" id="places" class="tableBorderWithRoundCorners" border="0" cellspacing="1" cellpadding="0">
			 			<tr>
							<td class="text14">&nbsp;<span title="ehsdat">Place of Acceptance</span></td>
							<td class="text14">&nbsp;<span title="ehlka">Land</span></td>
							<td class="text14">&nbsp;<span title="ehsda">UN/LOCODE</span></td>
						</tr>
						<tr>	
							<td class="text14">
								<input  type="text" class="inputTextMediumBlue" name="ehsdat" id="ehsdat" size="25" maxlength="30" value="${model.record.ehsdat}">								
							</td>
							<td class="text14">
								<select class="inputTextMediumBlue" name="ehlka" id="ehlka" >
			 						<option value="">-velg-</option>
				 				  	<c:forEach var="dto" items="${model.countryDto}" >
			                       	 	<option title="${dto.code}" value="${dto.code}" <c:if test="${model.record.ehlka == dto.code}"> selected </c:if> >${dto.code}</option>
									</c:forEach>
								</select>		
																				
							</td>
							<td class="text14">
								<input  type="text" class="inputTextMediumBlue" name="ehsda" id="ehsda" size="7" maxlength="5" value="${model.record.ehsda}">								
							</td>
						</tr>
						<tr height="2"><td></td></tr>	
						<tr>	
							<td class="text14">&nbsp;<span title="ehsddt">Place of Delivery</span></td>
							<td class="text14">&nbsp;<span title="ehlkd">Land</span></td>
							<td class="text14">&nbsp;<span title="ehsdd">UN/LOCODE</span></td>
						</tr>
						<tr>
							<td class="text14">
								<input  type="text" class="inputTextMediumBlue" name="ehsddt" id="ehsddt" size="25" maxlength="30" value="${model.record.ehsddt}">											
							</td>
							<td class="text14">
								<select class="inputTextMediumBlue" name="ehlkd" id="ehlkd" >
			 						<option value="">-velg-</option>
				 				  	<c:forEach var="dto" items="${model.countryDto}" >
			                       	 	<option title="${dto.code}" value="${dto.code}" <c:if test="${model.record.ehlkd == dto.code}"> selected </c:if> >${dto.code}</option>
									</c:forEach>
								</select>													
							</td>
							<td class="text14">
								<input  type="text" class="inputTextMediumBlue" name="ehsdd" id="ehsdd" size="7" maxlength="5" value="${model.record.ehsdd}">								
							</td>
						</tr>
						
						<tr height="5"><td>&nbsp;</td></tr>
						</table>
						</td>
					</tr>
					</table>
					</td>
					</tr>
				</table>
			</td>
		</tr>		
		<tr height="2"><td></td></tr> 

		<tr>
		<td>
		<table style="width:70%;">
 		<tr>
			<td >
			<table>
			 	<tr >
				 	<td >
					<table style="width:100%;" class="formFrameHeader" border="0" cellspacing="1" cellpadding="0">
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
			 					<td class="text14">&nbsp;<span title="ehkns">Knr</span></td>
								<td class="text14">&nbsp;<span title="ehnas">Navn</span><font class="text16RedBold" >*</font>
									<a tabindex="-1" id="ehnasIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
									</a>
								</td>
								<td class="text14">&nbsp;<span title="ehrgs">Org.nr /EORI</span><font class="text16RedBold" >*</font></td>
								
			 				</tr>
			 				<tr >
			 					
								<td class="text14"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="ehkns" id="ehkns" size="10" maxlength="8" value="${model.record.ehkns}"></td>
								
								<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="ehnas" id="ehnas" size="25" maxlength="30" value="${model.record.ehnas}"></td>
								<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="ehrgs" id="ehrgs" size="20" maxlength="17" value="${model.record.ehrgs}"></td>
			 				</tr>
			 				
			 				
			 				<tr >
			 					
			 					<td class="text14">&nbsp;<span title="ehtpps">Typ.person</span><font class="text16RedBold" >*</font></td>
								<td class="text14">&nbsp;<span title="ehpss">Sted</span></td>
								<td class="text14">&nbsp;<span title="ehlks">Landkode</span></td>
								
			 				</tr>
			 				<tr >
			 					<td class="text14">
		 							<select class="inputTextMediumBlueMandatoryField" id="ehtpps" name="ehtpps">
										<option title="En fysisk person" value="1" <c:if test="${model.record.ehtpps == 1}"> selected </c:if> >Fys.person</option>
								  		<option title="En juridisk person, det vil si en bedrift" value="2" <c:if test="${model.record.ehtpps == 0 || model.record.ehtpps == 2}"> selected </c:if> >Bedrift</option>
								  		<option title="En samling personer" value="3" <c:if test="${model.record.ehtpps == 3}"> selected </c:if> >Sam.pers.</option> 	
									</select>
			 					</td>
								<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehpss" id="ehpss" size="25" maxlength="24" value="${model.record.ehpss}"></td>
								<td class="text14">
									<select class="inputTextMediumBlue" name="ehlks" id="ehlks" >
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="dto" items="${model.countryDto}" >
				                       	 	<option title="${dto.code}" value="${dto.code}" <c:if test="${model.record.ehlks == dto.code}"> selected </c:if> >${dto.code}</option>
										</c:forEach>
									</select>	
									
								</td>
								
			 				</tr>
			 				<tr >
			 					<td class="text14">&nbsp;</td>
								<td class="text14">&nbsp;<span title="ehad1s">Adress</span></td>
								<td class="text14">&nbsp;<span title="ehpns">Postnr</span></td>
								
			 				</tr>
			 				<tr >
			 					<td class="text14">&nbsp;</td>
								<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehad1s" id="ehad1s" size="25" maxlength="30" value="${model.record.ehad1s}"></td>
								<td class="text14"><input  type="text" class="inputTextMediumBlue" name="ehpns" id="ehpns" size="12" maxlength="9" value="${model.record.ehpns}"></td>
			 				</tr>
			 				
			 				<tr >
			 					<td class="text14">&nbsp;</td>
								<td class="text14">&nbsp;<span title="own_ehems_email">E-post</span></td>
								<td class="text14">&nbsp;<span title="own_ehems_telephone">Telefon</span></td>
								
			 				</tr>
			 				<tr >
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
			 				<tr height="2"><td></td></tr>
			 				
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
				 					<td class="text14">&nbsp;<span title="ehknm">Knr</span></td>
									<td class="text14">&nbsp;<span title="ehnam">Navn</span><font class="text16RedBold" >*</font>
										<a tabindex="-1" id="ehnamIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
										</a>
									</td>
									<td class="text14">&nbsp;<span title="ehrgm">Org.nr /EORI</span><font class="text16RedBold" >*</font></td>
									
				 				</tr>
				 				<tr >
				 					
									<td class="text14"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="ehknm" id="ehknm" size="10" maxlength="8" value="${model.record.ehknm}"></td>
									<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="ehnam" id="ehnam" size="25" maxlength="30" value="${model.record.ehnam}"></td>
									<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="ehrgm" id="ehrgm" size="20" maxlength="17" value="${model.record.ehrgm}"></td>
				 				</tr>
				 				
				 				<tr >
				 					<td class="text14">&nbsp;<span title="ehtppm">Typ.person</span><font class="text16RedBold" >*</font></td>
									<td class="text14">&nbsp;<span title="ehpsm">Sted</span></td>
									<td class="text14">&nbsp;<span title="ehlkm">Landkode</span></td>
									
				 				</tr>
				 				<tr >
				 					<td class="text14">
				 						<select class="inputTextMediumBlueMandatoryField" id="ehtppm" name="ehtppm">
				 							<option title="En fysisk person" value="1" <c:if test="${model.record.ehtppm == 1}"> selected </c:if> >Fys.person</option>
								  			<option title="En juridisk person, det vil si en bedrift" value="2" <c:if test="${model.record.ehtppm == 0 || model.record.ehtppm == 2}"> selected </c:if> >Bedrift</option>
								  			<option title="En samling personer" value="3" <c:if test="${model.record.ehtppm == 3}"> selected </c:if> >Sam.pers.</option> 	
										</select>
				 					</td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehpsm" id="ehpsm" size="25" maxlength="24" value="${model.record.ehpsm}"></td>
									<td class="text14">
										<select class="inputTextMediumBlue" name="ehlkm" id="ehlkm" >
					 						<option value="">-velg-</option>
						 				  	<c:forEach var="dto" items="${model.countryDto}" >
					                       	 	<option title="${dto.code}" value="${dto.code}" <c:if test="${model.record.ehlkm == dto.code}"> selected </c:if> >${dto.code}</option>
											</c:forEach>
										</select>
										
									</td>
									
				 				</tr>
				 				
				 				<tr >
				 					
				 					<td class="text14">&nbsp;</td>
									<td class="text14">&nbsp;<span title="ehad1m">Adress</span></td>
									<td class="text14">&nbsp;<span title="ehpnm">Postnr</span></td>
									
				 				</tr>
				 				<tr >
				 					
				 					<td class="text14">&nbsp;</td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehad1m" id="ehad1m" size="25" maxlength="30" value="${model.record.ehad1m}"></td>
									<td class="text14"><input  type="text" class="inputTextMediumBlue" name="ehpnm" id="ehpnm" size="12" maxlength="9" value="${model.record.ehpnm}"></td>
				 				</tr>
				 				
				 				<tr >
				 					
				 					<td class="text14">&nbsp;</td>
									<td class="text14">&nbsp;<span title="own_ehemm_email">E-post</span></td>
									<td class="text14">&nbsp;<span title="own_ehemm_telephone">Telefon</span></td>
									
				 				</tr>
				 				<tr >
				 					
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
				 				
				 				<tr height="2"><td></td></tr>
				 				
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
		<tr height="3"><td></td></tr>
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
							<div style="display: none;" class="clazz_dialog" id="dialogSend" title="Dialog">
								 <p class="text14" >Er du sikker på at du vil sende till toll.no ?</p>
							</div>
						</c:otherwise>
					</c:choose>
					&nbsp;&nbsp;<input class="inputFormSubmitBlue" type="button" name="buttonInitVoec" id="buttonInitVoec" value='Varelinjer - VOEC'>	
				</c:if>
			</c:if>
		</tr> 
	</table>
	</td>
	</tr>	
	</table> 
	</form>
</td>
</tr>

<%-- list component - changed to own child window ...
<c:if test="${not empty model.record.listItemLines}">
	<tr>
		<td>		
		<table style="width:90%;" border="0" >
	    	<%-- separator 
	        <tr height="2"><td></td></tr> 
			<tr>
				<td>
				<table style="width:90%;" id="containerdatatableTable" cellspacing="2" align="left" >
				<tr>
				<td class="text11">
							
				<table id="mainList" class="compact" >
					<thead>
					<tr class="tableHeaderField" height="20" >
						<th width="2%" class="tableHeaderFieldFirst" >Endre</th>
                    	<th width="2%" class="tableHeaderField" >Lin</th>
                    	<th width="2%" class="tableHeaderField" >Status</th>
                    	<th width="2%" class="tableHeaderField" >Stk</th>
                		<th width="2%" class="tableHeaderField" >Vrd</th>
                		<th width="2%" class="tableHeaderField" >Tariffnr</th>
                		<th width="2%" class="tableHeaderField" >Selger id - VOEC</th>
                		<th width="2%" class="tableHeaderField" >Role</th>
                	</tr>
                	</thead>
                	<tbody> 
                	<c:forEach items="${model.record.listItemLines}" var="itemLinesRecord" varStatus="counter">    
		             <tr class="tableRow" height="20" >
			           <td width="2%" align="center" class="tableCellFirst"><img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="edit"></td>	
		          	   <td width="2%" align="center"class="tableCell" >${itemLinesRecord.eili}</td>
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
			<tr height="2"><td></td></tr> 
			<tr>
				<td>
				<table class="text14 formFrame" style="width:60%; background-color:lightyellow;" cellspacing="2" align="left" >
				<tr>
					<td class="text14"><span title="eili">Lin</span></td>
					<td class="text14"><span title="eibl">Stk</span></td>
					<td class="text14"><span title="eistk">Vrd</span></td>
					<td class="text14"><span title="eivnt">Tariffnr</span></td>
					<td class="text14"><span title="eirge">SelgerId - VOEC</span></td>
				</tr>
				<tr>
					<td class="text14"><input readonly type="text" class="inputTextReadOnly" name="eili" id="eili" size="6" maxlength="5" value=""></td>
					<td class="text14"><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="eibl" id="eibl" size="17" maxlength="15" value=""></td>
					<td class="text14"><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="eistk" id="eistk" size="9" maxlength="7" value=""></td>
					<td class="text14"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="eivnt" id="eivnt" size="8" maxlength="6" value=""></td>
					<td class="text14"><input type="text" class="inputTextMediumBlue" name="eirge" id="eirge" size="19" maxlength="17" value=""></td>
					<td class="text14">
						<input class="inputFormSubmit" type="button" name="itemLineButton" id="itemLineButton" value='Lagre'>
					</td>
				</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</c:if>

--%>




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
 
 	
 
	