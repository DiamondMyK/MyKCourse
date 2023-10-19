setTimeout(function () {
    layui.use(['form', 'layedit', 'laydate', 'upload'], function () {
        var $ = layui.$,
            form = layui.form,
            layer = layui.layer,
            layedit = layui.layedit,
            upload = layui.upload,
            laydate = layui.laydate;

        var uploadInst = upload.render({
                          elem: '#uploadFile', //绑定元素（上传文件的id）
                          url: '', //上传接口
                          method: 'POST',
                          auto: false,
                          accept: 'images',//指定允许上传时校验的文件类型
                          acceptMime: 'image/*',//规定打开文件选择框时，筛选出的文件类型，值为用逗号隔开的 MIME 类型列表
                          field: 'file',//设定文件域的字段名
                          //bindAction: '#submit', //指向一个按钮触发上传
                          size: 102400,//限制文件大小10M
                          multiple: false,
                          data: {},
                          choose: function (obj) {
                              obj.preview(function (index, file, result) {
                                  if (file.name.length > 0) {
                                      $("#prove").val(result);
                                      $('#demo1').attr('src', result);//图片链接（base64）
                                  }
                              });
                          },
                          before: function (obj) {
                          },

                          done: function (res, index, upload) {//上传完毕回调
                          }
                          , error: function () {//请求异常回调
                layer.closeAll('loading');
                layer.alert('网络异常，请稍后重试！');
            }
                      });

        //监听提交
        form.on('submit(form)', function (data) {
            var param = data.field;
            if(param.prove==''){
                layer.msg('请上传证明！', {anim: 6, icon: 5, time: 1500, shade: 0.1});
                return false;
            }
            if(param.reason==''){
                layer.msg('请填写原因！', {anim: 6, icon: 5, time: 1500, shade: 0.1});
                return false;
            }
            param.level = "modifyAudit"
            $.ajax({
                       type: "post",
                       url: "ModifyAuditServlet",
                       data: param,
                       dataType: "json",//预期服务器返回的数据类型
                       success: function (data) {
                           if (data == true) {
                               layer.msg('修改成功！', {anim: 3, icon: 6, time: 2000, shade: 0.1});
                               setTimeout(function () {
                                   // 关闭自身窗口
                                   var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                   parent.layer.close(index); //再执行关闭
                                   // 刷新父窗口
                                   parent.layui.table.reload('userTable');
                               }, 1000);
                           } else{
                               layer.msg('修改失败！', {anim: 6, icon: 5, time: 1500, shade: 0.1});
                           }
                       },
                       error: function (xhr,data) {
                           window.location = '../../views/error/500.html';
                       }
                   });
            return false;
        });
    });
}, 100);
