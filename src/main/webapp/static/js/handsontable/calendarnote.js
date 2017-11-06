//保存日历备忘录(添加)
function savecalendarrecord(){
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			$("#sumbitforcalendarid").text("提交");
			$("#sumbitforcalendarid").attr('disabled', false); // 对按钮设置disabled属性
			
			if (xmlhttp.responseText != "") {
            	var arr = xmlhttp.responseText.split("@@@@");
            	if(arr.length >= 7){
            		
            		var obj = new Object();
                    obj.id=arr[0];
                    obj.title=arr[1];
					obj.start=arr[2];
					obj.end=arr[3];
					obj.allDay=arr[4];
					obj.color=arr[5];
					
                    $("#calendar").fullCalendar('renderEvent', obj, false);
                	$('#noteinput').modal('hide');
                	if("0" == arr[6]){
                		//设置index.php中的日历备忘录条数
                    	$("#recordnumid", parent.document).html("");	
                	}else{
                		$("#recordnumid", parent.document).html(arr[6]);
                	}
                	//parent.document.getElementById('recordnumid').innerHtml = arr[6];
                	toastr.success('','操作成功!');
            	}else{
            		toastr.error('','操作失败,请重新操作');
                }
			}else{
				toastr.error('','操作失败,请重新操作');
			}
		}
	}

	//获取textarea中的内容
	var recordtext = $("#addrecordtextareaid").val();
	
	$("#sumbitforcalendarid").text("提交中...");
	$("#sumbitforcalendarid").attr('disabled', true); // 对按钮设置disabled属性
	
	var url = "../calendar/savedataforcalendar.php?recordtext="+recordtext+"&formatDate="+formatDatestr+"&sid="+ Math.random();
	url = encodeURI(url);
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
}

//修改日历备忘录
function modifycalendarrecord(){
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			$("#editforcalendarid").text("提交");
			$("#editforcalendarid").attr('disabled', false); // 对按钮设置disabled属性
			
			if (xmlhttp.responseText != "") {
            	var arr = xmlhttp.responseText.split("@@@@");
            	if(arr.length == 6){
            		
            		var obj = new Object();
                    obj.id=arr[0];
                    obj.title=arr[1];
					obj.start=arr[2];
					obj.end=arr[3];
					obj.allDay=arr[4];
					obj.color=arr[5];
					
					$('#calendar').fullCalendar('refetchEvents'); //重新获取所有事件数据
                    //$("#calendar").fullCalendar('renderEvent', obj, false); //把从后台取出的数据进行封装以后在页面上以fullCalendar的方式进行显示(第三个参数为true表示根据上次数据累加,
                                                                              //为false表示先清空原来数据再累加)
                	$('#noteedit').modal('hide');
                    toastr.success('','操作成功!');
            	}else{
            		toastr.error('','操作失败,请重新操作');
                }
			}else{
				toastr.error('','操作失败,请重新操作');
			}
		}
	}

	//获取textarea中的内容
	var recordtext = $("#recordtextareaid").val();
	//获取id
	var recordid = document.getElementById("dataid").value;
	
	$("#editforcalendarid").text("提交中...");
	$("#editforcalendarid").attr('disabled', true); // 对按钮设置disabled属性
	
	var url = "../calendar/modifyforcalendar.php?recordtext="+recordtext+"&recordid="+recordid+"&sid="+ Math.random();
	url = encodeURI(url);
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
}

//删除日历备忘录
function deletecalendarrecord(){
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			$("#deleteforcalendarid").text("删除");
			$("#deleteforcalendarid").attr('disabled', false); // 对按钮设置disabled属性
			
			if (xmlhttp.responseText != "") {
				
				var arr = xmlhttp.responseText.split("@@@@");
            	if(arr.length >= 1){
            		if(arr[0] == "1"){
            			
            			$('#calendar').fullCalendar('refetchEvents'); //重新获取所有事件数据
                		$('#noteedit').modal('hide');
                		toastr.success('','操作成功');
                		
            			if(arr.length > 1){
            				if("0" == arr[1]){
            					//设置index.php中的日历备忘录条数
                            	$("#recordnumid", parent.document).html("");	
            				}else{
            					//设置index.php中的日历备忘录条数
                            	$("#recordnumid", parent.document).html(arr[1]);
            				}
            			}
                	}else{
                		toastr.error('','操作失败,请重新操作');
                	}
            	}else{
            		toastr.error('','操作失败,请重新操作');
            	}
			}else{
				toastr.error('','操作失败,请重新操作');
			}
		}
	}

	//获取id
	var recordid = document.getElementById("dataid").value;
	
	$("#deleteforcalendarid").text("删除中...");
	$("#deleteforcalendarid").attr('disabled', true); // 对按钮设置disabled属性
	
	var url = "../calendar/deleteforcalendar.php?recordid="+recordid+"&sid="+ Math.random();
	url = encodeURI(url);
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
}