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
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadimport_edit_finansopplysninger.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
		<tr height="2">
			<td>
				<input type="hidden" name="modelStatus" id="modelStatus" value='${model.status}'>
			</td>
		</tr>
		<tr height="25"> 
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkTopicList" style="display:block;" href="tvinnsadimport.do?action=doFind&sg=${model.sign}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.import.list.tab"/></font>
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkHeader" style="display:block;" href="tvinnsadimport_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
						&sysg=${model.sign}&tuid=${refnr}&syst=${model.status}&sydt=${model.datum}&o2_sist=${ model.o2_sist}&o2_sidt=${ model.o2_sidt}&o2_simf=${ model.o2_simf}">
					
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.import.created.mastertopic.tab"/></font>
					<font class="text12MediumBlue">[${model.opd}]</font>
					<c:if test="${model.status == 'M' || empty model.status}">
						<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.import.finansopplys.createnew.tab"/></font>
				<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkNotis" style="display:block;" href="editNotisblock.do?action=doFetch&subsys=sadi&orig=topic&avd=${ model.avd}&sign=${ model.sign}
											&opd=${ model.opd}&o2_sist=${ model.o2_sist}&o2_sidt=${ model.o2_sidt}&o2_simf=${ model.o2_simf}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.import.notisblock.createnew.tab"/>
					</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkItemLines" style="display:block;" href="tvinnsadimport_edit_items.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
											&opd=${model.opd}&tullId=${model.tullId}
											&status=${model.status}&datum=${model.datum}&fabl=${recordTopicSkatXX.dkih_222}&o2_sist=${ model.o2_sist}&o2_sidt=${ model.o2_sidt}&o2_simf=${ model.o2_simf}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.import.item.createnew.tab"/>
					</font>
					<c:if test="${model.status == 'M' || empty model.status || model.status == '1'}">
						<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
					</c:if>
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkLogging" style="display:block;" href="tvinnsadimport_logging.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}
													&status=${model.status}&datum=${model.datum}&o2_sist=${ model.o2_sist}&o2_sidt=${ model.o2_sidt}&o2_simf=${ model.o2_simf}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.import.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkArchive" style="display:block;" href="tvinnsadimport_archive.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}
													&status=${model.status}&datum=${model.datum}&o2_sist=${ model.o2_sist}&o2_sidt=${ model.o2_sidt}&o2_simf=${ model.o2_simf}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.import.archive.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
				</a>
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
				 				&nbsp;&nbsp;Dekl.:&nbsp;<b>${recordTopicTvinnSad.sidty}</b>
				 				
				 				<span style="position:absolute; left:850px; top:100px; width:400px; height:500px;" id="status_info" class="popupWithInputText"  >
					           		<div class="text11" align="left">
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
									</div>
								</span>	
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
							            <td width="30%" class="text11Bold" align="left" >Eksportør</td>
							            <td class="text11" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <%--
							        <tr>
							        		<td width="30%" class="text11" align="left">EORI&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XrecordTopicTvinnSad.dkih_02a}</td>
							           	
							        </tr>
							         --%>
							        <tr>
							            <td width="30%" class="text11" align="left">Navn&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.sinas}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Adresse-1&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.siads1}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Adresse-2&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.siads2}</td>
							        </tr>
							        
									<tr>
							            <td width="30%" class="text11" align="left">Adresse-3&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.siads3}</td>
							        </tr>
							        <tr>
							        		<td width="30%" class="text11" align="left">&nbsp;</td>
							        </tr>						        
			        	        </table>
					        </td>
					        <td width="50%">
						 		<table width="100%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text11Bold" align="left" >Mottaker</td>
							            <td class="text11" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Regnr.&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.sirg}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Navn&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.sinak}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Adresse-1&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.siadk1}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Adresse-2&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.siadk2}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Adresse-3&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.siadk3}</td>
							        </tr>
							        <%--
									<tr>
							            <td width="30%" class="text11" align="left">Handläggare&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XX.svih_moha}</td>
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
								<form name="createNewItemLine" id="createNewItemLine" method="post" action="tvinnsadimport_edit_finansopplysninger.do">
								<input type="hidden" name="action" id="action" value='doFetch'>
				 				<input type="hidden" name="avd" id="avd" value='${model.avd}'>
				 				<input type="hidden" name="sign" id="sign" value='${model.sign}'>
								<input type="hidden" name="opd" id="opd" value='${model.opd}'>
				 				<input type="hidden" name="refnr" id="refnr" value='${dkih_07}'>
				 				<input type="hidden" name="status" id="status" value='${model.status}'>
				 				<input type="hidden" name="datum" id="datum" value='${model.datum}'>
				 				<input type="hidden" name="fabl" id="fabl" value='${XrecordTopicTvinnSad.sebel1}'>
				 				<input type="hidden" name="totalGrossWeight" id="totalGrossWeight" value='${XrecordTopicTvinnSad.sevkb}'>
							
								<table width="80%" cellspacing="0" border="0" cellpadding="0">
									<tr>
										<td class="text12Bold">
											<c:if test="${model.status == 'M' || empty model.status}">
												<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submit" onclick="javascript: form.action='tvinnsadimport_edit_finansopplysninger.do';" value="<spring:message code="systema.tvinn.sad.import.item.line.init.createnew.submit"/>">
												&nbsp;<button title="Import av eksterna fakturaer" name="importInvoicesButton" id="importInvoicesButton" class="buttonGrayWithGreenFrame" type="button" >Importera eksterna fakturaer</button>
											</c:if>
										</td>
									</tr>
									<tr>
										<td class="text12Bold">&nbsp;Antall fakturaer&nbsp;&nbsp;<font class="text12MediumBlue"><b>${model.recordItemContainerFinansOpplysningerTopic.totalNumberOfItemLines}</b></font>
						            		</td>
										<td align="right" class="text11">Fsum:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" maxlength=20" value="${recordTopicTvinnSad.sibel3}">
											<font class="inputText11BlueBoldReadOnly">${recordTopicTvinnSad.sival3}</font>
										</td>
										<td align="right" class="text11">Vsum&nbsp;(&Sigma;):&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" maxlength=20" value="${model.recordItemContainerFinansOpplysningerTopic.calculatedItemLinesTotalAmount}">
											<font class="inputText11BlueBoldReadOnly">${model.recordItemContainerFinansOpplysningerTopic.calculatedValidCurrency}</font>											
										</td>
										<%--
										<td align="right" class="text11">Diff:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly
												<c:choose>
												<c:when test="${fn:contains(model.recordItemContainerFinansOpplysningerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount,'-')}">
													class="inputText11RedBoldReadOnly" 
												</c:when>
												<c:otherwise>
													class="inputText11BlueBoldReadOnly"
												</c:otherwise>
												</c:choose>
												size="12" maxlength=20" value='${model.recordItemContainerFinansOpplysningerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount}'>
										</td>
										 --%>
									</tr>
									<tr height="2"><td></td></tr>
								</table>
								</form>
							</td>
						</tr> 
						
						<tr>
							<td>
								<form name="formItemList" id="formItemList" method="POST" >
			               		<input type="hidden" name="opdItemList" id="opdItemList" value="${model.opd}">
		 						<input type="hidden" name="avdItemList" id="avdItemList" value="${model.avd}">
		 						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">

				 				<table id="containerdatatableTable" width="80%" cellspacing="2" align="left" >
								<tr>
								<td class="text11">
							
								<table id="tblInvoices" class="display compact cell-border" >
									<thead>
									<tr style="background-color:#DDDDDD">
									    <th class="text12">&nbsp;Finans.opplysn.&nbsp;</th> 
									    <th align="center" class="text12" nowrap>&nbsp;Dato&nbsp;</th>
					                    <th align="right" class="text12" nowrap>&nbsp;Beløp&nbsp;</th>
					                    <th align="right" class="text12" nowrap>&nbsp;Valuta&nbsp;</th>
					                    <th align="right" class="text12" nowrap>&nbsp;Kurs&nbsp;</th>
					                    <th align="right" class="text12" nowrap>&nbsp;Faktor&nbsp;</th>
					                    <c:if test="${model.status == 'M' || empty model.status}">
					                    	<th align="center" class="text12" >Slett</th>
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
							               <td width="2%" class="text11" align="right">
							               		<a tabindex=-1 id="recordUpdate__${record.sftxt}__${record.sfdt}" href="#" onClick="getFinansOpplysningerItemData(this);">${record.sftxt}
							               			<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;
							               		</a>&nbsp;&nbsp;
							               </td>
							               <td align="center" class="text11" >&nbsp;${record.sfdt}</td>
							               <td align="right" class="text11" >&nbsp;${record.sfbl28}</td>
							               <td align="right" class="text11" >&nbsp;${record.sfvk28}</td>
							               <td align="right" class="text11" >&nbsp;${record.sfkr28}</td>
							               <td align="right" class="text11" >&nbsp;${record.sfom28}</td>
							               <c:if test="${model.status == 'M' || empty model.status}">	
								               <td class="text11" align="center" nowrap>
								               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadimport_edit_finansopplysninger.do?action=doDelete&sign=${model.sign}&avd=${model.avd}&opd=${model.opd}&status=${model.status}&fak=${record.sftxt}">
								               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
								               	</a>	&nbsp;
								               </td>
							               </c:if>
							            </tr>
								        <%-- this param is used ONLY in this JSP 
								        <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" />
								        --%> 
								        <%-- this param is used throughout the Controller --%>
								        <c:set var="numberOfItemLinesInTopic" value="${recordX.svln}" scope="request" /> 
								        </c:forEach>
								        </tbody>
							        </table>
							        </td>
						        </tr>
						        </table>
						        </form>
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
	 				<form name="sadImportEditTopicFinansOpplysningerItemForm" id="sadImportEditTopicFinansOpplysningerItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
				 	<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
				 	<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
				 	<input type="hidden" name="status" id="status" value="${model.status}"/>
				 	<input type="hidden" name="datum" id="datum" value="${model.datum}"/>
				 	<input type="hidden" name="fabl" id="fabl" value="${recordTopicTvinnSad.sibel3}"/>
				 	<input type="hidden" name="lineId" id="lineId" value="">
				 	<%-- <input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" /> --%>
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="80%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White" align="left" >
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
				 		<tr>
					 		<td>
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
							 			<td class="text12" align="left">
							 			<img onMouseOver="showPop('finans_opp_info');" onMouseOut="hidePop('finans_opp_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>28.</b><font class="text16RedBold" >*</font>&nbsp;<span title="sftxt">Finans.opplysn.</span>
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="finans_opp_info" class="popupWithInputText text11"  >
							           		<b>28.Finansielle opplysninger og bankdata</b>
											<p>
						           		 	Oppgi fakturanummer og dato. Fakturaer med fortløpende nummer kan oppgis med første og siste nummer. Feks. 270-275.
						           		 	</p>
						           		 	<p>
						           		 	Er det blanding av valutaslag, regner systemet om til NOK.
						           		 	</p> 
										</span>
										</div>
										</td>
							            <td class="text12" align="left"><span title="sfdt">&nbsp;<font class="text16RedBold" >*</font>Dato</span></td>
							            <td class="text12" align="left"><span title="sfbl28">&nbsp;<font class="text16RedBold" >*</font>Beløp</span></td>
							            <td class="text12" align="left"><span title="sfvk28">&nbsp;<font class="text16RedBold" >*</font>Valuta</span></td>
						            		<td class="text12" align="left"><span title="sfkr28">&nbsp;<font class="text16RedBold" >*</font>Kurs</span></td>
						            		<td class="text12" align="left"><span title="factor">Faktor&nbsp;</span></td>
							        </tr>
							        <tr>
						        		<td align="left">
						        			<input type="text" class="inputTextMediumBlueMandatoryField" name="sftxt" id="sftxt" size="20" maxlength="17" value="${model.record.sftxt}">
										</td>
										<td align="left">
							        			<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="sfdt" id="sfdt" size="6" maxlength="6" value="${model.record.sfdt}">
										</td>
										<td class="text12" align="left">
							            		<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="sfbl28" id="sfbl28" size="13" maxlength="12" value="${model.record.sfbl28}">
							            </td>
										<td align="left" nowrap>
								            	<select class="inputTextMediumBlueMandatoryField" name="sfvk28" id="sfvk28">
						 						<option value="">-velg-</option>
							 				  	<c:forEach var="currency" items="${model.currencyCodeList}" >
							 				  		<option value="${currency.zkod}"<c:if test="${model.record.sfvk28 == currency.zkod}"> selected </c:if> >${currency.zkod}</option>
												</c:forEach>  
											</select>
											<a tabindex="-1" id="sfvk28IdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
											<%-- info span
											<img onClick="showPop('valutaSearchInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
											<span style="position:absolute; left:800px; top:350px; width:350px; height:150px;" id="valutaSearchInfo" class="popupWithInputText"  >
								           		<div class="text10" align="left">
							           				<select class="text11" id="currencySearch" name="currencySearch" size="5" onDblClick="hidePop('valutaSearchInfo');">
								           				<c:forEach var="currency" items="${model.currencyCodeList}" >
								 				  			<option value="${currency.zkod}">${currency.zkod}&nbsp;${currency.ztxt}</option>
														</c:forEach>
								           			</select>
								           			<table width="100%" align="left" border="0">
														<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
														<tr align="left" >
															<td class="text11">&nbsp;<button name="valutaSearchButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('valutaSearchInfo');">&nbsp;<spring:message code="systema.tvinn.sad.import.ok"/></button> 
															</td>
														</tr>
													</table>
												</div>
											</span>
											--%>
										</td>
						        		<td class="text12" align="left">
						            		<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="sfkr28" id="sfkr28" size="6" maxlength="6" value="${model.record.sfkr28}">
							            </td>
							            <%-- this field is only used via Ajax since there is no database field. It is used to disclosed a factor when changing the currency --%>
							 			<td class="text12Grey" align="left" ><input readonly type="text" class="inputTextReadOnly" name="factor" id="factor" size="6" value=""></td>
							        </tr>
							        <tr height="10"><td class="text" align="left"></td></tr>
						        </table>
					        </td>
				        </tr>
					    <tr height="10"><td colspan="2" ></td></tr>
					    <tr>	
						    <td align="left" colspan="5">
									<c:choose>	
										<c:when test="${model.status == 'M' || empty model.status || model.status == '1'}">
											<input class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='tvinnsadimport_edit_finansopplysninger.do';" value='<spring:message code="systema.tvinn.sad.import.item.createnew.submit"/>'>
											&nbsp;&nbsp;
										</c:when>
										<c:otherwise>
				 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='<spring:message code="systema.tvinn.sad.submit.not.editable"/>'/>
				 				    	</c:otherwise>	
			 				    	</c:choose>	
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

