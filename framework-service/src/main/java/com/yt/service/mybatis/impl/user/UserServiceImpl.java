package com.yt.service.mybatis.impl.user;

import com.yt.core.dao.impl.BaseDaoImpl;
import com.yt.entity.mybatis.User;
import com.yt.entity.mybatis.UserExample;
import com.yt.service.mybatis.user.UserService;
import com.yt.util.dhqjr.EmptyUtil;
import com.yt.util.dhqjr.page.utils.PageResult;
import com.yt.util.dhqjr.page.utils.PageResultBuilder;
import com.yt.util.dhqjr.page.utils.PageSearch;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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


    private Logger logger = Logger.getLogger(this.getClass());

    private final static Integer pageSize = 10;
    private final static Integer page = 1;


    @Override
    public PageResult<User> selectByPageList(PageSearch search, String username, String email, String phone, Integer isLogin, Integer isEnable,String nikeName) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        //用户名称
        if (!StringUtils.isEmpty(username)) {
            criteria.andUserNameLike(username);
        }
        //昵称
        if (!StringUtils.isEmpty(nikeName)) {
            criteria.andNikeNameLike(nikeName);
        }
        //email
        if (!StringUtils.isEmpty(email)) {
            criteria.andEmailLike(email);
        }
        //电话
        if (!StringUtils.isEmpty(phone)) {
            criteria.andPhoneLike(phone);
        }
        //登录状态
        if (!StringUtils.isEmpty(isLogin)) {
            criteria.andIsLoginEqualTo(Byte.valueOf(String.valueOf(isLogin)));
        }
        //限制状态
        if (!StringUtils.isEmpty(isEnable)) {
            criteria.andIsLoginEqualTo(Byte.valueOf(String.valueOf(isEnable)));
        }
        search = PageSearch.setPageInfo(search);
        example.setPageSearch(search);

        PageResult<User> pr = null;

        try {
            pr = new PageResultBuilder<User>().buildPageData(
                    this.countByExample(example),
                    this.selectByExample(example)).toPageResult();
        } catch (Exception e) {
            logger.error("", e);
            return null;
        }

        return pr;
    }
}
