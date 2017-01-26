<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div id="content">

    <c:choose>
    <c:when test="${not empty facilities}">
        <table
                class="table table-striped table-bordered table-hover table-condensed">
            <tr>
                <th><fmt:message key="id"/></th>
                <th><fmt:message key="type"/></th>
                <th><fmt:message key="city"/></th>
                <th><fmt:message key="street"/></th>
                <th><fmt:message key="buildingno"/></th>
                <th><fmt:message key="places"/></th>
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
                        <a href='<c:url value="/estates/${facility.idfacility}/places" />' type="button"
                           class="btn btn-success btn-xs"><span class="glyphicon glyphicon-qrcode"
                                                                aria-hidden="true"></span></a>
                        <a href='<c:url value="/estates/${facility.idfacility}/places/place" />' type="button"
                           class="btn btn-success btn-xs"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>
                    </td>
                    <td>
                        <a href='<c:url value="/estates/${facility.idfacility}" />' type="button"
                           class="btn btn-info btn-xs"><fmt:message key="edit"/></a>
                        <security:authorize access="hasRole('ROLE_ADMIN')">
                            <a href='<c:url value="/estates/${facility.idfacility}/delete" />' type="button"
                               class="btn btn-danger btn-xs"><fmt:message key="delete"/></a>
                        </security:authorize>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
        <c:otherwise>
            <fmt:message key="noObjects"/>
        </c:otherwise>
    </c:choose>


</div>