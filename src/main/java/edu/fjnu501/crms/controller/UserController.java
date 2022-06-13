package edu.fjnu501.crms.controller;

import edu.fjnu501.crms.domain.Result;
import edu.fjnu501.crms.domain.User;
import edu.fjnu501.crms.service.AccountService;
import edu.fjnu501.crms.state.ResultCodeState;
import edu.fjnu501.crms.state.StateDesc;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public Result login(@RequestBody User user) {
        int uid = 0;
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());
        try {
            subject.login(token);
            uid = accountService.getUidByUsername(user.getAccount());
        } catch (UnknownAccountException e) {
            return new Result(ResultCodeState.INVALID.getState(), e.getMessage(), null);
        } catch (ExcessiveAttemptsException e) {
            return new Result(ResultCodeState.FAILED.getState(), e.getMessage(), null);
        } catch (IncorrectCredentialsException e) {
            return new Result(ResultCodeState.PASSWORD.getState(), e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.LOGIN_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.LOGIN_SUCCESS.getDesc(), uid);
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public Result register(@RequestBody User user) {
        String username = null;
        try {
            username = accountService.register(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.REGISTER_SUCCESS.getDesc(), username);
    }

    @RequestMapping(value = "/unauthorized")
    @ResponseBody
    public Result unauthorized() {
        return new Result(ResultCodeState.UNAUTHORIZED.getState(), StateDesc.UNAUTHORIZED.getDesc(), null);
    }

    @RequestMapping(value = "/unlogin")
    @ResponseBody
    public Result unlogin() {
        return new Result(ResultCodeState.UNLOGIN.getState(), StateDesc.NOT_LOGIN.getDesc(), null);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Result logout(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        } catch (Exception e) {
            return new Result(500, "登出失败", null);
        }
        return new Result(200, "成功登出", null);
    }

}
