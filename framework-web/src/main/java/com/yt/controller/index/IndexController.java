package com.yt.controller.index;

import com.google.code.kaptcha.Constants;
import com.yt.base.BaseAction;
import com.yt.entity.mybatis.Employee;
import com.yt.model.BaseResult;
import com.yt.service.mybatis.EmployeeService;
import com.yt.shiro.ShiroUserPasswordToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;


/**
 * 登录和首页
 *
 * @author yangtao
 * @version 2.0, 2016年3月22日
 * @since com.dongrongonline 2.0
 */
@Controller
public class IndexController extends BaseAction {


    @Autowired
    private EmployeeService employeeService;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);


    @RequestMapping(value = {"index"})
    public ModelAndView index() {
        ModelAndView mv = null;
        if (mv == null) {
            mv = new ModelAndView("login");
        } else {
            mv = new ModelAndView("index");
        }
        return mv;
    }


    /**
     * 后台登录
     *
     * @param loginName
     * @param password
     * @param session
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("loginName") String loginName, @RequestParam("password") String password,
                        HttpSession session, @RequestParam(value = "isRememberMe", defaultValue = "false") boolean isRememberMe, @RequestParam(value = "code") String code, Model model) {
        BaseResult baseResult = null;
        //图片验证码的session
        String imgCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        baseResult = employeeService.Login(loginName, password, code, imgCode);
        if (baseResult.isSuccess()) {
            //保存session用户
            Employee employee = (Employee) baseResult.getObj();
            setSessionEmployee(session, employee);
        }
        return "redirect:/index.do";
    }

    /**
     * 后台退出登录
     *
     * @return
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    @ResponseBody
    public void loginOut() {
    }

    @RequestMapping(value = "/editCurrentUserPwdPage")
    public ModelAndView editCurrentUserPwdPage() {
        ModelAndView mv = new ModelAndView("webpage/user/userEditPwd");
        return mv;
    }

    /**
     * 后台修改密码
     *
     * @param oldPwd
     * @param newPwd
     */
    @RequestMapping("/editCurrentUserPwd")
    @ResponseBody
    public void editCurrentUserPwd(
            @RequestParam("oldPwd") String oldPwd,
            @RequestParam("newPwd") String newPwd) {
    }


}
