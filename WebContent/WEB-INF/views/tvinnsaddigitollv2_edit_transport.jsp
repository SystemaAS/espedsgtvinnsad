<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_edit_transport.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
					
						<c:choose>
						<c:when test="${model.record.etlnrt > 0}">
							<td title="${model.record.etlnrt}" width="15%" valign="bottom" class="tab" align="center" nowrap>
						</c:when>
						<c:otherwise>
							<%-- Meaning CreateNew --%>
							<td width="15%" valign="bottom" class="tab" style="background-color:lightyellow;" style="" align="center" nowrap>
						</c:otherwise>
						</c:choose>
						<font class="tabLink">
							&nbsp;Transport
						</font>
						<img src="resources/images/update.gif" border="0" alt="edit">
						<c:if test="${model.record.etlnrt > 0}">
							<font class="text14MediumBlue">&nbsp;${model.record.etlnrt}</font>
						</c:if>
					</td>
					<c:if test="${model.record.etlnrt > 0 && model.record.etst != 'S'}"> <%-- CANCELED(S) --%>
						<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
						<td width="15%" valign="bottom" class="tabDisabled" style="background-color:lightyellow;"  align="center" nowrap>
							<a id="alinkHeader" style="display:block;" href="tvinnsaddigitollv2_edit_master.do?action=doCreate&emlnrt=${model.record.etlnrt}&emavd=${model.record.etavd}&emsg=${model.record.etsg}&empro=${model.record.etpro}">
								<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.createnew"/>&nbsp;<spring:message code="systema.tvinn.sad.digitoll.list.tab.master"/></font>
								<img src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
							</a>
						</td>
					</c:if>
					
					
					<%-- <td width="50%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>  --%>
			 		<td width="80%" class="tabFantomSpace" align="right" nowrap><font class="tabDisabledLink">&nbsp;</font>
			 			<c:if test="${not empty model.record.etmid_own}">
				 			<span title="Sist godkjente MRN" class="inputTextReadOnly text11" style="vertical-align:super;">MRN:&nbsp;${model.record.etmid_own}</span>
				 			&nbsp;&nbsp;
			 			</c:if>
						<a style="vertical-align:super;" title="Kodeverk toll.no" target="_blank" href="https://toll.github.io/api/mo-kodeverk.html">
							<font title="Kodeverk toll.no" class="inputFormSubmit text10 isa_warning"><b>Kodeverk</b></font>
						</a>
					
						<img style="vertical-align:bottom;" id="imgInfoRpgJarStart" style="cursor:pointer;" onClick="showPop('jarStartCmd');" src="resources/images/log-iconLOG.png" width="22" height="22" border="0" alt="info">
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
	<form name="manifestForm" id="manifestForm" action="tvinnsaddigitollv2_edit_transport.do" method="post">
			<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
			<input type="hidden" name="etuuid" id="etuuid" value="${model.record.etuuid}"> 
			<input type="hidden" name="etmid" id="etmid" value="${model.record.etmid}">
			<input type="hidden" name="action" id="action" value="doUpdate">
			
			<c:if test="${model.record.etlnrt > 0}">
				<input type="hidden" name="etlnrt" id="etlnrt" value="${model.record.etlnrt}"> 
				<input type="hidden" name="etst" id="etst" value="${model.record.etst}">
				<input type="hidden" name="etst2" id="etst2" value="${model.record.etst2}">
				<input type="hidden" name="etsg" id="etsg" value="${model.record.etsg}">
				<input type="hidden" name="etavd" id="etavd" value="${model.record.etavd}">
				<input type="hidden" name="etpro" id="etpro" value="${model.record.etpro}">
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
			
		<%-- WARNING - OBSOLETE ?. It will be the END-USER responsibility	
		<c:if test="${empty model.record.etmid && model.record.etlnrt > 0}">	 
			<tr >
				<td colspan="10" class="text14  ">
	    			<font class="inputText isa_warning" >
	    				For å opprette en ny Master må du ha sendt Transporten (Send-knappen) og fått et MRN-Api-nummer. Dette for å kunne låse hvilken Api(road eller air) som skal brukes på Master/House 
	    			</font>
	    		</td>
	   		</tr>
	  		<tr height="2"><td colspan="10">&nbsp;</td></tr>  
  		</c:if>
  		--%>
  		
  		<tr height="5">	
  			<td  colspan="10" class="text14 formFrame" >
			<table style="width:100%">
			<tr >
				<td class="text14" align="left" >
					<c:choose>
               		<c:when test="${ not empty model.record.etktyp && fn:startsWith(model.record.etktyp,'4') }">
						<img title="api:air" style="vertical-align:middle;" id="airplaneImg" src="resources/images/airplaneBlue.png" width="25" height="25"border="0" >&nbsp;
					</c:when>
					<c:otherwise>
						<img title="api:road" style="vertical-align:middle;" id="lorryImg" src="resources/images/lorry_green.png" width="20" height="20"border="0" >&nbsp;
					</c:otherwise>
					</c:choose>
					<span title="MRN nr. hos toll.no - per transport" >MRN-Api&nbsp;</span><span class="text14SkyBlue" id="${model.record.etmid}">${model.record.etmid}</span>
					<c:if test="${not empty model.record.etmid}">
						&nbsp;
						<input title="Slett fra toll.no" class="inputFormSubmitStd" type="button" name="deleteButton" id="deleteButton" value='Slett'>
						<div style="display: none;" class="clazz_dialog" id="dialogDelete" title="Dialog">
							 <p class="text14" >Er du sikker på at du ønsker å slette fra toll.no?</p>
						</div>
					</c:if>		
		    		&nbsp;&nbsp;<font style="font-weight: bold;color: lightgray;">|</font>&nbsp;&nbsp;
		    		<span title="Transaktionsid hos toll.no - per request" >Trans.id&nbsp;</span><a title="les status på toll.no" class="uuidLinkParent text14SkyBlue" id="${model.record.etuuid}">${model.record.etuuid}</a>
		    		&nbsp;&nbsp;<font style="font-weight: bold;color: lightgray;">|</font>&nbsp;&nbsp;
		    		<a title="lese logg" tabindex=-1 id="${model.record.etlnrt}" class="logLink" runat="server" href="#"><font class="text14 ">Api.st - log</font>&nbsp;
						<c:choose>
						<c:when test="${model.record.etst2 == 'S' || model.record.etst2 == 'R' || model.record.etst2 == 'D' || model.record.etst2 == 'C'}">
							<c:if test="${model.record.etst2 == 'S'}">
								<img src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
								<font style="color:gray;">SUBMITTED</font>
							</c:if>
							<c:if test="${model.record.etst2 == 'C'}">
								<img src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
								<font style="color:gray;">COMPLETED</font>
							</c:if>
							<c:if test="${model.record.etst2 == 'D'}">
								<img src="resources/images/bulletRed.png" width="10" height="10" border="0" >
								<font style="color:red;">SLETTET</font>
							</c:if>
							<c:if test="${model.record.etst2 == 'R'}">
								<font style="color:brown;">REOPENED/DRAFT</font>
							</c:if>
						</c:when>
						<c:otherwise>
							<c:choose>
							<c:when test="${model.record.etst2 == 'M'}">
								<img src="resources/images/bulletRed.png" width="10" height="10" border="0" >
								<font style="color:red">ERROR&nbsp;</font>
							</c:when>
							<c:otherwise>
								<font style="color:gray;">${model.record.etst2}&nbsp;</font>
							</c:otherwise>
							</c:choose>
						</c:otherwise>
						</c:choose>
					</a>&nbsp;
					<c:if test="${model.record.etst2 == 'M'}">
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
		<tr height="5"><td></td></tr>
	           		 
 		<tr>
			<td class="text14" valign="top">
				<table style="width:85%" align="left" border="0" cellspacing="1" cellpadding="0">
					
				 	<tr >
					 	<td >
						<table class="formFrameHeader" style="width:100%;"  border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<c:choose>
						 			<c:when test="${model.record.etlnrt > 0}">
						 				<td class="text14White">
						 						&nbsp;&nbsp;Løp.nr.&nbsp;${model.record.etlnrt}
						 						&nbsp;&nbsp;Avd&nbsp;${model.record.etavd}
						 						&nbsp;&nbsp;Sign&nbsp;${model.record.etsg}
						 						&nbsp;&nbsp;Turnr&nbsp;${model.record.etpro}
						 						  
						 						
						 				</td>
						 				<td class="text14White" align="right">
						 						Stat<a tabindex=-1 id="updateInternalStatusLink" name="updateInternalStatusLink" runat="server" href="#"><font class="text14White">u</font></a>s:&nbsp;${model.record.etst}
						 						&nbsp;&nbsp;
						 				</td>
						 			</c:when>
						 			<c:otherwise>
						 				<td class="text14White">&nbsp;&nbsp;Transportinfo.</td>
						 			</c:otherwise>
					 			</c:choose>
			 				</tr>
			            </table>
			            </td>
		            </tr>
		            <tr >
					 	<td>
						<table class="formFrame" style="width:100%;" border="0" cellspacing="1" cellpadding="0">
			 				<c:choose>
					 			<c:when test="${model.record.etlnrt > 0}">
					 				<tr >
										<td colspan="2" class="text12" title="etdtr">&nbsp;Reg.dato&nbsp;
											<input readonly type="text12"  class="inputTextReadOnly" name="etdtr" id="etdtr" size="8" maxlength="6" value="${model.record.etdtrStr}"></td>
										</td>
										
					 				</tr>
					 				<tr height="2"><td>&nbsp;</td></tr>
			 					</c:when>
				 				<c:otherwise>
									
									<tr>
					 					<td class="text12" title="etavd">&nbsp;Avd<font class="text16RedBold" >*</font></td>
					 					<td class="text12" title="etsg">&nbsp;Sign<font class="text16RedBold" >*</font></td>
					 					<td class="text12" title="etpro">&nbsp;Tur<font class="text16RedBold" >*</font></td>
				 					</tr>				 				
				 					<tr>
					 					<td>
					 						<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  size="7" maxlength="4" class="inputTextMediumBlueMandatoryField" list="etavd_list" id="etavd" name="etavd" value="${model.record.etavd}">
											<datalist id="etavd_list">
											  <option value="">-Välj-</option>
							 				  	<c:forEach var="record" items="${model.avdList}" >
							 				  		<option value="${record.avd}"<c:if test="${model.record.etavd == record.avd}"> selected </c:if> >${record.avd}</option> 
												</c:forEach>  
											</datalist>
					 					</td>
					 					<td>
					 						<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  size="6" maxlength="3" class="inputTextMediumBlueMandatoryField" list="etsg_list" id="etsg" name="etsg" value="${model.record.etsg}">
											<datalist id="etsg_list">
											  <option value="">-Välj-</option>
							 				  	<c:forEach var="record" items="${model.signList}" >
							 				  		<option value="${record.sign}"<c:if test="${model.record.etsg == record.sign}"> selected </c:if> >${record.sign}</option> 
												</c:forEach>  
											</datalist>	
					 					</td>
					 					<td>
					 						<c:choose>
							 				<c:when test="${model.record.etpro > 0}">
							 					<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text12"  class="inputTextMediumBlueMandatoryField" name="etpro" id="etpro" size="9" maxlength="8" value="${model.record.etpro}">
							 				</c:when>	
							 				<c:otherwise>
							 					<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"   type="text12"  class="inputTextMediumBlueMandatoryField" name="etpro" id="etpro" size="9" maxlength="8" value="">
							 				</c:otherwise>
							 				</c:choose>
					 					</td>
				 					</tr>
				 					<tr height="10"></tr>
				 				</c:otherwise>
			 				</c:choose>
			 				<tr >
			 					<td class="text14">
			 						<img style="cursor:pointer;" onMouseOver="showPop('etktkd_info');" onMouseOut="hidePop('etktkd_info');" style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
				            		<span title="etktkd Mode of Transport">ModeTr.</span><font class="text16RedBold" >*</font>
			                		<div class="text11" style="position: relative;" align="left">
				                	<span style="position:absolute;top:2px; width:250px;" id="etktkd_info" class="popupWithInputText text11"  >
					           		<ul>
					           			<c:forEach var="dto" items="${model.modeOfTransportDto}" >
				           				<li><b>${dto.code}</b>&nbsp;${dto.txt1}</li>
				           				</c:forEach>
				           			</ul>
									</span>	
									</div>

			 					</td>
			 					
			 					<td class="text14">
				 					<img style="cursor:pointer;" onMouseOver="showPop('etktd_info');" onMouseOut="hidePop('etktd_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
					            	<span title="etktyp Type of Identification"><font color="green">Kjør.Typ.</font></span><font class="text16RedBold" >*</font>
			                		<div class="text11" style="position: relative;" align="left">
				                	<span style="position:absolute;top:2px; width:250px;" id="etktd_info" class="popupWithInputText text11"  >
				                	<p><b>Kjør.Typ.</b>&nbsp;Bestemmer hvilket API som brukes...(f.eks: 41 = luftfartøy = api-air)</p>
					           		<ul>
					           			<c:forEach var="dto" items="${model.typeOfIdentificationMeansTransportDto}" >
				           				<li><b>${dto.code}</b>&nbsp;${dto.txt1}</li>
				           				</c:forEach>
				           			</ul>
									</span>	
									</div>
		 						</td>
								<td class="text14">
									<img title="Click!" style="cursor:pointer;" onClick="showPop('etktm_info');" style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
					            	<span title="etktm Type of Means of Transport">Tr.midd.typ.</span><font class="text16RedBold" >*</font>
			                		<div class="text11" style="position: relative;" align="left">
				                	<span style="position:absolute;top:2px; width:250px;" id="etktm_info" class="popupWithInputText text11"  >
				                	<button name="_ButtonCloseEtktm" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('etktm_info');">Close</button> 
					           		<ul>
					           			<c:forEach var="dto" items="${model.meansOfTransportDto}" >
				           				<li><b>${dto.code}</b>&nbsp;${dto.txt1}</li>
				           				</c:forEach>
				           			</ul>
									</span>	
									</div>
								</td>
								<td class="text14">&nbsp;<span title="etklk Land code">Landkode</span><font class="text16RedBold" >*</font></td>
				 			</tr>
				 				
				 			<tr >
			 					<td class="text14">
			 						
			 						<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  class="inputTextMediumBlueMandatoryField" name="etktkd" id="etktkd" >
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="dto" items="${model.modeOfTransportDto}" >
				                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.etktkd == dto.code}"> selected </c:if> >${dto.code}</option>
										</c:forEach>
									</select>	
								</td>
								<td>
									<c:choose>
										<c:when test="${ not empty model.record.etktyp && not empty model.record.etmid }">
											<input readonly type="text" class="inputTextReadOnly" name="etktyp" id="etktyp" size="3" maxlength="2" value="${model.record.etktyp}">
										</c:when>
										<c:otherwise>
											<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  class="inputTextMediumBlueMandatoryField" name="etktyp" id="etktyp" >
						 						<option value="">-velg-</option>
							 				  	<c:forEach var="dto" items="${model.typeOfIdentificationMeansTransportDto}" >
						                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.etktyp == dto.code}"> selected </c:if> >${dto.code}</option>
												</c:forEach>
											</select>	
										</c:otherwise>
									</c:choose>
								</td>
					 			<td class="text14">
					 				<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  class="inputTextMediumBlueMandatoryField" name="etktm" id="etktm" >
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="dto" items="${model.meansOfTransportDto}" >
				                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.etktm == dto.code}"> selected </c:if> >${dto.code}</option>
										</c:forEach>
									</select>
					 			</td>
					 			<td class="text14">
					 				<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  class="inputTextMediumBlueMandatoryField" name="etklk" id="etklk" >
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="dto" items="${model.countryDto}" >
				                       	 	<option title="${dto.code}" value="${dto.code}" <c:if test="${model.record.etklk == dto.code}"> selected </c:if> >${dto.code}</option>
										</c:forEach>
									</select>		
					 				
					 			</td>
						 			
				 			</tr>
				 				
			 				<tr >
								<td colspan="2" class="text14">
									<img style="cursor:pointer;" onMouseOver="showPop('etkmrk_info');" onMouseOut="hidePop('etkmrk_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
					            	<span title="etkmrk">Kjøretøy kjennemerke</span><font class="text16RedBold" >*</font>
			                		<div class="text11" style="position: relative;" align="left">
				                	<span style="position:absolute;top:2px; width:250px;" id="etkmrk_info" class="popupWithInputText text11"  >
				                	<p><b>Kjøretøy kjennemerke</b>&nbsp;
					           			Identifikasjonummer brukt for å unikt identifiserer transporten. (maxLength: 35-chars)
					           		</p>
					           		
					           		<p>
					           			For en bil på landevei er dette registreringsnummert for bilen. I typeOfIdentification angis kode 30 → Registration Number of the Road Vehicle
					           		</p>
					           		<p>
					           			For fly er dette halenummeret. I typeOfIdentification angis kode 41 → Registration Number of the Aircraft
					           		</p>
					           		<p>
					           			For tog angis toget nummer. I typeOfIdentification angis kode 21 → Train number For sjø angis IMO skipsregistreringsnummer. I typeOfIdentification angis kode 10 → IMO Ship Identification Number
					           		</p>
					           		
									</span>	
									</div>
								</td>
								<td colspan="2" class="text14">
									<img style="cursor:pointer;" onMouseOver="showPop('etcref_info');" onMouseOut="hidePop('etcref_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
					            	<span title="etcref">Turref.nr (IATA flight, other)</span>
			                		<div class="text11" style="position: relative;" align="left">
				                	<span style="position:absolute;top:2px; width:250px;" id="etcref_info" class="popupWithInputText text11"  >
				                	<p><b>Turref.nr - IATA flight</b>&nbsp;
					           			Identifikasjon av reisen for transportmiddelet. For flytransport er dette IATA flight number. (maxLength: 17-chars)
					           		</p>
									</span>	
									</div>
								</td>
				 			</tr>
				 			<tr>
			 					<td colspan="2" class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="etkmrk" id="etkmrk" size="25" maxlength="35" value="${model.record.etkmrk}"></td>
			 					<td colspan="2" class="text14"><input type="text" class="inputTextMediumBlue" name="etcref" id="etcref" size="19" maxlength="17" value="${model.record.etcref}"></td>
								
			 				</tr>
			 				<tr>
			 					<td colspan="2" class="text14">&nbsp;<span title="etsjaf">Fører-navn</span><font class="text16RedBold" >*</font></td>
								<td colspan="2" class="text14">&nbsp;<span title="etems">Fører-epost / Telefon</span><font class="text16RedBold" >*</font></td>
								
			 				</tr>
			 				<tr >
					 			<td colspan="2" class="text14">
					 				<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"   type="text" class="inputTextMediumBlueMandatoryField" name="etsjaf" id="etsjaf" size="30" maxlength="50" value="${model.record.etsjaf}">
					 			</td>
								<td colspan="2" class="text14">
									<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"   type="text" class="inputTextMediumBlueMandatoryField" name="etems" id="etems" size="30" maxlength="50" value="${model.record.etems}">
									
								</td>
			 				</tr>
			 				<tr >
			 					<td colspan="2" class="text14">
			 					<img style="cursor:pointer;" onMouseOver="showPop('etdkm_info');" onMouseOut="hidePop('etdkm_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
				            	<span title="etdkm - Ref.liste av masters">Doknr.&nbsp;</span>
		                		<div class="text11" style="position: relative;" align="left">
			                	<span style="position:absolute;top:2px; width:250px;" id="etdkm_info" class="popupWithInputText text11"  >
			                	<p><b>Dok.nr</b>&nbsp;
			                			Liste over hovedforsendelser (Masters) som skal transporteres til grensen med denne transporten.<br/>
			                			Identifisererer at dette er fraktbrevet til en hovedforsendelse.
			                			Ref. master-lista nedenfor (Doknr/Dokt)	</p>
			                			<p><b>KANSELLERTE</b> Masters vil ikke bli inkludert</p>
				           		</span>	
								</div>

			 					</td>
			 					<td class="text14">&nbsp;<span title="etdkmt - Ref.liste av masters">Doktyp.</span></td>
			 				</tr>
			 				<tr >	
			 					<td colspan="2" class="text14" >
									<input readonly type="text" class="inputTextReadOnly" style="color:#9F6000;" name="Xetdkm" id="Xetdkm" size="30" maxlength="50" value="Ref.liste nedenfor">
								</td>
								<td class="text14" >
									<input readonly type="text" class="inputTextReadOnly" style="color:#9F6000;" name="Xetdkmt" id="Xetdkmt" size="20" maxlength="50" value="Ref.liste nedenfor">
								</td>
			 				</tr>
				 				
			 				<tr height="2"><td>&nbsp;</td></tr>
			 				<tr>
			 					<td class="text14">&nbsp;<span title="etetad - Estimated date of arrival">ETA</span><font class="text16NavyBlueBold" >*</font></td>
								<td class="text14">&nbsp;<span title="etetat-HHmm Estimated time of arrival">ETA-Tid<font class="text16NavyBlueBold" >*</font>&nbsp;<font class="text10">(HHmm)</font></span></td>
								<td class="text14">&nbsp;<span title="ettsd">Pass.tollsted</span><font class="text16RedBold" >*</font></td>
			 				</tr>
			 				<tr >
			 					
					 			<td class="text14">
					 				<c:choose>
					 				<c:when test="${model.record.etetad > 0}">
					 					<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="etetad" id="etetad" size="8" maxlength="6" value="${model.record.etetadStr}">
					 				</c:when>	
					 				<c:otherwise>
					 					<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="etetad" id="etetad" size="8" maxlength="6" value="">
					 				</c:otherwise>
					 				</c:choose>
					 			</td>
								<td>
									<c:choose>
					 				<c:when test="${model.record.etetat > 0}">
					 					<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="etetat" id="etetat" size="6" maxlength="4" value="${model.record.etetatStr}">
					 				</c:when>
					 				<c:otherwise>
					 					<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="etetat" id="etetat" size="6" maxlength="4" value="">
					 				</c:otherwise>
					 				</c:choose>
									
								</td>
			 					
								<td>
									<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="ettsd" id="ettsd" size="9" maxlength="8" value="${model.record.ettsd}">
									<a tabindex="-1" id="ettsdIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
								</td>
			 				</tr>
			 				
			 				<tr>
			 					<td class="text14">&nbsp;<span title="etshed - Scheduled date of arrival - Flight ">STA</span></td>
								<td class="text14">&nbsp;<span title="etshet-HHmm Scheduled time of arrival - Flight">STA-Tid&nbsp;<font class="text10">(HHmm)</font></span></td>
			 				</tr>
			 				<tr >
			 					
					 			<td class="text14">
					 				<c:choose>
					 				<c:when test="${model.record.etshed > 0}">
					 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="etshed" id="etshed" size="8" maxlength="6" value="${model.record.etshedStr}">
					 				</c:when>	
					 				<c:otherwise>
					 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="etshed" id="etshed" size="8" maxlength="6" value="">
					 				</c:otherwise>
					 				</c:choose>
					 			</td>
								<td>
									<c:choose>
					 				<c:when test="${model.record.etshet > 0}">
					 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="etshet" id="etshet" size="6" maxlength="4" value="${model.record.etshetStr}">
					 				</c:when>
					 				<c:otherwise>
					 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="etshet" id="etshet" size="6" maxlength="4" value="">
					 				</c:otherwise>
					 				</c:choose>
									
								</td>
			 				</tr>
			 				<tr height="2"><td>&nbsp;</td></tr>
			 				
			            </table>
			            </td>
		            </tr>
	            
	            </table>
            </td>
            
           	<td class="text14" valign="top">
				<table style="width:85%;" align="left" border="0" cellspacing="1" cellpadding="0">
				 	<tr >
					 	<td >
						<table class="formFrameHeader" style="width:100%; border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<td class="text14White">
					 			
					 			<img style="cursor:pointer;" onMouseOver="showPop('transp_info');" onMouseOut="hidePop('transp_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
				            	<span title="Transportør">Transportør&nbsp;</span><font class="text16RedBold" >*</font>
		                		<div class="text11" style="position: relative;" align="left">
			                	<span style="position:absolute;top:2px; width:250px;" id="transp_info" class="popupWithInputText text11"  >
			                	<p><b>Transportør</b>&nbsp;
			                			Transportør for transport. Organisasjonen/bedriften som opererer transporten
			                	</p>
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
				 				<tr>
				 					<td class="text14">&nbsp;<span title="etknt">Knr</span>
				 						&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="etknt" id="etknt" size="10" maxlength="8" value="${model.record.etknt}">
				 					</td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="etnat">Navn</span><font class="text16RedBold" >*</font>
										<a tabindex="-1" id="etnatIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
										</a>
									
									</td>
									<td class="text14">
									
									<img style="cursor:pointer;" onMouseOver="showPop('etrgt_info');" onMouseOut="hidePop('etrgt_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
					            	<span title="etrgt">Orgnr / EORI&nbsp;</span><font class="text16RedBold" >*</font>
			                		<div class="text11" style="position: relative;" align="left">
				                	<span style="position:absolute;top:2px; width:250px;" id="etrgt_info" class="popupWithInputText text11"  >
				                	<p><b>Orgnr / EORI</b>&nbsp;
				                		Transportørens identifikasjonsnummer.På norske transportør forventes norsk organisasjonsnummer. 
				                		På utenlandske transportør som har EORI-nummer, forventes EORI-nummer. 
				                		På utenlandske transportør som ikke har EORI-nummer, forventes deres nasjonale organisasjonsnummer.<br/>
										Eksempel: 961510740 = SAS Norge	
				                	</p>
					           		</span>	
									</div>
									
									</td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"   type="text" class="inputTextMediumBlueMandatoryField" name="etnat" id="etnat" size="35" maxlength="30" value="${model.record.etnat}"></td>
									<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"   type="text" class="inputTextMediumBlueMandatoryField" name="etrgt" id="etrgt" size="18" maxlength="17" value="${model.record.etrgt}"></td>
									
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="etpst">Sted</span><font class="text16RedBold" >*</font></td>
									<td class="text14">&nbsp;<span title="etlkt">Landkode</span><font class="text16RedBold" >*</font></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="etpst" id="etpst" size="25" maxlength="24" value="${model.record.etpst}"></td>
									<td class="text14">
										<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  class="inputTextMediumBlueMandatoryField" name="etlkt" id="etlkt" >
					 						<option value="">-velg-</option>
						 				  	<c:forEach var="dto" items="${model.countryDto}" >
					                       	 	<option title="${dto.code}" value="${dto.code}" <c:if test="${model.record.etlkt == dto.code}"> selected </c:if> >${dto.code}</option>
											</c:forEach>
										</select>
									</td>
									
				 				</tr>
				 				<tr >
									<td class="text14">&nbsp;<span title="etad1t">Adress</span></td>
									<td class="text14">&nbsp;<span title="etpnt">Postnr</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="etad1t" id="etad1t" size="25" maxlength="30" value="${model.record.etad1t}"></td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="etpnt" id="etpnt" size="12" maxlength="9" value="${model.record.etpnt}"></td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="own_etemt_email">E-post</span></td>
									<td class="text14">&nbsp;<span title="own_etemt_telephone">Telefon</span></td>
									
				 				</tr>
				 				<tr >
				 					<c:choose>
				 					<c:when test="${model.record.etemtt == 'EM'}">
				 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_etemt_email" id="own_etemt_email" size="35" maxlength="50" value="${model.record.etemt}"></td>
				 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_etemt_telephone" id="own_etemt_telephone" size="15" maxlength="50" value=""></td>
				 					</c:when>
				 					<c:otherwise>
				 						<c:choose>
						 					<c:when test="${empty model.record.etemtt}">
						 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_etemt_email" id="own_etemt_email" size="35" maxlength="50" value=""></td>
						 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_etemt_telephone" id="own_etemt_telephone" size="15" maxlength="50" value=""></td>
						 					</c:when>
						 					<c:otherwise>
						 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_etemt_email" id="own_etemt_email" size="35" maxlength="50" value=""></td>
												<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_etemt_telephone" id="own_etemt_telephone" size="15" maxlength="50" value="${model.record.etemt}"></td>
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
				<table style="width:85%" align="left" border="0" cellspacing="1" cellpadding="0">
				 	<tr >
					 	<td >
						<table class="formFrameHeader" style="width:100%;" border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<td class="text14White">
					 			
					 			<img style="cursor:pointer;" onMouseOver="showPop('ombud_info');" onMouseOut="hidePop('ombud_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
				            	<span title="Tollrepresentant">Representant / Ombud&nbsp;</span><font class="text16RedBold" >*</font>
		                		<div class="text11" style="position: relative;" align="left">
			                	<span style="position:absolute;top:2px; width:250px;" id="ombud_info" class="popupWithInputText text11"  >
			                	<p><b>Tollrepresentant</b>&nbsp;
			                			Den som leverer opplysninger på vegne av føreren av transportmiddelet og i dennes navn, jf. vareførselsloven § 7-21.
										Merk at innsender (submitter) og tollrepresentant (representative) kan være samme organisasjon
			                	</p>
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
				 				<tr>
				 					<td class="text14">&nbsp;<span title="etknr">Knr</span>
				 					&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="etknr" id="etknr" size="10" maxlength="8" value="${model.record.etknr}">
				 					</td>
				 					
				 				</tr>
				 				<tr >
									<td class="text14">&nbsp;<span title="etnar">Navn</span><font class="text16RedBold" >*</font>
										<a tabindex="-1" id="etnarIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
										</a>
									</td>
									<td class="text14">
									
										<img style="cursor:pointer;" onMouseOver="showPop('etrgr_info');" onMouseOut="hidePop('etrgr_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
						            	<span title="etrgr">Orgnr / EORI&nbsp;</span><font class="text16RedBold" >*</font>
				                		<div class="text11" style="position: relative;" align="left">
					                	<span style="position:absolute;top:2px; width:250px;" id="etrgr_info" class="popupWithInputText text11"  >
					                	<p><b>Orgnr / EORI</b>&nbsp;
					                		Tollrepresentantens identifikasjonsnummer.På norske representanter forventes norsk organisasjonsnummer. 
					                		På utenlandske representant som har EORI-nummer, forventes EORI-nummer. 
					                		På utenlandske representant som ikke har EORI-nummer, forventes deres nasjonale organisasjonsnummer.<br/>
											Eksempel: 984661185 = Posten Norge	
					                	</p>
						           		</span>	
										</div>
									
									
									</td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="etnar" id="etnar" size="25" maxlength="30" value="${model.record.etnar}"></td>
									<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="etrgr" id="etrgr" size="20" maxlength="17" value="${model.record.etrgr}"></td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="etpsr">Sted</span><font class="text16RedBold" >*</font></td>
									<td class="text14">&nbsp;<span title="etlkr">Landkode</span><font class="text16RedBold" >*</font></td>
									
				 				</tr>
				 				<tr >
									<td class="text14">
										<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="etpsr" id="etpsr" size="25" maxlength="24" value="${model.record.etpsr}">
									</td>
									<td class="text14">
										<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  class="inputTextMediumBlueMandatoryField" name="etlkr" id="etlkr" >
					 						<option value="">-velg-</option>
						 				  	<c:forEach var="dto" items="${model.countryDto}" >
					                       	 	<option title="${dto.code}" value="${dto.code}" <c:if test="${model.record.etlkr == dto.code}"> selected </c:if> >${dto.code}</option>
											</c:forEach>
										</select>
						 			</td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="etad1r">Adress</span></td>
									<td class="text14">&nbsp;<span title="etpnr">Postnr</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="etad1r" id="etad1r" size="25" maxlength="30" value="${model.record.etad1r}"></td>
									<td class="text14"><input  type="text" class="inputTextMediumBlue" name="etpnr" id="etpnr" size="12" maxlength="9" value="${model.record.etpnr}"></td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="own_etemr_email">E-post</span></td>
									<td class="text14">&nbsp;<span title="own_etemr_telephone">Telefon</span></td>
									
				 				</tr>
				 				<tr >
									<c:choose>
				 					<c:when test="${empty model.record.etemrt}">
				 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_etemr_email" id="own_etemr_email" size="35" maxlength="50" value=""></td>
				 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_etemr_telephone" id="own_etemr_telephone" size="15" maxlength="50" value=""></td>
				 					</c:when>
		 							<c:otherwise>
				 						<c:choose>
				 							<c:when test="${model.record.etemrt == 'EM'}">
						 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_etemr_email" id="own_etemr_email" size="35" maxlength="50" value="${model.record.etemr}"></td>
						 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_etemr_telephone" id="own_etemr_telephone" size="15" maxlength="50" value=""></td>
						 					</c:when>
						 					<c:otherwise>
						 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_etemr_email" id="own_etemr_email" size="35" maxlength="50" value=""></td>
												<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_etemr_telephone" id="own_etemr_telephone" size="15" maxlength="50" value="${model.record.etemr}"></td>
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
		<tr height="10"><td></td></tr>
		<tr>
			<td align="left" >
				<c:if test="${model.record.etst != 'S'}"> <%-- CANCELED(S) --%>
					&nbsp;&nbsp;<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'>
					<c:if test="${model.record.etlnrt > 0}">
						<input class="inputFormSubmit" type="button" name="sendButton" id="sendButton" value='Send'>
						<div style="display: none;" class="clazz_dialog" id="dialogSend" title="Dialog">
							 <p class="text14" >Er du sikker på at du vil sende till toll.no ?</p>
						</div>
						<a id="alinkCreateNewButton" href="tvinnsaddigitollv2_edit_transport.do?action=doCreate">
							<input class="inputFormSubmitStd" type="button" name="createNewButton" id="createNewButton" value='Lage ny'>
						</a>
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

<c:if test="${not empty model.record.listMasters}">
	<%-- list component --%>
	<tr>
		<td>		
		<table style="width:100%" border="0" >
	    	<%-- separator --%>
	        <tr height="2"><td>&nbsp;</td></tr> 
			<tr>
				<td>
				<table style="width:100%" id="containerdatatableTable" cellspacing="2" align="left" >
				<tr>
				<td class="text11">
							
				<table id="mainList" class="compact" >
					<thead>
					<tr class="tableHeaderField" height="20" >
                    	<th width="2%" class="tableHeaderFieldFirst" ><img title="Update" style="vertical-align:middle;" src="resources/images/update.gif" border="0" alt="edit"></th>
                    	<th width="2%" class="tableHeaderField" >Lnr</th>
                    	<th width="2%" class="tableHeaderField" >Avd</th>
                		<th width="2%" class="tableHeaderField" >Sign</th>
                		<th width="2%" class="tableHeaderField" >Turnr</th>
                		<th width="2%" class="tableHeaderField" ></th>
                		<th width="2%" class="tableHeaderField10" >Flagg</th>
                		<th title="S=SLETTET" width="2%" class="tableHeaderField" ><spring:message code="systema.tvinn.sad.digitoll.list.column.sysped.status"/></th>
                		<th width="2%" class="tableHeaderField" >Br.vekt</th>
                		<th width="2%" class="tableHeaderField" >Doknr.</th>
                		<th width="2%" class="tableHeaderField" >Dokt.</th>
                		<th width="2%" class="tableHeaderField" >Mottaker</th>
                		<th width="2%" class="tableHeaderField" >Avsender</th>
                		<th width="2%" class="tableHeaderField" >Reg.dato</th>
                		<th width="2%" class="tableHeaderField" >Sen.dato</th>
                		<th width="2%" class="tableHeaderField" ><spring:message code="systema.tvinn.sad.digitoll.list.column.api"/></th>
                		<th width="2%" class="tableHeaderField" ><spring:message code="systema.tvinn.sad.digitoll.list.column.api.mrn"/></th>
                		<th width="2%" class="tableHeaderField" ><spring:message code="systema.tvinn.sad.digitoll.list.column.api.request"/></th>
                		<th title="Api-status" width="2%" class="tableHeaderField" ></th>
                		<th title="Api-status S=SUBMITTED,R=REOPENED/DRAFT,D=SLETTET,C=COMPLETED,M=ERROR" width="2%" class="tableHeaderField" ><spring:message code="systema.tvinn.sad.digitoll.list.column.api.status"/></th>
                		<th width="2%" class="tableHeaderField" title="Fjerner manifest fra Tollvesenet" >Slett</th>
                		<th width="2%" class="tableHeaderField" title="Fjerner manifest lokalt (SYSPED)">Kans.</th>
                		<th width="2%" class="tableHeaderField" title="Endre transport">EndrTransp</th>
                		</tr>
                	</thead>
                	<tbody> 
                	<c:forEach items="${model.record.listMasters}" var="masterConsignmentRecord" varStatus="counter">    
		              <c:choose> 
		              	  <%-- if the manifest is DELETED from tollv. show it as red --%>	   
			              <c:when test="${masterConsignmentRecord.emst2 == 'D'}">
			              	<tr class="tableRow" style="background-color: #FEEFB3;color:#9F6000;" height="20" >
			          	  </c:when>
			          	  <c:otherwise>
			          	  	<tr class="tableRow" height="20" >
			          	  </c:otherwise>
		          	  </c:choose>	
		          
		          	   <td  width="2%" class="tableCellFirst" <c:if test="${masterConsignmentRecord.emst2 == 'D'}">style="background-color: #FEEFB3;color: #9F6000;" </c:if> align="center">
		          	   		<a tabindex=-1 style="display: block; width: 100%; height: 100%;"  href="tvinnsaddigitollv2_edit_master.do?action=doFind&emlnrt=${masterConsignmentRecord.emlnrt}&emlnrm=${masterConsignmentRecord.emlnrm}" onClick="setBlockUI();">
               					<c:choose>
		               				<c:when test="${masterConsignmentRecord.emst2 == 'C' || masterConsignmentRecord.emst == 'S'}">
		               					<img title="Read" style="vertical-align:bottom;" src="resources/images/eye.png" height="18px" width="18px" border="0" alt="read">
		               				</c:when>
		               				<c:otherwise>
		               					<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="edit">
		               				</c:otherwise>
	               				</c:choose>
               				</a>
               				
	               	   </td>
	               	   <td width="2%" align="center" class="tableCell" <c:if test="${masterConsignmentRecord.emst2 == 'D'}">style="color: #9F6000;" </c:if> >${masterConsignmentRecord.emlnrm}</td>
		               <td width="2%" align="center" class="tableCell" <c:if test="${masterConsignmentRecord.emst2 == 'D'}">style="color: #9F6000;" </c:if> >${masterConsignmentRecord.emavd}</td>
		               <td width="2%" align="center" class="tableCell" >${masterConsignmentRecord.emsg}</td>
		               <td width="2%" align="center" class="tableCell" <c:if test="${masterConsignmentRecord.emst2 == 'D'}">style="color: #9F6000;" </c:if> >
		               		<c:if test="${masterConsignmentRecord.empro > 0}">
		               			<span title="..." onClick="showPop('h_info${counter.count}');" <c:if test="${not empty masterConsignmentRecord.listHouses}">style="cursor:pointer;color:green;" </c:if> >
		               				${masterConsignmentRecord.empro}
		               			</span>
		               			<c:if test="${not empty masterConsignmentRecord.listHouses}">
		               				<span class="text11" style="position: relative;" align="left">
				                	<span style="position:absolute;top:2px; width:250px;" id="h_info${counter.count}" class="popupWithInputText text11"  >
				                	<button name="_ButtonCloseEtktm" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('h_info${counter.count}');">Close</button> 
					           			<font style="color:royalblue">Antal opd(houses) <b>${masterConsignmentRecord.listHouses.size()}</b></font>
				           				<ul>
				           				<c:forEach items="${masterConsignmentRecord.listHouses}" var="houseRecord" varStatus="h_counter">  
				           					<li><font style="color:royalblue">Opd <b>${houseRecord.ehtdn}</b> Brut.vekt <b>${houseRecord.ehvkb}</b></font></li>
				           				</c:forEach>
				           				</ul>
									</span>	
									</span>
									
								</c:if>
		               		</c:if>
		               </td>
		               <td width="2%" align="center" class="tableCell" <c:if test="${masterConsignmentRecord.emst2 == 'D'}">style="color: #9F6000;" </c:if> >
		               		<c:if test="${masterConsignmentRecord.empro > 0}">
		               			<c:if test="${not empty masterConsignmentRecord.listHouses}">
		               				<font onClick="showPop('h_info2${counter.count}');" title="Houses..." class="inputFormSubmit11 text10 isa_warning"><b>${masterConsignmentRecord.listHouses.size()}</b></font>
			               			<span class="text11" style="position: relative;" align="left">
				                	<span style="position:absolute;top:2px; width:280px;" id="h_info2${counter.count}" class="popupWithInputText text11"  >
				                	<button name="_ButtonCloseEtktm" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('h_info2${counter.count}');">Close</button> 
					           			<font style="color:royalblue">Antal opd(houses) <b>${masterConsignmentRecord.listHouses.size()}</b></font>
				           				<ul>
				           				<c:forEach items="${masterConsignmentRecord.listHouses}" var="houseRecord" varStatus="h_counter">  
				           					<li>
				           						<font style="color:royalblue">Opd <b>${houseRecord.ehtdn}</b> Brut.vekt <b>${houseRecord.ehvkb}</b></font>
				           						<c:choose>
				           							<c:when test="${houseRecord.ehst2 =='M'}">
				           								<font style="color:red">Api&nbsp;<b>ERROR</b> </font>
				           							</c:when>
				           							<c:otherwise>
				           								<font style="color:royalblue">Api&nbsp;<b>${houseRecord.ehst2}</b> </font>
				           							</c:otherwise>
				           						</c:choose>
				           					</li>
				           				</c:forEach>
				           				</ul>
									</span>	
									</span>

								</c:if>
								
		               		</c:if>
		               </td>
		               <td width="2%" align="center" class="tableCell text12">
		               		<c:if test="${masterConsignmentRecord.own_invalidHousesExist}">
	           					<img onClick="showPop('h_info2${counter.count}');" title="Houses..." style="cursor:pointer;" title="ERROR on house/opp:${houseRecord.ehpro}" src="resources/images/redFlag.png" width="18" height="18" border="0">
	           				</c:if>
				       </td>    			
		               <td nowrap width="2%" align="center" class="tableCell text12">
		               	  <c:choose>
		               		<c:when test="${masterConsignmentRecord.emst == 'S'}">
		               			<font class="inputFormSubmit text12 isa_error">KANSELLERT</font>
		               			
	               	   			<%-- We can only CANCEL (S) internally if the emmid and emuuid are gone since we DELETED first from Tollv.(if we even got that far at some point...) --%>
	               	   			<c:if test="${empty masterConsignmentRecord.emmid && empty masterConsignmentRecord.emuuid}">
					   				<a tabindex=-1 class="grantLink" id="grantLink${counter.count}" runat="server" href="#">
										<font title="Gjøre tilgjengelig igjen ved å klikke" class="inputFormSubmit text12 isa_success"><b>e</b></font>
									</a> 
									<div id="dialogUpdateInternalStatusGrant${counter.count}" class="clazz_dialog" title="Dialog">
										<form action="tvinnsaddigitollv2_updateInternalStatus_master.do" name="updateInternalStatusGrantForm${counter.count}" id="updateInternalStatusGrantForm${counter.count}" method="post">
										 	<input type="hidden" name="current_id1${counter.count}" id="current_id1${counter.count}" value="${masterConsignmentRecord.emlnrt}">
											<input type="hidden" name="current_id2${counter.count}" id="current_id2${counter.count}" value="${masterConsignmentRecord.emlnrm}">
										 	<input type="hidden" name="current_status${counter.count}" id="current_status${counter.count}" value="">
										 	<p class="text14" >Er du sikker på at du vil gjøre tilgjengelig igjen Lnr <b>${masterConsignmentRecord.emlnrm}</b> i <b>SYSPED</b> ?</p>
												
										</form>
									</div>
								</c:if>
		               		</c:when>
		               		<c:otherwise>
		               			${masterConsignmentRecord.emst}
		               		</c:otherwise>
		               	   </c:choose>
		               </td>
		               <td width="2%" align="right" class="tableCell" >${masterConsignmentRecord.emvkb}</td>
		               <td width="2%" align="right" class="tableCell" <c:if test="${masterConsignmentRecord.emst2 == 'D'}">style="color: #9F6000;" </c:if> >${masterConsignmentRecord.emdkm}</td>
		               <td width="2%" align="right" class="tableCell" <c:if test="${masterConsignmentRecord.emst2 == 'D'}">style="color: #9F6000;" </c:if> >${masterConsignmentRecord.emdkmt}</td>
		               
		               <td width="2%" align="center" class="tableCell" >${masterConsignmentRecord.emnam}&nbsp;-&nbsp;${masterConsignmentRecord.empsm}&nbsp;${masterConsignmentRecord.emlkm}</td>
		               <td width="2%" align="center" class="tableCell" >${masterConsignmentRecord.emnas}&nbsp;-&nbsp;${masterConsignmentRecord.empss}&nbsp;${masterConsignmentRecord.emlks}</td>
		               <td width="2%" class="tableCell" ><c:if test="${masterConsignmentRecord.emdtr > 0}">${masterConsignmentRecord.emdtr}</c:if></td>
		               <td width="2%" class="tableCell" ><c:if test="${masterConsignmentRecord.emdtin > 0}">${masterConsignmentRecord.emdtin}</c:if></td>
		               <td width="2%" class="tableCell" >
		               		<c:choose>
		               		<c:when test="${ not empty model.record.etktyp && fn:startsWith(model.record.etktyp,'4') }">
								<img title="api:air" style="vertical-align:middle;" id="airplaneImg${masterConsignmentRecord.emuuid}" src="resources/images/airplaneBlue.png" width="25" height="25"border="0" >&nbsp;
							</c:when>
							<c:otherwise>
								<img title="api:road" style="vertical-align:middle;" id="lorryImg${masterConsignmentRecord.emuuid}" src="resources/images/lorry_green.png" width="20" height="20"border="0" >&nbsp;
							</c:otherwise>
							</c:choose>
		               </td>
		               
		               <td width="2%" class="tableCell" ><span class="text14SkyBlue">
		               		<%--
		               		<a style="display: block; width: 100%; height: 100%; cursor:pointer" class="uuidLink text12SkyBlue" id="${masterConsignmentRecord.emmid}">
								${masterConsignmentRecord.emmid}
							</a>
							 --%>
							<font class="text12SkyBlue">${masterConsignmentRecord.emmid}</font> 
							 
		               </td>
		               		
		               <td width="2%" class="tableCell" title="les status på toll.no">
		               		<a style="display: block; width: 100%; height: 100%; cursor:pointer" class="uuidLink text12SkyBlue" id="${masterConsignmentRecord.emuuid}">
								${masterConsignmentRecord.emuuid}
							</a>  
		               </td>
		               <td width="2%" align="center" class="tableCell" >
		               		<c:choose>
		               		<c:when test="${masterConsignmentRecord.emst2 == 'S' || masterConsignmentRecord.emst2 == 'R' || masterConsignmentRecord.emst2 == 'D' || 
		               				masterConsignmentRecord.emst2 == 'M' || masterConsignmentRecord.emst2 == 'C'}">
		               				
		               			<c:if test="${masterConsignmentRecord.emst2 == 'S'}">
		               				<img title="Submitted" src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
		               			</c:if>
		               			<c:if test="${masterConsignmentRecord.emst2 == 'R'}">

		               			</c:if>
		               			<c:if test="${masterConsignmentRecord.emst2 == 'D'}">
									
		               			</c:if>
		               			<c:if test="${masterConsignmentRecord.emst2 == 'M'}">
									<img title="Error" src="resources/images/bulletRed.png" width="10" height="10" border="0" >
		               			</c:if>
		               			<c:if test="${masterConsignmentRecord.emst2 == 'C'}">
		               				<img title="Completed" style="vertical-align:middle;" title="Completed tolldekl at toll.no" src="resources/images/complete-icon.png" width="14px" height="12px" border="0" alt="completion">
		               			</c:if>
		               			
		               		</c:when>
		               		<c:otherwise>
		               			<c:if test="${masterConsignmentRecord.emst != 'S'}">
		               				<img title="To be send?" src="resources/images/bulletYellow.png" width="10" height="10" border="0" >
		               			</c:if>
		               		</c:otherwise>
		               		</c:choose>
		               </td>
		               <td width="2%" align="center" class="tableCell" >
		               		<c:choose>
		               		<c:when test="${masterConsignmentRecord.emst2 == 'S' || masterConsignmentRecord.emst2 == 'R' || masterConsignmentRecord.emst2 == 'D' || 
		               					masterConsignmentRecord.emst2 == 'M' || masterConsignmentRecord.emst2 == 'C'}">
		               					
		               			<c:if test="${masterConsignmentRecord.emst2 == 'S'}">
		               				<span class="text12" title="S" >SUBMITTED</span>
		               			</c:if>
		               			<c:if test="${masterConsignmentRecord.emst2 == 'R'}">
		               				<span class="text12" title="R" >REOPENED/DRAFT</span>
		               			</c:if>
		               			<c:if test="${masterConsignmentRecord.emst2 == 'D'}">
		               				<font class="text12" title="D" color="red">SLETTET</font>
		               			</c:if>
		               			<c:if test="${masterConsignmentRecord.emst2 == 'M'}">
		               				<font class="text12" title="M" color="red">ERROR</font>
		               			</c:if>
		               			<c:if test="${masterConsignmentRecord.emst2 == 'C'}">
		               				<font class="text12" title="C" color="green">COMPLETED</font>
		               			</c:if>
		               			
		               		</c:when>
		               		<c:otherwise>
		               			${masterConsignmentRecord.emst2}
		               		</c:otherwise>
		               		</c:choose>
		               </td>

		               <td width="2%" class="tableCell" align="center"> 
		               		  	
					   				<c:if test="${not empty masterConsignmentRecord.emuuid  && not empty masterConsignmentRecord.emmid}">
					   					<c:if test="${not empty masterConsignmentRecord.emst2 && (masterConsignmentRecord.emst2 == 'S' || masterConsignmentRecord.emst2 == 'M') }">
					   						<a tabindex=-1 class="removeLink" id="removeLink${counter.count}" runat="server" href="#">
												<img src="resources/images/delete.gif" border="0" alt="remove">
											</a>
											<div style="display: none;" class="clazz_dialog" id="dialogUpdateStatus${counter.count}" title="Dialog">
												<form action="tvinnsaddigitollv2_api_delete_master.do" name="updateStatusForm${counter.count}" id="updateStatusForm${counter.count}" method="post">
													<input type="hidden" name="current_id1${counter.count}" id="current_id1${counter.count}" value="${masterConsignmentRecord.emlnrt}">
													<input type="hidden" name="current_id2${counter.count}" id="current_id2${counter.count}" value="${masterConsignmentRecord.emlnrm}">
													<input type="hidden" name="current_mrn${counter.count}" id="current_mrn${counter.count}" value="${masterConsignmentRecord.emmid}">
													<input type="hidden" name="action${counter.count}" id="action${counter.count}" value="doDelete">
												 	<p class="text14" >Er du sikker på at du vil slette denne&nbsp;MRN&nbsp;<b>${masterConsignmentRecord.emmid}</b> fra <b>Tollvesenet</b> ?</p>
												</form>
											</div>
										</c:if>
		              				</c:if>
		              				
              				
	               	   </td>
	               	   <td width="2%" class="tableCell" align="center">
	               	   		<c:if test="${masterConsignmentRecord.emst == 'M' || empty masterConsignmentRecord.emst}">
	               	   			<%-- We can only CANCEL (S) internally if the emmid and emuuid are gone since we DELETED first from Tollv.(if we even got that far at some point...) --%>
	               	   			<c:if test="${empty masterConsignmentRecord.emmid && empty masterConsignmentRecord.emuuid}">
					   				<a tabindex=-1 style="display: block; width: 100%; height: 100%;" class="cancelLink" id="cancelLink${counter.count}" runat="server" href="#">
										<img src="resources/images/remove.png" width="16" height="16" border="0" alt="remove">
									</a> 
									<div id="dialogUpdateInternalStatus${counter.count}" class="clazz_dialog" title="Dialog">
										<form action="tvinnsaddigitollv2_updateInternalStatus_master.do" name="updateInternalStatusForm${counter.count}" id="updateInternalStatusForm${counter.count}" method="post">
										 	<input type="hidden" name="current_id1${counter.count}" id="current_id1${counter.count}" value="${masterConsignmentRecord.emlnrt}">
											<input type="hidden" name="current_id2${counter.count}" id="current_id2${counter.count}" value="${masterConsignmentRecord.emlnrm}">
										 	<input type="hidden" name="current_status${counter.count}" id="current_status${counter.count}" value="S">
										 	<p class="text14" >Er du sikker på at du vil kansellere Lnr <b>${masterConsignmentRecord.emlnrm}</b> i <b>SYSPED</b> ?</p>
												
										</form>
									</div>
								</c:if>
							</c:if>
						</td>	
						<td width="2%" class="tableCell" align="center">
							<%-- Either tansport-not sent or transport-DELETED --%>
							<c:if test="${empty model.record.etmid}">
		               	   		<a tabindex=-1 style="display: block; width: 100%; height: 100%;" class="transformLink" id="emlnrt${masterConsignmentRecord.emlnrt}_emlnrm${masterConsignmentRecord.emlnrm}_etktyp${model.record.etktyp}" runat="server" href="#">
									<img title="Endre transport..." src="resources/images/transform.png" width="20" height="20" border="0" alt="endre transport">
								</a>
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
 
 	
 
	