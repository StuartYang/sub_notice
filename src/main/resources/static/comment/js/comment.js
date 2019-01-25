//<!--textarea高度自适应--> 
$(function () {
    $('.content').flexText();

});
$(function () {
    // 去首页
    to_page(1);
});

// 页面加载完成后，直接显示出全部的一级评论，发送ajax请求，要到分页数据
function to_page(pn) {
    var tnId = $("#gettnId").attr("tnId");
    $.ajax({
        url: "/querycomment/" + tnId ,
        type: "GET",
        data:"pn="+pn,
        success: function (result) {
            // 1.解析并显示一级评论
            build_comment_table(result);
            build_page_info(result);
            build_page_nav(result)
        }

    });

}

// 遍历一级评论
function build_comment_table(result) {
    // 清空table表格
    $(".comment-show").empty();
    var mms = result.data.list;
    // 遍历元素设定的格式
    $.each(mms, function (index, item) {

        var str = ` <div class="comment-show-con clearfix">
	<div class="comment-show-con-img pull-left"><img src="/static/images/123456.png" /></div>
        <div class="comment-show-con-list pull-left clearfix">
            <div class="pl-text clearfix">
                <a href="#" id="toName" class="comment-size-name">${item.fromName} : </a>
                <span class="my-pl-con">&nbsp;${item.content}</span>
            </div>
            <div class="date-dz">
                <span class="date-dz-left pull-left comment-time">${item.cTime}</span>
                <div class="date-dz-right pull-right comment-pl-block" >
                    <a href="" id="removeBlock"  class="removeBlock" deteleid="${item.id}" >删除</a>
                
                    
                </div>
            </div>
           
        </div>
    </div>`;


        $("#comment-show").append(str);


    });

}


// 解析显示分页条，点击分页要能去下一页....
function build_page_nav(result) {
    // page_nav_area
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");

    // 构建<li >元素
    var firstPageLi = $("<li></li>").append(
        $("<a></a>").append("首页").attr("href", "#"));
    var prePageLi = $("<li></li>").append(
        $("<a></a>").append("&laquo;"));
    // 判断是否有前一页信息
    if (result.data.hasPreviousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {// 为元素添加点击翻页的事件
        // 给首页添加一个点击事件
        firstPageLi.click(function () {
            to_page(1);
        });
        // 给前一页加一个点击事件
        prePageLi.click(function () {
            to_page(result.data.pageNum - 1);
        });
    }
    var nextPageLi = $("<li></li>").append(
        $("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append(
        $("<a></a>").append("末页").attr("href", "#"));
    // 判断没有下一页了
    if (result.data.hasNextPage == false) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    } else {
        nextPageLi.click(function () {
            to_page(result.data.pageNum + 1);
        });
        lastPageLi.click(function () {
            to_page(result.data.pages);
        });
    }

    // 添加首页和前一页 的提示
    ul.append(firstPageLi).append(prePageLi);

    // 遍历页码1.2.3.4.5 赋值numli。。.items，当前页码号
    $.each(result.data.navigatepageNums, function (index,
                                                   item) {
        var numLi = $("<li></li>").append($("<a></a>").append(item));
        // 使按钮看着是在活动状态
        if (result.data.pageNum == item) {
            numLi.addClass("active");
        }
        numLi.click(function () {
            to_page(item);
        });
        // 把numli添加给ul
        ul.append(numLi);
    });
    // 添加下一页和末页 的提示
    ul.append(nextPageLi).append(lastPageLi);

    // 把ul加入到nav
    var navEle = $("<nav></nav>").append(ul);
    navEle.appendTo("#page_nav_area");
}


function build_page_info(result) {
    // 找到这个div，然后往里面放东西，后面的数据是json内属性的调取方式
    $("#page_info_area").empty();
    $("#page_info_area").append(
        "<font color='gray' size='2'><em>" + result.data.total + "条评论</em></font>");
    // 定义的总记录数
    totalRecord = result.data.total;
    // 定义当前的页码数
    currentPage = result.data.pageNum;
}


// <!--textarea限制字数-->
function keyUP(t) {
    var len = $(t).val().length;
    if (len > 139) {
        $(t).val($(t).val().substring(0, 140));
    }
}


// 进行评论
$(document).on('click', '.plBtn', function () {
    // 获取输入内容
    var oSize = $(this).siblings('.flex-text-wrap').find('.comment-input').val();
    var tnId = $("#gettnId").attr("tnId");
    // 动态创建评论模块
    $.ajax({
        url: "/addcomment",
        type: "POST",
        data: {
            "content": oSize,
            "topicId": tnId,
        },
        success: function (result) {

            to_page(totalRecord);
            document.getElementById("text").value = "";

        }

    });

});

// <!--删除评论块-->
$(document).on('click', '.removeBlock', function () {

    var id = $(this).attr("deteleid");

    $.ajax({
        url: "/deleteComments/" + id,
        type: "GET",
        success: function (result) {

            to_page(currentPage);
        }
    });

})
//
//
//// <!--点击回复动态创建回复块-->
//$(document).on("click", ".pl-hf", function () {
//    var cid = $(this).attr("commentid");
//    var cname = $(this).attr("cname");
//
//
//    // 获取回复人的名字
//    var fhName = $(this).parents('.date-dz-right').parents('.date-dz').siblings('.pl-text').find('.comment-size-name').html();
//    // 回复@
//    var fhN = '回复@' + fhName;
//
//    var fhHtml = '<div class="hf-con pull-left"> <textarea class="content comment-input hf-input" placeholder="" onkeyup="keyUP(this)"></textarea> <a href="javascript:;" class="hf-pl" id="comm"   >评论</a></div>';
//    // 显示回复
//    if ($(this).is('.hf-con-block')) {
//        $(this).parents('.date-dz-right').parents('.date-dz').append(fhHtml);
//        $(this).removeClass('hf-con-block');
//        $('.content').flexText();
//        $(this).parents('.date-dz-right').siblings('.hf-con').find('.pre').css('padding', '6px 15px');
//        // input框自动聚焦
//        $(this).parents('.date-dz-right').siblings('.hf-con').find('.hf-input').val('').focus().val(fhN);
//    } else {
//        $(this).addClass('hf-con-block');
//        $(this).parents('.date-dz-right').siblings('.hf-con').remove();
//    }
//
//    $("#comm").attr("cname", cname);
//    $("#comm").attr("coid", cid);
//});
//
//// <!--评论回复块创建-->
//
//$(document).on('click', '.hf-pl', function () {
//
//
//    var oThis = $(this);
//
//    // 获取输入内容
//    var oHfVal = $(this).siblings('.flex-text-wrap').find('.hf-input').val();
//    console.log(oHfVal)
//
//    var oHfName = $(this).parents('.hf-con').parents('.date-dz').siblings('.pl-text').find('.comment-size-name').html();
//    var oAllVal = '回复@' + oHfName;
//
//
//    var cid = $(this).attr("coid");
//    var toName = $(this).attr("toName");
//    alert(toName);
//    $.ajax({
//        url: "/addrely2",
//        type: "POST",
//        data: {
//            "commentid":cid,
//            "content": oHfVal,
//            "toName": toName,
//        },
//        success: function () {
//            to_page(currentPage);
//        }
//
//
//    });
//
//});
//
//
//$(document).on('click', '.date-dz-z', function () {
//
//
//    var cids = $(this).attr("commentid");
//    //alert(cids);
//    $.ajax({
//
//        url: "/queryAllReplys/" + cids,
//        type: "GET",
//       
//        success: function (result) {
//
//            build_comments(result);
//
//
//        }
//
//
//    });
//});

function build_comments(result) {
    $(".modal-body").html("");
    var zths = result.data.list;
    // 遍历元素设定的格式
    $.each(zths, function (index, file) {


        var comments = `<div class="row">
				<div class="col-md-2"><font>${file.fromUid}:</font></div>
				<div class="col-md-4 ">${file.content}</div>
                <div class="col-md-4 col-md-offset-2">
                <font color="gray"><em>${file.rTime}</em></font>
                 </div>
               </div>`;

        $(".modal-body").append(comments);
    });


    $("#commentmodel").modal({
        backdrop: "static"
    });
}
 
 
 
 
 
 
 


		
		
		