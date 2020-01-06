<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Information - ${appName}</title>
    <%@include file="common/head.jspf" %>
</head>
<body>
<%@include file="common/topNav.jspf" %>
<div class="container">
    <div class="margin-top-100"></div>

    <form class="form-horizontal" id="form_password" role="form" action="/editUser" method="POST">
        <input type="hidden" id="id" name="id" value="${user.id}">
        <c:if test="${not empty error}">
            <div class="form-group">
                <div class="alert alert-danger"
                     role="alert">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                        ${error}
                </div>
            </div>
        </c:if>


        <div class="form-group">
            <label class="col-sm-3 control-label">Name</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" id="txt_old_password" name="name" value="${user.name}"
                       placeholder=""/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">Email</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" id="txt_new_password" name="email" value="${user.email}"
                       placeholder=""/>
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-3 control-label">Phone</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" id="txt_passwordConfirm" name="phone" value="${user.phone}"
                       placeholder=""/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">Interest Movie Type</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" id="txt_passwordConfirm2" name="aihao" value="${user.aihao}"
                       placeholder="Like: Love/Superhero/Crime/Animation"/>
            </div>
        </div>



        <div class="form-group margin-top-40">
            <div class="col-sm-offset-3 col-sm-10">
                <button type="submit" class="btn btn-info" id="password-submit">Submit
                </button>
                &nbsp;
                <button type="reset" class="btn btn-warning">Reset</button>
            </div>
        </div>


    </form>
    <div class="margin-bottom-100"></div>
</div>


<%@include file="common/footer.jspf" %>

<script type="text/javascript">
//    seajs.use("pages/password");
</script>

</body>
</html>
