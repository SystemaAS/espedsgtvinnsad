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
		                    	<th width="2%" class="tableHeaderField12" title="eibl">Verdi</th>
		                		<th width="2%" class="tableHeaderField12" title="eistk">Ant</th>
		                		<th width="2%" class="tableHeaderField12" >Tariffnr</th>
		                		<th nowrap width="2%" class="tableHeaderField12" >SelgId VOEC</th>
		                		<th width="2%" class="tableHeaderField12" >Role</th>
		                		<th width="2%" class="tableHeaderField" title="Fjerner fra db" >Slett</th>
		                	</tr>
		                	</thead>
		                	<tbody> 
		                	<c:forEach items="${model.record.listItemLines}" var="itemLinesRecord" varStatus="counter">    
				             <tr class="tableRow" height="20" >
					           <td width="2%" align="center" class="tableCellFirst12">
					           		<a tabindex=-1 title="${itemLinesRecord.eilnrt}_${itemLinesRecord.eilnrm}_${itemLinesRecord.eilnrh}" id="recordUpdate_${itemLinesRecord.eili}" href="#" onClick="getItemData(this);">
				               			<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;
				               		</a>
		
					           	</td>	
				          	   <td width="2%" align="center"class="tableCell12" >${itemLinesRecord.eili}</td>
				          	   <td width="2%" align="center" class="tableCell12" >${itemLinesRecord.eist}</td>
			               	   <td width="2%" align="right" class="tableCell12" >${itemLinesRecord.eibl}&nbsp;</td>
				               <td width="2%" align="right" class="tableCell12" >${itemLinesRecord.eistk}&nbsp;</td>
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
							<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
							<input type="hidden" name="action" id="action" value="doUpdate">
							<input type="hidden" name="eilnrt" id="eilnrt" value="${model.record.ehlnrt}">
							<input type="hidden" name="eilnrm" id="eilnrm" value="${model.record.ehlnrm}">
							<input type="hidden" name="eilnrh" id="eilnrh" value="${model.record.ehlnrh}">
							<input type="hidden" name="eiavd" id="eiavd" value="${model.record.ehavd}">
							<input type="hidden" name="eipro" id="eipro" value="${model.record.ehpro}">
							<input type="hidden" name="eitdn" id="eitdn" value="${model.record.ehtdn}">
							<input type="hidden" name="eiroe" id="eiroe" value="FR5">
							
							 
					
						<table class="text14 formFrame" style="width:70%; background-color:lightyellow;" cellspacing="2" align="left" >
						<tr>
							<td class="text14"><span title="eili">Lin</span></td>
							<td class="text14">
								<img style="cursor:pointer;" onMouseOver="showPop('eibl_info');" onMouseOut="hidePop('eibl_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
				            	<span title="eibl">Verdi</span>
			               		<div class="text11" style="position: relative;" align="left">
			                	<span style="position:absolute;top:2px; width:250px;" id="eibl_info" class="popupWithInputText text11"  >
			                	<p><b>Verdi</b><br/>
			                		Fakturert verdi for denne varen. Beløp i norske kroner (NOK)
			                	</p>
			                	<p>
			                		Skal kun brukes for VOEC: norske kroner eks mva, eks frakt, eks forsikring for den enkelte vare.
			                	</p>
								</span>	
								</div>
							
							</td>
							<td class="text14">
								<img style="cursor:pointer;" onMouseOver="showPop('eistk_info');" onMouseOut="hidePop('eistk_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
				            	<span title="eistk">Ant</span>
			               		<div class="text11" style="position: relative;" align="left">
			                	<span style="position:absolute;top:2px; width:250px;" id="eistk_info" class="popupWithInputText text11"  >
			                	<p><b>Antall</b><br/>
			                		Antall av den angitte VOEC-varen i denne forsendelsen
			                	</p>
			                	
								</span>	
								</div>
							</td>
							<td class="text14">
								<img style="cursor:pointer;" onMouseOver="showPop('eivnt_info');" onMouseOut="hidePop('eivnt_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
				            	<span title="eivnt">Tariffnr</span>
				            	<div class="text11" style="position: relative;" align="left">
			                	<span style="position:absolute;top:2px; width:250px;" id="eivnt_info" class="popupWithInputText text11"  >
			                	<p><b>Tariffnr.</b><br/>
			                		Varenummer i tolltariffen for VOEC-varen. <br/>
			                		Eksempel <b>551100</b> (maxLength: 6, minLength: 6)
			                	</p>
			                	<p>
			                		The Harmonized Commodity Description and Coding System generally referred to as "Harmonized System" or simply "HS" is a multipurpose international product nomenclature developed by the World Customs Organization (WCO).
			                	</p>
			                	
								</span>	
								</div>
								
							</td>
							<td  class="text14">
								<img style="cursor:pointer;" onMouseOver="showPop('eirge_info');" onMouseOut="hidePop('eirge_info');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
				            	<span title="eirge">SelgerId - VOEC</span>
			               		<div class="text11" style="position: relative;" align="left">
			                	<span style="position:absolute;top:2px; width:250px;" id="eirge_info" class="popupWithInputText text11"  >
			                	<b>SelgerId</b><br/>
			                	<p>	Denne seksjonen angir referanse til VOEC, det vil si at det angir at denne varelinjen er underlagt VOEC-bestemmelsene. 
			                		Dersom selger i utlandet har oppgitt at varelinjen er underlagt VOEC, skal VOEC nummer oppgis her
			                	</p>
			                	<p>
			                		Her oppgis VOEC-nummeret til selger av forsendelsen. Eksempel: 12345 (maxLength: 17, minLength: 1)
			                	</p>
								</span>	
								</div>
							
							</td>
						</tr>
						<tr>
							<td class="text14"><input readonly type="text" class="inputTextReadOnly" name="eili" id="eili" size="6" maxlength="5" value=""></td>
							<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="eibl" id="eibl" size="17" maxlength="15" value=""></td>
							<td class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="eistk" id="eistk" size="9" maxlength="7" value=""></td>
							<td nowrap class="text14"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="eivnt" id="eivnt" size="8" maxlength="6" value="">
								<a tabindex="-1" id="eivntIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								</a>
							</td>
							<td nowrap class="text14">&nbsp;<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="eirge" id="eirge" size="19" maxlength="17" value=""></td>
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
		
