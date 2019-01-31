var wd=null;
var relativeTop=null;
var relativeLeft=null;
var page=null;
var parDiv=null;
var timer = null;
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


			$(document).dblclick(function(){
				clearTimeout(timer);
			});


			// 在页面任意位置点击而触发此事件
			$(document).click(function(ev) {
				var e = ev || window.event;
				clearTimeout(timer);
				timer = setTimeout(function() {
					var temp=$(e.target).attr("wd");     // e.target表示被点击的目标
					if(typeof(temp) =="undefined"){
						return;
					}
					wd=temp;
					var parDivM=getElementByAttr(e.target.localName,'wd',wd);
					var top=getOffsetTop(parDivM);
					var left=getOffsetLeft(parDivM);
					relativeLeft=e.clientX-left;
					relativeTop=e.clientY-top;
					if(parDivM==parDiv || parDiv==null){
						createClick(wd);
					}else {
						parDiv.onmousemove = null;
						parDiv.onmouseup = null;
						parDiv.onmousedown=null;
						parDiv==null;
					}
				}, 300);


			});


		}

);

function createClick(wd){
	var param={"id":wd};
	var type="post";
	var isAsync=false;
	var url="/wy-manage-web/DblClick";
	sendAjaxNews(isAsync,type,url,param,function(data){
		parDiv=getElementByAttr(data.tagName,'wd',data.wd);

		var relaX=null;
		var relaY =null;
		parDiv.onmousedown = function(ev) {
			var ev = ev || window.event;

			relaX = ev.clientX - this.offsetLeft;
			relaY = ev.clientY - this.offsetTop;
			parDiv.onmousemove = function(ev) {
				var ev = ev || window.event;
				parDiv.style.left = ev.clientX - relaX + 'px';
				parDiv.style.top = ev.clientY - relaY + 'px';
			};
		};

		parDiv.onmouseup = function(ev) {
			var ev = ev || window.event;
			parDiv.onmousemove = null;
			parDiv.onmouseup = null;
			parDiv.onmousedown=null;
		};
	});
}
function createWindow(){
	var param={"id":wd,"left":relativeLeft,"top":relativeTop};
	var type="post";
	var isAsync=false;
	var url="/wy-manage-web/OpenWindow";
	sendAjaxNews(isAsync,type,url,param,function(data){
		var div=getElementByAttr('div','wd',wd);
		var bef=div.innerHTML;
		div.innerHTML=bef+data.str;
	});

	//openURL(param,"Window");
}

function createButton(){
	var param={"id":wd,"left":relativeLeft,"top":relativeTop};
	var type="post";
	var isAsync=false;
	var url="/wy-manage-web/Button";
	sendAjaxNews(isAsync,type,url,param,function(data){
		var div=getElementByAttr('div','wd',wd);
		var bef=div.innerHTML;
		div.innerHTML=bef+data.str;
	});
	//openURL(param,"Button");
}

function createTablePanel(){
	var param={"id":wd,"left":relativeLeft,"top":relativeTop};
	var type="post";
	var isAsync=false;
	var url="/wy-manage-web/TablePanel";
	sendAjaxNews(isAsync,type,url,param,function(data){
		var div=getElementByAttr('div','wd',wd);
		var bef=div.innerHTML;
		div.innerHTML=bef+data.str;
	});
	//openURL(param,"TablePanel");
}


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

function sendAjaxNews(isAsync,type,url,data,call){
	jQuery.ajax({
		async: isAsync,
		type: type,
		url: url,
		data: data,
		contenttype: "application/json; charset=utf-8",
		success: function (data) {
			var result = eval("(" + data + ")");
			//page=JSON.parse(data);
			call(result);
		},
		error: function () {
			alert("ajax error");
		}
	});
}



