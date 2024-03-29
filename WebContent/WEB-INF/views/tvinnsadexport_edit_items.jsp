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
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadexport_edit_items.js?ver=${user.versionEspedsg}"></SCRIPT>
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	
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
		<tr height="2">
			<td>
				<input type="hidden" name="modelStatus" id="modelStatus" value='${model.status}'>
			</td>
		</tr>		<tr height="25"> 
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkTopicList" style="display:block;" href="tvinnsadexport.do?action=doFind&sg=${model.sign}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.export.list.tab"/></font>
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkHeader" style="display:block;" href="tvinnsadexport_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
						&sysg=${model.sign}&tuid=${refnr}&syst=${model.status}&sydt=${model.datum}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
					
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.export.created.mastertopic.tab"/></font>
					<font class="text14MediumBlue">[${model.opd}]</font>
					<c:if test="${model.status == 'M' || empty model.status}">
						<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="10%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkInvoices" style="display:block;" href="tvinnsadexport_edit_finansopplysninger.do?action=doFetch&avd=${model.avd}&sign=${model.sign}&opd=${model.opd}&status=${ model.status}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.export.finansopplys.createnew.tab"/>
					</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="10%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkNotis" style="display:block;" href="editNotisblock.do?action=doFetch&subsys=sade&orig=topic&avd=${model.avd}&sign=${ model.sign}&opd=${ model.opd}&status=${ model.status}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.export.notisblock.createnew.tab"/>
					</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="10%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.export.item.createnew.tab"/></font>
				<c:if test="${model.status == 'M' || empty model.status || model.status == '10' || model.status == '20'}">
					<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
				</c:if>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="10%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkLogging" style="display:block;" href="tvinnsadexport_logging.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}&refnr=${dkih_07}
													&status=${model.status}&datum=${model.datum}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.export.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="10%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkArchive" style="display:block;" href="tvinnsadexport_archive.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}&refnr=${dkih_07}
													&status=${model.status}&datum=${model.datum}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
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
	 				<table width="100%" align="center" class="formFrameHeaderTransparent" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14MediumBlue">
				 				&nbsp;Avd&nbsp;<b>${model.avd}</b>
				 				&nbsp;Tolldeknr&nbsp;<b>${model.opd}</b>
				 				&nbsp;Sign&nbsp;<b>${model.sign}</b>
				 				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				Status:&nbsp;<b>${model.status}</b>
				 				&nbsp;&nbsp;Dekl.:&nbsp;<b>${recordTopicTvinnSad.sedty}</b>
				 				&nbsp;&nbsp;Eksp.type:&nbsp;<b>${recordTopicTvinnSad.sedp}</b>
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
					           		<br/>
					           		 Kun status <b>M</b> eller <b>' '</b> kan redigeres.
					           		 <br/><br/> 
					           			<table width="90%" align="center" cellspacing="0" border="0" cellpadding="0">
					           				<c:forEach var="record" items="${model.statusCodeList}" varStatus="counter" >
					           				<c:choose>
					           					<c:when test="${counter.count%2 != 0}" >
						           					<tr>
						           					<td class="tableCellFirst" width="50%">
					           				 			<b>${record.dkkd_kd}</b>&nbsp;${record.dkkf_txt}
					           						</td>
					           					</c:when>
						           				<c:otherwise>
													<td class="tableCell" width="50%">
					           				 			<b>${record.dkkd_kd}</b>&nbsp;${record.dkkf_txt}
					           						</td>
					           						</tr>
						           				</c:otherwise>
					           				</c:choose>
					           				</c:forEach>
					           			</table>
								 </span>
								 </div>	
			 				</td>
		 				</tr>
	 				</table>
					<%-- MASTER Topic information [it is passed through a session object: XX] --%>
				 	<table height="40" width="100%" align="center" class="formFrameTitaniumWhite" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="2"><td class="text" align="left" colspan="2"></td></tr>
				 		<tr>
					 		<td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text12Bold" align="left" >Eksportør</td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							        		<td width="30%" class="text12" align="left">Reg.nr&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.serg}</td>
							           	
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">Navn&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.senak}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">Adresse-1&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.seadk1}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">Adresse-2&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.seadk2}</td>
							        </tr>
							        
									<tr>
							            <td width="30%" class="text12" align="left">Adresse-3&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.seadk3}</td>
							        </tr>						        
							        
			        	        </table>
					        </td>
					        <td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text12Bold" align="left" >Mottaker</td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">&nbsp;</td>
							           	<td class="text12MediumBlue" align="left"></td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">Navn&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.senas}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">Adresse-1&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.seads1}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">Adresse-2&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.seads2}</td>
							        </tr>
							        
									<tr>
							            <td width="30%" class="text12" align="left">Adresse-3&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.seads3}</td>
							        </tr>
							        <%--
									<tr>
							            <td width="30%" class="text12" align="left">Handläggare&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${XX.svih_moha}</td>
							        </tr>
							         --%>
							        
			        	        </table>
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
							<form name="createNewItemLine" id="createNewItemLine" method="post" action="tvinnsadexport_edit_items.do">
								<input type="hidden" name="action" id="action" value='doFetch'>
								<input type="hidden" name="renew" id="renew" value='1'>
				 				<input type="hidden" name="avd" id="avd" value='${model.avd}'>
				 				<input type="hidden" name="sign" id="sign" value='${model.sign}'>
								<input type="hidden" name="opd" id="opd" value='${model.opd}'>
				 				<input type="hidden" name="refnr" id="refnr" value='${dkih_07}'>
				 				<input type="hidden" name="status" id="status" value='${model.status}'>
				 				<input type="hidden" name="datum" id="datum" value='${model.datum}'>
				 				<input type="hidden" name="fabl" id="fabl" value='${recordTopicTvinnSad.sebel1}'>
				 				<input type="hidden" name="totalGrossWeight" id="totalGrossWeight" value='${recordTopicTvinnSad.sevkb}'>
				 				<input type="hidden" name="senderId" id="senderId" value='${recordTopicTvinnSad.seknk}'>
				 										
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr>
										<td class="text14Bold">
											<c:if test="${model.status == 'M' || empty model.status}">
												<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submitCreateNew" id="submitCreateNew" onclick="javascript: form.action='tvinnsadexport_edit_items.do';" value="<spring:message code="systema.tvinn.sad.export.item.line.init.createnew.submit"/>">
											</c:if>
											<button name="allItemsButton" class="inputFormSubmitStd" type="button" onClick="showPop('allItems');" >Vis alle</button> 
										        <span style="background-color:#EEEEEE; position:absolute; left:50px; top:200px; width:1200px; height:1000px;" id="allItems" class="popupWithInputTextThickBorder"  >
									           		
									           			<table id="containerdatatableTable" width="98%" align="left" >
									           			<tr>
										           			<td colspan="3" class="text14"><b>Varelinjer</b></td>
										           		</tr>
											           	<tr>	
															<td class="text14">
															<table id="tblItemLinesAll" class="display compact cell-border" >
																<thead>
																<tr class="tableHeaderField">
																    <th class="text14">&nbsp;Linjenr.&nbsp;</th>  
																    <th class="text14" nowrap>&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.svfyl.fk"/>&nbsp;</th>
												                    <th class="text14" nowrap>&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.svvnt.vareNr"/>&nbsp;</th>
												                    <th class="text14">&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.svvktn.nettov"/>&nbsp;</th>
												                    <th class="text14">&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.svntm.mengde"/>&nbsp;</th>
												                    <th class="text14">&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.svpre.preference"/>&nbsp;</th>
												                    <th class="text14">&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.svbelt.varansPris"/>&nbsp;</th>
												                    <th class="text14">&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.svavt.ff"/></th>
												                    <th class="text14">&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.svavtp.tollsats"/></th>
												                    <th class="text14">&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.wd1.vareDescription"/>&nbsp;</th>
												                    <th align="center" class="text14">&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.sverr.error"/>&nbsp;</th>
												                    <c:if test="${model.status == 'M' || empty model.status}">
												                    	<th align="center" class="text14" nowrap>Slett</th>
												                    </c:if>
												                </tr> 
												                </thead>
											               		<tbody>
										 						  <c:forEach items="${model.list}" var="record" varStatus="counter">    
														               <c:choose>           
														                   <c:when test="${counter.count%2==0}">
														                       <tr class="tableRow" height="20" >
														                   </c:when>
														                   <c:otherwise> 
														                       <tr class="tableOddRow" height="20" >
														                   </c:otherwise>
														               </c:choose>
														               <td class="text14" align="center">&nbsp;${record.svli}</td>
														               <td class="text14" align="center">&nbsp;${record.svfyl}</td>
														               <td class="text14" >&nbsp;${record.svvnt}</td>
														               <td class="text14" >&nbsp;${record.svvktn}</td>
														               <td class="text14" >&nbsp;${record.svntm}</td>
														               <td class="text14" >&nbsp;${record.svpre}</td>
														               <td class="text14" >&nbsp;${record.svbelt}</td>
														               <td class="text14" >&nbsp;${record.svavt}</td>
														               <td class="text14" >&nbsp;${record.svavtp}</td>
														               <td class="text14" >&nbsp;${record.wd1}</td>
														               <td align="center" class="text14">&nbsp;
														               		<c:if test="${not empty record.sverr}">
														               			<img src="resources/images/redFlag.png" width="18px" height="18px" border="0" alt="remove">
														               		</c:if>
														               </td>
														               <c:if test="${model.status == 'M' || empty model.status}">	
															               <td class="text14" align="center" nowrap>
															               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadexport_edit_items.do?action=doDelete&sign=${model.sign}&avd=${record.svavd}&opd=${record.svtdn}&lin=${record.svli}&fabl=${recordTopicTvinnSad.sebel1}">
															               		<img src="resources/images/delete.gif" border="0" alt="remove">
															               	</a>	
															               </td>
														               </c:if>
														            </tr>
														            <%-- this param is used ONLY in this JSP --%>
															        <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" /> 
															        <%-- this param is used throughout the Controller --%>
															        <c:set var="numberOfItemLinesInTopic" value="${record.svli}" scope="request" /> 
															        </c:forEach>
															        </tbody>
													        </table>
															</td>											           		
												         </tr>
											         	</table>
											   			
											   			<div>
								           				<table >
														<%-- OK BUTTON --%>
								           				<tr align="left" >
															<td class="text14"><button name="allItemsButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('allItems');">&nbsp;Ok</button> 
															</td>
															<td class="text14">&nbsp;&nbsp;&nbsp;
												 	        		<a href="tvinnsadExportItemListExcelView.do" target="_new">
											                 		<img valign="bottom" id="itemListExcel" src="resources/images/excel.png" border="0" alt="excel">&nbsp;Excel
												 	        		</a>
												 	        		&nbsp;
													 		</td>
														</tr>
														</table>
												    	</div>
											    </span> 
										    <c:if test="${model.status == 'M' || empty model.status}">		
												&nbsp;<button title="Kontrollera varelinjer" name="itemListControlButton" id="itemListControlButton" class="buttonGrayWithGreenFrame" type="button" >Varelinje kontroll</button>
											</c:if>
											          		
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<table class="tableBorderWithRoundCornersGray" >
												<tr>
													<td class="text12" nowrap>
														<input tabindex=-1 class="inputFormSubmitGrayFont12" type="submit" name="submitStartItem" onclick="javascript: form.action='tvinnsadexport_edit_items.do';" value="Hent">
														fra linjenr:
														<input tabindex=-1 align="left" type="text" class="inputText" size="4" maxlength="5" name="startItemLineNr" id="startItemLineNr" value='${model.recordItemContainerTopic.startItemLineNr}'>
														fra varenr:
														<input tabindex=-1 align="left" type="text" class="inputText" size="10" maxlength="8" name="tariffNr" id="tariffNr" value='${model.recordItemContainerTopic.tariffNr}'>
													</td>
										        </tr>
											</table>
						            		</td>
									</tr>
									<tr>
										<td class="text14Bold">&nbsp;Antall varelinjer&nbsp;&nbsp;<font class="text14MediumBlue"><b>${totalNumberOfItemLines}</b></font>
						            		
										</td>
										<td width="10%" class="text14">&nbsp;</td>
										<td align="right" class="text14"><span title="headerRecord.sevkb">Bruttovekt:&nbsp;</span>
											<input tabindex=-1 align="right" type="text" readonly class="inputText14BlueBoldReadOnly" size="12" maxlength=11" value='${recordTopicTvinnSad.sevkb}'>
										</td>
										<td align="right" class="text14"><span title="headerRecord.sebel1">Fsum:&nbsp;</span>
											<input tabindex=-1 align="right" type="text" readonly class="inputText14BlueBoldReadOnly" size="12" maxlength=20" value='${recordTopicTvinnSad.sebel1}'>
											&nbsp;<font style="color:#000080; font-style: italic;"><b>${recordTopicTvinnSad.seval1}</b></font>
										</td>
										<td align="right" class="text14"><span title="headerRecord.calculatedItemLinesTotalAmount">Vsum&nbsp;(&Sigma;):&nbsp;</span>
											<input tabindex=-1 align="right" type="text" readonly class="inputText14BlueBoldReadOnly" size="12" maxlength=20" value='${model.recordItemContainerTopic.calculatedItemLinesTotalAmount}'>
										</td>
										<td align="right" class="text14"><span title="headerRecord.diffItemLinesTotalAmountWithInvoiceTotalAmount">Diff:&nbsp;</span>
											<input tabindex=-1 align="right" type="text" readonly
												<c:choose>
												<c:when test="${fn:contains(model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount,'-')}">
													class="inputText14RedBoldReadOnly" 
												</c:when>
												<c:otherwise>
													class="inputText14BlueBoldReadOnly"
												</c:otherwise>
												</c:choose>
												size="12" maxlength=20" value='${model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount}'>
										</td>
									</tr>
									<tr height="2"><td></td></tr>
								</table>
							</form>
							</td>
						</tr> 
						
						<tr>
							<td >
								<form name="formItemList" id="formItemList" method="POST" >
			               		<input type="hidden" name="opdItemList" id="opdItemList" value="${model.opd}">
		 						<input type="hidden" name="avdItemList" id="avdItemList" value="${model.avd}"> 
		 						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
		 						<input type="hidden" name="selkbCountryCode" id="selkbCountryCode" value="${recordTopicTvinnSad.selkb}">
				 					
								<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
								<tr>
								<td class="text14">
							
								<table id="tblItemLines" class="display compact cell-border" >
									<thead>
									<tr class="tableHeaderField">
									    <th class="text14">&nbsp;Lin.&nbsp;</th> 
									    <th class="text14">&nbsp;Endre&nbsp;</th> 
									    <th class="text14" nowrap>&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.svfyl.fk"/>&nbsp;</th>
					                    <th class="text14" nowrap>&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.svvnt.vareNr"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.svvktn.nettov"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.svntm.mengde"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.svpre.preference"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.svbelt.varansPris"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.svavt.ff"/></th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.svavtp.tollsats"/></th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.wd1.vareDescription"/>&nbsp;</th>
					                    <th align="center" class="text14">&nbsp;<spring:message code="systema.tvinn.sad.export.item.list.label.sverr.error"/>&nbsp;</th>
					                    <c:if test="${model.status == 'M' || empty model.status}">
					                    	<th align="center" class="text14" nowrap>Slett</th>
					                    </c:if> 
					               </tr> 
					               </thead>
					               <tbody>	
				 					  <c:forEach items="${model.list}" var="record" varStatus="counter">    
							               <c:choose>           
							                   <c:when test="${counter.count%2==0}">
							                       <tr id="parentItemListTableRowNr_${counter.count}" class="tableRow" height="20" >
							                   </c:when>
							                   <c:otherwise> 
							                       <tr id="parentItemListTableRowNr_${counter.count}" class="tableOddRow" height="20" >
							                   </c:otherwise>
							               </c:choose>
							               <td width="4%" class="text14" align="center">${record.svli}</td>
							               <td width="4%" class="text14" align="center">&nbsp;
							               		<a tabindex=-1 title="${counter.count}" id="recordUpdate_${record.svli}" href="#" onClick="getItemData(this);">
							               			<img src="resources/images/update.gif" border="0" alt="edit">&nbsp;
							               		</a>
							               </td>
							               <td class="text14" >&nbsp;${record.svfyl}</td>
							               <td class="text14" >&nbsp;${record.svvnt}&nbsp;&nbsp;
							               	  <img id="recordUpdate_${record.svli}" onClick="updateKundensVarReg(this);" src="resources/images/addOrder.png" width="12px" height="12px" border="0" title="Tilføy Kundens varereg.">
							               </td>
							               <td class="text14" >&nbsp;${record.svvktn}</td>
							               <td class="text14" >&nbsp;${record.svntm}</td>
							               <td class="text14" >&nbsp;${record.svpre}</td>
							               <td class="text14" >&nbsp;${record.svbelt}</td>
							               <td class="text14" >&nbsp;${record.svavt}</td>
							               <td class="text14" >&nbsp;${record.svavtp}</td>
							               <td class="text14">&nbsp;${record.wd1}</td>
							               <td align="center" class="text14">&nbsp;
							               		<c:if test="${not empty record.sverr}">
							               			<img valign="bottom" src="resources/images/redFlag.png" width="18px" height="18px" border="0" alt="remove">
							               		</c:if>
							               </td>
							               <c:if test="${model.status == 'M' || empty model.status}">	
								               <td class="text14" align="center" nowrap>
								               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadexport_edit_items.do?action=doDelete&avd=${record.svavd}&opd=${record.svtdn}&lin=${record.svli}&fabl=${recordTopicTvinnSad.sebel1}">
								               		<img src="resources/images/delete.gif" border="0" alt="remove">
								               	</a>	
								               </td>
							               </c:if>
							               
							            </tr>
								        <%-- <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" />  --%>
								        <c:set var="numberOfItemLinesInTopic" value="${record.svli}" scope="request" /> 
								        </c:forEach>
								    </tbody>    
						        </table>
								</td>
								</tr>
								</table>
						</form>
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
	 				<form name="tvinnSadExportEditTopicItemForm" id="tvinnSadExportEditTopicItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
				 	<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
				 	<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
				 	<input type="hidden" name="refnr" id="refnr" value="${dkih_07}"/>
				 	<input type="hidden" name="status" id="status" value="${model.status}"/>
				 	<input type="hidden" name="datum" id="datum" value="${model.datum}"/>
				 	<input type="hidden" name="fabl" id="fabl" value="${recordTopicTvinnSad.sebel1}"/>
				 	
				 	<input type="hidden" name="svli" id="svli" value=''/>
				 	<input type="hidden" name="dkiv_32" id="dkiv_32" value=''/>
				 	
				 	<input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" />
				 	<input type="hidden" name="lastSelectedItemLineNumber" id="lastSelectedItemLineNumber" value="${model.recordItemContainerTopic.lastSelectedItemLineNumber}" />
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="100%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14White" align="left" >
				 				<b>&nbsp;&nbsp;V<label onClick="showPop('debugPrintlnAjaxItemFetchAdmin');" >a</label>relinje&nbsp;</b>
				 				
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
		           		   			<div class="text14" align="left" style="display:block;width:700px;word-break:break-all;">
		           		   				${activeUrlRPGUpdate_TvinnSad}<br/><br/>
		           		   				<button name="updateInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('updateInfo');">Close</button> 
		           		   			</div>
						        </span>  
				 				&nbsp;&nbsp;&nbsp;
				 				<c:choose>
					 				<c:when test="${not empty model.record.svln}">
						 				<input title="from model" tabindex=-1 style="text-align:center;" class="text14BoldLightGreenForItemLinenr" readonly type="text" name="lineSvln" id="lineSvln" size="4" value="${model.record.svln}">
									</c:when>
									<c:otherwise>
				 						<input title="from session" tabindex=-1 style="text-align:center;" class="text14BoldLightGreenForItemLinenr" readonly type="text" name="lineSvln" id="lineSvln" size="4" value="${svln_SESSION}">
									</c:otherwise>
								</c:choose>
				 				<c:choose>
					 				<c:when test="${not empty model.record.svli}">								
										<input title="from model" tabindex=-1 style="text-align:center;" class="text14BoldRedForItemLinenr" readonly type="text" name="lineSvli" id="lineSvli" size="4" value="${model.record.svli}">
									</c:when>
									<c:otherwise>
				 						<input title="from session" tabindex=-1 style="text-align:center;" class="text14BoldRedForItemLinenr" readonly type="text" name="lineSvli" id="lineSvli" size="4" value="${svli_SESSION}">
									</c:otherwise>
								</c:choose>
			 				</td>
			 				<td class="text14White" align="right">Forhold B/N-vekt:&nbsp;
			 					<c:choose>
					 				<c:when test="${not empty recordTopicTvinnSad.sefvk}">
						 				<input style="text-align:center;" tabindex=-1 class="text14BoldLightGreenForItemLinenr" readonly type="text" name="grossNetFactor" id="grossNetFactor" size="5" value="${recordTopicTvinnSad.sefvk}">
						 			</c:when>
						 			<c:otherwise>
										<input style="text-align:center;" tabindex=-1 class="text14BoldLightGreenForItemLinenr" readonly type="text" name="grossNetFactor" id="grossNetFactor" size="5" value="0,90">														 			
						 			</c:otherwise>
					 			</c:choose>	
						 	</td>
		 				</tr>
	 				</table>
					<table width="100%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
							 			<td>&nbsp;</td>
							 			<td class="text14" align="left">
							 			<img onMouseOver="showPop('fk_info');" onMouseOut="hidePop('fk_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<font class="text16RedBold" >*</font>
							            <span title="svfyl">FK/LK</span>
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="fk_info" class="popupWithInputText text11">
						           			<b>FK Fylkeskode / Oppr.Land</b>
						           			<br/><br/>
						           			<p>
											Varens opprinnelses-/produksjonsfylke Med opprinnelsesfylke forstås det fylke der verdiskapningen er størst. 
											Eksempelvis vil pakking og innfrysing av oppdrettslaks ikke endre eksportverdien vesentlig, slik at produksjonsfylke blir oppdrettsfylket.
											</p>
											<p>
											Hvis importerte (utenlandske) varer bearbeides og eksportverdien blir minst dobbelt så høy som importverdien, oppgis fylke; i motsatt fall oppgis kode 91, utenlandsk vareopprinnelse.						           			
											</p>
											<p>
											Ved prosedure (Eksped.Type): 2,4,18,81,90 må gyldig oppr.land gis, (Ikke fylke).
											</p>
										</span>
										</div>
										
										<c:choose>
										<c:when test="${recordTopicTvinnSad.sedp=='02' || recordTopicTvinnSad.sedp=='04' || recordTopicTvinnSad.sedp=='18' 
														|| recordTopicTvinnSad.sedp=='81' || recordTopicTvinnSad.sedp=='90'}">
											<%-- Oppr.Land 
											<img onClick="showPop('landCodeFylkeInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											<span style="position:absolute; left:100px; top:450px; width:350px; height:150px;" id="landCodeFylkeInfo" class="popupWithInputText"  >
								           		<div class="text10" align="left">
							           				<select class="text11" id="opprlandFylke" name="opprlandFylke" size="5" onDblClick="hidePop('landCodeFylkeInfo');">
								           				<c:forEach var="country" items="${model.countryCodeList}" >
								 				  			<option value="${country.zkod}">${country.zkod}&nbsp;${country.ztxt}</option>
														</c:forEach>
								           			</select>
								           			<table width="100%" align="left" border="0">
														<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
														<tr align="left" >
															<td class="text11">&nbsp;<button name="opprlandFylkeButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('landCodeFylkeInfo');">&nbsp;<spring:message code="systema.tvinn.sad.export.ok"/></button> 
															</td>
														</tr>
													</table>
												</div>
											</span>
											--%>
										</c:when>
										<c:otherwise>
											<%-- Fylkeskode 
											<img onClick="showPop('fylkeInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											<span style="position:absolute; left:100px; top:450px; width:350px; height:200px;" id="fylkeInfo" class="popupWithInputText"  >
								           		<div class="text10" align="left">
							           				<select class="text11" id="fylke" name="fylke" size="5" onDblClick="hidePop('fylkeInfo');">
								           				<c:forEach var="code" items="${model.fylkesCodeList}" >
							 				  				<option value="${code.zkod}" >${code.zkod}&nbsp;${code.ztxt}</option>
														</c:forEach>
								           			</select>
								           			<table width="100%" align="left" border="0">
														<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
														<tr align="left" >
															<td class="text11">&nbsp;<button name="fylkeButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('fylkeInfo');">&nbsp;<spring:message code="systema.tvinn.sad.export.ok"/></button> 
															</td>
														</tr>
													</table>
												</div>
											</span>	
											--%>
										</c:otherwise>
										</c:choose>
										</td>
							            
							 			<td class="text14" align="left">
							            <img onMouseOver="showPop('varenr_info');" onMouseOut="hidePop('varenr_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							            <font class="text16RedBold" >*</font><span title="svvnt">Varenr.</span>
							            <div class="text11" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="varenr_info" class="popupWithInputText text11"  >
						           			<b>Varenr.</b>
						           			<br/><br/>
											Tast varens 8 siffrede trariffnummer. Gyldigheten testes mot toll-tariffen.
										</span>
										</div>
				 						<%--
							            <img id="imgTaricVarukodSearch" style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" onClick="showPop('searchTaricCodesDialog');">
							            <%-- ======================================================== --%>
						            		<%-- Here we have the search Taric codes popup window --%>
						            		<%-- ======================================================== 
						            		<span style="position:absolute; left:300px; top:450px; width:500px; height:210px;" id="searchTaricCodesDialog" class="popupWithInputText"  >
							           		<div class="text10" align="left">
							           			<table>
								           			<tr>
								           			<td>
									           			<table>
									           				<tr>
																<td class="text11" style="vertical-align:bottom;" >&nbsp;Varenr.</td>
																<td class="text11">&nbsp;<input type="text" class="inputText" name="search_svvnt" id="search_svvnt" size="10" maxlength="10" value="">
																	<a tabindex="-1" class="text14" target="_blank" href="${model.tolltariffenURL.value}" onclick="${model.tolltariffenURL.windowOpenDimensions}" >
					            											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tolltariffen 
					            											&nbsp;<img width="25px" height="35px" style="cursor:pointer;" src="resources/images/logoTollNorway.jpg" border="0" alt="search">
					            										</a>
																</td>
															</tr>
										           			<tr>
										           				<td align="right">&nbsp;<button name="searchTaricCode" id="searchTaricCode" class="buttonGray" type="button" onClick="searchTaricVarukod();"><spring:message code="systema.tvinn.sad.export.search"/></button></td>
										           				<td class="text11">&nbsp;</td>
											           			
											           		</tr>
											           		<tr height="4"><td ></td></tr>
										           		</table>
									           		</td>
									           		</tr>
													
													<tr>
								           			<td>
									           			<table>							           		
											           		<tr>
										           				<td class="text11">&nbsp;&nbsp;Liste</td>
											           			<td>&nbsp;</td>
											           		</tr>
											           		<tr>
																<td colspan="2">
																	<select class="text11" id="taricVarukodList" name="taricVarukodList" size="5" onDblClick="hidePop('searchTaricCodesDialog');">
					 													<option selected value="">-velg-</option>
					 							 					</select>
																</td>
															</tr>
									           			</table>
								           			</td>
								           			</tr>
														
													<tr>
								           			<td>								           			
														<table width="100%" align="left" border="0">
															<tr align="left" >
																<td >&nbsp;<button name="searchTaricCodesClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchTaricCodesDialog');"><spring:message code="systema.tvinn.sad.export.ok"/></button></td>
															</tr>
														</table>
													</td>
													</tr>
												</table>
											</div>
										</span>	
										 --%>
							            </td>
							            <td class="text14" align="left">
							            <img onMouseOver="showPop('svpre_info');" onMouseOut="hidePop('svpre_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info" >
				 						<b>36.</b><span title="svpre">Preferense</span>
				 						<div class="text11" style="position: relative;" align="left">
				 						<span style="position:absolute;top:2px; width:250px;"id="svpre_info" class="popupWithInputText text11"  >
						           			<b>36. Preferense</b>
						           			<br/>
						           			Hvis det foreligger tilfredsstillende opprinnelsesdokumentasjon ved utførsel, skal rubrikken fylles ut med en av følgende koder:
						           			 	<ul>
													<li><b>A</b> EØS-land, dvs. alle EU-land samt Island og Liechtenstein</li>
													<li><b>B</b> Bilaterale handelsavtalen EF/Norge</li>
													<li><b>C</b> EFTA konvensjonen, Sveits</li>
													<li><b>G</b> Det norske GSP systemet</li>
													<li><b>P</b> alle andre frihandelsavtaler; som for eksempel Marokko, Chile mm</li>
													
												</ul>
											<br>	
											<p>
											Hvis det ikke foreligger tilfredsstillende opprinnelsesdokumentasjon ved utførsel, skal rubrikken fylles ut med følgende kode:
												<ul>
													<li><b>N</b> Det foreligger ikke et opprinnelsesbevis</li>
												</ul>	
												N: Det foreligger ikke et opprinnelsesbevis
												Koden som skal benyttes knytter seg til gjeldende handelsavtale/preferanseordning man eksporterer under. Bokstaven J skal ikke benyttes ved utførsel. Dersom opprinnelsesbevis blir utstedt på et senere tidspunkt, skal det sendes inn en elektronisk omberegning med de nevnte opplysningene i rubrikk 36 og 44.
											</p>
											<p>
											<b>Rubrikk 44</b><br/>
											Rubrikken skal fylles ut dersom det foreligger et opprinnelsesbevis ved utførsel.
											<br/>
											Dersom opprinnelsesbeviset er et opprinnelsessertifikat skal det anføres «SER» og sertifikatets nummer.
											<br/>
											Dersom opprinnelsesbeviset er en faktura/opprinnelseserklæring skal det anføres «SER» og autorisasjonsnummeret eller; dersom eksportøren ikke er godkjent eksportør anføres «SER» og opprinnelseserklæring.
											<br/>
											Det er regelverket på deklarasjonstidspunktet som gjelder. Når regelverket trer i kraft 1. mai så gjelder det nye kravet kun deklarasjoner med deklarasjonsdato etter 1. mai.
											<br/>
											Omberegninger når opprinnelig deklarasjon har deklarasjonsdato før 1. mai – ingen krav til preferanse ved omberegning.
											FO-deklarasjon før første 1. mai, etterfulgt av EN-deklarasjon etter 1. mai - ingen krav til preferanse ved utførsel
											Til bakedatert MA med deklarasjonsdato før 1. mai – ingen krav til preferanse ved utførsel.
											</p>
										</span>
				 						</div>
				 						</td>
							            
							            
							            
							            <td class="text14" align="left">
							            <img onMouseOver="showPop('svbelt_info');" onMouseOut="hidePop('svbelt_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<span title="svbelt">Tollverdi</span>
							            	<div class="text11" style="position: relative;" align="left">
							            	<span style="position:absolute;top:2px; width:250px;" id="svbelt_info" class="popupWithInputText text11"  >
								           			<br/>
													<b>Tollverdi</b>
								           			<p>
													Tast varelinjens beløp i valuta.
													</p>
											</span>
											</div>
					            		</td>
						            		
							            <td class="text14" align="left">
							            <img onMouseOver="showPop('svvktb_info');" onMouseOut="hidePop('svvktb_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<font class="text16RedBold" >*</font><span title="svvktb">Bruttovekt(kg)</span>
							            <div class="text11" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="svvktb_info" class="popupWithInputText text11"  >
							           			<br/><br/>
												<b>Brutto/Nettovekt (kg)</b>
							           			<p>
												Tast varelinjens brutto og nettovekt. Taster man bare den ene av vektbegrepene så finner systemet selv den andre. (Legger til/trekker fra 10%) F-4 i feltet starter en kalkulator. Når alle beløpene er lagt sammen, avslutt kalkulatorer med F-3, og summen legges i feltet.												
												</p>
												<p>
												Dersom man taster brutto eller nettovekt så finner systemet automatisk den andre vekten. 
												(<b>10%</b> opp/ned eller angitt på hovedsiden)
												</p>
										</span>
										</div>
										</td>
										<td class="text14" align="left">
										<img onMouseOver="showPop('nettovekt_info');" onMouseOut="hidePop('nettovekt_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<font class="text16RedBold" >*</font><span title="svvktn">Nettovekt(kg)</span>
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;"id="nettovekt_info" class="popupWithInputText text11"  >
						           			<br/>
											<b>Brutto/Nettovekt (kg)</b>
						           			<p>
											Tast varelinjens brutto og nettovekt. Taster man bare den ene av vektbegrepene så finner systemet selv den andre. (Legger til/trekker fra 10%) F-4 i feltet starter en kalkulator. Når alle beløpene er lagt sammen, avslutt kalkulatorer med F-3, og summen legges i feltet.												
											</p>
											<p>
											Dersom man taster brutto eller nettovekt så finner systemet automatisk den andre vekten. 
											(<b>10%</b> opp/ned eller angitt på hovedsiden)
											</p>
										</span>
										</div>
										</td>

										<td class="text14" >
										<img onMouseOver="showPop('mengde_info');" onMouseOut="hidePop('mengde_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										<span title="svntm">Mengde</span>
				 						<div class="text11" style="position: relative;" align="left">
				 						<span style="position:absolute;top:2px; width:250px;" id="mengde_info" class="popupWithInputText text11"  >
							           		<b>Mengde</b>
						           			<br/><br/>
						           			Her tastes mengde i annen enhet dersom tolltariffen krever det.
						           			Systemet sjekker automatisk i tariffen og du får beskjed om å registrere antall eller annen enhet dersom du glemmer det.
											Følgende koder gjelder ved fortolling via Tvinn:
											<ul>
												<li><b>STK</b> = NMB Antall</li>
												<li><b>PAR</b> = NPP Par</li>
												<li><b>LIT</b> = LTR Liter</li>
												<li><b>M3</b> = MTO Kubikkmeter</li>
												<li><b>M2</b> = MTK Kvadratmeter</li>
												<li><b>GRA</b> = GRM Gram</li>
											</ul>						           			
											Disse kodene skal ligge definert i Tolltariffen og bruker behøver normalt ikke forholde seg til dette kodesettet.
											<p> 
											Skulle derimot felt for antall 'STK' i Tolltariffen ikke være utfyllt med kode J og Tollvesenet / Tvinn skulle kreve antall oppgitt må man gjøre følgende:
											Felt STK fylles ut med kode J og felt ENHET fylles ut med en av kodene som er vist over.
											<p>
											Systemet vil konvertere etter overstående tabell. Hvis andre enhetskoder benyttes vil systemet sende det som er blitt tastet med de muligheter for feilmelding fra Tvinn som dette medfører
										</span>
										</div>
										</td>
										<td class="text14" >
										<img onMouseOver="showPop('ff_info');" onMouseOut="hidePop('ff_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<span title="svavt">FF</span>
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="ff_info" class="popupWithInputText text11"  >
						           			<br/>
						           			<b>FF - FISKEAVGIFT</b>
											<p>
											Hvis det er lagt inn FF-avgift på tolltariffnr. (meny SADEX pkt. 8) setter systemet automatisk en 'X' i feltet. 
											</p>
											<p>
											Hvis det finnes flere sekvensnr., og det ikke er oppgitt om det gjelder EU eller 3.land får du opp valgbilde for å plukke gyldig sats.
											</p>
										</span>
										</div>
										</td>
																		
							        </tr>
							        <tr>
							        	<td align="center" >&nbsp;<button title="Kundens vareregister" name="kundensVaruregisterControlButton" id="kundensVaruregisterControlButton" class="buttonGrayWithGreenFrame" type="button" >Søk i kund.varereg.</button></td>
						        		<td align="left" nowrap>
							            	<c:choose>
											<c:when test="${recordTopicTvinnSad.sedp=='02' || recordTopicTvinnSad.sedp=='04' || recordTopicTvinnSad.sedp=='18' 
														|| recordTopicTvinnSad.sedp=='81' || recordTopicTvinnSad.sedp=='90'}">
												<select class="inputTextMediumBlueMandatoryField" name="svfyl" id="svfyl">
							 					<option value="">-velg-</option>
												<c:forEach var="country" items="${model.countryCodeList}" >
						 				  			<option value="${country.zkod}"<c:if test="${model.record.svfyl == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach>
												</select>
												<a tabindex="-1" id="svfylIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>		
											</c:when>
											<c:otherwise>
												<select class="inputTextMediumBlueMandatoryField" name="svfyl" id="svfyl">
							 					<option value="">-velg-</option>
												<c:forEach var="code" items="${model.fylkesCodeList}" >
													<c:choose>
							           					<c:when test="${not empty model.recordItemContainerTopic.w2fyl && empty model.record.svfyl}">
								 				  			<option title="${code.ztxt}" value="${code.zkod}" <c:if test="${model.recordItemContainerTopic.w2fyl == code.zkod}"> selected </c:if> >${code.zkod}&nbsp;${fn:substring(code.ztxt, 0, 6)}</option>
							 				  			</c:when>
							 				  			<c:otherwise>
							 				  				<option title="${code.ztxt}" value="${code.zkod}"<c:if test="${model.record.svfyl == code.zkod}"> selected </c:if> >${code.zkod}&nbsp;${fn:substring(code.ztxt, 0, 6)}</option>
							 				  			</c:otherwise>
													</c:choose>
												</c:forEach> 
												</select>
												<%--
												<a tabindex="-1" id="svfyl2IdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
												 --%>	
											</c:otherwise>
											</c:choose>
											
												
										</td>
						        			<td class="text14" align="left">
						            		<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField"  name="svvnt" id="svvnt" size="9" maxlength="8" value="${model.record.svvnt}">
						            		<a tabindex="-1" id="svvntIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
							            </td>
							            <td class="text14" align="left" nowrap>
											<select class="selectMediumBlueE2" name="svpre" id="svpre">
						        		    			<option value="">-velg-</option>
			  								  	<option title="EØS-land" value="A"<c:if test="${ model.record.svpre == 'A'}"> selected </c:if> >A</option>
											  	<option title="Bilaterale handelsavtalen" value="B"<c:if test="${ model.record.svpre == 'B'}"> selected </c:if> >B</option>
											  	<option title="EFTA konvensjonen" value="C"<c:if test="${ model.record.svpre == 'C'}"> selected </c:if> >C</option>
											  	<option title="Det norske GSP systemet" value="G"<c:if test="${ model.record.svpre == 'G'}"> selected </c:if> >G</option>
											  	<option title="Andre frihandelsavtaler" value="P"<c:if test="${ model.record.svpre == 'P'}"> selected </c:if> >P</option>
											  	<option title="Nej" value="N"<c:if test="${ model.record.svpre == 'N'}"> selected </c:if> >N</option>		
											  	
											</select>
										</td>
							            
										<td class="text14" align="left" nowrap>
											<c:choose>
												<%-- only when it is a new line  --%>
					           					<c:when test="${not empty model.recordItemContainerTopic.w2fyl && empty model.record.svfyl}">
						 				  			<c:choose>
														<c:when test="${fn:contains(model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount,'-')}">
															<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="svbelt" id="svbelt" size="12" maxlength="14" value="${fn:replace(model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount,'-','')}">
														</c:when>
														<c:otherwise>
															<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="svbelt" id="svbelt" size="12" maxlength="14" value="${model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount}">
														</c:otherwise>
						 				  			</c:choose>
						 				  		</c:when>		
					 				  			<c:otherwise>
					 				  				<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="svbelt" id="svbelt" size="12" maxlength="14" value="${model.record.svbelt}">
					 				  			</c:otherwise>
											</c:choose>
						            	</td>
						 				<td nowrap class="text14" align="left"><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="svvktb" id="svvktb" size="10" maxlength="12" value="${model.record.svvktb}"></td>
										<td nowrap class="text14" align="left"><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="svvktn" id="svvktn" size="10" maxlength="12" value="${model.record.svvktn}"></td>
										<td nowrap class="text11" valign="bottom">
											&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="svntm" id="svntm" size="10" maxlength="9" value="${model.record.svntm}">
										</td>
										<td class="text14" align="left" ><input type="text" class="inputText" name="svavt" id="svavt" size="2" maxlength="1" value="${model.record.svavt}"></td>
							        </tr>
									<tr height="10"><td class="text" align="left" colspan="12"><hr></td></tr>
									<tr>
										<td class="text14" align="center">
								            <img onMouseOver="showPop('tollsats_info');" onMouseOut="hidePop('tollsats_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 						<span title="svavtp">Sats&nbsp;</span>
								            <div class="text11" style="position: relative;" align="left">
								            <span style="position:absolute;top:2px; width:250px;" id="tollsats_info" class="popupWithInputText text11"  >
							           			<br/>
							           			<b>Sats - Tollsats</b>
												<br/><br/>
							           			Systemet finner selv tollsatsen. Dersom satsen skal reduseres iht. kode i TN rubrikken så må ønsket sats tastes her.
											</span>
											</div>
										</td>		
										<td class="text14" align="left">
								            <img onMouseOver="showPop('svmfr_info');" onMouseOut="hidePop('svmfr_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 						<span title="svavts">Sekv.</span>
					 						<div class="text11" style="position: relative;" align="left">
								            <span style="position:absolute;top:2px; width:250px;" id="svmfr_info" class="popupWithInputText text11"  >
							           			<br/>
							           			<b>Sekv.</b>
												<p>...</p>
											</span>
											</div>
					 						<%--
								            <img onClick="showPop('sekvInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												<span style="position:absolute; left:800px; top:580px; width:300px; height:200px;" id="sekvInfo" class="popupWithInputText"  >
									           		<div class="text10" align="left">
								           				<select class="text11" id="sekv" name="sekv" size="8" onDblClick="hidePop('sekvInfo');">
									           				<c:forEach var="code" items="${model.avgiftsCodeListB}" >
									 				  			<option value="${code.zskv}">${code.zskv}&nbsp;${code.zkod}&nbsp;${code.ztxt}</option>
															</c:forEach>
									           			</select>
									           			<table width="100%" align="left" border="0">
															<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
															<tr align="left" >
																<td class="text11">&nbsp;<button name="sekvButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('sekvInfo');">&nbsp;<spring:message code="systema.tvinn.sad.export.ok"/></button> 
																</td>
															</tr>
														</table>
													</div>
												</span>	
											 --%>	
										</td>
										<td class="text14" align="left"><span title="svlk">Lk.Oppr.</span>
											<%-- info span 
											<img onClick="showPop('landCodeOpprInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											<span style="position:absolute; left:800px; top:580px; width:350px; height:150px;" id="landCodeOpprInfo" class="popupWithInputText"  >
								           		<div class="text10" align="left">
							           				<select class="text11" id="opprland" name="opprland" size="5" onDblClick="hidePop('landCodeOpprInfo');">
								           				<c:forEach var="country" items="${model.countryCodeList}" >
								 				  			<option value="${country.zkod}">${country.zkod}&nbsp;${country.ztxt}</option>
														</c:forEach>
								           			</select>
								           			<table width="100%" align="left" border="0">
														<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
														<tr align="left" >
															<td class="text11">&nbsp;<button name="opprlandButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('landCodeOpprInfo');">&nbsp;<spring:message code="systema.tvinn.sad.export.ok"/></button> 
															</td>
														</tr>
													</table>
												</div>
											</span>
											--%>	
										</td>
										<td class="text14" align="left">
											<img onMouseOver="showPop('vf_info');" onMouseOut="hidePop('vf_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
											<span title="svvf">VF.</span>
											<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="vf_info" class="popupWithInputText text11">
							           			<b>VF Kode</b>
							           			<ol>
							           				<li>Tollverdien fastsatt på grunnlag av transaksjonsverdien</li>
							           				<li>Identiske varer</li>
							           				<li>Liknende varer</li>
							           				<li>Vidaresalgsprisen</li>
							           				<li>Beregnet verdi</li>
							           				<li>Skjønnsmessig fastsettelse</li>
							           			</ol>
											</span>
											</div>
										</td>
										<td class="text14" align="left"><span title="svcnr">Containernr.</span></td>

									</tr>
									<tr>
									<td class="text14" align="center" >&nbsp;<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="svavtp" id="svavtp" size="8" maxlength="7" value="${model.record.svavtp}"></td>
				            			<td class="text14" align="left" >
											<select class="selectMediumBlueE2" name="svavts" id="svavts">
						        		    			<option value="">-velg-</option>
			  								  	<c:forEach var="code" items="${model.avgiftsCodeListB}" >
			                                	 		<option value="${code.zskv}"<c:if test="${model.record.svavts == code.zskv}"> selected </c:if> >${code.zskv}</option>
												</c:forEach>
											</select>
											<a tabindex="-1" id="svavtsIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
										</td> 
										<td >
						            		<select class="selectMediumBlueE2" name="svlk" id="svlk">
						 						<option value="">-velg-</option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<option value="${country.zkod}"<c:if test="${model.record.svlk == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach>  
											</select>
											<a tabindex="-1" id="svlkIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
										</td>
									<td class="text14" align="left" >
										<select class="selectMediumBlueE2" name="svvf" id="svvf">
											
											<c:choose>
												<c:when test="${ not empty model.record.svvf}">
											    	<option title="Tollverdien fastsatt på grunnlag av transaksjonsverdien" value="1"<c:if test="${ model.record.svvf == '1'}"> selected </c:if> >1</option>
												  	<option title="Identiske varer" value="2"<c:if test="${ model.record.svvf == '2'}"> selected </c:if> >2</option>
												  	<option title="Liknende varer" value="3"<c:if test="${ model.record.svvf == '3'}"> selected </c:if> >3</option>
												  	<option title="Vidaresalgsprisen" value="4"<c:if test="${ model.record.svvf == '4'}"> selected </c:if> >4</option>
												  	<option title="Beregnet verdi" value="5"<c:if test="${ model.record.svvf == '5'}"> selected </c:if> >5</option>
												  	<option title="Skjønnsmessig fastsettelse" value="6"<c:if test="${ model.record.svvf == '6'}"> selected </c:if> >6</option>		
												</c:when>
												<c:otherwise>
													<option title="Tollverdien fastsatt på grunnlag av transaksjonsverdien" value="1"<c:if test="${ recordTopicTvinnSad.sevf == '1'}"> selected </c:if> >1</option>
												  	<option title="Identiske varer" value="2"<c:if test="${ recordTopicTvinnSad.sevf == '2'}"> selected </c:if> >2</option>
												  	<option title="Liknende varer" value="3"<c:if test="${ recordTopicTvinnSad.sevf == '3'}"> selected </c:if> >3</option>
												  	<option title="Vidaresalgsprisen" value="4"<c:if test="${ recordTopicTvinnSad.sevf == '4'}"> selected </c:if> >4</option>
												  	<option title="Beregnet verdi" value="5"<c:if test="${ recordTopicTvinnSad.sevf == '5'}"> selected </c:if> >5</option>
												  	<option title="Skjønnsmessig fastsettelse" value="6"<c:if test="${ recordTopicTvinnSad.sevf == '6'}"> selected </c:if> >6</option>		
												</c:otherwise>
											</c:choose>
											
										</select>
											
									</td>	
									<td colspan="3" class="text14" align="left" >
										<input type="text" class="inputText" name="svcnr" id="svcnr" size="13" maxlength="11" value="${model.record.svcnr}">
										<button name="itemContainerNr" id="itemContainerNr" class="buttonGray" style="font-size:12" type="button" >Mere...</button>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 <c:choose>	
											<c:when test="${model.status == 'M' || empty model.status }">
												<input class="inputFormSubmit" type="submit" name="submit" id="submit" onclick="javascript: form.action='tvinnsadexport_edit_items.do';" value='<spring:message code="systema.tvinn.sad.export.item.createnew.submit"/>'>
												&nbsp;&nbsp;
												
												<%-- SEND button: is causing some issues in the Stat.värde calculation
													 We shall have the send button ONLY at one place (so far) and that is at the header level
							 				    	<c:if test="${not empty totalNumberOfItemLines && '0' != totalNumberOfItemLines}">
							 				    		<input tabindex=-1 class="inputFormSubmit" type="submit" name="send" id="send" onclick="javascript: form.action='skatimport_send.do';" value='<spring:message code="systema.skat.import.createnew.send"/>'/>
							 				    	</c:if>
							 				  	 --%>
											</c:when>
											<c:otherwise>
						 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='<spring:message code="systema.tvinn.sad.submit.not.editable"/>'/>
						 				    	</c:otherwise>	
					 				    	</c:choose>	
									</td>
									
																	        	
							        </tr>									
								</table>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">	
									<tr height="10"><td class="text" align="left"></td></tr>
									<tr > 
								        <td align="left" style="vertical-align:top;">
							        		<table class="tableBorderWithRoundCornersGray" style="margin-left:3px;margin-right:auto;">
											<tr >
									        <td class="text14Bold" >&nbsp;
								        		<img onMouseOver="showPop('varebeskrivelse_info');" onMouseOut="hidePop('varebeskrivelse_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								        		Varebeskrivelse
										        <div class="text11" style="position: relative;" align="left">
									            <span style="position:absolute; top:2px; width:250px;" id="varebeskrivelse_info" class="popupWithInputText text11"  >
								           			<br/>
								           			<b>Varebeskrivelse</b>
								           			<p>
								           			Her tastes ønsket varebetegnelse.Dersom intet tastes hentes tekst fra tariff.
								           			</p>
								           			<ul>
														<li>Varetext</li>
														<li>Merke og nr</li>
														<li>Antall kolli</li>
														<li>Enhet</li>
													</ul>
												</span>
												</div>
									        </td>
									        </tr>
											<tr height="5"><td></td></tr>
											<tr>
												<td class="text14" align="left" >&nbsp;<span title="wd1-wd5(svvt)">Varetext</span></td>
												<td class="text14" align="left" >&nbsp;<span title="wa1-wa7(svft)">Merke og nr</span></td>
												<td class="text14" align="left" >&nbsp;<span title="wb1-wb7(svnt)">Antall kolli</span></td>
		               							<td class="text14" align="left" >&nbsp;<span title="wc1-wc7(sveh)">Enhet</span>
		               							<a tabindex="-1" id="svehWc1Wc7IdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
		               								<%--
		               								<img onClick="showPop('enhetVareInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													<span style="position:absolute; left:550px; top:600px; width:300px; height:180px;" id="enhetVareInfo" class="popupWithInputText"  >
										           		<div class="text10" align="left">
									           				<select class="text11" id="enhetVare" name="enhetVare" size="5" onDblClick="hidePop('enhetVareInfo');">
										           				<c:forEach var="code" items="${model.enhetsCodeList}" >
										 				  			<option value="${code.zkod}">${code.zkod}&nbsp;${code.ztxt}</option>
																</c:forEach>
										           			</select>
										           			<table width="100%" align="left" border="0">
																<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
																<tr align="left" >
																	<td class="text11">&nbsp;<button name="enhetVareButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('enhetVareInfo');">&nbsp;<spring:message code="systema.tvinn.sad.export.ok"/></button> 
																	</td>
																</tr>
															</table>
														</div>
													</span>	
													--%>
		               							</td>
		               						</tr>
											<tr>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wd1" id="wd1" size="31" maxlength="30" value="${model.record.wd1}">
												</td>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wa1" id="wa1" size="25" maxlength="28" value="${model.record.wa1}">
												</td>
												<td class="text14MediumBlue">
													<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="wb1" id="wb1" size="8" maxlength="6" value="${model.record.wb1}">
												</td>
												<td class="text14MediumBlue">
													<select class="selectMediumBlueE2"  name="wc1" id="wc1">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="code" items="${model.enhetsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wc1 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
											<tr>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wd2" id="wd2" size="31" maxlength="30" value="${model.record.wd2}">
												</td>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wa2" id="wa2" size="25" maxlength="28" value="${model.record.wa2}">
												</td>
												<td class="text14MediumBlue">
													<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="wb2" id="wb2" size="8" maxlength="6" value="${model.record.wb2}">
												</td>
												<td class="text14MediumBlue">
													<select class="selectMediumBlueE2"  name="wc2" id="wc2">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="code" items="${model.enhetsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wc2 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
											<tr>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wd3" id="wd3" size="31" maxlength="30" value="${model.record.wd3}">
												</td>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wa3" id="wa3" size="25" maxlength="28" value="${model.record.wa3}">
												</td>
												<td class="text14MediumBlue">
													<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="wb3" id="wb3" size="8" maxlength="6" value="${model.record.wb3}">
												</td>
												<td class="text14MediumBlue">
													<select class="selectMediumBlueE2"  name="wc3" id="wc3">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="code" items="${model.enhetsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wc3 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
											<tr>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wd4" id="wd4" size="31" maxlength="30" value="${model.record.wd4}">
												</td>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wa4" id="wa4" size="25" maxlength="28" value="${model.record.wa4}">
												</td>
												<td class="text14MediumBlue">
													<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="wb4" id="wb4" size="8" maxlength="6" value="${model.record.wb4}">
												</td>
												<td class="text14MediumBlue">
													<select class="selectMediumBlueE2"  name="wc4" id="wc4">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="code" items="${model.enhetsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wc4 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
											<tr>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wd5" id="wd5" size="31" maxlength="30" value="${model.record.wd5}">
												</td>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wa5" id="wa5" size="25" maxlength="28" value="${model.record.wa5}">
												</td>
												<td class="text14MediumBlue">
													<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="wb5" id="wb5" size="8" maxlength="6" value="${model.record.wb5}">
												</td>
												<td class="text14MediumBlue">
													<select class="selectMediumBlueE2"  name="wc5" id="wc5">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="code" items="${model.enhetsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wc5 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
											<tr>
												<td class="text14MediumBlue">
													&nbsp;
												</td>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wa6" id="wa6" size="20" maxlength="28" value="${model.record.wa6}">
												</td>
												<td class="text14MediumBlue">
													<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="wb6" id="wb6" size="6" maxlength="6" value="${model.record.wb6}">
												</td>
												<td class="text14MediumBlue">
													<select class="selectMediumBlueE2"  name="wc6" id="wc6">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="code" items="${model.enhetsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wc6 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
											<tr>
												<td class="text14MediumBlue">
													&nbsp;
												</td>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wa7" id="wa7" size="20" maxlength="28" value="${model.record.wa7}">
												</td>
												<td class="text14MediumBlue">
													<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="wb7" id="wb7" size="6" maxlength="6" value="${model.record.wb7}">
												</td>
												<td class="text14MediumBlue">
													<select class="selectMediumBlueE2"  name="wc7" id="wc7">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="code" items="${model.enhetsCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.wc7 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
											<tr>
												<td class="text14MediumBlue">
													&nbsp;
												</td>
												<td class="text14MediumBlue">
													&nbsp;
												</td>
												<td class="text14MediumBlue">
													&nbsp;
												</td>
												<td class="text14MediumBlue">
													&nbsp;
												</td>
											</tr>
											<tr height="5"><td class="text" align="left"></td></tr>	
						        			</table>
						        			</td>
						        			<td align="left" style="vertical-align: top;">
											<table class="tableBorderWithRoundCornersGray">
									        <tr >
										        <td class="text14Bold">
									        		&nbsp;<img onMouseOver="showPop('to_info');" onMouseOut="hidePop('to_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									        		<b>44.a</b>&nbsp;Tilleggsopplysn<label onClick="showPop('debugPrintlnAjaxAdmin');" >i</label>nger
													<div class="text11" style="position: relative;" align="left">										       
									         		<span style="position:absolute;top:2px; width:250px;" id="to_info" class="popupWithInputText text11"  >
								           			<br/>
								           			<b>Tillegsopplysningar</b>
													<br/><br/>
													Her tastes lisensnummer,eurnr, "Fakturaerklæring" etc. Opplysningene kvalifiseres med kode i felt 'Rf'.
													<br/>
													Dersom en sender på TVINN eller benytter dataliste (i stedet for tillegsark), er max feltlengde 17. 
													<p>
													<b>TOLL:</b>
													<ul>
														<li>Når TOLLNEDSETTELSE har verdi <b>E,H,F</b> eller <b>R</b> (generelle tollnedsettelsesskriv), forlanges IKKE T.O.</li>
														<li>Når TOLLNEDSETTELSE har verdi <b>S</b> (BREV fra TAD om tollneds./fritak), forlanges T.O.</li>
														<li>Ved TVINN - UND(registrert i TVINN) eller PRD(ikke reg.) i Rf-feltet.</li>
														<li>Når PREFERANSE er oppgitt skal Tillegsoppl. fylles ut m.h.t. opprinnnelsesbevis. "EUR...", "Fakturaerklæring.",...</li>             
														<li>Ved TVINN settes da "SER" i Rf-feltet.</li>
														<li>Når PREFERANSE har verdi <b>J</b> eller <b>N</b> skal Tillegsoppl. <b>IKKE fylles ut</b> m.h.t. opprinnelsesbevis.</li>
													</ul>
													OBS! Kombinasjon av tollnedsettelse og preferanse kan IKKE FOREKOMME. Er TN-rubrikken utfylt skal det være "N" (blank) i preferanse.
													</p>
													<p>
													<b>SÆRAVGIFTER:</b>
													Når sats er redusert i forhold til normalen, skal skriv som gir grunnlag for reduksjonen oppgis. 
													I TVINN settes UND(reg.) /PRD(ureg.) i Rf-feltet.
													</p>
													<p>
													<b>UTSATT AVGIFTSBETALING:</b>
													Har vareeieren tillatelse til utsatt avgiftsinnbetaling, må det refereres til nummeret på dette skrivet. Nummeret skal begynne med bokstaven 'U'. I TVINN settes UTB i feltet Rf.
													</p>
													<p>
													<b>OPPHEVELSE AV RESTRIKSJONER OG/ELLER LISENSKRAV:</b>
													Ved opphevelse av restriksjoner/lisenkrav, må aktuelt skriv refereres til.
													I TVINN settes UND(reg.) / PRD(ureg.) i Rf.-feltet.
													</p>
													</span>
													</div>
													
													<span style="position:absolute; left:200px; top:500px; width:600px; height:180px;" id="debugPrintlnAjaxAdmin" class="popupWithInputText"  >
									           		<div class="text11" align="left">
									           			<label id="debugPrintlnAjaxInfo"></label>
									           			&nbsp;&nbsp;&nbsp;&nbsp;<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('debugPrintlnAjaxAdmin');">Close</button> 
									           		</div>
									        		</span>										        		
									        </tr>
											<tr height="5"><td></td></tr>
											<tr>
												<td class="text14" align="left" >&nbsp;<span title="wf1-wf5(svtop)">Tilleggsoppl.</span></td>
			               							<td class="text14" align="left" >&nbsp;<span title="we1-we5(svcref)">Ref.</span>
			               							<a tabindex="-1" id="svcrefWe1We5IdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
														<%--	
			               								<img onClick="showPop('docSertExportRefInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
														<span style="position:absolute; left:450px; top:650px; width:450px; height:180px;" id="docSertExportRefInfo" class="popupWithInputText"  >
											           		<div class="text10" align="left">
										           				<select class="text11" id="docSertExportRef" name="docSertExportRef" size="8" onDblClick="hidePop('docSertExportRefInfo');">
											           				<c:forEach var="code" items="${model.docSertExportCodeList}" >
											 				  			<option value="${code.zkod}">${code.zkod}&nbsp;${code.ztxt}</option>
																	</c:forEach>
											           			</select>
											           			<table width="100%" align="left" border="0">
																	<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
																	<tr align="left" >
																		<td class="text11">&nbsp;<button name="docSertExportRefInfoButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('docSertExportRefInfo');">&nbsp;<spring:message code="systema.tvinn.sad.export.ok"/></button> 
																		</td>
																	</tr>
																</table>
															</div>
														</span>
														--%>	
			               							</td>
			               						</tr>
												<tr>
												<td class="text14MediumBlue">
													<c:choose>
														<c:when test="${not empty model.recordItemContainerTopic.w2topl && empty model.record.wf1}" >
															<input type="text" class="inputText" name="wf1" id="wf1" size="20" maxlength="17" value="${model.recordItemContainerTopic.w2topl}">
														</c:when>
														<c:otherwise>
															<input type="text" class="inputText" name="wf1" id="wf1" size="20" maxlength="17" value="${model.record.wf1}">
														</c:otherwise>
													</c:choose>
												</td>
												<td class="text14MediumBlue">
													<select class="selectMediumBlueE2"  name="we1" id="we1">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertExportCodeList}" >
									 				  		<c:choose>
																<c:when test="${not empty model.recordItemContainerTopic.w2cref && empty model.record.we1}" >
																	<option value="${code.zkod}"<c:if test="${model.recordItemContainerTopic.w2cref == code.zkod}"> selected </c:if> >${code.zkod}</option>
																</c:when>
																<c:otherwise>
																	<option value="${code.zkod}"<c:if test="${model.record.we1 == code.zkod}"> selected </c:if> >${code.zkod}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach> 
													</select>
												</td>
											</tr>
												<tr>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wf2" id="wf2" size="20" maxlength="17" value="${model.record.wf2}">
												</td>
												<td class="text14MediumBlue">
													<select class="selectMediumBlueE2"  name="we2" id="we2">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertExportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we2 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
												<tr>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wf3" id="wf3" size="20" maxlength="17" value="${model.record.wf3}">
												</td>
												<td class="text14MediumBlue">
													<select class="selectMediumBlueE2"  name="we3" id="we3">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertExportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we3 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
												<tr>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wf4" id="wf4" size="20" maxlength="17" value="${model.record.wf4}">
												</td>
												<td class="text14MediumBlue">
													<select class="selectMediumBlueE2"  name="we4" id="we4">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertExportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we4 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
												<tr>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wf5" id="wf5" size="20" maxlength="17" value="${model.record.wf5}">
												</td>
												<td class="text14MediumBlue">
													<select class="selectMediumBlueE2"  name="we5" id="we5">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertExportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we5 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
												<tr>
												<td class="text14MediumBlue">
													&nbsp;
												</td>
												<td class="text14MediumBlue">
													&nbsp;
												</td>
											</tr>
											</table>
											</td>
											<td align="left" style="vertical-align: top;">
							    			<table class="tableBorderWithRoundCornersGray" >
									        <tr >
										        <td class="text14Bold">
										        		&nbsp;<img onMouseOver="showPop('tob_info');" onMouseOut="hidePop('tob_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										        		<b>44.b</b>&nbsp;Tilleggsopplysninger
										       
										        <div class="text11" style="position: relative;" align="left">
									         	<span style="position:absolute;top:2px; width:250px;" id="tob_info" class="popupWithInputText text11"  >
								           			<br/>
								           			<b>44.b Tilleggsopplysningar</b>
													<br/><br/>
													Her tastes lisensnummer,eurnr, "Fakturaerklæring" etc. Opplysningene kvalifiseres med kode i felt 'Rf'.
													<br/>
													Dersom en sender på TVINN eller benytter dataliste (i stedet for tillegsark), er max feltlengde 17. 
													<p>
													<b>TOLL:</b>
													<ul>
														<li>Når TOLLNEDSETTELSE har verdi <b>E,H,F</b> eller <b>R</b> (generelle tollnedsettelsesskriv), forlanges IKKE T.O.</li>
														<li>Når TOLLNEDSETTELSE har verdi <b>S</b> (BREV fra TAD om tollneds./fritak), forlanges T.O.</li>
														<li>Ved TVINN - UND(registrert i TVINN) eller PRD(ikke reg.) i Rf-feltet.</li>
														<li>Når PREFERANSE er oppgitt skal Tillegsoppl. fylles ut m.h.t. opprinnnelsesbevis. "EUR...", "Fakturaerklæring.",...</li>             
														<li>Ved TVINN settes da "SER" i Rf-feltet.</li>
														<li>Når PREFERANSE har verdi <b>J</b> eller <b>N</b> skal Tillegsoppl. <b>IKKE fylles ut</b> m.h.t. opprinnelsesbevis.</li>
													</ul>
													OBS! Kombinasjon av tollnedsettelse og preferanse kan IKKE FOREKOMME. Er TN-rubrikken utfylt skal det være "N" (blank) i preferanse.
													</p>
													<p>
													<b>SÆRAVGIFTER:</b>
													Når sats er redusert i forhold til normalen, skal skriv som gir grunnlag for reduksjonen oppgis. 
													I TVINN settes UND(reg.) /PRD(ureg.) i Rf-feltet.
													</p>
													<p>
													<b>UTSATT AVGIFTSBETALING:</b>
													Har vareeieren tillatelse til utsatt avgiftsinnbetaling, må det refereres til nummeret på dette skrivet. Nummeret skal begynne med bokstaven 'U'. I TVINN settes UTB i feltet Rf.
													</p>
													<p>
													<b>OPPHEVELSE AV RESTRIKSJONER OG/ELLER LISENSKRAV:</b>
													Ved opphevelse av restriksjoner/lisenkrav, må aktuelt skriv refereres til.
													I TVINN settes UND(reg.) / PRD(ureg.) i Rf.-feltet.
													</p>
												</span>
												</div>
												</td>
									        </tr>
											<tr height="5"><td></td></tr>
											<tr>
												<td class="text14" align="left" >&nbsp;<span title="wf6-wf10(svtop)">Tilleggsoppl.</span></td>
			               							<td class="text14" align="left" >&nbsp;<span title="we6-we10(svcref)">Ref.</span>
			               								<a tabindex="-1" id="svcrefWe6We10IdLink">
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
														</a>
			               								<%--
			               								<img onClick="showPop('docSertExportRefInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
			               								 --%>
			               							</td>
			               						</tr>
												<tr>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wf6" id="wf6" size="20" maxlength="17" value="${model.record.wf6}">
												</td>
												<td class="text14MediumBlue">
													<select class="selectMediumBlueE2"  name="we6" id="we6">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertExportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we6 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
												<tr>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wf7" id="wf7" size="20" maxlength="17" value="${model.record.wf7}">
												</td>
												<td class="text14MediumBlue">
													<select class="selectMediumBlueE2"  name="we7" id="we7">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertExportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we7 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
												<tr>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wf8" id="wf8" size="20" maxlength="17" value="${model.record.wf8}">
												</td>
												<td class="text14MediumBlue">
													<select class="selectMediumBlueE2"  name="we8" id="we8">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertExportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we8 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
												<tr>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wf9" id="wf9" size="20" maxlength="17" value="${model.record.wf9}">
												</td>
												<td class="text14MediumBlue">
													<select class="selectMediumBlueE2"  name="we9" id="we9">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertExportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we9 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
											<tr>
												<td class="text14MediumBlue">
													<input type="text" class="inputText" name="wf10" id="wf10" size="20" maxlength="17" value="${model.record.wf10}">
												</td>
												<td class="text14MediumBlue">
													<select class="selectMediumBlueE2"  name="we10" id="we10">
										            		<option value="">-velg-</option>
									 				  	<c:forEach var="code" items="${model.docSertExportCodeList}" >
					                                	 		<option value="${code.zkod}"<c:if test="${model.record.we10 == code.zkod}"> selected </c:if> >${code.zkod}</option>
														</c:forEach> 
													</select>
												</td>
											</tr>
												<tr>
												<td class="text14MediumBlue">
													&nbsp;
												</td>
												<td class="text14MediumBlue">
													&nbsp;
												</td>
											</tr>
										</table>
										</td>
									</tr>
									<tr height="10"><td class="text" align="left"></td></tr>
									
						        </table>
					        </td>
				        </tr>
						<tr height="10"><td colspan="2" ></td></tr>
        	        </table>
        	        </form>
		        </td>
		    </tr>
	        <tr height="20"><td colspan="2" ></td></tr>
            <tr height="30"><td></td></tr>
		</table>
		</td>
		</tr>

		<tr>
		<td>
			<div id="dialogKundensVareregister" title="Dialog">
				<form  action="tvinnsadexport_edit_items_doUpdateKundensVareregister.do" name="updateKundensVareregisterForm" id="updateKundensVareregisterForm" method="post">
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="varebe" id="varebe" value=""/>
				 	
					<table>
						<tr>
							<td colspan="6">Beskrivning:&nbsp;<label class="text14Bold" id="svvt" name="svvt"></label></td>
						</tr>
						<tr height="5"><td></td></tr>
						
						<tr>
							<td class="tableHeaderField" align="left" >&nbsp;FK/LK</td>
          					<td class="tableHeaderField" align="left" >&nbsp;Varenr.</td>
          					<td class="tableHeaderField" align="left" >&nbsp;Bruttovekt</td>
          					<td class="tableHeaderField" align="left" >&nbsp;Kundens art.nr</td>
          					<td class="tableHeaderField" align="left" >&nbsp;Kundenr.</td>
          				</tr>
						<tr>
							<td class="tableCell11"><input readonly class="inputTextReadOnly" type="text"  id="w2lk" name="w2lk" size="10px" value=""></input></td>
							<td class="tableCellFirst11"><input class="inputTextReadOnly" type="text" id="w2vnti" name="w2vnti" size="10px" value=""></input></td>
							<td class="tableCell11"><input readonly class="inputTextReadOnly" type="text"  id="w2vktb" name="w2vktb" size="10px" value=""></input></td>
							<td class="tableCell11"><input  class="inputTextMediumBlue" type="text"  id="varenr" name="varenr" size="10px" value=""></input></td>
							<td class="tableCell11"><input  class="inputTextMediumBlue" type="text"  id="levenr" name="levenr" size="10px" value=""></input></td>
						</tr>
						
					</table>
				</form>
			</div>
		</td>
		</tr>
		
	</table>    

		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

