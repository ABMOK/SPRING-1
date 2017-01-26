<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
    <div class="bs-glyphicons">
        <ul class="bs-glyphicons-list">
            <a href="/classifiedads">
                <li>
                    <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
                    <span class="glyphicon-class"><fmt:message key="classyAds"/></span>

                </li>
            </a>
            <a href="/classifiedads/newad">
                <li>
                    <span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span>
                    <span class="glyphicon-class"><fmt:message key="add"/></span>
                </li>
            </a>
            <a href="/classifiedads/administration">
                <li>
                    <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
                    <span class="glyphicon-class"><fmt:message key="options"/></span>
                </li>
            </a>
        </ul>
    </div>
</security:authorize>