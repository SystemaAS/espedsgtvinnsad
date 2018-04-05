<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmaintenance_nctsexport_tr030r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>


<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkSadMaintNctsExportGate" tabindex=-1 style="display:block;" href="tvinnsadmaintenance_nctsexport.do">  <!-- kolla url -->
						<font class="tabDisabledLink">&nbsp;TVINN - Vedligehold</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center">
						<font class="tabLink">Garantiref.</font>&nbsp;<font class="text12">TR030R</font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tvinnsadmaintenance_nctsexport_tr030r.do?id=${model.dbTable}">
							<img style="vertical-align: middle;"  src="resources/images/bulletGreen.png" border="0" width="8px" height="8px" alt="db table">
						</a>
					</td>
					<td width="70%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
				</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<%-- space separator --%>
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="30"><td>&nbsp;</td></tr>
	 	    
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%" class="text12">
					<form action="tvinnsadmaintenance_nctsexport_tr030r.do?id=${model.dbTable}" name="formRecordSearch" id="formRecordSearch" method="POST" >
					Garantinr.&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchGaranti" id="searchGaranti" size="26" maxlength="25" value='${model.searchGaranti}'>
					<%--
					&nbsp;Søkebegrep&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchTaalfa" id="searchTaalfa" size="15" maxlength="25" value='${model.taalfa}'>
					--%>
					&nbsp;&nbsp;<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submitSearch" id="submitSearch" value='Søk'/>
					
					</form>
				</td>
			</tr>
			
			<%-- list component --%>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
				<table id="containerdatatableTable" width="98%" cellspacing="1" border="0" align="left">
			    	    <tr>
						<td class="text11">
						<table id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;Endre&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Garantinr.&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Tollsted&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Send dato&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Organisasjonnr.&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Adgangskode&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Status&nbsp;</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${record.tggnr}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.tggnr}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.tgtsd}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.tgdtNO}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.tgtina}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.tgakny}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
		                       		<c:choose> 
			    					<c:when test="${record.tgst=='S'}">
		                       			<font class="text12">&nbsp;&nbsp;&nbsp;INAKTIV&nbsp;</font>
		                       		</c:when>
		                       		<c:otherwise>
		                       			<font class="text12">&nbsp;&nbsp;&nbsp;AKTIV&nbsp;</font>
		                       		</c:otherwise>
		                       		</c:choose>
		                       </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
<!--  
				            <tfoot>
							<tr>
							    <th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;Endre&nbsp;</th>
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TGGNR</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TGTSD&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TGDT&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TGTINA&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TGAKNY&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TGST&nbsp;</th>
			                </tr>  
			                </tfoot> 
-->
			            </table>
					</td>	
					</tr>
				</table>
				</td>
			</tr>
		    
	 	    <tr height="15"><td>&nbsp;</td></tr>
	 	    
	 	    <%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
	            	<tr >
					<td >					
			            <ul class="isa_error text12" > 
			            <c:forEach var="error" items="${errors.allErrors}">
			                <li >
			                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>&nbsp;&nbsp;
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
				<td width="5">&nbsp;</td>
				<td >
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td >
				 				<ul class="isa_error text12" >
                                    <li>${model.errorMessage}</li>                                    
                                </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr height="2"><td>&nbsp;</td></tr>
			</c:if>
			<tr >
				<td width="5%">&nbsp;</td>
				<td><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" >Lage ny</button></td>
			</tr>
			<tr height="2"><td>&nbsp;</td></tr>
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%">
				<form action="tvinnsadmaintenance_nctsexport_tr030r_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
					<input type="hidden" name="action" id=action value="doUpdate">
					
					<table id="editRowTable" class="tableHeaderFieldXXX" width="98%" cellspacing="1" border="0" align="left">
						<tr>
							<%-- LEFT CELL --%>
							<td width="50%" valign="top">
								<table width="90%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0"> 
							 		<tr height="15">
							 			<td class="text12White">
								 			&nbsp;Hovedansvarlig&nbsp;
						 				</td>
					 				</tr>
				 				</table>
								<table width="90%" class="formFrame" cellspacing="1" border="0" align="left">
									<tr>
										<td class="text12" title="tgkna">&nbsp;Kundenr.</td>
										<td ><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="tgkna" id="tgkna" size="9" maxlength="8" value='${model.record.tgkna}'></td>
									</tr>
									<tr>	
										<td class="text12" title="tgtina">&nbsp;<font class="text14RedBold" >*</font>Organisasjonsnr.</td>
										<td ><input type="text" onKeyPress="return numberKey(event)" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tgtina" id="tgtina" size="18" maxlength="17" value='${model.record.tgtina}'></td>
									</tr>
									<tr>
										<td class="text12" title="tgnaa">&nbsp;<font class="text14RedBold" >*</font>Navn</td>
										<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tgnaa" id="tgnaa" size="31" maxlength="30" value='${model.record.tgnaa}'></td>
									</tr>
									<tr>	
										<td class="text12" title="tgada1">&nbsp;Adresse 1</td>
										<td ><input type="text" class="inputTextMediumBlue" name="tgada1" id="tgada1" size="31" maxlength="30" value='${model.record.tgada1}'></td>
									</tr>
									
									<tr>	
										<td class="text12" title="tgpna/tgpsa">&nbsp;<font class="text14RedBold" >*</font>Postnr/Postadr.</td>
										<td >
											<input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tgpna" id="tgpna" size="10" maxlength="9" value='${model.record.tgpna}'>
											<input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tgpsa" id="tgpsa" size="25" maxlength="24" value='${model.record.tgpsa}'>
										</td>
									</tr>
									<tr>	
										<td class="text12" title="tglka">&nbsp;<font class="text14RedBold" >*</font>Landekode</td>
										<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tglka" id="tglka" size="2" maxlength="2" value='${model.record.tglka}'></td>
									</tr>
									
								</table>
							</td>
							<%-- RIGHT CELL --%>
							<td width="50%" valign="top">
								<table width="100%" cellspacing="1" border="0" align="left">
									<tr>
										<td class="text12" title="tgtsd">&nbsp;<font class="text14RedBold" >*</font>Garanti tollsted:</td>
										<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tgtsd" id="tgtsd" size="9" maxlength="8" value='${model.record.tgtsd}'></td>
									</tr>
									<tr>	
										<td class="text12" title="tggty/tggnr">&nbsp;<font class="text14RedBold" >*</font>Garanti typ/Garanti nr.:</td>
										<td >
											<input type="text" onKeyPress="return numberKey(event)" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tggty" id="tggty" size="2" maxlength="1" value='${model.record.tggty}'>
											<input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tggnr" id="tggnr" size="25" maxlength="24" value='${model.record.tggnr}'>
										</td>
									</tr>
									<tr>	
										<td class="text12" title="tggfv">&nbsp;Gjeld følsom vare:</td>
										<td class="text12" >
											<select name="tggfv" id="tggfv" >
		 					  					<option value="">-velg-</option>
		 					  					<option value="J"<c:if test="${ model.record.tggfv == 'J'}"> selected </c:if> >Ja</option>
							  					<option value="N"<c:if test="${ model.record.tggfv == 'N'}"> selected </c:if> >Nej</option>
							  				</select>
										</td>
									</tr>
									<tr>
										<td class="text12" title="tgakny">&nbsp;<font class="text14RedBold" >*</font>Adgangskode:</td>
										
										<td class="text12" >
											<input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tgakny" id="tgakny" size="5" maxlength="4" value='${model.record.tgakny}'>
										</td>
										
									</tr>
									<tr>
										<td class="text12" title="tggbl">&nbsp;<font class="text14RedBold" >*</font>Garanti beløp:</td>
										<td class="text12" >
											<input type="text" onKeyPress="return numberKey(event)" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tggbl" id="tggbl" size="12" maxlength="11" value='${model.record.tggbl}'>
											&nbsp;&nbsp;&nbsp;<font class="text14RedBold" >*</font><span title="tggvk">Valuta:</span>
											<select required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tggvk" id="tggvk" >
		 					  					<option value="">-velg-</option>
												<c:forEach var="record" items="${model.currencyCodeList}" >
							 				  		<option value="${record.kvakod}"<c:if test="${model.record.tggvk == record.kvakod}"> selected </c:if> >${record.kvakod}</option>
												</c:forEach> 
							  					
							  				</select>
										</td>
									</tr>
									<tr>
										<td class="text12" title="tggblb">&nbsp;Brukt garantibeløp:</td>
										<td >
											<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="tggblb" id="tggblb" size="12" maxlength="11" value='${model.record.tggblb}'>
										</td>
									</tr>
									<tr>
										<td class="text12" title="tgprm">&nbsp;Varsling:</td>
										<td >
											<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="tgprm" id="tgprm" size="4" maxlength="3" value='${model.record.tgprm}'>
											&nbsp;<font class="text11">(Ved over X % er brukt opp)</font>
										</td>
										
									</tr>	
									<tr>
										<td class="text12" title="tgst">&nbsp;Status:</td>
										<td class="text12" >
											<select name="tgst" id="tgst" >
							  					<option value="S"<c:if test="${model.record.tgst == 'S'}"> selected </c:if> >INAKTIV</option>
							  					<option value=""<c:if test="${empty model.record.tgst}"> selected </c:if> >AKTIV</option>
							  				</select>
										</td>
										
									</tr>
								</table>
							</td>
						</tr>
						<tr>
						    <td>&nbsp;</td>
							<td align="right">
								<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'/>
							</td>
						</tr>
						<tr height="3"><td></td></tr>
						
					</table>
	 	    	</form>
	 	    </tr>
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	     
	 		</table>
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

