;(function( factory ) {
	if ( typeof define === "function" && define.amd ) {

		// AMD. Register as an anonymous module.
		define([ "jquery" ], factory );
	} else {

		// Browser globals
		factory( jQuery, window );
	}
}(function ( $, window ) {
	    var pluginName = 'buttonClick',
        defaults = {
        };

		function Plugin( element, options ) {
			this.element = element;

			this.options = $.extend( {}, defaults, options) ;

			this._defaults = defaults;
			this._name = pluginName;
			this.optionValues={};
			this.init();
		}

		Plugin.prototype.init = function () {
			var options = this.options;
			var optionValues=this.optionValues;

			$(this.element)
				.on('click', function (e) {
					e.preventDefault();
					if(options.record!=null){
						var list=options.record.selectedOptions;
						if(list!=null){
							for(var i=0;i<list.length;i++){
								var ud=$(list[i]).attr('wd');
								if(optionValues[ud]==null){
									var option=$("<option value='"+list[i].value+"'>"+list[i].innerHTML+"</option>");
									$(options.record2).append(option);
									optionValues[ud]=list[i].value;
								}

							}
						}
					}

				});
		};

		$.fn[pluginName] = function ( options ) {
			if (!$.data(this, 'plugin_' + pluginName)) {
				$.data(this, 'plugin_' + pluginName, new Plugin( this, options ));
			}
		 };

}));