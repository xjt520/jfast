<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="keywords" content="" />
    <meta name="description" content="">
    <title></title>
    <jsp:include page="/WEB-INF/pages/common/include_all.jsp"></jsp:include>
</head>
<style>
    .loginbg{background:#3281c6 url(${pageContext.request.contextPath }/res/images/loginBg.jpg); }
    .login_area{ position:relative; width:1000px; margin:0 auto;  z-index:100;}
    .login_area .title{ font-size:60px; line-height:120px; color:#fff; text-align:center; margin-top:40px;}
    .loginbox{ background: url("${pageContext.request.contextPath }/res/images/loginboxBg.png") no-repeat; width:692px; height:465px; margin-top:20px; margin: 40px 154px 0 154px;}
    .loginbox ul{ padding:60px 0 0 168px; font-size:16px; color:#666666;}
    .loginbox ul li{ margin-top:24px; clear:both; overflow:hidden;}
    .loginbox ul li p{ font-size:12px; line-height:24px;}
    .loginbox ul span{ width:160px; line-height:40px; padding-right:25px; text-align:right; display:inline-block; float:left; margin-top:4px;}
    .loginbox ul input[type=text],.loginbox ul input[type=password]{height:36px; line-height:36px; padding:0 10px; border:1px solid #2f3a4c; display:inline-block;float:left; box-shadow:0 -1px 2px #ccc; margin-top:4px; border-radius:4px;}
    .loginbox .username,.loginbox .password{ width:330px;}
    .loginbox .rememberpw input{ position: absolute; left: -3333333333333px; -moz-appearance: none; -webkit-appearance: none; appearance: none; outline:none;  opacity: 0;}
    .loginbox input[type=checkbox]+label{position:relative; top:4px;  background: url("${pageContext.request.contextPath }/res/images/login_select.png") no-repeat; display: inline-block; width: 21px; height: 21px; margin-right:10px;}
    .loginbox input[type=checkbox]:checked+label{background: url("${pageContext.request.contextPath }/res/images/login_selected.png")no-repeat;}
    .loginbox .rememberpw{line-height:22px; width:165px;}
    .loginbox .log_butt{ float:left;}
    .loginbox .log_butt .btn{ background:#2f3a4c; width:350px; height:34px; line-height:34px; text-align:center; color:#Fff; border:1px solid #2f3a4c; display:block; border-radius:4px; font-size:12px;}
    .loginbox .log_butt .btn:hover{}
    a{text-decoration: none;}
</style>
<script type="text/javascript">

    function chkFrm(){
        var logonName = $("#username").val();
        var passwd = $("#password").val();
        var verfyCode = $("#verfyCode").val();
        if(logonName==''){
            alert("登录名不能为空！");
            return ;
        }
        if(passwd==''){
            alert("登录密码不能为空!");
            return ;
        }
        if(verfyCode==''){
            alert("验证码不能为空!");
            return ;
        }
        else{
            $("#loginForm").submit();
        }
    }
    function changeImg(){
        var mySpan = document.getElementById("mySpan");
        var timenow = new Date().getTime();
        mySpan.innerHTML='<img align="middle" src="login/verification?'+timenow+'" onclick="changeImg()" width="84" height="35" border="0"/>';
    }
</script>
<body class="loginbg">
<div class="login_area">
    <h1 class="title">欢迎登录后台系统</h1>
    <div class="loginbox">
        <form id="loginForm" action="${pageContext.request.contextPath }/login/doLogin" method="post">
            <ul>
                <li>
                    <p>请输入用户名密码登录
                    </p>
                    <input type="text" id="username" name="loginName" value="${loginName}" autocomplete="off" class="username" placeholder="用户名"></li>
                <li><input type="password" id="password" name="password" autocomplete="off" value="${password}" class="password" placeholder="密码"></li>
                <%--<li><input type="text" id="verfyCode" name="verfyCode" class="ident_code"><div class="codeimg" id="mySpan"><img id ="img" src="login/verification" onclick="changeImg()" width="84" height="35"></div></li>--%>
                <%--<li class="m10">
                    <div class="rememberpw"><input name="" type="checkbox" value="" id="check_pass"><label for="check_pass" ></label>记住密码？</div>
                </li>--%>
                <p><span style="color: red">${login_error }</span></p>
                <li><div class="log_butt"><a href="#" onclick="chkFrm();"  class="btn">登 录</a></div></li>
            </ul>
        </form>
    </div>



</div>
<div class="bottomBg"></div>
</body>
</html>
