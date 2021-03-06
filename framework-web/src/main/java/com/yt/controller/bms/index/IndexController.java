package com.yt.controller.bms.index;

import com.google.code.kaptcha.Constants;
import com.yt.base.BaseAction;
import com.yt.entity.mybatis.Employee;
import com.yt.model.BaseResult;
import com.yt.service.mybatis.system.EmployeeAuthGroupRelationShipService;
import com.yt.service.mybatis.system.EmployeeService;
import com.yt.util.dhqjr.EmptyUtil;
import com.yt.util.sessionutil.EmployeeSessionUtil;
import com.yt.util.yt.annotation.system.BmsUnSecurity;
import com.yt.util.yt.annotation.system.BmsUnSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    private EmployeeAuthGroupRelationShipService employeeAuthGroupRelationShipService;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @BmsUnSession
    @RequestMapping(value = {"index"})
    public ModelAndView index(HttpSession session) {
        Employee employee = EmployeeSessionUtil.getSessionEmployee(session);
        ModelAndView mv = new ModelAndView();
        if (employee == null) {
            mv.setViewName("login");
        } else {
            mv.setViewName("index");
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
    @BmsUnSession
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
            EmployeeSessionUtil.setSessionEmployee(session, employee);
            //获取session权限
            employeeAuthGroupRelationShipService.saveSessionUserAuth(session, employee.getId(), EmployeeSessionUtil.loginAuth);

        }
        return "redirect:/index.do";
    }

    /**
     * 后台退出登录
     *
     * @return
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    @BmsUnSecurity
    @ResponseBody
    public BaseResult loginOut(HttpSession session, HttpServletResponse response) {
        EmployeeSessionUtil.deleteEmployeeAuth(session);
        EmployeeSessionUtil.deleteSessionEmployee(session);
        return new BaseResult(true, "退出登录!");
    }

    /**
     * 修改密码页面
     *
     * @return
     */
    @RequestMapping(value = "/editCurrentUserPwdPage")
    @BmsUnSecurity
    public String editCurrentUserPwdPage(HttpSession session, Model model) {
        Employee employee = EmployeeSessionUtil.getSessionEmployee(session);
        if (!EmptyUtil.isEmpty(employee) && !EmptyUtil.isEmpty(employee.getId())) {
            model.addAttribute("id", employee.getId());
        }
        return "employee/updialog";
    }
}
