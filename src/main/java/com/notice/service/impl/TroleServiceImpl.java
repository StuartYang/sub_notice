package com.notice.service.impl;

import com.notice.entity.Trole;
import com.notice.mapper.TroleMapper;
import com.notice.service.TroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("troleService")
public class TroleServiceImpl  extends BaseService<Trole> implements TroleService {
    @Autowired
    private TroleMapper troleMapper;
    @Override
    public List<Trole> selectRolesByUserId(Integer userid) {
        return troleMapper.selectRolesByUserId(userid);
    }
}
