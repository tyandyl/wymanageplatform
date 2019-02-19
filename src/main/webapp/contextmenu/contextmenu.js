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
				//{text: '生成弹出窗口',onclick: function() {createWindow()}},
				{text: '生成按钮', onclick: function(e) {createButton(e)}},
				{text: '生成输入面板', onclick: function(e) {createTablePanel(e)}},
				{text: '生成下拉列表', onclick: function(e) {createComboList(e)}},
				{divider: true},
				{text: '更多...', href: '#'}
			]};
			$('div').contextify(options);


			//$(document).dblclick(function(){
			//	clearTimeout(timer);
			//});



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

function createButton(e){
	var temp= e.data.wd;     // e.target表示被点击的目标
	if(typeof(temp) =="undefined"){
		return;
	}
	wd=temp;
	var parDivM=getElementByAttr(e.data.wdName,'wd',wd);
	var top=getOffsetTop(parDivM);
	var left=getOffsetLeft(parDivM);
	relativeLeft=e.data.clickX-left;
	relativeTop=e.data.clickY-top;
	var param={"id":wd,"left":relativeLeft,"top":relativeTop,"blocktype":5};
	var type="post";
	var isAsync=false;
	var url="/wy-manage-web/Button";
	sendAjaxNews(isAsync,type,url,param,function(data){
		var div=getElementByAttr('div','wd',wd);
		var cWd=data.curWd;
		var tree=data.widgetNodeTree;
		var widgetTree=tree.nodeMap;
		var value = widgetTree[cWd];
		var littleTree = eval("(" + value + ")");
		alert(littleTree);


		var bef=div.innerHTML;
		div.innerHTML=bef+data.str;
		var curQ=getElementByAttr(data.moveWdName,'wd',data.moveWd);
		$(curQ).text( '查询');
		var options = {
		};
		if(curQ!=null){
			document.body.appendChild(curQ);
			var dragM=moveWidget(curQ);
			dragM();
		}
	});
	//openURL(param,"Button");
}




function createTablePanel(e){
	var temp= e.data.wd;     // e.target表示被点击的目标
	if(typeof(temp) =="undefined"){
		return;
	}
	wd=temp;
	var parDivM=getElementByAttr(e.data.wdName,'wd',wd);
	var top=getOffsetTop(parDivM);
	var left=getOffsetLeft(parDivM);
	relativeLeft=e.data.clickX-left;
	relativeTop=e.data.clickY-top;

	var param={"id":wd,"left":relativeLeft,"top":relativeTop};
	var type="post";
	var isAsync=false;
	var url="/wy-manage-web/TablePanel";
	sendAjaxNews(isAsync,type,url,param,function(data){
		var div=getElementByAttr('div','wd',wd);
		var bef=div.innerHTML;
		div.innerHTML=bef+data.str;
		if(data.moveWd!=null){
			var options = {
			};
			var cur=getElementByAttr(data.moveWdName,'wd',data.moveWd);
			$(cur).moveWidget(options);
		}
	});
	//openURL(param,"TablePanel");
}
function createComboList(e){
	var temp= e.data.wd;     // e.target表示被点击的目标
	if(typeof(temp) =="undefined"){
		return;
	}
	wd=temp;
	var parDivM=getElementByAttr(e.data.wdName,'wd',wd);
	var top=getOffsetTop(parDivM);
	var left=getOffsetLeft(parDivM);
	relativeLeft=e.data.clickX-left;
	relativeTop=e.data.clickY-top;

	var param={"id":wd,"left":relativeLeft,"top":relativeTop,"blocktype":4};
	var type="post";
	var isAsync=false;
	var url="/wy-manage-web/ComboList";
	sendAjaxNews(isAsync,type,url,param,function(data){
		var div=getElementByAttr('div','wd',wd);
		var bef=div.innerHTML;
		div.innerHTML=bef+data.str;

		var options = {items:[
			{text: '输入框'},
			{text: '下拉列表(列表数量小于20)'},

			{text: '弹出列表(列表数量大于20)'}
			]
		};
		if(data.moveWd!=null){
			var cur6=getElementByAttr(data.moveWdName,'wd',data.moveWd);
			if(cur6!=null){
				document.body.appendChild(cur6);
				var drag=moveWidget(cur6);
				drag();
			}


		}
		if(data.clickWd!=null){
			var cur=getElementByAttr(data.clickWdName,'wd',data.clickWd);
			if(data.recordWd!=null){
				var rec=getElementByAttr(data.recordWdName,'wd',data.recordWd);
				options.record=rec;
			}
			$(cur).combolist(options);
		}
	});
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



