function DatagridToCsv(JSONData,ShowLabel,FileName) {
	// If JSONData is not an object then JSON.parse will parse the JSON string
	var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;
	var CSV = '';
	// This condition will generate the Label/Header
	if (ShowLabel) {
		// This loop will extract the label from 1st index of on array
		for ( var index in arrData[0]) {
			// Now convert each value to string and comma-seprated
			row += index + ',';
		} 
		// 自动遍历列
		row = row.slice(1, -1);
		// append Label row with line break
		CSV += row + '\r\n';
	}
	// 1st loop is to extract each row
	for ( var i = 0; i < arrData.length; i++) {
		var row = "";
		// 2nd loop will extract each column and convert it in string，comma-seprated
		for ( var index in arrData[i]) {
			row += '"' + arrData[i][index] + '",';
		}
		row = row.slice(1, row.length-1);
		// add a line break after each row
		CSV += row + '\r\n';
	}
	if (CSV == '') {
		return;
	}
	// Initialize file format you want csv or xls
	//var uri = 'data:text/csv;' + encodeURI(CSV);
	//var uri = 'data:text/csv;charset=utf-8;\uFEFF' + encodeURI(CSV);
	//var uri = 'data:text/csv;charset=ISO8859-1,' + encodeURI(CSV);
	//var uri = 'data:text/csv;charset=GB2312,' + encodeURI(CSV);
	var uri = 'data:text/csv;charset=UTF-8;\uFEFF' + encodeURI(CSV);// 不乱码
	//var uri = 'data:text/csv;charset=utf-8,' + (new TextEncoder('utf8')).encode(CSV);
	var csvlink = document.createElement("a");
	csvlink.href = uri;
	// set the visibility hidden so it will not effect on your web-layout
	csvlink.style = "visibility:hidden";
	csvlink.download = FileName + ".csv";
	// this part will append the anchor tag and remove it after automatic click
	document.body.appendChild(csvlink);
	csvlink.click();
	document.body.removeChild(csvlink);
}