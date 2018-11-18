<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生报名</title>
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
<script src="../static/js/ajaxupload.js"></script>

</head>
<body>
	<div id="eapp" v-cloak>
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<div v-show="showList">
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
						<input id="fileInput" type="file" style="display:none"
							accept="image/x-png,image/gif,image/jpeg,image/bmp"> <img
							id="imgWH" :src="certificatesImgUrl" style="display:none">
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
						<div class="col-sm-2 control-label">准考证号(选填)</div>
						<div class="col-sm-10">
							<input id="stuZKH" type="text" class="form-control"
								v-model="stuInfo.stuZKH" placeholder="准考证号" />
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
						<div class="col-sm-5">
							<input type="text" class="form-control"
								v-model="stuInfo.stuTrainSection" placeholder="A-B" />
						</div>
						<div class="col-sm-4">
						<div class="control-label" style="text-align:left">(例:桂林-南宁)</div>
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
							<select class="form-control" v-model="stuInfo.stuHealthCondition">
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
						<div class="col-sm-2 control-label">分段培养方式</div>
						<div class="col-sm-10">
							<select class="form-control" v-model="stuInfo.stuSubsectionCulture">
								<option v-for="subsectionCulture in subsectionCultures" v-bind:value="subsectionCulture.name">{{subsectionCulture.name}}</option>
							</select>
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
							<input id="stuFamilyMember1Name" type="text" class="form-control"
								v-model="stuInfo.stuFamilyMember1.name" placeholder="家庭成员1姓名"
								onblur="checkFamilyMember1Name()" onfocus="focus3()" />
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
							<input id="stuFamilyMember2Name" type="text" class="form-control"
								v-model="stuInfo.stuFamilyMember2.name" placeholder="家庭成员1姓名"
								onblur="checkFamilyMember2Name()" onfocus="focus4()" />
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
						<input type="button" class="btn btn-primary" @click="saveOrUpdate"
							value="保存" /> &nbsp;&nbsp;
					</div>
				</form>
			</div>
			<!-- 		<div v-show="test"> -->
			<!-- 			<img id="testImg" alt="" :src="certificatesImgUrl"> -->
			<!-- 		</div> -->
			<div v-show="isEnroll" style="text-align:center;margin-top:66px;">
				<h3>{{message}}</h3>
			</div>

			<div id="isSaveLayer"
				style="display: none;text-align:center;margin-top:30px;">
				您的信息已保存，是否前往确认报名页面？</div>
		</div>
		<div class="col-md-3"></div>
	</div>

	<script src="../js/areaArr.js"></script>
	<script src="../js/staticInfo.js"></script>
	<script src="../js/ChineseToPinyin.js"></script>
	<script src="../js/common.js"></script>
	<script src="js/studentEnrollUI.js"></script>
</body>
</html>