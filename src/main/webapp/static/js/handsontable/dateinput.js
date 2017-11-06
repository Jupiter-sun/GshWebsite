function getData(linenum, colnum) {
	var tArray = new Array(); // 先声明一维
	for ( var k = 0; k < linenum; k++) { // 一维长度为i,i为变量，可以根据实际情况改变

		tArray[k] = new Array(); // 声明二维，每一个一维数组里面的一个元素都是一个数组；

		for ( var j = 0; j < colnum; j++) { // 一维数组里面每个元素数组可以包含的数量p，p也是一个变量；

			tArray[k][j] = ""; // 这里将变量初始化，我这边统一初始化为空，后面在用所需的值覆盖里面的值
		}
	}
	return tArray;
}

// 导入数据函数
function importdata() {
	$('#importdatamodalid').modal('show');
}

function importnow(){
	var xhr = new XMLHttpRequest();

	// 上传中设置上传的百分比
	/*
	 * xhr.upload.addEventListener("progress", function(evt) { if
	 * (evt.lengthComputable) { var percentComplete = Math.round(evt.loaded *
	 * 100 / evt.total); document.getElementById("show" + fileId +
	 * "Me").innerHTML = '上传中' + percentComplete + "%"; } else {
	 * document.getElementById("show" + fileId + "Me").innerHTML = '无法计算'; } },
	 * false);
	 */
	// 请求完成后执行的操作
	xhr.addEventListener("load", function(evt) {

		$('#importdatamodalid').modal('hide');
		
		$("#importbuttonid").html(
				"<i class='fa fa-cloud-upload fa-lg'></i> 开始导入");
		$("#importbuttonid").attr('disabled', false); // 对按钮设置disabled属性

		$("#sureimportbuttonid").html("立即导入");
	    $("#sureimportbuttonid").attr('disabled', false); 
	    
		var message = evt.target.responseText;
		if (message != "") {
			var arr = message.split("@@@@@");
			if (arr[0] == "0") {
				toastr.error('', '导入失败,您还没有登录，请先退出后再进行登录');
			} else if (arr[0] == "1") {
				window.parent.showsuccessmsg('导入成功!');
				// 切换到主界面
				window.parent.tabclick("0");
				// 开始刷新界面
				window.parent.refreshpage();
			} else if (arr[0] == "2") {
				toastr.error('', '导入失败,导入的房间数量不能超过2000间!');
			} else if (arr[0] == "3") {
				// 没有创建的楼栋名称字符串
				var noexistflattext = "";
				var childarr = arr[1].split("@#$%&");
				var count = childarr.length;
				for ( var i = 0; i < count; i++) {
					if (childarr[i] != "") {
						noexistflattext += childarr[i] + "、";
					}
				}
				if (noexistflattext != "") {
					noexistflattext = noexistflattext.substring(0,
							noexistflattext.length - 1);
				}
				// 如果为分散式
				if (1 == customertype) {
					toastr.error('', '导入失败,您没有创建以下小区:' + noexistflattext);
				} else {
					toastr.error('', '导入失败,您没有创建以下楼栋:' + noexistflattext);
				}

			} else if (arr[0] == "4") {
				// 如果为分散式
				if (1 == customertype) {
					toastr.error('', '导入失败,没有找到相应的小区');
				} else {
					toastr.error('', '导入失败,没有找到相应的楼栋');
				}
			} else if (arr[0] == "5") {
				toastr.warning('', '导入过程中部分数据导入失败,您可以重新导入,也可以手动写入导入失败的数据');
				// 开始刷新界面
				window.parent.refreshpage();
			} else if (arr[0] == "6") {
				var noemptyroomtext = "";
				var childarr = arr[1].split("@#$%&");
				var count = childarr.length;
				for ( var i = 0; i < count; i++) {
					if (childarr[i] != "") {
						noemptyroomtext += childarr[i] + "、";
					}
				}
				if (noemptyroomtext != "") {
					noemptyroomtext = noemptyroomtext.substring(0,
							noemptyroomtext.length - 1);
				}
				// 如果为分散式
				if (1 == customertype) {
					toastr.error('', '导入失败,' + noemptyroomtext
							+ "中的房间有人居住,请先退房或取消预定");
				} else {
					toastr.error('', '导入失败,' + noemptyroomtext
							+ "中的房间有人居住,请先退房或取消预定");
				}
			} else if (arr[0] == "7") {
				toastr.error('', '导入失败,初始化失败!');
			} else if (arr[0] == "8") {
				toastr.warning('', '导入数据不能为空!');
			} else if (arr[0] == "9") {
				toastr.error('',
						'导入失败,时间格式不正确,正确的格式为:xxxx-xx-xx(例如:2015-08-08)');
			} else if (arr[0] == "10") {
				toastr.error('', '导入失败,房间数据写入失败,请您重新导入!');
			} else if (arr[0] == "11") {
				toastr.error('', '导入失败,缴费至数据写入失败,请您重新导入!');
			} else if (arr[0] == "12") {
				toastr.error('',
						'导入失败,姓名、合同起止时间、手机号这四个值只能同时为空或同时不为空,请您确认后重新导入!');
			} else {
				toastr.error('', '导入失败!');
			}
		} else {
			toastr.error('', '导入失败,请重新操作!');
		}
	}, false);
	// 请求error
	xhr.addEventListener("error", function(evt) {
		
		$('#importdatamodalid').modal('hide');
		
		$("#importbuttonid").html(
				"<i class='fa fa-cloud-upload fa-lg'></i> 开始导入");
		$("#importbuttonid").attr('disabled', false); // 对按钮设置disabled属性
		
		$("#sureimportbuttonid").html("立即导入");
	    $("#sureimportbuttonid").attr('disabled', false);
	    
		toastr.error('', '导入失败,请重新进行操作!');
	}, false);

	$("#importbuttonid").html(
			"<i class='fa fa-cloud-upload fa-lg'></i> 正在导入...");
	$("#importbuttonid").attr('disabled', true); // 对按钮设置disabled属性

	$("#sureimportbuttonid").html("正在导入...");
    $("#sureimportbuttonid").attr('disabled', true); // 对按钮设置disabled属性

	var fd = new FormData();
	fd.append("data", JSON.stringify(hot1.getData()));
	// 发送请求
	xhr.open("POST", "../fang/importdatatodatabase.php");
	xhr.send(fd);
}
// 窗口大小自适应

var winWidth = 0;
var winHeight = 0;

var primary = document.getElementById('example1');
function findDimensions() // 函数：获取尺寸
{
	// 获取窗口宽度
	if (window.innerWidth)
		winWidth = window.innerWidth;
	else if ((document.body) && (document.body.clientWidth))
		winWidth = document.body.clientWidth;
	// 获取窗口高度
	if (window.innerHeight)
		winHeight = window.innerHeight;
	else if ((document.body) && (document.body.clientHeight))
		winHeight = document.body.clientHeight;
	// 通过深入Document内部对body进行检测，获取窗口大小
	if (document.documentElement && document.documentElement.clientHeight
			&& document.documentElement.clientWidth) {
		winHeight = document.documentElement.clientHeight;
		winWidth = document.documentElement.clientWidth;
	}
	// 设定#primary高度

	primary.style.height = winHeight - 90 + "px";

}
findDimensions();
// 调用函数，获取数值
window.onresize = findDimensions;