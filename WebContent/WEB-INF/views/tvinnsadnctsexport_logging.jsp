<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->
<SCRIPT type="text/javascript" src="resources/js/tvinnsadnctsexport_logging.js?ver=${user.versionEspedsg}"></SCRIPT>

<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
			<tr height="25"> 
				<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkTopicList" tabindex=-1 style="display:block;" 
					<c:choose>
						<c:when test="${empty model.sign}">href="tvinnsadnctsexport.do?action=doFind&sign=${user.tvinnSadSign}"</c:when>
						<c:otherwise>href="tvinnsadnctsexport.do?action=doFind&sign=${model.sign}"</c:otherwise>
					</c:choose> >
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.tab"/></font>
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkHeader" tabindex=-1 style="display:block;" href="tvinnsadnctsexport_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
							&sysg=${model.sign}&tuid=${model.tullId}&syst=${model.status}&sydt=${model.datum}">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.created.mastertopic.tab"/></font>
						<font class="text12MediumBlue">[${model.opd}]</font>
						<c:if test="${model.status == 'G' || model.status == 'F' || model.status == 'M' || empty model.status}">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
					</a>
				</td>				
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkItemLines" tabindex=-1 style="display:block;" href="tvinnsadnctsexport_edit_items.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
												&opd=${model.opd}&tullId=${model.tullId}&mrnNr=${model.mrnNr}
												&status=${model.status}&datum=${model.datum}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.item.createnew.tab"/>
						</font>
						<c:if test="${model.status == 'G' || model.status == 'F' || model.status == 'M' || empty model.status}">
							<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
						</c:if>
						
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="15%" valign="bottom" class="tab" align="center" nowrap>
					<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.logging.tab"/></font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkArchive" tabindex=-1 style="display:block;" href="tvinnsadnctsexport_archive.do?avd=${model.avd}&sign=${model.sign}
												&opd=${model.opd}&tullId=${model.tullId}&mrnNr=${model.mrnNr}
												&status=${model.status}&datum=${model.datum}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.archive.tab"/>
						</font>
						<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
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
					<tr class="tableHeaderField" height="20" valign="left">
					
	                    <td class="tableHeaderFieldFirst">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.logging.list.label.topicNr"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;Interch.nr&nbsp;</td>
						<td class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.logging.list.label.messageNr"/>&nbsp;</td> 
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.logging.list.label.type"/>&nbsp;</td> 
	                      
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.logging.list.label.date"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.logging.list.label.time"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.logging.list.label.sentReceive"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.logging.list.label.text"/>&nbsp;</td>
	               </tr>     
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
		               		<a <c:if test="${record.m1225 == 'R'}">style="color:#9F6000;"</c:if> href="tvinnsadnctsexport_renderEdifact.do?fp=${record.wurl}" target="_new" >
			               		<img src="resources/images/list.gif" border="0" width="12px" height="12px" alt="Visa Edifact" >
			               		&nbsp;${record.mmn}
	               		   	</a>
		               </td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m1225}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.mdt}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.mtm}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.msr}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.wtxt}
		               		<c:if test="${record.wmore == 'X'}">
		               			&nbsp;&nbsp;
		               			<a href="tvinnsadnctsexport_renderLargeText.do?fmn=${record.mmn}" target="_blank" onClick="window.open(this.href,'targetWindow','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=700,height=500'); return false;">
		               				<img valign="bottom" src="resources/images/largeTextContent.png" width="16" hight="16" border="0" alt="large text content">
		               			</a>
							</c:if>
		               </td>
		            </tr> 
		            </c:forEach>
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

