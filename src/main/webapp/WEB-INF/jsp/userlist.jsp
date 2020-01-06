<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
    <%@include file="common/head.jspf" %>
    <link rel="stylesheet" type="text/css" href="/assets/css/pages/user.css"/>
</head>
<body>
<%@include file="common/topNav.jspf" %>
<div id="wrapper">
    <div class="margin-top-40"></div>
    <div id="content">
        <h1>User List</h1>
        <div class="margin-top-20" style="height: 25px;">

            <a  style="float: left;color: #2a6496"  href="/register" >+Add</a>

        </div>

        <div class="all">


                <c:choose>
                    <c:when test="${pageInfo.totalRows > 0}">
                        <c:forEach items="${pageInfo.resultList}" var="comment">
                            <div class="article">
                                <div class="mod-bd" id="coments">
                                    <div class="col-sm-9 content">
                                        <div class="comment-item">
                                            <div class="comment">
                                                <div class="list-title">
                                                    <div class="pull-left blue">${comment.userName}
                                                    </div>
                                                    <div class="pull-right">
                                                        <div class="delete"><mv:securityTag userId="${comment.id}">
                                                            &nbsp;&nbsp;&nbsp;
                                                            <button type="button"
                                                                    class="close  btn-danger delete_comment"
                                                                    data-dismiss="alert" value="${comment.id}">×
                                                            </button>
                                                        </mv:securityTag>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="title2">
                                                    <div class="pull-left">${comment.userName}&nbsp;&nbsp;${comment.name}&nbsp;&nbsp;${comment.phone}&nbsp;&nbsp;${comment.email}&nbsp;&nbsp;${comment.aihao}</div>
                                                    <div class="pull-left">
                                                        <a href="/editUser?id=${comment.id}" class="blue">&nbsp;&nbsp;Edit</a>
                                                        </a>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        No User Data yet
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="clearfix"></div>

        <div class="margin-left-30 margin-top-30">
            <ul id="paginator"></ul>
        </div>
    </div>
</div>


<%@include file="common/footer.jspf" %>


</body>
<script type="text/javascript">
    seajs.use("pages/delete-user.js");
    seajs.use("pages/user", function (comments) {
        comments.init(${pageInfo.pageNo}, ${pageInfo.totalPages});
    });


</script>
</html>
