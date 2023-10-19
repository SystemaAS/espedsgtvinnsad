<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %> <!-- generally you will include this in a header.jsp -->

<html>
	<head>
		<link href="/espedsg2/resources/${user.cssEspedsgMaintenance}?ver=${user.versionEspedsg}" rel="stylesheet" type="text/css"/>
		<link href="resources/jquery.calculator.css" rel="stylesheet" type="text/css"/>
		<link type="text/css" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/overcast/jquery-ui.css" rel="stylesheet">
		<%--<link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.0/themes/smoothness/jquery-ui.css" rel="stylesheet"> --%>
		
		<%-- datatables grid CSS --%>
		<link type="text/css" href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.css" rel="stylesheet">
		<link type="text/css" href="//cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" rel="stylesheet">
		<link type="text/css" href="//cdn.datatables.net/plug-ins/1.10.19/features/searchHighlight/dataTables.searchHighlight.css" rel="stylesheet">
		<%-- for dialog popup --%>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
		<style type = "text/css">
			.ui-dialog{font-size:10pt;}
		</style>
		
		<c:choose>
			<c:when test="${ fn:contains(user.cssEspedsg, 'Toten') }"> 
				<link rel="SHORTCUT ICON" type="image/ico" href="resources/images/toten_ico.ico"></link>
			</c:when>
			<c:otherwise>
				<link rel="SHORTCUT ICON" type="image/png" href="resources/images/systema_logo.png"></link>
			</c:otherwise>
		</c:choose>
		<%-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> --%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>eSpedsg - TVINN</title>
	</head>
	<body>
	<%-- include som javascript functions --%>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js""></script>
	<script type="text/javascript" src="resources/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="resources/js/systemaWebGlobal.js?ver=${user.versionEspedsg}"></script>
	<SCRIPT type="text/javascript" src="resources/js/headerTvinnSadMaintenance.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<input type="hidden" name="language" id=language value="${user.usrLang}">
	
	<%--datatables grid --%>
	<script type="text/javascript" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>

	
    <table class="noBg" width="100%" border="0" cellspacing="0" cellpadding="0">
		<%--Banner --%>
	 	<tr>
	 		 <%-- class="grayTitanBg" --%>
    		<td height="60" class="headerTdsBannerAreaBg" width="100%" align="left" colspan="3"> 
    			 <table width="100%" border="0" cellspacing="0" cellpadding="0">
    			 	<tr>
			        	<td>&nbsp;</td>
			        	<td>&nbsp;</td>
				 		<td>&nbsp;</td>
			        </tr>
				 	<tr>
				 		<td class="text12white" width="10%" align=left valign="bottom" >&nbsp;</td>
				 		<td class="text32Bold" width="100%" align="center" valign="middle" style="color:#778899;" >
				 			eSped<font style="color:#003300;">sg</font> - TVINN - VEDLIKEHOLD
				 			
				 		</td>
				 		 
			    		<td class="text12" width="10%" align="center" valign="middle" ><img src="resources/images/systema_logo.png" border="0" width=80px height=50px ></td>
			      		<%-- <td class="text12white" width="10%" align=right valign="bottom" >&nbsp;</td>--%>
			        </tr>
			        <tr>
			        	<td>&nbsp;</td>
			        	<td>&nbsp;</td>
				 		<td class="text14" width="10%" align=right valign="bottom" >&nbsp;</td>
			        </tr>
			        <tr class="text" height="1"><td></td></tr>
			     </table> 
			</td>
		</tr>
		<%-- Header menu --%>
		<tr >
			<td height="22" class="tabThinBorderLightGray" width="100%" align="left" colspan="3"> 
    			 <table width="100%" border="0" cellspacing="0" cellpadding="0">
				 	<tr >
			    		<td class="text14" width="70%" align="left" >&nbsp;&nbsp;
			    			<%-- --------------------- --%>
			    			<%-- TVINN-SAD EXPORT MENU --%>
			    			<%-- --------------------- --%>
			    			<a id="alinkTopicListMenuExp" tabindex=-1 href="tvinnsadmaintenanceexport.do?">
			    				&nbsp;<font 
			    				<c:choose>           
		                   			<c:when test="${user.activeMenu=='TVINN_SAD_MAINTENANCE_EXPORT'}">
		                       			class="headerMenuOrange"
		                   			</c:when>
		                   			<c:otherwise>   
		                       			class="headerMenuWhite"
		                   			</c:otherwise>
		               			</c:choose>
			    				
			    				>&nbsp;<spring:message code="systema.tvinn.sad.export.label"/>&nbsp;</font>
			    			</a>
			    			
			    			&nbsp;<font color="#FF6600"; style="font-weight: bold;">|</font>
			    			
			    			<%-- --------------------- --%>
			    			<%-- TVINN-SAD IMPORT MENU --%>
			    			<%-- --------------------- --%>
			    			<a id="alinkTopicListMenuImp" tabindex=-1 href="tvinnsadmaintenanceimport.do?">
			    				&nbsp;<font 
			    				<c:choose>           
	                   			<c:when test="${user.activeMenu=='TVINN_SAD_MAINTENANCE_IMPORT'}">
	                       			class="headerMenuOrange"
	                   			</c:when>
	                   			<c:otherwise>   
	                       			class="headerMenuWhite"
	                   			</c:otherwise>
	               			</c:choose>
			    				
		    				>&nbsp;<spring:message code="systema.tvinn.sad.import.label"/>&nbsp;</font>
			    			</a>
			    			&nbsp;<font color="#FF6600"; style="font-weight: bold;">|</font>
			    			
			    			<%--
			    			
			    			<%-- -------------------------- --%>
			    			<%-- TVINN-SAD NCTS EXPORT MENU --%>
			    			<%-- -------------------------- 
			    			<a id="alinkTopicListMenuNctsExp" tabindex=-1 href="tvinnsadmaintenance_nctsexport.do?">
			    				&nbsp;<font
			    				<c:choose>           
	                   			<c:when test="${user.activeMenu=='TVINN_SAD_MAINTENANCE_NCTS_EXPORT'}">
	                       			class="headerMenuOrange"
	                   			</c:when>
	                   			<c:otherwise>   
	                       			class="headerMenuWhite"
	                   			</c:otherwise>
	               			</c:choose>
			    				>&nbsp;<spring:message code="systema.tvinn.sad.ncts.export.label"/>&nbsp;</font>
			    			</a>
			    			&nbsp;<font color="#FF6600"; style="font-weight: bold;">|</font>
			    			
			    			<%-- --------------------- --%>
			    			<%-- TVINN-SAD NCTS IMPORT MENU --%>
			    			<%-- --------------------- 
			    			<a id="alinkTopicListMenuNctsImp" tabindex=-1 href="tvinnsadmaintenance_nctsimport.do?">
			    				&nbsp;<font
			    				<c:choose>           
		                   			<c:when test="${user.activeMenu=='TVINN_SAD_MAINTENANCE_NCTS_IMPORT'}">
		                       			class="headerMenuOrange"
		                   			</c:when>
		                   			<c:otherwise>   
		                       			class="headerMenuWhite"
		                   			</c:otherwise>
		               			</c:choose>
			    				>&nbsp;<spring:message code="systema.tvinn.sad.ncts.import.label"/>&nbsp;</font>
			    			</a>
			    			&nbsp;<font color="#FF6600"; style="font-weight: bold;">|</font>
			    			 --%>
			    			
			    			<%-- --------------------------------- --%>
			    			<%-- TVINN-SAD EKSPRESSFORTOLLING MENU --%>
			    			<%-- --------------------------------- --%>
			    			<a id="alinkTopicListMenuExpressfortolling" tabindex=-1 href="tvinnsadmaintenance_manifest.do?">
			    				&nbsp;<font
			    				<c:choose>           
		                   			<c:when test="${user.activeMenu=='TVINN_SAD_MAINTENANCE_EXPRESSFORTOLLING'}">
		                       			class="headerMenuOrange"
		                   			</c:when>
		                   			<c:otherwise>   
		                       			class="headerMenuWhite"
		                   			</c:otherwise>
		               			</c:choose>
			    				>&nbsp;<spring:message code="systema.tvinn.sad.manifest.label"/>&nbsp;</font>
			    			</a>
			    			&nbsp;<font color="#FF6600"; style="font-weight: bold;">|</font>
			    			<%-- --------------------------------- --%>
			    			<%-- TVINN-SAD DIGITOLLV2 MENU --%>
			    			<%-- --------------------------------- --%>
			    			<a id="alinkTopicListMenuDigitollV2" tabindex=-1 href="tvinnsadmaintenance_digitollv2.do?">
			    				&nbsp;<font
			    				<c:choose>           
		                   			<c:when test="${user.activeMenu=='TVINN_SAD_MAINTENANCE_DIGITOLLV2'}">
		                       			class="headerMenuOrange"
		                   			</c:when>
		                   			<c:otherwise>   
		                       			class="headerMenuWhite"
		                   			</c:otherwise>
		               			</c:choose>
			    				>&nbsp;Digitoll V2&nbsp;</font>
			    			</a>
			    			&nbsp;<font color="#FF6600"; style="font-weight: bold;">|</font>
			    			
			    			<%-- ----------------------- --%>
			    			<%-- VEDLIKEHOLD FELLES      --%>
			    			<%-- ----------------------- --%>
			    			<a id="alinkTopicListMenuMaintFelles" tabindex=-1 href="tvinnsadmaintenancefelles.do?">
			    				&nbsp;<font
			    				<c:choose>           
		                   			<c:when test="${user.activeMenu=='TVINN_SAD_MAINTENANCE_FELLES'}">
		                       			class="headerMenuOrange"
		                   			</c:when>
		                   			<c:otherwise>   
		                       			class="headerMenuWhite"
		                   			</c:otherwise>
		               			</c:choose>
			    				>&nbsp;<spring:message code="systema.tvinn.sad.maintenance.felles.label"/>&nbsp;</font>
			    			</a>
			    			&nbsp;<font color="#FF6600"; style="font-weight: bold;">|</font>
			    			
		    			 	<%-- ------------------- --%>
			    			<%-- Maintenance  MENU    --%>
			    			<%-- -------------------- --%>
			    			<a tabindex=-1 href="tvinnsadgate.do">
			    				&nbsp;<font class="headerMenuMaintenance">
		                   		&nbsp;&nbsp;<spring:message code="systema.tvinn.sad.main.gate.returnTo.label"/>&nbsp;&nbsp;</font>
			    			</a>
	      				</td>		      				
	      				<td class="text14" width="50%" align="right" valign="middle">
	      					
						    <img valign="bottom" src="resources/images/countryFlags/Flag_NO.gif" height="12" border="0" alt="country">
		      				&nbsp;
		      				<font class="headerMenuWhite">
			    				<img src="resources/images/appUser.gif" border="0" onClick="showPop('specialInformationAdmin');" > 
						        <font style="color:#000000" >${user.user}&nbsp;</font><font style="color:#FF6600" >${user.usrLang}</font>
			    			</font>
			    				
		    				<font color="#FF6600"; style="font-weight: bold;">&nbsp;&nbsp;|&nbsp;&nbsp;</font>
			    			<div class="text11" style="position: relative;display: inline;" align="left">
								<span style="position:absolute; left:-150px; top:3px; width:100;" id="specialInformationAdmin" class="popupWithInputText"  >
						           		<div class="text11" align="left">
						           			${activeUrlRPG_TvinnSad}
						           			<br/><br/>
						           			<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('specialInformationAdmin');">Close</button> 
						           		</div>
						        </span>   	
			    			
			    			
			    			<a tabindex=-1 href="logout.do">
			    				<font class="headerMenuWhite"><img src="resources/images/home.gif" border="0">&nbsp;
			    					<font style="color:#000000;" ><spring:message code="dashboard.menu.button"/>&nbsp;</font>
			    				</font>
			    			</a>
			    			<font color="#FF6600"; style="font-weight: bold;">&nbsp;&nbsp;|&nbsp;</font>
			    			<font class="text12" style="cursor:pointer;" onClick="showPop('versionInfo');">${user.versionSpring}&nbsp;</font>
		    				    <div class="text12" style="position: relative;display: inline;" align="left">
									<span style="position:absolute; left:-150px; top:3px;" id="versionInfo" class="popupWithInputText"  >
						           		<div class="text12" align="left">
						           			<b>${user.versionEspedsg}</b>
						           			
					           			<p>
					           			&nbsp;<a id="alinkLog4jLogger" ><font class="text14Orange style="cursor:pointer;">logsg</font></a>
					           			</p>
					           			<button name="versionInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('versionInfo');">Close</button> 
					           			</div>
					        		</span>
					        	</div> 
			    		</td>
			        </tr>
			     </table> 
			</td>
	    </tr>
	    <tr>
		    <td height="4" class="tabThinBorderOrange" width="100%" align="left" colspan="3"> 
	   			 <table width="1200" border="0" cellspacing="0" cellpadding="0">
	   			 </table>
			</td>
	    </tr>
	   
	    <tr class="text" height="2"><td></td></tr>
		
		<%-- ------------------------- --%>
		<%-- DIALOG render log4j.log   --%>
		<%-- ------------------------- --%>
		<tr>
		<td>
			<div id="dialogLogger" title="Dialog" style="display:none">
				<form>
			 	<table>
			 		<tr>
						<td colspan="3" class="text14" align="left" >Password</td>
  						</tr>
					<tr >
						<td>
							<input type="password" class="inputText" id="pwd" name="pwd" size="15" maxlength="15" value=''>
						</td>
					</tr>
  						<tr height="10"><td></td></tr>
					<tr>
						<td colspan="3" class="text14MediumBlue" align="left">
							<label id="loggerStatus"></label>
						</td>
					</tr>
				</table>
				</form>
			</div>
		</td>
		</tr>
		
		
		<%-- ------------------------------------
		Content after banner och header menu
		------------------------------------- --%>
		<tr>
    		<td width="100%" align="left" colspan="3"> 
    		     
     