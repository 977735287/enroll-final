<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>所有学生信息列表</title>
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
	<div id="eapp" v-cloak>
		<div v-show="showList">
			<div class="grid-btn">
				<div class="form-group col-sm-2">
					<input type="text" class="form-control" v-model="q.key"
						@keyup.enter="query" placeholder="学生姓名、身份证号码">
				</div>
				<div class="form-group col-sm-6">
					<div class="col-sm-4">
						<select class="form-control" v-model="q.enrollStatus">
							<option v-bind:value="null">录取状态</option>
							<option v-bind:value="2">已录取</option>
							<option v-bind:value="3">未录取</option>
						</select>
					</div>
					<div class="col-sm-4">
						<select class="form-control" v-model="q.checkStatus">
							<option v-bind:value="null">审核状态</option>
							<option v-bind:value="0">未审核</option>
							<option v-bind:value="1">已审核</option>
						</select>
					</div>
					<div class="col-sm-3">
						<a class="btn btn-default" @click="query">查询</a>
					</div>
				</div>
				<a class="btn btn-primary" @click="exportExcel"><i
					class="fa fa-print"></i>&nbsp;导出(excel)</a>
			</div>
			<table id="jqGrid"></table>
			<div id="jqGridPager"></div>
		</div>
<!-- 		<div v-show="false" style="display : none;"> -->
<!-- 			<form id="excelForm" action="school/list/info/export.do" method="post"></form> -->
<!-- 		</div> -->
	</div>

	<script src="../js/common.js"></script><script src="js/studentInfoListUI.js"></script>
</body>
</html>