<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>
	<div class="container">
		<form class="form-signin" id="form" action='<c:url value='/index' />'
			method="POST">
			<h2 class="form-signin-heading">LOGIN</h2>
			<c:if test="${not empty param.err}">
				<div>
					<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
				</div>
			</c:if>
			<c:if test="${not empty param.out}">
				<div>You've logged out successfully.</div>
			</c:if>
			<c:if test="${not empty param.time}">
				<div>You've been logged out due to inactivity.</div>
			</c:if>

			<br> <label for="email">e-mail</label> <br> 
			<br> <input	type="text" name="email" value="" /><br> 
			<br> <label	for="password">password</label> 
			<br> <input type="password"	name="password" value="" /> 
			<input value="Login" name="submit"	type="submit" />
		</form>
	</div>
</body>

