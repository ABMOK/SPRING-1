<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="content">

    <c:choose>
        <c:when test="${not empty places}">
            <table class="table table-striped table-bordered table-hover table-condensed">
                <tr>
                    <th><fmt:message key="id"/></th>
                    <th><fmt:message key="address"/></th>
                    <th><fmt:message key="estate"/></th>
                    <th><fmt:message key="area"/></th>
                    <th><fmt:message key="residentCount"/></th>
                    <th width="300"><fmt:message key="description"/></th>
                    <th><fmt:message key="options"/></th>
                    <th><fmt:message key="place"/></th>
                </tr>
                <c:forEach items="${places}" var="place">
                    <tr>
                        <td>${place.id}</td>
                        <td>${place.address.cityCode} ${place.address.cityName},
                                ${place.address.streetName}, ${place.address.buildingNo} <c:if
                                    test="${place.address.officeNo !=null && place.address.officeNo.length()>0}">/${place.address.officeNo}</c:if>
                        </td>
                        <td><fmt:message key="${place.facility.type}"/></td>
                        <td>${place.area}</td>
                        <td>${place.residentCount}</td>
                        <td>
                            <c:if test="${place.description!=null && place.description.length()>0}">
                                <div class="alert alert-success"> ${place.description} </div>
                            </c:if>
                        </td>
                        <td>
                            <table class="table table-striped table-bordered table-hover table-condensed">
                                <tr>
                                    <th><fmt:message key="placeowner"/></th>
                                    <td>
                                        <a href='<c:url value="/tenants/estates/${place.facility.idfacility}/places/${place.id}/owners" />'
                                           type="button" class="btn btn-success btn-xs"><span
                                                class="glyphicon glyphicon-user" aria-hidden="true"></span></a> <a
                                            href='<c:url value="/tenants/estates/${place.facility.idfacility}/places/${place.id}/owners/newowner" />'
                                            type="button" class="btn btn-success btn-xs"><span
                                            class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>
                                    </td>
                                </tr>
                                <tr>
                                    <th><fmt:message key="renters"/></th>
                                    <td>
                                        <a href='<c:url value="/tenants/estates/${place.facility.idfacility}/places/${place.id}/renters" />'
                                           type="button" class="btn btn-success btn-xs"><span
                                                class="glyphicon glyphicon-user" aria-hidden="true"></span></a> <a
                                            href='<c:url value="/tenants/estates/${place.facility.idfacility}/places/${place.id}/renters/newrenter" />'
                                            type="button" class="btn btn-success btn-xs"><span
                                            class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>
                                    </td>
                                </tr>
                            </table>
                        </td>

                        <td>
                            <a href='<c:url value="/estates/${place.facility.idfacility}/places/${place.id}" />'
                               type="button" class="btn btn-info btn-xs"><fmt:message
                                    key="edit"/></a> <security:authorize access="hasRole('ROLE_ADMIN')">
                            <a href='<c:url value="/estates/${place.facility.idfacility}/places/${place.id}/delete" />'
                               type="button" class="btn btn-danger btn-xs"><fmt:message
                                    key="delete"/></a>
                        </security:authorize></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <fmt:message key="noObjects"/>
        </c:otherwise>
    </c:choose>




</div>