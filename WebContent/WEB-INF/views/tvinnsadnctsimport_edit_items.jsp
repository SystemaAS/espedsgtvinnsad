<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadnctsimport_edit_items.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport.do?action=doFind&avd=${model.avd}&sign=${model.sign}&opd=${model.opd}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.tab"/></font>
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
						&sysg=${model.sign}&syst=${model.status}&sydt=${model.datum}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.created.mastertopic.tab"/></font>
					<font class="text12MediumBlue">[${model.opd}]</font>
					<c:if test="${ model.status == 'F' || model.status == 'M' || empty model.status}">
						<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.item.createnew.tab"/></font>
				<c:if test="${model.status == 'F' || model.status == 'M' || empty model.status}">
					<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
				</c:if>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_unloading_edit.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}
											&mrnNr=${model.mrnNr}&godsNr=${model.godsNr}&status=${model.status}&datum=${model.datum}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.unloading.createnew.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/unloading.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_unloading_edit_items.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
									&opd=${model.opd}&mrnNr=${model.mrnNr}&godsNr=${model.godsNr}
									&status=${model.status}&datum=${model.datum}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.unloading.item.createnew.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="item lines">
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_logging.do?avd=${model.avd}&sign=${model.sign}
									&opd=${model.opd}&mrnNr=${model.mrnNr}&godsNr=${model.godsNr}
									&status=${model.status}&datum=${model.datum}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_archive.do?avd=${model.avd}&sign=${model.sign}
									&opd=${model.opd}&mrnNr=${model.mrnNr}&godsNr=${model.godsNr}
									&status=${model.status}&datum=${model.datum}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.archive.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
				</a>
			</td>
			<td width="13%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
		 	
		</tr>
	</table>
	<%-- -------------------------------- --%>	
 	<%-- tab area container MASTER TOPIC  --%>
	<%-- -------------------------------- --%>
 	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
		<tr height="15"><td colspan="2">&nbsp;</td></tr>	
		
		<tr>
		<td >
		<table border="0" width="95%" align="center" border="0">
			<tr>
	 			<td >		
	 				<%-- MASTER Topic header --%>
	 				<table width="100%" align="center" class="formFrameHeaderTransparent" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text11MediumBlue">
				 				&nbsp;Avd&nbsp;<b>${recordTopicTvinnSad.tiavd}</b>
				 				&nbsp;Oppdrag&nbsp;<b>${recordTopicTvinnSad.titdn}</b>
				 				&nbsp;Sign&nbsp;<b>${recordTopicTvinnSad.tisg}</b>
				 				&nbsp;&nbsp;
				 				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
           						Status:&nbsp;<b>${recordTopicTvinnSad.tist}</b>
								&nbsp;&nbsp;Type av prosedyre: 
           						<c:if test="${recordTopicTvinnSad.tienkl == 'J'}"><b>Forenklet</b></c:if>
           						<c:if test="${recordTopicTvinnSad.tienkl == 'N'}"><b>Normal</b></c:if>           						 
				 				&nbsp;&nbsp;&nbsp;MRN-nr:&nbsp;<b>${recordTopicTvinnSad.titrnr}</b>
				 				&nbsp;&nbsp;&nbsp;Gods-nr:&nbsp;<b>${recordTopicTvinnSad.tign}</b>
				 				
							<div class="text11" style="position: relative;" align="left">
							<span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
				           		Kun status <b>M</b>, <b>(Fejl)</b> eller <b>' '</b> kan redigeres.
			           			<ul>
					            <li><b>' '</b>&nbsp;(Blank) Åpen for endring.</li>
		                        <li><b>E</b>&nbsp;Noen arbeider med (Endrer) den aktuelle TET nå. Hvis man arbeider med en
											deklarasjon og strømbrudd eller lignende inntreffer vil deklarasjonen bli
											"hengende" i status 'E'.
		                                   	Man må i slike tilfeller endre status på deklarasjonen (MENU TET, punkt 22.) fra
											'E' til ' ' manuelt.</li>
		                        <li><b>Q</b>&nbsp;Intern postkasse" (Venter på at program skal lage en sending av denne og andre)</li>
		                        <li><b>A</b>&nbsp;Ligger i sendekø (ER lagt i sendings-fil) for sending til TAD</li>
		                        <li><b>B</b>&nbsp;Kommet til IBM, men ennå ikke til TAD (kun ved nettverk IGN)</li>
		                        <li><b>C</b>&nbsp;Kommet til TAD (egentlig "hentet TIL TAD's postkasse)</li>
		                        <li><b>F</b>&nbsp;IE44 er sendt (egentlig "hentet TIL TAD's postkasse)</li>
		                        <li><b>M</b>&nbsp;Avvisning av ankomstmelding (har mottatt IE08)</li>
		                        <li><b>N</b>&nbsp;Avvisning av lossemerknader (har mottatt IE58)</li>
		                        <li><b>U</b>&nbsp;Har fått lossetillatelse (IE43)</li>
		                        <li><b>P</b>&nbsp;Transittering er avsluttet (Har mottat IE25)</li>
		                        <li><b>K</b>&nbsp;Venter på PRINT (midlertidig kortvarig status)</li>
		                        <li><b>+</b>&nbsp;Midlertidig arbeidsstatus. Alle som hadde Q på et gitt tidspunkt (=klar for sending)</li>
		                        <li><b>Å</b>&nbsp;Midlertidig arbeidstatus. (Mellom mottak av melding og printing av feilmelding)</li>
			           			</ul>
							</span>	
							</div>
							</td>                
		 				</tr>
		 				<tr height="15"><td></td></tr>
	 				</table>
	 				
					<%-- MASTER Topic information [it is passed through a session object: recordTopic] --%>
				 	<table height="40" width="100%" align="center" class="formFrameTitaniumWhite" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="2"><td class="text" align="left" colspan="2"></td></tr>
				 		<tr>
					 		<td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text11Bold" align="left" >Ansvarlig&nbsp;</td>
							            <td class="text11" align="left" ></td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left" >Kundenr&nbsp;</td>
							            <td class="text11MediumBlue" align="left" >${recordTopicTvinnSad.tikn}</td>
							        </tr>
							        
							        <tr>
							            <td width="30%" class="text11" align="left">TIN-nr&nbsp;</td>
							           	<td class="text11MediumBlue" align="left"><b>${recordTopicTvinnSad.titin}</b></td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Navn&nbsp;</td>
							           	<td class="text11MediumBlue" align="left"><b>${recordTopicTvinnSad.tina}</b></td>
							        </tr>
								</table>
					        </td>
							<td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td class="text11" align="left" >&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Adresse&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.tiad1}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Postadresse&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.tipn}&nbsp;${recordTopicTvinnSad.tips}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Landkode
							            </td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.tilk}</td>
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
							<form id="createNewItemLine" action="tvinnsadnctsimport_edit_items.do">
								<input type="hidden" name="action" id="action" value="doFetch">
				 				<input type="hidden" name="avd" id="avd" value="${model.avd	}">
				 				<input type="hidden" name="sign" id="sign" value="${model.sign}">
								<input type="hidden" name="opd" id="opd" value="${model.opd}">
				 				<input type="hidden" name="mrnNr" id="mrnNr" value="${model.mrnNr}">
				 				<input type="hidden" name="godsNr" id="godsNr" value="${model.godsNr}">
				 				<input type="hidden" name="status" id="status" value="${model.status}">
				 				<input type="hidden" name="datum" id="datum" value="${model.datum}">
				 					
				 					
							<td class="text12Bold">&nbsp;Hendelser&nbsp;&nbsp;
								<c:if test="${model.status == 'F' || model.status == 'M' || empty model.status}">
									<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submit" value='Lage ny'>
								</c:if>
								<button name="allItemsButton" class="inputFormSubmitStd" type="button" onClick="showPop('allItems');" >Vis alle</button> 
								        <span style="background-color:#EEEEEE; position:absolute; left:50px; top:200px; width:1200px; height:1000px;" id="allItems" class="popupWithInputTextThickBorder"  >
							           		<div class="ownScrollableSubWindow" style="width:1080px; height:900px; margin:10px;">
							           			<nav>
							           			<table width="95%" border="0" align="left" cellspacing="2">
							           			<tr>
								           			<td colspan="3" class="text14"><b>Hendelser</b></td>
								           		</tr>
									           	<tr>	
													<td >
													<table width="95%" cellspacing="0" border="0" cellpadding="0">
														<tr class="tableHeaderField" height="20" valign="left">
														    <td class="tableHeaderFieldFirst">&nbsp;Linjenr.&nbsp;</td>
														    <c:if test="${model.status == 'F' || model.status == 'M' || empty model.status}">
										                    	<td align="center" class="tableHeaderField">Slett</td>
										                    </c:if> 
														    <td class="tableHeaderField">&nbsp;Sted&nbsp;</td> 
														    <td class="tableHeaderField">&nbsp;Hendelse&nbsp;</td>   
										                    <td class="tableHeaderField">&nbsp;Omlasting&nbsp;</td>
										               	</tr> 
											           <c:forEach items="${model.list}" var="record" varStatus="counter">    
											               <c:choose>           
											                   <c:when test="${counter.count%2==0}">
											                       <tr class="tableRow" height="20" >
											                   </c:when>
											                   <c:otherwise>   
											                       <tr class="tableOddRow" height="20" >
											                   </c:otherwise>
											               </c:choose>
											               <td width="2%" class="tableCellFirst" align="center">&nbsp;${record.tvli}</td>
											               <c:if test="${model.status == 'F' || model.status == 'M' || empty model.status}">	
												               <td class="tableCell" align="center" nowrap>&nbsp;
												               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadnctsimport_edit_items.do?action=doDelete&sign=${model.sign}&avd=${record.tvavd}&opd=${record.tvtdn}&lin=${record.tvli}">
												               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
												               	</a>	
												               </td>
											               </c:if>
											               <td class="tableCell" >&nbsp;${record.tvst}</td>
											               <td class="tableCell" >
											               		&nbsp;${record.tvinf1}
											               		<c:if test="${not empty record.tvinf2}">
											               			&nbsp;<b>...</b>
											               		</c:if>
											               </td>
											               <td class="tableCell" >&nbsp;${record.tvtaid}</td>
											                
												           </tr>
											        		   <%-- <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" /> --%>
													       <c:set var="numberOfItemLinesInTopic" value="${record.tvli}" scope="request" />
												         
											            </c:forEach>
											        </table>
													</td>											           		
										         </tr>
										         <tr>
							           				<td>
							           				<table >
													<%-- OK BUTTON --%>
							           				<tr align="left" >
														<td class="text11"><button name="allItemsButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('allItems');">&nbsp;<spring:message code="systema.tvinn.sad.ok"/></button> 
														</td>
													</tr>
													</table>
													</td>
												</tr>
										           		
								   			</table>
								   			</nav>
								   			</div>
						   				</span>	
								</td>
							</form>
						</tr> 
						<tr>
							<td class="ownScrollableSubWindow" style="width:1050px; height:10em;">
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr class="tableHeaderField" height="20" valign="left">
									    <td class="tableHeaderFieldFirst">&nbsp;Linjenr.&nbsp;</td>
									    <c:if test="${model.status == 'F' || model.status == 'M' || empty model.status}">
					                    		<td align="center" class="tableHeaderField" nowrap>Slett</td>
					                    </c:if> 
									    <td class="tableHeaderField">&nbsp;Sted&nbsp;</td> 
									    <td class="tableHeaderField">&nbsp;Hendelse&nbsp;</td>   
					                    <td class="tableHeaderField">&nbsp;Omlasting&nbsp;</td>
					               </tr> 
					               
					               <form name="formItemList" id="formItemList" method="POST" >
					               		<input type="hidden" name="opdItemList" id="opdItemList" value="${model.opd}">
				 						<input type="hidden" name="avdItemList" id="avdItemList" value="${model.avd}"> 
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
							               <%-- <td class="tableCellFirst" width="2%">&nbsp;${counter.count}</td> --%>
							               <td width="2%" class="tableCellFirst" align="center">&nbsp;
						               			<a tabindex=-1 id="recordUpdate_${counter.count}_${record.tvli}" href="#" onClick="getItemData(this);">${record.tvli}
					               					<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;
							               		</a>
							               </td>
							               <c:if test="${model.status == 'F' || model.status == 'M' || empty model.status}">	
								               <td class="tableCell" align="center" nowrap>&nbsp;
								               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadnctsimport_edit_items.do?action=doDelete&sign=${model.sign}&avd=${record.tvavd}&opd=${record.tvtdn}&lin=${record.tvli}">
								               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
								               	</a>	
								               </td>
							               </c:if>
							               <td class="tableCell" >&nbsp;${record.tvst}</td>
							               <td class="tableCell" >
							               		&nbsp;${record.tvinf1}
							               		<c:if test="${not empty record.tvinf2}">
							               			&nbsp;<b>...</b>
							               		</c:if>
							               </td>
							               <td class="tableCell" >&nbsp;${record.tvtaid}</td>
								           </tr>
							        		   <%-- <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" /> --%>
									       <c:set var="numberOfItemLinesInTopic" value="${record.tvli}" scope="request" />
								         
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
	 			<form name="nctsImportEditTopicItemForm" id="nctsImportEditTopicItemForm" action="tvinnsadnctsimport_edit_items.do" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value="doUpdate"/>
				 	<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
				 	<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
				 	<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
				 	<input type="hidden" name="mrnNr" id="mrnNr" value="${model.mrnNr}">
 					<input type="hidden" name="godsNr" id="godsNr" value="${model.godsNr}">
				 	<input type="hidden" name="status" id="status" value="${model.status}"/>
				 	<input type="hidden" name="datum" id="datum" value="${model.datum}"/>
				 	
				 	<%-- Incident flag should always be 1 (PAC+3+(value=1)) --%>
				 	<input type="hidden" name="tvflg" id="tvflg" value="1"/>
				 	
				 	<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
				 	<input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" />
				 	
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="100%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
				 				<b>&nbsp;&nbsp;Beskrivelse (Hendelse)&nbsp;</b>
				 				<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 				<input tabindex=-1 align="center" class="text12BoldLightGreenForItemLinenr" readonly type="text" name="lineNr" id="lineNr" size="3" value="">
								
			 				</td>
		 				</tr>
	 				</table>
					<table width="100%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="10"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td colspan="3">
						 		<table class="tableBorderWithRoundCornersGray" width="80%" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
							 			<td class="text12" align="left">
							            		&nbsp;&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="tvst" >Sted</span>
							            </td>
							            <td class="text12" align="left" valign="bottom">
										<font class="text16RedBold" >*</font><span title="tvstlk" >Landkode</span>
						 					
										</td>
										<td class="text12" align="left" valign="bottom">
											&nbsp;<span title="tvstsk" >Språkkode</span>
											
										</td>
										<td class="text12" align="left" valign="bottom"><span title="tvctl" >Kontrol</span>
											<img onMouseOver="showPop('kontroll_info');" onMouseOut="hidePop('kontroll_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							                <div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="kontroll_info" class="popupWithInputText text11"  >
								           			<b>Nej</b>&nbsp;(0) = Hendelse er ikke registreret i.
								           			<br/>
           											<b>Ja</b>&nbsp;(1) = Hendelse er allerede registreret i NCTS [fra en transiteringssted].
											</span>	
											</div>
											
										</td>
							        </tr>
							        <tr>
						        		<td class="text12" align="left" >
						        			&nbsp;&nbsp;<input type="text" class="inputTextMediumBlueMandatoryField" name="tvst" id="tvst" size="35" maxlength="35" value="${model.record.tvst}">
		 			            		</td>
		 			            		<td>
							 				<select class="inputTextMediumBlueMandatoryField" name="tvstlk" id="tvstlk">
								            		<option value="">-velg-</option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
			                                	 	<option value="${country.zkod}"<c:if test="${model.record.tvstlk == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="tvstlkIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
						 				</td>
										<td >
											<select name="tvstsk" id="tvstsk">
							            			<option value="">-velg-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvstsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
											<a tabindex="-1" id="tvstskIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
										</td>
										<td >
											<select name="tvctl" id="tvctl">
							            			<option value="">-velg-</option>
						 					  	<option value="1"<c:if test="${model.record.tvctl == '1'}"> selected </c:if> >Ja</option>
											  	<option value="0"<c:if test="${model.record.tvctl == '0'}"> selected </c:if> >Nej</option>
											</select>
										</td>
							        </tr>
 							        <tr height="5"><td></td></tr>
			        	        </table>
					        </td>
				        </tr>
				        <tr height="5"><td></td></tr>
				 		<tr>
					 		<td valign="top">
						 		<table width="95%" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
							 			<td class="text12" align="left" valign="top" >
							 				&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="tvinf1-4" ><b>Hendelser</b></span>
							 			</td>
							 		</tr>
							 		<tr>	
							 			<td class="text12" align="left">
								            &nbsp;<textarea class="inputTextMediumBlueMandatoryField" name="tvinf" id="tvinf" cols="50" rows="7" maxlength="279">${model.record.tvinf}</textarea>
							            </td>
						            </tr>
			        	        		</table>
					        </td>
					        <td valign="top">
						 		<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
						 			<tr>
							 			<td class="text12" align="left" valign="top" >
							 				&nbsp;
							 			</td>
							 		</tr>
							 		<tr>
							 			<td class="text12" align="left" valign="bottom">
							 				&nbsp;<span title="tvgdt" >Godkj.dato</span>
							 			</td>
							 			<td class="text12" align="left" valign="bottom">
							 				&nbsp;<span title="tvgm" >Myndighet</span>
							 			</td>
							 			<td class="text12" align="left" valign="bottom">
											&nbsp;<span title="tvgmsk" >Språkkode</span>
											
										</td>
									</tr>
									<tr>	
							 			<td class="text12" align="left">
								            <input type="text" class="inputText" name="tvgdt" id="tvgdt" size="9" maxlength="8" value="${model.record.tvgdt}">
							            </td>
							            <td class="text12" align="left">
								            <input type="text" class="inputText" name="tvgm" id="tvgm" size="35" maxlength="35" value="${model.record.tvgm}">
							            </td>
							            <td >
											<select name="tvgmsk" id="tvgmsk">
							            			<option value="">-velg-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvgmsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
											<a tabindex="-1" id="tvgmskIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
										</td>
						            </tr>
						            <tr>
							            <td class="text12" align="left" valign="bottom">
											&nbsp;<span title="tvgmlk" >Godkj.landkode</span>
							 					
										</td>
										<td class="text12" align="left" valign="bottom">
							 				&nbsp;<span title="tvgmst" >Godkj.sted</span>
							 			</td>
							 			<td class="text12" align="left" valign="bottom">
											&nbsp;<span title="tvgmss" >Språkkode</span>
											
										</td>
									</tr>
									<tr>
										<td>
							 				<select name="tvgmlk" id="tvgmlk">
								            		<option value="">-velg-</option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
			                                	 		<option value="${country.zkod}"<c:if test="${model.record.tvgmlk == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="tvgmlkIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
						 				</td>
										<td class="text12" align="left">
								            <input type="text" class="inputText" name="tvgmst" id="tvgmst" size="35" maxlength="35" value="${model.record.tvgmst}">
							            </td>
							            <td >
											<select name="tvgmss" id="tvgmss">
							            			<option value="">-velg-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvgmss == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
											<a tabindex="-1" id="tvgmssIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
										</td>	
									</tr>
			        	        		</table>
					        </td>
				        </tr>
				        <tr height="10"><td></td></tr>
				        <tr><td class="text12"><b>&nbsp;&nbsp;Forsegling</b></td></tr>
				        <tr>
				        		<td >
					        		<table width="80%" align="left" border="0" cellspacing="0" cellpadding="0">
					        			<tr>
							 			<td class="text12" align="left" valign="bottom">
							 				&nbsp;&nbsp;<span title="tvdant" >Antal</span>
							 			</td>
							 			<td class="text12" align="left" valign="bottom">
							 				&nbsp;&nbsp;<span title="tvdfkd" >Kode</span>
							 			</td>
							 			<td class="text12" align="left" valign="bottom">
											&nbsp;<span title="tvdfsk" >Språkkode</span>
											
										</td>
									</tr>
						        		<tr>
										<td class="text12" align="left">
								            &nbsp;<input type="text" class="inputText" name="tvdant" id="tvdant" size="3" maxlength="3" value="${model.record.tvdant}">
							            </td>
										<td class="text12" align="left">
								            <input type="text" class="inputText" name="tvdfkd" id="tvdfkd" size="20" maxlength="20" value="${model.record.tvdfkd}">
							            </td>
							            <td >
											<select name="tvdfsk" id="tvdfsk">
							            			<option value="">-velg-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdfsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
											<a tabindex="-1" id="tvdfskIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
										</td>
									</tr>
								</table>
							</td>	
							
		 				    	<%-- only status = M,N or emtpy status is allowed  --%>
		 				    <c:choose>
			 				    <c:when test="${ model.status == 'F' || model.status == 'M' || empty model.status}">
				 				    <td class="text9BlueGreen" valign="bottom"  >
					 				    	<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='tvinnsadnctsimport_edit_items.do';" value="<spring:message code="systema.tvinn.sad.ncts.import.item.createnew.submit"/>"/>
					 				</td>    	
			 				    </c:when>
			 				    <c:otherwise>
			 				    <td colspan="2" class="text9BlueGreen" valign="bottom"  >
			 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value="<spring:message code="systema.tvinn.sad.submit.not.editable"/>"/>
			 				    	</td>	
			 				    </c:otherwise>	
		 				    </c:choose>
							
						</tr>
						<%-- separators 
						<tr height="8"><td></td></tr>
				        <tr><td class="text" colspan="9"><hr></td></tr>
				        <tr height="8"><td></td></tr>
				        --%>
				        <tr height="25"><td></td></tr>
				        
				        <tr>
					        <td colspan="3">
							<table width="99%" align="center" class="formFrameHeaderBlueWithBorder" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="15">
						 			<td class="text12White">
						 				&nbsp;&nbsp;<b>Omlasting</b>&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					 				</td>
				 				</tr>
			 				</table>				        
					        </td>
				        </tr>
				 		<tr>
					 		<td colspan="3" >
					 			<table align="center" width="99%" class="secondarySectionFrame"  border="0" cellspacing="0" cellpadding="0">
						 		<tr>
							 		<td valign="top" width="50%">
								 		<table width="85%" border="0" cellspacing="1" cellpadding="0">
							 			<tr>
								 			<td class="text12" align="left" valign="bottom">
								 				&nbsp;&nbsp;<span title="tvtaid" >Transport</span>
								 			</td>
								 			<td class="text12" align="left" valign="bottom">
								 				&nbsp;<span title="tvtalk" >Landkode</span>
								 				
								 			</td>
								 			<td class="text12" align="left" valign="bottom">
												&nbsp;<span title="tvtask" >Språkkode</span>
												
											</td>
										</tr>
										<tr>	
								 			<td class="text12" align="left">
									            &nbsp;<input type="text" class="inputText" name="tvtaid" id="tvtaid" size="25" maxlength="27" value="${model.record.tvtaid}">
								            </td>
											<td>
								 				<select name="tvtalk" id="tvtalk">
									            		<option value="">-velg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.zkod}"<c:if test="${model.record.tvtalk == country.zkod}"> selected </c:if> >${country.zkod}</option>
													</c:forEach> 
												</select>
												<a tabindex="-1" id="tvtalkIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
							 				</td>
								            <td >
												<select name="tvtask" id="tvtask">
								            			<option value="">-velg-</option>
							 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvtask == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
													</c:forEach>
												</select>
												<a tabindex="-1" id="tvtaskIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
											</td>
							            </tr>
							 			</table>
						 			</td>
						 			<td valign="top" width="50%">
							 			<table width="100%" border="0" cellspacing="1" cellpadding="0"> 
							 			<tr>
								 			<td class="text12" align="left" valign="bottom">
								 				&nbsp;<span title="tvgodt" >Godkj.dato</span>
								 			</td>
								 			<td class="text12" align="left" valign="bottom">
								 				&nbsp;<span title="tvom" >Myndighet</span>
								 			</td>
								 			<td class="text12" align="left" valign="bottom">
												&nbsp;<span title="tvomsk" >Språkkode</span>
												
											</td>
										</tr>
										<tr>	
								 			<td class="text12" align="left">
									            <input type="text" class="inputText" name="tvgodt" id="tvgodt" size="9" maxlength="8" value="${model.record.tvgodt}">
								            </td>
								            <td class="text12" align="left">
									            <input type="text" class="inputText" name="tvom" id="tvom" size="35" maxlength="35" value="${model.record.tvom}">
								            </td>
								            <td >
												<select name="tvomsk" id="tvomsk">
								            			<option value="">-velg-</option>
							 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvomsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
													</c:forEach>
												</select>
												<a tabindex="-1" id="tvomskIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
											</td>
							            </tr>
							            <tr>
								            <td class="text12" align="left" valign="bottom">
												&nbsp;<span title="tvomlk" >Godkj.landkode</span>
							 															
											</td>
											<td class="text12" align="left" valign="bottom">
								 				&nbsp;<span title="tvomst" >Godkj.sted</span>
								 			</td>
								 			<td class="text12" align="left" valign="bottom">
												&nbsp;<span title="tvomss" >Språkkode</span>
												
											</td>
										</tr>
										<tr>
											<td>
								 				<select name="tvomlk" id="tvomlk">
									            		<option value="">-velg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.zkod}"<c:if test="${model.record.tvomlk == country.zkod}"> selected </c:if> >${country.zkod}</option>
													</c:forEach> 
												</select>
												<a tabindex="-1" id="tvomlkIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
							 				</td>
											<td class="text12" align="left">
									            <input type="text" class="inputText" name="tvomst" id="tvomst" size="35" maxlength="35" value="${model.record.tvomst}">
								            </td>
								            <td >
												<select name="tvomss" id="tvomss">
								            			<option value="">-velg-</option>
							 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvomss == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
													</c:forEach>
												</select>
												<a tabindex="-1" id="tvomssIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
											</td>	
										</tr>
										<tr height="10"><td>&nbsp;</td></tr>
										<tr>
											<td class="text12" align="right">
								 				<span title="tvcnr">Container&nbsp;&nbsp;</span>
							 				</td>
											<td colspan="4" class="text12" align="left">
									            <input type="text" class="inputText" name="tvcnr" id="tvcnr" size="20" maxlength="17" value="${model.record.tvcnr}">
								            		&nbsp;<button name="containerNrButton" class="buttonGray" type="button" onClick="showPop('containerNrInfo');" >Mer...</button> 
							           		<span style="position:absolute; left:480px; top:650px; width:680px; height:120px;" id="containerNrInfo" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
									           			<tr>
										           			<td class="text11" colspan="5">
										           				<b><span title="tvcnr/tvcnr2/tvcnr3.../tvcnr5">Container nr</span></b>
										           			</td>
										        			</tr>
									           			<tr>
										           			<td class="text11">
																&nbsp;2.<input type="text" class="inputText" name="tvcnr2" id="tvcnr2" size="17" maxlength="17" value="${model.record.tvcnr2}">
															</td>
															<td class="text11">
																&nbsp;3.<input type="text" class="inputText" name="tvcnr3" id="tvcnr3" size="17" maxlength="17" value="${model.record.tvcnr3}">
															</td>
															<td class="text11">
										           				&nbsp;4.<input type="text" class="inputText" name="tvcnr4" id="tvcnr4" size="17" maxlength="17" value="${model.record.tvcnr4}">
										           			</td>
										           			<td class="text11">
										           				&nbsp;5.<input type="text" class="inputText" name="tvcnr5" id="tvcnr5" size="17" maxlength="17" value="${model.record.tvcnr5}">
										           			</td>
														</tr>
													</table>
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td class="text12" ><button name="containerNrButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('containerNrInfo');">&nbsp;<spring:message code="systema.tvinn.sad.ok"/></button> 
															</td>
														</tr>
													</table>
												</div>
								           	</span>
								            </td>
										</tr>
										</table>
									</td>
								</tr>
								<tr height="15"><td></td></tr>
			        	        		</table>
					        </td>
				        </tr>
				        <tr height="30"><td></td></tr>
					</table>          
            	</td>
           	</tr>
            <tr height="30"><td></td></tr>
		</table>
		</td>
		</tr>
	</table>    
	
	</form>
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

