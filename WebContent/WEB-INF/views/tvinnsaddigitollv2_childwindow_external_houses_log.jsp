<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_childwindow_external_houses_log.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			FTP-log Outbound
			</td>
		</tr>
		<tr>
		<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
					<%--
					<tr>
					<td>
						<table>
						<form name="tvinnsadCustomerForm" id="tvinnsadCustomerForm" action="tvinnsaddigitollv2_childwindow_external_houses.do?action=doFind" method="post">
						<tr>
							<td class="text14">&nbsp;Navn</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="name" id="name" size="30" maxlength="70" value="${model.name}"></td>
							<td class="text14">&nbsp;</td>
							<td class="text14">&nbsp;OrgNr.</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="orgnr" id="orgnr" size="15" maxlength="35" value="${model.orgnr}"></td>
							
							<td class="text14">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="search.label"/>'></td>
		           		</tr>
		           		</form>
		           		</table>
					</td>
					</tr>
					--%> 
													           		
	           		<tr height="10"><td></td></tr>
					
					<tr class="text14" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="mainList" class="display compact cell-border" width="100%" >
						<thead>
						<tr class="tableHeaderField" height="20" >
							<%--
							<th class="text14" >&nbsp;&nbsp;</th>
							 --%>
							<th class="text14" >&nbsp;Master-docId&nbsp;</th>
							<th class="text14" >&nbsp;Tr.id&nbsp;</th>
		                    <th class="text14" >&nbsp;St.&nbsp;</th>
		                    <th class="text14" >&nbsp;TimeStamp&nbsp;</th>
		                    <th class="text14" >&nbsp;Avs&nbsp;</th>
		                    <th class="text14" >&nbsp;Mot&nbsp;</th>
		                    <th class="text14" >&nbsp;uuid&nbsp;</th>
		                    <th class="text14" >&nbsp;St.text&nbsp;</th>
		                    <th class="text14" >&nbsp;Msg.type&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.sadmolffList}" varStatus="counter">    
			               <tr class="text14">
			               	   <%--	
			                   <td style="cursor:pointer;" class="text14MediumBlue" id="orgnr${record.orgnr}_name${record.name}_commtype${record.commtype}_format${record.format}" >
				               		<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;
				               	</td>
				                --%>	
			               	   <td class="text14">&nbsp;${record.emdkm}</td>
			               	   <td class="text14">&nbsp;${record.emlnrt}</td>
			               	   <td class="text14">&nbsp;${record.status}</td>
			               	   <td class="text14">&nbsp;${record.date}-${record.time}</td>
			               	   <td class="text14">&nbsp;${record.avsid}</td>
			               	   <td class="text14">&nbsp;${record.motid}</td>
			               	   <td class="text14">&nbsp;${record.uuid}</td>
			               	   <td class="text14">&nbsp;${record.statustxt}</td>
			               	   <td class="text14">
			               	   <c:choose>
				               	   <c:when test="${record.msgtype == 'null'}">
				               	   		&nbsp;
				               	   </c:when>
				               	   <c:otherwise>
				               	   		&nbsp;${record.msgtype}
				               	   </c:otherwise>
			               	   </c:choose>
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
