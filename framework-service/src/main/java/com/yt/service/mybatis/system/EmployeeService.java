package com.yt.service.mybatis.system;

import com.yt.core.dao.base.BaseDao;
import com.yt.entity.mybatis.Employee;
import com.yt.entity.mybatis.User;
import com.yt.model.BaseResult;
import com.yt.util.dhqjr.page.utils.PageResult;
import com.yt.util.dhqjr.page.utils.PageSearch;

import java.util.Date;

/**
 * 后台用户
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


    /**
     * @param username 用户名
     * @param email    email
     * @param phone    手机
     * @param isLogin  是否可登录
     * @param isEnable 是否限制
     * @param nikeName 昵称
     * @return
     */
    public PageResult<User> selectByPageList(PageSearch search, String username, String email, String phone, Integer isLogin, Integer isEnable, String nikeName);

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    public BaseResult saveUser(User user);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    public BaseResult updateUserInfo(User user);

    /**
     * 修改密码
     *
     * @param id          id
     * @param password    密码
     * @param newPassword 新密码
     * @param rePassword  确认密码
     * @return
     */
    public BaseResult updatePass(Integer id, String password, String newPassword, String rePassword);

    /**
     * 修改登录状态
     *
     * @param id
     * @return
     */
    public BaseResult isLogin(Integer id);

    /**
     * 修改可用状态
     *
     * @param id
     * @return
     */
    public BaseResult isEnable(Integer id);
}


