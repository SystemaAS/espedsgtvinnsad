<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_edit_master.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_logger.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
<table style="width:90%;" cellspacing="0" border="0" cellpadding="0">

 <tr>
 <td>	
	<%-- tab container component --%>
	<table style="width:100%;"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25">
			 
			 		<%-- TEMP --%>
			 
			 		<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkTransportList" style="display:block;" href="tvinnsaddigitollv2.do?action=doFind">
							<font class="tabDisabledLink">&nbsp;Transportliste</font>
							<img src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			
					
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkTransport" style="display:block;" href="tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=${model.record.emlnrt}">													
							<font class="tabDisabledLink">
								&nbsp;Transport&nbsp;
								<c:if test="${model.record.emlnrt > 0}">
									<font class="text14MediumBlue">&nbsp;${model.record.emlnrt}</font>
								</c:if>
							</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
						<c:choose>
						<c:when test="${model.record.emlnrm > 0}">
							<td title="${model.record.emlnrm}" width="15%" valign="bottom" class="tab" align="center" nowrap>
						</c:when>
						<c:otherwise>
							<%-- Meaning CreateNew --%>
							<td width="15%" valign="bottom" class="tab" style="background-color:lightyellow;" style="" align="center" nowrap>
						</c:otherwise>
						</c:choose>
						<font class="tabLink">
							&nbsp;<spring:message code="systema.tvinn.sad.digitoll.list.tab.master"/>
						</font>
						<img src="resources/images/update.gif" border="0" alt="edit">
						<c:if test="${model.record.emlnrm > 0}">
							<font class="text14MediumBlue">&nbsp;${model.record.emlnrm}</font>
						</c:if>
						
					</td>
					<c:if test="${model.record.emlnrm > 0 && model.record.emst != 'S'}">
						<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
						<td width="15%" valign="bottom" class="tabDisabled" style="background-color:lightyellow;" align="center" nowrap>
							<a id="alinkHouse" style="display:block;" href="tvinnsaddigitollv2_edit_house.do?action=doCreate&ehlnrt=${model.record.emlnrt}&ehlnrm=${model.record.emlnrm}&ehavd=${model.record.emavd}&ehpro=${model.record.empro}">
								<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.createnew"/>&nbsp;<spring:message code="systema.tvinn.sad.digitoll.list.tab.house"/></font>
								<img src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
							</a>
						</td>
					</c:if>
					
					<%-- <td width="50%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>  --%>
			 		<td width="60%" class="tabFantomSpace" align="right" nowrap><font class="tabDisabledLink">&nbsp;</font>
				 		<c:if test="${not empty model.record.emuuid}">
				 			<span cursor:pointer;" onClick="showPop('requestIdBup');" title="Sist godkjente MRN" class="inputTextReadOnly text11" style="vertical-align:super;">MRN:&nbsp;${model.record.emmid_own}</span>
				 			&nbsp;&nbsp;
				 			<div class="text12" style="position: relative;display: inline;" align="left">
							<span style="position:absolute; left:-200px; top:15px; width:280px;" id="requestIdBup" class="popupWithInputText"  >
								<div class="text12" align="left">
				           			<span class="uuidLinkParent" style="color:green;cursor:pointer;" id="${model.record.emuuid_own}">${model.record.emuuid_own}</span>
					           		<br/>
				           			<button name="_ButtonCloseApi" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('requestIdBup');">Close</button> 
				           		</div>
				           	</span>
				           	</div>	
			 			</c:if>
			 			
			 			<a style="vertical-align:super;" title="Kodeverk toll.no" target="_blank" href="https://toll.github.io/api/mo-kodeverk.html">
							<font title="Kodeverk toll.no" class="inputFormSubmit text10 isa_warning" ><b>Kodeverk</b></font>
						</a>
					
			 			<font style="vertical-align:super; cursor:pointer;" onClick="showPop('api-spec');" title="api-spesifikasjon toll.no" class="inputFormSubmit text10 isa_warning"><b>Api-spec.</b></font>
						<div class="text12" style="position: relative;display: inline;" align="left">
						<span style="position:absolute; left:-100px; top:15px;" id="api-spec" class="popupWithInputText"  >
			           		<div class="text11" align="left">
			           			<a class="text11" target="_blank" href="https://api-test.toll.no/api/movement/road/v2/swagger-ui/index.html">
				           				1. Road
				           		</a>
				           		<br/>
			           			<a class="text11" target="_blank" href="https://api-test.toll.no/api/movement/air/v1/swagger-ui/index.html">
			           					2. Air
			           			</a>
			           			<br/>
			           			<a class="text11" target="_blank" href="https://toll.github.io/">
			           					3. Digitoll - Teknisk informasjon
			           			</a>
			           			<br/>
			           			<br/>
			           			<button name="_ButtonCloseApi" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('api-spec');">Close</button> 
			           		</div>
			           	</span>
			           	</div>	
			 			
						<img style="vertical-align:bottom;cursor:pointer;" id="imgInfoRpgJarStart" onClick="showPop('jarStartCmd');" src="resources/images/log-iconLOG.png" width="22" height="22" border="0" alt="info">
						<div class="text12" style="position: relative;display: inline;" align="left">
						<span style="position:absolute; left:-100px; top:15px;"  id="jarStartCmd" class="popupWithInputText"  >
			           		<div class="text11" align="left">
			           			<a id="alinkLogsgLoggerApi" ><span class="text12" style="cursor:pointer;color:green;">1. Api-log</span></a><br/><br/>
			           			<a id="alinkLogsgLoggerSadService" ><span class="text12" style="cursor:pointer;color:green;">2. Sad-service-log</span></a><br/>
			           			<a id="alinkLogsgLoggerRoadEntry" ><span class="text12" style="cursor:pointer;color:green;">3. Road-Enry-log</span></a><br/>
			           			<a id="alinkLogsgLoggerCatalina" ><span class="text12" style="cursor:pointer;color:green;">4. Catalina-log</span></a><br/>
			           			
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
	<form name="manifestForm" id="manifestForm" action="tvinnsaddigitollv2_edit_master.do" method="post">
			<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
			<input type="hidden" name="emuuid" id="emuuid" value="${model.record.emuuid}"> 
			<input type="hidden" name="emmid" id="emmid" value="${model.record.emmid}">
			<input type="hidden" name="emlnrt" id="emlnrt" value="${model.record.emlnrt}">
			<input type="hidden" name="action" id="action" value="doUpdate">
			
			<c:if test="${model.record.emlnrm > 0}">
				<input type="hidden" name="emlnrm" id="emlnrm" value="${model.record.emlnrm}"> 
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
					<c:choose>
               		<c:when test="${ not empty model.record.transportDto.etktyp && fn:startsWith(model.record.transportDto.etktyp,'4') }">
						<img title="api:air" style="vertical-align:middle;" id="airplaneImg" src="resources/images/airplaneBlue.png" width="25" height="25"border="0" >
					</c:when>
					<c:otherwise>
						<img title="api:road" style="vertical-align:middle;" id="lorryImg" src="resources/images/lorry_green.png" width="20" height="20"border="0" >
					</c:otherwise>
					</c:choose>
					<c:if test="${not empty model.record.emmid}">
						<c:if test="${model.record.emst2 != 'C'}">
							&nbsp;
							<input title="Slett fra toll.no" class="inputFormSubmitStd" type="button" name="deleteButton" id="deleteButton" value='Slett'>
							<div style="display: none;" class="clazz_dialog" id="dialogDelete" title="Dialog">
								 <p class="text14" >Er du sikker på at du ønsker å slette fra toll.no?</p>
							</div>
						</c:if>
					</c:if>		
		    		<c:if test="${model.record.emlnrm > 0}">
			    		<a id="alinkRefreshButton" href="tvinnsaddigitollv2_edit_master.do?action=doFind&emlnrt=${model.record.emlnrt}&emlnrm=${model.record.emlnrm}">
			    			&nbsp;<input title="Refresh all status..." class="inputFormSubmitStd" type="button" name="refreshButton" id="refreshButton" value='Refresh'>
			    		</a>
		    		</c:if>
		    		
					<%-- <span title="MRN nr. hos toll.no - per Master" >MRN-Api&nbsp;</span><font class="text14SkyBlue">${model.record.emmid}</font> --%>
		    		<span title="MRN nr. hos toll.no - per Master" >MRN-Api&nbsp;<a class="uuidLinkParent text14SkyBlue" id="${model.record.emmid}">${model.record.emmid}</a>
		    		&nbsp;&nbsp;<font style="font-weight: bold;color: lightgray;">|</font>&nbsp;&nbsp;
		    		<span title="Transaktionsid hos toll.no - per request" >Trans.id&nbsp;</span><a title="les status på toll.no" class="uuidLinkParent text14SkyBlue" id="${model.record.emuuid}">${model.record.emuuid}</a>
		    		&nbsp;&nbsp;<font style="font-weight: bold;color: lightgray;">|</font>&nbsp;&nbsp;
		    		
		    		<a title="lese logg" tabindex=-1 id="${model.record.emlnrt}_${model.record.emlnrm}" class="logLink" runat="server" href="#"><font class="text14 ">Api.st - log</font>&nbsp;
						<c:choose>
						<c:when test="${model.record.emst2 == 'S' || model.record.emst2 == 'R' || model.record.emst2 == 'D' || model.record.emst2 == 'C'}">
							<c:if test="${model.record.emst2 == 'S'}">
								<img src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
								<font style="color:gray;">SUBMITTED</font>
							</c:if>
							<c:if test="${model.record.emst2 == 'C'}">
								<img src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
								<font style="color:gray;">COMPLETED</font>
							</c:if>
							<c:if test="${model.record.emst2 == 'D'}">
								<img src="resources/images/bulletRed.png" width="10" height="10" border="0" >
								<font style="color:red;">SLETTET</font>
							</c:if>
							<c:if test="${model.record.emst2 == 'R'}">
								<font style="color:brown;">REOPENED/DRAFT</font>
							</c:if>
						</c:when>
						<c:otherwise>
							<c:choose>
							<c:when test="${model.record.emst2 == 'M'}">
								<img src="resources/images/bulletRed.png" width="10" height="10" border="0" >
								<font style="color:red">ERROR&nbsp;</font>
							</c:when>
							<c:otherwise>
								<font style="color:gray;">${model.record.emst2}&nbsp;</font>
							</c:otherwise>
							</c:choose>
						</c:otherwise>
						</c:choose>
					</a>
					<c:if test="${model.record.emst2 == 'M'}">
						<span title="last error text..." onClick="showPop('logErrorText_info');" class="inputFormSubmit text11 isa_error" style="cursor:pointer;"><b>mer info ...</b></span>
						<span class="text11" style="position: relative;" align="left">
	                	<span style="position:absolute;top:2px; " id="logErrorText_info" class="popupWithInputText text11"  >
	                	<button name="_ButtonCloseErrorTextLog" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('logErrorText_info');">Close</button> 
		           			<p>
		           				${model.logErrorText}
		           			</p>
						</span>	
						</span>
					
					</c:if>
					
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
						<td class="text14">&nbsp;<span title="emavd">Avd</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="empro">Tur</span><font class="text16RedBold" >*</font>
							<a tabindex="-1" id="emproIdLink">
								<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
							</a>
						</td>
						<td class="text14">&nbsp;<span title="emsg">Sign</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="emvkb">Bruttovekt</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="emcn">Container</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="emdkm">Dok.nr</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="emdkmt">Dok.type</span><font class="text16RedBold" >*</font></td>
						
						<td class="text14">&nbsp;<span title="emst">St.</span></td>
						<td class="text14">&nbsp;<span title="emst2">
								St<a tabindex=-1 id="updateInternalStatus2Link" name="updateInternalStatus2Link" runat="server" href="#">.</a>2
						 </span>						
						
						
						</td>
						<td class="text14">&nbsp;<span title="emst3"><a tabindex=-1 id="updateInternalStatus3Link" name="updateInternalStatus3Link" runat="server" href="#"><span class="text14">St.3</span></a></span></td>
						<td class="text14">&nbsp;<span title="emdtr">Reg.dato</span></td>
						<td class="text14">&nbsp;<span title="emdtin">Send.dato</span></td>
												
					</tr>
					<tr>
						
						<td>
							<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  class="inputTextMediumBlueMandatoryField" id="emavd" name="emavd">
							  <option value="">-Välj-</option>
			 				  	<c:forEach var="record" items="${model.avdList}" >
			 				  		<option title="${record.namn}" value="${record.avd}"<c:if test="${model.record.emavd == record.avd}"> selected </c:if> >${record.avd}</option> 
								</c:forEach>  
							</select>						 
	 					</td>
	 					<td class="text14">
							<c:choose>
								<c:when test="${model.record.empro > 0}">
									<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="empro" id="empro" size="10" maxlength="8" value="${model.record.empro}">	
								</c:when>
								<c:otherwise>
									<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="empro" id="empro" size="10" maxlength="8" value="">								
								</c:otherwise>
							</c:choose>
							<%--
							<input title="Hent informasjon fra turen ..." class="text11" type="button" name="turFetchButton" id="turFetchButton" value='O'>
							 --%> 					
						</td>		
						<td>
							<%--
	 						<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  size="6" maxlength="3" class="inputTextMediumBlueMandatoryField" list="emsg_list" id="emsg" name="emsg" value="${model.record.emsg}">
							<datalist id="emsg_list">
							  <option value="">-Välj-</option>
			 				  	<c:forEach var="record" items="${model.signList}" >
			 				  		<option value="${record.sign}"<c:if test="${model.record.emsg == record.sign}"> selected </c:if> >${record.sign}</option> 
								</c:forEach>  
							</datalist>
							 --%>
							<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  class="inputTextMediumBlueMandatoryField" id="emsg" name="emsg">
							<option value="">-Välj-</option>
			 				  	<c:forEach var="record" items="${model.signList}" >
			 				  		<option title="${record.namn}" value="${record.sign}"<c:if test="${model.record.emsg == record.sign}"> selected </c:if> >${record.sign}</option> 
								</c:forEach>  
							</select> 		
	 					</td>
					
						<td class="text14">
							<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="emvkb" id="emvkb" size="11" maxlength="9" value="${model.record.emvkb}">									
						</td>
					
						<td class="text14">
							<select class="inputTextMediumBlueMandatoryField" id="emcn" name="emcn">
					  			<option title="Varer ikke transportert i beholder" value="0" <c:if test="${empty model.record.emcn || model.record.emcn == '0'}"> selected </c:if> >0</option>
	 							<option title="Varer transportert i beholder" value="1" <c:if test="${model.record.emcn == '1'}"> selected </c:if> >1</option>
							</select>									
						</td>
						<td class="text14">
							<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="emdkm" id="emdkm" size="25" maxlength="40" value="${model.record.emdkm}">
							<input title="unik nøkkel" readonly type="text" class="inputTextReadOnly" name="own_emdkmUnique" id="own_emdkmUnique" size="12" style="color: brown;" value="${model.record.own_emdkmUnique}">	
							
						</td>
						<td class="text14">
							<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  class="inputTextMediumBlueMandatoryField" name="emdkmt" id="emdkmt" >
		 						<option value="">-velg-</option>
			 				  	<c:forEach var="dto" items="${model.previousDocumentsDto}" >
		                       	 	<option title="${dto.txt1}&nbsp;-&nbsp;${dto.txt2}" value="${dto.code}" <c:if test="${model.record.emdkmt == dto.code}"> selected </c:if> >${dto.code}</option>
								</c:forEach>
							</select>		
						</td>
						
						
						<td class="text14">
							<input readonly type="text" class="inputTextReadOnly" name="emst" id="emst" size="2" maxlength="1" value="${model.record.emst}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextReadOnly" name="emst2" id="emst2" size="2" maxlength="1" value="${model.record.emst2}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextReadOnly" name="emst3" id="emst3" size="2" maxlength="1" value="${model.record.emst3}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextReadOnly" name="emdtr" id="emdtr" size="10" maxlength="8" value="${model.record.emdtrStr}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextReadOnly" name="emdtin" id="emdtin" size="10" maxlength="8" value="${model.record.emdtin}">		
						</td>
					</tr>
					
					<tr>
						<td class="text14">&nbsp;<span title="emknt Transportør KundeNr.">Transp.knr.</span></td>
						<td colspan="2" class="text14">&nbsp;<span title="emrgt - Transportør OrgNr. / EORI">Transp.Orgnr / EORI</span><font class="text16RedBold" >*</font></td>
					</tr>
					<tr>
						<td class="text14">
							<input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="emknt" id="emknt" size="10" maxlength="8" value="${model.record.emknt}">								
						</td>
						<td colspan="2" class="text14">
							<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="emrgt" id="emrgt" size="19" maxlength="17" value="${model.record.emrgt}">												
						</td>
						<td colspan="12">
							<table>
							<tr>
								<td class="inputFormSubmit text12 isa_success" style="cursor: not-allowed">Transportør&nbsp;<b>${model.record.transportDto.etnat}</b>
										&nbsp;&nbsp;Kjøretøy k.merke&nbsp;<b>${model.record.transportDto.etkmrk}</b>
										&nbsp;&nbsp;ETA&nbsp;<b>${model.record.transportDto.etetadStr}</b>&nbsp;&nbsp;ETA-tid&nbsp;<b>${model.record.transportDto.etetatStr}</b>
								</td>
							</tr>
							</table>
						</td>
					
					</tr>
					
					<tr height="2"><td></td></tr>
					<tr>
						<td colspan="15" class="text14" valign="top">
						<table style="width:90%" align="left" class="tableBorderWithRoundCorners" border="0" cellspacing="1" cellpadding="1">
						<tr>
							<td class="text14">&nbsp;<span title="emsdlt">Lastested</span></td>
							<td class="text14">&nbsp;<span title="emlkl">Land</span></td>
							<td class="text14">&nbsp;<span title="emsdl">UN/LOCODE</span></td>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td class="text14">&nbsp;<span title="emsdut">Lossested</span></td>
							<td class="text14">&nbsp;<span title="emlku">Land</span></td>
							<td class="text14">&nbsp;<span title="emsdu">UN/LOCODE</span></td>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td class="text14">&nbsp;<span title="emsddt">Leveringssted</span></td>
							<td class="text14">&nbsp;<span title="emlkd">Land</span></td>
							<td class="text14">&nbsp;<span title="emsdd">UN/LOCODE</span></td>
						</tr>
						<tr>	
							<td class="text14">
								<input  type="text" class="inputTextMediumBlue" name="emsdlt" id="emsdlt" size="25" maxlength="30" value="${model.record.emsdlt}">								
							</td>
							<td class="text14">
								<select class="inputTextMediumBlue" name="emlkl" id="emlkl" >
			 						<option value="">-velg-</option>
				 				  	<c:forEach var="dto" items="${model.countryDto}" >
			                       	 	<option title="${dto.code}" value="${dto.code}" <c:if test="${model.record.emlkl == dto.code}"> selected </c:if> >${dto.code}</option>
									</c:forEach>
								</select>
																			
							</td>
							<td class="text14">
								<input  type="text" class="inputTextMediumBlue" name="emsdl" id="emsdl" size="7" maxlength="5" value="${model.record.emsdl}">								
							</td>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td class="text14">
								<input  type="text" class="inputTextMediumBlue" name="emsdut" id="emsdut" size="25" maxlength="30" value="${model.record.emsdut}">											
							</td>
							<td class="text14">
								<select class="inputTextMediumBlue" name="emlku" id="emlku" >
			 						<option value="">-velg-</option>
				 				  	<c:forEach var="dto" items="${model.countryDto}" >
			                       	 	<option title="${dto.code}" value="${dto.code}" <c:if test="${model.record.emlku == dto.code}"> selected </c:if> >${dto.code}</option>
									</c:forEach>
								</select>												
							</td>
							<td class="text14">
								<input  type="text" class="inputTextMediumBlue" name="emsdu" id="emsdu" size="7" maxlength="5" value="${model.record.emsdu}">											
							</td>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td class="text14">
								<input  type="text" class="inputTextMediumBlue" name="emsddt" id="emsddt" size="25" maxlength="30" value="${model.record.emsddt}">											
							</td>
							<td class="text14">
								<select class="inputTextMediumBlue" name="emlkd" id="emlkd" >
			 						<option value="">-velg-</option>
				 				  	<c:forEach var="dto" items="${model.countryDto}" >
			                       	 	<option title="${dto.code}" value="${dto.code}" <c:if test="${model.record.emlkd == dto.code}"> selected </c:if> >${dto.code}</option>
									</c:forEach>
								</select>												
							</td>
							<td class="text14">
								<input  type="text" class="inputTextMediumBlue" name="emsdd" id="emsdd" size="7" maxlength="5" value="${model.record.emsdd}">											
							</td>
						</tr>
						</table>
						</td>
					</tr>
					<tr height="2"><td></td></tr>
					<tr>
						<td colspan="15" class="text14" valign="top">
						<table style="width:90%" align="left" class="tableBorderWithRoundCorners" border="0" cellspacing="1" cellpadding="1">
							<tr>
								<td colspan="4" class="text14"><b>&nbsp;Container.1</b></td>
								<td colspan="4" class="text14"><b>&nbsp;Container.2</b></td>
								<td colspan="4" class="text14"><b>&nbsp;Container.3</b></td>
							</tr>
							<tr>
								<td class="text14">&nbsp;<span title="emc1ty">Size</span></td>
								<td class="text14">&nbsp;<span title="emc1ps">Pack.</span></td>
								<td class="text14">&nbsp;<span title="emc1ss">Supp.type</span></td>
								<td class="text14">&nbsp;<span title="emc1id">Id</span></td>
								
								<td class="text14">&nbsp;<span title="emc2ty">Size</span></td>
								<td class="text14">&nbsp;<span title="emc2ps">Pack.</span></td>
								<td class="text14">&nbsp;<span title="emc2ss">Supp.type</span></td>
								<td class="text14">&nbsp;<span title="emc2id">Id</span></td>	
								
								<td class="text14">&nbsp;<span title="emc3ty">Size</span></td>
								<td class="text14">&nbsp;<span title="emc3ps">Pack.</span></td>
								<td class="text14">&nbsp;<span title="emc3ss">Supp.type</span></td>
								<td class="text14">&nbsp;<span title="emc3id">Id</span></td>	
							</tr>
							<tr>
								<td class="text14">
									<select class="inputTextMediumBlue" name="emc1ty" id="emc1ty" >
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="dto" items="${model.containerSizeAndTypeDto}" >
				                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.emc1ty == dto.code}"> selected </c:if> >${dto.code}</option>
										</c:forEach>
									</select>
											
								</td>
								<td class="text14">
									<select class="inputTextMediumBlue" name="emc1ps" id="emc1ps" >
				 						<option value="">-velg-</option>
					 				  	<option title="Tom" value="A" <c:if test="${model.record.emc1ps == 'A'}"> selected </c:if> >Tom</option>
					 				  	<option title="Ikke tom" value="B" <c:if test="${model.record.emc1ps == 'B'}"> selected </c:if> >Ikke tom</option>
									</select>
											
								</td>
								<td class="text14">
									<select class="inputTextMediumBlue" name="emc1ss" id="emc1ss" >
				 						<option value="">-velg-</option>
					 				  	<option title="Tom" value="1" <c:if test="${model.record.emc1ss == '1'}"> selected </c:if> >Lev.avs.</option>
					 				  	<option title="Ikke tom" value="2" <c:if test="${model.record.emc1ss == '2'}"> selected </c:if> >Lev.transp.</option>
									</select>		
								</td>
								<td class="text14">
									<input  type="text" class="inputTextMediumBlue" name="emc1id" id="emc1id" size="18" maxlength="17" value="${model.record.emc1id}">		
								</td>
								
								<td class="text14">
									<select class="inputTextMediumBlue" name="emc2ty" id="emc2ty" >
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="dto" items="${model.containerSizeAndTypeDto}" >
				                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.emc2ty == dto.code}"> selected </c:if> >${dto.code}</option>
										</c:forEach>
									</select>		
								</td>
								<td class="text14">
									<select class="inputTextMediumBlue" name="emc2ps" id="emc2ps" >
				 						<option value="">-velg-</option>
					 				  	<option title="Tom" value="A" <c:if test="${model.record.emc2ps == 'A'}"> selected </c:if> >Tom</option>
					 				  	<option title="Ikke tom" value="B" <c:if test="${model.record.emc2ps == 'B'}"> selected </c:if> >Ikke tom</option>
									</select>
											
								</td>
								<td class="text14">
									
									<select class="inputTextMediumBlue" name="emc2ss" id="emc2ss" >
				 						<option value="">-velg-</option>
					 				  	<option title="Tom" value="1" <c:if test="${model.record.emc2ss == '1'}"> selected </c:if> >Lev.avs.</option>
					 				  	<option title="Ikke tom" value="2" <c:if test="${model.record.emc2ss == '2'}"> selected </c:if> >Lev.transp.</option>
									</select>		
								</td>
								<td class="text14">
									<input  type="text" class="inputTextMediumBlue" name="emc2id" id="emc2id" size="18" maxlength="17" value="${model.record.emc2id}">		
								</td>
								
								<td class="text14">
									<select class="inputTextMediumBlue" name="emc3ty" id="emc3ty" >
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="dto" items="${model.containerSizeAndTypeDto}" >
				                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.emc3ty == dto.code}"> selected </c:if> >${dto.code}</option>
										</c:forEach>
									</select>		
								</td>
								<td class="text14">
									<select class="inputTextMediumBlue" name="emc3ps" id="emc3ps" >
				 						<option value="">-velg-</option>
					 				  	<option title="Tom" value="A" <c:if test="${model.record.emc3ps == 'A'}"> selected </c:if> >Tom</option>
					 				  	<option title="Ikke tom" value="B" <c:if test="${model.record.emc3ps == 'B'}"> selected </c:if> >Ikke tom</option>
									</select>
											
								</td>
								<td class="text14">
									<select class="inputTextMediumBlue" name="emc3ss" id="emc3ss" >
				 						<option value="">-velg-</option>
					 				  	<option title="Tom" value="1" <c:if test="${model.record.emc3ss == '1'}"> selected </c:if> >Lev.avs.</option>
					 				  	<option title="Ikke tom" value="2" <c:if test="${model.record.emc3ss == '2'}"> selected </c:if> >Lev.transp.</option>
									</select>		
								</td>
								<td class="text14">
									<input  type="text" class="inputTextMediumBlue" name="emc3id" id="emc3id" size="18" maxlength="17" value="${model.record.emc3id}">		
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
		<table style="width:70%;" border="0">
 		<tr>
			<td class="text14" valign="top">
				<table >
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
					 	<td valign="top">
						<table style="width:100%;" class="formFrame" border="0" cellspacing="1" cellpadding="0">
					 		<tr>
				 				<td>
				 				<table>
				 				<tr >
									<td class="text14">&nbsp;<span title="emkns">Knr</span></td>
									<td class="text14">&nbsp;<span title="emnas">Navn</span><font class="text16NavyBlueBold" >*</font>
										<a tabindex="-1" id="emnasIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
										</a>
									</td>
									<td class="text14">&nbsp;<span title="emrgs">Org.nr /EORI</span><font class="text16NavyBlueBold" >*</font></td>
									
				 				</tr>
				 				<tr >
									
									<td class="text14"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="emkns" id="emkns" size="10" maxlength="8" value="${model.record.emkns}"></td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="emnas" id="emnas" size="25" maxlength="30" value="${model.record.emnas}"></td>
									<td class="text14"><input  type="text" class="inputTextMediumBlue" name="emrgs" id="emrgs" size="20" maxlength="17" value="${model.record.emrgs}"></td>
				 				</tr>
				 				<tr >
				 					<td class="text14">&nbsp;<span title="emtpps">Typ.person</span><font class="text16NavyBlueBold" >*</font></td>
									<td class="text14">&nbsp;<span title="empss">Sted</span></td>
									<td class="text14">&nbsp;<span title="emlks">Landkode</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14">
										<select class="inputTextMediumBlue" id="emtpps" name="emtpps">
											<option value="">-velg-</option>
											<option title="En fysisk person" value="1" <c:if test="${model.record.emtpps == 1}"> selected </c:if> >Fys.person</option>
								  			<option title="En juridisk person, det vil si en bedrift" value="2" <c:if test="${model.record.emtpps == 2}"> selected </c:if> >Bedrift</option>
								  			<option title="En samling personer" value="3" <c:if test="${model.record.emtpps == 3}"> selected </c:if> >Sam.pers.</option> 	
										</select>
									</td>
									
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="empss" id="empss" size="25" maxlength="24" value="${model.record.empss}"></td>
									<td class="text14">
										<select class="inputTextMediumBlue" name="emlks" id="emlks" >
					 						<option value="">-velg-</option>
						 				  	<c:forEach var="dto" items="${model.countryDto}" >
					                       	 	<option title="${dto.code}" value="${dto.code}" <c:if test="${model.record.emlks == dto.code}"> selected </c:if> >${dto.code}</option>
											</c:forEach>
										</select>
									</td>
				 				</tr>
				 				<tr >
				 					<td class="text14">&nbsp;</td>
									<td class="text14">&nbsp;<span title="emad1s">Adress</span></td>
									<td class="text14">&nbsp;<span title="empns">Postnr</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14">&nbsp;</td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="emad1s" id="emad1s" size="25" maxlength="30" value="${model.record.emad1s}"></td>
									<td class="text14"><input  type="text" class="inputTextMediumBlue" name="empns" id="empns" size="12" maxlength="9" value="${model.record.empns}"></td>
				 				</tr>
				 				
				 				<tr >
				 					<td class="text14">&nbsp;</td>
									<td class="text14">&nbsp;<span title="own_emems_email">E-post</span></td>
									<td class="text14">&nbsp;<span title="own_emems_telephone">Telefon</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14">&nbsp;</td>
									<c:choose>
				 					<c:when test="${empty model.record.ememst}">
				 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_emems_email" id="own_emems_email" size="35" maxlength="50" value=""></td>
				 						<td class="text14"><input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="own_emems_telephone" id="own_emems_telephone" size="15" maxlength="50" value=""></td>
				 					</c:when>
				 					<c:otherwise>
				 						<c:choose>
						 					<c:when test="${model.record.ememst == 'EM'}">
						 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_emems_email" id="own_emems_email" size="35" maxlength="50" value="${model.record.emems}"></td>
						 						<td class="text14"><input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="own_emems_telephone" id="own_emems_telephone" size="15" maxlength="50" value=""></td>
						 					</c:when>
						 					<c:otherwise>
						 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_emems_email" id="own_emems_email" size="35" maxlength="50" value=""></td>
												<td class="text14"><input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="own_emems_telephone" id="own_emems_telephone" size="15" maxlength="50" value="${model.record.emems}"></td>
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
				<table style="width:80%" align="left" border="0" cellspacing="1" cellpadding="0">
				 	<tr >
					 	<td >
						<table style="width:100%;" class="formFrameHeader" border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<td class="text14White">&nbsp;&nbsp;Mottaker&nbsp;</td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
		            <tr >
					 	<td valign="top">
						<table style="width:100%;" class="formFrame" border="0" cellspacing="1" cellpadding="0">
					 		<tr>
				 				<td>
				 				<table>
				 				<tr >
									<td class="text14">&nbsp;<span title="emknm">Knr</span></td>
									<td class="text14">&nbsp;<span title="emnam">Navn</span><font class="text16NavyBlueBold" >*</font>
										<a tabindex="-1" id="emnamIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
										</a>
									</td>
									<td class="text14">&nbsp;<span title="emrgm">Org.nr /EORI</span><font class="text16NavyBlueBold" >*</font></td>
				 				</tr>
				 				<tr >
									
									<td class="text14"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="emknm" id="emknm" size="10" maxlength="8" value="${model.record.emknm}"></td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="emnam" id="emnam" size="25" maxlength="30" value="${model.record.emnam}"></td>
									<td class="text14"><input  type="text" class="inputTextMediumBlue" name="emrgm" id="emrgm" size="20" maxlength="17" value="${model.record.emrgm}"></td>
				 				
				 				</tr>
				 				
				 				<tr >
									
				 					<td class="text14">&nbsp;<span title="emtppm">Typ.person</span><font class="text16NavyBlueBold" >*</font></td>
									<td class="text14">&nbsp;<span title="empsm">Sted</span></td>
									<td class="text14">&nbsp;<span title="emlkm">Landkode</span></td>
									
				 				</tr>
				 				<tr>
									<td class="text14">
										<select class="inputTextMediumBlue" id="emtppm" name="emtppm">
											<option value="">-velg-</option>
											<option title="En fysisk person" value="1" <c:if test="${model.record.emtppm == 1}"> selected </c:if> >Fys.person</option>
								  			<option title="En juridisk person, det vil si en bedrift" value="2" <c:if test="${model.record.emtppm == 2}"> selected </c:if> >Bedrift</option>
								  			<option title="En samling personer" value="3" <c:if test="${model.record.emtppm == 3}"> selected </c:if> >Sam.pers.</option> 	
										</select>
									</td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="empsm" id="empsm" size="25" maxlength="24" value="${model.record.empsm}"></td>
									<td class="text14">
										<select class="inputTextMediumBlue" name="emlkm" id="emlkm" >
					 						<option value="">-velg-</option>
						 				  	<c:forEach var="dto" items="${model.countryDto}" >
					                       	 	<option title="${dto.code}" value="${dto.code}" <c:if test="${model.record.emlkm == dto.code}"> selected </c:if> >${dto.code}</option>
											</c:forEach>
										</select>
									</td>
				 				</tr>
				 				<tr >
									
				 					<td class="text14">&nbsp;</td>
									<td class="text14">&nbsp;<span title="emad1m">Adress</span></td>
									<td class="text14">&nbsp;<span title="empnm">Postnr</span></td>
				 				</tr>
				 				<tr >
									
				 					<td class="text14">&nbsp;</td>
				 					<td class="text14"><input type="text" class="inputTextMediumBlue" name="emad1m" id="emad1m" size="25" maxlength="30" value="${model.record.emad1m}"></td>
									<td class="text14"><input  type="text" class="inputTextMediumBlue" name="empnm" id="empnm" size="12" maxlength="9" value="${model.record.empnm}"></td>
				 				</tr>
				 				<tr >
				 					
				 					<td class="text14">&nbsp;</td>
									<td class="text14">&nbsp;<span title="own_ememm_email">E-post</span></td>
									<td class="text14">&nbsp;<span title="own_ememm_telephone">Telefon</span></td>
									
				 				</tr>
				 				<tr >
									
				 					<td class="text14">&nbsp;</td>
				 					
									<c:choose>
				 					<c:when test="${empty model.record.ememmt}">
				 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_ememm_email" id="own_ememm_email" size="35" maxlength="50" value=""></td>
				 						<td class="text14"><input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="own_ememm_telephone" id="own_ememm_telephone" size="15" maxlength="50" value=""></td>
				 					</c:when>
				 					<c:otherwise>
				 						<c:choose>
						 					<c:when test="${model.record.ememmt == 'EM'}">
						 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_ememm_email" id="own_ememm_email" size="35" maxlength="50" value="${model.record.ememm}"></td>
						 						<td class="text14"><input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="own_ememms_telephone" id="own_ememm_telephone" size="15" maxlength="50" value=""></td>
						 					</c:when>
						 					<c:otherwise>
						 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_ememm_email" id="own_ememm_email" size="35" maxlength="50" value=""></td>
												<td class="text14"><input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="own_ememm_telephone" id="own_ememm_telephone" size="15" maxlength="50" value="${model.record.ememm}"></td>
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
				<table style="width:60%" align="left" border="0" cellspacing="1" cellpadding="0">
				 	<tr >
					 	<td >
						<table class="formFrameHeader" style="width:100%;" border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<td class="text14White">
						 			&nbsp;
						 			<img style="cursor:pointer;" onMouseOver="showPop('ombud_info');" onMouseOut="hidePop('ombud_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
					            	<span title="Representant...">Representat / Ombud</span>
			                		<div class="text11" style="position: relative;" align="left">
				                	<span style="position:absolute;top:2px; width:250px;" id="ombud_info" class="popupWithInputText text11"  >
					           		<b>Representant</b>
					           		<p>Fyller du inn e-postadressene, sendes også hele Representantinformasjonen fra Transportinformasjonen</p>
									</span>	
									</div>
					 			</td>
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
				 					<td class="text14">&nbsp;<span title="emrcem1">E-post (1) <font style="font-size:11px;">bekreft. for fristilling/innpass.</font></span></td>
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="emrcem1" id="emrcem1" size="35" maxlength="50" value="${model.record.emrcem1}"></td>									
				 				</tr>
				 				<tr height="2"><td></td></tr>
				 				<tr >
				 					<td class="text14">&nbsp;<span title="emrcem2">E-post (2) <font style="font-size:11px;">bekreft. for fristilling/innpass.</font></span></td>
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="emrcem2" id="emrcem2" size="35" maxlength="50" value="${model.record.emrcem2}"></td>									
				 				</tr>
				 				<tr height="2"><td></td></tr>
				 				<tr >
				 					<td class="text14">&nbsp;<span title="emrcem3">E-post (3) <font style="font-size:11px;">bekreft. for fristilling/innpass.</font></span></td>
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="emrcem3" id="emrcem3" size="35" maxlength="50" value="${model.record.emrcem3}"></td>									
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
			<c:if test="${model.record.emst != 'S' && model.record.emst2 != 'C'}"> <%-- CANCELED(S) and COMPLETED(C) --%>
				&nbsp;&nbsp;<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'>
				<c:if test="${model.record.emlnrm > 0}">
					<c:choose>
						<c:when test="${model.record.emst2 == 'C' }"> <%--COMPLETED(C) --%>
							<%-- not possible --%>
						</c:when>
						<c:otherwise>
							<c:choose>
							<%--E(X)ECUTING on async triggered. emst3:PENDING is reserved to sendAllHouses at a master level 
							    This in order not to block the master SEND while sendAllHouses is still running in the background --%>
							<c:when test="${model.record.emst == 'X' }"> 
								<input title="Executing status ..." class="buttonGrayInsideDivPopup" style="cursor:not-allowed;color:brown;" type="button" name="sendButton" id="sendButton" value='Send'>
							</c:when>
							<c:otherwise>
								<input title="Send" class="inputFormSubmit" type="button" name="sendButton" id="sendButton" value='Send'>
								<div style="display: none;" class="clazz_dialog" id="dialogSend" title="Dialog">
									 <p class="text14" >Er du sikker på at du vil sende till toll.no ?</p>
								</div>
							</c:otherwise>
							</c:choose>
							
							
							<c:choose>
							<%-- st3 on the level that trigger the async-Send until the async-process is finnished --%>
							<c:when test="${model.record.emst3 == 'P' }"> <%--PENDING(P) on async triggered --%>
								<input title="Pending status ..." class="buttonGrayInsideDivPopup" style="cursor:not-allowed;" type="button" name="sendButtonAllHousesBlocked" id="sendButtonAllHousesBlocked" value='Send alle houses'>
							</c:when>
							<c:otherwise>
								<input title="Send alle underliggende houses..." class="buttonGrayWithGreenFrame" type="button" name="sendButtonAllHouses" id="sendButtonAllHouses" value='Send alle houses'>
								<div style="display: none;" class="clazz_dialog" id="dialogSendAllHouses" title="Dialog">
									 <p class="text14" >Er du sikker på at du vil sende till toll.no ?</p>
								</div>
							</c:otherwise>
							</c:choose>
							
							<a id="alinkCreateNewButton" href="tvinnsaddigitollv2_edit_master.do?action=doCreate&emlnrt=${model.record.emlnrt}&emavd=${model.record.transportDto.etavd}&emsg=${model.record.transportDto.etsg}&empro=${model.record.transportDto.etpro}">
								<input class="inputFormSubmitStd" type="button" name="createNewButton" id="createNewButton" value='Lage ny'>
							</a>
							<%-- For the moment the user should be responsible for deleting all houses prior to deleting the Master. All houses must have been DELETED from the API (no MRN) --%>
							<c:if test="${empty model.record.emmid && empty model.record.listHouses}">
								<input title="Fjerne fra SYSPED" class="inputFormSubmitStd" type="button" name="deleteMasterButton" id="deleteMasterButton" value='Fjerne'>
								<div id="dialogDeleteMaster" class="clazz_dialog" title="Dialog">
									 <p class="text14" >Er du sikker på at du vil fjerne Master - Lnr <b>${model.record.emlnrm}</b> i <b>SYSPED</b> ?</p>
								</div>
							</c:if>
							&nbsp;
							<span align="left" class="inputText">
			                	<input style="cursor:pointer;vertical-align:middle;" type="checkbox" id="async" name="async" value="1" checked>
			                	<span style="cursor:help;vertical-align:middle;font-size: 12px;" title="Vis du ønsker sende til en kø..." >Send til kø</span>
			                </span>
							
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




<c:if test="${not empty model.record.listHouses}">

	<%-- list component --%>
	<tr>
		<td >		
		<table style="width:100%" border="0" >
	    	<%-- separator --%>
	        <tr height="2"><td></td></tr>  
			<tr>
				<td>
				<table style="width:100%" id="containerdatatableTable" cellspacing="2" align="left" >
				<tr>
				<td class="text11">
							
				<table id="mainList" class="compact" >
					<thead>
					<tr class="tableHeaderField12" height="20" >
                    	<th width="2%" class="tableHeaderFieldFirst12" ><img title="Update" style="vertical-align:middle;" src="resources/images/update.gif" border="0" alt="edit"></th>
                    	<th width="2%" class="tableHeaderField12" >Lnr</th>
                    	<th width="2%" class="tableHeaderField12" >Avd</th>
                		<th width="2%" class="tableHeaderField12" >Turnr</th>
                		<th width="2%" class="tableHeaderField12" >Opd</th>
                    	<th title="S=SLETTET" width="2%" class="tableHeaderField12" ><spring:message code="systema.tvinn.sad.digitoll.list.column.sysped.status"/></th>
                		<th width="2%" class="tableHeaderField12" >Br.vekt</th>
                		<th width="2%" class="tableHeaderField12" >Ant. kolli</th>
                		<th width="2%" class="tableHeaderField12" >Doknr.</th>
                		<th width="2%" class="tableHeaderField12" >Dokt.</th>
                		<th width="2%" class="tableHeaderField12" >Tidl. doks</th>
                		<th width="2%" class="tableHeaderField12" >Prosed.</th>
                		<th width="2%" class="tableHeaderField12" >Out Prosed.</th>
                		<th width="2%" class="tableHeaderField12" >Type</th>
                		<th width="2%" class="tableHeaderField12" >Eksp.id</th>
                		<th width="2%" class="tableHeaderField12" >Mott.</th>
                		<th width="2%" class="tableHeaderField12" >Avs.</th>
                		<th width="2%" class="tableHeaderField12" >Sen. tid</th>
                		<th width="2%" class="tableHeaderField12" ><spring:message code="systema.tvinn.sad.digitoll.list.column.api"/></th>
                		<th width="2%" class="tableHeaderField12" ><spring:message code="systema.tvinn.sad.digitoll.list.column.api.mrn"/></th>
                		<th width="2%" class="tableHeaderField12" ><spring:message code="systema.tvinn.sad.digitoll.list.column.api.request"/></th>
                		<th title="Api-status" width="2%" class="tableHeaderField12" ></th>
                		<th title="S=SUBMITTED,R=REOPENED/DRAFT,D=SLETTET,C=COMPLETED,M=ERROR" width="2%" class="tableHeaderField12" ><spring:message code="systema.tvinn.sad.digitoll.list.column.api.status"/></th>
                		<th width="2%" class="tableHeaderField12" title="Fjerner manifest fra Tollvesenet" >Slett</th>
                		<th width="2%" class="tableHeaderField12" title="Fjerner manifest lokalt (SYSPED)">Fjerne-sysped</th>
                		</tr>
                	</thead>
                	<tbody> 
                	<c:forEach items="${model.record.listHouses}" var="houseConsignmentRecord" varStatus="counter">    
		              <c:choose> 
		              	  <%-- if the manifest is DELETED from tollv. show it as red --%>	   
			              <c:when test="${houseConsignmentRecord.ehst2 == 'D'}">
			              	<tr class="tableRow" style="background-color: #FEEFB3;color:#9F6000;" height="20" >
			          	  </c:when>
			          	  <c:otherwise>
			          	  	<tr class="tableRow" height="20" >
			          	  </c:otherwise>
		          	  </c:choose>	
		          
		          	   <td width="2%" class="tableCellFirst12" <c:if test="${houseConsignmentRecord.ehst2 == 'D'}">style="background-color: #FEEFB3;color: #9F6000;" </c:if> align="center">
		          	   		<a style="display: block; width: 100%; height: 100%;"  href="tvinnsaddigitollv2_edit_house.do?action=doFind&ehlnrt=${houseConsignmentRecord.ehlnrt}&ehlnrm=${houseConsignmentRecord.ehlnrm}&ehlnrh=${houseConsignmentRecord.ehlnrh}" onClick="setBlockUI();">
               					<c:choose>
		               				<c:when test="${houseConsignmentRecord.ehst2 == 'C' || houseConsignmentRecord.ehst == 'S'}">
		               					<img title="Read" style="vertical-align:bottom;" src="resources/images/eye.png" height="18px" width="18px" border="0" alt="read">
		               				</c:when>
		               				<c:otherwise>
		               					<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="edit">
		               				</c:otherwise>
	               				</c:choose>
               				</a>
               				
	               	   </td>
	               	   <td width="2%" align="center" class="tableCell12" >${houseConsignmentRecord.ehlnrh}</td>
	               	   <td width="2%" align="center" class="tableCell12" >${houseConsignmentRecord.ehavd}</td>
	               	   <td width="2%" align="center" class="tableCell12" >${houseConsignmentRecord.ehpro}</td>
	               	   <td width="2%" align="center" class="tableCell12" >${houseConsignmentRecord.ehtdn}</td>
		               <td nowrap width="2%" align="center" class="tableCell12 text12">
		               	  <c:choose>
		               		<c:when test="${houseConsignmentRecord.ehst == 'S'}">
		               			<font class="inputFormSubmit text12 isa_error">KANSELLERT</font>
		               			
	               	   			<%-- We can only CANCEL (S) internally if the emmid and emuuid are gone since we DELETED first from Tollv.(if we even got that far at some point...) --%>
	               	   			<c:if test="${empty houseConsignmentRecord.ehmid && empty houseConsignmentRecord.ehuuid}">
					   				<a tabindex=-1 class="grantLink" id="grantLink${counter.count}" runat="server" href="#">
										<font title="Gjøre tilgjengelig igjen ved å klikke" class="inputFormSubmit text12 isa_success"><b>e</b></font>
									</a> 
									<div id="dialogUpdateInternalStatusGrant${counter.count}" class="clazz_dialog" title="Dialog">
										<form action="tvinnsaddigitollv2_updateInternalStatus_house.do" name="updateInternalStatusGrantForm${counter.count}" id="updateInternalStatusGrantForm${counter.count}" method="post">
										 	<input type="hidden" name="current_id1${counter.count}" id="current_id1${counter.count}" value="${houseConsignmentRecord.ehlnrt}">
											<input type="hidden" name="current_id2${counter.count}" id="current_id2${counter.count}" value="${houseConsignmentRecord.ehlnrm}">
											<input type="hidden" name="current_id3${counter.count}" id="current_id3${counter.count}" value="${houseConsignmentRecord.ehlnrh}">
										 	<input type="hidden" name="current_status${counter.count}" id="current_status${counter.count}" value="">
										 	<p class="text14" >Er du sikker på at du vil gjøre tilgjengelig igjen Lnr <b>${houseConsignmentRecord.ehlnrh}</b> i <b>SYSPED</b> ?</p>
												
										</form>
									</div>
								</c:if>
		               		</c:when>
		               		<c:otherwise>
		               			${houseConsignmentRecord.ehst}
		               		</c:otherwise>
		               	   </c:choose>
		               </td>
		               <td width="2%" align="right" class="tableCell12" >${houseConsignmentRecord.ehvkb}</td>
		               <td width="2%" align="right" class="tableCell12" >${houseConsignmentRecord.ehntk}</td>
		               <td width="2%" align="left" class="tableCell12" >${houseConsignmentRecord.ehdkh}</td>
		               <td width="2%" align="left" class="tableCell12" >${houseConsignmentRecord.ehdkht}</td>
		               <td width="2%" align="left" class="tableCell12" >${houseConsignmentRecord.ehrg} ${houseConsignmentRecord.eh0068a} ${houseConsignmentRecord.eh0068b} ${houseConsignmentRecord.ehtrnr}</td>
		               <td width="2%" align="left" class="tableCell12" >${houseConsignmentRecord.ehprt}</td>
		               <td width="2%" align="left" class="tableCell12" >${houseConsignmentRecord.ehuprt}</td>
		               
		               <td width="2%" align="left" class="tableCell12" >${houseConsignmentRecord.ehetypt}</td>
		               <td width="2%" align="left" class="tableCell12" >${houseConsignmentRecord.eheid}</td>
		               
		               
		               <td width="2%" align="center" class="tableCell12" >${houseConsignmentRecord.ehnam}&nbsp;-&nbsp;${houseConsignmentRecord.ehpsm}&nbsp;${houseConsignmentRecord.ehlkm}</td>
		               <td width="2%" align="center" class="tableCell12" >${houseConsignmentRecord.ehnas}&nbsp;-&nbsp;${houseConsignmentRecord.ehpss}&nbsp;${houseConsignmentRecord.ehlks}</td>
		               <td width="2%" class="tableCell12" ><c:if test="${houseConsignmentRecord.ehdts > 0}">${houseConsignmentRecord.ehdts}-${houseConsignmentRecord.ehtms}</c:if></td>
		               
		               <td width="2%" class="tableCell12" >
		               		<c:choose>
		               		<c:when test="${ not empty model.record.transportDto.etktyp && fn:startsWith(model.record.transportDto.etktyp,'4') }">
								<img title="api:air" style="vertical-align:middle;" id="airplaneImg${houseConsignmentRecord.ehuuid}" src="resources/images/airplaneBlue.png" width="25" height="25"border="0" >&nbsp;
							</c:when>
							<c:otherwise>
								<img title="api:road" style="vertical-align:middle;" id="lorryImg${houseConsignmentRecord.ehuuid}" src="resources/images/lorry_green.png" width="20" height="20"border="0" >&nbsp;
							</c:otherwise>
							</c:choose>
		               </td>
		               
		               <td width="2%" class="tableCell12" ><font class="text12SkyBlue">${houseConsignmentRecord.ehmid}</font></td>
		               		
		               <td width="2%" class="tableCell12" title="les status på toll.no">
		               		<a style="display: block; width: 100%; height: 100%; cursor:pointer" class="uuidLink text12SkyBlue" id="${houseConsignmentRecord.ehuuid}">
								${houseConsignmentRecord.ehuuid}
							</a>  
		               </td>
		               <td width="2%" align="center" class="tableCell12" >
		               		<c:choose>
		               		<c:when test="${houseConsignmentRecord.ehst2 == 'S' || houseConsignmentRecord.ehst2 == 'R' || houseConsignmentRecord.ehst2 == 'D' || 
		               				houseConsignmentRecord.ehst2 == 'M' || houseConsignmentRecord.ehst2 == 'C'}">
		               				
		               			<c:if test="${houseConsignmentRecord.ehst2 == 'S'}">
		               				<img src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
		               			</c:if>
		               			<c:if test="${houseConsignmentRecord.ehst2 == 'R'}">

		               			</c:if>
		               			<c:if test="${houseConsignmentRecord.ehst2 == 'D'}">
									
		               			</c:if>
		               			<c:if test="${houseConsignmentRecord.ehst2 == 'M'}">
									<img src="resources/images/bulletRed.png" width="10" height="10" border="0" >
		               			</c:if>
		               			<c:if test="${houseConsignmentRecord.ehst2 == 'C'}">
		               				<img style="vertical-align:middle;" title="Completed tolldekl at toll.no" src="resources/images/complete-icon.png" width="14px" height="12px" border="0" alt="completion">
		               			</c:if>
		               			
		               		</c:when>
		               		<c:otherwise>
		               			<c:if test="${houseConsignmentRecord.ehst2 != 'S'}">
		               				<img src="resources/images/bulletYellow.png" width="10" height="10" border="0" >
		               			</c:if>
		               		</c:otherwise>
		               		</c:choose>
		               </td>
		               <td width="2%" align="center" class="tableCell12" >
		               		<c:choose>
		               		<c:when test="${houseConsignmentRecord.ehst2 == 'S' || houseConsignmentRecord.ehst2 == 'R' || houseConsignmentRecord.ehst2 == 'D' || 
		               				houseConsignmentRecord.ehst2 == 'M' || houseConsignmentRecord.ehst2 == 'C'}">
		               				
		               			<c:if test="${houseConsignmentRecord.ehst2 == 'S'}">
		               				<span class="text12" title="S" >SUBMITTED</span>
		               			</c:if>
		               			<c:if test="${houseConsignmentRecord.ehst2 == 'R'}">
		               				<span class="text12" title="R" >REOPENED/DRAFT</span>
		               			</c:if>
		               			<c:if test="${houseConsignmentRecord.ehst2 == 'D'}">
		               				<font class="text12" title="D" color="red">SLETTET</font>
		               			</c:if>
		               			<c:if test="${houseConsignmentRecord.ehst2 == 'M'}">
		               				<font class="text12" title="M" color="red">ERROR</font>
		               			</c:if>
		               			<c:if test="${houseConsignmentRecord.ehst2 == 'C'}">
		               				<font class="text12" title="C" color="green">COMPLETED</font>
		               			</c:if>
		               			
		               		</c:when>
		               		<c:otherwise>
		               			${houseConsignmentRecord.ehst2}
		               		</c:otherwise>
		               		</c:choose>
		               </td>

		               <td width="2%" class="tableCell12" align="center"> 
		               		  	
					   				<c:if test="${not empty houseConsignmentRecord.ehuuid  && not empty houseConsignmentRecord.ehmid}">
					   					<c:if test="${not empty houseConsignmentRecord.ehst2 && (houseConsignmentRecord.ehst2 == 'S' || houseConsignmentRecord.ehst2 == 'M') }">
				              				<a tabindex=-1 class="removeLink" id="removeLink${counter.count}" runat="server" href="#">
												<img src="resources/images/delete.gif" border="0" alt="remove">
											</a>
											<div style="display: none;" class="clazz_dialog" id="dialogUpdateStatus${counter.count}" title="Dialog">
												<form action="tvinnsaddigitollv2_api_delete_house.do" name="updateStatusForm${counter.count}" id="updateStatusForm${counter.count}" method="post">
													<input type="hidden" name="current_id1${counter.count}" id="current_id1${counter.count}" value="${houseConsignmentRecord.ehlnrt}">
													<input type="hidden" name="current_id2${counter.count}" id="current_id2${counter.count}" value="${houseConsignmentRecord.ehlnrm}">
													<input type="hidden" name="current_id3${counter.count}" id="current_id3${counter.count}" value="${houseConsignmentRecord.ehlnrh}">
													<input type="hidden" name="current_mrn${counter.count}" id="current_mrn${counter.count}" value="${houseConsignmentRecord.ehmid}">
													<input type="hidden" name="action${counter.count}" id="action${counter.count}" value="doDelete">
												 	<p class="text14" >Er du sikker på at du vil slette denne&nbsp;MRN&nbsp;<b>${houseConsignmentRecord.ehmid}</b> fra <b>Tollvesenet</b> ?</p>
													
												</form>
											</div>
										</c:if>
		              				</c:if>
		              				
              				
	               	   </td>
	               	   <td width="2%" class="tableCell12" align="center">
	               	   		<c:if test="${houseConsignmentRecord.ehst == 'M' || empty houseConsignmentRecord.ehst}">
	               	   			<%-- We can only CANCEL (S) internally if the emmid and emuuid are gone since we DELETED first from Tollv.(if we even got that far at some point...) --%>
	               	   			<c:if test="${empty houseConsignmentRecord.ehmid && empty houseConsignmentRecord.ehuuid}">
					   				<%-- REPLACED with DELETE but we can get back to this. Let it stay until we are certain 
					   				<a tabindex=-1 style="display: block; width: 100%; height: 100%;" class="cancelLink" id="cancelLink${counter.count}" runat="server" href="#">
										<img src="resources/images/remove.png" width="16" height="16" border="0" alt="remove">
									</a> 
									<div id="dialogUpdateInternalStatus${counter.count}" class="clazz_dialog" title="Dialog">
										<form action="tvinnsaddigitollv2_updateInternalStatus_house.do" name="updateInternalStatusForm${counter.count}" id="updateInternalStatusForm${counter.count}" method="post">
										 	<input type="hidden" name="current_id1${counter.count}" id="current_id1${counter.count}" value="${houseConsignmentRecord.ehlnrt}">
											<input type="hidden" name="current_id2${counter.count}" id="current_id2${counter.count}" value="${houseConsignmentRecord.ehlnrm}">
											<input type="hidden" name="current_id3${counter.count}" id="current_id3${counter.count}" value="${houseConsignmentRecord.ehlnrh}">
										 	<input type="hidden" name="current_status${counter.count}" id="current_status${counter.count}" value="S">
										 	<p class="text14" >Er du sikker på at du vil kansellere Lnr <b>${houseConsignmentRecord.ehlnrh}</b> i <b>SYSPED</b> ?</p>
												
										</form>
									</div>
									--%>
									<a tabindex=-1 style="display: block; width: 100%; height: 100%;" class="deleteHouseLink" id="deleteLink${counter.count}" runat="server" href="#">
										<img src="resources/images/remove.png" width="16" height="16" border="0" alt="remove">
									</a> 
									<div id="dialogDeleteHouse${counter.count}" class="clazz_dialog" title="Dialog">
										<form action="tvinnsaddigitollv2_delete_house.do" name="deleteHouseForm${counter.count}" id="deleteHouseForm${counter.count}" method="post">
										 	<input type="hidden" name="current_id1${counter.count}" id="current_id1${counter.count}" value="${houseConsignmentRecord.ehlnrt}">
											<input type="hidden" name="current_id2${counter.count}" id="current_id2${counter.count}" value="${houseConsignmentRecord.ehlnrm}">
											<input type="hidden" name="current_id3${counter.count}" id="current_id3${counter.count}" value="${houseConsignmentRecord.ehlnrh}">
										 	<p class="text14" >Er du sikker på at du vil fjerne House-Lnr <b>${houseConsignmentRecord.ehlnrh}</b> i <b>SYSPED</b> ?</p>
										</form>
									</div>
								</c:if>
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

</c:if>



<%-- Dialog update status 2 --%>		
<tr>
	<td>
		<div id="dialogStatus2" title="Dialog">
			
			<form action="tvinnsaddigitollv2_updateInternalStatus2_master.do" name="updateInternalStatusForm2" id="updateInternalStatusForm2" method="post">
			 	<input type="hidden" name="emlnrt" id="emlnrt" value="${model.record.emlnrt}">
			 	<input type="hidden" name="emlnrm" id="emlnrm" value="${model.record.emlnrm}">
			 	<p class="text14" >Change Internal status2 as needed.</p>
				<table>
					<tr>
						<td class="text14" align="left" >&nbsp;Status</td>
						<td class="text14MediumBlue">
							<select class="selectMediumBlueE2" name="emst2" id="emst2">
		            		  	<option title="EMPTY" value=" ">-velg-</option>
			            		<option title="ERROR(M)" value="M">ERROR</option>
	            		  		<option title="SLETTET(D)" value="D">SLETTET</option>
							  	<option title="SUBMITTED(S)" value="S">SUBMITTED</option>
							  	<option title="COMPLETED(C)" value="C">COMPLETED</option>
							  	
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</td>
</tr> 

<%-- Dialog update status 2 --%>		
<tr>
	<td>
		<div id="dialogStatus3" title="Dialog">
			
			<form action="tvinnsaddigitollv2_updateInternalStatus3_master.do" name="updateInternalStatusForm3" id="updateInternalStatusForm3" method="post">
			 	<input type="hidden" name="emlnrt" id="emlnrt" value="${model.record.emlnrt}">
			 	<input type="hidden" name="emlnrm" id="emlnrm" value="${model.record.emlnrm}">
			 	<p class="text14" >Change Internal status3 as needed.</p>
				<table>
					<tr>
						<td class="text14" align="left" >&nbsp;Status</td>
						<td class="text14MediumBlue">
							<select class="selectMediumBlueE2" name="emst3" id="emst3">
		            		  	<option title="EMPTY" value=" ">BLANK</option>
			            		<option title="X-TEST" value="X">X</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</td>
</tr> 


<%-- ------------------------- --%>
<%-- DIALOG render logsg   --%>
<%-- ------------------------- --%>
<tr>
<td>
	<div id="dialogLoggerLocal" title="Dialog" style="display:none">
		<form>
	 	<table>
	 		<tr>
				<td class="text14" align="left" >Password</td>
			</tr>
			<tr >
				<td>
					<input type="password" class="inputText" id="pwdLocal" name="pwdLocal" size="15" maxlength="15" value=''>
				</td>
			</tr>
			<tr >
				<td>
					<input type="text" class="inputText" id="logLevelLocal" name="logLevelLocal" size="8" maxlength="8" value='INFO'>
				</td>
			</tr>
			<tr height="5"><td></td></tr>
			<tr>
				<td class="text14" align="left" >Date</td>
			</tr>
			<tr >
				<td>
					<input type="text" class="inputText" id="dateLocal" name="dateLocal" size="15" maxlength="10" value=''>
				</td>
			</tr>
			<tr height="10"><td></td></tr>
			<tr>
				<td class="text14MediumBlue" align="left">
					<label id="loggerStatusLocal"></label>
				</td>
			</tr>
			
		</table>
		</form>
	</div>
</td>
</tr>

</table>

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->