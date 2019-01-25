package com.notice.service;

import com.notice.entity.Tnotice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoticeService {

    /*
    新增
     */
    int insert(Tnotice notice);

    /*
    查询一条数据
     */
    Tnotice selectByPrimaryKey(Integer id);


    /*
    跟新
     */
    int update(Tnotice notice);

    /*
    删除
     */
    int deleteByPrimaryKey(Tnotice notice);


    /*
    跟新点击量
     */
    int updatePv(Integer id);

	List<Tnotice> getQueryList();
}