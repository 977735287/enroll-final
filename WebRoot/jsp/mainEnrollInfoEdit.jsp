<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>招生信息编辑</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="../static/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../static/js/datetimepicker/bootstrap-datetimepicker.min.css" />
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
<script
	src="../static/js/datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script src="../static/js/vue.min.js"></script>
<script src="../static/js/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="../static/js/plugins/jqgrid/jquery.jqGrid.min.js"></script>
<script src="../static/js/plugins/ztree/jquery.ztree.all.min.js"></script>

</head>
<body>
	<div id="eapp" v-cloak>
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<div v-show="showList">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-list-alt"></span><b>&nbsp;&nbsp;招生信息</b>
					</div>
					<div v-show="!isLoading"><div id="massage"></div></div>
					<div v-show="isLoading">
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
				<div class="form-group">
					<input type="button" class="btn btn-primary" @click="edit"
						value="编辑" />
				</div>
			</div>
			<div v-show="!showList">
				<div class="panel panel-default">
					<div class="form-group">
						<textarea id="content"></textarea>
					</div>
					<div class="form-group">
						<div style="float:left;">
							<input type="button" class="btn btn-primary"
								@click="update" value="保存" />
						</div>
						<div style="float:right;">
							<input type="button" class="btn btn-warning" @click="reload"
								value="返回" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-3"></div>


	</div>

	<script src="../js/common.js"></script>
	<script src="js/mainEnrollInfoEdit.js"></script>
</body>
</html>