<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ================== special login header ==================-->
<jsp:include page="/WEB-INF/views/headerLogin.jsp" />
<!-- =====================end header ==========================-->
<SCRIPT type="text/javascript" src="resources/js/login.js"></SCRIPT>	
 
	<div>
	 	<h3 class="text18">&nbsp;</h3>
	 	<%-- <h4></h1><a href="?lang=en">en</a>|<a href="?lang=no">no</a></h4> --%>
	</div>	
	<table cellspacing="10" border="0" cellpadding="0">
		<tr>
		<td class="loginFrame" width="100%">
			<form action="logonDashboard.do" name="loginForm" id="loginForm" method="POST" >
	 		<table border="0" cellpadding="0" cellspacing="2" >
				<tr height="1"><td>&nbsp;</td></tr>
				<tr>
					<td class="text14"><spring:message code="login.title"/>&nbsp;</td>
					<td >&nbsp;</td>
				</tr>
				<tr height="1"><td>&nbsp;</td></tr>
				<tr>
					<td class="text12"><spring:message code="login.user.label.name"/>&nbsp;</td>
					<td ><input type="text" class="inputText" name="user" id="user" size="18" /></td>
				</tr>
				<tr>
					<td class="text12"><spring:message code="login.user.label.password"/>&nbsp;</td>
					<td><input type="password"  class="inputText" name="password" id="password" size="18"/></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="right"><input class="inputFormLoginSubmitGreen" type="submit" value="<spring:message code="login.user.submit"/>" /></td>
				</tr>
				<tr height="1"><td>&nbsp;</td></tr>
			</table>
			</form>
		</td>
		</tr>
		<%-- Validation Error section --%>
		<tr>
			<td colspan="5" >
			<table>
				<spring:hasBindErrors name="user"> <%-- name must equal the command object name in the Controller --%>
					<tr>
					<td colspan="5" class="textError">					
			            <ul>
			            <c:forEach var="error" items="${errors.allErrors}">
			                <li >
			                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>
			                </li>
			            </c:forEach>
			            </ul>
					</td>
					</tr>
				</spring:hasBindErrors>
				<c:if test="${not empty model.errorMessage}">
					<tr>
					<td colspan="5" class="textError">					
			            <ul>
			            	<li>${model.errorMessage}</li>
			            </ul>
			        </td>
			        </tr>
				</c:if>
			</table>
			</td>
		</tr>
	</table>
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->
	