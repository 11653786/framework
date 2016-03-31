package com.yt.model;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.model
 * @date 2016/3/29 0029 10:11
 * @descption: 疯狂的王麻子团队!
 */
public class BaseUrlResult extends BaseResult {

    //返回要跳转的url
    private String resultUrl;

    public BaseUrlResult(boolean success, String resultUrl) {
        super(success);
        this.resultUrl = resultUrl;
    }

    public BaseUrlResult(boolean success, String msg, String resultUrl) {
        super(success, msg);
        this.resultUrl = resultUrl;
    }

    public String getResultUrl() {
        return resultUrl;
    }

    public void setResultUrl(String resultUrl) {
        this.resultUrl = resultUrl;
    }
}
