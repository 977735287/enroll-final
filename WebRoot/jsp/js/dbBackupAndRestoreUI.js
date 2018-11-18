
var vm = new Vue({
	el : '#eapp',
	data : {
		isBackupping : false,
		isRestoreing : false,
		isBackupOk : false,
		isRestoreOk : false,
		sqlName : "enroll",
		progressActive : 'progress progress-striped active',
		progressActive1 : 'progress progress-striped active',
		width : 'width: 0%',
		width1 : 'width: 0%',
		num : 0,
		num1 : 0,
		valueText : '',
		valueText1 : '',
		interval : 0,
		interval1 : 0,
		backupStatus : '',
		restoreStatus : ''
	},
	methods : {
		backup : function() {
			vm.isBackupping = true;
			vm.backupStatus = '正在备份中.';
			vm.interval = setInterval(function() {
				if(vm.num < 99){
					vm.num += 1;
					vm.width = 'width: ' + vm.num + '%';
					vm.valueText = vm.num + '%';
					if(vm.num % 6 == 0){
						vm.backupStatus = '正在备份中.';
					}else if(vm.num % 6 == 1){
						vm.backupStatus = '正在备份中..';
					}else if(vm.num % 6 == 2){
						vm.backupStatus = '正在备份中...';
					}else if(vm.num % 6 == 3){
						vm.backupStatus = '正在备份中....';
					}else if(vm.num % 6 == 4){
						vm.backupStatus = '正在备份中.....';
					}else if(vm.num % 6 == 5){
						vm.backupStatus = '正在备份中......';
					}
				}else{
					window.clearInterval(vm.interval);
				}
			}, 50);
					$.ajax({
						type : "POST",
						url : baseURL + "school/backup/backup.do",
						data : "fileName=" + vm.sqlName,
						success : function(r){
							if(r.code == 0){
								vm.num = 100;
								vm.width = 'width: ' + vm.num + '%';
								vm.valueText = vm.num + '%';
								vm.backupStatus = '数据库备份完成...';
								vm.progressActive = 'progress progress-striped';
//								vm.isBackupOk = true;
//								alert("已备份");
								var eleForm = $("<form method='get'></form>");
								eleForm.attr("action",baseURL + r.url);
								$(document.body).append(eleForm);
								eleForm.submit();
							}
						}
					});
		},
		restore : function() {		
			vm.isRestoreing = true;
			vm.restoreStatus = '正在备份中.';
			vm.interval1 = setInterval(function() {
				if(vm.num1 < 99){
					vm.num1 += 1;
					vm.width1 = 'width: ' + vm.num1 + '%';
					vm.valueText1 = vm.num1 + '%';
					if(vm.num1 % 6 == 0){
						vm.restoreStatus = '正在进行数据库还原中.';
					}else if(vm.num1 % 6 == 1){
						vm.restoreStatus = '正在进行数据库还原中..';
					}else if(vm.num1 % 6 == 2){
						vm.restoreStatus = '正在进行数据库还原中...';
					}else if(vm.num1 % 6 == 3){
						vm.restoreStatus = '正在进行数据库还原中....';
					}else if(vm.num1 % 6 == 4){
						vm.restoreStatus = '正在进行数据库还原中.....';
					}else if(vm.num1 % 6 == 5){
						vm.restoreStatus = '正在进行数据库还原中......';
					}
				}else{
					window.clearInterval(vm.interval1);
				}
			}, 50);
			
			//document.getElementById("restoreForm").submit();
			var formData = new FormData();
			formData.append("file",document.getElementById("fileInput").files[0]);
			$.ajax({
				url : baseURL + "school/backup/restore.do?token=" + token,
				type : "POST",
				data : formData,
				contentType : false,
				processData : false,
				success : function(r){
					if(r.code == 0){
						vm.num1 = 100;
						vm.width1 = 'width: ' + vm.num1 + '%';
						vm.valueText1 = vm.num1 + '%';
						vm.restoreStatus = '已完成数据库还原...';
						vm.progressActive1 = 'progress progress-striped';
					}
				},
				erorr : function(){
					alert("上传失败");
				}
			});
		},
		reload : function() {}
	},
	created : function() {
		this.width = 'width: ' + this.num + '%';
		this.width1 = 'width: ' + this.num1 + '%';
	}
});