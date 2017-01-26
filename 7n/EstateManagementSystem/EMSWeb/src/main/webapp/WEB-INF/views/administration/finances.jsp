<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div id="content">

    <!-- media -->
    <c:if test="${mediaMap!=null && !mediaMap.isEmpty() }">
        <div class="row">
            <c:forEach items="${mediaMap}" var="facilityMedias" varStatus="loopMap">
            <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading text-center">
                        <h4>
                            <fmt:message
                                    key="${facilityMedias.key.type}"/>, ${facilityMedias.key.addressDto.cityName}, ${facilityMedias.key.addressDto.streetName} ${facilityMedias.key.addressDto.buildingNo}
                        </h4>
                    </div>
                    <div class="panel-body">

                        <c:forEach items="${facilityMedias.value}" var="medium" varStatus="loop">
                            <table class="table table-striped table-bordered table-hover table-condensed">
                                <tr>
                                    <td>
                                        <fmt:message key="${medium.type}"/>, <fmt:message key="regdate"/>
                                        : ${medium.dateregistered}
                                    </td>
                                </tr>
                            </table>
                        </c:forEach>
                        <a href='<c:url value="/finances/estates/${facilityMedias.key.idfacility}/media" />'
                           type="button"
                           class="btn btn-info btn-xs"><fmt:message key="details"/></a>
                    </div>
                    </div>
                </div>
                        ${!(loopMap.count%3==0) ? ' ' : '</div> <div class="row">'}
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>

    <!-- expenses -->
    <c:if test="${allExpenses!=null && !allExpenses.isEmpty()}">
        <div class="row">
            <c:forEach items="${allExpenses}" var="facilityExpenses" varStatus="loopMap">
            <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading text-center">
                        <h4>
                            <fmt:message
                                    key="${facilityExpenses.key.type}"/>, ${facilityExpenses.key.addressDto.cityName}, ${facilityExpenses.key.addressDto.streetName} ${facilityExpenses.key.addressDto.buildingNo}
                        </h4>
                    </div>
                    <div class="panel-body">
                        <c:forEach items="${facilityExpenses.value}" var="expense" varStatus="loop">
                            <table class="table table-striped table-bordered table-hover table-condensed">
                                <tr>
                                    <td>
                                        <fmt:message key="${expense.name}"/>,
                                        <fmt:message key="${expense.type}"/>,
                                        <fmt:message key="regdate"/>
                                        : ${expense.registered}
                                    </td>
                                </tr>
                            </table>
                        </c:forEach>
                        <a href='<c:url value="/finances/estates/${facilityExpenses.key.idfacility}/expenses" />'
                           type="button" class="btn btn-info btn-xs"><fmt:message key="details"/></a>
                    </div>
                    </div>
                </div>
                        ${!(loopMap.count%3==0) ? ' ' : '</div> <div class="row">'}
                    </c:forEach>
        </div>
                </div>
            </div>
        </div>
    </c:if>

    <!-- common counters -->
    <c:if test="${commonCountersMap!=null && !commonCountersMap.isEmpty() }">
        <div class="row">
            <c:forEach items="${commonCountersMap}" var="facilityCounters" varStatus="loopMap">
            <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading text-center">
                        <h4>
                            <fmt:message
                                    key="${facilityCounters.key.type}"/>, ${facilityCounters.key.addressDto.cityName}, ${facilityCounters.key.addressDto.streetName} ${facilityCounters.key.addressDto.buildingNo}
                        </h4>
                    </div>
                    <div class="panel-body">
                        <c:forEach items="${facilityCounters.value}" var="counter" varStatus="loop">
                            <table class="table table-striped table-bordered table-hover table-condensed">
                                <tr>
                                    <td>
                                        <fmt:message key="${counter.mediaType}"/>, ${counter.dateRegistered}
                                    </td>
                                </tr>
                            </table>
                        </c:forEach>
                        <a href='<c:url value="/finances/estates/${facilityCounters.key.idfacility}/counters" />'
                           type="button" class="btn btn-info btn-xs"><fmt:message key="details"/></a>
                    </div>
                    </div>
                </div>

                        ${!(loopMap.count%3==0) ? ' ' : '</div> <div class="row">'}
                    </c:forEach>
        </div>
                </div>
            </div>
        </div>
    </c:if>

    <!-- counters -->
    <c:if test="${allCountersMaps!=null && !allCountersMaps.isEmpty()}">
        <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
            <div class="bs-glyphicons">
                <ul class="bs-glyphicons-list">
                    <a href="/finances/counters">
                        <li>
                            <span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span>
                            <span class="glyphicon-class"><fmt:message key="individual"/></span>
                        </li>
                    </a>
                    <a href="/finances/counters/common">
                        <li>
                            <span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span>
                            <span class="glyphicon-class"><fmt:message key="common"/></span>
                        </li>
                    </a>
                </ul>
            </div>
        </security:authorize>
        <c:forEach items="${allCountersMaps}" var="allCounters" varStatus="loopMap">
              <div class="row">
                <c:forEach items="${allCounters}" var="placeCounters" varStatus="loopKeys">
                    <div class="col-md-4">
                        <div class="panel panel-default">
                            <div class="panel-heading text-center">
                                <h4>
                                        ${placeCounters.key.address.streetName}, ${placeCounters.key.address.buildingNo}
                                    / ${placeCounters.key.address.officeNo} <br>
                                </h4>
                            </div>
                            <div class="panel-body">

                                <table class="table table-striped table-bordered table-hover table-condensed">
                                    <c:forEach items="${placeCounters.value}" var="counter" varStatus="loop">
                                        <tr>
                                            <td>
                                                <fmt:message key="${counter.mediaType}"/>, ${counter.dateRegistered}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td>
                                            <a href='<c:url value="/finances/estates/${placeCounters.key.facility.idfacility}/places/${placeCounters.key.placeid}/counters" />'
                                               type="button" class="btn btn-info btn-xs"><fmt:message
                                                    key="details"/></a>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                    ${!(loopKeys.count%3==0) ? ' ' : '</div> <div class="row">'}
                </c:forEach>
            </div>
        </c:forEach>

    </c:if>


    <!-- estates -->
    <c:if test="${facilities!=null && facilities.size()>0 }">
        <table class="table table-striped table-bordered table-hover table-condensed">
            <tr>
                <th><fmt:message key="id"/></th>
                <th><fmt:message key="type"/></th>
                <th><fmt:message key="city"/></th>
                <th><fmt:message key="street"/></th>
                <th><fmt:message key="buildingno"/></th>
                <th><fmt:message key="options"/></th>
                <th><fmt:message key="estate"/></th>
            </tr>
            <c:forEach items="${facilities}" var="facility">
                <tr>
                    <td>${facility.idfacility}</td>
                    <td><fmt:message key="${facility.type}"/></td>
                    <td>${facility.addressDto.cityName}</td>
                    <td>${facility.addressDto.streetName}</td>
                    <td>${facility.addressDto.buildingNo}</td>
                    <td>
                        <table class="table table-striped table-bordered table-hover table-condensed">
                            <tr>
                                <th><fmt:message key="media"/></th>
                                <th><fmt:message key="expenses"/></th>
                            </tr>
                            <tr>
                                <td><a href='<c:url value="/finances/estates/${facility.idfacility}/media" />'
                                       type="button" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-tint"
                                                                                       aria-hidden="true"></span></a>
                                    <a href='<c:url value="/finances/estates/${facility.idfacility}/media/medium" />'
                                       type="button" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-plus"
                                                                                       aria-hidden="true"></span></a>
                                </td>
                                <td>
                                    <a href='<c:url value="/finances/estates/${facility.idfacility}/expenses" />'
                                       type="button" class="btn btn-warning btn-xs"><span
                                            class="glyphicon glyphicon-euro" aria-hidden="true"></span></a>
                                    <a href='<c:url value="/finances/estates/${facility.idfacility}/expenses/expense" />'
                                       type="button" class="btn btn-warning btn-xs"><span
                                            class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <a href='<c:url value="/estates/${facility.idfacility}" />' type="button"
                           class="btn btn-info btn-xs"><fmt:message key="details"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>


    <!-- places -->
    <c:if test="${places!=null && places.size()>0 }">
        <div id="content">
            <table
                    class="table table-striped table-bordered table-hover table-condensed">
                <tr>
                    <th><fmt:message key="id"/></th>
                    <th><fmt:message key="address"/></th>
                    <th><fmt:message key="estate"/></th>
                    <th><fmt:message key="area"/></th>
                    <th><fmt:message key="residentCount"/></th>
                    <th><fmt:message key="description"/></th>
                    <th><fmt:message key="counter"/></th>
                    <th><fmt:message key="place"/></th>
                </tr>
                <c:forEach items="${places}" var="place">
                    <tr>
                        <td>${place.id}</td>
                        <td>${place.address.cityCode} ${place.address.cityName},
                                ${place.address.streetName}, ${place.address.buildingNo} <c:if
                                    test="${place.address.officeNo !=null && place.address.officeNo.length()>0}">/ ${place.address.officeNo}</c:if>
                        </td>
                        <td><fmt:message key="${place.facility.type}"/></td>
                        <td>${place.area}</td>
                        <td>${place.residentCount}</td>
                        <td>${place.description}</td>
                        <td>
                            <a href='<c:url value="/finances/estates/${place.facility.idfacility}/places/${place.id}/counters" />'
                               type="button" class="btn btn-warning btn-xs"><span
                                    class="glyphicon glyphicon-refresh" aria-hidden="true"></span></a>
                        </td>
                        <td><a
                                href='<c:url value="/estates/${place.facility.idfacility}/places/${place.id}" />'
                                type="button" class="btn btn-info btn-xs"><fmt:message
                                key="details"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>

    <!-- OLD MEDIA COUNETRS -->
    <c:if test="${mediaCounters!=null && mediaCounters.size()>0 }">
        <table class="table table-striped table-bordered table-hover table-condensed">
            <tr>
                <th>idmediaCounter</th>
                <th>mediaType</th>
                <th>registryNumber</th>
                <th>dateRegistered</th>
                <th>totalAmount</th>
            </tr>
            <c:forEach items="${mediaCounters}" var="counter" varStatus="status">
                <tr>
                    <form:hidden path="mediaCounters[${status.index}].mediaDto.idmedia"
                                 name="mediaCounters[${status.index}].mediaDto.idmedia"
                                 value="${counter.mediaDto.idmedia}"/>
                    <td align="center">${status.count}</td>
                    <th>${counter.idmediaCounter}</th>
                    <td>${counter.mediaType}</td>
                    <td>
                        <input class="form-control" placeholder="Registry number"
                               name="mediaCounters[${status.index}].registryNumber" type="text"
                               value="${counter.registryNumber}"/>
                    </td>
                    <td>${counter.dateRegistered}</td>
                    <td>
                        <input class="form-control" placeholder="Amount"
                               name="mediaCounters[${status.index}].totalAmount" type="text"
                               value="${counter.totalAmount}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>