<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<jsp:include page="/WEB-INF/pages/common/include_all.jsp"></jsp:include>

<script type="text/javascript">
var viewSkipMode = '${viewSkipMode}';
$(function () {
	    //动态菜单数据
	    var menu = [{
            text : "基础管理",
            state : "open",
            children : [
                <%--{--%>
                    <%--text : "用户管理",--%>
                    <%--iconCls:"icon-gears",--%>
                    <%--attributes : {--%>
                        <%--url : "${pageContext.request.contextPath }/user/index"--%>
                    <%--}--%>
                <%--}--%>
                <%--,--%>
               {
                    text : "内容管理",
                    iconCls:"icon-gears",
                    attributes : {
                        url : "${pageContext.request.contextPath }/posts/index"
                    }
                }
//                ,{
//                    text : "分类管理",
//                    iconCls:"icon-gears",
//                    attributes : {
//                        url : "category/index"
//                    }
//                }

	            ]
	        }
	    ];
	    //实例化树形菜单
	    $("#tree").tree({
	        data : menu,
//	        lines : true,
	        onClick : function (node) {
	            if (node.attributes) {
	            	Open(node.text, node.attributes.url);
	            }
	        }
	    });
	    //绑定tabs的右键菜单
	    $("#tabs").tabs({
	        onContextMenu : function (e, title) {
	            e.preventDefault();
	            $('#tabsMenu').menu('show', {
	                left : e.pageX,
	                top : e.pageY
	            }).data("tabTitle", title);
	        }
	    });
	    //实例化menu的onClick事件
	    $("#tabsMenu").menu({
	        onClick : function (item) {
	            CloseTab(this, item.name);
	        }
	    });
	});
//在右边center区域打开菜单，新增tab
function Open(text, url) {
    if ($("#tabs").tabs('exists', text)) {
        $('#tabs').tabs('select', text);
    } else {
        $('#tabs').tabs('add', {
            title : text,
            closable : true,
            href : url
        });
    }
}
//几个关闭事件的实现
function CloseTab(menu, type) {
    var curTabTitle = $(menu).data("tabTitle");
    var tabs = $("#tabs");
    
    if (type === "close") {
        tabs.tabs("close", curTabTitle);
        return;
    }
    
    var allTabs = tabs.tabs("tabs");
    var closeTabsTitle = [];
    
    $.each(allTabs, function () {
        var opt = $(this).panel("options");
        if (opt.closable && opt.title != curTabTitle && type === "Other") {
            closeTabsTitle.push(opt.title);
        } else if (opt.closable && type === "All") {
            closeTabsTitle.push(opt.title);
        }
    });
    
    for (var i = 0; i < closeTabsTitle.length; i++) {
        tabs.tabs("close", closeTabsTitle[i]);
    }
}
</script>
</head>
<body class="easyui-layout">
  <div data-options="region:'north'" style="height:80px;padding:10px">
        <%--<h1>快速审核后台管理</h1>--%>
  </div>
  <div data-options="region:'center',iconCls:'icon-ok'" >
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
      <div title="首页">
      </div>
    </div>
  </div>
  
  <div data-options="region:'west'" style="width:15%;height:100%;" border="false" >
    <div class="easyui-accordion" style="width:100%;height:100%;" border="false" >
        <div title="菜单" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
            <ul id="tree"></ul>
        </div>
    </div>
  </div>
  
  <div id="tabsMenu" class="easyui-menu" style="width:120px;" >  
    <div name="close">关闭</div>  
    <div name="Other">关闭其他</div>  
    <div name="All">关闭所有</div>
  </div>
  
</body>
</html>