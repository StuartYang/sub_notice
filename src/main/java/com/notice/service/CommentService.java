package com.notice.service;


import com.notice.entity.Tcomment;
import com.notice.util.Result;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public interface  CommentService {

    //新增留言
    Result addComment(Tcomment tcomment);
    //新增二级留言
    //查询留言
    Result getallcomment(Integer pn, Integer topicId);

    Result deleteComments(Integer id);

    Result ReplyComment(Integer commentid, String content, String toName, HttpSession httpSession);

}
