package com.yt.controller.bms.user;

import com.yt.entity.mybatis.User;
import com.yt.service.mybatis.user.UserService;
import com.yt.util.dhqjr.page.utils.PageResult;
import com.yt.util.dhqjr.page.utils.PageSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.controller.bms.user
 * @date 2016/4/6 0006 11:30
 * @descption: 疯狂的王麻子团队!
 */
@Controller
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 数据表格页面
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "user/list";
    }

    /**
     * 数据表格的接口
     *
     * @param search
     * @param username
     * @param email
     * @param phone
     * @param isLogin
     * @param isEnable
     * @param nikeName
     * @return
     */
    @RequestMapping(value = "/selectByPageList", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<User> selectByPageList(PageSearch search, String username, String email, String phone, Integer isLogin, Integer isEnable, String nikeName) {
        return userService.selectByPageList(search, username, email, phone, isLogin, isEnable, nikeName);
    }
}
