<%@ page language="java" pageEncoding="UTF-8" isErrorPage="true" %>
<%@include file="../../../static/uniform.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>RMS登录</title>
    <link rel="stylesheet" href="<%=basePath%>/static/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<%=basePath%>/static/app/css/unicorn.grey.css" />
    <link rel="stylesheet" href="<%=basePath%>/static/app/css/unicorn.login.css"/>
</head>
<body>
<div id="background">

</div>
<!--logo-->
<div id="header">
    <div class="head-box">
        <h1 id="h1"></h1>
    </div>
</div>
<!--登录框-->
<div class="login">
    <div id="loginbox">
        <h1>登录</h1>
        <ul>
            <li>
                <p class="leftp">登录账号</p>
                <p class="logerror" id="account_p"></p>
                <input type="text" value="rms" placeholder="请输入账号..." id="login_name"/>
            </li>
            <li>
                <p class="leftp">登录密码</p>
                <p class="logerror" id="password_p"></p>
                <input type="password" value="craft" placeholder="请输入密码..." id="login_pwd" class="logpwd"/>
            </li>
            <li class="ppbtn">
                <a id="login_in">登录</a>
                <p id="login_p"></p>
            </li>
        </ul>
    </div>
</div>
<script src="<%=basePath%>/static/jquery/jquery-3.1.0.min.js"></script>
<script src="<%=basePath%>/static/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/static/app/js/unicorn.login.js"></script>
<script>
    $(function(){
        //提交事件
        $("#login_in").click(login);
        //按回车登录
        $("body").keydown(function(event){
            var code=event.keyCode;
            if(code==13){//回车
                login();//调用login函数
            }
        });
    });

    //登录函数
    function login(){
        //清空提示信息
        $("#account_p").empty();
        $("#password_p").empty();
        $("#login_pwd").empty();
        $('#login_p').empty();
        var uname=$("#login_name").val().trim();
        var password=$("#login_pwd").val().trim();
        var check=true;
        if(!uname || uname==''){
            check=false;
            $("#account_p").html("账户不能为空");

        }
        if(!password || password==''){
            check=false;
            $("#password_p").html("密码不能为空");

        }
        if(check){
            $.post('<%=basePath%>/sys/login', {
                userName: uname,
                password: password
            }, function (data) {
                if(data['success'] === 'true'){
                    window.location.href="<%=basePath%>/sys/user/index";
                }else {
                    $('#login_p').html(data['message']);
                }
            }, 'json');
        }
    }
</script>
</body>
</html>
