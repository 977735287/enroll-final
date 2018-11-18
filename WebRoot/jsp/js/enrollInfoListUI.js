$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'school/info/list.do',
		datatype : "json",
		colModel : [
			{
				label : 'ID',
				name : 'id',
				index : "id",
				width : 45,
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
				width : 65
			},
			{
				label : '性别',
				name : 'stuSex',
				index : "stuSex",
				width : 55,
				formatter : function(value, options, row) {
					if(value === 0){
						return "男";
					}else if(value === 1){
						return "女";
					}else{
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
			}
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
			stuName : null
		},
		showList : true,
		isStuFamilyMember1 : false,
		isStuFamilyMember2 : false,
		title : null,
		stuCertificatesImgurl : "",
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
		width : 0,
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
		stuInfo : {		
			user : {},
			stuFamilyMember1 : {
				relationShip : {
					id : 2
				}
			},
			stuFamilyMember2 : {
				relationShip : {
					id : 2
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
		query : function() {
			vm.reload();
		},
		descInput : function(){
			this.remnant = 255 - this.stuInfo.user.message.length;
		},
		check : function() {
			var id = getSelectedRow();
			if (id == null) {
				return;
			}
			
			vm.showList = false;
			vm.title = "审核学生信息";
						
			vm.getStudentInfo(id);			
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
			});
		},
		getStudentInfo : function(id) {
			$.ajax({
				type : "POST",
				url : baseURL + "school/info/info.do",
				data : "id=" + id,
				success : function(r) {					
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
							vm.width = 166;
						}else{
							vm.width = 266;
						}
					}else{
						vm.stuCertificatesImgurl = baseURL + "static/images/student/noPicture.png";
					}
					
					vm.setStaticInfo(vm.stuInfo);
					vm.stuInfo.stuBirthday = formatDate(vm.stuInfo.stuBirthday);
					vm.setArea(vm.stuInfo,vm.stuAreas);
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
			vm.showList = true;
			vm.isStuFamilyMember1 = false;
			vm.isStuFamilyMember2 = false;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'stuName' : vm.q.stuName
				},
				page : page
			}).trigger("reloadGrid");
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
	},
	updated : function() {}
});