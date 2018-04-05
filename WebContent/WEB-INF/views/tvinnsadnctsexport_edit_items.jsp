<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadnctsexport_edit_items.js?ver=${user.versionEspedsg}"></SCRIPT>
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
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
					<c:if test="${ model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">
						<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.item.createnew.tab"/></font>
				<c:if test="${model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">
					<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
				</c:if>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkLogging" tabindex=-1 style="display:block;" href="tvinnsadnctsexport_logging.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}&tullId=${model.tullId}
											&mrnNr=${model.mrnNr}&status=${model.status}&datum=${model.datum}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkArchive" tabindex=-1 style="display:block;" href="tvinnsadnctsexport_archive.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}&tullId=${model.tullId}
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
		<table border="0" width="95%" align="center" border="0">
			<tr>
	 			<td >		
	 				<%-- MASTER Topic header --%>
	 				<table width="100%" align="center" class="formFrameHeaderTransparent" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text11MediumBlue">
				 				&nbsp;Angivelse&nbsp;<b>${model.opd}</b>
				 				&nbsp;Sign&nbsp;<b>${model.sign}</b>
				 				&nbsp;&nbsp;&nbsp;Lrn-nr.:&nbsp;<b>${model.tullId}</b>
				 				&nbsp;
								<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
           						Status:&nbsp;<b>${model.status}</b>
				 				&nbsp;&nbsp;&nbsp;Mrn-nr.:&nbsp;<b>${model.mrnNr}</b>
			 		
			 				<div class="text11" style="position: relative;" align="left">
							<span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
		           			<br/>
				           		Kun status <b>M</b>, <b>(Fejl)</b> eller <b>' '</b> kan redigeres.
			           			<ul>
									<li><b>' '</b>(Blank) Åpen for endring.</li>
									<li><b>E</b>&nbsp;Noen arbeider med (Endrer) den aktuelle TET nå. Hvis man arbeider med en deklarasjon og strømbrudd eller lignende inntreffer vil deklarasjonen bli "hengende" i status'E'.
									Man må i slike tilfeller endre status på deklarasjonen (MENU TET, punkt 2.) fra 'E' til ' '
									manuelt.</li>
									<li><b>Q</b>&nbsp;Intern postkasse" (Venter på at program skal lage en sending av denne og andre)</li>
									<li><b>+</b>&nbsp;Midlertidig "arbeidsstatus" - Alle som hadde Q på et gitt tidspunkt.(=klar til sending)</li>
									<li><b>A</b>&nbsp;Ligger i sendekø (ER lagt i sendings-fil) for sending til TAD</li>
									<li><b>B</b>&nbsp;Kommet til IBM, men ennå ikke til TAD (kun ved nettverk IGN)</li>
									<li><b>C</b>&nbsp;Kommet til TAD (egentlig "hentet TIL TAD's postkasse)</li>
									<li><b>M</b>&nbsp;Feilmelding (har fått IE16)</li>
									<li><b>Å</b>&nbsp;En midlertidig ("arbeids-status") som den har kun en kort periode (mellom mottak av melding og printing av feilmelding)</li>
									<li><b>U</b>&nbsp;Har fått MRN-nr. (IE28)</li>
									<li><b>P</b>&nbsp;Har fått frigivelse (dokument ER printet) (IE29)</li>
									<li><b>K</b>&nbsp;Venter på PRINT av "T-dokument" (midlertidig kortvarig status) Benyttes også ved manuell print (før sending) eller ved kopi-print (valg 6)</li>
									<li><b>V</b>&nbsp;Varekontroll (IE60)</li>
									<li><b>F</b>&nbsp;Ingen frigivelse (gjerne etter en varekontroll (IE51)</li>
									<li><b>S</b>&nbsp;Frigivelse avvist (Endelig!, Etter å ha fått "Ingen frigivelse" OG "Bedt om frigivelse") (IE62)</li>
									<li><b>D</b>&nbsp;Kansellering innvilget (IE09)</li>
									<li><b>Z</b>&nbsp;Transitering avsluttet  (IE45 mottatt)</li>
			           			</ul>
							</span>
							</div>
							</td>
		 				</tr>
	 				</table>
					<%-- MASTER Topic information [it is passed through a session object: recordTopic] --%>
				 	<table height="40" width="100%" align="center" class="formFrameTitaniumWhite" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="2"><td class="text" align="left" colspan="2"></td></tr>
				 		<tr>
					 		<td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text11Bold" align="left" >Avsender&nbsp;</td>
							            <td class="text11" align="left" >${recordTopicTvinnSad.thkns}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">TIN-nr&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thtins}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Navn&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thnas}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Adresse&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thads1}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Postadresse&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thpns}&nbsp;${recordTopicTvinnSad.thpss}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Land
							            </td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thlks}</td>
							        </tr>
							        
			        	        </table>
					        </td>
					        <td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text11Bold" align="left" >Mottaker&nbsp;</td>
							            <td class="text11" align="left" >${recordTopicTvinnSad.thknk}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">TIN-nr&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thtink}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Navn&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thnak}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Adresse&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thadk1}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Postadresse&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicTvinnSad.thpnk}&nbsp;${recordTopicTvinnSad.thpsk}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Land</td>
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
							<form id="createNewItemLine" action="tvinnsadnctsexport_edit_items.do">
								<input type="hidden" name="action" id="action" value='doFetch'>
				 				<input type="hidden" name="avd" id="avd" value='${model.avd	}'>
				 				<input type="hidden" name="sign" id="sign" value='${model.sign}'>
								<input type="hidden" name="opd" id="opd" value='${model.opd}'>
				 				<input type="hidden" name="tullId" id="tullId" value='${model.tullId}'>
				 				<input type="hidden" name="mrnNr" id="mrnNr" value='${model.mrnNr}'>
				 				<input type="hidden" name="status" id="status" value='${model.status}'>
				 				<input type="hidden" name="datum" id="datum" value='${model.datum}'>
				 					
				 			<table width="100%" cellspacing="0" border="0" cellpadding="0">
				 			<tr>		
							<td class="text12Bold">Varelinjer&nbsp;&nbsp;
								<c:if test="${model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">
									<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submit" value='Lage ny'>
								</c:if>
								<button name="allItemsButton" class="inputFormSubmitStd" type="button" onClick="showPop('allItems');" >Vis alle</button> 
									        <span style="background-color:#EEEEEE; position:absolute; left:50px; top:200px; width:1200px; height:1000px;" id="allItems" class="popupWithInputTextThickBorder"  >
								           		<table id="containerdatatableTable" width="98%" align="left" >
								           			<tr>	
													<td class="text12">
								           			<table id="tblItemLinesAll" class="display compact cell-border">
								           				<thead>
										           		<tr style="background-color:#DDDDDD">	
															    <th class="text12">&nbsp;Linjenr.&nbsp;</th>
															    <th class="text12">&nbsp;Varenr.&nbsp;</th>   
											                    <th class="text12">&nbsp;Dekl&nbsp;</th>
											                    <th class="text12">&nbsp;Avs.land&nbsp;</th>
											                    <th class="text12">&nbsp;Best.land&nbsp;</th>
											                    <th class="text12">&nbsp;Dok.type&nbsp;</th>
											                    <th class="text12">&nbsp;Bruttovekt&nbsp;</th>
											                    <th class="text12">&nbsp;Nettovekt&nbsp;</th>
											                    <th class="text12">&nbsp;Kolliant.(&Sigma;)</th>
											                    <th class="text12">&nbsp;Stk.(&Sigma;)</th>
											                    <th class="text12">&nbsp;Varebeskrivelse&nbsp;</th>
											                    <c:if test="${model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">
										                    		<th align="center" class="text12" nowrap>Slett</td>
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
			               							               <td width="2%" class="text11" align="center">&nbsp;${record.tvli}</td>
													               <td class="text11" >&nbsp;${record.tvvnt}</td>
													               <td class="text11" >&nbsp;${record.tvdk}</td>
													               <td class="text11" >&nbsp;${record.tvalk}</td>
													               <td class="text11" >&nbsp;${record.tvblk}</td>
													               <td class="text11" >&nbsp;${record.tvdty}</td>
													               <td class="text11" align="right" >&nbsp;${record.tvvktb}&nbsp;</td>
													               <td class="text11" align="right" >&nbsp;${record.tvvktn}&nbsp;</td>
													               
													               <td class="text11" >&nbsp;${record.sum_of_tvnt}</td>
													               <td class="text11" >&nbsp;${record.sum_of_tvnteh}</td>
													               <td width="40%" class="text11" width="100" >&nbsp;${record.tvvt}</td>
													               <c:if test="${model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">	
														               <td class="text11" align="center" nowrap>&nbsp;
														               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadnctsexport_edit_items.do?action=doDelete&sign=${model.sign}&avd=${record.tvavd}&opd=${record.tvtdn}&lin=${record.tvli}">
														               		<img src="resources/images/delete.gif" border="0" alt="remove">
														               	</a>	
														               </td>
													               </c:if>
													            </tr>
													            <%-- this param is used ONLY in this JSP --%>
													            <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" />
														        <%-- <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" />  --%>
														        <c:set var="numberOfItemLinesInTopic" value="${record.tvli}" scope="request" />
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
															<td class="text11"><button name="allItemsButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('allItems');">&nbsp;Ok</button> 
															</td>
														</tr>
														</table>
													</div>
							   				</span>	
							   		
							   		<c:if test="${model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">
							   			&nbsp;<button title="Import av varuposter" name="itemLinesImportButton" id="itemLinesImportButton" class="buttonGrayWithGreenFrame11" type="button" >Importere varelinjer</button>	
							   		</c:if>	
							   				
									</td>
									<td width="20%" class="text12">&nbsp;</td>
								</tr>
							</table>	
							</form>
							</td>
						</tr> 
						
						<tr>
							<td >
							<form name="formItemList" id="formItemList" method="POST" >
			               		<input type="hidden" name="opdItemList" id="opdItemList" value='${model.opd}'>
		 						<input type="hidden" name="avdItemList" id="avdItemList" value='${model.avd}'> 
		 						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
								
								<table width="100%" id="containerdatatableTable" cellspacing="2" align="left" >
									<tr>
									<td class="text11">
							
									<table id="tblItemLines" class="display compact cell-border">
									<thead>
										<tr style="background-color:#DDDDDD">
									    <th class="text11">&nbsp;Linjenr.&nbsp;</th> 
									    <th class="text11">&nbsp;Oppd.&nbsp;</th>
									    <th class="text11">&nbsp;Varenr.&nbsp;</th>   
					                    <th class="text11">&nbsp;Dekl.&nbsp;</th>
					                    <th class="text11">&nbsp;Avs.land&nbsp;</th>
					                    <th class="text11">&nbsp;Best.land&nbsp;</th>
					                    <th class="text11">&nbsp;Dok.type&nbsp;</th>
					                    <th class="text11">&nbsp;Bruttovekt&nbsp;</th>
					                    <th class="text11">&nbsp;Nettovekt&nbsp;</th>
					                    <th class="text11">&nbsp;Kolliant.(&Sigma;)</th>
					                    <th class="text11">&nbsp;Stk.(&Sigma;)</th>
					                    <th class="text11">&nbsp;Varebeskrivelse&nbsp;</th>
					                    <c:if test="${model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">
					                    	<th align="center" class="text11" nowrap>Slett</th>
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
							               <%-- <td class="tableCellFirst" width="2%">&nbsp;${counter.count}</td> --%>
							               <td width="4%" class="text11" align="center">${record.tvli}</td>
							               <td width="4%" class="text11" align="center">&nbsp;
							               		<a tabindex=-1 id="recordUpdate_${counter.count}_${record.tvli}" href="#" onClick="getItemData(this);">
							               			<img src="resources/images/update.gif" border="0" alt="edit">&nbsp;
							               		</a>
							               </td>
							               <td class="text11" >&nbsp;${record.tvvnt}</td>
							               <td class="text11" >&nbsp;${record.tvdk}</td>
							               <td class="text11" >&nbsp;${record.tvalk}</td>
							               <td class="text11" >&nbsp;${record.tvblk}</td>
							               <td class="text11" >&nbsp;${record.tvdty}</td>
							               <td class="text11" align="right" >&nbsp;${record.tvvktb}&nbsp;</td>
							               <td class="text11" align="right" >&nbsp;${record.tvvktn}&nbsp;</td>
							               <td class="text11" >&nbsp;${record.sum_of_tvnt}</td>
							               <td class="text11" >&nbsp;${record.sum_of_tvnteh}</td>
							               <td class="text11" ><div style="width:120px" >&nbsp;${record.tvvt}</div></td>
							               <c:if test="${model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">	
								               <td class="text11" align="center" nowrap>&nbsp;
								               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadnctsexport_edit_items.do?action=doDelete&sign=${model.sign}&avd=${record.tvavd}&opd=${record.tvtdn}&lin=${record.tvli}">
								               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
								               	</a>	
								               </td>
							               </c:if>
							                
								           </tr>
								        <%-- <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" /> --%>
								        <c:set var="numberOfItemLinesInTopic" value="${record.tvli}" scope="request" />
								         
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
	 			<form name="nctsExportEditTopicItemForm" id="nctsExportEditTopicItemForm" action="tvinnsadnctsexport_edit_items.do" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="opd" id="opd" value='${model.opd}'/>
				 	<input type="hidden" name="avd" id="avd" value='${model.avd}'/>
				 	<input type="hidden" name="sign" id="sign" value='${model.sign}'/>
				 	<input type="hidden" name="tullId" id="tullId" value='${model.tullId}'/>
				 	<input type="hidden" name="status" id="status" value='${model.status}'/>
				 	<input type="hidden" name="datum" id="datum" value='${model.datum}'/>
				 	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
				 	<input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value='${numberOfItemLinesInTopic}' />
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="100%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
				 				
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
					           		<div class="text11" align="left" style="display:block;width:700px;word-break:break-all;">
					           			${activeUrlRPGUpdate_TvinnSad}<br/><br/>
					           			<button name="updateInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('updateInfo');">Close</button> 
					           		</div>
						        </span>
				 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 				<input tabindex=-1 align="center" class="text12BoldLightGreenForItemLinenr" readonly type="text" name="lineNr" id="lineNr" size="3" value=''>
			 				</td>
		 				</tr>
	 				</table>
					<table width="100%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
							 			<td class="text12" align="left">
							 				&nbsp;<span title="tvli" >Linjenr.</span>
							 			</td>
							 			<td class="text12" align="left">
							            <img id="imgTaricVarukodSearch" onMouseOver="showPop('32_info');" onMouseOut="hidePop('32_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>33.</b><span title="tvvnt" >Varenr.</span>

							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="32_info" class="popupWithInputText text11"  >
						           			<b>33.Varekod (Commodity Code)</b>
						           			Enter the HS code for the goods, made up of at least six digits. However, where
											Community legislation so provides, the Combined Nomenclature heading shall be used<br/><br/>
						           			<br/><br/>
						           			
						           			This box must be completed where:
						           			<ul>
												<li>The same person makes a transit declaration at the same time as, or following, a
												customs declaration which includes a commodity code; or</li>
												<li>a transit declaration covers goods mentioned in the ‘list of goods involving greater
												risk of fraud’ (Annex I to Appendix I Convention procedure/ Annex 44cof IPC).</li>
												<li>in the Community, where required by Community legislation.</li>
											</ul>
										</span>
										</div>
										</td>
							 			<td class="text12" >
							 				<img onMouseOver="showPop('deklTyp_info');" onMouseOut="hidePop('deklTyp_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 					<b>1.</b>&nbsp;<span title="tvdk" >Dekl.&nbsp;</span>
						 					
						 					<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="deklTyp_info" class="popupWithInputText text11"  >
		           									<ul>
								           				<li>
								           				 	<b>T-</b>&nbsp;for the transportation of mixed consignments of T1 and T2 goods. In this case, the
																goods will be specified in separate SAD-BIS forms or loading lists for each
																status category of goods. The blank space after the symbol T should be struck
																through to prevent the insertion of any additional digits or numbers.
								           				</li>	
														<li>
								           				 	<b>T1</b>&nbsp;to cover the movement of non-Community goods within the Community and
																to/from the Community and the EFTA countries, as well as between the EFTA
																countries themselves. T1 is also used to cover the movement of Community goods in
																certain circumstances..
								           				</li>
								           				<li>
								           				 	<b>T2</b>&nbsp;for the transportation of Community goods where required.
								           				</li>
								           				<li>
								           				 	<b>T2F</b>&nbsp;
								           				 	for Community goods consigned to/from/between the non-fiscal territories of the Community
								           				 	E.g. Åland och Kanarieöarna
								           				</li>
								           				
								           				<li>
								           				 	<b>T2SM</b>&nbsp;SE T2 + SAN MARINO (Gäller inte NCTS Sverige [ref. NCTS Manual sida 174].)
								           				</li>
								           				<li>
								           				 	<b>TIR</b>Transport Internationaux Routiers (Gäller inte NCTS Sverige [ref. NCTS Manual sida 174].)
								           				</li>
								           				
								           				<li>
								           				 	<b>SS</b>Förhandsanmälan
								           				</li>
								           				<li>
								           				 	<b>T2L</b>TEST
								           				</li>
								           			</ul>
								           			Note: where the symbols T1, T2, T2F, T1bis, T2bis, T2Fbis, as appropriate, have been
													omitted the goods shall be deemed to have been placed under the T1 procedure.
								           			<br/>
								           			Procedurernas syfte är att visa vad ditt gods har för tullstatus det vill säga om det är en gemenskapsvara eller icke-gemenskapsvara.
													<br/>Transiterar du en icke-gemenskapsvara ska du alltid använda T1 proceduren. Om du istället transiterar en gemenskapsvara kan du oftast använda T2 proceduren.						           			
											</span>		
											</div>
										</td>
										
							 			<td class="text12" align="left">
							 			<img onMouseOver="showPop('15_info');" onMouseOut="hidePop('15_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>15.</b>&nbsp;<span title="tvalk" >Avs.land</span>
				 						
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="15_info" class="popupWithInputText text11"  >
						           			<b>Important</b><br/>
						           			This field should be entered if the sibling fields on header (15 and 17) are empty
						           			<br/>
											As a general rule, when item: 1.Deklaration type (in header) = <b>T-</b> there will be a need for filling these fields at an item line level
						           			<br/><br/>
											This is by defintion since T- is a combination of T1 and T2 meaning that each item line belongs either to one or the other						           			
										</span>
										</div>
										</td>
							            
							            <td class="text12" align="left">
							            <img onMouseOver="showPop('17_info');" onMouseOut="hidePop('17_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>17.</b>&nbsp;<span title="tvblk" >Best.land</span>
				 						
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="17_info" class="popupWithInputText text11"  >
						           			<b>Important</b><br/>
						           			This field should be entered if the sibling fields on header (15 and 17) are empty
						           			<br/>
											As a general rule, when item: 1.Deklaration type (in header) = <b>T-</b> there will be a need for filling these fields at an item line level
						           			<br/><br/>
											This is by defintion since T- is a combination of T1 and T2 meaning that each item line belongs either to one or the other						           			
										</span>
										</div>
										</td>
										
										
							            <td class="text12" align="left">
							            <img onMouseOver="showPop('35_info');" onMouseOut="hidePop('35_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>35.</b>
							            <span title="tvvktb" >Brut.vekt(kg)</span>
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="35_info" class="popupWithInputText text11"  >
						           			<b>Bruttovekt</b><br/>
						           			Enter the gross mass, in kilograms, of the goods described in the corresponding box 31.The ‘gross mass’ means the total weight of the goods including all packing but excluding containers and other transport equipment.
											<br/>
											As a general rule, where the gross mass exceeds 1 kilogram and includes a fraction of a unit (kilogram), the figure entered in this box may be rounded as follows:
											<ul>
							           			<li>Fraction from 0.001 to 0.499: rounded down to the nearest kilogram</li>
							           			<li>Fraction from 0.500 to 0.999: rounded up to the nearest kilogram.</li>
							           		</ul>
						           			<br/><br/>

										</span>
										</div>
										</td>
										<td class="text12" align="left"><b>&nbsp;38.</b>&nbsp;<span title="tvvktn" >Net.vekt(kg)</span></td>
										<td class="text12" align="left"><b>&nbsp;44.</b>&nbsp;<span title="tvdty" >Dok.Type</span>&nbsp;&nbsp;</td>
										<td class="text12" align="left"><b>&nbsp;44.</b>&nbsp;<span title="tvdref" >Dok.Ref</span>&nbsp;&nbsp;</td>										
							        </tr>
							        <tr>
						        		<td class="text12" align="left">
						        			&nbsp;<input readonly type="text" class="inputText" name="tvli" id="tvli" size="3" maxlength="5" value="${model.record.tvli}">
		 			            		</td>
						        		<td class="text12" align="left">
						        			<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvvnt" id="tvvnt" size="12" maxlength="6" value="${model.record.tvvnt}">
						        			<a tabindex="-1" id="tvvntIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
		 			            		</td>
		 			            		
							            <td>&nbsp;
							 				<select name="tvdk" id="tvdk">
							            		<option value="">-velg-</option>
							 				  	<c:forEach var="code" items="${model.ncts031_DeklType_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>											
						 				</td>
										<td align="left">&nbsp;
					            			<select name="tvalk" id="tvalk">
							            		<option value="">-velg-</option>
						 				  		<c:forEach var="country" items="${model.countryCodeList}" >
		                                	 	<option value="${country.zkod}"<c:if test="${model.record.tvalk == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="tvalkIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
				            			</td> 
										<td align="left">&nbsp;
					            			<select name="tvblk" id="tvblk">
							            		<option value="">-velg-</option>
						 				  		<c:forEach var="country" items="${model.countryCodeList}" >
		                                	 	<option value="${country.zkod}"<c:if test="${model.record.tvblk == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="tvblkIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
					            		</td> 
										<td class="text12" align="left"><input onKeyPress="return amountKey(event)" type="text" class="inputText" name="tvvktb" id="tvvktb" size="10" maxlength="11" value="${model.record.tvvktb}"></td>
										<td class="text12" align="left"><input onKeyPress="return amountKey(event)" type="text" class="inputText" name="tvvktn" id="tvvktn" size="10" maxlength="11" value="${model.record.tvvktn}"></td>
										<%-- Doc.Type --%>
										<td >
											<select name="tvdty" id="tvdty">
							            		<option value="">-velg-</option>
							 				  	<c:forEach var="code" items="${model.ncts013_DocType_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdty == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>										           				
										</td>
										<td class="text12" align="left"><input type="text" class="inputText" name="tvdref" id="tvdref" size="10" maxlength="20" value="${model.record.tvdref}"></td>
										
							        </tr>
							        <tr height="10"><td class="text" align="left" colspan="9"><hr></td></tr>
							        
									<tr>
										<td class="text12" align="left"><b>&nbsp;44.</b>&nbsp;<span title="tvdsk" >Dok.språk</span>
										</td>
										
										<td class="text12" align="left">
										<img onMouseOver="showPop('44_main_info');" onMouseOut="hidePop('44_main_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">										
										<b>44.</b>&nbsp;Supp.oppl.
										
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="44_main_info" class="popupWithInputText text11"  >
					           				<b>44. Supplerende opplysninger</b>
									        <br/>
											Sektionen är indelad i en <b>huvud Rubrik 44</b> (som är denna) och en <b>sekundär Rubrik 44</b>, som du hittar under Extraordinära Uppgifter.
											<br/><br/>
						           			I den här huvudsektionen registrerar du huvuduppgifterna för Rubrik 44. 
						           			<br/><br/>
						           			Den sekundära sektionen under Extraordinära uppgifter använder du när du behöver registrera
						           			flera förekomster av Rubrik 44
										</span>	
										</div>									
										</td>
										
										<td class="text12" nowrap>
										<img onMouseOver="showPop('31_kslag_info');" onMouseOut="hidePop('31_kslag_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.</b>
										<font class="text16RedBold" >*</font><span title="tveh" >Kollislag</span>
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_kslag_info" class="popupWithInputText text11"  >
						           			<b>Kollislag [Enhet]</b>
											<br/>
						           			The correct kind of packages shall be entered.
										</span>
										</div>
										</td>
										
										<td class="text12">
										<img onMouseOver="showPop('31_kantal_info');" onMouseOut="hidePop('31_kantal_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.</b><span title="tvnt" >Kolliant.</span>
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_kantal_info" class="popupWithInputText text11"  >
							           		<b>Kolliantal</b>
						           			<br/>
						           			Number of packages 
											<br/>
						           			Enter the number of packages for packaged goods and number of parts for unpackaged goods. The number of parts for unwrapped goods can never be zero. If the package type=bulkvarer this field should be = <b>Zero</b>.

										</span>
							            </div>
							            </td>
										
							            <td class="text12" >
										<img onMouseOver="showPop('31_styck_enhet_info');" onMouseOut="hidePop('31_styck_enhet_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.</b><span title="tvnteh" >Stykker</span>
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_styck_enhet_info" class="popupWithInputText text11"  >
						           			<b>Stykker</b>
											<br/>
						           			This field is used when Kolliantal can not be used. E.g. bulkvaror such as gas, fluids, etc.
						           			<br/><br/>
						           			This field is <b>mandatory</b> when Kollislag = <b>VQ, VG, VL, VY, VR, VO</b><br/><br/>
						           			Kollislag: <b>NE</b> is always = unwrapped goods and requires (stykker) > 0
										</span>
										</div>
										</td>
										
							            
										<td class="text12" align="left" colspan="2">
										<img onMouseOver="showPop('31_varubesk_info');" onMouseOut="hidePop('31_varubesk_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.</b>
										<font class="text16RedBold" >*</font><span title="tvvt / tvvt2, tvvt3, etc" >Varebeskrivelse&nbsp;</span>
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_varubesk_info" class="popupWithInputText text11"  >
						           			<b>Varebeskrivelse</b>
											<br/>
						           			The item description should be as accurate as possible.
						           			<br/><br/>
										</span>
										</div>
										</td>
										
										<td>&nbsp;</td>
										<td class="text12" align="left" colspan="2">
							            		<b>31.</b>&nbsp;<span title="tvvtsk" >Varubesk.språk</span>
							            </td>
							        </tr>
							        
							        
									<tr>
										<td >
											<select name="tvdsk" id="tvdsk">
							            			<option value="">-velg-</option>
						 					  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvdsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
			                                	 	</c:forEach>
											</select>
						            		<a tabindex="-1" id="tvdskIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>									           				
										</td>
										
										<td class="text12" align="left"><button name="44_aditionalInfo_01" class="buttonGray" type="button" onClick="showPop('44_aditionalInfo_01');" >Mere...</button> 
								        <span style="position:absolute; left:280px; top:500px; width:650px; height:260px;" id="44_aditionalInfo_01" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table border="0" cellspacing="1" cellpadding="0">
								           			<tr>
								           			<td>
								           				<table class="lightGrayBg" >
								           				<tr>
										           			<td class="text11" colspan="2">
										           				<b>44.Supplerende opplysninger</b>
										           			</td>
										        			</tr>
														<tr>
															<td style="font-style:italic;" class="text11Gray">&nbsp;<span title="tvdty_readonly" >Dok.type</span></td>
										           			<td style="font-style:italic;" class="text11Gray">&nbsp;<span title="tvdref_readonly" >Dok.ref.</span></td>
															<td style="font-style:italic;" class="text11Gray">&nbsp;<span title="tvdsk_readonly" >Dok.språk</span></td>
															<td class="text11">&nbsp;<span title="tvdo" >Supplerende Opplysn.</span></td>
															<td class="text11">&nbsp;<span title="tvdosk" >Språk</span></td>
														</tr>
									           			<tr>
										           			<td class="text12" align="left"><input readonly type="text" class="inputTextReadOnly" name="tvdty_readonly" id="tvdty_readonly" size="10" maxlength="20" value="${model.record.tvdty}"></td>										           			
										           			<td class="text12" align="left"><input readonly type="text" class="inputTextReadOnly" name="tvdref_readonly" id="tvdref_readonly" size="10" maxlength="20" value="${model.record.tvdref}"></td>										           			
										           			<td class="text12" align="left"><input readonly type="text" class="inputTextReadOnly" name="tvdsk_readonly" id="tvdsk_readonly" size="10" maxlength="20" value="${model.record.tvdsk}"></td>										           			
										           			
										           			<td class="text11" nowrap >
										           				&nbsp;<input type="text" class="inputText" name="tvdo" id="tvdo" size="20" maxlength="26" value='${model.record.tvdo}'>										           			
										           			</td>
							            			   			<td class="text11" nowrap >&nbsp;
											            			<select name="tvdosk" id="tvdosk">
													            		<option value="">-velg-</option>
												 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvdosk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
										           			
					           							</tr>
					           							<tr>
										           			<td class="text11">&nbsp;<span title="tvtlo" >Kode [Supplerende opplysninger]</span></td>
										           			<td class="text11">&nbsp;</td>
										           			<td class="text11">&nbsp;<span title="tvexkd" >Eksport fra EF</span></td>
															<td class="text11">&nbsp;<span title="tvexlk" >Eksport fra land</span></td>
															
														</tr>
									           			<tr>
										           			<td class="text11" colspan="2" >
																<select name="tvtlo" id="tvtlo">
												 				  <option selected value="">-velg-</option>
																  <option value="CAL"<c:if test="${model.record.tvtlo == 'CAL'}"> selected </c:if> >CAL [Skuldbelopp]</option>
																  <option value="DG0"<c:if test="${model.record.tvtlo == 'DG0'}"> selected </c:if> >DG0 [Export med restriktioner]</option>
																  <option value="DG1"<c:if test="${model.record.tvtlo == 'DG1'}"> selected </c:if> >DG1 [Export med avgift]</option>
																  <option value="DG2"<c:if test="${model.record.tvtlo == 'DG2'}"> selected </c:if> >DG2 [Export]</option>
															  	</select>	
										           			</td>
										           			<td class="text11" >&nbsp;
																<select name="tvexkd" id="tvexkd">
												 				  <option value=""<c:if test="${model.record.tvexkd == '0' || model.record.tvexkd == ''}"> selected </c:if> >0</option>
																  <option value="1"<c:if test="${model.record.tvexkd == '1'}"> selected </c:if> >1</option>
															  	</select>	
										           			</td>
										           			
							            			   			<td class="text11" nowrap >&nbsp;
											            			<select name="tvexlk" id="tvexlk">
											 						<option value="">-velg-</option>
												 				  	<c:forEach var="country" items="${model.countryCodeList}" >
								                                	 	<option value="${country.zkod}"<c:if test="${model.record.tvexlk == country.zkod}"> selected </c:if> >${country.zkod}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
										           			
					           							</tr>
					           							<tr>
										           			<td class="text11" colspan="3">&nbsp;<span title="tvmtxt" >Yderligere opplysninger</span></td>
															<td class="text11">&nbsp;<span title="tvmsk" >Språk</span></td>
															
														</tr>
									           			<tr>
										           			<td class="text11" colspan="3" >
										           				&nbsp;<input type="text" class="inputText" name="tvmtxt" id="tvmtxt" size="35" maxlength="70" value="${model.record.tvmtxt}">										           			
										           			</td>
										           			<td class="text11" nowrap >&nbsp;
											            			<select name="tvmsk" id="tvmsk">
													            		<option value="">-velg-</option>
												 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvmsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
					           							</tr>					           															           			
					           							</table>
					           						</td>
					           						</tr>
					           						
					           						<tr height="4"><td class="text" align="left"></td></tr>
					           						</table>
					           						
					           						
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td class="text11"><button name="44_aditionalInfo_01ButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('44_aditionalInfo_01');">&nbsp;Ok</button> 
															</td>
														</tr>
													</table>
													
												</div>
										</span>
										</td>
										
					           			<td align="left">
					            			<select class="inputTextMediumBlueMandatoryField" name="tveh" id="tveh">
							            		<option value="">-velg-</option>
							 				  	<c:forEach var="record" items="${model.ncts017_Kolli_CodeList}" >
			                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tveh == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="tvehIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>										
				            				</td> 
				            				<td class="text11" valign="bottom">
											&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnt" id="tvnt" size="5" maxlength="5" value="${model.record.tvnt}">
										</td>
										<td class="text11" valign="bottom">
											&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnteh" id="tvnteh" size="5" maxlength="5" value="${model.record.tvnteh}">
										</td>
																				
										<td class="text12" align="left" colspan="2"><input type="text" class="inputTextMediumBlueMandatoryField" name="tvvt" id="tvvt" size="40" maxlength="70" value="${model.record.tvvt}"></td>
							            
							            <td class="text12" align="left"><button name="itemDescriptionExtraInformationButton" class="buttonGray" type="button" onClick="showPop('itemDescriptionExtraInformation');" >Mere...</button> 
								        <span style="position:absolute; left:580px; top:500px; width:350px; height:300px;" id="itemDescriptionExtraInformation" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
								           				<tr>
										           			<td class="text12">
																<b>Flere varebeskrivningar</b>
															</td>
														</tr>
														
									           			<tr>
										           			<td class="text11">
																&nbsp;31.2 Varebeskrivelse<input type="text" class="inputText" name="tvvt2" id="tvvt2" size="35" maxlength="70" value="${model.record.tvvt2}">
															</td>
														</tr>
														<tr>
										           			<td class="text11">
																&nbsp;31.3 Varebeskrivelse<input type="text" class="inputText" name="tvvt3" id="tvvt3" size="35" maxlength="70" value="${model.record.tvvt3}">
															</td>
														</tr>
														<tr>
										           			<td class="text11">
																&nbsp;31.4 Varebeskrivelse<input type="text" class="inputText" name="tvvt4" id="tvvt4" size="35" maxlength="70" value="${model.record.tvvt4}">
															</td>
														</tr>
														<tr>
										           			<td class="text11">
																&nbsp;31.5 Varebeskrivelse<input type="text" class="inputText" name="tvvt5" id="tvvt5" size="35" maxlength="70" value="${model.record.tvvt5}">
															</td>
														</tr>
														
								           			</table>
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td class="text11"><button name="itemDescriptionExtraInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('itemDescriptionExtraInformation');">&nbsp;<spring:message code="systema.tvinn.sad.ok"/></button> 
															</td>
														</tr>
													</table>
												</div>
										</span>
										</td>
										
										<td class="text12" align="left" colspan="2">
											<select name="tvvtsk" id="tvvtsk">
								            		<option value="">-velg-</option>
							 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvmsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
												</c:forEach>
											</select>
							            	<a tabindex="-1" id="tvvtskIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
										</td>
							            
 							        </tr>
 							        
 							        <tr height="10"><td class="text" align="left" colspan="9"><hr></td></tr>
									<tr>
										<td class="text12" align="left" colspan="2">
							            <img onMouseOver="showPop('31_gods_info');" onMouseOut="hidePop('31_gods_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.</b><span title="tvmn" >Kolli merke&nbsp;</span>
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_gods_info" class="popupWithInputText text11"  >
						           			<br/>
						           			<b>Kolli merke</b>
											<br/>
						           			Här anger du hur de kollin som varorna är förpackade i är märkta, obligatoriskt för emballerat gods. Fältet är frivilligt för bulkvaror och oemballerat gods.

										</span>
										</div>
										</td>
										<td class="text12" align="left" >
										<b>31.</b><span title="tvmnsk" >Kolli merke.språk&nbsp;</span></td>
										<td>&nbsp;</td>
										
							        </tr>
							        <tr>
							        	<td class="text11" colspan="2">
											&nbsp;<input type="text" class="inputText" name="tvmn" id="tvmn" size="17" maxlength="17" value='${model.record.tvmn}'>
										</td>
										<td >
											<select name="tvmnsk" id="tvmnsk">
							            			<option value="">-velg-</option>
							 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvmsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
												</c:forEach>
											</select>										           				
						            		<a tabindex="-1" id="tvmnskIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
										</td>
										
					           			<td>&nbsp;</td>
										
							            <td align="left">
											<c:choose>	
												<c:when test="${model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">
													<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.tvinn.sad.ncts.export.item.createnew.submit"/>'>
												</c:when>
												<c:otherwise>
						 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='<spring:message code="systema.tvinn.sad.submit.not.editable"/>'/>
						 				    		</c:otherwise>	
					 				    		</c:choose>	
											
							            </td>
 							        </tr>
			        	        </table>
					        </td>
					        
				        </tr>
				        <tr height="10"><td></td></tr>
					</table>          
            	</td>
           	</tr> 
           	<%-- --------------------------------------------------- --%>
           	<%-- DETAIL Section - Create Item line SECONDARY SECTION --%>
           	<%-- --------------------------------------------------- --%>
           	<tr height="10"><td></td></tr>
            <tr>
	 			<td >		
	 				<%-- Item header --%>
	 				<table width="100%" align="center" class="secondarySectionHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
				 				<b>&nbsp;&nbsp;Varelinje&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Supplerende opplysninger&nbsp;</b>
				 				<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				
			 				</td>
		 				</tr>
	 				</table>
					<%-- Item input --%>
				 	<table width="100%" align="center" class="secondarySectionFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="8"><td class="text" align="left"></td></tr>
				 		
				 		<tr>
		            		<td class="text12" align="left" >&nbsp;&nbsp;&nbsp;&nbsp;
		            		<img onMouseOver="showPop('tvtdn2_info');" onMouseOut="hidePop('tvtdn2_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		            		<span title="tvavd2/tvtdn2" >Oppdrag</span>&nbsp;&nbsp;
		            			<input type="text" class="inputTextMediumBlue" name="tvavd2" id="tvavd2" size="4" maxlength="4" value="${model.record.tvavd2}">
		            			<input type="text" class="inputTextMediumBlue" name="tvtdn2" id="tvtdn2" size="8" maxlength="7" value="${model.record.tvtdn2}">
		            			<a tabindex="-1" id="tvtdn2IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" border="0" alt="search" >
								</a>
		            		<div class="text11" style="position: relative;" align="left">
							<span style="position:absolute;top:2px; width:250px;" id="tvtdn2_info" class="popupWithInputText text11"  >
			           			Hvis deklarasjonen er basert på et oppdrag/eksportdekl., vises avd./oppdragsnr. her.
 							</span>
							</div>	
		            			
		            		</td>
		            	</tr>
		            	 
		        		<tr height="5"><td class="text" align="left"></td></tr>
				 		<tr>
			 				<%-- SENDER --%>
				 			<td >		
				 				<table width="95%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="15">
							 			<td class="text12White">
											&nbsp;<img onMouseOver="showPop('2_info');" onMouseOut="hidePop('2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 			<b>&nbsp;2.</b>Avsender&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
							 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="2_info" class="popupWithInputText text11"  >
							           			Begreppet exportör används vid export då gemenskapsvaror förs ut fra EU:s tullområde till tredjeland.
												Begreppet avsändare används vid: 
								           		<ul>
								           			<li>utförsel av gemenskapsvaror till länder och områden inom EU som valt att stå utanför skatteunionen, till exempel Åland, samt</li>
								           			<li>återexport, det vill säga då icke-gemenskapsvaror återutförs.</li>
								           		</ul>
											</span>
											</div>
						 				</td>
					 				</tr>
				 				</table>
				 			</td>
				 			
			 				<%-- RECEIVER --%>
							<td>		
				 				<table width="95%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="15">
							 			<td class="text12White">
							 				&nbsp;<img onMouseOver="showPop('8_info');" onMouseOut="hidePop('8_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<b>&nbsp;8.</b>Mottaker&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
							 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="8_info" class="popupWithInputText text11"  >
							           			<br>
							           			<ul>
								           			<li>Här anger du det fullständiga namnet och adressen på varumottagaren, oftast den som köpt varorna.
								           				Observera att även postnummer eller motsvarande ska anges.</li>
								           			<li>I de fall mottagaren har ett EORI-nummer kan du ange det.</li>
								           		</ul>
											</span>
											</div>
						 				</td>
					 				</tr>
				 				</table>
				 			</td>
				 		</tr>
				 		
				 		<tr>
			 				<%-- SENDER --%>
					 		<td >
						 		<table width="95%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		
							        <tr>
							        	<%-- ================================================================================== --%>
							        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
							        		 These original values will be used when the user clicks "Cancel" buttons (puttting
							        		 back original value)																--%> 
							        	<%-- ================================================================================== --%>
							        	<input type="hidden" name="orig_tvkns" id="orig_tvkns" value='${model.record.tvkns}'>
							        	<input type="hidden" name="orig_tvnas" id="orig_tvnas" value='${model.record.tvnas}'>
							        	<input type="hidden" name="orig_tvtins" id="orig_tvtins" value='${model.record.tvtins}'>
							        	<input type="hidden" name="orig_tvads1" id="orig_tvads1" value='${model.record.tvads1}'>
							        	<input type="hidden" name="orig_tvpns" id="orig_tvpns" value='${model.record.tvpns}'>
							        	<input type="hidden" name="orig_tvpss" id="orig_tvpss" value='${model.record.tvpss}'>
							        	<input type="hidden" name="orig_tvlks" id="orig_tvlks" value='${model.record.tvlks}'>
							        	<input type="hidden" name="orig_tvsks" id="orig_tvsks" value='${model.record.tvsks}'>
							        	
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="tvkns" >Kundenr.</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="tvnas" >Navn&nbsp;</span>
								            <a tabindex="-1" id="tvnasIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
										</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvkns" id="tvkns" size="8" maxlength="8" value="${model.record.tvkns}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvnas" id="tvnas" size="20" maxlength="35" value="${model.record.tvnas}"></td>
							        </tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="tvtins" >TIN</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvtins" id="tvtins" size="20" maxlength="17" value="${model.record.tvtins}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="tvads1" >Adresse</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvads1" id="tvads1" size="30" maxlength="35" value="${model.record.tvads1}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr>
							        	<td>
							        		<table>
							        		<tr>
							            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="tvpns" >Postnummer</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="tvpss" >Postadresse</span></td>
							            	</tr>
							        		<tr>
							            		<td align="left"><input type="text" class="inputTextMediumBlue" name="tvpns" id="tvpns" size="10" maxlength="9" value="${model.record.tvpns}"></td> 
							            		<td align="left"><input type="text" class="inputTextMediumBlue" name="tvpss" id="tvpss" size="10" maxlength="35" value="${model.record.tvpss}"></td> 
							        		</tr>    	
							            	</table>
							            </td>
							            <td>
							            	<table>
							        		<tr>
							            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="tvlks" >Landkode</span>
							            		
							            		</td>
							            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="tvsks" >Språkkode</span>
							            		
							            		</td>
							            	</tr>
							        		<tr>
							            		<td align="left">
							            			<select name="tvlks" id="tvlks">
									            		<option value="">-velg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.zkod}"<c:if test="${model.record.tvlks == country.zkod}"> selected </c:if> >${country.zkod}</option>
													</c:forEach> 
												</select>
												<a tabindex="-1" id="tvlksIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
							            		</td> 
							            		<td align="left">
							            			<select name="tvsks" id="tvsks">
													<option value="">-velg-</option>	
								 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
				                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvsks == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
													</c:forEach>												
												</select>
												<a tabindex="-1" id="tvsksIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
											</td> 
							        		</tr>    	
							            	</table>
							            </td>
							        </tr>
							        <tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							        </tr>
						        </table>
					        </td>

			 				<%-- RECEIVER --%>
					 		<td >
						 		<table width="95%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
							        	<%-- ================================================================================== --%>
							        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
							        		 These original values will be used when the user clicks "Cancel" buttons (puttting
							        		 back original value)																--%> 
							        	<%-- ================================================================================== --%>
							        	<input type="hidden" name="orig_tvknk" id="orig_tvknk" value='${model.record.tvknk}'>
							        	<input type="hidden" name="orig_tvnak" id="orig_tvnak" value='${model.record.tvnak}'>
							        	<input type="hidden" name="orig_tvtink" id="orig_tvtink" value='${model.record.tvtink}'>
							        	<input type="hidden" name="orig_tvadk1" id="orig_tvadk1" value='${model.record.tvadk1}'>
							        	<input type="hidden" name="orig_tvpnk" id="orig_tvpnk" value='${model.record.tvpnk}'>
							        	<input type="hidden" name="orig_tvpsk" id="orig_tvpsk" value='${model.record.tvpsk}'>
							        	<input type="hidden" name="orig_tvlkk" id="orig_tvlkk" value='${model.record.tvlkk}'>
							        	<input type="hidden" name="orig_tvskk" id="orig_tvskk" value='${model.record.tvskk}'>
							        	
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="tvknk" >Kundenr.</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="tvnak" >Navn&nbsp;</span>
								            <a tabindex="-1" id="tvnakIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>	
										</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvknk" id="tvknk" size="8" maxlength="8" value="${model.record.tvknk}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvnak" id="tvnak" size="20" maxlength="35" value="${model.record.tvnak}"></td>
							        </tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="tvtink" >TIN</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvtink" id="tvtink" size="20" maxlength="17" value="${model.record.tvtink}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="tvadk1" >Adresse</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvadk1" id="tvadk1" size="30" maxlength="35" value="${model.record.tvadk1}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr>
							        	<td>
							        		<table>
							        		<tr>
							            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="tvpnk" >Postnummer&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="tvpsk" >Postadresse</span></td>
							            	</tr>
							        		<tr>
							            		<td align="left"><input type="text" class="inputTextMediumBlue" name="tvpnk" id="tvpnk" size="10" maxlength="9" value="${model.record.tvpnk}"></td> 
							            		<td align="left"><input type="text" class="inputTextMediumBlue" name="tvpsk" id="tvpsk" size="10" maxlength="35" value="${model.record.tvpsk}"></td> 
							        		</tr>    	
							            	</table>
							            </td>
							            <td>
							            	<table>
							        		<tr>
							            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="tvlkk" >Landkode</span>
							            		
							            		
							            		</td>
							            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="tvskk" >Språkkode</span>
							            		
							            		</td>
							            	</tr>
							        		<tr>
							            		<td align="left">
							            			<select name="tvlkk" id="tvlkk">
									            		<option value="">-velg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.zkod}"<c:if test="${model.record.tvlkk == country.zkod}"> selected </c:if> >${country.zkod}</option>
													</c:forEach> 
												</select>
												<a tabindex="-1" id="tvlkkIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
							            		</td> 
							            		<td align="left">
							            			<select name="tvskk" id="tvskk">
									            		<option value="">-velg-</option>
								 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
				                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvmsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
													</c:forEach>
												</select>
												<a tabindex="-1" id="tvskkIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
											</td> 
							        		</tr>    	
							            	</table>
							            </td>
							        </tr>
							        <tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							        </tr>
						        </table>
					        </td>
				        </tr>
				        
				 		<!-- to be desided if this section (below) has big changes or not (waiting for Christer...) -->
				 		<tr height="15"><td class="text" align="left"></td></tr>
				 		
				 		<tr>
					 		<td width="50%">
						 		<table border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td colspan="2" class="text12" align="left">
							            &nbsp;<img onMouseOver="showPop('44_info');" onMouseOut="hidePop('44_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>44.</b>Certifikat og bevilling
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="44_info" class="popupWithInputText text11"  >
							           			<br/>
							           			<b>Certifikat og bevilling</b>
												<br/>
							           			This box shall contain information necessary in respect of special regulations that
												might apply in the country of dispatch/export together with the reference numbers of the documents produced in support of 
												the declaration (e.g. the serial number of the T5 182 control copy, the export licence or permit number, the data required under 
												veterinary and phytosanitary regulations, the bill of lading number, etc.).<br/><br/>
												<br/><br/>
												In the case of goods involving greater risk of fraud, or where the customs authorities or the principal consider it necessary, an itinerary shall be prescribed. Details of at least
												the countries to be transited shall be entered. Where an exemption from the requirement of a prescribed itinerary has been authorised, the endorsment ‘Prescribed
												itinerary waived’ shall be entered. The sub - division ‘ “Additional information code” (AI) shall not be completed. 
												<br/><br/>
										</span>
										</div>
										</td>
										
							           	<td class="text12" align="left">&nbsp;</td>
							        </tr>
							        
							        
							        <tr height="5"><td class="text" align="left"></td></tr>
									<tr>
							            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Supplerende opplysninger&nbsp;</td>
							           	<td class="text12" align="left"><button name="specialInformationCodeButton" class="buttonGray" type="button" onClick="showPop('specialInformationCode');" >Mere...</button> 
								        <span style="position:absolute; left:280px; top:800px; width:650px; height:580px;" id="specialInformationCode" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table border="0" cellspacing="1" cellpadding="0">
								           			<tr>
								           			<td>
								           				<table class="lightGrayBg" >
								           				<tr>
										           			<td class="text11" colspan="2">
										           				<b>44.Supplerende opplysninger (2)</b>
										           			</td>
										        			</tr>
														<tr>
										           			<td class="text11">&nbsp;<span title="tvdty2" >Dok.type</span></td>
										           			<td class="text11">&nbsp;<span title="tvdref2" >Dok.ref.</span></td>
															<td class="text11">&nbsp;<span title="tvdsk2" >Dok.språk</span></td>
															
															<td class="text11">&nbsp;<span title="tvdo2" >Supplerende Opplysn.</span></td>
															<td class="text11">&nbsp;<span title="tvdosk2" >Språk</span></td>
														</tr>
									           			<tr>
										           			<td class="text11" nowrap >
																<select name="tvdty2" id="tvdty2">
												            		<option value="">-velg-</option>
												 				  	<c:forEach var="code" items="${model.ncts013_DocType_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdty2 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>	
										           			</td>
										           			<td class="text12" align="left"><input type="text" class="inputText" name="tvdref2" id="tvdref2" size="10" maxlength="20" value="${model.record.tvdref2}"></td>										           			
										           			
							            			   			<td class="text11" nowrap >&nbsp;
											            			<select name="tvdsk2" id="tvdsk2">
												            			<option value="">-velg-</option>
												 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvmsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
																	</c:forEach>
																</select>
												            	
										           			</td>
										           			<td class="text11" nowrap >
										           				&nbsp;<input type="text" class="inputText" name="tvdo2" id="tvdo2" size="20" maxlength="26" value="${model.record.tvdo2}">										           			
										           			</td>
							            			   			<td class="text11" nowrap >&nbsp;
											            			<select name="tvdosk2" id="tvdosk2">
													            		<option value="">-velg-</option>
												 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvmsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
																	</c:forEach> 
																</select>
												            	
										           			</td>
										           			
					           							</tr>
					           							<tr>
										           			<td class="text11">&nbsp;<span title="tvtlo2" >Kode [Supplerende opplysninger]</span></td>
										           			<td class="text11">&nbsp;</td>
										           			<td class="text11">&nbsp;<span title="tvexkd2" >Eksport fra EF</span></td>
															<td class="text11">&nbsp;<span title="tvexlk2" >Eksport fra land</span></td>
															
														</tr>
									           			<tr>
										           			<td class="text11" colspan="2" >
																<select name="tvtlo2" id="tvtlo2">
												 				  <option selected value="">-velg-</option>
																  <option value="CAL"<c:if test="${model.record.tvtlo2 == 'CAL'}"> selected </c:if> >CAL [Skuldbelopp]</option>
																  <option value="DG0"<c:if test="${model.record.tvtlo2 == 'DG0'}"> selected </c:if> >DG0 [Export med restriktioner]</option>
																  <option value="DG1"<c:if test="${model.record.tvtlo2 == 'DG1'}"> selected </c:if> >DG1 [Export med avgift]</option>
																  <option value="DG2"<c:if test="${model.record.tvtlo2 == 'DG2'}"> selected </c:if> >DG2 [Export]</option>
															  	</select>	
										           			</td>
										           			<td class="text11" >&nbsp;
																<select name="tvexkd2" id="tvexkd2">
												 				  <option value=""<c:if test="${model.record.tvexkd2 == '0' || model.record.tvexkd2 == ''}"> selected </c:if> >0</option>
																  <option value="1"<c:if test="${model.record.tvexkd2 == '1'}"> selected </c:if> >1</option>
															  	</select>	
										           			</td>
										           			
							            			   			<td class="text11" nowrap >&nbsp;
											            			<select name="tvexlk2" id="tvexlk2">
											 						<option value="">-velg-</option>
												 				  	<c:forEach var="country" items="${model.countryCodeList}" >
								                                	 	<option value="${country.zkod}"<c:if test="${model.record.tvexlk2 == country.zkod}"> selected </c:if> >${country.zkod}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
										           			
					           							</tr>
					           							<tr>
										           			<td class="text11" colspan="3">&nbsp;<span title="tvmtxt2" >Yderligere opplysninger</span></td>
															<td class="text11">&nbsp;<span title="tvmsk2" >Språk</span>
															</td>
															
														</tr>
									           			<tr>
										           			<td class="text11" colspan="3" >
										           				&nbsp;<input type="text" class="inputText" name="tvmtxt2" id="tvmtxt2" size="25" maxlength="70" value="${model.record.tvmtxt2}">										           			
										           			</td>
										           			<td class="text11" nowrap >&nbsp;
											            			<select name="tvmsk2" id="tvmsk2">
													            		<option value="">-velg-</option>
												 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvmsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
																	</c:forEach>
																</select>
												            	
																
										           			</td>
					           							</tr>					           															           			
					           							</table>
					           						</td>
					           						</tr>
					           						
					           						<tr height="5"><td class="text" align="left"></td></tr>
					           						
					           						<tr>
								           			<td>
								           				<table class="grayBg">
								           				<tr>
										           			<td class="text11" colspan="2">
										           				<b>44.Supplerende opplysninger (3)</b>
										           			</td>
										        			</tr>
														<tr>
										           			<td class="text11">&nbsp;<span title="tvdty3" >Dok.type</span></td>
										           			<td class="text11">&nbsp;<span title="tvdref3" >Dok.ref.</span></td>
															<td class="text11">&nbsp;<span title="tvdsk3" >Dok.språk</span></td>
															<td class="text11">&nbsp;<span title="tvdo3" >Supplerende Opplysn.</span></td>
															<td class="text11">&nbsp;<span title="tvdosk3" >Språk</span></td>
														</tr>
									           			<tr>
										           			<td class="text11" nowrap>
																<select name="tvdty3" id="tvdty3">
												            		<option value="">-velg-</option>
												 				  	<c:forEach var="code" items="${model.ncts013_DocType_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdty3 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>	
										           			</td>
										           			<td class="text12" align="left"><input type="text" class="inputText" name="tvdref3" id="tvdref3" size="10" maxlength="20" value="${model.record.tvdref3}"></td>										           			
										           			
							            			   			<td class="text11" nowrap >&nbsp;
											            			<select name="tvdsk3" id="tvdsk3">
												            			<option value="">-velg-</option>
												 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvmsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
																	</c:forEach>
																</select>
												            		
										           			</td>
										           			<td class="text11" nowrap >
										           				&nbsp;<input type="text" class="inputText" name="tvdo3" id="tvdo3" size="20" maxlength="26" value="${model.record.tvdo3}">										           			
										           			</td>
							            			   			<td class="text11" nowrap >&nbsp;
											            			<select name="tvdosk3" id="tvdosk3">
													            		<option value="">-velg-</option>
												 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvmsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
										           			
					           							</tr>
					           							<tr>
										           			<td class="text11">&nbsp;<span title="tvtlo3" >Kode [Supplerende opplysninger]</span></td>
										           			<td class="text11">&nbsp;</td>
										           			<td class="text11">&nbsp;<span title="tvexkd3" >Eksport fra EF</span></td>
															<td class="text11">&nbsp;<span title="tvexlk3" >Eksport fra land</span></td>
															
														</tr>
									           			<tr>
										           			<td class="text11" colspan="2" >
																<select name="tvtlo3" id="tvtlo3">
												 				  <option selected value="">-velg-</option>
																  <option value="CAL"<c:if test="${model.record.tvtlo3 == 'CAL'}"> selected </c:if> >CAL [Skuldbelopp]</option>
																  <option value="DG0"<c:if test="${model.record.tvtlo3 == 'DG0'}"> selected </c:if> >DG0 [Export med restriktioner]</option>
																  <option value="DG1"<c:if test="${model.record.tvtlo3 == 'DG1'}"> selected </c:if> >DG1 [Export med avgift]</option>
																  <option value="DG2"<c:if test="${model.record.tvtlo3 == 'DG2'}"> selected </c:if> >DG2 [Export]</option>
															  	</select>	
										           			</td>
										           			<td class="text11" >&nbsp;
																<select name="tvexkd3" id="tvexkd3">
												 				  <option value=""<c:if test="${model.record.tvexkd3 == '0' || model.record.tvexkd3 == ''}"> selected </c:if> >0</option>
																  <option value="1"<c:if test="${model.record.tvexkd3 == '1'}"> selected </c:if> >1</option>
															  	</select>	
										           			</td>
										           			
							            			   			<td class="text11" nowrap >&nbsp;
											            			<select name="tvexlk3" id="tvexlk3">
											 						<option value="">-velg-</option>
												 				  	<c:forEach var="country" items="${model.countryCodeList}" >
								                                	 	<option value="${country.zkod}"<c:if test="${model.record.tvexlk3 == country.zkod}"> selected </c:if> >${country.zkod}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
										           			
					           							</tr>
					           							<tr>
										           			<td class="text11" colspan="3">&nbsp;<span title="tvmtxt3" >Yderligere opplysninger</span></td>
															<td class="text11">&nbsp;<span title="tvmsk3" >Språk</span></td>
														</tr>
									           			<tr>
										           			<td class="text11" colspan="3" >
										           				&nbsp;<input type="text" class="inputText" name="tvmtxt3" id="tvmtxt3" size="25" maxlength="70" value="${model.record.tvmtxt3}">										           			
										           			</td>
										           			<td class="text11" nowrap >&nbsp;
											            			<select name="tvmsk3" id="tvmsk3">
													            		<option value="">-velg-</option>
												 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvmsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
																	</c:forEach>
																</select>
												            		
																
										           			</td>
					           							</tr>					           															           			
					           							</table>
					           						</td>
					           						</tr>
					           						
					           						<tr height="5"><td class="text" align="left"></td></tr>
					           						
					           						<tr>
								           			<td>
								           				<table class="lightGrayBg">
								           				<tr>
										           			<td class="text11" colspan="2">
										           				<b>44.Supplerende opplysninger (4)</b>
										           			</td>
										        			</tr>
														<tr>
										           			<td class="text11">&nbsp;<span title="tvdty4" >Dok.type</span></td>
										           			<td class="text11">&nbsp;<span title="tvdref4" >Dok.ref.</span></td>
															<td class="text11">&nbsp;<span title="tvdsk4" >Dok.språk</span></td>
															<td class="text11">&nbsp;<span title="tvdo4" >Supplerende Opplysn.</span></td>
															<td class="text11">&nbsp;<span title="tvdosk4" >Språk</span></td>
														</tr>
									           			<tr>
										           			<td class="text11" nowrap>
																<select name="tvdty4" id="tvdty4">
												            		<option value="">-velg-</option>
												 				  	<c:forEach var="code" items="${model.ncts013_DocType_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdty4 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>	
										           			</td>
										           			<td class="text12" align="left"><input type="text" class="inputText" name="tvdref4" id="tvdref4" size="10" maxlength="20" value="${model.record.tvdref4}"></td>										           			
										           			
							            			   			<td class="text11" nowrap >&nbsp;
											            			<select name="tvdsk4" id="tvdsk4">
												            			<option value="">-velg-</option>
												 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvmsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
																	</c:forEach>
																</select>
												            		
										           			</td>
										           			<td class="text11" nowrap >
										           				&nbsp;<input type="text" class="inputText" name="tvdo4" id="tvdo4" size="20" maxlength="26" value="${model.record.tvdo4}">										           			
										           			</td>
							            			   			<td class="text11" nowrap >&nbsp;
											            			<select name="tvdosk4" id="tvdosk4">
													            		<option value="">-velg-</option>
												 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvmsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
										           			
					           							</tr>
					           							<tr>
										           			<td class="text11">&nbsp;<span title="tvtlo4" >Kode [Supplerende opplysninger]</span></td>
										           			<td class="text11">&nbsp;</td>
										           			<td class="text11">&nbsp;<span title="tvexkd4" >Eksport fra EF</span></td>
															<td class="text11">&nbsp;<span title="tvexlk4" >Eksport fra land</span></td>
															
														</tr>
									           			<tr>
										           			<td class="text11" colspan="2" >
																<select name="tvtlo4" id="tvtlo4">
												 				  <option selected value="">-velg-</option>
																  <option value="CAL"<c:if test="${model.record.tvtlo4 == 'CAL'}"> selected </c:if> >CAL [Skuldbelopp]</option>
																  <option value="DG0"<c:if test="${model.record.tvtlo4 == 'DG0'}"> selected </c:if> >DG0 [Export med restriktioner]</option>
																  <option value="DG1"<c:if test="${model.record.tvtlo4 == 'DG1'}"> selected </c:if> >DG1 [Export med avgift]</option>
																  <option value="DG2"<c:if test="${model.record.tvtlo4 == 'DG2'}"> selected </c:if> >DG2 [Export]</option>
															  	</select>	
										           			</td>
										           			<td class="text11" >&nbsp;
																<select name="tvexkd4" id="tvexkd4">
												 				  <option value=""<c:if test="${model.record.tvexkd4 == '0' || model.record.tvexkd4 == ''}"> selected </c:if> >0</option>
																  <option value="1"<c:if test="${model.record.tvexkd4 == '1'}"> selected </c:if> >1</option>
															  	</select>	
										           			</td>
										           			
							            			   			<td class="text11" nowrap >&nbsp;
											            			<select name="tvexlk4" id="tvexlk4">
											 						<option value="">-velg-</option>
												 				  	<c:forEach var="country" items="${model.countryCodeList}" >
								                                	 	<option value="${country.zkod}"<c:if test="${model.record.tvexlk4 == country.zkod}"> selected </c:if> >${country.zkod}</option>
																	</c:forEach> 
																</select>
										           			</td>
					           							</tr>
					           							<tr>
										           			<td class="text11" colspan="3">&nbsp;<span title="tvmtxt4" >Yderligere opplysninger</span></td>
															<td class="text11">&nbsp;<span title="tvmsk4" >Språk</span></td>
														</tr>
									           			<tr>
										           			<td class="text11" colspan="3" >
										           				&nbsp;<input type="text" class="inputText" name="tvmtxt4" id="tvmtxt4" size="25" maxlength="70" value="${model.record.tvmtxt4}">										           			
										           			</td>
										           			<td class="text11" nowrap >&nbsp;
											            			<select name="tvmsk4" id="tvmsk4">
													            		<option value="">-velg-</option>
												 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvmsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
																	</c:forEach>
																</select>
												            		
										           			</td>
					           							</tr>					           															           			
					           							</table>
					           						</td>
					           						</tr>
					           						
					           						
					           						</table>
					           						
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td class="text11"><button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('specialInformationCode');">&nbsp;<spring:message code="systema.tvinn.sad.ok"/></button> 
															</td>
														</tr>
													</table>
													
												</div>
										</span>
										</td>
							        </tr>
							        
							        
							        <tr height="10"><td colspan="2" ></td></tr>
							        <tr>
							            <td class="text12" align="left">
							            &nbsp;<img onMouseOver="showPop('40_info');" onMouseOut="hidePop('40_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>40.</b>Summarisk angivelse / Forudgående dokument
							            
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="40_info" class="popupWithInputText text11"  >
						           			<br/>
						           			<b>Summarisk angivelse / Forudgående dokument</b>
											<br/>
						           			Give the reference for the previous customs destination or corresponding customs documents.
											In cases where more than one reference has to be entered, the word ‘Various’ may be entered in this box and a list of the references concerned may accompany the transit declaration.  
										</span>
										</div>
										</td>
							            
							           	<td class="text12" align="left"><button name="previousDocumentsButton" class="buttonGray" type="button" onClick="showPop('previousDocuments');" >Mere...</button> 
								        <span style="position:absolute; left:280px; top:800px; width:700px; height:200px;" id="previousDocuments" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table width="100%">
								           				<tr>
										           			<td class="text11" colspan="5">
										           				<b>Forudgående dokument</b>
										           			</td>
										        			</tr>	
								           				<tr>
										           			<td class="text11">&nbsp;<span title="tvtdt" >Dok.type</span></td>
										           			<td class="text11">&nbsp;<span title="tvtdr" >Dok.ref.</span></td>
															<td class="text11">&nbsp;<span title="tvtdsk" >Dok.språk</span></td>
															<td class="text11">&nbsp;<span title="tvtdo" >Opplysninger</span></td>
															<td class="text11">&nbsp;<span title="tvtdos" >Opplysn.språk</span></td>
														</tr>
									           			<tr>
										           			<td class="text11" nowrap >&nbsp;1.
										           				<select name="tvtdt" id="tvtdt">
												 				  <option selected value="">-velg-</option>
																  <option value="CO"<c:if test="${model.record.tvtdt == 'CO'}"> selected </c:if> >CO</option>
																  <option value="EU"<c:if test="${model.record.tvtdt == 'EU'}"> selected </c:if> >EU</option>
																  <option value="EX"<c:if test="${model.record.tvtdt == 'EX'}"> selected </c:if> >EX</option>
																  <option value="IM"<c:if test="${model.record.tvtdt == 'IM'}"> selected </c:if> >IM</option>
																  <option value="T-"<c:if test="${model.record.tvtdt == 'T-'}"> selected </c:if> >T-</option>
																  <option value="T1"<c:if test="${model.record.tvtdt == 'T1'}"> selected </c:if> >T1</option>
																  <option value="T2"<c:if test="${model.record.tvtdt == 'T2'}"> selected </c:if> >T2</option>
																  <option value="T2ATA"<c:if test="${model.record.tvtdt == 'T2ATA'}"> selected </c:if> >T2ATA</option>
																  <option value="T2CIM"<c:if test="${model.record.tvtdt == 'T2CIM'}"> selected </c:if> >T2CIM</option>
																  <option value="T2F"<c:if test="${model.record.tvtdt == 'T2F'}"> selected </c:if> >T2F</option>
																  <option value="T2L"<c:if test="${model.record.tvtdt == 'T2L'}"> selected </c:if> >T2L</option>
																  <option value="T2LF"<c:if test="${model.record.tvtdt == 'T2LF'}"> selected </c:if> >T2LF</option>
																  <option value="T2TIR"<c:if test="${model.record.tvtdt == 'T2TIR'}"> selected </c:if> >T2TIR</option>
																</select>
										           			</td>
										           			<td class="text11" nowrap >
										           				&nbsp;<input type="text" class="inputText" name="tvtdr" id="tvtdr" size="20" maxlength="20" value="${model.record.tvtdr}">										           			
										           			</td>
							            			   			<td class="text11" nowrap >&nbsp;
											            			<select name="tvtdsk" id="tvtdsk">
													            		<option value="">-velg-</option>
												 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvmsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
										           			<td class="text11" nowrap >
										           				&nbsp;<input type="text" class="inputText" name="tvtdo" id="tvtdo" size="20" maxlength="26" value="${model.record.tvtdo}">										           			
										           			</td>
							            			   			<td class="text11" nowrap >&nbsp;
											            			<select name="tvtdos" id="tvtdos">
													            		<option value="">-velg-</option>
												 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvmsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
																	</c:forEach>
																</select>
												            		
										           			</td>
										           			
										           		</tr>
										           		
									           			<tr>
										           			<tr>
										           			<td class="text11" nowrap >&nbsp;2.
										           				<select name="tvtdtTODO" id="tvtdtTODO">
												 				  <option selected value="">-velg-</option>
																  <option value="CO"<c:if test="${model.record.tvtdt == 'CO'}"> selected </c:if> >CO</option>
																  <option value="EU"<c:if test="${model.record.tvtdt == 'EU'}"> selected </c:if> >EU</option>
																  <option value="EX"<c:if test="${model.record.tvtdt == 'EX'}"> selected </c:if> >EX</option>
																  <option value="IM"<c:if test="${model.record.tvtdt == 'IM'}"> selected </c:if> >IM</option>
																  <option value="T-"<c:if test="${model.record.tvtdt == 'T-'}"> selected </c:if> >T-</option>
																  <option value="T1"<c:if test="${model.record.tvtdt == 'T1'}"> selected </c:if> >T1</option>
																  <option value="T2"<c:if test="${model.record.tvtdt == 'T2'}"> selected </c:if> >T2</option>
																  <option value="T2ATA"<c:if test="${model.record.tvtdt == 'T2ATA'}"> selected </c:if> >T2ATA</option>
																  <option value="T2CIM"<c:if test="${model.record.tvtdt == 'T2CIM'}"> selected </c:if> >T2CIM</option>
																  <option value="T2F"<c:if test="${model.record.tvtdt == 'T2F'}"> selected </c:if> >T2F</option>
																  <option value="T2L"<c:if test="${model.record.tvtdt == 'T2L'}"> selected </c:if> >T2L</option>
																  <option value="T2LF"<c:if test="${model.record.tvtdt == 'T2LF'}"> selected </c:if> >T2LF</option>
																  <option value="T2TIR"<c:if test="${model.record.tvtdt == 'T2TIR'}"> selected </c:if> >T2TIR</option>
																</select>
										           			</td>
										           			<td class="text11" nowrap >
										           				&nbsp;<input type="text" class="inputTextReadOnly" size="20" maxlength="20" value="NOT SUPPORTED">										           			
										           			</td>
							            			   			<td class="text11" nowrap >&nbsp;
											            			<select>
													            		<option value="">-velg-</option>
												 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvmsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
																	</c:forEach>
															</select>
										           			</td>
										           			<td class="text11" nowrap >
										           				&nbsp;<input type="text" class="inputTextReadOnly" size="26" maxlength="26" value="NOT SUPPORTED">										           			
										           			</td>
							            			   			<td class="text11" nowrap >&nbsp;
											            			<select >
													            		<option value="">-velg-</option>
												 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvmsk == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
																	</c:forEach> 
																</select>
										           			</td>
										           		</tr>
									           			<tr align="left" >
															<td colspan="5" ><button name="previousDocumentsButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('previousDocuments');">&nbsp;Ok</button> 
															</td>
														</tr>
								           			</table>								           			
												</div>
										</span>
										</td>
							        </tr>
							        
							        <tr height="10"><td colspan="2" ></td></tr>
							        
			        	        </table>
					        </td>
					        <td width="50%" valign="top">
						 		<table border="0" cellspacing="1" cellpadding="0">
						 			<tr>
							            <td class="text12" nowrap colspan="2" align="left"><b>&nbsp;31.</b>Kolli merke &nbsp;</td>
							           	<td class="text12" align="left">&nbsp;</td>
							        </tr>
							        
							        <tr>
							            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;Kolli merke &nbsp;</td>
							           	<td class="text12" align="left"><button name="goodsMarkButton" class="buttonGray" type="button" onClick="showPop('goodsMark');" >Mere...</button> 
								        <span style="position:absolute; left:480px; top:800px; width:450px; height:320px;" id="goodsMark" class="popupWithInputText"  >
								           		<div class="text10" align="left" valign="top">
								           			<table>
								           				<tr>
										           			<td class="text11">
										           				&nbsp;<span title="tvmn2/tvmn3/tvmn4..." >Kolli merke  [max 42 tegn]:</span>
										           			</td>
										           			<td class="text11">
																&nbsp;<span title="tveh2/tveh3/tveh4..." >Kolli slag [kode]</span>
															</td>
															<td class="text11">
																&nbsp;<span title="tvnt2/tvnt3/tvnt4..." >Kolli antall:</span>
															</td>
															<td class="text11">
																&nbsp;<span title="tvnteh2/tvnteh3/tvnteh4..." >Stykker:</span>
															</td>
															
															
														</tr>
									           			<tr>
										           			<td valign="top" class="text11">
										           				&nbsp;2.<textarea rows="2" cols="15" class="inputText" name="tvmn2" id="tvmn2" maxlength="42">${model.record.tvmn2}</textarea>
										           			</td>
										           			<td align="left" valign="bottom">
										            			<select name="tveh2" id="tveh2">
												            		<option value="">-velg-</option>
												 				  	<c:forEach var="code" items="${model.ncts017_Kolli_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tveh2 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>
																<a tabindex="-1" id="tveh2IdLink">
																	<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																</a>
									            				</td> 
															<td valign="bottom" class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnt2" id="tvnt2" size="5" maxlength="5" value="${model.record.tvnt2}">
															</td>
															<td valign="bottom" class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnteh2" id="tvnteh2" size="5" maxlength="5" value="${model.record.tvnteh2}">
															</td>
										           		</tr>
														<tr>
										           			<td valign="top" class="text11">
										           				&nbsp;3.<textarea rows="2" cols="15" class="inputText" name="tvmn3" id="tvmn3" maxlength="42">${model.record.tvmn3}</textarea>
										           			</td>
										           			<td align="left" valign="bottom">
										            			<select name="tveh3" id="tveh3">
												            		<option value="">-velg-</option>
												 				  	<c:forEach var="code" items="${model.ncts017_Kolli_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tveh3 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>
																<a tabindex="-1" id="tveh3IdLink">
																	<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																</a>
									            				</td> 
															<td valign="bottom" class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnt3" id="tvnt3" size="5" maxlength="5" value="${model.record.tvnt3}">
															</td>
															<td valign="bottom" class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnteh3" id="tvnteh3" size="5" maxlength="5" value="${model.record.tvnteh3}">
															</td>
										           		</tr>
									           			<tr>
										           			<td valign="top" class="text11">
										           				&nbsp;4.<textarea rows="2" cols="15" class="inputText" name="tvmn4" id="tvmn4" maxlength="42">${model.record.tvmn4}</textarea>
										           			</td>
										           			<td align="left" valign="bottom">
										            			<select name="tveh4" id="tveh4">
												            		<option value="">-velg-</option>
												 				  	<c:forEach var="code" items="${model.ncts017_Kolli_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tveh4 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>
																<a tabindex="-1" id="tveh4IdLink">
																	<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																</a>
									            				</td> 
															<td valign="bottom" class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnt4" id="tvnt4" size="5" maxlength="5" value="${model.record.tvnt4}">
															</td>
															<td valign="bottom" class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnteh4" id="tvnteh4" size="5" maxlength="5" value="${model.record.tvnteh4}">
															</td>
															
										           		</tr>
									           			<tr>
										           			<td valign="top" class="text11">
										           				&nbsp;5.<textarea rows="2" cols="15" class="inputText" name="tvmn5" id="tvmn5" maxlength="42">${model.record.tvmn5}</textarea>
										           			</td>
										           			<td align="left" valign="bottom">
										            			<select name="tveh5" id="tveh5">
												            		<option value="">-velg-</option>
												 				  	<c:forEach var="code" items="${model.ncts017_Kolli_CodeList}">
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tveh5 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>
																<a tabindex="-1" id="tveh5IdLink">
																	<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																</a>
									            				</td> 
															<td valign="bottom" class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnt5" id="tvnt5" size="5" maxlength="5" value="${model.record.tvnt5}">
															</td>
															<td valign="bottom" class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnteh5" id="tvnteh5" size="5" maxlength="5" value="${model.record.tvnteh5}">
															</td>
															
										           		</tr>
									           			
								           			</table>
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td  class="text12"><button name="goodsMarkButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('goodsMark');">&nbsp;<spring:message code="systema.tvinn.sad.ok"/></button> 
															</td>
														</tr>
													</table>
												</div>
										</span>
										</td>
							        </tr>
									<tr>
							            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;Container nr:&nbsp;</td>
							           	<td class="text12" align="left">
							           	<button name="containerNrButton" class="buttonGray" type="button" onClick="showPop('containerNrInfo');" >Mere...</button> 
								           	<span style="position:absolute; left:480px; top:800px; width:680px; height:200px;" id="containerNrInfo" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
								           			<tr>
									           			<td class="text11" colspan="5">
									           				<b><span title="tvcnr/tvcnr2/tvcnr3.../tvcnr6/svev_co07/svev_co08.../svev_co12" >Container nr</span></b>
									           			</td>
									        		</tr>
								           			<tr>
									           			<td class="text11">
									           				&nbsp;1.<input type="text" class="inputText" name="tvcnr" id="tvcnr" size="17" maxlength="17" value="${model.record.tvcnr}">
									           			</td>
														<td class="text11">
															&nbsp;2.<input type="text" class="inputText" name="tvcnr2" id="tvcnr2" size="17" maxlength="17" value="${model.record.tvcnr2}">
														</td>
														<td class="text11">
															&nbsp;3.<input type="text" class="inputText" name="tvcnr3" id="tvcnr3" size="17" maxlength="17" value="${model.record.tvcnr3}">
														</td>
														<td class="text11">
									           				&nbsp;4.<input type="text" class="inputText" name="tvcnr4" id="tvcnr4" size="17" maxlength="17" value="${model.record.tvcnr4}">
									           			</td>
													</tr>
													<tr>
									           			<td class="text11">
									           				&nbsp;5.<input type="text" class="inputText" name="tvcnr5" id="tvcnr5" size="17" maxlength="17" value="${model.record.tvcnr5}">
									           			</td>
														<td class="text11">
															&nbsp;6.<input type="text" class="inputText" name="tvcnr6" id="tvcnr6" size="17" maxlength="17" value="${model.record.tvcnr6}">
														</td>
														<td class="text11">
															&nbsp;7.<input type="text" class="inputTextReadOnly" name="svev_co07" id="svev_co07" size="17" maxlength="17" value="NOT SUPPORTED">
														</td>
														<td class="text11">
									           				&nbsp;8.<input type="text" class="inputTextReadOnly" name="svev_co08" id="svev_co08" size="17" maxlength="17" value="NOT SUPPORTED">
									           			</td>
													</tr>
													<tr>
									           			<td class="text11">
									           				&nbsp;9.<input type="text" class="inputTextReadOnly" name="svev_co09" id="svev_co09" size="17" maxlength="17" value="NOT SUPPORTED">
									           			</td>
														<td class="text11">
															10.<input type="text" class="inputTextReadOnly" name="svev_co10" id="svev_co10" size="17" maxlength="17" value="NOT SUPPORTED">
														</td>
														<td class="text11">
															11.<input type="text" class="inputTextReadOnly" name="svev_co11" id="svev_co11"  size="17" maxlength="17" value="NOT SUPPORTED">
														</td>
														<td class="text11">
									           				12.<input type="text" class="inputTextReadOnly" name="svev_co12" id="svev_co12" size="17" maxlength="17" value="NOT SUPPORTED">
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
							        <tr height="10"><td></td></tr>
							        
							        <tr>
							            <td colspan="2" class="text12" align="left">&nbsp;&nbsp;<b>Følsomme Varer</b>&nbsp;</td>
							        </tr>
							        <tr>
							            <td colspan="2" class="text12" align="left">
							            		&nbsp;
							            		<span title="tvfv">Kode:</span>&nbsp;<input readonly type="text" class="inputTextReadOnly" name="tvfv" id="tvfv" size="3" maxlength="2" value="${model.record.tvfv}">
							            		&nbsp;&nbsp;<span title="tvfvnt">Antall:</span>&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvfvnt" id="tvfvnt" size="15" maxlength="11" value="${model.record.tvfvnt}">
							            </td>
							        </tr>
							</table>
					        </td>
				        </tr>
						<tr height="10"><td></td></tr>
						
						
						
						<!--  SIKKERHET HERE ! -->
				<tr>
				<td colspan="2">&nbsp;&nbsp;
		            	<table width="99%" align="center" class="tableBorderWithRoundCornersDarkRed" border="0" cellspacing="2" cellpadding="0">
		            		<tr>
							<td width="100%" align="left" valign="top" class="text12" >
								<table width="50%" align="left" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="18px">
							 			<td class="text14">
							 				&nbsp;<img onMouseOver="showPop('sikkerhet_info');" onMouseOut="hidePop('sikkerhet_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									        	<b>Sikkerhet</b>&nbsp;Arbeid med forhåndsvarsling
									        <div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="sikkerhet_info" class="popupWithInputText text11"  >
					           					TODO
											</span>
											</div>	
						 				</td>
					 				</tr>
					 				<tr>
				            			<td class="text12">
				            				<img onMouseOver="showPop('betalmade_info');" onMouseOut="hidePop('betalmade_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				            				<span title="tvtkbm">Transp.kost/Betal.måte</span>&nbsp;
				            				<select name="tvtkbm" id="tvtkbm">
				            				<option value="">-velg-</option>
				 				  			<c:forEach var="code" items="${model.ncts116_BetalningTransport_CodeList}" >
                             	 				<option value="${code.tkkode}"<c:if test="${model.record.tvtkbm == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
											</c:forEach> 
										</select>
										<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="betalmade_info" class="popupWithInputText text11"  >
							           			<ul>
							           				<c:forEach var="code" items="${model.ncts116_BetalningTransport_CodeList}" >
								 				  		<li><b>${code.tkkode}</b>&nbsp;${code.tktxtn}</li>
							 				  		</c:forEach>	
							           			</ul>
										</span>
										</div>
				            			</td>
				            			<td class="text12">
				            				<img onMouseOver="showPop('farliggods_info');" onMouseOut="hidePop('farliggods_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				            				<span title="tvfgkd">Farlig gods KD</span>&nbsp;
				            				<input type="text" class="inputTextMediumBlue" name="tvfgkd" id="tvfgkd" size="5" maxlength="4" value="${model.record.tvfgkd}">
											<a title="UN-Dangerous Goods List" tabindex=-1 href="renderLocalPdf.do?fn=UN_dangerousGoods.pdf" target="_blank">
 												<img style="vertical-align:middle;" width="16px" height="16px" src="resources/images/pdf.png" border="0" alt="pdf">
 											</a>
										<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="farliggods_info" class="popupWithInputText text11"  >
							           			Refer to UN-list of dangerous goods
										</span>
										</div>
										
										</td>
				            		</tr>
				            		<tr height="5"><td></td></tr>
				 					<tr>
				            			<td class="text12">&nbsp;<span title="tvkref">Kommersielt ref.nr</span></td>
				            		</tr>
				            		<tr>
				            			<td colspan="2"><input type="text" class="inputTextMediumBlue" name="tvkref" id="tvkref" size="70" maxlength="70" value="${model.record.tvkref}"></td>
				            		</tr>
				        		</table>
					        </td>
				            
						</tr>
		            		<tr height="3"><td>&nbsp;</td></tr>
				        	<tr>
				        		<td width="50%" align="center" valign="top">
								<table width="500px" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="18px">
							 			<td class="text12White">
							 				&nbsp;
					 						&nbsp;Avsender - Sikkerhet&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						 				</td>
					 				</tr>
			 					</table>
			 					<table width="500px" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		<tr>	
							 			<td width="5">&nbsp;</td>
							            <td >	
											<%-- create record --%>
										 	<table align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
										 		<tr>
											 		<td>
												 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
											 				<%-- ================================================================================== --%>
													        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
													        		 These original values will be used when the user clicks "Cancel" buttons (puttting
													        		 back original value)																--%> 
													        	<%-- ================================================================================== --%>
													        	<input type="hidden" name="orig_tvknss" id="orig_tvknss" value='${Xmodel.record.tvknss}'>
													        	<input type="hidden" name="orig_tvnass" id="orig_tvnass" value='${Xmodel.record.tvnass}'>
													        	<input type="hidden" name="orig_tvtinss" id="orig_tvtinss" value='${Xmodel.record.tvtinss}'>
													        	<input type="hidden" name="orig_tvadss1" id="orig_tvadss1" value='${Xmodel.record.tvadss1}'>
													        	<input type="hidden" name="orig_tvpnss" id="orig_tvpnss" value='${Xmodel.record.tvpnss}'>
													        	<input type="hidden" name="orig_tvpsss" id="orig_tvpsss" value='${Xmodel.record.tvpsss}'>
													        	<input type="hidden" name="orig_tvlkss" id="orig_tvlkss" value='${Xmodel.record.tvlkss}'>
													        	<input type="hidden" name="orig_tvskss" id="orig_tvskss" value='${Xmodel.record.tvskss}'>
													        	
													        	<tr height="10">
													            <td class="text12" align="left">&nbsp;</td> 
													        </tr>
													 		<tr height="15">
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="tvknss">Kundenummer</span></td>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="tvnass">Navn</span>
													            	<a tabindex="-1" id="tvnassIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																	</a>
											            		</td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="tvknss" id="tvknss" size="10" maxlength="8" value="${model.record.tvknss}"></td>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvnass" id="tvnass" size="30" maxlength="35" value="${model.record.tvnass}"></td>
													        </tr>
													        <tr>
											        				<td class="text12" align="left" >&nbsp;&nbsp;<span title="tvtinss">TIN</span></td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="tvtinss" id="tvtinss" size="20" maxlength="17" value="${model.record.tvtinss}"></td>
													        </tr>
				
													        <tr height="4"><td>&nbsp;</td></tr>
													        <tr>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="tvadss1">Adresse</span></td>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="tvskss">Språkkode</span>
												            		
													            </td>
													        </tr>
													        <tr>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvadss1" id="tvadss1" size="30" maxlength="30" value="${model.record.tvadss1}"></td>
													            <td class="text12" align="left" >
													            		&nbsp;<select name="tvskss" id="tvskss">
														            		<option value="">-velg-</option>
														 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
										                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvskss == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																			</c:forEach> 
																	</select>
																	<a tabindex="-1" id="tvskssIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																	</a>
																</td>
													        </tr>
													        <tr>
													        		<td>
														        		<table>
														        		<tr>
														            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="tvpsss">Postadresse</span></td>
														            		<td align="left">&nbsp;</td>
														            	</tr>
														        		<tr>
														            		<td align="left">
														       				<input type="text" class="inputTextMediumBlue" name="tvpsss" id="tvpsss" size="30" maxlength="24" value="${model.record.tvpsss}">
													            			</td> 
														            		<td align="left">&nbsp;</td>
														        		</tr>    	
														            	</table>
													            </td>
													            <td >
														            	<table>
														        		<tr>
														        			<td class="text12" align="left" >&nbsp;&nbsp;<span title="tvpnss">Postnummer</span></td>
														            		<td class="text12" align="left" >&nbsp;<span title="tvlkss">Land</span>
														            		
														            		</td>
														            	</tr>
														        		<tr >
														        			<td align="left"><input type="text" class="inputTextMediumBlue" name="tvpnss" id="tvpnss" size="10" maxlength="8" value="${model.record.tvpnss}"></td> 
														            		<td align="left">
														            			<select name="tvlkss" id="tvlkss">
																            		<option value="">-velg-</option>
																            	<c:forEach var="country" items="${model.countryCodeList}" >
											                                	 	<option value="${country.zkod}"<c:if test="${model.record.tvlkss == country.zkod}"> selected </c:if> >${country.zkod}</option>
																				</c:forEach> 
																			</select>
																			<a tabindex="-1" id="tvlkssIdLink">
																				<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																			</a>
														            		</td> 
														        		</tr>  
														            	</table>
													            </td>
												            	</tr>
													        
													        <tr height="15">
											            		<td class="text12Bold" align="left" >&nbsp;</td>
											            		<td class="text12Bold" align="left" >&nbsp;</td> 
													        </tr>  
													        
												        </table>
											        </td>
										        </tr>
											</table>          
						            		</td>
						           	</tr> 
		 						</table>
			 					
			        			</td>
			        			<td width="50%" align="left" valign="top">
								<table width="500px" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="18px">
							 			<td class="text12White">
							 				&nbsp;
					 						&nbsp;Mottaker - Sikkerhet&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						 				</td>
					 				</tr>
			 					</table>
			 					<table width="500px" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		<tr>	
							 			<td width="5">&nbsp;</td>
							            <td >	
											<%-- create record --%>
										 	<table align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
										 		<tr>
											 		<td>
												 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
													 		<%-- ================================================================================== --%>
													        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
													        		 These original values will be used when the user clicks "Cancel" buttons (puttting
													        		 back original value)																--%> 
													        	<%-- ================================================================================== --%>
													        	<input type="hidden" name="orig_tvknks" id="orig_tvknks" value='${Xmodel.record.tvknks}'>
													        	<input type="hidden" name="orig_tvnaks" id="orig_tvnaks" value='${Xmodel.record.tvnaks}'>
													        	<input type="hidden" name="orig_tvtinks" id="orig_tvtinks" value='${Xmodel.record.tvtinks}'>
													        	<input type="hidden" name="orig_tvadks1" id="orig_tvadks1" value='${Xmodel.record.tvadks1}'>
													        	<input type="hidden" name="orig_tvpnks" id="orig_tvpnks" value='${Xmodel.record.tvpnks}'>
													        	<input type="hidden" name="orig_tvpsks" id="orig_tvpsks" value='${Xmodel.record.tvpsks}'>
													        	<input type="hidden" name="orig_tvlkks" id="orig_tvlkks" value='${Xmodel.record.tvlkks}'>
													        	<input type="hidden" name="orig_tvskks" id="orig_tvskks" value='${Xmodel.record.tvskks}'>
													 		<tr height="10">
													            <td class="text12" align="left">&nbsp;</td> 
													        </tr>
													        <tr height="15">
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="tvknks">Kundenummer</span></td>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="tvnaks">Navn</span>
													            	<a tabindex="-1" id="tvnaksIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																	</a>	
													            </td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="tvknks" id="tvknks" size="10" maxlength="8" value="${model.record.tvknks}"></td>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvnaks" id="tvnaks" size="30" maxlength="35" value="${model.record.tvnaks}"></td>
													        </tr>
													        <tr>
											        				<td class="text12" align="left" >&nbsp;&nbsp;<span title="tvtinks">TIN</span></td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="tvtinks" id="tvtinks" size="20" maxlength="17" value="${model.record.tvtinks}"></td>
													        </tr>
				
													        <tr height="4"><td>&nbsp;</td></tr>
													        <tr>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="tvadks1">Adresse</span></td>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="tvskks">Språkkode</span>
												            		
													            </td>
													        </tr>
													        <tr>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvadks1" id="tvadks1" size="30" maxlength="30" value="${Xmodel.record.tvadks1}"></td>
													            <td class="text12" align="left" >
													            		&nbsp;<select name="tvskks" id="tvskks">
														            		<option value="">-velg-</option>
														 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
										                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvskks == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																			</c:forEach> 
																	</select>
																	<a tabindex="-1" id="tvskksIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																	</a>
																</td>
													        </tr>
													        <tr>
													        		<td>
														        		<table>
														        		<tr>
														            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="tvpsks">Postadresse</span></td>
														            		<td align="left">&nbsp;</td>
														            	</tr>
														        		<tr>
														            		<td align="left">
														       				<input type="text" class="inputTextMediumBlue" name="tvpsks" id="tvpsks" size="30" maxlength="24" value="${model.record.tvpsks}">
													            			</td> 
														            		<td align="left">&nbsp;</td>
														        		</tr>    	
														            	</table>
													            </td>
													            <td >
														            	<table>
														        		<tr>
														        			<td class="text12" align="left" >&nbsp;&nbsp;<span title="tvpnks">Postnummer</span></td>
														            		<td class="text12" align="left" >&nbsp;<span title="tvlkks">Land</span>
														            		
														            		</td>
														            	</tr>
														        		<tr >
														        			<td align="left"><input type="text" class="inputTextMediumBlue" name="tvpnks" id="tvpnks" size="10" maxlength="8" value="${model.record.tvpnks}"></td> 
														            		<td align="left">
														            			<select name="tvlkks" id="tvlkks">
																            		<option value="">-velg-</option>
															 				  	<c:forEach var="country" items="${model.countryCodeList}" >
											                                	 	<option value="${country.zkod}"<c:if test="${model.record.tvlkks == country.zkod}"> selected </c:if> >${country.zkod}</option>
																				</c:forEach> 
																			</select>
																			<a tabindex="-1" id="tvlkksIdLink">
																				<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																			</a>
														            		</td> 
														        		</tr>  
														            	</table>
													            </td>
												            	</tr>
													        
													        <tr height="15">
											            		<td class="text12Bold" align="left" >&nbsp;</td>
											            		<td class="text12Bold" align="left" >&nbsp;</td> 
													        </tr>  
												        </table>
											        </td>
										        </tr>
											</table>          
						            		</td>
						           	</tr> 
		 						</table>
			        			</td>
				        	</tr>
				        	<tr height="10"><td>&nbsp;</td></tr>
		            	</table>
		            </td>  
		            <td>&nbsp;</td>
		        </tr>
				<tr height="25"><td></td></tr>		
						
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

