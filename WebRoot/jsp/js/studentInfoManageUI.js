$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'school/manage/info/list.do',
		datatype : "json",
		colModel : [
			{
				label : 'ID',
				name : 'id',
				index : "id",
				width : 35,
				key : true
			},
			{
				label : '证件号码',
				name : 'stuCertificatesNum',
				index : "stuCertificatesNum",
				width : 85
			},
			{
				label : '学生姓名',
				name : 'stuName',
				index : "stuName",
				width : 75
			},
			{
				label : '性别',
				name : 'stuSex',
				index : "stuSex",
				width : 55,
				formatter : function(value, options, row) {
					if (value === 0) {
						return "男";
					} else if (value === 1) {
						return "女";
					} else {
						return "";
					}
				}
			},
			{
				label : '出生日期',
				name : 'stuBirthday',
				index : "stuBirthday",
				width : 80,
				formatter : function(value, options, row) {
					var dateSeconds = parseInt(value);
					var date = new Date(dateSeconds);
					date = date.Format("yyyy-MM-dd");
					return date;
				}
			},
			{
				label : '录取状态',
				name : 'user.enrollStatus',
				index : "user.enrollStatus",
				width : 55,
				formatter : function(value, options, row) {
					if (value === 2) {
						return '<span class="label label-success">已录取</span>';
					} else if (value === 3) {
						return '<span class="label label-danger">未录取</span>';
					} else {
						return "";
					}
				}
			},
		],
		viewrecords : true,
		height : 385,
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		rownumbers : true,
		rownumWidth : 25,
		autowidth : true,
		multiselect : true,
		pager : "#jqGridPager",
		jsonReader : {
			root : "page.list",
			page : "page.currPage",
			total : "page.totalPage",
			records : "page.totalCount"
		},
		prmNames : {
			page : "page",
			rows : "limit",
			order : "order"
		},
		gridComplete : function() {
			//隐藏grid底部滚动条
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
				"overflow-x" : "hidden"
			});
		}
	});
});

var vm = new Vue({
	el : '#eapp',
	data : {
		q : {
			key : null,
			stuSex : null,
			enrollStatus : null
		},
		showList : true,
		display : true,
		isStuFamilyMember1 : false,
		isStuFamilyMember2 : false,
		isShowStuDetailInfo : false,
		isUpdateStuInfo : false,
		isImg : false,
		isImgUpload : false,
		isWidthImg : false,
		isHeightImg : false,
		certificatesImgUrl : '',
		addImgUrl : baseURL + 'static/images/student/addImg.png',
		width : 0,
		isName : true,
		isCertificatesNum : true,
		isFamilyMember1Name : true,
		isFamilyMember2Name : true,
		isPhoneNum : true,
		isEnroll : false,
		isReCheck : false,
		nameError : "",
		certificatesNumError : "",
		familyMember1NameError : "",
		familyMember2NameError : "",
		phoneNumError : "",
		prov : 1,
		city : null,
		district : null,
		isNull : true,
		provBirth : 1,
		cityBirth : null,
		districtBirth : null,
		isBirthNull : true,
		provNation : 1,
		cityNation : null,
		districtNation : null,
		isNationNull : true,
		provHouse : 1,
		cityHouse : null,
		districtHouse : null,
		isHouseNull : true,
		title : null,
		stuCertificatesImgurl : "",
		stuSex : "",
		stuHealthCondition : "",
		stuMarried : "",
		stuStudyType : "",
		stuComeFrom : "",
		stuSuiqian : "",
		stuDomicile : "",
		stuHouseHoldType : "",
		stuFamily1Guardian : "",
		stuFamily2Guardian : "",
		stuStudyWay : "",
		stuEnrolWay : "",
		stuCertificatesType : "",
		stuEnrolObject : "",
		stuPoliticalOutlook : "",
		stuNation : "",
		stuEnrolType : "",
		stuNationality : "",
		stuEntranceWay : "",
		stuHKMTOC : "",
		stuType : "",
		stuMajor : "",
		stuLength : "",
		relationShip1 : "",
		relationShip2 : "",
		stuArea : "",
		birthArea : "",
		nationArea : "",
		houseArea : "",
		remnant : 255,
		stuMap : {},
		stuFamilyMember1 : {
			relationShip : {
				id : 0
			}
		},
		stuFamilyMember2 : {
			relationShip : {
				id : 0
			}
		},
		stuClass : {},
		stuInfo : {
			stuDomicile : 0,
			stuHouseHoldType : 0,
			stuHealthCondition : 0,
			user : {},
			stuClass : {
				id : 0
			},
			stuUploadFile : {
				id : 0
			},
			stuCertificatesType : {
				id : 1
			},
			stuEntranceWay : {
				id : 1
			},
			stuNationality : {
				id : 1
			},
			stuHKMTOC : {
				id : 1
			},
			stuBirthArea : {
				id : 3
			},
			stuArea : {
				id : 3
			},
			stuNationArea : {
				id : 3
			},
			stuHouseArea : {
				id : 3
			},
			stuMajor : {
				majorId : 1
			},
			stuLength : {
				id : 1
			},
			stuNation : {
				id : 1
			},
			stuPoliticalOutlook : {
				id : 1
			},
			stuEnrolObject : {
				id : 1
			},
			stuEnrolType : {
				id : 1
			},
			stuType : {
				id : 1
			},
			stuFamilyMember1 : {
				relationShip : {
					id : 1
				}
			},
			stuFamilyMember2 : {
				relationShip : {
					id : 1
				}
			}
		},
		stuCertificatesTypes : [],
		stuEnrolObjects : [],
		stuPoliticalOutlooks : [],
		stuNations : [],
		stuEnrolTypes : [],
		stuNationalitys : [],
		stuEntranceWays : [],
		stuHKMTOCs : [],
		stuTypes : [],
		stuMajors : [],
		stuLengths : [],
		stuAreas : [],
		stuBirthAreas : [],
		stuNationAreas : [],
		stuHouseAreas : [],
		relationShips : [],
		stuAreaArr : areaArr,
		stuAreaCityArr : [],
		stuAreaDistrictArr : [],
		stuBirthAreaArr : areaArr,
		stuBirthAreaCityArr : [],
		stuBirthAreaDistrictArr : [],
		stuNationAreaArr : areaArr,
		stuNationAreaCityArr : [],
		stuNationAreaDistrictArr : [],
		stuHouseAreaArr : areaArr,
		stuHouseAreaCityArr : [],
		stuHouseAreaDistrictArr : [],
	},
	methods : {
		query : function() {
			vm.reload();
		},
		descInput : function(){
			this.remnant = 255 - this.stuInfo.user.message.length;
		},
		print : function() {
			var id = getSelectedRow();
			if (id == null) {
				return;
			}

			//			vm.getStudentInfo(id);
			//			setPrintData(vm.stuMap);			
			//			document.getElementById("printForm").submit();

			window.open(baseURL + "printWord.jsp?id=" + id);
		},
		detail : function() {
			var id = getSelectedRow();
			if (id == null) {
				return;
			}

			vm.getStudentInfo(id);
			vm.title = "学生信息";
			vm.showList = false;
			vm.isUpdateStuInfo = false;
			vm.isReCheck = false;
			vm.isShowStuDetailInfo = true;
		},
		updateStuInfo : function() {
			var id = getSelectedRow();
			if (id == null) {
				return;
			}

			vm.getStudentInfoToUpdate(id);
			vm.title = "修改学生信息";
			vm.showList = false;
			vm.isShowStuDetailInfo = false;
			vm.isReCheck = false;
			vm.isUpdateStuInfo = true;
		},
		del : function() {
			var ids = getSelectedRows();
			if (ids == null) {
				return;
			}
			confirm('确定要删除选中的学生信息？', function() {
				$.ajax({
					type : "POST",
					url : baseURL + "school/manage/info/delete.do",
					contentType : "application/json",
					data : JSON.stringify(ids),
					success : function(r) {
						if (r.code == 0) {
							alert('操作成功');
							vm.reload();
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdate : function() {
			if(!vm.isImg){
				alert("请上传证件照片！");
				return;
			}
			vm.imgUpload();			
		},
		save : function() {
			vm.getArea();
			var url = "school/enroll/update.do";
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.stuInfo),
				success : function(r) {
					if (r.code === 0) {
						vm.showList = true;
						vm.isUpdateStuInfo = false;
						vm.reload();
						alert("已成功修改该学生信息！");
					} else {
						alert(r.msg);
					}
				}
			});
		},
		imgUpload : function(){
			if(vm.isImgUpload){
				vm.save();
				return;
			}
			var url = vm.stuInfo.stuUploadFile.id == null ? "school/upload/upload.do" : "school/upload/reUpload.do";
			var formData = new FormData();
			formData.append("file", document.getElementById("fileInput").files[0]);
			formData.append("uploadId", vm.stuInfo.stuUploadFile.id);
			$.ajax({
				url : baseURL + url + "?token=" + token,
				type : "POST",
				data : formData,
				contentType : false,
				processData : false,
				success : function(r){
					if(r.code == 0){
						vm.stuInfo.stuUploadFile.id = r.uploadFile.id;
						vm.isImgUpload = true;
						vm.save();
					}
				},
				erorr : function(){
					alert("图片上传失败！");
					return;
				}
			});
		},
		delImg : function(){
			vm.isImg = false;
			vm.certificatesImgUrl = '';
		},
		getFileInput : function(){
			$('input[id=fileInput]').click();
		},
		reCheck : function() {
			if(vm.isShowStuDetailInfo){
				vm.title = "重新审核学生信息";
				vm.isReCheck = true;
			}else{
				var id = getSelectedRow();
				if (id == null) {
					return;
				}

				vm.getStudentInfo(id);
				vm.title = "重新审核学生信息";
				vm.showList = false;
				vm.isUpdateStuInfo = false;
				vm.isShowStuDetailInfo = true;
				vm.isReCheck = true;
			}
		},
		pass : function(){
//			alert(vm.stuInfo.user.userId);
			confirm("'确定审核通过？'", function() {
				$.ajax({
					type : "POST",
					url : baseURL + "school/info/pass.do",
					contentType : "application/json",
					data : JSON.stringify(vm.stuInfo.user),
					success : function(r) {
						if (r.code == 0) {
							alert('操作成功');
							vm.reload();
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		nopass : function(){
			confirm("'确定审核不通过？'", function() {
				$.ajax({
					type : "POST",
					url : baseURL + "school/info/nopass.do",
					contentType : "application/json",
					data : JSON.stringify(vm.stuInfo.user),
					success : function(r) {
						if (r.code == 0) {
							alert('操作成功');
							vm.reload();
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		getStaticInfo : function() {
			$.get(baseURL + "school/enroll/select.do", function(r) {
				vm.stuMajors = r.stuMajors;
				vm.stuLengths = r.stuLengths;
				for (var i = 0; i < vm.stuMajors.length; i++) {
					if (vm.stuInfo.stuMajor.majorId == vm.stuMajors[i].majorId) {
						$("#length").val(vm.stuMajors[i].majorLength.lengthName);
					}
				}
			});
		},
		getStudentInfo : function(id) {
			$.ajax({
				type : "POST",
				url : baseURL + "school/manage/info/info.do",
				data : "id=" + id,
				success : function(r) {
					vm.stuInfo = r.stuInfoEntity;
					if (vm.stuInfo.stuFamilyMember1 == null) {
						vm.isStuFamilyMember1 = false;
						vm.stuInfo.stuFamilyMember1 = vm.stuFamilyMember1;
					} else {
						vm.isStuFamilyMember1 = true;
						for (var i = 0; i < vm.relationShips.length; i++) {
							if (vm.relationShips[i].id == vm.stuInfo.stuFamilyMember1.relationShip.id) {
								vm.relationShip1 = vm.relationShips[i].name;
							}
						}
					}
					if (vm.stuInfo.stuFamilyMember2 == null) {
						vm.isStuFamilyMember2 = false;
						vm.stuInfo.stuFamilyMember2 = vm.stuFamilyMember2;
					} else {
						vm.isStuFamilyMember2 = true;
						for (var i = 0; i < vm.relationShips.length; i++) {
							if (vm.relationShips[i].id == vm.stuInfo.stuFamilyMember2.relationShip.id) {
								vm.relationShip2 = vm.relationShips[i].name;
							}
						}
					}
					if (vm.stuInfo.stuClass == null) {
						vm.stuInfo.stuClass = vm.stuClass;
					}
					
					if (vm.stuInfo.user != null) {
						if(vm.stuInfo.user.enrollStatus == 2){
							vm.isEnroll = true;
						}
					}

					if (r.stuInfoEntity.stuUploadFile != null) {
						vm.stuCertificatesImgurl = baseURL + vm.stuInfo.stuUploadFile.url;
						if (document.getElementById('imgWH1').naturalWidth > document.getElementById('imgWH1').naturalHeight) {
							vm.width = 266;
						}else{
							vm.width = 176;
						}
					//						alert(vm.stuInfo.stuUploadFile.url);
					} else {
						vm.stuCertificatesImgurl = baseURL + "static/images/student/noPicture.png";
					}

					vm.setStaticInfo(vm.stuInfo);
					vm.stuInfo.stuBirthday = formatDate(vm.stuInfo.stuBirthday);
					vm.setArea(vm.stuInfo, vm.stuAreas);
				}
			});
		},
		getStudentInfoToUpdate : function(id) {
			$.ajax({
				type : "POST",
				url : baseURL + "school/enroll/info.do",
				data : "id=" + id,
				success : function(r) {
					if (r.stuInfoEntity != null) {
						vm.stuInfo.id = r.stuInfoEntity.id;
						vm.stuInfo.stuName = r.stuInfoEntity.stuName;
						vm.stuInfo.stuSex = r.stuInfoEntity.stuSex;
						vm.stuInfo.stuBirthday = formatDate(r.stuInfoEntity.stuBirthday);
						vm.stuInfo.stuCertificatesNum = r.stuInfoEntity.stuCertificatesNum;
						vm.stuInfo.stuPinyinName = r.stuInfoEntity.stuPinyinName;
						vm.stuInfo.stuClassName = r.stuInfoEntity.stuClassName;
						vm.stuInfo.stuIsAllDay = r.stuInfoEntity.stuIsAllDay;
						vm.stuInfo.stuStudyWay = r.stuInfoEntity.stuStudyWay;
						vm.stuInfo.stuIsMarried = r.stuInfoEntity.stuIsMarried;
						vm.stuInfo.stuTrainSection = r.stuInfoEntity.stuTrainSection;
						vm.stuInfo.stuIsSuiqian = r.stuInfoEntity.stuIsSuiqian;
						vm.stuInfo.stuDetailAdress = r.stuInfoEntity.stuDetailAdress;
						vm.stuInfo.stuPoliceStation = r.stuInfoEntity.stuPoliceStation;
						vm.stuInfo.stuHouseHoldType = r.stuInfoEntity.stuHouseHoldType;
						vm.stuInfo.stuDomicile = r.stuInfoEntity.stuDomicile;
						vm.stuInfo.stuHealthCondition = r.stuInfoEntity.stuHealthCondition;
						vm.stuInfo.stuPhoneNum = r.stuInfoEntity.stuPhoneNum;
						vm.stuInfo.stuIsCurrent = r.stuInfoEntity.stuIsCurrent;
						vm.stuInfo.stuEnrolWay = r.stuInfoEntity.stuEnrolWay;
						if (r.stuInfoEntity.stuFamilyMember1 != null) {
							vm.stuInfo.stuFamilyMember1 = r.stuInfoEntity.stuFamilyMember1;
						}
						if (r.stuInfoEntity.stuFamilyMember2 != null) {
							vm.stuInfo.stuFamilyMember2 = r.stuInfoEntity.stuFamilyMember2;
						}
						if (r.stuInfoEntity.stuUploadFile != null) {
							vm.stuInfo.stuUploadFile = r.stuInfoEntity.stuUploadFile;
							vm.certificatesImgUrl = baseURL + r.stuInfoEntity.stuUploadFile.url;
							vm.isImg = true;
							vm.isImgUpload = true;
							if (document.getElementById('imgWH').naturalWidth > document.getElementById('imgWH').naturalHeight) {
								vm.width = 266;
							}else{
								vm.width = 176;
							}
						}
						if (r.stuInfoEntity.stuType != null) {
							vm.stuInfo.stuType.id = r.stuInfoEntity.stuType.id;
						}
						if (r.stuInfoEntity.stuCertificatesType != null) {
							vm.stuInfo.stuCertificatesType.id = r.stuInfoEntity.stuCertificatesType.id;
						}
						if (r.stuInfoEntity.stuEntranceWay != null) {
							vm.stuInfo.stuEntranceWay.id = r.stuInfoEntity.stuEntranceWay.id;
						}
						if (r.stuInfoEntity.stuNationality != null) {
							vm.stuInfo.stuNationality.id = r.stuInfoEntity.stuNationality.id;
						}
						if (r.stuInfoEntity.stuHKMTOC != null) {
							vm.stuInfo.stuHKMTOC.id = r.stuInfoEntity.stuHKMTOC.id;
						}
						if (r.stuInfoEntity.stuMajor != null) {
							vm.stuInfo.stuMajor.majorId = r.stuInfoEntity.stuMajor.majorId;
						}
						if (r.stuInfoEntity.stuLength != null) {
							vm.stuInfo.stuLength.id = r.stuInfoEntity.stuLength.id;
						}
						if (r.stuInfoEntity.stuNation != null) {
							vm.stuInfo.stuNation.id = r.stuInfoEntity.stuNation.id;
						}
						if (r.stuInfoEntity.stuPoliticalOutlook != null) {
							vm.stuInfo.stuPoliticalOutlook.id = r.stuInfoEntity.stuPoliticalOutlook.id;
						}
						if (r.stuInfoEntity.stuEnrolObject != null) {
							vm.stuInfo.stuEnrolObject.id = r.stuInfoEntity.stuEnrolObject.id;
						}
						if (r.stuInfoEntity.stuEnrolType != null) {
							vm.stuInfo.stuEnrolType.id = r.stuInfoEntity.stuEnrolType.id;
						}

						vm.setAreaToUpdate(r.stuInfoEntity, placesArr);
					}
				}
			});
		},
		setArea : function(stuInfo, areaArr) {
			for (var i = 0; i < areaArr.length; i++) {
				if (areaArr[i].id == stuInfo.stuArea.id) {
					if (stuInfo.stuArea.level == 2) {
						vm.stuArea = areaArr[areaArr[areaArr[i].parentId - 1].parentId - 1].name + areaArr[areaArr[i].parentId - 1].name + areaArr[i].name;
					} else if (stuInfo.stuArea.level == 1) {
						vm.stuArea = areaArr[areaArr[i].parentId - 1].name + areaArr[i].name;
					} else if (stuInfo.stuArea.level == 0) {
						vm.stuArea = areaArr[i].name;
					}
				}
				if (areaArr[i].id == stuInfo.stuBirthArea.id) {
					if (stuInfo.stuBirthArea.level == 2) {
						vm.birthArea = areaArr[areaArr[areaArr[i].parentId - 1].parentId - 1].name + areaArr[areaArr[i].parentId - 1].name + areaArr[i].name;
					} else if (stuInfo.stuBirthArea.level == 1) {
						vm.birthArea = areaArr[areaArr[i].parentId - 1].name + areaArr[i].name;
					} else if (stuInfo.stuBirthArea.level == 0) {
						vm.birthArea = areaArr[i].name;
					}
				}
				if (areaArr[i].id == stuInfo.stuNationArea.id) {
					if (stuInfo.stuNationArea.level == 2) {
						vm.nationArea = areaArr[areaArr[areaArr[i].parentId - 1].parentId - 1].name + areaArr[areaArr[i].parentId - 1].name + areaArr[i].name;
					} else if (stuInfo.stuNationArea.level == 1) {
						vm.nationArea = areaArr[areaArr[i].parentId - 1].name + areaArr[i].name;
					} else if (stuInfo.stuNationArea.level == 0) {
						vm.nationArea = areaArr[i].name;
					}
				}
				if (areaArr[i].id == stuInfo.stuHouseArea.id) {
					if (stuInfo.stuHouseArea.level == 2) {
						vm.houseArea = areaArr[areaArr[areaArr[i].parentId - 1].parentId - 1].name + areaArr[areaArr[i].parentId - 1].name + areaArr[i].name;
					} else if (stuInfo.stuHouseArea.level == 1) {
						vm.houseArea = areaArr[areaArr[i].parentId - 1].name + areaArr[i].name;
					} else if (stuInfo.stuHouseArea.level == 0) {
						vm.houseArea = areaArr[i].name;
					}
				}
				if (vm.stuArea != "" && vm.birthArea != "" && vm.nationArea != "" && vm.houseArea != "") {
					break;
				}
			}
		},
		change : function(id) {
			change(id);
		},
		getArea : function(){
			if(vm.city != null){
				if(vm.district != null){
					vm.stuInfo.stuArea.id = vm.district;
				}else{
					vm.stuInfo.stuArea.id = vm.city;
				}
			}else{
				vm.stuInfo.stuArea.id = vm.prov;
			}
			
			if(vm.cityBirth != null){
				if(vm.districtBirth != null){
					vm.stuInfo.stuBirthArea.id = vm.districtBirth;
				}else{
					vm.stuInfo.stuBirthArea.id = vm.cityBirth;
				}
			}else{
				vm.stuInfo.stuBirthArea.id = vm.provBirth;
			}
			
			if(vm.cityNation != null){
				if(vm.districtNation != null){
					vm.stuInfo.stuNationArea.id = vm.districtNation;
				}else{
					vm.stuInfo.stuNationArea.id = vm.cityNation;
				}
			}else{
				vm.stuInfo.stuNationArea.id = vm.provNation;
			}
			
			if(vm.cityHouse != null){
				if(vm.districtHouse != null){
					vm.stuInfo.stuHouseArea.id = vm.districtHouse;
				}else{
					vm.stuInfo.stuHouseArea.id = vm.cityHouse;
				}
			}else{
				vm.stuInfo.stuHouseArea.id = vm.provHouse;
			}			
		},
		setAreaToUpdate : function(stuInfo,areaArr) {
			for(var i=0; i<areaArr.length;i++){
				if(areaArr[i].id == stuInfo.stuArea.id){
					if(stuInfo.stuArea.level == 2){
						vm.prov = areaArr[areaArr[areaArr[i].parentId-1].parentId-1].id;
						vm.city = areaArr[areaArr[i].parentId-1].id;
						vm.district = areaArr[i].id;
					}else if(stuInfo.stuArea.level == 1){
						vm.prov = areaArr[areaArr[i].parentId-1].id;
						vm.city = areaArr[i].id;
						vm.district = null;
					}else if(stuInfo.stuArea.level == 0){
						vm.prov = areaArr[i].id;
						vm.city = null;
						vm.district = null;
					}					
				}
				if(areaArr[i].id == stuInfo.stuBirthArea.id){
					if(stuInfo.stuBirthArea.level == 2){
						vm.provBirth = areaArr[areaArr[areaArr[i].parentId-1].parentId-1].id;
						vm.cityBirth = areaArr[areaArr[i].parentId-1].id;
						vm.districtBirth = areaArr[i].id;
					}else if(stuInfo.stuBirthArea.level == 1){
						vm.provBirth = areaArr[areaArr[i].parentId-1].id;
						vm.cityBirth = areaArr[i].id;
						vm.districtBirth = null;
					}else if(stuInfo.stuBirthArea.level == 0){
						vm.provBirth = areaArr[i].id;
						vm.cityBirth = null;
						vm.districtBirth = null;
					}
				}
				if(areaArr[i].id == stuInfo.stuNationArea.id){
					if(stuInfo.stuNationArea.level == 2){
						vm.provNation = areaArr[areaArr[areaArr[i].parentId-1].parentId-1].id;
						vm.cityNation = areaArr[areaArr[i].parentId-1].id;
						vm.districtNation = areaArr[i].id;
					}else if(stuInfo.stuNationArea.level == 1){
						vm.provNation = areaArr[areaArr[i].parentId-1].id;
						vm.cityNation = areaArr[i].id;
						vm.districtNation = null;
					}else if(stuInfo.stuNationArea.level == 0){
						vm.provNation = areaArr[i].id;
						vm.cityNation = null;
						vm.districtNation = null;
					}
				}
				if(areaArr[i].id == stuInfo.stuHouseArea.id){
					if(stuInfo.stuHouseArea.level == 2){
						vm.provHouse = areaArr[areaArr[areaArr[i].parentId-1].parentId-1].id;
						vm.cityHouse = areaArr[areaArr[i].parentId-1].id;
						vm.districtHouse = areaArr[i].id;
					}else if(stuInfo.stuHouseArea.level == 1){
						vm.provHouse = areaArr[areaArr[i].parentId-1].id;
						vm.cityHouse = areaArr[i].id;
						vm.districtHouse = null;
					}else if(stuInfo.stuHouseArea.level == 0){
						vm.provHouse = areaArr[i].id;
						vm.cityHouse = null;
						vm.districtHouse = null;
					}
				}
			}
			this.isNull = false;
			this.isBirthNull = false;
			this.isNationNull = false;
			this.isHouseNull = false;
		},
		setStaticInfo : function(stuInfo) {

			for (var i = 0; i < vm.stuCertificatesTypes.length; i++) {
				if (vm.stuCertificatesTypes[i].id == stuInfo.stuCertificatesType.id) {
					vm.stuCertificatesType = vm.stuCertificatesTypes[i].name;
				}
			}
			for (var i = 0; i < vm.stuEnrolObjects.length; i++) {
				if (vm.stuEnrolObjects[i].id == stuInfo.stuEnrolObject.id) {
					vm.stuEnrolObject = vm.stuEnrolObjects[i].name;
				}
			}
			for (var i = 0; i < vm.stuPoliticalOutlooks.length; i++) {
				if (vm.stuPoliticalOutlooks[i].id == stuInfo.stuPoliticalOutlook.id) {
					vm.stuPoliticalOutlook = vm.stuPoliticalOutlooks[i].name;
				}
			}
			for (var i = 0; i < vm.stuCertificatesTypes.length; i++) {
				if (vm.stuCertificatesTypes[i].id == stuInfo.stuCertificatesType.id) {
					vm.stuCertificatesType = vm.stuCertificatesTypes[i].name;
				}
			}
			for (var i = 0; i < vm.stuNations.length; i++) {
				if (vm.stuNations[i].id == stuInfo.stuNation.id) {
					vm.stuNation = vm.stuNations[i].name;
				}
			}
			for (var i = 0; i < vm.stuEnrolTypes.length; i++) {
				if (vm.stuEnrolTypes[i].id == stuInfo.stuEnrolType.id) {
					vm.stuEnrolType = vm.stuEnrolTypes[i].name;
				}
			}
			for (var i = 0; i < vm.stuNationalitys.length; i++) {
				if (vm.stuNationalitys[i].id == stuInfo.stuNationality.id) {
					vm.stuNationality = vm.stuNationalitys[i].name;
				}
			}
			for (var i = 0; i < vm.stuEntranceWays.length; i++) {
				if (vm.stuEntranceWays[i].id == stuInfo.stuEntranceWay.id) {
					vm.stuEntranceWay = vm.stuEntranceWays[i].name;
				}
			}
			for (var i = 0; i < vm.stuHKMTOCs.length; i++) {
				if (vm.stuHKMTOCs[i].id == stuInfo.stuHKMTOC.id) {
					vm.stuHKMTOC = vm.stuHKMTOCs[i].name;
				}
			}
			for (var i = 0; i < vm.stuTypes.length; i++) {
				if (vm.stuTypes[i].id == stuInfo.stuType.id) {
					vm.stuType = vm.stuTypes[i].name;
				}
			}
			for (var i = 0; i < vm.stuMajors.length; i++) {
				if (vm.stuMajors[i].majorId == stuInfo.stuMajor.majorId) {
					vm.stuMajor = vm.stuMajors[i].majorName;
				}
			}
			for (var i = 0; i < vm.stuLengths.length; i++) {
				if (vm.stuLengths[i].id == stuInfo.stuLength.id) {
					vm.stuLength = vm.stuLengths[i].lengthName;
				}
			}
		},
		updateCity: function () {
			for (var i in this.stuAreaArr) {
				var obj = this.stuAreaArr[i];
				if (obj.id == this.prov) {
					this.stuAreaCityArr = obj.sub;
					break;
				}
			}
			if(this.isNull){
				this.city = this.stuAreaCityArr[1].id;
			}
		},
		updateDistrict: function () {
			for (var i in this.stuAreaCityArr) {
				var obj = this.stuAreaCityArr[i];
				if (obj.id == this.city) {
					this.stuAreaDistrictArr = obj.sub;
					break;
				}
			}
			if(this.isNull){
				if(this.stuAreaDistrictArr && this.stuAreaDistrictArr.length > 0 && this.stuAreaDistrictArr[1].id) {
					this.district = this.stuAreaDistrictArr[1].id;
				} else {
					this.district = this.stuAreaDistrictArr[1].id;
				}
			}
			this.isNull = true;		
		},
		updateBirthCity: function () {		
			for (var i in this.stuBirthAreaArr) {
				var obj = this.stuBirthAreaArr[i];
				if (obj.id == this.provBirth) {
					this.stuBirthAreaCityArr = obj.sub;
					break;
				}
			}
			if(this.isBirthNull){
				this.cityBirth = this.stuBirthAreaCityArr[1].id;
			}					
		},
		updateBirthDistrict: function () {
			for (var i in this.stuBirthAreaCityArr) {
				var obj = this.stuBirthAreaCityArr[i];
				if (obj.id == this.cityBirth) {
					this.stuBirthAreaDistrictArr = obj.sub;
					break;
				}
			}
			if(this.isBirthNull){
				if(this.stuBirthAreaDistrictArr && this.stuBirthAreaDistrictArr.length > 0 && this.stuBirthAreaDistrictArr[1].id) {
					this.districtBirth = this.stuBirthAreaDistrictArr[1].id;
				} else {
					this.districtBirth = this.stuBirthAreaDistrictArr[1].id;
				}
			}
			this.isBirthNull = true;
		},
		updateNationCity: function () {
			for (var i in this.stuNationAreaArr) {
				var obj = this.stuNationAreaArr[i];
				if (obj.id == this.provNation) {
					this.stuNationAreaCityArr = obj.sub;
					break;
				}
			}
			if(this.isNationNull){
				this.cityNation = this.stuNationAreaCityArr[1].id;
			}
		},
		updateNationDistrict: function () {
			for (var i in this.stuNationAreaCityArr) {
				var obj = this.stuNationAreaCityArr[i];
				if (obj.id == this.cityNation) {
					this.stuNationAreaDistrictArr = obj.sub;
					break;
				}
			}
			if(this.isNationNull){
				if(this.stuNationAreaDistrictArr && this.stuNationAreaDistrictArr.length > 0 && this.stuNationAreaDistrictArr[1].id) {
					this.districtNation = this.stuNationAreaDistrictArr[1].id;
				} else {
					this.districtNation = this.stuNationAreaDistrictArr[1].id;
				}
			}
			this.isNationNull = true;
		},
		updateHouseCity: function () {
			for (var i in this.stuHouseAreaArr) {
				var obj = this.stuHouseAreaArr[i];
				if (obj.id == this.provHouse) {
					this.stuHouseAreaCityArr = obj.sub;
					break;
				}
			}
			if(this.isHouseNull){
				this.cityHouse = this.stuHouseAreaCityArr[1].id;
			}
		},
		updateHouseDistrict: function () {
			for (var i in this.stuHouseAreaCityArr) {
				var obj = this.stuHouseAreaCityArr[i];
				if (obj.id == this.cityHouse) {
					this.stuHouseAreaDistrictArr = obj.sub;
					break;
				}
			}
			if(this.isHouseNull){
				if(this.stuHouseAreaDistrictArr && this.stuHouseAreaDistrictArr.length > 0 && this.stuHouseAreaDistrictArr[1].id) {
					this.districtHouse = this.stuHouseAreaDistrictArr[1].id;
				} else {
					this.districtHouse = this.stuHouseAreaDistrictArr[1].id;
				}
			}	
			this.isHouseNull = true;
		},
		reload : function() {
			vm.showList = true;
			vm.isStuFamilyMember1 = false;
			vm.isStuFamilyMember2 = false;
			vm.isShowStuDetailInfo = false;
			vm.isUpdateStuInfo = false;
			if (vm.q.stuSex == 'null') {
				vm.q.stuSex = null;
			}
			if (vm.q.enrollStatus == 'null') {
				vm.q.enrollStatus = null;
			}
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'key' : vm.q.key,
					'stuSex' : vm.q.stuSex,
					'enrollStatus' : vm.q.enrollStatus
				},
				page : page
			}).trigger("reloadGrid");
		}
	},
	beforeMount: function () {
		this.updateCity();
		this.updateDistrict();
		this.updateBirthCity();
		this.updateBirthDistrict();
		this.updateNationCity();
		this.updateNationDistrict();
		this.updateHouseCity();
		this.updateHouseDistrict();
	},
	watch: {
		prov: function () {
			this.updateCity();
			this.updateDistrict();
		},
		city: function () {
			this.updateDistrict();
		},
		provBirth: function () {
			this.updateBirthCity();
			this.updateBirthDistrict();
		},
		cityBirth: function () {
			this.updateBirthDistrict();
		},
		provNation: function () {
			this.updateNationCity();
			this.updateNationDistrict();
		},
		cityNation: function () {
			this.updateNationDistrict();
		},
		provHouse: function () {
			this.updateHouseCity();
			this.updateHouseDistrict();
		},
		cityHouse: function () {
			this.updateHouseDistrict();
		}
	},
	created : function() {
		this.stuAreas = placesArr;
		this.stuBirthAreas = placesArr;
		this.stuNationAreas = placesArr;
		this.stuHouseAreas = placesArr;
		this.stuCertificatesTypes = certificatesTypesArr;
		this.stuEnrolObjects = stuEnrolObjectsArr;
		this.stuPoliticalOutlooks = stuPoliticalOutlooksArr;
		this.stuNations = stuNationsArr;
		this.stuEnrolTypes = stuEnrolTypesArr;
		this.stuNationalitys = stuNationalitysArr;
		this.stuEntranceWays = stuEntranceWaysArr;
		this.stuHKMTOCs = stuHKMTOCsArr;
		this.stuTypes = stuTypesArr;
		this.relationShips = relationShipsArr;
		this.getStaticInfo();
	}
});

$(function() {
	
	$(function() {
		$('input[id=fileInput]').change(function() {
			var file = this.files[0];
			var fileType = getFileType($(this).val());
//			alert(fileType);
			if ("jpg" != fileType && "jpeg" != fileType && "bmp" != fileType && "png" != fileType && "gif" != fileType) {
				alert("请上传JPG,JPEG,BMP,PNG,GIF格式的图片");
			} else {
				if (file.size > 1024*1024) {
					alert("图片大小不能超过1MB");
				} else {
					var url = null;
					if (window.createObjectURL != undefined) { // basic
						url = window.createObjectURL(file);
					} else if (window.URL != undefined) { // mozilla(firefox)
						url = window.URL.createObjectURL(file);
					} else if (window.webkitURL != undefined) { // webkit or chrome
						url = window.webkitURL.createObjectURL(file);
					}
					vm.certificatesImgUrl = url;
					vm.isImg = true;
					vm.isImgUpload = false;
					//					alert(document.getElementById('imgWH').naturalWidth + ':' + document.getElementById('imgWH').naturalHeight);
					if (document.getElementById('imgWH').naturalWidth > document.getElementById('imgWH').naturalHeight) {
						vm.width = 266;
					} else {
						vm.width = 176;
					}
				}
			}
		});
	});
	
//	new AjaxUpload('#upload', {
//		action : baseURL + "school/enroll/upload.do?token=" + token,
//		name : 'file',
//		responseType : "json",
//		onSubmit : function(file, extension) { //file 文件名   extension 文件类型
//			if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
//				alert('只支持jpg、png、gif格式的图片！');
//				return false;
//			}
//		},
//		onComplete : function(file, r) {
//			if (r.code == 0) {
//				vm.certificatesImgUrl = baseURL + r.uploadFile.url;
//				vm.stuInfo.stuUploadFile.id = r.uploadFile.id;
//				vm.isImg = true;
//			} else {
//				alert(r.msg);
//			}
//		}
//	});
//		
//	new AjaxUpload('#reUpload', {
//		action : baseURL + "school/enroll/reUpload.do?token=" + token,
//		name : 'file',
//		responseType : "json",
//		onSubmit : function(file, extension) { //file 文件名   extension 文件类型
//			if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
//				alert('只支持jpg、png、gif格式的图片！');
//				return false;
//			}
//		},
//		onComplete : function(file, r) {
//			if (r.code == 0) {
//				vm.certificatesImgUrl = baseURL + r.uploadFile.url;
//				vm.stuInfo.stuUploadFile.id = r.uploadFile.id;
//				vm.isImg = true;
//			} else {
//				alert(r.msg);
//			}
//		}
//	});
});

function setBirthday(Birthday) {
	var stuCertificatesNum = $("#stuCertificatesNum").val();
	if (stuCertificatesNum == "") {
		vm.certificatesNumError = "身份证号码不能为空";
		vm.isCertificatesNum = false;
	} else if (!IdCardValidate(stuCertificatesNum)) {
		vm.certificatesNumError = "身份证号码格式错误或不存在";
		vm.isCertificatesNum = false;
	} else {
		var birthday = IdCardGet(stuCertificatesNum, 1);
		$("#stuBirthday").val(birthday);
		vm.stuInfo.stuBirthday = birthday;
	}
}

function change(id) {
	for (var i = 0; i < vm.stuMajors.length; i++) {
		if (id == vm.stuMajors[i].majorId) {
			$("#length").val(vm.stuMajors[i].majorLength.lengthName);
			vm.stuInfo.stuLength.id = vm.stuMajors[i].majorLength.id;
		}
	}
}

function checkName() {
	var stuName = $("#stuName").val();
	if (stuName == "") {
		vm.nameError = "姓名不能为空";
		vm.isName = false;
	} else if (!isChinese(stuName)) {
		vm.nameError = "请输入有效的姓名";
		vm.isName = false;
	} else {
		var pinyin = ChineseToPinyin(stuName);
		$("#stuPinyinName").val(pinyin);
		vm.stuInfo.stuPinyinName = pinyin;
	}
}

function checkFamilyMember1Name() {
	var name = $("#stuFamilyMember1Name").val();
	if (name == "") {
		vm.isFamilyMember1Name = true;
	} else if (!isChinese(name)) {
		vm.familyMember1NameError = "请输入有效的姓名";
		vm.isFamilyMember1Name = false;
	}
}

function checkFamilyMember2Name() {
	var name = $("#stuFamilyMember2Name").val();
	if (name == "") {
		vm.isFamilyMember2Name = true;
	} else if (!isChinese(name)) {
		vm.familyMember2NameError = "请输入有效的姓名";
		vm.isFamilyMember2Name = false;
	}
}

function checkPhoneNum() {
	var num = $("#stuPhoneNum").val();
	if (num == "") {
		vm.phoneNumError = "联系电话不能为空";
		vm.isPhoneNum = false;
	} else if (!isMobile(num) && !isTel(num)) {
		vm.phoneNumError = "请输入有效的电话号码";
		vm.isPhoneNum = false;
	}
}

function focus1() {
	vm.isName = true;
}

function focus2() {
	vm.isCertificatesNum = true;
}

function focus3() {
	vm.isFamilyMember1Name = true;
}

function focus4() {
	vm.isFamilyMember2Name = true;
}

function focus5() {
	vm.isPhoneNum = true;
}

function setPrintData(map) {
	map["stuName"] = vm.stuInfo.stuName;

	if (vm.stuInfo.stuSex == 0) {
		map["stuSex"] = "男";
		vm.stuSex = "男";
	} else if (vm.stuInfo.stuSex == 1) {
		map["stuSex"] = "女";
		vm.stuSex = "女";
	} else {
		map["stuSex"] = "";
		vm.stuSex = "";
	}

	map["stuCertificatesType"] = vm.stuCertificatesType;
	map["stuCertificatesNum"] = vm.stuInfo.stuCertificatesNum;
	map["stuNamePinyin"] = vm.stuInfo.stuPinyinName;
	map["stuBirthday"] = vm.stuInfo.stuBirthday;
	map["stuPoliceStation"] = vm.stuInfo.stuPoliceStation;
	map["stuPhoneNum"] = vm.stuInfo.stuPhoneNum;
	map["stuDetailAdress"] = vm.stuInfo.stuDetailAdress;

	if (vm.stuInfo.stuHealthCondition == 0) {
		map["stuHealthCondition"] = "健康良好";
		vm.stuHealthCondition = "健康良好";
	} else if (vm.stuInfo.stuHealthCondition == 1) {
		map["stuHealthCondition"] = "一般较弱";
		vm.stuHealthCondition = "一般较弱";
	} else if (vm.stuInfo.stuHealthCondition == 2) {
		map["stuHealthCondition"] = "有慢性病";
		vm.stuHealthCondition = "有慢性病";
	} else if (vm.stuInfo.stuHealthCondition == 3) {
		map["stuHealthCondition"] = "残疾";
		vm.stuHealthCondition = "残疾";
	} else {
		map["stuHealthCondition"] = "";
		vm.stuHealthCondition = "";
	}

	map["stuTrainSetion"] = vm.stuInfo.stuTrainSection;

	if (vm.stuInfo.stuIsMarried == 0) {
		map["stuMarried"] = "是";
		vm.stuMarried = "是";
	} else if (vm.stuInfo.stuIsMarried == 1) {
		map["stuMarried"] = "否";
		vm.stuMarried = "否";
	} else if (vm.stuInfo.stuIsMarried == 2) {
		map["stuMarried"] = "其它";
		vm.stuMarried = "其它";
	} else {
		map["stuSex"] = "";
		vm.stuMarried = "";
	}

	if (vm.stuInfo.stuIsAllDay == 0) {
		map["stuStudyType"] = "是";
		vm.stuStudyType = "是";
	} else if (vm.stuInfo.stuIsAllDay == 1) {
		map["stuStudyType"] = "否";
		vm.stuStudyType = "否";
	} else if (vm.stuInfo.stuIsAllDay == 2) {
		map["stuStudyType"] = "其它";
		vm.stuStudyType = "其它";
	} else {
		map["stuStudyType"] = "";
		vm.stuStudyType = "";
	}

	if (vm.stuInfo.stuIsCurrent == 0) {
		map["stuComeFrom"] = "是";
		vm.stuComeFrom = "是";
	} else if (vm.stuInfo.stuIsCurrent == 1) {
		map["stuComeFrom"] = "否";
		vm.stuComeFrom = "否";
	} else if (vm.stuInfo.stuIsCurrent == 2) {
		map["stuComeFrom"] = "其它";
		vm.stuComeFrom = "其它";
	} else {
		map["stuComeFrom"] = "";
		vm.stuComeFrom = "";
	}

	if (vm.stuInfo.stuIsSuiqian == 0) {
		map["stuSuiqian"] = "是";
		vm.stuSuiqian = "是";
	} else if (vm.stuInfo.stuIsSuiqian == 1) {
		map["stuSuiqian"] = "否";
		vm.stuSuiqian = "否";
	} else if (vm.stuInfo.stuIsSuiqian == 2) {
		map["stuSuiqian"] = "其它";
		vm.stuSuiqian = "其它";
	} else {
		map["stuSuiqian"] = "";
		vm.stuSuiqian = "";
	}

	if (vm.stuInfo.stuDomicile == 0) {
		map["stuDomicile"] = "农村";
		vm.stuDomicile = "农村";
	} else if (vm.stuInfo.stuDomicile == 1) {
		map["stuDomicile"] = "乡镇";
		vm.stuDomicile = "乡镇";
	} else if (vm.stuInfo.stuDomicile == 2) {
		map["stuDomicile"] = "县城";
		vm.stuDomicile = "县城";
	} else if (vm.stuInfo.stuDomicile == 3) {
		map["stuDomicile"] = "城市";
		vm.stuDomicile = "城市";
	} else {
		map["stuDomicile"] = "";
		vm.stuDomicile = "";
	}

	if (vm.stuInfo.stuHouseHoldType == 0) {
		map["stuHouseHoldType"] = "农业户口";
		vm.stuHouseHoldType = "农业户口";
	} else if (vm.stuInfo.stuHouseHoldType == 1) {
		map["stuHouseHoldType"] = "非农业户口";
		vm.stuHouseHoldType = "非农业户口";
	} else {
		map["stuHouseHoldType"] = "";
		vm.stuHouseHoldType = "";
	}

	map["stuEnrollObject"] = vm.stuEnrolObject;
	map["stuType"] = vm.stuType;
	map["stuPoliticalOutlook"] = vm.stuPoliticalOutlook;

	map["stuNation"] = vm.stuNation;
	map["stuEnrolType"] = vm.stuEnrolType;
	map["stuNationality"] = vm.stuNationality;
	map["stuHKMTOC"] = vm.stuHKMTOC;
	map["stuEntranceWay"] = vm.stuEntranceWay;
	map["stuMajor"] = vm.stuMajor;
	map["stuLength"] = vm.stuLength;
	map["stuArea"] = vm.stuArea;
	map["stuBirthArea"] = vm.birthArea;
	map["stuNationArea"] = vm.nationArea;

	map["stuHouseArea"] = vm.houseArea;

	if (vm.isStuFamilyMember1) {
		map["stufamily1Name"] = vm.stuInfo.stuFamilyMember1.name;
		map["stuFamily1Relation"] = vm.relationShip1;
		if (vm.stuInfo.stuFamilyMember1.guardian == 0) {
			map["stuFamily1Guardian"] = "是";
			vm.stuFamily1Guardian = "是";
		} else if (vm.stuInfo.stuFamilyMember1.guardian == 1) {
			map["stuFamily1Guardian"] = "否";
			vm.stuFamily1Guardian = "否";
		} else {
			map["stuFamily1Guardian"] = "";
			vm.stuFamily1Guardian = "";
		}
		map["stuFamily1Phone"] = vm.stuInfo.stuFamilyMember1.phoneNum;
	} else {
		map["stufamily1Name"] = "";
		map["stuFamily1Relation"] = "";
		map["stuFamily1Guardian"] = "";
		map["stuFamily1Phone"] = "";
	}

	if (vm.isStuFamilyMember2) {
		map["stufamily2Name"] = vm.stuInfo.stuFamilyMember2.name;
		map["stuFamily2Relation"] = vm.relationShip2;
		if (vm.stuInfo.stuFamilyMember2.guardian == 0) {
			map["stuFamily2Guardian"] = "是";
			vm.stuFamily2Guardian = "是";
		} else if (vm.stuInfo.stuFamilyMember2.guardian == 1) {
			map["stuFamily2Guardian"] = "否";
			vm.stuFamily2Guardian = "否";
		} else {
			map["stuFamily2Guardian"] = "";
			vm.stuFamily2Guardian = "";
		}
		map["stuFamily2Phone"] = vm.stuInfo.stuFamilyMember2.phoneNum;
	} else {
		map["stufamily2Name"] = "";
		map["stuFamily2Relation"] = "";
		map["stuFamily2Guardian"] = "";
		map["stuFamily2Phone"] = "";
	}

	if (vm.stuInfo.stuStudyWay == 0) {
		map["stuStudyWay"] = "走读";
		vm.stuStudyWay = "走读";
	} else if (vm.stuInfo.stuStudyWay == 1) {
		map["stuStudyWay"] = "住校";
		vm.stuStudyWay = "住校";
	} else {
		map["stuStudyWay"] = "";
		vm.stuStudyWay = "";
	}

	if (vm.stuInfo.stuEnrolWay == 0) {
		map["stuEnrolWay"] = "统一招生";
		vm.stuEnrolWay = "统一招生";
	} else if (vm.stuInfo.stuEnrolWay == 1) {
		map["stuEnrolWay"] = "自主招生";
		vm.stuEnrolWay = "自主招生";
	} else {
		map["stuEnrolWay"] = "";
		vm.stuEnrolWay = "";
	}
}