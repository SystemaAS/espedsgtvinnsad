<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmanifest_childwindow_postalcodes.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr height="5"><td colspan="2"></td></tr>
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
				<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
				Postnummer
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
			<%-- ============================================  --%>
          	<%-- Here we have the search  popup window --%>
          	<%-- ============================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%">
					<tr>
					<td>
						<table>
						<form name="postalCodeForm" id="postalCodeForm" action="tvinnsadmanifest_childwindow_postalcodes_sted2.do?action=doFind" method="post">
						<input type="hidden" name="ctype" id="ctype" value="${model.ctype}">
						<tr>
							<td class="text14">&nbsp;Postkode</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="st2kod" id="st2kod" size="10" maxlength="10" value="${model.record.st2kod}"></td>
							<td class="text14">&nbsp;</td>
							<td class="text14">&nbsp;Landkode</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="st2lk" id="st2lk" size="4" maxlength="2" value="${model.record.st2lk}"></td>
							
							<td class="text14">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='Søk'></td>
		           		</tr>
		           		</form>
		           		</table>
					</td>
					</tr>								           		
	           		<tr height="10"><td></td></tr>
					<tr class="text14" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:50em;">
					<%-- this is the datatables grid (content)--%>
					<table id="postalCodeList" class="display compact cell-border" width="100%">
						<thead>
						<tr class="tableHeaderField" height="20">
							<th width="2%" class="text14">&nbsp;Postkode</th>   
		                    <th width="2%" class="text14">&nbsp;Sted</th>
		                    <th width="2%" align="center" class="text14">&nbsp;Landkode</th>
		                    
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.postalCodeList}" varStatus="counter">    
			               <tr class="text14" >
			               
			               <td width="2%" class="text14MediumBlue" style="cursor:pointer;" id="postalcode_${record.st2kod}@country_${record.st2lk}@city_${record.st2nvn}@st2ko2_${record.st2ko2}@st2son_${record.st2son}@counter_${counter.count}">
			               	 	${record.st2kod}
			               </td>
			               <td width="2%" class="text14" >&nbsp;${record.st2nvn}</td> 
			               <td width="2%" align="center" class="text14" >&nbsp;${record.st2lk}</td>
			               
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
