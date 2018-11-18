
var vm = new Vue({
	el : '#eapp',
	data : {
		showList : false,
		test : false,
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
		message : "",
		nameError : "",
		certificatesNumError : "",
		familyMember1NameError : "",
		familyMember2NameError : "",
		phoneNumError : "",
		title : null,
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
		stuInfo : {
			stuDomicile : 0,
			stuHouseHoldType : 0,
			stuHealthCondition : 0,
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
			stuSubsectionCulture : "非分段培养",
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
		user : {
			roleIdList : []
		},
		roleList : [],
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
		relationShips : [],
		subsectionCultures : [],
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
		getStaticInfo : function() {
			$.get(baseURL + "school/enroll/select.do", function(r) {
				vm.stuMajors = r.stuMajors;
				vm.stuLengths = r.stuLengths;
				vm.getStudentInfo();
				for (var i = 0; i < vm.stuMajors.length; i++) {
					if (vm.stuInfo.stuMajor.majorId == vm.stuMajors[i].majorId) {
						$("#length").val(vm.stuMajors[i].majorLength.lengthName);
					}
				}
			});
		},
		getStudentInfo : function() {
			$.get(baseURL + "school/enroll/info.do", function(r) {
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
						} else {
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

					vm.setArea(r.stuInfoEntity, placesArr);
				}

			});
		},
		getUserInfo : function() {
			$.get(baseURL + "school/enroll/userInfo.do", function(r) {
				vm.roleList = r.roleList;
				vm.user = r.userInfo;
				for (var i = 0; i < vm.user.roleIdList.length; i++) {
					for (var j = 0; j < vm.roleList.length; j++) {
						if (vm.user.roleIdList[i] == vm.roleList[j].roleId) {
							if (vm.roleList[j].roleName == "学生") {
								if (vm.user.enrollStatus == 0 || vm.user.enrollStatus == null) {
									vm.showList = true;
									vm.isEnroll = false;
									vm.stuCertificatesTypes = certificatesTypesArr;
									vm.stuEnrolObjects = stuEnrolObjectsArr;
									vm.stuPoliticalOutlooks = stuPoliticalOutlooksArr;
									vm.stuNations = stuNationsArr;
									vm.stuEnrolTypes = stuEnrolTypesArr;
									vm.stuNationalitys = stuNationalitysArr;
									vm.stuEntranceWays = stuEntranceWaysArr;
									vm.stuHKMTOCs = stuHKMTOCsArr;
									vm.stuTypes = stuTypesArr;
									vm.relationShips = relationShipsArr;
									vm.subsectionCultures = subsectionCultureArr;
									if (vm.user.mobile != null) {
										vm.stuInfo.stuPhoneNum = vm.user.mobile;
									}
									vm.getStaticInfo();
								} else {
									vm.showList = false;
									vm.isEnroll = true;
									vm.message = "您已报名，信息无法修改";
								}
								break;
							} else {
								vm.isEnroll = true;
								vm.message = "您不是学生，请注册学生账号";
							}
						}
					}
				}
			});
		},
		saveOrUpdate : function() {
			if (!vm.isImg) {
				alert("请上传证件照片！");
				return;
			}
			vm.imgUpload();
		},
		save : function() {
			vm.getArea();
			var url = vm.stuInfo.id == null ? "school/enroll/save.do" : "school/enroll/update.do";
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.stuInfo),
				success : function(r) {
					if (r.code === 0) {
						layer.open({
							type : 1,
							skin : 'layui-layer-molv',
							title : "",
							area : [ '400px', '100px' ],
							shadeClose : false,
							content : jQuery("#isSaveLayer"),
							btn : [ '是', '否' ],
							btn1 : function(index) {
								layer.close(index);
								parent.vm.navTitle = "确认报名";
								parent.document.getElementById("myframe").src = baseURL + "student/confirmEnrollInfo.jsp";
							}
						});
					} else {
						alert(r.msg);
					}
				}
			});

		},
		imgUpload : function() {
			if (vm.isImgUpload) {
				vm.save();
				return;
			}
			var url = vm.stuInfo.stuUploadFile.id == 0 ? "school/upload/upload.do" : "school/upload/reUpload.do";
			var formData = new FormData();
			formData.append("file", document.getElementById("fileInput").files[0]);
			formData.append("uploadId", vm.stuInfo.stuUploadFile.id);
			$.ajax({
				url : baseURL + url + "?token=" + token,
				type : "POST",
				data : formData,
				contentType : false,
				processData : false,
				success : function(r) {
					if (r.code == 0) {
						vm.stuInfo.stuUploadFile.id = r.uploadFile.id;
						vm.isImgUpload = true;
						vm.save();
					}
				},
				erorr : function() {
					alert("图片上传失败！");
					return;
				}
			});
		},
		delImg : function() {
			vm.isImg = false;
			vm.certificatesImgUrl = '';
		},
		change : function(id) {
			change(id);
		},
		getArea : function() {
			if (vm.city != null) {
				if (vm.district != null) {
					vm.stuInfo.stuArea.id = vm.district;
				} else {
					vm.stuInfo.stuArea.id = vm.city;
				}
			} else {
				vm.stuInfo.stuArea.id = vm.prov;
			}

			if (vm.cityBirth != null) {
				if (vm.districtBirth != null) {
					vm.stuInfo.stuBirthArea.id = vm.districtBirth;
				} else {
					vm.stuInfo.stuBirthArea.id = vm.cityBirth;
				}
			} else {
				vm.stuInfo.stuBirthArea.id = vm.provBirth;
			}

			if (vm.cityNation != null) {
				if (vm.districtNation != null) {
					vm.stuInfo.stuNationArea.id = vm.districtNation;
				} else {
					vm.stuInfo.stuNationArea.id = vm.cityNation;
				}
			} else {
				vm.stuInfo.stuNationArea.id = vm.provNation;
			}

			if (vm.cityHouse != null) {
				if (vm.districtHouse != null) {
					vm.stuInfo.stuHouseArea.id = vm.districtHouse;
				} else {
					vm.stuInfo.stuHouseArea.id = vm.cityHouse;
				}
			} else {
				vm.stuInfo.stuHouseArea.id = vm.provHouse;
			}
		},
		setArea : function(stuInfo, areaArr) {
			for (var i = 0; i < areaArr.length; i++) {
				if (areaArr[i].id == stuInfo.stuArea.id) {
					if (stuInfo.stuArea.level == 2) {
						vm.prov = areaArr[areaArr[areaArr[i].parentId - 1].parentId - 1].id;
						vm.city = areaArr[areaArr[i].parentId - 1].id;
						vm.district = areaArr[i].id;
					} else if (stuInfo.stuArea.level == 1) {
						vm.prov = areaArr[areaArr[i].parentId - 1].id;
						vm.city = areaArr[i].id;
						vm.district = null;
					} else if (stuInfo.stuArea.level == 0) {
						vm.prov = areaArr[i].id;
						vm.city = null;
						vm.district = null;
					}
				}
				if (areaArr[i].id == stuInfo.stuBirthArea.id) {
					if (stuInfo.stuBirthArea.level == 2) {
						vm.provBirth = areaArr[areaArr[areaArr[i].parentId - 1].parentId - 1].id;
						vm.cityBirth = areaArr[areaArr[i].parentId - 1].id;
						vm.districtBirth = areaArr[i].id;
					} else if (stuInfo.stuBirthArea.level == 1) {
						vm.provBirth = areaArr[areaArr[i].parentId - 1].id;
						vm.cityBirth = areaArr[i].id;
						vm.districtBirth = null;
					} else if (stuInfo.stuBirthArea.level == 0) {
						vm.provBirth = areaArr[i].id;
						vm.cityBirth = null;
						vm.districtBirth = null;
					}
				}
				if (areaArr[i].id == stuInfo.stuNationArea.id) {
					if (stuInfo.stuNationArea.level == 2) {
						vm.provNation = areaArr[areaArr[areaArr[i].parentId - 1].parentId - 1].id;
						vm.cityNation = areaArr[areaArr[i].parentId - 1].id;
						vm.districtNation = areaArr[i].id;
					} else if (stuInfo.stuNationArea.level == 1) {
						vm.provNation = areaArr[areaArr[i].parentId - 1].id;
						vm.cityNation = areaArr[i].id;
						vm.districtNation = null;
					} else if (stuInfo.stuNationArea.level == 0) {
						vm.provNation = areaArr[i].id;
						vm.cityNation = null;
						vm.districtNation = null;
					}
				}
				if (areaArr[i].id == stuInfo.stuHouseArea.id) {
					if (stuInfo.stuHouseArea.level == 2) {
						vm.provHouse = areaArr[areaArr[areaArr[i].parentId - 1].parentId - 1].id;
						vm.cityHouse = areaArr[areaArr[i].parentId - 1].id;
						vm.districtHouse = areaArr[i].id;
					} else if (stuInfo.stuHouseArea.level == 1) {
						vm.provHouse = areaArr[areaArr[i].parentId - 1].id;
						vm.cityHouse = areaArr[i].id;
						vm.districtHouse = null;
					} else if (stuInfo.stuHouseArea.level == 0) {
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
		getFileInput : function() {
			$('input[id=fileInput]').click();
		},
		upload : function() {},
		reload : function() {},
		updateCity : function() {
			for (var i in this.stuAreaArr) {
				var obj = this.stuAreaArr[i];
				if (obj.id == this.prov) {
					this.stuAreaCityArr = obj.sub;
					break;
				}
			}
			if (this.isNull) {
				this.city = this.stuAreaCityArr[1].id;
			}
		},
		updateDistrict : function() {
			for (var i in this.stuAreaCityArr) {
				var obj = this.stuAreaCityArr[i];
				if (obj.id == this.city) {
					this.stuAreaDistrictArr = obj.sub;
					break;
				}
			}
			if (this.isNull) {
				if (this.stuAreaDistrictArr && this.stuAreaDistrictArr.length > 0 && this.stuAreaDistrictArr[1].id) {
					this.district = this.stuAreaDistrictArr[1].id;
				} else {
					this.district = this.stuAreaDistrictArr[1].id;
				}
			}
			this.isNull = true;
		},
		updateBirthCity : function() {
			for (var i in this.stuBirthAreaArr) {
				var obj = this.stuBirthAreaArr[i];
				if (obj.id == this.provBirth) {
					this.stuBirthAreaCityArr = obj.sub;
					break;
				}
			}
			if (this.isBirthNull) {
				this.cityBirth = this.stuBirthAreaCityArr[1].id;
			}
		},
		updateBirthDistrict : function() {
			for (var i in this.stuBirthAreaCityArr) {
				var obj = this.stuBirthAreaCityArr[i];
				if (obj.id == this.cityBirth) {
					this.stuBirthAreaDistrictArr = obj.sub;
					break;
				}
			}
			if (this.isBirthNull) {
				if (this.stuBirthAreaDistrictArr && this.stuBirthAreaDistrictArr.length > 0 && this.stuBirthAreaDistrictArr[1].id) {
					this.districtBirth = this.stuBirthAreaDistrictArr[1].id;
				} else {
					this.districtBirth = this.stuBirthAreaDistrictArr[1].id;
				}
			}
			this.isBirthNull = true;
		},
		updateNationCity : function() {
			for (var i in this.stuNationAreaArr) {
				var obj = this.stuNationAreaArr[i];
				if (obj.id == this.provNation) {
					this.stuNationAreaCityArr = obj.sub;
					break;
				}
			}
			if (this.isNationNull) {
				this.cityNation = this.stuNationAreaCityArr[1].id;
			}
		},
		updateNationDistrict : function() {
			for (var i in this.stuNationAreaCityArr) {
				var obj = this.stuNationAreaCityArr[i];
				if (obj.id == this.cityNation) {
					this.stuNationAreaDistrictArr = obj.sub;
					break;
				}
			}
			if (this.isNationNull) {
				if (this.stuNationAreaDistrictArr && this.stuNationAreaDistrictArr.length > 0 && this.stuNationAreaDistrictArr[1].id) {
					this.districtNation = this.stuNationAreaDistrictArr[1].id;
				} else {
					this.districtNation = this.stuNationAreaDistrictArr[1].id;
				}
			}
			this.isNationNull = true;
		},
		updateHouseCity : function() {
			for (var i in this.stuHouseAreaArr) {
				var obj = this.stuHouseAreaArr[i];
				if (obj.id == this.provHouse) {
					this.stuHouseAreaCityArr = obj.sub;
					break;
				}
			}
			if (this.isHouseNull) {
				this.cityHouse = this.stuHouseAreaCityArr[1].id;
			}
		},
		updateHouseDistrict : function() {
			for (var i in this.stuHouseAreaCityArr) {
				var obj = this.stuHouseAreaCityArr[i];
				if (obj.id == this.cityHouse) {
					this.stuHouseAreaDistrictArr = obj.sub;
					break;
				}
			}
			if (this.isHouseNull) {
				if (this.stuHouseAreaDistrictArr && this.stuHouseAreaDistrictArr.length > 0 && this.stuHouseAreaDistrictArr[1].id) {
					this.districtHouse = this.stuHouseAreaDistrictArr[1].id;
				} else {
					this.districtHouse = this.stuHouseAreaDistrictArr[1].id;
				}
			}
			this.isHouseNull = true;
		}
	},
	beforeMount : function() {
		this.updateCity();
		this.updateDistrict();
		this.updateBirthCity();
		this.updateBirthDistrict();
		this.updateNationCity();
		this.updateNationDistrict();
		this.updateHouseCity();
		this.updateHouseDistrict();
	},
	watch : {
		prov : function() {
			this.updateCity();
			this.updateDistrict();
		},
		city : function() {
			this.updateDistrict();
		},
		provBirth : function() {
			this.updateBirthCity();
			this.updateBirthDistrict();
		},
		cityBirth : function() {
			this.updateBirthDistrict();
		},
		provNation : function() {
			this.updateNationCity();
			this.updateNationDistrict();
		},
		cityNation : function() {
			this.updateNationDistrict();
		},
		provHouse : function() {
			this.updateHouseCity();
			this.updateHouseDistrict();
		},
		cityHouse : function() {
			this.updateHouseDistrict();
		}
	},
	created : function() {
		this.getUserInfo();
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

		//			alert("W:" + document.getElementById('imgWH').naturalWidth + "---H:" + document.getElementById('imgWH').naturalHeight);
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