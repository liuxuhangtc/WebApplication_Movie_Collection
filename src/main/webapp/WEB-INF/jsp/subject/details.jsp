<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${subject.title}-${appName}</title>
    <%@include file="../common/head.jspf" %>
    <link rel="stylesheet" type="text/css" href="/assets/css/pages/details.css"/>
</head>
<body>
<%@include file="../common/topNav.jspf" %>
<div class="main-part">
    <div class="container">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div class="part1">
                <div class="margin-top-10"></div>
                <div class="space">
                    <div class="pull-left">
                        <img src="${subject.image}">

                    </div>
                    <div class="pull-left margin-left-30">
                        <div class="film-name">${subject.title}(${subject.year})</div>
                        <div class="margin-top-30"></div>
                        <table class="table table-condensed table-striped table-bordered" style="font-size:12px;">
                            <tbody>
                            <tr>
                                <td class="span2"><span class="x-m-label">Director</span></td>
                                <td>${subject.directors}</td>
                            </tr>
                            <tr>
                                <td class="span2"><span class="x-m-label">Starring</span></td>
                                <td>${subject.casts}</td>
                            </tr>
                            <tr>
                                <td class="span2"><span class="x-m-label">Writer</span></td>
                                <td>${subject.writers}</td>
                            </tr>
                            <tr>
                                <td class="span2">
                                    <span class="x-m-label">Genre</span>
                                </td>
                                <td>${subject.genres}</td>
                            </tr>
                            <tr>
                                <td class="span2"><span class="x-m-label">Area</span></td>
                                <td>${subject.countries}</td>
                            </tr>
                            <tr>
                                <td class="span2"><span class="x-m-label">Language</span></td>
                                <td>${subject.languages}</td>
                            </tr>
                            <tr>
                                <td class="span2"><span class="x-m-label">Released</span></td>
                                <td><fmt:formatDate value="${subject.pubDate}" pattern="yyyy-MM-dd"/></td>
                            </tr>
                            <tr>
                                <td class="span2"><span class="x-m-label">Length</span></td>
                                <td>${subject.durations}</td>
                            </tr>
                            <tr>
                                <td class="span2"><span class="x-m-label">Other title</span></td>
                                <td>${subject.originalTitle}</td>
                            </tr>
                            <tr class="x-m-rating">
                                <td class="span2">
                                    <span class="x-m-label">Score</span></td>
                                <td><span class="badge" style="color: orange; font-weight: bold;"><fmt:formatNumber
                                        value="${subject.rating}" pattern="#.##" minFractionDigits="2"/></span></td>
                            </tr>
                            <c:if test="${SessionUtils.user.admin}">
                                <tr>
                                    <td class="span2"><span class="x-m-label">Operate</span></td>
                                    <td><a class="btn btn-danger" href="/subject/${subject.id}/delete">Delete</a>&nbsp;&nbsp;
                                        <a class="btn btn-info" href="/subject/${subject.id}/edit">Edit</a></td>
                                </tr>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="margin-top-10"></div>
            <div class="part2">
                <div class="descri">
                    <div class="descri-title">Synopsis</div>
                    <div class="margin-top-20"></div>
                    <div class="descri-content">
                        ${subject.imageurl}
                    </div>
                </div>
            </div>
            <div class="margin-top-10"></div>
            <div class="part3">
                <div class="comments">
                    <div class="comment-title">Comment</div>
                    <div class="margin-top-20"></div>
                    <div class="comment-content">
                        <div class="short-comment">
                            <div class="pull-left"><span class="green">${subject.title}'s comment......</span><a
                                    href="/subject/${id}/comments"><span
                                    class="blue">（Total count: ${subject.commentCount}）</span></a>
                            </div>
                            <div class="pull-right red"><a data-toggle="modal" data-target="#comment">Review</a></div>
                            <div class=" clearfix">
                            </div>
                        </div>
                        <div class="margin-top-10"></div>
                        <c:if test="${not empty error}">
                            <div class="form-group">
                                <div class="alert alert-danger"
                                     role="alert">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                        ${error}
                                </div>
                            </div>
                        </c:if>
                        <c:choose>
                            <c:when test="${subject.commentCount > 0}">
                                <c:forEach items="${comments}" var="comment">
                                    <div class="comment-list">
                                        <div class="list-title">
                                            <div class="pull-left"><a class="blue"
                                                                      href="/user/${comment.userId}">${appUtils.findUserNameById(comment.userId)}</a>
                                            </div>
                                            <div class="pull-left allstar${comment.rating}"></div>
                                            <div class="pull-left gray">${comment.submitDate}
                                                <mv:securityTag userId="${comment.userId}">
                                                    &nbsp;&nbsp;&nbsp;
                                                    <button type="button" class="close  btn-danger delete_comment"
                                                            data-dismiss="alert" value="${comment.id}">×
                                                    </button>
                                                </mv:securityTag>
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="list-content">
                                                ${comment.content}
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                No comment yet
                            </c:otherwise>
                        </c:choose>
                        <div class="margin-top-20"></div>
                    </div>
                    <div class="col-md-1"></div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../common/footer.jspf" %>
<%@include file="comment.jspf" %>
<script type="text/javascript">
    seajs.use("pages/delete-comment");
</script>

</body>
</html>
