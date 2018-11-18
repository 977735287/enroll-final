
var vm = new Vue({
	el : '#eapp',
	data : {
		showList : true,
		isStuFamilyMember1 : false,
		isStuFamilyMember2 : false,
		title : null,
		stuCertificatesImgurl : "",
		width : 0,
		username : "",
		email : "",
		mobile : "",
		role : "",
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
		stuFamilyMember1 : {
			relationShip : {
				id : 2
			}
		},
		stuFamilyMember2 : {
			relationShip : {
				id : 2
			}
		},
		user : {
			roleIdList : []
		},
		stuInfo : {			
			stuFamilyMember1 : {
				relationShip : {
					id : 0
				}
			},
			stuFamilyMember2 : {
				relationShip : {
					id : 0
				}
			}
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
		getUserInfo : function() {
			$.get(baseURL + "school/enroll/userInfo.do", function(r) {
				vm.roleList = r.roleList;
				vm.user = r.userInfo;
				vm.username = vm.user.username;
				vm.email = vm.user.email;
				vm.mobile = vm.user.mobile;
				for(var i=0; i<vm.user.roleIdList.length;i++){
					for(var j=0; j<vm.roleList.length;j++){
						if(vm.user.roleIdList[i] == vm.roleList[j].roleId){
							vm.role += vm.roleList[j].roleName + " ";
							if(vm.user.roleIdList[i] == 1 || vm.user.roleIdList[i] == 2){
								vm.showList = false;
							}else if(vm.user.roleIdList[i] == 3){
								vm.showList = true;
							}else{
								vm.showList = false;
							}
						}
					}
				}
				if(vm.showList){
					vm.getStaticInfo();
				}
			});
		},
		getStudentInfo : function(stuAreas) {
			$.get(baseURL + "school/enroll/info.do", function(r) {
				vm.stuInfo = r.stuInfoEntity;
				if(vm.stuInfo.stuFamilyMember1 == null){
					vm.stuInfo.stuFamilyMember1 = vm.stuFamilyMember1;
				}else{
					vm.isStuFamilyMember1 = true;
					for(var i=0; i<vm.relationShips.length;i++){				
						if(vm.relationShips[i].id == vm.stuInfo.stuFamilyMember1.relationShip.id){
							vm.relationShip1 = vm.relationShips[i].name;
						}
					}
					
				}
				if(vm.stuInfo.stuFamilyMember2 == null){
					vm.stuInfo.stuFamilyMember2 = vm.stuFamilyMember2;
				}else{
					vm.isStuFamilyMember2 = true;
					for(var i=0; i<vm.relationShips.length;i++){				
						if(vm.relationShips[i].id == vm.stuInfo.stuFamilyMember2.relationShip.id){
							vm.relationShip2 = vm.relationShips[i].name;
						}
					}
				}
				
				if(r.stuInfoEntity.stuUploadFile != null){
					vm.stuCertificatesImgurl = baseURL + vm.stuInfo.stuUploadFile.url;
					if (document.getElementById('imgWH').naturalWidth > document.getElementById('imgWH').naturalHeight) {
						vm.width = 266;
					}else{
						vm.width = 166;
					}
				}else{
					vm.stuCertificatesImgurl = baseURL + "/upload/images/noPicture.png";
				}
				vm.setStaticInfo(vm.stuInfo);
				vm.stuInfo.stuBirthday = formatDate(vm.stuInfo.stuBirthday);
				vm.setArea(vm.stuInfo,stuAreas);	
			});
		},
		update : function() {
			
			layer.open({
				type: 1,
				skin: 'layui-layer-molv',
				title: "修改基础信息",
				area: ['550px', '300px'],
				shadeClose: false,
				content: jQuery("#userInfoLayer"),
				btn: ['修改','取消'],
				btn1: function (index) {
					if(vm.username == ""){
						alert("用户名不能为空！");
					}else{
						vm.checkUserName(vm.username,index);
					}
	            }
			});
		},
		save : function(index) {
			var data = "username="+vm.username+"&email="+vm.email+"&mobile="+vm.mobile;
			$.ajax({
				type: "POST",
			    url: baseURL + "school/enroll/updateUserInfo.do",
			    data: data,
			    dataType: "json",
			    success: function(r){
					if(r.code == 0){
						layer.close(index);
						layer.alert('修改成功', function(){
							location.reload();
						});
					}else{
						layer.alert(r.msg);
					}
				}
			});
		},
		checkUserName : function(username,index) {
			var data = "username=" + username;
			$.ajax({
				type : "POST",
				url : baseURL + "/sys/user/checkUsername.do",
				data : data,
				dataType : "json",
				success : function(r) {
					if (r.code == 0) {
						if(vm.user.username == username){
							if(vm.email != ""){
								vm.checkUserEmail(vm.email,index);
							}else if(vm.mobile != ""){
								vm.checkUserPhone(vm.mobile,index);
							}else{
								vm.save(index);
							}
						}else{
							alert("用户名已经存在，请更换一个！");
						}
					} else {
						if(vm.email != ""){
							vm.checkUserEmail(vm.email,index);
						}else if(vm.mobile != ""){
							vm.checkUserPhone(vm.mobile,index);
						}else{
							vm.save(index);
						}
					}
				}
			});
		},
		checkUserPhone : function(phone,index) {
			var data = "phone=" + phone;
			$.ajax({
				type : "POST",
				url : baseURL + "/sys/user/checkPhoneIsUse.do",
				data : data,
				dataType : "json",
				success : function(r) {
					if (r.code == 0) {
						vm.save(index);				
					} else {
						if(vm.user.mobile == phone){
							vm.save(index);
						}else{
							alert(r.msg);
						}
					}
				}
			});
		},
		checkUserEmail : function(email,index) {
			var data = "email=" + email;
			$.ajax({
				type : "POST",
				url : baseURL + "/sys/user/checkEmailIsUse.do",
				data : data,
				dataType : "json",
				success : function(r) {
					if (r.code == 0) {
						if(vm.mobile != ""){
							vm.checkUserPhone(vm.mobile,index);
						} else {
							vm.save(index);
						}
					} else {
						if(vm.user.email == email){
							if(vm.mobile != ""){
								vm.checkUserPhone(vm.mobile,index);
							} else {
								vm.save(index);
							}
						}else{
							alert(r.msg);
						}						
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
		setStaticInfo : function(stuInfo) {
			
			for(var i=0; i<vm.stuCertificatesTypes.length;i++){				
				if(vm.stuCertificatesTypes[i].id == stuInfo.stuCertificatesType.id){
					vm.stuCertificatesType = vm.stuCertificatesTypes[i].name;
				}
			}
			for(var i=0; i<vm.stuEnrolObjects.length;i++){				
				if(vm.stuEnrolObjects[i].id == stuInfo.stuEnrolObject.id){
					vm.stuEnrolObject = vm.stuEnrolObjects[i].name;
				}
			}
			for(var i=0; i<vm.stuPoliticalOutlooks.length;i++){				
				if(vm.stuPoliticalOutlooks[i].id == stuInfo.stuPoliticalOutlook.id){
					vm.stuPoliticalOutlook = vm.stuPoliticalOutlooks[i].name;
				}
			}
			for(var i=0; i<vm.stuCertificatesTypes.length;i++){				
				if(vm.stuCertificatesTypes[i].id == stuInfo.stuCertificatesType.id){
					vm.stuCertificatesType = vm.stuCertificatesTypes[i].name;
				}
			}
			for(var i=0; i<vm.stuNations.length;i++){				
				if(vm.stuNations[i].id == stuInfo.stuNation.id){
					vm.stuNation = vm.stuNations[i].name;
				}
			}
			for(var i=0; i<vm.stuEnrolTypes.length;i++){				
				if(vm.stuEnrolTypes[i].id == stuInfo.stuEnrolType.id){
					vm.stuEnrolType = vm.stuEnrolTypes[i].name;
				}
			}
			for(var i=0; i<vm.stuNationalitys.length;i++){				
				if(vm.stuNationalitys[i].id == stuInfo.stuNationality.id){
					vm.stuNationality = vm.stuNationalitys[i].name;
				}
			}
			for(var i=0; i<vm.stuEntranceWays.length;i++){				
				if(vm.stuEntranceWays[i].id == stuInfo.stuEntranceWay.id){
					vm.stuEntranceWay = vm.stuEntranceWays[i].name;
				}
			}
			for(var i=0; i<vm.stuHKMTOCs.length;i++){				
				if(vm.stuHKMTOCs[i].id == stuInfo.stuHKMTOC.id){
					vm.stuHKMTOC = vm.stuHKMTOCs[i].name;
				}
			}
			for(var i=0; i<vm.stuTypes.length;i++){				
				if(vm.stuTypes[i].id == stuInfo.stuType.id){
					vm.stuType = vm.stuTypes[i].name;
				}
			}
			for(var i=0; i<vm.stuMajors.length;i++){				
				if(vm.stuMajors[i].majorId == stuInfo.stuMajor.majorId){
					vm.stuMajor = vm.stuMajors[i].majorName;
				}
			}
			for(var i=0; i<vm.stuLengths.length;i++){				
				if(vm.stuLengths[i].id == stuInfo.stuLength.id){
					vm.stuLength = vm.stuLengths[i].lengthName;
				}
			}			
		},
		reload : function() {
			vm.isStuFamilyMember1 = false;
			vm.isStuFamilyMember2 = false;
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
		this.getUserInfo();
	},
	updated : function() {}
});