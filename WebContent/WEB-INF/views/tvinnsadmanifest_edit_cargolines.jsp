<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadmanifest_edit_cargolines.js?ver=${user.versionEspedsg}"></SCRIPT>
	
<table style="width:100%;"  cellspacing="0" border="0" cellpadding="0">

 <tr>
 <td>	
	<%-- tab container component --%>
	<table style="width:100%;"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 id="alinkManifestList" style="display:block;" href="tvinnsadmanifest.do?action=doFind&avd=${model.efavd}&sign=${model.efsg}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.manifest.list.tab"/></font>
					<img src="resources/images/list.gif" border="0" alt="general list">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
	
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 id="alinkHeader" style="display:block;" href="tvinnsadmanifest_edit.do?action=doFetch&efuuid=${model.efuuid}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tvinn.sad.manifest.created.header.tab"/>
					</font>
					<font class="text14MediumBlue">[${model.efpro}]</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
					<font class="tabLink">
						&nbsp;<spring:message code="systema.tvinn.sad.manifest.createnew.last.tab"/>
					</font>
					<img src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
			</td>
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
		</tr>
	</table>
	</td>
</tr>

 
 <tr>
 	<td>
	<%-- --------------------------- --%>	
 	<%-- tab area container PRIMARY  --%>
	<%-- --------------------------- --%>
	<form name="manifestForm" id="manifestForm" method="post">
	<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
			
	<table style="width:100%;" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<tr height="10"><td colspan="10">&nbsp;</td></tr>
 		
		<%-- --------------- --%>
		<%-- CONTENT --%>
		<%-- --------------- --%>
		<tr>
		<td >
		<table align="center" style="width:100%;" border="0" cellspacing="1" cellpadding="0">
			
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
		<td>		
		<table style="width:100%;" cellspacing="0" border="0" cellpadding="0">
	    	<%-- separator --%>
	        <tr height="1"><td></td></tr> 
			<tr>
				<td>
				<table id="containerdatatableTable" width="100%" cellspacing="2" align="left" >
				<tr>
				<td class="text11">
							
				<table id="mainList" class="display compact cell-border" >
					<thead>
					<tr class="tableHeaderField" height="20" >
                    	<th width="2%" class="tableHeaderFieldFirst" ><spring:message code="systema.tvinn.sad.update"/></th>
                		<th class="tableHeaderField" >Avd</th>
                		<th class="tableHeaderField" >Oppdrag</th>
                		<th class="tableHeaderField" >St</th>
                		<th class="tableHeaderField" >Eksport type</th>
                		<th class="tableHeaderField" >EksportId</th>
                		<th class="tableHeaderField" >Ser</th>
                		<th class="tableHeaderField" >Bilnr</th>
                		<th class="tableHeaderField" >E.enh.</th>
                	</tr>
                	</thead>
                	<tbody> 
		           	<c:forEach items="${model.list}" var="record" varStatus="counter">    
		              <tr class="tableRow" height="20" >
		          
		          	   <td width="2%" class="tableCellFirst" align="center" >
		               		<a id="clpro_${record.clpro}@cltdn_${record.cltdn}@clavd_${record.clavd}" href="#" onClick="getItemData(this);">
               					<img src="resources/images/update.gif" border="0" alt="edit">
               				</a>
               				
	               	   </td>
		               <td width="2%" class="tableCell" align="center" >${record.clavd}</td>
		               <td width="2%" class="tableCell" align="center" >${record.cltdn}</td>
		               <td width="2%" class="tableCell" align="center" >${record.clst}</td>
		               <td width="2%" class="tableCell" align="center" >${record.cletyp}&nbsp;${record.cletypt}</td>
		               <td width="2%" class="tableCell" align="center" >${record.cleid}</td>
		               <td width="2%" class="tableCell" align="center" >${record.cleser}</td>
		               <td width="2%" class="tableCell" align="center" >${record.cltrid}</td>
		               <td width="2%" class="tableCell" align="center" >${record.cl3039e}</td>
		               
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
 		
	<tr>
		<td class="text14" valign="top">
			<table style="width:100%" align="left" border="0" cellspacing="1" cellpadding="0">
			 	<tr >
				 	<td >
					<table style="width:100%" class="formFrameHeader" border="0" cellspacing="1" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14White">&nbsp;&nbsp;Last&nbsp;&nbsp;-
				 				&nbsp;&nbsp;Turnr:&nbsp;${model.efpro}
	 							&nbsp;&nbsp;Avd:&nbsp;${model.efavd}&nbsp;&nbsp;
	 							&nbsp;&nbsp;Manifestid:&nbsp;${model.efuuid}
				 			</td>
		 				</tr>
		            </table>
		            </td>
	            </tr>
	            <tr >
				 	<td>
					<table style="width:100%" class="formFrame" border="0" cellspacing="1" cellpadding="0">
						<tr >
							<td colspan="4" class="text16"><b>&nbsp;Import</b></td>
						<tr >
		 				<tr >
							<td class="text14">&nbsp;<span title="clvt">Varebeskrivelse</span><font class="text16RedBold" >*</font></td>
							<td class="text14">&nbsp;<span title="todo">Deklarantnr.</span><font class="text16RedBold" >*</font></td>
							<td class="text14">&nbsp;<span title="todo">Dato</span><font class="text16RedBold" >*</font></td>
							<td class="text14">&nbsp;<span title="todo">Sekvensnr.</span><font class="text16RedBold" >*</font></td>
							<td class="text14">&nbsp;<span title="todo">Mottaker</span></td>
							<td class="text14">&nbsp;<span title="todo">Antall kolli i dekl.</span><font class="text16RedBold" >*</font></td>
							<td class="text14">&nbsp;<span title="todo">Br.vkt. (kg) i dekl.</span><font class="text16RedBold" >*</font></td>
							<td class="text14">&nbsp;<span title="todo">Antall i fraktbr.</span><font class="text16RedBold" >*</font></td>
							<td class="text14">&nbsp;<span title="todo">Br.vkt. (kg) i fraktbr.</span><font class="text16RedBold" >*</font></td>
		 				</tr>
		 				<tr >
				 			<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="clvt" id="clvt" size="31" maxlength="30" value="${Xmodel.record.titin}"></td>
				 			<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="todo" id="todo" size="20" maxlength="20" value="${Xmodel.record.titin}"></td>
				 			<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="todo" id="todo" size="8" maxlength="8" value="${Xmodel.record.titin}"></td>
				 			<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="todo" id="todo" size="20" maxlength="20" value="${Xmodel.record.titin}"></td>
							<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="todo" id="todo" size="20" maxlength="20" value="${Xmodel.record.titin}"></td>
							<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="todo" id="todo" size="10" maxlength="10" value="${Xmodel.record.titin}"></td>
							<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="todo" id="todo" size="10" maxlength="10" value="${Xmodel.record.titin}"></td>
							<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="todo" id="todo" size="10" maxlength="10" value="${Xmodel.record.titin}"></td>
							<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="todo" id="todo" size="10" maxlength="10" value="${Xmodel.record.titin}"></td>
		 				</tr>
		 				<tr height="15"><td></td></tr>
		 				
		 				<tr>
					 		<td colspan="2">
								<table class="tableBorderWithRoundCorners" border="0" cellspacing="1" cellpadding="0">
				 				<tr >
									<td colspan="2" class="text16"><b>&nbsp;Eksport</b></td>
								<tr >
								<tr >
									<td class="text14">&nbsp;<span title="todo">Eksporttype</span><font class="text16RedBold" >*</font></td>
									<td class="text14">&nbsp;<span title="todo"><label id="lblExporttypeName">&nbsp;</label></td>									
								</tr>
								<tr>
									<td class="text14">
						 				<select class="inputTextMediumBlueMandatoryField" name="todo" id="todo" >
					 						<option value="">Velg</option>
			 		 				  		<option value="UGE">UGE</option>
			 		 				  		<option value="2">Kvalitetssikres</option>
										</select>
						 			</td>
						 			<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="todo" id="todo" size="30" maxlength="30" value="${Xmodel.record.titin}"></td>						 			
								</tr>
								<tr height="10"><td></td></tr>
								</table>
							</td>
							
							<td colspan="3">
								<table class="tableBorderWithRoundCorners" border="0" cellspacing="1" cellpadding="0">
				 				<tr >
									<td colspan="2" class="text16"><b>&nbsp;Laste/Losse</b></td>
								<tr >
								<tr >
									<td class="text14">&nbsp;<span title="todo">Lastes i</span></td>
									<td class="text14">&nbsp;<span title="todo">Lastested</span></td>
									<td class="text14">&nbsp;<span title="todo">Lossested</span></td>
								</tr>
								<tr>
									<%--laste/losse --%> 
									<td class="text14">
						 				<select class="inputTextMediumBlue" name="todo" id="todo" style="width:100px;">
					 						<option value="">Velg</option>
			 		 				  		<option value="SE">Sverige</option>
			 		 				  		<option value="NO">Norge</option>
										</select>
						 			</td>
						 			<td class="text14"><input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="20" maxlength="20" value="${Xmodel.record.titin}"></td>
						 			<td class="text14"><input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="20" maxlength="20" value="${Xmodel.record.titin}"></td>
								</tr>
								<tr height="10"><td></td></tr>
								</table>
							</td>
						</tr>						
		 				<tr height="10"><td></td></tr>		 					 		
		 			</table>
		 			</td>	
	            </tr>            
            </table>
           </td>
	</tr>
	<tr height="10"><td></td></tr>
		
		
	</table>
	</td>
	</tr>
		
	</table> 
	</form>
	</td>
	</tr>
</table>
 
 	
 
	