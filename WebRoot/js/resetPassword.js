/**
 *  登录
 */

var vm = new Vue({
	el : '#eapp',
	data : {
		username : '',
		email : '',
		phone : '',
		code : '',
		currentCode : '',
		newPassword : '',
		confirmPassword : '',
		captcha : '',
		clock : '点击获取验证码',
		isSelectWay : true,
		isCheckUsername : false,
		isCheckEmail : false,
		isConfirm : false,
		isCheckPhoneCode : false,
		isGetCode : false,
		isUpdatePW : false,
		isPasswordSame : false,
		isOk : false,
		error : false,
		usernameError : false,
		emailError : false,
		phoneError : false,
		errorMsg : '',
		errorUsernameMsg : '',
		errorEmailMsg : '',
		errorPhoneMsg : '',
		okMsg : '',
		src : 'captcha.do'
	},
	methods : {
		refreshCode : function() {
			this.src = "captcha.do?t=" + $.now();
		},
		emailFind : function() {
			vm.isSelectWay = false;
			vm.isCheckUsername = true;
		},
		phoneFind : function() {
			vm.isSelectWay = false;
			vm.isCheckPhoneCode = true;
		},
		checkUsername : function() {
			if (vm.username == "") {
				vm.errorUsernameMsg = "用户名不能为空";
				vm.usernameError = true;
			} else {
				var data = "username=" + vm.username;
				$.ajax({
					type : "POST",
					url : baseURL + "/sys/user/checkUsername.do",
					data : data,
					dataType : "json",
					success : function(r) {
						if (r.code == 0) {
							vm.isCheckUsername = false;
							vm.isCheckEmail = true;
						} else {
							vm.errorUsernameMsg = r.msg;
							vm.usernameError = true;
						}
					}
				});
			}
		},
		checkEmail : function() {
			if (vm.email == "") {
				vm.errorEmailMsg = "电子邮箱不能为空";
				vm.emailError = true;
			} else {
				var data = "username=" + vm.username + "&email=" + vm.email;
				$.ajax({
					type : "POST",
					url : baseURL + "/sys/user/checkEmail.do",
					data : data,
					dataType : "json",
					success : function(r) {
						if (r.code == 0) {
							vm.isCheckEmail = false;
							vm.isConfirm = true;
						} else if (r.code == -1) {
							alert(r.msg);
						} else {
							vm.errorEmailMsg = r.msg;
							vm.emailError = true;
						}
					}
				});
			}
		},
		confirmReset : function() {
			var data = "username=" + vm.username + "&email=" + vm.email;
			$.ajax({
				type : "POST",
				url : baseURL + "/sys/user/resetPassword.do",
				data : data,
				dataType : "json",
				success : function(r) {
					if (r.code == 0) {
						vm.okMsg = r.msg;
						vm.isConfirm = false;
						vm.isOk = true;
					} else {
						vm.okMsg = r.msg;
						vm.isConfirm = false;
						vm.isOk = true;
					}
				}
			});
		},
		checkCode : function() {
			if (vm.phone == "") {
				vm.errorPhoneMsg = "手机号码不能为空";
				vm.phoneError = true;
			} else if (vm.phoneError) {
				alert(vm.errorPhoneMsg);
			} else if (!vm.isGetCode) {
				alert("请先获取验证码！");
			} else if (vm.currentCode == vm.code) {
				vm.isCheckPhoneCode = false;
				vm.isUpdatePW = true;
			} else {
				alert("验证码错误！");
			}
		},
		updatePW : function() {
			if(vm.newPassword != ""){
				if (vm.newPassword == vm.confirmPassword) {
					var data = "phone=" + vm.phone + "&newPassword=" + vm.newPassword;
					$.ajax({
						type : "POST",
						url : baseURL + "/sys/user/updatePassword.do",
						data : data,
						dataType : "json",
						success : function(r) {
							if (r.code == 0) {
								vm.okMsg = "成功修改密码！";
								vm.isUpdatePW = false;
								vm.isOk = true;
							} else {
								alert(r.msg);
							}
						}
					});
				} else {
					alert("两次输入密码不一致！");
				}
			}else{
				alert("密码不能为空！");
			}
		},
		back : function() {
			if (vm.isCheckEmail) {
				vm.email = "";
				vm.isCheckUsername = true;
				vm.isCheckEmail = false;
				vm.emailError = false;
			} else if (vm.isConfirm) {
				vm.isCheckEmail = true;
				vm.isConfirm = false;
			}
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
	vm.usernameError = false;
	vm.emailError = false;
	vm.phoneError = false;
}

function onBlur() {
	if ($("#newPassword").val() != $("#confirmPassword").val()) {
		alert("两次输入密码不一致！");
	}
}

var clock = '';
var nums = 59;
var btn;
function sendCode(thisBtn) {
	if ($("#phone").val() == "") {
		vm.errorPhoneMsg = "手机号码不能为空";
		vm.phoneError = true;
		alert("请先输入手机号码！");
	} else {
		btn = thisBtn;
		btn.disabled = true;
		vm.clock = nums + '秒后可重新获取';
		clock = setInterval(doLoop, 1000);
		var data = "phone=" + vm.phone;
		$.ajax({
			type : "POST",
			url : baseURL + "/sys/user/sendCode.do",
			data : data,
			dataType : "json",
			success : function(r) {
				if (r.code == 0) {
//					alert(r.currentCode);
					vm.currentCode = r.currentCode;
					vm.isGetCode = true;
				}
			}
		});
	}
}
function doLoop() {
	nums--;
	if (nums > 0) {
		vm.clock = nums + '秒后可重新获取';
	} else {
		clearInterval(clock);
		btn.disabled = false;
		vm.clock = '重新获取';
		nums = 59;
	}
}

function checkPhoneNum() {
	var num = $("#phone").val();
	if (num == "") {
		vm.errorPhoneMsg = "手机号码不能为空";
		vm.phoneError = true;
	} else if (!isMobile(num) && !isTel(num)) {
		vm.errorPhoneMsg = "请输入有效的手机号码";
		vm.phoneError = true;
	} else {
		var data = "phone=" + num;
		$.ajax({
			type : "POST",
			url : baseURL + "/sys/user/checkPhoneIsUse.do",
			data : data,
			dataType : "json",
			success : function(r) {
				if (r.code == 0) {
					vm.errorPhoneMsg = "该手机号码尚未注册！";
					vm.phoneError = true;
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

function toPhoneFind(){
	vm.isCheckUsername = false;
	vm.isCheckEmail = false;
	vm.isCheckPhoneCode = true;
}

function toEmailFind(){
	vm.isCheckPhoneCode = false;
	vm.isCheckUsername = true;
}