$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'school/major/list.do',
		datatype : "json",
		colModel : [
			{
				label : '专业ID',
				name : 'majorId',
				index : "majorId",
				width : 45,
				key : true
			},
			{
				label : '专业代码',
				name : 'majorCode',
				index : "majorCode",
				width : 75
			},
			{
				label : '专业名称',
				name : 'majorName',
				index : "majorName",
				width : 85
			},
			{
				label : '班最大人数',
				name : 'majorMax',
				index : "majorMax",
				width : 45
			},
			{
				label : '专业学制时长',
				name : 'majorLength.lengthName',
				index : "majorLength.lengthName",
				width : 45
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
			majorName : null
		},
		showList : true,
		title : null,
		major : {
			majorLength : {
				id : 1
			}
		},
		lengths : []
	},
	methods : {
		query : function() {
			vm.reload();
		},
		add : function() {
			vm.major = {
				majorLength : {
					id : 1
				}
			};
			vm.showList = false;
			vm.title = "新增";
			vm.getLengths();
		},
		update : function() {
			var majorId = getSelectedRow();
			if (majorId == null) {
				return;
			}

			vm.showList = false;
			vm.title = "修改";

			vm.getMajorInfo(majorId);
			vm.getLengths();
		},
		del : function() {
			var majorIds = getSelectedRows();
			if (majorIds == null) {
				return;
			}
			confirm('确定要删除选中的专业？', function() {
				$.ajax({
					type : "POST",
					url : baseURL + "school/major/delete.do",
					contentType : "application/json",
					data : JSON.stringify(majorIds),
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
			var url = vm.major.majorId == null ? "school/major/save.do" : "school/major/update.do";
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.major),
				success : function(r) {
					if (r.code === 0) {
						alert('操作成功');
						vm.reload();
					} else {
						alert(r.msg);
					}
				}
			});
		},
		getMajorInfo : function(id) {
			$.ajax({
				type : "POST",
				url : baseURL + "school/major/info.do",
				data : "id=" + id,
				success : function(r) {
					vm.major = r.major;
				}
			});
		},
		getLengths : function() {
			//获取学制时长
			$.get(baseURL + "school/length/select.do", function(r) {
				vm.lengths = r.list;
			});
		},
		reload : function() {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'majorName' : vm.q.majorName
				},
				page : page
			}).trigger("reloadGrid");
		}
	}
});