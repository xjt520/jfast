<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function(){
        var frozenColumns = [[{field : 'id',title : '编号',width : 60,checkbox : true,halign:'center'}]];
        var columns = [[
                        {field : 'postTitle',title : '标题',width : 150,halign:'center',align:'left'},
//                        {field : 'postAuthor',title : '对应作者id',width : 150,halign:'center',align:'center'},
//                        {field : 'postContent',title : '正文',width : 150,halign:'center',align:'center'},
                        {field : 'postExcerpt',title : '摘要',width : 150,halign:'center',align:'left'},
//                        {field : 'postStatus',title : '文章状态',width : 150,halign:'center',align:'center'},
//                        {field : 'postDate',title : '发布时间',width : 150,halign:'center',align:'center',formatter:formatTime},
                        {field : 'postDate',title : '发布时间',width : 150,halign:'center',align:'center'},
//                        {field : 'postModified',title : '修改时间',width : 150,halign:'center',align:'center'},
//                        {field : 'postCategory',title : '分类id',width : 150,halign:'center',align:'center'},
                      ]];
        dg.datagrid($('#datagrid_${timer }'), '#tb_${timer }','posts/list', frozenColumns, columns);
    });
</script>

<div id="tb_${timer }" style="padding:5px;height:auto">
    <div id="button_${timer }" style="margin-bottom:5px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="dg.insertRecord('${timer }','posts/add', '新增')" id="/posts/add_${timer }">新增</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="dg.updateRecord('${timer }','posts/update','id','修改')" id="/posts/update_${timer }">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-tip" plain="true" onclick="dg.viewRecord('${timer }','posts/view','id','查看')" id="/posts/view_${timer }">查看</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="dg.deleteRecord($('#datagrid_${timer }'),'posts/delete','id')" id="/posts/delete_${timer }">删除</a>
    </div>
    <div style="margin-bottom:5px">
        <form id="form_${timer }">
            <span style="padding-left:5px">查询条件：</span>
            <input class="easyui-textbox" data-options="prompt:'请输入标题'" style="width:200px;height:26px" name="postTitle_string_like" />
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="dg.queryRecord($('#form_${timer }'),$('#datagrid_${timer }'))" >查询</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="dg.resetRecord($('#form_${timer }'),$('#datagrid_${timer }'))" >重置</a>
        </form>
    </div>
</div>
<div id="dialog_${timer }"></div>
<table id="datagrid_${timer }"></table>