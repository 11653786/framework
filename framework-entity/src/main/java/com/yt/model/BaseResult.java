package com.yt.model;

import com.yt.util.yt.annotation.RestAttribute;

import java.io.Serializable;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.model
 * @date 2016/3/29 0029 10:09
 * @descption: 疯狂的王麻子团队!
 */
public class BaseResult implements Serializable {

    /**
     * @author LiuYiJun
     * @date 2015骞�鏈�0鏃�
     */
    private static final long serialVersionUID = 1768125542229706041L;


    @RestAttribute(name = "返回结果", remark = "成功失败")
    protected boolean success;

    @RestAttribute(name = "返回提示信息", remark = "返回提示信息", notnull = false)
    protected String msg;

    protected Object obj;

    public BaseResult() {

    }


    public BaseResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public BaseResult(boolean success, String msg, Object obj) {
        this.success = success;
        this.msg = msg;
        this.obj = obj;
    }

    public BaseResult(String msg) {
        this.msg = msg;
    }

    public BaseResult(boolean success) {
        this.success = success;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }





    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public static BaseResult fail(String message) {
        return new BaseResult(false, message);
    }

    public static BaseResult success(String message) {
        return new BaseResult(true, message);
    }

    public static BaseResult success(String message, Object obj) {
        return new BaseResult(true, message, obj);
    }


}
