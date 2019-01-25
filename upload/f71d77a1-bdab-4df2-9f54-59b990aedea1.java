package com.notice.service.impl;

import com.notice.entity.Tnotice;
import com.notice.mapper.TnoticeMapper;
import com.notice.service.NoticeService;

import tk.mybatis.mapper.entity.Example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class NoticeServiceImpl  implements NoticeService {

    @Autowired
    private TnoticeMapper noticeMapper;

    @Override
    public int insert(Tnotice notice) {
        return noticeMapper.insert(notice);
    }

    @Override
    public Tnotice selectByPrimaryKey(Long id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Tnotice notice) {
        return noticeMapper.updateByPrimaryKey(notice);
    }

    @Override
    public int deleteByPrimaryKey(Tnotice notice) {
        return noticeMapper.deleteByPrimaryKey(notice.getId());
    }

    @Override
    public List<Tnotice> QueryList() {
        return noticeMapper.selectNoticeList();
    }

    @Override
    public int deleteNotices(List<Integer> ids) {
        return noticeMapper.deleteList(ids);
    }

	@Override
	public List<Tnotice> getQueryList() {
		Example example = new Example(Tnotice.class);
		example.setOrderByClause("views DESC");
		List<Tnotice> selectAll = noticeMapper.selectByExample(example);
		return selectAll;
	}
	
	/*
     * 增加访问量 +1
     */
    @Override
    public int updatePv(Long  id) {
    	Tnotice tnotice = noticeMapper.selectByPrimaryKey(id);
    	Integer views = tnotice.getViews();
    	Integer num =views +1;
    	Tnotice record =new Tnotice();
    	record.setViews(num);
    	record.setId(id);
    	return noticeMapper.updateByPrimaryKeySelective(record);
    }
}