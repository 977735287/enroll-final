<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报名</title>
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
	<div class="col-md-3"></div>
		<div class="col-md-6">
		<div v-show="showList">
			<div class="panel-heading">{{title}}</div>
			<form class="form-horizontal" method="post">
				<div class="form-group">
					<div class="col-sm-2 control-label">姓名</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" v-model="stuInfo.stuName" disabled="disabled"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">性别</div>
					<label class="radio-inline"> <input type="radio"
						name="stuSex" value="0" v-model="stuInfo.stuSex" disabled="disabled"/> 男
					</label> <label class="radio-inline"> <input type="radio"
						name="stuSex" value="1" v-model="stuInfo.stuSex" disabled="disabled"/> 女
					</label>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">证件类型</div>
					<div class="col-sm-10">
						<select class="form-control"
							v-model="stuInfo.stuCertificatesType.id" disabled="disabled">
							<option v-for="stuCertificatesTyp in stuCertificatesTypes"
								v-bind:value="stuCertificatesTyp.id">{{stuCertificatesTyp.name}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">证件号码</div>
					<div class="col-sm-10">
						<input type="text" class="form-control"
							v-model="stuInfo.stuCertificatesNum" disabled="disabled"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">证件图片</div>
					<div class="col-sm-10">
					<img id="imgWH" :src="stuCertificatesImgurl" style="display:none"> 
					<img class="img-thumbnail" alt="证件图片" :src="stuCertificatesImgurl"
								:width="width" height="166">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">姓名拼音</div>
					<div class="col-sm-10">
						<input type="text" class="form-control"
							v-model="stuInfo.stuPinyinName" disabled="disabled"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">出生日期</div>
					<div class="col-sm-10">
						<input type="text" class="form-control"
							v-model="stuInfo.stuBirthday" disabled="disabled"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">所属派出所名称</div>
					<div class="col-sm-10">
						<input type="text" class="form-control"
							v-model="stuInfo.stuPoliceStation" disabled="disabled"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">联系电话</div>
					<div class="col-sm-10">
						<input type="text" class="form-control"
							v-model="stuInfo.stuPhoneNum" disabled="disabled"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">详细地址</div>
					<div class="col-sm-10">
						<input type="text" class="form-control"
							v-model="stuInfo.stuDetailAdress" disabled="disabled"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">火车区间</div>
					<div class="col-sm-10">
						<input type="text" class="form-control"
							v-model="stuInfo.stuTrainSection" disabled="disabled"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">是否已婚</div>
					<label class="radio-inline"> <input type="radio"
						name="stuIsMarried" value="0" v-model="stuInfo.stuIsMarried" disabled="disabled"/> 是
					</label> <label class="radio-inline"> <input type="radio"
						name="stuIsMarried" value="1" v-model="stuInfo.stuIsMarried" disabled="disabled"/> 否
					</label>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">是否全日制</div>
					<label class="radio-inline"> <input type="radio"
						name="stuIsAllDay" value="0" v-model="stuInfo.stuIsAllDay" disabled="disabled"/> 是
					</label> <label class="radio-inline"> <input type="radio"
						name="stuIsAllDay" value="1" v-model="stuInfo.stuIsAllDay" disabled="disabled"/> 否
					</label>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">是否应届</div>
					<label class="radio-inline"> <input type="radio"
						name="stuIsCurrent" value="0" v-model="stuInfo.stuIsCurrent" disabled="disabled"/> 是
					</label> <label class="radio-inline"> <input type="radio"
						name="stuIsCurrent" value="1" v-model="stuInfo.stuIsCurrent" disabled="disabled"/> 否
					</label>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">是否随迁子女</div>
					<label class="radio-inline"> <input type="radio"
						name="stuIsSuiqian" value="0" v-model="stuInfo.stuIsSuiqian" disabled="disabled"/> 是
					</label> <label class="radio-inline"> <input type="radio"
						name="stuIsSuiqian" value="1" v-model="stuInfo.stuIsSuiqian" disabled="disabled"/> 否
					</label>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">居住地类型</div>
					<div class="col-sm-10">
						<select class="form-control" v-model="stuInfo.stuDomicile" disabled="disabled">
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
						<select class="form-control" v-model="stuInfo.stuHouseHoldType" disabled="disabled">
							<option v-bind:value="0">农业户口</option>
							<option v-bind:value="1">非农业户口</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">健康状况</div>
					<div class="col-sm-10">
						<select class="form-control" v-model="stuInfo.stuHealthCondition" disabled="disabled">
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
						<select class="form-control" v-model="stuInfo.stuEnrolObject.id" disabled="disabled">
							<option v-for="stuEnrolObject in stuEnrolObjects"
								v-bind:value="stuEnrolObject.id">{{stuEnrolObject.name}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">政治面貌</div>
					<div class="col-sm-10">
						<select class="form-control"
							v-model="stuInfo.stuPoliticalOutlook.id" disabled="disabled">
							<option v-for="stuPoliticalOutlook in stuPoliticalOutlooks"
								v-bind:value="stuPoliticalOutlook.id">{{stuPoliticalOutlook.name}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">民族</div>
					<div class="col-sm-10">
						<select class="form-control" v-model="stuInfo.stuNation.id" disabled="disabled">
							<option v-for="stuNation in stuNations"
								v-bind:value="stuNation.id">{{stuNation.name}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">联招类型</div>
					<div class="col-sm-10">
						<select class="form-control" v-model="stuInfo.stuEnrolType.id" disabled="disabled">
							<option v-for="stuEnrolType in stuEnrolTypes"
								v-bind:value="stuEnrolType.id">{{stuEnrolType.name}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">国籍</div>
					<div class="col-sm-10">
						<select class="form-control" v-model="stuInfo.stuNationality.id" disabled="disabled">
							<option v-for="stuNationality in stuNationalitys"
								v-bind:value="stuNationality.id">{{stuNationality.name}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">港澳台华侨</div>
					<div class="col-sm-10">
						<select class="form-control" v-model="stuInfo.stuHKMTOC.id" disabled="disabled">
							<option v-for="stuHKMTOC in stuHKMTOCs"
								v-bind:value="stuHKMTOC.id">{{stuHKMTOC.name}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">入学方式</div>
					<div class="col-sm-10">
						<select class="form-control" v-model="stuInfo.stuEntranceWay.id" disabled="disabled">
							<option v-for="stuEntranceWay in stuEntranceWays"
								v-bind:value="stuEntranceWay.id">{{stuEntranceWay.name}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">学生类型</div>
					<div class="col-sm-10">
						<select class="form-control" v-model="stuInfo.stuType.id" disabled="disabled">
							<option v-for="stuType in stuTypes" v-bind:value="stuType.id">{{stuType.name}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">专业</div>
					<div class="col-sm-10">
						<select class="form-control" v-model="stuInfo.stuMajor.majorId" disabled="disabled">
							<option v-for="stuMajor in stuMajors"
								v-bind:value="stuMajor.majorId">{{stuMajor.majorName}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">学制</div>
					<div class="col-sm-10">
						<select class="form-control" v-model="stuInfo.stuLength.id" disabled="disabled">
							<option v-for="stuLength in stuLengths"
								v-bind:value="stuLength.id">{{stuLength.lengthName}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">生源地行政区</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" v-model="stuArea"
							disabled="disabled"/>
					</div>					
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">出生地行政区</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" v-model="birthArea"
							disabled="disabled"/>
					</div>	
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">籍贯地行政区</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" v-model="nationArea"
							disabled="disabled"/>
					</div>	
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">户口地行政区</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" v-model="houseArea"
							disabled="disabled"/>
					</div>	
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">家庭成员1姓名</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" v-model="stuInfo.stuFamilyMember1.name"
							disabled="disabled"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">家庭成员1电话</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" v-model="stuInfo.stuFamilyMember1.phoneNum"
							disabled="disabled"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">是否监护人</div>
					<label class="radio-inline"> <input type="radio"
						name="isGuardian1" value="0" v-model="stuInfo.stuFamilyMember1.guardian" disabled="disabled"/> 是
					</label> <label class="radio-inline"> <input type="radio"
						name="isGuardian1" value="1" v-model="stuInfo.stuFamilyMember1.guardian" disabled="disabled"/> 否
					</label>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">成员1关系</div>
					<div class="col-sm-10">
						<select class="form-control" v-model="stuInfo.stuFamilyMember1.relationShip.id" disabled="disabled">
							<option v-for="relationShip in relationShips"
								v-bind:value="relationShip.id">{{relationShip.name}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">家庭成员2姓名</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" v-model="stuInfo.stuFamilyMember2.name"
							disabled="disabled"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">家庭成员2电话</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" v-model="stuInfo.stuFamilyMember2.phoneNum"
							disabled="disabled"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">是否监护人</div>
					<label class="radio-inline"> <input type="radio"
						name="isGuardian2" value="0" v-model="stuInfo.stuFamilyMember2.guardian" disabled="disabled"/> 是
					</label> <label class="radio-inline"> <input type="radio"
						name="isGuardian2" value="1" v-model="stuInfo.stuFamilyMember2.guardian" disabled="disabled"/> 否
					</label>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">成员2关系</div>
					<div class="col-sm-10">
						<select class="form-control" v-model="stuInfo.stuFamilyMember2.relationShip.id" disabled="disabled">
							<option v-for="relationShip in relationShips"
								v-bind:value="relationShip.id">{{relationShip.name}}</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-2 control-label"></div>
					<input type="button" class="btn btn-primary" @click="confirmEnroll"
						value="确定" />
				</div>
			</form>
		</div>
		<div v-show="isEnroll" style="text-align:center;margin-top:66px;">
			<h3>{{enrollMsg}}</h3>
		</div>
		</div>
		<div class="col-md-3"></div>
	</div>

	<script src="../js/staticInfo.js"></script>
	<script src="../js/common.js"></script>
	<script src="js/confirmEnrollInfoUI.js"></script>
</body>
</html>