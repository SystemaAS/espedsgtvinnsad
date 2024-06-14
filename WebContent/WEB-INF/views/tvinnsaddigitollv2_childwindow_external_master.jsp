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
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Søk - Ekstern Dok.nr </td>
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
						
	           				<td align="right">&nbsp;<input class="inputFormSubmit" onClick="setBlockUI(this)" type="submit" name="submit" value='<spring:message code="search.label"/>'>
		           		</tr>
		           		
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

		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.mainList}" varStatus="counter">    
			               
			               <tr class="text14">
				               <td nowrap style="cursor:pointer;" class="text14MediumBlue" id="emdkm${record.emdkm}@trreforg${record.trreforg}@ctype${model.callerType}@emdkmt${record.emdkmt}@avsid${record.avsid}" >
			               			${record.emdkm}
				               </td>
				               <td class="text14">&nbsp;${record.emdkmt}</td>
			               	   <td class="text14">&nbsp;${record.empro}</td>
			               	   <td class="text14">&nbsp;${record.date}</td>
			               	   <td class="text14">&nbsp;${record.time}</td>
			               	   <td class="text14">&nbsp;${record.trreforg}</td>
			               	   <td class="text14">&nbsp;${record.trrefreg}</td>
			               	   <td class="text14">&nbsp;${record.avsna}</td>
			               	   <td class="text14">&nbsp;${record.avsid}</td>
			               	   <td class="text14">&nbsp;${record.motna}</td>
			               	   <td class="text14">&nbsp;${record.motid}</td>
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
