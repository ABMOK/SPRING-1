<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
    <h3>
    <div class="bs-glyphicons">
        <ul class="bs-glyphicons-list">
            <a href="/estates">
                <li>
                    <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                    <span class="glyphicon-class"><fmt:message key="estates"/></span>
                </li>
            </a>
            <a href="/estates/estate">
                <li>
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                    <span class="glyphicon-class"><fmt:message key="add"/></span>
                </li>
            </a>
            <a href="/estates/places/allplaces">
                <li>
                    <span class="glyphicon glyphicon-qrcode" aria-hidden="true"></span>
                    <span class="glyphicon-class"><fmt:message key="places"/></span>
                </li>
            </a>
        </ul>
    </div>
    </h3>
</security:authorize>
