function moveWidget(el) {

	var elemDrag=el;
	var buttonNumId=0;
	var wdDrag=null;
	function drag(){
		$(elemDrag)
				.on('click', function (ev) {
					ev.preventDefault();
					var e = ev || window.event;
					clearTimeout(timer);
					timer = setTimeout(function() {
						var temp=$(ev.target).attr("wd");     // e.target表示被点击的目标
						if(typeof(temp) =="undefined"){
							return;
						}

						wdDrag=temp;
						if(buttonNumId==0){
							var parDivM=getElementByAttr(e.target.localName,'wd',wdDrag);
							var top=getOffsetTop(parDivM);
							var left=getOffsetLeft(parDivM);
							relativeLeft=e.clientX-left;
							relativeTop=e.clientY-top;
							createClick(wdDrag);
							buttonNumId++;
						}else {
							buttonNumId=0;
						}
					}, 300);

				})
		;
	}

	return drag;

}
