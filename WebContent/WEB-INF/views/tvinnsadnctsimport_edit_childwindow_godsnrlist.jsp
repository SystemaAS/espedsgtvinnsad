<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadnctsimport_edit_childwindow_godsnrlist.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Søk Godsn.</td>
		</tr>
		<tr>
		<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
					<tr>
					<td>
						<table>
						<form name="tvinnsadNctsGodsnrForm" id="tvinnsadNctsGodsnrForm" action="tvinnsadnctsimport_edit_childwindow_godsnrlist.do?action=doFind" method="post">
							<input type="hidden" name="ctype" id="ctype" value="${model.callerType}">
							<input type="hidden" name="sign" id="sign" value="${model.sign}">
						<tr>
							<td class="text14">&nbsp;Godsnr</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="gogn" id="gogn" size="20" maxlength="15" value="${model.record.gogn}"></td>
						
							<td class="text14">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="search.label"/>'>
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
							<th class="text14" title="adunnr">&nbsp;Godsnr&nbsp;</th>
		                    <th class="text14" title="adembg">&nbsp;Mottaker&nbsp;</th>
		                    <th class="text14" title="adembg">&nbsp;Bilnr.&nbsp;</th>
		                    <th class="text14" title="adembg">&nbsp;Avg.&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.godsnrList}" varStatus="counter">    
			               <tr class="text14">
				               <td nowrap style="cursor:pointer;" class="text14MediumBlue" id="gogn${record.gogn}@ctype${model.callerType}" >
			               			<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;${record.gogn}
				               </td>
			               	   <td class="text14">&nbsp;${record.gomott}</td>
			               	   <td class="text14">&nbsp;${record.gobiln}</td>
			               	   <td class="text14">&nbsp;${record.goavg}</td>
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
