<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div>
    <h2>
        <fmt:message key="place"/>
    </h2>
</div>

<h5>
    <label for="address.cityCode">${place.address.cityCode}</label>
    <label for="address.cityName">${place.address.cityName}</label>
    <label for="address.streetName">${place.address.streetName}</label>
    <label for="address.buildingNo">${place.address.buildingNo}</label>
    <label for="address.cityName"><fmt:message key="officeno"/>:</label>
    <label for="address.buildingNo">${place.address.officeNo}</label>

</h5>

<form:form modelAttribute="place" method="POST" commandName="place">
    <form:hidden path="placeid" name="placeid" value="${place.placeid}"/>
    <form:hidden path="facility.idfacility" name="facility.idfacility" value="${place.facility.idfacility}"/>
    <form:hidden path="address.idaddress" name="address.idaddress" value="${place.address.idaddress}"/>
    <div class="form-group">
        <div class="col-lg-6">
            <label for="address.countryCode"><fmt:message key="countrycode"/></label>
            <input type="text" class="form-control" id="address.countryCode" name="address.countryCode"
                   value="${place.address.countryCode}" placeholder="<fmt:message key="countrycode" />">

            <label for="address.country"><fmt:message key="country"/></label>
            <input type="text" class="form-control" id="address.country" name="address.country"
                   value="${place.address.country}" placeholder="<fmt:message key="country" />">
        </div>

        <div class="col-lg-6">
            <label for="address.districtName"><fmt:message key="district"/></label>
            <input type="text" class="form-control" id="address.districtName" name="address.districtName"
                   value="${place.address.districtName}" placeholder="<fmt:message key="district" />">

            <label for="address.cityCode"><fmt:message key="citycode"/></label>
            <input type="text" class="form-control" id="address.cityCode" name="address.cityCode"
                   value="${place.address.cityCode}" placeholder="<fmt:message key="citycode" />">
            <form:errors path="address.cityCode" cssStyle="color: #ff0000;"/>
        </div>

        <div class="col-lg-6">
            <label for="address.cityName"><fmt:message key="city"/></label>
            <input type="text" class="form-control" id="address.cityName" name="address.cityName"
                   value="${place.address.cityName}" placeholder="<fmt:message key="city" />">
            <form:errors path="address.cityName" cssStyle="color: #ff0000;"/>
            <label for="address.streetName"><fmt:message key="street"/></label>
            <input type="text" class="form-control" id="address.streetName" name="address.streetName"
                   value="${place.address.streetName}" placeholder="<fmt:message key="street" />">
            <form:errors path="address.streetName" cssStyle="color: #ff0000;"/>
        </div>

        <div class="col-lg-6">
            <label for="address.buildingNo"><fmt:message key="buildingno"/></label>
            <input type="text" class="form-control" id="address.buildingNo" name="address.buildingNo"
                   value="${place.address.buildingNo}" placeholder="<fmt:message key="buildingno" />">
            <form:errors path="address.buildingNo" cssStyle="color: #ff0000;"/>
            <label for="address.officeNo"><fmt:message key="officeno"/></label>
            <input type="text" class="form-control" id="address.officeNo" value="${place.address.officeNo}"
                   name="address.officeNo" placeholder="<fmt:message key="officeno" />">
            <form:errors path="address.officeNo" cssStyle="color: #ff0000;"/>
        </div>

        <div class="col-lg-6">
            <label for="area"><fmt:message key="area"/></label>
            <input type="text" class="form-control" id="area" name="area" value="${place.area}"
                   placeholder="<fmt:message key="area" />">

            <label for="residentCount"><fmt:message key="residentCount"/></label>
            <input type="text" class="form-control" id="residentCount" name="residentCount"
                   value="${place.residentCount}" placeholder="<fmt:message key="residentCount" />">
        </div>

        <div class="col-lg-6">
            <label for="description"><fmt:message key="description"/></label>
            <textarea class="form-control" rows="3" id="description" name="description"
                      placeholder="<fmt:message key="description" />">${place.description}</textarea>
            <form:errors path="description" cssStyle="color: #ff0000;"/>
        </div>
        <button type="submit" class="btn btn-default" style="float: right;">Submit</button>
    </div>
</form:form>
<c:if test="${possibleDuplicates!=null && !possibleDuplicates.isEmpty()}">
    <div class="row">
        <fmt:message key="duplicatePlaceWarning"/>
        <table class="table table-striped table-bordered table-hover table-condensed">
            <c:forEach items="${possibleDuplicates}" var="duplicatePlace" varStatus="loopMap">
                <tr>
                    <td>
                            ${duplicatePlace.address.streetName} ${duplicatePlace.address.buildingNo} <label
                            for="address.cityName"><fmt:message key="place"/></label> ${duplicatePlace.address.officeNo}
                        <a href='<c:url value="/estates/${duplicatePlace.facility.idfacility}/places/${duplicatePlace.placeid}" />'
                           type="button" class="btn btn-success btn-xs"><fmt:message key="useMe"/></a>

                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>