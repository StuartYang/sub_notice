package com.notice.controller;


import com.notice.entity.Tcomment;
import com.notice.entity.Tuser;
import com.notice.service.CommentService;
import com.notice.service.ReplyService;
import com.notice.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {


    @Autowired
    private CommentService commentService;

    @Autowired
    private ReplyService replyservice;
    //查看一级评论
    @GetMapping(value = "/querycomment/{topicId}")
    @ResponseBody
    public Result queryComment(@RequestParam(value = "pn", defaultValue = "1") Integer pn,@PathVariable("topicId") Integer topicId) {
        Result result = this.commentService.getallcomment(pn, topicId);
        return result;

    }

    //查询二级评论()
    @GetMapping(value = "/queryAllReplys/{commentId}")
    @ResponseBody
    public Result queryAllReplys(@PathVariable("commentId") Integer commentId) {
        Result result = this.replyservice.queryAllReplys(commentId, 1);
        return result;
    }


    //添加一级评论
    @PostMapping(value = "/addcomment")
    @ResponseBody
    public Result addComment(Tcomment tcomment, HttpSession httpSession) {
        tcomment.setcTime(new Date());
        Tuser tuser = (Tuser) httpSession.getAttribute("currentUser");
        tcomment.setFromName(tuser.getUserName());
        Result result = commentService.addComment(tcomment);
        return result;
    }


    //删除一级评论
    @GetMapping(value = "/deleteComments/{id}")
    @ResponseBody
    public Result deleteComments(@PathVariable("id") Integer id) {
        Result result = null;
        result = this.commentService.deleteComments(id);
        return result;
    }



}
