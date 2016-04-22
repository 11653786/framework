package com.yt.service.mybatis.system;

import com.yt.core.dao.base.BaseDao;
import com.yt.entity.mybatis.AuthGroup;
import com.yt.entity.mybatis.User;
import com.yt.model.BaseResult;
import com.yt.util.dhqjr.page.utils.PageResult;
import com.yt.util.dhqjr.page.utils.PageSearch;

import java.util.Date;

/**
 * 权限组
 *
 * @author zhangsan
 * @version 1.0
 * @package com.yt.service.mybatis.system
 * @date 2016/4/19 0019 10:11
 * @descption: 疯狂的王麻子团队!
 */
public interface AuthGroupService extends BaseDao<AuthGroup> {

    /**
     * 获取数据表格
     *
     * @param search
     * @return
     */
    public PageResult<AuthGroup> selectByPageList(PageSearch search);

    /**
     * 保存用户
     *
     * @param authGroup
     * @return
     */
    public BaseResult saveAuthGroup(AuthGroup authGroup);

    /**
     * 修改用户信息
     *
     * @param authGroup
     * @return
     */
    public BaseResult updateAuthGroupInfo(AuthGroup authGroup);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public BaseResult deleteAuthGroup(Integer id);

    /**
     * 根据权限组名称查询
     *
     * @param authGroupName
     * @return
     */
    public AuthGroup selectByName(String authGroupName);
}
