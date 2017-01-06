<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<security:authorize
					access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_OWNER', 'ROLE_RENTER')">
					<div class="col-md-4">
						<div class="panel panel-default">
							<div class="panel-heading">

								<fmt:message key="login.welcome" />
								<c:out value="${pageContext.request.userPrincipal.name}" />

							</div>
						</div>

					</div>
				</security:authorize>
				<div class="col-md-4">
					<security:authorize
						access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_OWNER', 'ROLE_RENTER')">
						<div class="panel panel-default">
							<div class="panel-heading">
								<security:authentication property="principal.authorities" />
							</div>
						</div>
					</security:authorize>
				</div>
				<div class="col-md-4">

					<security:authorize
						access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_OWNER', 'ROLE_RENTER')">
						<div class="panel panel-default">
							<div class="panel-heading">
								<ul class="bs-glyphicons-list">
									<li><span class="glyphicon glyphicon-ok-circle"
										aria-hidden="true"></span> <a
										href='<c:url value="/administration/users" />'> <span
											class="glyphicon-class"><fmt:message
													key="administration" /></span>
									</a></li>

									<li><span class="glyphicon glyphicon-ok-circle"
										aria-hidden="true"></span> <a href='<c:url value="/index" />'>
											<span class="glyphicon-class"><fmt:message key="start" /></span>
									</a></li>

									<li><span class="glyphicon glyphicon-off"
										aria-hidden="true"></span> <a href='<c:url value="/logout" />'>
											<span class="glyphicon-class"><fmt:message
													key="login.logout" /></span>
									</a></li>
								</ul>
							</div>
						</div>
					</security:authorize>
				</div>
			</div>
		</div>
	</div>
</div>
