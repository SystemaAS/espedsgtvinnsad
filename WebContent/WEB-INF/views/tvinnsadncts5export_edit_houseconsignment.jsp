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
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadncts5export_edit_houseconsignment.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkTopicList" tabindex=-1 style="display:block;" 
					<c:choose>
						<c:when test="${empty model.sign}">href="tvinnsadncts5export.do?action=doFind&sign=${user.tvinnSadSign}"</c:when>
						<c:otherwise>href="tvinnsadncts5export.do?action=doFind&sign=${model.sign}"</c:otherwise>
					</c:choose> >
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.list.tab"/></font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkHeader" tabindex=-1 style="display:block;" href="tvinnsadncts5export_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
						&sysg=${model.sign}&tuid=${model.tullId}&syst=${model.status}&sydt=${model.datum}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.created.mastertopic.tab"/></font>
					<font class="text14MediumBlue">[${model.opd}]</font>
					<c:if test="${ model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">
						<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.houseconsignment.createnew.tab"/></font>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkItemLines" tabindex=-1 style="display:block;" href="tvinnsadncts5export_edit_items.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
												&opd=${model.opd}&tullId=${model.tullId}&mrnNr=${model.mrnNr}
												&status=${model.status}&datum=${model.datum}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.item.createnew.tab"/>
						</font>
						
					</a>
				</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkLogging" tabindex=-1 style="display:block;" href="tvinnsadncts5export_logging.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}&tullId=${model.tullId}
											&mrnNr=${model.mrnNr}&status=${model.status}&datum=${model.datum}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkArchive" tabindex=-1 style="display:block;" href="tvinnsadncts5export_archive.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}&tullId=${model.tullId}
											&mrnNr=${model.mrnNr}&status=${model.status}&datum=${model.datum}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.archive.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
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
				 				&nbsp;&nbsp;&nbsp;Mrn-nr.:&nbsp;<b>${model.mrnNr}</b>
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
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text11Bold" align="left" >Avsender&nbsp;</td>
							            <td class="text14" align="left" >${recordTopicTvinnSad.thkns}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text14" align="left">TIN-nr&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thtins}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text14" align="left">Navn&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thnas}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text14" align="left">Adresse&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thad1s}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text14" align="left">Postadresse&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thpns}&nbsp;${recordTopicTvinnSad.thpss}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text14" align="left">Land
							            </td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thlks}</td>
							        </tr>
							        
			        	        </table>
					        </td>
					        <td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text11Bold" align="left" >Mottaker&nbsp;</td>
							            <td class="text14" align="left" >${recordTopicTvinnSad.thknk}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text14" align="left">TIN-nr&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thtink}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text14" align="left">Navn&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thnak}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text14" align="left">Adresse&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thad1k}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text14" align="left">Postadresse&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thpnk}&nbsp;${recordTopicTvinnSad.thpsk}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text14" align="left">Land</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thlkk}</td>
							        </tr>
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
							<form action="tvinnsadncts5export_edit_houseconsignment.do" name="createNewItemLine" id="createNewItemLine" method="post" >
								<input type="hidden" name="action" id="action" value='doFetch'>
				 				<input type="hidden" name="avd" id="avd" value='${model.avd	}'>
				 				<input type="hidden" name="sign" id="sign" value='${model.sign}'>
								<input type="hidden" name="opd" id="opd" value='${model.opd}'>
				 				<input type="hidden" name="mrnNr" id="mrnNr" value='${model.mrnNr}'>
				 				<input type="hidden" name="status" id="status" value='${model.status}'>
				 				<input type="hidden" name="datum" id="datum" value='${model.datum}'>
				 				
				 				
								<table width="80%" cellspacing="0" border="0" cellpadding="0">
									<tr>
										<td class="text12Bold">
											<c:if test="${model.status == 'M' || empty model.status}">
												<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submitNewLine" id="submitNewLine"  value="<spring:message code="systema.tvinn.sad.ncts.export.houseconsignment.createnew"/>">
											</c:if>
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
		 						
				 				<table id="container tableTable" width="80%" cellspacing="2" align="left" >
								<tr>
								<td class="text12">
										
								<table id="tblHcLines" class="display compact cell-border" width="100%" >
									<thead>
									<tr class="tableHeaderField" height="20" >
									    <th class="text14">&nbsp;Linjenr.&nbsp;</th>
									    <th class="text14">&nbsp;Endre&nbsp;</th>
									    <th class="text14">&nbsp;Ekst.ref.&nbsp;</th>   
					                    <th class="text14" align="right">&nbsp;Bruttovekt&nbsp;</th>
					                    <th class="text14">&nbsp;Avs.land&nbsp;</th>
					                    <th class="text14">&nbsp;Best.land&nbsp;</th>
					                    <th class="text14">&nbsp;Ref.UCR&nbsp;</th>
					                    <c:if test="${model.status == 'M' || empty model.status}">
					                    	<th align="center" class="text14" nowrap>Slett</th>
					                    </c:if> 
					               </tr> 
								   </thead>
								   <body>						               
				 					  <c:forEach items="${model.list}" var="record" varStatus="counter">    
							               <c:choose>           
							                   <c:when test="${counter.count%2==0}">
							                       <tr class="tableRow" height="20" >
							                   </c:when>
							                   <c:otherwise> 
							                       <tr class="tableOddRow" height="20" >
							                   </c:otherwise>
							               </c:choose>
							               <td width="2%" class="text14" align="right">${record.tcli}</td>
							               <td width="2%" class="text14" align="center">
							               		<a style="display: block;" tabindex=-1 id="recordUpdate_${counter.count}_${record.tcli}" href="#" onClick="getItemData(this);">
							               			<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
							               		</a>
							               		
							               </td>
							               <td align="right" class="text14" >&nbsp;${record.tcxext}</td>
							               <td align="right" class="text14" >&nbsp;${record.tcvktb}</td>
							               <td align="center" class="text14" >&nbsp;${record.tcalk}</td>
							               <td align="center" class="text14" >&nbsp;${record.tcblk}</td>
							               <td align="left" class="text14" >&nbsp;${record.tcucr}</td>
										   <c:if test="${model.status == 'M' || empty model.status}">	
								               <td class="text14" align="center" nowrap>
								               	<a id="alinkDeleteLine" onclick="return deleteConfirm()" tabindex=-1 href="tvinnsadncts5export_edit_houseconsignment.do?action=doDelete&sign=${model.sign}&avd=${model.avd}&opd=${model.opd}&lineNr=${record.tcli}">
								               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
								               	</a>	&nbsp;
								               </td>
							               </c:if>
							            </tr>
								        <%-- this param is used ONLY in this JSP 
								        <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" />
								        --%> 
								        <%-- this param is used throughout the Controller --%>
								        <c:set var="numberOfItemLinesInTopic" value="${Xrecord.svln}" scope="request" /> 
								        </c:forEach>
						            </body>
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
	 			<td >
	 				<form action="tvinnsadncts5export_edit_houseconsignment.do"  name="nctsExportEditHouseConsigmentForm" id="nctsExportEditHouseConsigmentForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="tctdn" id="tctdn" value="${model.opd}"/>
				 	<input type="hidden" name="tcavd" id="tcavd" value="${model.avd}"/>
				 	<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
				 	<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
				 	<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
				 	<input type="hidden" name="status" id="status" value="${model.status}"/>
				 	<input type="hidden" name="lineNr" id="lineNr" value="${model.lineNr}">
				 	
				 	
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="80%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White" align="left" >
				 				<b>&nbsp;&nbsp;House consignment&nbsp;</b>
				 				<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit">
				 				  
			 				</td>
		 				</tr>
	 				</table>
					<table width="80%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table width="100%" border="0" cellspacing="0" cellpadding="1">
							 		<tr>
							 			
							            <td class="text14" align="left"><span title="tcxext">&nbsp;Ekst.ref.</span></td>
							            <td class="text14" align="left"><span title="tcvktb">&nbsp;Bruttovekt</span></td>
							            <td class="text14" align="left"><span title="tcalk">&nbsp;Avs.land</span></td>
					            		<td class="text14" align="left"><span title="tcblk">&nbsp;Best.land</span></td>
					            		<td class="text14" align="left"><span title="tcucr">&nbsp;Refnr.UCR</span></td>
					            		<td class="text14" align="left"><span title="tcidr">&nbsp;Lev.id</span></td>
					            		<td class="text14" align="left"><span title="tcrole">&nbsp;Lev.rolle</span></td>
							        </tr>
							        <tr>
						        		<td align="left">
						        			<input type="text" class="inputTextMediumBlue" name="tcxext" id="tcxext" size="20" maxlength="35" value="${model.record.tcxext}">
										</td>
										<td class="text14" align="left">
						            		<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="tcvktb" id="tcvktb" size="13" maxlength="15" value="${model.record.tcvktb}">
							            </td>
										<td align="left">&nbsp;
					            			<select class="selectMediumBlueE2" name="tcalk" id="tcalk">
							            		<option value="">-velg-</option>
						 				  		<c:forEach var="country" items="${model.countryCodeList}" >
		                                	 	<option value="${country.zkod}"<c:if test="${model.record.tcalk == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="tcalkIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
				            			</td> 
				            			<td align="left">&nbsp;
					            			<select class="selectMediumBlueE2" name="tcblk" id="tcblk">
							            		<option value="">-velg-</option>
						 				  		<c:forEach var="country" items="${model.countryCodeList}" >
		                                	 	<option value="${country.zkod}"<c:if test="${model.record.tcblk == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="tcblkIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
				            			</td> 
				            			<td align="left">
						        			<input type="text" class="inputTextMediumBlue" name="tcucr" id="tcucr" size="30" maxlength="35" value="${model.record.tcucr}">
										</td>
										<td align="left">
						        			<input type="text" class="inputTextMediumBlue" name="tcidr" id="tcidr" size="10" maxlength="17" value="${model.record.tcidr}">
										</td>
										<td align="left">
						        			<input type="text" class="inputTextMediumBlue" name="tcrole" id="tcrole" size="3" maxlength="3" value="${model.record.tcrole}">
										</td>
										
							        </tr>
							        
							        <tr height="5"><td class="text" align="left" ></td></tr>
							        
							        <tr>
							        	<td colspan="3">
								        <table width="100%" class="tableBorderWithRoundCornersLightYellow" align="left" border="0" cellspacing="2" cellpadding="0">
								        	<tr>
									            <td colspan="3" class="text12" align="left"><b>Departure Transport Means</b></td>
									        </tr>
											<tr>
									            <td class="text14" align="left"><span title="tctaty">&nbsp;Type.Ident.</span></td>
									            <td class="text14" align="left"><span title="tctaid">&nbsp;Ident.nr</span></td>
									            <td class="text14" align="left"><span title="tctalk">&nbsp;Landkode</span></td>
							            		
									        </tr>
									        
									        <tr>
								        		<td align="left">
								        			<input type="text" class="inputTextMediumBlue" name="tctaty" id="tctaty" size="3" maxlength="2" value="${model.record.tctaty}">
												</td>
												<td class="text14" align="left">
								            		<input type="text" class="inputTextMediumBlue" name="tctaid" id="tctaid" size="13" maxlength="35" value="${model.record.tctaid}">
									            </td>
												<td align="left">&nbsp;
							            			<select class="selectMediumBlueE2" name="tctalk" id="tctalk">
									            		<option value="">-velg-</option>
								 				  		<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.zkod}"<c:if test="${model.record.tctalk == country.zkod}"> selected </c:if> >${country.zkod}</option>
														</c:forEach> 
													</select>
													
						            			</td> 
						            			
									        </tr>
								
								        </table>
							        	</td>
							        	
							      
							        	<td colspan="3">
								        <table width="100%" class="tableBorderWithRoundCornersLightYellow" align="left" border="0" cellspacing="2" cellpadding="0">
								        	<tr>
									            <td colspan="3" class="text12" align="left"><b>Previous Documents</b></td>
									        </tr>
											<tr>
									            <td class="text14" align="left"><span title="tcpdty">&nbsp;Dok.type</span></td>
							            		<td class="text14" align="left"><span title="tcpdrf">&nbsp;Dok.ref</span></td>
							            		<td class="text14" align="left"><span title="tcpdin">&nbsp;Info.</span></td>
									        </tr>
									        
									        <tr>
								        		<td align="left">&nbsp;
							            			<input type="text" class="inputTextMediumBlue" name="tcpdty" id="tcpdty" size="5" maxlength="4" value="${model.record.tcpdty}">
						            			</td> 
						            			<td align="left">
								        			<input type="text" class="inputTextMediumBlue" name="tcpdrf" id="tcpdrf" size="30" maxlength="70" value="${model.record.tcpdrf}">
												</td>
												<td align="left">
								        			<input type="text" class="inputTextMediumBlue" name="tcpdin" id="tcpdin" size="15" maxlength="35" value="${model.record.tcpdin}">
												</td>
												
									        </tr>
								
								        </table>
							        	</td>
									</tr>
									
							        <tr height="5"><td class="text" align="left"></td></tr>
							        <tr>
							        	<td colspan="3">
								        <table width="100%" class="tableBorderWithRoundCornersLightYellow" align="left" border="0" cellspacing="2" cellpadding="0">
								        	<tr>
									            <td colspan="3" class="text12" align="left"><b>Supporting Documents</b></td>
									        </tr>
											<tr>
									            <td class="text14" align="left"><span title="tcsdty">&nbsp;Type.Ident.</span></td>
									            <td class="text14" align="left"><span title="tcsdrf">&nbsp;Ident.nr</span></td>
									            <td class="text14" align="left"><span title="tcsdln">&nbsp;Line ItemNr</span></td>
							            		<td class="text14" align="left"><span title="tcsdin">&nbsp;Info.</span></td>
									        </tr>
									        
									        <tr>
								        		<td align="left">&nbsp;
							            			<input type="text" class="inputTextMediumBlue" name="tcsdty" id="tcsdty" size="5" maxlength="4" value="${model.record.tcsdty}">
						            			</td> 
						            			<td align="left">
								        			<input type="text" class="inputTextMediumBlue" name="tcsdrf" id="tcsdrf" size="30" maxlength="70" value="${model.record.tcsdrf}">
												</td>
												<td align="left">
								        			<input type="text" class="inputTextMediumBlue" name="tcsdln" id="tcsdln" size="6" maxlength="5" value="${model.record.tcsdln}">
												</td>
						            			<td align="left">
								        			<input type="text" class="inputTextMediumBlue" name="tcsdin" id="tcsdin" size="15" maxlength="35" value="${model.record.tcsdin}">
												</td>
									        </tr>
								
								        </table>
							        	</td>
							        	
							      
							        	<td colspan="3">
								        <table width="100%" class="tableBorderWithRoundCornersLightYellow" align="left" border="0" cellspacing="2" cellpadding="0">
								        	<tr>
									            <td colspan="3" class="text12" align="left"><b>Transport Documents</b></td>
									        </tr>
											<tr>
									            <td class="text14" align="left"><span title="tctdty">&nbsp;Dok.type</span></td>
							            		<td class="text14" align="left"><span title="tctdrf">&nbsp;Dok.ref</span></td>
							            		<td class="text14" align="left">&nbsp;</td>
									        </tr>
									        
									        <tr>
								        		<td align="left">&nbsp;
							            			<input type="text" class="inputTextMediumBlue" name="tctdty" id="tctdty" size="5" maxlength="4" value="${model.record.tctdty}">
						            			</td> 
						            			<td align="left">
								        			<input type="text" class="inputTextMediumBlue" name="tctdrf" id="tctdrf" size="30" maxlength="70" value="${model.record.tctdrf}">
												</td>
												<td align="left">
								        			&nbsp;
												</td>
												
									        </tr>
								
								        </table>
							        	</td>
							        	
									</tr>
									
									<tr height="5"><td class="text" align="left"></td></tr>
							        <tr>
							        	<td colspan="3">
								        <table width="100%" class="tableBorderWithRoundCornersLightYellow" align="left" border="0" cellspacing="2" cellpadding="0">
								        	<tr>
									            <td colspan="3" class="text12" align="left"><b>Additional References</b></td>
									        </tr>
											<tr>
									            <td class="text14" align="left"><span title="tcadty">&nbsp;Type</span></td>
									            <td class="text14" align="left"><span title="tcadrf">&nbsp;Ref.nr</span></td>
									            
									        
									        <tr>
								        		<td align="left">&nbsp;
							            			<input type="text" class="inputTextMediumBlue" name="tcadty" id="tcadty" size="5" maxlength="4" value="${model.record.tcadty}">
						            			</td> 
						            			<td align="left">
								        			<input type="text" class="inputTextMediumBlue" name="tcadrf" id="tcadrf" size="30" maxlength="70" value="${model.record.tcadrf}">
												</td>
												
									        </tr>
								
								        </table>
							        	</td>
							        	
							      
							        	<td colspan="3">
								        <table width="100%" class="tableBorderWithRoundCornersLightYellow" align="left" border="0" cellspacing="2" cellpadding="0">
								        	<tr>
									            <td colspan="3" class="text12" align="left"><b>Aditional Information</b></td>
									        </tr>
											<tr>
									            <td class="text14" align="left"><span title="tcaicd">&nbsp;Dok.type</span></td>
							            		<td class="text14" align="left"><span title="tctdrf">&nbsp;Dok.ref</span></td>
							            		<td class="text14" align="left">&nbsp;</td>
									        </tr>
									        
									        <tr>
								        		<td align="left">&nbsp;
							            			<input type="text" class="inputTextMediumBlue" name="tcaicd" id="tcaicd" size="6" maxlength="5" value="${model.record.tcaicd}">
						            			</td> 
						            			<td align="left">
								        			<input type="text" class="inputTextMediumBlue" name="tcaitx" id="tcaitx" size="30" maxlength="120" value="${model.record.tcaitx}">
												</td>
												<td align="left">
								        			&nbsp;
												</td>
												
									        </tr>
								
								        </table>
							        	</td>
							        	
									</tr>

							        <tr height="20"><td class="text" align="left"></td></tr>
							        
							        <tr>
							        	<td colspan="3">
							        	<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
									 		<tr height="18px">
									 			<td class="text14White">
									 				&nbsp;
							 						&nbsp;Avsender&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
								 				</td>
							 				</tr>
					 					</table>
								        <table width="100%" class="tableBorderWithRoundCorners" style="background-color:#F2F5F0;" align="left" border="0" cellspacing="2" cellpadding="0">
									        <tr >
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tckns">Kundenummer</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tcnas">Navn</span>
									            	<a tabindex="-1" id="tcnasIdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
													</a>
							            		</td>
									        </tr>
									        <tr>
									        		<td align="left"><input type="text" class="inputTextMediumBlue" name="tckns" id="tckns" size="10" maxlength="8" value="${model.record.tckns}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="tcnas" id="tcnas" size="30" maxlength="35" value="${model.record.tcnas}"></td>
									        </tr>
									        <tr>
							        				<td class="text14" align="left" >&nbsp;&nbsp;<span title="tctins">TIN</span></td>
							        				<td class="text14" align="left" >&nbsp;&nbsp;<span title="tcad1s">Adresse</span></td>
									  
									        </tr>
									        <tr>
									        		<td align="left"><input type="text" class="inputTextMediumBlue" name="tctins" id="tctins" size="20" maxlength="17" value="${model.record.tctins}"></td>
									        		<td align="left"><input type="text" class="inputTextMediumBlue" name="tcad1s" id="tcad1s" size="30" maxlength="30" value="${model.record.tcad1s}"></td>
									            
									        </tr>
		
									        <tr height="4"><td>&nbsp;</td></tr>
									        <tr>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tcpns">Postnr</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tcpss">Sted</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tclks">Land</span></td>
									            
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="tcpns" id="tcpns" size="10" maxlength="9" value="${model.record.tcpns}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="tcpss" id="tcpss" size="20" maxlength="24" value="${model.record.tcpss}"></td>
									            
									            <td class="text14" align="left" >
									            		&nbsp;<select class="selectMediumBlueE2" name="tclks" id="tclks">
										            		<option value="">-velg-</option>
										 				  	<c:forEach var="country" items="${model.countryCodeList}" >
						                                	 	<option value="${country.zkod}"<c:if test="${model.record.tclks == country.zkod}"> selected </c:if> >${country.zkod}</option>
															</c:forEach>  
													</select>
													
												</td>
									        </tr>
									        
									        <tr>
									        	<td class="text14" align="left" >&nbsp;&nbsp;<span title="tccps">Kontaktperson</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tctls">Telefon</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tcems">Email</span></td>
									            
									        </tr>
									        <tr>
									         	<td align="left"><input type="text" class="inputTextMediumBlue" name="tccps" id="tccps" size="15" maxlength="30" value="${model.record.tccps}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="tctls" id="tctls" size="15" maxlength="20" value="${model.record.tctls}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="tcems" id="tcems" size="20" maxlength="50" value="${model.record.tcems}"></td>
									           
									        </tr>
							        	</table>
							        	</td>
							        	
							        
							        	<td colspan="3">
							        	<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
									 		<tr height="18px">
									 			<td class="text14White">
									 				&nbsp;
							 						&nbsp;Mottaker&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
								 				</td>
							 				</tr>
					 					</table>
								        <table width="100%" class="tableBorderWithRoundCorners" style="background-color:#F2F5F0;" align="left" border="0" cellspacing="2" cellpadding="0">
									        <tr >
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tcknk">Kundenummer</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tcnak">Navn</span>
									            	<a tabindex="-1" id="thnassIdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
													</a>
							            		</td>
									        </tr>
									        <tr>
									        		<td align="left"><input type="text" class="inputTextMediumBlue" name="tcknk" id="tcknk" size="10" maxlength="8" value="${model.record.tcknk}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="tcnak" id="tcnak" size="30" maxlength="35" value="${model.record.tcnak}"></td>
									        </tr>
									        <tr>
							        				<td class="text14" align="left" >&nbsp;&nbsp;<span title="tctink">TIN</span></td>
							        				<td class="text14" align="left" >&nbsp;&nbsp;<span title="tcad1k">Adresse</span></td>
									        </tr>
									        <tr>
									        		<td align="left"><input type="text" class="inputTextMediumBlue" name="tctink" id="tctink" size="20" maxlength="17" value="${model.record.tctink}"></td>
									        		<td align="left"><input type="text" class="inputTextMediumBlue" name="tcad1k" id="tcad1k" size="30" maxlength="30" value="${model.record.tcad1k}"></td>
									        </tr>
		
									        <tr height="4"><td>&nbsp;</td></tr>
									        <tr>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tcpnk">Postnr</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tcpsk">Sted</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tclkk">Land</span></td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="tcpnk" id="tcpnk" size="10" maxlength="9" value="${model.record.tcpnk}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="tcpsk" id="tcpsk" size="20" maxlength="24" value="${model.record.tcpsk}"></td>
									            
									            <td class="text14" align="left" >
									            		&nbsp;<select class="selectMediumBlueE2" name="tclkk" id="tclkk">
										            		<option value="">-velg-</option>
										 				  	<c:forEach var="country" items="${model.countryCodeList}" >
						                                	 	<option value="${country.zkod}"<c:if test="${model.record.tclkk == country.zkod}"> selected </c:if> >${country.zkod}</option>
															</c:forEach>  
													</select>
													<a tabindex="-1" id="thskssIdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
												</td>
									        </tr>
									        
									        <tr>
									        	<td class="text14" align="left" >&nbsp;&nbsp;<span title="todo">Kontaktperson</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="todo">Telefon</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="todo">Email</span></td>
									            
									        </tr>
									        <tr>
									         	<td align="left"><input type="text" readonly class="inputTextReadOnly" name="todo" id="todo" size="15" maxlength="30" value="${Xmodel.record.tccps}"></td>
									            <td align="left"><input type="text" readonly class="inputTextReadOnly" name="todo" id="todo" size="15" maxlength="20" value="${Xmodel.record.tctls}"></td>
									            <td align="left"><input type="text" readonly class="inputTextReadOnly" name="todo" id="todo" size="20" maxlength="50" value="${Xmodel.record.tcems}"></td>
									           
									        </tr>
							        	</table>
							        	</td>
							        	
							        	<td align="left" valign="bottom" >
											<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.tvinn.sad.ncts.export.houseconsignment.createnew.submit"/>'>
											&nbsp;&nbsp;
										</td>
							        </tr>
							        <tr height="10"><td class="text" align="left"></td></tr>
						        </table>
					        </td>
					        
				        </tr>
					    <tr height="2"><td colspan="2" ></td></tr>
					    <tr>	
						    							        	
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

