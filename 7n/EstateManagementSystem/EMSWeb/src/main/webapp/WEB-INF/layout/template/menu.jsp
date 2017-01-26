<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${classy!=null}">
    <label><fmt:message key="categories"/></label>
    <div id="content">
        <c:if test="${classy.categoryList!=null && !classy.categoryList.isEmpty() }">
            <table class="table table-striped table-bordered table-hover table-condensed">
                <c:forEach items="${classy.categoryList}" var="category" varStatus="loopMap">
                    <tr>
                        <td>
                                ${category.idcategory}
                        </td>
                        <td>
                                ${category.categoryName}
                        </td>
                        <td><a
                                href='<c:url value="/classifiedads/categories/${category.idcategory}" />'
                                type="button"
                                class="btn btn-success btn-xs glyphicon glyphicon-new-window">
                            <fmt:message key="read"/>
                        </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</c:if>
