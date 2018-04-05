<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
								           	
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>		
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadnctsimport_unloading_edit_items.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport.do?action=doFind&avd=${recordTopicTvinnSad.tiavd}&sign=${recordTopicTvinnSad.tisg}&opd=${recordTopicTvinnSad.titdn}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.list.tab"/></font>
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_edit.do?action=doFetch&avd=${recordTopicTvinnSad.tiavd}&sign=${recordTopicTvinnSad.tisg}&opd=${recordTopicTvinnSad.titdn}
						&sysg=${recordTopicTvinnSad.tisg}&syst=${recordTopicTvinnSad.tist}&sydt=${recordTopicTvinnSad.tidt}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.created.mastertopic.tab"/></font>
					<font class="text12MediumBlue">[${recordTopicTvinnSad.titdn}]</font>
					<c:if test="${ recordTopicTvinnSad.tist == 'F' || recordTopicTvinnSad.tist == 'M' || empty recordTopicTvinnSad.tist}">
						<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_edit_items.do?action=doFetch&avd=${recordTopicTvinnSad.tiavd}&sign=${recordTopicTvinnSad.tisg}
											&opd=${recordTopicTvinnSad.titdn}&mrnNr=${recordTopicTvinnSad.titrnr}&godsNr=${recordTopicTvinnSad.tign}
											&status=${recordTopicTvinnSad.tist}&datum=${recordTopicTvinnSad.tidt}">
				<font class="tabDisabledLink">
					&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.item.createnew.tab"/>
				</font>
				<c:if test="${recordTopicTvinnSad.tist == 'F' || recordTopicTvinnSad.tist == 'M' || empty recordTopicTvinnSad.tist}">
					<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
				</c:if>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_unloading_edit.do?avd=${recordTopicTvinnSad.tiavd}&sign=${recordTopicTvinnSad.tisg}
											&opd=${recordTopicTvinnSad.titdn}&mrnNr=${recordTopicTvinnSad.titrnr}&godsNr=${recordTopicTvinnSad.tign}
											&status=${recordTopicTvinnSad.tist}&datum=${recordTopicTvinnSad.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.unloading.createnew.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/unloading.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">
					&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.unloading.item.createnew.tab"/>
				</font>
				<img style="vertical-align: bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="item lines">
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_logging.do?avd=${recordTopicTvinnSad.tiavd}&sign=${recordTopicTvinnSad.tisg}
									&opd=${recordTopicTvinnSad.titdn}&mrnNr=${recordTopicTvinnSad.titrnr}&godsNr=${recordTopicTvinnSad.tign}
									&status=${recordTopicTvinnSad.tist}&datum=${recordTopicTvinnSad.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tvinnsadnctsimport_archive.do?avd=${recordTopicTvinnSad.tiavd}&sign=${recordTopicTvinnSad.tisg}
									&opd=${recordTopicTvinnSad.titdn}&mrnNr=${recordTopicTvinnSad.titrnr}&godsNr=${recordTopicTvinnSad.tign}
									&status=${recordTopicTvinnSad.tist}&datum=${recordTopicTvinnSad.tidt}">
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
				 				&nbsp;Tolldekl.&nbsp;<b>${recordTopicTvinnSad.titdn}</b>
				 				&nbsp;Sign&nbsp;<b>${recordTopicTvinnSad.tisg}</b>
				 				
				 				&nbsp;
				 				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
           						Status:&nbsp;<b>${recordTopicTvinnSad.tist}</b>
				 				&nbsp;&nbsp;
								<span title="tienkl">Type prosedyre:</span>&nbsp;
								<c:if test="${recordTopicTvinnSad.tienkl == 'J'}"><b>Forenklet</b></c:if>
								<c:if test="${recordTopicTvinnSad.tienkl == 'N'}"><b>Normal</b></c:if>
				 				&nbsp;&nbsp;&nbsp;Mrn-nr:&nbsp;<b>${recordTopicTvinnSad.titrnr}</b>
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
		 				<tr height="20"><td></td></tr>
	 				</table>
	 				
					<%-- MASTER Topic information [it is passed through a session object: recordTopicTvinnSad] --%>
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
							<form id="createNewItemLine" action="tvinnsadnctsimport_unloading_edit_items.do">
								<input type="hidden" name="action" id="action" value='doFetch'>
				 				<input type="hidden" name="avd" id="avd" value='${model.avd	}'>
				 				<input type="hidden" name="sign" id="sign" value='${model.sign}'>
								<input type="hidden" name="opd" id="opd" value='${model.opd}'>
				 				<input type="hidden" name="mrnNr" id="mrnNr" value='${model.mrnNr}'>
				 				<input type="hidden" name="status" id="status" value='${model.status}'>
				 				<input type="hidden" name="datum" id="datum" value='${model.datum}'>
				 					
							<td class="text12Bold">&nbsp;Varelinjer&nbsp;&nbsp;
								<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submit" value='Lage ny'>
								<button name="allItemsButton" class="inputFormSubmitStd" type="button" onClick="showPop('allItems');" >Vis alle</button> 
							        <span style="background-color:#EEEEEE; position:absolute; left:50px; top:200px; width:1100px; height:1000px;" id="allItems" class="popupWithInputTextThickBorder"  >
						           		<div class="ownScrollableSubWindow" style="width:1080px; height:900px; margin:10px;">
						           			<nav>
						           			<table width="95%" border="0" align="left" cellspacing="2">
						           			<tr>
							           			<td colspan="3" class="text14"><b>Varelinjer</b></td>
							           		</tr>
								           	<tr>	
												<td >
												<table width="95%" cellspacing="0" border="0" cellpadding="0">
													<tr class="tableHeaderField" height="20" valign="left">
													    <td class="tableHeaderFieldFirst">&nbsp;Linjenr.&nbsp;</td>   
													    <td align="center" class="tableHeaderField" nowrap>Slett</td>
									                    <td class="tableHeaderField">&nbsp;Varenr.&nbsp;</td>  
									                    <td class="tableHeaderField">&nbsp;Varebeskrivelse&nbsp;</td> 
									                    <td class="tableHeaderField">&nbsp;Bruttovekt&nbsp;</td>
									                    <td class="tableHeaderField">&nbsp;Nettovekt&nbsp;</td>
									                    <td class="tableHeaderField">&nbsp;Dok.type&nbsp;</td>
									                    <td class="tableHeaderField">&nbsp;Dok.ref&nbsp;</td>
									                    <td class="tableHeaderField">&nbsp;Kollislag</td>
									                    <td class="tableHeaderField">&nbsp;Kolliantal</td>
									                    
									                    	
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
										               <%-- <td class="tableCellFirst" width="2%">&nbsp;${counter.count}</td> --%>
										               <td width="2%" class="tableCellFirst" align="center">&nbsp;${record.tvli}</td>
										               <td class="tableCell" align="center" nowrap>&nbsp;
										               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadnctsimport_unloading_edit_items.do?action=doDelete&avd=${record.tvavd}&opd=${record.tvtdn}&lin=${record.tvli}">
										               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
										               	</a>	
										               </td>
										               <td class="tableCell" >&nbsp;${record.nvvnt}</td>
										               <td width="40%" class="tableCell" width="100" >&nbsp;${record.nvvt}</td>
										               <td class="tableCell" align="right" >&nbsp;${record.nvvktb}&nbsp;</td>
										               <td class="tableCell" align="right" >&nbsp;${record.nvvktn}&nbsp;</td>
										               <td class="tableCell" >&nbsp;${record.nvdty}</td>
										               <td class="tableCell" >&nbsp;${record.nvdref}</td>
										               <td class="tableCell" >&nbsp;${record.nveh}</td>
										               <td class="tableCell" >&nbsp;${record.nvnt}</td>

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
													<td class="text11"><button name="allItemsButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('allItems');">&nbsp;Ok</button> 
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
									    	<td align="center" class="tableHeaderField" nowrap>Slett</td>   
					                    <td class="tableHeaderField">&nbsp;Varenr.&nbsp;</td> 
					                    <td class="tableHeaderField">&nbsp;Varebeskrivelse&nbsp;</td>  
					                    <td class="tableHeaderField">&nbsp;Bruttovekt&nbsp;</td>
					                    <td class="tableHeaderField">&nbsp;Nettovekt&nbsp;</td>
					                    <td class="tableHeaderField">&nbsp;Dok.type&nbsp;</td>
					                    <td class="tableHeaderField">&nbsp;Dok.ref&nbsp;</td>
					                    <td class="tableHeaderField">&nbsp;Kollislag</td>
					                    <td class="tableHeaderField">&nbsp;Kolliantal</td>
					                    
					               </tr> 
					               
					               <form name="formItemList" id="formItemList" method="POST" >
					               		<input type="hidden" name="opdItemList" id="opdItemList" value='${model.opd}'>
				 						<input type="hidden" name="avdItemList" id="avdItemList" value='${model.avd}'> 
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
							               <td class="tableCell" align="center" nowrap>&nbsp;
							               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadnctsimport_unloading_edit_items.do?action=doDelete&avd=${record.tvavd}&opd=${record.tvtdn}&lin=${record.tvli}">
							               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
							               	</a>	
							               </td>	
							               <td class="tableCell" >&nbsp;${record.nvvnt}</td>
							               <td class="tableCell" ><div style="width:120px">&nbsp;${record.nvvt}</div></td>
							               <td class="tableCell" align="right" >&nbsp;${record.nvvktb}&nbsp;</td>
							               <td class="tableCell" align="right" >&nbsp;${record.nvvktn}&nbsp;</td>
							               <td class="tableCell" >&nbsp;${record.nvdty}</td>
							               <td class="tableCell" >&nbsp;${record.nvdref}</td>
							               <td class="tableCell" >&nbsp;${record.nveh}</td>
							               <td class="tableCell" >&nbsp;${record.nvnt}</td>

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
	 			<form name="nctsImportEditTopicItemForm" id="nctsImportEditTopicItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="opd" id="opd" value='${model.opd}'/>
				 	<input type="hidden" name="avd" id="avd" value='${model.avd}'/>
				 	<input type="hidden" name="sign" id="sign" value='${model.sign}'/>
				 	<input type="hidden" name="status" id="status" value='${model.status}'/>
				 	<input type="hidden" name="datum" id="datum" value='${model.datum}'/>
				 	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
				 	<input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value='${numberOfItemLinesInTopic}' />
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="100%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
				 				<b>&nbsp;&nbsp;Varelinje&nbsp;-&nbsp;Kontrolresultat&nbsp;&nbsp;</b>
				 				<img src="resources/images/update.gif" border="0" alt="edit">
				 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 				<input tabindex=-1 align="center" class="text12BoldLightGreenForItemLinenr" readonly type="text" name="lineNr" id="lineNr" size="3" value="">
			 				</td>
			 				<td class="text12White" align="right">
				 				Konform&nbsp;&nbsp;<font class="text16RedBold">${ recordTopicTvinnSadUnloading.nikonf }</font>&nbsp;&nbsp;&nbsp;
				 			</td>
		 				</tr>
	 				</table>
	 				
					<table width="100%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="4"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td >
						 		<table width="100%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							 			<td class="text12" align="left" valign="bottom">
							 				<img onMouseOver="showPop('control_code_info');" onMouseOut="hidePop('control_code_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<c:if test="${recordTopicTvinnSadUnloading.nikonf != '1'}">
								 				<font class="text16RedBold" >*</font>
							 				</c:if>
							 				<span title="nvct" >Kode</span>
							 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="control_code_info" class="popupWithInputText text11"  >
							           			<b>Kode</b>
												<br/>
												 (Hvis losningsresultat = 0)												 
												 <ul>
												 	<li><b>OR</b> = Den opprinnelige vareposten rapporteres (Denne koden benyttes av programmet for å rapportereopprinnelige verdier, og skal normalt ikke benyttes).</li>
												 	<li><b>DI</b> = Avvik i varepostens verdi.</li>
												 	<li><b>NE</b> = Verdiene i ny varepost oppgis. (Du har lagd en ny varepost)</li>
												 	<li><b>NP</b> = Dokumenter/sertifikater ikke fremlagt</li>
												 	<li><b>OT</b> = Det er andre ting å rapportere. (enn det som det er rubrikker for)</li>
												 </ul>
											</span>
											</div>							 				
							 			</td>
							 			<td class="text12" align="left">&nbsp;
							 				<c:if test="${recordTopicTvinnSadUnloading.nikonf != '1'}">
								 				<font class="text16RedBold" >*</font>
							 				</c:if>
							 				<span title="nvctsk" >Språkkode</span>
							            </td>
							            <td colspan="4" class="text12" align="left" valign="bottom">&nbsp;
											<c:if test="${recordTopicTvinnSadUnloading.nikonf != '1'}">
								 				<font class="text16RedBold" >*</font>
							 				</c:if>
											<span title="nvctb" >Merknad</span>
										</td>
										<td class="text12" align="left">&nbsp;
											<img onMouseOver="showPop('pointer_info');" onMouseOut="hidePop('pointer_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<span title="nvctp" >Peker</span>
							 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="pointer_info" class="popupWithInputText text11"  >
							           			<b>Peker</b>
												<p>
													TR0010<br/>													
													This data item is Required when the accompanying Control Indicator (<b>Kode</b>) is equal to <b>NP</b>.<br/>  
													In all other cases, this data item must not be used.
												</p>
											</span>
											</div>
							            </td>
							        </tr>
							        <tr>
							        		<td class="text12" align="left">
											<select class="inputTextMediumBlueMandatoryField" class="text11" name="nvct" id="nvct">
												<option value=""<c:if test="${model.record.nvct == ''}"> selected </c:if> >-velg-</option>
											  	<option value="OR"<c:if test="${model.record.nvct == 'OR'}"> selected </c:if> >OR</option>
											  	<option value="DI"<c:if test="${model.record.nvct == 'DI'}"> selected </c:if> >DI</option>
											  	<option value="NE"<c:if test="${model.record.nvct == 'NE'}"> selected </c:if> >NE</option>
											  	<option value="NP"<c:if test="${model.record.nvct == 'NP'}"> selected </c:if> >NP</option>
											  	<option value="OT"<c:if test="${model.record.nvct == 'OT'}"> selected </c:if> >OT</option>
											</select>
			 			            		</td>
							        		<td >
											<select class="inputTextMediumBlueMandatoryField" name="nvctsk" id="nvctsk">
							            			<option value="">-velg-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nvctsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
											<a tabindex="-1" id="nvctskIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
										</td>
										<td colspan="4" class="text12" align="left" >
							        			<input type="text" class="inputTextMediumBlueMandatoryField" name="nvctb" id="nvctb" size="71" maxlength="70" value="${model.record.nvctb}">
			 			            		</td>
			 			            		<td colspan="2" class="text12" align="left" >
							        			<input type="text" class="inputText" name="nvctp" id="nvctp" size="25" maxlength="35" value="${model.record.nvctp}">
			 			            		</td>
			 			            		
			 			            					 			            		
							        </tr>
							        <tr height="12"><td class="text" align="left"></td></tr>
							        
							 		<tr>
							 			<td class="text12" align="left" valign="bottom">
							 				&nbsp;&nbsp;<span title="tvli" >Linjenr.</span>
							 			</td>
							 			<td class="text12" align="left">
							            	&nbsp;&nbsp;<span title="nvvnt" >Varenr.</span>						            		
							            </td>
							            <td nowrap class="text12" align="left" valign="bottom">&nbsp;<span title="nvvtsk" >Språkkode</span></td>
										<td class="text12" align="left">&nbsp;&nbsp;<span title="nvvktb" >Brut.vekt(kg)</span></td>
										<td class="text12" align="left">&nbsp;&nbsp;<span title="nvvktn" >Net.vekt(kg)</span></td>
							            <td colspan="3" class="text12" align="left">&nbsp;<font class="text16RedBold" >*</font><span title="nvvt" >Varebeskrivelse</span></td>
							        </tr>
							        <tr>
							        		<td class="text12" align="left">
							        			&nbsp;<input tabindex=-1 readonly type="text" class="inputTextReadOnly" name="tvli" id="tvli" size="4" maxlength="5" value="${model.record.tvli}">
			 			            		</td>
							        		<td class="text12" align="left" >
							        			<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="nvvnt" id="nvvnt" size="9" maxlength="8" value="${model.record.nvvnt}">
							        			<a tabindex="-1" id="nvvntIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
			 			            		</td>
			 			            		<td >
											<select name="nvvtsk" id="nvvtsk">
							            			<option value="">-velg-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nvvtsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
											<a tabindex="-1" id="nvvtskIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
										</td>
										<td class="text12" align="left" >
							        			<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="nvvktb" id="nvvktb" size="9" maxlength="9" value="${model.record.nvvktb}">
			 			            		</td>
			 			            		<td class="text12" align="left" >
							        			<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="nvvktn" id="nvvktn" size="9" maxlength="9" value="${model.record.nvvktn}">
			 			            		</td>
			 			            		<td colspan="3" class="text12" align="left" >
							        			<input type="text" class="inputTextMediumBlueMandatoryField" name="nvvt" id="nvvt" size="60" maxlength="70" value="${model.record.nvvt}">
			 			            		</td>
			 			            					 			            		
							        </tr>
							        
							        
							        <tr><td class="text" colspan="9"><hr></td></tr>
							         
							        <tr>
							 			<td class="text12" align="left" valign="bottom">
							 				&nbsp;<b>44.</b>&nbsp;<span title="nvdty" >Dok.type</span>
							 			</td>
							 			<td colspan="3" class="text12" align="left">
							            		&nbsp;<b>44.</b>&nbsp;<span title="nvdref" >Dok.ref.</span>
							            </td>
										<td class="text12" align="left" valign="bottom">
											<b>44.</b>&nbsp;<span title="nvdsk" >Dok.språk</span>
											
										</td>
										
										<td class="text12" align="left">
							            		&nbsp;<b>44.</b>&nbsp;<span title="nvdo" >Met information</span>
							            </td>
							            
										<td class="text12" align="left">
							            		&nbsp;<b>44.</b>&nbsp;<span title="nvdosk" >Språk</span>
							            </td>
							            
							        </tr>
							        <tr>
							        
										<%-- Doc.Type --%>
										<td class="text12" align="left">
											<select name="nvdty" id="nvdty">
							            		<option value="">-velg-</option>
							 				  	<c:forEach var="code" items="${model.ncts013_DocType_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nvdty == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="nvdtyIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>										           				
										</td>							        
							        		<td colspan="3" class="text12" align="left" >
							        			<input type="text" class="inputText" name="nvdref" id="nvdref" size="35" maxlength="35" value="${model.record.nvdref}">
			 			            		</td>
			 			            		<td >
											<select name="nvdsk" id="nvdsk">
							            			<option value="">-velg-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nvdsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
											<a tabindex="-1" id="nvdskIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
										</td>
										<td class="text12" align="left" >
							        			<input type="text" class="inputText" name="nvdo" id="nvdo" size="26" maxlength="26" value="${model.record.nvdo}">
			 			            		</td>
			 			            		
										<td >
											<select name="nvdosk" id="nvdosk">
							            			<option value="">-velg-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nvdosk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
											<a tabindex="-1" id="nvdoskIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
										</td>
							        </tr>
							        
							        <tr>
							 			<td colspan="4" class="text12" align="left" valign="bottom">
							 				&nbsp;<b>31.</b>&nbsp;<font class="text16RedBold" >*</font><span title="nvmn" >Merke og nr.</span>
							 			</td>
							 			<td nowrap class="text12" align="left" valign="bottom">
											<b>31.</b>&nbsp;<span title="nvmnsk" >Godsm.språk</span>
											
										</td>
										<td class="text12" align="left">
							            		&nbsp;<b>31.</b>&nbsp;<span title="nvcnr" >Container</span>
							            </td>
							            <td class="text12" align="left">
							            		&nbsp;<b>31.</b>&nbsp;<font class="text16RedBold" >*</font><span title="nveh" >Kollislag</span>
							            </td>
							           <td class="text12" align="left">
							            		&nbsp;<b>31.</b>&nbsp;<span title="nvnt" >Kolliantal</span>
							            </td>
							           <td class="text12" align="left">
							            		&nbsp;<b>31.</b>&nbsp;<span title="nvnteh" >STK</span>
							            </td>
							        </tr>
							        <tr>
						        		<td colspan="4" class="text12" align="left">
						        			<input type="text" class="inputTextMediumBlueMandatoryField" name="nvmn" id="nvmn" size="35" maxlength="42" value="${model.record.nvmn}">
		 			            		</td>
						        		<td >
											<select name="nvmnsk" id="nvmnsk">
						            			<option value="">-velg-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
	                        	        	 	<option value="${code.tkkode}"<c:if test="${model.record.nvmnsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
											<a tabindex="-1" id="nvmnskIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
										</td>
										<td class="text12" align="left" >
						        			<input type="text" class="inputText" name="nvcnr" id="nvcnr" size="20" maxlength="17" value="${model.record.nvcnr}">
		 			            		</td>
		 			            		
		 			            		<td class="text12" align="left" >
						        			<select class="inputTextMediumBlueMandatoryField" name="nveh" id="nveh">
						            		<option value="">-velg-</option>
						 				  	<c:forEach var="code" items="${model.ncts017_Kolli_CodeList}" >
		                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nveh == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
											</c:forEach> 
											</select>
											<a tabindex="-1" id="nvehIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>		
		 			            		</td>
		 			            		<td class="text12" align="left" >
						        			<input onKeyPress="return numberKey(event)" align="right" type="text" class="inputText" name="nvnt" id="nvnt" size="6" maxlength="5" value="${model.record.nvnt}">
		 			            		</td>
		 			            		<td class="text12" align="left" >
						        			<input onKeyPress="return numberKey(event)" align="right" type="text" class="inputText" name="nvnteh" id="nvnteh" size="6" maxlength="5" value="${model.record.nvnteh}">
		 			            		</td>
							        </tr>
							        
   							        <tr><td class="text" colspan="9"><hr></td></tr>
   							        <tr>
							 			<td colspan="2" class="text12Bold" align="left" >
							 				&nbsp;&nbsp;Følsomme varer
							 			</td>
							 		</tr>
							        <tr>
							 			<td class="text12" align="left" >
							 				<img onMouseOver="showPop('f_vare_info');" onMouseOut="hidePop('f_vare_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<span title="nvfv" >Kode</span>
							 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="f_vare_info" class="popupWithInputText text11"  >
							           			<b>Følsomme varer - Kode</b>
												<p>Kode følsomme varer. Må fylles ut, hvis varenummeret ikke er nok til å identifisere følsomme varer.</p>
												 
											</span>
											</div>	
							 			</td>
							 			<td colspan="2" class="text12" align="left">&nbsp;&nbsp;<span title="nvfvnt" >Antal</span></td>
							        </tr>
							        <tr>
							        		<td class="text12" align="left">
							        			<select name="nvfv" id="nvfv">
								        			<option value="">-velg-</option>
								 				<option value="1"<c:if test="${model.record.nvfv == '1'}"> selected </c:if> >1</option>
											</select>
			 			            		</td>
							        		<td colspan="2" class="text12" align="left">
							        			<input onKeyPress="return numberKey(event)" align="right" type="text" class="inputText" name="nvfvnt" id="nvfvnt" size="12" maxlength="11" value="${model.record.nvfvnt}">
			 			            		</td>
							        		
							        
						            		<%-- only status = U,H are allowed  
					 				    <c:choose>
						 				    <c:when test="${ recordTopicTvinnSad.tist == 'U' ||  recordTopicTvinnSad.tist == 'H' }">
						 				    		<c:choose>
						 				    			<c:when test="${ recordTopicTvinnSadUnloading.nikonf != '1'}">
									 				    <td align="center" class="text9BlueGreen" valign="bottom"  >
										 				    &nbsp;&nbsp;&nbsp;<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='tvinnsadnctsimport_unloading_edit_items.do';" value="<spring:message code="systema.tvinn.sad.ncts.import.unloading.createnew.submit"/>"/>
										 				</td>    	
									 				</c:when>
									 				<c:otherwise>
									 				    <td  align="center" class="text9BlueGreen" valign="bottom"  >
									 				    		&nbsp;&nbsp;&nbsp;<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value="<spring:message code="systema.tvinn.sad.submit.not.editable"/>"/>
									 				    	</td>	
									 				</c:otherwise>
								 				</c:choose>
						 				    </c:when>
						 				    <c:otherwise>
							 				    <td  align="center" class="text9BlueGreen" valign="bottom"  >
							 				    		&nbsp;&nbsp;&nbsp;<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value="<spring:message code="systema.tvinn.sad.submit.not.editable"/>"/>
							 				    	</td>	
						 				    </c:otherwise>	
					 				    </c:choose>
					 				    --%>
					 				    <c:choose>
						 				    <c:when test="${ recordTopicTvinnSad.tist == 'U' ||  recordTopicTvinnSad.tist == 'H' }">
							 				    <td  align="center" class="text9BlueGreen" valign="bottom"  >
							 				    		&nbsp;&nbsp;&nbsp;<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value="<spring:message code="systema.tvinn.sad.submit.not.editable"/>"/>
						 				    	</td>	
						 				    </c:when>
						 				    <c:otherwise>
							 				    <td  align="center" class="text9BlueGreen" valign="bottom"  >
							 				    		&nbsp;&nbsp;&nbsp;<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value="<spring:message code="systema.tvinn.sad.submit.not.editable"/>"/>
							 				    	</td>	
						 				    </c:otherwise>	
					 				    </c:choose>
							        </form>
 							        <tr height="5"><td></td></tr>
				        	        </table>
					        </td>
				        </tr>
						
						<%-- ----------------- --%>						
						<%-- READ ONLY SECTION --%>
						<%-- ----------------- --%>					
						<tr height="22"><td>&nbsp;</td></tr>
				        <tr>
 					        <td colspan="3">
							<table width="90%" align="left" class="formFrameHeaderBlueWithBorder" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="15">
						 			<td class="text12White">
						 				&nbsp;&nbsp;<b>Varelinje&nbsp;-&nbsp;Original</b>&nbsp;[fra eksportøren]
					 				</td>
				 				</tr>
			 				</table>				        
					        </td>
				        </tr>
				        
				 		<tr>
					 		<td>
						 		<table align="left" width="90%" class="secondarySectionFrame" border="0" cellspacing="0" cellpadding="0">
						 			<tr>
							 			<td class="text12" align="left" valign="bottom">
							 				&nbsp;<span title="tvvnt" >Varenr.</span>
							 			</td>
							 			<td class="text12" align="left" valign="bottom">
							            		&nbsp;&nbsp;<span title="tvvtsk" >Språkkode</span>
							            </td>
										<td class="text12" align="left" valign="bottom">
											&nbsp;<span title="tvvktb" >Brut.vekt(kg)</span>
										</td>
							 			<td class="text12" align="left" valign="bottom">
											&nbsp;<span title="tvvktn" >Net.vekt(kg)</span>
										</td>
							 			<td class="text12" align="left" valign="bottom">
							            		&nbsp;&nbsp;<span title="tvvt" >Varebeskrivelse</span>
							            </td>
							            
							        </tr>
							        <tr>
							        		<td class="text12" align="left">
							        			&nbsp;<input readonly type="text" class="inputTextReadOnly" name="tvvnt" id="tvvnt" size="7" maxlength="6" value="${model.record.tvvnt}">
			 			            		</td>
			 			            		<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvvtsk" id="tvvtsk" size="3" maxlength="2" value="${model.record.tvvtsk}">
										</td>
							        		<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvvktb" id="tvvktb" size="10" maxlength="9" value="${model.record.tvvktb}">
										</td>
										<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvvktn" id="tvvktn" size="10" maxlength="9" value="${model.record.tvvktn}">
										</td>
										<td colspan="4" class="text12" align="left" >
							        			<input readonly type="text" class="inputTextReadOnly" name="tvvt" id="tvvt" size="35" maxlength="70" value="${model.record.tvvt}">
			 			            		</td>
			 			            		
							        </tr>
 							        <tr><td class="text" colspan="6"><hr></td></tr>
						 			<tr>
							 			<td class="text12" align="left" valign="bottom">
							 				&nbsp;<span title="tvdty" ><b>44.</b>&nbsp;Dok.type</span>
							 			</td>
										<td class="text12" align="left" valign="bottom">
											&nbsp;<span title="tvdref"><b>44.</b>&nbsp;Dok.ref.</span>
										</td>
							 			<td class="text12" align="left" valign="bottom">
											&nbsp;<span title="tvdsk"><b>44.</b>&nbsp;Dok.språk</span>
										</td>
							 			<td nowrap class="text12" align="left" valign="bottom">
							            		&nbsp;<span title="tvdo"><b>44.</b>&nbsp;Met information</span>
							            </td>
							            <td nowrap class="text12" align="left" valign="bottom">
							            		&nbsp;<span title="tvdosk"><b>44.</b>&nbsp;Språk</span>
							            </td>
							        </tr>
							        <tr>
							        		<td class="text12" align="left">
							        			&nbsp;<input readonly type="text" class="inputTextReadOnly" name="tvdty" id="tvdty" size="5" maxlength="4" value="${model.record.tvdty}">
			 			            		</td>
							        		<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvdref" id="tvdref" size="20" maxlength="35" value="${model.record.tvdref}">
										</td>
										<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvdsk" id="tvdsk" size="3" maxlength="2" value="${model.record.tvdsk}">
										</td>
										<td class="text12" align="left" >
							        			<input readonly type="text" class="inputTextReadOnly" name="tvdo" id="tvdo" size="20" maxlength="26" value="${model.record.tvdo}">
			 			            		</td>
			 			            		<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvdosk" id="tvdosk" size="3" maxlength="2" value="${model.record.tvdosk}">
										</td>
							        </tr>

 							        <tr><td class="text" colspan="6"><hr></td></tr>
						 			<tr>
							 			<td colspan="2" class="text12" align="left" valign="bottom">
							 				&nbsp;<span title="tvmn" ><b>31.</b>&nbsp;Kollimærkning</span>
							 			</td>
										<td class="text12" align="left" valign="bottom">
											&nbsp;<span title="tvmnsk"><b>31.</b>&nbsp;Godsm.språk</span>
										</td>
							 			<td class="text12" align="left" valign="bottom">
											&nbsp;<span title="tvcnr"><b>31.</b>&nbsp;Container</span>
										</td>
							 			<td class="text12" align="left" valign="bottom">
							            		&nbsp;<span title="tveh"><b>31.</b>&nbsp;Kollislag</span>
							            </td>
							            <td class="text12" align="left" valign="bottom">
							            		&nbsp;<span title="tvnt"><b>31.</b>&nbsp;Kolliantal</span>
							            </td>
							            <td class="text12" align="left" valign="bottom">
							            		&nbsp;<span title="tvnteh"><b>31.</b>&nbsp;STK</span>
							            </td>
							        </tr>
							        <tr>
							        		<td colspan="2" class="text12" align="left">
							        			&nbsp;<input readonly type="text" class="inputTextReadOnly" name="tvmn" id="tvmn" size="20" maxlength="42" value="${model.record.tvmn}">
			 			            		</td>
							        		<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvmnsk" id="tvmnsk" size="3" maxlength="2" value="${model.record.tvmnsk}">
										</td>
										<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvcnr" id="tvcnr" size="20" maxlength="17" value="${model.record.tvcnr}">
										</td>
										
										<td class="text12" align="left" >
							        			<input readonly type="text" class="inputTextReadOnly" name="tveh" id="tveh" size="3" maxlength="2" value="${model.record.tveh}">
			 			            		</td>
			 			            		<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvnt" id="tvnt" size="6" maxlength="5" value="${model.record.tvnt}">
										</td>
										<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvnteh" id="tvnteh" size="6" maxlength="5" value="${model.record.tvnteh}">
										</td>
							        </tr>
							        <tr height="15"><td></td></tr>
			        	        		</table>
					        </td>
				        </tr>
				        <tr height="10"><td class="text" align="left"></td></tr>
				</table>
			</td>
		</tr>
		<tr height="30"><td>&nbsp;</td></tr>
	</table>    
	
	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

