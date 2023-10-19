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
		} else if (obj.event === 'delete') {
			layer.confirm('真的删除该课程吗?', function(index) {
				$.getJSON('RemoveCourseServlet', param, function(data){
					if(data == true) {
						layer.msg('删除成功！', {anim: 3, icon: 6, time: 1500, shade: 0.1});
						//执行重载
						table.reload('userTable', {
							where: {
								cname:$('#cname').val()
							}
						}, 'data');
					}
					else{
						layer.msg('删除失败，请查该课程是否已经删除！', {anim: 6, icon: 5, time: 1500, shade: 0.1});
					}
				});
				layer.close(index);
			});
		}
	});
	// 定义触发事件弹出厨框
	var active = {
		edituser: function(othis, param) {
			layer.open({
				id: 'modifycourse_iframe', // 防止重复弹出
				title: '修改课程',
				type: 2,
				offset: ['20px', '25%'],
				area: ['500px', '600px'],
				content: ['views/course/modifycourse.html', 'no'], // no：弹窗不要滑动条
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
				    body.find('#cid').val(param.cid);
				    body.find('#cname').val(param.cname);
	    			body.find('#cremark').val(param.cremark);
	    		    // 渲染页面表单，否则不显示数据
	    		    iframeWin.layui.form.render();
				}
			});
		},
		configcourse: function(othis, param) {
			layer.open({
						   id: 'configcourse_iframe', // 防止重复弹出
						   title: '配置审批',
						   type: 2,
						   offset: ['20px', '25%'],
						   area: ['500px', '600px'],
						   content: ['views/course/configcourse.html', 'no'], // no：弹窗不要滑动条
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
							   //console.log(body.html()) //得到iframe页的body内容
							   body.find('#cid').val(param.cid);
							   body.find('#cname').val(param.cname);

							   findAuditOne(body, iframeWin, param);
							   findAuditTwo(body, iframeWin, param);
							   // 渲染页面表单，否则不显示数据
							   iframeWin.layui.form.render();
						   }
					   });
		},
	};
});

function findAuditOne(body, iframeWin, srcparam){
	body.find("#auditone").html("");//empty()
	var pleaseOption = $("<option>").val("0").text("所有课程主讲教师").prop('selected',true);
	body.find("#auditone").append(pleaseOption);
	var param = {'ram' : Math.random()};
	$.getJSON("FindUserServlet?userrole=课程主讲教师",param,function(data){
		$.each(data.data, function(index, item) {
			var op = $("<option>").val(item.userid).text(item.realname);
			body.find("#auditone").append(op);
		});
		// 渲染页面表单，否则不显示数据
		iframeWin.layui.form.render('select');
	});
}

function findAuditTwo(body, iframeWin, srcparam){
	body.find("#audittwo").html("");//empty()
	var pleaseOption = $("<option>").val("-1").text("不需要").prop('selected',true);
	body.find("#audittwo").append(pleaseOption);
	var pleaseOption2 = $("<option>").val("0").text("所有主管教师");
	body.find("#audittwo").append(pleaseOption2);

	var param = {'ram' : Math.random()};
	$.getJSON("FindUserServlet?userrole=主管教师",param,function(data){
		$.each(data.data, function(index, item) {
			var op = $("<option>").val(item.userid).text(item.realname);
			body.find("#audittwo").append(op);
		});
		// 渲染页面表单，否则不显示数据
		iframeWin.layui.form.render('select');
	});
}
