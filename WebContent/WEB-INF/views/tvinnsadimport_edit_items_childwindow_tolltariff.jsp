<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadimport_edit_items_childwindow_tolltariff.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Søk kode
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
						<form name="tvinnsadImportTolltariffForm" id="tvinnsadImportTolltariffForm" action="tvinnsadimport_edit_items_childwindow_tolltariff.do?action=doInit" method="post">
						<tr>
							<td class="text11">&nbsp;Varenr.</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="vkod" id="vkod" size="10" maxlength="10" value="${model.vkod}"></td>
							
							<td class="text11">&nbsp;Vareslag</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="tekst" id="tekst" size="30" maxlength="50" value="${model.tekst}"></td>
							
							<td class="text11">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.tvinn.sad.search"/>'></td>
		           		</tr>
		           		</form>
		           		</table>
					</td>
					</tr>
					 
													           		
	           		<tr height="10"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="tolltariffList" class="display compact cell-border" width="100%" >
						<thead>
						<tr style="background-color:#EEEEEE">
							<th class="text11" title="adunnr">&nbsp;Varenr.&nbsp;</th>
		                    <th class="text11" title="adembg">&nbsp;Vareslag&nbsp;</th>
		                    <th class="text11" title="adembg">&nbsp;Beskrivelse&nbsp;</th>
		                    
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.tolltariffList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text11">
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text11">
			                   </c:otherwise>
			               </c:choose>
			               <td nowrap style="cursor:pointer;" class="text11MediumBlue" id="vkod${record.tatanr}@text${record.taalfa}" >
			               		<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;${record.tatanr}
			               	</td>
		               	   <td class="text11">&nbsp;${record.taalfa}</td>
		               	   <td class="text11">&nbsp;${record.beskr1}</td>
		               	   
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
