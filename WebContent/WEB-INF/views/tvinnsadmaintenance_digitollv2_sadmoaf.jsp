<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadMaintenance.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmaintenance_digitollv2_sadmoaf.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
			 
	 		<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;Transport</font>
				<img src="resources/images/update.gif" border="0" alt="edit">
						
			</td>
			<c:if test="${model.record.etavd > -1}">
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a tabindex=-1 id="alinkMaster" style="display:block;" href="tvinnsadmaintenance_digitollv2_sadmoaf_master.do?etavd=${model.record.etavd}">
						<font class="tabDisabledLink">&nbsp;Master</font>
					</a>
				</td>
			</c:if>
	 		<td width="80%" class="tabFantomSpace" align="right" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					
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
                    <th class="tableHeaderField" >&nbsp;Transp.&nbsp;</th>
                    <th class="tableHeaderField" >&nbsp;Ombud&nbsp;</th>
                    <th class="tableHeaderField" >&nbsp;Pass.tollsted&nbsp;</th>
                    <th class="tableHeaderField" >&nbsp;Kjør.Typ.&nbsp;</th>
                    <th class="tableHeaderField" >&nbsp;Slett&nbsp;</th>
                </tr>  
                </thead> 
                <tbody >  
	            <c:forEach var="record" items="${model.list}" varStatus="counter">   
	               <tr class="tableRow" height="20" >
	              
	               <td align="center" width="2%" class="tableCell" style="border-style:solid; border-width:0px 1px 1px 0px; border-color:#FAEBD7;" >
	        	       	<a onClick="setBlockUI(this)" style="display:block;width:100%; height:100%;" id="efavd_${record.etavd}" href="tvinnsadmaintenance_digitollv2_sadmoaf.do?action=doFind&etavd=${record.etavd}">
  							<img src="resources/images/update.gif" border="0" alt="edit">
               			</a>
	               </td>
	               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.etavd}
	               </td>
	               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.etsg}
	               </td>
	               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.etpro}
	               </td>
	               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.etnat}
	               </td>
	               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.etnar}
	               </td>
	               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.ettsd}
	               </td>
	               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.etktyp}
	               </td>
	               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >   		
			   				
              				<a onClick="setBlockUI(this)" style="display:block;width:100%; height:100%;" id="etavdLink_${record.etavd}" href="tvinnsadmaintenance_digitollv2_sadmoaf_delete.do?etavd=${record.etavd}">
								<img src="resources/images/delete.gif" border="0" alt="remove">
							</a>
							<%--
							<div style="display: none;" class="clazz_dialog" id="dialogDelete${counter.count}" title="Dialog">
								<form action="tvinnsadmaintenance_digitollv2_sadmoaf_delete.do" name="deleteForm${counter.count}" id="deleteForm${counter.count}" method="post">
								 	<input type="hidden" name="currentEfavd${counter.count}" id="currentEfavd${counter.count}" value="${record.etavd}">
								 	<input type="hidden" name="selectedStatus${counter.count}" id="selectedStatus${counter.count}" value="D">
								 	<p class="text14" >Er du sikker på at du vil slette Avd&nbsp;<b>${record.etavd}</b></p>
									
								</form>
							</div>
							 --%>	
	               	   </td> 
	            </tr> 
	            </c:forEach>
	            </tbody>
            </table>
	</td>	
</tr>
	
<tr height="5"><td colspan="10">&nbsp;</td></tr>
 
 <tr>
 	<td>
	<%-- --------------------------- --%>	
 	<%-- tab area container PRIMARY  --%>
	<%-- --------------------------- --%>
	<form name="manifestForm" id="manifestForm" action="tvinnsadmaintenance_digitollv2_sadmoaf_edit.do" method="post">
			<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
			<input type="hidden" name="applicationUserSign" id="applicationUserSign" value="${user.tvinnSadSign}">
			<input type="hidden" name="action" id="action" value="doUpdate">
			
			 
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
	           		 
 		<tr>
			<td class="text14" valign="top">
				<table style="width:85%" align="left" border="0" cellspacing="1" cellpadding="0">
					
				 	<tr >
					 	<td >
						<table class="formFrameHeader" style="width:100%;"  border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<td class="text14White">&nbsp;&nbsp;Transportinfo.</td>
						 	</tr>
			            </table>
			            </td>
		            </tr>
		            <tr >
					 	<td>
						<table class="formFrame" style="width:100%;" border="0" cellspacing="1" cellpadding="0">
			 				
							<tr>
			 					<td class="text12" title="etavd">&nbsp;Avd<font class="text16RedBold" >*</font></td>
			 					<td class="text12" title="etsg">&nbsp;Sign</td>
			 					<td class="text12" title="etpro">&nbsp;Tur</td>
		 					</tr>				 				
		 					<tr>
			 					<td>
			 						<c:choose>
			 						<c:when test="${model.record.etavd > 0}">
				 						<input readonly size="7" maxlength="4" class="inputTextReadOnly" id="etavd" name="etavd" value="${model.record.etavd}">
									</c:when>
									<c:otherwise>
										<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return numberKey(event)" size="7" maxlength="4" class="inputTextMediumBlueMandatoryField" list="etavd_list" id="etavd" name="etavd" value="${model.record.etavd}">
										<datalist id="etavd_list">
										  <option value="">-Välj-</option>
						 				  	<c:forEach var="record" items="${model.avdList}" >
						 				  		<option value="${record.avd}"<c:if test="${model.record.etavd == record.avd}"> selected </c:if> >${record.avd}</option> 
											</c:forEach>  
										</datalist>
									</c:otherwise>
									</c:choose>
			 					</td>
			 					<td>
			 						<input size="6" maxlength="3" class="inputTextMediumBlue" list="etsg_list" id="etsg" name="etsg" value="${model.record.etsg}">
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
					 					<input type="text12"  class="inputTextMediumBlue" onKeyPress="return numberKey(event)" name="etpro" id="etpro" size="9" maxlength="8" value="${model.record.etpro}">
					 				</c:when>	
					 				<c:otherwise>
					 					<input type="text12"  class="inputTextMediumBlue" onKeyPress="return numberKey(event)" name="etpro" id="etpro" size="9" maxlength="8" value="">
					 				</c:otherwise>
					 				</c:choose>
			 					</td>
		 					</tr>
		 					<tr height="10"></tr>
				 				
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
			 						
			 						<select class="inputTextMediumBlue" name="etktkd" id="etktkd" >
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="dto" items="${model.modeOfTransportDto}" >
				                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.etktkd == dto.code}"> selected </c:if> >${dto.code}</option>
										</c:forEach>
									</select>	
								</td>
								<td>
									<select class="inputTextMediumBlue" name="etktyp" id="etktyp" >
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="dto" items="${model.typeOfIdentificationMeansTransportDto}" >
				                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.etktyp == dto.code}"> selected </c:if> >${dto.code}</option>
										</c:forEach>
									</select>	
										
								</td>
					 			<td class="text14">
					 				<select class="inputTextMediumBlue" name="etktm" id="etktm" >
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="dto" items="${model.meansOfTransportDto}" >
				                       	 	<option title="${dto.txt1}" value="${dto.code}" <c:if test="${model.record.etktm == dto.code}"> selected </c:if> >${dto.code}</option>
										</c:forEach>
									</select>
					 			</td>
					 			<td class="text14">
					 				<select class="inputTextMediumBlue" name="etklk" id="etklk" >
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
					            	<span title="etkmrk">Kjøretøy kjennemerke</span>
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
			 					<td colspan="2" class="text14"><input type="text" class="inputTextMediumBlue" name="etkmrk" id="etkmrk" size="25" maxlength="35" value="${model.record.etkmrk}"></td>
			 					<td colspan="2" class="text14"><input type="text" class="inputTextMediumBlue" name="etcref" id="etcref" size="19" maxlength="17" value="${model.record.etcref}"></td>
								
			 				</tr>
			 				<tr>
			 					<td colspan="2" class="text14">&nbsp;<span title="etsjaf">Fører-navn</span></td>
								<td colspan="2" class="text14">&nbsp;<span title="etems">Fører-epost / Telefon</span></td>
								
			 				</tr>
			 				<tr >
					 			<td colspan="2" class="text14">
					 				<input type="text" class="inputTextMediumBlue" name="etsjaf" id="etsjaf" size="30" maxlength="50" value="${model.record.etsjaf}">
					 			</td>
								<td colspan="2" class="text14">
									<input type="text" class="inputTextMediumBlue" name="etems" id="etems" size="30" maxlength="50" value="${model.record.etems}">
									
								</td>
			 				</tr>
			 				
				 				
			 				<tr height="2"><td>&nbsp;</td></tr>
			 				<tr>
			 					<td class="text14">&nbsp;<span title="etetad - Estimated date of arrival">ETA</span></td>
								<td class="text14">&nbsp;<span title="etetat-HHmm Estimated time of arrival">ETA-Tid&nbsp;<font class="text10">(HHmm)</font></span></td>
								<td class="text14">&nbsp;<span title="ettsd">Pass.tollsted</span></td>
			 				</tr>
			 				<tr >
			 					
					 			<td class="text14">
					 				<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="etetad" id="etetad" size="8" maxlength="6" value="${model.record.etetadStr}">
					 			</td>
								<td>
									<input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="etetat" id="etetat" size="6" maxlength="4" value="${model.record.etetatStr}">
					 				
								</td>
			 					
								<td>
									<input type="text" class="inputTextMediumBlue" name="ettsd" id="ettsd" size="9" maxlength="8" value="${model.record.ettsd}">
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
					 				<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="etshed" id="etshed" size="8" maxlength="6" value="${model.record.etshedStr}">
					 				
					 			</td>
								<td>
									<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="etshet" id="etshet" size="6" maxlength="4" value="${model.record.etshetStr}">
									
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
									<td class="text14">&nbsp;<span title="etnat">Navn</span>
										<a tabindex="-1" id="etnatIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
										</a>
									
									</td>
									<td class="text14">
									
									<img style="cursor:pointer;" onMouseOver="showPop('etrgt_info');" onMouseOut="hidePop('etrgt_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
					            	<span title="etrgt">Orgnr / EORI&nbsp;</span>
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
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="etnat" id="etnat" size="35" maxlength="30" value="${model.record.etnat}"></td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="etrgt" id="etrgt" size="18" maxlength="17" value="${model.record.etrgt}"></td>
									
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="etpst">Sted</span></td>
									<td class="text14">&nbsp;<span title="etlkt">Landkode</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="etpst" id="etpst" size="25" maxlength="24" value="${model.record.etpst}"></td>
									<td class="text14">
										<select class="inputTextMediumBlue" name="etlkt" id="etlkt" >
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
				            	<span title="Tollrepresentant">Representant / Ombud&nbsp;</span>
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
									<td class="text14">&nbsp;<span title="etnar">Navn</span>
										<a tabindex="-1" id="etnarIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
										</a>
									</td>
									<td class="text14">
									
										<img style="cursor:pointer;" onMouseOver="showPop('etrgr_info');" onMouseOut="hidePop('etrgr_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
						            	<span title="etrgr">Orgnr / EORI&nbsp;</span>
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
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="etnar" id="etnar" size="25" maxlength="30" value="${model.record.etnar}"></td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="etrgr" id="etrgr" size="20" maxlength="17" value="${model.record.etrgr}"></td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="etpsr">Sted</span></td>
									<td class="text14">&nbsp;<span title="etlkr">Landkode</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14">
										<input type="text" class="inputTextMediumBlue" name="etpsr" id="etpsr" size="25" maxlength="24" value="${model.record.etpsr}">
									</td>
									<td class="text14">
										<select class="inputTextMediumBlue" name="etlkr" id="etlkr" >
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
				&nbsp;&nbsp;<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'>
				<a id="alinkCreateNewButton" href="tvinnsadmaintenance_digitollv2_sadmoaf.do?">
					<input class="inputFormSubmitStd" type="button" name="createNewButton" id="createNewButton" value='Lage ny'>
				</a>
			</td>
		</tr> 
	</table>
	</td>
	</tr>	
	</table> 
	</form>
</td>
</tr>




</table>
 
 	
 
	