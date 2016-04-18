package com.yt.service.mybatis.system.impl;

import com.yt.core.dao.base.impl.BaseDaoImpl;
import com.yt.entity.mybatis.Auth;
import com.yt.entity.mybatis.AuthExample;
import com.yt.entity.mybatis.User;
import com.yt.entity.mybatis.UserExample;
import com.yt.service.mybatis.system.AuthService;
import com.yt.util.dhqjr.page.utils.PageResult;
import com.yt.util.dhqjr.page.utils.PageResultBuilder;
import com.yt.util.dhqjr.page.utils.PageSearch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2016/2/29 0029.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AuthServiceImpl extends BaseDaoImpl<Auth> implements AuthService {
    @Override
    public PageResult<Auth> selectByPageList(PageSearch search) {
        //条件查询拼装
        AuthExample example = new AuthExample();
        AuthExample.Criteria criteria = example.createCriteria();
        //设置分页信息
        search = PageSearch.setPageInfo(search);
        //排序
        if (!StringUtils.isEmpty(search.getSort()) && !StringUtils.isEmpty(search.getOrder())) {
            example.setOrderByClause(search.getSort() + " " + search.getOrder());
        }
        example.setPageSearch(search);

        PageResult<Auth> pr = null;

        try {
            pr = new PageResultBuilder<Auth>().buildPageData(
                    this.countByExample(example),
                    this.selectByExample(example)).toPageResult();
        } catch (Exception e) {
            return null;
        }

        return pr;
    }
}
