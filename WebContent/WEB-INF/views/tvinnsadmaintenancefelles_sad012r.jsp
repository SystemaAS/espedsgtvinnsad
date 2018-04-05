<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmaintenancefelles_sad012r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<a id="alinkSadMaintFellesGate" tabindex=-1 style="display:block;" href="tvinnsadmaintenancefelles.do">
						<font class="tabDisabledLink">&nbsp;TVINN - Vedlikehold</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="30%" valign="bottom" class="tab" align="center">
						<font class="tabLink">Leveringsbeting.</font>&nbsp;<font class="text12">SAD012 / KODTLB </font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tvinnsadmaintenancefelles_sad012r.do?id=${model.dbTable}">
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
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    
			<%-- list component --%>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
				<table id="containerdatatableTable" width="90%" cellspacing="1" border="0" align="left">
			    	    <tr>
						<td class="text11">
						<table id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
								<th align="center" width="2%" class="tableHeaderFieldFirst" >&nbsp;KLBSTA&nbsp;</th>
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;Endre&nbsp;</th>
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;KLBUNI</th>
			                    <th align="center" width="2%" class="tableHeaderField" >&nbsp;Lev.kode&nbsp;</th>
			                    <th align="left" class="tableHeaderField" >&nbsp;K.tekst&nbsp;</th>
								<th align="center" class="tableHeaderField" >&nbsp;Beskrivelse&nbsp;</th>
			                    <th align="center" class="tableHeaderField" >&nbsp;Fors.kode&nbsp;</th>
			                    <th align="center" class="tableHeaderField">&nbsp;Promille&nbsp;</th>
			                    <th align="center" class="tableHeaderField" >&nbsp;Fr.kode&nbsp;</th>
			                    <th align="center" class="tableHeaderField">&nbsp;And.K.&nbsp;</th>
			                    <th align="center" class="tableHeaderField">&nbsp;Avs&nbsp;</th>
			                    <th align="center" class="tableHeaderField">&nbsp;Mott.&nbsp;</th>
			                    <th align="center" class="tableHeaderField">Slett</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				              
				               <td align="center" width="2%" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.klbsta}&nbsp;</font></td>
				               <td id="recordUpdate_${record.klbkod}" onClick="getRecord(this);" align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.klbuni}&nbsp;</font></td>
				               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.klbkod}&nbsp;</font></td>
		                       <td align="left"   class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.klbkt}&nbsp;</font></td>
				               <td align="center" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.klbnav}&nbsp;</font></td>
		                       <td align="center" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.klbfok}&nbsp;</font></td>
		                       <td align="center" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.klbprm}&nbsp;</font></td>
		                       <td align="center" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.klbfrk}&nbsp;</font></td>
		                       <td align="center" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.klbxxx_andrek}&nbsp;</font></td>
		                       <td align="center" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.klbxxx_avs}&nbsp;</font></td>
		                       <td align="center" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.klbxxx_mot}&nbsp;</font></td>
		                       <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadmaintenancefelles_sad012r_edit.do?action=doDelete&id=${model.dbTable}&klbkod=${record.klbkod}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
				            <tfoot>
							<tr>
							    <th align="center" width="2%" class="tableHeaderFieldFirstWhiteBg11" >&nbsp;KLBSTA&nbsp;</th>
								<th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;Endre&nbsp;</th>
								<th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;KLBUNI</th>
			                    <th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;KLBKOD&nbsp;</th>
			                    <th align="left" class="tableHeaderFieldWhiteBg11" >&nbsp;KLBKT&nbsp;</th>
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;KLBNAV&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;KLBFOK&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;KLBPRM&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;KLBFRK&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;KLBXXX&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;KLBXXX&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;KLBXXX&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">Slett</th>
			                </tr>  
			                </tfoot> 
			            </table>
					</td>	
					</tr>
				</table>
				</td>
			</tr>
		    
	 	    <tr height="25"><td>&nbsp;</td></tr>
	 	    
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
                                    <li>[ERROR on Update] - Server return code: ${model.errorMessage}</li>                                    
                                </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
			<tr height="2"><td>&nbsp;</td>
			</tr>
			<tr >
				<td width="5%">&nbsp;</td>
				<td><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" >Lage ny</button></td>
			</tr>
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%">
				<form action="tvinnsadmaintenancefelles_sad012r_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value=""> <%-- this value is set in AJAX in order to know if the SAVE = ADD or UPDATE --%>
					<input type="hidden" name="action" id=action value="doUpdate">
					<table width="80%" cellspacing="1" border="0" align="left">
					
			    	    <tr>
							<td class="text12" title="KLBKOD">&nbsp;<font class="text14RedBold" >*</font>Lev.kode</td>
							<td class="text12" title="KLBKT">&nbsp;<font class="text14RedBold" >*</font>K.tekst</td>
							<td class="text12" title="KLBNAV">&nbsp;Beskrivelse</td>
							<td class="text12" title="KLBFOK">&nbsp;<font class="text14RedBold" >*</font>Fors.kode</td>
							<td class="text12" title="KLBPRM">&nbsp;Promille</td>
							<td class="text12" title="KLBFRK">&nbsp;<font class="text14RedBold" >*</font>Fr.kode</td>
							<td class="text12" title="KLBXXX">&nbsp;And.K.</td>
							<td class="text12" title="KLBXXX">&nbsp;Avs</td>
							<td class="text12" title="KLBXXX">&nbsp;Mot</td>
						</tr>
						<tr>
						<td ><input type="text" class="inputTextMediumBlueUPPERCASEMandatoryField" name="klbkod" id="klbkod" size="4" maxlength="3" value='${model.record.klbkod}'></td>
						<td ><input type="text" class="inputTextMediumBlueUPPERCASEMandatoryField" name="klbkt" id="klbkt" size="4" maxlength="3" value='${model.record.klbkt}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="klbnav" id="klbnav" size="15" maxlength="15" value='${model.record.klbnav}'></td>
						<td >
							<select class="inputTextMediumBlueMandatoryField" name="klbfok" id="klbfok">
			            		<option value="">-velg-</option>
			 				  	<option value="A" <c:if test="${ model.record.klbfok == 'A'}"> selected </c:if> >A</option>
			 				  	<option value="N" <c:if test="${ model.record.klbfok == 'N'}"> selected </c:if> >N</option>
							</select>
						</td>
						<td ><input type="text" class="inputTextMediumBlue" name="klbprm" id="klbprm" size="4" maxlength="4" value='${model.record.klbprm}'></td>
						<td >
							<select class="inputTextMediumBlueMandatoryField" name="klbfrk" id="klbfrk">
			            		<option value="">-velg-</option>
			            		<option value="F" <c:if test="${ model.record.klbfrk == 'F'}"> selected </c:if> >F</option>
			 				  	<option value="J" <c:if test="${ model.record.klbfrk == 'J'}"> selected </c:if> >Ja</option>
			 				  	<option value="N" <c:if test="${ model.record.klbfrk == 'N'}"> selected </c:if> >Nej</option>
							</select>
						</td>
						<td >
							<select class="inputTextMediumBlue" name="klbxxx_andrek" id="klbxxx_andrek">
			            		<option value="">-velg-</option>
			 				  	<option value="J" <c:if test="${ model.record.klbxxx_andrek == 'J'}"> selected </c:if> >Ja</option>
			 				  	<option value="N" <c:if test="${ model.record.klbxxx_andrek == 'N'}"> selected </c:if> >Nej</option>
							</select>
						</td>
						<td >
							<select class="inputTextMediumBlue" name="klbxxx_avs" id="klbxxx_avs">
			            		<option value="">-velg-</option>
			 				  	<option value="A" <c:if test="${ model.record.klbxxx_avs == 'A'}"> selected </c:if> >A</option>
			 				  	<option value="M" <c:if test="${ model.record.klbxxx_avs == 'M'}"> selected </c:if> >M</option>
							</select>
						</td>
						<td >
							<select class="inputTextMediumBlue" name="klbxxx_mot" id="klbxxx_mot">
			            		<option value="">-velg-</option>
			 				  	<option value="A" <c:if test="${ model.record.klbxxx_mot == 'A'}"> selected </c:if> >A</option>
			 				  	<option value="M" <c:if test="${ model.record.klbxxx_mot == 'M'}"> selected </c:if> >M</option>
							</select>
						</td>
						<td>
							<input class="inputFormSubmit" type="submit" name="submit" value='Lagre'/>
						</td>
						</tr>
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

