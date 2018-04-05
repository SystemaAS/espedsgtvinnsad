<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmaintenance_nctsexport_tr001r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<a id="alinkSadMaintNctsExportGate" tabindex=-1 style="display:block;" href="tvinnsadmaintenance_nctsexport.do">  <!-- kolla url -->
						<font class="tabDisabledLink">&nbsp;TVINN - Vedligehold</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center">
						<font class="tabLink">Koderegister</font>&nbsp;<font class="text12">TR001R</font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tvinnsadmaintenance_nctsexport_tr001r.do?id=${model.dbTable}">
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
	 	    <tr>
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%" class="text12">
					<form action="tvinnsadmaintenance_nctsexport_tr001r.do?id=${model.dbTable}" name="formRecordSearch" id="formRecordSearch" method="POST" >
							<font class="text14RedBold" >*</font><span title="searchTkunik">Kodetype</span>
							<select required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="searchTkunik" id="searchTkunik" >
								<c:forEach var="record" items="${model.transitCodeList}" >
			 				  		<option value="${record.tkunik}"<c:if test="${searchFilterNctsExportKoderegister.searchTkunik == record.tkunik}"> selected </c:if> >${record.description}(${record.tkunik})</option>
								</c:forEach> 
			  				</select>
							Kode&nbsp;
							<input type="text" class="inputTextMediumBlue" name="searchTkkode" id="searchTkkode" size="11" maxlength="10" value='${searchFilterNctsExportKoderegister.searchTkkode}'>
							&nbsp;Beskrivelse(norsk)&nbsp;
							<input type="text" class="inputTextMediumBlue" name="searchTktxtn" id="searchTktxtn" size="25" maxlength="35" value='${searchFilterNctsExportKoderegister.searchTktxtn}'>
							&nbsp;&nbsp;<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submitSearch" id="submitSearch" value='Søk'/>
					</form>
				</td>
			</tr>
	
			<%-- Search Validation errors --%>
			<spring:hasBindErrors name="searchRecord"> <%-- name must equal the command object name in the Controller --%>
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
			</spring:hasBindErrors>
	
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
			                    <th class="tableHeaderField" >&nbsp;Beskrivelse(norsk)&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Beskrivelse(engelsk)&nbsp;</th>
								<th align="center" class="tableHeaderField">Slett</th>
								
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${searchFilterNctsExportKoderegister.searchTkunik}_${record.tkkode}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.tkkode}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.tktxtn}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.tktxte}&nbsp;</font></td>
							   <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadmaintenance_nctsexport_tr001r_edit.do?action=doDelete&id=${model.dbTable}&tkunik=${model.searchTkunik}&tkkode=${record.tkkode}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
							   </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
  <!--  
				            <tfoot>
							<tr>
							    <th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;Endre&nbsp;</th>
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TKKODE</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TKTXTN&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TKTXTE&nbsp;</th>
			                 	<th align="center" class="tableHeaderFieldWhiteBg11">Slett</th>
			                    
			                </tr>  
			                </tfoot> 
 -->
			            </table>
					</td>	
					</tr>
				</table>
				</td>
			</tr>
		    
	 	    <tr height="15"><td>&nbsp;</td></tr>

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
                                    <li>${model.errorMessage}</li>                                    
                                </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr height="2"><td>&nbsp;</td></tr>
			</c:if>
			<tr >
				<td width="5%">&nbsp;</td>
				<td><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" >Lage ny</button></td>
			</tr>
			<tr height="2"><td>&nbsp;</td></tr>
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%">
				<form action="tvinnsadmaintenance_nctsexport_tr001r_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="searchTkunik" id="searchTkunik" value="${searchFilterNctsExportKoderegister.searchTkunik}"> 
					<input type="hidden" name="searchTkkode" id="searchTkkode" value="${searchFilterNctsExportKoderegister.searchTkkode}"> 
					<input type="hidden" name="searchTktxtn" id="searchTktxtn" value="${searchFilterNctsExportKoderegister.searchTktxtn}"> 
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
					<input type="hidden" name="action" id=action value="doUpdate">
						<table width="60%" cellspacing="1" border="0" align="left">
				    	    <tr>
								<td class="text12" title="Kode">&nbsp;<font class="text14RedBold" >*</font>Kode</td>
								<td class="text12" title="Beskrivelse(norsk)">&nbsp;<font class="text14RedBold" >*</font>Beskrivelse(norsk)</td>
								<td class="text12" title="Beskrivelse(engelsk)">&nbsp;<font class="text14RedBold" >*</font>Beskrivelse(engelsk)</td>
								<c:if test="${searchFilterNctsExportKoderegister.searchTkunik == '106'}">
									<td class="text12" title="Avgang">&nbsp;Avgang</td>
									<td class="text12" title="Ankomst">&nbsp;Ankomst</td>
									<td class="text12" title="Transit">&nbsp;Transit</td>
								</c:if>
							</tr>
							<tr>
								<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="tkkode" id="tkkode" size="15" maxlength="10" value='${model.record.tkkode}'></td>
								<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="tktxtn" id="tktxtn" size="40" maxlength="35" value='${model.record.tktxtn}'></td>
								<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="tktxte" id="tktxte" size="40" maxlength="35" value='${model.record.tktxte}'></td>
								
								<c:if test="${searchFilterNctsExportKoderegister.searchTkunik == '106'}">
								<td >
				            		<select class="inputTextMediumBlue" name="tkavg" id="tkavg">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="tkavg" items="${model.tkavgList}" >
					 				  		<option value="${tkavg}"<c:if test="${model.record.tkavg == tkavg}"> selected </c:if> >${tkavg}</option>
										</c:forEach>  
									</select>
								</td>																	

								<td >
				            		<select class="inputTextMediumBlue" name="tkank" id="tkank">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="tkank" items="${model.tkankList}" >
					 				  		<option value="${tkank}"<c:if test="${model.record.tkank == tkank}"> selected </c:if> >${tkank}</option>
										</c:forEach>  
									</select>
								</td>																	
	
								<td >
				            		<select class="inputTextMediumBlue" name="tktrs" id="tktrs">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="tktrs" items="${model.tktrsList}" >
					 				  		<option value="${tktrs}"<c:if test="${model.record.tktrs == tktrs}"> selected </c:if> >${tktrs}</option>
										</c:forEach>  
									</select>
								</td>																	
								</c:if>
								
								<td>
									<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" value='Lagre'/>
								</td>
							</tr>
							<tr height="3">
								<td></td>
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

