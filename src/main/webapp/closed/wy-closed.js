;(function( factory ) {
	if ( typeof define === "function" && define.amd ) {

		// AMD. Register as an anonymous module.
		define([ "jquery" ], factory );
	} else {

		// Browser globals
		factory( jQuery, window );
	}
}(function ( $, window ) {
	    var pluginName = 'wyClick',
        defaults = {
        };

		function Plugin( element, options ) {
			this.element = element;

			this.options = $.extend( {}, defaults, options) ;

			this._defaults = defaults;
			this._name = pluginName;
			this.init();
		}

		Plugin.prototype.init = function () {
			var options = this.options;

			$(this.element)
				.on('click', function (e) {
					e.preventDefault();
					if(options.record!=null){
						$(options.record).empty();
						options.record.parentNode.removeChild(options.record);
					}

				});
		};

		$.fn[pluginName] = function ( options ) {
			if (!$.data(this, 'plugin_' + pluginName)) {
				$.data(this, 'plugin_' + pluginName, new Plugin( this, options ));
			}
		 };

}));