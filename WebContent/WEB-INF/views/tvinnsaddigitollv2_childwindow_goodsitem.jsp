<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_childwindow_goodsitem.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
					
													           		
			    <tr>
				<td>		
				<table style="width:90%;" border="0" >
			    	<%-- separator --%>
			        <tr height="2"><td></td></tr> 
					<tr>
						<td>
						<table style="width:90%;" id="containerdatatableTable" cellspacing="2" align="left" >
						<tr>
						<td class="text11">
									
						<table id="mainList" class="compact" >
							<thead>
							<tr class="tableHeaderField" height="20" >
								<th width="2%" class="tableHeaderFieldFirst12"  >Endre</th>
		                    	<th width="2%" class="tableHeaderField12" >Lin</th>
		                    	<th width="2%" class="tableHeaderField12" >Status</th>
		                    	<th width="2%" class="tableHeaderField12" >Stk</th>
		                		<th width="2%" class="tableHeaderField12" >Vrd</th>
		                		<th width="2%" class="tableHeaderField12" >Tariffnr</th>
		                		<th nowrap width="2%" class="tableHeaderField12" >SelgId VOEC</th>
		                		<th width="2%" class="tableHeaderField12" >Role</th>
		                		<th width="2%" class="tableHeaderField" title="Fjerner fra db" >Slett</th>
		                	</tr>
		                	</thead>
		                	<tbody> 
		                	<c:forEach items="${model.record.listItemLines}" var="itemLinesRecord" varStatus="counter">    
				             <tr class="tableRow" height="20" >
					           <td width="2%" align="center" class="tableCellFirst12"><img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="edit"></td>	
				          	   <td width="2%" align="center"class="tableCell12" >${itemLinesRecord.eili}</td>
				          	   <td width="2%" align="center" class="tableCell12" >${itemLinesRecord.eist}</td>
			               	   <td width="2%" align="right" class="tableCell12" >${itemLinesRecord.eistk}&nbsp;</td>
				               <td width="2%" align="right" class="tableCell12" >${itemLinesRecord.eibl}&nbsp;</td>
				               <td width="2%" align="center" class="tableCell12" >${itemLinesRecord.eivnt}</td>
				               <td width="2%" align="center" class="tableCell12" >${itemLinesRecord.eirge}</td>
				               <td width="2%" align="center" class="tableCell12" >${itemLinesRecord.eiroe}</td> 
				               <td width="2%" class="tableCell" align="center"> 
		               		  		<a tabindex=-1 class="removeLink" id="removeLink${counter.count}" runat="server" href="#">
										<img src="resources/images/delete.gif" border="0" alt="remove">
									</a>
									<div style="display: none;" class="clazz_dialog" id="dialogDeleteItem${counter.count}" title="Dialog">
										<form action="tvinnsaddigitollv2_delete_goodsitem.do" name="deleteItemForm${counter.count}" id="deleteItemForm${counter.count}" method="post">
											<input type="hidden" name="current_id1${counter.count}" id="current_id1${counter.count}" value="${itemLinesRecord.eilnrt}">
											<input type="hidden" name="current_id2${counter.count}" id="current_id2${counter.count}" value="${itemLinesRecord.eilnrm}">
											<input type="hidden" name="current_id3${counter.count}" id="current_id3${counter.count}" value="${itemLinesRecord.eilnrh}">
											<input type="hidden" name="current_id4${counter.count}" id="current_id4${counter.count}" value="${itemLinesRecord.eili}">
											<input type="hidden" name="current_avd${counter.count}" id="current_avd${counter.count}" value="${itemLinesRecord.eiavd}">
											<input type="hidden" name="current_pro${counter.count}" id="current_pro${counter.count}" value="${itemLinesRecord.eipro}">
											<input type="hidden" name="current_tdn${counter.count}" id="current_tdn${counter.count}" value="${itemLinesRecord.eitdn}">
											<p class="text14" >Er du sikker på at du vil slette denne&nbsp;Lin&nbsp;<b>${itemLinesRecord.eili}</b> fra <b>House</b> ?</p>
											
										</form>
									</div>
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
					
					<tr>
						<td>
						<form name="itemLinesForm" id="itemLinesForm" action="tvinnsaddigitollv2_edit_goodsitem.do" method="post">
							<input type="hidden" name="action" id="action" value="doUpdate">
							<input type="hidden" name="eilnrt" id="eilnrt" value="${model.record.ehlnrt}">
							<input type="hidden" name="eilnrm" id="eilnrm" value="${model.record.ehlnrm}">
							<input type="hidden" name="eilnrh" id="eilnrh" value="${model.record.ehlnrh}">
							<input type="hidden" name="eiavd" id="eiavd" value="${model.record.ehavd}">
							<input type="hidden" name="eipro" id="eipro" value="${model.record.ehpro}">
							<input type="hidden" name="eitdn" id="eitdn" value="${model.record.ehtdn}">
							<input type="hidden" name="eiroe" id="eiroe" value="FR5">
							
							 
					
						<table class="text14 formFrame" style="width:60%; background-color:lightyellow;" cellspacing="2" align="left" >
						<tr>
							<td class="text14"><span title="eili">Lin</span></td>
							<td class="text14"><span title="eibl">Stk</span></td>
							<td class="text14"><span title="eistk">Vrd</span></td>
							<td class="text14"><span title="eivnt">Tariffnr</span></td>
							<td class="text14"><span title="eirge">SelgerId - VOEC</span></td>
						</tr>
						<tr>
							<td class="text14"><input readonly type="text" class="inputTextReadOnly" name="eili" id="eili" size="6" maxlength="5" value=""></td>
							<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="eibl" id="eibl" size="17" maxlength="15" value=""></td>
							<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="eistk" id="eistk" size="9" maxlength="7" value=""></td>
							<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="eivnt" id="eivnt" size="8" maxlength="6" value=""></td>
							<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="eirge" id="eirge" size="19" maxlength="17" value=""></td>
							<td class="text14">
								<input class="inputFormSubmit" type="submit" name="saveItem" id="saveItem" value='Lagre'>
							</td>
						</tr>
						</table>
						</form>
						</td>
					</tr>
				</table>
				</td>
				</tr>
				
			</table> 
			</td>
		</tr>
</table>
		
