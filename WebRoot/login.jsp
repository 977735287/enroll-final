<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link href="favicon.ico" rel="Shortcut icon" type="image/x-icon">
<title>智能招生信息管理系统</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/font-awesome.min.css">
<link rel="stylesheet" href="static/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="static/css/all-skins.min.css">
<link rel="stylesheet" href="static/css/main.css">
<link rel="stylesheet" type="text/css" href="login/css/style.css" />
<style>
body {
	height: 100%;
	background: #16a085;
/* 	background-image: url(static/images/student/schoolBg.jpg); */
/* 	background-size: cover; */
	overflow: hidden;
}

canvas {
	z-index: -1;
	position: absolute;
}
</style>
</head>
<body>
	<div class="login-box" id="rrapp" v-cloak>
		<div class="login-logo" style="background-color: rgba(0, 0, 0, 0);">
			<b>智能招生信息管理系统</b>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body"
			style="background-color: rgba(0, 0, 0, 0.4);">
			<div v-show="!isTourist">
				<p class="login-box-msg">登录</p>
				<div v-if="error" class="alert alert-danger alert-dismissible">
					<h4 style="margin-bottom: 0px;">
						<i class="fa fa-exclamation-triangle"></i> {{errorMsg}}
					</h4>
				</div>
				<div class="form-group has-feedback">
					<input id="username" type="text" class="form-control"
						v-model="username" placeholder="用户名/手机号/邮箱" onfocus="onFocus();">
					<span class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div v-show="usernameError"
					style="color: #F00;font-size: 100%; margin-top: -10px">*{{errorUsernameMsg}}
				</div>
				<div class="form-group has-feedback" style="margin-top: 6px">
					<input id="password" type="password" class="form-control"
						v-model="password" placeholder="密码" onfocus="onFocus();">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div v-show="passwordError"
					style="color: #F00;font-size: 100%; margin-top: -10px">*{{errorPasswordMsg}}
				</div>
				<div class="form-group has-feedback" style="margin-top: 6px">
					<input type="text" class="form-control" v-model="captcha"
						@keyup.enter="login" placeholder="验证码"> <span
						class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<img alt="如果看不清楚，请单击图片刷新！" class="pointer" :src="src"
						@click="refreshCode"> &nbsp;&nbsp;&nbsp;&nbsp;<a
						href="javascript:;" @click="refreshCode" style="color: #0080ff;">点击刷新</a>
				</div>
				<div class="form-group has-feedback"
					style="font-size : 11px; color: #000000;">
					<a href="resetPassword.jsp" target="_blank">忘记密码</a>
				</div>
				<div class="row">
					<!-- 				<div class="col-xs-12" style="margin-top : 0px; font-size : 11px; color: #000000;"> -->
					<!-- 					还没有账号？点击<a href="register.jsp">注册</a> -->
					<!-- 					<a href="resetPassword.jsp" -->
					<!-- 						target="_blank" style="margin-left: 26px;">忘记密码</a> -->
					<!-- 				</div> -->
					<div class="col-xs-12" style="margin-top : 6px;">
						<button type="button" class="btn btn-primary btn-block btn-flat"
							@click="login">登录</button>
						<a href="register.jsp" class="btn btn-primary btn-block btn-flat"
							role="button">注册</a>
						<button type="button" class="btn btn-primary btn-block btn-flat"
							@click="touristVisit">游客访问</button>
					</div>
				</div>
			</div>
			<div v-show="isTourist">
				<div class="form-group has-feedback">
					<div class="control-label-r" style="color: #000000;">请留下您的手机号码：</div>
				</div>
				<div class="form-group has-feedback">
					<input id="touristPhone" type="text" class="form-control"
						v-model="touristPhone" placeholder="手机号码" onfocus=""> <span
						class="glyphicon glyphicon-phone-alt form-control-feedback"></span>
				</div>
				<div class="row">	
					<div class="col-xs-8"></div>			
					<div class="col-xs-4" style="margin-top : 6px;">
						<button type="button" class="btn btn-primary btn-block btn-flat"
							@click="touristVisitConfirm">确定</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="login/js/jquery.js"></script>
<script src="login/js/verificationNumbers.js"></script>
<script src="login/js/Particleground.js"></script>
<script src="static/js/plugins/layer/layer.js"></script>
<script src="static/js/jquery.jqGrid.min.js"></script>
<script src="static/js/vue.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/app.js"></script>

<script src="js/common.js"></script>
<script src="js/loginUI.js"></script>
<script>
	$(document).ready(function() {
		//粒子背景特效
		$('body').particleground({
			dotColor : '#5cbdaa',
			lineColor : '#5cbdaa'
		});
		//验证码
		createCode();
		//测试提交，对接程序删除即可
		$(".submit_btn").click(function() {
			location.href = "javascrpt:;" /*tpa=http://***index.html*/ ;
		});
	});
</script>
</html>
