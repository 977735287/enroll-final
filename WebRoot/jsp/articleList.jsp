<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>公告列表</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="../static/css/bootstrap.min.css">
<link rel="stylesheet" href="../static/css/font-awesome.min.css">
<link rel="stylesheet" href="../static/css/ui.jqgrid-bootstrap.css">
<link rel="stylesheet" href="../static/css/metroStyle/metroStyle.css">
<link rel="stylesheet" href="../static/css/main.css">

<script src="../static/js/jquery.min.js"></script>
<script src="../static/js/plugins/layer/layer.js"></script>
<script src="../static/js/bootstrap.min.js"></script>
<script src="../static/js/vue.min.js"></script>
<script src="../static/js/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="../static/js/plugins/jqgrid/jquery.jqGrid.min.js"></script>
<script src="../static/js/plugins/ztree/jquery.ztree.all.min.js"></script>
<style type="text/css">
.container ul {
	padding: 0px;
	margin: 0px;
	list-style: none;
}
</style>
</head>

<body>
	<div id="eapp" v-cloak>
		<div class="container" style="text-align:left;">
			<div class="container">
				<div class="row clearfix">
					<div class="col-md-1 column"></div>
					<div class="col-md-10 column">
						<div v-show="!isLoading">
							<table class="table table-condensed">
								<thead>
									<tr>
										<th>类型</th>
										<th>标题</th>
										<th>发布时间</th>
									</tr>
								</thead>
								<tbody>
									<template v-for="article in articleList">
									<tr style="margin-top:-10px">
										<td style="border:none"><span v-if="article.artType == 0">政策</span>
											<span v-else>公告</span></td>
										<td style="border:none"><div style="text-align:left;">
												<ul v-if="article.artTitle.length > 40">
													<li><a href="javascript:;" @click="detail(article.id)">{{article.artTitle.substring(0,40)}}...</a>
													</li>
												</ul>
												<ul v-else>
													<li><a href="javascript:;" @click="detail(article.id)">{{article.artTitle}}</a>
													</li>
												</ul>
											</div></td>
										<td style="border:none">{{article.artPublishTime}}</td>
									</tr>
									</template>
								</tbody>
							</table>
						</div>
						<div v-show="isLoading">
							<table class="table table-condensed">
								<thead>
									<tr>
										<th>类型</th>
										<th>标题</th>
										<th>发布时间</th>
									</tr>
								</thead>
							</table>
							<div class="v-h-center">
								<div class="loader-inner ball-spin-fade-loader">
									<div></div>
									<div></div>
									<div></div>
									<div></div>
									<div></div>
									<div></div>
									<div></div>
									<div></div>
								</div>
							</div>
							<div>&nbsp;</div>
							<div>&nbsp;</div>
							<div>&nbsp;</div>
							<div>&nbsp;</div>
							<div>&nbsp;</div>
							<div>&nbsp;</div>
							<div>&nbsp;</div>
							<div>&nbsp;</div>
							<div>&nbsp;</div>
							<div>&nbsp;</div>
							<div>&nbsp;</div>
							<div>&nbsp;</div>
							<div>&nbsp;</div>
							<div>&nbsp;</div>
							<div>&nbsp;</div>							
						</div>
					</div>
					<div class="col-md-1 column"></div>
				</div>
			</div>
			<div v-show="!isLoading" style="text-align:center;">
				<ul class="pagination">
					<li><a href="javascript:;" @click="updatePage(1)">&laquo;</a></li>
					<li v-for="page in pages"><a href="javascript:;"
						@click="updatePage(page)">{{page}}</a></li>
					<li><a href="javascript:;" @click="updatePage(totalPage)">&raquo;</a></li>
				</ul>
			</div>
		</div>
	</div>

	<script src="../js/common.js"></script>
	<script src="js/articleListUI.js"></script>
</body>
</html>