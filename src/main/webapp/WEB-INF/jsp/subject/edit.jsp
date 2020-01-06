<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit ${subject.title}-${appName}</title>
    <%@include file="../common/head.jspf" %>
</head>
<body>
<%@include file="../common/topNav.jspf" %>
<div class="container">
    <div class="margin-top-50"></div>

    <form class="form-horizontal" id="form_register" role="form" action="/subject/${subject.id}/update" method="POST">
        <div class="form-group">
            <label class="col-sm-3 control-label">Director</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" name="directors"
                       placeholder="Please enter director" value="${subject.directors}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">Starring</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" name="casts"
                       placeholder="Please enter stars" value="${subject.casts}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">Writer</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" name="writers"
                       placeholder="Please enter writer" value="${subject.writers}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">Genre</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" name="genres"
                       placeholder="Please enter genre" value="${subject.genres}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">Area</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" name="countries"
                       placeholder="Please enter area" value="${subject.countries}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">Language</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" name="languages"
                       placeholder="Please enter language" value="${subject.languages}"/>
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-3 control-label">Length</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" name="durations"
                       placeholder="Please enter length" value="${subject.durations}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">Other Title</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" name="originalTitle"
                       placeholder="Please enter other title" value="${subject.originalTitle}"/>
            </div>
        </div>

        <div class="form-group margin-top-40">
            <div class="col-sm-offset-3 col-sm-10">
                <button type="submit" class="btn btn-info">Submit
                </button>
            </div>
        </div>


    </form>
    <div class="margin-bottom-100"></div>
</div>


<%@include file="../common/footer.jspf" %>


</body>
</html>
