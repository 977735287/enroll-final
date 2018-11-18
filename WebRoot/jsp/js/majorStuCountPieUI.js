
var majorArr = new Array();
var stuArr = new Array();

var vm = new Vue({
	el : '#eapp',
	data : {
		majorList : [],
		stuList : []
	},
	methods : {

	},
	created : function() {}
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
				stuArr[i] = 0;
			}
		}
	});
	$.ajax({
		type : "POST",
		url : baseURL + "school/list/info/stuList.do",
		async : false,
		data : {},
		success : function(r) {
			vm.stuList = r.stuList;
			for (var i = 0; i < vm.stuList.length; i++) {
				for (var j = 0; j < vm.majorList.length; j++) {
					if(vm.stuList[i].stuMajor.majorId == vm.majorList[j].majorId){
						stuArr[j] = stuArr[j] + 1;
					}
				}
				
			}
		}
	});
	var pieChart = echarts.init(document.getElementById("echarts-pie-chart"));
	var pieoption = {
		title : {
			text : '各专业人数统计',
			x : 'center'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		}, 
		toolbox: {
	        show : true,
	        feature : {
	            dataView : {show: true, readOnly: false},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
		legend : {
			orient : 'vertical',
			left : 'left',
			data : majorArr
		},
		series : [
			{
				name : '专业',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ],
				data : [
					{
						value : stuArr[0],
						name : majorArr[0]
					},
					{
						value : stuArr[1],
						name : majorArr[1]
					},
					{
						value : stuArr[2],
						name : majorArr[2]
					},
					{
						value : stuArr[3],
						name : majorArr[3]
					},
					{
						value : stuArr[4],
						name : majorArr[4]
					},
					{
						value : stuArr[5],
						name : majorArr[5]
					},
					{
						value : stuArr[6],
						name : majorArr[6]
					},
					{
						value : stuArr[7],
						name : majorArr[7]
					}
				],
				itemStyle : {
					emphasis : {
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					}
				}
			}
		]
	};
	pieChart.setOption(pieoption);
	window.onresize = pieChart.resize;
});