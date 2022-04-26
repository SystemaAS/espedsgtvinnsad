<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmanifest_childwindow_many_expid_per_cargoline.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" cellspacing="0" border="0" cellpadding="0">
		<tr height="5"><td colspan="2"></td></tr>
		
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
			<%-- ============================================  --%>
          	<%-- Here we have the search  popup window --%>
          	<%-- ============================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<form name="mainForm" id="mainForm" >
				<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
				<input type="hidden" name="wsavd" id="wsavd" value="${model.wsavd}">
				<input type="hidden" name="wsopd" id="wsopd" value="${model.wsopd}">
				<div id="Title" class="text16">
					<img style="vertical-align: middle;" src="resources/images/add.png" width="14px" height="14px" border="0" alt="create new" >
					&nbsp;<b>Lage flere svenske eksportId&nbsp;</b></div>
				<table class="tableBorderWithRoundCorners" >
					<tr>
			 		<td valign="top" >
 						<table id="mainList" class="display compact cell-border" >
 							<tr height="5"><td>&nbsp;</td></tr>
 							<tr>
 								<td class="text14"><b>Avd</b>&nbsp;${model.wsavd}</td>
 								<td class="text14"><b>Oppdrag</b>&nbsp;${model.wsopd}</td>
 								<td>
 									<input tabindex=-1 class="inputFormSubmitStd" type="button" name="newButton" id="newButton" value='Lage ny'>
 								</td>
 							</tr>
 						</table>
	 				</td>
	 				</tr>
	 				<c:if test="${not empty model.list}">		
						<tr>
				 		<td valign="top" class="text14">
	 						<table id="mainList" class="display compact cell-border" >
		 						<tr class="tableHeaderField" >
			 						<th align="left" class="text14">Lnr.</th>
			 						<th align="left" class="text14">Type</th>
			 						<th align="left" class="text14">Eksp.id</th>
			 						<th align="left" class="text14">TODO</th>
			 						<th align="left" class="text14">Sv.Exp.Avd</th>
			 						<th align="left" class="text14">Sv.Exp.Oppdrag</th>
		 						</tr>
	 							<c:forEach items="${model.list}" var="record" varStatus="counter">
			 						<tr class="text14 tableRow">
				 						<td class="tableCellFirst">${record.cmli}</td>
				 						<td class="tableCell" >${record.cmetyp}&nbsp;${record.cmetypt}</td>
				 						<td class="tableCell" >${record.cmeid}</td>
				 						<td class="tableCell" >todo</td>
				 						<td class="tableCell" >${record.cmavde}</td>
				 						<td class="tableCell" >${record.cmtdne}</td>
									</tr>
			 					</c:forEach>
			 				</table>
		 				</td>
						</tr>
					</c:if>
					
					<tr>
						<td colspan="2" width="50%" valign="top">
						<table id="tblSeExportId" width="100%"  border="0" cellspacing="1" cellpadding="0">
			 			<tr>
			 				<td class="text14">&nbsp;<span title="cmli">Lnr.</span></td>
			 				<td class="text14">&nbsp;<span title="cmavde">SE Eksport avd.<font class="text16RedBold" >*</font></span></td>
							<td class="text14">&nbsp;<span title="cmtdne">SE Eksport oppdrag<font class="text16RedBold" >*</font></span></td>
							<td class="text14">&nbsp;<span title="cmetyp">Eksp.type<font class="text16RedBold" >*</font></span></td>
							<td class="text14">&nbsp;<span title="cmeid">Eksp.id<font class="text16RedBold" >*</font></span></td>
							<td class="text14">&nbsp;<span title="cmeser">Sert.</span></td>
						</tr>
						<tr>
							<td class="text14"><input tabindex=-1 type="text" class="inputTextReadOnly toggleDirektfortolling" name="cmli" id="cmli" size="5" maxlength="5" value="${model.record.cmli}"></td>
			 				<td class="text14"><input type="text" class="inputTextMediumBlue toggleDirektfortolling" name="cmavde" id="cmavde" size="5" maxlength="4" value='<c:if test="${model.record.cmavde!='0'}">${model.record.cmavde}</c:if>'></td>
			 				<td class="text14"><input type="text" class="inputTextMediumBlue toggleDirektfortolling" name="cmtdne" id="cmtdne" size="8" maxlength="7" value='<c:if test="${model.record.cmtdne!='0'}">${model.record.cmtdne}</c:if>'></td>
			 				<td class="text14">
				 				<select class="inputTextMediumBlue toggleDirektfortolling" name="cmetyp" id="cmetyp" >
			 						<option value="">-velg-</option>
				 				  	<c:forEach var="record" items="${model.etTypeList}" >
			                       	 	<option title="${record.kftxt}" value="${record.kfkod}" <c:if test="${model.record.cmetyp == record.kfkod}"> selected </c:if> >${record.kfkod}&nbsp;${record.kftxt}</option>
									</c:forEach>
								</select>
				 			</td>
				 			<td class="text14"><input type="text" class="inputTextMediumBlue toggleDirektfortolling" name="cmeid" id="cmeid" size="21" maxlength="18" value="${model.record.cmeid}"></td>
				 			<td class="text14">
				 				<select class="inputTextMediumBlue" name="cmeser" id="cmeser" >
				 					<option value="">-velg-</option>
			 						<option value="N">Nei</option>
	 		 				  		<option value="J">Ja</option>
								</select>
				 			</td>
				 			<td><input class="inputFormSubmit" type="button" name="saveButton" id="saveButton" value='Lagre'></td>
						</tr>
						<tr height="5"><td></td></tr>
						</table>
						</td>
					</tr>
				</table>
       			</form>
		</td>
		</tr>
	</table> 
