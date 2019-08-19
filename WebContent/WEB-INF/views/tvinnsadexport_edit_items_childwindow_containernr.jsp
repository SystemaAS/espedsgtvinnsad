<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===============================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ==============================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>			
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadexport_edit_items_childwindow_containernr.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
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
	
	
	<%-- -------------------------------- --%>	
 	<%-- tab area container MASTER TOPIC  --%>
	<%-- -------------------------------- --%>
 	<table width="100%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" border="0" cellspacing="0" cellpadding="0">
		<tr height="3"><td colspan="2">&nbsp;</td></tr>
		<tr>
			<td colspan="3" class="text16Bold">&nbsp;&nbsp;&nbsp;
				<img width="20px" height="20px" src="resources/images/find.png" border="0" alt="frisok">
				Containernr.&nbsp;<font class="text16MediumBlue">Avd / Tolldekl. / Linjenr.&nbsp;&nbsp;&nbsp;<b>${model.avd}</b>&nbsp;/&nbsp;<b>${model.opd}</b>&nbsp;/&nbsp;<b>${model.lin}</b></font>	
			</td>
		</tr>
		<tr height="5"><td colspan="2">&nbsp;</td></tr>
		<tr>
		<td >
		<table border="0" align="left">
			
           	<%-- ---------------------- --%>
           	<%-- LIST of existent ITEMs --%>
           	<%-- ---------------------- --%>
           	<tr>
				<td>
					<table style="width:100%" cellspacing="0" border="0" cellpadding="0">
	    				<%-- separator --%>
	        			<tr height="10"><td></td></tr> 
						
						<tr>
							<td class="ownScrollableSubWindow" style="width:90%; height:14em;">
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr class="tableHeaderField" height="20" valign="left">
										<td align="center" width="2%" class="tableHeaderFieldFirst" >&nbsp;<span title="todo">Containernr.&nbsp;</span></td>
										<td align="center" width="2%" class="tableHeaderField" >&nbsp;Slett&nbsp;</td>
					               </tr> 
					               
				 					  <c:forEach items="${model.containernrList}" var="record" varStatus="counter">    
							               <tr class="tableOddRow" height="20" >
						
							               <td align="center" width="2%" class="tableCellFirst" ><b>${record.svcnr}</b></td>
						               	   <%-- DELETE cell --%>							           
							               <td width="2%" class="tableCell" align="center">
							               	   <c:if test="${not empty record.svcnr}">
							                   		<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadexport_edit_items_childwindow_containernr_edit.do?action=doDelete&user=${user.user}&svavd=${record.svavd}&svtdn=${record.svtdn}&svli=${record.svli}&svcnr=${record.svcnr}">
								               			<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
								               		</a>	&nbsp;
									               	
								               	</c:if>
					               		  </td> 
							            </tr>
								        
								        </c:forEach>
						           
						        </table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr height="5"><td></td></tr>
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
			
			<%-- -------------------------- --%>
			<%-- Validation errors on model --%>
			<%-- -------------------------- --%>
			<c:if test="${not empty model.errorMessage}">
				<tr>
				<td>
		           	<table class="tabThinBorderWhiteWithSideBorders" width="90%" align="left" border="0" cellspacing="0" cellpadding="0">
		           	<tr>
					<td valign="bottom" class="textError">					
			            <ul>
			            	<li >${model.errorMessage}</li>
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
	 				<form action="tvinnsadexport_edit_items_childwindow_containernr_edit.do" name="updateItemContainernrForm" id="updateItemContainernrForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
					<input type="hidden" name="svavd" id="svavd" value='${model.avd}'>
					<input type="hidden" name="svtdn" id="svtdn" value='${model.opd}'>
					<input type="hidden" name="svli" id="svli" value='${model.lin}'>
					
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="90%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
	 					
				 		<tr height="15">
				 			<td class="text14White" align="left" >
				 				<b>&nbsp;&nbsp;Varelinje&nbsp;</b>
 								<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit">&nbsp;&nbsp;<font id="editLineNr"></font>
			 				</td>
		 				</tr>
	 				</table>
					<table width="90%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="12"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table  class="tableBorderWithRoundCornersGray" width="90%" border="0" cellspacing="0" cellpadding="0">
						 			<tr height="5"><td class="text" align="left"></td></tr>
						 			<tr >
						 				<td class="text14" align="left">&nbsp;<span title="svcnr">&nbsp;Containernr.</span></td>
					            		</tr>
							        <tr>
						        		    <td class="text14" align="left" >
							        			&nbsp;<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="svcnr" id="svcnr" size="18" maxlength="17" value="${model.record.svcnr}">
							        			&nbsp;<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.tvinn.sad.export.item.line.init.createnew.submit"/>'>
							        		</td>
							        		
						        		</tr>
							        <tr height="5"><td class="text" align="left"></td></tr>
						        </table>
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

