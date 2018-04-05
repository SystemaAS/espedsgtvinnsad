<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>			
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadexport_edit_notisblock.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	
	.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
	.ui-timepicker-div dl { text-align: left; }
	.ui-timepicker-div dl dt { float: left; clear:left; padding: 0 0 0 5px; }
	.ui-timepicker-div dl dd { margin: 0 10px 10px 40%; }
	.ui-timepicker-div td { font-size: 90%; }
	.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
	
	.ui-timepicker-rtl{ direction: rtl; }
	.ui-timepicker-rtl dl { text-align: right; padding: 0 5px 0 0; }
	.ui-timepicker-rtl dl dt{ float: right; clear: right; }
	.ui-timepicker-rtl dl dd { margin: 0 40% 10px 10px; }
	
	</style>
	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<c:choose>
				<c:when test="${not empty model.orig && 'topic'==model.orig}">
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkTopicList" style="display:block;" 
							<c:choose>
								<c:when test="${empty model.sign}">href="tvinnsadexport.do?action=doFind&sg=${user.tvinnSadSign}"</c:when>
								<c:otherwise>href="tvinnsadexport.do?action=doFind&sg=${model.sign}"</c:otherwise>
							</c:choose> > 
							<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.export.list.tab"/></font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkHeader" style="display:block;" href="tvinnsadexport_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
								&sysg=${model.sign}&tuid=${refnr}&syst=${model.status}&sydt=${model.datum}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.export.created.mastertopic.tab"/></font>
							<font class="text12MediumBlue">[${model.opd}]</font>
							<c:if test="${model.status == 'M' || empty model.status}">
								<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
							</c:if>
						</a>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkInvoices" style="display:block;" href="tvinnsadexport_edit_finansopplysninger.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
													&opd=${model.opd}&status=${model.status}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.export.finansopplys.createnew.tab"/>
							</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.export.notisblock.createnew.tab"/></font>
						<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="notisblock">
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkItemLines" style="display:block;" href="tvinnsadexport_edit_items.do?action=doFetch&avd=${model.avd}&sign=${ model.sign}
													&opd=${model.opd}&status=${ model.status}&fabl=&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.export.item.createnew.tab"/>
							</font>
							
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkLogging" style="display:block;" href="tvinnsadexport_logging.do?avd=${ model.avd}&sign=${ model.sign}
													&opd=${model.opd}&status=${model.status}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.export.logging.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkArchive" style="display:block;" href="tvinnsadexport_archive.do?avd=${model.avd}&sign=${model.sign}
													&opd=${model.opd}&status=${model.status}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.export.archive.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
						</a>
					</td>
					<%-- This tab should be hidden here ?
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkOmberegning" style="display:block;" href="tvinnsadexport_edit_omberegning.do?action=doFetch&avd=${model.avd}&sysg=${model.sign}
													&opd=${ model.opd}&syst=${model.status}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tvinn.sad.export.omberegning.mastertopic.tab"/>
							</font>
						</a>
					</td>
					--%>
					<td width="20%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:when>
				<c:otherwise>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a style="display:block;" href="tvinnsadexport.do?action=doFind&sg=${model.sign}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.export.list.tab"/></font>
							<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.export.notisblock.createnew.tab"/></font>
						<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="notisblock">
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="70%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				
				</c:otherwise>
			</c:choose>
		</tr>
	</table>
	<%-- -------------------------------- --%>	
 	<%-- tab area container MASTER TOPIC  --%>
	<%-- -------------------------------- --%>
 	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
		<tr height="15"><td colspan="2">&nbsp;</td></tr>	
		
		<tr>
		<td >
		<table border="0" width="95%" align="center">
			<tr>
	 			<td >		
	 				<%-- MASTER Topic header --%>
	 				<table width="80%" align="left" class="formFrameHeaderTransparent" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12MediumBlue">
				 				&nbsp;Avd&nbsp;<b>${model.avd}</b>
				 				&nbsp;Tolldeknr.&nbsp;<b>${model.opd}</b>
			 				</td>
		 				</tr>
	 				</table>
	            	</td>
           	</tr> 
           	<%-- ---------------------- --%>
           	<%-- LIST of existent ITEMs --%>
           	<%-- ---------------------- --%>
           	<tr>
				<td>
					<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    				<%-- separator --%>
	        			<tr height="10"><td></td></tr> 
						<tr >
							<td>
							<form name="createNewItemLine" id="createNewItemLine" method="post" action="editNotisblock.do">
								<%--Required key parameters --%>
							 	<input type="hidden" name="action" id="action" value='doFetch'/>
							 	<input type="hidden" name="subsys" id="subsys" value='sade'/>
							 	<input type="hidden" name="orig" id="orig" value="${model.orig}"/>
							 	<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
							 	<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
							 	<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
								<table width="80%" cellspacing="0" border="0" cellpadding="0">
									<tr>
										<td class="text12Bold">
											<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submit" onclick="javascript: form.action='editNotisblock.do';" value="<spring:message code="systema.tvinn.sad.export.item.line.init.createnew.submit"/>">
										</td>
									</tr>
									<tr>
										<td class="text12Bold">&nbsp;Antall notater&nbsp;&nbsp;<font class="text12MediumBlue"><b>${model.recordItemContainerFinansOpplysningerTopic.totalNumberOfItemLines}</b></font>
						            		</td>
									</tr>
									<tr height="2"><td></td></tr>
								</table>
							</form>
							</td>
						</tr> 
						
						<tr>
							<td class="ownScrollableSubWindow" style="width:830px; height:10em;">
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr class="tableHeaderField" height="20" valign="left">
									    <td class="tableHeaderFieldFirst">&nbsp;Linje&nbsp;</td>   
					                    <td class="tableHeaderField" nowrap>&nbsp;Dato&nbsp;</td>
					                    <td class="tableHeaderField" nowrap>&nbsp;Part&nbsp;</td>
					                    <td class="tableHeaderField" nowrap>&nbsp;Fritekst&nbsp;</td>
					                    	<td align="center" class="tableHeaderField" nowrap>Slett</td>
					               </tr> 
					               <form name="formItemList" id="formItemList" method="POST" >
					               		<input type="hidden" name="frtavd" id="frtavd" value="${model.avd}">
				 						<input type="hidden" name="frtopd" id="frtopd" value="${model.opd}">
				 						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
				 					  <c:forEach items="${model.list}" var="record" varStatus="counter">    
							               <c:choose>           
							                   <c:when test="${counter.count%2==0}">
							                       <tr class="tableRow" height="20" >
							                   </c:when>
							                   <c:otherwise> 
							                       <tr class="tableOddRow" height="20" >
							                   </c:otherwise>
							               </c:choose>
							               <td width="2%" class="tableCellFirst" align="right">
							               		<a tabindex=-1 id="recordUpdate_${record.frtli}_${record.frtdt}" href="#" onClick="getNotisblockItemData(this);">${record.frtli}
							               			<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;
							               		</a>&nbsp;&nbsp;
							               </td>
							               <td class="tableCell" >&nbsp;${record.frtdt}</td>
							               <td class="tableCell" >&nbsp;${record.frtkod}</td>
							               <td class="tableCell" >&nbsp;${record.frttxt}</td>
									       <td class="tableCell" align="center" nowrap>
							               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="editNotisblock.do?action=doDelete&subsys=sade&orig=${model.orig}&sign=${model.sign}&frtli=${record.frtli}&frtdt=${record.frtdt}&opd=${record.frtopd}&avd=${record.frtavd}">
							               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
							               	</a>	&nbsp;
							               </td>
							            </tr>
								        <%-- this param is used ONLY in this JSP 
								        <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" />
								        --%> 
								        <%-- this param is used throughout the Controller --%>
								        <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" /> 
								        </c:forEach>
						            </form>
						        </table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr height="3"><td></td></tr>
			<%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td>
	            	<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
	            	<tr>
					<td class="textError">					
			            <ul>
			            <c:forEach var="error" items="${errors.allErrors}">
			                <li >
			                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>
			                </li>
			            </c:forEach>
			            </ul>
					</td>
					</tr>
					</table>
				</td>
			</tr>
			</spring:hasBindErrors>
			<%-- ------------------------------------------------- --%>
           	<%-- DETAIL Section - Create Item line PRIMARY SECTION --%>
           	<%-- ------------------------------------------------- --%>
           	<tr>
	 			<td >
	 				<form name="sadExportEditTopicNotisblockItemForm" id="sadExportEditTopicNotisblockItemForm" method="post">
				 	<%--Required key parameters --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="subsys" id="subsys" value='sade'/>
				 	<input type="hidden" name="orig" id="orig" value="${model.orig}"/>
				 	
				 	<input type="hidden" name="frtopd" id="frtopd" value="${model.opd}"/>
				 	<input type="hidden" name="frtavd" id="frtavd" value="${model.avd}"/>
				 	<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
				 	<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
				 	<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
				 	<input type="hidden" name="ceilingLineNumber" id="ceilingLineNumber" value="${model.containerParent.ceilingLineNumber}"/>
				 	
				 	
				 	<%-- <input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" /> --%>
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="80%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White" align="left" >
				 				<b>&nbsp;&nbsp;N<label onClick="showPop('debugPrintlnAjaxItemFetchAdmin');" >o</label>tat&nbsp;</b>
		 									<span style="position:absolute; left:150px; top:200px; width:800px; height:400px;" id="debugPrintlnAjaxItemFetchAdmin" class="popupWithInputText"  >
								           		<div class="text11" align="left">
								           			<label id="debugPrintlnAjaxItemFetchInfo"></label>
								           			<br/>
								           			&nbsp;&nbsp;
								           			<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('debugPrintlnAjaxItemFetchAdmin');">
								           			Close
								           			</button> 
								           		</div>
								        		</span>
				 				<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit">
				 				<span style="position:absolute; left:150px; top:200px; width:800px; height:400px;" id="updateInfo" class="popupWithInputText"  >
		           		   			<div class="text12" align="left" style="display:block;width:700px;word-break:break-all;">
		           		   				${activeUrlRPGUpdate_TvinnSad}<br/><br/>
		           		   				<button name="updateInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('updateInfo');">Close</button> 
		           		   			</div>
						        </span>  
			 				</td>
		 				</tr>
	 				</table>
					<table width="80%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15"><td class="text" align="left"></td></tr>
				 		<input type="hidden" name="frtli" id="frtli" value="${model.record.frtli}"/>
				 		
				 		<tr>
					 		<td>
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
							            <td class="text12" align="left"><span title="frtdt">&nbsp;Dato</span></td>
							            <td class="text12" align="left"><span title="frtkod">&nbsp;Part</span></td>
							            <td class="text12" align="left"><span title="frttxt">&nbsp;Tekst</span></td>
							        </tr>
							        <tr>
						        		<td align="left" valign="top">
						        			<input type="text" class="inputText" name="frtdt" id="frtdt" size="9" maxlength="8" value="${model.record.frtdt}">
										</td>
										<td align="left" valign="top">
						        			<input type="text" class="inputText" name="frtkod" id="frtkod" size="2" maxlength="1" value="${model.record.frtkod}">
										</td>
										<td class="text12" align="left">
						            		<textarea rows="1" cols="79" class="inputText" name="frttxt" id="frttxt" maxlength="79">${model.record.frttxt}</textarea>
							            </td>
							        </tr>
							        <tr height="10"><td class="text" align="left"></td></tr>
						        </table>
					        </td>
				        </tr>
					    <tr height="10"><td colspan="2" ></td></tr>
					    <tr>	
						    <td align="left" colspan="5">
								<input class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='editNotisblock.do';" value="Lagre notat">
							</td>							        	
				        </tr>
        	        </table>
        	        </form>
		        </td>
		    </tr>
			<tr height="20"><td colspan="2" ></td></tr>
			<tr height="30"><td></td></tr>
		</table>
		</td>
		</tr>
	</table>    

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

