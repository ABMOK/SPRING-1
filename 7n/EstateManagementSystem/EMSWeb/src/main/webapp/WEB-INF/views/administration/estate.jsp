<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div>
    <h2>
        <fmt:message key="estate"/>
    </h2>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="panel-heading"><fmt:message key="estate"/></div>
        <form:form modelAttribute="facility" method="POST" commandName="facility">
        <form:hidden path="idfacility" name="idfacility" value="${facility.idfacility}"/>
        <form:hidden path="addressDto.idaddress" name="addressDto.idaddress" value="${facility.addressDto.idaddress}"/>
        <div class="panel-body">
            <div class="col-md-4">
                <div class="form-group">
                    <label for="type">
                        <fmt:message key="type"/>
                    </label>
                    <form:select class="form-control" path="type" multiple="false">
                        <form:options items="${facilityTypes}" itemLabel="facilityTypeName"/>
                    </form:select>
                    <form:errors path="type" cssStyle="color: #ff0000;"/>
                </div>
                <div class="form-group">
                    <label for="description"><fmt:message key="description"/></label>
                    <input type="text" class="form-control" id="description" name="description"
                           value="${facility.description}" placeholder="<fmt:message key="description" />">
                    <form:errors path="description" cssStyle="color: #ff0000;"/>
                </div>
                <div class="form-group">
                    <label for="area"><fmt:message key="area"/></label>
                    <input type="text" class="form-control" id="area" name="area"
                           value="${facility.area}" placeholder="<fmt:message key="area" />">
                </div>
                <c:if test="${facility.idfacility==null  || facility.idfacility==0 }">
                    <div class="form-group">
                        <label for="numberOfPlaces"><fmt:message key="numberOfPlaces"/></label>
                        <input type="text" class="form-control" id="numberOfPlaces" name="numberOfPlaces"
                               value="${facility.places.size()}" placeholder="<fmt:message key="numberOfPlaces" />">
                    </div>
                </c:if>

            </div>
            <div class="col-md-4">

                <div class="form-group">
                    <label for="addressDto.districtName"><fmt:message key="district"/></label>
                    <input type="text" class="form-control" id="addressDto.districtName" name="addressDto.districtName"
                           value="${facility.addressDto.districtName}" placeholder="<fmt:message key="district" />">
                    <form:errors path="addressDto.districtName" cssStyle="color: #ff0000;"/>
                </div>
                <div class="form-group">
                    <label for="addressDto.cityCode"><fmt:message key="citycode"/></label>
                    <input type="text" class="form-control" id="addressDto.cityCode" name="addressDto.cityCode"
                           value="${facility.addressDto.cityCode}" placeholder="<fmt:message key="citycode" />">
                    <form:errors path="addressDto.cityCode" cssStyle="color: #ff0000;"/>
                </div>
                <div class="form-group">
                    <label for="addressDto.cityName"><fmt:message key="city"/></label>
                    <input type="text" class="form-control" id="addressDto.cityName" name="addressDto.cityName"
                           value="${facility.addressDto.cityName}" placeholder="<fmt:message key="city" />">
                    <form:errors path="addressDto.cityName" cssStyle="color: #ff0000;"/>
                </div>

                <div class="form-group">
                    <label for="addressDto.streetName"><fmt:message key="street"/></label>
                    <input type="text" class="form-control" id="addressDto.streetName" name="addressDto.streetName"
                           value="${facility.addressDto.streetName}" placeholder="<fmt:message key="street" />">
                    <form:errors path="addressDto.streetName" cssStyle="color: #ff0000;"/>
                </div>

                <div class="form-group">
                    <label for="addressDto.buildingNo"><fmt:message key="buildingno"/></label>
                    <input type="text" class="form-control" id="addressDto.buildingNo" name="addressDto.buildingNo"
                           value="${facility.addressDto.buildingNo}" placeholder="<fmt:message key="buildingno" />">
                    <form:errors path="addressDto.buildingNo" cssStyle="color: #ff0000;"/>
                </div>
            </div>
            <div class="col-md-4">

                <div class="form-group">
                    <label for="addressDto.countryCode"><fmt:message key="countrycode"/></label>
                    <input type="text" class="form-control" id="addressDto.countryCode" name="addressDto.countryCode"
                           value="${facility.addressDto.countryCode}" placeholder="<fmt:message key="countrycode" />">
                </div>
                <div class="form-group">
                    <label for="addressDto.country"><fmt:message key="country"/></label>
                    <input type="text" class="form-control" id="addressDto.country" name="addressDto.country"
                           value="${facility.addressDto.country}" placeholder="<fmt:message key="country" />">
                </div>
                <c:if test="${null == facility.idfacility}">
                <div class="col-md-5">
                    <div class="form-group">
                        <c:forEach items="${mediaTypes}" var="mediaType">
                            <div>
                                <form:checkbox path="mediaTypes" value="${mediaType.mediaTypeValue}"/>
                                <fmt:message key="${mediaType}"/>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-md-7">
                    <div class="form-group">
                        <c:forEach items="${expenseTypes}" var="expenseType">
                            <div>
                                <form:checkbox path="expenseTypes" value="${expenseType.commonExpenseTypeValue}"/>
                                <fmt:message key="${expenseType}"/>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                </c:if>
            </div>
        </div>
        <button type="submit" class="btn btn-default" style="float: right;">Submit</button>
        <c:if test="${possibleDuplicates!=null && !possibleDuplicates.isEmpty()}">
            <div class="row">

                <div class="panel panel-default">
                    <div class="panel-heading text-center">
                        <h4>
                            <fmt:message key="duplicateFacilityWarning"/>
                        </h4>
                    </div>
                    <div class="panel-body">
                        <table class="table table-striped table-bordered table-hover table-condensed">
                            <c:forEach items="${possibleDuplicates}" var="duplicateFacility" varStatus="loopMap">
                                <tr>
                                    <td>
                                            ${duplicateFacility.addressDto.streetName} ${duplicateFacility.addressDto.buildingNo}
                                        <a href='<c:url value="/estates/${duplicateFacility.idfacility}" />' type="button"
                                           class="btn btn-success btn-xs"><fmt:message key="useMe"/></a>

                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        </div>
                    </div>
            </div>
        </c:if>
    </div>
    </form:form>
</div>






