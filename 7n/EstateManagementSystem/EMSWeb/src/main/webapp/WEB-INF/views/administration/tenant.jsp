<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div>
	<h2>
		<fmt:message key="placeuser" />
	</h2>
</div>

<div class="container-fluid">
<form:form modelAttribute="tenant" method="POST" commandName="tenant">
<form:hidden path="idplaceuser" name="idplaceuser" value="${tenant.idplaceuser}"/>
    <form:hidden path="userDto.iduser" name="userDto.iduser" value="${tenant.userDto.iduser}"/>
    <form:hidden path="placeDto.placeid" name="placeDto.placeid" value="${tenant.placeDto.placeid}"/>
    <form:hidden path="placeDto.address.idaddress" name="placeDto.address.idaddress"
                 value="${tenant.placeDto.address.idaddress}"/>
    <form:hidden path="placeDto.address.cityCode" name="placeDto.address.cityCode"
                 value="${tenant.placeDto.address.cityCode}"/>
    <form:hidden path="placeDto.address.cityName" name="placeDto.address.cityName"
                 value="${tenant.placeDto.address.cityName}"/>
    <form:hidden path="placeDto.address.streetName" name="placeDto.address.streetName"
                 value="${tenant.placeDto.address.streetName}"/>
    <form:hidden path="placeDto.address.buildingNo" name="placeDto.address.buildingNo"
                 value="${tenant.placeDto.address.buildingNo}"/>
    <form:hidden path="placeDto.address.officeNo" name="placeDto.address.officeNo"
                 value="${tenant.placeDto.address.officeNo}"/>
    <form:hidden path="placeDto.address.country" name="placeDto.address.country"
                 value="${tenant.placeDto.address.country}"/>
    <form:hidden path="placeDto.address.countryCode" name="placeDto.address.countryCode"
                 value="${tenant.placeDto.address.countryCode}"/>
    <form:hidden path="placeDto.address.districtName" name="placeDto.address.districtName"
                 value="${tenant.placeDto.address.districtName}"/>
    <form:hidden path="placeDto.area" name="placeDto.area"
                 value="${tenant.placeDto.area}"/>
	<div class="row">
	<div class="form-group">
                <div class="alert alert-info" role="alert">
                  <fmt:message key="place" /> : ${tenant.placeDto.address.cityCode}, ${tenant.placeDto.address.cityName},
                        ${tenant.placeDto.address.streetName} ${tenant.placeDto.address.buildingNo}
                    / ${tenant.placeDto.address.officeNo}
                </div>
            </div>
		<div class="col-md-4">		

            <div class="form-group">
            <label for="utype"><fmt:message key="type"/></label>
                <form:select path="usageType" multiple="false" class="form-control" id="utype">
                    <form:options items="${usageTypes}" itemLabel="userTypeName"/>
                </form:select>
            </div>
             <div class="form-group">
                <label for="userDto.name"><fmt:message key="name"/></label>
                <input id="userDto.name" class="form-control" placeholder="<fmt:message key="name"/>" name="userDto.name" type="text"
                       value="${tenant.userDto.name}"/>
                 <form:errors path="userDto.name" cssStyle="color: #ff0000;"/>
            </div>
            <div class="form-group">
                <label for="userDto.surname"><fmt:message key="surname"/></label> <input
                    id="userDto.surname" class="form-control" placeholder="<fmt:message key="surname"/>" name="userDto.surname" type="text"
                    value="${tenant.userDto.surname}"/>
                <form:errors path="userDto.surname" cssStyle="color: #ff0000;"/>
            </div>
		</div>
		<div class="col-md-4">
            <div class="form-group">
                <label for="userDto.mail"><fmt:message key="mail"/></label>
                <input type="email" class="form-control" id="userDto.mail" placeholder="<fmt:message key="mail"/>" name="userDto.mail"
                       value="${tenant.userDto.mail}">
                <form:errors path="userDto.mail" cssStyle="color: #ff0000;"/>
            </div>
            <div class="form-group">
                <label for="userDto.phoneNo"><fmt:message key="phone"/></label>
                <input id="userDto.phoneNo" class="form-control" placeholder="<fmt:message key="phone"/>"
                                                                  name="userDto.phoneNo" type="text"
                                                                  value="${tenant.userDto.phoneNo}"/>
                <form:errors path="userDto.phoneNo" cssStyle="color: #ff0000;"/>
            </div>            
            <div class="form-group">
                <label for="placeDto.residentCount"><fmt:message key="rescount"/></label>
                <input type="number" class="form-control" id="placeDto.residentCount" name="placeDto.residentCount"
                       value="${tenant.placeDto.residentCount}">
            </div>
        
        </div>
		<div class="col-md-4">
		<div class="form-group">
                <label for="userDto.login"><fmt:message key="loginName"/></label>
                <input id="userDto.login" class="form-control" placeholder="<fmt:message key="loginName"/>" name="userDto.login" type="text"
                       value="${tenant.userDto.login}"/>
            <form:errors path="userDto.login" cssStyle="color: #ff0000;"/>
            </div>
            <div class="form-group">
                <label for="userDto.password"><fmt:message key="password"/></label>
                <input type="password" class="form-control" id="userDto.password" name="userDto.password"
                       value="${tenant.userDto.password}">
                <form:errors path="userDto.password" cssStyle="color: #ff0000;"/>
            </div>
		</div>
		<button type="submit" class="btn btn-default" style="float: right;">Submit</button>
		
	</div>
	</form:form>
</div>

