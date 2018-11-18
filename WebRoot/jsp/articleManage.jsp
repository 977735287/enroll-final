<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>公告列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="../static/css/bootstrap.min.css">
<link rel="stylesheet" href="../static/js/datetimepicker/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="../static/css/font-awesome.min.css">
<link rel="stylesheet"
	href="../static/js/plugins/jqgrid/ui.jqgrid-bootstrap.css">
<link rel="stylesheet"
	href="../static/js/plugins/ztree/css/metroStyle/metroStyle.css">
<link rel="stylesheet" href="../static/js/datetimepicker/editor.css" />
<link rel="stylesheet" href="../static/css/main.css">

<script src="../static/js/jquery.min.js"></script>
<script src="../static/js/plugins/layer/layer.js"></script>
<script src="../static/js/bootstrap.min.js"></script>
<script src="../static/js/datetimepicker/editor.js"></script>
<script src="../static/js/datetimepicker/moment-with-locales.min.js"></script>
<script src="../static/js/datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script src="../static/js/vue.min.js"></script>
<script src="../static/js/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="../static/js/plugins/jqgrid/jquery.jqGrid.min.js"></script>
<script src="../static/js/plugins/ztree/jquery.ztree.all.min.js"></script>

</head>
<body>
	<div id="eapp" v-cloak>
		<div v-show="showList">
			<div class="grid-btn">
				<div class="form-group col-sm-2">
					<input type="text" class="form-control" v-model="q.artTitle"
						@keyup.enter="query" placeholder="标题">
				</div>
				<a class="btn btn-default" @click="query">查询</a> <a
					v-if="hasPermission('school:article:save')" class="btn btn-primary"
					@click="add"><i class="fa fa-plus"></i>&nbsp;新增</a> <a
					v-if="hasPermission('school:article:update')"
					class="btn btn-primary" @click="update"><i
					class="fa fa-pencil-square-o"></i>&nbsp;修改</a> <a
					v-if="hasPermission('school:article:delete')"
					class="btn btn-primary" @click="del"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
			</div>
			<table id="jqGrid"></table>
			<div id="jqGridPager"></div>
		</div>

		<div v-show="!showList" class="panel panel-default">
			<div class="panel-heading">{{title}}</div>
			<form class="form-horizontal" method="post">
				<div class="form-group">
					<div class="col-sm-2 control-label">公告标题</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" v-model="article.artTitle"
							placeholder="公告标题" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">类型</div>
					<div class="col-sm-10">
						<select class="form-control" v-model="article.artType">
							<option v-bind:value="0">政策</option>
							<option v-bind:value="1">公告</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">发布时间</div>
					<div class="col-sm-10">
						<input id="artPublishTime" type="text" class="form-control"
							v-model="article.artPublishTime" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">内容</div>
					<div class="col-sm-10">
						<textarea id="artContent" ></textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label"></div>
					<input type="button" class="btn btn-primary" @click="saveOrUpdate"
						value="确定" /> &nbsp;&nbsp;<input type="button"
						class="btn btn-warning" @click="reload" value="返回" />
				</div>
			</form>
		</div>
	</div>

	<script src="../js/common.js"></script>
	<script src="js/articleManageUI.js"></script>
</body>
</html>