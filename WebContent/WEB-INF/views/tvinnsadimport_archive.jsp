<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->
<SCRIPT type="text/javascript" src="resources/js/tvinnsadimport_archive.js?ver=${user.versionEspedsg}"></SCRIPT>

<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
			<tr height="25"> 
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkTopicList" tabindex=-1 style="display:block;" href="tvinnsadimport.do?action=doFind&sg=${model.sign}">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.import.list.tab"/></font>
						<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkHeader" tabindex=-1 style="display:block;" href="tvinnsadimport_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
							&sysg=${model.sign}&tuid=${model.tullId}&syst=${model.status}&sydt=${model.datum}&o2_sist=${ model.o2_sist}&o2_sidt=${ model.o2_sidt}&o2_simf=${ model.o2_simf}">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.import.created.mastertopic.tab"/></font>
						<font class="text12MediumBlue">[${model.opd}]</font>
						<c:if test="${model.status == 'M' || empty model.status}">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
					</a>
				</td>
				
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkInvoices" tabindex=-1 style="display:block;" href="tvinnsadimport_edit_finansopplysninger.do?action=doFetch&avd=${ model.avd}&sign=${ model.sign}
												&opd=${ model.opd}&status=${ model.status}&fabl=${ XX.dkih_222}&o2_sist=${ model.o2_sist}&o2_sidt=${ model.o2_sidt}&o2_simf=${ model.o2_simf}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.tvinn.sad.import.finansopplys.createnew.tab"/>
						</font>
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a tabindex=-1 style="display:block;" href="editNotisblock.do?action=doFetch&subsys=sadi&orig=topic&avd=${ model.avd}&sign=${ model.sign}
												&opd=${ model.opd}&o2_sist=${ model.o2_sist}&o2_sidt=${ model.o2_sidt}&o2_simf=${ model.o2_simf}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.tvinn.sad.import.notisblock.createnew.tab"/>
						</font>
					</a>
				</td>			
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkItemLines" tabindex=-1 style="display:block;" href="tvinnsadimport_edit_items.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
												&opd=${model.opd}&tullId=${model.tullId}
												&status=${model.status}&datum=${model.datum}&fabl=${recordTopic.svih_fabl}&o2_sist=${ model.o2_sist}&o2_sidt=${ model.o2_sidt}&o2_simf=${ model.o2_simf}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.tvinn.sad.import.item.createnew.tab"/>
						</font>
						<c:if test="${model.status == 'M' || empty model.status}">
							<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
						</c:if>
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkLogging" tabindex=-1 style="display:block;" href="tvinnsadimport_logging.do?avd=${model.avd}&sign=${model.sign}
												&opd=${model.opd}&tullId=${model.tullId}&status=${model.status}&datum=${model.datum}&o2_sist=${ model.o2_sist}&o2_sidt=${ model.o2_sidt}&o2_simf=${ model.o2_simf}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.tvinn.sad.import.logging.tab"/>
						</font>
						<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
					</a>
				</td>
					
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tab" align="center" nowrap>
					<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.import.archive.tab"/></font>
					<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
				</td>
				<%-- This tab should be hidden here ?
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkOmberegning" style="display:block;" href="tvinnsadimport_edit_omberegning.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
												&opd=${ model.opd}&status=${model.status}&o2_sist=${ model.o2_sist}&o2_sidt=${ model.o2_sidt}&o2_simf=${ model.o2_simf}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.tvinn.sad.import.omberegning.mastertopic.tab"/>
						</font>
					</a>
				</td>
				--%>	
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
	    	
	        <tr height="2"><td></td></tr> 
			<tr>
				<td>
				<table width="100%" cellspacing="0" border="0" cellpadding="0">
					<tr class="tableHeaderField" height="20" valign="left">
					
	                    <td class="tableHeaderFieldFirst">&nbsp;<spring:message code="systema.tvinn.sad.import.archive.list.label.topicNr"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.import.archive.list.label.docType"/>&nbsp;</td> 
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.import.archive.list.label.subject"/>&nbsp;</td> 
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.import.archive.list.label.date"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.import.archive.list.label.time"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tvinn.sad.import.archive.list.label.additionalInfo"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;Dokument</td> 
	                    
	               </tr> 
	               <c:if test="${not empty list}">    
		           	<c:forEach items="${list}" var="record" varStatus="counter">    
		               <c:choose>           
		                   <c:when test="${counter.count%2==0}">
		                       <tr class="tableRow" height="20" >
		                   </c:when>
		                   <c:otherwise>   
		                       <tr class="tableOddRow" height="20" >
		                   </c:otherwise>
		               </c:choose>
		               <td class="tableCellFirst" >&nbsp;${model.opd}&nbsp;&nbsp;<font class="text8">[${model.sign}]</font></td>
		               <td class="tableCell">&nbsp;${record.docType}</td>
		               <td class="tableCell" >&nbsp;${record.subject}</td>
		               <td class="tableCell" >&nbsp;${record.createDate}</td>
		               <td class="tableCell" >&nbsp;${record.createTime}</td>
		               <td class="tableCell" >&nbsp;${record.additionalInfo}</td>
		               <%-- <td class="tableCell" >&nbsp;${record.url}</td> --%> 
		               <td class="tableCell" >&nbsp;
		               		<a href="tvinnsadimport_renderArchive.do?fp=${record.url}" target="_new" >
			               		<img src="resources/images/pdf.png" border="0" width="16px" height="16px" alt="Visa arkivdokument" >
			               		${record.documentName}
		               		</a>
		               </td>
		            </tr> 
		            </c:forEach>
		            </c:if>
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

