<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <title><fmt:message key="title.small"/></title>
    <fmt:setLocale value="pl_PL"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="author" content=""/>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link href="<c:url value="/webjars/bootstrap/3.1.0/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="webjars/bootstrap/3.1.0/css/bootstrap-theme.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/3.1.0/css/bootstrap-theme.min.css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/dashboard.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/justified.css" rel="stylesheet"/>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-1">
        </div>
        <div class="col-md-10">
            <div class="page-header">
                <h4>
                    <tiles:insertAttribute name="header"/>
                </h4>
               <div class="row">
                    <h5>
                        
                    </h5>
                </div> 
            </div>  
            <div class="row">
                <div class="col-md-2">
                    <tiles:insertAttribute name="menu"/>
                </div>
                <div class="col-md-10">
                    <tiles:insertAttribute name="content"/>
                </div>
                <div class="col-md-1">
                </div>
            </div>
            <div class="panel-footer">
                <tiles:insertAttribute name="footer"/>
            </div>
        </div>
        <div class="col-md-1">
        </div>
    </div>
</div>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="webjars/bootstrap/3.1.0/js/bootstrap.js"
        type="text/javascript"></script>

<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/3.1.0/js/bootstrap.js"
        type="text/javascript"></script>

</body>
</html>