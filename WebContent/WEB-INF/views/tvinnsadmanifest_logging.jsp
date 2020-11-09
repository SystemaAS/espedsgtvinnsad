<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->
<SCRIPT type="text/javascript" src="resources/js/tvinnsadmanifest_logging.js?ver=${user.versionEspedsg}"></SCRIPT>

<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
			<tr height="25"> 
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkManifestList" style="display:block;" href="tvinnsadmanifest.do?action=doFind&avd=${model.efavd}&sign=${model.efsg}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.manifest.list.tab"/></font>
							<img src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td title="" width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkHeader" style="display:block;" href="tvinnsadmanifest_edit.do?action=doFetch&efuuid=${model.efuuid}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.manifest.created.header.tab"/>
							</font>
							<font class="text14MediumBlue">[${model.efpro}]</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 id="alinkItems" style="display:block;" href="tvinnsadmanifest_edit_cargolines.do?action=doFetch&efpro=${model.efpro}&efsg=${model.efsg}
													&efavd=${model.efavd}&efuuid=${model.efuuid}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.manifest.createnew.last.tab"/>
							</font>
							<img src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
							
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.manifest.created.header.logging.tab"/></font>
						<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
					</td>
					
					<td width="50%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			</tr>
			<tr height="3"><td></td></tr>
		</table>
		</td>
	</tr>
	
	<%-- list component --%>
	<tr>
		<td>		
		<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    	<%-- separator --%>
	        <tr height="1"><td></td></tr> 
			<tr>
				<td>
				<table style="width:100%;" id="containerdatatableTable" cellspacing="2" align="left" >
				<tr>
				<td class="text11">

				<table id="mainList" class="display compact cell-border" >
					<thead>
					<tr class="tableHeaderField" height="20" >
						<th class="tableHeaderFieldFirst">&nbsp;Avsender&nbsp;</th>
						<th class="tableHeaderField">&nbsp;Mottaker&nbsp;</th>
					    <th class="tableHeaderField">&nbsp;Msg Id&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;Funktion&nbsp;</th> 
	                    <th class="tableHeaderField">&nbsp;Status&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;Fil&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;Sent&nbsp;</th>
	                    <th title="S=SEND; R=RECEIVED" class="tableHeaderField">&nbsp;S/R&nbsp;</th>
	                    
	               </tr> 
	               </thead>
	               <tbody>    
		           	<c:forEach items="${model.list}" var="record" varStatus="counter">    
		               <c:choose>           
		                   <c:when test="${record.ssr == 'R'}">
		                       <tr class="tableRow" style="background-color:#EEEEEE;" height="20" >
		                   </c:when>
		                   <c:otherwise>   
		                       <tr class="tableOddRow" height="20" >
		                   </c:otherwise>
		               </c:choose>
		               
		               <td width="2%" class="tableCellFirst" <c:if test="${record.ssr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.s0004}</td>
		               
		               <td width="2%" class="tableCell" <c:if test="${record.ssr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.s0010}</td>
		               <td align="center" width="2%" class="tableCell" <c:if test="${record.ssr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.s0026}</td>
		               <td align="center" width="2%" class="tableCell" <c:if test="${record.ssr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.s0036}</td>
		               <td align="center" width="2%" class="tableCell" <c:if test="${record.ssr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.sst}</td>
		               <td class="tableCell">&nbsp;
		               		<a <c:if test="${record.ssr == 'R'}">style="color:#9F6000;"</c:if>  href="tvinnsadmanifest_logging_renderJson.do?fp=${record.wurl}" target="_new" >
			               		<img src="resources/images/largeTextContent.png" border="0" width="16px" height="16px" alt="Vis payload" >
			               		&nbsp;${record.own_fileName}
	               		   	</a>
		               </td>
		               <td align="center" width="2%" class="tableCell" <c:if test="${record.ssr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.sdt}&nbsp;${record.stm}</td>
		               <td align="center" width="2%" class="tableCell" <c:if test="${record.ssr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.ssr}</td>
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
		</td>
	</tr>
	
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

