package com.yt.service.mybatis.system.impl;

import com.yt.core.dao.base.impl.BaseDaoImpl;
import com.yt.entity.mybatis.*;
import com.yt.model.BaseResult;
import com.yt.service.mybatis.system.*;
import com.yt.util.dhqjr.EmptyUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private AuthGroupRelationShipService authGroupRelationShipService;
    @Autowired
    private AuthService authService;


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
            int int1 = this.deleteByExample(example);


            employeeAuthGroupRelationShip.setEmployeeName(employee.getUserName());
            employeeAuthGroupRelationShip.setAuthGroupName(authGroup.getAuthGroupName());
            this.insert(employeeAuthGroupRelationShip);
        } catch (Exception e) {
            return BaseResult.fail("操作异常!");
        }

        return result;
    }

    @Override
    public void saveSessionUserAuth(HttpSession session, Integer employeeId) {
        //查询当前用户的所有权限组,一般只有一个组,除非是bug了
        EmployeeAuthGroupRelationShipExample example = new EmployeeAuthGroupRelationShipExample();
        EmployeeAuthGroupRelationShipExample.Criteria criteria = example.createCriteria();
        List<EmployeeAuthGroupRelationShip> ships = this.selectByExample(example);
        //循环查询权限
        List<Auth> auths = new ArrayList<Auth>();
        String authIds = "";
        for (EmployeeAuthGroupRelationShip ship : ships) {
            //根据权限组id获取当前权限组内的权限id
            authIds = authGroupRelationShipService.getAuthGroupAuth(ship.getAuthGroupId()) + authIds;
        }

        //获取所有的权限id
        List<Integer> listAuthids = intArrayToStrArray(authIds);

        //授权
        if (!StringUtils.isEmpty(authIds)) {
            AuthExample authExample = new AuthExample();
            AuthExample.Criteria authCriteria = authExample.createCriteria();
            authCriteria.andIdIn(listAuthids);
            authService.selectByExample(authExample);
        }
    }


    /**
     * @param ids 传入1,2,3,类型的权限id转换成list类型
     * @return
     */
    private List<Integer> intArrayToStrArray(String ids) {
        String[] authIdsArray = ids.split(",");
        List<Integer> integerList = new ArrayList<Integer>();
        for (int i = 0; i < authIdsArray.length; i++) {
            integerList.add(Integer.valueOf(authIdsArray[i]));
            System.out.println("ok: " + authIdsArray[i]);
        }
        return integerList;
    }


}
