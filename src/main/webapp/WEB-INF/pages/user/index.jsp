<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function(){
        var frozenColumns = [[{field : 'id',title : '编号',width : 60,checkbox : true,halign:'center'}]];
        var columns = [[
                        {field : 'account',title : '登陆名称',width : 150,halign:'center',align:'center'},
                        {field : 'username',title : '用户名',width : 150,halign:'center',align:'center'},
                        {field : 'mobile',title : '手机号码',width : 150,halign:'center',align:'center'},
                        {field : 'last_login_time',title : '最后登录时间',width : 150,halign:'center',align:'center'},
                      ]];
        dg.datagrid($('#datagrid_${timer }'), '#tb_${timer }','${pageContext.request.contextPath }/user/list', frozenColumns, columns);
    });
</script>

<div id="tb_${timer }" style="padding:5px;height:auto">
    <div id="button_${timer }" style="margin-bottom:5px">
        <%--<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="dg.insertRecord('${timer }','user/add', '新增用户')" id="/user/add_${timer }">新增</a>--%>
        <%--<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="dg.updateRecord('${timer }','user/update','id','修改用户')" id="/user/update_${timer }">修改</a>--%>
        <%--<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="dg.updateRecord('${timer }','user/updatePwd','id','修改密码')" id="/user/updatePwd_${timer }">修改密码</a>--%>
        <%--<a href="#" class="easyui-linkbutton" iconCls="icon-view" plain="true" onclick="dg.viewRecord('${timer }','user/view','id','查看')" id="/user/view_${timer }">查看</a>--%>
        <%--<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="dg.deleteRecord($('#datagrid_${timer }'),'user/delete','id')" id="/user/delete_${timer }">删除</a>--%>
    </div>
    <div style="margin-bottom:5px">
        <form id="form_${timer }">
            <span style="padding-left:5px">查询条件：</span>
            <input class="easyui-textbox" data-options="prompt:'请输入登录名'" style="width:200px;height:26px" name="account_string_like" />
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="dg.queryRecord($('#form_${timer }'),$('#datagrid_${timer }'))" >查询</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="dg.resetRecord($('#form_${timer }'),$('#datagrid_${timer }'))" >重置</a>
        </form>
    </div>
</div>
<div id="dialog_${timer }"></div>
<table id="datagrid_${timer }"></table>