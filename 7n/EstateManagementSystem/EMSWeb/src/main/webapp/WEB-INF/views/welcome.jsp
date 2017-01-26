<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${classy!=null}">
    <div id="content">
        <c:if test="${classy.classyAdList!=null && !classy.classyAdList.isEmpty() }">
            <div class="row">
                <div class="col-md-2">
                </div>
                <div class="col-md-8">
                    <security:authorize access="hasAnyRole('ROLE_OWNER','ROLE_RENTER')">
                        <div class="bs-glyphicons">
                            <ul class="bs-glyphicons-list">
                                <a href="/classifiedads/newad">
                                    <li>
                                        <span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span>
                                        <span class="glyphicon-class"><fmt:message key="add"/></span>
                                    </li>
                                </a>
                            </ul>
                        </div>
                    </security:authorize>
                </div>
                <div class="col-md-2">
                </div>
            </div>
            <div class="row">
                <div class="col-md-1">
                </div>
                <div class="col-md-10">

                    <c:forEach items="${classy.classyAdList}" var="ad" varStatus="loopMap">
                        <div class="panel panel-default">
                            <div class="panel-heading text-center">
                                <div class="row">
                                        ${ad.title}
                                </div>

                            </div>
                            <div class="panel-body">
                                    ${ad.shortDescription}
                            </div>
                            <div class="panel-footer text-center">
                                <div class="row">
                                    <a
                                            href='<c:url value="/classifiedads/categories/${ad.category.idcategory}/ads/${ad.idclassifiedAd}" />'
                                            type="button"
                                            class="btn btn-success btn-xs glyphicon glyphicon-remove-circle">
                                        <fmt:message key="read"/>
                                    </a>
                                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                                        <a
                                                href='<c:url value="/classifiedads/categories/${ad.category.idcategory}/ads/${ad.idclassifiedAd}/delete" />'
                                                type="button"
                                                class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle">
                                            <fmt:message key="delete"/>
                                        </a>
                                    </security:authorize>
                                </div>
                            </div>

                        </div>
                    </c:forEach>
                </div>
                <div class="col-md-1">
                </div>
            </div>
        </c:if>
    </div>

    <c:if test="${classy.classyAd!=null}">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <div class="row">
                    <h3>${classy.classyAd.title}</h3>
                </div>
            </div>
            <div class="panel-body">
                <h4>${classy.classyAd.shortDescription}</h4>
                <div class="body">
                    <p>${classy.classyAd.longDescription}</p>
                </div>
            </div>
        </div>
    </c:if>
</c:if>
