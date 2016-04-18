package com.yt.service.mybatis.user;

import com.yt.core.dao.base.BaseDao;
import com.yt.entity.mybatis.User;
import com.yt.model.BaseResult;
import com.yt.util.dhqjr.page.utils.PageResult;
import com.yt.util.dhqjr.page.utils.PageSearch;

import java.util.Date;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.service.mybatis.impl.user
 * @date 2016/4/6 0006 11:05
 * @descption: 疯狂的王麻子团队!
 */
public interface UserService extends BaseDao<User> {

    /**
     * @param page      当前分页
     * @param rows      每页显示数量
     * @param username  用户名
     * @param email     email
     * @param phone     手机
     * @param isLogin   是否可登录
     * @param isEnable  是否限制
     * @param nikeName  昵称
     * @param startTime 用来查询最后一次登录时间的查询条件
     * @param endTime
     * @return
     */
    public PageResult<User> selectByPageList(PageSearch search, String username, String email, String phone, Integer isLogin, Integer isEnable, String nikeName, Date startTime, Date endTime);

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
     * @param id    id
     * @param password  密码
     * @param newPassword   新密码
     * @param rePassword    确认密码
     * @return
     */
    public BaseResult updatePass(Integer id, String password, String newPassword, String rePassword);
}
