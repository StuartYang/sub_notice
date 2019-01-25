<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link href="${basePath!}/static/layui/css/layui.css" type="text/css" media="screen" rel="stylesheet"/>
  <script src="${basePath!}/static/js/jquery.min.js" type="text/javascript"></script>
</head>
<body>
 
<table class="layui-hide" id="table_role" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" id="add"><a href="#">新增</a></button>
  </div>
</script>

<script type="text/html" id="barDemo">
 	<a class="layui-btn layui-btn-xs" lay-event="detail">设置菜单权限</a>
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<#--↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓add↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓-->
<#--带有 class="layui-fluid" 的容器中，那么宽度将不会固定，而是 100% 适应-->
<div id="addeditformdivid" hidden="" class="layui-fluid" style="margin: 15px;">
    <form class="layui-form" action="" id="addeditformid">
        <label hidden="true" id="editlabelid"></label>
        <input id="editid" name="id" value="" hidden/>
        <div class="layui-form-item">
            <label class="layui-form-label">角色名称</label>
            <div class="layui-input-block">
                <input type="text" id="name" name="name" lay-verify="name" autocomplete="off" placeholder="请输入角色名称" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <input type="text" id="bz" name="bz" autocomplete="off" placeholder="请输入备注信息" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addeditsubmitfilter">立即提交</button>
                <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
</body>

<script src="${basePath!}/static/layui/layui.js" charset="utf-8"></script>

<script>
layui.use('table', function(){
  var table = layui.table;
  table.render({
    elem: '#table_role'
    ,url:'/static/json/data.json'
    ,toolbar: '#toolbarDemo'
    ,title: '角色管理'
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
      ,{field:'rolename', title:'角色名称', width:200,unresize:true}
      ,{field:'bz', title:'备注', width:300,unresize:true}
      ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
    ]]
    ,page: true
  });
  
  //头工具栏事件
  table.on('toolbar(table_role)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    switch(obj.event){
      case 'getCheckData':
        var data = checkStatus.data;
        layer.alert(JSON.stringify(data));
      break;
      case 'getCheckLength':
        var data = checkStatus.data;
        layer.msg('选中了：'+ data.length + ' 个');
      break;
      case 'isAll':
        layer.msg(checkStatus.isAll ? '全选': '未全选');
      break;
    };
  });
  
  //监听行工具事件
  table.on('tool(table_role)', function(obj){
    var data = obj.data;
    //console.log(obj)
    if(obj.event === 'del'){
      layer.confirm('真的删除行么', function(index){
        //obj.del();
      //请求后台，获取该记录的详细记录，并填充进表单
      $.ajax({
          type: "POST",
          url:"role/delrowById",
          data:{id:data.id},
          async: false,
          error: function(request) {
             layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
          },
          success: function(result) {
             if(result.state=='fail'){
                 layer.alert("error");
                 return false;
             }
             if(result.state=='success'){
                 layer.alert("success");
             }
           }
      });
      });
    }else if(obj.event === 'edit'){
		//请求后台，获取该记录的详细记录，并填充进表单
	      $.ajax({
	          type: "POST",
	          url:"user/getrowById",
	          data:{id:data.id},
	          async: false,
	          error: function(request) {
	             layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
	          },
	          success: function(result) {
	             if(result.state=='fail'){
	                 layer.alert("error");
	                 return false;
	             }
	             if(result.state=='success'){
	                 //向表单填充数据
	                 $("#name").val(request.name);
	                 $("#bz").val(request.bz);
	                 //开启编辑表单所在的弹层。注意编辑和新建的表单是一套模板
	                 layerid=layer.open({
	                    skin: 'layui-layer-molv',
	                    area:'60%',
	                    type: 1,
	                    title:'编辑角色',
	                    content: $('#addeditformdivid') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
	                 });
	             }
	           }
	        });
    }
  });
  
	//添加按钮点击事件
	$("#add").click(function () {
		$("#reset").click();//重置表单(新建时在进入表单前要重置一下表单的内容，不然表单打开后会显示上一次的表单的内容。这里调用表单中重置按钮的点击方法来重置)
		layerid=layer.open({//开启表单弹层
			skin: 'layui-layer-molv',
        	area:'60%',
			type: 1,
			title:'新建角色',
			content: $('#addeditformdivid') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		});
	});
	
	
});
</script>