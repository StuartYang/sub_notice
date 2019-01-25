package com.notice.service.impl;


import com.notice.entity.Tnotice;
import com.notice.entity.Treply;
import com.notice.mapper.TnoticeMapper;
import com.notice.mapper.TreplyMapper;
import com.notice.service.ReplyService;
import com.notice.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service("replyService")
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	TreplyMapper treplyMapper;
	
	@Autowired
	TnoticeMapper tnoticeMapper;
	//查询全部二级评论
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Result queryAllReplys(Integer commentid, Integer pn) {
		Result result=new Result();
		  Example example =new Example(Treply.class);
		  Example.Criteria criteria = example.createCriteria();
		   criteria.andEqualTo(commentid);
		PageHelper.startPage(pn, 8);
		 List<Treply> selectByExample = treplyMapper.selectByExample(example);
		PageInfo page = new PageInfo(selectByExample, 8);
		result.setData(page);
		
		result.setMessage("成功");
		
		result.setStatus(1);
		return result;
	}

	
	

}
