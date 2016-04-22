package com.yt.service.mybatis.system.impl;

import com.yt.core.dao.base.impl.BaseDaoImpl;
import com.yt.entity.mybatis.AuthExample;
import com.yt.entity.mybatis.AuthGroup;
import com.yt.entity.mybatis.AuthGroupExample;
import com.yt.entity.mybatis.User;
import com.yt.model.BaseResult;
import com.yt.service.mybatis.system.AuthGroupService;
import com.yt.util.dhqjr.EmptyUtil;
import com.yt.util.dhqjr.page.utils.PageResult;
import com.yt.util.dhqjr.page.utils.PageResultBuilder;
import com.yt.util.dhqjr.page.utils.PageSearch;
import com.yt.util.yt.myutils.Md5Utils;
import com.yt.util.yt.myutils.ValidUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.service.mybatis.system.impl
 * @date 2016/4/19 0019 10:12
 * @descption: 疯狂的王麻子团队!
 */
@Service(value = "authGroupService")
@Transactional(propagation = Propagation.REQUIRED)
public class AuthGroupServiceImpl extends BaseDaoImpl<AuthGroup> implements AuthGroupService {
    @Override
    public PageResult<AuthGroup> selectByPageList(PageSearch search) {
        PageResult<AuthGroup> pr = null;
        AuthGroupExample example = new AuthGroupExample();
        example.setPageSearch(search);
        try {
            pr = new PageResultBuilder<AuthGroup>().buildPageData(
                    this.countByExample(example),
                    this.selectByExample(example)).toPageResult();
        } catch (Exception e) {
            return null;
        }

        return pr;
    }

    @Override
    public BaseResult saveAuthGroup(AuthGroup authGroup) {
        BaseResult result = new BaseResult(true, "保存成功!");
        try {

            if (StringUtils.isEmpty(authGroup.getAuthGroupName())) {
                return BaseResult.fail("名称不能为空!");
            }
            //密码设置
            this.insert(authGroup);
        } catch (Exception e) {
            return BaseResult.fail("保存参数异常!");
        }
        return result;
    }

    @Override
    public BaseResult updateAuthGroupInfo(AuthGroup authGroup) {
        BaseResult result = new BaseResult(true, "修改成功!");
        try {
            if (StringUtils.isEmpty(authGroup.getAuthGroupName())) {
                return BaseResult.fail("名称不能为空!");
            }
            //密码设置
            this.updateByPrimaryKey(authGroup);
        } catch (Exception e) {
            return BaseResult.fail("修改参数异常!");
        }
        return result;
    }

    @Override
    public BaseResult deleteAuthGroup(Integer id) {
        BaseResult result = new BaseResult(true, "删除成功!");
        try {
            if (EmptyUtil.isEmpty(id)) {
                return BaseResult.fail("传入参数不正确!");
            }
            //密码设置
            return this.deleteByPrimaryKey(id) == 1 ? result : BaseResult.fail("修改失败!");
        } catch (Exception e) {
            return BaseResult.fail("修改异常!");
        }
    }

    @Override
    public AuthGroup selectByName(String authGroupName) {
        AuthGroupExample authGroupExample = new AuthGroupExample();
        AuthGroupExample.Criteria criteria = authGroupExample.createCriteria();
        criteria.andAuthGroupNameEqualTo(authGroupName);
        List<AuthGroup> list = selectByExample(authGroupExample);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
