<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmanifest_childwindow_released_cargolines.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr height="5"><td colspan="2"></td></tr>
		
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
			<%-- ============================================  --%>
          	<%-- Here we have the search  popup window --%>
          	<%-- ============================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<form name="mainForm" id="mainForm" >
				<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
				<input type="hidden" name="parentClpro" id="parentClpro" value="${model.parentClpro}">
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%">
					<tr>
						<td>
							<input class="inputFormSubmitBlue" type="button" name="buttonCloseOk" id="buttonCloseOk" value='Plukke oppdrag'>
						</td>
					</tr>
					<%--
					<tr>
					<td>
						<table>
						<form name="postalCodeForm" id="postalCodeForm" action="tvinnsadmanifest_childwindow_postalcodes_sted2.do?action=doFind" method="post">
						<input type="hidden" name="ctype" id="ctype" value="${model.ctype}">
						<tr>
							<td class="text14">&nbsp;Avd</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="st2kod" id="st2kod" size="10" maxlength="10" value="${model.record.st2kod}"></td>
							<td class="text14">&nbsp;</td>
							<td class="text14">&nbsp;Oppdrag</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="st2lk" id="st2lk" size="4" maxlength="2" value="${model.record.st2lk}"></td>
							
							<td class="text14">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='Søk'></td>
		           		</tr>
		           		</form>
		           		</table>
					</td>
					</tr>
					 --%>								           		
	           		<tr height="10"><td></td></tr>
					<tr class="text14" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:50em;">
					<%-- this is the datatables grid (content)--%>
					
					<table id="releasedCargoLinesList" class="display compact cell-border" width="100%">
						<thead>
						<tr class="tableHeaderField" height="20">
							<th width="2%" class="text14">&nbsp;Velg</th>
							<th width="2%" class="text14">&nbsp;Avd</th>   
		                    <th width="2%" class="text14">&nbsp;Opp.nr.</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.releasedCargoLinesList}" varStatus="counter">    
			               <tr class="text14" >
			               
			               <td align="center" width="2%" class="text14MediumBlue">
			               	 	<input class="clazzPickAware" type="checkbox" value="J" id="avd${record.clavd}_tdn${record.cltdn}" name="avd${record.clavd}_tdn${record.cltdn}" >
			               </td>
			               <td width="2%" class="text14" >&nbsp;${record.clavd}</td>
			               <td width="2%" class="text14" >&nbsp;${record.cltdn}</td> 
			               
			            </tr> 
			            </c:forEach>
			            </tbody>
		            </table>
		            </td>
	           		</tr>
        			</table>
        			</form>
		</td>
		</tr>
	</table> 
