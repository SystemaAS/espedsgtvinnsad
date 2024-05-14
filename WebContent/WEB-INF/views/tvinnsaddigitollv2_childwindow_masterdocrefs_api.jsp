<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_childwindow_masterdocrefs_api.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
				<form>
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="mrn" id="mrn" value="${model.mrn}">
				</form>									           		
			    <tr>
				<td>		
				<table style="width:100%;" border="0" >
			    	<%-- separator --%>
			        <tr height="2"><td></td></tr> 
					<tr>
						<td>
						<table style="width:100%;" id="containerdatatableTable" cellspacing="2" align="left" >
						
						<tr >
							<td class="text14  ">
				    			<font class="inputText isa_warning" >
				    				Doc.refs on Master MRN: ${model.mrn}
				    			</font>
				    		</td>
				   		</tr>
						<tr height="5"><td></td></tr> 
						<tr>
							<td class="text11">
										
							<table id="mainList" class="compact" >
								<thead>
								<tr class="tableHeaderField" height="20" >
									<th width="2%" class="tableHeaderFieldFirst12" >Doc.nr</th>
			                    	<th width="2%" class="tableHeaderField12" >Type</th>
			                    	<th width="2%" class="tableHeaderField12" >Status</th>
			                	</tr>
			                	</thead>
			                	<tbody> 
			                	<c:forEach items="${model.list}" var="record" varStatus="counter">    
					             <tr class="tableRow" height="20" >
					          	   <td width="2%" align="center"class="tableCellFirst12" >${record.documentNumber}</td>
					          	   <td width="2%" align="center" class="tableCell12" >${record.type}</td>
				               	   <td width="2%" align="center" class="tableCell12" >${record.documentStatus}</td>
					            </tr> 
					            </c:forEach>
					            </tbody>
				            </table>
				            </td>
			            </tr>
			            
			            
			            <tr height="40"><td>&nbsp;</td></tr>
			            <tr >
							<td class="text14  ">
				    			<font class="inputText isa_success" >
				    				Doc.refs on Master MRN: ${model.mrnWithDescendants}&nbsp;<b>with descendants</b>
				    			</font>
				    		</td>
				   		</tr>
				   		<tr >
							<td class="text14">
				    			&nbsp;Houses -  Sum vekt:&nbsp;<b>${model.dto.sumOfWeightForHouseConsignments}</b>	
				    		</td>
				   		</tr>
				   		
				   		
						<tr height="5"><td></td></tr> 
			            <tr>
				            <td class="text11">
							<table id="mainListWithDescendants" class="compact" >
								<thead>
								<tr class="tableHeaderField" height="20" >
									<th width="2%" class="tableHeaderFieldFirst12" >Doc.nr</th>
			                    	<th width="2%" class="tableHeaderField12" >Type</th>
			                    	<th width="2%" class="tableHeaderField12" >Weight</th>
			                    	<th width="2%" class="tableHeaderField12" >Received/Status</th>
			                    	<th width="2%" class="tableHeaderField12" >Sum vekt-houses</th>
			                	</tr>
			                	</thead>
			                	<tbody>
			                	    
					             <tr class="tableRow" height="20" >
					          	   <td width="2%" align="center"class="tableCellFirst12" >${model.dto.documentNumber}</td>
					          	   <td width="2%" align="center" class="tableCell12" >${model.dto.type}</td>
					          	   <td width="2%" align="center" class="tableCell12" >${model.dto.weight}</td>
				               	   <td width="2%" align="center" class="tableCell12" >${model.dto.received}</td>
				               	   <td width="2%" align="center" class="tableCell12" >${model.dto.sumOfWeightForHouseConsignments}</td>
					            </tr> 
					            <c:if test="${not empty model.dto.houseConsignments}">
						            <c:forEach items="${model.dto.houseConsignments}" var="record" varStatus="counter">    
						             <tr class="tableRow" height="20" >
						          	   <td width="2%" align="center"class="tableCellFirst12" >house-${counter.count}</td>
						          	   <td width="2%" align="center" class="tableCell12" ></td>
						          	   <td width="2%" align="center" class="tableCell12" >${record.weight}</td>
					               	   <td width="2%" align="center" class="tableCell12" >${record.status}</td>
					               	   <td width="2%" align="center" class="tableCell12" ></td>
						            </tr> 
						            </c:forEach>
					      		</c:if>
					      		
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
		
