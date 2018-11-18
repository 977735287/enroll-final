/**
 *  登录
 */

var vm = new Vue({
	el : '#rrapp',
	data : {
		isTourist : false,
		touristPhone : '',
		username : '',
		password : '',
		captcha : '',
		error : false,
		usernameError : false,
		passwordError : false,
		errorMsg : '',
		errorUsernameMsg : '',
		errorPasswordMsg : '',
		src : baseURL + 'captcha.do'
	},
	beforeCreate : function() {
		if (self != top) {
			top.location.href = self.location.href;
		}
	},
	methods : {
		refreshCode : function() {
			this.src = baseURL + "captcha.do?t=" + $.now();
		},
		login : function() {
			if (vm.username == "") {
				vm.errorUsernameMsg = "请输入账号";
				vm.usernameError = true;
			} else if (vm.password == "") {
				vm.errorPasswordMsg = "请输入密码";
				vm.passwordError = true;
			} else {
				var data = "username=" + vm.username + "&password=" + vm.password + "&captcha=" + vm.captcha;
				$.ajax({
					type : "POST",
					url : baseURL + "/sys/login.do",
					data : data,
					dataType : "json",
					success : function(r) {
						if (r.code == 0) { //登录成功
							localStorage.setItem("token", r.token);
							parent.location.href = "index.jsp";
						} else {
							vm.error = true;
							vm.errorMsg = r.msg;
							vm.captcha = "";
							vm.refreshCode();
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown){
						alert("error!");
					}
				});
			}
		},
		touristVisit : function(){
			vm.isTourist = true;
		},
		touristVisitConfirm : function(){
			checkPhoneNum();
		}
	}
});

$(document).ready(
	function() {
		$("#username").keydown(function(event) {
			keyLogin(event);
		});
		$("#password").keydown(function(event) {
			keyLogin(event);
		});
	}
);

function keyLogin(event) {
	if (event.keyCode == 13) {
		vm.login();
	}
}

function onFocus() {
	vm.error = false;
	vm.usernameError = false;
	vm.passwordError = false;
}

function checkPhoneNum() {
	var num = $("#touristPhone").val();
	if (num == "") {
		alert("手机号码不能为空");
	} else if (!isMobile(num)) {
		alert("请输入有效的手机号码");
	} else {
		var data = "phone=" + num;
		$.ajax({
			type : "POST",
			url : baseURL + "/sys/user/checkPhoneIsUse.do",
			data : data,
			dataType : "json",
			success : function(r) {
				if (r.code == 0) {
					$.ajax({
						type : "POST",
						url : baseURL + "/sys/touristVisit.do",
						data : data,
						dataType : "json",
						success : function(r) {
							if (r.code == 0) {
								localStorage.setItem("token", r.token);
								parent.location.href = "index.jsp";
							}else{
								alert(r.msg);
							}
						}
					});
				}else{
					alert("该手机号已注册!");
					vm.isTourist = false;
					vm.username = num;
					vm.password = "";
				}
			}
		});
	}
	if (vm.phoneError) {
		$("#getCode").attr('disabled', "false");
	} else {
		$("#getCode").removeAttr("disabled");
	}
}