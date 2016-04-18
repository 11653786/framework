package com.yt.service.mybatis.system.impl;

import com.yt.core.dao.base.impl.BaseDaoImpl;
import com.yt.entity.mybatis.Log;
import com.yt.service.mybatis.system.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/2/26 0026.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LogServiceImpl extends BaseDaoImpl<Log> implements LogService {
}
