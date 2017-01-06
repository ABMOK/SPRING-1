<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<body>
	<div class="container">
		<form class="form-signin" id="form" action='<c:url value='/index' />'
			method="POST">
			<h2 class="form-signin-heading">
				<fmt:message key="login.login" />
			</h2>
			<c:if test="${not empty param.err}">
				<div>
					<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
				</div>
			</c:if>
			<br> <label for="email"> <fmt:message key="login.email" />
			</label> <br> <br> <input type="text" name="email" value="" /><br>
			<br> <label for="password"> <fmt:message
					key="login.password" />
			</label> <br> <input type="password" name="password" value="" /> <input
				value="Login" name="submit" type="submit" />
		</form>
	</div>
</body>

