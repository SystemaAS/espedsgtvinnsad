<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmaintenancefelles_sad010r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<font class="tabLink">Tolltariff</font>&nbsp;<font class="text12">SAD010 / TARI</font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tvinnsadmaintenancefelles_sad010r.do?id=${model.dbTable}">
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
					<form action="tvinnsadmaintenancefelles_sad010r.do?id=${model.dbTable}" name="formRecord" id="formRecord" method="POST" >
					Tariffnr&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchTatanr" id="searchTatanr" size="9" maxlength="8" value='${model.tatanr}'>
					&nbsp;Søkebegrep&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchTaalfa" id="searchTaalfa" size="15" maxlength="25" value='${model.taalfa}'>
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
								<th class="tableHeaderField" >&nbsp;Tariffnr.&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Ordinær&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;PVA&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;EFTA&nbsp;</th>
			                    <th class="tableHeaderField">&nbsp;PVA&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;EF&nbsp;</th>
			                    <th class="tableHeaderField">&nbsp;PVA&nbsp;</th>
			                    <th class="tableHeaderField">&nbsp;Stk&nbsp;</th>
			                    <th class="tableHeaderField">&nbsp;Søkebegrep&nbsp;</th>
			                    <th class="tableHeaderField">&nbsp;Text&nbsp;</th>
			                    <th class="tableHeaderField">&nbsp;Enh&nbsp;</th>
			                    <th class="tableHeaderField">&nbsp;Fdat.&nbsp;</th>
			                    <th class="tableHeaderField">&nbsp;Tdat.&nbsp;</th>
			                    
			                    <th align="center" class="tableHeaderField">Slett</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${record.tatanr}_${record.taalfa}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.tatanr}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.taordb}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.taordk}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.taeftb}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.taeftk}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.taefb}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.taefk}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.tastk}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.taalfa}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.tatxt}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.taenhe}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.tadatoNO}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.tadtsNO}&nbsp;</font></td>
		                       <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadmaintenancefelles_sad010r_edit.do?action=doDelete&id=${model.dbTable}&tatanr=${record.tatanr}&taalfa=${record.taalfa}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
				            <tfoot>
							<tr>
							    <th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;Endre&nbsp;</th>
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TATANR</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TAORDB&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TAORDK&nbsp;</th>
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TAEFTB&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TAEFTK&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;TAEFB&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TAEFK&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;TASTK&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;TAALFA&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;TATXT&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;TAENHE&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;TADATO&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;TADTS&nbsp;</th>
			                    
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
				<form action="tvinnsadmaintenancefelles_sad010r_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
					<input type="hidden" name="action" id=action value="doUpdate">
					<input type="hidden" name="taalfaOrig" id=taalfaOrig value="${model.record.taalfaOrig}">
					
					<table width="98%" cellspacing="1" border="0" align="left">
			    	    <tr>
							<td class="text12" title="TATANR">&nbsp;<font class="text14RedBold" >*</font>Tariffnr.</td>
							<td class="text12" title="TAALFA">&nbsp;<font class="text14RedBold" >*</font>Søkebgrep</td>
							<td class="text12" title="TADATO">&nbsp;<font class="text14RedBold" >*</font>F.o.m dato</td>
							<td class="text12" title="TADTR">&nbsp;Opd.dato</td>
							<td class="text12" title="TADATO">&nbsp;T.o.m dato</td>
							<td class="text12" title="TASTK">&nbsp;Stk</td>
							<td class="text12" title="TATXT">&nbsp;Text</td>
							<td class="text12" title="TAENHE">&nbsp;Enhet</td>
							<td class="text12" title="TAREST">&nbsp;Regul.(NHLE)</td>
							<td class="text12" title="TAKAPA">&nbsp;Øvrige kap.</td>
						</tr>
						<tr>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="tatanr" id="tatanr" size="9" maxlength="8" value='${model.record.tatanr}'></td>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="taalfa" id="taalfa" size="15" maxlength="15" value='${model.record.taalfa}'></td>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="tadatoNO" id="tadatoNO" size="8" maxlength="8" value='${model.record.tadatoNO}'></td>
						<td ><input readOnly type="text" class="inputTextReadOnly" name="tadtrNO" id="tadtrNO" size="8" maxlength="8" value='${model.record.tadtrNO}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="tadtsNO" id="tadtsNO" size="8" maxlength="8" value='${model.record.tadtsNO}'></td>
						<td >
							<select name="tastk" id="tastk">
        		    			<option value="">-velg-</option>
							  	<option value="J"<c:if test="${ model.record.tastk == 'J'}"> selected </c:if> >Ja</option>
							</select>
						</td>
						<td ><input type="text" class="inputTextMediumBlue" name="tatxt" id="tatxt" size="25" maxlength="80" value='${model.record.tatxt}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="taenhe" id="taenhe" size="4" maxlength="3" value='${model.record.taenhe}'></td>
						<td align="center"><input type="text" class="inputTextMediumBlue" name="tarest" id="tarest" size="3" maxlength="2" value='${model.record.tarest}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="takapa" id="takapa" size="5" maxlength="4" value='${model.record.takapa}'></td>
						
						<td>
							<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" value='Lagre'/>
						</td>
						</tr>
						<tr height="3"><td></td>
					</table>
					
					
					<table class="tableHeaderField" width="98%" cellspacing="1" border="0" align="left">	
						<%--
						------------------------ 
						 FIRST  SECONDARY LINE
						------------------------ 
						--%>
						<tr>
							<td class="text12" title="TAORDB">&nbsp;Ordinær</td>
							<td class="text12" title="TAORDK">&nbsp;PVA</td>
							<td class="text12" title="TAEFTB">&nbsp;EFTA</td>
							<td class="text12" title="TAEFTK">&nbsp;PVA</td>
							<td class="text12" title="TAEFB">&nbsp;EF</td>
							<td class="text12" title="TAEFK">&nbsp;PVA</td>
							
						</tr>

						<tr>
						<td ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="taordb" id="taordb" size="10" maxlength="10" value='${model.record.taordb}'></td>
						<td >
							<select name="taordk" id="taordk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.taordk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.taordk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.taordk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.taordk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.taordk == 'W'}"> selected </c:if> >W</option>
							</select>
						</td>
						<td ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="taeftb" id="taeftb" size="10" maxlength="10" value='${model.record.taeftb}'></td>
						<td >
							<select name="taeftk" id="taeftk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.taeftk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.taeftk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.taeftk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.taeftk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.taeftk == 'W'}"> selected </c:if> >W</option>
							</select>
						</td>
						<td ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="taefb" id="taefb" size="10" maxlength="10" value='${model.record.taefb}'></td>
						<td >
							<select name="taefk" id="taefk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.taefk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.taefk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.taefk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.taefk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.taefk == 'W'}"> selected </c:if> >W</option>
							</select>
						</td>
						
						</tr>
						<%--
						------------------------ 
						 SECOND  SECONDARY LINE
						------------------------ 
						--%>
						<tr height="10"><td></td></tr>
						<tr >
							<td class="text12" title="TAEOSB">&nbsp;EØS</td>
							<td class="text12" title="TAEOSK">&nbsp;PVA</td>
							<td class="text12" title="TATSJB">&nbsp;Swaziland</td>
							<td class="text12" title="TATSJK">&nbsp;PVA</td>
							<td class="text12" title="TATYRB">&nbsp;Tyrkia</td>
							<td class="text12" title="TATYRK">&nbsp;PVA</td>
							<td class="text12" title="TAISRB">&nbsp;Israel</td>
							<td class="text12" title="TAISRK">&nbsp;PVA</td>
							<td class="text12" title="TAELLB">&nbsp;Singapore</td>
							<td class="text12" title="TAELLK">&nbsp;PVA</td>
							<td class="text12" title="TABULB">&nbsp;Chile</td>
							<td class="text12" title="TABULK">&nbsp;PVA</td>
							
						</tr>
						<tr >
							<td ><input type="text" class="inputTextMediumBlue" name="taeosb" id="taeosb" size="10" maxlength="10" value='${model.record.taeosb}'></td>
							<td>
								<select name="taeosk" id="taeosk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.taeosk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.taeosk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.taeosk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.taeosk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.taeosk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tatsjb" id="tatsjb" size="10" maxlength="10" value='${model.record.tatsjb}'></td>
							<td>
								<select name="tatsjk" id="tatsjk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tatsjk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tatsjk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tatsjk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tatsjk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tatsjk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tatyrb" id="tatyrb" size="10" maxlength="10" value='${model.record.tatyrb}'></td>
							<td>
								<select name="tatyrk" id="tatyrk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tatyrk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tatyrk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tatyrk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tatyrk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tatyrk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="taisrb" id="taisrb" size="10" maxlength="10" value='${model.record.taisrb}'></td>
							<td>
								<select name="taisrk" id="taisrk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.taisrk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.taisrk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.taisrk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.taisrk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.taisrk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="taellb" id="taellb" size="10" maxlength="10" value='${model.record.taellb}'></td>
							<td>
								<select name="taellk" id="taellk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.taellk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.taellk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.taellk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.taellk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.taellk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tabulb" id="tabulb" size="10" maxlength="10" value='${model.record.tabulb}'></td>
							<td>
								<select name="tabulk" id="tabulk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tabulk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tabulk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tabulk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tabulk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tabulk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							
						</tr>
						<%--
						------------------------ 
						 3:d  SECONDARY LINE
						------------------------ 
						--%>
						<tr>
							<td class="text12" title="TAPOLB">&nbsp;Makedonia</td>
							<td class="text12" title="TAPOLK">&nbsp;PVA</td>
							<td class="text12" title="TAROMB">&nbsp;Sør-Korea</td>
							<td class="text12" title="TAROMK">&nbsp;PVA</td>
							<td class="text12" title="TAN05B">&nbsp;Serbia</td>
							<td class="text12" title="TAN05K">&nbsp;PVA</td>
							<td class="text12" title="TAN06B">&nbsp;Albania</td>
							<td class="text12" title="TAN06K">&nbsp;PVA</td>
							<td class="text12" title="TAN07B">&nbsp;Ukraina</td>
							<td class="text12" title="TAN07K">&nbsp;PVA</td>
							<td class="text12" title="TAUNGB">&nbsp;Jordan</td>
							<td class="text12" title="TAUNGK">&nbsp;PVA</td>
						</tr>
						<tr>
							<td ><input type="text" class="inputTextMediumBlue" name="tapolb" id="tapolb" size="10" maxlength="10" value='${model.record.tapolb}'></td>
							<td>
								<select name="tapolk" id="tapolk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tapolk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tapolk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tapolk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tapolk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tapolk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="taromb" id="taromb" size="10" maxlength="10" value='${model.record.taromb}'></td>
							<td>
								<select name="taromk" id="taromk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.taromk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.taromk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.taromk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.taromk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.taromk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan05b" id="tan05b" size="10" maxlength="10" value='${model.record.tan05b}'></td>
							<td>
								<select name="tan05k" id="tan05k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tan05k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tan05k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tan05k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tan05k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tan05k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan06b" id="tan06b" size="10" maxlength="10" value='${model.record.tan06b}'></td>
							<td>
								<select name="tan06k" id="tan06k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tan06k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tan06k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tan06k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tan06k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tan06k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan07b" id="tan07b" size="10" maxlength="10" value='${model.record.tan07b}'></td>
							<td>
								<select name="tan07k" id="tan07k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tan07k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tan07k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tan07k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tan07k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tan07k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="taungb" id="taungb" size="10" maxlength="10" value='${model.record.taungb}'></td>
							<td>
								<select name="taungk" id="taungk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.taungk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.taungk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.taungk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.taungk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.taungk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
						</tr>
						
						<%--
						------------------------ 
						 4:th  SECONDARY LINE
						------------------------ 
						--%>
						<tr>
							<td class="text12" title="TASLOB">&nbsp;Tunisia</td>
							<td class="text12" title="TASLOK">&nbsp;PVA</td>
							<td class="text12" title="TAMULB">&nbsp;Min.uland</td>
							<td class="text12" title="TAMULK">&nbsp;PVA</td>
							<td class="text12" title="TAOULB">&nbsp;Ord.uland</td>
							<td class="text12" title="TAOULK">&nbsp;PVA</td>
							<td class="text12" title="TAGRLB">&nbsp;Grønnland</td>
							<td class="text12" title="TAGRLK">&nbsp;PVA</td>
							<td class="text12" title="TAFERB">&nbsp;Færøyen</td>
							<td class="text12" title="TAFERK">&nbsp;PVA</td>
							<td class="text12" title="TAISTB">&nbsp;IndiaSrTh</td>
							<td class="text12" title="TAISTK">&nbsp;PVA</td>
						</tr>
						<tr>
							<td ><input type="text" class="inputTextMediumBlue" name="taslob" id="taslob" size="10" maxlength="10" value='${model.record.taslob}'></td>
							<td>
								<select name="taslok" id="taslok">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.taslok == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.taslok == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.taslok == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.taslok == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.taslok == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tamulb" id="tamulb" size="10" maxlength="10" value='${model.record.tamulb}'></td>
							<td>
								<select name="tamulk" id="tamulk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tamulk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tamulk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tamulk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tamulk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tamulk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="taoulb" id="taoulb" size="10" maxlength="10" value='${model.record.taoulb}'></td>
							<td>
								<select name="taoulk" id="taoulk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.taoulk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.taoulk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.taoulk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.taoulk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.taoulk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tagrlb" id="tagrlb" size="10" maxlength="10" value='${model.record.tagrlb}'></td>
							<td>
								<select name="tagrlk" id="tagrlk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tagrlk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tagrlk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tagrlk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tagrlk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tagrlk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="taferb" id="taferb" size="10" maxlength="10" value='${model.record.taferb}'></td>
							<td>
								<select name="taferk" id="taferk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.taferk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.taferk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.taferk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.taferk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.taferk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="taistb" id="taistb" size="10" maxlength="10" value='${model.record.taistb}'></td>
							<td>
								<select name="taistk" id="taistk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.taistk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.taistk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.taistk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.taistk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.taistk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
						</tr>
						
						<%--
						------------------------ 
						 5:th  SECONDARY LINE
						------------------------ 
						--%>
						<tr>
							<td class="text12" title="TAMARB">&nbsp;Marokka</td>
							<td class="text12" title="TAMARK">&nbsp;PVA</td>
							<td class="text12" title="TAN08B">&nbsp;Peru</td>
							<td class="text12" title="TAN08K">&nbsp;PVA</td>
							<td class="text12" title="TAN09B">&nbsp;Montenegro</td>
							<td class="text12" title="TAN09K">&nbsp;PVA</td>
							<td class="text12" title="TAN10B">&nbsp;Hong Kong</td>
							<td class="text12" title="TAN10K">&nbsp;PVA</td>
							<td class="text12" title="TAMEXB">&nbsp;México</td>
							<td class="text12" title="TAMEXK">&nbsp;PVA</td>
							<td class="text12" title="TAVGAB">&nbsp;VB Gaza</td>
							<td class="text12" title="TAVGAK">&nbsp;PVA</td>
						</tr>
						<tr>
							<td ><input type="text" class="inputTextMediumBlue" name="tamarb" id="tamarb" size="10" maxlength="10" value='${model.record.tamarb}'></td>
							<td>
								<select name="tamark" id="tamark">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tamark == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tamark == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tamark == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tamark == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tamark == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan08b" id="tan08b" size="10" maxlength="10" value='${model.record.tan08b}'></td>
							<td>
								<select name="tan08k" id="tan08k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tan08k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tan08k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tan08k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tan08k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tan08k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan09b" id="tan09b" size="10" maxlength="10" value='${model.record.tan09b}'></td>
							<td>
								<select name="tan09k" id="tan09k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tan09k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tan09k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tan09k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tan09k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tan09k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan10b" id="tan10b" size="10" maxlength="10" value='${model.record.tan10b}'></td>
							<td>
								<select name="tan10k" id="tan10k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tan10k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tan10k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tan10k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tan10k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tan10k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tamexb" id="tamexb" size="10" maxlength="10" value='${model.record.tamexb}'></td>
							<td>
								<select name="tamexk" id="tamexk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tamexk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tamexk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tamexk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tamexk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tamexk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tavgab" id="tavgab" size="10" maxlength="10" value='${model.record.tavgab}'></td>
							<td>
								<select name="tavgak" id="tavgak">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tavgak == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tavgak == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tavgak == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tavgak == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tavgak == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
						</tr>
						
						<%--
						------------------------ 
						 6:th  SECONDARY LINE
						------------------------ 
						--%>
						<tr>
							<td class="text12" title="TAN01B">&nbsp;Libanon</td>
							<td class="text12" title="TAN01K">&nbsp;PVA</td>
							<td class="text12" title="TAN02B">&nbsp;SACU</td>
							<td class="text12" title="TAN02K">&nbsp;PVA</td>
							<td class="text12" title="TAN03B">&nbsp;Egypt</td>
							<td class="text12" title="TAN03K">&nbsp;PVA</td>
							<td class="text12" title="TAN04B">&nbsp;Canada</td>
							<td class="text12" title="TAN04K">&nbsp;PVA</td>
							<td class="text12" title="TAN11B">&nbsp;BW,NA,SZ</td>
							<td class="text12" title="TAN11K">&nbsp;PVA</td>
							<td class="text12" title="TAN12B">&nbsp;Lavere MIL</td>
							<td class="text12" title="TAN12K">&nbsp;PVA</td>
						</tr>
						<tr>
							<td ><input type="text" class="inputTextMediumBlue" name="tan01b" id="tan01b" size="10" maxlength="10" value='${model.record.tan01b}'></td>
							<td>
								<select name="tan01k" id="tan01k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tan01k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tan01k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tan01k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tan01k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tan01k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan02b" id="tan02b" size="10" maxlength="10" value='${model.record.tan02b}'></td>
							<td>
								<select name="tan02k" id="tan02k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tan02k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tan02k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tan02k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tan02k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tan02k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan03b" id="tan03b" size="10" maxlength="10" value='${model.record.tan03b}'></td>
							<td>
								<select name="tan03k" id="tan03k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tan03k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tan03k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tan03k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tan03k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tan03k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan04b" id="tan04b" size="10" maxlength="10" value='${model.record.tan04b}'></td>
							<td>
								<select name="tan04k" id="tan04k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tan04k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tan04k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tan04k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tan04k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tan04k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan11b" id="tan11b" size="10" maxlength="10" value='${model.record.tan11b}'></td>
							<td>
								<select name="tan11k" id="tan11k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tan11k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tan11k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tan11k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tan11k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tan11k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan12b" id="tan12b" size="10" maxlength="10" value='${model.record.tan12b}'></td>
							<td>
								<select name="tan12k" id="tan12k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tan12k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tan12k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tan12k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tan12k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tan12k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
						</tr>
						
						<%--
						------------------------ 
						 7:th  SECONDARY LINE
						------------------------ 
						--%>
						<tr>
							<td class="text12" title="TAN13B">&nbsp;Reserver</td>
							<td class="text12" title="TAN13K">&nbsp;PVA</td>
							<td class="text12" title="TAN14B">&nbsp;Reserver</td>
							<td class="text12" title="TAN14K">&nbsp;PVA</td>
							<td class="text12" title="TAN15B">&nbsp;Reserver</td>
							<td class="text12" title="TAN15K">&nbsp;PVA</td>
						</tr>
						<tr>
							<td ><input type="text" class="inputTextMediumBlue" name="tan13b" id="tan13b" size="10" maxlength="10" value='${model.record.tan13b}'></td>
							<td>
								<select name="tan13k" id="tan13k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tan13k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tan13k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tan13k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tan13k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tan13k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan14b" id="tan14b" size="10" maxlength="10" value='${model.record.tan14b}'></td>
							<td>
								<select name="tan14k" id="tan14k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tan14k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tan14k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tan14k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tan14k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tan14k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan15b" id="tan15b" size="10" maxlength="10" value='${model.record.tan15b}'></td>
							<td>
								<select name="tan15k" id="tan15k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.tan15k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ model.record.tan15k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ model.record.tan15k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ model.record.tan15k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ model.record.tan15k == 'W'}"> selected </c:if> >W</option>
								</select>
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

