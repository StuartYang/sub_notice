package com.notice.service;

import com.notice.util.Result;
import org.springframework.stereotype.Service;


@Service
public interface ReplyService {
	
	Result queryAllReplys(Integer commentid, Integer pn);
}
