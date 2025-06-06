<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_childwindow_external_master.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			Registrer - Ekstern Dok.nr manuelt </td>
		</tr>
		<tr height="10"><td></td></tr>
		<tr>
		<td colspan="3" valign="top">
		  		
				<table cellspacing="2" align="left" >
				<form name="insertForm" id="insertForm" >
						<input type="hidden" name="etlnrt_insert" id="etlnrt_insert" value="${model.etlnrt}">
					<tr>
						<td class="text14">&nbsp;Dok.nr</td>
						<td class="text14">&nbsp;<input type="text" class="inputText" name="emdkm_insert" id="emdkm_insert" size="25" maxlength="70" value="${model.emdkm_insert}"></td>
						<td class="text14">&nbsp;Dok.type</td>
						<td class="text14">&nbsp;<input type="text" class="inputText" name="emdkmt_insert" id="emdkmt_insert" size="6" maxlength="4" value="${model.emdkmt_insert}"></td>
						<td align="right">&nbsp;<input class="inputFormSubmit" type="button" name="insertButton" id="insertButton" value='Lage ny'></td>		
					</tr>
				</form>	
				</table>
				
		</td>
		</tr>
		
		<tr height="10"><td><hr></td></tr>
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			Søk - Ekstern Dok.nr 
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			</td>
		</tr>
		<tr>
		<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
					
					<tr>
					<td>
						<table>
						<form name="mainForm" id="mainForm" action="tvinnsaddigitollv2_childwindow_external_master.do?action=doInit" method="post">
							<input type="hidden" name="ctype" id="ctype" value="${model.callerType}">
							<input type="hidden" name="etlnrt" id="etlnrt" value="${model.etlnrt}">
							<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
							
							<tr>
								<td class="text14">&nbsp;Date</td>
								<td class="text14">&nbsp;<input type="text" class="inputText" onKeyPress="return numberKey(event)" name="date" id="date" size="10" maxlength="8" value="${model.date}"></td>
								
								<td class="text14">&nbsp;Dok.nr</td>
								<td class="text14">&nbsp;<input type="text" class="inputText" name="emdkm" id="emdkm" size="35" maxlength="50" value="${model.emdkm}"></td>
							
		           				<td align="right">&nbsp;<input class="inputFormSubmit" onClick="setBlockUI(this)" type="submit" name="submit" value='<spring:message code="search.label"/>'></td>
		           			</tr>
		           		</form>
		           		</table>
					</td>
					</tr>
					
													           		
	           		<tr height="10"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="mainList" class="display compact cell-border" width="100%" >
						<thead>
						<tr class="tableHeaderField" >
							<th title="Velg manuelt ..." width="2%" class="tableHeaderFieldFirst12" >
								<img title="select manual" src="resources/images/update.gif" border="0" alt="edit">
							</th>
							<th class="text14" title="emdkm">&nbsp;Dok.nr&nbsp;</th>
							<th class="text14" title="emdkmt">&nbsp;Dok.type&nbsp;</th>
							<th class="text14" title="empro">&nbsp;Ref.Id/Tur&nbsp;</th>
							<th class="text14" title="date">&nbsp;ETA&nbsp;</th>
							<th class="text14" title="time">&nbsp;ETA-Tid&nbsp;</th>
		                    <th class="text14" title="trreforg">&nbsp;Transp.Orgnr&nbsp;</th>
		                    <th class="text14" title="trrefreg">&nbsp;Transp.Regnr&nbsp;</th>
		                    <th class="text14" title="avsna">&nbsp;Avs&nbsp;</th>
		                    <th class="text14" title="avsid">&nbsp;Avs.Orgnr&nbsp;</th>
		                    
		                    <th class="text14" title="motna">&nbsp;Mot&nbsp;</th>
		                    <th class="text14" title="motid">&nbsp;Mot.Orgnr&nbsp;</th>
							<th width="2%" class="text14" title="Fjern record" >Slett</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.mainList}" varStatus="counter">    
			               
			               <tr class="text14">
			               	   <td width="2%" align="center" style="cursor:pointer;" class="text14MediumBlue" id="emdkm${record.emdkm}@trreforg${record.trreforg}@ctype${model.callerType}@emdkmt${record.emdkmt}@avsid${record.avsid}" >
				          	   		<img title="select manual edit" src="resources/images/update.gif" border="0" alt="edit">
				          	   </td>
				          	   <%--
				               <td nowrap style="cursor:pointer;" class="text14MediumBlue" id="emdkm${record.emdkm}@trreforg${record.trreforg}@ctype${model.callerType}@emdkmt${record.emdkmt}@avsid${record.avsid}" >
			               			${record.emdkm}
				               </td>
				                --%>
				               <td nowrap class="text14MediumBlue">&nbsp;${record.emdkm}</td>
				               <td class="text14">&nbsp;${record.emdkmt}</td>
			               	   <td class="text14">&nbsp;<c:if test="${'null'!= record.empro}">${record.empro}</c:if></td>
			               	   <td class="text14">&nbsp;<c:if test="${'null'!= record.date}">${record.date}</c:if></td>
			               	   <td class="text14">&nbsp;<c:if test="${'null'!= record.time}">${record.time}</c:if></td>
			               	   <td class="text14">&nbsp;<c:if test="${'null'!= record.trreforg}">${record.trreforg}</c:if></td>
			               	   <td class="text14">&nbsp;<c:if test="${'null'!= record.trrefreg}">${record.trrefreg}</c:if></td>
			               	   <td class="text14">&nbsp;<c:if test="${'null'!= record.avsna}">${record.avsna}</c:if></td>
			               	   <td class="text14">&nbsp;<c:if test="${'null'!= record.avsid}">${record.avsid}</c:if></td>
			               	   <td class="text14">&nbsp;<c:if test="${'null'!= record.motna}">${record.motna}</c:if></td>
			               	   <td class="text14">&nbsp;<c:if test="${'null'!= record.motid}">${record.motid}</c:if></td>
			               	   <td class="text14" align="center">
			               	   		<%--
			               	   		<a tabindex=-1 style="display:block;" onClick="setBlockUI(this);"
			               	   				href="tvinnsaddigitollv2_delete_light_master_zadmomlf.do?etlnrt=${model.etlnrt}&emdkm=${record.emdkm}&emdkmt=${record.emdkmt}">
										<img src="resources/images/delete.gif" border="0" alt="remove">
									</a>
									--%>
									<a tabindex=-1 class="removeLink" id="removeLink${counter.count}" runat="server" href="#">
												<img src="resources/images/delete.gif" border="0" alt="remove">
									</a>
									<div style="display: none;" class="clazz_dialog" id="dialogDeleteRecord${counter.count}" title="Dialog">
										<form action="tvinnsaddigitollv2_delete_light_master_zadmomlf.do" name="deleteRecordForm${counter.count}" id="deleteRecordForm${counter.count}" method="post">
											<input type="hidden" name="current_id1${counter.count}" id="current_id1${counter.count}" value="${model.etlnrt}">
											<input type="hidden" name="current_id2${counter.count}" id="current_id2${counter.count}" value="${record.emdkm}">
											<input type="hidden" name="current_id3${counter.count}" id="current_id3${counter.count}" value="${record.emdkmt}">
											<input type="hidden" name="action${counter.count}" id="action${counter.count}" value="doDelete">
										 	<p class="text14" >Er du sikker på at du vil slette denne?</p>
										</form>
									</div> 
									 
									
					
									
							   </td>
			            	</tr> 
			            	
			            </c:forEach>
			            </tbody>
		            </table>
		            </td>
	           		</tr>
        			</table>
		
		</td>
		</tr>
	</table> 
