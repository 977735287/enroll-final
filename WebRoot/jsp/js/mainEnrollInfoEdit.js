var vm = new Vue({
	el : '#eapp',
	data : {
		showList : true,
		isLoading : true,
		message : "",
		sysMainInfo : {}
	},
	methods : {
		getMainInfo : function(){
			var data = "id=1";
			$.ajax({
				type: "POST",
			    url: baseURL + "sys/main/info.do",
			    data: data,
			    dataType: "json",
			    success: function(r){
					if(r.code == 0){
						vm.sysMainInfo = r.sysMainInfo;
						vm.message = vm.sysMainInfo.message;
						var massageDiv = document.getElementById("massage");
						massageDiv.innerHTML = vm.message;
						vm.isLoading = false;
					}else{
						alert(r.msg);
					}
				}
			});
		},
		update : function() {
			vm.sysMainInfo.message = $("#content").Editor("getText");
			$.ajax({
				type : "POST",
				url : baseURL + "sys/main/update.do",
				contentType : "application/json",
				data : JSON.stringify(vm.sysMainInfo),
				success : function(r) {
					if (r.code === 0) {
//						alert('操作成功');
						vm.reload();
					} else {
						alert(r.msg);
					}
				}
			});
		},
		edit : function() {
			$("#content").Editor("setText",vm.message);
			vm.showList = false;
		},
		reload : function() {
			vm.showList = true;
			location.reload();
		}
	},
	created : function() {
		this.getMainInfo();
	}
});

$(document).ready(function() {
	$("#content").Editor();
});