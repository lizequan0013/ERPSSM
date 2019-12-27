//文本框
var TextBox = null;
//需要取值字段列表
var OutFields = "";
//储存取出来的JSON
var JsonData = "";

//初始化下拉列表的事件
$(function() {
	//鼠标停在下拉列表某一项上面，蓝色选中
	$("#PopSelect").bind("mouseover", function(e) {
		if (e.target.id != "PopSelect") {
			$("#PopSelect").val(e.target.value);
		}
	});
	//鼠标单击下拉列表某一项时，将这一项的值添加到输入框中 
	$("#PopSelect").click(function() {
		//文本框赋值
		TextBox.value = $("#PopSelect").val();
		$("#PopDiv").hide();
		//焦点回到文本框
		TextBox.focus();
		//给需要同步赋值的字段设定值
		if (subIndex(OutFields,",") > -1) set_other_field_value( $("#PopSelect").val() );
	});
});

//下拉列表按键事件
function select_down() {
	//ESC隐藏选择层
	if (event.keyCode == 27) {
		$("#PopDiv").hide();
	}
	//当在下拉列表某一项按回车时，将这一项的值添加到输入框中  
	if (event.keyCode == 13) {
		//文本框赋值
		TextBox.value = $("#PopSelect").val();
		$("#PopDiv").hide();
		//此处焦点不回到文本框否则会再次触发弹出层		TextBox.focus();
		//给需要同步赋值的字段设定值
		if (subIndex(OutFields,",") > -1) set_other_field_value( $("#PopSelect").val() );
	}
}

//文本框按键事件
function input_down(theInput) {
	TextBox = theInput;
	//方向下键
	if ((event.keyCode == 40)) {
		$("#PopSelect").focus();
	}
	//ESC或者TAB隐藏选择层
	if ((event.keyCode == 27) || (event.keyCode == 9)) {
		$("#PopDiv").hide();
	}
};

//显示下拉列表层,参数：当前Input控件,完整查询语句,需要取值字段列表
function popup_grid(theInput, theFields, SQL) {
	TextBox = theInput;
	OutFields = theFields;
	theValue = theInput.value;
	//值不为空，不是按向下键，不是ESC键，则显示层
	if ((theValue!="") && (event.keyCode!=40) && (event.keyCode!=27) ) {
		var theDiv = document.getElementById("PopDiv");
		var theSelect = document.getElementById("PopSelect");
		if (theValue.length > 0) {
			//删除下拉列表的旧内容
			for ( var i = theSelect.options.length - 1; i >= 0; i--) {
				theSelect.options[i] = null;
			}
			//计算弹出层的位置并显示
			var x = theInput.offsetLeft, y = theInput.offsetTop;
			while (theInput = theInput.offsetParent) {
				x += theInput.offsetLeft;
				y += theInput.offsetTop;
			}
			theDiv.style.left = x;
			theDiv.style.top = y + 22;
			$("#PopDiv").show();
			//替换SQL中的带:号的变量
			SubSQL = SQL;
			while (subIndex(SubSQL,":") > -1){
				//从:开始取到末尾
				SubSQL = SubSQL.substring(subIndex(SubSQL,":")+1);
				//若存在空格，则取空格前。若存在)，则取)前。
				if (subIndex(SubSQL," ") > -1){
					FieldName = SubSQL.substring(0,subIndex(SubSQL," "));
				} else if (subIndex(SubSQL,")") > -1){
					FieldName = SubSQL.substring(0,subIndex(SubSQL,")"));
				} else {
					FieldName = SubSQL;
				}
				DataSet = FieldName.substring(0,subIndex(FieldName,"_"));
				FieldName = FieldName.substring(subIndex(FieldName,"_")+1);
				if (DataSet=="M") {
					//从主表取值
					SQL = SQL.replace(":"+DataSet+"_"+FieldName,"'"+$("#"+FieldName).val()+"'");
				} else {
					//从明细表取值，当前编辑行的编辑器PopEditors，按列名取值
		            for (j=0;j<PopEditors.length;j++){
		            	if (PopEditors[j].field==FieldName){
		            		SQL = SQL.replace(":"+DataSet+"_"+FieldName,"'"+PopEditors[j].target.val()+"'");
		            	}
		            }
				}
			}
			//获取数据添加到下拉列表
			$.ajax({
				async : false,
				contentType : "application/x-www-form-urlencoded;charset=UTF-8",
				dataType : "json",
				type : "POST",
				url : "../servlet/ComboGrid",
				data : {
					"OldValue" : theValue,
					"SQL" : SQL
				},
				success : function(data) {
					$("#PopSelect").empty();
					JsonData = data;
					for ( var key in data) {
						var option = "<option value=\"" + key + "\">" + data[key]	+ "</option>";
						$("#PopSelect").append($(option));
					}
				}
			});
		}
	} else {
		$("#PopDiv").hide();
	}
}

//OutFields中保存了需要同步赋值的字段名，M_代表主表D1_代表第一个明细D2_代表第二个明细，类推
function set_other_field_value( keyvalue ) {
	//拆开OutFields列表  M_ProductNo,M_ProductName,M_ProductSpec,M_Unit
	var list = OutFields.split(",");
	var value = JsonData[keyvalue].split("|");
	i = 0;
	for ( var key in list ) {
		FieldName = list[key];
		FieldValue = value[i];
		//如果没有指定数据集则不赋值，第一个不用赋值所以i>0
		if ((i>0) && (subIndex(FieldName,"_") > -1)){
			DataSet = FieldName.substring(0,subIndex(FieldName,"_"));
			FieldName = FieldName.substring(subIndex(FieldName,"_")+1);
			if (DataSet=="M") {
				//给主表赋值
				$("#"+FieldName).val(FieldValue);
			} else {
	            //给明细表赋值，当前编辑行的编辑器PopEditors，按列名赋值
	            for (j=0;j<PopEditors.length;j++){
	            	if (PopEditors[j].field==FieldName){
	            		PopEditors[j].target.val(FieldValue);
	            	}
	            }
			}
		}
		i++;
	}
}


