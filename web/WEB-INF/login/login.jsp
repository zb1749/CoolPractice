<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 2016/10/27
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="../../commons/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../commons/bootstrap/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" href="../../commons/css/demo.css"/>
    <link rel="stylesheet" href="../../commons/css/style.css"/>
    <link rel="stylesheet" href="../../commons/css/animate-custom.css"/>
    <script type="text/javascript" src="../../commons/jquerylib/jquery-3.1.0.js"></script>
    <script type="text/javascript" src="../../commons/bootstrap/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
    $(function () {
        //加载即刷新验证码
        verifyImgFlush();
        //登录提交
        $("#loginForm").submit(function () {
            var username = $("#username").val();
            var password = $("#password").val();
            var verifyCode = $("#verifyCode").val();
            var data = {username: username, password: password, verifyCode: verifyCode};
            var url = "/mvc/user/login";
            $.ajax({
                type: "POST",
                url: url,
                data: data,
                dataType: "json",
                success: function (result) {
                    if (result.ok) {
                        location.href = "/mvc";
                    } else {
                        $(".error").remove();
                        $("#loginForm").prepend("<div class='error' style='color: red'>" + result.msg + "</div>");
                        $("#verify").attr("src", "/login/verifyImgCodex?timestamp=" + new Date().getTime()); // 刷新验证码
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert('读取超时，请检查网络连接');
                }
            });
            return false;
        });

    });

    //验证码更新
    function verifyImgFlush () {
        $("#verify").attr("src", "/login/verifyImgCode?timestamp=" + new Date().getTime());
    }

//    $(function () {
//        $("#dd").popover();
//    });
</script>
<body>
<div class="container">
    <div id="container_demo">
        <div id="wrapper">
            <div id="login" class="animate form">
                <h1>支付登录测试</h1>

                <form id='loginForm' method="POST">
                    <p>
                        <label class="uname" data-icon="u"> 用户名 </label>
                        <input id="username" name="username" required="required" type="text"
                               placeholder="username or mail@mail.com">
                    </p>

                    <p>
                        <label class="youpasswd" data-icon="p"> 密码 </label>
                        <input id="password" name="password" required="required" type="password"
                               placeholder="eg. xx1234@@">
                    </p>

                    <p>
                        <label class="verification" data-icon="v"> 验证 </label>
                        <img id="verify" onclick="javascript:verifyImgFlush()" align="middle" title="看不清，请点我" style="cursor:hand;"/><br/>
                        <input type="verification" id="verifyCode" name="verifyCode" placeholder="验证码"
                               required="required">
                    </p>

                    <p class="login button">
                        <input type="submit" id="submitId" value="登录">
                    </p>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

</html>