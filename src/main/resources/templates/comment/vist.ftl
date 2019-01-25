<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="application/html" charset="UTF-8">
    <link rel="stylesheet" href="${basePath!}/static/comment/css/style.css"/>
    <link rel="stylesheet" href="${basePath!}/static/comment/css/comment.css"/>
    <script type="text/javascript" src="${basePath!}/static/comment/js/jquery-1.12.0.min.js"></script>
    <script type="text/javascript" src="${basePath!}/static/comment/js/jquery.flexText.js"></script>
    <script type="text/javascript" src="${basePath!}/static/comment/js/comment.js"></script>
    <link href="${basePath!}/static/comment/js/bootstrap-3.3.7-dist/css/bootstrap.min.css"
          rel="stylesheet"/>
    <script src="${basePath!}/static/comment/js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

    <div class="page-header">

        <center><h1>${pass.tnTitle}</h1></center>
    </div>

    <div class="row">
        <div class="col-md-12 col-md-offset-1">
            &nbsp; &nbsp; <font size="+1"><b>发布于：</b>
                <small><em>${pass.genTime?string('yyyy-MM-dd HH:mm:ss')}</em></small>
            </font>
        </div>
        <div class="col-md-12 col-md-offset-10">
            <font size="3" color="red"><em>${pass.views}</em></font> <font
                    size="4">浏览量</font>

        </div>
    </div>


    <div class="panel panel-default">


        <div class="panel-heading " id="gettnId" tnId="${pass.id}">
            <font size="2"><b>第<em>${pass.id}</em>条公告</b></font>
        </div>


        <div class="panel-body">
            <center>
                <font size="3">${pass.tnContent}</font>
            </center>
        </div>

    </div>


    <div class="commentAll">
        <!--评论区域 begin-->
        <div class="reviewArea clearfix">
				<textarea class="content comment-input" id="text"
                          placeholder="Please enter a comment.a .." onkeyup="keyUP(this)">
        </textarea>

            <a href="javascript:;" class="plBtn">评论</a>
        </div>

        <!--回复区域 begin-->

        <div class="row">
            <div class="col-md-3" id="page_info_area"></div>
        </div>
        <!--评论区域 end-->
        <div class="comment-show" id="comment-show">
            <div class="hf-con pull-left">
					<textarea class="content comment-input hf-input" placeholder=""
                              onkeyup="keyUP(this)">
         </textarea>
                <a href="javascript:;" class="hf-pl">评论</a>
            </div>
        </div>

        <div class="row">
            <div class="col-md-10 col-md-offset-6 " id="page_nav_area"></div>
        </div>
    </div>
</div>


<div class="modal fade" id="commentmodel" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">查看回复</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-2"><font>小麻：</font></div>
                    <div class="col-md-4 ">@小赵：说的真好呢</div>
                    <div class="col-md-4 col-md-offset-2">
                        <font color="gray"><em>2019-01-17 9:20:50</em></font>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>