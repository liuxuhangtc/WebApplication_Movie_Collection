<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Password-${appName}</title>
    <%@include file="common/head.jspf" %>
</head>
<body>
<%@include file="common/topNav.jspf" %>
<div class="container">
    <div class="margin-top-100"></div>

    <form class="form-horizontal" id="form_password" role="form" action="/password" method="POST">

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
            <label class="col-sm-3 control-label">Old Password</label>

            <div class="col-sm-5">
                <input type="password" class="form-control" id="txt_old_password" name="oldPassword"
                       placeholder="Please enter old password"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">New Password</label>

            <div class="col-sm-5">
                <input type="password" class="form-control" id="txt_new_password" name="newPassword"
                       placeholder="Please enter new password"/>
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-3 control-label">Confirm Password</label>

            <div class="col-sm-5">
                <input type="password" class="form-control" id="txt_passwordConfirm"
                       placeholder="Please confirm password"/>
            </div>
        </div>


        <div class="form-group" style="display: none">
            <label for="txt_captcha" class="col-sm-3 control-label">Validation</label>

            <div class="col-sm-9">
                <div class="col-xs-4" style="margin-left: -15px;">
                    <input class="form-control code" type="text" placeholder="Please enter validation"
                           name="captcha" id="txt_captcha"/>
                </div>
                <div class="col-xs-4 margin-left-20">
                                            <span class="codeimg"><img class="captcha-img" src="/captcha.png"
                                                                       style="height: 40px;width:120px;cursor: pointer;"/></span>
                </div>

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
    seajs.use("pages/password");
</script>

</body>
</html>
