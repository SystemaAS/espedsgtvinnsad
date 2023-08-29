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
	<table style="width:90%;"  class="text11" cellspacing="0" border="0" cellpadding="0">
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
					<td title="${model.record.emlnrm}" width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;Master
						</font>
						<img src="resources/images/update.gif" border="0" alt="edit">
						<c:if test="${model.record.emlnrm > 0}">
							<font class="text14MediumBlue">&nbsp;${model.record.emlnrm}</font>
						</c:if>
						
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
	<form name="manifestForm" id="manifestForm" action="tvinnsadmanifest_edit.do" method="post">
			<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
			<input type="hidden" name="updateId" id="updateId" value=""> <%-- this value is set in AJAX in order to know if the SAVE = ADD or UPDATE --%>
			<input type="hidden" name="actionU" id="actionU" value="doUpdate">
			<%--
			<c:if test="${not empty model.record.efuuid}">
				<input type="hidden" name="efuuid" id=efuuid value="${model.record.efuuid}">
				<input type="hidden" name="efavd" id=efavd value="${model.record.efavd}">
				<input type="hidden" name="efsg" id=efsg value="${model.record.efsg}">
				<input type="hidden" name="efpro" id=efpro value="${model.record.efpro}">
				<input type="hidden" name="efst" id=efst value="${model.record.efst}">	
				<input type="hidden" name="efst2" id=efst2 value="${model.record.efst2}">		
			</c:if>
			 --%>
			 
	<table style="width:90%;" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
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
			 
			 
		<tr>
			<td colspan="3" class="text14" valign="top">
				<table style="width:90%" align="left" border="0" cellspacing="1" cellpadding="0">
					<tr height="4"><td>&nbsp;</td></tr>
					<tr>	
						<td nowrap colspan="10" class="text14 formFrame" >
		               		MRN-Api:&nbsp;<a class="uuidLinkParent text14SkyBlue" id="${model.record.emmid}">${model.record.emmid}</a>&nbsp;&nbsp;&nbsp;
		               		Id:&nbsp;<a class="uuidLinkParent text14SkyBlue" id="${model.record.emuuid}">${model.record.emuuid}</a>
	               		</td>
	         
					</tr>
					<tr height="1"><td>&nbsp;</td></tr>
					<tr>
						<td class="text14">&nbsp;<span title="emavd">Avd</span></td>
						<td class="text14">&nbsp;<span title="empro">Tur</span></td>
						<td class="text14">&nbsp;<span title="emvkb">Bruttovekt</span></td>
						<td class="text14">&nbsp;<span title="emvkb">Container</span></td>
						<td class="text14">&nbsp;<span title="emdkm">Dok.nr</span></td>
						<td class="text14">&nbsp;<span title="emdkmt">Dok.type</span></td>
						<td class="text14">&nbsp;<span title="emst">St.</span></td>
						<td class="text14">&nbsp;<span title="emst2">St.2</span></td>
						<td class="text14">&nbsp;<span title="emst3">St.3</span></td>
						<td class="text14">&nbsp;<span title="emst3">Reg.dato</span></td>
						<td class="text14">&nbsp;<span title="emst3">Send.dato</span></td>
						<td class="text14">&nbsp;<span >&nbsp;&nbsp;</span></td>
						<td class="text14">&nbsp;<span title="emc1ty">Cont.1 size</span></td>
						<td class="text14">&nbsp;<span title="emc1ps">Cont.1 pack.</span></td>
						<td class="text14">&nbsp;<span title="emc1ss">Cont.1 supp.type</span></td>
						<td class="text14">&nbsp;<span title="emc1id">Cont.1 id</span></td>
						
						<td class="text14">&nbsp;<span title="emc1ty">Cont.2 size</span></td>
						<td class="text14">&nbsp;<span title="emc1ps">Cont.2 pack.</span></td>
						<td class="text14">&nbsp;<span title="emc1ss">Cont.2 supp.type</span></td>
						<td class="text14">&nbsp;<span title="emc1id">Cont.2 id</span></td>
						
					</tr>
					<tr>	
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emavd" id="emavd" size="5" maxlength="4" value="${model.record.emavd}">									
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="empro" id="empro" size="10" maxlength="8" value="${model.record.empro}">									
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emvkb" id="emvkb" size="10" maxlength="9" value="${model.record.emvkb}">									
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emcn" id="emcn" size="2" maxlength="1" value="${model.record.emcn}">									
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emdkm" id="emdkm" size="25" maxlength="50" value="${model.record.emdkm}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emdkmt" id="emdkmt" size="5" maxlength="4" value="${model.record.emdkmt}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emst" id="emst" size="2" maxlength="1" value="${model.record.emst}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emst2" id="emst2" size="2" maxlength="1" value="${model.record.emst2}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emst3" id="emst3" size="2" maxlength="1" value="${model.record.emst3}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emdtr" id="emdtr" size="10" maxlength="8" value="${model.record.emdtr}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emdtin" id="emdtin" size="10" maxlength="8" value="${model.record.emdtin}">		
						</td>
						
						<td class="text14">&nbsp;&nbsp;</td>
						
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emc1ty" id="emc1ty" size="3" maxlength="2" value="${model.record.emc1ty}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emc1ps" id="emc1ps" size="2" maxlength="1" value="${model.record.emc1ps}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emc1ss" id="emc1ss" size="2" maxlength="1" value="${model.record.emc1ss}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emc1id" id="emc1id" size="18" maxlength="17" value="${model.record.emc1id}">		
						</td>
						
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emc2ty" id="emc2ty" size="3" maxlength="2" value="${model.record.emc2ty}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emc2ps" id="emc2ps" size="2" maxlength="1" value="${model.record.emc2ps}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emc2ss" id="emc2ss" size="2" maxlength="1" value="${model.record.emc2ss}">		
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emc2id" id="emc2id" size="18" maxlength="17" value="${model.record.emc2id}">		
						</td>
					</tr>
					<tr height="2"><td>&nbsp;</td></tr>
					<tr>
						<td colspan="3" class="text14">&nbsp;<span title="emsdlt">Lastested</span></td>
						<td class="text14">&nbsp;<span title="emlkl">Land</span></td>
						<td colspan="2" class="text14">&nbsp;<span title="emsdut">Lossested</span></td>
						<td class="text14">&nbsp;<span title="emlku">Land</span></td>
					</tr>
					<tr>	
						<td colspan="3" class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emsdlt" id="emsdlt" size="25" maxlength="30" value="${model.record.emsdlt}">								
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emlkl" id="emlkl" size="4" maxlength="2" value="${model.record.emlkl}">												
						</td>
						<td colspan="2" class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emsdut" id="emsdut" size="25" maxlength="30" value="${model.record.emsdut}">											
						</td>
						<td class="text14">
							<input readonly type="text" class="inputTextMediumBlue" name="emlku" id="emlku" size="4" maxlength="2" value="${model.record.emlku}">												
						</td>
						
					</tr>		
				</table>
			</td>
		</tr>
		
		<tr height="4"><td>&nbsp;</td></tr> 
 		<tr>
			<td style="width:33%" class="text14" valign="top">
				<table style="width:70%" align="left" border="0" cellspacing="1" cellpadding="0">
				 	<tr >
					 	<td >
						<table class="formFrameHeader" style="width:100%;" border="0" cellspacing="1" cellpadding="0">
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
									<td class="text14">&nbsp;<span title="emnas">Navn</span></td>
									<td class="text14">&nbsp;<span title="emrgs">Org.nr /EORI</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="emnas" id="emnas" size="25" maxlength="30" value="${model.record.emnas}"></td>
									<td class="text14"><input readonly type="text" class="inputTextReadOnly" name="emrgs" id="emrgs" size="20" maxlength="17" value="${model.record.emnas}"></td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="empss">Sted</span></td>
									<td class="text14">&nbsp;<span title="emlks">Landkode</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="empss" id="empss" size="25" maxlength="24" value="${model.record.empss}"></td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="emlks" id="emlks" size="4" maxlength="2" value="${model.record.emlks}"></td>
									
				 				</tr>
				 				<tr >
									<td class="text14">&nbsp;<span title="emad1s">Adress</span></td>
									<td class="text14">&nbsp;<span title="empns">Postnr</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="emad1s" id="emad1s" size="25" maxlength="30" value="${model.record.emad1s}"></td>
									<td class="text14"><input readonly type="text" class="inputTextReadOnly" name="empns" id="empns" size="12" maxlength="9" value="${model.record.empns}"></td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="emems">E-post</span></td>
									<td class="text14">&nbsp;<span title="emems">Telefon</span></td>
									
				 				</tr>
				 				<tr >
									<c:choose>
				 					<c:when test="${model.record.ememst == 'EM'}">
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="emems" id="emems" size="35" maxlength="50" value="${model.record.emems}"></td>
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="empty" id="empty" size="35" maxlength="50" value=""></td>
				 					</c:when>
				 					<c:otherwise>
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="empty" id="empty" size="35" maxlength="50" value=""></td>
										<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="emems" id="emems" size="35" maxlength="50" value="${model.record.emems}"></td>
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
            
           	<td style="width:33%" class="text14" valign="top">
				<table style="width:70%" align="left" border="0" cellspacing="1" cellpadding="0">
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
									<td class="text14">&nbsp;<span title="emnam">Navn</span></td>
									<td class="text14">&nbsp;<span title="emrgm">Org.nr /EORI</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="emnam" id="emnam" size="25" maxlength="30" value="${model.record.emnam}"></td>
									<td class="text14"><input readonly type="text" class="inputTextReadOnly" name="emrgm" id="emrgm" size="20" maxlength="17" value="${model.record.emnam}"></td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="empsm">Sted</span></td>
									<td class="text14">&nbsp;<span title="emlkm">Landkode</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="empsm" id="empsm" size="25" maxlength="24" value="${model.record.empsm}"></td>
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="emlkm" id="emlkm" size="4" maxlength="2" value="${model.record.emlkm}"></td>
									
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="emad1m">Adress</span></td>
									<td class="text14">&nbsp;<span title="empnm">Postnr</span></td>
									
				 				</tr>
				 				<tr >
									<td class="text14"><input type="text" class="inputTextMediumBlue" name="emad1m" id="emad1m" size="25" maxlength="30" value="${model.record.emad1m}"></td>
									<td class="text14"><input readonly type="text" class="inputTextReadOnly" name="empnm" id="empnm" size="12" maxlength="9" value="${model.record.empnm}"></td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="ememm">E-post</span></td>
									<td class="text14">&nbsp;<span title="ememm">Telefon</span></td>
									
				 				</tr>
				 				<tr >
									<c:choose>
				 					<c:when test="${model.record.ememmt == 'EM'}">
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="ememm" id="ememm" size="35" maxlength="50" value="${model.record.ememm}"></td>
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="empty" id="empty" size="35" maxlength="50" value=""></td>
				 					</c:when>
				 					<c:otherwise>
				 						<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="empty" id="empty" size="35" maxlength="50" value=""></td>
										<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="ememm" id="ememm" size="35" maxlength="50" value="${model.record.ememm}"></td>
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
		<td>		
		<table style="width:90%" border="0" >
	    	<%-- separator --%>
	        <tr height="2"><td>&nbsp;</td></tr>  
			<tr>
				<td>
				<table style="width:100%" id="containerdatatableTable" cellspacing="2" align="left" >
				<tr>
				<td class="text11">
							
				<table id="mainList" class="display compact cell-border" >
					<thead>
					<tr class="tableHeaderField" height="20" >
                    	<th width="2%" class="tableHeaderFieldFirst" ><img title="Update" style="vertical-align:middle;" src="resources/images/update.gif" border="0" alt="edit"></th>
                    	<th width="2%" class="tableHeaderField" >Lnr</th>
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
                		<th title="S=SUBMITTED,R=REOPENED/DRAFT,D=SLETTET,C=COMPLETED" width="2%" class="tableHeaderField" >Manif.st</th>
                		<th width="2%" class="tableHeaderField" title="Fjerner manifest fra Tollvesenet" >Slett</th>
                		<th width="2%" class="tableHeaderField" title="Fjerner manifest lokalt (SYSPED)">Kans.</th>
                		</tr>
                	</thead>
                	<tbody> 
                	<c:forEach items="${model.record.listHouses}" var="houseConsignmentRecord" varStatus="counter">    
		              <c:choose> 
		              	  <%-- if the manifest is correct with all cargo lines OR the manifest has been SUBMITTED(S) or DELETED(D) don´t show it as a warning-line --%>	   
			              <c:when test="${XhouseConsignmentRecord.own_valid > 0 || XhouseConsignmentRecord.efst2 == 'S' || XhouseConsignmentRecord.efst2 == 'D' }">
			              	<tr class="tableRow" height="20" >
			          	  </c:when>
			          	  <c:otherwise>
			          	  	<%-- <tr class="tableRow" style="background-color: #FEEFB3;color:#9F6000;" height="20" >  --%>
			          	  	<tr class="tableRow" height="20" >
			          	  </c:otherwise>
		          	  </c:choose>	
		          
		          	   <td width="2%" class="tableCellFirst" align="center">
		          	   		<a style="display: block; width: 100%; height: 100%;"  href="tvinnsaddigitollv2_edit_house.do?action=doFind&ehlnrt=${houseConsignmentRecord.ehlnrt}&ehlnrm=${houseConsignmentRecord.ehlnrm}&ehlnrh=${houseConsignmentRecord.ehlnrh}" onClick="setBlockUI();">
               					<c:choose>
		               				<c:when test="${XhouseConsignmentRecord.own_editable > 0}">
		               					<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="edit">
		               				</c:when>
		               				<c:otherwise>
		               					<img title="Read" style="vertical-align:bottom;" src="resources/images/eye.png" height="18px" width="18px" border="0" alt="read">
		               				</c:otherwise>
	               				</c:choose>
               				</a>
               				
	               	   </td>
	               	   <td width="2%" align="center" class="tableCell" >${houseConsignmentRecord.ehlnrh}</td>
		               <td width="2%" align="center" class="tableCell" >
		               	  <c:choose>
		               		<c:when test="${houseConsignmentRecord.ehst == 'S'}">
		               			<font class="inputFormSubmit isa_error">KANSELLERT</font>
		               		</c:when>
		               		<c:otherwise>
		               			${houseConsignmentRecord.ehst}
		               		</c:otherwise>
		               	   </c:choose>
		              	</td>
		               <td align="right" class="tableCell" >${houseConsignmentRecord.ehvkb}</td>
		               <td align="right" class="tableCell" >${houseConsignmentRecord.ehntk}</td>
		               <td align="right" class="tableCell" >${houseConsignmentRecord.ehdkh}</td>
		               <td align="right" class="tableCell" >${houseConsignmentRecord.ehdkht}</td>
		               <td align="right" class="tableCell" >${houseConsignmentRecord.ehprt}</td>
		               <td align="right" class="tableCell" >${houseConsignmentRecord.ehuprt}</td>
		               
		               <td align="right" class="tableCell" >${houseConsignmentRecord.ehetypt}</td>
		               <td align="right" class="tableCell" >${houseConsignmentRecord.eheid}</td>
		               
		               
		               <td align="center" class="tableCell" >${houseConsignmentRecord.ehnam}&nbsp;-&nbsp;${houseConsignmentRecord.ehpsm}&nbsp;${houseConsignmentRecord.ehlkm}</td>
		               <td align="center" class="tableCell" >${houseConsignmentRecord.ehnas}&nbsp;-&nbsp;${houseConsignmentRecord.ehpss}&nbsp;${houseConsignmentRecord.ehlks}</td>
		               <td class="tableCell" ><c:if test="${houseConsignmentRecord.ehdts > 0}">${houseConsignmentRecord.ehdts}-${houseConsignmentRecord.ehtms}</c:if></td>
		               
		               
		               <td width="2%" class="tableCell" ><font style="font-size:11px;">${houseConsignmentRecord.ehmid}</font></td>
		               		
		               <td class="tableCell" title="check status in toll.no">
		               		<a style="display: block; width: 100%; height: 100%; cursor:pointer" class="uuidLink text12SkyBlue" id="${houseConsignmentRecord.ehuuid}">
								${houseConsignmentRecord.ehuuid}
							</a>  
		               </td>
		               
		               <td align="center" class="tableCell" >
		               		<c:choose>
		               		<c:when test="${houseConsignmentRecord.ehst2 == 'S' || houseConsignmentRecord.ehst2 == 'R' || houseConsignmentRecord.ehst2 == 'D' || houseConsignmentRecord.ehst2 == 'C'}">
		               			<c:if test="${houseConsignmentRecord.ehst2 == 'S'}">
		               				<img src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
		               				<span title="S" >SUBMITTED</span>
		               			</c:if>
		               			<c:if test="${houseConsignmentRecord.ehst2 == 'R'}">
		               				<span title="R" >REOPENED/DRAFT</span>
		               			</c:if>
		               			<c:if test="${houseConsignmentRecord.ehst2 == 'D'}">
		               				<font title="D" color="red">SLETTET</font>
		               			</c:if>
		               			<c:if test="${houseConsignmentRecord.ehst2 == 'C'}">
		               				<img style="vertical-align:middle;" title="Completed tolldekl at toll.no" src="resources/images/complete-icon.png" width="14px" height="12px" border="0" alt="completion">
		               				<font title="C" color="green">COMPLETED</font>
		               			</c:if>
		               			
		               		</c:when>
		               		<c:otherwise>
		               			${houseConsignmentRecord.ehst2}
		               		</c:otherwise>
		               		</c:choose>
		               </td>

		               <td width="2%" class="tableCell" align="center"> 
		               		  		
				   				<c:if test="${XhouseConsignmentRecord.own_editable > 0}">
		              				<a style="display: block; width: 100%; height: 100%;" class="removeLink" id="removeLink${counter.count}" runat="server" href="#">
										<img src="resources/images/delete.gif" border="0" alt="remove">
									</a>
									<div style="display: none;" class="clazz_dialog" id="dialogUpdateStatus${counter.count}" title="Dialog">
										<form action="tvinnsadmanifest_edit_delete.do" name="updateStatusForm${counter.count}" id="updateStatusForm${counter.count}" method="post">
										 	<input type="hidden" name="currentUuid${counter.count}" id="currentUuid${counter.count}" value="${XhouseConsignmentRecord.efuuid}">
										 	<input type="hidden" name="selectedStatus${counter.count}" id="selectedStatus${counter.count}" value="D">
										 	<input type="hidden" name="selectedPro${counter.count}" id="selectedPro${counter.count}" value="${XhouseConsignmentRecord.efpro}">
											<p class="text14" >Er du sikker på at du vil slette Turnr. <b>${Xrecord.efpro}</b> fra <b>Tollvesenet</b> ?</p>
											
										</form>
									</div>
	              				</c:if>
              				
	               	   </td>
	               	   <td width="2%" class="tableCell" align="center">
	               	   		<c:if test="${XhouseConsignmentRecord.efst == 'M' || empty XhouseConsignmentRecord.efst}">   		
				   				<a style="display: block; width: 100%; height: 100%;" class="cancelLink" id="cancelLink${counter.count}" runat="server" href="#">
									<img src="resources/images/remove.png" width="16" height="16" border="0" alt="remove">
								</a> 
								<div id="dialogUpdateInternalStatus${counter.count}" class="clazz_dialog" title="Dialog">
									<form action="tvinnsadmanifest_updateInternalStatus.do" name="updateInternalStatusForm${counter.count}" id="updateInternalStatusForm${counter.count}" method="post">
									 	<input type="hidden" name="currentUuid${counter.count}" id="currentUuid${counter.count}" value="${XhouseConsignmentRecord.efuuid}">
									 	<input type="hidden" name="currentSign${counter.count}" id="currentSign${counter.count}" value="${XhouseConsignmentRecord.efsg}">
									 	<input type="hidden" name="selectedStatus${counter.count}" id="selectedStatus${counter.count}" value="S">
									 	<p class="text14" >Er du sikker på at du vil kansellere Turnr. <b>${XhouseConsignmentRecord.efpro}</b> fra <b>SYSPED</b> ?</p>
											
									</form>
								</div>
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
 
 	
 
	