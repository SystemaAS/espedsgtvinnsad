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
					<input type="hidden" name="lnrt" id="lnrt" value="${model.lnrt}">
					<input type="hidden" name="lnrm" id="lnrm" value="${model.lnrm}">
				</form>	
													           		
			    <tr>
				<td>		
				<table style="width:100%;" border="0" >
			    	<%-- separator --%>
			        <tr height="2"><td></td></tr> 
			        
					<tr>
						<td>
						<table id="containerdatatableTable" cellspacing="2" align="left" >
						
						<tr >
							<td>
							<table id="old" >
								<tr>
								<td class="text14  ">
					    			<font class="inputText isa_warning" >
					    				Tur&nbsp;${model.tur}
					    			</font>
					    		</td>
					    		<td width="20px">&nbsp;</td>
					    		<td nowrap align="right" class="text16" style="color: gray;"  >
					    			<%--Liste av oppdrag med godkjente deklarasjoner --%>
					    			
					    		</td>
					    		</tr>
				    		</td>
				    		</table>
				   		</tr>
						<tr height="5"><td></td></tr> 
						<tr>
			               <td align="left">
			               		<c:if test="${not empty model.list}">
			               			&nbsp;<input class="inputFormSubmit" type="button" name="buttonCreateHousesOk" id="buttonCreateHousesOk" value='Ok'>
			               		</c:if>
			               		&nbsp;<input class="inputFormSubmit" type="button" name="buttonCancel" id="buttonCancel" value='Avbryt'>
			               </td>
		               </tr>
		               	
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
		                    	<th width="2%" class="tableHeaderField12" ></th>
		                    	<th width="2%" class="tableHeaderField12" >Mott</th>
		                    	<th width="2%" class="tableHeaderField12" ></th>
		                    	<th width="2%" class="tableHeaderField12" >Reg.dato</th>
		                	</tr>
		                	</thead>
		                	<tbody> 
		                	<c:forEach items="${model.list}" var="record" varStatus="counter">    
				             <tr class="tableRow" height="20" >
					           <td width="2%" align="center" class="tableCellFirst12">
					           		<input class="clazzCreateHouseAware" type="checkbox" value="J" id="avd${record.siavd}_opd${record.sitdn}_tur${model.tur}" name="avd${record.siavd}_opd${record.sitdn}_tur${model.tur}" >
					           </td>	
				          	   <td width="2%" align="center"class="tableCell12" >${record.siavd}</td>
				          	   <td width="2%" align="center" class="tableCell12" >
				          	   		<a tabindex=-1 title="${model.tur}_${record.siavd}_${record.sitdn}" id="recordUpdate_${record.sitdn}" href="#" onClick="getItemData(this);">
				          	   			${record.sitdn}
				          	   		</a>
				          	   </td>
				          	   <td width="2%" align="center" class="tableCell12" >${record.sivkb}</td>
				          	   <td width="2%" align="center" class="tableCell12" >${record.sintk}</td>
				          	   <td width="2%" align="center" class="tableCell12" >${record.wehrg}&nbsp;${record.weh0068a}&nbsp;${record.weh0068b}</td>
				          	   <td width="2%" align="center" class="tableCell12" >${record.sinas}</td> 
				          	   <td width="2%" align="center" class="tableCell12" ><span style="font-size: 10px;">${record.siads1}&nbsp;${record.siads2}&nbsp;${record.siads3}</span></td>
			               	   <td width="2%" align="center" class="tableCell12" >${record.sinak}</td>
			               	   <td width="2%" align="center" class="tableCell12" ><span style="font-size: 10px;">${record.siadk1}&nbsp;${record.siadk2}&nbsp;${record.siadk3}</span></td>
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
		
