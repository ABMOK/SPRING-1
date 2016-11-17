<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<head>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
</head>

<body>
	<br>
	<form:form modelAttribute="user" method="POST" commandName="user">
		<form:hidden path="userId" name="userId" />
		<label for="gender">Title</label>
		<br>
		<br>
		<input type="text" class="form-control" id="gender" name="gender"
			placeholder="Mrs / Mr">
		<br>
		<br>
		<label for="firstName">Name</label>
		<br>
		<br>
		<input type="text" class="form-control" id="firstName"
			name="firstName" placeholder="Name">
		<br>
		<br>
		<label for="lastName">Last Name</label>
		<br>
		<br>
		<input type="text" class="form-control" id="lastName" name="lastName"
			placeholder="Last Name">
		<br>
		<br>
		<label for="email">Email address</label>
		<br>
		<br>
		<input type="email" class="form-control" id="email" name="email"
			placeholder="Email">
		<br>
		<br>
		<label for="password">Password</label>
		<br>
		<br>
		<input type="password" class="form-control" id="password"
			name="password" placeholder="Password">
		<br>
		<br>
		<label>ROLE:</label>
		<br>
		<br>
		<div class=row>
			<form:select class="form-control" path="userRoles">
				<form:options items="${roles}" />
			</form:select>
		</div>
		<br>
		<br>
		<button type="submit" class="btn btn-default pull-right">Submit</button>
	</form:form>
	<br>
	<br>
	<p>users</p>
	<table>
		<tr>
			<td>Title</td>
			<td>First Name</td>
			<th>Last Name</th>
			<th>Login</th>
			<th>Mail</th>
			<th>Details</th>
			<th>Action</th>
			<th>Action</th>
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
					<td>${role}</td>
				</c:forEach>
				</table></td>
				<!-- <td><a href='<c:url value="/administration/details/${user.userId}"/>' >${user.userId}</a></td> -->
				<td><a
					href='<c:url value="/administration/users/update/${user.userId}" />'
					type="button" class="btn btn-info btn-xs">DETAILS</a></td>
				<td><a
					href='<c:url value="/administration/users/delete/${user.userId}" />'
					type="button" class="btn btn-danger btn-xs">DELETE</a> <a
					href='<c:url value="/administration/role/assign/${user.userId}" />'
					type="button" class="btn btn-success btn-xs">MANAGE</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
