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
				{text: '生成后台窗口', href: '/Window'},
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

//获取点击目标
function getClickTarget(e){
	//e的data是点击事件的参数
	var kWidgetNode=e.data.kWidgetNode;
	var kMain=null;
	if(kWidgetNode!=null){
		kMain=kWidgetNode.kWidget;
		if(kMain!=null){
			return $(kMain).attr('wd')
		}

	}
}

function createDivElement(data,call,call2){
	var kNodeA=null;
	//当前对象为map，存放wd和元素对象的对应关系
	var wyTemp = new WyTemp();
	var tabList=null;

	//事件参数
	var eventList=new Array();

	for(var i=0;i<data.length;i++){
		var parentWd = data[i].parentWd;
		var parentTagName = data[i].parentTagName;
		var parentTagDiv=getElementByAttr(parentTagName,'wd',parentWd);

		var curWd = data[i].curWd;
		var curTagName = data[i].curTagName;

		var curDiv=document.createElement(curTagName);
		wyTemp[curWd]=curDiv;
		var el = $(curDiv);
		if(data[i].outContentValue!=null){
			curDiv.innerHTML=data[i].outContentValue;
		}

		if(data[i].widgetName=="table_list"){
			tabList=curWd;
		}

		if(curTagName=='button'){
			var option={};
			option.wd=curWd;
			option.url=data[i].url;
			option.requestParam=data[i].registerParam.requestParam;
      option.handleType=data[i].handleType;
			el.on('click',option, function (e) {
				//targetId：有两个目标，一个前台展示，一个弹出窗口的列表
				var wdMain=e.data.wd;
				var requestParam=e.data.requestParam;
				var addressUrl=null;
				var targetList=null;
				var selectValueList=null;
				var param={"id":wdMain,"handleType":option.handleType};
				for(var p=0;p<requestParam.length;p++){
					if(requestParam[p]!=null){
						var re=null;
						var pro=requestParam[p].split(":");
						if(pro[1]!=null && pro[1]!="null" && pro[0]!=null && pro[0]!="null" && pro[2]!=null && pro[2]!="null"){
              var tagDiv=getElementByAttr(pro[1],'wd',pro[0]);
              if(pro[1]=='select'){
                var selValues=new Array();
                var list=tagDiv.options;
                if(list!=null){
                  for(var r=0;r<list.length;r++){
                    var ud=$(list[r]).attr('value');
                    selValues.push(ud);
                  }
                }
                re=selValues.join(",");
                param[pro[2]]=re;
              }else{
                param[pro[2]]=$(tagDiv).val();
              }
						}



					}
				}

				//url:"/wy-manage-web/TablePanel"
				var url=e.data.url;
				if(url==null){
					url="/TablePanel";
				}
				sendAjaxNews(false,"post",url,param,function(resultList){
					var dataResult=resultList.result;
					for(var u=0;u<dataResult.length;u++) {
						var data = dataResult[u];
						createDivElement(data,function(curTagName,curWd,parentTagDiv){
							var curTagDiv=getElementByAttr(curTagName,'wd',curWd);
							$(curTagDiv).empty();
							parentTagDiv.removeChild(curTagDiv);
						},function(curDiv){});
					}

				});
			});
		}

		if(data[i].multiple!=null){
			el.attr('multiple',"multiple");
		}
		if(data[i].value!=null && data[i].value.trim()!=""){
			el.attr('value',data[i].value);
		}

    if(data[i].url!=null && data[i].url.trim()!=""){
      el.attr('value',data[i].url);
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

		if(data[i].flag){
			kNodeA={};
			kNodeA.kWidget=curDiv;
			el.data("kWidgetNode",kNodeA);
		}else{
			if(kNodeA!=null){
				el.data("kWidgetNode",kNodeA);
			}
		}

		if(i==0){
			call(curTagName,curWd,parentTagDiv);
		}

		var rd=data[i].registerParam.register;
		if(rd!=null && rd.length>0){
			var eventNode={
				"rd":rd,
				"curEle":curDiv
			};
			eventList.push(eventNode);
		}
	}
	call2(kNodeA.kWidget);
	handleEvent(eventList,wyTemp);
}

function handleEvent(eventList,wyTemp){
	if(eventList==null || typeof(eventList)=="undefined"){
		return;
	}
	for(var i=0;i<eventList.length;i++){
		var rd=eventList[i].rd;
		var curDiv=eventList[i].curEle;

		for(var u=0;u<rd.length;u++){
			var rdList=rd[u].split(",");

			if(rdList[0]=="dropDown"){
				var options = {items:[
					{text: '输入框',value:'1'},
					{text: '下拉列表(列表数量小于20)',value:'2'},

					{text: '弹出列表(列表数量大于20)',value:'3'}
				]
				};
				options.record=wyTemp[rdList[1]];
				options.record2=wyTemp[rdList[2]];

				$(curDiv).unbind().combolist(options);
			}
			if(rdList[0]=="click"){
				var options = {};
				if(typeof(rdList[1])!="undefined" && rdList[1]!=null){
					options.record=wyTemp[rdList[1]];
				}
				if(typeof(rdList[2])!="undefined" && rdList[2]!=null){
					options.record2=wyTemp[rdList[2]];
					$(curDiv).unbind().buttonClick(options);
				}
			}
			if(rdList[0]=="closed"){
				var options = {};
				options.record=wyTemp[rdList[1]];
				$(curDiv).unbind().wyClick(options);
			}
		}
	}

}

function createWindow(e){
	var divM=$("body").children("div").get(0);
	var temp=$(divM).attr('wd');
	if(typeof(temp) =="undefined"){
		alert("没有获取定位的div");
		return;
	}
	var wdMain=getClickTarget(e);
	//每次打开窗口的时候，都必须有指向targetId

	relativeLeft=e.data.clickX;
	relativeTop=e.data.clickY;
	//点击按钮打开窗口，朝向资源就是按钮。
	var param={"id":temp,"left":relativeLeft+'px',"top":relativeTop+'px',"handleType":2,"position":"absolute","targetId":wdMain};
	var type="post";
	var isAsync=false;
	var url="/OpenWindow";
	sendAjaxNews(isAsync,type,url,param,function(resultList){
		var data=resultList.result;
		createDivElement(data,function(curTagName,curWd,parentTagDiv){
		},function(curDiv){});
	});

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
	sendAjaxNews(isAsync,type,url,param,function(resultList){
		var data=resultList.result;
		createDivElement(data,
				function(curTagName,curWd,parentTagDiv){
				},function(curDiv){
					var options={};
					$(curDiv).unbind().tablePlugin(options);
				});
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
	var url="/Button";
	sendAjaxNews(isAsync,type,url,param,function(resultList){
		var kNodeA=null;
		var data=resultList.result;
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
			el.text(data[i].proDataTitle);
			el.attr('wd',curWd);
			parentTagDiv.appendChild(curDiv);

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
	var url="/ComboList";
	sendAjaxNews(isAsync,type,url,param,function(resultList){
		var data=resultList.result;
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
	var url="/TablePanel";
	sendAjaxNews(isAsync,type,url,param,function(resultList){
		var kNodeA=null;
		var data=resultList.result;
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
		url: "/"+url,
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



