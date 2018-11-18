
var majorArr = new Array();
var stuArrGirl = new Array();
var stuArrBoy = new Array();

var barChart;

var data = "";

var vm = new Vue({
	el : '#eapp',
	data : {
		majorList : [],
		stuList : [],
		time : null
	},
	methods : {
		
	},
	created : function() {
	}
});

$(function() {
	$.ajax({
		type : "POST",
		url : baseURL + "school/major/majorList.do",
		async : false,
		data : {},
		success : function(r) {
			vm.majorList = r.majorList;
			for (var i = 0; i < vm.majorList.length; i++) {
				majorArr[i] = vm.majorList[i].majorName;
				stuArrGirl[i] = 0;
				stuArrBoy[i] = 0;
			}
		}
	});
//	data = "startTime=2018-01-01" + "&endTime=2018-12-30";
	$.ajax({
		type : "POST",
		url : baseURL + "school/list/info/stuList.do",
		async : false,
		data : data,
		dataType : "json",
		success : function(r) {
			vm.stuList = r.stuList;
//			console.info(vm.stuList[0].stuMajor.majorId);
			for (var i = 0; i < vm.stuList.length; i++) {
				for (var j = 0; j < vm.majorList.length; j++) {
					if(vm.stuList[i].stuMajor.majorId == vm.majorList[j].majorId){
//						alert();
						if(0 == vm.stuList[i].stuSex){
							stuArrBoy[j] = stuArrBoy[j] + 1;
						}else {
							stuArrGirl[j] = stuArrGirl[j] + 1;
						}
					}
				}
				
			}
		}
	});
	barChart = echarts.init(document.getElementById("echarts-bar-chart"));
	var baroption = {
		title : {
			text : '专业男女数据统计',
			x : 'center'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			orient : 'vertical',
			left : 'left',
			data : [ '男生', '女生' ]
		},
		toolbox : {
			show : true,
			feature : {
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [
			{
				type : 'category',
				data : majorArr
			}
		],
		yAxis : [
			{
				type : 'value'
			}
		],
		series : [
			{
				name : '男生',
				type : 'bar',
				data : stuArrBoy,
				markPoint : {
					data : [
						{
							type : 'max',
							name : '最大值'
						},
						{
							type : 'min',
							name : '最小值'
						}
					]
				},
				markLine : {
					data : [
						{
							type : 'average',
							name : '平均值'
						}
					]
				}
			},
			{
				name : '女生',
				type : 'bar',
				data : stuArrGirl,
				markPoint : {
					data : [
						{
							type : 'max',
							name : '最大值'
						},
						{
							type : 'min',
							name : '最小值'
						}
					]
				},
				markLine : {
					data : [
						{
							type : 'average',
							name : '平均值'
						}
					]
				}
			}
		]
	};
	barChart.setOption(baroption);
	window.onresize = barChart.resize;
});

$("#timeCol").change(function() {
	clearArr();
	var time = $(this).val();
	if(time == "0"){
		data = "";
	}else if(time == "1"){
		data = "startTime=2017-01-01" + "&endTime=2017-12-30";
	}else if(time == "2"){
		data = "startTime=2018-01-01" + "&endTime=2018-12-30";
	}
	$.ajax({
		type : "POST",
		url : baseURL + "school/list/info/stuList.do",
		async : false,
		data : data,
		dataType : "json",
		success : function(r) {
			vm.stuList = r.stuList;
			for (var i = 0; i < vm.stuList.length; i++) {
				for (var j = 0; j < vm.majorList.length; j++) {
					if(vm.stuList[i].stuMajor.majorId == vm.majorList[j].majorId){
						if(0 == vm.stuList[i].stuSex){
							stuArrBoy[j] = stuArrBoy[j] + 1;
						}else {
							stuArrGirl[j] = stuArrGirl[j] + 1;
						}
					}
				}
				
			}
		}
	});
	refreshData(stuArrGirl,stuArrBoy);
});

function refreshData(stuArrGirl,stuArrBoy) {
	barChart.clear();
	var baroption = {
			title : {
				text : '专业男女数据统计',
				x : 'center'
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				orient : 'vertical',
				left : 'left',
				data : [ '男生', '女生' ]
			},
			toolbox : {
				show : true,
				feature : {
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			xAxis : [
				{
					type : 'category',
					data : majorArr
				}
			],
			yAxis : [
				{
					type : 'value'
				}
			],
			series : [
				{
					name : '男生',
					type : 'bar',
					data : stuArrBoy,
					markPoint : {
						data : [
							{
								type : 'max',
								name : '最大值'
							},
							{
								type : 'min',
								name : '最小值'
							}
						]
					},
					markLine : {
						data : [
							{
								type : 'average',
								name : '平均值'
							}
						]
					}
				},
				{
					name : '女生',
					type : 'bar',
					data : stuArrGirl,
					markPoint : {
						data : [
							{
								type : 'max',
								name : '最大值'
							},
							{
								type : 'min',
								name : '最小值'
							}
						]
					},
					markLine : {
						data : [
							{
								type : 'average',
								name : '平均值'
							}
						]
					}
				}
			]
		};
		barChart.setOption(baroption);
		window.onresize = barChart.resize;
}

function clearArr(){
	for(var i=0;i<stuArrBoy.length;i++){
		stuArrBoy[i] = 0;
	}for(var i=0;i<stuArrGirl.length;i++){
		stuArrGirl[i] = 0;
	}
}
