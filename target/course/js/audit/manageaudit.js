// 定义全局变量
var $;
layui.use(['form', 'table', 'layer', 'laydate'], function () {
    $ = layui.jquery;
    var form = layui.form,
        table = layui.table,
        layer = layui.layer,
        laydate = layui.laydate;
    // 初始化表格
    table.render({
                     // 设置ID
                     id: 'userTable',
                     // 指定原始 table 容器
                     elem: '#userTableId',
                     // 异步数据接口
                     url: 'FindAuditServlet',
                     // 设置导出按钮
                     toolbar: '#tablebar',
                     page: true,
                     //设置表头。值是一个二维数组
                     cols: [
                         [{
                             type: 'numbers',
                             width: 50,
                             title: '序号',
                             fixed: "left"
                         }, {
                             field: 'cname',
                             width: 100,
                             title: '课程名'
                         }, {
                             field: 'uname',
                             width: 100,
                             title: '申请人'
                         }, {
                             field: 'reason',
                             width: 330,
                             title: '申请原因'
                         }, {
                             field: 'prove',
                             width: 200,
                             title: '相关证明',
                             templet: '#proveBar',
                             unresize: true
                         }, {
                             field: 'status',
                             width: 200,
                             title: '审核状态'
                         }, {
                             field: 'finalonename',
                             width: 100,
                             title: '审核主讲教师'
                         }, {
                             field: 'finaltwoname',
                             width: 100,
                             title: '审核主管教师'
                         }, {
                             field: 'rejectedreason',
                             width: 230,
                             title: '驳回原因'
                         }, {
                             title: '操作',
                             minWidth: 200,
                             templet: '#currentTableBar',
                             fixed: "right",
                             align: "center"
                         }]
                     ],
                     where: {
                         cname: '',
                         uname:'',
                         status: '',
                         role:1
                     },
                 });
    // 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
        var result = data.field;
        console.log(result);
        //执行搜索重载
        table.reload('userTable', {
            where: {
                cname: result.cname,
                uname: result.uname,
                status: result.status,
                role:1
            }
        }, 'data');
        return false;
    });
    // 监听表格编辑、删除按钮
    table.on('tool(userTableFilter)', function (obj) {
        var param = obj.data;
        console.log(param);
        if (obj.event === 'search') {
            var othis = $(this),
                method = othis.data('method');
            active[method] ? active[method].call(this, othis, param) : '';
        } else if (obj.event === 'edit') {
			var level = 'one';
			if(param.status == '主管教师审批中'){
				level = 'two'
			}
            $.getJSON('ModifyAuditServlet?level='+level+'&op=1&aid=' + param.aid, function (data) {
                if (data == true) {
                    layer.msg('操作成功！', {anim: 3, icon: 6, time: 1500, shade: 0.1});
                    //执行重载
                    table.reload('userTable', {
                        where: {
                            cname: $('#cname').val(),
                            uname: $('#uname').val(),
                            status: $('#status').val(),
                            role:1
                        }
                    }, 'data');
                } else {
                    layer.msg('操作失败！', {anim: 6, icon: 5, time: 1500, shade: 0.1});
                }
            });
            layer.close(index);
        }else if (obj.event === 'rejected') {
			var othis = $(this),
				method = othis.data('method');
			active[method] ? active[method].call(this, othis, param) : '';
		}
    });

	// 定义触发事件弹出厨框
	var active = {
		rejected: function(othis, param) {
			layer.open({
						   id: 'rejected_iframe', // 防止重复弹出
						   title: '驳回申请',
						   type: 2,
						   offset: ['20px', '25%'],
						   area: ['500px', '600px'],
						   content: ['views/audit/rejected.html', 'no'], // no：弹窗不要滑动条
						   skin: 'layui-layer-molv',
						   shade: 0.3, //不显示遮罩
						   maxmin: true, //开启最大化按钮
						   anim: 3, // 过渡效果（3或者4）
						   resize: false, // 禁止窗体拉伸
						   //							time : 1000,//定时关闭
						   success: function(layero, index) { //弹出层弹出后的回调函数
							   var body = layer.getChildFrame('body', index);
							   var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
							   //console.log(body.html()) //得到iframe页的body内容
							   body.find('#aid').val(param.aid);
							   body.find('#status').val(param.status);
							   body.find('#uname').val(param.uname);
							   body.find('#cname').val(param.cname);
							   // 渲染页面表单，否则不显示数据
							   iframeWin.layui.form.render();
						   }
					   });
		}
	};
});

