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
			<%-- only those customers that have SYSPED as TMS --%>
			<c:if test="${ fn:contains(user.servletHostWithoutHttpPrefix, 'localhost') || fn:contains(user.servletHostWithoutHttpPrefix, 'gw.systema') || 
						   fn:contains(user.servletHostWithoutHttpPrefix, 'kingsrod') || fn:contains(user.servletHostWithoutHttpPrefix, 'nortrail')}">
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" style="background-color:lightgoldenrodyellow;" align="center" nowrap>
				<a id="alinkHeaderCreateFromSysped" style="display:block;">
					<font class="tabDisabledLink" style="color:taupe;font-style:italic;">&nbsp;<spring:message code="systema.tvinn.sad.createnew"/> fra Sysped </font>
					<img src="resources/images/add.png" width="12" hight="12" border="0" alt="create new from Sysped">
				</a>
			</td>
			 </c:if>
			<td width="80%" class="tabFantomSpace" align="right" nowrap><font class="tabDisabledLink">&nbsp;</font>
				<a href="digitollTransportMainListExcelView.do" target="_blank">
               		<img title="Excel export..." id="itemListExcel" style="vertical-align:top;" width="22px" height="22px"src="resources/images/excel.png" border="0" alt="excel">
	        	</a>&nbsp;
	        		
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
 		<table style="width:99%;">
 	        <form name="searchForm" id="searchForm" action="tvinnsaddigitollv2.do?action=doFind" method="post" >
			<input type="hidden" name="movementRoutingId" id="movementRoutingId" value="${model.routingId}">
			 	       
 	        <tr height="3"><td></td></tr>
 	        <tr>	
                <td class="text14" align="left" title="avd" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.manifest.list.search.label.avd"/></td>
                <td class="text14" align="left" title="sign" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.manifest.list.search.label.signatur"/></td>
                <td class="text14" align="left" title="lnr" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.manifest.list.search.label.lnr"/></td>
                <td class="text14" align="left" ><span title="turnr"><spring:message code="systema.tvinn.sad.manifest.list.search.label.turnr"/></span></td>
                <td class="text14" align="left" ><span title="etadatum"><spring:message code="systema.tvinn.sad.manifest.list.search.label.etafdatum"/></span></td>
                <td class="text14" align="left" ><span title="etadatumt"><spring:message code="systema.tvinn.sad.manifest.list.search.label.etatdatum"/></span></td>
                <td class="text14" align="left" ><span title="datum"><spring:message code="systema.tvinn.sad.manifest.list.search.label.fdatum"/></span></td>
                <td class="text14" align="left" ><span title="datumt"><spring:message code="systema.tvinn.sad.manifest.list.search.label.tdatum"/></span></td>
                <td class="text14" align="left" ><span title="transpId"><spring:message code="systema.tvinn.sad.manifest.list.search.label.transpid"/></span></td>
                <td class="text14" align="left" ><span title="status"><spring:message code="systema.tvinn.sad.manifest.list.search.label.status"/></span></td>
                <td class="text14" align="left" >
                	<img style="cursor:pointer;" onMouseOver="showPop('masterid_info');" onMouseOut="hidePop('masterid_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
	            	<span title="MasterId"><font color="green">Dok.nr - Master</font></span>
               		<div class="text11" style="position: relative;" align="left">
                	<span style="position:absolute;top:2px; width:250px;" id="masterid_info" class="popupWithInputText text11"  >
                	<p><b>Dok.nr - Master</b><br/>
                	Søker alle Master-Doc.nr som finnes per transport.
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
				<td align="left" ><input type="text" class="inputText" name="lnr" id="lnr" size="8" maxlength="7" value="${searchFilterSadDigitollTransportList.lnr}">&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="turnr" id="turnr" size="10" maxlength="8" value="${searchFilterSadDigitollTransportList.turnr}">&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="etaDatum" id="etaDatum" size="6" maxlength="6" value="${searchFilterSadDigitollTransportList.etaDatum}">&nbsp;</td> 
				<%-- TEST <td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="etaDatum" id="etaDatum" size="6" maxlength="6" value="010823">&nbsp;</td> --%>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="etaDatumt" id="etaDatumt" size="6" maxlength="6" value="${searchFilterSadDigitollTransportList.etaDatumt}">&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datum" id="datum" size="6" maxlength="6" value="${searchFilterSadDigitollTransportList.datum}">&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datumt" id="datumt" size="6" maxlength="6" value="${searchFilterSadDigitollTransportList.datumt}">&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="transpId" id="transpId" size="10" maxlength="35" value="${searchFilterSadDigitollTransportList.transpId}">&nbsp;</td>
				<td align="left" >
					<%-- 
					<select class="selectMediumBlueE2" name="status" id="status">
					  <option value="">-velg-</option>
	 				  	<option title="C" value="C"<c:if test="${searchFilterSadDigitollTransportList.status == 'C'}"> selected </c:if> >COMPLETED</option>
	 				  	<option title="N" value="N"<c:if test="${searchFilterSadDigitollTransportList.status == 'N'}"> selected </c:if> >DENIED</option> 
						<option title="M" value="M"<c:if test="${searchFilterSadDigitollTransportList.status == 'M'}"> selected </c:if> >ERROR</option> 
						<option title="K" value="K"<c:if test="${searchFilterSadDigitollTransportList.status == 'K'}"> selected </c:if> >KANSELLERT</option> 
						<option title="D" value="D"<c:if test="${searchFilterSadDigitollTransportList.status == 'D'}"> selected </c:if> >SLETTET</option> 
						<option title="S" value="S"<c:if test="${searchFilterSadDigitollTransportList.status == 'S'}"> selected </c:if> >SUBMITTED</option> 
					</select>&nbsp;
					--%>
					<c:choose>
						<c:when test="${searchFilterSadDigitollTransportList.cb_EMPTY == '1' || searchFilterSadDigitollTransportList.cb_C == 'C' || searchFilterSadDigitollTransportList.cb_N == 'N' 
										|| searchFilterSadDigitollTransportList.cb_M == 'M' || searchFilterSadDigitollTransportList.cb_D == 'D' || searchFilterSadDigitollTransportList.cb_S == 'S'
										|| searchFilterSadDigitollTransportList.cb_Z == 'Z'}">
							<span title="Flere-søkeparemetere..." class="selectMediumBlueE2" onClick="showPop('search_more_status');" style="cursor:pointer;vertical-align: middle;width:50px;background-color:#0096FF;color:white;" >...</span>
						</c:when>
						<c:otherwise>
							<span title="Flere-søkeparemetere..." class="selectMediumBlueE2" onClick="showPop('search_more_status');" style="cursor:pointer;vertical-align: middle;width:50px;">...</span>
						</c:otherwise>
					</c:choose>
						<%--<img title="Multi-søkeparemetere..." onClick="showPop('search_more_status');" style="cursor:pointer;vertical-align: middle;" src="resources/images/add.png" width="12px" height="12px" border="0" alt="search more ..." > --%>
		 					<div class="text14" style="position: relative;" align="left" >
	 						<span style="position:absolute;top:0px;width:150px;" id="search_more_status" class="popupWithInputText"  >
					           		<div class="text10" align="left" >
					           			<table border="0" cellspacing="1" cellpadding="0" width="100%">
					           			<tr>
					           			<td>
					           				<table border="0" class="lightGrayBg" width="100%">
					           				<tr>
							           			<td class="text14" colspan="2">
							           				<b>Flere ...</b>
							           			</td>
						        			</tr>
											<tr>	
												<td class="text12" align="left" ><input type="checkbox" id="cb_EMPTY" name="cb_EMPTY" value="1" <c:if test="${searchFilterSadDigitollTransportList.cb_EMPTY == '1'}"> checked </c:if> >blank</td>
											</tr>
											<tr>
												<td class="text12" align="left" ><input type="checkbox" id="cb_Z" name="cb_Z" value="Z" <c:if test="${searchFilterSadDigitollTransportList.cb_Z == 'Z'}"> checked </c:if> >AUTO-GEN.</td>
											</tr>
											<tr>
												<td class="text12" align="left" ><input type="checkbox" id="cb_C" name="cb_C" value="C" <c:if test="${searchFilterSadDigitollTransportList.cb_C == 'C'}"> checked </c:if> >COMPLETED</td>
											</tr>
											<tr>	
												<td class="text12" align="left" ><input type="checkbox" id="cb_N" name="cb_N" value="N" <c:if test="${searchFilterSadDigitollTransportList.cb_N == 'N'}"> checked </c:if> >DENIED</td>
											</tr>
											<tr>	
												<td class="text12" align="left" ><input type="checkbox" id="cb_M" name="cb_M" value="M" <c:if test="${searchFilterSadDigitollTransportList.cb_M == 'M'}"> checked </c:if> >ERROR</td>
											</tr>
											
											
											 
											<tr>
												<td class="text12" align="left" ><input type="checkbox" id="cb_D" name="cb_D" value="D" <c:if test="${searchFilterSadDigitollTransportList.cb_D == 'D'}"> checked </c:if> >SLETTET</td>
											</tr>
											<tr>
												<td class="text12" align="left" ><input type="checkbox" id="cb_S" name="cb_S" value="S" <c:if test="${searchFilterSadDigitollTransportList.cb_S == 'S'}"> checked </c:if> >SUBMITTED</td>
											</tr>
											</table>
										</td>
										</tr>
										<tr height="4"><td class="text" align="left"></td></tr>
										</table>
										<table align="left" border="0">
											<tr align="left" >
												<td class="text14"><button name="search_more_status" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('search_more_status');">&nbsp;Ok</button> 
												</td>
											</tr>
										</table>
									</div>
						</span>
						</div>
					 <%--
					 <select class="js-example-basic-single" name="status" >
						<option value="">-velg-</option>
	 				  	<option title="C" value="C"<c:if test="${searchFilterSadDigitollTransportList.status == 'C'}"> selected </c:if> >COMPLETED</option>
	 				  	<option title="N" value="N"<c:if test="${searchFilterSadDigitollTransportList.status == 'N'}"> selected </c:if> >DENIED</option> 
						<option title="M" value="M"<c:if test="${searchFilterSadDigitollTransportList.status == 'M'}"> selected </c:if> >ERROR</option> 
						<option title="K" value="K"<c:if test="${searchFilterSadDigitollTransportList.status == 'K'}"> selected </c:if> >KANSELLERT</option> 
						<option title="D" value="D"<c:if test="${searchFilterSadDigitollTransportList.status == 'D'}"> selected </c:if> >SLETTET</option> 
						<option title="S" value="S"<c:if test="${searchFilterSadDigitollTransportList.status == 'S'}"> selected </c:if> >SUBMITTED</option>
					 </select>
					  --%>
				</td>
				
				<td align="left" ><input type="text" class="inputText" name="masterId" id="masterId" size="25" maxlength="50" value="${searchFilterSadDigitollTransportList.masterId}">&nbsp;
						<img title="Flere søkeparemetere..." onClick="showPop('search_more');" style="cursor:pointer;vertical-align: middle;" src="resources/images/add.png" width="12px" height="12px" border="0" alt="search more ..." >
		 					<div class="text14" style="position: relative;" align="left" >
	 						<span style="position:absolute;top:0px" id="search_more" class="popupWithInputText"  >
					           		<div class="text10" align="left">
					           			<table border="0" cellspacing="1" cellpadding="0">
					           			<tr>
					           			<td>
					           				<table class="lightGrayBg" >
					           				<tr>
							           			<td class="text14" colspan="2">
							           				<b>Flere søkeparemetere</b>
							           			</td>
						        			</tr>
											<tr>
												<td class="text14" align="left" >
								                	<img style="cursor:pointer;" onMouseOver="showPop('extref_info');" onMouseOut="hidePop('extref_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
									            	<span title="Ekst.ref - house"><font color="green">Ekst.ref - House</font></span>
								               		<div class="text11" style="position: relative;" align="left">
								                	<span style="position:absolute;top:2px; width:250px;" id="extref_info" class="popupWithInputText text11"  >
								                	<p><b>Ekstern ref. - House</b><br/>
								                	Søker alle houses (med ekst.ref som id) som finnes per transport uavhengig av hvilke Master de tilhører.
								                	</p>
								                	<p>
								                 	Parameteren søker ikke i kombinasjon med andre søkeparameter. Den <b>søker</b> ubetinget i <b>hele databasen!</b>
								                	</p>
													</span>	
													</div>
								                </td>
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
								                <%-- TODO more here ...
								                <td class="text14" align="left" >
								                	<img style="cursor:pointer;" onMouseOver="showPop('todo_info');" onMouseOut="hidePop('todo_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
									            	<span title="todo"><font color="green">Todo</font></span>
								               		<div class="text11" style="position: relative;" align="left">
								                	<span style="position:absolute;top:2px; width:250px;" id="todo_info" class="popupWithInputText text11"  >
								                	<p><b>Todo</b><br/>
								                	Future legend.
								                	</p>
									           		<p>
								                 	Parameteren søker ikke i kombinasjon med andre søkeparameter. Den <b>søker</b> ubetinget i <b>hele databasen!</b>
								                	</p>
													</span>	
													</div>
								                </td>
								                 --%>										           			
											</tr>
											<tr>
												<td align="left" ><input type="text" class="inputText" name="extref" id="extref" size="15" maxlength="35" value="${searchFilterSadDigitollTransportList.extref}">&nbsp;</td>
												<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="opd" id="opd" size="8" maxlength="7" value="${searchFilterSadDigitollTransportList.opd}">&nbsp;</td>
												
												<%-- TODO more here ...
												<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="todo" id="todo" size="8" maxlength="7" value="${TODOsearchFilterSadDigitollTransportList.opd}">&nbsp;</td>
												 --%>
											</tr>
											</table>
										</td>
										</tr>
										<tr height="4"><td class="text" align="left"></td></tr>
										</table>
										<table align="left" border="0">
											<tr align="left" >
												<td class="text14"><button name="search_more" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('search_more');">&nbsp;Ok</button> 
												</td>
											</tr>
										</table>
									</div>
						</span>
						</div>

				</td>			
												
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
                		<th width="2%" class="tableHeaderField" >Tr.land</th>
                		<th width="2%" class="tableHeaderField" >Tr.navn</th>
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
							<%-- manual creation by end-user (transport) --%>			              
			              	<c:if test="${record.etst2 != 'Z' }">
			              		<tr class="tableRow" style="background-color: #FEEFB3;color:#9F6000;" height="20" >
			              	</c:if>
			              	<%-- auto-generated by a process from CB from TURER --%>
			              	<c:if test="${record.etst2 == 'Z' }">
			              		<tr class="tableRow" style="background-color: #ECFFDC;color:#9F6000;" height="20" >
			              	</c:if>
			              	
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
		               <td width="4%" align="left" class="tableCell" >${record.etsg}
		               
		               		<a title="Endre signatur..." tabindex=-1 class="reassignLink" id="reassignLink${counter.count}" runat="server" href="#">
								<font class="text12" style="color:green; font-weight: bold;">e</font>
							</a>
							<div style="display: none;" class="clazz_dialog_mini" id="dialogReassignSign${counter.count}" title="Dialog">
								<form action="tvinnsaddigitollv2_reassignSignatur_transport.do" name="reassignSignForm${counter.count}" id="reassignSignForm${counter.count}" method="post">
									<input type="hidden" name="current_id1${counter.count}" id="current_id1${counter.count}" value="${record.etlnrt}">
									<input type="hidden" name="action${counter.count}" id="action${counter.count}" value="doUpdate">
									<p class="text14" >Er du sikker på at du vil tildele ny signatur?</p>
									<select class="selectMediumBlueE2" style="background-color:lightyellow;" name="current_sg${counter.count}" id="current_sg${counter.count}">
									  <option value="">-velg-</option>
					 				  	<c:forEach var="recordWithin" items="${model.signList}" >
					 				  		<option title="${recordWithin.namn}" value="${recordWithin.sign}">${recordWithin.sign}</option> 
										</c:forEach>  
									</select>		
								</form>
							</div>
		               </td>
		               
		               
		               <%-- <td width="2%" align="center" class="tableCell" ><c:if test="${record.etpro > 0}">${record.etpro}</c:if></td>  --%>
		               <td width="3%" align="left" class="tableCell" >${record.etpro}
		               		
		               		<c:if test="${not empty record.listMasters[0].listHouses}">
		               			
		               			<font class="text10">&nbsp;Ant.h.&nbsp;</font><font onClick="showPop('h_info_sekv_error${counter.count}');" title="Houses..." class="inputFormSubmit11 text10 isa_warning"><b>${record.listMasters[0].listHouses.size()}</b></font>
		               			<c:if test="${record.own_invalidSekvnrOnHouse}">
		               				<img style="cursor:help;" title="Error on house...CUDE_DOCUMENTATION" src="resources/images/redFlag.png" width="18" height="18" border="0">
		               			</c:if>
		               			
		               			<span class="text11" style="position: relative;" align="left">
			                	<span style="position:absolute;top:2px; width:280px;" id="h_info_sekv_error${counter.count}" class="popupWithInputText text11"  >
			                	<button name="_ButtonCloseEtktm" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('h_info_sekv_error${counter.count}');">Close</button><br/>
				           			<font style="color:royalblue">Ant.h.&nbsp;<b>${record.listMasters[0].listHouses.size()}</b></font>
			           				<ul>
			           				<c:forEach items="${record.listMasters[0].listHouses}" var="houseRecord" varStatus="h_counter">  
			           					<li>
			           						<a class="clazz_gotoHouse" title="goto house..." href="tvinnsaddigitollv2_edit_house.do?action=doFind&ehlnrt=${houseRecord.ehlnrt}&ehlnrm=${houseRecord.ehlnrm}&ehlnrh=${houseRecord.ehlnrh}">
			           						<c:choose>
			           							<c:when test="${not empty houseRecord.incltdoc && not empty houseRecord.ehmid}">
			           								<font class="text10" style="color:red">House nr.&nbsp;<b>${houseRecord.ehlnrh}&nbsp;</b></font>
			           								<font class="text10" style="color:red">ERROR on: CUDE_DOCUMENTATION</font>
			           							</c:when>
			           							<c:otherwise>
			           								<font class="text10" style="color:royalblue">House nr.&nbsp;<b>${houseRecord.ehlnrh}</b></font>
			           								
			           							</c:otherwise>
			           						</c:choose>
			           						</a>
			           					</li>
			           				</c:forEach>
			           				</ul>
								</span>	
								</span>
	               			</c:if>
		              		
		               
		               </td>
		            
		               <td width="2%" align="center" class="tableCell text12">
		               		<c:if test="${record.own_invalidMastersExist || record.own_invalidHousesExist || record.own_unsentMastersExist || record.own_unsentHousesExist || record.etst2 == 'M'}">
		               			<c:choose>
		               			<c:when test="${record.etst2 == 'M'}">
		               				<img style="cursor:help;" title="Error on transport..." src="resources/images/redFlag.png" width="18" height="18" border="0"><font class="text10" style="color:red;">T</font>
		               			</c:when>
		               			<c:otherwise>
		               				
			               				<c:if test="${record.own_invalidMastersExist}">
		           							<img style="cursor:help;" title="Error on master..." src="resources/images/redFlag.png" width="18" height="18" border="0"><font class="text10" style="color:red;">M</font>
		           						</c:if>
		           						<c:if test="${record.own_unsentMastersExist}">
		           							<img style="cursor:help;" title="Unsent master..." src="resources/images/yellowflag2.png" width="12" height="18" border="0"><font class="text10" style="color:red;">M</font>
		           						</c:if>
		           						
		           						<c:if test="${record.own_invalidHousesExist}">
		           							<img style="cursor:help;" title="Error on house..." src="resources/images/redFlag.png" width="18" height="18" border="0"><font class="text10" style="color:red;">H</font>
		           						</c:if>
		           						<c:if test="${record.own_unsentHousesExist}">
		           							<img style="cursor:help;" title="Unsent house..." src="resources/images/yellowflag2.png" width="12" height="18" border="0"><font class="text10" style="color:red;">H</font>
		           						</c:if>
	           						
	           					</c:otherwise>
	           					</c:choose>
	           				</c:if>
	           				
				       </td>
				       <td class="tableCell text12" >
		               		<c:if test="${not empty record.listMasters}">
		               			<c:if test="${not empty record.listMasters[0].emmid}">
		               				<span class="text10">Mrn.&nbsp;<span class="text11SkyBlue">${ record.listMasters[0].emmid}</span></span>
		               			</c:if>
		               			<span class="text10">Doc.nr&nbsp;<span class="text11SkyBlue">${ record.listMasters[0].emdkm}</span></span>
		               			<c:if test="${not empty record.listMasters[0].listHouses}">
		               				<c:if test="${record.listMasters[0].listHouses.size() > 1}">
		               					<span class="text10">&nbsp;Ant.h.&nbsp;${record.listMasters[0].listHouses.size()}</span>
		               				</c:if>
		               				<c:if test="${not empty record.listMasters[0].listHouses[0].ehtrnr}">
		               					<span class="text10">&nbsp;Transit.h.&nbsp;${record.listMasters[0].listHouses[0].ehtrnr}</span>
		               				</c:if>
		               			</c:if>
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
		               <td align="center" class="tableCell" >${record.etlkt}</td>
		               <td class="tableCell" >${record.etnat}</td>
		               <td class="tableCell" >${record.etsjaf}</td>
		               
		               <td class="tableCell" ><c:if test="${record.etdtr > 0}">${record.etdtrStr}</c:if></td>
		               <td width="2%" class="tableCell" >
		               		<c:choose>
		               		<c:when test="${ not empty record.etktyp && (fn:startsWith(record.etktyp,'4') || fn:startsWith(record.etktyp,'2') ) }">
		               			<c:if test="${fn:startsWith(record.etktyp,'4')}">
									<img style="cursor:help;vertical-align:middle;" title="api:air" id="airplaneImg${record.etuuid}" src="resources/images/airplaneBlue.png" width="25" height="25"border="0" >&nbsp;
								</c:if>
								<c:if test="${fn:startsWith(record.etktyp,'2')}">
									<img style="cursor:help;vertical-align:middle;" title="api:rail" id="railImg${record.etuuid}" src="resources/images/rail.png" width="25" height="25"border="0" >&nbsp;
								</c:if>
							</c:when>
							<c:otherwise>
								<c:if test="${record.etst2 == 'C'}">
									<a tabindex=-1 class="routingLink" id="${record.etmid}_${record.etktyp}">						
										<img style="cursor:pointer;vertical-align:middle;" title="api:road" id="lorryImg${record.etuuid}" src="resources/images/delivery-truck.png" width="25" height="25"border="0" >&nbsp;
									</a>
								</c:if>
								<c:if test="${record.etst2 != 'C'}">
									<c:choose>
			               				<c:when test="${record.etst2 == 'N'}">
											<a tabindex=-1 class="routingLink" id="${record.etmid}_${record.etktyp}">
												<img style="cursor:pointer;vertical-align:middle;" title="api:road" id="lorryImg${record.etuuid}" src="resources/images/lorry_green.png" width="20" height="20"border="0" >&nbsp;
											</a>
										</c:when>
									<c:otherwise>							
										<img style="cursor:help;vertical-align:middle;" title="api:road" id="lorryImg${record.etuuid}" src="resources/images/lorry_green.png" width="20" height="20"border="0" >&nbsp;
									</c:otherwise>
									</c:choose>
								</c:if>
								
							</c:otherwise>
							</c:choose>
		               </td>
		               <td class="tableCell" title="Doc.refs på toll.no (road) ...">
		               		<c:choose>
		               		<%-- implemented only on road ... todo air and rail --%>
		               		<c:when test="${ not empty record.etmid && fn:startsWith(record.etktyp,'3') }">
			               		<a style="display: block; width: 100%; height: 100%;" class="descendantsLink text12SkyBlue" id="${record.etmid}">
		               				${record.etmid}
		               			</a>	
		               		</c:when>
		               		<c:otherwise>
		               			<span class="text12">${record.etmid}</span>
		               		</c:otherwise>
		               		</c:choose>
		               </td>
		               <td class="tableCell" title="les status på toll.no">
		               		<a style="display: block; width: 100%; height: 100%; cursor:pointer" class="uuidLink text12SkyBlue" id="${record.etuuid}">
								${record.etuuid}
							</a>  
		               </td>
		               
		               <td align="center" class="tableCell" >
		               		<c:choose>
		               		<c:when test="${record.etst2 == 'S' || record.etst2 == 'N' || record.etst2 == 'D' || record.etst2 == 'M' || record.etst2 == 'C'}">
		               			<c:if test="${record.etst2 == 'S'}">
		               				<img style="cursor:help;" title="Submitted" src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
		               			</c:if>
		               			<c:if test="${record.etst2 == 'D'}">
									
		               			</c:if>
		               			<c:if test="${record.etst2 == 'M'}">
									<img style="cursor:help;" title="Error" src="resources/images/bulletRed.png" width="10" height="10" border="0" >
		               			</c:if>
		               			<c:if test="${record.etst2 == 'C'}">
		               				<a tabindex=-1 class="entryLink" id="${record.etmid}_${record.etktyp}">
										<img style="vertical-align:middle;" title="Completed digitoll-pass at toll.no" src="resources/images/complete-icon.png" width="14px" height="12px" border="0" alt="completion">
									</a>
		               			</c:if>
		               			<c:if test="${record.etst2 == 'N'}">
		               				<a tabindex=-1 class="entryLink" id="${record.etmid}_${record.etktyp}">
										<img style="vertical-align:middle;" title="Denied digitoll-pass at toll.no" src="resources/images/warning.png" width="14px" height="14px" border="0" alt="denied">
									</a>
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
		               		<c:when test="${record.etst2 == 'S' ||  record.etst2 == 'D' || 
		               				record.etst2 == 'M' || record.etst2 == 'C' || record.etst2 == 'N' || record.etst2 == 'Z'}">
		               				
		               			<c:if test="${record.etst2 == 'S'}">
		               				<span class="text12" style="cursor:help;" title="S" >SUBMITTED</span>
		               			</c:if>
		               			<c:if test="${record.etst2 == 'D'}">
		               				<font class="text12" style="cursor:help;" title="D" color="red">SLETTET</font>
		               			</c:if>
		               			<c:if test="${record.etst2 == 'M'}">
		               				<font class="text12" style="cursor:help;" title="M" color="red">ERROR</font>
		               			</c:if>
		               			<c:if test="${record.etst2 == 'C'}">
		               				<c:choose>
		               					<c:when test="${record.etentval == '0'}">
		               						<font class="text12" style="cursor:help;" title="UTC-tid:${record.etenttim} Valid:false Tollst:${record.etentoff}" >COMPLETED</font>
		               					</c:when>
		               					<c:otherwise>
		               						<font class="text12" style="cursor:help;" title="UTC-tid:${record.etenttim} Valid:true Tollst:${record.etentoff}" >COMPLETED</font>
		               					</c:otherwise>
		               				</c:choose>
		               			</c:if>
		               			<c:if test="${record.etst2 == 'N'}">
		               				<c:choose>
		               					<c:when test="${record.etentval == '0'}">
		               						<font class="text12" style="cursor:help;" title="UTC-tid:${record.etenttim} Valid:false Tollst:${record.etentoff}" >DENIED</font>
		               					</c:when>
		               					<c:otherwise>
		               						<font class="text12" style="cursor:help;" title="UTC-tid:${record.etenttim} Valid:true Tollst:${record.etentoff}" >DENIED</font>
		               					</c:otherwise>
		               				</c:choose>
		               				
		               			</c:if>
		               			<c:if test="${record.etst2 == 'Z'}">
		               				<span class="text12" style="cursor:help;" title="Z" >
		               					<a tabindex=-1 style="display: block; width: 100%; height: 100%;" class="consolidateLink" id="etlnrt${record.etlnrt}_etpro${record.etpro}" runat="server" href="#">
		               						AUTO-GEN
		               					</a>
		               				</span>
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
													<input type="" name="current_id1${counter.count}" id="current_id1${counter.count}" value="${record.etlnrt}">
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

