package com.yt.controller.bms.system;

import com.yt.entity.mybatis.Employee;
import com.yt.entity.mybatis.User;
import com.yt.model.BaseResult;
import com.yt.service.mybatis.system.EmployeeService;
import com.yt.service.mybatis.user.UserService;
import com.yt.util.dhqjr.EmptyUtil;
import com.yt.util.dhqjr.page.utils.PageResult;
import com.yt.util.dhqjr.page.utils.PageSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 后台帐号
 *
 * @author zhangsan
 * @version 1.0
 * @package com.yt.controller.bms.system
 * @date 2016/4/19 0019 18:08
 * @descption: 疯狂的王麻子团队!
 */
@Controller
@RequestMapping(value = "/api/employee")
public class EmployeeController {


    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * 数据表格页面
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {

        return "employee/employeelist";
    }

    /**
     * 数据表格的接口
     *
     * @param search
     * @param username
     * @param email
     * @param phone
     * @param isLogin
     * @param isEnable
     * @param nikeName
     * @return
     */
    @RequestMapping(value = "/selectByPageList", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<Employee> selectByPageList(PageSearch search, String username, String email, String phone, Integer isLogin, Integer isEnable, String nikeName) {
        return employeeService.selectByPageList(search, username, email, phone, isLogin, isEnable, nikeName);
    }

    /**
     * @param id 用户列表主键,如果没有主键就是添加页面
     * @return
     */
    @RequestMapping(value = "/addOrEdit", method = RequestMethod.GET)
    public String addOrEdit(Integer id, Model model) {
        //不为空修改为空保存
        if (!EmptyUtil.isEmpty(id)) {
            Employee employee = employeeService.selectByPrimaryKey(id);
            if (!EmptyUtil.isEmpty(employee)) {
                model.addAttribute("employee", employee);
                model.addAttribute("isUpdate", true);
            }
        } else {
            model.addAttribute("isUpdate", false);
        }
        return "employee/employeeaddoredit";
    }

    /**
     * 保存或者修改
     *
     * @param user
     * @param isUpdate
     * @return
     */
    @RequestMapping(value = "/saveAddOrEdit", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult saveAddOrEdit(Employee employee, @RequestParam(value = "isUpdate", defaultValue = "false") boolean isUpdate) {
        if (isUpdate) {
            return employeeService.updateUserInfo(employee);
        } else {
            return employeeService.saveUser(employee);
        }
    }

    @RequestMapping(value = "/updatepass", method = RequestMethod.GET)
    public String updatePass(String id, Model model) {
        model.addAttribute("id", id);
        return "employee/updatepass";
    }

    /**
     * 修改密码
     *
     * @param id
     * @param password
     * @param newPassword
     * @param rePassword
     * @return
     */
    @RequestMapping(value = "/updatepass", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult saveUpdatePass(Integer id, String password, String newPassword, String rePassword) {
        return employeeService.updatePass(id, password, newPassword, rePassword);
    }


    /**
     * 修改登录状态
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/isLogin", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult isLogin(Integer id) {
        return employeeService.isLogin(id);
    }

    /**
     * 修改登录状态
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/isEnable", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult isEnable(Integer id) {
        return employeeService.isEnable(id);
    }


}
