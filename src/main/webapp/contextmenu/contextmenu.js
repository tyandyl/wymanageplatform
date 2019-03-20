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
				{text: '生成表格列表', onclick: function(e) {createTableList(e)}},
				{divider: true},
				{text: '更多...', href: '#'}
			]};
			$('div').contextify(options);

		}

);

function createWindow(e){
	var divM=$("body").children("div").get(0);
	var temp=$(divM).attr('wd');
	if(typeof(temp) =="undefined"){
		alert("没有获取定位的div");
		return;
	}
	var wdMain=null;
	var kWidgetNode=e.data.kWidgetNode;
	var kMain=null;
	if(kWidgetNode!=null){
		kMain=kWidgetNode.kWidget;
		if(kMain!=null){
			wdMain=$(kMain).attr('wd')
		}

	}

	relativeLeft=e.data.clickX;
	relativeTop=e.data.clickY;
	var param={"id":temp,"left":relativeLeft+'px',"top":relativeTop+'px',"handleType":2,"position":"absolute"};
	var type="post";
	var isAsync=false;
	var url="/wy-manage-web/OpenWindow";
	sendAjaxNews(isAsync,type,url,param,function(data){
		var kNodeA=null;
		for(var i=0;i<data.length;i++){
			var parentWd = data[i].parentWd;
			var parentTagName = data[i].parentTagName;
			var parentTagDiv=getElementByAttr(parentTagName,'wd',parentWd);

			var curWd = data[i].curWd;
			var curTagName = data[i].curTagName;

			var curDiv=document.createElement(curTagName);

			var el = $(curDiv);
			if(data[i].outContentValue!=null){
				//var t=document.createTextNode(data[i].outValue);
				//curDiv.appendChild(t);
				curDiv.innerHTML=data[i].outContentValue;
			}
			if(curTagName=='button'){
				el.on('click', function (e) {
					var param={"handleType":3,"targetId":wdMain};
					$(kMain).empty();
					kMain.parentNode.removeChild(kMain);
					sendAjaxNews(false,"post","/wy-manage-web/TablePanel",param,function(data){
						var kNodeA=null;
						for(var i=0;i<data.length;i++){
							var parentWd = data[i].parentWd;
							var parentTagName = data[i].parentTagName;
							var parentTagDiv=getElementByAttr(parentTagName,'wd',parentWd);

							var curWd = data[i].curWd;
							var curTagName = data[i].curTagName;

							var curDiv=document.createElement(curTagName);
							var el = $(curDiv);
							el.attr('wd',curWd);
							var curPros=data[i].curPros;
							var sList=curPros.split(";");
							if(data[i].outContentValue!=null){
								//var t=document.createTextNode(data[i].outValue);
								//curDiv.appendChild(t);
								curDiv.innerHTML=data[i].outContentValue;
							}
							for(var y=0;y<sList.length;y++){
								var pros=sList[y];
								if(pros!=null){
									var pro=pros.split(":");
									if(pro!=null){
										el.css(pro[0],pro[1]);
									}
								}
							}
							parentTagDiv.appendChild(curDiv);
							//if(i==0){
							//	el.data("widgetNodeA","1");
							//}
							if(data[i].flag){
								kNodeA={};
								kNodeA.kWidget=curDiv;
								el.data("kWidgetNode",kNodeA);
							}else{
								if(kNodeA!=null){
									el.data("kWidgetNode",kNodeA);
								}
							}
						}
					});
				});
			}
			el.attr('wd',curWd);
			var curPros=data[i].curPros;
			var sList=curPros.split(";");
			for(var y=0;y<sList.length;y++){
				var pros=sList[y];
				if(pros!=null){
					var pro=pros.split(":");
					if(pro!=null){
						el.css(pro[0],pro[1]);
					}
				}
			}
			parentTagDiv.appendChild(curDiv);
			//if(i==0){
			//	el.data("widgetNodeA","1");
			//}
			if(data[i].flag){
				kNodeA={};
				kNodeA.kWidget=curDiv;
				el.data("kWidgetNode",kNodeA);
			}else{
				if(kNodeA!=null){
					el.data("kWidgetNode",kNodeA);
				}
			}
		}
	});

	//openURL(param,"Window");
}

function createTableList(e){
	var temp= e.data.wd;     // e.target表示被点击的目标，这里是弹出框上的a元素
	if(typeof(temp) =="undefined"){
		return;
	}
	wd=temp;
	var parDivM=getElementByAttr(e.data.wdName,'wd',wd);
	var top=getOffsetTop(parDivM);
	var left=getOffsetLeft(parDivM);
	relativeLeft=e.data.clickX-left;
	relativeTop=e.data.clickY-top;
	var param={"id":wd,"left":relativeLeft+'px',"top":relativeTop+'px',"handleType":2,"position":"absolute"};
	var type="post";
	var isAsync=false;
	var url="/wy-manage-web/TableList";
	sendAjaxNews(isAsync,type,url,param,function(data){
		var kNodeA=null;
		for(var i=0;i<data.length;i++){
			var parentWd = data[i].parentWd;
			var parentTagName = data[i].parentTagName;
			var parentTagDiv=getElementByAttr(parentTagName,'wd',parentWd);

			var curWd = data[i].curWd;
			var curTagName = data[i].curTagName;

			var curDiv=document.createElement(curTagName);
			var el = $(curDiv);
			el.attr('wd',curWd);
			if(data[i].outContentValue!=null){
				//var t=document.createTextNode(data[i].outValue);
				//curDiv.appendChild(t);
				curDiv.innerHTML=data[i].outContentValue;
			}
			var curPros=data[i].curPros;
			var sList=curPros.split(";");
			for(var y=0;y<sList.length;y++){
				var pros=sList[y];
				if(pros!=null){
					var pro=pros.split(":");
					if(pro!=null){
						el.css(pro[0],pro[1]);
					}
				}
			}
			parentTagDiv.appendChild(curDiv);
			//if(i==0){
			//	el.data("widgetNodeA","1");
			//}
			if(data[i].flag){
				kNodeA={};
				kNodeA.kWidget=curDiv;
				el.data("kWidgetNode",kNodeA);
			}else{
				if(kNodeA!=null){
					el.data("kWidgetNode",kNodeA);
				}
			}
		}
	});
}

function createButton(e){
	var temp= e.data.wd;     // e.target表示被点击的目标，这里是弹出框上的a元素
	if(typeof(temp) =="undefined"){
		return;
	}
	wd=temp;
	var parDivM=getElementByAttr(e.data.wdName,'wd',wd);
	var top=getOffsetTop(parDivM);
	var left=getOffsetLeft(parDivM);
	relativeLeft=e.data.clickX-left;
	relativeTop=e.data.clickY-top;
	var param={"id":wd,"left":relativeLeft+'px',"top":relativeTop+'px',"handleType":2,"position":"absolute"};
	var type="post";
	var isAsync=false;
	var url="/wy-manage-web/Button";
	sendAjaxNews(isAsync,type,url,param,function(data){
		var kNodeA=null;
		for(var i=0;i<data.length;i++){
			var parentWd = data[i].parentWd;
			var parentTagName = data[i].parentTagName;
			var parentTagDiv=getElementByAttr(parentTagName,'wd',parentWd);

			var curWd = data[i].curWd;
			var curTagName = data[i].curTagName;

			var curDiv=document.createElement(curTagName);
			var el = $(curDiv);

			var curPros=data[i].curPros;
			var sList=curPros.split(";");
			for(var y=0;y<sList.length;y++){
				var pros=sList[y];
				if(pros!=null){
					var pro=pros.split(":");
					if(pro!=null){
						el.css(pro[0],pro[1]);
					}
				}
			}
			el.text( '查询');
			el.attr('wd',curWd);
			parentTagDiv.appendChild(curDiv);
			//if(i==0){
			//	el.data("widgetNodeA","1");
			//}
			if(data[i].flag){
				kNodeA={};
				kNodeA.kWidget=curDiv;
				el.data("kWidgetNode",kNodeA);
			}else{
				if(kNodeA!=null){
					el.data("kWidgetNode",kNodeA);
				}
			}
		}
	});
}

function WyTemp(){};

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

	var param={"id":wd,"left":relativeLeft+'px',"top":relativeTop+'px',"handleType":2,"position":"absolute"};
	var type="post";
	var isAsync=false;
	var url="/wy-manage-web/ComboList";
	sendAjaxNews(isAsync,type,url,param,function(data){
		var options = {items:[
			{text: '输入框'},
			{text: '下拉列表(列表数量小于20)'},

			{text: '弹出列表(列表数量大于20)'}
		]
		};
		var wyTemp = new WyTemp();
		var kNodeA=null;
		for(var i=0;i<data.length;i++){
			var parentWd = data[i].parentWd;
			var parentTagName = data[i].parentTagName;
			var parentTagDiv=getElementByAttr(parentTagName,'wd',parentWd);

			var curWd = data[i].curWd;
			var curTagName = data[i].curTagName;

			var curDiv=document.createElement(curTagName);
			wyTemp[curWd]=curDiv;
			var el = $(curDiv);
			el.attr('wd',curWd);
			var curPros=data[i].curPros;
			var sList=curPros.split(";");
			for(var y=0;y<sList.length;y++){
				var pros=sList[y];
				if(pros!=null){
					var pro=pros.split(":");
					if(pro!=null){
						el.css(pro[0],pro[1]);
					}
				}
			}
			parentTagDiv.appendChild(curDiv);
			//if(i==0){
			//	el.data("widgetNodeA","1");
			//}
			if(data[i].flag){
				kNodeA={};
				kNodeA.kWidget=curDiv;
				el.data("kWidgetNode",kNodeA);
			}else{
				if(kNodeA!=null){
					el.data("kWidgetNode",kNodeA);
				}
			}
			var rd=data[i].registerParam.register;
			if(rd.length>0){
				for(var y=0;y<rd.length;y++){
					var rdList=rd[y].split(",");
					if(rdList[0]=="dropDown"){
						options.record=wyTemp[rdList[1]];
						options.record2=wyTemp[rdList[2]];
						$(curDiv).unbind().combolist(options);
					}
				}
			}


		}
	});
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

	var param={"id":wd,"left":relativeLeft+'px',"top":relativeTop+'px',"handleType":2,"position":"absolute"};
	var type="post";
	var isAsync=false;
	var url="/wy-manage-web/TablePanel";
	sendAjaxNews(isAsync,type,url,param,function(data){
		var kNodeA=null;
		for(var i=0;i<data.length;i++){
			var parentWd = data[i].parentWd;
			var parentTagName = data[i].parentTagName;
			var parentTagDiv=getElementByAttr(parentTagName,'wd',parentWd);

			var curWd = data[i].curWd;
			var curTagName = data[i].curTagName;

			var curDiv=document.createElement(curTagName);
			var el = $(curDiv);
			el.attr('wd',curWd);
			var curPros=data[i].curPros;
			var sList=curPros.split(";");
			for(var y=0;y<sList.length;y++){
				var pros=sList[y];
				if(pros!=null){
					var pro=pros.split(":");
					if(pro!=null){
						el.css(pro[0],pro[1]);
					}
				}
			}
			parentTagDiv.appendChild(curDiv);
			//if(i==0){
			//	el.data("widgetNodeA","1");
			//}
			if(data[i].flag){
				kNodeA={};
				kNodeA.kWidget=curDiv;
				el.data("kWidgetNode",kNodeA);
			}else{
				if(kNodeA!=null){
					el.data("kWidgetNode",kNodeA);
				}
			}
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



