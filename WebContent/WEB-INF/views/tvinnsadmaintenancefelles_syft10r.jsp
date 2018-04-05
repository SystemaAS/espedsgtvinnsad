<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmaintenancefelles_syft10r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<font class="tabLink">Gyldige tariffører</font>&nbsp;<font class="text12">SYFT10 / KODTSI</font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tvinnsadmaintenancefelles_syft10r.do?id=${model.dbTable}">
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
							                                                                
								<th align="center" width="2%" class="tableHeaderFieldFirst" >&nbsp;KSISTA&nbsp;</th>
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;Endre&nbsp;</th>
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;KSIUNI</th>
			                    <th align="center" width="2%" class="tableHeaderField" >&nbsp;Sign.&nbsp;</th>
			                    <th align="left" class="tableHeaderField" >&nbsp;Navn&nbsp;</th>
								<th align="center" class="tableHeaderField" >&nbsp;Overlay&nbsp;</th>
			                    <th align="center" class="tableHeaderField" >&nbsp;Brukerid&nbsp;</th>
			                    <th align="center" class="tableHeaderField">&nbsp;Epost.&nbsp;</th>
			                    <th align="center" class="tableHeaderField">Slett</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				              
				               <td align="center" width="2%" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.ksista}&nbsp;</font></td>
				               <td id="recordUpdate_${record.ksisig}" onClick="getRecord(this);" align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.ksiuni}&nbsp;</font></td>
				               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.ksisig}&nbsp;</font></td>
		                       <td align="left"   class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.ksinav}&nbsp;</font></td>
				               <td align="center" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.ksovl}&nbsp;</font></td>
		                       <td align="center" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.ksuser}&nbsp;</font></td>
		                       <td align="center" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.ksixxx}&nbsp;</font></td>
		                       <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadmaintenancefelles_syft10r_edit.do?action=doDelete&id=${model.dbTable}&ksisig=${record.ksisig}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
				            <tfoot>
							<tr>
							                                                                
								<th align="center" width="2%" class="tableHeaderFieldFirstWhiteBg11" >&nbsp;KSISTA&nbsp;</th>
								<th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;Endre&nbsp;</th>
								<th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;KSIUNI</th>
			                    <th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;KSISIG&nbsp;</th>
			                    <th align="left" class="tableHeaderFieldWhiteBg11" >&nbsp;KSINAV&nbsp;</th>
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;KSOVL&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;KSUSER&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;KSIXXX&nbsp;</th>
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
			
			<tr height="2"><td>&nbsp;</td></tr>
	 	    <tr >
				<td width="5%">&nbsp;</td>
				<td><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" >Lage ny</button></td>
			</tr>
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%">
				<form action="tvinnsadmaintenancefelles_syft10r_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value=""> <%-- this value is set in AJAX in order to know if the SAVE = ADD or UPDATE --%>
					<input type="hidden" name="action" id=action value="doUpdate">
					<table width="40%" cellspacing="1" border="0" align="left">
					
			    	    <tr>
							<td class="text12" title="KSISIG">&nbsp;<font class="text14RedBold" >*</font>Sig.</td>
							<td class="text12" title="KSINAV">&nbsp;<font class="text14RedBold" >*</font>Navn</td>
							<td class="text12" title="KSOVL">&nbsp;Overlay</td>
							<td class="text12" title="KSUSER">&nbsp;Brukerid</td>
							<td class="text12" title="KSUSER">&nbsp;Epost for TVINN-melding</td>
						</tr>
						<tr>
						<td ><input type="text" class="inputTextMediumBlueUPPERCASEMandatoryField" name="ksisig" id="ksisig" size="4" maxlength="3" value='${model.record.ksisig}'></td>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="ksinav" id="ksinav" size="16" maxlength="16" value='${model.record.ksinav}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="ksovl" id="ksovl" size="10" maxlength="8" value='${model.record.ksovl}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="ksuser" id="ksuser" size="10" maxlength="10" value='${model.record.ksuser}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="ksixxx" id="ksixxx" size="30" maxlength="42" value='${model.record.ksixxx}'></td>
						
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

