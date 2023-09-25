<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_childwindow_transports.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
				<form>
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="fromEmlnrt" id="fromEmlnrt" value="${model.fromEmlnrt}">
					<input type="hidden" name="fromEmlnrm" id="fromEmlnrm" value="${model.fromEmlnrm}">
					<input type="hidden" name="fromEtktyp" id="fromEtktyp" value="${model.fromEtktyp}">
				</form>									           		
			    <tr>
				<td>		
				<table style="width:90%;" border="0" >
			    	<%-- separator --%>
			        <tr height="2"><td></td></tr> 
					<tr>
						<td>
						<table style="width:90%;" id="containerdatatableTable" cellspacing="2" align="left" >
						
						<tr >
							<td class="text14  ">
				    			<font class="inputText isa_warning" >
				    				Gyldige transporter er kun innenfor samme API og uten tidligere registrerte Masters
				    			</font>
				    		</td>
				   		</tr>
						<tr height="5"><td></td></tr> 
						<tr>
						<td class="text11">
									
						<table id="mainList" class="compact" >
							<thead>
							<tr class="tableHeaderField" height="20" >
								<th width="2%" class="tableHeaderFieldFirst12"  >Velg</th>
		                    	<th width="2%" class="tableHeaderField12" >Transport Lnr</th>
		                    	<th width="2%" class="tableHeaderField12" >Bilnr/Fly</th>
		                    	<th width="2%" class="tableHeaderField12" >Sjåførs navn</th>
		                    	<th width="2%" class="tableHeaderField12" >Api</th>
		                	</tr>
		                	</thead>
		                	<tbody> 
		                	<c:forEach items="${model.list}" var="itemLinesRecord" varStatus="counter">    
				             <tr class="tableRow" height="20" >
					           <td width="2%" align="center" class="tableCellFirst12">
					           		<c:choose>
					           		<c:when test="${empty itemLinesRecord.listMasters}">
						           		<a tabindex=-1 title="${itemLinesRecord.etlnrt}" id="recordUpdate_${itemLinesRecord.etlnrt}" href="#" onClick="changeTransport(this);">
					               			<img valign="bottom" src="resources/images/transform.png" width="15" height="15" border="0" alt="edit">&nbsp;
					               		</a>
				               		</c:when>
				               		<c:otherwise>
				               			<img style="cursor: not-allowed" title="Ugyldig transport. Ingen tidigare reg. Master(s) skal eksistere i transporten du skal bytte til" valign="bottom" src="resources/images/lock.gif" border="0" alt="not-allowed">&nbsp;
				               		</c:otherwise>
									</c:choose>
					           	</td>	
				          	   <td width="2%" align="center"class="tableCell12" >${itemLinesRecord.etlnrt}</td>
				          	   <td width="2%" align="center" class="tableCell12" >${itemLinesRecord.etkmrk}</td>
			               	   <td width="2%" align="right" class="tableCell12" >${itemLinesRecord.etsjaf}</td>
			               	   <td width="2%" align="center" class="tableCell" >
				               		<c:choose>
				               		<c:when test="${ not empty itemLinesRecord.etktyp && fn:startsWith(itemLinesRecord.etktyp,'4') }">
										<img title="api:air" style="vertical-align:middle;" id="airplaneImg${itemLinesRecord.etlnrt}" src="resources/images/airplaneBlue.png" width="18" height="18"border="0" >&nbsp;
									</c:when>
									<c:otherwise>
										<img title="api:road" style="vertical-align:middle;" id="lorryImg${itemLinesRecord.etlnrt}" src="resources/images/lorry_green.png" width="16" height="16"border="0" >&nbsp;
									</c:otherwise>
									</c:choose>
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
					<tr height="2"><td></td></tr> 
	
				</table>
				</td>
				</tr>
				
			</table> 
			</td>
		</tr>
</table>
		
