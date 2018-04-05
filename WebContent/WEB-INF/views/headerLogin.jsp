<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %> <!-- generally you will include this in a header.jsp -->

<html>
	<head>
		<c:choose>
			<%-- set up BEFORE login --%>
			<c:when test="${not empty model.user.cssEspedsg}">
				<link href="resources/${model.user.cssEspedsg}?ver=${user.versionEspedsg}" rel="stylesheet" type="text/css"/>
			</c:when>
			<c:otherwise>
				<link href="resources/espedsg.css?ver=${user.versionEspedsg}" rel="stylesheet" type="text/css"/>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<%-- set up BEFORE login --%>
			<c:when test="${ fn:contains(model.user.cssEspedsg, 'Toten') }"> 
				<link rel="SHORTCUT ICON" type="image/ico" href="resources/images/toten_ico.ico"></link>
			</c:when>
			<c:otherwise>
				<link rel="SHORTCUT ICON" type="image/png" href="resources/images/systema_logo.png"></link>
			</c:otherwise>
		</c:choose>
		<%-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> --%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>${model.user.espedsgLoginTitle}</title>
	</head>

	<body>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
	
