<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<div id="content">
	<h2>
	<security:authorize access="hasAnyRole('ROLE_ADMIN')">
    <div class="bs-glyphicons">
    <ul class="bs-glyphicons-list">
         <a href="/administration/newuser">
          <li> 
          <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>          
          <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
          <span class="glyphicon-class"><fmt:message key="add" /></span>         
        </li> </a>
        </ul>
        </div>
</security:authorize>		
	</h2>

 <table class="table table-striped table-bordered table-hover table-condensed">
            <tr>
                <th><fmt:message key="id"/></th>
                <th><fmt:message key="name"/></th>
                <th><fmt:message key="surname"/></th>
                <th><fmt:message key="mail"/></th>
                <th><fmt:message key="loginName"/></th>
                <th><fmt:message key="password"/></th>
                <th><fmt:message key="regdate"/></th>                
                <th><fmt:message key="options"/></th>
            </tr>
           <c:forEach items="${admin.allUsers}" var="user">
					<tr>
						<td>${user.iduser}</td>
						<td>${user.name}</td>
						<td>${user.surname}</td>
						<td>${user.mail}</td>
						<td>${user.login}</td>
						<td>${user.password}</td>
						<td>${user.regdate}</td>
						<td>
							<a href='<c:url value="/administration/users/${user.iduser}/delete"/>' type="button" class="btn btn-danger btn-xs"><fmt:message key="delete"/></a>
							<a
									href='<c:url value="/administration/users/${user.iduser}" />'
									type="button" class="btn btn-warning btn-xs"><fmt:message key="edit"/></a>
						</td>
					</tr>
				</c:forEach>
                    </table>
</div>