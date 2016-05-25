package com.yt.controller;

import com.yt.entity.mybatis.Auth;
import com.yt.entity.mybatis.AuthExample;
import com.yt.service.mybatis.system.AuthService;
import com.yt.util.yt.annotation.system.UnSecurity;
import com.yt.util.yt.annotation.system.UnSession;
import com.yt.util.yt.myutils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    @UnSession
    public String index() {
        return "main/index";
    }
}
