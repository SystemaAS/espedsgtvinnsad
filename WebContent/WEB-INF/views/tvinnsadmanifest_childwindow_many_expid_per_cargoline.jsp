<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmanifest_childwindow_many_expid_per_cargoline.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" cellspacing="0" border="0" cellpadding="0">
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
				<input type="hidden" name="wsavd" id="wsavd" value="${model.wsavd}">
				<input type="hidden" name="wsopd" id="wsopd" value="${model.wsopd}">
				<input type="hidden" name="clrg" id="clrg" value="${model.clrg}">
				<input type="hidden" name="cl0068a" id="cl0068a" value="${model.cl0068a}">
				<input type="hidden" name="cl0068b" id="cl0068b" value="${model.cl0068b}">
				<div id="tollErrorMessage"></div>
				<table class="tableBorderWithRoundCorners" >
					<tr>
			 		 <td valign="top" class="text14">&nbsp;Eksp.Id&nbsp;<span id="tollErrorMessage"></span>
	 						<table id="mainList" class="display compact cell-border" >
		 						<tr class="tableHeaderField" >
		 						<th align="left" class="text14">Type</th>
		 						<th align="left" class="text14">Eksp.id</th>
		 						<th align="left" class="text14">TODO</th>
		 						<th align="left" class="text14">Avd</th>
		 						<th align="left" class="text14">Oppd.nr</th>
		 						</tr>
	 						
			 					<c:forEach items="${model.list}" var="record" varStatus="counter">
			 						<tr class="text14 tableRow">
			 						<td class="tableCellFirst">
			 							<input title="doctyp${record.doctyp}_doclnk${record.doclnk}" id="${counter.count}" class="inputFormSubmit11Slim isa_warning" type="button" name="uplButton${counter.count}" value="Send til toll.no" onClick="sendFile(this);">
			 						</td>
			 						<td class="tableCell" style="white-space:nowrap">${record.doctyp}</td>
			 						<td class="tableCell" style="white-space:nowrap;color:darkgray">${record.doctxt}</td>
									<td class="tableCell" style="white-space:nowrap">
	 									<c:choose>
						              		<c:when test="${fn:startsWith(record.doclnk, 'http')}">
												<a href="${record.doclnk}" target="_new" >
							               			<img src="resources/images/pdf.png" border="0" width="16px" height="16px" alt="Visa arkivdokument on cloud" >
							               			${record.doctxtaux} 
						               			</a>		              
						               		</c:when>
						               		<c:otherwise>
						               			<a target="_blank" href="tvinnsadmanifest_renderArchive.do?doclnk=${record.doclnk}">
		    		    							<c:choose>
			    		    							<c:when test="${fn:contains(record.doclnk, '.pdf')}">
			    		    								<img title="Archive" style="vertical-align:middle;" src="resources/images/pdf.png" width="14" height="14" border="0" alt="PDF arch.">
			    		    							</c:when>
			    		    							<c:otherwise>
			    		    								<img title="Archive" style="vertical-align:middle;" src="resources/images/jpg.png" width="14" height="14" border="0" alt="Other arch.">
			    		    							</c:otherwise>
		    		    							</c:choose>
		    		    							${record.doclnk}
				   								</a>
						               		</c:otherwise>
					               		</c:choose>
	   								</td>
		   							<td class="tableCell" style="white-space:nowrap">${record.docdat}&nbsp;${record.doctim}</td>
		   							
	   								</tr>
			 					</c:forEach>
			 				</table>
		 				</td>
						</tr>
					</table>
        			</form>
		</td>
		</tr>
	</table> 
