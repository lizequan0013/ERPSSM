//删除明细表的记录
function delDetail(TableName,SNo){
	if ( SNo==undefined ) {
		return true;
	} else 	if ( State>0 ) {
		$.messager.alert("错误","非新建状态不允许删除明细","error");
		return false;
	} else {
		$.ajax({
			async : false,
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			dataType : "json",
			type : "POST",
			url : "../servlet/AjaxDelDetail",
			data : {
				"TableName" : TableName,
				"SNo" : SNo
			},
			success : function(data) {
				Result = data.Result;
			}
		});
		if (Result=="OK"){
			return true;
		} else {
			return false;
		};
	}
}

//设置主表字段的只读与必录
function setMainControl(BillState){
	//激活主表的所有项
	Fields = ShowFields[BillState];
	Fields = Fields[0];
	var list = Fields.split(",");
	for ( var key in list ) {
		FieldName = list[key];
		FieldClass = $("#"+FieldName).attr("class");
		if ( subIndex(FieldClass,"easyui-datebox") > -1) {
			$("#"+FieldName).datebox({disabled:false});
		} else if ( subIndex(FieldClass,"easyui-validatebox") > -1) {
			$("#"+FieldName).attr("readonly", true);
		} else if (subIndex(FieldClass,"wu-popup") > -1) {
			$("#"+FieldName).attr("disabled",false);
			$("#"+FieldName).css("background-color","#FBF7B4");
		} else if (subIndex(FieldClass,"wu-date") > -1) {
			$("#"+FieldName).attr("disabled",false);
			$("#"+FieldName).css("background-color","#DDFF95");
		} else {
			$("#"+FieldName).attr("disabled",false);
			$("#"+FieldName).css("background-color","#FFFFFF");
		}
		$("#"+FieldName).css("border","solid 1px #95B8E7");
	}
	//调整主表的只读项
	Fields = DisableFields[BillState];
	Fields = Fields[0];
	var list = Fields.split(",");
	for ( var key in list ) {
		FieldName = list[key];
		FieldClass = $("#"+FieldName).attr("class");
		if ( subIndex(FieldClass,"easyui-datebox") > -1) {
			$("#"+FieldName).datebox({disabled:true});
		} else {
			$("#"+FieldName).attr("disabled",true);
		}
		$("#"+FieldName).css("background-color","#F4F4F4");
		$("#"+FieldName).css("border","solid 1px #E2E3EA");
	}
	//调整主表的必录项
	Fields = NeedFields[BillState];
	Fields = Fields[0];
	var list = Fields.split(",");
	for ( var key in list ) {
		FieldName = list[key];
		FieldClass = $("#"+FieldName).attr("class");
		$("#"+FieldName).css("border","solid 1px #F2A79D");
	}
}

//调整明细表的只读和必录项
function setDetailControl(BlockIndex){
	//调整明细表的只读项
	Fields = DisableFields[State];
	Fields = Fields[BlockIndex];
	var list = Fields.split(",");
	for ( var key in list ) {
		FieldName = list[key];
		for (j=0;j<PopEditors.length;j++){
			if (PopEditors[j].field==FieldName){
				//datebox-f  combobox-f 
				FieldClass=PopEditors[j].target.attr("class");
				if (subIndex(FieldClass,"wu-popup")>-1){
					PopEditors[j].target.attr("disabled",true);
				} else if (subIndex(FieldClass,"datebox-f")>-1){
					PopEditors[j].target.datebox({disabled:true});
				} else if (subIndex(FieldClass,"combobox-f")>-1){
					PopEditors[j].target.combobox("disable");
				} else {
					PopEditors[j].target.attr("readonly",true);
				}
				PopEditors[j].target.css("background-color","#F4F4F4");
        		PopEditors[j].target.css("border","solid 1px #E2E3EA");	        		
			}
        }
	}
	//调整明细表的必录项
	Fields = NeedFields[State];
	Fields = Fields[BlockIndex];
	var list = Fields.split(",");
	for ( var key in list ) {
		FieldName = list[key];
		for (j=0;j<PopEditors.length;j++){
			if (PopEditors[j].field==FieldName){
				PopEditors[j].target.css("border","solid 1px #F2A79D");	
			}
		}
	}		
}

//获取今天日期，格式YYYY-MM-DD
function getToday() {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (day >= 0 && day <= 9) {
		day = "0" + day;
	}
	var Result = year + "-" + month + "-" + day;
	return Result;
}

//修复Chrome不支持indexOf的问题
function subIndex(Str, subStr, fromIndex) {
	var k;
	if (Str == null) {
		return -1;
	}
	var len = Str.length;
	if (len == 0) {
		return -1;
	}
	var n = fromIndex | 0;
	if (n >= len) {
		return -1;
	}
	k = Math.max(n >= 0 ? n : len-Math.abs(n), 0);
	s = subStr.length;
	while (k < len) {
		if (Str.substring(k,k+s) == subStr) {
			return k;
		}
		k++;
	}
	return -1;
};

//数据验证
$.extend($.fn.validatebox.defaults.rules,{
	float: { // 验证整数或小数
		validator: function(value, param){
			return isFloat(value);
		},
		message: '请输入数字'
	},
	int: { // 验证整数 可正负数
		validator: function(value, param){
			return isNumber(value);
		},
		message: '请输入整数'
	},
	date: { 
		validator: function(value, param){
			return isDate(value);
		},
		message: '请输入形如2001-01-01的日期格式'
	}
});

//是否整数
function isNumber(z_check_value){
	var z_reg = /^(([0-9])|([1-9]([0-9]+)))$/;
	return z_reg.test($.trim(z_check_value));
};

//是否合规带小数数字
function isFloat(z_check_value){
	var z_reg = /^((([0-9])|([1-9][0-9]+))(\.([0-9]+))?)$/;//.是特殊字符，需要转义
	return z_reg.test($.trim(z_check_value));
};

//是否形如“2001-01-01”的日期
function isDate(strDate) {
	var strDate;
	var strDateArray;
	var strDay="";
	var strMonth="";
	var strYear="";
	var intday;
	var intMonth;
	var intYear;
	//空字符串
	if (strDate.length == 0) {
		return true;
	}
	//据分隔符将日期字符串分解成三部分
	if (subIndex(strDate,"-") != -1) {
		strDateArray = strDate.split("-");
		if (strDateArray.length != 3) {
			err = 1;
			return false;
		} else {
			strYear = strDateArray[0];
			strMonth = strDateArray[1];
			strDay = strDateArray[2];
		}
	}
	//检查年月日各自是否为整数
	intday = parseInt(strDay, 10);
	if (isNaN(intday)) {
		err = 2;
		return false;
	}
	intMonth = parseInt(strMonth, 10);
	if (isNaN(intMonth)) {
		err = 3;
		return false;
	}
	intYear = parseInt(strYear, 10);
	if (isNaN(intYear)) {
		err = 4;
		return false;
	}
	//月必须1-12
	if (intMonth > 12 || intMonth < 1) {
		err = 5;
		return false;
	}
	//判断日
	if ((intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12)
		&& (intday > 31 || intday < 1)) {
		err = 6;
		return false;
	}
	if ((intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11)
			&& (intday > 30 || intday < 1)) {
		err = 7;
		return false;
	}
	if (intMonth == 2) {
		if (intday < 1) {
			err = 8;
			return false;
		}
		if (LeapYear(intYear) == true) {
			if (intday > 29) {
				err = 9;
				return false;
			}
		} else {
			if (intday > 28) {
				err = 10;
				return false;
			}
		}
	}
	//判断年必须为4位
	if (intYear < 1000 || intYear > 9999) {
		err = 11;
		return false;
	}
	return true;
}

//是否闰年
function LeapYear(intYear) {
	if (intYear % 100 == 0) {
		if (intYear % 400 == 0) {
			return true;
		}
	} else {
		if ((intYear % 4) == 0) {
			return true;
		}
	}
	return false;
}

//日期输入框
function DateFormat(vDateName, vDateValue, e, dateCheck) {
	var whichCode = (window.Event) ? e.which : e.keyCode;
	if (whichCode == 8) // Ignore the Netscape value for backspace. IE has no value
		return false;
	else {
		if (vDateValue.length == 10 && dateCheck) {
			if (!isDate( vDateValue )) {
				alert("请按2001-01-01的格式重新输入。");
				vDateName.focus();
				vDateName.select();
			}
		} else {
			if (vDateValue.length == 4) {
				vDateName.value = vDateValue + "-";
			}
			if (vDateValue.length == 7) {
				vDateName.value = vDateValue + "-";
			}
		}
	}
}