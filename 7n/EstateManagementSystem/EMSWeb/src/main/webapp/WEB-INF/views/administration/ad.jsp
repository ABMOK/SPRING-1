<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<div class="container-fluid">
    <div class="row">

        <form:form modelAttribute="classy" method="POST" commandName="classy">
            <form:hidden path="classyAd.idclassifiedAd" name="classyAd.idclassifiedAd"
                         value="${classy.classyAd.idclassifiedAd}"/>

            <div class="col-md-2">
            </div>
            <div class="col-md-8">
                <div>
                    <h3>
                        <fmt:message key="classyAd"/>
                    </h3>
                </div>
                <div class="form-group">
                    <label for="classyAd.title"><fmt:message key="adtitle"/></label>
                    <input type="text" class="form-control" id="classyAd.title" name="classyAd.title"
                           value="${classy.classyAd.title}" placeholder="<fmt:message key="adtitle" />">
                    <form:errors path="classyAd.title" cssStyle="color: #ff0000;"/>

                </div>
                <div class="form-group">
                    <label><fmt:message key="category"/></label>
                    <form:select path="classyAd.category.idcategory" multiple="false"
                                 id="classyAd.category.idcategory"
                                 name="classyAd.category.idcategory"
                                 class="form-control">
                        <form:options path="classyAd.category.idcategory"
                                      items="${classy.categoryList}"
                                      itemValue="idcategory"
                                      itemLabel="categoryName"/>

                    </form:select>
                </div>
                <div class="form-group">
                    <label for="classyAd.shortDescription"><fmt:message key="classyAd.shortDescription"/></label>
                    <input type="text" class="form-control" id="classyAd.shortDescription"
                           name="classyAd.shortDescription" value="${classy.classyAd.shortDescription}"
                           placeholder="<fmt:message key="classyAd.shortDescription" />">
                    <form:errors path="classyAd.shortDescription" cssStyle="color: #ff0000;"/>

                </div>
                <div class="form-group">
                    <label for="classyAd.longDescription"><fmt:message key="longDescription"/></label>
                    <textarea class="form-control" id="classyAd.longDescription"
                              name="classyAd.longDescription" value="${classy.classyAd.longDescription}"
                              placeholder="<fmt:message key="longDescription" />"></textarea>
                    <form:errors path="classyAd.longDescription" cssStyle="color: #ff0000;"/>
                </div>
                <button type="submit" class="btn btn-default" style="float: right;"><fmt:message key="submit" /></button>
            </div>
            <div class="col-md-2">
            </div>
        </form:form>
    </div>
</div>
