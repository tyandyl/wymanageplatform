var wd=null;
var relativeTop=null;
var relativeLeft=null;
$(document).ready(
	function(){
		var options = {items:[
			{header: '右键功能菜单'},
			{divider: true},
			{text: '生成窗口', href: '/wy-manage-web/Window'},
			{text: '生成按钮', onclick: function() {createButton()}},
			{text: '生成文本框', onclick: function() {alert("你点击了第3个链接")}},
			{text: '揍杨蕾', onclick: function() {alert("你揍了杨蕾，好牛逼啊")}},
			{divider: true},
			{text: '更多...', href: '#'}
		]};
		$('div').contextify(options);
	}

);

function createButton(){
	var param={"id":wd,"left":relativeLeft,"top":relativeTop,"isAnalyze":1};
	openButton(param);
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

function openButton(param){
	$.ajax({
		type: "post",
		url: "/wy-manage-web/Button",
		async: false,
		data: param,
		success: function (data) {
			var div=getElementByAttr('div','wd',wd);
			div.innerHTML=data;
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			console.log("error")
		}
	});
}



