<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_childwindow_external_master_attachments.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			Søk - Ekstern Dokument 
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
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
						<form name="mainForm" id="mainForm" action="tvinnsaddigitollv2_childwindow_external_master_attachments.do?action=doInit" method="post">
							<input type="hidden" name="ctype" id="ctype" value="${model.callerType}">
							<input type="hidden" name="docref" id="docref" value="${model.docref}">
							<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
							
							<tr>
								<td class="text14">&nbsp;Date</td>
								<td class="text14">&nbsp;<input type="text" class="inputText" onKeyPress="return numberKey(event)" name="date" id="date" size="10" maxlength="8" value="${model.date}"></td>
								<%--
								<td class="text14">&nbsp;Dok.nr</td>
								<td class="text14">&nbsp;<input type="text" class="inputText" name="emdkm" id="emdkm" size="35" maxlength="50" value="${model.emdkm}"></td>
								 --%>
		           				<td align="right">&nbsp;<input class="inputFormSubmit" onClick="setBlockUI(this)" type="submit" name="submit" value='<spring:message code="search.label"/>'></td>
		           			</tr>
		           		</form>
		           		</table>
					</td>
					</tr>
					
													           		
	           		<tr height="10"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="mainList" class="display compact cell-border" width="100%" >
						<thead>
						<tr class="tableHeaderField" >
							<th class="tableHeaderField" title="id">&nbsp;F.navn&nbsp;</th>
							<th class="text14" title="date">&nbsp;Dato&nbsp;</th>
							<th class="text14" title="time">&nbsp;Tid&nbsp;</th>
							<th class="text14" title="avsid">&nbsp;Avs.id&nbsp;</th>
							<th class="text14" title="motid">&nbsp;Mot.id&nbsp;</th>
							<%--
		                    <th class="text14" title="docname">&nbsp;Dok.navn&nbsp;</th>
		                    --%>
		                    <th class="text14" title="typref">&nbsp;Typ&nbsp;</th>
		                    <th class="text14" title="docref">&nbsp;Dok.ref&nbsp;</th>
		                    
		                    <th width="2%" class="text14" title="Fjern record" >Slett</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.mainList}" varStatus="counter">    
			               
			               <tr class="text14">
			               	   <td style="cursor:pointer;" class="text14MediumBlue">
			               	   		<a title="${record.docname}" target="_blank" tabindex=-1 class="renderAttachmentLink" id="renderAttachmentLink${counter.count}">
				               		<%-- <a tabindex=-1 target="_blank" href="tvinnsaddigitollv2_renderAttachment.do?doclnk=${record.docname}"> --%>
	    							<c:choose>
		    							<c:when test="${fn:contains(record.id, '.pdf')}">
		    								<img title="file on server" style="vertical-align:middle;" src="resources/images/pdf.png" width="14" height="14" border="0" alt="PDF arch.">
		    							</c:when>
		    							<c:otherwise>
		    								<img title="file on server" style="vertical-align:middle;" src="resources/images/jpg.png" width="14" height="14" border="0" alt="Other arch.">
		    							</c:otherwise>
	    							</c:choose>
	    							${record.id}
	    							</a>
	    							<form target="_blank" action="tvinnsaddigitollv2_renderAttachment.do" name="renderAttachmentForm${counter.count}" id="renderAttachmentForm${counter.count}" method="post">
										<input type="hidden" name="doclnk${counter.count}" id="doclnk${counter.count}" value="${record.docname}">
									</form>
	    							
				               </td>
				               <td class="text14">&nbsp;${record.date}</td>
			               	   <td class="text14">&nbsp;${record.time}</td>
			               	   <td class="text14">&nbsp;${record.avsid}</td>
			               	   <td class="text14">&nbsp;${record.motid}</td>
			               	   <%-- 
			               	   <td class="text14">&nbsp;${record.docname}</td>
			               	   --%>
			               	   <td class="text14">&nbsp;${record.typref}</td>
			               	   <td class="text14">&nbsp;${record.docref}</td>
			               	   <td class="text14" align="center">
			               	   		<a tabindex=-1 class="removeLink" id="removeLink${counter.count}" runat="server" href="#">
												<img src="resources/images/delete.gif" border="0" alt="remove">
									</a>
									<div style="display: none;" class="clazz_dialog" id="dialogDeleteRecord${counter.count}" title="Dialog">
										<form action="tvinnsaddigitollv2_delete_master_zadmoattf.do" name="deleteRecordForm${counter.count}" id="deleteRecordForm${counter.count}" method="post">
											<input type="hidden" name="current_id1${counter.count}" id="current_id1${counter.count}" value="${record.id}">
											<input type="hidden" name="current_id2${counter.count}" id="current_id2${counter.count}" value="${record.avsid}">
											<input type="hidden" name="current_id3${counter.count}" id="current_id3${counter.count}" value="${record.docref}">
											<input type="hidden" name="current_id4${counter.count}" id="current_id4${counter.count}" value="${model.date}">
											<input type="hidden" name="current_id5${counter.count}" id="current_id5${counter.count}" value="${record.docname}">
											<input type="hidden" name="action${counter.count}" id="action${counter.count}" value="doDelete">
										 	<p class="text14" >Er du sikker på at du vil slette denne?</p>
										</form>
									</div> 
									
							   </td>
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
