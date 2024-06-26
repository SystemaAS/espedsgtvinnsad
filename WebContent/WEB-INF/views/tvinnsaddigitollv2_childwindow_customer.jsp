<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_childwindow_customer.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Søk Kunde
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
						<form name="tvinnsadCustomerForm" id="tvinnsadCustomerForm" action="tvinnsaddigitollv2_childwindow_customer.do?action=doFind" method="post">
						<input type="hidden" name="ctype" id="ctype" value="${model.ctype}">
						<tr>
							<td class="text14">&nbsp;Kundenr.</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="knr" id="knr" size="10" maxlength="10" value="${model.knr}"></td>
							<td class="text14">&nbsp;</td>
							<td class="text14">&nbsp;Navn</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="sonavn" id="sonavn" size="30" maxlength="50" value="${model.sonavn}"></td>
							
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
					<table id="customerList" class="display compact cell-border" width="100%" >
						<thead>
						<tr class="tableHeaderField" height="20" >
							<th class="text14" >&nbsp;Kundenr.&nbsp;</th>
		                    <th class="text14" >&nbsp;Navn&nbsp;</th>
		                    <th class="text14" >&nbsp;OrgNr&nbsp;</th>
		                    <th class="text14" >&nbsp;EORI&nbsp;</th>
		                    <th class="text14" >&nbsp;Adresse&nbsp;</th>
		                    <th class="text14" >&nbsp;Postadresse&nbsp;</th>
		                    <th class="text14" >&nbsp;Postnr&nbsp;</th>
		                    <th class="text14" >&nbsp;Land&nbsp;</th>
		                    <th class="text14" >&nbsp;Tlf&nbsp;</th>
		                    <th class="text14" >&nbsp;Epost&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.customerList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text14">
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text14">
			                   </c:otherwise>
			               </c:choose>
			               <td style="cursor:pointer;" class="text14MediumBlue" id="knr${record.kundnr}_knavn${record.knavn}_kadr1${record.adr1}_kadr3${record.adr3}_kpostnr${record.postnr}_kland${record.syland}_keori${record.eori}_ctype${model.ctype}_ksyrg${record.syrg}_ktlf${record.tlf}_ksyepos${record.syepos}" >
			               		<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;${record.kundnr}
			               	</td>
		               	   <td class="text14">&nbsp;${record.knavn}</td>
		               	   <td class="text14">&nbsp;${record.syrg}</td>
		               	   <td class="text14">&nbsp;${record.eori}</td>
		               	   <td class="text14">&nbsp;${record.adr1}</td>
		               	   <td class="text14">&nbsp;${record.adr3}</td>
		               	   <td class="text14">&nbsp;${record.postnr}</td>
		               	   <td class="text14">&nbsp;${record.syland}</td>
		               	   <td class="text14">&nbsp;${record.tlf}</td>
		               	   <td class="text14">&nbsp;${record.syepos}</td>
		               	   
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
