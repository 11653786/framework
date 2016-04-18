package com.yt.service.mybatis.impl.user;

import com.yt.core.dao.base.impl.BaseDaoImpl;
import com.yt.entity.mybatis.User;
import com.yt.entity.mybatis.UserExample;
import com.yt.model.BaseResult;
import com.yt.service.mybatis.user.UserService;
import com.yt.util.dhqjr.DateUtil;
import com.yt.util.dhqjr.EmptyUtil;
import com.yt.util.dhqjr.page.utils.PageResult;
import com.yt.util.dhqjr.page.utils.PageResultBuilder;
import com.yt.util.dhqjr.page.utils.PageSearch;
import com.yt.util.yt.myutils.Md5Utils;
import com.yt.util.yt.myutils.ValidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.service.mybatis.impl.user
 * @date 2016/4/6 0006 11:06
 * @descption: 疯狂的王麻子团队!
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {


    private static final Logger baseLog = LoggerFactory.getLogger(UserServiceImpl.class);

    private final static Integer pageSize = 10;
    private final static Integer page = 1;


    @Override
    public PageResult<User> selectByPageList(PageSearch search, String username, String email, String phone, Integer isLogin, Integer isEnable, String nikeName, Date startTime, Date endTime) {

        //条件查询拼装
        UserExample example = createExample(search, username, email, phone, isLogin, isEnable, nikeName, startTime, endTime);
        //设置分页信息
        search = PageSearch.setPageInfo(search);
        //排序
        if (!StringUtils.isEmpty(search.getSort()) && !StringUtils.isEmpty(search.getOrder())) {
            example.setOrderByClause(search.getSort() + " " + search.getOrder());
        }
        example.setPageSearch(search);

        PageResult<User> pr = null;

        try {
            pr = new PageResultBuilder<User>().buildPageData(
                    this.countByExample(example),
                    this.selectByExample(example)).toPageResult();
        } catch (Exception e) {
            baseLog.error("", e);
            return null;
        }

        return pr;
    }

    @Override
    public BaseResult saveUser(User user) {
        BaseResult result = new BaseResult(true, "保存成功!");
        try {
            if (!ValidUtils.isEmail(user.getEmail())) {
                return BaseResult.fail("邮箱输入不正确!");
            }

            if (!ValidUtils.isMobile(user.getPhone())) {
                return BaseResult.fail("手机号码输入不正确!");
            }
            //密码设置
            user.setPassword(Md5Utils.getMD5String(user.getPassword()));
            this.insert(user);
        } catch (Exception e) {
            baseLog.error("保存用户信息异常!:" + user.getClass().getName());
            return BaseResult.fail("保存参数异常!");
        }
        return result;
    }

    @Override
    public BaseResult updateUserInfo(User user) {
        BaseResult result = new BaseResult(true, "保存成功!");
        try {
            if (!ValidUtils.isEmail(user.getEmail())) {
                return BaseResult.fail("邮箱输入不正确!");
            }

            if (!ValidUtils.isMobile(user.getPhone())) {
                return BaseResult.fail("手机号码输入不正确!");
            }
            //密码设置
            this.updateByPrimaryKey(user);
        } catch (Exception e) {
            baseLog.error("保存用户信息异常!:" + user.getClass().getName());
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
            List<User> users = this.selectByExample(example);
            if (users.isEmpty()) {
                return BaseResult.fail("当前用户不存在或密码错误!");
            }
            //修改用户
            User user = users.get(0);
            user.setPassword(Md5Utils.getMD5String(newPassword));
            this.updateByPrimaryKey(user);
        } catch (Exception e) {
            baseLog.error("修改密码错误: ", "" + e.getMessage());
            return BaseResult.fail("操作异常!");
        }
        return result;
    }


    /**
     * 专门用来查询时间
     *
     * @param
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public UserExample createExample(PageSearch search, String username, String email, String phone, Integer isLogin, Integer isEnable, String nikeName, Date startTime, Date endTime) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
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
            criteria.andPhoneEqualTo(phone);
        }
        //登录状态
        if (!StringUtils.isEmpty(isLogin)) {
            criteria.andIsLoginEqualTo(Byte.valueOf(String.valueOf(isLogin)));
        }
        //限制状态
        if (!StringUtils.isEmpty(isEnable)) {
            criteria.andIsLoginEqualTo(Byte.valueOf(String.valueOf(isEnable)));
        }


        // A , NULL 上线时间
        if (EmptyUtil.isNotEmpty(startTime)
                && EmptyUtil.isEmpty(endTime)) {
            criteria.andLastLoginTimeGreaterThanOrEqualTo(startTime);

        } // NULL , B
        else if (EmptyUtil.isEmpty(startTime)
                && EmptyUtil.isNotEmpty(endTime)) {
            Date afterDay = DateUtil.getAfterDateAsDate(endTime, 1);
            criteria.andLastLoginTimeLessThanOrEqualTo(afterDay);

        }// A , B 不为空
        else if (EmptyUtil.isNotEmpty(startTime)
                && EmptyUtil.isNotEmpty(endTime)) {
            // A < = B
            if (DateUtil.checkMax(endTime, startTime)) {
                criteria.andLastLoginTimeGreaterThanOrEqualTo(startTime);
        /*		Date afterDay = DateUtil
                        .getAfterDateAsDate(dto.getEndTime(), 1);*/
                SimpleDateFormat s = new SimpleDateFormat(DateUtil.DATETIMESHOWFORMAT);
                criteria.andLastLoginTimeLessThanOrEqualTo(endTime);
            }
        }
        return example;
    }
}