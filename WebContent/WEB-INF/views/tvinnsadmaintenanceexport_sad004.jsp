<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmaintenanceexport_sad004.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<a id="alinkSadMaintImportGate" tabindex=-1 style="display:block;" href="tvinnsadmaintenanceexport.do">
						<font class="tabDisabledLink">&nbsp;TVINN - Vedlikehold</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="30%" valign="bottom" class="tab" align="center">
						<font class="tabLink">Kunders vareregister</font>&nbsp;<font class="text12">SAD004 / SADL</font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tvinnsadmaintenanceimport_sad004r.do?id=${model.dbTable}">
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
					<form action="tvinnsadmaintenanceexport_sad004.do?id=${model.dbTable}" name="formRecord" id="formRecord" method="POST" >
					Kundenr&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchKundnr" id="searchKundnr" size="9" maxlength="8" value='${model.kundnr}'>
					<%--
					&nbsp;Søkebegrep&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchTaalfa" id="searchTaalfa" size="15" maxlength="25" value='${model.taalfa}'>
					--%>
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
								<th class="tableHeaderField" >&nbsp;Varenr.&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Beskrivelse&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;R31&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;L/F&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Tariffnr.&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Tn&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Pref.&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Vekt&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;PVA&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Tollsats&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;MF&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Avgift&nbsp;</th>
								<th align="center" class="tableHeaderField">Slett</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${record.slalfa}_${record.slknr}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.slalfa}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.sltxt}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.r31}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.sloppl}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.sltanr}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.sltn}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.pref}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.slvekt}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.slpva}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.slsats}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.mf}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.slkdae}&nbsp;${record.slkdse}</font></td>
				               
				               <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadmaintenanceexport_sad004_edit.do?action=doDelete&id=${model.dbTable}&slalfa=${record.slalfa}&slknr=${record.slknr}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
				            </tr> 
				            				            
				            </c:forEach>
				            				            
				            </tbody>
				            <tfoot>
							<tr>
							    <th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;Endre&nbsp;</th>
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SLALFA</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SLTXT&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;R31&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SLOPPL&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SLTANR&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SLTN&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;PREF&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SLVEKT&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SLPVA&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SLSATS&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;MF&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;SLKDAÆ/SLKDSÆ&nbsp;</th>
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
				<form action="tvinnsadmaintenanceexport_sad004_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
					<input type="hidden" name="action" id=action value="doUpdate">
					<input type="hidden" name="slknr" id=slknr value="${model.kundnr}">
					
					<table width="99%" cellspacing="1" border="0" align="left">
			    	    <tr>
							<td class="text12" title="SLALFA">&nbsp;<font class="text14RedBold" >*</font>Varenr.</td>
							<td class="text12" title="SLTXT">&nbsp;Beskrivelse</td>
							<td class="text12" title="SLTXT(21)">&nbsp;R31</td>
							<td class="text12" title="SLOPPL">&nbsp;<font class="text14RedBold" >*</font>L/F</td>
							<td class="text12" title="SLTANR">&nbsp;<font class="text14RedBold" >*</font>Tariffnr.</td>
							<td class="text12" title="SLTN">&nbsp;Tn</td>
							<td class="text12" title="SLTXT(22)">&nbsp;Pref.</td>
							<td class="text12" title="SLVEKT">&nbsp;Vekt</td>
							<td class="text12" title="SLPVA">&nbsp;PVA</td>
							<td class="text12" title="SLSATS">&nbsp;Tollsats</td>
							<td class="text12" title="SLTXT(23)">&nbsp;MF</td>
							<td class="text12" title="SLKDAÆ/SLKDSÆ">&nbsp;Avgift(kode/sekv)</td>
							
						</tr>
						<tr>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="slalfa" id="slalfa" size="28" maxlength="28" value='${model.record.slalfa}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="sltxt" id="sltxt" size="20" maxlength="20" value='${model.record.sltxt}'></td>
						
						<td>
		            		<select class="inputTextMediumBlueMandatoryField" name="r31" id="r31">
		 						<option value="">-velg-</option>
			 				  	<c:forEach var="r31" items="${model.r31List}" >
			 				  		<option value="${r31}"<c:if test="${model.record.r31 == r31}"> selected </c:if> >${r31}</option>
								</c:forEach>  
							</select>
						</td>
						
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="sloppl" id="sloppl" size="3" maxlength="2" value='${model.record.sloppl}'></td>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="sltanr" id="sltanr" size="9" maxlength="8" value='${model.record.sltanr}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="sltn" id="sltn" size="2" maxlength="1" value='${model.record.sltn}'></td>
						
						<td >
							<select class="inputTextMediumBlueMandatoryField" name="pref" id="pref">
		 						<option value="">-velg-</option>
			 				  	<c:forEach var="kalle" items="${model.kalle}" >
			 				  		<option value="${kalle.ks6pre}"<c:if test="${model.record.pref == kalle.ks6pre}"> selected </c:if> >${kalle.ks6pre}</option>
								</c:forEach>  
							</select>						
						</td>											
						<td ><input type="text" class="inputTextMediumBlue" name="slvekt" id="slvekt" size="13" maxlength="12" value='${model.record.slvekt}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="slpva" id="slpva" size="2" maxlength="1" value='${model.record.slpva}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="slsats" id="slsats" size="8" maxlength="7" value='${model.record.slsats}'></td>						
						<td >
		            		<select class="inputTextMediumBlueMandatoryField" name="mf" id="mf">
		 						<option value="">-velg-</option>
			 				  	<c:forEach var="mf" items="${model.mfList}" >
			 				  		<option value="${mf}"<c:if test="${model.record.mf == mf}"> selected </c:if> >${mf}</option>
								</c:forEach>  
							</select>
						</td>																	
						<td >
							<input type="text" class="inputTextMediumBlue" name="slkdae" id="slkdae" size="3" maxlength="2" value='${model.record.slkdae}'>
							<input type="text" class="inputTextMediumBlue" name="slkdse" id="slkdse" size="4" maxlength="3" value='${model.record.slkdse}'>
						</td>
						</tr>
						
						<tr>
							<td class="text12" title="SLTO">&nbsp;Till.Opplysn.</td>
							<td class="text12" title="SLCREF">&nbsp;Ref.</td>
						</tr>
						<tr>
							<td ><input type="text" class="inputTextMediumBlue" name="slto" id="slto" size="30" maxlength="45" value='${model.record.slto}'></td>
							<td><input type="text" class="inputTextMediumBlue" name="slcref" id="slcref" size="4" maxlength="3" value='${model.record.slcref}'></td>
							<td>
								<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" value='Lagre'/>
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

