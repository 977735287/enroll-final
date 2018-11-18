var vm = new Vue({
	el:'#eapp',
	data:{
		article : {}
	},
	methods: {
		init : function(article){
			var contentDiv = document.getElementById("artContent");
			contentDiv.innerHTML = article.artContent;
		},
		getArticle : function(){
			var data = "id=" + getQueryString("id");
			$.ajax({
				type: "POST",
			    url: baseURL + "school/article/info.do",
			    data: data,
			    dataType: "json",
			    success: function(r){
					if(r.code == 0){
						vm.article = r.article;
						vm.article.artPublishTime = formatDate(vm.article.artPublishTime);
						vm.init(vm.article);
					}else{
						alert(r.msg);
					}
				}
			});
		}
	},
	created : function() {
		this.getArticle();
	}
});