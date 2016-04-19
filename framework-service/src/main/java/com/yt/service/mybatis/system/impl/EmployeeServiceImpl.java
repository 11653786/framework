package com.yt.service.mybatis.system.impl;

import com.yt.core.dao.base.impl.BaseDaoImpl;
import com.yt.entity.mybatis.Employee;
import com.yt.entity.mybatis.EmployeeExample;
import com.yt.entity.mybatis.UserExample;
import com.yt.model.BaseResult;
import com.yt.service.mybatis.system.EmployeeService;
import com.yt.util.dhqjr.EmptyUtil;
import com.yt.util.dhqjr.page.utils.PageResult;
import com.yt.util.dhqjr.page.utils.PageResultBuilder;
import com.yt.util.dhqjr.page.utils.PageSearch;
import com.yt.util.yt.myutils.Md5Utils;
import com.yt.util.yt.myutils.ValidUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/2/26 0026.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EmployeeServiceImpl extends BaseDaoImpl<Employee> implements EmployeeService {
    @Override
    public BaseResult Login(String username, String password, String code, String imgCode) {
        BaseResult result = new BaseResult(true);

        //判断输入信息是否有误
        result = isLogin(username, password, code, imgCode);
        if (!result.isSuccess()) {
            return result;
        }
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(username).andPasswordEqualTo(Md5Utils.getMD5String(password));
        List<Employee> employees = this.selectByExample(example);
        if (employees.isEmpty()) {
            return BaseResult.fail("帐号密码输入错误!");
        } else {
            if (employees.get(0).getIsEnable() == 0 || employees.get(0).getIsLogin() == 0) {
                return BaseResult.fail("账户不可使用!");
            }
            return BaseResult.success("登录成功", employees.get(0));
        }
    }

    @Override
    public PageResult<Employee> selectByPageList(PageSearch search, String username, String email, String phone, Integer isLogin, Integer isEnable, String nikeName) {

        //条件查询拼装
        EmployeeExample example = createExample(search, username, email, phone, isLogin, isEnable, nikeName);
        //设置分页信息
        search = PageSearch.setPageInfo(search);
        //排序
        if (!StringUtils.isEmpty(search.getSort()) && !StringUtils.isEmpty(search.getOrder())) {
            example.setOrderByClause(search.getSort() + " " + search.getOrder());
        }
        example.setPageSearch(search);

        PageResult<Employee> pr = null;

        try {
            pr = new PageResultBuilder<Employee>().buildPageData(
                    this.countByExample(example),
                    this.selectByExample(example)).toPageResult();
        } catch (Exception e) {
            return null;
        }

        return pr;
    }

    @Override
    public BaseResult saveUser(Employee employee) {
        BaseResult result = new BaseResult(true, "保存成功!");
        try {
            if (!ValidUtils.isEmail(employee.getEmail())) {
                return BaseResult.fail("邮箱输入不正确!");
            }

            if (!ValidUtils.isMobile(employee.getMobile())) {
                return BaseResult.fail("手机号码输入不正确!");
            }
            //密码设置
            employee.setPassword(Md5Utils.getMD5String(employee.getPassword()));
            this.insert(employee);
        } catch (Exception e) {
            return BaseResult.fail("保存参数异常!");
        }
        return result;
    }

    @Override
    public BaseResult updateUserInfo(Employee employee) {
        BaseResult result = new BaseResult(true, "保存成功!");
        try {
            if (!ValidUtils.isEmail(employee.getEmail())) {
                return BaseResult.fail("邮箱输入不正确!");
            }

            if (!ValidUtils.isMobile(employee.getMobile())) {
                return BaseResult.fail("手机号码输入不正确!");
            }
            //密码设置
            this.updateByPrimaryKey(employee);
        } catch (Exception e) {
            return BaseResult.fail("保存参数异常!");
        }
        return result;
    }

    @Override
    public BaseResult updatePass(Integer id, String password, String newPassword, String rePassword) {
        BaseResult result = new BaseResult(true, "修改成功!");
        try {
            if (EmptyUtil.isEmpty(id) || StringUtils.isEmpty(password) || StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(rePassword)) {
                return BaseResult.fail("传入参数异常!");
            }

            if (!newPassword.equals(rePassword)) {
                return BaseResult.fail("两次密码不一致!");
            }

            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(id).andPasswordEqualTo(Md5Utils.getMD5String(password));
            List<Employee> employees = this.selectByExample(example);
            if (employees.isEmpty()) {
                return BaseResult.fail("当前用户不存在或密码错误!");
            }
            //修改用户
            Employee employee = employees.get(0);
            employee.setPassword(Md5Utils.getMD5String(newPassword));
            this.updateByPrimaryKey(employee);
        } catch (Exception e) {
            return BaseResult.fail("操作异常!");
        }
        return result;
    }

    @Override
    public BaseResult isLogin(Integer id) {
        BaseResult result = new BaseResult(true, "修改成功!");
        try {
            if (EmptyUtil.isEmpty(id)) {
                return BaseResult.fail("传入参数异常!");
            }
            Employee employee = this.selectByPrimaryKey(id);

            if (EmptyUtil.isEmpty(employee)) {
                return BaseResult.fail("当前用户不存在或密码错误!");
            }

            employee.setIsLogin((employee.getIsLogin() == 1 ? 0 : 1));
            this.updateByPrimaryKey(employee);
        } catch (Exception e) {
            return BaseResult.fail("操作异常!");
        }

        return result;
    }

    @Override
    public BaseResult isEnable(Integer id) {
        BaseResult result = new BaseResult(true, "修改成功!");
        try {
            if (EmptyUtil.isEmpty(id)) {
                return BaseResult.fail("传入参数异常!");
            }
            Employee employee = this.selectByPrimaryKey(id);

            if (EmptyUtil.isEmpty(employee)) {
                return BaseResult.fail("当前用户不存在或密码错误!");
            }

            employee.setIsEnable((employee.getIsEnable() == 1 ? 0 : 1));
            this.updateByPrimaryKey(employee);
        } catch (Exception e) {
            return BaseResult.fail("操作异常!");
        }

        return result;
    }


    /**
     * 验证登录的方法
     *
     * @param loginName
     * @param password
     * @param code
     * @param imgCode
     * @return
     */
    private BaseResult isLogin(String loginName, String password, String code, String imgCode) {


        if (StringUtils.isEmpty(loginName)) {
            return BaseResult.fail("登录名称不能为空!");
        } else if (StringUtils.isEmpty(password)) {
            return BaseResult.fail("登录密码不能为空!");

        } else if (StringUtils.isEmpty(code) || !code.equalsIgnoreCase(imgCode)) {
            return BaseResult.fail("验证码不正确!");
        } else {
            return BaseResult.success("");
        }
    }


    public EmployeeExample createExample(PageSearch search, String username, String email, String phone, Integer isLogin, Integer isEnable, String nikeName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        //用户名称
        if (!StringUtils.isEmpty(username)) {
            criteria.andUserNameEqualTo(username);
        }
        //昵称
        if (!StringUtils.isEmpty(nikeName)) {
            criteria.andNikeNameLike(nikeName);
        }
        //email
        if (!StringUtils.isEmpty(email)) {
            criteria.andEmailEqualTo(email);
        }
        //电话
        if (!StringUtils.isEmpty(phone)) {
            criteria.andMobileEqualTo(phone);
        }
        //登录状态
        if (!StringUtils.isEmpty(isLogin)) {
            criteria.andIsLoginEqualTo(isLogin);
        }
        //限制状态
        if (!StringUtils.isEmpty(isEnable)) {
            criteria.andIsLoginEqualTo(isEnable);
        }

        return example;
    }

}
