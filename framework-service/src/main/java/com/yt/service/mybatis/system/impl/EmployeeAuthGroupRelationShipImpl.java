package com.yt.service.mybatis.system.impl;

import com.yt.core.dao.base.impl.BaseDaoImpl;
import com.yt.entity.mybatis.AuthGroup;
import com.yt.entity.mybatis.Employee;
import com.yt.entity.mybatis.EmployeeAuthGroupRelationShip;
import com.yt.entity.mybatis.EmployeeAuthGroupRelationShipExample;
import com.yt.model.BaseResult;
import com.yt.service.mybatis.system.AuthGroupService;
import com.yt.service.mybatis.system.EmployeeAuthGroupRelationShipService;
import com.yt.service.mybatis.system.EmployeeService;
import com.yt.util.dhqjr.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.service.mybatis.system.impl
 * @date 2016/4/20 0020 15:43
 * @descption: 疯狂的王麻子团队!
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EmployeeAuthGroupRelationShipImpl extends BaseDaoImpl<EmployeeAuthGroupRelationShip> implements EmployeeAuthGroupRelationShipService {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AuthGroupService authGroupService;

    @Override
    public BaseResult saveOrUpdateAuthGroup(EmployeeAuthGroupRelationShip employeeAuthGroupRelationShip) {
        BaseResult result = new BaseResult(true, "操作成功!");
        try {
            if (EmptyUtil.isEmpty(employeeAuthGroupRelationShip.getEmployeeId()) || EmptyUtil.isEmpty(employeeAuthGroupRelationShip.getAuthGroupId())) {
                return BaseResult.fail("传入参数异常!");
            }
            Employee employee = employeeService.selectByPrimaryKey(employeeAuthGroupRelationShip.getEmployeeId());

            if (EmptyUtil.isEmpty(employee)) {
                return BaseResult.fail("员工不存在!");
            }
            AuthGroup authGroup = authGroupService.selectByPrimaryKey(employeeAuthGroupRelationShip.getAuthGroupId());

            if (EmptyUtil.isEmpty(authGroup)) {
                return BaseResult.fail("权限组不存在!");
            }

            //保存之前先清除以前的权限
            EmployeeAuthGroupRelationShipExample example = new EmployeeAuthGroupRelationShipExample();
            EmployeeAuthGroupRelationShipExample.Criteria criteria = example.createCriteria();
            criteria.andEmployeeIdEqualTo(employeeAuthGroupRelationShip.getEmployeeId());
            int int1=this.deleteByExample(example);


            employeeAuthGroupRelationShip.setEmployeeName(employee.getUserName());
            employeeAuthGroupRelationShip.setAuthGroupName(authGroup.getAuthGroupName());
            this.insert(employeeAuthGroupRelationShip);
        } catch (Exception e) {
            return BaseResult.fail("操作异常!");
        }

        return result;
    }
}
