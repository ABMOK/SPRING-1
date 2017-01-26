<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<body>

	<div class="container">
		<h2>
			<c:if test="${empty user.userId}">
				<fmt:message key="user.newuser" />
			</c:if>
			<c:if test="${not empty user.userId}">
				<fmt:message key="user.olduser" />
			</c:if>
		</h2>
		<form:form modelAttribute="user" method="POST" commandName="user">
			<form:hidden path="userId" name="userId" />

			<div class="form-group">
				<label for="gender"> <fmt:message key="user.gender" />
				</label> <input type="text" class="form-control" id="gender" name="gender"
					value="${user.gender}">
			</div>

			<div class="form-group">
				<label for="firstName"><fmt:message key="user.name" /></label> <input
					type="text" class="form-control" id="firstName" name="firstName"
					value="${user.firstName}">
			</div>

			<div class="form-group">
				<label for="lastName"><fmt:message key="user.lastname" /></label> <input
					type="text" class="form-control" id="lastName" name="lastName"
					value="${user.lastName}">
			</div>

			<div class="form-group">
				<label for="email"><fmt:message key="user.email" /></label> <input
					type="email" class="form-control" id="email" name="email"
					value="${user.email}">
			</div>

			<div class="form-group">
				<label for="password"><fmt:message key="user.password" /></label> <input
					type="password" class="form-control" id="password" name="password"
					value="${user.password}">
			</div>

			<div class="form-group">
				<label><fmt:message key="user.role" /></label>
				<div class=row>
					<form:select class="form-control" path="userRoles">
						<form:options items="${roles}" />
					</form:select>
				</div>
			</div>

			<button type="submit" class="btn btn-default pull-right">
				<fmt:message key="submit" />
			</button>
		</form:form>
	</div>

	<div class="container">
		<h2>
			<fmt:message key="users.users" />
		</h2>
		<table class="table table-striped">
			<tr>
				<th><fmt:message key="users.id" /></th>
				<th><fmt:message key="user.email" /></th>
				<th><fmt:message key="user.name" /></th>
				<th><fmt:message key="user.lastname" /></th>
				<th><fmt:message key="user.password" /></th>
				<th><fmt:message key="user.gender" /></th>
				<th><fmt:message key="user.role" /></th>
				<th><fmt:message key="users.action" /></th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.userId}</td>
					<td>${user.email}</td>
					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
					<td>${user.password}</td>
					<td>${user.gender}</td>
					<td><table>
							<c:forEach items="${user.userRoles}" var="role">
								<td>${role}, </td>
							</c:forEach>
						</table></td>
					<td><a
						href='<c:url value="/administration/users/update/${user.userId}" />'
						type="button" class="btn btn-info btn-xs"><fmt:message key="details" /></a> <a
						href='<c:url value="/administration/users/delete/${user.userId}" />'
						type="button" class="btn btn-danger btn-xs"><fmt:message key="delete" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
