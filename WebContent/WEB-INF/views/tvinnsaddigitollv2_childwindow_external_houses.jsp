<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_childwindow_external_houses.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Søk
			</td>
		</tr>
		<tr>
		<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
					
					<tr>
					<td>
						<table>
						<form name="juxForm" id="juxForm">
								<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
								<input type="hidden" name="emlnrt" id="emlnrt" value="${model.emlnrt}">
								<input type="hidden" name="emlnrm" id="emlnrm" value="${model.emlnrm}">
								
							</form>	
						<tr>
									
							<td align="left">
	           				<c:if test="${not empty model.sadmocfList}">
					               &nbsp;<input title="automatisk generere send-filer..." class="inputFormSubmit" type="button" name="buttonCreateFilesOK" id="buttonCreateFilesOK" value='Ok'>
					         </c:if>
					         <input class="inputFormSubmit" type="button" name="buttonCancel" id="buttonCancel" value='Avbryt'>
					         <c:if test="${not empty model.sadmocfList}">
					              <input class="inputFormSubmitStd" type="button" name="buttonCheckAll" id="buttonCheckAll" value='Velg alle'>
					         </c:if>
	           				</td>
		           		</tr>
		           		</table>
					</td>
					</tr>
					
					
													           		
	           		<tr height="10"><td></td></tr>
					
					<tr class="text14" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="partyList" class="display compact cell-border" width="100%" >
						<thead>
						<tr class="tableHeaderField" height="20" >
							<th class="text14" >&nbsp;&nbsp;</th>
							<th class="text14" >&nbsp;Velg</th>
							<th class="text14" >&nbsp;Navn&nbsp;</th>
		                    <th class="text14" >&nbsp;Orgnr.&nbsp;</th>
		                    <th class="text14" >&nbsp;Comm&nbsp;</th>
		                    <th class="text14" >&nbsp;Format&nbsp;</th>
		                    <th class="text14" >&nbsp;Channel&nbsp;</th>
		                    <th class="text14" >&nbsp;Info&nbsp;</th>
		                    <th class="text14" >&nbsp;sFtp target dir&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.sadmocfList}" varStatus="counter">    
			               <tr class="text14">
			                   <td style="cursor:pointer;" class="text14MediumBlue" id="orgnr${record.orgnr}_name${record.name}_commtype${record.commtype}_format${record.format}_singlePick" >
				               		<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;
				               	</td>
				               	<td width="2%" align="center" class="text12">
					           		<input title="${record.name}" class="clazzSendDocIdToExternalPartyAware" style="cursor:pointer;" type="checkbox" value="J" id="orgnr${record.orgnr}" name="orgnr${record.orgnr}" >
					           </td>
			               	   <td width="15%" class="text12">&nbsp;${record.name}</td>
			               	   <td class="text12">&nbsp;${record.orgnr}</td>
			               	   <td class="text12">&nbsp;${record.commtype}</td>
			               	   <td class="text12">&nbsp;${record.format}</td>
			               	   <td class="text12">&nbsp;<c:if test="${not empty record.xmlxsd && record.xmlxsd!='null'}">${record.xmlxsd}</c:if></td>
			               	   <td class="text12">
			               	   		<c:if test="${not empty record.ftpserver && record.ftpserver!='null'}">&nbsp;<b>FTP-server&nbsp;</b>${record.ftpserver}</c:if>
			               	   		<c:if test="${not empty record.ftpdir && record.ftpdir!='null'}">&nbsp;<b>Dir&nbsp;</b>${record.ftpdir}</c:if>
			               	   		<c:if test="${not empty record.avsorgnr && record.avsorgnr!='null'}">
				               	   		<img style="cursor:pointer;" onMouseOver="showPop('extra_info${counter.count}');" onMouseOut="hidePop('extra_info${counter.count}');"style="vertical-align:middle;" width="11px" height="11px" src="resources/images/info3.png" border="0" alt="info">
						            	<div class="text11" style="position: relative;" align="left">
					                	<span style="position:absolute;top:2px; width:250px;" id="extra_info${counter.count}" class="popupWithInputText text11"  >
						           		<b>Avs.Navn og Orgnr</b> - når man sender "house" tilbake til Ombud/Representanten.<br/>
						           		<p><c:if test="${not empty record.avsname && record.avsname!='null'}">${record.avsname}-</c:if>
						           			<c:if test="${not empty record.avsorgnr && record.avsorgnr!='null'}">${record.avsorgnr}</c:if>
						           		</p>
										</span>	
										</div>
									</c:if>
									
			               	   </td>
			               	   <td class="text12">
			               	   	<c:if test="${not empty record.sftpdir_ps && record.sftpdir_ps!='null'}">${record.sftpdir_ps}</c:if>
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
	</table> 
