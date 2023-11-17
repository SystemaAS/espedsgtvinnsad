<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->
<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_logger.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
<table style="width:90%;"  class="text11" cellspacing="0" border="0" cellpadding="0">
<tr>
	<td>
	<%-- tab container component --%>
	<table style="width:100%;" class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;Transportliste</font>
				<img src="resources/images/list.gif" border="0" alt="general list">
				&nbsp;&nbsp;${listSize}
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" style="background-color:lightyellow;" align="center" nowrap>
				<a id="alinkHeader" style="display:block;" href="tvinnsaddigitollv2_edit_transport.do?action=doCreate">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.createnew"/></font>
					<img src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
				</a>
			</td>
			 
			<td width="80%" class="tabFantomSpace" align="right" nowrap><font class="tabDisabledLink">&nbsp;</font>
				<a tabindex=-1 href="renderLocalPdf.do?fn=Digitoll-MOv2.pdf" target="_blank">
 					<img title="Documentation..." style="vertical-align:top;" width="22px" height="22px" src="resources/images/pdf2.png" border="0" alt="pdf">
 				</a>
				&nbsp;&nbsp;
				<img id="imgInfoRpgJarStart" style="cursor:pointer;" onClick="showPop('jarStartCmd');" src="resources/images/log-iconLOG.png" width="22" height="22" border="0" alt="info">
				<div class="text12" style="position: relative;display: inline;" align="left">
				<span style="position:absolute; left:-100px; top:15px;" id="jarStartCmd" class="popupWithInputText"  >
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
	<%-- search filter component --%>
		
 		<table style="width:100%;" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<tr>
 		<td>
 		<table style="width:95%;">
 	        <form name="searchForm" id="searchForm" action="tvinnsaddigitollv2.do?action=doFind" method="post" >
 	        <tr height="3"><td></td></tr>
 	        <tr>	
                <td class="text14" align="left" title="avd" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.manifest.list.search.label.avd"/></td>
                <td class="text14" align="left" title="sign" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.manifest.list.search.label.signatur"/></td>
                
                <td class="text14" align="left" ><span title="turnr"><spring:message code="systema.tvinn.sad.manifest.list.search.label.turnr"/></span></td>
                <td class="text14" align="left" ><span title="etafdatum"><spring:message code="systema.tvinn.sad.manifest.list.search.label.etafdatum"/></span></td>
                <td class="text14" align="left" ><span title="etatdatum"><spring:message code="systema.tvinn.sad.manifest.list.search.label.etatdatum"/></span></td>
                <td class="text14" align="left" ><span title="datum"><spring:message code="systema.tvinn.sad.manifest.list.search.label.fdatum"/></span></td>
                <td class="text14" align="left" ><span title="datumt"><spring:message code="systema.tvinn.sad.manifest.list.search.label.tdatum"/></span></td>
                <td class="text14" align="left" ><span title="status">Status</span></td>
                <td class="text14" align="left" >
                	<img style="cursor:pointer;" onMouseOver="showPop('opd_info');" onMouseOut="hidePop('opd_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
	            	<span title="opd - house"><font color="green">Opd - House</font></span>
               		<div class="text11" style="position: relative;" align="left">
                	<span style="position:absolute;top:2px; width:250px;" id="opd_info" class="popupWithInputText text11"  >
                	<p><b>Opd - House</b><br/>
                	Søker alle houses (med opd.nr som id) som finnes per transport uavhengig av hvilke Master de tilhører.
                	</p>
                	<p>
                 	Søket kan gjøres med en <b>del av strengen: minst 4 sifre</b> eller hele strengen
                	</p>
	           		<p>
                 	Parameteren søker ikke i kombinasjon med andre søkeparameter. Den <b>søker</b> ubetinget i <b>hele databasen!</b>
                	</p>
					</span>	
					</div>
                
                </td>
                
                
			</tr>
 	        <tr>
				<td align="left" >&nbsp;
           			<select class="selectMediumBlueE2" name="avd" id="avd">
	            		<option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                        	 	<option title="${record.namn}" value="${record.avd}"<c:if test="${searchFilterSadDigitollTransportList.avd == record.avd}"> selected </c:if> >${record.avd}</option>                       	 	
						</c:forEach> 
					</select>
					 
					<%--
					<input size="8" maxlength="4" class="selectMediumBlueE2" list="avd_list" id="avd" name="avd" value="${searchFilterSadDigitollTransportList.avd}">
					<datalist id="avd_list">
					  <option value="">-Välj-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
	 				  		<option value="${record.avd}"<c:if test="${searchFilterSadDigitollTransportList.avd == record.avd}"> selected </c:if> >${record.avd}</option> 
						</c:forEach>  
					</datalist>
					--%>
					
				</td>
				<td align="left" >
					<select class="selectMediumBlueE2" name="sign" id="sign">
					  <option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
	 				  		<option title="${record.namn}" value="${record.sign}"<c:if test="${searchFilterSadDigitollTransportList.sign == record.sign}"> selected </c:if> >${record.sign}</option> 
						</c:forEach>  
					</select>
				</td>
				<td align="left" ><input type="text" class="inputText" name="turnr" id="turnr" size="10" maxlength="8" value="${searchFilterSadDigitollTransportList.turnr}">&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="etaDatum" id="etaDatum" size="6" maxlength="6" value="${searchFilterSadDigitollTransportList.etaDatum}">&nbsp;</td> 
				<%-- TEST <td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="etaDatum" id="etaDatum" size="6" maxlength="6" value="010823">&nbsp;</td> --%>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="etaDatumt" id="etaDatumt" size="6" maxlength="6" value="${searchFilterSadDigitollTransportList.etaDatumt}">&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datum" id="datum" size="6" maxlength="6" value="${searchFilterSadDigitollTransportList.datum}">&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datumt" id="datumt" size="6" maxlength="6" value="${searchFilterSadDigitollTransportList.datumt}">&nbsp;</td>
				<td align="left" >
					<select class="selectMediumBlueE2" name="status" id="status">
					  <option value="">-velg-</option>
	 				  	<option title="C" value="C"<c:if test="${searchFilterSadDigitollTransportList.status == 'C'}"> selected </c:if> >COMPLETED</option>
	 				  	<option title="N" value="N"<c:if test="${searchFilterSadDigitollTransportList.status == 'N'}"> selected </c:if> >DENIED</option> 
						<option title="M" value="M"<c:if test="${searchFilterSadDigitollTransportList.status == 'M'}"> selected </c:if> >ERROR</option> 
						<option title="K" value="K"<c:if test="${searchFilterSadDigitollTransportList.status == 'K'}"> selected </c:if> >KANSELLERT</option> 
						<option title="D" value="D"<c:if test="${searchFilterSadDigitollTransportList.status == 'D'}"> selected </c:if> >SLETTET</option> 
						<option title="S" value="S"<c:if test="${searchFilterSadDigitollTransportList.status == 'S'}"> selected </c:if> >SUBMITTED</option> 
					</select>
				</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="opd" id="opd" size="8" maxlength="7" value="${searchFilterSadDigitollTransportList.opd}">&nbsp;</td>
				
				<td valign="top" align="left" >
                   <input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.tvinn.sad.search"/>'>
                </td>
                
                <td align="left" class="inputText">
                	<input type="checkbox" id="showErrorLayers" name="showErrorLayers" value="1">
                	<span style="cursor:help;font-size: 12px;" title="Vis info om error-flagg (M/H nivå) eller master info-Dette fungerer kun med maks 35 linjer i bildet..." >Dypt søk</span>
                </td>

			</tr>
			<tr height="10"><td></td></tr>
			</form>
			</table>
			</td>
			</tr>
			
		</table>
	</td>
	</tr>
	<tr height="3"><td></td></tr>
	<%-- Validation errors --%>
	<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
	<tr>
		<td>
           	<table style="width:100%;" align="left" border="0" cellspacing="0" cellpadding="0">
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
                		<th width="2%" class="tableHeaderField" >Sign</th>
                		<th width="2%" class="tableHeaderField" >Turnr</th>
                		<th title="Viser om det er feil på transp-master-house-nivå" width="2%" class="tableHeaderField10" >T/M/H Flagg</th>
                		<th width="2%" class="tableHeaderField10" >Master info</th>
                		<th title="S=SLETTET" width="2%" class="tableHeaderField" ><spring:message code="systema.tvinn.sad.digitoll.list.column.sysped.status"/></th>
                		<th width="2%" class="tableHeaderField" >Pass. ETA</th>
                		<th width="2%" class="tableHeaderField" >Tollst.</th>
                		<th width="2%" class="tableHeaderField" >Bilnr/Fly</th>
                		<th width="2%" class="tableHeaderField" >Transp.</th>
                		<th width="2%" class="tableHeaderField" >Sjåførs navn</th>
                		<th width="2%" class="tableHeaderField" >Reg.dato</th>
                		<th width="2%" class="tableHeaderField" ><spring:message code="systema.tvinn.sad.digitoll.list.column.api"/></th>
                		<th width="2%" class="tableHeaderField" ><spring:message code="systema.tvinn.sad.digitoll.list.column.api.mrn"/></th>
                		<th width="2%" class="tableHeaderField" ><spring:message code="systema.tvinn.sad.digitoll.list.column.api.request"/></th>
                		<th title="Api-status" width="2%" class="tableHeaderField" ></th>
                		<th title="S=SUBMITTED,R=REOPENED/DRAFT,D=SLETTET,C=COMPLETED, N=DENIED,M=ERROR" width="2%" class="tableHeaderField" ><spring:message code="systema.tvinn.sad.digitoll.list.column.api.status"/></th>
                		<th width="2%" class="tableHeaderField" title="Fjerner manifest fra Tollvesenet" >Slett</th>
                		<%--<th width="2%" class="tableHeaderField" title="Fjerner manifest lokalt (SYSPED)">Kans.</th> --%>
                		</tr>
                	</thead>
                	<tbody> 
                	<c:forEach items="${list}" var="record" varStatus="counter">    
		              <c:choose> 
		              	  <%-- if the manifest is DELETED from tollv. show it as red 	   
			              <c:when test="${record.etst2 == 'D'}"> --%>
			              <c:when test="${empty record.etmid && empty record.etuuid && record.etst2 != 'D' }"> 
			              	<tr class="tableRow" style="background-color: #FEEFB3;color:#9F6000;" height="20" >
			          	  </c:when>
			          	  <c:otherwise>
			          	  	<tr class="tableRow" height="20" >
			          	  </c:otherwise>
		          	  </c:choose>	
		          
		          		<td width="2%" class="tableCellFirst" align="center">	
		          	   	<%-- <td width="2%" class="tableCellFirst" <c:if test="${record.etst2 == 'D'}">style="background-color: #FEEFB3;color: #9F6000;" </c:if> align="center">  --%>
		          	   		<a style="display: block; width: 100%; height: 100%;"  href="tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=${record.etlnrt}" onClick="setBlockUI();">
               					<c:choose>
		               				<c:when test="${record.etst2 == 'C' || record.etst2 == 'N' || record.etst == 'S'}">
		               					<img title="Read" style="vertical-align:bottom;" src="resources/images/eye.png" height="18px" width="18px" border="0" alt="read">
		               				</c:when>
		               				<c:otherwise>
		               					<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="edit">
		               				</c:otherwise>
	               				</c:choose>
               				</a>
               				
	               	   </td>
	               	   <td width="2%" align="center" class="tableCell" >${record.etlnrt}</td>
		               <td width="2%" align="center" class="tableCell" >${record.etavd}</td>
		               <td width="2%" align="center" class="tableCell" >${record.etsg}</td>
		               <td width="2%" align="center" class="tableCell" ><c:if test="${record.etpro > 0}">${record.etpro}</c:if></td>
		            
		               <td width="2%" align="center" class="tableCell text12">
		               		<c:if test="${record.own_invalidMastersExist || record.own_invalidHousesExist || record.etst2 == 'M'}">
		               			<c:choose>
		               			<c:when test="${record.etst2 == 'M'}">
		               				<img style="cursor:help;" title="Error on transport..." src="resources/images/redFlag.png" width="18" height="18" border="0"><font class="text10" style="color:red;">T</font>
		               			</c:when>
		               			<c:otherwise>
		               				
			               				<c:if test="${record.own_invalidMastersExist}">
		           							<img style="cursor:help;" title="Error on master..." src="resources/images/redFlag.png" width="18" height="18" border="0"><font class="text10" style="color:red;">M</font>
		           						</c:if>
		           						<c:if test="${record.own_invalidHousesExist}">
		           							<img style="cursor:help;" title="Error on house..." src="resources/images/redFlag.png" width="18" height="18" border="0"><font class="text10" style="color:red;">H</font>
		           						</c:if>
	           						
	           					</c:otherwise>
	           					</c:choose>
	           				</c:if>
				       </td>
				       <td class="tableCell text12" >
		               		<c:if test="${not empty record.listMasters}">
		               			<span class="text12SkyBlue">${ record.listMasters[0].emmid}</span>
		               		</c:if>
		               
		               </td>
		               <td nowrap width="2%" align="center" class="tableCell" >
		               	  <c:choose>
		               		<c:when test="${record.etst == 'S'}">
		               			<font class="inputFormSubmit text12 isa_error">KANSELLERT</font>
		               			
	               	   			<%-- We can only CANCEL (S) internally if the emmid and emuuid are gone since we DELETED first from Tollv.(if we even got that far at some point...) --%>
	               	   			<c:if test="${empty record.etmid && empty record.etuuid}">
					   				<a tabindex=-1 class="grantLink" id="grantLink${counter.count}" runat="server" href="#">
										<font title="Gjøre tilgjengelig igjen ved å klikke" class="inputFormSubmit text12 isa_success"><b>e</b></font>
									</a> 
									<div id="dialogUpdateInternalStatusGrant${counter.count}" class="clazz_dialog" title="Dialog">
										<form action="tvinnsaddigitollv2_updateInternalStatus_transport.do" name="updateInternalStatusGrantForm${counter.count}" id="updateInternalStatusGrantForm${counter.count}" method="post">
										 	<input type="hidden" name="current_id1${counter.count}" id="current_id1${counter.count}" value="${record.etlnrt}">
											<input type="hidden" name="current_status${counter.count}" id="current_status${counter.count}" value="">
										 	<p class="text14" >Er du sikker på at du vil gjøre tilgjengelig igjen Lnr <b>${record.etlnrt}</b> i <b>SYSPED</b> ?</p>
												
										</form>
									</div>
								</c:if>
		               		</c:when>
		               		<c:otherwise>
		               			${record.etst}
		               		</c:otherwise>
		               	   </c:choose>
		              	</td>
		               <td class="tableCell" >${record.etetadStr}&nbsp;${record.etetatStr}</td>
		               <td align="center" class="tableCell" >${record.ettsd}</td>
		               <td class="tableCell" >${record.etkmrk}</td>
		               <td class="tableCell" >${record.etnat}</td>
		               <td class="tableCell" >${record.etsjaf}</td>
		               
		               <td class="tableCell" ><c:if test="${record.etdtr > 0}">${record.etdtrStr}</c:if></td>
		               <td width="2%" class="tableCell" >
		               		<c:choose>
		               		<c:when test="${ not empty record.etktyp && fn:startsWith(record.etktyp,'4') }">
								<img style="cursor:help;" title="api:air" style="vertical-align:middle;" id="airplaneImg${record.etuuid}" src="resources/images/airplaneBlue.png" width="25" height="25"border="0" >&nbsp;
							</c:when>
							<c:otherwise>
								<img style="cursor:help;" title="api:road" style="vertical-align:middle;" id="lorryImg${record.etuuid}" src="resources/images/lorry_green.png" width="20" height="20"border="0" >&nbsp;
							</c:otherwise>
							</c:choose>
		               </td>
		               <td class="tableCell" ><span class="text12SkyBlue">${record.etmid}</span></td>
		               <td class="tableCell" title="les status på toll.no">
		               		<a style="display: block; width: 100%; height: 100%; cursor:pointer" class="uuidLink text12SkyBlue" id="${record.etuuid}">
								${record.etuuid}
							</a>  
		               </td>
		               
		               <td align="center" class="tableCell" >
		               		<c:choose>
		               		<c:when test="${record.etst2 == 'S' || record.etst2 == 'R' || record.etst2 == 'D' || record.etst2 == 'M' || record.etst2 == 'C'}">
		               			<c:if test="${record.etst2 == 'S'}">
		               				<img style="cursor:help;" title="Submitted" src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
		               			</c:if>
		               			<c:if test="${record.etst2 == 'R'}">

		               			</c:if>
		               			<c:if test="${record.etst2 == 'D'}">
									
		               			</c:if>
		               			<c:if test="${record.etst2 == 'M'}">
									<img style="cursor:help;" title="Error" src="resources/images/bulletRed.png" width="10" height="10" border="0" >
		               			</c:if>
		               			<c:if test="${record.etst2 == 'C'}">
		               				<img title="Completed" style="vertical-align:middle;cursor:help;" title="Completed digitoll-pass at toll.no" src="resources/images/complete-icon.png" width="14px" height="12px" border="0" alt="completion">
		               			</c:if>
		               			<c:if test="${record.etst2 == 'N'}">
		               				<img title="Denied" style="vertical-align:middle;" title="Denied digitoll-pass at toll.no" src="resources/images/warning.png" width="14px" height="12px" border="0" alt="denied">
		               			</c:if>
		               			
		               		</c:when>
		               		<c:otherwise>
		               			<c:if test="${record.etst2 != 'S'}">
		               				<img style="cursor:help;" title="To be send?" src="resources/images/bulletYellow.png" width="10" height="10" border="0" >
		               			</c:if>
		               		</c:otherwise>
		               		</c:choose>
		               </td>
		               
		               <td width="2%" align="center" class="tableCell" >
		               		<c:choose>
		               		<c:when test="${record.etst2 == 'S' || record.etst2 == 'R' || record.etst2 == 'D' || 
		               				record.etst2 == 'M' || record.etst2 == 'C' || record.etst2 == 'N'}">
		               				
		               			<c:if test="${record.etst2 == 'S'}">
		               				<span class="text12" style="cursor:help;" title="S" >SUBMITTED</span>
		               			</c:if>
		               			<c:if test="${record.etst2 == 'R'}">
		               				<span class="text12" style="cursor:help;" title="R" >REOPENED/DRAFT</span>
		               			</c:if>
		               			<c:if test="${record.etst2 == 'D'}">
		               				<font class="text12" style="cursor:help;" title="D" color="red">SLETTET</font>
		               			</c:if>
		               			<c:if test="${record.etst2 == 'M'}">
		               				<font class="text12" style="cursor:help;" title="M" color="red">ERROR</font>
		               			</c:if>
		               			<c:if test="${record.etst2 == 'C'}">
		               				<font class="text12" style="cursor:help;" title="UTC-tid:${record.etenttim} Valid:${record.etentval} Tollst:${record.etentoff}" color="green">COMPLETED</font>
		               			</c:if>
		               			<c:if test="${record.etst2 == 'N'}">
		               				<font class="text12" style="cursor:help;" title="UTC-tid:${record.etenttim} Valid:${record.etentval} Tollst:${record.etentoff}" color="green">DENIED</font>
		               			</c:if>
		               			
		               		</c:when>
		               		<c:otherwise>
		               			${record.etst2}
		               		</c:otherwise>
		               		</c:choose>
		               </td>
		               
		               

		               <td width="2%" class="tableCell" align="center"> 
		               		  		
				   				<c:if test="${not empty record.etuuid  && not empty record.etmid}">
					   					<c:if test="${not empty record.etst2 && (record.etst2 == 'S' || record.etst2 == 'M') }">
				   							<a tabindex=-1 class="removeLink" id="removeLink${counter.count}" runat="server" href="#">
												<img src="resources/images/delete.gif" border="0" alt="remove">
											</a>
											<div style="display: none;" class="clazz_dialog" id="dialogUpdateStatus${counter.count}" title="Dialog">
												<form action="tvinnsaddigitollv2_api_delete_transport.do" name="updateStatusForm${counter.count}" id="updateStatusForm${counter.count}" method="post">
													<input type="hidden" name="current_id1${counter.count}" id="current_id1${counter.count}" value="${record.etlnrt}">
													<input type="hidden" name="current_mrn${counter.count}" id="current_mrn${counter.count}" value="${record.etmid}">
													<input type="hidden" name="action${counter.count}" id="action${counter.count}" value="doDelete">
												 	<p class="text14" >Er du sikker på at du vil slette denne&nbsp;MRN&nbsp;<b>${record.etmid}</b> fra <b>Tollvesenet</b> ?</p>
													
												</form>
											</div>
										</c:if>
		              				</c:if>
              				
	               	   </td>
	               	   <%--
	               	   <td width="2%" class="tableCell" align="center">
	               	   		<c:if test="${record.etst == 'M' || empty record.etst}">
	               	   			<%-- We can only CANCEL (S) internally if the emmid and emuuid are gone since we DELETED first from Tollv.(if we even got that far at some point...) 
	               	   			<c:if test="${empty record.etmid && empty record.etuuid}">
					   				<a tabindex=-1 style="display: block; width: 100%; height: 100%;" class="cancelLink" id="cancelLink${counter.count}" runat="server" href="#">
										<img src="resources/images/remove.png" width="16" height="16" border="0" alt="remove">
									</a> 
									<div id="dialogUpdateInternalStatus${counter.count}" class="clazz_dialog" title="Dialog">
										<form action="tvinnsaddigitollv2_updateInternalStatus_transport.do" name="updateInternalStatusForm${counter.count}" id="updateInternalStatusForm${counter.count}" method="post">
										 	<input type="hidden" name="current_id1${counter.count}" id="current_id1${counter.count}" value="${record.etlnrt}">
										 	<input type="hidden" name="current_status${counter.count}" id="current_status${counter.count}" value="S">
										 	<p class="text14" >Er du sikker på at du vil kansellere Lnr <b>${record.etlnrt}</b> i <b>SYSPED</b> ?</p>
												
										</form>
									</div>
								</c:if>
							</c:if>
						</td>
						 --%>	
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

