<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<div class="container-fluid">
    <div class="row">

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <fmt:message key="attributes"/>
                </h3>
            </div>
        </div>

        <div class="col-md-6">
            <div class="panel panel-default ">

                <div class="panel-heading text-center">
                    <h5>
                        <fmt:message key="attribute"/>
                    </h5>
                </div>

                <form:form modelAttribute="classy" method="POST" commandName="classy">
                    <form:hidden path="attributeDto.id" name="attributeDto.id"
                                 value="${classy.attributeDto.id}"/>
                    <div class="form-group text-center">
                        <div class="row top-buffer">
                            <div class="col-md-4 form-group text-center">
                                <label for="selectbasic"><fmt:message key="visibilityLevel"/></label>
                                <form:select path="attributeDto.roleName" multiple="false"
                                             id="selectbasic" name="selectbasic" class="form-control">
                                    <form:options items="${usageTypes}" itemLabel="userTypeName"/>
                                </form:select>
                            </div>
                            <div class="col-md-4">
                                <label for="attributeDto.attributeName"><fmt:message
                                        key="itsName"/></label> <input id="attributeDto.attributeName"
                                                                       class="form-control" placeholder="<fmt:message key="itsName"/>"
                                                                       name="attributeDto.attributeName" type="text"
                                                                       value="${classy.attributeDto.attributeName}"/>
                                <form:errors path="attributeDto.attributeName" cssStyle="color: #ff0000;"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-11">
                                <button type="submit" class="btn btn-default pull-right"><fmt:message
                                        key="submit"/></button>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>

        <div class="col-md-6">

            <c:if test="${classy.allAttributes!=null && !classy.allAttributes.isEmpty() }">
                <c:forEach items="${classy.allAttributes}" var="attribute" varStatus="loopMap">
                    <div class="panel panel-default">
                        <div class="panel-heading text-center">
                            <div class="row">
                                    ${attribute.attributeName}
                            </div>
                        </div>
                        <div class="panel-body text-center">
                                ${attribute.roleName}
                        </div>
                        <div class="panel-footer text-center">
                            <div class="row">
                                <div class="col-md-6">
                                    <c:if test="${attribute.categories!=null && !attribute.categories.isEmpty() }">
                                        <fmt:message key="categories"/>:  ${attribute.categories.size()}
                                    </c:if>
                                </div>
                                <div class="col-md-6">
                                    <a
                                            href='<c:url value="/classifiedads/administration/attributes/${attribute.id}" />'
                                            type="button"
                                            class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle">
                                        <fmt:message key="delete"/>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>

    <div class="row">

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <fmt:message key="categories"/>
                </h3>
            </div>
        </div>

        <div class="col-md-6">
            <div class="panel panel-default text-center">
                <div class="panel-heading text-center">
                    <h5>
                        <fmt:message key="category"/>
                    </h5>
                </div>
                <form:form modelAttribute="classy" method="POST" commandName="classy">
                    <form:hidden path="category.idcategory"
                                 name="category.idcategory"
                                 value="${classy.category.idcategory}"/>
                    <div class="panel panel-body">
                    <div class="form-group text-center">
                                <label for="category.categoryName">
                                    <fmt:message key="itsName"/></label>
                                <input id="category.categoryName" class="form-control" placeholder=" <fmt:message key="itsName"/>"
                                       name="category.categoryName" type="text"
                                       value="${classy.category.categoryName}"/>
                        <form:errors path="category.categoryName" cssStyle="color: #ff0000;"/>
                                <label for="category.categoryDescriptionShort">
                                    <fmt:message key="description"/></label>
                                <input id="category.categoryDescriptionShort" class="form-control"
                                    placeholder="<fmt:message key="description"/>"
                                    name="category.categoryDescriptionShort" type="text"
                                    value="${classy.category.categoryDescriptionShort}"/>
                        <form:errors path="category.categoryDescriptionShort" cssStyle="color: #ff0000;"/>
                                    <label for="category.categoryDescriptionLong">
                                        <fmt:message key="descriptionLong"/></label>
                         <textarea id="category.categoryDescriptionLong" class="form-control"
                                   name="category.categoryDescriptionLong" type="text"
                                   value="${classy.category.categoryDescriptionLong}"></textarea>

                        <form:errors path="category.categoryDescriptionLong" cssStyle="color: #ff0000;"/>

                        <label for="categoryAttributes"> <fmt:message
                                        key="pick"/> <fmt:message key="attributes"/>
                                </label>

                                <form:select path="categoryAttributes" multiple="true"
                                             id="categoryAttributes"
                                             name="categoryAttributes"
                                             var="id"
                                             class="form-control">
                                    <form:options path="categoryAttributes" items="${classy.allAttributes}"
                                                  itemValue="id"
                                                  itemLabel="attributeName"/>
                                </form:select>
                        </div>
                        <div class="row top-buffer">
                                <div class="col-md-11">
                            <button type="submit" class="btn btn-default pull-right"><fmt:message
                                    key="submit"/></button>
                                    </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
        <div class="col-md-6">

            <c:if test="${classy.categoryList!=null && !classy.categoryList.isEmpty() }">
                <div class="row">
                    <c:forEach items="${classy.categoryList}" var="category" varStatus="loopMap">
                       <div class="panel panel-default ">
                          <div class="panel-heading text-center">${category.categoryName}</div>
                                <div class="panel-body text-center">
                                    <div class="alert alert-info" role="alert">
                                            ${category.categoryDescriptionShort}
                                    </div>

                                    <c:if test="${category.categoryAttributes!=null && !category.categoryAttributes.isEmpty() }">
                                       <div class="row">
                                        <fmt:message key="attributes"/>:
                                        <c:forEach items="${category.categoryAttributes}" var="attribute"
                                                       varStatus="loop">
                                                <div class="row top-buffer text-center">
                                                        ${attribute.attributeName}
                                                </div>

                                            </c:forEach>
                                       </div>
                                    </c:if>
                                </div>
                                <div class="panel-footer text-center">
                                    <a
                                            href='<c:url value="/classifiedads/categories/${category.idcategory}" />'
                                            type="button"
                                            class="btn btn-success btn-xs glyphicon glyphicon-list-alt">
                                        <fmt:message key="classyAds"/>
                                    </a>
                                    <a
                                            href='<c:url value="/classifiedads/administration/categories/${category.idcategory}" />'
                                            type="button"
                                            class="btn btn-warning btn-xs glyphicon glyphicon-edit">
                                        <fmt:message key="edit"/>
                                    </a>
                                    <a
                                            href='<c:url value="/classifiedads/administration/categories/${category.idcategory}/delete" />'
                                            type="button"
                                            class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle">
                                        <fmt:message key="delete"/>
                                    </a>
                                </div>
                            </div>
                    </c:forEach>
                </div>
            </c:if>

        </div>
    </div>
</div>
