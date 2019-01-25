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
<table class="layui-hide" id="table_user" lay-filter="table_user"></table>

<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" id="add"><a href="#">新增</a></button>
  </div>
</script>

<script type="text/html" id="barDemo">
 	<a class="layui-btn layui-btn-xs" lay-event="setRole">设置角色</a>
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<#--↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓add↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓-->
<#--带有 class="layui-fluid" 的容器中，那么宽度将不会固定，而是 100% 适应-->
<div id="addeditformdivid" hidden="" class="layui-fluid" style="margin: 15px;">
    <form class="layui-form" action="" id="addeditformid">
        <label hidden="true" id="editlabelid"></label>
        <input id="editid" name="editid" value="" hidden/>
        <div class="layui-form-item" hidden>
            <label class="layui-form-label">ID</label>
            <div class="layui-input-block">
                <input type="text" id="id" name="id" value="" lay-verify="id" autocomplete="off" readonly="readonly" placeholder="ID" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" id="userName" name="userName" lay-verify="userName" autocomplete="off" placeholder="请输入用户名" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="password" id="password" name="password" autocomplete="off" placeholder="请输入密码" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">真实姓名</label>
            <div class="layui-input-block">
                <input type="text" id="trueName" name="trueName" lay-verify="trueName" autocomplete="off" placeholder="请输入姓名" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备注信息</label>
            <div class="layui-input-block">
                <input type="text" id="bz" name="bz" lay-verify="bz" autocomplete="off" placeholder="备注信息" class="layui-input">
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
<#--↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑add↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑-->

<#--↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓为用户设置角色↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓-->

<#--带有 class="layui-fluid" 的容器中，那么宽度将不会固定，而是 100% 适应-->
<div id="editroleformdivid" hidden="" class="layui-fluid" style="margin: 15px;">
    <form class="layui-form" action="" id="editroleformid">
        <label hidden="true" id="editrolelabelid"></label>
        <input id="editroleid" name="id" value="" hidden />
        <div class="layui-form-item">
            <label class="layui-form-label">角色复选框</label>
            <div class="layui-input-block" id="checkboxlistid">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="editroleformsubmit">立即提交</button>
                <button id="editroleformreset" type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

    </form>
</div>
<#--↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑为用户设置角色↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑-->
</body>

<script src="${basePath!}/static/layui/layui.js" charset="utf-8"></script>

<script>
layui.use(['table','form'], function(){
  var table = layui.table;
  var form = layui.form;
  
  table.render({
    elem: '#table_user'
    ,url:'/admin/user/list'
    ,parseData: function(res){ //res 即为原始返回的数据
                return {
                    "code": res.code == 0 ? 1 : 0, //解析接口状态 0为有数据
                    "msg": res.msg, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": JSON.parse(res).data //解析数据列表
                };
            }
    ,toolbar: '#toolbarDemo'
    ,title: '用户管理'
    ,cols: [[
      {field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
      ,{field:'userName', title:'用户名', width:200, unresize:true}
      ,{field:'password', title:'密码', width:200, unresize:true}
      ,{field:'trueName', title:'真实姓名', width:200, unresize:true}
      ,{field:'bz', title:'备注', width:300,unresize:true}
      ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
    ]]
    ,page: true
  });
  
  //监听行工具事件
  table.on('tool(table_user)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
      layer.confirm('真的删除行么', function(index){
      //obj.del();
      //请求后台，获取该记录的详细记录，并填充进表单
      $.ajax({
          type: "POST",
          url:"/admin/user/deleteuser",
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
                 location.reload();
             }
           }
      });
      });
    }else if(obj.event === 'edit'){
		//请求后台，获取该记录的详细记录，并填充进表单
	      $.ajax({
	          type: "POST",
	          url:"/admin/user/selectUserById",
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
	                 document.getElementById("password").type="text";
	                 $("#id").val(result.tuser.id);
	                 $("#userName").val(result.tuser.userName);
	                 $("#password").val(result.tuser.password);
	                 $("#trueName").val(result.tuser.trueName);
	                 $("#bz").val(result.tuser.bz);
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
    }else if(obj.event === 'setRole'){
    	//获得当前用户已经拥有的角色集合和未拥有的角色集合，并组装表单的复选按钮
        $.ajax({
        	type: "POST",
        	url:"/admin/user/selectUserById",
        	data:{id:data.id},
       		async: false,
        	error: function(request) {
        		layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
        	},
       		success: function(result) {
            	if(result.state=='fail'){
                	layer.alert(result.mesg);
                	return false;
            	}
            	if(result.state=='success'){
             		$("#editrolelabelid").html(data.id);//临时存放id，当提交时再去除赋值给input
                	var roleList=[];
                	roleList=result.roleList;//该记录已经拥有的记录集合
                	var notinrolelist=[];
                 	notinrolelist=result.notinrolelist;//该记录尚未拥有的记录集合
                 	var strs="";
                	$.each(roleList, function (n, value) {//n从0开始自增+1；value为每次循环的单个对象
                    	strs+='<input type="checkbox" name="role" title="'+value.name+'" value="'+value.id+'"  checked="checked">';
                	});
                	$.each(notinrolelist, function (n, value) {
                    	strs+='<input type="checkbox" name="role" title="'+value.name+'"  value="'+value.id+'" >';
                	});
                	$("#checkboxlistid").empty();//每次填充前都要清空所有按钮，重新填充
                	$("#checkboxlistid").append(strs);

                	layui.form.render(); //更新全部
                	layerid=layer.open({
                 		skin: 'layui-layer-molv',
                  		area:'60%',
                		type: 1,
                 		title:'编辑用户角色',
                 		content: $('#editroleformdivid') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
               		});
         		}
            }
      	});
    }
  });
  
  
  //监听修改用户提交
  form.on('submit(addeditsubmitfilter)', function(data) {
	//为了防止form中的id值被重置后置空,将编辑的id存放在label中
    $("#editid").val($("#editlabelid").html() );
    $("#editlabelid").html("");
    $.ajax({
    	type: "POST",
        url:"/admin/user/addupdateuser",
        data:$('#addeditformid').serialize(),
        async: false,
        error: function(request) {
        	layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
        },
        success: function(result) {
        	if(data.state=='fail'){
            	layer.alert(result.mesg);
            }
            if(data.state=='success'){
            	layer.open({
                	skin: 'layui-layer-molv',
                    type:1,
                    area:"10%",
                    content:result.mesg,
                    shadeClose:true,
                    end: function(){
                    	layer.close(layerid);
                        $("#reset").click();//重置表单
                    }
                });
			}
         }
     });
  });
  
  
  //监听修改角色提交
  form.on('submit(editroleformsubmit)', function(data) {
  	//为了防止form中的id值被重置后置空,将编辑的id存放在label中
    $("#editroleid").val($("#editrolelabelid").html() );
    $("#editrolelabelid").html("");

    $.ajax({
    	type: "POST",
        url:"admin/user/saveRoleSet",
        data:$('#editroleformid').serialize(),// 你的formid
        async: false,
        error: function(request) {
        	layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
        },
        success: function(data) {
        	if(data.state=='fail'){
            	layer.alert(data.mesg);
            }
            if(data.state=='success'){
            	layer.open({
                	skin: 'layui-layer-molv',
                    type:1,
                    area:"10%",
                    content:data.mesg,
                    shadeClose:true,
                    end: function(){
                    	layer.close(layerid);
                    	$("#reset").click();//重置表单
            		}
        		});
            }
         }
     });
	 return false;
  });
  
  //监听修改角色提交
  form.on('submit(editroleformsubmit)', function(data) {
  	//为了防止form中的id值被重置后置空,将编辑的id存放在label中
  	$("#editroleid").val($("#editrolelabelid").html() );
  	$("#editrolelabelid").html("");

	$.ajax({
    	type: "POST",
    	url:"/admin/user/saveRoleSet",
    	data:$('#editroleformid').serialize(),// 你的formid
    	async: false,
    	error: function(request) {
    		layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
     	},
    	success: function(result) {
  			if(result.state=='fail'){
            	layer.alert(result.mesg);
        	}
        	if(result.state=='success'){
        		layer.open({
                	skin: 'layui-layer-molv',
                	type:1,
                	area:"10%",
                	content:result.mesg,
                	shadeClose:true,
                	end: function(){
                		layer.close(layerid);
                 		$("#reset").click();//重置表单
            		}
            	});

           	}
    	}
	});
	return false;
  });
  
  
	//添加按钮点击事件
	$("#add").click(function () {
		$("#reset").click();//重置表单(新建时在进入表单前要重置一下表单的内容，不然表单打开后会显示上一次的表单的内容。这里调用表单中重置按钮的点击方法来重置)
		document.getElementById("password").type="password";
		layerid=layer.open({//开启表单弹层
			skin: 'layui-layer-molv',
      		area:'60%',
			type: 1,
			title:'新建用户',
			content: $('#addeditformdivid') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		});
	});
	
});
</script>
  