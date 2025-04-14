<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadMaintenance.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmaintenance_digitollv2_sadmocf.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
<table style="width:90%;" cellspacing="0" border="0" cellpadding="0">

 <tr>
 <td>	
	<%-- tab container component --%>
	<table style="width:100%;"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25">
			 
	 		<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;Transport</font>
				<img src="resources/images/update.gif" border="0" alt="edit">
						
			</td>
			<td width="80%" class="tabFantomSpace" align="right" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					
		</tr>
	</table>
	</td>
 </tr>
 
 
 
<tr height="5"><td colspan="10">&nbsp;</td></tr>
 
 <tr>
 	<td>
	<%-- --------------------------- --%>	
 	<%-- tab area container PRIMARY  --%>
	<%-- --------------------------- --%>
	<form name="manifestForm" id="manifestForm" action="tvinnsadmaintenance_digitollv2_sadmocf_edit.do" method="post">
			<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
			<input type="hidden" name="applicationUserSign" id="applicationUserSign" value="${user.tvinnSadSign}">
			<input type="hidden" name="action" id="action" value="doUpdate">
			
			 
	<table style="width:100%;" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<tr height="10"><td colspan="10">&nbsp;</td></tr>
 		
		<%-- --------------- --%>
		<%-- CONTENT --%>
		<%-- --------------- --%>
		<tr>
		<td >
		<table align="center" style="width:100%;"  border="0" cellspacing="1" cellpadding="0">
			
			<%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td colspan="10">
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
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
	           		 
 		<tr>
			<td class="text14" valign="top">
				<table style="width:65%;" align="left" border="0" cellspacing="1" cellpadding="0">
				 	<tr >
					 	<td >
						<table class="formFrameHeader" style="width:100%; border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<td class="text14White">
					 			
					 			<img style="cursor:pointer;" onMouseOver="showPop('transp_info');" onMouseOut="hidePop('transp_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
				            	<span title="Part">Part&nbsp;</span>
		                		<div class="text11" style="position: relative;" align="left">
			                	<span style="position:absolute;top:2px; width:250px;" id="transp_info" class="popupWithInputText text11"  >
			                	<p><b>Part</b>&nbsp;
			                			Part. Organisasjonen som fortoller selv.
			                	</p>
				           		</span>	
								</div>
					 			
					 			</td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
		            <tr >
					 	<td>
						<table style="width:100%;" class="formFrame" border="0" cellspacing="1" cellpadding="0">
					 		<tr>
				 				<td>
				 				<table>
				 				<tr>
				 					<td class="text14">&nbsp;<span title="etknt">Knr</span>
				 						&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="etknt" id="etknt" size="10" maxlength="8" value="${model.record.etknt}">
				 					</td>
				 				</tr>
				 				
				 				<tr >
									<td class="text14">&nbsp;<span title="name">Navn</span>
										<a tabindex="-1" id="nameIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
										</a>
									
									</td>
									<td class="text14">
										<img style="cursor:pointer;" onMouseOver="showPop('etrgt_info');" onMouseOut="hidePop('etrgt_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
						            	<span title="etrgt">Orgnr&nbsp;</span>
				                		<div class="text11" style="position: relative;" align="left">
					                	<span style="position:absolute;top:2px; width:250px;" id="etrgt_info" class="popupWithInputText text11"  >
					                	<p><b>Orgnr</b>&nbsp;
					                		Kundens identifikasjonsnummer. Forventes norsk organisasjonsnummer. 
					                		
					                	</p>
						           		</span>	
										</div>
									
									</td>
									
				 				</tr>
				 				<tr >
		 							<td class="text14"><input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="name" id="name" size="35" maxlength="50" value="${model.record.name}"></td>
									<td class="text14"><input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="orgnr" id="orgnr" size="30" maxlength="30" value="${model.record.orgnr}"></td>
				 				</tr>
				 				<tr >
				 					<td>
					 					<table>
					 					<tr>
											<td class="text14">&nbsp;<span title="commtype">Comm.</span></td>
											<td class="text14">&nbsp;<span title="format">Format</span></td>
										</tr>
						 				<tr>
											<td class="text14">
												<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="commtype" id="commtype" >
							 						<option value="">-velg-</option>
								 				  	<option title="ftp" value="ftp" <c:if test="${model.record.commtype == 'ftp'}"> selected </c:if> >ftp</option>
								 				  	<option title="sftp" value="sftp" <c:if test="${model.record.commtype == 'sftp'}"> selected </c:if>>sftp</option>
												</select>
											</td>
											<td class="text14">
												<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="format" id="format" >
							 						<option value="">-velg-</option>
								 				  	<option title="xml" value="xml" <c:if test="${model.record.format == 'xml'}"> selected </c:if>>xml</option>
								 				  	<option title="json" value="json" <c:if test="${model.record.format == 'json'}"> selected </c:if>>json</option>
												</select>
											</td>
										</tr>
										</table>
									</td>
									<td>
					 					<table>
					 					<tr>
											<td class="text14">&nbsp;<span title="xmlxsd">Channel</span></td>
										</tr>
										<tr>
											<td class="text14">
												<select class="inputTextMediumBlue" name="xmlxsd" id="xmlxsd" >
							 						<option value="">-velg-</option>
								 				  	<option title="xmlxsd" value="peppol-sbdh" <c:if test="${model.record.xmlxsd == 'peppol-sbdh'}"> selected </c:if>>peppol-sbdh</option>
													<option title="xmlxsd" value="evry" <c:if test="${model.record.xmlxsd == 'evry'}"> selected </c:if>>evry</option>
												</select>
											</td>
										</tr>
										</table>
									</td>
				 				</tr>
				 				<tr height="2"><td>&nbsp;</td></tr>
				 				<tr >
				 					<td>
					 					<table>
					 					<tr>
											<td class="text14">&nbsp;<span title="ftpserver">Ftp server</span></td>
											<td class="text14">&nbsp;<span title="ftpport">Ftp port</span></td>
											
										</tr>
						 				<tr>
											<td class="text14"><input type="text" class="inputTextMediumBlue" name="ftpserver" id="ftpserver" size="35" maxlength="70" value="${model.record.ftpserver}"></td>
											<td class="text14"><input type="text" class="inputTextMediumBlue" name="ftpport" id="ftpport" size="10" maxlength="10" value="${model.record.ftpport}"></td>
											
										</tr>
										</table>
									</td>
									<td>
					 					<table>
					 					<tr>
											<td class="text14">&nbsp;<span title="ftpuser">Ftp user</span></td>
											<td class="text14">&nbsp;<span title="ftppwd">Ftp pwd</span></td>
										</tr>
						 				<tr>
											<td class="text14"><input type="text" class="inputTextMediumBlue" name="ftpuser" id="ftpuser" size="20" maxlength="35" value="${model.record.ftpuser}"></td>
											<td class="text14"><input type="text" class="inputTextMediumBlue" name="ftppwd" id="ftppwd" size="20" maxlength="70" value="${model.record.ftppwd}"></td>
										</tr>
										</table>
									</td>
								</tr>
								
								<tr >
				 					<td>
					 					<table>
					 					<tr>
											<td class="text14">&nbsp;<span title="ftpdir">Ftp dir.</span></td>
											
										</tr>
						 				<tr>
											<td class="text14"><input type="text" class="inputTextMediumBlue" name="ftpdir" id="ftpdir" size="45" maxlength="70" value="${model.record.ftpdir}"></td>
											
										</tr>
										</table>
									</td>
									<td>
					 					<table>
					 					<tr>
											<td class="text14">&nbsp;<span title="ftptmp">Ftp tmp</span></td>
											
										</tr>
						 				<tr>
											<td class="text14"><input type="text" class="inputTextMediumBlue" name="ftptmp" id="ftptmp" size="35" maxlength="70" value="${model.record.ftptmp}"></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr >
				 					<td>
					 					<table>
					 					<tr>
											<td class="text14">&nbsp;<span title="ftpbupdir">Ftp backup dir.</span></td>
											
										</tr>
						 				<tr>
											<td class="text14"><input type="text" class="inputTextMediumBlue" name="ftpbupdir" id="ftpbupdir" size="45" maxlength="70" value="${model.record.ftpbupdir}"></td>
											
										</tr>
										</table>
									</td>
									
								</tr>
								
								<tr height="2"><td>&nbsp;</td></tr>
								<tr >
				 					<td>
					 					<table>
					 					<tr>
											<td class="text14">&nbsp;<span title="sftpdir_ps">sFtp dir.</span></td>
											
										</tr>
						 				<tr>
											<td class="text14"><input type="text" class="inputTextMediumBlue" name="sftpdir_ps" id="sftpdir_ps" size="45" maxlength="70" value="${model.record.sftpdir_ps}"></td>
											
										</tr>
										</table>
									</td>
									<td>
					 					<table>
					 					<tr>
											<td class="text14">&nbsp;<span title="avsorgnr">Avs.orgnr</span></td>
											<td class="text14">&nbsp;<span title="avsname">Avs.name</span></td>
										</tr>
						 				<tr>
											<td class="text14"><input type="text" class="inputTextMediumBlue" name="avsorgnr" id="avsorgnr" size="20" maxlength="30" value="${model.record.avsorgnr}"></td>
											<td class="text14"><input type="text" class="inputTextMediumBlue" name="avsname" id="avsname" size="30" maxlength="30" value="${model.record.avsname}"></td>
											
										</tr>
										</table>
									</td>
									
								</tr>
								
								
				 				<tr height="2"><td>&nbsp;</td></tr>
				 				
				 				</table>
				 				</td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
	            </table>
            </td>
            
             
		</tr>
		<tr height="10"><td></td></tr>
		<tr>
			<td align="left" >
				&nbsp;&nbsp;<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'>
				<a id="alinkCreateNewButton" href="tvinnsadmaintenance_digitollv2_sadmocf.do?">
					<input class="inputFormSubmitStd" type="button" name="createNewButton" id="createNewButton" value='Lage ny'>
				</a>
			</td>
		</tr> 
	</table>
	</td>
	</tr>	
	
	
	<tr>
 	<td>
	<table style="width:100%;" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<tr height="5"><td colspan="10">&nbsp;</td></tr>
 		
 		<tr>
			<td class="text14">
			<table id="mainList" class="display compact cell-border" >
				<thead>
				<tr>
					<th class="tableHeaderFieldFirst" style="font-size:11;" >&nbsp;Endre</th>
                    <th class="tableHeaderField" style="font-size:11px;">&nbsp;Navn&nbsp;</th>
                    <th class="tableHeaderField" style="font-size:11px;">&nbsp;Orgnr.&nbsp;</th>
                    <th class="tableHeaderField" style="font-size:11px;">&nbsp;Comm&nbsp;</th>
                    <th class="tableHeaderField" style="font-size:11px;">&nbsp;Format&nbsp;</th>
                    <th class="tableHeaderField" style="font-size:11px;">&nbsp;Channel&nbsp;</th>
                    <th class="tableHeaderField" style="font-size:11px;">&nbsp;Ftp-server&nbsp;</th>
                    <th class="tableHeaderField" style="font-size:11px;">&nbsp;Ftp-port&nbsp;</th>
                    <th class="tableHeaderField" style="font-size:11px;">&nbsp;Ftp-user&nbsp;</th>
                    <th class="tableHeaderField" style="font-size:11px;">&nbsp;Ftp-pwd&nbsp;</th>
                    <th class="tableHeaderField" style="font-size:11px;">&nbsp;Ftp-dir&nbsp;</th>
                    <th class="tableHeaderField" style="font-size:11px;">&nbsp;Ftp-tmp&nbsp;</th>
                    <th class="tableHeaderField" style="font-size:11px;">&nbsp;sFtp-PS&nbsp;</th>
                    <th class="tableHeaderField" style="font-size:11px;">&nbsp;Slett&nbsp;</th>
                </tr>  
                </thead> 
                <tbody >  
	            <c:forEach var="record" items="${model.list}" varStatus="counter">   
	               <tr class="tableRow" height="20" >
	              
	               <td align="center" width="2%" class="tableCell" style="border-style:solid; border-width:0px 1px 1px 0px; border-color:#FAEBD7;" >
	        	       	<a onClick="setBlockUI(this)" style="display:block;width:100%; height:100%;" id="orgnr_${record.orgnr}" href="tvinnsadmaintenance_digitollv2_sadmocf.do?action=doFind&orgnr=${record.orgnr}">
  							<img src="resources/images/update.gif" border="0" alt="edit">
               			</a>
	               </td>
	               <td align="left" class="tableCell11" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.name}
	               </td>
	               <td align="left" class="tableCell11" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.orgnr}
	               </td>
	               <td align="left" class="tableCell11" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.commtype}
	               </td>
	               <td align="left" class="tableCell11" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.format}
	               </td>
	               <td align="left" class="tableCell11" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		${record.xmlxsd}
	               </td>
	               <td align="left" class="tableCell11" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		<c:if test="${not empty record.ftpserver && record.ftpserver != 'null'}">${record.ftpserver}</c:if>&nbsp;
	               </td>
	               <td align="left" class="tableCell11" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		<c:if test="${not empty record.ftpport && record.ftpport != 'null'}">${record.ftpport}</c:if>&nbsp;
	               </td>
	               <td align="left" class="tableCell11" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		<c:if test="${not empty record.ftpuser && record.ftpuser != 'null'}">${record.ftpuser}</c:if>&nbsp;
	               </td>
	               <td align="left" class="tableCell11" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		<c:if test="${not empty record.ftppwd  && record.ftppwd != 'null'}">${record.ftppwd}</c:if>
	               </td>
	               <td align="left" class="tableCell11" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		<c:if test="${not empty record.ftpdir && record.ftpdir != 'null'}">${record.ftpdir}</c:if>
	               </td>
	               <td align="left" class="tableCell11" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		<c:if test="${not empty record.ftptmp && record.ftptmp != 'null'}">${record.ftptmp}</c:if>
	               </td>
	               <td align="left" class="tableCell11" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
	               		<c:if test="${not empty record.ftptmp && record.sftpdir_ps != 'null'}">${record.sftpdir_ps}</c:if>
	               </td>
	               <td align="center" width="2%" class="tableCell11" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >   		
			   				
              				<a onClick="setBlockUI(this)" style="display:block;width:100%; height:100%;" id="etavdLink_${record.orgnr}" href="tvinnsadmaintenance_digitollv2_sadmocf_delete.do?orgnr=${record.orgnr}">
								<img src="resources/images/delete.gif" border="0" alt="remove">
							</a>
							
	               	   </td> 
	            </tr> 
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
 
 	
 
	