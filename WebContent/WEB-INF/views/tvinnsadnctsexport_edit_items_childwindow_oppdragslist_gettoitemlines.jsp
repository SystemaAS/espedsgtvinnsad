<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadnctsexport_edit_items_childwindow_oppdragslist_gettoitemlines.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="select" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Importere Eksport Tolldeklarasjoner
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
						<form name="nctsExportAngivelseForm" id="nctsExportAngivelseForm" action="tvinnsadnctsexport_edit_items_childwindow_oppdragslist_gettoitemlines.do?action=doFind" method="post">
							<input type="hidden" name="avdNcts" id="avdNcts" value="${model.avdNcts}">
							<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
							<input type="hidden" name="opdNcts" id="opdNcts" value="${model.opdNcts}">
						<tr>
							<td class="text11">&nbsp;&nbsp;Avd</td>
							<td class="text11">&nbsp;&nbsp;Tar</td>
							<td class="text11">&nbsp;&nbsp;Tolldek.nr.</td>
							<td class="text11">&nbsp;&nbsp;Ekst.ref.nr.</td>
							<td class="text11">&nbsp;&nbsp;Dato</td>
							<td class="text11">&nbsp;&nbsp;Løpenr</td>
							<td class="text11">&nbsp;&nbsp;Status</td>
							<td class="text11">&nbsp;&nbsp;Avsender</td>
							<td class="text11">&nbsp;&nbsp;Mottaker</td>
							<td class="text11">&nbsp;&nbsp;Godsnr</td>
						 </tr>
						 <tr>	
							<td class="text11">&nbsp;<input type="text" class="inputText" name="avd" id="avd" size="5" maxlength="10" value="${searchFilter.avd}"></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="sg" id="sg" size="3" maxlength="3" value="${searchFilter.sg}"></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="opd" id="opd" size="8" maxlength="7" value="${searchFilter.opd}"></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="xref" id="xref" size="10" maxlength="35" value="${searchFilter.xref}"></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="datum" id="datum" size="7" maxlength="6" value="${searchFilter.datum}"></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="setll" id="setll" size="10" maxlength="35" value="${searchFilter.setll}"></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="status" id="status" size="1" maxlength="1" value="${searchFilter.status}"></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="avsNavn" id="avsNavn" size="9" maxlength="50" value="${searchFilter.avsNavn}"></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="motNavn" id="motNavn" size="9" maxlength="50" value="${searchFilter.motNavn}"></td>
							<%--<td align="left" ><input type="text" class="inputText" name="godsnr" id="godsnr" size="10" maxlength="50" value='${searchFilter.godsnr}'>&nbsp;</td> --%>
							<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="search.label"/>'>
		           		</tr>
		           		</form>
		           		</table>
					</td>
					</tr>
					 								           		
	           		<tr height="20"><td></td></tr>
					
					<tr class="text11" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					
					<%-- this is the datatables grid (content)--%>
					<form action="N/A_is_done_with_jquery....?action=doFind" name="searchForm" id="searchForm" method="post">
						
					<table id="angivelseList" class="display compact cell-border" width="100%" >
						<thead>
						<tr style="background-color:#EEEEEE">
							<th class="text12">&nbsp;Velg&nbsp;</th>
							<th class="text11" title="avd">&nbsp;Avd&nbsp;</th>
		                    <th class="text11" title="sg">&nbsp;Sign&nbsp;</th>
		                    <th class="text11" title="opd">&nbsp;Tolldeknr&nbsp;</th>
		                    <th class="text11" title="h_xref">&nbsp;Ekst.ref.nr&nbsp;</th>
		                    <th class="text11" title="datum">&nbsp;Datum&nbsp;</th>
		                    <th class="text11" title="setll">&nbsp;Løpenr&nbsp;</th>
		                    <th class="text11" title="status">&nbsp;Status&nbsp;</th>
		                    <th class="text11" title="avsNavn">&nbsp;Avs&nbsp;</th>
		                    <th class="text11" title="motNavn">&nbsp;Mot&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.angivelseList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text11">
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text11">
			                   </c:otherwise>
			               </c:choose>
			               <td align="center" class="text11" >
			               		<input class="clazzEksportAware" type="checkbox" value="J" id="syav${record.avd}_syop${record.opd}" name="syav${record.avd}_syop${record.opd}" >
			               </td>
			               <td class="text11">&nbsp;${record.avd}</td>
			               <td class="text11">&nbsp;${record.sg}</td>
			               <%-- <td nowrap style="cursor:pointer;" class="text11MediumBlue" id="avd${record.avd}@opd${record.opd}@xref${record.xref}@refnr${record.refnr}@mrn${record.dkeh_mrn}@valuta${record.dkeh_221}@blp${record.dkeh_222}" >  --%>
			               <td nowrap style="cursor:pointer;" class="text11MediumBlue" id="avd${record.avd}@opd${record.opd}@xref${record.h_xref}" >
			               		<img title="select" style="vertical-align:top;" src="resources/images/bebullet.gif" border="0" alt="edit">&nbsp;${record.opd}
			               	</td>
		               	   <td class="text11">&nbsp;${record.h_xref}</td>
		               	   <td class="text11">&nbsp;${record.datum}</td>
		               	   <td class="text11">&nbsp;${record.setll}</td>
		               	   <td class="text11">&nbsp;${record.status}</td>
		               	   <td class="text11">&nbsp;${record.avsNavn}</td>
		               	   <td class="text11">&nbsp;${record.motNavn}</td>
			            </tr> 
			            </c:forEach>
			            </tbody>
		            </table>
		            </form>
	    	        </td>
	    	        
           		</tr>
           		<tr height="10"><td></td></tr>
           		<tr>
		          <td align="left">
		          		&nbsp;<input class="inputFormSubmit" type="button" name="buttonPick" id="buttonPick" value='Plukk'>
		          		&nbsp;&nbsp;&nbsp;<input class="inputFormSubmit" type="button" name="buttonCloseOk" id="buttonCloseOk" value='Importer'>
		          		&nbsp;<input class="inputFormSubmit" type="button" name="cancel" id="cancel" value='Avbryt'>
		          </td>
		         </tr>
       			</table>
		</td>
		</tr>

	</table> 
