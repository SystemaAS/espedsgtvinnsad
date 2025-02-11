<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_childwindow_docapi_log.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
				<form>
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="ehdkh" id="ehdkh" value="${model.ehdkh}">
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
				    				Dok.nr: ${model.ehdkh}
				    			</font>
				    		</td>
				   		</tr>
						<tr height="5"><td></td></tr> 
						<tr>
							<td class="text11">
										
							<table id="mainList" class="compact" >
								<thead>
								<tr class="tableHeaderField" height="20" >
									<th class="tableHeaderFieldFirst12" >Sekv.nr</th>
			                    	<th class="tableHeaderField12" >Dok.typ</th>
			                    	<th class="tableHeaderField12" >Dekl.id</th>
			                    	<th class="tableHeaderField12" >Senddato</th>
			                    	<th class="tableHeaderField12" >Sendtid</th>
			                    	<th class="tableHeaderField12" >Handelsfak.</th>
			                    	<th class="tableHeaderField12" >Api result</th>
			                	</tr>
			                	</thead>
			                	<tbody> 
			                	<c:if test="${not empty model.list}">  
			                		 <c:forEach items="${model.list}" var="record" varStatus="counter">   
							             <tr class="tableRow" height="20" >
							               <td align="center" class="tableCell12" >${record.deklsekv}</td>
							          	   <td align="center" class="tableCell12" >${record.doctyp}</td>
						               	   <td align="center" class="tableCell12" >${record.deklid}</td>
						               	   <td align="center" class="tableCell12" >${record.senddate}</td>
						               	   <td align="center" class="tableCell12" >${record.sendtime}</td>
						               	   <td align="center"class="tableCellFirst12" >
						               	   		<c:choose>
						               	   			<c:when test="${not empty record.resultapi && fn:startsWith(record.resultapi, '20')}">
						               	   				<img src="resources/images/bulletGreen.png" onMouseOver="showPop('pdf_info${counter.count}');" onMouseOut="hidePop('pdf_info${counter.count}');" width="10" height="10" border="0" >
						               	   				<span title="pdf"></span>
									                		<div class="text11" style="position:relative;" align="left">
										                	<span style="position:absolute;top:2px;" id="pdf_info${counter.count}" class="popupWithInputText text11" >
											           		<b>PDF</b>
											           		<p>${record.doclnk}</p>
															</span>	
															</div>
						               	   			</c:when>
						               	   			<c:otherwise>
						               	   				<img src="resources/images/bulletRed.png" width="10" height="10" border="0" >
						               	   			</c:otherwise>
						               	 		</c:choose>
						               	   </td>
						               	   <td align="center"class="tableCellFirst12" >${record.resultapi}</td>
							          	   
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
		
