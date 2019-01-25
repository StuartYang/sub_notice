package com.notice;

import com.notice.entity.Tnotice;
import com.notice.service.NoticeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerApplicationTests {

    @Autowired
    private NoticeService noticeService;
    @Test
    public void testSelect(){

    }
}
