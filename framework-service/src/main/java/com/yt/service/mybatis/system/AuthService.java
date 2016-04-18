package com.yt.service.mybatis.system;

import com.yt.core.dao.base.BaseDao;
import com.yt.entity.mybatis.Auth;
import com.yt.model.BaseResult;
import com.yt.util.dhqjr.page.utils.PageResult;
import com.yt.util.dhqjr.page.utils.PageSearch;

/**
 * Created by Administrator on 2016/2/29 0029.
 */
public interface AuthService extends BaseDao<Auth> {


    public PageResult<Auth> selectByPageList(PageSearch search);

    /**
     * 保存权限
     *
     * @param auth
     * @return
     */
    public BaseResult saveAuth(Auth auth);

    /**
     * 修改权限
     *
     * @param auth
     * @return
     */
    public BaseResult updateAuth(Auth auth);
}
