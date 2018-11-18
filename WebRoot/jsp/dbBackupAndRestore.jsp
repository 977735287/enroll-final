<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据库备份与恢复</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
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

</head>
<body>
	<div id="eapp" v-cloak>
		<div class="container">
			<div class="col-md-6">
				<h3 class="text-center">数据库备份</h3>
				<form class="form-horizontal" method="post">
					<div class="form-group">
						<div class="col-sm-4 control-label">数据库备份文件名</div>
						<div class="col-sm-6">
							<input type="text" class="form-control" v-model="sqlName"
								placeholder="enroll.sql" />
						</div>
					</div>
					<div v-show="isBackupping" class="form-group">
						<div class="col-sm-4 control-label"></div>
						<div class="col-sm-6">
							<div :class="progressActive">
								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="60" aria-valuemin="0"
									aria-valuemax="100" :style="width">
									<span class="progress-text">{{valueText}}</span>
								</div>
							</div>
						</div>
						<div class="col-sm-4 control-label"></div>
						<div class="col-sm-6" style="margin-top:-16px">{{backupStatus}}</div>
					</div>
					<div class="form-group">
						<div class="col-sm-5 control-label"></div>
						<input type="button" class="btn btn-primary" @click="backup"
							value="备份" /> &nbsp;&nbsp;
					</div>
				</form>
			</div>
			<div class="col-md-6">
				<h3 class="text-center">数据库还原</h3>
				<form class="form-horizontal" method="post"
					enctype="multipart/form-data" id="restoreForm">
					<div class="form-group">
						<div class="col-sm-4 control-label">本地sql文件路径</div>
						<div class="col-sm-8">
							<!-- 							<input class="input-file" id="fileInput" name="fileInput" type="file"> -->
							<input id="fileInput" type="file" style="display:none"
								accept=".sql">
							<div class="input-group">
								<input type="text" class="form-control" id="fileInputText">
								<button class="btn input-group-addon" type="button"
									onclick="$('input[id=fileInput]').click();">Browse</button>
							</div>
						</div>
					</div>
					<div v-show="isRestoreing" class="form-group">
						<div class="col-sm-4 control-label"></div>
						<div class="col-sm-6">
							<div :class="progressActive1">
								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="60" aria-valuemin="0"
									aria-valuemax="100" :style="width1">
									<span class="progress-text">{{valueText1}}</span>
								</div>
							</div>
						</div>
						<div class="col-sm-4 control-label"></div>
						<div class="col-sm-6" style="margin-top:-16px">{{restoreStatus}}</div>
					</div>
					<div class="form-group">
						<div class="col-sm-5 control-label"></div>
						<input type="button" class="btn btn-primary" @click="restore"
							value="还原" /> &nbsp;&nbsp;
					</div>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			$('input[id=fileInput]').change(function() {
				if ("sql" != getFileType($(this).val())) {
					alert("请选择sql文件");					
					$(this).val("No file selected...");					
				} else {
					$('#fileInputText').val($(this).val());					
				}
			});
			$('input[id=fileInput]').each(function() {
				$('#fileInputText').val("No file selected...");
			});
		});
	</script>

	<script src="../js/common.js"></script>
	<script src="js/dbBackupAndRestoreUI.js"></script>
</body>
</html>