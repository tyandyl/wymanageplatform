var wd=null;
var relativeTop=null;
var relativeLeft=null;
$(document).ready(
	function(){
		var options = {items:[
			{header: '右键功能菜单'},
			{divider: true},
			{text: '生成后台窗口', href: '/wy-manage-web/Window'},
			{text: '生成弹出窗口',onclick: function() {createWindow()}},
			{text: '生成按钮', onclick: function() {createButton()}},
			{text: '生成输入面板', onclick: function() {createTablePanel()}},
			{divider: true},
			{text: '更多...', href: '#'}
		]};
		$('div').contextify(options);
	}

);

function createWindow(){
	var param={"id":wd,"left":relativeLeft,"top":relativeTop,"win":2};
	openURL(param,"Window");
}

function createButton(){
	var param={"id":wd,"left":relativeLeft,"top":relativeTop};
	openURL(param,"Button");
}

function createTablePanel(){
	var param={"id":wd,"left":relativeLeft,"top":relativeTop};
	openURL(param,"TablePanel");
}
// 在页面任意位置点击而触发此事件
$(document).click(function(e) {
	var temp=$(e.target).attr("wd");     // e.target表示被点击的目标
	if(typeof(temp) =="undefined"){
		return;
	}
	wd=temp;
	var parDiv=getElementByAttr('div','wd',wd);
	var top=getOffsetTop(parDiv);
	var left=getOffsetLeft(parDiv);
	relativeLeft=e.clientX-left;
	relativeTop=e.clientY-top;
});

function getElementByAttr(tag,attr,value)
{
	var aElements=document.getElementsByTagName(tag);
	for(var i=0;i<aElements.length;i++)
	{
		if(aElements[i].getAttribute(attr)==value){
			return aElements[i];
		}
	}
	return null;
}

function getOffsetTop(obj){
	var tmp = obj.offsetTop;
	var val = obj.offsetParent;
	while(val != null){
		tmp += val.offsetTop;
		val = val.offsetParent;
	}
	return tmp;
}
function getOffsetLeft(obj){
	var tmp = obj.offsetLeft;
	var val = obj.offsetParent;
	while(val != null){
		tmp += val.offsetLeft;
		val = val.offsetParent;
	}
	return tmp;
}

function openURL(param,url){
	$.ajax({
		type: "post",
		url: "/wy-manage-web/"+url,
		async: false,
		data: param,
		success: function (data) {
			var div=getElementByAttr('div','wd',wd);
			var result=eval("("+data+")");
			if(result.strStyle!=null && result.strStyle!=""){
				var style = document.createElement('style');
				style.type = 'text/css';
				style.innerHTML=result.strStyle;
				var head=document.getElementsByTagName('head').item(0);
				head.appendChild(style);
			}
			var bef=div.innerHTML
			div.innerHTML=bef+result.str;
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			console.log("error")
		}
	});
}



