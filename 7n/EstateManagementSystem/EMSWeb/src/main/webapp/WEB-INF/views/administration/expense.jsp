<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div id="content">

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4">
			</div>
			<div class="col-md-4">
				<form:form modelAttribute="expenseDto" method="POST" commandName="expenseDto">
					<form:hidden path="idexpense" name="idexpense" value="${expenseDto.idexpense}"/>
					<form:hidden path="payment.idpayment" name="payment.idpayment" value="${expenseDto.payment.idpayment}"/>
					<form:hidden path="mediaCounter.idmediaCounter" name="mediaCounter.idmediaCounter" value="${expenseDto.mediaCounter.idmediaCounter}"/>
					<form:hidden path="facility.idfacility" name="facility.idfacility" value="${expenseDto.facility.idfacility}"/>

					<div class="row">

							<div class="form-group">
								<label for="type"><fmt:message key="type" /></label>
								<form:select path="type" multiple="false" id="selectbasic" name="selectbasic" class="form-control">
									<c:forEach items="${happeningTypes}" var="happening">
										<option value="${happening}" label="<fmt:message key="${happening}" />" > </option>
									</c:forEach>
								</form:select>
							</div>
							<div class="form-group">
								<label for="name"><fmt:message key="itsName" /></label>
								<form:select path="name" multiple="false" id="selectbasic" name="selectbasic" class="form-control">
									<form:options items="${expenseTypes}" itemLabel="commonExpenseTypeName"/>
								</form:select>
							</div>
							<div class="form-group">
								<label for="occurence"><fmt:message key="occurence" /></label>
								<form:select path="occurence" multiple="false" id="selectbasic" name="selectbasic" class="form-control">
									<form:options items="${happeningOccurence}" itemLabel="happeningOccurenceTypeValue"/>

								</form:select>
							</div>
							<div class="form-group">
								<label for="amount"><fmt:message key="price" /></label>
								<input type="text" class="form-control" id="amount" name="amount" value="${expenseDto.amount}"  placeholder="<fmt:message key="price" />">
							</div>
							<div class="form-group">
								<label for="description"><fmt:message key="description" /></label>
								<input type="text" class="form-control" id="description" name="description" value="${expenseDto.description}" placeholder="<fmt:message key="description" />">
							</div>
							<div class="form-group">
								<label for="calculationPeriod"><fmt:message key="calculationPeriod" /></label>
								<input type="text" class="form-control" id="calculationPeriod" name="calculationPeriod" value="${expenseDto.calculationPeriod}"  placeholder="<fmt:message key="calculationPeriod" />">
							</div>

							<button type="submit" class="btn btn-default"  style="float: right;">Submit</button>

						<div class="col-lg-4">
						</div>
					</div>
				</form:form>
			</div>
			<div class="col-md-4">
			</div>
		</div>
	</div>
	

</div>