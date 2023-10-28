<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadMaintenance.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmaintenance_digitollv2_sadmoaf_master.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
			 
		 		<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a tabindex=-1 id="alinkTransport" style="display:block;" href="tvinnsadmaintenance_digitollv2_sadmoaf.do?action=doFind&etavd=${model.record.etavd}">													
						<font class="tabDisabledLink">
							&nbsp;Transport&nbsp;
							<font class="text14MediumBlue">&nbsp;${model.record.etavd}</font>
						</font>
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="15%" valign="bottom" class="tab" align="center" nowrap>
					<font class="tabLink">&nbsp;Master</font>
					<img src="resources/images/update.gif" border="0" alt="edit">	
				</td>
				<td width="80%" class="tabFantomSpace" align="right" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
		</tr>
	</table>
	</td>
 </tr>
 
 <tr>
 	<td>
	<%-- --------------------------- --%>	
 	<%-- tab area container PRIMARY  --%>
	<%-- --------------------------- --%>
	<form name="manifestForm" id="manifestForm" action="tvinnsadmaintenance_digitollv2_sadmoaf_edit_master.do?etavd=${model.record.etavd}" method="post">
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

		<tr height="2"><td></td></tr>
		
		<tr>
			<td colspan="3" class="text14" valign="top">
				<table style="width:90%" align="left" border="0" cellspacing="1" cellpadding="0">					
					<tr>
						<td class="text14">&nbsp;<span title="emavd">Avd</span></td>
						<td class="text14">&nbsp;<span title="emsg">Sign</span></td>
						<td class="text14">&nbsp;<span title="empro">Tur</span>
							
						</td>
						<td class="text14">&nbsp;<span title="emvkb">Bruttovekt</span></td>
						<td class="text14">&nbsp;<span title="emcn">Container</span></td>
						<td class="text14">&nbsp;<span title="emdkm">Dok.nr</span></td>
						<td class="text14">&nbsp;<span title="emdkmt">Dok.type</span></td>
						<td class="text14">&nbsp;<span title="emknt Transportør KundeNr.">Transp.knr.</span></td>
						<td class="text14">&nbsp;<span title="emrgt - Transportør OrgNr. / EORI">Transp.Orgnr / EORI</span></td>
						
						<td class="text14">&nbsp;<span title="emst">St.</span></td>
						<td class="text14">&nbsp;<span title="emst2">St.2</span></td>
						<td class="text14">&nbsp;<span title="emst3">St.3</span></td>
						<td class="text14">&nbsp;<span title="emdtr">Reg.dato</span></td>
						<td class="text14">&nbsp;<span title="emdtin">Send.dato</span></td>
												
					</tr>
					<tr>
						
						<td>
	 						<select class="inputTextMediumBlue" id="emavd" name="emavd" >
							  <option value="">-Välj-</option>
			 				  	<c:forEach var="record" items="${model.avdList}" >
			 				  		<option title="${record.namn}" value="${record.avd}"<c:if test="${model.record.emavd == record.avd}"> selected </c:if> >${record.avd}</option> 
								</c:forEach>  
							</select>
	 					</td>
	 					<td>
	 						<select class="inputTextMediumBlue" id="emsg" name="emsg">
							  <option value="">-Välj-</option>
			 				  	<c:forEach var="record" items="${model.signList}" >
			 				  		<option title="${record.namn}" value="${record.sign}"<c:if test="${model.record.emsg == record.sign}"> selected </c:if> >${record.sign}</option> 
								</c:forEach>  
							</select>	
	 					</td>
						
						<td class="text14">
							<c:choose>
								<c:when test="${model.record.empro > 0}">
									<input      onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="empro" id="empro" size="10" maxlength="8" value="${model.record.empro}">	
								</c:when>
								<c:otherwise>
									<input      onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="empro" id="empro" size="10" maxlength="8" value="">								
								</c:otherwise>
							</c:choose>					
						</td>		
							
						<td class="text14">
							<input      onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="emvkb" id="emvkb" size="11" maxlength="9" value="${model.record.emvkb}">									
						</td>
					
						<td class="text14">
							<select class="inputTextMediumBlue" id="emcn" name="emcn">
					  			<option title="Varer ikke transportert i beholder" value="0" <c:if test="${empty model.record.emcn || model.record.emcn == '0'}"> selected </c:if> >0</option>
	 							<option title="Varer transportert i beholder" value="1" <c:if test="${model.record.emcn == '1'}"> selected </c:if> >1</option>
							</select>									
						</td>
						<td class="text14">
							<input     type="text" class="inputTextMediumBlue" name="emdkm" id="emdkm" size="25" maxlength="50" value="${model.record.emdkm}">	
							
						</td>
						<td class="text14">
							<select     class="inputTextMediumBlue" name="emdkmt" id="emdkmt" >
		 						<option value="">-velg-</option>
			 				  	<c:forEach var="dto" items="${model.previousDocumentsDto}" >
		                       	 	<option title="${dto.txt1}&nbsp;-&nbsp;${dto.txt2}" value="${dto.code}" <c:if test="${model.record.emdkmt == dto.code}"> selected </c:if> >${dto.code}</option>
								</c:forEach>
							</select>		
						</td>
						
						<td class="text14">
							<input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="emknt" id="emknt" size="10" maxlength="8" value="${model.record.emknt}">								
						</td>
						<td class="text14">
							<input      type="text" class="inputTextMediumBlue" name="emrgt" id="emrgt" size="19" maxlength="17" value="${model.record.emrgt}">												
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
				 					<c:when test="${model.record.ememst == 'EM'}">
				 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_emems_email" id="own_emems_email" size="35" maxlength="50" value="${model.record.emems}"></td>
				 						<td class="text14"><input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="own_emems_telephone" id="own_emems_telephone" size="15" maxlength="50" value=""></td>
				 					</c:when>
				 					<c:otherwise>
				 						<c:choose>
						 					<c:when test="${empty model.record.ememst}">
						 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_emems_email" id="own_emems_email" size="35" maxlength="50" value=""></td>
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
				 					<c:when test="${model.record.ememmt == 'EM'}">
				 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_ememm_email" id="own_ememm_email" size="35" maxlength="50" value="${model.record.ememm}"></td>
				 						<td class="text14"><input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="own_ememm_telephone" id="own_ememm_telephone" size="15" maxlength="50" value=""></td>
				 					</c:when>
				 					<c:otherwise>
				 						<c:choose>
						 					<c:when test="${empty model.record.ememmt}">
						 						<td class="text14"><input  type="text" class="inputTextMediumBlue" name="own_ememm_email" id="own_ememm_email" size="35" maxlength="50" value=""></td>
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
			&nbsp;&nbsp;<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'>
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
 
 	
 
	