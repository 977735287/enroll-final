$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'school/article/list.do',
		datatype : "json",
		colModel : [
			{
				label : '公告ID',
				name : 'id',
				index : "id",
				width : 45,
				key : true
			},
			{
				label : '公告标题',
				name : 'artTitle',
				index : "artTitle",
				width : 115,
				formatter : function(value, options, row) {
					if(value.length > 26){
						return value.substring(0, 26) + "...";
					}else{
						return value;
					}
				}
			},
			{
				label : '作者',
				name : 'artAuthor.username',
				width : 45
			},
			{
				label : '公告类型',
				name : 'artType',
				index : "artType",
				width : 45,
				formatter : function(value, options, row) {
					if(value === 0){
						return "政策";
					}else if(value === 1){
						return "公告";
					}else{
						return "";
					}
				}
			},
			{
				label : '发布时间',
				name : 'artPublishTime',
				index : "artPublishTime",
				width : 80,
				formatter : function(value, options, row) {
					var dateSeconds = parseInt(value);			
					var date = new Date(dateSeconds);
					date = date.Format("yyyy-MM-dd");
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
	el : '#eapp',
	data : {
		q : {
			artTitle : null
		},
		showList : true,
		title : null,
		article : {
			artType : 0
		}
	},
	methods : {
		query : function() {
			vm.reload();
		},
		add : function() {
			$("#artContent").Editor("setText","");
			vm.showList = false;
			vm.title = "新增";
			vm.article = {
				artType : 0
			};
		},
		update : function() {
			var id = getSelectedRow();
			if (id == null) {
				return;
			}
			
			vm.getArticleInfo(id);

			vm.showList = false;
			vm.title = "修改";			
		},
		del : function(event) {
			var ids = getSelectedRows();
			if (ids == null) {
				return;
			}
			confirm('确定要删除选中的公告？', function(){
				$.ajax({
					type : "POST",
					url : baseURL + "school/article/delete.do",
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
			var url = vm.article.id == null ? "school/article/save.do" : "school/article/update.do";
			vm.article.artContent = $("#artContent").Editor("getText");
			vm.article.artPublishTime = $("#artPublishTime").val();
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.article),
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
		getArticleInfo : function(id) {
			$.ajax({
				type : "POST",
				url : baseURL + "school/article/info.do",
				data : "id=" + id,
				success : function(r) {
					vm.article = r.article;
					vm.article.artPublishTime = new Date(parseInt(vm.article.artPublishTime)).Format("yyyy-MM-dd");
					$("#artContent").Editor("setText",vm.article.artContent);
				}
			});
		},
		reload : function() {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'artTitle' : vm.q.artTitle
				},
				page : page
			}).trigger("reloadGrid");
		}
	}
});

$('#artPublishTime').datetimepicker({
    format: 'YYYY-MM-DD',
    locale: moment.locale('zh-cn')
});

$(document).ready(function() {
	$("#artContent").Editor();
});