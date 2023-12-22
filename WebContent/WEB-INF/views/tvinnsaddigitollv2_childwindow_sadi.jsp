<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_childwindow_sadi.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
	<table width="90%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
					           		
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
								<td colspan="10">
									<form name="tvinnsadCustomerForm" id="tvinnsadCustomerForm" action="tvinnsaddigitollv2_childwindow_sadi.do?action=doFind" method="post">
										<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
										<input type="hidden" name="lnrt" id="lnrt" value="${model.lnrt}">
										<input type="hidden" name="lnrm" id="lnrm" value="${model.lnrm}">
										<input type="hidden" name="tur" id="tur" value="${model.tur}">
									
									<table>
									<tr>
					            		<td class="text14">&nbsp;Kjøretøy kjennemerke</td>
										<td class="text14">&nbsp;<input readonly type="text" class="inputTextReadOnly" name="bil" id="bil" size="10" maxlength="15" value="${model.bil}"></td>
										
					            		<td class="text14">&nbsp;Dato</td>
										<td class="text14">&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dato" id="dato" size="8" maxlength="6" value="${model.dato}"></td>
										
										<td class="text14">&nbsp;</td>
				           				<td align="right">&nbsp;<input class="inputFormSubmitStd" type="submit" name="submit" value='Søk'></td>
					           		</tr>
					           		</table>
					           		</form>
								</td>
								</tr>
								<tr height="15"><td></td></tr> 
								<tr>
									<td class="text14  ">
						    			<font class="inputText isa_warning" >
						    				Import fortollinger
						    			</font>
						    		</td>
						    		<%--
						    		<td nowrap align="right" class="text16" style="color: gray;"  >
						    			<%--Liste av oppdrag med godkjente deklarasjoner
						    			
						    		</td>
						    		 --%>
						    		<td align="left">
					               		<c:if test="${not empty model.list}">
					               			&nbsp;<input title="automatisk generere house(s)..." class="inputFormSubmit" type="button" name="buttonCreateHousesOk" id="buttonCreateHousesOk" value='Ok Auto'>
					               		</c:if>
					               		<input class="inputFormSubmit" type="button" name="buttonCancel" id="buttonCancel" value='Avbryt'>
					               		<c:if test="${not empty model.list}">
					               			<input class="inputFormSubmitStd" type="button" name="buttonCheckAll" id="buttonCheckAll" value='Velg alle'>
					               		</c:if>
					               	</td>
					               	
					    		</tr>
				    		</td>
				    		</table>
				   		</tr>
						<tr height="2"><td></td></tr> 
						
						<tr>
						<td class="text11">
									
						<table id="mainList" class="compact" >
							<thead>
							<tr class="tableHeaderField" height="20" >
								<th title="Velg manuelt ..." width="2%" class="tableHeaderFieldFirst12" >
									<img title="select manual edit" src="resources/images/update.gif" border="0" alt="edit">
								</th>
								<th width="2%" title="Velg automatisk generere house(s)..." class="tableHeaderField12" >Velg auto.</th>
		                    	<th width="2%" class="tableHeaderField12" >Avd</th>
		                    	<th width="2%" class="tableHeaderField12" >Opd</th>
		                    	<th width="2%" class="tableHeaderField12" >Bilnr</th>
		                    	<th width="2%" class="tableHeaderField12" >Ekst.ref.</th>
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
				               <td width="2%" align="center" class="tableCellFirst12" >
				          	   		<a style="display: block;" tabindex=-1 title="${record.siavd}_${record.sitdn}_${record.sidt}_${model.bil}" id="recordUpdate_${record.sitdn}" href="#" onClick="getItemData(this);">
				          	   			<img title="select manual edit" src="resources/images/update.gif" border="0" alt="edit">
				          	   		</a>
				          	   </td>
				          	   <td width="2%" align="center" class="tableCell12">
					           		<input class="clazzCreateHouseAware" style="cursor:pointer;" type="checkbox" value="J" id="avd${record.siavd}_opd${record.sitdn}_dato${record.sidt}_bil${model.bil}" name="avd${record.siavd}_opd${record.sitdn}_dato${record.sidt}_bil${model.bil}" >
					           </td>	
				          	   <td width="2%" align="center"class="tableCell12" >${record.siavd}</td>
				          	   <td width="2%" align="center" class="tableCell12" >${record.sitdn}</td>
				          	   <td width="2%" align="center" class="tableCell12" >${record.sitrid}</td>
				          	   <td width="2%" align="center" class="tableCell12" >${record.fssok}</td>
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
		
