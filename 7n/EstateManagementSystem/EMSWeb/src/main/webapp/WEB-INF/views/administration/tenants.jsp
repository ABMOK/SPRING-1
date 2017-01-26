<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="content">

<c:choose>
    <c:when test="${not empty tenants}">
        <table class="table table-striped table-bordered table-hover table-condensed">
            <tr>
                <th><fmt:message key="id"/></th>
                <th><fmt:message key="estate"/></th>
                <th><fmt:message key="address"/></th>
                <th><fmt:message key="relation"/></th>
                <th><fmt:message key="name"/>, <fmt:message key="surname"/></th>
                <th><fmt:message key="startdate"/></th>
                <th><fmt:message key="enddate"/></th>                
                <th><fmt:message key="options"/></th>
            </tr>
            <c:forEach items="${tenants}" var="tenant">
                <tr>
                    <th>${tenant.idplaceuser}</th>
                    <td><fmt:message key="${tenant.placeDto.facility.type}"/></td>
                    <td>${tenant.placeDto.address.cityCode} ${tenant.placeDto.address.cityName},
                            ${tenant.placeDto.address.streetName}, ${tenant.placeDto.address.buildingNo}
                        /${tenant.placeDto.address.officeNo}
                    </td>
                    <td><fmt:message key="${tenant.usageType}"/></td>
                    <td>${tenant.userDto.name} ${tenant.userDto.surname}</td>
                    <td>${tenant.startdate}</td>
                    <td><c:if test="${tenant.enddate == null}"><fmt:message key="yet"/></c:if>
                            ${tenant.enddate}</td>
                    
                    <td>
                        <a href='<c:url value="/tenants/estates/${tenant.placeDto.facility.idfacility}/places/${tenant.placeDto.placeid}/${tenant.usageType.toLowerCase()}s/${tenant.idplaceuser}" />'
                           type="button" class="btn btn-info btn-xs"><fmt:message key="details"/></a>
                        <security:authorize access="hasRole('ROLE_ADMIN')">
                            <a href='<c:url value="/tenants/${tenant.idplaceuser}/delete" />' type="button"
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