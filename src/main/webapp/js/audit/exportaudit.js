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
                     url: 'ExportAuditServlet',
                     // 设置导出按钮
                     toolbar: '#tablebar',
                     page: false,
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
                             title: '审核主管教师',
                             templet: function (data) {
                                 if (typeof data.finaltwoname === "undefined") return "";
                                 return data.finaltwoname
                             }
                         }, {
                             field: 'rejectedreason',
                             width: 230,
                             title: '驳回原因',
                             templet: function (data) {
                                 if (typeof data.rejectedreason === "undefined") return "";
                                 return data.rejectedreason
                             }
                         }]
                     ],
                     where: {
                     },
                 });
});

