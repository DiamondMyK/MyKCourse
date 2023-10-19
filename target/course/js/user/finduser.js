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
		url: 'FindUserServlet',
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
				field: 'username',
				width: 130,
				title: '用户名'
			}, {
				field: 'realname',
				width: 130,
				title: '真实姓名'
			}, {
				field: 'userrole',
				width: 130,
				title: '身份'
			},  {
				field : 'status',
				width: 130,
				title : '状态',
				templet : '#statusTableBar',
				unresize : true,
				align: "center"
			}, {
				field: 'userremark',
				title: '备注',
				minWidth: 400
			}, {
				title: '操作',
				minWidth: 200,
				templet: '#currentTableBar',
				fixed: "right",
				align: "center"
			}]
		],
		where: {
			username:'',
			realname:'',
			userrole:''
		},
		// 是否开启分页
		page: false
	});
	// 监听开关（禁用用户）
	 form.on('switch(modifystatus)', function(obj){
	 	console.log(obj);
	 	var param = {
 			userid : obj.value
	 	}
		$.getJSON('ModifyUserServlet?status=banuser',param,function(data){
			if(data == true){
				//执行搜索重载
				table.reload('userTable', {
					where: {
						username:$('#username').val(),
						realname:$('#realname').val(),
						userrole:$('#role').val()
					}
				}, 'data');
			}
		});
	 });
	// 监听搜索操作
	form.on('submit(data-search-btn)', function(data) {
		var result = data.field;
		console.log(result);
		//执行搜索重载
		table.reload('userTable', {
			where: {
				username:result.username,
				realname:result.realname,
				userrole:result.role
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
			// 修改班级弹出框
			var othis = $(this),
				method = othis.data('method');
			active[method] ? active[method].call(this, othis, param) : '';
		} else if (obj.event === 'delete') {
			layer.confirm('真的删除该用户吗?', function(index) {
				$.getJSON('RemoveUserServlet', param, function(data){
					if(data == true) {
						layer.msg('删除成功！', {anim: 3, icon: 6, time: 1500, shade: 0.1});
						//执行重载
						table.reload('userTable', {
							where: {
								username:$('#username').val(),
								realname:$('#realname').val(),
								userrole:$('#role').val()
							}
						}, 'data');
					}
					else{
						layer.msg('删除失败，请查该用户是否已经删除！', {anim: 6, icon: 5, time: 1500, shade: 0.1});
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
				id: 'modifyuser_iframe', // 防止重复弹出
				title: '修改用户',
				type: 2,
				offset: ['20px', '25%'],
				area: ['500px', '600px'],
				content: ['views/user/modifyuser.html', 'no'], // no：弹窗不要滑动条
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
				    body.find('#userid').val(param.userid);
				    body.find('#username').val(param.username);
	    			body.find('#userpwd').val(param.userpwd);
	    			body.find('input[name=role][value=学生]').attr("checked",param.userrole=="学生" ? true : false);
					body.find('input[name=role][value=管理员]').attr("checked",param.userrole=="管理员" ? true : false);
					body.find('input[name=role][value=课程主讲教师]').attr("checked",param.userrole=="课程主讲教师" ? true : false);
					body.find('input[name=role][value=主管教师]').attr("checked",param.userrole=="主管教师" ? true : false);
	    			body.find('#realname').val(param.realname);
	    			body.find('#remark').val(param.userremark);
	    		    // 渲染页面表单，否则不显示数据
	    		    iframeWin.layui.form.render();
				}
			});
		},
	};
});
