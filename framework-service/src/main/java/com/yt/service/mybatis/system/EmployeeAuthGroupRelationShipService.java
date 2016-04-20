package com.yt.service.mybatis.system;

import com.yt.core.dao.base.BaseDao;
import com.yt.entity.mybatis.EmployeeAuthGroupRelationShip;
import com.yt.model.BaseResult;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.service.mybatis.system
 * @date 2016/4/20 0020 15:43
 * @descption: 疯狂的王麻子团队!
 */
public interface EmployeeAuthGroupRelationShipService extends BaseDao<EmployeeAuthGroupRelationShip> {

    /**
     * 权限组分配
     *
     * @param employeeAuthGroupRelationShip
     * @return
     */
    public BaseResult saveOrUpdateAuthGroup(EmployeeAuthGroupRelationShip employeeAuthGroupRelationShip);
}
