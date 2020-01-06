<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Movies -${appName}</title>
    <%@include file="../common/head.jspf" %>
    <link rel="stylesheet" type="text/css" href="/assets/css/pages/category-list.css"/>
</head>
<body>
<%@include file="../common/topNav.jspf" %>
<div class="container">
    <div class="subnav subnav-fixed">
        <ul class="nav nav-pills margin-left-30 margin-top-30">
            <li>
                <div>
                    <select class="form-control" id="year">
                        <option>Year(No limit)</option>
                        <option>2019</option>
                        <option>2018</option>
                        <option>2017</option>
                        <option>2016</option>
                        <option>2015</option>
                        <option>2014</option>
                        <option>2013</option>
                        <option>2012</option>
                        <option>2011</option>
                        <option>2010</option>
                        <option>2009</option>
                        <option>2008</option>
                        <option>2007</option>
                        <option>2006</option>
                        <option>2005</option>
                        <option>2004</option>
                        <option>2003</option>
                        <option>2002</option>
                        <option>2001</option>
                        <option>2000</option>
                        <option>1999</option>
                        <option>1998</option>
                        <option>1997</option>
                        <option>1996</option>
                        <option>1995</option>
                        <option>1994</option>
                        <option>1993</option>
                    </select>
                </div>

            </li>
            <li>
                <div>
                    <select class="form-control" id="place">
                        <option>Area(No limit)</option>
                        <option>US</option>
                        <option>UK</option>
                        <option>CHINA</option>
                    </select>
                </div>
            </li>
            <li>
                <div>
                    <select class="form-control" id="type">
                        <option>Genre(No limit)</option>
                        <option>Superhero</option>
                        <option>Animation</option>
                        <option>Crime</option>
                        <option>Love</option>
                    </select>
                </div>
            </li>
            <li>
                <div>
                    <select class="form-control" id="sort">
                        <option value="hot">Sort by hot</option>
                        <option value="rating">Sort by score</option>
                        <option value="date">Score by release date</option>
                    </select>
                </div>
            </li>
            <c:if test="${not empty key}">
                <li>
                    <div>
                        <input class="form-control" id="key" value="${key}" READONLY>
                    </div>
                </li>
            </c:if>
        </ul>
    </div>
    <div class="container">
        <ul id="film-list">
        </ul>
    </div>
    <div class="margin-top-50"></div>
    <div class="margin-left-30 margin-top-30">
        <ul id="paginator"></ul>
    </div>
</div>

<%@include file="../common/footer.jspf" %>


</body>

<script type="text/javascript">
    seajs.use("pages/category-list");
</script>
</html>
