<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmaintenanceimport_sad002_kodts2r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkSadMaintImportSad002" tabindex=-1 style="display:block;" href="tvinnsadmaintenanceimport_sad002r.do?id=${model.dbTable}">
						<font class="tabDisabledLink">&nbsp;GYLDIGE KODER</font>&nbsp;
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="30%" valign="bottom" class="tab" align="center">
						<font class="tabLink">Landkoder</font>&nbsp;<font class="text12">SAD002 / KODTS2</font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tvinnsadmaintenanceimport_sad002_kodts2r.do?id=${model.dbTable}">
							<img style="vertical-align: middle;"  src="resources/images/bulletGreen.png" border="0" width="8px" height="8px" alt="db table">
						</a>
					</td>
					<td width="30%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
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
								<th class="tableHeaderField" >&nbsp;Endre&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Kode</th>
			                    <th class="tableHeaderField" >&nbsp;Fritekst&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Språk&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;M/O&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Pref&nbsp;</th>
			                    <th class="tableHeaderField">Slett</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${record.ks2lk}" onClick="getRecord(this);" align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td width="2%" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.ks2lk}&nbsp;</font></td>
				               
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.ks2ftx}&nbsp;</font></td>
				               <td align="center" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.ks2nas}&nbsp;</font></td>
				               <td width="2%" align="center" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.ks2mo}&nbsp;</font></td>
				               <td width="2%" align="center" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.ks2pre}&nbsp;</font></td>
				               
				               <td align="center" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadmaintenanceimport_sad002_kodts2r_edit.do?action=doDelete&id=${model.dbTable}&ks2lk=${record.ks2lk}">
					               		<img src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
				            <tfoot>
							<tr>
								<th class="tableHeaderField" >&nbsp;Endre&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;KS2LK</th>
			                    <th class="tableHeaderField" >&nbsp;KS2FTX&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;KS2NAS&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;KS2MO&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;KS2PRE&nbsp;</th>
								<th class="tableHeaderField">Slett</th>
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
				<form action="tvinnsadmaintenanceimport_sad002_kodts2r_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> <%-- this value is set in AJAX in order to know if the SAVE = ADD or UPDATE --%>
					<input type="hidden" name="action" id=action value="doUpdate">
					<table width="70%" cellspacing="1" border="0" align="left">
			    	    <tr>
						<td class="text12" title="KS2LK">&nbsp;<font class="text14RedBold" >*</font>Kode</td>
						<td class="text12" title="KS2FTX">&nbsp;<font class="text14RedBold" >*</font>Fritekst</td>
						<td class="text12" title="KS2NAS">&nbsp;Språk</td>
						<td class="text12" title="KS2MO">&nbsp;M/O</td>
						<td class="text12" title="KS2PRE">&nbsp;Pref.</td>
						
						</tr>
						<tr>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="ks2lk" id="ks2lk" size="3" maxlength="2" value='${model.record.ks2lk}'></td>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="ks2ftx" id="ks2ftx" size="55" maxlength="55" value='${model.record.ks2ftx}'></td>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="ks2nas" id="ks2nas" size="3" maxlength="2" value='${model.record.ks2nas}'></td>
						<td>
			 				<select class="inputTextMediumBlueMandatoryField" name="ks2mo" id="ks2mo" >
			 				  <option value="">-velg-</option>
							  <option value="M"<c:if test="${model.record.ks2mo == 'M'}"> selected </c:if> >M</option>
							  <option value="O"<c:if test="${model.record.ks2mo == 'O'}"> selected </c:if> >O</option>
							  <option value="S"<c:if test="${model.record.ks2mo == 'S'}"> selected </c:if> >S</option>
							</select>
		 				</td>
		 				<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="ks2pre" id="ks2pre" size="2" maxlength="1" value='${model.record.ks2pre}'></td>
						
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

