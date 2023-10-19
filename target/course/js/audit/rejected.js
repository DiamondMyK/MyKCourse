setTimeout(function () {
    layui.use(['form', 'layedit', 'laydate'], function () {
        var $ = layui.$,
            form = layui.form,
            layer = layui.layer,
            layedit = layui.layedit,
            laydate = layui.laydate;

        //监听提交
        form.on('submit(form)', function (data) {
            var param = data.field;
            if(param.rejectedreason==''){
                layer.msg('请填写驳回理由！', {anim: 6, icon: 5, time: 1500, shade: 0.1});
                return false;
            }
            param.level = 'one';
            if(param.status == '主管教师审批中'){
                param.level = 'two'
            }
            param.op='0';
            $.ajax({
                       type: "post",
                       url: "ModifyAuditServlet",
                       data: param,
                       dataType: "json",//预期服务器返回的数据类型
                       success: function (data) {
                           if (data == true) {
                               layer.msg('已驳回申请！', {anim: 3, icon: 6, time: 2000, shade: 0.1});
                               setTimeout(function () {
                                   // 关闭自身窗口
                                   var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                   parent.layer.close(index); //再执行关闭
                                   // 刷新父窗口
                                   parent.layui.table.reload('userTable');
                               }, 1000);
                           }  else{
                               layer.msg('操作失败！', {anim: 6, icon: 5, time: 1500, shade: 0.1});
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
