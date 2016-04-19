package com.yt.service.mybatis.system;

import com.yt.core.dao.base.BaseDao;
import com.yt.entity.mybatis.AuthGroupRelationShip;
import com.yt.model.BaseResult;

/**
 * 权限权限组关系
 *
 * @author zhangsan
 * @version 1.0
 * @package com.yt.service.mybatis.system
 * @date 2016/4/19 0019 17:04
 * @descption: 疯狂的王麻子团队!
 */
public interface AuthGroupRelationShipService extends BaseDao<AuthGroupRelationShip> {

    /**
     * 授权
     *
     * @param authGroupId 权限组id
     * @param ids         权限id
     * @return
     */
    public BaseResult grantAuthorization(Integer authGroupId, String ids);


    /**
     * 获取权限组的所有权限id
     *
     * @param authGroupId 权限组id
     * @return 拼接的权限id, 例如1, 2, 3,
     */
    public String getAuthGroupAuth(Integer authGroupId);
}
