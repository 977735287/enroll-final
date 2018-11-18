<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>其它展示</title>
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
<link rel="stylesheet" href="../static/css/imageShow/htmleaf-demo.css">
<link rel="stylesheet"
	href="../static/css/imageShow/baguetteBox.min.css">
<link rel="stylesheet"
	href="../static/css/imageShow/thumbnail-gallery.css">

<script src="../static/js/jquery.min.js"></script>
<script src="../static/js/plugins/layer/layer.js"></script>
<script src="../static/js/bootstrap.min.js"></script>
<script src="../static/js/vue.min.js"></script>
<script src="../static/js/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="../static/js/plugins/jqgrid/jquery.jqGrid.min.js"></script>
<script src="../static/js/plugins/ztree/jquery.ztree.all.min.js"></script>

</head>

<body>
	<div id="eapp">
		<div class="htmleaf-container">
			<div class="container gallery-container">
				<h1>其它展示</h1>
<!-- 				<p class="page-description text-center">Thumbnails With Title -->
<!-- 					And Description</p> -->
				<div class="tz-gallery">
					<div class="row">
						<div class="col-sm-6 col-md-4">
							<div class="thumbnail">
								<a class="lightbox" href="../../enrol/images/others/优秀毕业生风采.jpg"> <img
									src="../../enrol/preShow/images/others/优秀毕业生风采.jpg" alt="优秀毕业生风采">
								</a>
								<div class="caption">
									<h3>优秀毕业生风采</h3>
									<p></p>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-md-4">
							<div class="thumbnail">
								<a class="lightbox" href="../../enrol/images/others/林业学校总平面图.jpg"> <img
									src="../../enrol/preShow/images/others/林业学校总平面图.jpg" alt="林业学校总平面图">
								</a>
								<div class="caption">
									<h3>林业学校总平面图</h3>
									<p></p>
								</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="../js/common.js"></script>
	<script type="text/javascript"
		src="../static/js/imageShow/baguetteBox.min.js"></script>
	<script type="text/javascript">
		baguetteBox.run('.tz-gallery');
	</script>
</body>
</html>

