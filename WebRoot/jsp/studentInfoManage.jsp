<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>已审核学生信息管理</title>
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
<script src="../static/js/ajaxupload.js"></script>

</head>
<body>
	<div id="eapp" v-cloak>
		<div v-show="showList">
			<div class="grid-btn">
				<div class="form-group col-sm-2">
					<input type="text" class="form-control" v-model="q.key"
						@keyup.enter="query" placeholder="学生姓名、身份证号码">
				</div>
				<div class="form-group col-sm-4">
					<div class="col-sm-5">
						<select class="form-control" v-model="q.enrollStatus">
							<option v-bind:value="null">录取状态</option>
							<option v-bind:value="2">已录取</option>
							<option v-bind:value="3">未录取</option>
						</select>
					</div>
					<div class="col-sm-4">
						<select class="form-control" v-model="q.stuSex">
							<option v-bind:value="null">性别</option>
							<option v-bind:value="0">男</option>
							<option v-bind:value="1">女</option>
						</select>
					</div>
					<div class="col-sm-3">
						<a class="btn btn-default" @click="query">查询</a>
					</div>
				</div>
				<a class="btn btn-primary" @click="reCheck"><i
					class="fa fa-check"></i>&nbsp;重新审核</a> <a class="btn btn-primary"
					@click="print"><i class="fa fa-print"></i>&nbsp;打印</a><a
					class="btn btn-primary" @click="detail"><i class="fa fa-info"></i>&nbsp;详细</a>
				<a class="btn btn-primary" @click="updateStuInfo"><i
					class="fa fa-pencil-square-o"></i>&nbsp;修改</a><a class="btn btn-danger"
					@click="del"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
			</div>
			<table id="jqGrid"></table>
			<div id="jqGridPager"></div>
		</div>

		<div v-show="isShowStuDetailInfo">
			<div class="container" style="font-size: 14px">
				<div class="row clearfix">
					<div class="col-md-1 column"></div>
					<div class="col-md-10 column">
						<div class="panel panel-default">
							<div class="panel-heading" style="text-align: center">{{title}}</div>
							<div class="table-responsive" style="margin: 0 auto">
								<table class="table table-bordered">
									<tbody>
										<!-- 						<tr> -->
										<!-- 							<td colspan="4" style="text-align: center"><strong>学生信息</strong></td> -->
										<!-- 						</tr> -->
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
											<td colspan="3"><img id="imgWH1"
												:src="stuCertificatesImgurl" style="display:none"> <img
												class="img-thumbnail" alt="证件图片"
												:src="stuCertificatesImgurl" :width="width" height="166"></td>
										</tr>
										<tr>
											<td><strong>姓名拼音</strong></td>
											<td>{{stuInfo.stuPinyinName}}</td>
											<td><strong>出生日期</strong></td>
											<td>{{stuInfo.stuBirthday}}</td>
										</tr>									
										<tr v-show="isEnroll">
											<td><strong>班级</strong></td>
											<td>{{stuInfo.stuClass.className}}</td>
											<td><strong>学号</strong></td>
											<td>{{stuInfo.stuNum}}</td>
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
						<div class="form-group">
							<div v-show="!isReCheck">
								<div class="col-sm-4 control-label"></div>
								<input type="button" class="btn btn-primary" @click="reCheck"
									value="重新审核" /> &nbsp;&nbsp;<input type="button"
									class="btn btn-warning" @click="reload" value="返回" />
							</div>
							<div v-show="isReCheck">
								<div class="col-sm-3">
									<div style="text-align:right">
										<div class="control-label">反馈信息</div>
									</div>
								</div>
								<div class="col-sm-6">
									<textarea class="form-control" rows="3" maxlength="255"
										@input="descInput" v-model="stuInfo.user.message"></textarea>
									<div style="text-align:right">
										<span>{{remnant}}/255</span>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="control-label"></div>
								</div>
								<div class="col-sm-12">&nbsp;</div>
								<div class="col-sm-12">
									<div class="col-sm-4 control-label"></div>
									<input type="button" class="btn btn-primary" @click="pass"
										value="通过" /> &nbsp;&nbsp;<input type="button"
										class="btn btn-primary" @click="nopass" value="不通过" />
									&nbsp;&nbsp;<input type="button" class="btn btn-warning"
										@click="reload" value="返回" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-1 column"></div>
				</div>
			</div>
		</div>

		<div v-show="isUpdateStuInfo">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div>
					<form class="form-horizontal" method="post">
						<div class="form-group">
							<div class="col-sm-2 control-label">姓名</div>
							<div class="col-sm-10">
								<input id="stuName" type="text" class="form-control"
									v-model="stuInfo.stuName" placeholder="姓名" onblur="checkName()"
									onfocus="focus1()" />
								<div v-show="!isName"
									style="color: #F00;font-size: 100%; margin-top: 6px">*{{nameError}}
								</div>
							</div>

						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">性别</div>
							<label class="radio-inline"> <input type="radio"
								name="stuSex" value="0" v-model="stuInfo.stuSex" /> 男
							</label> <label class="radio-inline"> <input type="radio"
								name="stuSex" value="1" v-model="stuInfo.stuSex" /> 女
							</label>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">证件类型</div>
							<div class="col-sm-10">
								<select class="form-control"
									v-model="stuInfo.stuCertificatesType.id">
									<option v-for="stuCertificatesType in stuCertificatesTypes"
										v-bind:value="stuCertificatesType.id">{{stuCertificatesType.name}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">证件号码</div>
							<div class="col-sm-10">
								<input id="stuCertificatesNum" type="text" class="form-control"
									v-model="stuInfo.stuCertificatesNum" placeholder="证件号码"
									onBlur="setBirthday()" onfocus="focus2()" />
								<div v-show="!isCertificatesNum"
									style="color: #F00;font-size: 100%; margin-top: 6px">*{{certificatesNumError}}
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">证件图片(正面)</div>
							<img id="imgWH" :src="certificatesImgUrl" style="display:none">
							<input id="fileInput" type="file" style="display:none"
								accept="image/x-png,image/gif,image/jpeg,image/bmp">
							<div v-show="!isImg" class="col-sm-10">
								<img class="img-thumbnail" alt="无图片" :src="addImgUrl"
									@click="getFileInput" width="100" height="100"
									style="cursor:pointer"> <input type="button"
									class="btn btn-primary" style="margin-left:6px"
									@click="getFileInput" value="选择" />
							</div>
							<div v-show="isImg" class="col-sm-10">
								<img class="img-thumbnail" alt="证件图片" :src="certificatesImgUrl"
									:width="width" height="166">
								<div class="form-group" style="margin-top:6px">
									<div class="col-sm-2">
										<input type="button" class="btn btn-primary"
											@click="getFileInput" value="重选" />
									</div>
									<div class="col-sm-2">
										<input type="button" class="btn btn-danger" @click="delImg"
											value="移除" />
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">姓名拼音</div>
							<div class="col-sm-10">
								<input id="stuPinyinName" type="text" class="form-control"
									v-model="stuInfo.stuPinyinName" placeholder="姓名拼音" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">出生日期</div>
							<div class="col-sm-10">
								<input id="stuBirthday" type="text" class="form-control"
									v-model="stuInfo.stuBirthday" placeholder="出生日期" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">所属派出所名称</div>
							<div class="col-sm-10">
								<input type="text" class="form-control"
									v-model="stuInfo.stuPoliceStation" placeholder="所属派出所名称" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">联系电话</div>
							<div class="col-sm-10">
								<input id="stuPhoneNum" type="text" class="form-control"
									v-model="stuInfo.stuPhoneNum" placeholder="联系电话"
									onblur="checkPhoneNum()" onfocus="focus5()" />
								<div v-show="!isPhoneNum"
									style="color: #F00;font-size: 100%; margin-top: 6px">*{{phoneNumError}}
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">详细地址</div>
							<div class="col-sm-10">
								<input type="text" class="form-control"
									v-model="stuInfo.stuDetailAdress" placeholder="详细地址" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">火车区间</div>
							<div class="col-sm-10">
								<input type="text" class="form-control"
									v-model="stuInfo.stuTrainSection" placeholder="A-B" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">是否已婚</div>
							<label class="radio-inline"> <input type="radio"
								name="stuIsMarried" value="0" v-model="stuInfo.stuIsMarried" />
								是
							</label> <label class="radio-inline"> <input type="radio"
								name="stuIsMarried" value="1" v-model="stuInfo.stuIsMarried" />
								否
							</label>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">是否全日制</div>
							<label class="radio-inline"> <input type="radio"
								name="stuIsAllDay" value="0" v-model="stuInfo.stuIsAllDay" /> 是
							</label> <label class="radio-inline"> <input type="radio"
								name="stuIsAllDay" value="1" v-model="stuInfo.stuIsAllDay" /> 否
							</label>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">是否应届</div>
							<label class="radio-inline"> <input type="radio"
								name="stuIsCurrent" value="0" v-model="stuInfo.stuIsCurrent" />
								是
							</label> <label class="radio-inline"> <input type="radio"
								name="stuIsCurrent" value="1" v-model="stuInfo.stuIsCurrent" />
								否
							</label>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">是否随迁子女</div>
							<label class="radio-inline"> <input type="radio"
								name="stuIsSuiqian" value="0" v-model="stuInfo.stuIsSuiqian" />
								是
							</label> <label class="radio-inline"> <input type="radio"
								name="stuIsSuiqian" value="1" v-model="stuInfo.stuIsSuiqian" />
								否
							</label>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">居住地类型</div>
							<div class="col-sm-10">
								<select class="form-control" v-model="stuInfo.stuDomicile">
									<option v-bind:value="0">农村</option>
									<option v-bind:value="1">乡镇</option>
									<option v-bind:value="2">县城</option>
									<option v-bind:value="3">城市</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">户口性质</div>
							<div class="col-sm-10">
								<select class="form-control" v-model="stuInfo.stuHouseHoldType">
									<option v-bind:value="0">农业户口</option>
									<option v-bind:value="1">非农业户口</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">健康状况</div>
							<div class="col-sm-10">
								<select class="form-control"
									v-model="stuInfo.stuHealthCondition">
									<option v-bind:value="0">健康良好</option>
									<option v-bind:value="1">一般较弱</option>
									<option v-bind:value="2">有慢性病</option>
									<option v-bind:value="3">残疾</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">招生对象</div>
							<div class="col-sm-10">
								<select class="form-control" v-model="stuInfo.stuEnrolObject.id">
									<option v-for="stuEnrolObject in stuEnrolObjects"
										v-bind:value="stuEnrolObject.id">{{stuEnrolObject.name}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">政治面貌</div>
							<div class="col-sm-10">
								<select class="form-control"
									v-model="stuInfo.stuPoliticalOutlook.id">
									<option v-for="stuPoliticalOutlook in stuPoliticalOutlooks"
										v-bind:value="stuPoliticalOutlook.id">{{stuPoliticalOutlook.name}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">民族</div>
							<div class="col-sm-10">
								<select class="form-control" v-model="stuInfo.stuNation.id">
									<option v-for="stuNation in stuNations"
										v-bind:value="stuNation.id">{{stuNation.name}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">联招类型</div>
							<div class="col-sm-10">
								<select class="form-control" v-model="stuInfo.stuEnrolType.id">
									<option v-for="stuEnrolType in stuEnrolTypes"
										v-bind:value="stuEnrolType.id">{{stuEnrolType.name}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">国籍</div>
							<div class="col-sm-10">
								<select class="form-control" v-model="stuInfo.stuNationality.id">
									<option v-for="stuNationality in stuNationalitys"
										v-bind:value="stuNationality.id">{{stuNationality.name}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">港澳台华侨</div>
							<div class="col-sm-10">
								<select class="form-control" v-model="stuInfo.stuHKMTOC.id">
									<option v-for="stuHKMTOC in stuHKMTOCs"
										v-bind:value="stuHKMTOC.id">{{stuHKMTOC.name}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">入学方式</div>
							<div class="col-sm-10">
								<select class="form-control" v-model="stuInfo.stuEntranceWay.id">
									<option v-for="stuEntranceWay in stuEntranceWays"
										v-bind:value="stuEntranceWay.id">{{stuEntranceWay.name}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">学生类型</div>
							<div class="col-sm-10">
								<select class="form-control" v-model="stuInfo.stuType.id">
									<option v-for="stuType in stuTypes" v-bind:value="stuType.id">{{stuType.name}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">专业</div>
							<div class="col-sm-10">
								<select id="major" class="form-control"
									v-model="stuInfo.stuMajor.majorId"
									@change="change(stuInfo.stuMajor.majorId)">
									<option v-for="stuMajor in stuMajors"
										v-bind:value="stuMajor.majorId">{{stuMajor.majorName}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">学制</div>
							<div class="col-sm-10">
								<input id="length" type="text" class="form-control"
									placeholder="学制" disabled="disabled" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">生源地行政区</div>
							<div class="col-sm-10">
								<select class="form-control-select" v-model="prov">
									<option v-for="stuArea in stuAreaArr" v-bind:value="stuArea.id">{{stuArea.name}}</option>
								</select> <select class="form-control-select" v-model="city">
									<option v-for="stuArea in stuAreaCityArr"
										v-bind:value="stuArea.id">{{stuArea.name}}</option>
								</select> <select class="form-control-select" v-model="district">
									<option v-for="stuArea in stuAreaDistrictArr"
										v-bind:value="stuArea.id">{{stuArea.name}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">出生地行政区</div>
							<div class="col-sm-10">
								<select class="form-control-select" v-model="provBirth">
									<option v-for="stuBirthArea in stuBirthAreaArr"
										v-bind:value="stuBirthArea.id">{{stuBirthArea.name}}</option>
								</select> <select class="form-control-select" v-model="cityBirth">
									<option v-for="stuBirthArea in stuBirthAreaCityArr"
										v-bind:value="stuBirthArea.id">{{stuBirthArea.name}}</option>
								</select> <select class="form-control-select" v-model="districtBirth">
									<option v-for="stuBirthArea in stuBirthAreaDistrictArr"
										v-bind:value="stuBirthArea.id">{{stuBirthArea.name}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">籍贯地行政区</div>
							<div class="col-sm-10">
								<select class="form-control-select" v-model="provNation">
									<option v-for="stuNationArea in stuNationAreaArr"
										v-bind:value="stuNationArea.id">{{stuNationArea.name}}</option>
								</select> <select class="form-control-select" v-model="cityNation">
									<option v-for="stuNationArea in stuNationAreaCityArr"
										v-bind:value="stuNationArea.id">{{stuNationArea.name}}</option>
								</select> <select class="form-control-select" v-model="districtNation">
									<option v-for="stuNationArea in stuNationAreaDistrictArr"
										v-bind:value="stuNationArea.id">{{stuNationArea.name}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">户口地行政区</div>
							<div class="col-sm-10">
								<select class="form-control-select" v-model="provHouse">
									<option v-for="stuHouseArea in stuHouseAreaArr"
										v-bind:value="stuHouseArea.id">{{stuHouseArea.name}}</option>
								</select> <select class="form-control-select" v-model="cityHouse">
									<option v-for="stuHouseArea in stuHouseAreaCityArr"
										v-bind:value="stuHouseArea.id">{{stuHouseArea.name}}</option>
								</select> <select class="form-control-select" v-model="districtHouse">
									<option v-for="stuHouseArea in stuHouseAreaDistrictArr"
										v-bind:value="stuHouseArea.id">{{stuHouseArea.name}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">家庭成员1姓名</div>
							<div class="col-sm-10">
								<input id="stuFamilyMember1Name" type="text"
									class="form-control" v-model="stuInfo.stuFamilyMember1.name"
									placeholder="家庭成员1姓名" onblur="checkFamilyMember1Name()"
									onfocus="focus3()" />
								<div v-show="!isFamilyMember1Name"
									style="color: #F00;font-size: 100%; margin-top: 6px">*{{familyMember1NameError}}
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">家庭成员1电话</div>
							<div class="col-sm-10">
								<input type="text" class="form-control"
									v-model="stuInfo.stuFamilyMember1.phoneNum"
									placeholder="家庭成员1电话" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">是否监护人</div>
							<label class="radio-inline"> <input type="radio"
								name="isGuardian1" value="0"
								v-model="stuInfo.stuFamilyMember1.guardian" /> 是
							</label> <label class="radio-inline"> <input type="radio"
								name="isGuardian1" value="1"
								v-model="stuInfo.stuFamilyMember1.guardian" /> 否
							</label>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">成员1关系</div>
							<div class="col-sm-10">
								<select class="form-control"
									v-model="stuInfo.stuFamilyMember1.relationShip.id">
									<option v-for="relationShip in relationShips"
										v-bind:value="relationShip.id">{{relationShip.name}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">家庭成员2姓名</div>
							<div class="col-sm-10">
								<input id="stuFamilyMember2Name" type="text"
									class="form-control" v-model="stuInfo.stuFamilyMember2.name"
									placeholder="家庭成员1姓名" onblur="checkFamilyMember2Name()"
									onfocus="focus4()" />
								<div v-show="!isFamilyMember2Name"
									style="color: #F00;font-size: 100%; margin-top: 6px">*{{familyMember2NameError}}
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">家庭成员2电话</div>
							<div class="col-sm-10">
								<input type="text" class="form-control"
									v-model="stuInfo.stuFamilyMember2.phoneNum"
									placeholder="家庭成员1电话" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">是否监护人</div>
							<label class="radio-inline"> <input type="radio"
								name="isGuardian2" value="0"
								v-model="stuInfo.stuFamilyMember2.guardian" /> 是
							</label> <label class="radio-inline"> <input type="radio"
								name="isGuardian2" value="1"
								v-model="stuInfo.stuFamilyMember2.guardian" /> 否
							</label>
						</div>
						<div class="form-group">
							<div class="col-sm-2 control-label">成员2关系</div>
							<div class="col-sm-10">
								<select class="form-control"
									v-model="stuInfo.stuFamilyMember2.relationShip.id">
									<option v-for="relationShip in relationShips"
										v-bind:value="relationShip.id">{{relationShip.name}}</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-2 control-label"></div>
							<input type="button" class="btn btn-primary"
								@click="saveOrUpdate" value="保存" /> &nbsp;&nbsp;<input
								type="button" class="btn btn-warning" @click="reload" value="返回" />
						</div>
					</form>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>

		<div v-show="!display">
			<form id="printForm" action="sys/print/word.do" method="post">
				<input type="text" name="stuName" v-model="stuInfo.stuName" /> <input
					type="text" name="stuSex" v-model="stuSex" /> <input type="text"
					name="stuCertificatesType" v-model="stuCertificatesType" /> <input
					type="text" name="stuCertificatesNum"
					v-model="stuInfo.stuCertificatesNum" /> <input type="text"
					name="stuNamePinyin" v-model="stuInfo.stuPinyinName" /> <input
					type="text" name="stuBirthday" v-model="stuInfo.stuBirthday" /> <input
					type="text" name="stuPoliceStation"
					v-model="stuInfo.stuPoliceStation" /> <input type="text"
					name="stuPhoneNum" v-model="stuInfo.stuPhoneNum" /> <input
					type="text" name="stuDetailAdress"
					v-model="stuInfo.stuDetailAdress" /> <input type="text"
					name="stuHealthCondition" v-model="stuHealthCondition" /> <input
					type="text" name="stuTrainSetion" v-model="stuInfo.stuTrainSection" />
				<input type="text" name="stuMarried" v-model="stuMarried" /> <input
					type="text" name="stuStudyType" v-model="stuStudyType" /> <input
					type="text" name="stuComeFrom" v-model="stuComeFrom" /> <input
					type="text" name="stuSuiqian" v-model="stuSuiqian" /> <input
					type="text" name="stuDomicile" v-model="stuDomicile" /> <input
					type="text" name="stuHouseHoldType" v-model="stuHouseHoldType" />
				<input type="text" name="stuEnrollObject" v-model="stuEnrolObject" />
				<input type="text" name="stuType" v-model="stuType" /> <input
					type="text" name="stuPoliticalOutlook"
					v-model="stuPoliticalOutlook" /> <input type="text"
					name="stuNation" v-model="stuNation" /> <input type="text"
					name="stuEnrolType" v-model="stuEnrolType" /> <input type="text"
					name="stuNationality" v-model="stuNationality" /> <input
					type="text" name="stuHKMTOC" v-model="stuHKMTOC" /> <input
					type="text" name="stuEntranceWay" v-model="stuEntranceWay" /> <input
					type="text" name="stuMajor" v-model="stuMajor" /> <input
					type="text" name="stuLength" v-model="stuLength" /> <input
					type="text" name="stuArea" v-model="stuArea" /> <input type="text"
					name="stuBirthArea" v-model="birthArea" /> <input type="text"
					name="stuNationArea" v-model="nationArea" /> <input type="text"
					name="stuHouseArea" v-model="houseArea" /> <input type="text"
					name="stufamily1Name" v-model="stuInfo.stuFamilyMember1.name" /> <input
					type="text" name="stuFamily1Relation" v-model="relationShip1" /> <input
					type="text" name="stuFamily1Guardian" v-model="stuFamily1Guardian" />
				<input type="text" name="stuFamily1Phone"
					v-model="stuInfo.stuFamilyMember1.phoneNum" /> <input type="text"
					name="stufamily2Name" v-model="stuInfo.stuFamilyMember2.name" /> <input
					type="text" name="stuFamily2Relation" v-model="relationShip2" /> <input
					type="text" name="stuFamily2Guardian" v-model="stuFamily2Guardian" />
				<input type="text" name="stuFamily2Phone"
					v-model="stuInfo.stuFamilyMember2.phoneNum" /> <input type="text"
					name="stuStudyWay" v-model="stuStudyWay" /> <input type="text"
					name="stuEnrolWay" v-model="stuEnrolWay" />
			</form>
		</div>
	</div>

	<script src="../js/areaArr.js"></script>
	<script src="../js/staticInfo.js"></script>
	<script src="../js/common.js"></script>
	<script src="../js/ChineseToPinyin.js"></script>
	<script src="js/studentInfoManageUI.js"></script>
</body>
</html>