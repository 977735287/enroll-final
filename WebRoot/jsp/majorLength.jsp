<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>专业学制</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="../static/css/bootstrap.min.css">
<link rel="stylesheet" href="../static/css/font-awesome.min.css">
<link rel="stylesheet"
	href="../static/js/plugins/jqgrid/ui.jqgrid-bootstrap.css">
<link rel="stylesheet"
	href="../static/js/plugins/ztree/css/metroStyle/metroStyle.css">
<link rel="stylesheet" href="../static/css/main.css">

<script src="../static/js/jquery.min.js"></script>
<script src="../static/js/plugins/layer/layer.js"></script>
<script src="../static/js/bootstrap.min.js"></script>
<script src="../static/js/vue.min.js"></script>
<script src="../static/js/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="../static/js/plugins/jqgrid/jquery.jqGrid.min.js"></script>
<script src="../static/js/plugins/ztree/jquery.ztree.all.min.js"></script>

</head>
<body>
	<div id="rrapp" v-cloak>
		<div v-show="showList">
			<div class="grid-btn">
				<div class="form-group col-sm-2">
					<input type="text" class="form-control" v-model="q.lengthName"
						@keyup.enter="query" placeholder="学制名称">
				</div>
				<a class="btn btn-default" @click="query">查询</a> <a
					v-if="hasPermission('school:length:save')" class="btn btn-primary"
					@click="add"><i class="fa fa-plus"></i>&nbsp;新增</a> <a
					v-if="hasPermission('school:length:update')" class="btn btn-primary"
					@click="update"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a> <a
					v-if="hasPermission('school:length:delete')" class="btn btn-primary"
					@click="del"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
			</div>
			<table id="jqGrid"></table>
			<div id="jqGridPager"></div>
		</div>

		<div v-show="!showList" class="panel panel-default">
			<div class="panel-heading">{{title}}</div>
			<form class="form-horizontal" method="post">
				<div class="form-group">
					<div class="col-sm-2 control-label">学制名称</div>
					<div class="col-sm-10">
						<input type="text" class="form-control"
							v-model="length.lengthName" placeholder="学制名称" />
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
	<script src="js/majorLengthUI.js"></script>
</body>
</html>