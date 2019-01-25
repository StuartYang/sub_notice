<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${basePath!}/static/layui/css/layui.css" />
		<title></title>
	</head>

	<body>
		<div>
			<table class="layui-hide" id="table_sysNotice" lay-filter="table_sysNotice"></table>
			<script type="text/html" id="barDemo">
				<a class="layui-btn layui-btn-xs" lay-event="detail">查看详情</a>
			</script>
		</div>
	</body>
	
	<script type="text/javascript" src="${basePath!}/static/layui/layui.js"></script>
	<script src="${basePath!}/static/js/jquery.min.js" type="text/javascript"></script>
	
	<script>
		layui.use('table', function() {
			var table = layui.table;
			table.render({
				elem: '#table_sysNotice',
				url: '/notice/list',
				parseData: function(res) { //res 即为原始返回的数据
					return {
						"code": res.code == 0 ? 1 : 0, //解析接口状态 0为有数据
						"msg": res.mesg, //解析提示文本
						"count": res.count, //解析数据长度
						"data": JSON.parse(res).data //解析数据列表
					};
				},
				title: '用户数据表',
				cols: [
					[{
						field: 'tnTitle',
						title: '标题',
						width: 200,
						unresize: true
					}, {
						field: 'tnContent',
						title: '内容',
						width: 300,
						unresize: true
					}, {
						field: 'file',
						title: '附件',
						unresize: true,
						width: 300,
						templet:function(obj){
							if(obj.file==''){
								return '无文件';
							}else{
								return '有附件';
							}
				      	}
					}, {
						field: 'publicTime',
						title: '发布时间',
						width: 200,
						unresize: true,
						templet:getDate
					},{
						fixed: 'right',
						title: '操作',
						toolbar: '#barDemo'
					}]
				],
				page: true
			});
			
			table.on('tool(table_sysNotice)', function(obj){
				var data = obj.data;
				if(obj.event === 'detail'){
					window.location.href = "/notice/select/"+data.id;
				}
			});
		});
	</script>
	<script>
	//时间戳格式化 
	 function getDate(obj){
		function formatDate(date){
		　　		var year = date.getFullYear();
		　　	 	var month = date.getMonth() + 1;
		　　		var day = date.getDate();
				var hour = date.getHours();
		　　		var minute = date.getMinutes();
		　　		var second = date.getSeconds();
		　　		return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
		}
		var date = new Date(obj.publicTime);
		return formatDate(date);
	}
	</script>

</html>