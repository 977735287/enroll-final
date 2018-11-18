<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<div class="login-logo">
			<b>找回密码</b>
		</div>

		<div v-show="isSelectWay" class="login-box-body"
			style="background-color: rgba(0, 0, 0, 0);">
			<div class="row-fluid">
				<div class="span12">
					<button class="btn btn-block btn-info" type="button"
						@click="emailFind">邮箱方式找回</button>
					<button class="btn btn-block btn-info" type="button"
						@click="phoneFind">手机方式找回</button>
				</div>
			</div>

		</div>

		<div v-show="isCheckUsername" class="login-box-body"
			style="background-color: rgba(0, 0, 0, 0.1);">
			<div class="form-group has-feedback" style="color: #000000;">请输入要找回密码的用户名：</div>
			<div class="form-group has-feedback">
				<input id="username" type="text" class="form-control"
					v-model="username" placeholder="用户名" onfocus="onFocus();">
			</div>
			<div v-show="usernameError" class="form-group has-feedback"
				style="color: #F00;font-size: 100%; margin-top: -10px">*{{errorUsernameMsg}}
			</div>
			<div class="row">
				<div class="col-xs-8" style="margin-top:6px;font-size:9px">
					<a href="javascript:toPhoneFind();">通过手机找回&nbsp;&nbsp;》</a>
				</div>
				<div class="col-xs-4">
					<button type="button" class="btn btn-primary btn-block btn-flat"
						@click="checkUsername">下一步</button>
				</div>
			</div>
		</div>

		<div v-show="isCheckEmail" class="login-box-body"
			style="background-color: rgba(0, 0, 0, 0.1);">
			<div class="form-group has-feedback" style="color: #000000;">请输入您的电子邮箱：</div>
			<div class="form-group has-feedback">
				<input id="email" type="text" class="form-control" v-model="email"
					placeholder="电子邮箱" onfocus="onFocus();">
			</div>
			<div v-show="emailError" class="form-group has-feedback"
				style="color: #F00;font-size: 100%; margin-top: -10px">*{{errorEmailMsg}}
			</div>
			<div class="row">
				<div class="col-xs-4" style="margin-top:6px;font-size:9px">
					<a href="javascript:toPhoneFind();">通过手机找回&nbsp;&nbsp;》</a>
				</div>
				<div class="col-xs-4">
					<button type="button" class="btn btn-primary btn-block btn-flat"
						@click="back">上一步</button>
				</div>
				<div class="col-xs-4">
					<button type="button" class="btn btn-primary btn-block btn-flat"
						@click="checkEmail">下一步</button>
				</div>
			</div>
		</div>

		<div v-show="isConfirm" class="login-box-body"
			style="background-color: rgba(0, 0, 0, 0.1);">
			<div class="form-group has-feedback"
				style="text-align: center;color: #000000;">重置密码</div>
			<div class="form-group has-feedback"
				style="text-align: center;color: #000000;">用户名：{{username}}</div>
			<div class="form-group has-feedback"
				style="text-align: center;color: #000000;">电子邮箱：{{email}}</div>

			<div class="row">
				<div class="col-xs-4" style="margin-top : 6px;font-size:11"></div>
				<div class="col-xs-4">
					<button type="button" class="btn btn-primary btn-block btn-flat"
						@click="back">上一步</button>
				</div>
				<div class="col-xs-4">
					<button type="button" class="btn btn-primary btn-block btn-flat"
						@click="confirmReset">确认重置</button>
				</div>
			</div>
		</div>

		<div v-show="isCheckPhoneCode" class="login-box-body"
			style="background-color: rgba(0, 0, 0, 0.1);">
			<div class="form-group has-feedback" style="color: #000000;">请输入手机号码：</div>
			<div class="form-group">
				<input id="phone" type="text" class="form-control" v-model="phone"
					placeholder="手机号码" onfocus="onFocus();" onblur="checkPhoneNum()">
			</div>
			<div v-show="phoneError" class="form-group has-feedback"
				style="color: #F00;font-size: 100%; margin-top: -10px">*{{errorPhoneMsg}}
			</div>
			<div class="form-group has-feedback" style="color: #000000;">请输入验证码：</div>
			<div class="input-group">
				<input id="code" type="text" class="form-control" v-model="code"
					placeholder="手机验证码">
				<button id="getCode" class="input-group-addon" type="button"
					onclick="sendCode(this)">{{clock}}</button>
			</div>
			<div class="row">
				<div class="col-xs-8" style="margin-top:6px;font-size:9px">
					<a href="javascript:toEmailFind();">通过邮箱找回&nbsp;&nbsp;》</a>
				</div>
				<div class="col-xs-4">
					<button type="button" class="btn btn-primary btn-block btn-flat"
						@click="checkCode">下一步</button>
				</div>
			</div>
		</div>

		<div v-show="isUpdatePW" class="login-box-body"
			style="background-color: rgba(0, 0, 0, 0.1);">
			<div class="form-group">
				<div class="col-sm-4 control-label-r" style="color: #000000;">输入新密码</div>
				<div class="form-group has-feedback">
					<input id="newPassword" type="password" class="form-control-input"
						v-model="newPassword" placeholder="新密码">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-4 control-label-r" style="color: #000000;">确认新密码</div>
				<div class="form-group has-feedback">
					<input id="confirmPassword" type="password"
						class="form-control-input" v-model="confirmPassword"
						placeholder="确认密码" onBlur="onBlur();">
				</div>
			</div>
			<div class="form-group">
				<button class="btn btn-block btn-primary" type="button"
					@click="updatePW">确认</button>
			</div>
		</div>

		<div v-show="isOk" class="login-box-body"
			style="background-color: rgba(0, 0, 0, 0.1);">
			<div class="form-group has-feedback"
				style="text-align: center;color: #000000;">{{okMsg}}</div>
			<div class="form-group has-feedback" style="text-align: center;">
				<div class="closeWinsow">
					<a
						href="javascript:window.opener=null;window.open('','_self');window.close();">关闭</a>
				</div>
			</div>
		</div>

	</div>
	<script src="login/js/jquery.js"></script>
	<script src="login/js/verificationNumbers.js"></script>
	<script src="login/js/Particleground.js"></script>
	<script src="static/js/plugins/layer/layer.js"></script>
	<script src="static/js/jquery.jqGrid.min.js"></script>
	<script src="static/js/vue.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/app.js"></script>

	<script type="text/javascript" src="js/common.js"></script>
	<script src="js/resetPassword.js"></script>

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

</body>
</html>