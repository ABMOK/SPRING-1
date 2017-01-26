<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form modelAttribute="admin" method="POST" commandName="admin">
    <div class="panel panel-default">
        <div class="panel-heading"><fmt:message key="systemuser"/>
            <form:hidden path="userDto.iduser" name="userDto.iduser" value="${admin.userDto.iduser}"/>
            <form:hidden path="userDto.placeUserId" name="userDto.placeUserId" value="${admin.userDto.placeUserId}"/>
        </div>
        <div class="row">
            <div class="col-md-4">
                <div class="panel-body">
                    <div class="form-group">
                        <label for="userDto.name"><fmt:message key="name"/></label>
                        <input id="userDto.name" class="form-control" placeholder="<fmt:message key="name"/>" name="userDto.name" size="50"
                               maxlength="50" value="${admin.userDto.name}"
                               type="text"/>
                        <form:errors path="userDto.name" cssStyle="color: #ff0000;"/>
                    </div>
                    <div class="form-group">
                        <label for="userDto.surname"><fmt:message key="surname"/></label>
                        <input id="userDto.surname" class="form-control" placeholder="<fmt:message key="surname"/>" name="userDto.surname"
                               size="50" value="${admin.userDto.surname}"
                               maxlength="50" type="text"/>
                        <form:errors path="userDto.surname" cssStyle="color: #ff0000;"/>
                    </div>
                    <div class="form-group">
                        <label for="userDto.phoneNo"><fmt:message key="phone"/></label>
                        <input id="userDto.phoneNo" class="form-control" placeholder="<fmt:message key="phone"/>" name="userDto.phoneNo"
                               size="50" value="${admin.userDto.phoneNo}"
                               maxlength="50" type="text"/>
                        <form:errors path="userDto.phoneNo" cssStyle="color: #ff0000;"/>
                    </div>
                    <div class="form-group">
                        <label for="userDto.mail"><fmt:message key="mail"/></label>
                        <input type="userDto.email" class="form-control" id="userDto.mail" placeholder="<fmt:message key="mail"/>"
                               name="userDto.mail"  value="${admin.userDto.mail}">
                        <form:errors path="userDto.mail" cssStyle="color: #ff0000;"/>
                    </div>

                </div>
            </div>
            <div class="col-md-4">
                <div class="panel-body">
                    <div class="form-group">
                        <label for="userDto.myRoles"><fmt:message key="type"/></label>
                        <form:select path="userDto.myRoles" multiple="true" class="form-control" size="5">
                            <form:options items="${usageTypes}" itemLabel="userTypeName"/>
                        </form:select>
                        <form:errors path="userDto.myRoles" cssStyle="color: #ff0000;"/>
                    </div>
                    <c:if test="${not empty facilities}">
                    <div class="form-group">
                        <label for="placeDto.facility.idfacility"><fmt:message key="estate"/></label>

                            <form:select path="placeDto.facility.idfacility" class="form-control"
                                         id="facility"
                                         onchange="ajaxPlaces()">
                                <option value='-1'><fmt:message key="estate"/></option>
                                <form:options items="${facilities}" itemLabel="idfacility" itemValue="idfacility"/>
                            </form:select>
                            <label for="placeDto.placeid"><fmt:message key="place"/></label>
                            <form:select path="placeDto.placeid"
                                         multiple="false"
                                         id="placeid"
                                         name="placeDto.placeid"
                                         class="form-control">
                            </form:select>

                        </div>
                    </c:if>

                </div>
            </div>
            <div class="col-md-4">
                <div class="panel-body">
                    <div class="form-group">
                        <label for="userDto.login"><fmt:message key="loginName"/></label>
                        <input id="userDto.login" class="form-control" placeholder="<fmt:message key="loginName"/>" name="userDto.login"
                               size="50" value="${admin.userDto.login}"
                               maxlength="50"
                               type="text"/>
                        <form:errors path="userDto.login" cssStyle="color: #ff0000;"/>
                    </div>
                    <div class="form-group">
                        <label for="userDto.password"><fmt:message key="password"/></label>
                        <input type="userDto.password" class="form-control" id="userDto.password"
                               name="userDto.password" value="${admin.userDto.password}"
                               placeholder="<fmt:message key="password"/>">
                        <form:errors path="userDto.password" cssStyle="color: #ff0000;"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel-footer">

            <div class="row">
                <div class="col-md-4">
                </div>
                <div class="col-md-4">

                </div>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-default" style="float: right;"><fmt:message key="submit"/></button>

                </div>
            </div>

        </div>
    </div>
</form:form>


<script type="text/javascript">
    function ajaxPlaces() {
        var facility = $('#facility').val();
        $.ajax({
            headers: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            },

            type: "GET",
            url: "/ajax/places",
            data: "facility=" + facility,
            success: function (response) {
                showPlaces(response);
            },
            error: function (e) {
                alert('Error: ' + e + e.cache + e.data);
            }
        });
    }

    function showPlaces(data) {
        $('#placeid').html(' ');
        $('#placeid').append("<option value='-1'><fmt:message key="place"/></option>");
        for (var i = 0, len = data.length; i < len; ++i) {
            var place = data[i];
            $('#placeid').append("<option value=\"" + place.placeid + "\">" + place.placeid
                    +" "+ place.address.streetName
                    + " "+ place.address.buildingNo
                    + " / "+ place.address.officeNo
                    + "</option>");
        }
    }
</script>

