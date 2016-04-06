package com.yt.service.mybatis.system;

import com.yt.core.dao.BaseDao;
import com.yt.entity.mybatis.Employee;
import com.yt.model.BaseResult;

/**
 * Created by Administrator on 2016/2/26 0026.
 */
public interface EmployeeService extends BaseDao<Employee> {

    /**
     * @param username 帐号
     * @param password 密码
     * @param code     输入的验证码
     * @param imgCode  验证码
     * @return
     */
    public BaseResult Login(String username, String password, String code, String imgCode);
}
