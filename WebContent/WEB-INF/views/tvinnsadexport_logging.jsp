<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->
<SCRIPT type="text/javascript" src="resources/js/tvinnsadexport_logging.js?ver=${user.versionEspedsg}"></SCRIPT>

<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
			<tr height="25"> 
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkTopicList" style="display:block;" href="tvinnsadexport.do?action=doFind&sg=${model.sign}">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.export.list.tab"/></font>
						<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkHeader" style="display:block;" href="tvinnsadexport_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
							&sysg=${model.sign}&tuid=${model.tullId}&syst=${model.status}&sydt=${model.datum}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.export.created.mastertopic.tab"/></font>
						<font class="text14MediumBlue">[${model.opd}]</font>
						<c:if test="${model.status == 'M' || empty model.status || model.status == '1'}">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="10%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkInvoices" style="display:block;" href="tvinnsadexport_edit_finansopplysninger.do?action=doFetch&avd=${ model.avd}&sign=${ model.sign}&opd=${ model.opd}&status=${ model.status}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.tvinn.sad.export.finansopplys.createnew.tab"/>
						</font>
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="10%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a style="display:block;" href="editNotisblock.do?action=doFetch&subsys=sade&orig=topic&avd=${ model.avd}&sign=${ model.sign}&opd=${ model.opd}&status=${ model.status}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.tvinn.sad.export.notisblock.createnew.tab"/>
						</font>
					</a>
				</td>				
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="10%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkItemLines" style="display:block;" href="tvinnsadexport_edit_items.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
												&opd=${model.opd}&tullId=${model.tullId}
												&status=${model.status}&datum=${model.datum}&fabl=${recordTopicTvinnSadX.XX}&status=${ model.status}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.tvinn.sad.export.item.createnew.tab"/>
						</font>
						<c:if test="${model.status == 'M' || empty model.status || model.status == '1'}">
							<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
						</c:if>
						
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="10%" valign="bottom" class="tab" align="center" nowrap>
					<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.export.logging.tab"/></font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="10%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkArchive" style="display:block;" href="tvinnsadexport_archive.do?avd=${model.avd}&sign=${model.sign}
												&opd=${model.opd}&tullId=${model.tullId}&status=${model.status}&datum=${model.datum}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.tvinn.sad.export.archive.tab"/>
						</font>
						<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="10%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkEur" style="display:block;" href="tvinnsadexport_edit_eur.do?avd=${model.avd}&sign=${model.sign}
						&opd=${model.opd}&status=${model.status}&datum=${model.datum}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.tvinn.sad.export.eur.tab"/>
						</font>
						
					</a>
				</td>
				<td width="20%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
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
	        <tr height="2"><td></td></tr> 
			<tr>
				<td>
				<table width="100%" cellspacing="0" border="0" cellpadding="0">
					<thead>
					<tr class="tableHeaderField" height="20" valign="left">
					    <th class="tableHeaderFieldFirst">&nbsp;<spring:message code="systema.tvinn.sad.export.logging.list.label.topicNr"/>&nbsp;</th>
						<th class="tableHeaderField">&nbsp;Interch.nr&nbsp;</th>
						<th class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.export.logging.list.label.messageNr"/>&nbsp;</th>
					    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.export.logging.list.label.sender"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.export.logging.list.label.receiver"/>&nbsp;</th> 
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.export.logging.list.label.t"/>&nbsp;</th> 
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.export.logging.list.label.messageId"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.export.logging.list.label.function"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.export.logging.list.label.date"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.export.logging.list.label.sequence"/>&nbsp;</th> 
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.export.logging.list.label.ver"/>&nbsp;</th> 
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.export.logging.list.label.eksp"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.export.logging.list.label.s"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.export.logging.list.label.sent"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;Deklarantnr&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.export.logging.list.label.sentReceive"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.export.logging.list.label.text"/>&nbsp;</th>
	               </tr> 
	               </thead>
	               <tbody>    
		           	<c:forEach items="${list}" var="record" varStatus="counter">    
		               <c:choose>           
		                   <c:when test="${record.msr == 'R'}">
		                       <tr class="tableRow" style="background-color:#EEEEEE;" height="20" >
		                   </c:when>
		                   <c:otherwise>   
		                       <tr class="tableOddRow" height="20" >
		                   </c:otherwise>
		               </c:choose>
		               <td class="tableCellFirst" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.mtdn}&nbsp;&nbsp;<font class="text8">[${model.sign}]</font></td>
		               <td class="tableCell">&nbsp;
		               		<a <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> href="ediftplog.do?sssn=${record.msn}&ftplev=EDISS" target="_new" onClick="window.open(this.href,'targetWindow','top=200px,left=600px,height=800px,width=700px,scrollbars=no,status=no,location=no'); return false;">
		               			<img src="resources/images/bebullet.gif" border="0" alt="Vis Ftp log" >
		               			&nbsp;${record.msn}
		               		</a>
		               	</td>
		               <td class="tableCell" >&nbsp;
		               		<c:choose>
			               		<c:when test="${fn:containsIgnoreCase(record.wurl,'.xml')}">
			               			<c:set var="renderType" value="renderXml" scope="request" />
			               		</c:when>
			               		<c:otherwise>
			               			<c:set var="renderType" value="renderEdifact" scope="request" />
		               		   	</c:otherwise>
	               		   	</c:choose>
		               		
	               		   	<c:choose>
		              		<c:when test="${fn:startsWith(record.wurl, 'http')}">
								<a <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> href="${record.wurl}" target="_new" >
			               			<img src="resources/images/list.gif" border="0" width="16px" height="16px" alt="Show file on cloud" >
			               			${record.mmn}
		               			</a>		              
		               		</c:when>
		               		<c:otherwise>
		               			<a <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> href="tvinnsadexport_${renderType}.do?fp=${record.wurl}" target="_new" >
			               			<img src="resources/images/list.gif" border="0" width="12px" height="12px" alt="Show file" >
			               			&nbsp;${record.mmn}
	               		   		</a>
		               		</c:otherwise>
		               		</c:choose>
	               		   	
		               </td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m0004}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m0010}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m0035}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m0065}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m1n07}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m0068a}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m0068b}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m0068c}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m3039e}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.mst}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.mdt}-${record.mtm}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.deklarant}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.msr}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.wtxt}
		               		<c:if test="${record.wmore == 'X'}">
		               			&nbsp;&nbsp;
		               			<a href="tvinnsadexport_renderLargeText.do?fmn=${record.mmn}" target="_blank" onClick="window.open(this.href,'targetWindow','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=700,height=500'); return false;">
		               				<img valign="bottom" src="resources/images/largeTextContent.png" width="16" hight="16" border="0" alt="large text content">
		               			</a>
							</c:if>
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
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

