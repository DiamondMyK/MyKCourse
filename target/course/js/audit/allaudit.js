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
                             width: 140,
                             title: '审核主讲教师'
                         }, {
                             field: 'finaltwoname',
                             width: 140,
                             title: '审核主管教师'
                         }, {
                             field: 'rejectedreason',
                             width: 230,
                             title: '驳回原因'
                         }]
                     ],
                     where: {
                         cname: '',
                         uname:'',
                         status: '',
                         role:9
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
                role:9
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
        }
    });

});

