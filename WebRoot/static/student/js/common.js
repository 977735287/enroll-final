var loginStatus = 0; // 0：未登录 1：已登陆
// var userStatus = 0;
// alert(user.username);

// 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
var curWwwPath = window.document.location.href;
// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName = window.document.location.pathname;
var pos = curWwwPath.indexOf(pathName);
// 获取主机地址，如： http://localhost:8083
var localhostPaht = curWwwPath.substring(0, pos);
// 获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var baseURL = localhostPaht + projectName + "/";

// 登录user
var loginUser = JSON.parse(localStorage.getItem("loginUser"));
// 登录token
var token = localStorage.getItem("token");
// alert(token);
// jquery全局配置
$.ajaxSetup({
	dataType : "json",
	cache : false,
	headers : {
		"token" : token
	},
	complete : function(xhr) {
		// token过期
		if (xhr.responseJSON.code == 401) {
			localStorage.removeItem("loginUser");
			localStorage.removeItem("token");
		    location.reload();
		}
	}
});

// 重写confirm式样框
window.confirm = function(msg, callback) {
	parent.layer.confirm(msg, {
		btn : [ '确定', '取消' ]
	}, function() {// 确定事件
		if (typeof (callback) === "function") {
			callback("ok");
		}
	});
}

//对Date的扩展，将 Date 转化为指定格式的String  
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，  
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)  
//例子：  
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423  
//(new Date()).Format("yyyy-M-d h:m:s.S")   ==> 2006-7-2 8:9:4.18  
Date.prototype.Format = function(fmt) { //author: meizz  
	var o = {
		"M+" : this.getMonth() + 1, //月份  
		"d+" : this.getDate(), //日  
		"h+" : this.getHours(), //小时  
		"m+" : this.getMinutes(), //分  
		"s+" : this.getSeconds(), //秒  
		"q+" : Math.floor((this.getMonth() + 3) / 3), //季度  
		"S" : this.getMilliseconds() //毫秒  
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

function GetRequest() {
	var url = location.search; // 获取url中"?"符后的字串
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
	}
	return theRequest;
}

function logout(){
	 localStorage.removeItem("loginUser");
	 localStorage.removeItem("token");
     location.reload();   
}

function formatDate(value) {
	var dateSeconds = parseInt(value);
	var date = new Date(dateSeconds);
	date = date.Format("yyyy-MM-dd");
	return date;
}

function IdCardValidate(idCard) {
	idCard = trimNull(idCard.replace(/ /g, "")); //去掉字符串头尾空格                     
	if (idCard.length == 15) {
		return isValidityBrithBy15IdCard(idCard); //进行15位身份证的验证    
	} else if (idCard.length == 18) {
		var a_idCard = idCard.split(""); // 得到身份证数组   
		if (isValidityBrithBy18IdCard(idCard) && isTrueValidateCodeBy18IdCard(a_idCard)) { //进行18位身份证的基本验证和第18位的验证
			return true;
		} else {
			return false;
		}
	} else {
		return false;
	}
}
/**  
 * 判断身份证号码为18位时最后的验证位是否正确  
 * @param a_idCard 身份证号码数组  
 * @return  
 */
function isTrueValidateCodeBy18IdCard(a_idCard) {
	var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ]; // 加权因子   
	var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]; // 身份证验证位值.10代表X 
	var sum = 0; // 声明加权求和变量   
	if (a_idCard[17].toLowerCase() == 'x') {
		a_idCard[17] = 10; // 将最后位为x的验证码替换为10方便后续操作   
	}
	for (var i = 0; i < 17; i++) {
		sum += Wi[i] * a_idCard[i]; // 加权求和   
	}
	valCodePosition = sum % 11; // 得到验证码所位置   
	if (a_idCard[17] == ValideCode[valCodePosition]) {
		return true;
	} else {
		return false;
	}
}
/**  
  * 验证18位数身份证号码中的生日是否是有效生日  
  * @param idCard 18位书身份证字符串  
  * @return  
  */
function isValidityBrithBy18IdCard(idCard18) {
	var year = idCard18.substring(6, 10);
	var month = idCard18.substring(10, 12);
	var day = idCard18.substring(12, 14);
	var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
	// 这里用getFullYear()获取年份，避免千年虫问题   
	if (temp_date.getFullYear() != parseFloat(year)
		|| temp_date.getMonth() != parseFloat(month) - 1
		|| temp_date.getDate() != parseFloat(day)) {
		return false;
	} else {
		return true;
	}
}
/**  
 * 验证15位数身份证号码中的生日是否是有效生日  
 * @param idCard15 15位书身份证字符串  
 * @return  
 */
function isValidityBrithBy15IdCard(idCard15) {
	var year = idCard15.substring(6, 8);
	var month = idCard15.substring(8, 10);
	var day = idCard15.substring(10, 12);
	var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
	// 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
	if (temp_date.getYear() != parseFloat(year)
		|| temp_date.getMonth() != parseFloat(month) - 1
		|| temp_date.getDate() != parseFloat(day)) {
		return false;
	} else {
		return true;
	}
}

//去掉字符串头尾空格   
function trimNull(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}


//1:获取生日  2:获取性别  3:获取年龄
function IdCardGet(UUserCard, num) {
	if (num == 1) {
		var birth = "";
		//获取出生日期
		if (UUserCard.length == 15) {
			birth = "19" + UUserCard.substring(6, 8) + "-" + UUserCard.substring(8, 10) + "-" + UUserCard.substring(10, 12);
		} else if (UUserCard.length == 18) {
			birth = UUserCard.substring(6, 10) + "-" + UUserCard.substring(10, 12) + "-" + UUserCard.substring(12, 14);
		}
		return birth;
	}
	if (num == 2) {
		//获取性别
		if (parseInt(UUserCard.substr(16, 1)) % 2 == 1) {
			//男
			return "男";
		} else {
			//女
			return "女";
		}
	}
	if (num == 3) {
		//获取年龄
		var myDate = new Date();
		var month = myDate.getMonth() + 1;
		var day = myDate.getDate();
		var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
		if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
			age++;
		}
		return age;
	}
}

//验证输入的姓名是否有效
function isChinese(name) {
	reg = /^([\u4e00-\u9fa5]){2,7}$/; //只能是中文，长度为2-7位
	if (!reg.test(name)) {
		return false;
	} else {
		return true;
	}
}

//验证手机号码的正确性 
function isMobile(value) {
	var pattern = /^1[358][0123456789]\d{8}$/;
	if (!pattern.test(value)) {
		return false;
	}
	return true;
}

function isTel(value) {
	var regTel1 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(value); //带区号的固定电话
	var regTel2 = /^(\d{7,8})(-(\d{3,}))?$/.test(value); //不带区号的固定电话
	if (value != "") {
		if (!regTel1 && !regTel2) {
			return false;
		}
	} else {
		return false;
	}
	return true;
}

//验证电子邮箱的正确性 
function isEmail(value) {
	var pattern = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	if (!pattern.test(value)) {
		return false;
	}
	return true;
}

//采用正则表达式获取地址栏参数
function getQueryString(paramName) {
	var reg = new RegExp("(^|&)"+ paramName +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]);
	return null;
}

function getFileType(filePath){
	var startIndex = filePath.lastIndexOf(".");
	if(startIndex != -1)
		return filePath.substring(startIndex+1, filePath.length).toLowerCase();
	else return "";
}