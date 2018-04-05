<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmaintenanceimport_syft19r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<font class="tabLink">Gyldige likvidit.koder</font>&nbsp;<font class="text12">SYFT19 / KODTLIK</font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tvinnsadmaintenanceimport_syft19r.do?id=${model.dbTable}">
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
								<th align="center" width="2%" class="tableHeaderFieldFirst" >&nbsp;KLISTA&nbsp;</th>
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;Endre&nbsp;</th>
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;KLIUNI</th>
			                    <th align="center" width="2%" class="tableHeaderField" >&nbsp;Likvid.kode&nbsp;</th>
			                    <th align="left" class="tableHeaderField" >&nbsp;Beskrivelse&nbsp;</th>
								<th align="center" width="2%" class="tableHeaderField">&nbsp;Stopp?&nbsp;</th>
			                    <th align="center" class="tableHeaderField" >&nbsp;KLIXXX&nbsp;</th>
			                    <th align="center" class="tableHeaderField">Slett</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				              
				               <td align="center" width="2%" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.klista}&nbsp;</font></td>
				               <td id="recordUpdate_${record.klikod}" onClick="getRecord(this);" align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.kliuni}&nbsp;</font></td>
				               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.klikod}&nbsp;</font></td>
		                       <td align="left"  class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.klinav}&nbsp;</font></td>
				               <td align="center"width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.klisto}&nbsp;</font></td>
		                       <td align="center"class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.klixxx}&nbsp;</font></td>
		                       <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadmaintenanceimport_syft19r_edit.do?action=doDelete&id=${model.dbTable}&klikod=${record.klikod}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
				            <tfoot>
							<tr>
								<th align="center" width="2%" class="tableHeaderFieldFirstWhiteBg11" >&nbsp;KLISTA&nbsp;</th>
								<th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;Endre&nbsp;</th>
								<th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;KLIUNI</th>
			                    <th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;KLIKOD&nbsp;</th>
			                    <th align="left" class="tableHeaderFieldWhiteBg11" >&nbsp;KLINAV&nbsp;</th>
								<th align="center" width="2%" class="tableHeaderFieldWhiteBg11">&nbsp;KLISTO&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;KLIXXX&nbsp;</th>
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
				<form action="tvinnsadmaintenanceimport_syft19r_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value=""> <%-- this value is set in AJAX in order to know if the SAVE = ADD or UPDATE --%>
					<input type="hidden" name="action" id=action value="doUpdate">
					<table width="40%" cellspacing="1" border="0" align="left">
			    	    <tr>
						<td class="text12" title="KLIKOD">&nbsp;<font class="text14RedBold" >*</font>Likvid.kode</td>
						<td class="text12" title="KLINAV">&nbsp;<font class="text14RedBold" >*</font>Beskrivelse</td>
						<td class="text12" title="KLISTO">&nbsp;<font class="text14RedBold" >*</font>Stopp?</td>
						</tr>
						<tr>
						<td ><input type="text" class="inputTextMediumBlueUPPERCASEMandatoryField" name="klikod" id="klikod" size="2" maxlength="1" value='${model.record.klikod}'></td>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="klinav" id="klinav" size="25" maxlength="30" value='${model.record.klinav}'></td>
						<td >
							<select class="inputTextMediumBlueMandatoryField" name="klisto" id="klisto">
			            		<option value="">-velg-</option>
			 				  	<option value="J" <c:if test="${ model.record.klisto == 'J'}"> selected </c:if> >Ja</option>
			 				  	<option value="N" <c:if test="${ model.record.klisto == 'N'}"> selected </c:if> >Nej</option>
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

