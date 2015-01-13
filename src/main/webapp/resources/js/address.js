//页面中第一个下拉选的onchange事件
window.onload = function() {
	document.getElementById("province").onchange = function() {
		// 1 获取页面中选中的省份名称
		var provinceValue = document.getElementById("province").value;

		// 2 解析xml文件
		var docXml = parseXML("resources/cities.xml");

		// 3 遍历xml文件中的省份
		var provinceXmlElements = docXml.getElementsByTagName("province");

		// 清空
		/*
		 * <select id="city" name="city"> <option value="">请选择.....</option>
		 * <option value="长春">长春</option> </select>
		 */
		var cityElement = document.getElementById("city");
		var optionsOld = cityElement.getElementsByTagName("option");
		// for(var z=1;z<optionsOld.length;){
		// cityElement.removeChild(optionsOld[z]);
		// }

		for (var z = optionsOld.length - 1; z > 0; z--) {
			cityElement.removeChild(optionsOld[z]);
		}

		// 4 遍历xml文件中的所有省份
		for (var i = 0; i < provinceXmlElements.length; i++) {
			var provinceXmlElement = provinceXmlElements[i];

			var provinceXmlValue = provinceXmlElement.getAttribute("name");

			// 5 判断页面中的省份名称与xml文件中的省份是否一致
			if (provinceValue == provinceXmlValue) {
				// 6 如果一致，读取对应省份的所有城市信息
				var cityXmlElements = provinceXmlElement
						.getElementsByTagName("city");

				for (var j = 0; j < cityXmlElements.length; j++) {
					var cityXmlElement = cityXmlElements[j];

					var cityXmlValue = cityXmlElement.firstChild.nodeValue;

					// 7 插入到页面中的第二个下拉选中
					/*
					 * <select id="city" name="city"> <option value="">请选择.....</option>
					 * <option value="长春">长春</option> </select>
					 */
					var optionElement = document.createElement("option");
					optionElement.setAttribute("value", cityXmlValue);
					var cityText = document.createTextNode(cityXmlValue);
					optionElement.appendChild(cityText);

					cityElement.appendChild(optionElement);

				}
			}
		}
	}
}
/**
 * 跨浏览器的解析xml文件的函数
 * 
 * @param {Object}
 *            fileXmlName
 */
function parseXML(fileXmlName) {
	var xmlDoc;
	try {// Internet Explorer 创建一个空的xml文档
		xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
	} catch (e) {
		try {// Firefox, Mozilla, Opera, 创建一个空的xml文档
			xmlDoc = document.implementation.createDocument("", "", null);
		} catch (e) {
		}
	}
	// 关闭异步加载
	xmlDoc.async = false;
	// 加载xml文件
	xmlDoc.load(fileXmlName);
	return xmlDoc;
}