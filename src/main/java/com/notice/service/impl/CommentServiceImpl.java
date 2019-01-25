package com.notice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.notice.entity.Tcomment;
import com.notice.entity.Treply;
import com.notice.entity.Tuser;
import com.notice.mapper.TcommentMapper;
import com.notice.mapper.TreplyMapper;
import com.notice.util.Result;
import com.notice.mapper.TuserMapper;
import com.notice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	TcommentMapper tcommentMapper;

	@Autowired
	TreplyMapper treplyMapper;
	@Autowired
	TuserMapper tusermapper;

	/*
        添加一级评论
     */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Result addComment(Tcomment tcomment) {
		Result result = new Result();
		tcommentMapper.insertSelective(tcomment);
		result.setMessage("留言成功");
		result.setStatus(1);
		return result;
	}

	/*
    查看一级评论
     */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Result getallcomment(Integer pn, Integer topicId) {
		Result result = new Result();
		Example example = new Example(Tcomment.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo(topicId);
		PageHelper.startPage(pn, 8);
		List<Tcomment> comment = tcommentMapper.selectByExample(example);
		PageInfo page = new PageInfo(comment, 8);
		result.setData(page);
		result.setMessage("留言遍历成功");
		result.setStatus(1);
		return result;
	}

	/*
    删除一级评论
     */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Result deleteComments(Integer id) {
		Result result = new Result();
		tcommentMapper.deleteByPrimaryKey(id);
		result.setMessage("删除一级留言成功！");
		result.setStatus(1);
		return result;
	}


	/*
    回复评论
     */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Result ReplyComment(Integer commentid, String content, String toUid, HttpSession httpSession) {
		//根据comment id 查 reply的表
		Result result = new Result();
		//插入二级评论的
		Tuser tuser = (Tuser) httpSession.getAttribute("currentUser");
		Treply treply = new Treply();
		treply.setCommentId(commentid);
		treply.setContent(content);
		treply.setReplyType(0);
		treply.setrTime(new Date());
		treply.setFromName(tuser.getUserName());
		//判断一下是二级评论还是三级评论
		Tcomment tcomment2 = tcommentMapper.selectByPrimaryKey(commentid);
		Integer rnum = tcomment2.getcRnum();
		int rnums = rnum + 1;
		Tcomment tcomment = new Tcomment();
		tcomment.setId(commentid);
		tcomment.setcRnum(rnums);
		tcommentMapper.updateByPrimaryKeySelective(tcomment);
		treplyMapper.insertSelective(treply);
		result.setMessage("留言成功");
		result.setStatus(1);
		return result;
	}


}
