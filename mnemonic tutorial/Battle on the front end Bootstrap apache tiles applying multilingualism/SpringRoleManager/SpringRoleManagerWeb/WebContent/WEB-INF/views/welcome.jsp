<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="row">
						<div class="bs-glyphicons">
							<ul class="bs-glyphicons-list">
								<a href="administration/users">
									<li><span class="glyphicon glyphicon-pushpin"
										aria-hidden="true"></span> <span class="glyphicon-class"><fmt:message
												key="administration" /></span></li>
								</a>
								<a href="logout">
									<li><span class="glyphicon glyphicon-off"
										aria-hidden="true"></span> <span class="glyphicon-class"><fmt:message
												key="login.logout" /></span></li>
								</a>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</security:authorize>
<div>
${msg}
</div>