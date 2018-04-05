<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
<SCRIPT type="text/javascript" src="resources/js/tvinnsadmaintenanceexport_sad015.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<font class="tabLink">Fiskeavgifter</font>&nbsp;<font class="text12">SAD015 / SADAVGE</font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tvinnsadmaintenanceexport_sad015.do?id=${model.dbTable}">
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
					<form action="tvinnsadmaintenanceexport_sad015.do?id=${model.dbTable}" name="formRecord" id="formRecord" method="POST" >
					Tariffnr&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchAgtanr" id="searchAgtanr" size="9" maxlength="8" value='${model.agtanr}'>					
					&nbsp;&nbsp;<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submitSearch" id="submitSearch" value='Søk'/>
										
					</form>
				</td>
			</tr>
			
			<%-- list component --%>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
				<table id="containerdatatableTable" width="98%" cellspacing="1" border="0" align="left" >
			    	    <tr>
						<td class="text11">
						<table id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;Endre&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Tariffnr.&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Beskriv.&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Av&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Sekv.&nbsp;</th>
			                    <th class="tableHeaderField">&nbsp;Fdato&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Tdato&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;EU&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;%/P&nbsp;</th>
			                    <th class="tableHeaderField">&nbsp;Sats&nbsp;</th>
			                    <th class="tableHeaderField">&nbsp;Status&nbsp;</th>
			                    
			                </tr>  
			                </thead> 
			                <tbody >  
	                       	   <c:forEach var="record" items="${model.list}" varStatus="counter">	                       	   
	                       	   
				               <tr class="tableRow" height="20" >
					               <td id="recordUpdate_${record.agtanr}_${record.agakd}_${record.agskv}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
			               				<img src="resources/images/update.gif" border="0" alt="edit">
					               </td>
					               				               				               
					               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.agtanr}&nbsp;</font></td>
					               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.taalfa}&nbsp;</font></td>
					               <td align="center" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.agakd}&nbsp;</font></td>
			                       <td align="center" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.agskv}&nbsp;</font></td>
					               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.agdtfNO}&nbsp;</font></td>
			                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.agdttNO}&nbsp;</font></td>
			                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.agkd}&nbsp;</font></td>
			                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.agpp}&nbsp;</font></td>				                       
			                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.agsats}&nbsp;</font></td>
			                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
			                       		<c:choose> 
				    					<c:when test="${record.agaktk=='A'}">
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
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;AGTANR</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TAALFA&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;AGAKD&nbsp;</th>
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;AGSKV&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;AGDTF&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;AGDTT&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;AGKD&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;AGPP&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;AGSATS&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;AGAKTK&nbsp;</th>
			                    
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
				<form action="tvinnsadmaintenanceexport_sad015_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
					<input type="hidden" name="action" id=action value="doUpdate">
					<input type="hidden" name="searchAgtanr" id=searchAgtanr value="${model.agtanr}">
					
					
					<table width="98%" cellspacing="1" border="0" align="left">
			    	    <tr>
							<td class="text12" title="AGTANR">&nbsp;<font class="text14RedBold" >*</font>Tariffnr.</td>
							<td class="text12" title="TAALFA">&nbsp;Beskrivelse</td>
							<td class="text12" title="AGAKD">&nbsp;Avg.</td>
							<td class="text12" title="AGSVK">&nbsp;<font class="text14RedBold" >*</font>Sekv.</td>
							<td class="text12" title="AGDTF">&nbsp;<font class="text14RedBold" >*</font>F.o.m dato</td>
							<td class="text12" title="AGDTT">&nbsp;<font class="text14RedBold" >*</font>T.o.m dato</td>
							<td class="text12" title="AGKD">&nbsp;EU</td>
							<td class="text12" title="AGPP">&nbsp;%/P</td>
							<td class="text12" title="AGSATS">&nbsp;<font class="text14RedBold" >*</font>Sats</td>
							<td class="text12" title="AGAKTK">&nbsp;Status</td>
						</tr>
						<tr>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="agtanr" id="agtanr" size="9" maxlength="8" value='${model.record.agtanr}'></td>
						<td ><input readonly type="text" class="inputTextReadOnly" name="taalfa" id="taalfa" size="15" maxlength="30" value='${model.record.taalfa}'></td>
						<td ><input readonly type="text" class="inputTextReadOnly" name="agakd" id="agakd" size="3" maxlength="2" value='${model.record.agakd}'></td>				
						<td ><input type="text" class="inputTextMediumBlueMandatoryField"" name="agskv" id="agskv" size="4" maxlength="3" value='${model.record.agskv}'></td>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="agdtfNO" id="agdtfNO" size="9" maxlength="8" value='${model.record.agdtfNO}'></td>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="agdttNO" id="agdttNO" size="9" maxlength="8" value='${model.record.agdttNO}'></td>					
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="agkd" id="agkd" size="2" maxlength="1" value='${model.record.agkd}'></td>	
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="agpp" id="agpp" size="2" maxlength="1" value='${model.record.agpp}'></td>						
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="agsats" id="agsats" size="6" maxlength="5" value='${model.record.agsats}'></td>
						<td >
							<select name="agaktk" id="agaktk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.agaktk == 'A'}"> selected </c:if> >AKTIV</option>
							  	<option value="I"<c:if test="${ model.record.agaktk == 'I'}"> selected </c:if> >INAKTIV</option>
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

