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
			<tr>
	 			<td >		
	 				<%-- MASTER Topic header --%>
	 				<table width="80%" align="left" class="formFrameHeaderTransparent" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12MediumBlue">
				 				&nbsp;Avd&nbsp;<b>${model.avd}</b>
				 				&nbsp;Tolldeknr.&nbsp;<b>${model.opd}</b>
				 				&nbsp;Sign&nbsp;<b>${model.sign}</b>
				 				<%--
				 				&nbsp;&nbsp;&nbsp;&nbsp;Ref.nr.:&nbsp;<b>${XrecordTopicTvinnSad.dkih_07}</b>
				 				--%>
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
	 				</table>
					<%-- MASTER Topic information [it is passed through a session object: XX] --%>
				 	<table height="40" width="80%" align="left" class="formFrameTitaniumWhite" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="2"><td class="text" align="left" colspan="2"></td></tr>
				 		<tr>
					 		<td width="50%">
						 		<table width="100%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text12Bold" align="left" >Eksportør</td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">Regnr.&nbsp;</td>
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
							        <tr>
							        		<td width="30%" class="text12" align="left">&nbsp;</td>
							        </tr>						        
			        	        </table>
					        </td>
					        <td width="50%">
						 		<table width="100%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text12Bold" align="left" >Mottaker</td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
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
           	<%-- DETAIL Section - Create Item line PRIMARY SECTION --%>
           	<%-- ------------------------------------------------- --%>
           	<tr>
	 			
	 			<td  >
	 			
	 				<form name="sadImportEurForm" id="sadImportEurForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
				 	<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
				 	<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
				 	<input type="hidden" name="status" id="status" value="${model.status}"/>
				 	<input type="hidden" name="datum" id="datum" value="${model.datum}"/>
				 	<input type="hidden" name="fabl" id="fabl" value="${recordTopicTvinnSad.sebel1}"/>
				 	<input type="hidden" name="lineId" id="lineId" value="${model.lineId}">
				 	<%--<span style="background-color:#EEEEEE;" id="eurArea" class="areaWithInputTextThickBorder"  >  --%>
	           		
	           			<table border="0" align="left" cellspacing="2" style="width:80%;background-color:#EEEEEE;" id="eurArea" class="areaWithThickBorder" >
	           			<tr>
		           			<td colspan="3" class="text14"><b>Varesertifikat EUR.1</b></td>
		           		</tr>
		           		<%-- FIRST SECTION --%>
		           		<tr>
	           				<td>
	           				<table class="formFrameTitaniumWhite" >
	           				
							<tr>
			           			<td colspan="5" class="text12">
									&nbsp;&nbsp;<span title="todo"><b>1.&nbsp;</b>Eksportør (Navn, full adresse, land)</span>
								</td>
							</tr>
							<tr>
								<td>
									<table>
									<tr>
				           			<td class="text12">
										&nbsp;<input type="text" class="inputText" name="todo" id="todo" size="20" maxlength="35" value="${Xmodel.record.dkih_t05a}">
									</td>
									<td class="text12">
										&nbsp;<input type="text" class="inputText" name="todo" id="todo" size="20" maxlength="35" value="${Xmodel.record.dkih_t05a}">
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
	           				<table class="formFrameTitaniumWhite" >
	           				
							<tr>
			           			<td colspan="5" class="text12">
									&nbsp;&nbsp;<span title="todo"><b>3.&nbsp;</b>Mottaker (Navn, fullstendig adresse, land) (utfylling ikke påkrevet)</span>
								</td>
							</tr>
							<tr>
								<td>
									<table>
									<tr>
				           			<td class="text12">
										&nbsp;<input type="text" class="inputText" name="todo" id="todo" size="20" maxlength="35" value="${Xmodel.record.dkih_t05a}">
									</td>
									<td class="text12">
										&nbsp;<input type="text" class="inputText" name="todo" id="todo" size="20" maxlength="35" value="${Xmodel.record.dkih_t05a}">
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
	           				<table class="formFrameTitaniumWhite" >
	           				
							<tr>
			           			<td class="text12">
									&nbsp;&nbsp;<span title="todo"><b>4.&nbsp;</b>Land, gruppe av land eller territorium hvor varene anses å ha sin opprinnelse</span>
								</td>
								<td class="text12">
									&nbsp;<input type="text" class="inputText" name="todo" id="todo" size="5" maxlength="2" value="${Xmodel.record.dkih_t05a}">
								</td>
							</tr>
							<tr>
			           			<td class="text12">
									&nbsp;&nbsp;<span title="todo"><b>5.&nbsp;</b>Bestemmelsesland, gruppe av land eller territorier</span>
								</td>
								<td class="text12">
									&nbsp;<input type="text" class="inputText" name="todo" id="todo" size="5" maxlength="2" value="${Xmodel.record.dkih_t05a}">
								</td>
							</tr>	
							
							</table>
							</td>
						</tr>
						
				 		</table>
			 			
				 </form>
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

