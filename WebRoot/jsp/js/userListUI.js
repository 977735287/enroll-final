$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'sys/user/list.do',
		datatype : "json",
		colModel : [
			{
				label : '用户ID',
				name : 'userId',
				index : "userId",
				width : 45,
				key : true
			},
			{
				label : '用户名',
				name : 'username',
				width : 75
			},
			{
				label : '邮箱',
				name : 'email',
				width : 90
			},
			{
				label : '手机号',
				name : 'mobile',
				width : 100
			},
			{
				label : '状态',
				name : 'status',
				width : 80,
				formatter : function(value, options, row) {
					return value === 0 ?
						'<span class="label label-danger">禁用</span>' :
						'<span class="label label-success">正常</span>';
				}
			},
			{
				label : '创建时间',
				name : 'createTime',
				index : "createTime",
				width : 80,
				formatter : function(value, options, row) {
					var dateSeconds = parseInt(value);			
					var date = new Date(dateSeconds);
					date = date.Format("yyyy-MM-dd hh:mm:ss");
					return date;
				}
			}
		],
		viewrecords : true,
		height : 385,
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		rownumbers : true,
		rownumWidth : 25,
		autowidth : true,
		multiselect : true,
		pager : "#jqGridPager",
		jsonReader : {
			root : "page.list",
			page : "page.currPage",
			total : "page.totalPage",
			records : "page.totalCount"
		},
		prmNames : {
			page : "page",
			rows : "limit",
			order : "order"
		},
		gridComplete : function() {
			//隐藏grid底部滚动条
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
				"overflow-x" : "hidden"
			});
		}
	});
});

var vm = new Vue({
	el : '#rrapp',
	data : {
		q : {
			username : null
		},
		showList : true,
		nameExistError : false,
		nameExistErrorMsg : '',
		title : null,
		username : "",
		mobile : "",
		email : "",
		roleList : {},
		user : {
			status : 1,
			roleIdList : []
		}
	},
	methods : {
		query : function() {
			vm.reload();
		},
//		test : function() {
//			$.ajax({
//				type : "POST",
//				url : baseURL + "sys/information/import1.do",
//				data : "",
//				success : function(r) {
//					if (r.code == 0) {
//						alert('操作成功');
//					} else {
//						alert(r.msg);
//					}
//				}
//			});
//		},
//		test1 : function() {
//			$.ajax({
//				type : "POST",
//				url : baseURL + "sys/information/import2.do",
//				data : "",
//				success : function(r) {
//					if (r.code == 0) {
//						alert('操作成功');
//					} else {
//						alert(r.msg);
//					}
//				}
//			});
//		},
		add : function() {
			vm.showList = false;
			vm.title = "新增";
			vm.roleList = {};
			vm.user = {
				status : 1,
				roleIdList : []
			};

			//获取角色信息
			this.getRoleList();
		},
		update : function() {
			var userId = getSelectedRow();
			if (userId == null) {
				return;
			}

			vm.showList = false;
			vm.title = "修改";

			vm.getUser(userId);			
			
			//获取角色信息
			this.getRoleList();
		},
		del : function() {
			var userIds = getSelectedRows();
			if (userIds == null) {
				return;
			}
			confirm("'确定要删除选中的记录？'", function() {
				$.ajax({
					type : "POST",
					url : baseURL + "sys/user/delete.do",
					contentType : "application/json",
					data : JSON.stringify(userIds),
					success : function(r) {
						if (r.code == 0) {
							alert('操作成功');
							vm.reload();
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdate : function() {
			var url = vm.user.userId == null ? "sys/user/save.do" : "sys/user/update.do";
			if(vm.user.email != ""){
				vm.checkUserEmail(vm.user.email,url);
			} else if(vm.user.mobile != ""){
				vm.checkUserPhone(vm.user.mobile,url);
			}else {
				vm.save(url);
			}
		},
		save : function(url) {
			if(!vm.nameExistError){
				$.ajax({
					type : "POST",
					url : baseURL + url,
					contentType : "application/json",
					data : JSON.stringify(vm.user),
					success : function(r) {
						if (r.code === 0) {
							alert('操作成功');
							vm.reload();
						} else {
							alert(r.msg);
						}
					}
				});
			}			
		},
		getUser : function(userId) {
			//			$.get(baseURL + "sys/user/all/info.do", function(r){
			//				alert(r.user.username);
			//				vm.user = r.user;
			//				vm.user.password = null;
			//			});
			$.ajax({
				type : "POST",
				url : baseURL + "sys/user/all/info.do",
				data : "userId=" + userId,
				success : function(r) {
					vm.user = r.user;
					vm.user.password = null;
					vm.username = vm.user.username;
					vm.email = vm.user.email;
					vm.mobile = vm.user.mobile;
				}
			});
		},
		getRoleList : function() {
			$.get(baseURL + "sys/role/select.do", function(r) {
				vm.roleList = r.list;
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
					if (r.code == 0) { //用户名已存在
						if(vm.username == username){
							vm.nameExistError = false;
							vm.nameExistErrorMsg = "";
						}else{
							vm.nameExistError = true;
							vm.nameExistErrorMsg = "该用户名已存在";
						}						
					} else {
						vm.nameExistError = false;
						vm.nameExistErrorMsg = "";
					}
				}
			});
		},
		checkUserPhone : function(phone,url) {
			var data = "phone=" + phone;
			$.ajax({
				type : "POST",
				url : baseURL + "/sys/user/checkPhoneIsUse.do",
				data : data,
				dataType : "json",
				success : function(r) {
					if (r.code == 0) {
						vm.save(url);				
					} else {
						if(vm.mobile == phone){
							vm.save(url);
						}else{
							alert(r.msg);
						}
					}
				}
			});
		},
		checkUserEmail : function(email,url) {
			var data = "email=" + email;
			$.ajax({
				type : "POST",
				url : baseURL + "/sys/user/checkEmailIsUse.do",
				data : data,
				dataType : "json",
				success : function(r) {
					if (r.code == 0) {
						if(vm.user.mobile != ""){
							vm.checkUserPhone(vm.user.mobile,url);
						} else {
							vm.save(url);
						}
					} else {
						if(vm.email == email){
							if(vm.user.mobile != ""){
								vm.checkUserPhone(vm.user.mobile,url);
							} else {
								vm.save(url);
							}
						}else{
							alert(r.msg);
						}						
					}
				}
			});
		},
		focus : function() {
			vm.nameExistError = false;
			vm.nameExistErrorMsg = "";
		},
		reload : function() {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'username' : vm.q.username
				},
				page : page
			}).trigger("reloadGrid");
		}
	}
});