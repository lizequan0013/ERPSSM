//扩展EasyUI的Datagrid的Editor
$.extend($.fn.datagrid.defaults.editors, {
	popup:{
		//初始化编辑器并且返回目标对象。
		init: function(container, options){
			var input = $('<input type="text" class="wu-popup" onkeyup="'+options.keyup+'" onkeydown="javascript:input_down(this)">').appendTo(container);
			return input;
		},
		//如果必要就销毁编辑器。
		destroy: function(target){
			$(target).remove();
		},
		//从编辑器的文本获取值。
		getValue: function(target){
			return $(target).val();
		},
		//给编辑器设置值。
		setValue: function(target, value){
			$(target).val(value);
		},
		//如果必要就调整编辑器的尺寸。
		resize: function(target, width){
			$(target)._outerWidth(width);
		}
	}
});