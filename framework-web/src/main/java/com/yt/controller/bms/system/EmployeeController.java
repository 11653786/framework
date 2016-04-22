package com.yt.controller.bms.system;

import com.yt.base.ResourceBaseController;
import com.yt.entity.mybatis.*;
import com.yt.model.BaseResult;
import com.yt.service.mybatis.system.AuthGroupService;
import com.yt.service.mybatis.system.EmployeeAuthGroupRelationShipService;
import com.yt.service.mybatis.system.EmployeeService;
import com.yt.util.dhqjr.EmptyUtil;
import com.yt.util.dhqjr.page.utils.PageResult;
import com.yt.util.dhqjr.page.utils.PageSearch;
import com.yt.util.yt.annotation.system.ParentSecurity;
import com.yt.util.yt.annotation.system.ResourceAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
@ResourceAnnotation(resourceGroup = "系统管理")
public class EmployeeController extends ResourceBaseController {


    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeAuthGroupRelationShipService employeeAuthGroupRelationShipService;
    @Autowired
    private AuthGroupService authGroupService;

    /**
     * 数据表格页面
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResourceAnnotation(name = "员工管理", pName = "系统管理", url = "/api/employee/list", remark = "员工管理", resourceType = "2", parentIsRoot = true)
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
    @ParentSecurity("/api/employee/list")
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
    @ResourceAnnotation(name = "员工添加", pName = "员工管理", url = "/api/employee/addOrEdit", remark = "员工添加", resourceType = "2")
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
     * @param employee
     * @param isUpdate
     * @return
     */
    @ParentSecurity("/api/employee/addOrEdit")
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
    @ResourceAnnotation(name = "员工修改密码", pName = "员工管理", url = "/api/employee/updatepass", remark = "员工修改密码", resourceType = "2")
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
    @ParentSecurity("/api/employee/updatepass")
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
    @ResourceAnnotation(name = "员工登录状态修改", pName = "员工管理", url = "/api/employee/isLogin", remark = "员工登录状态修改", resourceType = "2")
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
    @ResourceAnnotation(name = "员工使用状态修改", pName = "员工管理", url = "/api/employee/isEnable", remark = "员工使用状态修改", resourceType = "2")
    @RequestMapping(value = "/isEnable", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult isEnable(Integer id) {
        return employeeService.isEnable(id);
    }


    /**
     * 分配权限
     *
     * @param id 员工id
     * @return
     */
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    @ResourceAnnotation(name = "分配权限", pName = "员工管理", url = "/api/employee/auth", remark = "分配权限", resourceType = "2")
    public String auth(Integer id, Model model) {
        //不为空修改为空保存
        if (!EmptyUtil.isEmpty(id)) {
            EmployeeAuthGroupRelationShipExample employeeExample = new EmployeeAuthGroupRelationShipExample();
            EmployeeAuthGroupRelationShipExample.Criteria criteria = employeeExample.createCriteria();
            criteria.andEmployeeIdEqualTo(id);
            //用户id传过去
            List<EmployeeAuthGroupRelationShip> mygroups = employeeAuthGroupRelationShipService.selectByExample(employeeExample);
            model.addAttribute("employeeId", id);
            if (!mygroups.isEmpty()) {
                model.addAttribute("mygroup", mygroups.get(0));
                model.addAttribute("isUpdate", true);
            }
        } else {
            model.addAttribute("isUpdate", false);
        }
        AuthExample example = new AuthExample();
        AuthExample.Criteria criteria = example.createCriteria();
        criteria.andIsEnableEqualTo("1");
        //全部的权限组
        List<AuthGroup> authGroups = authGroupService.selectByExample(example);
        model.addAttribute("authGroups", authGroups);
        return "employee/addauth";
    }

    /**
     * 分配权限
     *
     * @param employeeAuthGroupRelationShip
     * @return
     */
    @ParentSecurity("/api/employee/auth")
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addAuth(EmployeeAuthGroupRelationShip employeeAuthGroupRelationShip) {
        return employeeAuthGroupRelationShipService.saveOrUpdateAuthGroup(employeeAuthGroupRelationShip);
    }


}
