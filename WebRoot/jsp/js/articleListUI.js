
var vm = new Vue({
	el:'#eapp',
	data:{
		isLoading : true,
		totalPage : 0,		
		articleList : [],
		pages : []
	},
	methods: {
		init : function() {
			var map = {};
			map["page"] = getQueryString("page");			
			map["limit"] = 15;
			$.ajax({
				type: "POST",
			    url: baseURL + "school/article/list.do",
			    data: map,
			    dataType: "json",
			    success: function(r){
					if(r.code == 0){
						vm.articleList = r.page.list;
						vm.totalPage = r.page.totalPage;
						vm.pages = new Array(r.page.totalPage);
						for(var i=0;i<r.page.totalPage;i++){
							vm.pages[i] = i+1;
						}
						for(var i=0;i<vm.articleList.length;i++){
							vm.articleList[i].artPublishTime = formatDate(vm.articleList[i].artPublishTime);
						}
						if(vm.articleList != null){
							vm.isLoading = false;
						}
					}else{
						alert(r.msg);
					}
				}
			});
			

		},
		updatePage : function(page){
			parent.document.getElementById("myframe").src = baseURL + "jsp/articleList.jsp?page=" + page;
		},
		detail : function(id) {
			parent.vm.navTitle = "公告详情";
			parent.document.getElementById("myframe").src = baseURL + "jsp/articleDetail.jsp?id=" + id;
		},
	},
	created : function() {
		this.init();
	}
});