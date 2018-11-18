$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'school/list/info/list.do',
		datatype : "json",
		colModel : [
			{
				label : 'ID',
				name : 'id',
				index : "id",
				width : 35,
				key : true
			},
			{
				label : '证件号码',
				name : 'stuCertificatesNum',
				index : "stuCertificatesNum",
				width : 85
			},
			{
				label : '学生姓名',
				name : 'stuName',
				index : "stuName",
				width : 75
			},
			{
				label : '性别',
				name : 'stuSex',
				index : "stuSex",
				width : 55,
				formatter : function(value, options, row) {
					if (value === 0) {
						return "男";
					} else if (value === 1) {
						return "女";
					} else {
						return "";
					}
				}
			},
			{
				label : '出生日期',
				name : 'stuBirthday',
				index : "stuBirthday",
				width : 80,
				formatter : function(value, options, row) {
					var dateSeconds = parseInt(value);
					var date = new Date(dateSeconds);
					date = date.Format("yyyy-MM-dd");
					return date;
				}
			},
			{
				label : '录取状态',
				name : 'user.enrollStatus',
				index : "user.enrollStatus",
				width : 55,
				formatter : function(value, options, row) {
					if (value === 2) {
						return '<span class="label label-success">已录取</span>';
					} else if (value === 3) {
						return '<span class="label label-danger">未录取</span>';
					} else {
						return '<span class="label label-warning">未审核</span>';
					}
				}
			},
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
	el : '#eapp',
	data : {
		q : {
			key : null,
			checkStatus : null,
			enrollStatus : null
		},
		showList : true,
	},
	methods : {
		query : function() {
			vm.reload();
		},
		exportExcel : function() {
			window.open(baseURL + "school/list/info/export.do");
//			$.ajax({
//				type : "POST",
//				url : baseURL + "school/list/info/export.do",
//				success : function(r) {
//					if(r.code == 0){
//						
//					}else {
//						alert(r.msg);
//					}
//				}
//			});
		},
		reload : function() {
			vm.showList = true;
			if (vm.q.checkStatus == 'null') {
				vm.q.checkStatus = null;
			}
			if (vm.q.enrollStatus == 'null') {
				vm.q.enrollStatus = null;
			}
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'key' : vm.q.key,
					'checkStatus' : vm.q.checkStatus,
					'enrollStatus' : vm.q.enrollStatus
				},
				page : page
			}).trigger("reloadGrid");
		}
	},
	created : function() {
		
	}
});