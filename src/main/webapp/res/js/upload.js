function uploadImg(fileid,action,da,showid,timer,bathPath){
    var file = $('#'+fileid).val();
    if(file == ""){
        return;
    }else{
        msg.showProcess('正在上传');
        $.ajaxFileUpload({
                url: action, //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: fileid, //文件上传域的ID
                type : 'post',
                dataType : 'JSON', //返回值类型 一般设置为json
                data:da,
                success: function (data, status){
                    msg.closeProgress();
                    if(data.isSuccess == 'true'){
                        $('#span_'+fileid).html(data.title+'上传成功');

                        $('#'+showid).append('<div id="img_'+data.id+'_'+timer+'">');
                        $('#'+showid).append('<br><img width="200px;" height="200px" src="'+bathPath+ data.filePath +'">');
                        $('#'+showid).append('<br>'+data.title+'<button onclick="deleteImg'+timer+'('+ data.id+')">删除</button>');
                        $('#'+showid).append('<button onclick="setMain'+timer+'('+ data.id+')">设为主图</button>');
                        $('#'+showid).append('</div>');

                    }else{
                        msg.alert('请选择合适大小的文件重试!');
                    }
                },
                error: function (data, status, e){
                    msg.closeProgress();
                    msg.alert('请选择合适大小的文件重试!!!');
                }
            }
        )
        return false;
    }
}

function uploadFile(fileid,action,da){
    var file = $('#'+fileid).val();
    if(file == ""){
        return;
    }else{
        msg.showProcess('正在上传');
        $.ajaxFileUpload({
                url: action, //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: fileid, //文件上传域的ID
                type : 'post',
                dataType : 'JSON', //返回值类型 一般设置为json
                data:da,
                success: function (data, status){
                    msg.closeProgress();
                    if(data.isSuccess == 'true'){
                        $('#span_'+fileid).html(data.title+'上传成功');
                    }else{
                        msg.alert('请选择合适大小的文件重试!');
                    }
                },
                error: function (data, status, e){
                    msg.closeProgress();
                    msg.alert('请选择合适大小的文件重试!!!');
                }
            }
        )
        return false;
    }
}