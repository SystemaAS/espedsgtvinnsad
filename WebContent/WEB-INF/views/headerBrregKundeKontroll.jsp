<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
	<head>
		<link href="resources/${user.cssEspedsg}?ver=${user.versionEspedsg}" rel="stylesheet" type="text/css"/>
		<link href="resources/jquery.calculator.css" rel="stylesheet" type="text/css"/>
		<link type="text/css" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/overcast/jquery-ui.css" rel="stylesheet">
		<%-- datatables grid CSS --%>
		<link type="text/css" href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.css" rel="stylesheet">
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
		<title>Kundedata kontroll</title>
	</head>
	<body>
	<%-- include som javascript functions --%>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js""></script>
	<script type="text/javascript" src="resources/js/systemaWebGlobal.js?ver=${user.versionEspedsg}"></script>
	
	<%--datatables grid JS --%>
	<script type="text/javascript" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.blockUI.js"></script>
	<input type="hidden" name="language" id=language value="${user.usrLang}">
	
    <table class="noBg" width="1100" border="0" cellspacing="0" cellpadding="0">
		<%--Banner --%>
	 	<tr>
	 		 <%-- class="grayTitanBg" --%>
    		<td height="60" class="headerTdsBannerAreaBg" width="100%" align="left" colspan="3"> 
    			 <table width="1100" border="0" cellspacing="0" cellpadding="0">
    			 	<tr>
			        	<td>&nbsp;</td>
			        	<td>&nbsp;</td>
				 		<td>&nbsp;</td>
			        </tr>
				 	<tr>
				 		<td class="text12white" width="10%" align=left valign="bottom" >&nbsp;</td>
				 		<td class="text22Bold" width="80%" align="middle" valign="middle" style="color:#778899;" >
				 			eSped<font style="color:#003300;">sg</font> - Kundedata kontroll
				 			
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
		
		<tr >
			<td height="23" class="tabThinBorderLightSlateGray" width="100%" align="left" colspan="3"> 
    			 <table width="1100" border="0" cellspacing="0" cellpadding="0">
				 	<tr >
			    		<td class="text11" width="70%" align="left" >&nbsp;&nbsp;
			    			<a tabindex=-1 href="tvinnsad_brreg_kundekontroll.do?action=doFind" onClick="setBlockUI(this);">
			    				&nbsp;<font 
			    				<c:choose>           
		                   			<c:when test="${user.activeMenu=='BRREG_KUNDE_KONTROLL'}">
		                       			class="headerMenuMediumGreen"
		                   			</c:when>
		                   			<c:otherwise>   
		                       			class="headerMenuLightGreen"
		                   			</c:otherwise>
		               			</c:choose>
			    				
			    				>&nbsp;Kundedata kontroll&nbsp;</font>
			    			</a>
			    			
	      				</td>		      				
	      				<td class="text11" width="50%" align="right" valign="middle">
	      					<img valign="bottom" src="resources/images/countryFlags/ 
	      					<c:choose>           
	                   			<c:when test="${user.usrLang =='NO' || empty user.usrLang }">
		                   			Flag_NO.gif
      							</c:when>
	      						<c:otherwise>
	      							<c:choose>           
	                   				<c:when test="${user.usrLang =='EN'}">
	      								Flag_UK.gif		
	      							</c:when>
	      							<c:otherwise>
	      								<%-- put new implementations of flags here --%>
	      								Flag_UNKNOWN.png
	      							</c:otherwise>
	      							</c:choose>
	      						</c:otherwise>
	      					</c:choose>
	      					" height="12" border="0" alt="country">&nbsp;
		      				
		      				<font class="headerMenuGreen">
			    				<img src="resources/images/appUser.gif" border="0" onClick="showPop('specialInformationAdmin');" > 
						        <span style="position:absolute; left:100px; top:150px; width:1000px; height:400px;" id="specialInformationAdmin" class="popupWithInputText"  >
						           		<div class="text11" align="left">
						           			${activeUrlRPG_SporringOppdrag}
						           			<br/><br/>
						           			<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('specialInformationAdmin');">Close</button> 
						           		</div>
						        </span>   		
			    				<font style="color:#000000" >${user.user}&nbsp;</font>${user.usrLang}</font>
			    				<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;|&nbsp;&nbsp;</font>
				    			<a tabindex=-1 href="logoutBrregKontroll.do">
				    				<font class="headerMenuGreen"><img src="resources/images/home.gif" border="0">&nbsp;
				    					<font style="color:#000000;" ><spring:message code="dashboard.menu.button"/>&nbsp;</font>
				    				</font>
				    			</a>
				    			<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;&nbsp;|&nbsp;</font>
				    			<font class="text12LightGreen" style="cursor:pointer;" onClick="showPop('versionInfo');">${user.versionSpring}&nbsp;</font>
			    				    <span style="position:absolute; left:800px; top:105px; width:150px; height:100px;" id="versionInfo" class="popupWithInputText"  >
						           		<div class="text11" align="left">
						           			&nbsp;<b>${user.versionEspedsg}</b>
						           			<br/><br/>
						           			&nbsp;<a href="renderLocalLog4j.do" target="_blank">log4j</a>
						           			<br/><br/><br/>
						           			<button name="versionInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('versionInfo');">Close</button> 
						           		</div>
						        </span> 
						        
				    		</td>
	      				
			        </tr>
			     </table> 
			</td>
	    </tr>
	    	<tr class="text" height="20"><td></td></tr>
		    
	    <%-- Validation Error section --%>
	    <c:if test="${errorMessage!=null}">
		<tr>
			<td colspan=3>
			<table>
					<tr>
					<td class="textError">					
			            <ul>
			                <li >
			                	${errorMessage}
			                </li>
			            
			            </ul>
					</td>
					</tr>
			</table>
			</td>
		</tr>
		</c:if>

	    <tr class="text" height="2"><td></td></tr>
		
		<%-- ------------------------------------
		Content after banner och header menu
		------------------------------------- --%>
		<tr>
    		<td width="100%" align="left" colspan="3"> 
    		     
     