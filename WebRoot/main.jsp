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
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/font-awesome.min.css">
<link rel="stylesheet" href="static/css/ui.jqgrid-bootstrap.css">
<link rel="stylesheet" href="static/css/metroStyle/metroStyle.css">
<link rel="stylesheet" href="static/css/main.css">

<script src="static/js/jquery.min.js"></script>
<script src="static/js/plugins/layer/layer.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/vue.min.js"></script>
<script src="static/js/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="static/js/plugins/jqgrid/jquery.jqGrid.min.js"></script>
<script src="static/js/plugins/ztree/jquery.ztree.all.min.js"></script>
<style type="text/css">
.glyphicon {
	margin-right: 4px !important; /*override*/
}

.panel .panel-body ul {
	padding: 0px;
	margin: 0px;
	list-style: none;
}

.panel ul a {
	float: right;
}

.panel-heading a {
	float: right;
	text-decoration: none;
}
</style>
</head>

<body>
	<div id="eapp" v-cloak>
		<div class="container">
			<div v-show="isStudent">
				<div class="row clearfix" style="margin-top:16px">
					<div>
						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title"></h3>
							</div>
							<div class="panel-body">

								<div class="col-md-3 column"></div>
								<div class="col-md-6 column">
								<div style="text-align:center">我的报名状态：{{message}}</div>							
								<div v-show="isCheckedEnroll" style="text-align:center"><div>&nbsp;</div>反馈信息：{{replyMessage}}</div>
								</div>
								<div class="col-md-3 column"></div>
							</div>
							<div class="panel-footer"></div>
						</div>
					</div>
				</div>
			</div>
			<div v-show="!isStudent">
				<div class="row clearfix" style="margin-top:16px">
					<div class="col-md-12 column">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title"></h3>
							</div>
							<div class="panel-body" style="text-align:center">
								<span class="blink"><a href="javascript:;"
									@click="checkStuInfo"
									style="text-decoration : none;color : #000000;">{{promptMessage}}</a></span>
							</div>
							<div class="panel-footer"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-list-alt"></span><b>招生信息</b>
					</div>
					<div v-show="!isLoading">
						<div style="padding:10px 10px 10px;">
							<div id="enrollInfo"></div>
						</div>
					</div>
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
			</div>
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-list-alt"></span><b>公告</b> <a
							href="javascript:;" @click="articleAll">更多>></a>
					</div>
					<div class="panel-body">
						<div v-show="!isALoading1">
							<div class="row">
								<div class="col-xs-12">
									<ul v-for="notice in noticeList">
										<li v-if="notice.artTitle.length > 16">
											({{notice.artPublishTime}})&nbsp;&nbsp;{{notice.artTitle.substring(0,16)}}...<a
											href="javascript:;" @click="detail(notice.id)">详细</a>
										</li>
										<li v-else>
											({{notice.artPublishTime}})&nbsp;&nbsp;{{notice.artTitle}}<a
											href="javascript:;" @click="detail(notice.id)">详细</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div v-show="isALoading1">
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
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6"></div>
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-list-alt"></span><b>政策</b> <a
							href="javascript:;" @click="articleAll">更多>></a>
					</div>
					<div class="panel-body">
						<div v-show="!isALoading2">
							<div class="row">
								<div class="col-xs-12">
									<ul v-for="policy in policyList">
										<li v-if="policy.artTitle.length > 16">
											({{policy.artPublishTime}})&nbsp;&nbsp;{{policy.artTitle.substring(0,16)}}...<a
											href="javascript:;" @click="detail(policy.id)">详细</a>
										</li>
										<li v-else>
											({{policy.artPublishTime}})&nbsp;&nbsp;{{policy.artTitle}}<a
											href="javascript:;" @click="detail(policy.id)">详细</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div v-show="isALoading1">
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
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="js/common.js"></script>
	<script src="js/mainUI.js"></script>
</body>
</html>

