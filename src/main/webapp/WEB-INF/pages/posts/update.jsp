<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    $(function() {
        $("#form_${timer }").form({
            url : 'posts/modify',
            dataType : "text",
            success : function(data) {
                var result = $.parseJSON(data);
                if(result.isSuccess == 'true'){
                    closeCurrent();
                    msg.message(result.msg);
                    dg.queryRecord($("#form_${lastTimer }"),$("#datagrid_${lastTimer }"));
                }else{
                    $("#span_${timer }").html(result.msg);
                    return;
                }
            },
            onSubmit : function(){
                if($(this).form('validate')){
                    return checkform${timer }();
                }else{
                    return false;
                }
            },
            onLoadError : function(data) {
                msg.alert('表单加载错误,请刷新页面或重新登录');
            }
        });

    });

    var ue = UE.getEditor('editor_${timer }');

    /**
     * 表单验证
     */
    function checkform${timer }() {
        var flag = true;
        return flag;
    }

    function closeCurrent(){
        if(viewSkipMode == 'tab'){
            tab.closeCurrentTab($('#tabs'));
        }else{
            $('#dialog_${lastTimer }').dialog('close');
        }
    }
</script>
<div style="padding:5px;height:auto">
    <div class="easyui-panel" style="width:100%;padding:30px 60px">
        <form id="form_${timer }" method="post">
            <input type="hidden" name="posts.id" value="${posts.id}">
            <table style="width: 100%">
                    <tr>
                        <td width="18%">标题:<label style="color: red">*</label></td>
                        <td width="82%">
                            <input class="easyui-textbox" data-options="required:true,validType:['length[0,50]']" style="width:200px;height:32px" id="postTitle_${timer }" name="posts.postTitle" value="${posts.postTitle}" />
                        </td>
                    </tr>
                    <tr>
                        <td width="18%">摘要:<label style="color: red">*</label></td>
                        <td width="82%">
                            <input class="easyui-textbox" data-options="required:true,validType:['length[0,50]']" style="width:400px;height:32px" id="postExcerpt_${timer }" name="posts.postExcerpt" value="${posts.postExcerpt}" />
                        </td>
                    </tr>
                    <tr>
                        <td width="18%">分类id:<label style="color: red">*</label></td>
                        <td width="82%">
                            <input class="easyui-combobox" name="posts.postCategory" id="postCategory_${timer }" style="width:200px;height:32px" value="${posts.postCategory}"
                                   data-options="url:'category/select',
            				   				 required:true,
			                    			 method:'get',
			                    			 valueField:'id',
			                    			 textField:'name',
			                    			 panelHeight:'auto',
			                    			 editable:false
			            					 " />
                        </td>
                    </tr>
                <tr>
                    <td width="18%"></td>
                    <td width="82%">
                        <!-- 加载编辑器的容器 -->
                        <script id="editor_${timer }" name="posts.postContent" type="text/plain">${posts.postContent}</script>
                    </td>
                </tr>
            </table>
            <span id="span_${timer }" style="color: red"></span>
            <hr>
            <div id="dlg-buttons" style="text-align:center;">
                <a id="btnSubmit" onclick="$('#form_${timer }').submit()" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确认</a>
                <a id="btnReset" onclick="closeCurrent()" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
            </div>
        </form>
    </div>
</div>