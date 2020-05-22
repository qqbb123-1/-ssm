package com.swjd.controller;

import com.swjd.bean.User;
import com.swjd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    //去登陆
    @RequestMapping("/toLogin")
    public String toLogin(Model model){
        User user=new User();
        model.addAttribute("user",user);
        return "login";
    }

    //做登录
    @RequestMapping("/doLogin")
    public String doLogin(User user,Model model,HttpSession session){
        User u=userService.login(user);
        if (u!=null){
            //账号密码没问题
            if(u.getFlag().equals("1")){
                //登录成功把用户名存到session
                session.setAttribute("activeName",u.getuName());
                return "redirect:/customerController/tomain";
            }else {
                model.addAttribute("user",user);
                model.addAttribute("errorMsg","当前账号是禁用状态,请联系管理员");
                return "login";
            }
        }else {
            //账号密码有问题
            model.addAttribute("errorMsg","账号或者密码错误");
            model.addAttribute("user",user);
            return "login";
        }
    }

    //去main.jsp
    @RequestMapping("/tomain")
    public String toMian(){
        return "main";
    }

    //退出账号
    @RequestMapping("/logOut")
    public String logOut(HttpSession session,Model model){
        User user=new User();
        model.addAttribute("user",user);
        session.invalidate();
        return "login";
    }

    //购物车
    @RequestMapping("/toCart")
    public String toCart(){

        return "cart";
    }

    //退出账号
    @RequestMapping("/toMyTb")
    public String toMyTb(){
        return "myTb";
    }
}
