<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadimport_edit_items_childwindow_kundensvarereg.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Søk Kundens Varenr.
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
						<form name="tvinnsadImportKundensvareRegForm" id="tvinnsadImportKundensvareRegForm" action="tvinnsadimport_edit_items_childwindow_kundensvarereg.do?action=doInit" method="post">
						<tr>
							<td class="text14">&nbsp;Varenr.</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="vkod" id="vkod" size="10" maxlength="10" value="${model.vkod}"></td>
							<td class="text14">&nbsp;</td>
	           				<td class="text14">&nbsp;Kundenr.(Mottaker)</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="recId" id="recId" size="10" maxlength="20" value="${model.recId}"></td>
							<td class="text14">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.tvinn.sad.search"/>'></td>
           				</tr>
		           		</form>
		           		</table>
					</td>
					</tr>
					
													           		
	           		<tr height="5"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="kundensVareRegList" class="display compact cell-border" width="100%" >
						<thead>
						<tr class="tableHeaderField" height="20" >
							<th class="text14" title="adunnr">&nbsp;Varenr&nbsp;</th>
		                    <th class="text14" title="adunnr">&nbsp;Varekod/Tariffnr&nbsp;</th>
		                    <th class="text14" title="adembg">&nbsp;Beskrivelse&nbsp;</th>
		                    <th class="text14" title="adunnr">&nbsp;Tollverdi&nbsp;</th>
		                    <th class="text14" title="adunnr">&nbsp;Bruttovekt&nbsp;</th>
		                    <th class="text14" title="adunnr">&nbsp;Nettovekt&nbsp;</th>
		                    <th class="text14" title="adunnr">&nbsp;DEBUG&nbsp;</th>
		                </tr> 
		                </thead>
		                <tbody>
		                <c:forEach var="record" items="${model.kundensVareRegList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text14">
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text14">
			                   </c:otherwise>
			               </c:choose>
			               <td nowrap style="cursor:pointer;" class="text14MediumBlue" id="varenr${record.varenr}@varebe${record.varebe}@w2vnti${record.w2vnti}@w2belt${record.w2belt}@w2vktb${record.w2vktb}@w2vktn${record.w2vktn}
			               			@w2top1${record.w2top1}@w2cre1${record.w2cre1}@w2top2${record.w2top2}@w2cre2${record.w2cre2}@w2top3${record.w2top3}@w2cre3${record.w2cre3}
			               			@w2top4${record.w2top4}@w2cre4${record.w2cre4}@w2top5${record.w2top5}@w2cre5${record.w2cre5}@w2top6${record.w2top6}@w2cre6${record.w2cre6}
			               			@w2top7${record.w2top7}@w2cre7${record.w2cre7}@w2top8${record.w2top8}@w2cre8${record.w2cre8}@w2top9${record.w2top9}@w2cre9${record.w2cre9}
			               			@w2top10${record.w2top10}@w2cre10${record.w2cre10}
			               			@w2akd1${record.w2akd1}@w2asv1${record.w2asv1}@w2asa1${record.w2asa1}@w2agr1${record.w2agr1}@w2abl1${record.w2abl1}
			               			@w2akd2${record.w2akd2}@w2asv2${record.w2asv2}@w2asa2${record.w2asa2}@w2agr2${record.w2agr2}@w2abl2${record.w2abl2}
			               			@w2akd3${record.w2akd3}@w2asv3${record.w2asv3}@w2asa3${record.w2asa3}@w2agr3${record.w2agr3}@w2abl3${record.w2abl3}
			               			@w2akd4${record.w2akd4}@w2asv4${record.w2asv4}@w2asa4${record.w2asa4}@w2agr4${record.w2agr4}@w2abl4${record.w2abl4}
			               			@w2akd5${record.w2akd5}@w2asv5${record.w2asv5}@w2asa5${record.w2asa5}@w2agr5${record.w2agr5}@w2abl5${record.w2abl5}
			               			@w2akd6${record.w2akd6}@w2asv6${record.w2asv6}@w2asa6${record.w2asa6}@w2agr6${record.w2agr6}@w2abl6${record.w2abl6}
			               			@w2akd7${record.w2akd7}@w2asv7${record.w2asv7}@w2asa7${record.w2asa7}@w2agr7${record.w2agr7}@w2abl7${record.w2abl7}
			               			@w2akd8${record.w2akd8}@w2asv8${record.w2asv8}@w2asa8${record.w2asa8}@w2agr8${record.w2agr8}@w2abl8${record.w2abl8}
			               			@w2ft01${record.w2ft01}@w2nt01${record.w2nt01}@w2eh01${record.w2eh01}@w2vt01${record.w2vt01}
			               			@w2ft02${record.w2ft02}@w2nt02${record.w2nt02}@w2eh02${record.w2eh02}@w2vt02${record.w2vt02}
			               			@w2ft03${record.w2ft03}@w2nt03${record.w2nt03}@w2eh03${record.w2eh03}@w2vt03${record.w2vt03}
			               			@w2ft04${record.w2ft04}@w2nt04${record.w2nt04}@w2eh04${record.w2eh04}@w2vt04${record.w2vt04}
			               			@w2ft05${record.w2ft05}@w2nt05${record.w2nt05}@w2eh05${record.w2eh05}@w2vt05${record.w2vt05}
			               			@w2ft06${record.w2ft06}@w2nt06${record.w2nt06}@w2eh06${record.w2eh06}@w2ft07${record.w2ft07}@w2nt07${record.w2nt07}@w2eh07${record.w2eh07}" >
			               			
               			   		<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;${record.varenr}
			               </td>
		               	   <td class="text14">&nbsp;${record.w2vnti}</td>
		               	   <td class="text14">&nbsp;${record.varebe}</td>
		               	   <td class="text14">&nbsp;${record.w2belt}</td>
		               	   <td class="text14">&nbsp;${record.w2vktb}</td>
		               	   <td class="text14">&nbsp;${record.w2vktn}</td>
		               	   <td class="text14">varenr${record.varenr}@varebe${record.varebe}@w2vnti${record.w2vnti}@w2belt${record.w2belt}@w2vktb${record.w2vktb}@w2vktn${record.w2vktn}
			               			@w2top1${record.w2top1}@w2cre1${record.w2cre1}@w2top2${record.w2top2}@w2cre2${record.w2cre2}@w2top3${record.w2top3}@w2cre3${record.w2cre3}
			               			@w2top4${record.w2top4}@w2cre4${record.w2cre4}@w2top5${record.w2top5}@w2cre5${record.w2cre5}@w2top6${record.w2top6}@w2cre6${record.w2cre6}
			               			@w2top7${record.w2top7}@w2cre7${record.w2cre7}@w2top8${record.w2top8}@w2cre8${record.w2cre8}@w2top9${record.w2top9}@w2cre9${record.w2cre9}
			               			@w2top10${record.w2top10}@w2cre10${record.w2cre10}
			               			@w2akd1${record.w2akd1}@w2asv1${record.w2asv1}@w2asa1${record.w2asa1}@w2agr1${record.w2agr1}@w2abl1${record.w2abl1}
			               			@w2akd2${record.w2akd2}@w2asv2${record.w2asv2}@w2asa2${record.w2asa2}@w2agr2${record.w2agr2}@w2abl2${record.w2abl2}
			               			@w2akd3${record.w2akd3}@w2asv3${record.w2asv3}@w2asa3${record.w2asa3}@w2agr3${record.w2agr3}@w2abl3${record.w2abl3}
			               			@w2akd4${record.w2akd4}@w2asv4${record.w2asv4}@w2asa4${record.w2asa4}@w2agr4${record.w2agr4}@w2abl4${record.w2abl4}
			               			@w2akd5${record.w2akd5}@w2asv5${record.w2asv5}@w2asa5${record.w2asa5}@w2agr5${record.w2agr5}@w2abl5${record.w2abl5}
			               			@w2akd6${record.w2akd6}@w2asv6${record.w2asv6}@w2asa6${record.w2asa6}@w2agr6${record.w2agr6}@w2abl6${record.w2abl6}
			               			@w2akd7${record.w2akd7}@w2asv7${record.w2asv7}@w2asa7${record.w2asa7}@w2agr7${record.w2agr7}@w2abl7${record.w2abl7}
			               			@w2akd8${record.w2akd8}@w2asv8${record.w2asv8}@w2asa8${record.w2asa8}@w2agr8${record.w2agr8}@w2abl8${record.w2abl8}
			               			@w2ft01${record.w2ft01}@w2nt01${record.w2nt01}@w2eh01${record.w2eh01}@w2vt01${record.w2vt01}
			               			@w2ft02${record.w2ft02}@w2nt02${record.w2nt02}@w2eh02${record.w2eh02}@w2vt02${record.w2vt02}
			               			@w2ft03${record.w2ft03}@w2nt03${record.w2nt03}@w2eh03${record.w2eh03}@w2vt03${record.w2vt03}
			               			@w2ft04${record.w2ft04}@w2nt04${record.w2nt04}@w2eh04${record.w2eh04}@w2vt04${record.w2vt04}
			               			@w2ft05${record.w2ft05}@w2nt05${record.w2nt05}@w2eh05${record.w2eh05}@w2vt05${record.w2vt05}
			               			@w2ft06${record.w2ft06}@w2nt06${record.w2nt06}@w2eh06${record.w2eh06}
			               			@w2ft07${record.w2ft07}@w2nt07${record.w2nt07}@w2eh07${record.w2eh07}
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
