package com.yt.aop;

import com.yt.entity.mybatis.Employee;
import com.yt.entity.mybatis.Log;
import com.yt.entity.mybatis.User;
import com.yt.model.BaseResult;
import com.yt.service.mybatis.system.LogService;
import com.yt.util.dhqjr.ByteUtil;
import com.yt.util.yt.annotation.Table;
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
    @Pointcut("execution(public * com.yt.service.mybatis.user.UserService.saveUser(..))")
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
        long start = System.currentTimeMillis();
        //获取传入的参数
        Object[] objects = pjp.getArgs();
        Object object = objects[0];
        //获取方法名
        String methodName = pjp.getSignature().getName();
        //保存修改删除
        String actions = getActions(methodName);
        //判断当前实体类使用是否注解
        Table table = object.getClass().getAnnotation(Table.class);

        String tableName = table != null && !StringUtils.isEmpty(table.name()) ? table.name() : object.getClass().getSimpleName();

        //获取监控方法得到的返回值
        BaseResult result = (BaseResult) pjp.proceed();
        Integer sendTime = Long.valueOf(System.currentTimeMillis() - start).intValue();
        if (result.isSuccess()) {
            //String className, Integer createUser, Date createDate, Integer isSuccess, String entityName, String actions, byte[] logInfo, Integer spendTime
            Log log = new Log(object.getClass().getName(), 0, new Date(), 1, tableName, actions, ByteUtil.ObjectToByte(object), sendTime);
            logService.insertSelective(log);
        } else {
            //保存异常信息信息
        }

        return result;
    }

    /**
     * 获取日志标中需要的信息
     *
     * @param methodName
     * @return
     */
    private String getActions(String methodName) {
        String actions = "";
        if (methodName.equalsIgnoreCase("save")) {
            actions = "保存";
        } else if (methodName.equalsIgnoreCase("update")) {
            actions = "修改";
        } else if (methodName.equalsIgnoreCase("delete")) {
            actions = "删除";
        }
        return actions;
    }
}


