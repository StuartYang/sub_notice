package com.notice.controller;

import com.notice.entity.Tuser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class WeChatController {
    @GetMapping("/weChat")
    public String index(Model model, HttpSession httpSession){
        Tuser tuser = (Tuser) httpSession.getAttribute("currentUser");
        model.addAttribute("username",tuser.getUserName());
        return "talk/chat";
    }
}
