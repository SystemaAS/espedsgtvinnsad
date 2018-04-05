<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>


<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerBrregKundeKontroll.jsp" />
<!-- =====================end header ==========================-->

 
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsad_brreg_kundekontroll.js?ver=${user.versionEspedsg}"></SCRIPT> 

<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;Kundedata kontroll</font>
				<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
		</tr>
	</table>
	</td>
	</tr>
			<tr>
			<td>
				<%-- this table wrapper is necessary to apply the css class with the thin border --%>
				<table id="wrapperTable" class="tabThinBorderWhite" width="100%" cellspacing="1">
				<tr height="10"><td></td></tr> 
			
				<%-- Validation errors --%>
				<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
				<tr>
					<td>
			           	<table style="width:99%" align="left" border="0" cellspacing="0" cellpadding="0">
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
				<%-- -------------------- --%>
				<%-- Datatables component --%>
				<%-- -------------------- --%>
				
				<%-- list component --%>
				<tr>
	            <tr>
					<td>
					<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
						 the cosmetic frame will not follow the whole datatable grid including the search field.... --%>
					<table id="containerdatatableTable" style="width:100%" cellspacing="3" align="left" border="0">
					<tr>
						<td class="ownScrollableSubWindow" style="width:100%; height:60em;">
						<%-- this is the datatables grid (content) --%>
							<table id="invalideKunderList" class="display compact cell-border" border="0">
								<thead>
								<tr style="background-color:#EEEEEE">
								    <th class="text12">Kundnr</th>   
				                    <th class="text12">Kundenavn</th>
				                    <th class="text12">Org.nr</th>
				                    <th class="text12">HovedEnhet</th>
				                    <th class="text12">UnderEnhet</th>
				                    <th class="text12">HovedEnhets Org.nr</th>
				                    <th class="text12">Konkurs</th>
				                    <th class="text12">Merverdi</th>
				                    <th class="text12">Under avvikling</th>
				                    <th class="text12">Under tvangsavvikling</th>
				                </tr> 
				                </thead>
				                
				                <tbody>
					            <c:forEach items="${model.list}" var="record" varStatus="counter">    
					               <c:choose>           
					                   <c:when test="${counter.count%2==0}">
					                       <tr >
					                   </c:when>
					                   <c:otherwise>   
					                       <tr >
					                   </c:otherwise>
					               </c:choose>
			
			        		       <td class="text11">&nbsp;${record.kundenr}</td>
			            		   <td class="text11">&nbsp;${record.kundenavn}</td>
			            		   <td class="text11">&nbsp;${record.orgnr}</td>
			            		   <td class="text11">&nbsp;${record.existsashovedenhet}</td>
			            		   <td class="text11">&nbsp;${record.existsasunderenhet}</td>
			            		   <td class="text11">&nbsp;${record.overordnetenhetorgnr}</td>
			            		   <td class="text11">&nbsp;${record.konkurs}</td>
					               <td class="text11">&nbsp;${record.registrertimvaregisteret}</td>
			            		   <td class="text11">&nbsp;${record.underavvikling}</td>
					               <td class="text11">&nbsp;${record.undertvangsavviklingellertvangsopplosning}</td>
					               
					            </tr> 
					            </c:forEach>
					            <tfoot>
								<tr style="background-color:#EEEEEE">
									<c:if test="${not empty cw}">
										<th width="5%" class="text12">Hente Ordre</th>
									</c:if>
								    <th class="text12">Kundnr</th>   
				                    <th class="text12">Kundenavn</th>
				                    <th class="text12">Org.nr</th>
				                    <th class="text12">HovedEnhet</th>
				                    <th class="text12">UnderEnhet</th>
				                    <th class="text12">HovedEnhets Org.nr</th>
				                    <th class="text12">Konkurs</th>
				                    <th class="text12">Merverdi</th>
				                    <th class="text12">Under avvikling</th>
				                    <th class="text12">Under tvangsavvikling</th>
				                </tr> 
				                </tfoot>
					            </tbody>
				            </table>
				        </td>
		            </tr>
	           		<tr>
	            		<td colspan="4" class="text12">
		            		<table align="left" class="tabThinBorderWhite">
						    <tr>
								<td>	
									<a href="invalidaKunderMainListExcelView.do" target="_new">
				                			<img valign="bottom" id="mainListExcel" src="resources/images/excel.gif" width="14" height="14" border="0" alt="excel">
				                			<font class="text12MediumBlue">&nbsp;Excel</font>
				 	        		</a>
				 	        		&nbsp;
			 	        		</td>
		 	        		</tr>
		 	        		</table>
			 			</td>
	         		</tr>
	            </table> <%--containerdatatableTable END --%>
	            </td>
	            </tr>
				</table> <%--wrapperTable END --%>
	         </td>
	         </tr>
         <tr height="10"><td>&nbsp;</td></tr>
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

