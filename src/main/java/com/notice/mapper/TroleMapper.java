package com.notice.mapper;

import com.notice.entity.Trole;
import com.notice.util.MyMapper;

import java.util.List;

public interface TroleMapper extends MyMapper<Trole> {

    List<Trole> selectRolesByUserId(Integer userid);

}