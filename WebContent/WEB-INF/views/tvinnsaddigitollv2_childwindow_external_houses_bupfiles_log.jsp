<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_childwindow_external_houses_bupfiles_log.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			BUP-files log
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
						<form name="digitollExtHousesBupLogForm" id="digitollExtHousesBupLogForm" action="tvinnsaddigitollv2_childwindow_external_houses_bupfiles_log.do" method="post">
							<input type="hidden" name="action" id="action" value="doFind">
						<tr>
							<%--
							<td class="text14">&nbsp;DocId</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="emdkm" id="emdkm" size="40" maxlength="50" value="${model.id}"></td>
							<td class="text14">&nbsp;</td>
							 --%>
							<td class="text14">&nbsp;Dato</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="date" id="date" size="12" maxlength="10" value="${model.date}"></td>
							
							<td class="text14">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="search.label"/>'></td>
		           		</tr>
		           		</form>
		           		</table>
					</td>
					</tr>
					
													           		
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
							<th class="text14" >&nbsp;File&nbsp;</th>
							<th class="text14" >&nbsp;Date&nbsp;</th>
		                    <th class="text14" >&nbsp;Time&nbsp;</th>
		                    
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.list}" varStatus="counter">    
			               <tr class="text14">
			               	   <%--	
			                   <td style="cursor:pointer;" class="text14MediumBlue" id="orgnr${record.orgnr}_name${record.name}_commtype${record.commtype}_format${record.format}" >
				               		<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;
				               	</td>
				                --%>
				               <td class="text12">
					                <a href="renderExternalHouseBupFiles.do?fp=${record.file}" target="_new" onClick="window.open(this.href,'legendWindow','top=200px,left=600px,height=800px,width=700px,scrollbars=yes,status=no,location=no'); return false;">
				               			<img title="select" style="vertical-align:middle;" src="resources/images/bebullet.gif" border="0" alt="edit">&nbsp;${record.file}
				               		</a>
				               </td> 	
			               	   <td class="text14">&nbsp;${record.date}</td>
			               	   <td class="text14">&nbsp;${record.time}</td>
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
