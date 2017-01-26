<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4>
    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
        <div class="bs-glyphicons">
            <ul class="bs-glyphicons-list">
                <a href="/tenants/allowners">
                    <li>
                        <span class="glyphicon glyphicon-import" aria-hidden="true"></span>
                        <span class="glyphicon-class"><fmt:message key="owners"/></span>
                    </li>
                </a>
                <a href="/tenants/allrenters">
                    <li>
                        <span class="glyphicon glyphicon-export" aria-hidden="true"></span>
                        <span class="glyphicon-class"><fmt:message key="renters"/></span>
                    </li>
                </a>
                <a href="/administration">
                    <li>
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                        <span class="glyphicon-class"><fmt:message key="systemusers"/></span>
                    </li>
                </a>
            </ul>
        </div>
    </security:authorize>
</h4>