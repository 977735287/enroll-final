
var vm = new Vue({
	el : '#eapp',
	data : {
		userInfo : {},
		isLogin : false,
		articleList : [],
	},
	methods : {
		checkLoginStatus : function() {
			if(loginUser != null){
				this.isLogin = true;
				this.userInfo = loginUser;
			}
		},
		getArticleList : function() {
			$.get(baseURL + "school/student/artList.do", function(r) {
				vm.articleList = r.articleList.slice(0, 4);
				vm.init(vm.articleList);
			});
		},
		init : function(articleList) {
			for (var i = 0; i < articleList.length; i++) {
				articleList[i].artPublishTime = formatDate(articleList[i].artPublishTime);
			}
		},
	},
	created : function() {
		this.checkLoginStatus();
		this.getArticleList();
	}
});