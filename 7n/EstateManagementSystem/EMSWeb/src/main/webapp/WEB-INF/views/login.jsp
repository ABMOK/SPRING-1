<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>


<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-4">
                </div>
                <div class="col-md-4">
                    <form class="form-signin" id="form" action='/index' method="POST">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">
                                    <h6 class="form-signin-heading"><fmt:message key="login"/></h6>
                                </h3>
                            </div>
                            <div class="panel-body">
                                <h1>
                                <label for="inputEmail" class="sr-only"><fmt:message key="mail"/></label>
                                </h1>
                                <input type="email" id="inputEmail" name="mail" class="form-control"
                                       placeholder="<fmt:message key="mail"/>" required autofocus>
                                <h1>
                                <label for="inputPassword" class="sr-only"><fmt:message key="password"/></label>
                                </h1>
                                <input type="password" id="inputPassword" name="password" class="form-control"
                                       placeholder="<fmt:message key="password"/>" required>
                            </div>
                            <div class="panel-footer">
                                <div>
                                    <button class="btn btn-xs btn-primary btn-block" type="submit"><fmt:message
                                            key="logmein"/></button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-1">
                </div>
                <div class="col-md-4">
                </div>
            </div>
        </div>
    </div>
</div>
</body>

