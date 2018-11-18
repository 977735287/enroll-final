
var vm = new Vue({
	el : '#eapp',
	data : {
		showList : false,
		isEnroll : false,
		title : null,
		stuCertificatesImgurl : "",
		width : 0,
		stuArea : "",
		birthArea : "",
		nationArea : "",
		houseArea : "",
		enrollMsg : "",
		stuFamilyMember1 : {
			relationShip : {
				id : null
			}
		},
		stuFamilyMember2 : {
			relationShip : {
				id : null
			}
		},
		stuInfo : {
			stuDomicile : null,
			stuHouseHoldType : null,
			stuHealthCondition : null,
			stuUploadFile : {
				id : null
			},
			stuCertificatesType : {
				id : null
			},
			stuEntranceWay : {
				id : null
			},
			stuNationality : {
				id : null
			},
			stuHKMTOC : {
				id : null
			},
			stuBirthArea : {
				id : null
			},
			stuArea : {
				id : null
			},
			stuNationArea : {
				id : null
			},
			stuHouseArea : {
				id : null
			},
			stuMajor : {
				majorId : null
			},
			stuLength : {
				id : null
			},
			stuNation : {
				id : null
			},
			stuPoliticalOutlook : {
				id : null
			},
			stuEnrolObject : {
				id : null
			},
			stuEnrolType : {
				id : null
			},
			stuType : {
				id : null
			},
			stuFamilyMember1 : {
				relationShip : {
					id : null
				}
			},
			stuFamilyMember2 : {
				relationShip : {
					id : null
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
		relationShips : []
	},
	methods : {
		getStaticInfo : function() {
			$.get(baseURL + "school/enroll/select.do", function(r) {
				vm.stuMajors = r.stuMajors;
				vm.stuLengths = r.stuLengths;
				vm.getStudentInfo(vm.stuAreas);
			});
		},
		getStudentInfo : function(stuAreas) {
			$.get(baseURL + "school/enroll/info.do", function(r) {
				if(r.stuInfoEntity != null){
					vm.stuInfo = r.stuInfoEntity;
					if(vm.stuInfo.stuFamilyMember1 == null){
						vm.stuInfo.stuFamilyMember1 = vm.stuFamilyMember1;
					}
					if(vm.stuInfo.stuFamilyMember2 == null){
						vm.stuInfo.stuFamilyMember2 = vm.stuFamilyMember2;
					}
					
					if(r.stuInfoEntity.stuUploadFile != null){
						vm.stuCertificatesImgurl = baseURL + vm.stuInfo.stuUploadFile.url;
						if (document.getElementById('imgWH').naturalWidth > document.getElementById('imgWH').naturalHeight) {
							vm.width = 166;
						}else{
							vm.width = 266;
						}
					}else{
						vm.stuCertificatesImgurl = baseURL + "/upload/images/noPicture.png";
					}
					
					vm.stuInfo.stuBirthday = formatDate(vm.stuInfo.stuBirthday);
					vm.setArea(vm.stuInfo,stuAreas);
				}else{
					vm.enrollMsg = "您还未填写报名信息，请先填写报名信息！";
					vm.showList = false;
					vm.isEnroll = true;
				}				
			});
		},
		getUserInfo : function() {
			$.get(baseURL + "school/enroll/userInfo.do", function(r) {
				vm.roleList = r.roleList;
				vm.user = r.userInfo;				
				for(var i=0; i<vm.user.roleIdList.length;i++){
					for(var j=0; j<vm.roleList.length;j++){
						if(vm.user.roleIdList[i] == vm.roleList[j].roleId){
							if(vm.roleList[j].roleName == "学生"){
								if(vm.user.enrollStatus == null){
									vm.enrollMsg = "您还未填写报名信息，请先填写报名信息！";
									vm.showList = false;
									vm.isEnroll = true;
								}else if(vm.user.enrollStatus == 0){
									vm.showList = true;
									vm.isEnroll = false;
									vm.stuAreas = placesArr;
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
									vm.getStaticInfo();
								}else{
									vm.enrollMsg = "已经报名，请等待录取通知！";
									vm.showList = false;
									vm.isEnroll = true;
								}
								break;
							}else{
								vm.enrollMsg = "您不是学生，请注册学生账号"; 
								vm.isEnroll = true;
							}			
						}
					}
				}
			});
		},
		confirmEnroll : function() {
			$.ajax({
				type : "POST",
				url : baseURL + "school/enroll/enroll.do",
				contentType : "application/json",
				success : function(r) {
					if (r.code === 0) {
						alert('报名成功，请等待通知！');
						vm.enrollMsg = "您已经报名！"; 
						vm.isEnroll = true;
						window.localtion.reload();
					} else {
						alert(r.msg);
					}
				}
			});
		},
		update : function() {
			$.ajax({
				type : "POST",
				url : baseURL + "school/enroll/update.do",
				success : function(r) {
					if (r.code === 0) {
						alert('操作成功');
						vm.reload();
					} else {
						alert(r.msg);
					}
				}
			});
		},
		setArea : function(stuInfo,areaArr) {
			for(var i=0; i<areaArr.length;i++){
				if(areaArr[i].id == stuInfo.stuArea.id){
					if(stuInfo.stuArea.level == 2){
						vm.stuArea = areaArr[areaArr[areaArr[i].parentId-1].parentId-1].name + areaArr[areaArr[i].parentId-1].name + areaArr[i].name;
					}else if(stuInfo.stuArea.level == 1){
						vm.stuArea = areaArr[areaArr[i].parentId-1].name + areaArr[i].name;
					}else if(stuInfo.stuArea.level == 0){
						vm.stuArea = areaArr[i].name;
					}					
				}
				if(areaArr[i].id == stuInfo.stuBirthArea.id){
					if(stuInfo.stuBirthArea.level == 2){
						vm.birthArea = areaArr[areaArr[areaArr[i].parentId-1].parentId-1].name + areaArr[areaArr[i].parentId-1].name + areaArr[i].name;
					}else if(stuInfo.stuBirthArea.level == 1){
						vm.birthArea = areaArr[areaArr[i].parentId-1].name + areaArr[i].name;
					}else if(stuInfo.stuBirthArea.level == 0){
						vm.birthArea = areaArr[i].name;
					}
				}
				if(areaArr[i].id == stuInfo.stuNationArea.id){
					if(stuInfo.stuNationArea.level == 2){
						vm.nationArea = areaArr[areaArr[areaArr[i].parentId-1].parentId-1].name + areaArr[areaArr[i].parentId-1].name + areaArr[i].name;
					}else if(stuInfo.stuNationArea.level == 1){
						vm.nationArea = areaArr[areaArr[i].parentId-1].name + areaArr[i].name;
					}else if(stuInfo.stuNationArea.level == 0){
						vm.nationArea = areaArr[i].name;
					}
				}
				if(areaArr[i].id == stuInfo.stuHouseArea.id){
					if(stuInfo.stuHouseArea.level == 2){
						vm.houseArea = areaArr[areaArr[areaArr[i].parentId-1].parentId-1].name + areaArr[areaArr[i].parentId-1].name + areaArr[i].name;
					}else if(stuInfo.stuHouseArea.level == 1){
						vm.houseArea = areaArr[areaArr[i].parentId-1].name + areaArr[i].name;
					}else if(stuInfo.stuHouseArea.level == 0){
						vm.houseArea = areaArr[i].name;
					}
				}
				if(vm.stuArea != "" && vm.birthArea != "" && vm.nationArea != "" && vm.houseArea != ""){
					break;
				}
			}
		},
		reload : function() {}
	},
	created : function() {
		this.getUserInfo();
	},
	updated : function() {}
});