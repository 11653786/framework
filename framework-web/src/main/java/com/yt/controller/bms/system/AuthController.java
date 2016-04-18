package com.yt.controller.bms.system;

import com.yt.base.ResourceBaseController;
import com.yt.entity.mybatis.Auth;
import com.yt.entity.mybatis.AuthExample;
import com.yt.model.BaseResult;
import com.yt.service.mybatis.system.AuthService;
import com.yt.util.dhqjr.EmptyUtil;
import com.yt.util.dhqjr.page.utils.PageResult;
import com.yt.util.dhqjr.page.utils.PageSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.controller.bms.system
 * @date 2016/4/18 0018 12:08
 * @descption: 疯狂的王麻子团队!
 */
@Controller
@RequestMapping(value = "/api/auth")
public class AuthController extends ResourceBaseController {


    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    /**
     * 数据表格页面
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "auth/authlist";
    }

    /**
     * 数据表格的接口
     *
     * @param search
     * @return
     */
    @RequestMapping(value = "/selectByPageList", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<Auth> selectByPageList(PageSearch search) {
        return authService.selectByPageList(search);
    }

    /**
     * @param id 用户列表主键,如果没有主键就是添加页面
     * @return
     */
    @RequestMapping(value = "/addOrEdit", method = RequestMethod.GET)
    public String addOrEdit(Integer id, Model model) {
        //不为空修改为空保存
        if (!EmptyUtil.isEmpty(id)) {
            Auth auth = authService.selectByPrimaryKey(id);
            if (!EmptyUtil.isEmpty(auth)) {
                model.addAttribute("auth", auth);
                model.addAttribute("isUpdate", true);
            }
        } else {
            model.addAttribute("isUpdate", false);
        }
        return "auth/authaddoredit";
    }


    /**
     * 保存或者修改
     *
     * @param isUpdate
     * @return
     */
    @RequestMapping(value = "/saveAddOrEdit", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult saveAddOrEdit(Auth auth, Integer parentId, @RequestParam(value = "isUpdate", defaultValue = "false") boolean isUpdate) {
        if(parentId!=null){
            auth.set_parentId(parentId);
        }
        if (isUpdate) {
            return authService.updateAuth(auth);
        } else {
            return authService.saveAuth(auth);
        }
    }

    /**
     * 保存或者修改
     *
     * @param isUpdate
     * @return
     */
    @RequestMapping(value = "/getAllTree", method = RequestMethod.POST)
    @ResponseBody
    public List<Auth> getAllTree() {
        AuthExample example = new AuthExample();
        return authService.selectByExample(example);
    }
}
