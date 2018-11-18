<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>main</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
<link rel="stylesheet" href="../static/css/bootstrap.min.css">
<link rel="stylesheet" href="../static/css/font-awesome.min.css">
<link rel="stylesheet" href="../static/css/ui.jqgrid-bootstrap.css">
<link rel="stylesheet" href="../static/css/metroStyle/metroStyle.css">
<link rel="stylesheet" href="../static/css/main.css">

<script src="../static/js/jquery.min.js"></script>
<script src="../static/js/plugins/layer/layer.js"></script>
<script src="../static/js/bootstrap.min.js"></script>
<script src="../static/js/vue.min.js"></script>
<script src="../static/js/echarts.js"></script>
<script src="../static/js/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="../static/js/plugins/jqgrid/jquery.jqGrid.min.js"></script>
<script src="../static/js/plugins/ztree/jquery.ztree.all.min.js"></script>

</head>

<body>
	<div id="eapp" v-cloak>
		<!--Step:1 Prepare a dom for ECharts which (must) has size (width & hight)-->
		<!--Step:1 为ECharts准备一个具备大小（宽高）的Dom-->
		<div id="echarts-bar-chart" style="height:500px;padding:10px;"></div>
		
		<div class="col-sm-2" style="float:right;margin-right:96px">
			<select id="timeCol" class="form-control">
				<option value="0">全部</option>
				<option value="1">2017</option>
				<option value="2">2018</option>
			</select>
		</div>
	</div>

	<script type="text/javascript">
    
    </script>

	<script src="../js/common.js"></script>
	<script src="js/majorSexBarUI.js"></script>
</body>
</html>

