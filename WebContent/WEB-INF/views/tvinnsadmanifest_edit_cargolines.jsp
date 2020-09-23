<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
f
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
		<table style="width:90%;" cellspacing="0" border="0" cellpadding="0">
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
                		<th class="tableHeaderField" >MRN nr</th>
                		<th class="tableHeaderField" >Sert</th>
                		<th class="tableHeaderField" >Bilnr</th>
                		<th class="tableHeaderField" >E.enh</th>
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
               					<img src="resources/images/update.gif" border="0" alt="edit">
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
		               <td width="2%" class="tableCell" <c:if test="${record.clst != 'O'}">style="color: #9F6000;" </c:if> align="center" >${record.cleser}</td>
		               <td width="2%" class="tableCell" <c:if test="${record.clst != 'O'}">style="color: #9F6000;" </c:if> align="center" >${record.cltrid}</td>
		               <td width="2%" class="tableCell" <c:if test="${record.clst != 'O'}">style="color: #9F6000;" </c:if> align="center" >${record.cl3039e}</td>
		               <td width="2%" class="tableCell" align="center" >
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
 	<td>
 		<input class="inputFormSubmitStd" type="button" name="newButton" id="newButton" value='Lage ny'>
		<input class="inputFormSubmitBlue" type="button" name="cnButton" id="cnButton" value='Plukke oppdrag'>
	</td>	
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
			
			
			<table style="width:90%" align="left" border="0" cellspacing="1" cellpadding="0">
			 	<tr >
				 	<td >
					<table style="width:100%" class="formFrameHeader" border="0" cellspacing="1" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14White">&nbsp;&nbsp;Last&nbsp;&nbsp;-
				 				&nbsp;&nbsp;Turnr:&nbsp;${model.efpro}
	 							&nbsp;&nbsp;Avd:&nbsp;${model.efavd}&nbsp;&nbsp;
	 							&nbsp;&nbsp;Manifestid:&nbsp;${model.efuuid}
				 			</td>
		 				</tr>
		            </table>
		            </td>
	            </tr>
	            <tr >
				 	<td>
					<table style="width:100%" class="formFrame" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td colspan="2" valign="top">	
						<table width="80%">
						<tr >
							<td colspan="4" class="text16"><b>&nbsp;Import</b></td>
						<tr >
		 				<tr >
							<td class="text14">&nbsp;<span title="cltdn">Oppdrag</span></td>

							<td class="text14">&nbsp;<span title="clrg">Deklarantnr.</span><font class="text16RedBold" >*</font></td>
							<td class="text14">&nbsp;<span title="cl0068a">Dato</span><font class="text16RedBold" >*</font></td>
							<td class="text14">&nbsp;<span title="cl0068b">Sekvensnr.</span><font class="text16RedBold" >*</font></td>
							<td class="text14">&nbsp;<span title="clpr">Prosedyre</span><font class="text16RedBold" >*</font></td>
							<td class="text14">&nbsp;<span title="clvt">Varebeskrivelse</span><font class="text16RedBold" >*</font></td>
							<td class="text14">&nbsp;<span title="clntk">Antal Kolli</span><font class="text16RedBold" >*</font></td>
							<td class="text14">&nbsp;<span title="clvkb">Bruttovekt</span><font class="text16RedBold" >*</font></td>
							
		 				</tr>
		 				<tr >
		 					<td class="text14"><input type="text" class="inputTextReadOnly" style="color:yellow;background-color:#BBBBBB;" name="cltdn" id="cltdn" size="8" maxlength="7" value="${model.record.cltdn}"></td>
				 			<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="clrg" id="clrg" size="12" maxlength="11" value="${model.record.clrg}"></td>
				 			<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="cl0068a" id="cl0068a" size="7" maxlength="6" value="${model.record.cl0068a}"></td>
				 			<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="cl0068b" id="cl0068b" size="7" maxlength="6" value="${model.record.cl0068b}"></td>
							<td>
								<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="clpr" id="clpr" >
			 						<option value="">-select-</option>
					 				  	<c:forEach var="record" items="${model.prTypeList}" >
				                       	 	<option title="${record.kftxt}" value="${record.kfkod}" <c:if test="${model.record.clpr == record.kfkod}"> selected </c:if> >${record.kfkod}&nbsp;${record.kftxt}</option>
										</c:forEach>
								</select>
							</td>
							<td class="text14">&nbsp;<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" type="text" class="inputTextMediumBlueMandatoryField" name="clvt" id="clvt" size="30" maxlength="30" value="${model.record.clvt}"></td>
				 			<td class="text14"><input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="clntk" id="clntk" size="8" maxlength="7" value="${model.record.clntk}"></td>
				 			<td class="text14"><input onKeyPress="return numberKey(event)" style="text-align: right" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="clvkb" id="clvkb" size="10" maxlength="9" value="${model.record.clvkb}"></td>
		 				</tr>
		 				</table>
	 					</td>
		 			</tr>	
		 			<tr height="15"><td></td></tr>
	 				<tr>
	 					<td >
						<table width="95%" class="tableBorderWithRoundCorners" border="0" cellspacing="1" cellpadding="0">
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
							&nbsp;<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="clsdft" id="clsdft" size="31" maxlength="30" value="${model.record.clsdft}">
							<select class="inputTextMediumBlue" name="cllkf" id="cllkf">
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
								<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  type="text" class="inputTextMediumBlueMandatoryField" name="clsdtt" id="clsdtt" size="31" maxlength="30" value="${model.record.clsdtt}">
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
						
						<tr height="10"><td></td></tr>
						</table>
						</td>
	 					
						<td >
							<table width="95%" class="tableBorderWithRoundCorners" border="0" cellspacing="1" cellpadding="0">
			 				<tr >
								<td class="text16"><b>&nbsp;Eksport og Transitering</b></td>
							<tr >
							<tr >
								<td class="text14">&nbsp;<span title="cletyp">Eksporttype</span><font class="text16RedBold" >*</font></td>
								<td class="text14">&nbsp;<span title="cleid">MRN nr.</span><font class="text16RedBold" >*</font></td>
								<td class="text14">&nbsp;<span title="cleser">Sertifisert</span><font class="text16RedBold" >*</font></td>
																	
							</tr>
							<tr>
								<td class="text14">&nbsp;
					 				<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="cletyp" id="cletyp" >
				 						<option value="">-select-</option>
					 				  	<c:forEach var="record" items="${model.etTypeList}" >
				                       	 	<option title="${record.kftxt}" value="${record.kfkod}" <c:if test="${model.record.cletyp == record.kfkod}"> selected </c:if> >${record.kfkod}&nbsp;${record.kftxt}</option>
										</c:forEach>
									</select>
					 			</td>
					 			<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="cleid" id="cleid" size="20" maxlength="18" value="${model.record.cleid}"></td>
					 			<td class="text14">
					 				<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="cleser" id="cleser" >
				 						<option value="N">Nei</option>
		 		 				  		<option value="J">Ja</option>
									</select>
					 			</td>						 			
							</tr>
							<tr height="10"><td></td></tr>
							</table>
						</td>
						<td valign="bottom">
							<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'>
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
 
 	
 
	