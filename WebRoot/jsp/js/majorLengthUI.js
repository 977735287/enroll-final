$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'school/length/list.do',
		datatype : "json",
		colModel : [
			{
				label : '学制ID',
				name : 'id',
				index : "id",
				width : 45,
				key : true
			},
			{
				label : '学制名称',
				name : 'lengthName',
				index : "lengthName",
				width : 75
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
			lengthName : null
		},
		showList : true,
		title : null,
		length : {}
	},
	methods : {
		query : function() {
			vm.reload();
		},
		add : function() {
			vm.length = {};
			vm.showList = false;
			vm.title = "新增";
		},
		update : function() {
			var lengthId = getSelectedRow();
			if (lengthId == null) {
				return;
			}

			vm.showList = false;
			vm.title = "修改";
						
			vm.getLengthInfo(lengthId);
			
		},
		del : function(event) {
			var ids = getSelectedRows();
			if (ids == null) {
				return;
			}
			confirm('确定要删除选中的学制？', function(){
				$.ajax({
					type : "POST",
					url : baseURL + "school/length/delete.do",
					contentType : "application/json",
					data : JSON.stringify(ids),
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
			var url = vm.length.id == null ? "school/length/save.do" : "school/length/update.do";
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.length),
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
		getLengthInfo : function(id) {
			$.ajax({
				type : "POST",
				url : baseURL + "school/length/info.do",
				data : "id=" + id,
				success : function(r) {
					vm.length = r.length;
				}
			});
		},
		reload : function() {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'lengthName' : vm.q.lengthName
				},
				page : page
			}).trigger("reloadGrid");
		}
	}
});