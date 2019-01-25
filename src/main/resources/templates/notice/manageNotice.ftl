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
 
<table class="layui-hide" id="table_notice" lay-filter="table_notice"></table>

<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" id="add"><a href="#">新增</a></button>
  </div>
</script>
 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>          
 
<#--↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓add↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓-->
<#--带有 class="layui-fluid" 的容器中，那么宽度将不会固定，而是 100% 适应-->
<div id="addNotice" hidden="" class="layui-fluid" style="margin: 15px;">
    <form class="layui-form" action="" id="addeditformid">
        <label hidden="true" id="editlabelid"></label>
        <input id="editid" name="id" value="" hidden/>
        <div class="layui-form-item">
            <label class="layui-form-label">通知标题</label>
            <div class="layui-input-block">
                <input type="text" id="tnTitle" name="tnTitle" lay-verify="name" autocomplete="off" placeholder="请输入标题" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">内容</label>
            <div class="layui-input-block">
                <textarea type="text" id="tnContent" name="tnContent" class="layui-textarea" ></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">附件</label>
            <input type="hidden" id="file" name="file" value=""/>
            <button type="button" class="layui-btn" id="upload_file">
  				<i class="layui-icon">&#xe67c;</i>上传文件
			</button>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addsubmitfilter">立即提交<tton>
                <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置<tton>
            </div>
        </div>
    </form>
</div>
<#--带有 class="layui-fluid" 的容器中，那么宽度将不会固定，而是 100% 适应-->
<div id="infoNotice" hidden="" class="layui-fluid" style="margin: 15px;">
        <label hidden="true" id="editlabelid"></label>
        <input id="editid" name="id" value="" hidden/>
        <div class="layui-form-item">
            <label class="layui-form-label">通知标题</label>
            <div class="layui-input-block">
                <input type="text" id="info_title" name="title" lay-verify="name" autocomplete="off" readonly="readonly" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">内容</label>
            <div class="layui-input-block">
                <textarea type="text" id="info_content" name="content" class="layui-textarea" readonly></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">发布时间</label>
            <div class="layui-input-block">
                <input type="text" id="info_date" name="content" class="layui-input" readonly="readonly"></input>
            </div>
        </div>
	    <div class="layui-form-item">
	        <label class="layui-form-label">附件</label>
	        <div class="layui-input-block">
	        	<input class="layui-input" type="text" name="fileName" readonly="readonly" value="" id="fileName"></input>
	        </div>
	        <div class="layui-input-block">
	        	<a id="down_url" href="" target="_blank">
			        <button class="layui-btn" type="button" >
						<i class="layui-icon">&#xe67c;</i>下载文件
					</button>
				</a>
			</div>
	    </div>
</div>

<script src="${basePath!}/static/layui/layui.js" charset="utf-8"></script>

<script>
layui.use(['table','form'], function(){
  var table = layui.table;
  var form = layui.form;
  
  table.render({
    elem: '#table_notice'
    ,url:'/notice/list'
    ,parseData: function(res){ //res 即为原始返回的数据
                return {
                    "code": res.code == 0 ? 1 : 0, //解析接口状态 0为有数据
                    "msg": res.mesg, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": JSON.parse(res).data //解析数据列表
                };
            }
    ,toolbar: '#toolbarDemo'
    ,title: '用户数据表'
    ,cols: [[
      {field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
      ,{field:'tnTitle', title:'标题', width:200,unresize:true}
      ,{field:'tnContent', title:'内容', width:300,unresize:true}
      ,{field: 'file', title:'附件',unresize:true, width:300,
      	templet:function(obj){
      		var start = obj.file.lastIndexOf('/');
			var end = obj.file.length;
            obj.file.substr(start+1,end);
      		if(obj.file==''){
      			return '无文件';
      		}else{
      			return '有附件';
      		}
      	}
      }
      ,{field:'publicTime', title:'发布时间',width:200,unresize:true,templet:getDate}
      ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
    ]]
    ,page: true
  });

  //头工具栏事件
  table.on('toolbar(table_notice)', function(obj){
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
  table.on('tool(table_notice)', function(obj){
    var data = obj.data;
    //console.log(obj)
    if(obj.event === 'del'){
      layer.confirm('真的删除行么', function(index){
      obj.del();
      //请求后台，获取该记录的详细记录，并填充进表单
      $.ajax({
          type: "POST",
          url:"/notice/remove",
          data:{id:data.id},
          async: false,
          error: function(request) {
             layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
          },
          success: function(result) {
             if(result.mesg=='操作失败'){
                 layer.alert("error");
                 return false;
             }
             if(result.mesg=='操作成功'){
                 layer.alert("success");
             }
           }
      });
      });
    } else if(obj.event === 'detail'){
      //请求后台，获取该记录的详细记录，并填充进表单
      $.ajax({
          type: "POST",
          url:"/notice/select",
          data:{id:data.id},
          async: true,
          error: function(request) {
             layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
          },
          success: function(result) {
             if(result.mesg=='操作失败'){
                 layer.alert("error");
                 return false;
             }
             if(result.mesg=='操作成功'){
             	 var date = getDate(result.data);
                 var start = result.data.file.lastIndexOf('/');
                 var end = result.data.file.length;
                 //向表单填充数据
                 $("#info_title").val(result.data.id);
                 $("#info_content").val(result.data.tnTitle);
                 $("#download_file").val(result.data.tnContent);
                 $("#info_date").val(date);
                 $("#fileName").val(result.data.file.substr(start+1,end));
                 $("#down_url").attr('href',"/file/op/download?fileName="+result.data.file.substr(start+1,end));
                 //开启编辑表单所在的弹层。注意编辑和新建的表单是一套模板
                 layerid=layer.open({
                    skin: 'layui-layer-molv',
                    area:'60%',
                    type: 1,
                    title:'编辑角色',
                    content: $('#infoNotice') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                 });
             }
           }
        });
    }
  });
  
  //提交增加notic表单监听
  form.on('submit(addsubmitfilter)', function(data){
    	//为了防止form中的id值被重置后置空,将编辑的id存放在label中
        $("#editid").val($("#editlabelid").html() );
        $("#editlabelid").html("");
        $.ajax({
            type: "POST",
            url:"/notice/save",
            data:$('#addeditformid').serialize(),
            async: true,
            error: function(request) {
                layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
            },
            success: function(result) {
                if(result.mesg=='操作失败'){
                    layer.alert(data.mesg);
                }
                if(result.mesg=='操作成功'){
                    layer.open({
                        skin: 'layui-layer-molv',
                        type:1,
                        area:"10%",
                        content:result.mesg,
                        shadeClose:true,
                        end: function(){
                            layer.close(layerid);
                            location.reload();
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
		layerid=layer.open({//开启表单弹层
			skin: 'layui-layer-molv',
        	area:'60%',
			type: 1,
			title:'新建公告',
			content: $('#addNotice') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		});
	});
});
</script>
<script>
layui.use('upload', function(){
	var upload = layui.upload;
	//上传文件
	upload.render({
    	elem: '#upload_file' //绑定元素
    	,url: '/file/op/upload' //上传接口
    	,accept: 'file'
    	,done: function(res){
    	    $("#file").val(res.fileName);
      		alert("上传成功");
    	}
    	,error: function(){
      		//请求异常回调
    	}
    });
});

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
</body>
</html>