<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>print</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link href="favicon.ico" rel="Shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/font-awesome.min.css">
<link rel="stylesheet"
	href="static/js/plugins/jqgrid/ui.jqgrid-bootstrap.css">
<link rel="stylesheet"
	href="static/js/plugins/ztree/css/metroStyle/metroStyle.css">
<link rel="stylesheet" href="static/css/main.css">

<script src="static/js/jquery.min.js"></script>
<script src="static/js/plugins/layer/layer.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/vue.min.js"></script>
<script src="static/js/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="static/js/plugins/jqgrid/jquery.jqGrid.min.js"></script>
<script src="static/js/plugins/ztree/jquery.ztree.all.min.js"></script>

</head>
<body>
	<div id="eapp">		
		<div v-show="!display" style="display : none;">
			<form id="printForm" action="sys/print/word.do" method="post">
				<input type="text" name="stuName" v-model="stuInfo.stuName"/>
				<input type="text" name="stuSex" v-model="stuSex"/>
				<input type="text" name="stuCertificatesType" v-model="stuCertificatesType"/>
				<input type="text" name="stuCertificatesNum" v-model="stuInfo.stuCertificatesNum"/>
				<input type="text" name="stuNamePinyin" v-model="stuInfo.stuPinyinName"/>
				<input type="text" name="stuBirthday" v-model="stuInfo.stuBirthday"/>
				<input type="text" name="stuPoliceStation" v-model="stuInfo.stuPoliceStation"/>
				<input type="text" name="stuPhoneNum" v-model="stuInfo.stuPhoneNum"/>
				<input type="text" name="stuDetailAdress" v-model="stuInfo.stuDetailAdress"/>
				<input type="text" name="stuHealthCondition" v-model="stuHealthCondition"/>
				<input type="text" name="stuTrainSetion" v-model="stuInfo.stuTrainSection"/>
				<input type="text" name="stuMarried" v-model="stuMarried"/>
				<input type="text" name="stuStudyType" v-model="stuStudyType"/>
				<input type="text" name="stuComeFrom" v-model="stuComeFrom"/>
				<input type="text" name="stuSuiqian" v-model="stuSuiqian"/>
				<input type="text" name="stuDomicile" v-model="stuDomicile"/>
				<input type="text" name="stuHouseHoldType" v-model="stuHouseHoldType"/>
				<input type="text" name="stuEnrollObject" v-model="stuEnrolObject"/>
				<input type="text" name="stuType" v-model="stuType"/>
				<input type="text" name="stuPoliticalOutlook" v-model="stuPoliticalOutlook"/>
				<input type="text" name="stuNation" v-model="stuNation"/>
				<input type="text" name="stuEnrolType" v-model="stuEnrolType"/>
				<input type="text" name="stuNationality" v-model="stuNationality"/>
				<input type="text" name="stuHKMTOC" v-model="stuHKMTOC"/>
				<input type="text" name="stuEntranceWay" v-model="stuEntranceWay"/>
				<input type="text" name="stuMajor" v-model="stuMajor"/>
				<input type="text" name="stuLength" v-model="stuLength"/>
				<input type="text" name="stuArea" v-model="stuArea"/>
				<input type="text" name="stuBirthArea" v-model="birthArea"/>
				<input type="text" name="stuNationArea" v-model="nationArea"/>
				<input type="text" name="stuHouseArea" v-model="houseArea"/>
				<input type="text" name="stufamily1Name" v-model="stuInfo.stuFamilyMember1.name"/>
				<input type="text" name="stuFamily1Relation" v-model="relationShip1"/>
				<input type="text" name="stuFamily1Guardian" v-model="stuFamily1Guardian"/>
				<input type="text" name="stuFamily1Phone" v-model="stuInfo.stuFamilyMember1.phoneNum"/>
				<input type="text" name="stufamily2Name" v-model="stuInfo.stuFamilyMember2.name"/>
				<input type="text" name="stuFamily2Relation" v-model="relationShip2"/>
				<input type="text" name="stuFamily2Guardian" v-model="stuFamily2Guardian"/>
				<input type="text" name="stuFamily2Phone" v-model="stuInfo.stuFamilyMember2.phoneNum"/>
				<input type="text" name="stuStudyWay" v-model="stuStudyWay"/>
				<input type="text" name="stuEnrolWay" v-model="stuEnrolWay"/>
			</form>
		</div>	
		<div style="text-align : center;margin: 166px auto;">
			<a href="javascript:;" @click="upload"> 点击下载...... </a>
		</div>
			
	</div>

	<script src="js/staticInfo.js"></script>
	<script src="js/common.js"></script>
	<script src="js/printWordUI.js"></script>
</body>
</html>