<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_childwindow_tur.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Søk kode</td>
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
						<form name="tvinnsadImportTullkontorForm" id="tvinnsadImportTullkontorForm" action="tvinnsadnctsexport_edit_childwindow_tullkontor.do?action=doInit" method="post">
							<input type="hidden" name="ctype" id="ctype" value="${model.callerType}">
						<tr>
							<td class="text14">&nbsp;Kode</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="tkkode" id="tkkode" size="10" maxlength="10" value="${model.tkkode}"></td>
						
							<td class="text14">&nbsp;Tollsted</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="tktxtn" id="tktxtn" size="30" maxlength="50" value="${model.tktxtn}"></td>
							
							<td class="text14">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="search.label"/>'>
		           		</tr>
		           		
		           		</table>
					</td>
					</tr>
					--%> 
													           		
	           		<tr height="10"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="turList" class="display compact cell-border" width="100%" >
						<thead>
						<tr class="tableHeaderField" >
							<th class="text14" title="tupro">&nbsp;Turnr&nbsp;</th>
							<th class="text14" title="tuavd">&nbsp;Avd&nbsp;</th>
							<th class="text14" title="tubiln">&nbsp;Kjøretøy kjennemerke&nbsp;</th>
		                    <th class="text14" title="tulk">&nbsp;Land&nbsp;</th>
		                    <th class="text14" title="tusjn1">&nbsp;Fører-navn&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.turList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text14">
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text14">
			                   </c:otherwise>
			               </c:choose>
			               <td nowrap style="cursor:pointer;" class="text14MediumBlue" id="tupro${record.tupro}@tuavd${record.tuavd}@ctype${model.callerType}" >
		               			<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;${record.tupro}
			               </td>
		               	   <td class="text14">&nbsp;${record.tuavd}</td>
		               	   <td class="text14">&nbsp;${record.tubiln}</td>
		               	   <td class="text14">&nbsp;${record.tulk}</td>
		               	   <td class="text14">&nbsp;${record.tusjn1}</td>
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
