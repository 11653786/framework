package com.yt.controller;

import com.yt.service.mybatis.system.AuthService;
import com.yt.util.yt.annotation.system.BmsUnSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前台首页
 *
 * @author zhangsan
 * @version 1.0
 * @package com.yt.controller
 * @date 2016/5/25 0025 10:07
 * @descption: 疯狂的王麻子团队!
 */
@Controller
@RequestMapping(value = "/main")
public class MainController {

    @Autowired
    private AuthService authService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "index")
    @BmsUnSession
    public String index() {
        return "main/index";
    }
}
