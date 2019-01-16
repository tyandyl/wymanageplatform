$(document).ready(
	function(){
		var options = {items:[
			{header: '右键功能菜单'},
			{divider: true},
			{text: '生成窗口', href: '/wy-manage-web/Demo'},
			{text: '生成按钮', onclick: function() {alert("你点击了第二个链接")}},
			{text: '生成文本框', onclick: function() {alert("你点击了第3个链接")}},
			{text: '揍杨蕾', onclick: function() {alert("你揍了杨蕾，好牛逼啊")}},
			{divider: true},
			{text: '更多...', href: '#'}
		]};
		$('div').contextify(options);
	}
);

