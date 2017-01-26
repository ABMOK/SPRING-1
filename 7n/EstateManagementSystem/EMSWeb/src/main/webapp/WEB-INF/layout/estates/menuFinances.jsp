<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
    <div class="bs-glyphicons">
        <ul class="bs-glyphicons-list">
            <a href="/finances/estates">
                <li>
                    <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                    <span class="glyphicon-class"><fmt:message key="finances"/> - <fmt:message key="estates"/></span>

                </li>
            </a>
            <a href="/finances/allplaces">
                <li>
                    <span class="glyphicon glyphicon-qrcode" aria-hidden="true"></span>

                    <span class="glyphicon-class"><fmt:message key="finances"/> - <fmt:message key="places"/></span>

                </li>
            </a>
            <a href="/finances/reporting">
                <li>
                    <span class="glyphicon glyphicon-usd" aria-hidden="true"></span>
                    <span class="glyphicon-class"><fmt:message key="reporting"/></span>
                </li>
            </a>
            <a href="/finances/expenses">
                <li>
                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    <span class="glyphicon-class"><fmt:message key="expenses"/></span>
                </li>
            </a>
            <a href="/finances/media">
                <li>
                    <span class="glyphicon glyphicon-tint" aria-hidden="true"></span>
                    <span class="glyphicon-class"><fmt:message key="media"/></span>
                </li>
            </a>
            <a href="/finances/counters">
                <li>
                    <span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span>
                    <span class="glyphicon-class"><fmt:message key="counters"/></span>
                </li>
            </a>
        </ul>
    </div>
</security:authorize>