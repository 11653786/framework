package com.yt.service.mybatis.impl;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.google.code.kaptcha.Constants;
import com.yt.core.dao.impl.BaseDaoImpl;
import com.yt.entity.mybatis.Employee;
import com.yt.entity.mybatis.EmployeeExample;
import com.yt.model.BaseResult;
import com.yt.service.mybatis.EmployeeService;
import com.yt.util.yt.myutils.Md5Utils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
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
}
