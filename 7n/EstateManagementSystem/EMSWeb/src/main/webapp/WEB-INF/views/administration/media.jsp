<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<fmt:setLocale value="${user.locale}" />
<div id="content">
<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">	
    <div class="bs-glyphicons">
    <ul class="bs-glyphicons-list">
         <a href="media/medium">
          <li> 
          <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>          
          <span class="glyphicon glyphicon-tint" aria-hidden="true"></span>
          <span class="glyphicon-class"><fmt:message key="add" /></span>         
        </li> </a>
        </ul>
        </div>
</security:authorize>
<c:if test="${media!=null && !media.isEmpty() }">
	<table class="table table-striped table-bordered table-hover table-condensed">
		<tr>		
			<th><fmt:message key="id" /></th>
			<th><fmt:message key="itsName" /></th>
			<th><fmt:message key="type" /></th>
			<th><fmt:message key="price" /> / <fmt:message key="measureunit" /></th>			
			<th><fmt:message key="regdate" /></th>
			<th><fmt:message key="estate" /></th>
			<th><fmt:message key="estate" /> - <fmt:message key="type" /></th>
			<th><fmt:message key="options" /></th>
		</tr>
		<c:forEach items="${media}" var="medium">
			<tr>
				<th>${medium.idmedia}</th>
				<td><fmt:message key="${medium.type}" /></td>				
				<td>${medium.type}</td>				
				<td>
				<c:if test="${medium.price!='null'}">
				${medium.price} / ${medium.measureunit}
				</c:if>
				</td>													
				<td>${medium.dateregistered}</td>	
				<td>${medium.facilityDto.addressDto.cityCode} ${medium.facilityDto.addressDto.cityName},
					${medium.facilityDto.addressDto.streetName}, ${medium.facilityDto.addressDto.buildingNo} <c:if
						test="${medium.facilityDto.addressDto.officeNo !=null && medium.facilityDto.addressDto.officeNo.length()>0}">/${medium.facilityDto.addressDto.officeNo}</c:if>
				</td>
				<td><fmt:message key="${medium.facilityDto.type}" /></td>	
				<td>
				<a href='<c:url value="/finances/estates/${medium.facilityDto.idfacility}/media/${medium.idmedia}" />'	type="button" class="btn btn-info btn-xs"><fmt:message key="edit" /></a>
				<security:authorize access="hasRole('ROLE_ADMIN')">	
				<a href='<c:url value="/finances/estates/${medium.facilityDto.idfacility}/media/${medium.idmedia}/delete" />'	type="button" class="btn btn-danger btn-xs"><fmt:message key="delete" /></a>
				</security:authorize>
				</td>		
			</tr>
		</c:forEach>
	</table>
	</c:if>
</div>
