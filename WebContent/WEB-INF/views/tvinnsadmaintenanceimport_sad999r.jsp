<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmaintenanceimport_sad999r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<a id="alinkSadMaintImportGate" tabindex=-1 style="display:block;" href="tvinnsadmaintenanceimport.do">
						<font class="tabDisabledLink">&nbsp;TVINN - Vedlikehold</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="30%" valign="bottom" class="tab" align="center">
						<font class="tabLink">Særavgifter</font>&nbsp;<font class="text12">SAD999 / SADSD</font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tvinnsadmaintenanceimport_sad999r.do?id=${model.dbTable}">
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
					<form action="tvinnsadmaintenanceimport_sad999r.do?id=${model.dbTable}" name="formRecord" id="formRecord" method="POST" >
					Tariffnr&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchSdtnrf" id="searchSdtnrf" size="9" maxlength="8" value='${model.sdtnrf}'>
					
					&nbsp;Avgiftskode&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchSdkdae" id="searchSdkdae" size="3" maxlength="2" value='${model.sdkdae}'>
					
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
								<th class="tableHeaderField" >&nbsp;Tariffnr.&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Beskriv.&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Avg.&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Sekv.&nbsp;</th>
			                    <th class="tableHeaderField">&nbsp;Fdato&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Tdato&nbsp;</th>
			                    <th class="tableHeaderField">&nbsp;Sats&nbsp;</th>
			                    <th class="tableHeaderField">&nbsp;Status&nbsp;</th>
			                    
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${record.sdtnrf}_${record.taalfa}_${record.sddtf}_${record.sddtt}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.sdtnrf}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.taalfa}&nbsp;</font></td>
				               <td align="center" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.sdkdae}&nbsp;</font></td>
		                       <td align="center" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.sdkdse}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.sddtfNO}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.sddttNO}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.sdblse}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
		                       		<c:choose> 
			    					<c:when test="${record.sdaktk=='A'}">
		                       			<font class="text12">&nbsp;&nbsp;&nbsp;AKTIV&nbsp;</font>
		                       		</c:when>
		                       		<c:otherwise>
		                       			<font class="text12">&nbsp;&nbsp;&nbsp;INAKTIV&nbsp;</font>
		                       		</c:otherwise>
		                       		</c:choose>
		                       </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
				            <tfoot>
							<tr>
							    <th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;Endre&nbsp;</th>
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SDTNRF</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TAALFA&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SDKDAE&nbsp;</th>
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SDKDSE&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SDDTF&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;SDDTT&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SDBLSE&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;SDAKTK&nbsp;</th>
			                    
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
				<form action="tvinnsadmaintenanceimport_sad999r_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
					<input type="hidden" name="action" id=action value="doUpdate">
					<input type="hidden" name="sddtfOrig" id=sddtfOrig value="${model.record.sddtfOrig}">
					<input type="hidden" name="sddttOrig" id=sddttOrig value="${model.record.sddttOrig}">
					<input type="hidden" name="searchSdtnrf" id=searchSdtnrf value="${model.sdtnrf}">
					<input type="hidden" name="searchSdkdae" id=searchSdkdae value="${model.sdkdae}">
					
					<table width="98%" cellspacing="1" border="0" align="left">
			    	    <tr>
							<td class="text12" title="SDTNRF">&nbsp;<font class="text14RedBold" >*</font>Tariffnr.</td>
							<td class="text12" title="TAALFA">&nbsp;Beskrivelse</td>
							<td class="text12" title="SDKDAE">&nbsp;<font class="text14RedBold" >*</font>Avg.</td>
							<td class="text12" title="SDKDSE">&nbsp;Sekv.</td>
							<td class="text12" title="SDDTF">&nbsp;<font class="text14RedBold" >*</font>F.o.m dato</td>
							<td class="text12" title="SDDTT">&nbsp;<font class="text14RedBold" >*</font>T.o.m dato</td>
							<td class="text12" title="SDBLSE">&nbsp;<font class="text14RedBold" >*</font>Sats</td>
							<td class="text12" title="SDAKTK">&nbsp;Status</td>
						</tr>
						<tr>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="sdtnrf" id="sdtnrf" size="9" maxlength="8" value='${model.record.sdtnrf}'></td>
						<td ><input readonly type="text" class="inputTextReadOnly" name="taalfa" id="taalfa" size="15" maxlength="30" value='${model.record.taalfa}'></td>
						<td >
							<input type="text" class="inputTextMediumBlueMandatoryField" name="sdkdae" id="sdkdae" size="3" maxlength="2" value='${model.record.sdkdae}'>
							<a tabindex="-1" id="sdkdaeIdLink">
								<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
							</a>
						</td>
						<td ><input type="text" class="inputTextMediumBlue" name="sdkdse" id="sdkdse" size="4" maxlength="3" value='${model.record.sdkdse}'></td>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="sddtfNO" id="sddtfNO" size="9" maxlength="8" value='${model.record.sddtfNO}'></td>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="sddttNO" id="sddttNO" size="9" maxlength="8" value='${model.record.sddttNO}'></td>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="sdblse" id="sdblse" size="11" maxlength="10" value='${model.record.sdblse}'></td>
						<td >
							<select name="sdaktk" id="sdaktk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.sdaktk == 'A'}"> selected </c:if> >AKTIV</option>
							  	<option value="I"<c:if test="${ model.record.sdaktk == 'I'}"> selected </c:if> >INAKTIV</option>
						  	</select>
						</td>
						<td>
							<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'/>
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

