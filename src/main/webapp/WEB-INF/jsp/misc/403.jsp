<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>No access - ${appName}</title>
    <%@include file="../common/head.jspf" %>
</head>
<body>
<%@include file="../common/topNav.jspf" %>
<div class="margin-top-50"></div>
<div class="container">
    <div class="jumbotron">
        <h1><span class="glyphicon glyphicon-fire red"></span> ${appName}</h1>

        <p class="lead">Sorry you don't have the right to access<em><span id="display-domain"></span></em>.
        </p>
        <a href="/" class="btn btn-default btn-lg text-center"><span
                class="green">Back Home Page</span></a>
    </div>
</div>
<div class="margin-bottom-100"></div>
<%@include file="../common/footer.jspf" %>
</body>
</html>