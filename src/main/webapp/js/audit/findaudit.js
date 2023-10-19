// 定义全局变量
var $;
layui.use(['form', 'table', 'layer', 'laydate'], function() {
	$ = layui.jquery;
	var form = layui.form,
		table = layui.table,
		layer = layui.layer,
		laydate = layui.laydate;
	// 初始化表格
	table.render({
		// 设置ID
		id : 'userTable',
		// 指定原始 table 容器
		elem: '#userTableId',
		// 异步数据接口
		url: 'FindCourseServlet',
		// 设置导出按钮
		toolbar: '#tablebar',
		//设置表头。值是一个二维数组
		cols: [
			[{
				type:'numbers',
				width: 50,
				title: '序号',
				fixed: "left"
			}, {
				field: 'cname',
				width: 230,
				title: '课程名'
			}, {
				field: 'nameone',
				width: 230,
				title: '第一审批人'
			}, {
				field: 'nametwo',
				width: 230,
				title: '第二审批人'
			}, {
				field: 'cremark',
				width: 630,
				title: '说明'
			}, {
				title: '操作',
				minWidth: 200,
				templet: '#currentTableBar',
				fixed: "right",
				align: "center"
			}]
		],
		where: {
			cname:'',
		},
		// 是否开启分页
		page: false
	});
	// 监听搜索操作
	form.on('submit(data-search-btn)', function(data) {
		var result = data.field;
		console.log(result);
		//执行搜索重载
		table.reload('userTable', {
			where: {
				cname:result.cname
			}
		}, 'data');
		return false;
	});
	// 监听表格编辑、删除按钮
	table.on('tool(userTableFilter)', function(obj) {
		var param = obj.data;
		console.log(param);
		if (obj.event === 'search') {
			var othis = $(this),
				method = othis.data('method');
			active[method] ? active[method].call(this, othis, param) : '';
		} else if (obj.event === 'edit') {
			var othis = $(this),
				method = othis.data('method');
			active[method] ? active[method].call(this, othis, param) : '';
		}
	});
	// 定义触发事件弹出厨框
	var active = {
		apply: function(othis, param) {
			layer.open({
						   id: 'applyaudit_iframe', // 防止重复弹出
						   title: '申请课程',
						   type: 2,
						   offset: ['20px', '25%'],
						   area: ['500px', '600px'],
						   content: ['views/audit/applyaudit.html', 'no'], // no：弹窗不要滑动条
						   skin: 'layui-layer-molv',
						   shade: 0.3, //不显示遮罩
						   maxmin: true, //开启最大化按钮
						   anim: 3, // 过渡效果（3或者4）
						   resize: false, // 禁止窗体拉伸
						   //							time : 1000,//定时关闭
						   success: function(layero, index) { //弹出层弹出后的回调函数
							   console.log(layero, index);
							   var body = layer.getChildFrame('body', index);
							   var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
							   body.find('#cid').val(param.cid);
							   body.find('#cname').val(param.cname);
							   // 渲染页面表单，否则不显示数据
							   iframeWin.layui.form.render();
						   }
					   });
		},
	};
});

