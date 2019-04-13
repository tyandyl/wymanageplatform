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

    var pluginName = 'contextify',
        defaults = {
            items: [],
            action: "contextmenu",
            menuId: "contextify-menu",
            menuClass: "dropdown-menu",
            headerClass: "dropdown-header",
            dividerClass: "divider",
            before: function(element,options) {beforeCheck(element,options)}
        },
        contextifyId = 0;

    function beforeCheck(element,options){
        var val = element;
        var moved=null;
        while(val != null){
            var el=$(val);
            var isMoved=el.data("widgetNodeA");
            if(isMoved==1){
                moved=val;
                break;
            }
            val = val.offsetParent;
        }
        if(moved!=null){
            options.widgetNodeA=moved;
            options.items=[{header: '右键功能菜单'},
                {divider: true},
                {text: '移动', onclick: function(e) {moveDragA(e)}},
                {text: '设置', onclick: function(e) {createWindow(e)}},
                {divider: true},
                {text: '更多...', href: '#'}];
        }
    }

    function moveDragA(e){
        $(e.data.widgetNodeA).moveDrag(e.data.widgetNodeA);
    }

    function Plugin( element, options ) {
        this.element = element;

        this.options = $.extend( {}, defaults, options) ;

        this.element.me=this;

        this._defaults = options.items;
        this._name = pluginName;

        this.init();
    }

    Plugin.prototype.init = function () {
        var options = $.extend( {}, this.options, $(this.element).data());
        options.id = contextifyId;

        $(this.element)
            .attr('data-contextify-id', options.id)
            .on('contextmenu', function (e) {
                e.preventDefault();
                var self=this.me;//获得拖动对象
                var _defaults = self._defaults;
                options.items=_defaults;
                // run before
                if(typeof(options.before) === 'function') {
                    options.before(e.target, options);
                }
                //每个元素注册上点击的父界面,创建控件时使用
                var temp=$(e.target).attr("wd");
                var wdName=e.target.localName;
                options.wd=temp;
                options.wdName=wdName;
                options.clickX=e.clientX;
                options.clickY=e.clientY;

                var menu = $('<ul class="' + options.menuClass + '" role="menu" id="' + options.menuId + '" data-contextify-id="' + options.id + '"/>');

                menu.data(options);

                var l = options.items.length;
                var i;

                for (i = 0; i < l; i++) {
                    var item = options.items[i];
                    var el = $('<li/>');

                    if (item.divider) {
                        el.addClass(options.dividerClass);
                    }
                    else if (item.header) {
                        el.addClass(options.headerClass);
                        el.html(item.header);
                    }
                    else {
                        el.append('<a/>');
                        var a = el.find('a');

                        if (item.href) {
                            a.attr('href', item.href);
                        }
                        if (item.onclick) {
                            a.on('click', options, item.onclick);
                            a.css('cursor', 'pointer');
                        }
                        if (item.data) {
                            for (var data in item.data) {
                                menu.attr('data-' + data, item.data[data]);
                            }
                                a.data(item.data);
                            }
                        a.html(item.text);
                    }

                    menu.append(el);
                }

                var currentMenu = $("#" + options.menuId);

                if (currentMenu.length > 0) {
                    if(currentMenu !== menu) {
                        currentMenu.replaceWith(menu);
                    }
                }
                else {
                    $('body').append(menu);
                }

                var clientTop = $(window).scrollTop() + e.clientY,
                    x = (menu.width() + e.clientX < $(window).width()) ? e.clientX : e.clientX - menu.width(),
                    y = (menu.height() + e.clientY < $(window).height()) ? clientTop : clientTop - menu.height();

                menu
                    .css('top',  e.clientY)
                    .css('left', e.clientX)
                    .css('position', 'fixed')
                    .show();
            })
        .parents().on('mouseup', function () {
            $("#" + options.menuId).hide();
        });

        contextifyId++;
    };

    Plugin.prototype.destroy = function () {
        var el = $(this.element),
            options = $.extend({}, this.options, el.data()),
            menu = $("#" + options.menuId);

        el
            .removeAttr('data-contextify-id')
            .off('contextmenu')
            .parents().off('mouseup', function () {
                menu.hide();
            });

        menu.remove();
    };

    $.fn[pluginName] = function ( options ) {
        return this.each(function () {
            if( $.data(this, 'plugin_' + pluginName) && Object.prototype.toString.call(options) === '[object String]' ) {
                $.data(this, 'plugin_' + pluginName)[options]();
            }
            else if (!$.data(this, 'plugin_' + pluginName)) {
                $.data(this, 'plugin_' + pluginName, new Plugin( this, options ));
            }
        });
    };

}));
