package com.yt.service.mybatis.system.impl;

import com.yt.core.dao.base.impl.BaseDaoImpl;
import com.yt.entity.mybatis.AuthGroup;
import com.yt.entity.mybatis.AuthGroupRelationShip;
import com.yt.entity.mybatis.AuthGroupRelationShipExample;
import com.yt.model.BaseResult;
import com.yt.service.mybatis.system.AuthGroupRelationShipService;
import com.yt.service.mybatis.system.AuthGroupService;
import com.yt.util.dhqjr.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.service.mybatis.system.impl
 * @date 2016/4/19 0019 17:05
 * @descption: 疯狂的王麻子团队!
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AuthGroupRelationShipServiceImpl extends BaseDaoImpl<AuthGroupRelationShip> implements AuthGroupRelationShipService {


    @Autowired
    private AuthGroupService authGroupService;

    @Override
    public BaseResult grantAuthorization(Integer authGroupId, String ids) {
        BaseResult result = new BaseResult(true, "保存成功!");
        try {
            if (isValid(authGroupId, ids)) {
                return BaseResult.fail("传入参数异常!");
            }

            AuthGroupRelationShipExample example = new AuthGroupRelationShipExample();
            AuthGroupRelationShipExample.Criteria criteria = example.createCriteria();
            //in 查询
            List<Integer> intList = getIntList(ids);
            criteria.andAuthIdIn(intList);
            this.deleteByExample(example);
            //之前的都删除
            for (Integer authId : intList) {
                AuthGroupRelationShip relationShip = new AuthGroupRelationShip(authId, authGroupId);
                this.insert(relationShip);
            }

        } catch (Exception e) {
            return BaseResult.fail("授权失败!");
        }
        return result;
    }

    @Override
    public String getAuthGroupAuth(Integer authGroupId) {
        String ids = "";
        try {
            if (isValid(authGroupId, ids)) {
                return ids;
            }

            AuthGroupRelationShipExample example = new AuthGroupRelationShipExample();
            AuthGroupRelationShipExample.Criteria criteria = example.createCriteria();
            criteria.andAuthGroupIdEqualTo(authGroupId);
            //in 查询
            List<AuthGroupRelationShip> list = this.selectByExample(example);
            //之前的都删除
            for (AuthGroupRelationShip entity : list) {
                ids = entity.getId() + "" + ids;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ids;
        }
        return ids;
    }

    private List<Integer> getIntList(String ids) {
        String[] idsArray = ids.split(",");
        List<String> strlist = Arrays.asList(idsArray);
        List<Integer> intList = new ArrayList<Integer>();
        for (String strId : strlist) {
            intList.add(Integer.valueOf(strId));
        }
        return intList;
    }

    private boolean isValid(Integer authGroupId, String ids) {
        boolean isTrue = true;
        if (EmptyUtil.isEmpty(authGroupId) || StringUtils.isEmpty(ids)) {
            isTrue = false;
        }
        AuthGroup authGroup = authGroupService.selectByPrimaryKey(authGroupId);
        if (EmptyUtil.isEmpty(authGroup)) {
            isTrue = false;
        }
        return isTrue;
    }
}
