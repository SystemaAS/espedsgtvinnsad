<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadMaintenanceChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmaintenanceimport_sad002_kodts8_childwindow_avgiftcodes.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
						<tr>
							<td class="text11">&nbsp;Kod</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="kode" id="kode" size="10" maxlength="10" value="${Xmodel.container.kode}"></td>
							
							<td class="text11">&nbsp;Beskrivning</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="tekst" id="tekst" size="10" maxlength="20" value="${Xmodel.container.tekst}"></td>
						
							<td class="text11">&nbsp;</td>
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
					<table id="generalCodeList" class="display compact cell-border" width="100%" >
						<thead>
						<tr style="background-color:#EEEEEE">
							<th class="text11" title="ks8avg">&nbsp;Kode&nbsp;</th>
		                    <th class="text11" title="ks8skv">&nbsp;Sekvens&nbsp;</th>
		                    <th class="text11" title="ks8ftx">&nbsp;Fritekst&nbsp;</th>
		                    <th class="text11" title="ks8sat">&nbsp;Sats&nbsp;</th>
		                    <th class="text11" title="ks8sty">&nbsp;Type&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.generalCodeList}" varStatus="counter">    
	                       	<tr class="text11">
				               <td style="cursor:pointer;" class="text11MediumBlue" 
				               		id="kod${record.ks8avg}@skv${record.ks8skv}@ctype${model.callerType}" >
				               		&nbsp;<img title="select" valign="bottom" src="resources/images/bebullet.gif" border="0" alt="edit">
				               		&nbsp;&nbsp;${record.ks8avg}
				               </td>
			               	   	<td class="text11">&nbsp;${record.ks8skv}</td>
		               	   		<td class="text11">&nbsp;${record.ks8ftx}</td>
		               	   		<td class="text11">&nbsp;${record.ks8sat}</td>
		               	   		<td class="text11">&nbsp;${record.ks8sty}</td>
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
