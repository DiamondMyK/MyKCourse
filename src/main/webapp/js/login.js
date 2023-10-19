// 保证登陆页面始终再最外一层
if (window != top) {
	top.location.href = location.href;
}

var slideVerify;
layui.use(['form'], function() {
	var form = layui.form,
		layer = layui.layer;
	//自定义验证规则
	form.verify({
		account: function(value) {
			if (value == '') {
				return '用户名不能为空';
			}
		},
		userpwd: function(value) {
			if (value == '') {
				return '密码不能为空';
			} else if (value.length < 6 || value.length > 11) {
				return '请正确输入6~11位密码';
			}
		}
	});
	$(document).ready(function() {

	});
	// 进行登录操作
	form.on('submit(login)', function(data) {
			var param = data.field;
			$.ajax({
				type : "post",
				url : "LoginServlet?status=login",
				data : param,
				dataType : "json",//预期服务器返回的数据类型
				success : function(data){
					console.log(data);
					if(data == '1'){
						layer.msg('登录成功！', {anim: 3, icon: 6, time: 2000, shade: 0.1});
						setTimeout(function() {
							window.location = 'index.html';
						}, 1000);
					} else if(data == '0') {
		                  layer.msg('密码错误，登录失败',{anim: 6, icon: 5, time: 1500, shade: 0.1});
		                  $('#userpwd').val("");
					} else if(data == '-1') {
		                  layer.msg('该用户已被禁用！',{anim: 6, icon: 5, time: 1500, shade: 0.1});
		                  $('#username').val("");
		                  $('#userpwd').val("");
					}
				},
				error:function(xhr){
					window.location = '../views/error/500.html';
				}
			});
		return false;
	});
});
