package com.yt.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.shiro
 * @date 2016/3/28 0028 15:44
 * @descption: 疯狂的王麻子团队!
 */
public class ShiroUserPasswordToken extends UsernamePasswordToken {

    /**
     * 登录类型,1:前台登录,2后台登录。
     */
    private String loginType;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机
     */
    private String mobile;

    /**
     * 前后台登录都在这里,甚至可以限制ip登录
     *
     * @param username
     * @param password
     * @param rememberMe
     * @param host
     * @param loginType
     * @param email
     * @param mobile
     */
    public ShiroUserPasswordToken(String username, String password, boolean rememberMe, String host, String loginType, String email, String mobile) {
        super(username, password, rememberMe, host);
        this.loginType = loginType;
        this.email = email;
        this.mobile = mobile;
    }

    public ShiroUserPasswordToken(String username, char[] password, boolean rememberMe) {
        super(username, password, rememberMe);
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
