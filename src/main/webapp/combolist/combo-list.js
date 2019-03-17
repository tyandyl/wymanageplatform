;(function( factory ) {
	if ( typeof define === "function" && define.amd ) {

		// AMD. Register as an anonymous module.
		define([ "jquery" ], factory );
	} else {

		// Browser globals
		factory( jQuery, window );
	}
}(function ( $, window ) {
	    var pluginName = 'combolist',
        defaults = {
            items: [],
            action: "combolistmenu",
            combolistId: "combolist-cell-id",
            combolistClass: "combolist-cell-class"
        };

		var  combolistNumId = 0;
		
		function Plugin( element, options ) {
			this.element = element;

			this.options = $.extend( {}, defaults, options) ;

			this._defaults = defaults;
			this._name = pluginName;

			this.init();
		}

		Plugin.prototype.init = function () {
			var options = $.extend( {}, this.options, $(this.element).data());
			options.id = combolistNumId;

			$(this.element)
				.on('mousedown', function (e){
					 $(this).css('background-color','#ffffff');
				})
				.on('click', function (e) {
					e.preventDefault();
					if(combolistNumId==0){
						var menu_div=$("<div id='window2-combo-list-cell-content'></div>");
						var l = options.items.length;
						var i;
						var menu = $(options.record);
						for (i = 0; i < l; i++) {
							var item = options.items[i];
							var div="<div>"+item.text+"</div>";
							var el = $(div);
							el
								.css('position','relative')
								.css('z-index','999')
								.css('margin','0px')
								.css('padding','0px')
								.css('top','2px')
								.css('left','-2px')
								.css('width','202px')
								.css('height','23px')
								.css('background-color','#ffffff')
								.css('font',"13px/1.7 verdana,'\\5b8b\\4f53',arial,georgia,helvetica,sans-serif")
								.css('border-left','1px solid #A9D4E7')
								.css('border-right','1px solid #A9D4E7');
							if(i==(l-1)){
								el.css('border-bottom','1px solid #A9D4E7');
							}
							el
							.on('mouseenter', function () {
								 $(this).css('background-color','#e3f4fe');
							})
							.on('mouseleave', function () {
								 $(this).css('background-color','#ffffff');
							})
							.on('click', function () {
								//$(options.record).text($(this).text());
								var vn=$(this).text();
								$(options.record2).text(vn);
								$(menu_div).hide();
								//combolistNumId=0;
							})
							;

							menu_div.append(el);
						}
						menu.append(menu_div);
						$(menu_div).show();
					}else{
						$("#window2-combo-list-cell-content").toggle();
					}
					
					combolistNumId++;
				})
				.on('mouseup', function () {
					 $(this).css('background-color','#e3f4fe');
				});

			
		};

		$.fn[pluginName] = function ( options ) {
			if (!$.data(this, 'plugin_' + pluginName)) {
				$.data(this, 'plugin_' + pluginName, new Plugin( this, options ));
			}
		 };

}));