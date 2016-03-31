package com.yt.shiro;

import com.yt.entity.mybatis.Employee;
import com.yt.entity.mybatis.EmployeeExample;
import com.yt.service.mybatis.EmployeeService;
import com.yt.util.yt.myutils.Md5Utils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.shiro
 * @date 2016/3/28 0028 15:40
 * @descption: 疯狂的王麻子团队!
 */
@Component
public class MyRealm extends AuthorizingRealm {



    private String bmsLoginType="1";
    private String mainLoginType="2";
    @Autowired
    private EmployeeService employeeService;

    /**
     * 权限验证,登录完成之后调用这个方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("123213321");
        return null;
    }

    /**
     * 登录验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        ShiroUserPasswordToken loginUser = (ShiroUserPasswordToken) authenticationToken;
        //若存在，将此用户存放到登录认证info中
        //后台登录
        if (loginUser.getLoginType().equals(bmsLoginType)) {

            List<Employee> employees = employeeService.selectByExample(setInfo(loginUser));
            if (employees.isEmpty()) {
                return null;
            } else {
                Employee employee = employees.get(0);
                //帐号不可用或者限制登录
                if (employee.getIsEnable() == 0 || employee.getIsLogin() == 0) {
                    return null;
                } else {
                    //登录成功返回登录信息,, String mobile, String loginType, String email, String nikeName
                    return new ShiroAuthenticationInfo(loginUser.getUsername(), loginUser.getPassword(), getName(),employee.getMobile(),bmsLoginType,employee.getEmail(),employee.getNikeName());
                    //return new SimpleAuthenticationInfo(loginUser.getUsername(), loginUser.getPassword(), getName());
                }
            }
        } else {//前台登录

        }
      return null;
    }

    private EmployeeExample setInfo(ShiroUserPasswordToken loginUser) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria c = employeeExample.createCriteria();
        c.andUserNameEqualTo(loginUser.getUsername());
        String password = Md5Utils.getMD5String(String.valueOf(loginUser.getPassword()));
        c.andPasswordEqualTo(password);
        return employeeExample;

    }
}
