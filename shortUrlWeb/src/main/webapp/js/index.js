function init(){
	getServers();
}

/**
 * 获取服务器列表
 */
function getServers(){
	$.post("api/getServices.json",null,showService,"json");
}

function selectServer(id,domain){
	$("#server").text(domain);
}
function showService(res){
	var dom = $("#domain");
	dom.html("")
	var data = res.data;
	for ( var i in data) {
		var d = data[i]
		dom.append("<option value=\""+ d.id +"\">"+ d.name +"</option>");
	}
}
function onSubmit(){
	var serverId = $("#domain").val();
	if(serverId == ""){
		error("请选择服务器");
		$("#datas").css("display", "none");// display
		return;
	}
	var url = $("#url").val();
	if(url == ""){
		error("请输入网址");
		$("#datas").css("display", "none");// display
		return;
	}
	var alias = $("#alias").val();
	submit(serverId, url, alias);
}
function submit(serviceId,url,alias){
	var dom = $("#result");
	dom.removeClass("alert-danger");
	dom.removeClass("alert-success");
	dom.html("")
	$.getJSON("api/create.json",{serviceId:serviceId,longUrl:url,alias:alias},createCall);
//	$("#datas").css("display", "");// display
}
function createCall(res){
	if(res == null){
		
	}else{
		if(res.status == 0){
			succ("短网址：" + res.data)
		}else{
			error("错误：" + res.data)
		}
	}
	
	
}
function succ(data){
	var dom = $("#result");
	dom.removeClass("alert-danger");
	dom.addClass("alert-success");
	dom.html(data);
}
function error(data){
	var dom = $("#result");
	dom.removeClass("alert-success");
	dom.addClass("alert-danger");
	dom.html(data);
}

function toggleAlias(){
	var dom = $("#alias");
	dom.val("");
	dom.toggle();
}