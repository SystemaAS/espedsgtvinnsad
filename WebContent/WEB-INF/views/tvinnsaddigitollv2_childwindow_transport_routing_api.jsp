<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_childwindow_transport_routing_api.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
				<form name="transportForm" id="transportForm" action="tvinnsaddigitollv2_childwindow_routinginfo.do" method="post">
					<tr>
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="level" id="level" value="${model.level}">
					
					<td class="text14  ">
					<font class="inputText isa_warning" >
				    	Routing info
				    </font>	
				    &nbsp;&nbsp;RoutingId&nbsp;
					<input type="text" class="inputText" name="uuid" id="uuid" size="39" maxlength="36" value="${model.uuid}">
					<input class="inputFormSubmit" onClick="setBlockUI(this)" type="submit" name="submit" value='<spring:message code="search.label"/>'>
					</tr>
				</form>									           		
			    <tr>
				<td>		
				<table style="width:100%;" border="0" >
			    	<%-- separator --%>
			        <tr height="2"><td></td></tr> 
					<tr>
						<td>
						<table style="width:100%;" id="containerdatatableTable" cellspacing="2" align="left" >
						
						<tr height="5"><td></td></tr> 
						<tr>
							<td class="text11">	
							<table id="mainList" class="compact" >
								<thead>
								<tr class="tableHeaderField" height="20" >
									<th width="2%" class="tableHeaderFieldFirst12" >ETA</th>
			                    	<th width="2%" class="tableHeaderField12" >RoutingId</th>
			                    	<th width="2%" class="tableHeaderField12" >RoutingTxt</th>
			                    	<th width="2%" class="tableHeaderField12" >ENS-MRN</th>
			                	</tr>
			                	</thead>
			                	<tbody> 
			                	<c:forEach items="${model.list}" var="record" varStatus="counter">    
					             <tr class="tableRow" height="20" >
					          	   <td width="2%" align="center" class="tableCellFirst12" >${record.estimatedTimeOfArrival}</td>
					          	   <td width="2%" align="center" class="tableCell12" >${record.routingResult.id}</td>
				               	   <td width="2%" align="center" class="tableCell12" >${record.routingResult.routing}</td>
				               	   <td width="2%" align="center" class="tableCell12" >${record.entrySummaryDeclarationMRN}</td>
					            </tr> 
					            </c:forEach>
					            
					            </tbody>
				            </table>
				            </td>
			            </tr>
			            
			            </table>
					</td>	
					</tr>
					<tr height="2"><td></td></tr> 
	
	
				</table>
				</td>
				</tr>
				
			</table> 
			</td>
		</tr>
</table>
		
