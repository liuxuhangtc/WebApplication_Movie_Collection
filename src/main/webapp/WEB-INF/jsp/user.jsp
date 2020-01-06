<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${user.userName}'s Collection-${appName}</title>
    <%@include file="common/head.jspf" %>
    <link rel="stylesheet" type="text/css" href="/assets/css/pages/user.css"/>
</head>
<body>
<%@include file="common/topNav.jspf" %>
<div id="wrapper">
    <div class="margin-top-40"></div>
    <div id="content">
        <h1>${user.userName}'s collection（${commentCount}）</h1>

        <div class="margin-top-20"></div>

        <div class="all">
            <div class="col-md-8">
                <c:choose>
                    <c:when test="${commentCount > 0}">
                        <c:forEach items="${pageInfo.resultList}" var="comment">
                            <c:set var="subject" scope="page" value="${appUtils.findSubjectById(comment.subjectId)}"/>
                            <div class="article">
                                <div class="mod-bd" id="coments">
                                    <div class="col-sm-3 pic">
                                        <img width="120px" height="170px"
                                             src="${subject.image}">
                                    </div>
                                    <div class="col-sm-9 content">
                                        <div class="comment-item">
                                            <div class="comment">
                                                <div class="list-title">
                                                    <div class="pull-left blue">${comment.submitDate}
                                                    </div>
                                                    <div class="pull-right">
                                                        <div class="delete"><mv:securityTag userId="${comment.userId}">
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
                                                    <div class="pull-left">${user.userName}&nbsp;&nbsp;Comment:<a
                                                            href="/subject/${subject.id}" class="blue">《${subject.title}》</a>
                                                    </div>
                                                    <div class="pull-left allstar10 rating"></div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="list-content" id="wrap">
                                                        ${comment.content}
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
                        No comment yet
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="col-md-4 aside">
                <div class="margin-top-10"></div>
                <p class="pl">Welcome to my review collection......</p>

                <div class="margin-top-30"></div>
                <p class="p2">>&nbsp;<a class="blue" name="">${user.userName}'s Movie Home</a></p>

                <div class="margin-top-30"></div>
                <div class="decri">
                    <p>This is my collection of all movies, you can also express different opinions~</p>

                    <div class="margin-top-10"></div>
                    <p>Welcome again~~</p>
                </div>
                <div class="margin-top-30"></div>

                <c:if test="${SessionUtils.user.admin}">
                    <a href="/user/${user.id}/setAdmin">
                        <c:choose>
                            <c:when test="${user.admin}">
                                <button type="button" class="btn btn-danger">Cancel Administrator</button>
                            </c:when>
                            <c:otherwise>
                                <button type="button" class="btn btn-success">Set to Administrator</button>
                            </c:otherwise>
                        </c:choose>
                    </a>
                    <a href="/user/list">
                        <button type="button" class="btn btn-info">User List</button>
                    </a>
                </c:if>
                <c:if test="${!SessionUtils.user.admin}">
<%--                     <c:if test="${SessionUtils.user.id eq user.id}"> --%>
<!--                         <a href="/password"> -->
<!--                             <button type="button" class="btn btn-info">Edit Password</button> -->
<!--                         </a> -->
<%--                     </c:if> --%>
                    <c:if test="${SessionUtils.user.id eq user.id}">
                        <a href="/editUser?id=${SessionUtils.user.id}">
                            <button type="button" class="btn btn-info">Edit Info</button>
                        </a>
                    </c:if>
                </c:if>

            </div>
            <div class="clearfix"></div>
        </div>

        <div class="margin-left-30 margin-top-30">
            <ul id="paginator"></ul>
        </div>
    </div>
</div>


<%@include file="common/footer.jspf" %>


</body>
<script type="text/javascript">
    seajs.use("pages/delete-comment");
    seajs.use("pages/comments", function (comments) {
        comments.init(${pageInfo.pageNo}, ${pageInfo.totalPages});
    });


</script>
</html>
