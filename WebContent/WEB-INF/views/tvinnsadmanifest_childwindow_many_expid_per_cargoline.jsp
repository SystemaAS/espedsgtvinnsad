<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmanifest_childwindow_many_expid_per_cargoline.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	.ui-datepicker { font-size:9pt;}
	</style>
	
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
				
				<div id="Title" class="text16">
					<img style="vertical-align: middle;" src="resources/images/add.png" width="14px" height="14px" border="0" alt="create new" >
					&nbsp;<b>Lage flere svenske eksportId&nbsp;</b></div>
				<table class="tableBorderWithRoundCorners" >
					<tr>
			 		<td valign="top" >
 						<table>
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
	 						<table id="mainList" style="width:90%;" >
		 						<tr class="tableHeaderField" >
			 						<th align="left" class="text14">Lnr.</th>
			 						<th align="left" class="text14">Type</th>
			 						<th align="left" class="text14">Eksp.id</th>
			 						<th align="left" class="text14">Sv.Exp.Avd</th>
			 						<th align="left" class="text14">Sv.Exp.Oppdrag</th>
			 						<th align="left" class="text14">Sert.</th>
			 						<th align="left" class="text14">Slett</th>
		 						</tr>
	 							<c:forEach items="${model.list}" var="record" varStatus="counter">
			 						<tr class="text14 tableRow">
				 						<td class="tableCellFirst">${record.cmli}</td>
				 						<td class="tableCell" >${record.cmetyp}&nbsp;${record.cmetypt}</td>
				 						<td class="tableCell" >${record.cmeid}</td>
				 						<td class="tableCell" >${record.cmavde}</td>
				 						<td class="tableCell" >${record.cmtdne}</td>
				 						<td class="tableCell" >${record.cmeser}</td>
				 						<td width="2%" class="tableCell" >
				 						<a style="display: block; width: 100%; height: 100%;" class="removeLink" id="removeLink${counter.count}" runat="server" href="#">
											<img src="resources/images/delete.gif" border="0" alt="remove">
										</a>
										<div style="display: none;" class="clazz_dialog" id="dialogRemove${counter.count}" title="Dialog">
											<form action="tvinnsadmanifest_childwindow_many_expid_delete.do" name="deleteForm${counter.count}" id="deleteForm${counter.count}" method="post">
											 	<input type="hidden" name="wsavd${counter.count}" id="wsavd${counter.count}" value="${model.wsavd}">
											 	<input type="hidden" name="wsopd${counter.count}" id="wsopd${counter.count}" value="${model.wsopd}">
											 	<input type="hidden" name="currentCmli${counter.count}" id="currentCmli${counter.count}" value="${record.cmli}">
											 	<input type="hidden" name="currentCmtdn${counter.count}" id="currentCmtdn${counter.count}" value="${record.cmtdn}">
											 	<input type="hidden" name="currentCmavd${counter.count}" id="currentCmavd${counter.count}" value="${record.cmavd}">
											 	<p class="text14" >Er du sikker på at du vil slette denne?</p>
												
											</form>
										</div>
										</td>
									</tr>
			 					</c:forEach>
			 				</table>
		 				</td>
						</tr>
					</c:if>
					<tr height="10"><td>&nbsp;</td></tr>
					<tr>
					  <td colspan="2" width="50%" valign="top">
						<form action="tvinnsadmanifest_childwindow_many_expid_edit.do?action=doSave" name="mainForm" id="mainForm" method="post"  >
						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
						<input type="hidden" name="wsavd" id="wsavd" value="${model.wsavd}">
						<input type="hidden" name="wsopd" id="wsopd" value="${model.wsopd}">
						<input type="hidden" name="mode" id="mode" value="A">
						<input type="hidden" name="newLineCounter" id="newLineCounter" value="${model.newLineCounter}">
						
						<table id="tblSeExportId" width="100%"  border="0" cellspacing="1" cellpadding="0">
			 			<tr>
			 				<td class="text14">&nbsp;<span title="cmli">Lnr.</span></td>
			 				<td class="text14">&nbsp;<span title="cmetyp">Eksp.type<font class="text16RedBold" >*</font></span></td>
							<td class="text14">&nbsp;<span title="cmeid">Eksp.id<font class="text16RedBold" >*</font></span></td>
							<td class="text14">&nbsp;<span title="cmeser">Sert.</span></td>
							<td class="text14">&nbsp;<span title="cmavde">Eksp.avd.SE<font class="text16RedBold" >*</font></span></td>
							<td class="text14">&nbsp;<span title="cmtdne">Eksp.oppd.SE<font class="text16RedBold" >*</font></span></td>
							
						</tr>
						<tr>
							<td class="text14"><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="cmli" id="cmli" size="5" maxlength="5" value="${model.newLineCounter}"></td>
			 				<td class="text14">
				 				<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlue" name="cmetyp" id="cmetyp" >
			 						<option value="">-velg-</option>
				 				  	<c:forEach var="record" items="${model.etTypeList}" >
			                       	 	<option title="${record.kftxt}" value="${record.kfkod}" <c:if test="${model.record.cmetyp == record.kfkod}"> selected </c:if> >${record.kfkod}&nbsp;${record.kftxt}</option>
									</c:forEach>
								</select>
				 			</td>
				 			<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlue" name="cmeid" id="cmeid" size="21" maxlength="18" value="${model.record.cmeid}"></td>
				 			<td class="text14">
				 				<select class="inputTextMediumBlue" name="cmeser" id="cmeser" >
				 					<option value="">-velg-</option>
			 						<option value="N">Nei</option>
	 		 				  		<option value="J">Ja</option>
								</select>
				 			</td>
				 			<td class="text14"><input onKeyPress="return numberKey(event)" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlue toggleDirektfortolling" name="cmavde" id="cmavde" size="5" maxlength="4" value='<c:if test="${model.record.cmavde!='0'}">${model.record.cmavde}</c:if>'></td>
			 				<td class="text14"><input onKeyPress="return numberKey(event)" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlue toggleDirektfortolling" name="cmtdne" id="cmtdne" size="8" maxlength="7" value='<c:if test="${model.record.cmtdne!='0'}">${model.record.cmtdne}</c:if>'></td>
			 				<td><input class="inputFormSubmit" type="submit" name="saveButton" id="saveButton" value='Lagre'></td>
						</tr>
						<tr height="5"><td></td></tr>
						</table>
						</form>
					  </td>
					</tr>
				</table>
       			
		</td>
		</tr>
	</table> 
