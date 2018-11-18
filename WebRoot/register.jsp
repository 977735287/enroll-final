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
/* 	background-image: url(static/images/student/schoolBg.jpg);     */
/*     background-size:cover;   */
	overflow: hidden;
}

canvas {
	z-index: -1;
	position: absolute;
}
</style>
</head>
<body>
	<div class="login-box" id="eapp" v-cloak>
		<div class="login-logo" style="background-color: rgba(0, 0, 0, 0);">
			<b>注册</b>
			<!-- 			<div style="font-weight: normal;font-size: 16px;">注册</div> -->
		</div>
		<!-- /.login-logo -->
		<div class="register-box-body" style="background-color: rgba(0, 0, 0, 0.3);">
			<div v-if="error" class="alert alert-danger alert-dismissible">
				<h4 style="margin-bottom: 0px;">
					<i class="fa fa-exclamation-triangle"></i> {{errorMsg}}
				</h4>
			</div>
			<div class="form-group">
				<div class="col-sm-4 control-label-r" style="color: #000000;">*用 &nbsp;户&nbsp;名</div>
				<div class="form-group has-feedback">
					<input id="username" type="text" class="form-control-input"
						v-model="userInfo.username" placeholder="用户名"
						v-on:blur="checkUserName(userInfo.username)" v-on:focus="focus">
				</div>
			</div>
			<div class="form-group" v-show="nameExistError">
				<div class="col-sm-4"></div>
				<div style="color: #F00;font-size: 100%; margin-top: -10px">*{{nameExistErrorMsg}}
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-4 control-label-r" style="color: #000000;">*密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</div>
				<div class="form-group has-feedback">
					<input id="password" type="password" class="form-control-input"
						v-model="userInfo.password" placeholder="密码" onfocus="onFocus();">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-4 control-label-r" style="color: #000000;">*确认密码</div>
				<div class="form-group has-feedback">
					<input id="confirmPassword" type="password"
						class="form-control-input" v-model="confirmPassword"
						placeholder="确认密码" onfocus="onFocus();" onBlur="onBlur();">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-4 control-label-r" style="color: #000000;">手机号码</div>
				<div class="form-group has-feedback">
					<input id="phone" type="text" class="form-control-input"
						v-model="userInfo.mobile" placeholder="手机号码">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-4 control-label-r" style="color: #000000;">电子邮箱</div>
				<div class="form-group has-feedback">
					<input id="email" type="text" class="form-control-input"
						v-model="userInfo.email" placeholder="电子邮箱">
				</div>
			</div>


			<div class="row">
				<div class="col-xs-4">
					<div class="checkbox icheck"></div>
				</div>
				<!-- /.col -->
				<div class="col-xs-4" style="float : right;">
					<button type="button" class="btn btn-primary-b btn-block btn-flat"
						@click="back">返回</button>
				</div>
				<div class="col-xs-4" style="float : right;">
					<button type="button" class="btn btn-primary btn-block btn-flat"
						@click="register">注册</button>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.social-auth-links -->

		</div>
		<!-- /.login-box-body -->
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

<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/registerUI.js"></script>
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
