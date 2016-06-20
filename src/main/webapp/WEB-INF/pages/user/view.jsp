<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    function closeCurrent(){
        if(viewSkipMode == 'tab'){
            tab.closeCurrentTab($('#tabs'));
        }else{
            $('#dialog_${lastTimer }').dialog('close');
        }
    }
</script>
<div style="padding:5px;height:auto">
    <div class="easyui-panel" fit=true style="padding:10px 10px">
        <table class="table_view" style="width: 100%" border="0" align="left" cellpadding="2" cellspacing="0">

                <tr>
                    <td width="18%" class="table_td_view">登陆名称:</td>
                    <td width="82%">${user.loginName}</td>
                </tr>
<%--                <tr>
                    <td width="18%" class="table_td_view">登陆密码:</td>
                    <td width="82%">${user.password}</td>
                </tr>--%>
                <tr>
                    <td width="18%" class="table_td_view">邮件:</td>
                    <td width="82%">${user.eamil}</td>
                </tr>
                <tr>
                    <td width="18%" class="table_td_view">:</td>
                    <td width="82%">${user.mobile}</td>
                </tr>
                <tr>
                    <td width="18%" class="table_td_view">创建时间:</td>
                    <td width="82%">${user.createTime}</td>
                </tr>
                <tr>
                    <td width="18%" class="table_td_view">用户状态:</td>
                    <td width="82%">${user.status}</td>
                </tr>
<%--                <tr>
                    <td width="18%" class="table_td_view">激活码:</td>
                    <td width="82%">${user.activationKey}</td>
                </tr>--%>
        </table>
        <hr>
        <div id="dlg-buttons" style="text-align:center;">
            <a id="btnReset" onclick="closeCurrent()" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">关闭</a>
        </div>
    </div>
</div>