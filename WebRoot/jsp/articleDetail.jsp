<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>公告政策页面</title>
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
.container ul
{
    padding:0px;
    margin:0px;
    list-style:none;
}

.news-item
{
    padding:36px 0px;
    margin:0px;
    border-bottom:1px dotted #555; 
}

.title
{
	text-align : center;
	font-size : 26px;
}

.content
{
	margin-top : 36px;
}
</style>
</head>

<body>
	<div id="eapp" v-cloak>
		<div class="container">
			<div class="title">
				<ul id="demo3">
					<li class="news-item">{{article.artTitle}}
					</li>					
				</ul>
			</div>
			<div style="padding:10px 0 0">发布时间：{{article.artPublishTime}}</div>
			<div class="content" id="artContent"></div>
		</div>
	</div>

	<script src="../js/common.js"></script>
	<script src="js/articleDetailUI.js"></script>
</body>
</html>

