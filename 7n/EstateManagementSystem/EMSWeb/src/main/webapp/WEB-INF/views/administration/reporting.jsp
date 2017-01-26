<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div id="content">


    <c:if test="${requiredReports!=null}">
        <c:choose>
            <c:when test="${not empty requiredReports.requiredReports}">
                <security:authorize access="hasAnyRole('ROLE_OWNER','ROLE_RENTER')">
                <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                    <div>
                        <h2>
                            <fmt:message key="reportCommon"/>
                        </h2>
                    </div>
                </security:authorize>
                <security:authorize access="hasAnyRole('ROLE_OWNER','ROLE_RENTER')">
                    <div>
                        <h2>
                            <fmt:message key="reportCounter"/>
                        </h2>
                    </div>
                </security:authorize>

                <form:form modelAttribute="requiredReports" method="POST" commandName="requiredReports">
                    <table class="table table-striped table-bordered table-hover table-condensed">
                        <tr>
                            <th><fmt:message key="type"/></th>
                            <th><fmt:message key="amount"/></th>
                            <th><fmt:message key="msg"/></th>
                        </tr>
                        <c:forEach items="${requiredReports.requiredReports}" var="report" varStatus="status">
                            <tr>
                                <form:hidden path="requiredReports[${status.index}].idreport"
                                             name="requiredReports[${status.index}].idreport"
                                             value="${report.idreport}"/>
                                <form:hidden path="requiredReports[${status.index}].mediaCounterDto.idmediaCounter"
                                             name="requiredReports[${status.index}].mediaCounterDto.idmediaCounter"
                                             value="${report.mediaCounterDto.idmediaCounter}"/>
                                <form:hidden path="requiredReports[${status.index}].type"
                                             name="requiredReports[${status.index}].type" value="${report.type}"/>

                                <td><fmt:message key="${report.type}"/></td>

                                <td><input class="form-control"
                                           name="requiredReports[${status.index}].amount" type="text"
                                           <c:if test="${not null == report.mediaCounterDto.totalAmount}">
                                           value="${report.mediaCounterDto.totalAmount}"
                                           </c:if> />
                                </td>
                                <td><input class="form-control"
                                           name="requiredReports[${status.index}].comment" type="text"
                                           value="${report.comment}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <button type="submit" class="btn btn-default"><fmt:message key="submit"/></button>
                </form:form>
                </security:authorize>
            </c:when>
            <c:otherwise>
                <fmt:message key="noreportsneeded"/>
            </c:otherwise>
        </c:choose>

<c:if test="${not empty requiredReports.usersMissingReports}">
    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
        <div>
            <h2>
                <fmt:message key="reports.usersMissing"/>
            </h2>
        </div>
        <table class="table table-striped table-bordered table-hover table-condensed">
            <tr>
                <th><fmt:message key="name"/>, <fmt:message key="surname"/></th>
                <th><fmt:message key="place"/></th>
                <th><fmt:message key="type"/></th>
                <th><fmt:message key="counter"/></th>

            </tr>
            <c:forEach items="${requiredReports.usersMissingReports}" var="report" varStatus="status">
                <tr>
                    <td>${report.placeuser.userDto.name} ${report.placeuser.userDto.surname}</td>
                    <td>${report.placeuser.placeDto.address.streetName}, ${report.placeuser.placeDto.address.buildingNo} / ${report.placeuser.placeDto.address.officeNo}</td>
                    <td> <fmt:message key="${report.type}"/></td>
                    <td>${report.mediaCounterDto.registryNumber}</td>
                </tr>
            </c:forEach>
        </table>
    </security:authorize>
</c:if>

        <c:choose>
            <c:when test="${not empty requiredReports.existingReports}">

                <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                    <div>
                        <h2>
                            <fmt:message key="reports.users"/>
                        </h2>
                    </div>
                </security:authorize>
                <security:authorize access="hasAnyRole('ROLE_OWNER','ROLE_RENTER')">
                    <div>
                        <h2>
                            <fmt:message key="yourreports"/>
                        </h2>
                    </div>
                </security:authorize>


                <table class="table table-striped table-bordered table-hover table-condensed">
                    <tr>

                        <th><fmt:message key="name"/>, <fmt:message key="surname"/></th>
                        <th><fmt:message key="place"/></th>
                        <th><fmt:message key="counter"/>-<fmt:message key="registryNumber"/></th>
                        <th><fmt:message key="counter"/>-<fmt:message key="amount"/></th>
                        <th><fmt:message key="type"/></th>
                        <th><fmt:message key="report"/>-<fmt:message key="amount"/></th>
                        <th><fmt:message key="description"/></th>
                        <th><fmt:message key="sent"/></th>

                    </tr>
                    <c:forEach items="${requiredReports.existingReports}" var="report" varStatus="status">
                        <tr>

                            <td>${report.placeuser.userDto.name} ${report.placeuser.userDto.surname}</td>
                            <td>${report.placeuser.placeDto.address.streetName}, ${report.placeuser.placeDto.address.buildingNo} / ${report.placeuser.placeDto.address.officeNo}</td>
                            <td>${report.mediaCounterDto.registryNumber}</td>
                            <td>${report.mediaCounterDto.totalAmount}</td>
                            <td> <fmt:message key="${report.type}"/></td>
                            <td>${report.amount}</td>
                            <td>${report.comment}</td>
                            <td>${report.date}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <fmt:message key="noObjects"/>
            </c:otherwise>
        </c:choose>
    </c:if>
</div>