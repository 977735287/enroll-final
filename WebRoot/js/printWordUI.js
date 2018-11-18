
var vm = new Vue({
	el : '#eapp',
	data : {
		display : true,
		isStuFamilyMember1 : false,
		isStuFamilyMember2 : false,
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
		print : function() {			
			var id = getQueryString("id");
			this.getStudentInfo(id);									
		},
		upload : function() {
			document.getElementById("printForm").submit();
		},
		getStaticInfo : function() {
			$.get(baseURL + "school/enroll/select.do", function(r) {
				vm.stuMajors = r.stuMajors;
				vm.stuLengths = r.stuLengths;
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
					vm.setStaticInfo(vm.stuInfo);
					vm.stuInfo.stuBirthday = formatDate(vm.stuInfo.stuBirthday);
					vm.setArea(vm.stuInfo, vm.stuAreas);
					vm.setPrintData();
				}
			});
		},
		setArea : function(stuInfo, stuAreas) {
			for (var i = 0; i < stuAreas.length; i++) {
				if (vm.stuAreas[i].id == stuInfo.stuArea.id) {
					vm.stuArea = stuAreas[stuAreas[stuAreas[i].parentId - 1].parentId - 1].name + stuAreas[stuAreas[i].parentId - 1].name + stuAreas[i].name;
				}
				if (vm.stuAreas[i].id == stuInfo.stuBirthArea.id) {
					vm.birthArea = stuAreas[stuAreas[stuAreas[i].parentId - 1].parentId - 1].name + stuAreas[stuAreas[i].parentId - 1].name + stuAreas[i].name;
				}
				if (vm.stuAreas[i].id == stuInfo.stuNationArea.id) {
					vm.nationArea = stuAreas[stuAreas[stuAreas[i].parentId - 1].parentId - 1].name + stuAreas[stuAreas[i].parentId - 1].name + stuAreas[i].name;
				}
				if (vm.stuAreas[i].id == stuInfo.stuHouseArea.id) {
					vm.houseArea = stuAreas[stuAreas[stuAreas[i].parentId - 1].parentId - 1].name + stuAreas[stuAreas[i].parentId - 1].name + stuAreas[i].name;
				}
				if (vm.stuArea != "" && vm.birthArea != "" && vm.nationArea != "" && vm.houseArea != "") {
					break;
				}
			}
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
		setPrintData : function(){
			if (vm.stuInfo.stuSex == 0) {	
				vm.stuSex = "男";
			} else if (vm.stuInfo.stuSex == 1) {
				vm.stuSex = "女";
			} else {
				vm.stuSex = "";
			}

			if (vm.stuInfo.stuHealthCondition == 0) {
				vm.stuHealthCondition = "健康良好";
			} else if (vm.stuInfo.stuHealthCondition == 1) {
				vm.stuHealthCondition = "一般较弱";
			} else if (vm.stuInfo.stuHealthCondition == 2) {
				vm.stuHealthCondition = "有慢性病";
			} else if (vm.stuInfo.stuHealthCondition == 3) {
				vm.stuHealthCondition = "残疾";
			} else {
				vm.stuHealthCondition = "";
			}

			if (vm.stuInfo.stuIsMarried == 0) {
				vm.stuMarried = "是";
			} else if (vm.stuInfo.stuIsMarried == 1) {
				vm.stuMarried = "否";
			} else if (vm.stuInfo.stuIsMarried == 2) {
				vm.stuMarried = "其它";
			} else {
				vm.stuMarried = "";
			}

			if (vm.stuInfo.stuIsAllDay == 0) {
				vm.stuStudyType = "是";
			} else if (vm.stuInfo.stuIsAllDay == 1) {
				vm.stuStudyType = "否";
			} else if (vm.stuInfo.stuIsAllDay == 2) {
				vm.stuStudyType = "其它";
			} else {
				vm.stuStudyType = "";
			}

			if (vm.stuInfo.stuIsCurrent == 0) {
				vm.stuComeFrom = "是";
			} else if (vm.stuInfo.stuIsCurrent == 1) {
				vm.stuComeFrom = "否";
			} else if (vm.stuInfo.stuIsCurrent == 2) {
				vm.stuComeFrom = "其它";
			} else {
				vm.stuComeFrom = "";
			}

			if (vm.stuInfo.stuIsSuiqian == 0) {
				vm.stuSuiqian = "是";		
			} else if (vm.stuInfo.stuIsSuiqian == 1) {
				vm.stuSuiqian = "否";
			} else if (vm.stuInfo.stuIsSuiqian == 2) {
				vm.stuSuiqian = "其它";
			} else {
				vm.stuSuiqian = "";
			}

			if (vm.stuInfo.stuDomicile == 0) {
				vm.stuDomicile = "农村";
			} else if (vm.stuInfo.stuDomicile == 1) {
				vm.stuDomicile = "乡镇";
			} else if (vm.stuInfo.stuDomicile == 2) {
				vm.stuDomicile = "县城";
			} else if (vm.stuInfo.stuDomicile == 3) {
				vm.stuDomicile = "城市";
			} else {
				vm.stuDomicile = "";
			}

			if (vm.stuInfo.stuHouseHoldType == 0) {
				vm.stuHouseHoldType = "农业户口";
			} else if (vm.stuInfo.stuHouseHoldType == 1) {
				vm.stuHouseHoldType = "非农业户口";
			} else {
				vm.stuHouseHoldType = "";
			}

			if (vm.isStuFamilyMember1) {
				if (vm.stuInfo.stuFamilyMember1.guardian == 0) {
					vm.stuFamily1Guardian = "是";
				} else if (vm.stuInfo.stuFamilyMember1.guardian == 1) {
					vm.stuFamily1Guardian = "否";
				} else {
					vm.stuFamily1Guardian = "";
				}
			} else {
				vm.stuFamily1Guardian = "";
			}

			if (vm.isStuFamilyMember2) {
				if (vm.stuInfo.stuFamilyMember2.guardian == 0) {
					vm.stuFamily2Guardian = "是";
				} else if (vm.stuInfo.stuFamilyMember2.guardian == 1) {
					vm.stuFamily2Guardian = "否";
				} else {
					vm.stuFamily2Guardian = "";
				}
			} else {
				vm.stuFamily2Guardian = "";
			}

			if (vm.stuInfo.stuStudyWay == 0) {
				vm.stuStudyWay = "走读";
			} else if (vm.stuInfo.stuStudyWay == 1) {
				vm.stuStudyWay = "住校";
			} else {
				vm.stuStudyWay = "";
			}

			if (vm.stuInfo.stuEnrolWay == 0) {
			    vm.stuEnrolWay = "统一招生";
			} else if (vm.stuInfo.stuEnrolWay == 1) {
				 vm.stuEnrolWay = "自主招生";
			} else {
				 vm.stuEnrolWay = "";
			}
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
		this.print();
	},
	mounted : function() {
		
	}
});


