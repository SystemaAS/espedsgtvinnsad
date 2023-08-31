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
			
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;Transport
						</font>
						<img src="resources/images/update.gif" border="0" alt="edit">
						<c:if test="${model.record.etlnrt > 0}">
							<font class="text14MediumBlue">&nbsp;${model.record.etlnrt}</font>
						</c:if>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkHeader" style="display:block;" href="tvinnsaddigitollv2_edit_master.do?action=doCreate&emlnrt=${model.record.etlnrt}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.createnew"/>&nbsp;Master</font>
							<img src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
						</a>
					</td>
					
					
					
					<%-- <td width="50%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>  --%>
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
	<form name="manifestForm" id="manifestForm" action="tvinnsaddigitollv2_edit_transport.do" method="post">
			<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
			<input type="hidden" name="etuuid" id="etuuid" value="${model.record.etuuid}"> 
			<input type="hidden" name="etmid" id="etmid" value="${model.record.etmid}">
			<input type="hidden" name="action" id="action" value="doUpdate">
			
			<c:if test="${model.record.etlnrt > 0}">
				<input type="hidden" name="etsg" id="etsg" value="${model.record.etsg}">
				<input type="hidden" name="etavd" id="etavd" value="${model.record.etavd}"> 
				<input type="hidden" name="etpro" id="etpro" value="${model.record.etpro}">
				<input type="hidden" name="etlnrt" id="etlnrt" value="${model.record.etlnrt}"> 
			
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
		<c:if test="${!model.record.own_okToSend}">	 
			<tr >
				<td colspan="10" class="text14  ">
	    			<font class="inputText isa_warning" >
	    				Lasten er ikke gyldig for sending via api. Alle master-consignments skal ha MRN-Api-nr. bortsett fra de som er KANSELLERT. Minst en linje må eksistere.
	    			</font>
	    		</td>
	   		</tr>
	  		<tr height="2"><td colspan="10">&nbsp;</td></tr>  
  		</c:if>
  		<tr>	
			<td nowrap colspan="10" class="text14 formFrame" >
	    		MRN-Api:&nbsp;<a class="uuidLinkParent text14SkyBlue" id="${model.record.etmid}">${model.record.etmid}</a>&nbsp;&nbsp;&nbsp;
	    		Id:&nbsp;<a class="uuidLinkParent text14SkyBlue" id="${model.record.etuuid}">${model.record.etuuid}</a>
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
						 						&nbsp;&nbsp;Turnr&nbsp;${model.record.etpro}
						 						&nbsp;&nbsp;Avd&nbsp;${model.record.etavd}  
						 						
						 				</td>
						 				<td class="text14White" align="right">
						 						Stat<a tabindex=-1 id="updateInternalStatusLink" name="updateInternalStatusLink" runat="server" href="#"><font class="text14White">u</font></a>s:&nbsp;${model.record.etst}
						 						&nbsp;&nbsp;&nbsp;Manif.<a tabindex=-1 id="updateManifestStatusLink" name="updateManifestStatusLink" runat="server" href="#"><font class="text14White">st.</font></a>&nbsp;
						 						<a cursor:pointer" title="lese logg" tabindex=-1 id="${model.record.etlnrt}" class="logLink" runat="server" href="#">
						 	
							 						<c:choose>
							 						<c:when test="${model.record.etst2 == 'S' || model.record.etst2 == 'R' || model.record.etst2 == 'D'}">
							 							<c:if test="${model.record.etst2 == 'S'}">
							 								<img src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
							 								<font style="color:#FFFFCC;">SUBMITTED</font>
							 							</c:if>
							 							<c:if test="${model.record.etst2 == 'D'}">
							 								<img src="resources/images/bulletRed.png" width="10" height="10" border="0" >
							 								<font style="color:red;">SLETTET</font>
							 							</c:if>
							 							<c:if test="${model.record.etst2 == 'R'}">
							 								<font style="color:#FFFFFF;">REOPENED/DRAFT</font>
							 							</c:if>
							 						</c:when>
							 						<c:otherwise>
							 							<c:choose>
							 							<c:when test="${model.record.etst2 == 'M'}">
							 								<img src="resources/images/bulletRed.png" width="10" height="10" border="0" >
							 								<font style="color:red">ERROR&nbsp;</font>
							 							</c:when>
							 							<c:otherwise>
							 								<font style="color:#606060;">${model.record.etst2}&nbsp;</font>
							 							</c:otherwise>
							 							</c:choose>
							 						</c:otherwise>
							 						</c:choose>
						 						</a>
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
										
										<td align="right" colspan="2" class="text12" ><span class="text14SkyBlue">
						               		<a tabindex=-1 style="display: block; cursor:pointer" class="uuidLinkParent text12SkyBlue" id="${model.record.etuuid}">
												${model.record.etuuid}
											</a>
						               </td>
										
					 				</tr>
					 				<tr height="2"><td>&nbsp;</td></tr>
			 					</c:when>
				 				<c:otherwise>
									
									<tr>
					 					<td class="text12" title="etsg">&nbsp;Sign<font class="text16RedBold" >*</font></td>
					 					<td class="text12" title="etavd">&nbsp;Avd<font class="text16RedBold" >*</font></td>
					 					<td class="text12" title="etpro">&nbsp;Tur<font class="text16RedBold" >*</font></td>
				 					</tr>				 				
				 					<tr>
					 					<td><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text12"  class="inputTextMediumBlueMandatoryField" name="etsg" id="etsg" size="4" maxlength="3" value="${model.record.etsg}"></td>
					 					<td><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text12"  class="inputTextMediumBlueMandatoryField" name="etavd" id="etavd" size="5" maxlength="4" value="${model.record.etavd}"></td>
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
				 					<td class="text14">&nbsp;<span title="etktkd Mode of Transport">ModeTr.</span><font class="text16RedBold" >*</font></td>
				 					<td class="text14">&nbsp;<span title="etktyp Type of Identification">Kjør.Typ.</span><font class="text16RedBold" >*</font></td>
									<td class="text14">&nbsp;<span title="etktm Type of Means of Transport">Tr.midd.typ.</span><font class="text16RedBold" >*</font></td>
									<td class="text14">&nbsp;<span title="etklk Land code">Landkode</span><font class="text16RedBold" >*</font></td>
				 				</tr>
				 				<tr >
				 					<td class="text14">
										<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"   type="text" class="inputTextMediumBlueMandatoryField" name="etktkd" id="etktkd" size="2" maxlength="1" value="${model.record.etktkd}">
									</td>
									<td>
										<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"   type="text" class="inputTextMediumBlueMandatoryField" name="etktyp" id="etktyp" size="9" maxlength="35" value="${model.record.etktyp}">
									</td>
									
						 			<td class="text14">
						 				<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"   type="text" class="inputTextMediumBlueMandatoryField" name="etktm" id="etktm" size="5" maxlength="4" value="${model.record.etktm}">
										
						 			</td>
						 			<td class="text14">
						 				<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"   type="text" class="inputTextMediumBlueMandatoryField" name="etklk" id="etklk" size="5" maxlength="4" value="${model.record.etklk}">
						 				
						 			</td>
						 			
				 				</tr>
				 				
			 				<tr >
								<td colspan="3" class="text14">&nbsp;<span title="etkmrk">Kjøretøy kjennemerke</span><font class="text16RedBold" >*</font></td>
				 			</tr>
				 			<tr>
			 					<td colspan="3" class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="etkmrk" id="etkmrk" size="25" maxlength="35" value="${model.record.etkmrk}"></td>
								
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
			 					<td colspan="2" class="text14">&nbsp;<span title="etdkm-Ref.liste av masters">Doknr.</span></td>
			 					<td class="text14">&nbsp;<span title="etdkmt-Ref.liste av masters">Doktyp.</span></td>
			 				</tr>
			 				<tr >	
			 					<td colspan="2" class="text14" >
									<input readonly type="text" class="inputTextReadOnly" style="color:#9F6000;" name="Xetdkm" id="Xetdkm" size="30" maxlength="50" value="Ref.liste">
								</td>
								<td class="text14" >
									<input readonly type="text" class="inputTextReadOnly" style="color:#9F6000;" name="Xetdkmt" id="Xetdkmt" size="8" maxlength="50" value="Ref.liste">
								</td>
			 				</tr>
				 				
			 				<tr height="2"><td>&nbsp;</td></tr>
			 				<tr>
			 					<td class="text14">&nbsp;<span title="etetad">ETA</span></td>
								<td class="text14">&nbsp;<span title="etetat-HHmm">ETA-Tid</span></td>
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
					 			<td class="text14White">&nbsp;&nbsp;Transportør&nbsp;</td>
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
									<td class="text14">&nbsp;<span title="etnat">Navn</span><font class="text16RedBold" >*</font></td>
									<td class="text14">&nbsp;<span title="etrgt">Orgnr / EORI</span><font class="text16RedBold" >*</font></td>
									
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
									<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="etlkt" id="etlkt" size="4" maxlength="2" value="${model.record.etlkt}"></td>
									
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
									<td class="text14">&nbsp;<span title="etemt">E-post</span></td>
									<td class="text14">&nbsp;<span title="etemt">Telefon</span></td>
									
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
									<td class="text14">&nbsp;<span title="etnar">Navn</span></td>
									<td class="text14">&nbsp;<span title="etrgr">Orgnr / EORI</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="etnar" id="etnar" size="25" maxlength="30" value="${model.record.etnar}"></td>
									<td class="text14"><input  type="text" class="inputTextMediumBlue" name="etrgr" id="etrgr" size="20" maxlength="17" value="${model.record.etrgr}"></td>
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
						 				<input type="text" class="inputTextMediumBlue" name="etlkr" id="etlkr" size="4" maxlength="2" value="${model.record.etlkr}">
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
									<td class="text14">&nbsp;<span title="etemr">E-post</span></td>
									<td class="text14">&nbsp;<span title="etemr">Telefon</span></td>
									
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
						<c:if test="${model.record.own_okToSend}">
							&nbsp;<input class="inputFormSubmit" type="button" name="sendButton" id="sendButton" value='Send'>
						</c:if>
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
		<table style="width:100%;" border="0" >
	    	<%-- separator --%>
	        <tr height="2"><td>&nbsp;</td></tr> 
			<tr>
				<td>
				<table style="width:100%;" id="containerdatatableTable" cellspacing="2" align="left" >
				<tr>
				<td class="text11">
							
				<table id="mainList" class="compact" >
					<thead>
					<tr class="tableHeaderField" height="20" >
                    	<th width="2%" class="tableHeaderFieldFirst" ><img title="Update" style="vertical-align:middle;" src="resources/images/update.gif" border="0" alt="edit"></th>
                    	<th width="2%" class="tableHeaderField" >Lnr</th>
                    	<th width="2%" class="tableHeaderField" >Avd</th>
                		<th width="2%" class="tableHeaderField" >Turnr</th>
                		<th width="2%" class="tableHeaderField" >Sig</th>
                		<th title="S=SLETTET" width="2%" class="tableHeaderField" >St.</th>
                		<th width="2%" class="tableHeaderField" >Br.vekt</th>
                		<th width="2%" class="tableHeaderField" >Doknr.</th>
                		<th width="2%" class="tableHeaderField" >Dokt.</th>
                		<th width="2%" class="tableHeaderField" >Mottaker</th>
                		<th width="2%" class="tableHeaderField" >Avsender</th>
                		<th width="2%" class="tableHeaderField" >Reg.dato</th>
                		<th width="2%" class="tableHeaderField" >Sen.dato</th>
                		<th width="2%" class="tableHeaderField" >MRN-Api</th>
                		<th width="2%" class="tableHeaderField" >Req.id</th>
                		<th title="Api-status" width="2%" class="tableHeaderField" ></th>
                		<th title="S=SUBMITTED,R=REOPENED/DRAFT,D=SLETTET,C=COMPLETED" width="2%" class="tableHeaderField" >Manif.st</th>
                		<th width="2%" class="tableHeaderField" title="Fjerner manifest fra Tollvesenet" >Slett</th>
                		<th width="2%" class="tableHeaderField" title="Fjerner manifest lokalt (SYSPED)">Kans.</th>
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
		               <td width="2%" align="center" class="tableCell" <c:if test="${masterConsignmentRecord.emst2 == 'D'}">style="color: #9F6000;" </c:if> ><c:if test="${masterConsignmentRecord.empro > 0}">${masterConsignmentRecord.empro}</c:if></td>
		               <td width="2%" align="center" class="tableCell" >${masterConsignmentRecord.emsg}</td>
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
		               
		               <%--
		               <td width="2%" class="tableCell" ><font style="font-size:11px;">${record.efuuid}</font></td>
		                --%>
		               <td width="2%" class="tableCell" ><span class="text14SkyBlue">
		               		<a style="display: block; width: 100%; height: 100%; cursor:pointer" class="uuidLink text12SkyBlue" id="${masterConsignmentRecord.emmid}">
								${masterConsignmentRecord.emmid}
							</a>
		               </td>
		               		
		               <td width="2%" class="tableCell" title="check status in toll.no">
		               		<a style="display: block; width: 100%; height: 100%; cursor:pointer" class="uuidLink text12SkyBlue" id="${masterConsignmentRecord.emuuid}">
								${masterConsignmentRecord.emuuid}
							</a>  
		               </td>
		               <td width="2%" align="center" class="tableCell" >
		               		<c:choose>
		               		<c:when test="${masterConsignmentRecord.emst2 == 'S' || masterConsignmentRecord.emst2 == 'R' || masterConsignmentRecord.emst2 == 'D' || masterConsignmentRecord.emst2 == 'C'}">
		               			<c:if test="${masterConsignmentRecord.emst2 == 'S'}">
		               				<img src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
		               			</c:if>
		               			<c:if test="${masterConsignmentRecord.emst2 == 'R'}">

		               			</c:if>
		               			<c:if test="${masterConsignmentRecord.emst2 == 'D'}">
									
		               			</c:if>
		               			<c:if test="${masterConsignmentRecord.emst2 == 'M'}">
									<img src="resources/images/bulletRed.png" width="10" height="10" border="0" >
		               			</c:if>
		               			<c:if test="${masterConsignmentRecord.emst2 == 'C'}">
		               				<img style="vertical-align:middle;" title="Completed tolldekl at toll.no" src="resources/images/complete-icon.png" width="14px" height="12px" border="0" alt="completion">
		               			</c:if>
		               			
		               		</c:when>
		               		<c:otherwise>
		               			<c:if test="${masterConsignmentRecord.emst != 'S'}">
		               				<img src="resources/images/bulletYellow.png" width="10" height="10" border="0" >
		               			</c:if>
		               		</c:otherwise>
		               		</c:choose>
		               </td>
		               <td width="2%" align="center" class="tableCell" >
		               		<c:choose>
		               		<c:when test="${masterConsignmentRecord.emst2 == 'S' || masterConsignmentRecord.emst2 == 'R' || masterConsignmentRecord.emst2 == 'D' || masterConsignmentRecord.emst2 == 'C'}">
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
 
 	
 
	