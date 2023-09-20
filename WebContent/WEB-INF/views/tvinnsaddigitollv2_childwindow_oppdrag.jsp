<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_childwindow_oppdrag.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
				<form>
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
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
				    				Tur&nbsp;${model.tur}
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
		                    	<th width="2%" class="tableHeaderField12" >Avd</th>
		                    	<th width="2%" class="tableHeaderField12" >Opd</th>
		                    	<th width="2%" class="tableHeaderField12" >Br.vekt</th>
		                    	<th width="2%" class="tableHeaderField12" >Kolli</th>
		                    	<th width="2%" class="tableHeaderField12" >Tidl.doks</th>
		                    	<th width="2%" class="tableHeaderField12" >Avs</th>
		                    	<th width="2%" class="tableHeaderField12" >Mott</th>
		                    	<th width="2%" class="tableHeaderField12" >Reg.dato</th>
		                	</tr>
		                	</thead>
		                	<tbody> 
		                	<c:forEach items="${model.list}" var="record" varStatus="counter">    
				             <tr class="tableRow" height="20" >
					           <td width="2%" align="center" class="tableCellFirst12">
					           		<a tabindex=-1 title="${model.tur}_${record.siavd}_${record.sitdn}" id="recordUpdate_${record.sitdn}" href="#" onClick="getItemData(this);">
				               			<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;
				               		</a>
		
					           	</td>	
				          	   <td width="2%" align="center"class="tableCell12" >${record.siavd}</td>
				          	   <td width="2%" align="center" class="tableCell12" >${record.sitdn}</td>
				          	   <td width="2%" align="center" class="tableCell12" >${record.sivkb}</td>
				          	   <td width="2%" align="center" class="tableCell12" >${record.sintk}</td>
				          	   <td width="2%" align="center" class="tableCell12" >${record.wehrg}&nbsp;${record.weh0068a}&nbsp;${record.weh0068b}</td>
				          	   <td width="2%" align="center" class="tableCell12" >${record.sinas}</td> 
			               	   <td width="2%" align="center" class="tableCell12" >${record.sinak}</td>
			               	   <td width="2%" align="center" class="tableCell12" >${record.sidt}</td>		               	
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
		
