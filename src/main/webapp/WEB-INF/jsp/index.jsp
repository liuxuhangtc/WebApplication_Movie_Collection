<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Index - ${appName}</title>
    <%@include file="common/head.jspf" %>
    <link rel="stylesheet" type="text/css" href="/assets/css/pages/index.css"/>
</head>
<body>
<%@include file="common/topNav.jspf" %>
<div class="container">
    <div class="margin-top-30"></div>
    <div class="title">${subjects.size()} Recommend Movies</div>
    <div class="margin-top-30"></div>
    <div class="timeLayout">

        <ul class="time-list">
            <c:forEach items="${subjects}" var="subject" varStatus="status">
                <li class="time-item <c:choose><c:when test="${status.count%2==0}">right</c:when><c:otherwise>left</c:otherwise></c:choose>"
                        ><span class="timedot">●</span>

                    <div class="item-inner margin-left-100">
                        <img width="254" height="317"
                             src="${subject.image}">

                        <div class="margin-top-10"></div>
                        <div class="filmInfo">
                            <h3><a class="mdbColor" href="/subject/${subject.id}"
                                   target="_blank"><c:if test="${not empty  subject.pubDate}">
                                <fmt:formatDate value="${subject.pubDate}" pattern="yyyy-MM-dd"/> Released
                            </c:if>
                                《${subject.title}》</a></h3>

                            <p class="score"><label>Score:</label>
                                <span class="badge"
                                      style="width:40px;color: orange; font-weight: bold;"><fmt:formatNumber
                                        value="${subject.rating}" pattern="#.##"
                                        minFractionDigits="2"/></span></p>

                            <p class="direct">
                                <label>Director:</label><span>${subject.directors}</span>
                            </p>

                            <p>
                                <label>Starring:</label><span>${subject.casts}</span>
                            </p>

                            <p>
                                <label>Genre:</label><span>${subject.genres}</span>
                            </p>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <div class="midLine"></div>
    </div>

</div>

<%@include file="common/footer.jspf" %>
</body>
</html>