
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmanifest_edit_cargolines.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
<table style="width:100%;"  cellspacing="0" border="0" cellpadding="0">

 <tr>
 <td>	
	<%-- tab container component --%>
	<table style="width:100%;"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 id="alinkManifestList" style="display:block;" href="tvinnsadmanifest.do?action=doFind&avd=${model.efavd}&sign=${model.efsg}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.manifest.list.tab"/></font>
					<img src="resources/images/list.gif" border="0" alt="general list">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
	
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 id="alinkHeader" style="display:block;" href="tvinnsadmanifest_edit.do?action=doFetch&efuuid=${model.efuuid}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.manifest.created.header.tab"/>
					</font>
					<font class="text14MediumBlue">[${model.efpro}]</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
					<font class="tabLink">
						&nbsp;<spring:message code="systema.tvinn.sad.manifest.createnew.last.tab"/>
					</font>
					<img src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
			</td>
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
		</tr>
	</table>
	</td>
</tr>

 
 <tr>
 	<td>
	<%-- --------------------------- --%>	
 	<%-- tab area container PRIMARY  --%>
	<%-- --------------------------- --%>
			
	<table style="width:100%;" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<tr height="10"><td colspan="10">&nbsp;</td></tr>
 		
		<%-- --------------- --%>
		<%-- CONTENT --%>
		<%-- --------------- --%>
		<tr>
		<td >
		<table align="center" style="width:100%;" border="0" cellspacing="1" cellpadding="0">
			
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
		<td>		
		<table style="width:95%;" cellspacing="0" border="0" cellpadding="0">
	    	<%-- separator --%>
	        <tr height="1"><td></td></tr> 
			<tr>
				<td>
				<table id="containerdatatableTable" width="100%" cellspacing="2" align="left" >
				<tr>
				<td class="text11">
							
				<table id="mainList" class="display compact cell-border" >
					<thead>
					<tr class="tableHeaderField" height="20" >
                    	<th width="2%" class="tableHeaderFieldFirst" ><spring:message code="systema.tvinn.sad.update"/></th>
                		<th class="tableHeaderField" >Avd</th>
                		<th class="tableHeaderField" >Oppdrag</th>
                		<th title="S=UPDATED and RELEASED by USER, O=OK" class="tableHeaderField" >St</th>
                		<th class="tableHeaderField" >Eksp.type</th>
                		<th class="tableHeaderField" >Eksp.id</th>
                		<th class="tableHeaderField" style="background-color:lightyellow;">MRN</th>
                		<th class="tableHeaderField" style="background-color:lightyellow;">Avs</th>
                		<th class="tableHeaderField" style="background-color:lightyellow;">Mottak.</th>
                		<th class="tableHeaderField" >Lastested</th>
                		<th class="tableHeaderField" >Lossested</th>
                		
                		<th class="tableHeaderField" >Varebesk.</th>
                		<th class="tableHeaderField" >Kolli</th>
                		<th class="tableHeaderField" >B.vekt</th>
                		
                		<th class="tableHeaderField" >Sert</th>
                		<th class="tableHeaderField" >Arkiv</th>
                		<th class="tableHeaderField" >Slett</th>
                	</tr>
                	</thead>
                	<tbody> 
		           	<c:forEach items="${model.list}" var="record" varStatus="counter">
		           	 <c:choose>    
			              <c:when test="${record.clst == 'O'}">
			              	<tr class="tableRow" height="20" >
			          	  </c:when>
			          	  <c:otherwise>
			          	  	<tr class="tableRow" style="background-color: #FEEFB3;color: #9F6000;" height="20" >
			          	  </c:otherwise>
		          	  </c:choose>	
		          	   <td width="2%" class="tableCellFirst" align="center" >
			          	   <a style="display: block; width: 100%; height: 100%;" id="clpro_${record.clpro}@cltdn_${record.cltdn}@clavd_${record.clavd}" href="#" onClick="getItemData(this);">
				          	   <c:choose>
				          	   <c:when test="${headerRecord.own_editable > 0}">
				               		<img src="resources/images/update.gif" border="0" alt="edit">
		               		   </c:when>
		               		   <c:otherwise>
		            					<img title="Read" style="vertical-align:bottom;" src="resources/images/eye.png" height="18px" width="18px" border="0" alt="read">
		            				</c:otherwise>
		            				</c:choose>
	               		   </a>
	               	   </td>
		               <td width="2%" class="tableCell" <c:if test="${record.clst != 'O'}">style="color: #9F6000;" </c:if> align="center" >${record.clavd}</td>
		               <td width="2%" class="tableCell" <c:if test="${record.clst != 'O'}">style="color: #9F6000;" </c:if> align="center" >${record.cltdn}</td>
		               <td width="2%" class="tableCell" <c:if test="${record.clst != 'O'}">style="color: #9F6000;" </c:if> align="center" >
	               			<c:choose>
	               				<c:when test="${record.clst == 'O'}">
	               					<img title="${record.clst}" src="resources/images/bulletGreen.png" width="12" height="12" border="0" >
	               				</c:when>
	               				<c:otherwise>
	               					<img title="${record.clst}" src="resources/images/bulletRed.png" width="12" height="12" border="0" >
	               				</c:otherwise>
               				</c:choose>
		               </td>
		               <td width="2%" class="tableCell" <c:if test="${record.clst != 'O'}">style="color: #9F6000;" </c:if> align="center" >${record.cletyp}&nbsp;${record.cletypt}</td>
		               <td width="2%" class="tableCell" <c:if test="${record.clst != 'O'}">style="color: #9F6000;" </c:if> align="center" >${record.cleid}</td>
		               
		               <td width="2%" class="tableCell" <c:if test="${record.clst != 'O'}">style="color: #9F6000;" </c:if> align="center" >${record.cltrnr}</td>
		               <td width="2%" class="tableCell" <c:if test="${record.clst != 'O'}">style="color: #9F6000;" </c:if> align="center" >${record.clnas}</td>
		               <td width="2%" class="tableCell" <c:if test="${record.clst != 'O'}">style="color: #9F6000;" </c:if> align="center" >${record.clnak}</td>
		               
		               <td width="2%" class="tableCell" <c:if test="${record.clst != 'O'}">style="color: #9F6000;" </c:if> align="left" >${record.clsdft}</td>
		               <td width="2%" class="tableCell" <c:if test="${record.clst != 'O'}">style="color: #9F6000;" </c:if> align="left" >${record.clsdtt}</td>
		               
		               
		               <td width="2%" class="tableCell" <c:if test="${record.clst != 'O'}">style="color: #9F6000;" </c:if> align="left" >${record.clvt}</td>
		               <td width="2%" class="tableCell" <c:if test="${record.clst != 'O'}">style="color: #9F6000;" </c:if> align="right" >${record.clntk}</td>
		               <td width="2%" class="tableCell" <c:if test="${record.clst != 'O'}">style="color: #9F6000;" </c:if> align="right" >${record.clvkb}</td>
		               
		               
		               <td width="2%" class="tableCell" <c:if test="${record.clst != 'O'}">style="color: #9F6000;" </c:if> align="center" >${record.cleser}</td>
		               <td width="2%" align="center" class="text14 tableCellGray">
	            		   		<input class="inputFormSubmit11Slim" type="button" value="Upload" name="uplButton${counter.count}" onClick="window.open('tvinnsadmanifest_childwindow_uploadFile.do?action=doInit&wsavd=${record.clavd}&wsopd=${record.cltdn}','cargoLineListFileUpload','top=300px,left=800px,height=250px,width=330px,scrollbars=no,status=no,location=no')">	 
	            		   </td>
		               <td width="2%" class="tableCell" align="center" >
		               		<c:if test="${headerRecord.own_editable > 0}">
			               		<a style="display: block; width: 100%; height: 100%;" class="removeLink" id="removeLink${counter.count}" runat="server" href="#">
									<img src="resources/images/delete.gif" border="0" alt="remove">
								</a>
								<div style="display: none;" class="clazz_dialog" id="dialogUpdateStatus${counter.count}" title="Dialog">
									<form action="tvinnsadmanifest_edit_cargolines_delete.do" name="updateStatusForm${counter.count}" id="updateStatusForm${counter.count}" method="post">
									 	<input type="hidden" name="currentClpro${counter.count}" id="currentClpro${counter.count}" value="${record.clpro}">
									 	<input type="hidden" name="currentCltdn${counter.count}" id="currentCltdn${counter.count}" value="${record.cltdn}">
									 	<input type="hidden" name="currentClavd${counter.count}" id="currentClavd${counter.count}" value="${record.clavd}">
									 	<input type="hidden" name="currentEuuid${counter.count}" id="currentEuuid${counter.count}" value="${model.efuuid}">
									 	<input type="hidden" name="currentEfsg${counter.count}" id="currentEfsg${counter.count}" value="${model.efsg}">
									 	<p class="text14" >Er du sikker på at du vil slette denne?</p>
										<p class="text14"> Tekst </p>
										<input type="text" class="inputText" name="currentText${counter.count}" id="currentText${counter.count}" size="45" maxlength="70" value=''>&nbsp;</td>
										
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
 	<tr>
 	<td>
 		<c:if test="${headerRecord.own_editable > 0}">
	 		<input class="inputFormSubmitStd" type="button" name="newButton" id="newButton" value='Lage ny'>
			<input class="inputFormSubmitBlue" type="button" name="cnButton" id="cnButton" value='Plukke oppdrag'>
		</c:if>
	</td>
	</tr>	
	<tr>
		<td class="text14" valign="top">
			<form name="cargoLineForm" id="cargoLineForm" action="tvinnsadmanifest_edit_cargolines.do" method="post">
			<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
			<input type="hidden" name="efavd" id="efavd" value="${model.efavd}">
			<input type="hidden" name="efsg" id="efsg" value="${model.efsg}">
			<input type="hidden" name="efpro" id="efpro" value="${model.efpro}">
			<input type="hidden" name="efuuid" id="efuuid" value="${model.efuuid}">
			<input type="hidden" name="action" id="action" value="doUpdate">
			<input type="hidden" name="clpro" id="clpro" value="${model.record.clpro}">
			<input type="hidden" name="clavd" id="clavd" value="${model.record.clavd}">
			<input type="hidden" name="clst" id="clst" value="O"> <%-- O=OK always  --%>
			<%--sets in jquery on dropdown event --%>
			<input type="hidden" name="cletypt" id="cletypt" value="${model.record.cletypt}">
			<table style="width:95%" align="left" border="0" cellspacing="1" cellpadding="0">
			 	<tr >
				 	<td >
					<table style="width:100%" class="formFrameHeader" border="0" cellspacing="1" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14White">&nbsp;&nbsp;Last&nbsp;&nbsp;-
				 				&nbsp;&nbsp;Turnr:&nbsp;${model.efpro}
	 							&nbsp;&nbsp;Avd:&nbsp;${model.efavd}&nbsp;&nbsp;
	 							&nbsp;&nbsp;Manifestid:&nbsp;${model.efuuid}
	 							
	 							
				 			</td>
				 			<td align="right" class="text14White" >
				 				Status:&nbsp;${headerRecord.efst}
				 				&nbsp;&nbsp;&nbsp;<b>Manifest status:&nbsp;</b>
		 						<c:choose>
		 						<c:when test="${headerRecord.efst2 == 'S' || headerRecord.efst2 == 'R' || headerRecord.efst2 == 'D'}">
		 							<c:if test="${headerRecord.efst2 == 'S'}">
		 								<img src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
		 								<font style="color:#FFFFCC;">SUBMITTED</font>
		 							</c:if>
		 							<c:if test="${headerRecord.efst2 == 'D'}">
		 								<img src="resources/images/bulletRed.png" width="10" height="10" border="0" >
		 								<font style="color:red;">SLETTET</font>
		 							</c:if>
		 							<c:if test="${headerRecord.efst2 == 'R'}">
		 								<font style="color:#FFFFFF;">REOPENED/DRAFT</font>
		 							</c:if>
		 						</c:when>
		 						<c:otherwise>
		 							<font style="color:#606060;">${headerRecord.efst2}</font>
		 						</c:otherwise>
		 						</c:choose>
				 				
				 			</td>
		 				</tr>
		            </table>
		            </td>
	            </tr>
	            <tr >
				 	<td>
					<table style="width:100%" class="formFrame" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td valign="top">	
						<table width="95%">
						<tr >
		 					<td class="text14">&nbsp;<span title="cltdn">Oppdrag</span></td>
							<td class="text14">&nbsp;<span title="clpr">Prosedyre</span><font class="text16RedBold" >*</font></td>
						</tr>
						
		 				<tr >
		 					<td class="text14"><input type="text" class="inputTextReadOnly" style="color:yellow;background-color:#BBBBBB;" name="cltdn" id="cltdn" size="8" maxlength="7" value="${model.record.cltdn}"></td>
				 			<td>
								<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="clpr" id="clpr" >
			 						<option value="">-select-</option>
					 				  	<c:forEach var="record" items="${model.prTypeList}" >
					 				  		<%--filter the goodsregistrering (03) so far --%>
					 				  		<c:if test="${record.kfkod != '03'}">
				                       	 		<option title="${record.kftxt}" value="${record.kfkod}" <c:if test="${model.record.clpr == record.kfkod}"> selected </c:if> >${record.kfkod}&nbsp;${record.kftxt}</option>
				                       	 	</c:if>
										</c:forEach>
								</select>
							</td>
		 					
						</tr>
						
						<tr>
							<td colspan="2" width="50%" valign="top">
							<table id="tblDirektfortolling" width="100%" class="tableBorderWithRoundCorners" border="0" cellspacing="1" cellpadding="0">
				 			<tr >
								<td class="text16"><b>&nbsp;Direktfortolling</b></td>
							<tr >
				 			<tr>
				 				<td class="text14">&nbsp;<span title="clrg">Deklarantnr.</span></td>
								<td class="text14">&nbsp;<span title="cl0068a">Dato</span></td>
								<td class="text14">&nbsp;<span title="cl0068b">Sekvensnr.</span></td>
							</tr>
							<tr>
								<td class="text14"><input type="text" class="inputTextMediumBlue toggleDirektfortolling" name="clrg" id="clrg" size="12" maxlength="11" value="${model.record.clrg}"></td>
				 				<td class="text14"><input type="text" class="inputTextMediumBlue toggleDirektfortolling" name="cl0068a" id="cl0068a" size="7" maxlength="6" value='<c:if test="${model.record.cl0068a!='0'}">${model.record.cl0068a}</c:if>'></td>
				 				<td class="text14"><input type="text" class="inputTextMediumBlue toggleDirektfortolling" name="cl0068b" id="cl0068b" size="7" maxlength="6" value='<c:if test="${model.record.cl0068b!='0'}">${model.record.cl0068b}</c:if>'></td>
							</tr>
							<tr >
								<td class="text14">&nbsp;<span title="cletyp">Eksporttype</span></td>
								<td colspan="2" class="text14">&nbsp;<span title="cleid">Eksp.id</span></td>							
							</tr>
							
							<tr>
								<td class="text14">
					 				<select class="inputTextMediumBlue toggleDirektfortolling" name="cletyp" id="cletyp" >
				 						<option value="">-select-</option>
					 				  	<c:forEach var="record" items="${model.etTypeList}" >
				                       	 	<option title="${record.kftxt}" value="${record.kfkod}" <c:if test="${model.record.cletyp == record.kfkod}"> selected </c:if> >${record.kfkod}&nbsp;${record.kftxt}</option>
										</c:forEach>
									</select>
					 			</td>
					 			<td colspan="2" class="text14"><input type="text" class="inputTextMediumBlue toggleDirektfortolling" name="cleid" id="cleid" size="20" maxlength="18" value="${model.record.cleid}"></td>
					 								 			
							</tr>
							<tr height="5"><td></td></tr>
							</table>
							</td>
							
							<td width="50%" valign="top" >
							<table id="tblTransit" width="100%" class="tableBorderWithRoundCorners" border="0" cellspacing="1" cellpadding="0">
				 			<tr >
								<td class="text16"><b>&nbsp;Transitering</b></td>
							<tr >
				 			<tr >
				 				<td class="text14">&nbsp;<span title="cltrnr">MRNnr.</span></td>
								<td class="text14">&nbsp;<span title="clnas">Avsender</span></td>
								<td class="text14">&nbsp;<span title="clnak">Mottaker</span></td>
					 		</tr>
					 		<tr>
								<td class="text14"><input type="text" class="inputTextMediumBlue toggleTransit" name="cltrnr" id="cltrnr" size="20" maxlength="18" value="${model.record.cltrnr}"></td>
					 			<td class="text14"><input type="text" class="inputTextMediumBlue toggleTransit" name="clnas" id="clnas" size="20" maxlength="30" value="${model.record.clnas}"></td>
					 			<td class="text14"><input type="text" class="inputTextMediumBlue toggleTransit" name="clnak" id="clnak" size="20" maxlength="30" value="${model.record.clnak}"></td>
					 		</tr>
					 		<tr height="51"><td></td></tr>
				 			</table>
				 			</td>	
						</tr>
						
						<tr>
							<td colspan="2" valign="top" >
							<table width="100%" class="tableBorderWithRoundCorners" style="background-color:#F2F5F0;" border="0" cellspacing="1" cellpadding="0">
				 			<tr >
								<td class="text16"><b>&nbsp;Varer</b></td>
							<tr >
				 			<tr>	
								<td class="text14">&nbsp;<span title="clvt">Varebeskrivelse</span><font class="text16RedBold" >*</font></td>
								<td class="text14">&nbsp;<span title="clntk">Antal Kolli</span><font class="text16RedBold" >*</font></td>
								<td class="text14">&nbsp;<span title="clvkb">Bruttovekt</span><font class="text16RedBold" >*</font></td>
								<td class="text14">&nbsp;<span title="cleser">Sertifisert</span></td>				
			 				</tr>
							
							<tr>	
								<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" type="text" class="inputTextMediumBlueMandatoryField" name="clvt" id="clvt" size="31" maxlength="30" value="${model.record.clvt}"></td>
					 			<td class="text14"><input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="clntk" id="clntk" size="8" maxlength="7" value="${model.record.clntk}"></td>
					 			<td class="text14"><input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="clvkb" id="clvkb" size="10" maxlength="9" value="${model.record.clvkb}"></td>
					 			<td class="text14">
					 				<select class="inputTextMediumBlue" name="cleser" id="cleser" >
				 						<option value="N">Nei</option>
		 		 				  		<option value="J">Ja</option>
									</se	lect>
					 			</td>	
			 				</tr>
		 					<tr height="5"><td></td></tr>
			 				</table>
		 					</td>
		 					
		 					
		 					<td valign="top">
							<table width="100%" class="tableBorderWithRoundCorners" style="background-color:#F2F5F0;" border="0" cellspacing="1" cellpadding="0">
			 				<tr >
								<td class="text16"><b>&nbsp;Laste / Losse</b></td>
							<tr >
							<tr>
								<td class="text14">&nbsp;<span title="clsdft/cllkf/clsdf">Lastes i</span><font class="text16RedBold" >*</font>
									
								</td>
								<td class="text14">&nbsp;<span title="clsdtt/cllkt/clsdt/">Lossested</span><font class="text16RedBold" >*</font>
									
								</td>
							</tr>
							<tr>
								<td class="text14">
								<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="clsdft" id="clsdft" size="20" maxlength="30" value="${model.record.clsdft}">
								<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  class="inputTextMediumBlueMandatoryField" name="cllkf" id="cllkf">
			 						<option value="">-velg-</option>
				 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				 				  		<option title="${country.ztxt}" value="${country.zkod}"<c:if test="${model.record.cllkf == country.zkod}"> selected </c:if> >${country.zkod}</option>
									</c:forEach>  
								</select>
								<a tabindex="-1" id="clsdfIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								</a>
								<%-- 
								<input type="text" class="inputTextMediumBlue" name="clsdf" id="clsdf" size="6" maxlength="5" value="${model.record.clsdf}">
								--%>
								</td>
								<td class="text14">
									<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="clsdtt" id="clsdtt" size="20" maxlength="30" value="${model.record.clsdtt}">
									<select class="inputTextMediumBlue" name="cllkt" id="cllkt">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					 				  		<option title="${country.ztxt}" value="${country.zkod}"<c:if test="${model.record.cllkt == country.zkod}"> selected </c:if> >${country.zkod}</option>
										</c:forEach>  
									</select>
									<a tabindex="-1" id="clsdtIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									<%--
									<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="clsdt" id="clsdt" size="6" maxlength="5" value="${model.record.clsdt}">
									 --%>
								</td>
							</tr>
							<tr height="5"><td></td></tr>
							</table>
							</td>
							
		 				</tr>
	 					</table>
	 					</td>
		 			</tr>	
		 			<tr height="5"><td></td></tr>
	 				<tr>
	 					<td valign="top" >
	 					<table width="95%" border="0" cellspacing="1" cellpadding="0">
	 					
	 					<tr>	
	 					<td valign="top" class="text14">
		 				  <table class="tableBorderWithRoundCorners" >
							<tr>
					 		 <td valign="top" class="text12">
				 					Arkiv docs.&nbsp;
				 					<div id="resultUploadedDocs">
				 						<table >
					 						<tr class="tableHeaderField" >
					 						<th align="left" class="text14">Dok.type</th>
					 						<th align="left" class="text14">Dok.navn</th>
					 						<th align="left" class="text14">Dato/kl</th>
					 						</tr>
				 						
						 					<c:forEach items="${model.record.getdoctrip}" var="record" varStatus="counter">
						 						<tr class="text14 tableRow">
						 						<td class="tableCellFirst" style="white-space:nowrap">${record.doctyp}</td>
		 										<td class="tableCell" style="white-space:nowrap">
						 						<a target="_blank" href="transportdisp_workflow_renderArchivedDocs.do?doclnk=${record.doclnk}">
		    		    							<c:choose>
			    		    							<c:when test="${fn:contains(record.doclnk, '.pdf')}">
			    		    								<img title="Archive" style="vertical-align:middle;" src="resources/images/pdf.png" width="14" height="14" border="0" alt="PDF arch.">
			    		    							</c:when>
			    		    							<c:otherwise>
			    		    								<img title="Archive" style="vertical-align:middle;" src="resources/images/jpg.png" width="14" height="14" border="0" alt="Other arch.">
			    		    							</c:otherwise>
		    		    							</c:choose>
		    		    							${record.doctxt}
				   								</a>
				   								</td>
					   							<td class="tableCell" style="white-space:nowrap">${record.docdat}&nbsp;${record.doctim}</td>
				   								</tr>
						 					</c:forEach>
						 				</table>
				 					</div>
				 				</td>
								</tr>
							</table>
		 				</td>
		 				
	 					
	 					<td valign="top" align="right">
							<c:choose>
								<c:when test="${headerRecord.own_editable > 0}">
									<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'>&nbsp;
								</c:when>
								<c:otherwise>
									<input title="Status combination or date = blocked" class="inputFormSubmitStd isa_info" type="button" name="fakeButton" id="fakeButton" value='<spring:message code="systema.tvinn.sad.manifest.disabled.button"/>'>
								</c:otherwise>
							</c:choose>
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
	<tr height="10"><td></td></tr>
		
		
	</table>
	</td>
	</tr>
		
	</table> 
	</td>
	</tr>
</table>
 
 	
 
	