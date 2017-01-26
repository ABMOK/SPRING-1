<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="panel-body">
	<div class="container-fluid">
		<div class="row">

			<div class="col-md-4">
			</div>
			<div class="col-md-4">
				<div>
					<h3>
						<fmt:message key="estate" />
					</h3>
				</div>

					<div class="panel-heading">
						<h4>
						<fmt:message key="medium" />
					</h4>
					</div>
					<form:form  modelAttribute="medium" method="POST" commandName="medium">
						<form:hidden path="idmedia" name="idmedia" value="${medium.idmedia}"/>
						<form:hidden path="facilityDto.idfacility" name="facilityDto.idfacility" value="${medium.facilityDto.idfacility}"/>
						<div class="panel-body">
							<div class="form-group">
								<label for="type"><fmt:message key="type" /></label>
								<form:select path="type" multiple="false"  id="selectbasic" name="selectbasic" class="form-control">
									<form:option value="${medium.type}" />
									<form:options items="${mediaTypes}" itemLabel="mediaTypeName"/>
								</form:select>
							</div>
							<div class="form-group">
								<label for="medianame"><fmt:message key="medianame" /></label>
								<input type="text" class="form-control" id="medianame" name="medianame" value="${medium.medianame}" placeholder="<fmt:message key="medianame" />">
							</div>
							<div class="form-group">
								<label for="price"><fmt:message key="price" /></label>
								<input type="text" class="form-control" id="price" name="price" value="${medium.price}" placeholder="<fmt:message key="price" />">
							</div>
							<div class="form-group">
								<label for="measureunit"><fmt:message key="measureunit" /></label>
								<input type="text" class="form-control" id="measureunit" name="measureunit" value="${medium.measureunit}"  placeholder="<fmt:message key="measureunit" />">
							</div>
						</div>

							<button type="submit" class="btn btn-default" style="float: right;"><fmt:message key="submit" /></button>

					</form:form>

			</div>
			<div class="col-md-4">
			</div>
		</div>
	</div>

							
	</div>



