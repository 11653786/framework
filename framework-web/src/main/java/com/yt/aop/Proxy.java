package com.yt.aop;

import com.yt.entity.mybatis.Employee;
import com.yt.entity.mybatis.Log;
import com.yt.model.BaseResult;
import com.yt.util.dhqjr.ByteUtil;
import com.yt.util.yt.annotation.Table;
import com.yt.service.mybatis.system.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * apo日志监控
 * Created by user on 2015/9/6.
 */
@Aspect
@Component
public class Proxy {


    @Autowired
    private LogService logService;


    /**
     * 要监控的方法
     */
    @Pointcut("execution(public * com.yt.service.mybatis.*.save*(..))")
    private void saveMethod() {
    }

    /**
     * aop做日志
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("saveMethod()")
    public Object aroundPointcut(ProceedingJoinPoint pjp) throws Throwable {
        Log log = new Log();
        long start = System.currentTimeMillis();
        Object[] objects = pjp.getArgs();
        for (Object object : objects) {
            if (object instanceof Employee) {
                Employee account = (Employee) object;
                //判断当前实体类使用是否注解
                Table table = account.getClass().getAnnotation(Table.class);
                if (!StringUtils.isEmpty(table)) {
                    //获取注解内容
                    log.setEntityName(table.name());
                }
                log.setCreateDate(new Date());
                log.setActions("保存");
                log.setClassName(account.getClass().getName());
                log.setCreateUser(account.getCreateUser());
                log.setLogInfo(ByteUtil.ObjectToByte(account));
            }
        }

        //获取监控方法得到的返回值
        BaseResult result = (BaseResult) pjp.proceed();
        //如果成功就记录日志,否则记录异常日志
        if (result.isSuccess()) {
            log.setIsSuccess(1);
        }
        long end = System.currentTimeMillis();
        //耗时
        log.setSpendTime(Long.valueOf(end - start).intValue());
        logService.insertSelective(log);
        return result;
    }
}


