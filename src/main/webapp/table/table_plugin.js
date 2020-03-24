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

    var pluginName = 'tablePlugin';
    var defaults = {
            items: [],
            clickClass: "contextmenu",
            mouseoverClass: "contextify-menu"
        };

    function Plugin( element ,options) {
        this.element = element;

        this.element.me=this;

        this.options = $.extend( {}, defaults, options) ;

        this.init();
    }

    Plugin.prototype.init = function () {
        var borderContent=null;
        $(this.element).find("td").each(function () {
            $(this).mouseenter(function(){
                borderContent= $(this).css("border");
                $(this).css("border","2px solid #A9D4E7");
            });
            $(this).mouseleave(function(){
                $(this).css("border",borderContent);
            });
        });



    };

    $.fn[pluginName] = function ( options ) {
        return this.each(function () {
            if (!$.data(this, 'plugin_' + pluginName)) {
                $.data(this, 'plugin_' + pluginName, new Plugin( this, options ));
            }
        });
    };

}));
