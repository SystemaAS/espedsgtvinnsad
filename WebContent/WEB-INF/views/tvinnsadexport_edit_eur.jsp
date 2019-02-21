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
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadexport_edit_eur.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
	<table width="100%"  class="text12" cellspacing="0" border="0" cellpadding="0">
		<tr height="2">
			<td>
				<input type="hidden" name="modelStatus" id="modelStatus" value='${model.status}'>
			</td>
		</tr>
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
				<a id="alinkInvoices" style="display:block;" href="tvinnsadexport_edit_finansopplysninger.do?action=doFetch&avd=${ model.avd}&sign=${ model.sign}
							&opd=${model.opd}&status=${ model.status}&fabl=${model.sebel1}&o2_sest=${model.o2_sest}&o2_sedt=${model.o2_sedt}&o2_semf=${ model.o2_semf}">
				<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.export.finansopplys.createnew.tab"/></font>
				<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="10%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkNotis" style="display:block;" href="editNotisblock.do?action=doFetch&subsys=sade&orig=topic&avd=${model.avd}&sign=${model.sign}
							&opd=${ model.opd}&status=${model.status}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.export.notisblock.createnew.tab"/>
					</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="10%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkItemLines" style="display:block;" href="tvinnsadexport_edit_items.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
											&opd=${model.opd}&tullId=${model.tullId}
											&status=${model.status}&datum=${model.datum}&fabl=${recordTopicSkatXX.dkih_222}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.import.item.createnew.tab"/>
					</font>
					<c:if test="${model.status == 'M' || empty model.status || model.status == '1'}">
						<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
					</c:if>
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="10%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkLogging" style="display:block;" href="tvinnsadexport_logging.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}
													&status=${model.status}&datum=${model.datum}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.export.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="10%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkArchive" style="display:block;" href="tvinnsadexport_archive.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}
													&status=${model.status}&datum=${model.datum}&o2_sest=${ model.o2_sest}&o2_sedt=${ model.o2_sedt}&o2_semf=${ model.o2_semf}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.export.archive.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="10%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">
					&nbsp;<spring:message code="systema.tvinn.sad.export.eur.tab"/>
				</font>
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
			
			<%-- Other errors (none validation errors) --%>
			<c:if test="${not empty model.errorMessage}">
			<tr>
				<td >
	            	<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td class="textError">
				 				<ul>
                                    <li>
                                    	${model.errorMessage}  
                                    </li>                                    
                                </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
			<%-- ------------------------------------------------- --%>
           	<%-- Form section --%>
           	<%-- ------------------------------------------------- --%>
           	<tr>
	 			
	 			<td  >
	 			
	 				<form name="sadExportEurForm" id="sadExportEurForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
				 	<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
				 	<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
				 	<input type="hidden" name="status" id="status" value="${model.status}"/>
				 	<input type="hidden" name="datum" id="datum" value="${model.datum}"/>
				 	<input type="hidden" name="fabl" id="fabl" value="${recordTopicTvinnSad.sebel1}"/>
				 	<input type="hidden" name="lineId" id="lineId" value="${model.lineId}">
				 	
				 	<div style="width:70%">
	           			<table style="width:100%;background-color:#EEEEEE;" id="eurArea" class="areaWithThickBorder" >
	           			<tr>
	           				<td>
		           				<table >
		           				<tr>
			           			<td class="text14"><b>&nbsp;Varesertifikat EUR.1</b></td>
			           			<td width="15px" class="text14">&nbsp;</td>
			           			
			           			<td class="text14">
			           				<a tabindex=-1 href="TODOtvinnsadexport_edit_printTopic.do?avd=${model.avd}&opd=${model.opd}">
										<img title="Print" style="vertical-align: bottom;cursor:pointer;" src="resources/images/pdf2.png" width="20px" height="20px" border="0" alt="Print">
									</a>
			           			</td>
			           			</tr>
		           			</table>
		           			</td>
		           		</tr>
		           		<%-- FIRST SECTION --%>
		           		<tr>
	           				<td >
	           				<table style="width:100%" class="formFrameTitaniumWhite" >
	           				
							<tr>
			           			<td colspan="5" class="text12">
									&nbsp;&nbsp;<span title="-"><b>1.<font class="text14RedBold" >*</font></b>Eksportør (Navn, full adresse, land)</span>
								</td>
							</tr>
							<tr>
								<td>
									<table>
									
									<tr>
										<td class="text12">&nbsp;<font class="text14RedBold" >*</font><span title="eur01a">Navn</span></td>
										<td class="text12">&nbsp;<font class="text14RedBold" >*</font><span title="eur01b">Adresse-1</span></td>
										<td class="text12">&nbsp;<font class="text14RedBold" >*</font><span title="eur01c">Adresse-2</span></td>
										<td class="text12">&nbsp;<font class="text14RedBold" >*</font><span title="eur01d">Adresse-3</span></td>
									</tr>
									<tr>
					           			<td class="text12">
											<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" type="text" name="eur01a" id="eur01a" size="25" maxlength="30" value="${model.record.eur01a}">
										</td>
										<td class="text12">
											<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" type="text" name="eur01b" id="eur01b" size="25" maxlength="30" value="${model.record.eur01b}">
										</td>
										<td class="text12">
											<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField"type="text" name="eur01c" id="eur01c" size="25" maxlength="30" value="${model.record.eur01c}">
										</td>
										<td class="text12">
											<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField"type="text" name="eur01d" id="eur01d" size="25" maxlength="30" value="${model.record.eur01d}">
										</td>
									</tr>
									</table>
								</td>	
							</tr>
							</table>
							</td>
						</tr>
						<tr>
	           				<td>
	           				<table style="width:100%" class="formFrameTitaniumWhite" >
	           				
							<tr>
			           			<td colspan="5" class="text12">
									&nbsp;&nbsp;<span title="-"><b>3.&nbsp;</b>Mottaker (Navn, fullstendig adresse, land) (utfylling ikke påkrevet)</span>
								</td>
							</tr>
							<tr>
								<td>
									<table>
									
									<tr>
										<td class="text12">&nbsp;<span title="eur03a">Navn</span></td>
										<td class="text12">&nbsp;<span title="eur03b">Adresse-1</span></td>
										<td class="text12">&nbsp;<span title="eur03c">Adresse-2</span></td>
										<td class="text12">&nbsp;<span title="eur03d">Adresse-3</span></td>
									</tr>
									<tr>
					           			<td class="text12">
											<input type="text" class="inputText" name="eur03a" id="eur03a" size="25" maxlength="30" value="${model.record.eur03a}">
										</td>
										<td class="text12">
											<input type="text" class="inputText" name="eur03b" id="eur03b" size="25" maxlength="30" value="${model.record.eur03b}">
										</td>
										<td class="text12">
											<input type="text" class="inputText" name="eur03c" id="eur03c" size="25" maxlength="30" value="${model.record.eur03c}">
										</td>
										<td class="text12">
											<input type="text" class="inputText" name="eur03d" id="eur03d" size="25" maxlength="30" value="${model.record.eur03d}">
										</td>
									</tr>
									</table>
								</td>	
							</tr>
							</table>
							</td>
						</tr>
						
						<tr>
	           				<td>
	           				<table style="width:100%" class="formFrameTitaniumWhite" >
	           				
							<tr>
			           			<td class="text12">
									&nbsp;&nbsp;<span title="eur04"><b>4.<font class="text14RedBold" >*</font></b>Land, gruppe av land eller territorium hvor varene anses å ha sin opprinnelse</span>
								</td>
								<td class="text12">
									&nbsp;<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" type="text" name="eur04" id="eur04" size="3" maxlength="2" value="${model.record.eur04}">
								</td>
								<td width="15px" class="text12">&nbsp;</td>
			           			<td class="text12">
									&nbsp;&nbsp;<span title="eur05"><b>5.<font class="text14RedBold" >*</font></b>Bestemmelsesland, gruppe av land eller territorier</span>
								</td>
								<td class="text12">
									&nbsp;<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" type="text" name="eur05" id="eur05" size="3" maxlength="2" value="${model.record.eur05}">
								</td>
							</tr>	
							
							</table>
							</td>
						</tr>
						
						<tr>
	           				<td>
	           				<table style="width:100%" class="formFrameTitaniumWhite" >
	           				<tr>
	           					<td class="text12">&nbsp;</td>
	           					
			           			<td class="text12">
									&nbsp;<span title="eur06a">Transp.måte</span>
								</td>
			           			<td class="text12">
									&nbsp;<span title="eur06b">Transp.id</span>
								</td>
			           			<td class="text12">
									&nbsp;<span title="eur06c">Landkode Transp.id</span>
								</td>
							</tr>	
							<tr>
			           			<td class="text12">
									&nbsp;&nbsp;<span title="-"><b>6.&nbsp;</b>Opplysninger om transporten (utfylling ikke påkrevet)</span>
								</td>
								<td class="text12">
									&nbsp;<input type="text" class="inputText" name="eur06a" id="eur06a" size="3" maxlength="2" value="${model.record.eur06a}">
								</td>
								<td class="text12">
									&nbsp;<input type="text" class="inputText" name="eur06b" id="eur06b" size="20" maxlength="17" value="${model.record.eur06b}">
								</td>
								<td class="text12">
									&nbsp;<input type="text" class="inputText" name="eur06c" id="eur06c" size="3" maxlength="2" value="${model.record.eur06c}">
								</td>
							</tr>	
							
							</table>
							</td>
						</tr>
						<tr>
	           				<td>
	           				<table style="width:100%" class="formFrameTitaniumWhite" >
	           				
							<tr>
			           			<td class="text12">
									&nbsp;<span title="-"><b>7.&nbsp;</b>Merknader</span>
								</td>
							</tr>
							<tr>	
								<td class="text12">
									&nbsp;1.<input type="text" class="inputText" name="eur07a" id="eur07a" size="20" maxlength="30" value="${model.record.eur07a}">
								</td>
								<td class="text12">
									&nbsp;2.<input type="text" class="inputText" name="eur07b" id="eur07b" size="20" maxlength="30" value="${model.record.eur07b}">
								</td>
								<td class="text12">
									&nbsp;3.<input type="text" class="inputText" name="eur07c" id="eur07c" size="20" maxlength="30" value="${model.record.eur07c}">
								</td>
								<td class="text12">
									&nbsp;4.<input type="text" class="inputText" name="eur07d" id="eur07d" size="20" maxlength="30" value="${model.record.eur07d}">
								</td>
								<td class="text12">
									&nbsp;5.<input type="text" class="inputText" name="eur07e" id="eur07e" size="20" maxlength="30" value="${model.record.eur07e}">
								</td>
							</tr>	
							
							</table>
							</td>
						</tr>

						<tr>
		           			<td height="10px" class="text12">&nbsp;</td>
		           		</tr>
		           		
						<tr>
	           				<td>
	           				<table style="width:100%" class="formFrameTitaniumWhite" >
	           				
							<tr>
			           			<td colspan="5" class="text12">
									&nbsp;<span title="todo"><b>8.&nbsp;</b>Løpenr.,kollienes merke, nr., antall og art; vareslag.&nbsp;&nbsp;&nbsp;<b>9.&nbsp;</b>Bruttovekt(kg) eller annet mål (l,m3,etc)&nbsp;&nbsp;&nbsp;<b>10.&nbsp;</b>Fakturaer&nbsp;(utfylling ikke påkrevet)</span>
								</td>
							</tr>
							<tr>	
								<td class="text12">
									<table id="tblItems" style="width:100%" >
										<thead>
										<tr >
											<th align="left" class="tableHeaderFieldFirst12"><span title="eur081a...">Koll.merke.</th>
											<th align="right" class="tableHeaderField12"><span title="eur082a...">Antall</span></th>
											<th align="center" class="tableHeaderField12"><span title="eur083a...">Art</th>
											<th align="center" class="tableHeaderField12"><span title="eur084a...">Vareslag</th>
											<th align="right" class="tableHeaderField12"><span title="eur09a...">Bruttovekt</th> 
											<th align="center" class="tableHeaderField12"><span title="eur10a...">Fakt.nr</th>  	
										</tr>
										</thead>
										<tbody>
											<tr class="tableRow" >
												<td class="tableCellFirst">
													<input type="text" class="inputTextMediumBlue12" name="eur081a" id="eur081a" size="15" maxlength="28" value="${model.record.eur081a}">
												</td>
												<td class="tableCell"  align="right">
													<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue12" style="text-align:right;" name="eur082a" id="eur082a" size="7" maxlength="6" value="${model.record.eur082a}">
												</td>
												<td class="tableCell" align="center">
													<input type="text" class="inputTextMediumBlue12" name="eur083a" id="eur083a" size="5" maxlength="4" value="${model.record.eur083a}">
												</td>
												<td class="tableCell" align="center">
													<input type="text" class="inputTextMediumBlue12" name="eur084a" id="eur084a" size="15" maxlength="30" value="${model.record.eur084a}">
												</td>
												<td class="tableCell" align="right">
													<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue12" style="text-align:right;" name="eur09a" id="eur09a" size="15" maxlength="15" value="${model.record.eur09a}">
												</td>
												<td class="tableCell" align="center">
													<input type="text" class="inputTextMediumBlue12" name="eur10a" id="eur10a" size="15" maxlength="17" value="${model.record.eur10a}">
												</td>
												
											</tr>
											<tr class="tableRow" >
												<td class="tableCellFirst">
													<input type="text" class="inputTextMediumBlue12" name="eur081b" id="eur081b" size="15" maxlength="28" value="${model.record.eur081b}">
												</td>
												<td class="tableCell"  align="right">
													<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue12" style="text-align:right;" name="eur082b" id="eur082b" size="7" maxlength="6" value="${model.record.eur082b}">
												</td>
												<td class="tableCell" align="center">
													<input type="text" class="inputTextMediumBlue12" name="eur083b" id="eur083b" size="5" maxlength="4" value="${model.record.eur083b}">
												</td>
												<td class="tableCell" align="center">
													<input type="text" class="inputTextMediumBlue12" name="eur084b" id="eur084b" size="15" maxlength="30" value="${model.record.eur084b}">
												</td>
												<td class="tableCell" align="right">
													<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue12" style="text-align:right;" name="eur09b" id="eur09b" size="15" maxlength="15" value="${model.record.eur09b}">
												</td>
												<td class="tableCell" align="center">
													<input type="text" class="inputTextMediumBlue12" name="eur10b" id="eur10b" size="15" maxlength="17" value="${model.record.eur10b}">
												</td>
												
											</tr>
											<tr class="tableRow" >
												<td class="tableCellFirst">
													<input type="text" class="inputTextMediumBlue12" name="eur081c" id="eur081c" size="15" maxlength="28" value="${model.record.eur081c}">
												</td>
												<td class="tableCell"  align="right">
													<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue12" style="text-align:right;" name="eur082c" id="eur082c" size="7" maxlength="6" value="${model.record.eur082c}">
												</td>
												<td class="tableCell" align="center">
													<input type="text" class="inputTextMediumBlue12" name="eur083c" id="eur083c" size="5" maxlength="4" value="${model.record.eur083c}">
												</td>
												<td class="tableCell" align="center">
													<input type="text" class="inputTextMediumBlue12" name="eur084c" id="eur084c" size="15" maxlength="30" value="${model.record.eur084c}">
												</td>
												<td class="tableCell" align="right">
													<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue12" style="text-align:right;" name="eur09c" id="eur09c" size="15" maxlength="15" value="${model.record.eur09c}">
												</td>
												<td class="tableCell" align="center">
													<input type="text" class="inputTextMediumBlue12" name="eur10c" id="eur10c" size="15" maxlength="17" value="${model.record.eur10c}">
												</td>
												
											</tr>
											<tr class="tableRow" >
												<td class="tableCellFirst">
													<input type="text" class="inputTextMediumBlue12" name="eur081d" id="eur081d" size="15" maxlength="28" value="${model.record.eur081d}">
												</td>
												<td class="tableCell"  align="right">
													<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue12" style="text-align:right;" name="eur082d" id="eur082d" size="7" maxlength="6" value="${model.record.eur082d}">
												</td>
												<td class="tableCell" align="center">
													<input type="text" class="inputTextMediumBlue12" name="eur083d" id="eur083d" size="5" maxlength="4" value="${model.record.eur083d}">
												</td>
												<td class="tableCell" align="center">
													<input type="text" class="inputTextMediumBlue12" name="eur084d" id="eur084d" size="15" maxlength="30" value="${model.record.eur084d}">
												</td>
												<td class="tableCell" align="right">
													<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue12" style="text-align:right;" name="eur09d" id="eur09d" size="15" maxlength="15" value="${model.record.eur09d}">
												</td>
												<td class="tableCell" align="center">
													<input type="text" class="inputTextMediumBlue12" name="eur10d" id="eur10d" size="15" maxlength="17" value="${model.record.eur10d}">
												</td>
												
											</tr>
											<tr class="tableRow" >
												<td class="tableCellFirst">
													<input type="text" class="inputTextMediumBlue12" name="eur081e" id="eur081e" size="15" maxlength="28" value="${model.record.eur081e}">
												</td>
												<td class="tableCell"  align="right">
													<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue12" style="text-align:right;" name="eur082e" id="eur082e" size="7" maxlength="6" value="${model.record.eur082e}">
												</td>
												<td class="tableCell" align="center">
													<input type="text" class="inputTextMediumBlue12" name="eur083e" id="eur083e" size="5" maxlength="4" value="${model.record.eur083e}">
												</td>
												<td class="tableCell" align="center">
													<input type="text" class="inputTextMediumBlue12" name="eur084e" id="eur084e" size="15" maxlength="30" value="${model.record.eur084e}">
												</td>
												<td class="tableCell" align="right">
													<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue12" style="text-align:right;" name="eur09e" id="eur09e" size="15" maxlength="15" value="${model.record.eur09e}">
												</td>
												<td class="tableCell" align="center">
													<input type="text" class="inputTextMediumBlue12" name="eur10e" id="eur10e" size="15" maxlength="17" value="${model.record.eur10e}">
												</td>
												
											</tr>
										</tbody>
										
									</table>
								</td>
							</tr>
							
							
							</table>
							</td>
						</tr>
						
						<tr>
	           				<td>
	           				<table style="width:100%" class="formFrameTitaniumWhite" >
	           				
							<tr>
			           			<td class="text12">
									&nbsp;<span title="todo"><b>11.&nbsp;</b>TOLLVESENETS PÅTEGNING</span>
								</td>
							</tr>
							<tr>	
								<td class="text12">
									&nbsp;<input tabindex=-1 readonly type="text" class="inputTextReadOnly" name="n/a" id="n/a" size="10" value="N/A">
								</td>
							</tr>
							<tr>	
								<td style="height:10px" class="text12"></td>
							</tr>
							<tr>
			           			<td class="text12">
									&nbsp;<span title="todo"><b>12.<font class="text14RedBold" >*</font></b>EKSPORTØRENS ERKLÆRING</span>
								</td>
							</tr>
							<tr>
			           			<td class="text12">
									&nbsp;<span title="todo">Undertegnede erklærer att de ovenfor nevnte varer oppfyller de vilkpr som kreves ror utstedelse av dette varesertifikat.
								</td>
							</tr>
							<tr>	
								<td class="text12">
								<table>
									<tr>
									<td>
									&nbsp;<font class="text14RedBold" >*</font><span title="eur12a">Sted:&nbsp;</span><input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="eur12a" id="eur12a" size="16" maxlength="15" value="${model.record.eur12a}">
									&nbsp;<font class="text14RedBold" >*</font><span title="eur12b">Dato:&nbsp;</span><input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="eur12b" id="eur12b" size="9" maxlength="8" value="${model.record.eur12b}">
									&nbsp;<font class="text14RedBold" >*</font><span title="eur12c">Navn deklarant:&nbsp;</span><input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="eur12c" id="eur12c" size="25" maxlength="30" value="${model.record.eur12c}">
									</td>
									<td class="text14">
			           					<input class="inputFormSubmit" type="submit" name="submit" id="submit" value="Lagre" onclick="javascript: form.action='tvinnsadexport_edit_eur.do';" />
			           				</td>
									</tr>
								</table>	
								</td>
							</tr>	
							
							</table>
							</td>
						</tr>
						
				 		</table>
			 		</div>	
				 </form>
				 </td>
				 
				 
				 
				 
				 
				
	 			<td valign="top" >
	 				<%-- MASTER Topic header --%>
	 				<table class="formFrameTitaniumWhite" border="0" >
				 		<tr height="15">
				 			<td class="text12MediumBlue">
				 				&nbsp;Avd&nbsp;<b>${model.avd}</b>
				 				&nbsp;Tolldeknr.&nbsp;<b>${model.opd}</b>
				 				&nbsp;Sign&nbsp;<b>${model.sign}</b>
				 				
				 				&nbsp;&nbsp;
				 				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				Status:&nbsp;<b>${model.status}</b>
				 				&nbsp;&nbsp;Dekl.:&nbsp;<b>${recordTopicTvinnSad.sedty}</b>
				 				<div class="text12" style="position: relative;" align="left">
				 				<span style="position:absolute;top:2px; width:400px;" id="status_info" class="popupWithInputText text12"  >
					           		<br/>
				           		 	Bare status <b>M</b> (Fejl) eller <b>' '</b> kan redigeres. 
				           			<ul>
				           				<li><b>' '</b>&nbsp;Deklarasjonen er åpen for endring.
				           				<li><b>+</b>&nbsp;Systemet lager nu utgående EDIFACT melding for å kunne sende deklarasjonen..</li>
				           				<li><b>A</b>&nbsp;Deklarasjonen ligger i en sending i påvente av å bli sendt.</li>
				           				<li><b>C</b>&nbsp;Sendingen er videresendt til TVINN.</li>
				           				<li><b>D</b>&nbsp;Melding om dokumentkontroll er mottatt fra Tollvesenet.</li>
				           				<li><b>E</b>&nbsp;Deklarasjonen blir endret av en saksbehandler.</li>
				           				<li><b>F</b>&nbsp;Edifacttekniske fejl oppdaget.</li>
				           				<li><b>K</b>&nbsp;Deklarasjonen er klar for utskrift men er ikke skrevet ut.</li>
				           				<li><b>L</b>&nbsp;Deklarasjonen er klar for utskrift men er ikke skrevet ut.</li>	
				           				<li><b>M</b>&nbsp;Tollteknisk fejl</li>
				           				<li><b>P</b>&nbsp;Deklarasjonen er skrevet ut. Hvis den er sendt på TVINN vil denne koden bety at tollkvittering er mottatt fra Tollvesenet og skrevet ut.</li>
				           				<li><b>Q</b>&nbsp;Deklarasjonen ligger i utgående postkasse for TVINN. men er ikke sendt.</li>
				           				<li><b>T</b>&nbsp;Informasjonsmelding om at deklarasjonen er lagt til manuell ekspedering hos Tollvesenet.</li>
				           				<li><b>U</b>&nbsp;Utleveringsattest er mottatt fra Tollvesenet.</li>
				           				<li><b>V</b>&nbsp;Melding om varekontroll er mottatt fra Tollvesenet.</li>
				           				<li><b>1</b>&nbsp;Melding fra Input-kontroll / Toller er mottatt og klar til å skrives ut.</li>	
				           				<li><b>2</b>&nbsp;Utleveringsattest er mottatt fra Tvinn og klar til å skrives ut.</li>	
				           				<li><b>3</b>&nbsp;Tollkvittering/tolldeklarasjon er mottatt fra Tvinn og klar til å skrives ut.</li>		           				
				           			</ul>
								</span>	
								</div>
			 				</td>
		 				</tr>
				 		<tr height="2"><td class="text" align="left" colspan="2"></td></tr>
				 		<tr>
					 		<td >
						 		<table >
							 		<tr>
							            <td class="text12Bold" align="left" >Eksportør</td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td class="text12" align="left">Navn&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.senak}</td>
							        </tr>
							        <tr>
							            <td class="text12" align="left">Regnr.&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.serg}</td>
						           	</tr>
							        <tr>
							            <td class="text12" align="left">Adresse-1&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.seadk1}</td>
							        </tr>
							        <tr>
							            <td class="text12" align="left">Adresse-2&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.seadk2}</td>
							        </tr>   	
							        <tr>
							            <td class="text12" align="left">Adresse-3&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.seadk3}</td>
							        </tr>
							        <tr height="5"><td class="text" ></td></tr>
							        <tr>
							            <td class="text12Bold" align="left" >Mottaker</td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td class="text12" align="left">Navn&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.senas}</td>
							       </tr>
							       <tr>
							            <td class="text12" align="left">Adresse-1&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.seads1}</td>
							        </tr>
							        <tr>
							            <td class="text12" align="left">Adresse-2&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.seads2}</td>
							        </tr>
							        <tr>
							            <td class="text12" align="left">Adresse-3&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopicTvinnSad.seads3}</td>
							        </tr>
							           					        
			        	        </table>
					        </td>
					        
				        </tr>
					</table>      
            	</td>
				 
				 
		    </tr>
			<tr height="30"><td></td></tr>
		</table>
		</td>
		</tr>
	</table>    
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

