(function($) {
	$.fn.sidebarMenu = function(options) {
		options = $.extend( {}, $.fn.sidebarMenu.defaults, options || {});
		var target = $(this);
		target.addClass('nav');
		target.addClass('nav-list');
		if (options.data) {
			init(target, options.data);
		} else {
			if (!options.url)
				return;
			$.getJSON(options.url, options.param, function(data) {
				init(target, data);
			});
		}
		var url = window.location.pathname;
		function init(target, data) {
			$.each(data, function(i, item) {
				var li = null;
				if (isUndefinedAndEmpty(item.parentID)) {
					li = $('<li></li>');
				} else {
					li = $('<li id="parentli' + item.parentID + '"></li>');
				}
				var a = $('<a></a>');
				var icon = $('<i></i>');
				icon.addClass(item.icon);
				var text = $('<span></span>');
				text.addClass('menu-text').text(item.text);
				a.append(icon);
				a.append("&nbsp;");
				a.append(text);
				if (item.menus && item.menus.length > 0) {
					a.attr('href', '#');
					a.addClass('dropdown-toggle');
					var arrow = $('<b></b>');
					arrow.addClass('arrow').addClass('arrow fa fa-angle-down');
					a.append(arrow);
					li.append(a);
					var menus = $('<ul></ul>');
					menus.addClass('submenu');
					init(menus, item.menus);
					li.append(menus);
				} else {
					var url = getRoot()+"/"+item.url;
					var href = 'javascript:addTabs({id:\'' + item.id
							+ '\',title: \'' + item.text
							+ '\',close: true,url: \'' + url
							+ '\',parentID: \'' + item.parentID + '\'});';
					a.attr('href', href);
					a.append('<i class="menu-icon fa fa-caret-right"></i>');
					li.append(a);
					li.attr("onclick", "selectMenu(this)");
				}
				target.append(li);
			});
		}
	}

	$.fn.sidebarMenu.defaults = {
		url : null,
		param : null,
		data : null
	};
})(jQuery);

var addTabs = function(options) {
	// var rand = Math.random().toString();
	// var id = rand.substring(rand.indexOf('.') + 1);
	var url = window.location.protocol + '//' + window.location.host;
	options.url = url + options.url;
	id = "tab_" + options.id;
	$("#showMenuDiv .active").removeClass("active");
	// 如果TAB不存在，创建一个新的TAB
	if (!$("#" + id)[0]) {
		loadingDiv();
		// 固定TAB中IFRAME高度
		var mainHeight = getBodyHeight() - topPx;
		// 创建新TAB的title
		title = '<li role="presentation" id="tab_' + id
				+ '" onclick="selectliMenu(' + options.parentID
				+ ')"><a href="#' + id + '" aria-controls="' + id
				+ '" role="tab" data-toggle="tab">' + options.title;
		// 是否允许关闭
		if (options.close) {
			title += '&nbsp;<i class="fa fa-remove" tabclose="' + id + '" style="cursor: pointer;"></i>';
		}
		title += '</a></li>';
		// 是否指定TAB内容
		if (options.content) {
			content = '<div role="tabpanel" class="tab-pane" id="' + id + '">'
					+ options.content + '</div>';
		} else {// 没有内容，使用IFRAME打开链接
			content = '<div role="tabpanel" class="tab-pane" id="'
					+ id
					+ '"><iframe src="'
					+ options.url
					+ '" width="'
					+ (strToInt($("#showMenuDiv").css("width")) - 5)
					+ '" height="'
					+ mainHeight
					+ '" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" style="margin: -15px -1px 0px -12px" allowtransparency="yes"></iframe></div>';
		}
		// 加入TABS
		$(".nav-tabs").append(title);
		$(".tab-content").append(content);
	}
	// 激活TAB
	$("#tab_" + id).addClass('active');
	$("#" + id).addClass("active");
};
var closeTab = function(id) {
	// 如果关闭的是当前激活的TAB，激活他的前一个TAB
	if ($("#tabsMenu li.active").attr('id') == "tab_" + id) {
		$("#tab_" + id).prev().addClass('active');
		$("#" + id).prev().addClass('active');
	}
	// 关闭TAB
	$("#tab_" + id).remove();
	$("#" + id).remove();
};
$(function() {
	mainHeight = $(document.body).height() - 45;
	$('.main-left,.main-right').height(mainHeight);
	$("[addtabs]").click(function() {
		addTabs( {
			id : $(this).attr("id"),
			title : $(this).attr('title'),
			close : true
		});
	});

	$(".nav-tabs").on("click", "[tabclose]", function(e) {
		id = $(this).attr("tabclose");
		closeTab(id);
	});
});
/**
 * 选中li菜单 左边
 * 
 * @param this_
 */
function selectMenu(this_) {
	$("#menu .active").removeClass("active");
	$(this_).addClass("active");
	// 父元素
	$(this_).parent().parent().addClass("active");
}

/**
 * 选中li菜单 右边
 * 
 * @param parentID
 */
function selectliMenu(parentID) {
	var this_ = $("#parentli" + parentID);
	$("#menu .active").removeClass("active");
	$("#menu .open").removeClass("open");
	$(this_).addClass("active");
	// 父元素
	$(this_).parent().parent().addClass("active").addClass("open");
	$(this_).parent().show();
}

$(function() {
	var root = $("#root").val();
	// 设置首页高度
	$("#indexPage").css("height", getBodyHeight() - topPx);
	var jsonData = strToJson($("#menuData").val());
	// 设置菜单数据
	$('#menu').sidebarMenu(jsonData);
});
