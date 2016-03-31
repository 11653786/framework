package com.yt.shiro;

import org.apache.shiro.authc.SimpleAuthenticationInfo;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.shiro
 * @date 2016/3/28 0028 15:52
 * @descption: 疯狂的王麻子团队!
 */
public class ShiroAuthenticationInfo extends SimpleAuthenticationInfo {

    private String mobile;

    private String loginType;

    private String email;

    private String nikeName;

    public ShiroAuthenticationInfo(Object principal, Object credentials, String realmName) {
        super(principal, credentials, realmName);
    }

    public ShiroAuthenticationInfo(Object principal, Object credentials, String realmName, String mobile, String loginType, String email, String nikeName) {
        super(principal, credentials, realmName);
        this.mobile = mobile;
        this.loginType = loginType;
        this.email = email;
        this.nikeName = nikeName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }
}
