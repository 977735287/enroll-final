
var vm = new Vue({
	el : '#eapp',
	data : {
		isRegister : false,
		title : "学生登录入口",
		tag : "学生登陆",
		username : "",
		password : "",
	},
	methods : {
		login : function(){
			if (vm.username == "") {
				alert("用户名不能为空！");
			} else if (vm.password == "") {
				alert("请输入密码");
			} else {
				var data = "username=" + vm.username + "&password=" + vm.password;
				$.ajax({
					type : "POST",
					url : baseURL + "/school/student/login.do",
					data : data,
					dataType : "json",
					success : function(r) {
						if (r.code == 0) { //登录成功
							localStorage.setItem("loginUser", JSON.stringify(r.user));
							localStorage.setItem("token", r.token);
							parent.location.href = "index.html";
						} else {
							alert(r.msg);
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown){
						alert("error!");
					}
				});
			}
		},
		regiter : function(){
			
		},
		getStatus : function() {
			if(GetRequest().status == 1){
				regiterStaus(this);
			}else {
				loginStaus(this);
			}
		},
	},
	created : function() {
		this.getStatus();
	}
});

function regiterStaus(obj){
	obj.isRegister = true;
	obj.title = "学生注册入口";
	obj.tag = "学生注册";
}

function loginStaus(obj){
	obj.isRegister = false;
	obj.title = "学生登录入口";
	obj.tag = "学生登陆";
}