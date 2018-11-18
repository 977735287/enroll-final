<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="../static/css/bootstrap.min.css">
<link rel="stylesheet" href="../static/css/bootstrap.css">
<link rel="stylesheet" href="../static/css/font-awesome.min.css">
<link rel="stylesheet" href="../static/css/ui.jqgrid-bootstrap.css">
<link rel="stylesheet" href="../static/css/metroStyle/metroStyle.css">
<link rel="stylesheet" href="../static/css/main.css">

<!-- <link -->
<!-- 	href="https://cdn.bootcss.com/bootstrap/4.0.0-beta.3/css/bootstrap.css" -->
<!-- 	rel="stylesheet"> -->
<!-- <link -->
<!-- 	href="https://cdn.bootcss.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" -->
<!-- rel="stylesheet"> -->

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
		<div class="container" style="font-size: 14px">
			<div class="row clearfix">
				<div class="col-md-2 column"></div>
				<div class="col-md-8 column">
					<div class="table-responsive">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<td colspan="2" style="text-align: center"><strong>基本资料</strong></td>
								</tr>
								<tr>
									<td><strong>用户名</strong></td>
									<td>{{user.username}}</td>
								</tr>
								<tr>
									<td><strong>邮箱</strong></td>
									<td>{{user.email}}</td>
								</tr>
								<tr>
									<td><strong>电话</strong></td>
									<td>{{user.mobile}}</td>
								</tr>
								<tr>
									<td><strong>角色</strong></td>
									<td>{{role}}</td>
								</tr>
							</tbody>
						</table>
						<div class="form-group">
							<input type="button" class="btn btn-primary"
								style="font-size: 14px" @click="update" value="修改" />
						</div>
					</div>
				</div>
				<div class="col-md-2 column"></div>
			</div>
			<div v-show="showList">
				<div class="row clearfix">
					<div class="col-md-1 column"></div>
					<div class="col-md-10 column">
						<div class="table-responsive">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<td colspan="4" style="text-align: center"><strong>学生信息</strong></td>
									</tr>
									<tr>
										<td><strong>姓名</strong></td>
										<td>{{stuInfo.stuName}}</td>
										<td><strong>性别</strong></td>
										<td v-if="stuInfo.stuSex==0">男</td>
										<td v-else-if="stuInfo.stuSex==1">女</td>
										<td v-else></td>
									</tr>
									<tr>
										<td><strong>证件类型</strong></td>
										<td>{{stuCertificatesType}}</td>
										<td><strong>证件号码</strong></td>
										<td>{{stuInfo.stuCertificatesNum}}</td>
									</tr>
									<tr>
										<td><strong>证件照片</strong></td>
										<td colspan="3"><img id="imgWH"
											:src="stuCertificatesImgurl" style="display:none"><img
											class="img-thumbnail" alt="证件图片" :src="stuCertificatesImgurl"
											:width="width" height="166"></td>
									</tr>
									<tr>
										<td><strong>姓名拼音</strong></td>
										<td>{{stuInfo.stuPinyinName}}</td>
										<td><strong>出生日期</strong></td>
										<td>{{stuInfo.stuBirthday}}</td>
									</tr>
									<tr>
										<td><strong>所属派出所名称</strong></td>
										<td>{{stuInfo.stuPoliceStation}}</td>
										<td><strong>联系电话</strong></td>
										<td>{{stuInfo.stuPhoneNum}}</td>
									</tr>
									<tr>
										<td><strong>详细地址</strong></td>
										<td colspan="3">{{stuInfo.stuDetailAdress}}</td>
									</tr>
									<tr>
										<td><strong>健康状况</strong></td>
										<td v-if="stuInfo.stuHealthCondition==0">健康良好</td>
										<td v-else-if="stuInfo.stuHealthCondition==1">一般较弱</td>
										<td v-else-if="stuInfo.stuHealthCondition==2">有慢性病</td>
										<td v-else-if="stuInfo.stuHealthCondition==3">残疾</td>
										<td v-else></td>
										<td><strong>火车区间</strong></td>
										<td>{{stuInfo.stuTrainSection}}</td>
									</tr>
									<tr>
										<td><strong>是否已婚</strong></td>
										<td v-if="stuInfo.stuIsMarried==0">是</td>
										<td v-else-if="stuInfo.stuIsMarried==1">否</td>
										<td v-else></td>
										<td><strong>是否全日制</strong></td>
										<td v-if="stuInfo.stuIsAllDay==0">是</td>
										<td v-else-if="stuInfo.stuIsAllDay==1">否</td>
										<td v-else></td>
									</tr>
									<tr>
										<td><strong>是否应届</strong></td>
										<td v-if="stuInfo.stuIsCurrent==0">是</td>
										<td v-else-if="stuInfo.stuIsCurrent==1">否</td>
										<td v-else></td>
										<td><strong>是否随迁子女</strong></td>
										<td v-if="stuInfo.stuIsSuiqian==0">是</td>
										<td v-else-if="stuInfo.stuIsSuiqian==1">否</td>
										<td v-else></td>
									</tr>
									<tr>
										<td><strong>居住地类型</strong></td>
										<td v-if="stuInfo.stuDomicile==0">农村</td>
										<td v-else-if="stuInfo.stuDomicile==1">乡镇</td>
										<td v-else-if="stuInfo.stuDomicile==2">县城</td>
										<td v-else-if="stuInfo.stuDomicile==3">城市</td>
										<td v-else></td>
										<td><strong>户口性质</strong></td>
										<td v-if="stuInfo.stuHouseHoldType==0">农业户口</td>
										<td v-else-if="stuInfo.stuHouseHoldType==1">非农业户口</td>
										<td v-else></td>
									</tr>
									<tr>
										<td><strong>招生对象</strong></td>
										<td>{{stuEnrolObject}}</td>
										<td><strong>学生类型</strong></td>
										<td>{{stuType}}</td>
									</tr>
									<tr>
										<td><strong>政治面貌</strong></td>
										<td>{{stuPoliticalOutlook}}</td>
										<td><strong>民族</strong></td>
										<td>{{stuNation}}</td>
									</tr>
									<tr>
										<td><strong>联招类型</strong></td>
										<td>{{stuEnrolType}}</td>
										<td><strong>国籍</strong></td>
										<td>{{stuNationality}}</td>
									</tr>
									<tr>
										<td><strong>港澳台华侨</strong></td>
										<td>{{stuHKMTOC}}</td>
										<td><strong>入学方式</strong></td>
										<td>{{stuEntranceWay}}</td>
									</tr>
									<tr>
										<td><strong>专业</strong></td>
										<td>{{stuMajor}}</td>
										<td><strong>学制</strong></td>
										<td>{{stuLength}}</td>
									</tr>
									<tr>
										<td><strong>生源地行政区</strong></td>
										<td>{{stuArea}}</td>
										<td><strong>出生地行政区</strong></td>
										<td>{{birthArea}}</td>
									</tr>
									<tr>
										<td><strong>籍贯地行政区</strong></td>
										<td>{{nationArea}}</td>
										<td><strong>户口地行政区</strong></td>
										<td>{{houseArea}}</td>
									</tr>
									<tr>
										<td colspan="4"></td>
									</tr>
									<tr>
										<td colspan="4" style="text-align: center"><strong>家庭成员</strong></td>
									</tr>
									<tr style="text-align: center">
										<td><strong>姓名</strong></td>
										<td><strong>电话</strong></td>
										<td><strong>是否监护人</strong></td>
										<td><strong>关系</strong></td>
									</tr>
									<tr v-show="isStuFamilyMember1" style="text-align: center">
										<td>{{stuInfo.stuFamilyMember1.name}}</td>
										<td>{{stuInfo.stuFamilyMember1.phoneNum}}</td>
										<td v-if="stuInfo.stuFamilyMember1.guardian==0">是</td>
										<td v-else-if="stuInfo.stuFamilyMember1.guardian==1">否</td>
										<td v-else></td>
										<td>{{relationShip1}}</td>
									</tr>
									<tr v-show="isStuFamilyMember2" style="text-align: center">
										<td>{{stuInfo.stuFamilyMember2.name}}</td>
										<td>{{stuInfo.stuFamilyMember2.phoneNum}}</td>
										<td v-if="stuInfo.stuFamilyMember2.guardian==0">是</td>
										<td v-else-if="stuInfo.stuFamilyMember2.guardian==1">否</td>
										<td v-else></td>
										<td>{{relationShip2}}</td>
									</tr>
									<tr v-show="!isStuFamilyMember1 && !isStuFamilyMember2">
										<td colspan="4" style="text-align: center">无</td>
									</tr>
								</tbody>
							</table>
						</div>

					</div>
					<div class="col-md-1 column"></div>
				</div>
			</div>
		</div>

		<!-- 修改用户信息 -->
		<div id="userInfoLayer" style="display: none;margin-top: 20px;">
			<form class="form-horizontal">
				<div class="form-group">
					<div class="form-group">
						<div class="col-sm-2 control-label">用户名</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" v-model="username" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 control-label">邮箱</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" v-model="email" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 control-label">电话</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" v-model="mobile" />
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>


	<script src="../js/staticInfo.js"></script>
	<script src="../js/common.js"></script>
	<script src="js/personalInfoUI.js"></script>
</body>
</html>