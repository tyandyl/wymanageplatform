/* jQuery Contextify | (c) 2014-2016 Adam Bouqdib | abemedia.co.uk/license */

/*global define */

;(function( factory ) {
	if ( typeof define === "function" && define.amd ) {

		// AMD. Register as an anonymous module.
		define([ "jquery" ], factory );
	} else {

		// Browser globals
		factory( jQuery, window );
	}
}(function ( $, window ) {

	var pluginName = "moveDrag";

	function Drag( element ) {
		this.elementNode = element;
		this.elementNode.me=this;//保存自身的引用
		this.init();
	}

	Drag.z = 999;

	Drag.prototype.init = function () {

		this.elementNode.onmousedown=function(e){
			e = e || window.event;
			var self=this.me;//获得拖动对象
			var node = self.elementNode;//获得拖动元素
			node.offset_x = e.clientX - node.offsetLeft;
			node.offset_y = e.clientY - node.offsetTop;
			document.wyMoveDrag=self;
			//非点击元素node注册，是每个元素注册document
			document.onmouseup = function(e){
				node=document.wyMoveDrag.elementNode;
				//alert(node.style.left+","+node.style.top);
				document.onmouseup = null;
				document.onmousemove = null;
				document.onmousedown = null;
				document.wyMoveDrag=null;
				node.onmousedown = null;
			};

			document.onmousemove = function(e){
				node=document.wyMoveDrag.elementNode;
				e = e || window.event;
				node.style.cursor = "pointer";
				!+"\v1"? document.selection.empty() : window.getSelection().removeAllRanges();
				var _left = e.clientX - node.offset_x,
						_top = e.clientY - node.offset_y;

				node.style.left = _left  + "px";
				node.style.top = _top  + "px";

			};
			node.style.zIndex = ++Drag.z;
			return false;
		}

	};

	$.fn[pluginName] = function ( element) {
		return new Drag(element);
	};

}));
