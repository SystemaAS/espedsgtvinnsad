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
						<a tabindex=-1 id="alinkTransportList" style="display:block;" href="tvinnsaddigitollv2.do?action=doFind">
							<font class="tabDisabledLink">&nbsp;Transportliste</font>
							<img src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkTransport" style="display:block;" href="tvinnsaddigitollv2_edit_transport.do?action=doFind&etlnrt=${model.record.ehlnrt}">													
							<font class="tabDisabledLink">
								&nbsp;Transport&nbsp;
								<c:if test="${model.record.ehlnrt > 0}">
									<font class="text14MediumBlue">&nbsp;${model.record.ehlnrt}</font>
								</c:if>
								
							</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td title="Master-Doknr:${model.record.masterDto.emdkm}" width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkMaster" style="display:block;" href="tvinnsaddigitollv2_edit_master.do?action=doFind&emlnrt=${model.record.ehlnrt}&emlnrm=${model.record.ehlnrm}">
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
						<c:if test="${not empty model.record.ehmid_own}">
				 			<span title="Sist godkjente MRN" class="inputTextReadOnly text11" style="vertical-align:super;">MRN:&nbsp;${model.record.ehmid_own}</span>
				 			&nbsp;&nbsp;
			 			</c:if>
			 			<a style="vertical-align:super;" title="Kodeverk toll.no" target="_blank" href="https://toll.github.io/api/mo-kodeverk.html">
							<font title="Kodeverk toll.no" class="inputFormSubmit text10 isa_warning"><b>Kodeverk</b></font>
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
						<span style="position:absolute; left:-100px; top:15px;" id="jarStartCmd" class="popupWithInputText"  >
			           		<div class="text11" align="left">
			           			<a id="alinkLogsgLoggerApi" ><span class="text12" style="cursor:pointer;color:green;">1. Api-log</span></a><br/><br/>
			           			<a id="alinkLogsgLoggerSadService" ><span class="text12" style="cursor:pointer;color:green;">2. Sad-service-log</span></a><br/>
			           			<a id="alinkLogsgLoggerCatalina" ><span class="text12" style="cursor:pointer;color:green;">3. Catalina-log</span></a><br/>
			           			
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
			<%-- extras for some child windows i.e Oppdrag childwindow --%>
			<input type="hidden" name="emlnrt" id="emlnrt" value="${model.record.masterDto.emlnrt}">
			<input type="hidden" name="emlnrm" id="emlnrm" value="${model.record.masterDto.emlnrm}">
			
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
						<img title="api:air:entry" style="vertical-align:middle; cursor:pointer;" id="airplaneImg" src="resources/images/airplaneBlue.png" width="25" height="25"border="0" >
					</c:when>
					<c:otherwise>
						<img title="api:road:entry" style="vertical-align:middle;cursor:pointer;" id="lorryImg" src="resources/images/lorry_green.png" width="20" height="20"border="0" >
					</c:otherwise>
					</c:choose>
					<c:if test="${not empty model.record.ehmid}">
						&nbsp;
						<input title="Slett fra toll.no" class="inputFormSubmitStd" type="button" name="deleteButton" id="deleteButton" value='Slett'>
						<div style="display: none;" class="clazz_dialog" id="dialogDelete" title="Dialog">
							 <p class="text14" >Er du sikker på at du ønsker å slette fra toll.no?</p>
						</div>
					</c:if>	
		    		<c:if test="${model.record.ehlnrh > 0}">
			    		<a id="alinkRefreshButton" href="tvinnsaddigitollv2_edit_house.do?action=doFind&ehlnrt=${model.record.ehlnrt}&ehlnrm=${model.record.ehlnrm}&ehlnrh=${model.record.ehlnrh}">
			    			&nbsp;<input title="Refresh all status..." class="inputFormSubmitStd" type="button" name="refreshButton" id="refreshButton" value='Refresh'>
			    		</a>
		    		</c:if>
		    		
		    		<span title="MRN nr. hos toll.no - per House" >MRN-Api&nbsp;</span><span class="text14SkyBlue" id="${model.record.ehmid}">${model.record.ehmid}</span>
		    		&nbsp;&nbsp;<font style="font-weight: bold;color: lightgray;">|</font>&nbsp;&nbsp;
		    		<span title="Transaktionsid hos toll.no - per request" >Trans.id&nbsp;</span><a title="les status på toll.no" class="uuidLink text14SkyBlue" id="${model.record.ehuuid}">${model.record.ehuuid}</a>
		    		&nbsp;&nbsp;<font style="font-weight: bold;color: lightgray;">|</font>&nbsp;&nbsp;
		    		
		    		<a title="lese logg" tabindex=-1 id="${model.record.ehlnrt}_${model.record.ehlnrm}_${model.record.ehlnrh}" class="logLink" runat="server" href="#"><font class="text14 ">Api.st - log</font>&nbsp;
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
					</a>
					<c:if test="${model.record.ehst2 == 'M'}">
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
					<td>
					<table>
					<tr>
						<td class="text14">&nbsp;<span title="ehavd">Avd</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="ehpro">Tur</span><font class="text16RedBold" >*</font>
							<a tabindex="-1" id="ehproIdLink">
								<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
							</a>
						</td>
						<td class="text14">&nbsp;<span title="ehtdn"><span id="ehtdnIdSpan" style="cursor:pointer;color:brown;">O</span>pd</span><font class="text16RedBold" >*</font>
							<a tabindex="-1" id="ehtdnIdLink">
								<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
							</a>
						</td>
						<td class="text14">
							<img style="cursor:pointer;" onMouseOver="showPop('ehvkb_info');" onMouseOut="hidePop('ehvkb_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
			            	<span title="ehvkb">Bruttovekt</span><font class="text16RedBold" >*</font>
	                		<div class="text11" style="position: relative;" align="left">
		                	<span style="position:absolute;top:2px; width:250px;" id="ehvkb_info" class="popupWithInputText text11"  >
		                	<p><b>Bruttovekt</b>&nbsp;
		                			Total bruttovekt for forsendelsen (denne House Consignment) inkludert emballasje. Angis i kilo. 
		                			The total gross mass is the weight of goods of the whole consignment including packaging, but excluding the carrier's equipment for the declaration. 
		                			Skal alltid sendes inn.</p>
			           		</span>	
							</div>
						
						
						</td>
						<td class="text14">&nbsp;
							<img style="cursor:pointer;" onMouseOver="showPop('ehntk_info');" onMouseOut="hidePop('ehntk_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
			            	<span title="ehntk">Ant.kolli</span><font class="text16RedBold" >*</font>
	                		<div class="text11" style="position: relative;" align="left">
		                	<span style="position:absolute;top:2px; width:250px;" id="ehntk_info" class="popupWithInputText text11"  >
		                	<p><b>Ant.kolli</b>&nbsp;
		                			Antall kolli som denne forsendelsen består av.</p>
			           		</span>	
							</div>
						
						
						
						</td>
						<td class="text14">&nbsp;
							<img style="cursor:pointer;" onMouseOver="showPop('ehvt_info');" onMouseOut="hidePop('ehvt_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
			            	<span title="ehvt">Varebeskrivelse</span><font class="text16RedBold" >*</font>
	                		<div class="text11" style="position: relative;" align="left">
		                	<span style="position:absolute;top:2px; width:250px;" id="ehvt_info" class="popupWithInputText text11"  >
		                	<p><b>Varebeskrivelse</b><br/>
		                		<b>Krav til varebeskrivelse</b><br/>
		                		Oppsummert beskrivelse av varene som denne forsendelsen består av. Beskrivelsen skal være så spesifikk som mulig.
		                		Beskrivelsen av varen skal være i tekstlig format og den skal være på varelinjenivå, slik at hver unike vare blir beskrevet. 
		                		Beskrivelsen skal være presis nok til at både fører av transportmiddelet og Tolletaten enkelt kan identifisere varene, og beskrivelsene skal omfatte alle de ulike vareslagene som faktisk befinner seg i lasten. 
		                		Å identifisere varene betyr i denne sammenhengen at de ulike varene kan gjenfinnes og skilles fra hverandre på lasten.
		                	</p>
		                	<p>	
								Generelle beskrivelser som ikke sier noe om en vares beskaffenhet eller art, som for eksempel «deler», «stykkgods», «consolidated», «diverse varer» og lignende skal ikke benyttes. 
								Heller ikke beskrivelser av varegrupper som «bildeler», «landbruksprodukter», «hvitevarer», «frukt og grønt», «husholdningsprodukter», «klær», «sommerklær», mv. vil være en tilstrekkelig varebeskrivelse.
								Eksempler på tilstrekkelig detaljerte varebeskrivelser av varegruppene «bildeler», «landbruksprodukter», «hvitevarer» kan være henholdsvis «bremseskive», «felg», «vindusvisker», «appelsin», «tomat», «hvetemel», «kjøleskap», «kjøkkenmaskin» og «avtrekksvifte».
								Varebeskrivelse kan formuleres på norsk eller engelsk.	
		                	</p>
			           		</span>	
							</div>
						
						
						</td>
						<td class="text14">&nbsp;<span title="ehcnin">Container</span><font class="text16RedBold" >*</font></td>
						<td class="text14">
							<%-- Kan inte ändras tyvärr (Api error)
							<c:if test="${not empty model.record.ehdkh}">
								<a title="Oppdater Dok.nr hvis ønskelig. Du må lagre posten selv for å bevare endringen" tabindex="-1" id="${model.record.ehlnrt}_${model.record.ehdkh}" OnClick="getDocNumber(this)">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/update.gif" border="0" alt="update" >
								</a>
							</c:if>
							 --%>
							<img style="cursor:pointer;" onMouseOver="showPop('ehdkh_info');" onMouseOut="hidePop('ehdkh_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
			            	<span title="ehdkh">Dok.nr</span><font class="text16RedBold" >*</font>
	                		<div class="text11" style="position: relative;" align="left">
		                	<span style="position:absolute;top:2px; width:250px;" id="ehdkh_info" class="popupWithInputText text11"  >
		                	<p><b>Dok.nr</b><br/>
		                		Identifisererer at dette er fraktbrevet til en hovedforsendelse	
		                	</p>
		                	<p>
		                		Det settes automatisk med: representantens organisasjonsnummer + avdeling + oppdragsnummer + random seed (0-100)
		                	</p>
			           		</span>	
							</div> 
							 
							 
						</td>
						<td class="text14">&nbsp;<span title="ehdkht">Dok.type</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="ehst">St.</span></td>
						<td class="text14">&nbsp;<span title="ehst2">
								St<a tabindex=-1 id="updateInternalStatus2Link" name="updateInternalStatus2Link" runat="server" href="#">.</a>2
								</span>
						</td>
						<td class="text14">&nbsp;<span title="ehst3">St.3</span></td>
						<td class="text14">&nbsp;<span title="ehdts">Send.dato</span></td>
						<td class="text14">&nbsp;<span title="ehdts">Master Doknr</span></td>
						
					</tr>
					<tr>	
						<td class="text14">
							<%--
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
							 --%>
							<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  class="inputTextMediumBlueMandatoryField" id="ehavd" name="ehavd">
							  <option value="">-Välj-</option>
			 				  	<c:forEach var="record" items="${model.avdList}" >
			 				  		<option title="${record.namn}" value="${record.avd}"<c:if test="${model.record.ehavd == record.avd}"> selected </c:if> >${record.avd}</option> 
								</c:forEach>  
							</select>	
												
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
							<input  type="text" class="inputTextReadOnly" name="ehdkh" id="ehdkh" size="28" maxlength="50" value="${model.record.ehdkh}">		
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
							<input readonly type="text" class="inputTextReadOnly" name="ehdts" id="ehdts" size="12" maxlength="8" value="${model.record.ehdtsStr}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextReadOnly" name="master_emdkm" id="master_emdkm" size="25" maxlength="50" value="${model.record.masterDto.emdkm}">		
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
							<td class="text14">
								<img style="cursor:pointer;" onMouseOver="showPop('ehuprt_info');" onMouseOut="hidePop('ehuprt_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
				            	<span title="ehuprt">Eksp.prosedyr</span>
		                		<div class="text11" style="position: relative;" align="left">
			                	<span style="position:absolute;top:2px; width:250px;" id="ehuprt_info" class="popupWithInputText text11"  >
				           		<b>Eksp.prosedyr - Outgoing Procedure</b>
				           		<ul>
				           			<li><b>TRA</b> Transittering. Kode som brukes når valgt Tollprosedyre er Direktefortolling for å angi om prosedyren fram til grensen er transittering</li>
									<li><b>EXP</b> Eksport. Kode som brukes når valgt Tollprosedyre er Direktefortolling for å angi om prosedyren fram til grensen er eksport</li>
									<li><b>TRE</b> Transittering og Eksport. Kode som brukes når valgt Tollprosedyre er Direktefortolling for å angi om prosedyren fram til grensen er transittering OG eksport</li>
									<li><b>FALSE</b> Tolldeklarasjon der eksportene er avsluttet i avgangslandet. Eksempel: når sendingen <b>ikke kommer på landevei via Sverige</b>, men for eksempel med fly fra Tyskland eller med båt fra Nederland. Da forutsettes det at eksportene blir utførselsbekreftet/ekspedert av tollmyndigheten i avgangslandet.
													<b/><b>IMMEDIATE_RELEASE_VOEC</b> har nesten alltid <b>FALSE</b>
				           		</ul>
								</span>	
								</div>
							
							
							</td>
							<td class="text14" width="20px"></td>
					
							<td class="text14">
								<img style="cursor:pointer;" onMouseOver="showPop('ehrecid_info');" onMouseOut="hidePop('ehrecid_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
				            	<span title="ehrecid">Postsekk identifikator</span>
		                		<div class="text11" style="position: relative;" align="left">
			                	<span style="position:absolute;top:2px; width:250px;" id="ehrecid_info" class="popupWithInputText text11"  >
				           		<b>Postsekk identifikator - Receptacle Identification Number</b>
				           		<p>
				           			Identifikasjonsnummer til postsekken som denne forsendelsen er fraktet i. Skal kun brukes for post. maxlenght=35
				           		</p>
				           		<p>
				           			Når Postsekk identifikator er fylt ut, sendes ikke ref til masterdokumentet. Enten har du en Postsekk eller Master-dokref når du sender inn. <b>Systemet tar seg av det selv hvis begge finnes</b>
				           		</p>
								</span>	
								</div>
							</td>
							
						</tr>
						<tr>
							<td colspan="3" class="text14">
								<select class="inputTextMediumBlueMandatoryField" id="ehprt" name="ehprt">
								  	<option title="Overgang til fri disponering" value="IMMEDIATE_RELEASE_IMPORT" <c:if test="${model.record.ehprt == 'IMMEDIATE_RELEASE_IMPORT'}"> selected </c:if> >IMMEDIATE_RELEASE_IMPORT</option> 
									<option title="Transittering" value="TRANSIT_IMPORT" <c:if test="${model.record.ehprt == 'TRANSIT_IMPORT'}"> selected </c:if> >TRANSIT_IMPORT</option> 
									<option title="Oppstart transittering" value="TRANSIT_RELEASE" <c:if test="${model.record.ehprt == 'TRANSIT_RELEASE'}"> selected </c:if> >TRANSIT_RELEASE</option> 
									<option title="VOEC-sendinger" value="IMMEDIATE_RELEASE_VOEC" <c:if test="${model.record.ehprt == 'IMMEDIATE_RELEASE_VOEC'}"> selected </c:if> >IMMEDIATE_RELEASE_VOEC</option>
									<option title="Samlefortolling" value="COLLECTIVE_RELEASE" <c:if test="${model.record.ehprt == 'COLLECTIVE_RELEASE'}"> selected </c:if> >COLLECTIVE_RELEASE</option>
									<option title="Innlegg på tollager" value="WAREHOUSE_RELEASE" <c:if test="${model.record.ehprt == 'WAREHOUSE_RELEASE'}"> selected </c:if> >WAREHOUSE_RELEASE</option> 
									 
								</select>
				
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
							<td class="text14" width="20px"></td>
							<td class="text14"><input type="text" class="inputTextMediumBlue" name="ehrecid" id="ehrecid" size="30" maxlength="35" value="${model.record.ehrecid}"></td>
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
							<td  class="text16">
								<a title="Aktuelle eksempler..." target="_blank" href="https://toll.github.io/api/mo-eksempler">
									<font title="Aktuelle eksempler" class="inputFormSubmit text10 isa_info"><b>Eksempler</b></font>
								</a>
								<img style="cursor:pointer;" onMouseOver="showPop('prevDocs_info');" onMouseOut="hidePop('prevDocs_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
				            	<b>Tidligere dokumenter</b>
								
								<div class="text11" style="position: relative;" align="left">
			                	<span style="position:absolute;top:2px; width:250px;" id="prevDocs_info" class="popupWithInputText text11"  >
			                	<p><b>Tidligere dokumenter</b><br/>
			                		Informasjon som angir referanser til de relaterte dokumentene for denne forsendelsen. 
			                		Det gjelder både dokumentene for tidligere prosedyrer, som for eksempel transittering, og for påfølgende prosedyrer, som tolldeklarasjon. 
			                		Relevant dokument inneholder referansenummer til annen melding allerede avgitt på en annen plikt eller prosedyre som omhandler den samme forsendelsen, f.eks. MRN fra Transittering eller Tolldeklarasjonsid.
			                	</p>
			                	
				           		</span>	
								</div>
							</td>
							
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							
						<tr >
			 			<tr>
			 				<td class="text14">&nbsp;
								<img style="cursor:pointer;" onMouseOver="showPop('ehtrty_info');" onMouseOut="hidePop('ehtrty_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
				            	<span title="ehtrty">Ref.type</span>
		                		<div class="text11" style="position: relative;" align="left">
			                	<span style="position:absolute;top:2px; width:250px;" id="ehtrty_info" class="popupWithInputText text11"  >
				           		<b>Ref.type - Additional Previous Document Type</b>
				           		<ul>
				           			<li><b>CUDE</b> Tolldeklarasjon. Kode som skal brukes dersom det er deklarasjonsID og ikke typeOfReference som skal fylles ut på previousDocuments-objektet</li>
									<li><b>RETR</b> Oppstart transittering. Kode som skal brukes dersom det er en LRN til transitteringsdeklarasjonen som fylles ut i referenceNumber på previousDocuments-objektet</li>
									<li><b>N820</b> Transittering. Transittering som er startet opp utenfor Norge og som bare skal grensepasseres ved ankomst til grensen eller Transittering som skal fullføres mot tolldeklarasjon ved grensepassering
				           		</ul>
								</span>	
								</div>

							</td>
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
			 				<td class="text14">
			 					<c:choose>
					 				<c:when test="${model.record.eh0068a > 0}">
					 					<input type="text" class="inputTextMediumBlue toggleDirektfortolling" name="eh0068a" id="eh0068a" size="10" maxlength="8" value="${model.record.eh0068aStr}">
					 				</c:when>	
					 				<c:otherwise>
					 					<input type="text" class="inputTextMediumBlue toggleDirektfortolling" name="eh0068a" id="eh0068a" size="10" maxlength="8" value="">
					 				</c:otherwise>
				 				</c:choose>
			 					
			 				</td>
			 				<td class="text14"><input type="text" class="inputTextMediumBlue toggleDirektfortolling" name="eh0068b" id="eh0068b" size="8" maxlength="6" value='<c:if test="${model.record.eh0068b!='0'}">${model.record.eh0068b}</c:if>'></td>
			 				<td class="text14"><input type="text" class="inputTextMediumBlue toggleTransit" name="ehtrnr" id="ehtrnr" size="20" maxlength="18" value="${model.record.ehtrnr}"></td>
						</tr>
						<tr height="5"><td></td></tr>
						
						<tr >
							<td class="text14">
								<img style="cursor:pointer;" onMouseOver="showPop('ehetypt_info');" onMouseOut="hidePop('ehetypt_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
				            	<span title="ehetypt">Eksporttype</span>
		                		<div class="text11" style="position: relative;" align="left">
			                	<span style="position:absolute;top:2px; width:500px;" id="ehetypt_info" class="popupWithInputText text11"  >
				           		<b>Eksporttype</b>
				           		<ul>
				           			<li><b>UGE_EXPORT</b> UGE Eksport fra Sverige som på forhånd er klarert for utførsel av det svenske Tullverket. Ifm med ankomst til grensen sendes det automatisk en ankomst- og utførselsbekreftelse til Tullverket. Medfører ikke behov for manuell ekspedisjon på grensen.	
										18 tegn. de to første tegnene skal være siffer.de tre neste skal være bokstavene SEE. <br/>Eksempel: 23SEE0123456789QWE</li>
									<li><b>EUEIR_EXPORT</b>	EUEIR Eksport fra Sverige. Ingen videre oppfølging for Tolletaten ifm ankomst og innpassering. Medfører ikke behov for manuell ekspedisjon på grensen.	
										inntil 35 tegn. <br/>Eksempel: SE009251-20230602-24</li>
									<li><b>ALE_EXPORT</b> ALE Eksport fra Sverige. Ingen videre oppfølging for Tolletaten ifm ankomst og innpassering. Medfører ikke behov for manuell ekspedisjon på grensen.	
										18 tegn. <br/>Eksempel:23SEALEATD3OAW6MG1</li>
									<li><b>UNU_EXPORT</b> UNU Eksport fra Sverige. Dette er det Tullverket kaller en "Standardtulldeklaration" og den må alltid ekspederes av et "Exporttullkontor" før varen kan føres ut av Sverige. Pga grensetollsamarbeidet er det f.eks No SVD/No Ørje som fungerer som nettopp dette Exporttullkontoret når eksporten skal til Norge. Dette innebærer at den norske tolleren må ekspedere eksportdeklarasjonen i det svenske systemet. Ekspedisjon kan også innebære manuell behandling tilsvarende maskebehandling i Tvinn. Etter av deklarasjonen er ekspedert, må den utførselsbekreftes på samme måte som en UGE. Medfører behov for manuell ekspedisjon på grensen.	
										10 tegn. <br/>Eksempel:FKR0116862</li>
									<li><b>ECS_EXPORT</b> ECS Eksport fra EU. Ekspedisjonsprosessen innebærer at toller utførselsbekrefter eksporten i eksportkontrollsystemet (ECS). Medfører behov for manuell ekspedisjon på grensen.	
										18 tegn <br/>Eksempel:23FIMHFG8987345TY7</li>
								</ul>		
				           		</span>	
								</div>
							
							
							
							</td>
							<td class="text14">
								<img style="cursor:pointer;" onMouseOver="showPop('eheid_info');" onMouseOut="hidePop('eheid_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
				            	<span title="eheid">Eksp.id</span>
		                		<div class="text11" style="position: relative;" align="left">
			                	<span style="position:absolute;top:2px; width:250px;" id="eheid_info" class="popupWithInputText text11"  >
				           		<b>Eksp.id</b>
				           		<p>Unik referanse til eksportdeklarasjonen fra Sverige. Dette kan være den MRN (Master Record Number) som EU utsteder for eksportdeklarasjonen. Hva slags eksportreferanse som oppgis angis i eksporttype-feltet</p>
								</span>	
								</div>
							
							
							</td>	
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
				 				<input type="text" class="inputTextMediumBlue toggleDirektfortolling" name="eheid" id="eheid" size="22" maxlength="18" value="${model.record.eheid}">
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
								<td class="text14">&nbsp;<span title="ehlks">Landkode</span><font class="text16RedBold" >*</font></td>
								
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
									<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  class="inputTextMediumBlueMandatoryField" name="ehlks" id="ehlks" >
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
								<td colspan="2" class="text14">&nbsp;<span title="ehpbs">Adress-2</span></td>
								
			 				</tr>
			 				<tr >
			 					<td class="text14">&nbsp;</td>
								<td colspan="2" class="text14"><input type="text" class="inputTextMediumBlue" name="ehpbs" id="ehpbs" size="50" maxlength="70" value="${model.record.ehpbs}"></td>
								
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
									<td class="text14">&nbsp;<span title="ehlkm">Landkode</span><font class="text16RedBold" >*</font></td>
									
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
										<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  class="inputTextMediumBlueMandatoryField" name="ehlkm" id="ehlkm" >
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
				 					<td colspan="2" class="text14">&nbsp;<span title="ehpbm">Adress-2</span></td>
									
				 				</tr>
				 				<tr >
				 					<td class="text14">&nbsp;</td>
									<td colspan="2" class="text14"><input type="text" class="inputTextMediumBlue" name="ehpbm" id="ehpbm" size="50" maxlength="70" value="${model.record.ehpbm}"></td>
									
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
							<input class="inputFormSubmit" type="button" name="sendButton" id="sendButton" value='Send'>
							<div style="display: none;" class="clazz_dialog" id="dialogSend" title="Dialog">
								 <p class="text14" >Er du sikker på at du vil sende till toll.no ?</p>
							</div>
							<a id="alinkCreateNewButton" href="tvinnsaddigitollv2_edit_house.do?action=doCreate&ehlnrt=${model.record.ehlnrt}&ehlnrm=${model.record.masterDto.emlnrm}&ehavd=${model.record.masterDto.emavd}&ehpro=${model.record.masterDto.empro}">
								<input class="inputFormSubmitStd" type="button" name="createNewButton" id="createNewButton" value='Lage ny'>
							</a>
						</c:otherwise>
					</c:choose>
					&nbsp;&nbsp;&nbsp;&nbsp;<input class="inputFormSubmitBlue" type="button" name="buttonInitVoec" id="buttonInitVoec" value='Varelinjer - VOEC'>
					<input class="inputFormSubmitStd" type="button" value="Laste opp til arkivet" name="uplButton" onClick="window.open('tvinnsadmanifest_childwindow_uploadFile.do?action=doInit&wsavd=${model.record.ehavd}&wsopd=${model.record.ehtdn}','archiveFromHouseFileUpload','top=300px,left=800px,height=250px,width=330px,scrollbars=no,status=no,location=no')">
					<c:if test="${not empty model.record.ehrg && not empty model.record.eh0068a && not empty model.record.eh0068b }">
						<input title="" class="inputFormSubmitStd" type="button" name="sttButton" id="sttButton" value='Send doks. til toll.no'>
					</c:if>
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




<%-- Dialog update status 2 --%>		
<tr>
	<td>
		<div id="dialogUpdateInternalStatus2" title="Dialog">
			
			<form action="tvinnsaddigitollv2_updateInternalStatus2_house.do" name="updateInternalStatusForm2" id="updateInternalStatusForm2" method="post">
			 	<input type="hidden" name="ehlnrt" id="ehlnrt" value="${model.record.ehlnrt}">
			 	<input type="hidden" name="ehlnrm" id="ehlnrm" value="${model.record.ehlnrm}">
			 	<input type="hidden" name="ehlnrh" id="ehlnrh" value="${model.record.ehlnrh}">
			 	<p class="text14" >Change Internal status2 as needed.</p>
				<table>
					<tr>
						<td class="text14" align="left" >&nbsp;Status</td>
						<td class="text14MediumBlue">
							<select class="selectMediumBlueE2" name="ehst2" id="ehst2">
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




<%-- ------------------------- --%>
<%-- DIALOG render logsg   --%>
<%-- ------------------------- --%>
<tr>
<td>
	<div id="dialogLoggerLocal" title="Dialog" style="display:none">
		<form>
	 	<table>
	 		<tr>
				<td colspan="3" class="text14" align="left" >Password</td>
			</tr>
			<tr >
				<td>
					<input type="password" class="inputText" id="pwdLocal" name="pwdLocal" size="15" maxlength="15" value=''>
				</td>
			</tr>
			
			<tr height="5"><td></td></tr>
			<tr >
				<td>
					<input type="text" class="inputText" id="logLevelLocal" name="logLevelLocal" size="8" maxlength="8" value='INFO'>
				</td>
			</tr>
			<tr height="10"><td></td></tr>
			<tr>
				<td colspan="3" class="text14MediumBlue" align="left">
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
 
 	
 
	