<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTvinnSad.jsp" />
<!-- =====================end header ==========================-->
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadgate.js?ver=${user.versionEspedsg}"></SCRIPT>
 	
 	<%-- The important part of this JSP is the above view: headerTds.jsp  --%>
 	<table class="text32Bold" width="100%" border="0" cellspacing="0" cellpadding="0">
 	        <tr height="50"><td >&nbsp;</td></tr> 
            <tr><td align="center"></td></tr> 
    </table>          
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

