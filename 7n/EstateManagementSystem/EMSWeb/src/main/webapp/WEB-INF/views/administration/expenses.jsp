<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div id="content">
	<c:choose>
	<c:when test="${not empty commonExpenses}">
		<table
				class="table table-striped table-bordered table-hover table-condensed">
			<tr>
				<th><fmt:message key="id" /></th>
				<th><fmt:message key="exptype" /></th>
				<th><fmt:message key="estate" /></th>
				<th><fmt:message key="itsName" /></th>
				<th><fmt:message key="occurence" /></th>

				<th><fmt:message key="description" /></th>
				<th><fmt:message key="options" /></th>

			</tr>
			<c:forEach items="${commonExpenses}" var="expense">
				<tr>
					<td>${expense.idexpense}</td>
					<td><fmt:message key="${expense.type}" /></td>
					<td><fmt:message key="${expense.facility.type}" />, <fmt:message key="id" /> ${expense.facility.idfacility}</td>
					<td><fmt:message key="${expense.name}"/></td>
					<td>${expense.occurence.happeningOccurenceTypeValue}</td>

					<td>${expense.description}</td>
					<td>
						<a href='<c:url value="/finances/estates/${expense.facility.idfacility}/expenses/${expense.idexpense}" />'	type="button" class="btn btn-info btn-xs"><fmt:message key="edit" /></a>
						<security:authorize access="hasRole('ROLE_ADMIN')">
							<a href='<c:url value="/finances/estates/${expense.facility.idfacility}/expenses/${expense.idexpense}/delete" />'	type="button" class="btn btn-danger btn-xs"><fmt:message key="delete" /></a>
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