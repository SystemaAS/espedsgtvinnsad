<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_edit_master.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
							<a id="alinkHeader" style="display:block;" href="tvinnsaddigitollv2_edit_house.do?action=doCreate&ehlnrt=${model.record.emlnrt}&ehlnrm=${model.record.emlnrm}">
								<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.createnew"/>&nbsp;<spring:message code="systema.tvinn.sad.digitoll.list.tab.house"/></font>
								<img src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
							</a>
						</td>
					</c:if>
					
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
		    		MRN-Api&nbsp;<a class="uuidLinkParent text14SkyBlue" id="${model.record.emmid}">${model.record.emmid}</a>
		    		&nbsp;&nbsp;<font style="font-weight: bold;color: lightgray;">|</font>&nbsp;&nbsp;
		    		Id&nbsp;<a class="uuidLinkParent text14SkyBlue" id="${model.record.emuuid}">${model.record.emuuid}</a>
		    		&nbsp;&nbsp;<font style="font-weight: bold;color: lightgray;">|</font>&nbsp;&nbsp;
		    		
		    		<a title="lese logg" tabindex=-1 id="${model.record.emlnrt}_${model.record.emlnrm}" class="logLink" runat="server" href="#"><font class="text14 ">Master.st - log</font>&nbsp;
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
					</a>&nbsp;
		   		</td>
	   		</tr>
	   		</table>
	   		</td>
		</tr>
		<tr height="2"><td></td></tr>
		
		<tr>
			<td colspan="3" class="text14" valign="top">
				<table style="width:90%" align="left" border="0" cellspacing="1" cellpadding="0">					
					<tr>
						<td class="text14">&nbsp;<span title="emavd">Avd</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="emsg">Sign</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="empro">Tur</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="emvkb">Bruttovekt</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="emcn">Container</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="emdkm">Dok.nr</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="emdkmt">Dok.type</span><font class="text16RedBold" >*</font></td>
						<td class="text14">&nbsp;<span title="emknt Transportør KundeNr.">Transp.knr.</span></td>
						<td class="text14">&nbsp;<span title="emrgt - Transportør OrgNr. / EORI">Transp.Orgnr / EORI</span><font class="text16RedBold" >*</font></td>
						
						<td class="text14">&nbsp;<span title="emst">St.</span></td>
						<td class="text14">&nbsp;<span title="emst2">St.2</span></td>
						<td class="text14">&nbsp;<span title="emst3">St.3</span></td>
						<td class="text14">&nbsp;<span title="emdtr">Reg.dato</span></td>
						<td class="text14">&nbsp;<span title="emdtin">Send.dato</span></td>
												
					</tr>
					<tr>
						
						<td>
	 						<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  size="7" maxlength="4" class="inputTextMediumBlueMandatoryField" list="emavd_list" id="emavd" name="emavd" value="${model.record.emavd}">
							<datalist id="emavd_list">
							  <option value="">-Välj-</option>
			 				  	<c:forEach var="record" items="${model.avdList}" >
			 				  		<option value="${record.avd}"<c:if test="${model.record.emavd == record.avd}"> selected </c:if> >${record.avd}</option> 
								</c:forEach>  
							</datalist>
	 					</td>
	 					<td>
	 						<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  size="6" maxlength="3" class="inputTextMediumBlueMandatoryField" list="emsg_list" id="emsg" name="emsg" value="${model.record.emsg}">
							<datalist id="emsg_list">
							  <option value="">-Välj-</option>
			 				  	<c:forEach var="record" items="${model.signList}" >
			 				  		<option value="${record.sign}"<c:if test="${model.record.emsg == record.sign}"> selected </c:if> >${record.sign}</option> 
								</c:forEach>  
							</datalist>	
	 					</td>
						
						<td class="text14">
							<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="empro" id="empro" size="10" maxlength="8" value="${model.record.empro}">									
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
							<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="emdkm" id="emdkm" size="25" maxlength="50" value="${model.record.emdkm}">		
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
							<input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="emknt" id="emknt" size="10" maxlength="8" value="${model.record.emknt}">								
						</td>
						<td class="text14">
							<input  required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="emrgt" id="emrgt" size="19" maxlength="17" value="${model.record.emrgt}">												
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
		<table style="width:65%;" border="0">
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
											<option title="En fysisk person" value="1" <c:if test="${model.record.emtpps == '1'}"> selected </c:if> >Fys.person</option>
								  			<option title="En juridisk person, det vil si en bedrift" value="2" <c:if test="${model.record.emtpps == '2'}"> selected </c:if> >Bedrift</option>
								  			<option title="En samling personer" value="3" <c:if test="${model.record.emtpps == '3'}"> selected </c:if> >Sam.pers.</option> 	
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
											<option title="En fysisk person" value="1" <c:if test="${model.record.emtppm == '1'}"> selected </c:if> >Fys.person</option>
								  			<option title="En juridisk person, det vil si en bedrift" value="2" <c:if test="${model.record.emtppm == '2'}"> selected </c:if> >Bedrift</option>
								  			<option title="En samling personer" value="3" <c:if test="${model.record.emtppm == '3'}"> selected </c:if> >Sam.pers.</option> 	
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
		</tr>
		</table>
		</td>
		</tr>
	
		<tr height="3"><td></td></tr>
		<tr>
			<td align="left" >
			<c:if test="${model.record.emst != 'S'}"> <%-- CANCELED(S) --%>
				&nbsp;&nbsp;<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'>
				<c:if test="${model.record.emlnrm > 0}">
					<c:choose>
						<c:when test="${model.record.emst2 == 'C' }"> <%--COMPLETED(C) --%>
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
					<tr class="tableHeaderField" height="20" >
                    	<th width="2%" class="tableHeaderFieldFirst" ><img title="Update" style="vertical-align:middle;" src="resources/images/update.gif" border="0" alt="edit"></th>
                    	<th width="2%" class="tableHeaderField" >Lnr</th>
                    	<th width="2%" class="tableHeaderField" >Avd</th>
                		<th width="2%" class="tableHeaderField" >Turnr</th>
                		<th width="2%" class="tableHeaderField" >Opd</th>
                    	<th title="S=SLETTET" width="2%" class="tableHeaderField" >St.</th>
                		<th width="2%" class="tableHeaderField" >Br.vekt</th>
                		<th width="2%" class="tableHeaderField" >Ant. kolli</th>
                		<th width="2%" class="tableHeaderField" >Doknr.</th>
                		<th width="2%" class="tableHeaderField" >Dokt.</th>
                		<th width="2%" class="tableHeaderField" >Prosed.</th>
                		<th width="2%" class="tableHeaderField" >Out Prosed.</th>
                		<th width="2%" class="tableHeaderField" >Type</th>
                		<th width="2%" class="tableHeaderField" >Eksp.id</th>
                		<th width="2%" class="tableHeaderField" >Mott.</th>
                		<th width="2%" class="tableHeaderField" >Avs.</th>
                		<th width="2%" class="tableHeaderField" >Sen. tid</th>
                		<th width="2%" class="tableHeaderField" >MRN-Api</th>
                		<th width="2%" class="tableHeaderField" >Req.id</th>
                		<th title="Api-status" width="2%" class="tableHeaderField" ></th>
                		<th title="S=SUBMITTED,R=REOPENED/DRAFT,D=SLETTET,C=COMPLETED,M=ERROR" width="2%" class="tableHeaderField" >Manif.st</th>
                		<th width="2%" class="tableHeaderField" title="Fjerner manifest fra Tollvesenet" >Slett</th>
                		<th width="2%" class="tableHeaderField" title="Fjerner manifest lokalt (SYSPED)">Kans.</th>
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
		          
		          	   <td width="2%" class="tableCellFirst" <c:if test="${houseConsignmentRecord.ehst2 == 'D'}">style="background-color: #FEEFB3;color: #9F6000;" </c:if> align="center">
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
	               	   <td width="2%" align="center" class="tableCell" >${houseConsignmentRecord.ehlnrh}</td>
	               	   <td width="2%" align="center" class="tableCell" >${houseConsignmentRecord.ehavd}</td>
	               	   <td width="2%" align="center" class="tableCell" >${houseConsignmentRecord.ehpro}</td>
	               	   <td width="2%" align="center" class="tableCell" >${houseConsignmentRecord.ehtdn}</td>
		               <td nowrap width="2%" align="center" class="tableCell text12">
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
		               <td width="2%" align="right" class="tableCell" >${houseConsignmentRecord.ehvkb}</td>
		               <td width="2%" align="right" class="tableCell" >${houseConsignmentRecord.ehntk}</td>
		               <td width="2%" align="right" class="tableCell" >${houseConsignmentRecord.ehdkh}</td>
		               <td width="2%" align="right" class="tableCell" >${houseConsignmentRecord.ehdkht}</td>
		               <td width="2%" align="right" class="tableCell" >${houseConsignmentRecord.ehprt}</td>
		               <td width="2%" align="right" class="tableCell" >${houseConsignmentRecord.ehuprt}</td>
		               
		               <td width="2%" align="right" class="tableCell" >${houseConsignmentRecord.ehetypt}</td>
		               <td width="2%" align="right" class="tableCell" >${houseConsignmentRecord.eheid}</td>
		               
		               
		               <td width="2%" align="center" class="tableCell" >${houseConsignmentRecord.ehnam}&nbsp;-&nbsp;${houseConsignmentRecord.ehpsm}&nbsp;${houseConsignmentRecord.ehlkm}</td>
		               <td width="2%" align="center" class="tableCell" >${houseConsignmentRecord.ehnas}&nbsp;-&nbsp;${houseConsignmentRecord.ehpss}&nbsp;${houseConsignmentRecord.ehlks}</td>
		               <td width="2%" class="tableCell" ><c:if test="${houseConsignmentRecord.ehdts > 0}">${houseConsignmentRecord.ehdts}-${houseConsignmentRecord.ehtms}</c:if></td>
		               
		               
		               <td width="2%" class="tableCell" ><font style="font-size:11px;">${houseConsignmentRecord.ehmid}</font></td>
		               		
		               <td width="2%" class="tableCell" title="check status in toll.no">
		               		<a style="display: block; width: 100%; height: 100%; cursor:pointer" class="uuidLink text12SkyBlue" id="${houseConsignmentRecord.ehuuid}">
								${houseConsignmentRecord.ehuuid}
							</a>  
		               </td>
		               <td width="2%" align="center" class="tableCell" >
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
		               <td width="2%" align="center" class="tableCell" >
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

		               <td width="2%" class="tableCell" align="center"> 
		               		  	
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
	               	   <td width="2%" class="tableCell" align="center">
	               	   		<c:if test="${houseConsignmentRecord.ehst == 'M' || empty houseConsignmentRecord.ehst}">
	               	   			<%-- We can only CANCEL (S) internally if the emmid and emuuid are gone since we DELETED first from Tollv.(if we even got that far at some point...) --%>
	               	   			<c:if test="${empty houseConsignmentRecord.ehmid && empty houseConsignmentRecord.ehuuid}">
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
 
 	
 
	