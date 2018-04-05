<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmaintenancefelles_syft02r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<font class="tabLink">Tollkurser</font>&nbsp;<font class="text12">SYFT02 / KODTVA</font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tvinnsadmaintenancefelles_syft02r.do?id=${model.dbTable}">
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
					<form action="tvinnsadmaintenancefelles_syft02r.do?id=${model.dbTable}" name="formRecordSearch" id="formRecordSearch" method="POST" >
					Kode&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchKode" id="searchKode" size="5" maxlength="3" value='${model.searchKode}'>
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
								<th class="tableHeaderField" >&nbsp;Kode&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Beskrivelse&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Kurs&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Omr.faktor&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;F.o.m.dato&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Grenseverdi&nbsp;</th>
			                    <th align="center" class="tableHeaderField">Slett</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${record.kvakod}_${record.kvadtNO}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.kvakod}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.kvaxxx}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.kvakrs}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.kvaomr}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.kvadtNO}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.kvagv}&nbsp;</font></td>
				               
				               <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadmaintenancefelles_syft02r_edit.do?action=doDelete&id=${model.dbTable}&kvakod=${record.kvakod}&kvadt=${record.kvadtNO}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
				            <tfoot>
							<tr>
							    <th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;Endre&nbsp;</th>
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;KVAKOD</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;KVAXXX&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;KVAKRS&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;KVAOMR&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;KVADT&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;KVAGV&nbsp;</th>
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
				<form action="tvinnsadmaintenancefelles_syft02r_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
					<input type="hidden" name="action" id=action value="doUpdate">
					
					<table width="80%" cellspacing="1" border="0" align="left">
			    	    <tr>
							<td class="text12" title="KVAKOD">&nbsp;<font class="text14RedBold" >*</font>Kode</td>
							<td class="text12" title="KVAXXX">&nbsp;Beskrivelse</td>
							<td class="text12" title="KVAKRS">&nbsp;<font class="text14RedBold" >*</font>Kurs</td>
							<td class="text12" title="KVAOMR">&nbsp;<font class="text14RedBold" >*</font>Omr.faktor</td>
							<td class="text12" title="KVADT">&nbsp;<font class="text14RedBold" >*</font>F.o.m.dato</td>
							<td class="text12" title="KVAGV">&nbsp;Grenseverdi</td>
						</tr>
						<tr>
						<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="kvakod" id="kvakod" size="4" maxlength="3" value='${model.record.kvakod}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="kvaxxx" id="kvaxxx" size="30" maxlength="27" value='${model.record.kvaxxx}'></td>
						<td ><input onKeyPress="return amountKey(event)" type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="kvakrs" id="kvakrs" size="15" maxlength="12" value='${model.record.kvakrs}'></td>
						<td ><input onKeyPress="return numberKey(event)" type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="kvaomr" id="kvaomr" size="4" maxlength="3" value='${model.record.kvaomr}'></td>
						<td ><input type="text" required class="inputTextMediumBlueMandatoryField" name="kvadtNO" id="kvadtNO" size="9" maxlength="8" value='${model.record.kvadtNO}'></td>
						<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kvagv" id="kvagv" size="12" maxlength="11" value='${model.record.kvagv}'></td>
						<td>
							<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'/>
						</td>
						</tr>
						<tr height="3"><td></td>
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

