<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>


<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER', 'ROLE_OWNER')">
<form:form modelAttribute="placeDto" method="POST" commandName="placeDto">
	<form:errors path="*" cssClass="error" />
	<table class="table table-striped table-bordered table-hover table-condensed">
		<tr>		
			<th><fmt:message key="id" /></th>
			<th><fmt:message key="type" /></th>
			<th><fmt:message key="registryNumber" /></th>						
			<th><fmt:message key="regdate" /></th>	
			<th><fmt:message key="amount" /></th>		
		</tr>
		<c:forEach items="${placeDto.mediaCounters}" var="counter" varStatus="status">
			<tr>
			<form:hidden path="mediaCounters[${status.index}].idmediaCounter" name="mediaCounters[${status.index}].idmediaCounter" value="${counter.idmediaCounter}" />	
			<form:hidden path="mediaCounters[${status.index}].mediaDto.idmedia" name="mediaCounters[${status.index}].mediaDto.idmedia" value="${counter.mediaDto.idmedia}" />	
				<th>${counter.idmediaCounter}</th>
				<td> <fmt:message key="${counter.mediaType}"/></td>
				<td>
				<input class="form-control" placeholder="<fmt:message key="registryNumber" />"
				name="mediaCounters[${status.index}].registryNumber" type="text" 
				value="${counter.registryNumber}" />
				</td>
				<td>${counter.dateRegistered}</td>
				<td>
				<input class="form-control" placeholder="<fmt:message key="amount" />"
				name="mediaCounters[${status.index}].totalAmount" type="text" 
				value="${counter.totalAmount}" />
				</td>
			</tr>
		</c:forEach>
	</table>
			<button type="submit" class="btn btn-default"><fmt:message key="submit"/></button>
</form:form>
</security:authorize>