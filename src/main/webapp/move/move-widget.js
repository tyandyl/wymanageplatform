;(function( factory ) {
	if ( typeof define === "function" && define.amd ) {

		// AMD. Register as an anonymous module.
		define([ "jquery" ], factory );
	} else {

		// Browser globals
		factory( jQuery, window );
	}
}(function ( $, window ) {
	    var pluginName = 'moveWidget',
        defaults = {
            items: []
        };

		var  buttonNumId = 0;
		
		function Plugin( element, options ) {
			this.element = element;

			this.options = $.extend( {}, defaults, options) ;

			this._defaults = defaults;
			this._name = pluginName;

			this.init();
		}

		Plugin.prototype.init = function () {
			var options = $.extend( {}, this.options, $(this.element).data());
			//var l = options.items.length;
			//var i;
			//for (i = 0; i < l; i++) {
			//	var item = options.items[i];
			//	var content=item.text;
			//	$(this.element).text(content);
			//}
			$(this.element)
					.on('click', function (ev) {
						ev.preventDefault();
						var e = ev || window.event;
						clearTimeout(timer);
						timer = setTimeout(function() {
							var temp=$(ev.target).attr("wd");     // e.target表示被点击的目标
							if(typeof(temp) =="undefined"){
								return;
							}

							wd=temp;
							if(buttonNumId==0){
								var parDivM=getElementByAttr(e.target.localName,'wd',wd);
								var top=getOffsetTop(parDivM);
								var left=getOffsetLeft(parDivM);
								relativeLeft=e.clientX-left;
								relativeTop=e.clientY-top;
								createClick(wd);
								buttonNumId++;
							}else {
								buttonNumId=0;
							}
						}, 300);

					})
			;



		};

		//$.fn[pluginName] = function ( options ) {
		//	if (!$.data(this, 'plugin_' + pluginName)) {
		//		$.data(this, 'plugin_' + pluginName, new Plugin( this, options ));
		//	}
		// };

	$.fn['moveWidgetButton'] = function ( options ) {
		if (!$.data(this, 'plugin_' + pluginName)) {
			$.data(this, 'plugin_' + pluginName, new Plugin( this, options ));
		}
	};

	$.fn['moveWidgetComboList'] = function ( options ) {
		if (!$.data(this, 'plugin_' + pluginName)) {
			$.data(this, 'plugin_' + pluginName, new Plugin( this, options ));
		}
	};

}));