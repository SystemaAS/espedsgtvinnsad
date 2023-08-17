<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->
<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
<table style="width:100%;"  class="text11" cellspacing="0" border="0" cellpadding="0">
<tr>
	<td>
	<%-- tab container component --%>
	<table style="width:100%;" class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.manifest.list.tab"/></font>
				<img src="resources/images/list.gif" border="0" alt="general list">
				&nbsp;&nbsp;${listSize}
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkHeader" style="display:block;" href="tvinnsadmanifest_edit.do?user=${user.user}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.createnew"/></font>
					<img src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
				</a>
			</td>
			<td width="60%" class="tabFantomSpace" align="right" nowrap><font class="tabDisabledLink">&nbsp;</font>
				<img id="imgInfoRpgJarStart" style="cursor:pointer;" onClick="showPop('jarStartCmd');" src="resources/images/info4.png" width="12" height="12" border="0" alt="info">
				<div class="text12" style="position: relative;display: inline;" align="left">
				<span style="position:absolute; left:-580px; top:3px;" id="jarStartCmd" class="popupWithInputText"  >
	           		<div class="text11" align="left">
	           			<b>Communication API</b>&nbsp;upload-engine-expressmanif-client.jar<br/>
	           			<p><b>Stop</b> go tvinmnu --> 2 ...</p>
	           			<p><b>Start</b> SBMJOB CMD(CALL PGM(SADEMSNDC)) JOB(EXPMANSND) JOBQ(SYJOBQNMAX) USER(SY400USR)
	           			</p>
	           			<p>
		           			<a class="text11" target="_blank" id="alinkHeader" style="display:block;" href="renderLocalLogsgExpft.do?user=${user.user}">
		           				logsg_syjservicestn-expft.log
		           			</a>
	           			</p>
	           			<br/>
	           			<button name="_ButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('jarStartCmd');">Close</button> 
	           		</div>
	           	</span>
	           	</div>
			</td>
		</tr>
	</table>
	</td>
</tr>

<tr>
	<td>
	<%-- search filter component --%>
		
 		<table style="width:100%;" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<tr>
 		<td>
 		<table style="width:70%;">
 	        <form name="searchForm" id="searchForm" action="tvinnsaddigitollv2.do?action=doFind" method="post" >
 	        <tr height="3"><td></td></tr>
 	        <tr>	
                <td class="text14" align="left" title="avd" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.manifest.list.search.label.avd"/></td>
                <td class="text14" align="left" title="sign" >&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.manifest.list.search.label.signatur"/></td>
                
                <td class="text14" align="left" ><span title="turnr"><spring:message code="systema.tvinn.sad.manifest.list.search.label.turnr"/></span></td>
                <td class="text14" align="left" ><span title="datum"><spring:message code="systema.tvinn.sad.manifest.list.search.label.etafdatum"/></span></td>
                <td class="text14" align="left" ><span title="datumt"><spring:message code="systema.tvinn.sad.manifest.list.search.label.etatdatum"/></span></td>
                <td class="text14" align="left" ><span title="datum"><spring:message code="systema.tvinn.sad.manifest.list.search.label.fdatum"/></span></td>
                <td class="text14" align="left" ><span title="datumt"><spring:message code="systema.tvinn.sad.manifest.list.search.label.tdatum"/></span></td>
                
			</tr>
 	        <tr>
				<td align="left" >&nbsp;
           			<select class="selectMediumBlueE2" name="avd" id="avd">
	            		<option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                        	 	<option value="${record.avd}"<c:if test="${searchFilterSadDigitollTransportList.avd == record.avd}"> selected </c:if> >${record.avd}</option>                       	 	
						</c:forEach> 
					</select>
				</td>
				<td align="left" >
           			<select class="selectMediumBlueE2" name="sign" id="sign">
	            		<option value="">-velg-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${searchFilterSadDigitollTransportList.sign == record.sign}"> selected </c:if> >${record.sign}</option>
						</c:forEach> 
					</select>
				</td>
				<td align="left" ><input type="text" class="inputText" name="turnr" id="turnr" size="10" maxlength="8" value="${searchFilterSadDigitollTransportList.turnr}">&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="etaDatum" id="etaDatum" size="6" maxlength="6" value="${searchFilterSadDigitollTransportList.etaDatum}">&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="etaDatumt" id="etaDatumt" size="6" maxlength="6" value="${searchFilterSadDigitollTransportList.etaDatumt}">&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datum" id="datum" size="6" maxlength="6" value="${searchFilterSadDigitollTransportList.datum}">&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datumt" id="datumt" size="6" maxlength="6" value="${searchFilterSadDigitollTransportList.datumt}">&nbsp;</td>
				<td valign="top" align="left" >
                   <input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.tvinn.sad.search"/>'>
                </td>
			</tr>
			<tr height="10"><td></td></tr>
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
           	<table style="width:100%;" align="left" border="0" cellspacing="0" cellpadding="0">
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
		<td colspan="10">
           	<table align="left" border="0" cellspacing="0" cellpadding="0">
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
	<%-- list component --%>
	<tr>
		<td>		
		<table style="width:100%;" border="0" >
	    	<%-- separator --%>
	        <tr height="1"><td></td></tr> 
			<tr>
				<td>
				<table style="width:100%;" id="containerdatatableTable" cellspacing="2" align="left" >
				<tr>
				<td class="text11">
							
				<table id="mainList" class="display compact cell-border" >
					<thead>
					<tr class="tableHeaderField" height="20" >
                    	<th width="2%" class="tableHeaderFieldFirst" ><spring:message code="systema.tvinn.sad.update"/></th>
                    	<th width="2%" class="tableHeaderField" >Avd</th>
                		<th width="2%" class="tableHeaderField" >Turnr</th>
                		<th width="2%" class="tableHeaderField" >Sig</th>
                		<th title="S=SLETTET" width="2%" class="tableHeaderField" >Status</th>
                		<th width="2%" class="tableHeaderField" >Passering ETA</th>
                		<th width="2%" class="tableHeaderField" >Tollsted</th>
                		<th width="2%" class="tableHeaderField" >MasterDoknr.</th>
                		<th width="2%" class="tableHeaderField" >MasterDoktyp.</th>
                		<th width="2%" class="tableHeaderField" >Bilnr</th>
                		<th width="2%" class="tableHeaderField" >Sjåførs navn</th>
                		<th width="2%" class="tableHeaderField" >Reg.dato</th>
                		<th width="2%" class="tableHeaderField" >MRN-Api</th>
                		<th width="2%" class="tableHeaderField" >Req.id</th>
                		<th title="S=SUBMITTED,R=REOPENED/DRAFT,D=SLETTET,C=COMPLETED" width="2%" class="tableHeaderField" >Manif.st</th>
                		<th width="2%" class="tableHeaderField" title="Fjerner manifest fra Tollvesenet" >Slett</th>
                		<th width="2%" class="tableHeaderField" title="Fjerner manifest lokalt (SYSPED)">Kanseller</th>
                		</tr>
                	</thead>
                	<tbody> 
                	<c:forEach items="${list}" var="record" varStatus="counter">    
		              <c:choose> 
		              	  <%-- if the manifest is correct with all cargo lines OR the manifest has been SUBMITTED(S) or DELETED(D) don´t show it as a warning-line --%>	   
			              <c:when test="${Xrecord.own_valid > 0 || Xrecord.efst2 == 'S' || Xrecord.efst2 == 'D' }">
			              	<tr class="tableRow" height="20" >
			          	  </c:when>
			          	  <c:otherwise>
			          	  	<%-- <tr class="tableRow" style="background-color: #FEEFB3;color:#9F6000;" height="20" >  --%>
			          	  	<tr class="tableRow" height="20" >
			          	  </c:otherwise>
		          	  </c:choose>	
		          
		          	   <td width="2%" class="tableCellFirst" align="center">
		          	   		<a style="display: block; width: 100%; height: 100%;"  href="tvinnsadmanifest_edit.do?action=doFetch&efuuid=${Xrecord.efuuid}" onClick="setBlockUI();">
               					<c:choose>
		               				<c:when test="${Xrecord.own_editable > 0}">
		               					<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="edit">
		               				</c:when>
		               				<c:otherwise>
		               					<img title="Read" style="vertical-align:bottom;" src="resources/images/eye.png" height="18px" width="18px" border="0" alt="read">
		               				</c:otherwise>
	               				</c:choose>
               				</a>
               				
	               	   </td>
	               	   
		               <td width="2%" align="center" class="tableCell" >${record.etavd}</td>
		               <td width="2%" align="center" class="tableCell" ><c:if test="${record.etpro > 0}">${record.etpro}</c:if></td>
		               <td width="2%" align="center" class="tableCell" >${record.etsg}</td>
		               <td width="2%" align="center" class="tableCell" >
		               	  <c:choose>
		               		<c:when test="${Xrecord.efst == 'S'}">
		               			<font class="inputFormSubmit isa_error">KANSELLERT</font>
		               		</c:when>
		               		<c:otherwise>
		               			${Xrecord.efst}
		               		</c:otherwise>
		               	   </c:choose>
		              	</td>
		               <td width="2%" class="tableCell" >${record.etetad}&nbsp;${record.etetat}</td>
		               <td align="center" width="2%" class="tableCell" >${record.ettsd}</td>
		               <td align="center" width="2%" class="tableCell" >${record.etdkm}</td>
		               <td align="center" width="2%" class="tableCell" >${record.etdkmt}</td>
		               <td width="2%" class="tableCell" >${record.etkmrk}</td>
		               <td width="2%" class="tableCell" >${record.etsjaf}</td>
		               <td width="2%" class="tableCell" ><c:if test="${record.etdtr > 0}">${record.etdtr}</c:if></td>
		               <%--
		               <td width="2%" class="tableCell" ><font style="font-size:11px;">${record.efuuid}</font></td>
		                --%>
		               <td width="2%" class="tableCell" >
		               		<a style="display: block; width: 100%; height: 100%; cursor:pointer" class="uuidLink text14SkyBlue" id="${record.etmid}">
								${record.etmid}
							</a>  
		               </td>
		               <td width="2%" class="tableCell" >
		               		<a style="display: block; width: 100%; height: 100%; cursor:pointer" class="uuidLink text14SkyBlue" id="${record.etuuid}">
								${record.etuuid}
							</a>  
		               </td>
		               
		               <td width="2%" align="center" class="tableCell" >
		               		<c:choose>
		               		<c:when test="${record.etst2 == 'S' || record.etst2 == 'R' || record.etst2 == 'D' || record.etst2 == 'C'}">
		               			<c:if test="${record.etst2 == 'S'}">
		               				<img src="resources/images/bulletGreen.png" width="10" height="10" border="0" >
		               				<span title="S" >SUBMITTED</span>
		               			</c:if>
		               			<c:if test="${record.etst2 == 'R'}">
		               				<span title="R" >REOPENED/DRAFT</span>
		               			</c:if>
		               			<c:if test="${record.etst2 == 'D'}">
		               				<font title="D" color="red">SLETTET</font>
		               			</c:if>
		               			<c:if test="${record.etst2 == 'C'}">
		               				<img style="vertical-align:middle;" title="Completed tolldekl at toll.no" src="resources/images/complete-icon.png" width="14px" height="12px" border="0" alt="completion">
		               				<font title="C" color="green">COMPLETED</font>
		               			</c:if>
		               			
		               		</c:when>
		               		<c:otherwise>
		               			${record.etst2}
		               		</c:otherwise>
		               		</c:choose>
		               </td>

		               <td width="2%" class="tableCell" align="center"> 
		               		  		
				   				<c:if test="${Xrecord.own_editable > 0}">
		              				<a style="display: block; width: 100%; height: 100%;" class="removeLink" id="removeLink${counter.count}" runat="server" href="#">
										<img src="resources/images/delete.gif" border="0" alt="remove">
									</a>
									<div style="display: none;" class="clazz_dialog" id="dialogUpdateStatus${counter.count}" title="Dialog">
										<form action="tvinnsadmanifest_edit_delete.do" name="updateStatusForm${counter.count}" id="updateStatusForm${counter.count}" method="post">
										 	<input type="hidden" name="currentUuid${counter.count}" id="currentUuid${counter.count}" value="${Xrecord.efuuid}">
										 	<input type="hidden" name="selectedStatus${counter.count}" id="selectedStatus${counter.count}" value="D">
										 	<input type="hidden" name="selectedPro${counter.count}" id="selectedPro${counter.count}" value="${Xrecord.efpro}">
											<p class="text14" >Er du sikker på at du vil slette Turnr. <b>${Xrecord.efpro}</b> fra <b>Tollvesenet</b> ?</p>
											
										</form>
									</div>
	              				</c:if>
              				
	               	   </td>
	               	   <td width="2%" class="tableCell" align="center">
	               	   		<c:if test="${Xrecord.efst == 'M' || empty Xrecord.efst}">   		
				   				<a style="display: block; width: 100%; height: 100%;" class="cancelLink" id="cancelLink${counter.count}" runat="server" href="#">
									<img src="resources/images/remove.png" width="16" height="16" border="0" alt="remove">
								</a> 
								<div id="dialogUpdateInternalStatus${counter.count}" class="clazz_dialog" title="Dialog">
									<form action="tvinnsadmanifest_updateInternalStatus.do" name="updateInternalStatusForm${counter.count}" id="updateInternalStatusForm${counter.count}" method="post">
									 	<input type="hidden" name="currentUuid${counter.count}" id="currentUuid${counter.count}" value="${Xrecord.efuuid}">
									 	<input type="hidden" name="currentSign${counter.count}" id="currentSign${counter.count}" value="${Xrecord.efsg}">
									 	<input type="hidden" name="selectedStatus${counter.count}" id="selectedStatus${counter.count}" value="S">
									 	<p class="text14" >Er du sikker på at du vil kansellere Turnr. <b>${Xrecord.efpro}</b> fra <b>SYSPED</b> ?</p>
											
									</form>
								</div>
							</c:if>
						</td>	
		            </tr> 
		            </c:forEach>
		            </tbody>
	            </table>
	            </td>
	            </tr>
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

