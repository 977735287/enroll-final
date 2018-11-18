/**
 *  注册
 */

var vm = new Vue({
	el:'#eapp',
	data:{		
		confirmPassword : '',
		error: false,
		nameExistError : false,
		nameExistErrorMsg : '',
		errorMsg: '',
        userInfo : {}
	},
	methods: {
		register : function() {
			if(vm.userInfo.username == ""){
				vm.nameExistError = true;
				vm.nameExistErrorMsg = "用户名不能为空";
			}else if(vm.userInfo.password == ""){
				alert("密码不能为空！");
			}else{
				if(vm.userInfo.mobile != ""){
					vm.checkUserPhone(vm.userInfo.mobile);
				}else if(vm.userInfo.email != ""){
					vm.checkUserEmail(vm.userInfo.email);
				} else {
					vm.save();
				}				
			}			
		},
		save : function() {
			$.ajax({
				type: "POST",
			    url: baseURL + "/sys/register/save.do",
			    contentType : "application/json",
			    data: JSON.stringify(vm.userInfo),
			    success: function(r){
					if(r.code == 0){//注册成功
                        parent.location.href = "login.jsp";
					}else{
						vm.error = true;
						vm.errorMsg = r.msg;
					}
				}
			});
		},
		checkUserName : function(username) {
			var data = "username=" + username;
			$.ajax({
				type : "POST",
				url : baseURL + "/sys/user/checkUsername.do",
				data : data,
				dataType : "json",
				success : function(r) {
					if (r.code == 0) { //验证用户名已存在
						vm.nameExistError = true;
						vm.nameExistErrorMsg = "该用户名已存在";
					} else {
						vm.nameExistError = false;
						vm.nameExistErrorMsg = "";
					}
				}
			});
		},
		checkUserPhone : function(phone) {
			var data = "phone=" + phone;
			$.ajax({
				type : "POST",
				url : baseURL + "/sys/user/checkPhoneIsUse.do",
				data : data,
				dataType : "json",
				success : function(r) {
					if (r.code == 0) {
						if(vm.userInfo.email != ""){
							vm.checkUserEmail(vm.userInfo.email);
						} else {
							vm.save();
						}						
					} else {
						alert(r.msg);
					}
				}
			});
		},
		checkUserEmail : function(email) {
			var data = "email=" + email;
			$.ajax({
				type : "POST",
				url : baseURL + "/sys/user/checkEmailIsUse.do",
				data : data,
				dataType : "json",
				success : function(r) {
					if (r.code == 0) {
						vm.save();
					} else {
						alert(r.msg);
					}
				}
			});
		},
		back : function() {
			parent.location.href = baseURL + "login.jsp";
		},
		focus : function() {
			vm.nameExistError = false;
			vm.nameExistErrorMsg = "";
		} 
	}
});

//$(document).ready(
//		function() {
//			
//		}
//);

function onFocus(){
	vm.error = false;
}

function onBlur(){
	if($("#password").val() != $("#confirmPassword").val()){
		vm.errorMsg = "两次输入密码不一致！";
		vm.error = true;
	}
}