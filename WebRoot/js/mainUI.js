var vm = new Vue({
	el : '#eapp',
	data : {
		isStudent : false,
		isALoading1 : true,
		isALoading2 : true,
		isLoading : true,
		isCheckedEnroll : false,
		message : "",
		replyMessage : "",
		enrollInfo : "",
		promptMessage : "",
		policyList : [],
		noticeList : [],
		articleList : [],
		roleList : [],
		user : {
			roleIdList : []
		},
		userList : {}
	},
	methods : {
		articleAll : function() {
			parent.vm.navTitle = "所有公告政策";
			parent.document.getElementById("myframe").src = baseURL + "jsp/articleList.jsp?page=1";
		},
		detail : function(id) {
			parent.vm.navTitle = "公告详情";
			parent.document.getElementById("myframe").src = baseURL + "jsp/articleDetail.jsp?id=" + id;
		},
		checkStuInfo : function() {
			parent.vm.navTitle = "报名信息列表";
			parent.document.getElementById("myframe").src = baseURL + "jsp/enrollInfoList.jsp";
		},
		init : function(noticeList, policyList) {

			for (var i = 0; i < noticeList.length; i++) {
				noticeList[i].artPublishTime = formatDate(noticeList[i].artPublishTime);
			}
			for (var i = 0; i < policyList.length; i++) {
				policyList[i].artPublishTime = formatDate(policyList[i].artPublishTime);
			}
		},
		getArticleList : function() {
			$.get(baseURL + "school/article/artList.do", function(r) {
				vm.articleList = r.articleList;
				vm.noticeList = r.noticeList.slice(0, 6);
				vm.policyList = r.policyList.slice(0, 6);
				vm.init(vm.noticeList, vm.policyList);
				if (vm.noticeList != null) {
					vm.isALoading1 = false;
				}
				if (vm.policyList != null) {
					vm.isALoading2 = false;
				}
			});
		},
		getUserInfo : function() {
			$.get(baseURL + "school/enroll/userInfo.do", function(r) {
				vm.roleList = r.roleList;
				vm.user = r.userInfo;
				for (var i = 0; i < vm.user.roleIdList.length; i++) {
					for (var j = 0; j < vm.roleList.length; j++) {
						if (vm.user.roleIdList[i] == vm.roleList[j].roleId) {
							if (vm.roleList[j].roleName == "学生") {
								if(vm.user.enrollStatus == 0){
									vm.message = "尚未通过报名，请报名";
								}else if(vm.user.enrollStatus == 1){
									vm.message = "已经报名，请等待录取通知";
								}else if(vm.user.enrollStatus == 2){
									vm.message = "您已被录取";
									vm.replyMessage = vm.user.message;
									vm.isCheckedEnroll = true;
								}else if(vm.user.enrollStatus == 3){
									vm.message = "您不能被录取";
									vm.replyMessage = vm.user.message;
									vm.isCheckedEnroll = true;
								}
								vm.isStudent = true;
								break;
							}
							if (vm.roleList[j].roleName == "招生人员") {
								vm.isStudent = false;
								$.get(baseURL + "school/enroll/userList.do", function(r) {
									vm.userList = r.userList;
									for (var p = 0; p < vm.userList.length; p++) {
										if(vm.userList[p].enrollStatus == 1){
											var audio = new Audio(baseURL + "tips.mp3");
											audio.play();
											vm.promptMessage = "有学生报名信息未审核";
											break;
										}
									}
								});
								break;
							} else {
								vm.promptMessage = "";
							}
						}						
					}
				}
			});
		},
		getMainInfo : function() {
			var data = "id=1";
			$.ajax({
				type : "POST",
				url : baseURL + "sys/main/info.do",
				data : data,
				dataType : "json",
				success : function(r) {
					if (r.code == 0) {
						vm.enrollInfo = r.sysMainInfo.message;
						var enrollInfoDiv = document.getElementById("enrollInfo");
						enrollInfoDiv.innerHTML = vm.enrollInfo;
						vm.isLoading = false;
					} else {
						alert(r.msg);
					}
				}
			});
		}
	},
	created : function() {
		this.getUserInfo();
		this.getMainInfo();
		this.getArticleList();
	}
});
