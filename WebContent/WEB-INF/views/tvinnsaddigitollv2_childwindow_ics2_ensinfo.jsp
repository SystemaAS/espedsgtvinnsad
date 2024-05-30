<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsaddigitollv2_childwindow_transport_ics2_ensinfo.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
				<form name="ics2EnsForm" id="ics2EnsForm" action="tvinnsaddigitollv2_childwindow_ics2_presentation_ensinfo.do" method="post">
					<tr height="5"><td></td></tr> 
					<tr>
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="level" id="level" value="${model.level}">
						<td class="text14">
						<font class="inputText isa_warning" >
					    	ICS2 Presentation - Fremlegging - ENS Entry Summary Declaration
					    </font>	
					    </td>
				    </tr>
				    <tr height="10"><td></td></tr> 
				    <tr>
					    <td class="text14">
					    	&nbsp;Mrn&nbsp;
							<input type="text" class="inputText" name="mrn" id="mrn" size="39" maxlength="36" value="${model.mrn}">
							<input class="inputFormSubmit" onClick="setBlockUI(this)" type="submit" name="submit" value='<spring:message code="systema.tvinn.sad.search"/>'>
						</td>
						<td class="text14" >
							&nbsp;Request-Id&nbsp;<input type="text" class="inputText" name="lrn" id="lrn" size="39" maxlength="36" value="${model.lrn}">
						</td>
					</tr>
	
					
				</form>									           		
			    <tr>
				<td colspan="2">		
				<table style="width:100%;" border="0" >
			    	<%-- separator --%>
			        <tr height="2"><td><hr/></td></tr> 
					<tr>
						<td>
						<table style="width:100%;" id="containerdatatableTable" cellspacing="2" align="left" >
						
						<tr height="5"><td></td></tr> 
	
						<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
						specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
						<tr>
							<td>
							<div class="text12" >
								${model.content}		
							</div>
							</td>
						</tr>
						</table>
						</td> 	
	
					</tr>
					</table>
				</td>
				</tr>
			</table>
	</td>
	</tr>
	</table>
	
	

		