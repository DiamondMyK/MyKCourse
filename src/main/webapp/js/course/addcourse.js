layui.use(['form', 'layedit', 'laydate'], function() {
	var $ = layui.$,
		form = layui.form,
		layer = layui.layer,
		layedit = layui.layedit,
		laydate = layui.laydate;
	//创建一个编辑器
	var editIndex = layedit.build('cremark', {
		height: 150 //设置编辑器高度
	});
	// 表示文档结构已经加载完成后触发的事件（用于动态数据的准备）
	$(document).ready(function() {
		console.log("在这里准备数据");
    });
    // 点击重置按钮除触发事件(用于清除layui富文本)
    $("#reset").on("click", function() {
    	layedit.setContent(editIndex, null);// 富文本中内容置空
    });
	//自定义验证规则
	form.verify({
		username: function(value) {
			if (value == '') {
				return '请填写课程名';
			}
		},
		content: function(value) {
			layedit.sync(editIndex);// 将富文本中内容同步到文本
		}
	});
	//监听提交
	form.on('submit(form)', function(data) {
		var param = data.field;
		console.log(param);
		$.ajax({
			type : "post",
			url : "AddCourseServlet",
			data : param,
			dataType : "json",//预期服务器返回的数据类型
			success : function(data){
				console.log(data);
				if(data == true){
					layer.msg('提交成功！', {anim: 3, icon: 6, time: 2000, shade: 0.1});
					setTimeout(function() {
						window.location = '../../views/course/addcourse.html';
					}, 1000);
				} else {
					layer.msg('提交失败！', {anim: 6, icon: 5, time: 1500, shade: 0.1});
				}
			},
			error:function(xhr){
				window.location = '../../views/error/500.html';
			}
		});

		return false;
	});
});
