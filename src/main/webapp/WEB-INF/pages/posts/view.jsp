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
                    <td width="18%" class="table_td_view">标题:</td>
                    <td width="82%">${posts.postTitle}</td>
                </tr>
<%--                <tr>
                    <td width="18%" class="table_td_view">对应作者id:</td>
                    <td width="82%">${posts.postAuthor}</td>
                </tr>--%>
                <tr>
                    <td width="18%" class="table_td_view">正文:</td>
                    <td width="82%">${posts.postContent}</td>
                </tr>
                <tr>
                    <td width="18%" class="table_td_view">摘要:</td>
                    <td width="82%">${posts.postExcerpt}</td>
                </tr>
<%--                <tr>
                    <td width="18%" class="table_td_view">文章状态:</td>
                    <td width="82%">${posts.postStatus}</td>
                </tr>--%>
                <tr>
                    <td width="18%" class="table_td_view">发布时间:</td>
                    <td width="82%">${posts.postDate}</td>
                </tr>
<%--                <tr>
                    <td width="18%" class="table_td_view">修改时间:</td>
                    <td width="82%">${posts.postModified}</td>
                </tr>--%>
<%--                <tr>
                    <td width="18%" class="table_td_view">分类id:</td>
                    <td width="82%">${posts.postCategory}</td>
                </tr>--%>
        </table>
        <hr>
        <div id="dlg-buttons" style="text-align:center;">
            <a id="btnReset" onclick="closeCurrent()" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">关闭</a>
        </div>
    </div>
</div>