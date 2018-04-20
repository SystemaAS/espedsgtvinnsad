<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmaintenanceimport_sad006r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<font class="tabLink">Løpenummer</font>&nbsp;<font class="text14">SAD006 / SADH-HEADF</font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tvinnsadmaintenanceimport_sad006r.do?id=${model.dbTable}">
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
				<td width="100%" class="text14">
					<form action="tvinnsadmaintenanceimport_sad006r.do?id=${model.dbTable}" name="formRecord" id="formRecord" method="POST" >
						<font class="text14RedBold" >*</font>Avd&nbsp;
						<input type="text" class="inputTextMediumBlue" name="searchAvd" id="searchAvd" size="6" maxlength="6" value='${model.avd}'>
						Tolldekl.nr.&nbsp;
						<input type="text" class="inputTextMediumBlue" name="searchOpd" id="searchOpd" size="6" maxlength="6" value='${model.opd}'>
						<input type="hidden" name="sh" id="sh" value="y">
						&nbsp;&nbsp;<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" value='Søk'/>					
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
								<th class="tableHeaderField" >&nbsp;Avd.&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Tolldekl.nr.&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Mottak.navn&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Løpenr.&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Eksp.nr&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Dato&nbsp;</th>
								
								<%--  
			                    <th align="center" class="tableHeaderField">Slett</th>
			                     --%>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${record.siavd}_${record.sitdn}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td width="5%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text14">&nbsp;${record.siavd}&nbsp;</font></td>
				               <td width="10%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text14">&nbsp;${record.sitdn}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.sinak}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.sitll}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.sitle}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.sidtg}&nbsp;</font></td>
				               <%--
				               <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadmaintenanceimport_sad006r_edit.do?action=doDelete&id=${model.dbTable}&tariff=${record.tariff}&beskr1=${record.beskr1}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
				                --%>
				            </tr> 
				            </c:forEach>
				            </tbody>
				            <%--
				            <tfoot>
							<tr>
							    <th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;Endre&nbsp;</th>
							    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SIAVD</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SITDN</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SINAK&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SITLL</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SITLE</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SIDTG</th>
			                    
			                    
			                </tr>  
			                </tfoot>
			                 --%> 
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
			            <ul class="isa_error text14" >
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
				 				<ul class="isa_error text14" >
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
			<%-- N/A 
			<tr >
				<td width="5%">&nbsp;</td>
				<td><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" >Lage ny</button></td>
			</tr>
			--%>
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%">
				<form action="tvinnsadmaintenanceimport_sad006r_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
					<input type="hidden" name="action" id=action value="doUpdate">
					<input type="hidden" name="siavd" id=siavd value="${model.record.siavd}">
					
					<table width="60%" cellspacing="1" border="0" align="left">
			    	    <tr>
							<td class="text14" title="SITDN">&nbsp;<font class="text14RedBold" >*</font>Tolldekl.nr.</td>
							<td class="text14" title="SINAK">&nbsp;Mottak.navn</td>
							<td class="text14" title="SITLL">&nbsp;Løpenr.</td>
							<td class="text14" title="SITLE">&nbsp;Eksp.nr</td>
							<td class="text14" title="SIDTG">&nbsp;Dato</td>
						</tr>
						<tr>
						<td ><input readonly type="text" class="inputTextReadOnly" name="sitdn" id="sitdn" size="8" maxlength="7" value='${model.record.sitdn}'></td>
						<td ><input readonly type="text" class="inputTextReadOnly" name="sinak" id="sinak" size="25" maxlength="30" value='${model.record.sinak}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="sitll" id="sitll" size="11" maxlength="10" value='${model.record.sitll}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="sitle" id="sitle" size="11" maxlength="6" value='${model.record.sitle}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="sidtg" id="sidtg" size="9" maxlength="8" value='${model.record.sidtg}'></td>
						
						<td>
							<input <c:if test="${ empty model.record.sitdn}"> disabled </c:if> onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'/>
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

