<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_childwindow_transports_consolidated.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
				<form>
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="lnrt" id="lnrt" value="${model.lnrt}">
					<input type="hidden" name="tur" id="tur" value="${model.tur}">
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
				    			<font class="inputText">
				    				Lnr&nbsp;<b>${model.lnrt}</b> - Turnr&nbsp;<b>${model.tur}</b>
				    			</font>
				    		</td>
				   		</tr>
				   		<tr height="5"><td></td></tr>
						<tr >
							<td class="text14  ">
				    			<font class="inputText isa_warning" >
				    				Gyldige transporter med status = AUTO-GEN. som kan konsolideres
				    			</font>
				    		</td>
				   		</tr>
				   		<tr height="2"><td></td></tr>
				   		<tr >
					   		<td align="left">
			               		<c:if test="${not empty model.list}">
			               			&nbsp;<input title="auto-consolidate house(s)..." class="inputFormSubmit" type="button" name="buttonCreateHousesOk" id="buttonCreateHousesOk" value='Ok Auto'>
			               		</c:if>
			               		<input class="inputFormSubmit" type="button" name="buttonCancel" id="buttonCancel" value='Avbryt'>
			               		<c:if test="${not empty model.list}">
			               			<input class="inputFormSubmitStd" type="button" name="buttonCheckAll" id="buttonCheckAll" value='Velg alle'>
			               		</c:if>
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
		                    	<th width="2%" class="tableHeaderField12" >Turnr</th>
		                    	<th width="2%" class="tableHeaderField12" >Bilnr/Fly</th>
		                    	<th width="2%" class="tableHeaderField12" >Transp.</th>
		                    	<th width="2%" class="tableHeaderField12" >Api</th>
		                	</tr>
		                	</thead>
		                	<tbody> 
		                	<c:forEach items="${model.list}" var="itemLinesRecord" varStatus="counter">    
				             <tr class="tableRow" height="20" >
					           <td width="2%" align="center" class="tableCellFirst12">
					           		<input class="clazzCreateHouseAware" style="cursor:pointer;" type="checkbox" value="J" id="${itemLinesRecord.etlnrt}" >
					           </td>
					           	</td>	
				          	   <td width="2%" align="center"class="tableCell12" >${itemLinesRecord.etlnrt}</td>
				          	   <td width="2%" align="center"class="tableCell12" >${itemLinesRecord.etpro}</td>
				          	   <td width="2%" align="center" class="tableCell12" >${itemLinesRecord.etkmrk}</td>
				          	   <td width="2%" align="center" class="tableCell12" >${itemLinesRecord.etnat}</td>
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
		
