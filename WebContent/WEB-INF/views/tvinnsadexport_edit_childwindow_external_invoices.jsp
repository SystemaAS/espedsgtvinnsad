<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadexport_edit_childwindow_external_invoices.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="99%" height="100px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
	
		<tr><td valign="top" colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
		<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
		Eksterna Fakturaer</td>
		</tr>
		<tr height="8"><td></td></tr>
		<tr>
		<td valign="top">
		<form action="TODO_childwindow_external_invoices.do?action=doFind" name="searchForm" id="searchForm" method="post">
				<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
				<input type="hidden" name="avd" id="avd" value="${model.avd}">
				<input type="hidden" name="opd" id="opd" value="${model.opd}">
				<input type="hidden" name="seknk" id="seknk" value="${recordTopicTvinnSad.seknk}">
				
				<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" width="98%" cellspacing="2" align="left">
					<%-- Fakturalista (external invoices) --%>
					<tr height="15"><td></td></tr>
					<tr><td valign="top" colspan="3" class="text14Bold">&nbsp;Fakturalista</td></tr>
					<tr>
						<td colspan="3">
							<table id="tblInvoices" class="display compact cell-border" width="100%">
								<thead>
								<tr style="background-color:#DDDDDD">
									<th align="center" width="2%" class="text12">&nbsp;Velg&nbsp;</th>
								    <th align="center" width="20%" class="text12">&nbsp;Fakturanr.&nbsp;</th>   
				                    <th align="center" class="text12">&nbsp;Dato&nbsp;</th> 
				                    <th align="right" class="text12">&nbsp;Beløp&nbsp;</th> 
				                    <th align="right" class="text12">&nbsp;Valuta&nbsp;</th> 
				                    <th align="right" class="text12">&nbsp;Kurs&nbsp;</th>
				                    <th align="center" class="text12">Slett</th>
				             
			                    </tr>
			                    </thead>
			                    <tbody>
			                    <c:forEach items="${model.list}" var="record" varStatus="counter">    
					               <c:choose>           
					                   <c:when test="${counter.count%2==0}">
					                       <tr class="tableRow" height="15" >
					                   </c:when>
					                   <c:otherwise>   
					                       <tr class="tableOddRow" height="15" >
					                   </c:otherwise>
					               </c:choose>
					               <td align="center" width="2%" class="text11" >
					               		<input class="clazzInvoiceAware" type="checkbox" value="J" id="id${record.sfreff}__unik${record.sfunik}" name="id${record.sfreff}__unik${record.sfunik}" >
					               </td>
					               <td align="center" width="20%" class="text11" >&nbsp;<span title="reff/unik:${record.sfreff}/${record.sfunik}">${record.sftxt}</span></td>
					               <td align="center" class="text11" >&nbsp;${record.sfdt}</td>
					               <td align="right" class="text11" >&nbsp;${record.sfbl28}</td>
					               <td align="right" class="text11" >&nbsp;${record.sfvk28}</td>
					               <td align="right" class="text11" >&nbsp;${record.sfkr28}</td>
					               <td width="4%" class="text11" align="center" nowrap>
						               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadexport_edit_childwindow_external_invoices_delete.do?action=doDelete&sfavd=${model.avd}&sfopdn=${model.opd}&sfreff=${record.sfreff}&sfunik=${record.sfunik}">
						               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
						               	</a>
					               </td>
				               </tr>
				               </c:forEach>
							   </tbody>				               
		                    </table>  
						</td>
					</tr>
					
	               <tr>
		               <td align="left">
		               		&nbsp;<input class="inputFormSubmit" type="button" name="buttonCloseOk" id="buttonCloseOk" value='OK'>
		               		&nbsp;<input class="inputFormSubmit" type="button" name="cancel" id="cancel" value='Avbryt'>
		               </td>
	               </tr>	
			</table>
				
		</form>	
		</td>
		</tr>
	</table> 
