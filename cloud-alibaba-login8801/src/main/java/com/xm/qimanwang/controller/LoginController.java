package com.xm.qimanwang.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.xm.qimanwang.entity.Users;
import com.xm.qimanwang.mapper.UsersMapper;
import com.xm.qimanwang.services.LoginService;
import com.xm.qimanwang.util.MD5Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
public class LoginController {
    @Resource
    private UsersMapper usersMapper;
    @Value("${server.port}")
    private String SERVER_PORT;
    private String PREFIX = "http://localhost:";

    @Resource
    private LoginService loginService;

    @GetMapping("/getUsersAll")
    @ResponseBody
    public Users getUsersAll(){
        return usersMapper.getUserByPhone("13680850306");
    }

    //跳转登录页面
    @RequestMapping("/login")
    public String goLogin(){
        return "/login";
    }
    //跳转注册页面
    @RequestMapping("/register")
    public String goRegister(){
        return "/register";
    }

    @GetMapping("/test")
    @ResponseBody
    public Object test(HttpServletRequest request){
        System.out.println(request.getSession().getAttribute("MyUser"));
        return "测试";
    }

    @GetMapping("/getUserByPhone/{phone}")
    @ResponseBody
    public Object getUserByPhone(@PathVariable String phone){
        return usersMapper.getUserByPhone(phone);
    }

    //登录判断转跳并返回提示信息
    @PostMapping("/loginCheck")
//    @SentinelResource(value = "CheckCodeBlockHandler",
//            fallback = "getCheckCodeException")
    public ModelAndView loginCheck(HttpServletRequest request, Map<String,Object> msg,
                            String phone,
                            String password,
                            String checkCode){
        System.out.println("取出session中存入的验证码："+request.getSession().getAttribute("SecurityCode"));
        if (checkCode.equals(request.getSession().getAttribute("SecurityCode"))){
            System.out.println("验证码正确");
            Map<String, Boolean> checkLogin = loginService.isPhone(phone, password);
            if (checkLogin.get("checkPhone")){
                if (checkLogin.get("checkPassword")){
                    //登录成功保存用户信息
                    request.getSession().setAttribute("MyUser",phone);
                    request.getSession().setAttribute("returnInfo","");
                    return new ModelAndView(new RedirectView("http://localhost:9901/home"));
                }else {
                    request.getSession().setAttribute("returnInfo","密码错误o(╥﹏╥)o");
                }
            }else {
                request.getSession().setAttribute("returnInfo","该手机号未在本网站注册o(╥﹏╥)o");
            }

        }else{
            System.out.println("验证码错误");
            request.getSession().setAttribute("returnInfo","验证码错误o(╥﹏╥)o");
        }
        return new ModelAndView(new RedirectView("http://localhost:8801/login"));
    }

    //新用户的注册
    @PostMapping("/registerUser")
    public String newUserRegister(Users users,Map<String,Object> msg){
        //性别默认为男
        users.setGender("男");
        //hutool工具包获取系统日期
        users.setRecently(DateUtil.date().toDateStr());
        //设置密码序列化
        users.setPassword(MD5Utils.getMD5String(users.getPassword()));
        if (loginService.register(users).get("isPhone")){
            System.out.println("注册成功");
            msg.put("returnInfo","注册成功,请登录^_^");
            return "/login";
        }else{
            System.out.println("注册失败");
            msg.put("returnInfo","手机号存在");
            return "/register";
        }
    }

    //注销
    @GetMapping("/logout")
    public String Logout(HttpSession session){
        if (session.getAttribute("MyUser")!=null){
            session.removeAttribute("MyUser");
        }
        return "redirect:/login";
    }



    //生成验证码并输出
    @GetMapping("/getCode")
    public void  getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //定义图形验证码的长和宽  码值个数  干扰圈数
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(90, 40, 4, 10);
        //得到验证码
        String authCode = circleCaptcha.getCode();
        System.out.println("SecurityCode: "+authCode);
        //存入ServletContext中，与session的区别是ServletContext是全局并且浏览器仅存在一个
//        request.getServletContext().setAttribute("SecurityCode",authCode);
        //将验证码保存进session
        request.getSession().setAttribute("SecurityCode",authCode);
        circleCaptcha.write(response.getOutputStream());
    }


}
