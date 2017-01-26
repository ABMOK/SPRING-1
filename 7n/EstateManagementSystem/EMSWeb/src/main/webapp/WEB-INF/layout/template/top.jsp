<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_OWNER','ROLE_RENTER')">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-1">
                    </div>
                    <div class="col-md-10">
                        <div class="bs-glyphicons">
                            <ul class="bs-glyphicons-list">
                                <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                                    <a href="/estates">
                                        <li>
                                            <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                                            <span class="glyphicon-class"><fmt:message key="estates"/></span>

                                        </li>
                                    </a>
                                    <a href="/tenants">
                                        <li>
                                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>

                                            <span class="glyphicon-class"><fmt:message key="placeusers"/></span>

                                        </li>
                                    </a>
                                    <a href="/classifiedads/administration">
                                        <li>
                                            <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
                                            <span class="glyphicon-class"><fmt:message key="options"/>-<fmt:message
                                                    key="classyAds"/></span>

                                        </li>
                                    </a>


                                    <a href="/finances">
                                        <li>
                                            <span class="glyphicon glyphicon-usd" aria-hidden="true"></span>
                                            <span class="glyphicon-class"><fmt:message key="options"/>-<fmt:message
                                                    key="finances"/></span>
                                        </li>
                                    </a>
                                    </li>
                                    </a>
                                </security:authorize>
    <security:authorize access="hasAnyRole('ROLE_OWNER')">
        <a href="/finances/countersrestart">
            <li>
                <span class="glyphicon glyphicon-usd" aria-hidden="true"></span>
                <span class="glyphicon-class">Restart <fmt:message key="counters"/></span>
            </li>
        </a>
        </li>
        </a>
    </security:authorize>
                                <security:authorize access="hasAnyRole('ROLE_OWNER','ROLE_RENTER')">
                                    <a href="/finances/reporting">
                                        <li>
                                            <span class="glyphicon glyphicon-usd" aria-hidden="true"></span>
                                            <span class="glyphicon-class"><fmt:message key="reporting"/></span>
                                        </li>
                                    </a>
                                    </li>
                                    </a>
                                    <a href="/classifiedads">
                                        <li>
                                            <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
                                            <span class="glyphicon-class"><fmt:message key="classyAds"/></span>

                                        </li>
                                    </a>
                                </security:authorize>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-1">
                    </div>
                </div>
            </div>
        </div>
    </div>
</security:authorize>

