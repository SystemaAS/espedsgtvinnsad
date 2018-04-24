<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadadmin_avggrunnlag.js?ver=${user.versionEspedsg}"></SCRIPT>	
			
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>


<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25">
			<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.admin.avggrunnlag.list.tab"/></font>
				<img valign="bottom" src="resources/images/list.gif" border="0" alt="avg.grunnlag">
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a style="display:block;" id="transportLink" href="tvinnsadadmin_transport.do">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tvinn.sad.admin.transport.list.tab"/></font>
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="transport general list">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="70%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
		</tr>
	</table>
	</td>
</tr>

<tr>
	<td>
	<%-- search filter component --%>
		
 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 			<tr height="20"><td></td></tr>
 			
 			
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
 			

 	        <tr>
 	        	<td width="50%" valign="top">
 	        		<table style="width:100%;" align="center" class="XformFrame" border="0" cellspacing="0" cellpadding="0">
 	        			<tr height="10"><td >&nbsp;</td></tr>
 	        			<tr>
						<td width="99%" valign="top">
							<table class="dashboardFrameHeader" align="center" style="width:90%" cellspacing="1" cellpadding="0">
					 			<tr height="18">
						 			<td  class="text14White">
							 			&nbsp;<img style="vertical-align: middle" src="resources/images/bulletGreen.png" width="8" hight="8" border="0" alt="alt">
							 			Beregningskriteriene
						 			</td>
						    		</tr>
					 		</table>
						</td>
						</tr>	
 	        			
 	        			<tr>
						<td width="99%" valign="top">
						<table style="width:90%" align="center" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="1" cellpadding="0">
						<tr>
							<td>
							<form name="sadAdminAvggrunnlagForm" id="sadAdminAvggrunnlagForm" action="tvinnsadadmin_avggrunnlag.do?action=doCalculate" method="post" >
							<table align="left" style="width:90%" cellspacing="1" cellpadding="0"> 
					 			<tr height="8"><td >&nbsp;</td></tr>
 	        					<tr>
 	        						<td>&nbsp;</td>
					 	        	<td class="text14" align="left" >&nbsp;Fra dato</td>
					                <td class="text14" align="left" >&nbsp;Til dato</td>
					            </tr>
					            <tr>
					            	<td>&nbsp;</td>
					 	        	<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="fromDate" id="fromDate" size="10" maxlength="6" value='${searchFilter.fromDateNO}'>&nbsp;</td>
									<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="toDate" id="toDate" size="10" maxlength="6" value='${searchFilter.toDateNO}'>&nbsp;</td>
									
								</tr>
								
								<tr height="5"><td >&nbsp;</td></tr>
					            <tr> 
					            	<td>&nbsp;</td>   
					                <td class="text14" align="left" >&nbsp;Kunde
					                	<a tabindex="-1" id="avggCustomerIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
										</a>
					                </td>
								</tr>
					 	        <tr>
					 	        	<td>&nbsp;</td>
					 	        	<td >
										<input type="text" class="inputText" name="avggCustomerId" id="avggCustomerId" size="10" maxlength="8" value='${searchFilter.avggCustomerId}'>&nbsp;
									</td>
									<td >	
										<input readonly type="text" class="inputTextReadOnly" name="avggCustomerName" id="avggCustomerName" size="35" maxlength="35" value=''>
									</td>
									<td valign="top" align="left" >
					                   &nbsp;<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lage'>
					                </td>
								</tr>
								<tr height="8"><td >&nbsp;</td></tr>
								
 	        				</table>
 	        				</form>
 	        				</td>
 	        			</tr>
 	        			<tr height="10"><td ></td></tr>
 	        			</table>	
 	        			</td>
 	        			</tr>
 	        			<tr height="15"><td >&nbsp;</td></tr>
 	        		</table>
        		</td>
 	        		
				<td width="50%" valign="top">
					<table style="width:85%" align="center" cellspacing="0" border="0" cellpadding="0">
						<tr height="10"><td >&nbsp;</td></tr>
						<tr>
						<td class="text14">
			 				<img style="vertical-align: bottom" src="resources/images/excel.png" width="18" height="18" border="0" alt="show docs">
			 				&nbsp;<b>Resultat</b>
			 			</td>
			    		</tr>
	    				<tr height="2"><td></td></tr>
						<tr class="tableHeaderField" height="20" valign="left">
		                    <td class="tableHeaderFieldFirst">&nbsp;Dokument&nbsp;</td>
		               </tr> 
		              
		               	<c:forEach var="record" items="${model.list}" varStatus="counter">    
			               <tr class="tableRow" height="20" >
			               <td class="tableCellFirst">&nbsp;&nbsp;
			               		<a href="tvinnsadadmin_renderArchive.do?fp=${record.filnam}" target="_new">
			               			<img title="select" style="vertical-align:middle;" src="resources/images/bebullet.gif" border="0" alt="edit">&nbsp;${record.filnam}
			               		</a>
			               </td>
			            </tr> 
			            </c:forEach>
			             
		            </table>
				</td>
				</tr>			
      		</table>	
 	        	</td>
 	        </tr>
 	        <tr height="20"><td>&nbsp;</td></tr>
		</table>

	<tr height="3"><td></td></tr>
		
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->


