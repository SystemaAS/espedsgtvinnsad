<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadnctsexport_edit_items_childwindow_oppdragslist.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="select" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Søk Tolldeknr - SAD Eksport
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
						<form name="tvinnsadNctsExportOppdragForm" id="tvinnsadNctsExportOppdragForm" action="tvinnsadnctsexport_edit_items_childwindow_oppdragslist.do?action=doFind" method="post">
						<tr>
							<td class="text11">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.export.list.search.label.avd"/></td>
							<td class="text11">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.export.list.search.label.arende"/></td>
							<td class="text11">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.export.list.search.label.extrefnr"/></td>
							<td class="text11">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.export.list.search.label.datum"/>.</td>
							<td class="text11">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.export.list.search.label.avsandare"/></td>
							<td class="text11">&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.export.list.search.label.mottagare"/></td>
						 </tr>
						 <tr>	
							<td class="text11">&nbsp;<input type="text" class="inputText" name="avd" id="avd" size="5" maxlength="4" value="${searchFilter.avd}"></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="opd" id="opd" size="8" maxlength="7" value="${searchFilter.opd}"></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="xref" id="xref" size="10" maxlength="10" value="${searchFilter.xref}"></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="datum" id="datum" size="10" maxlength="8" value="${searchFilter.datum}"></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="avsNavn" id="avsNavn" size="15" maxlength="25" value="${searchFilter.avsNavn}"></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="motNavn" id="motNavn" size="15" maxlength="25" value="${searchFilter.motNavn}"></td>
							
							<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="search.label"/>'>
		           		</tr>
		           		
		           		</table>
					</td>
					</tr>
					 								           		
	           		<tr height="20"><td></td></tr>
					
					<tr class="text11" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="oppdragsList" class="display compact cell-border" width="100%" >
						<thead>
						<tr style="background-color:#EEEEEE">
							<th class="text11" title="avd">&nbsp;<spring:message code="systema.tvinn.sad.export.list.search.label.avd"/>&nbsp;</th>
		                    <th class="text11" title="sign">&nbsp;<spring:message code="systema.tvinn.sad.export.list.search.label.signatur"/>&nbsp;</th>
		                    <th class="text11" title="opd">&nbsp;<spring:message code="systema.tvinn.sad.export.list.search.label.arende"/>&nbsp;</th>
		                    <th class="text11" title="refnr">&nbsp;<spring:message code="systema.tvinn.sad.export.list.search.label.extrefnr"/>&nbsp;</th>
		                    <th class="text11" title="datum">&nbsp;<spring:message code="systema.tvinn.sad.export.list.search.label.datum"/>&nbsp;</th>
		                    <th class="text11" title="status">&nbsp;<spring:message code="systema.tvinn.sad.export.list.search.label.status"/>&nbsp;</th>
		                    <th class="text11" title="avsNavn">&nbsp;<spring:message code="systema.tvinn.sad.export.list.search.label.avsandare"/>&nbsp;</th>
		                    <th class="text11" title="motNavn">&nbsp;<spring:message code="systema.tvinn.sad.export.list.search.label.mottagare"/>&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.oppdragsList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text11">
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text11">
			                   </c:otherwise>
			               </c:choose>
			               <td class="text11">&nbsp;${record.avd}</td>
			               <td class="text11">&nbsp;${record.sign}</td>
			               <td nowrap style="cursor:pointer;" class="text11MediumBlue" id="avd${record.avd}@opd${record.opd}" >
			               		<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;${record.opd}
			               	</td>
		               	   <td class="text11">&nbsp;${record.h_xref}</td>
		               	   <td class="text11">&nbsp;${record.datum}</td>
		               	   <td class="text11">&nbsp;${record.status}</td>
		               	   <td class="text11">&nbsp;${record.avsNavn}</td>
		               	   <td class="text11">&nbsp;${record.motNavn}</td>
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
