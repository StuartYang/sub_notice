package com.notice.controller;

import com.notice.entity.Tnotice;
import com.notice.entity.Tuser;
import com.notice.service.NoticeService;
import com.notice.util.R;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("list")
    @ResponseBody
    public R gertlistNotice() {
        List<Tnotice> pList = noticeService.getQueryList();
        if (pList != null) {
            HashMap result = R.ok();
            result.put("count", pList.size());
            result.put("data", pList);
            return (R) result;
        }
        return R.error("操作错误");
    }


    /**
     * 新增公告
     * 页面请求，跳转到add界面
     */
    @GetMapping("/add")
    public String addNotice() {
        return "/notice/add";
    }

    /**
     * 新增保存公告（缺用户）
     */
    @PostMapping("save")
    @ResponseBody
    public R addSaveNotice(HttpSession session, Tnotice notice) {
        System.out.println(notice.getTnTitle());
        Tuser tuser = (Tuser) session.getAttribute("currentUser");
        notice.setTnType(1);
        notice.setTuId(tuser.getId());
        notice.setTuStatus(1);
        notice.setGenTime(new Date());
        notice.setPublicTime(new Date());
        notice.setModifyTime(new Date());
        notice.setViews(0);

        //将影响的行数传进去判断
        int status = noticeService.insert(notice);
        if (status == 1) {
            return R.ok();
        }
        return R.error();
    }


    /**
     * url：/edit/{noticeId} 路径传参，公告id
     * return：将该公告信息返回去，进行随页面返回展示
     */
    @RequestMapping("/edit/{id}")
    @ResponseBody
    public R editNotice(@PathVariable("id") Integer id) {
        Tnotice tnotice = noticeService.selectByPrimaryKey(id);
        if (tnotice != null) {
            return R.ok().put("data", tnotice);
        }
        return R.error();
    }


    /**
     * 修改保存公告
     * 返回如果正确
     * url：/edit post提交 表单封装为对象传输
     * 返回的是R状态json
     */
    @RequestMapping("/update")
    @ResponseBody
    public R updatNotice(Tnotice tnotice) {
        tnotice.setModifyTime(new Date());
        int status = noticeService.update(tnotice);
        if (status == 1) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 路径传参，测试成功
     * url: id是公告的id
     * 删除一条公告
     */

    @RequestMapping("/remove")
    @ResponseBody
    public R delectNotice(Tnotice tnotice) {
        int status = noticeService.deleteByPrimaryKey(tnotice);
        if (status == 1) {
            return R.ok();
        }
        return R.error();
    }


    /**
     * 后台管理的查看公告
     */
    @PostMapping("/select")
    @ResponseBody
    public R selectNotice(@RequestParam("id") Integer id) {
        Tnotice tnotice = noticeService.selectByPrimaryKey(id);
        System.out.println(tnotice.getTnTitle());
        int status = noticeService.updatePv(id);
        if (tnotice != null && status > 0) {
            return R.ok().put("data", tnotice);
        }
        return R.error();
    }

    /**
     * 查询某一条公告,  并且访问量加一
     */
    @GetMapping("/select/{id}")
    public String selectNotice(@PathVariable("id") Integer id, Model model) {
        Tnotice tnotice = noticeService.selectByPrimaryKey(id);
        model.addAttribute("pass", tnotice);
        noticeService.updatePv(id);
        return "/comment/vist";
    }
}