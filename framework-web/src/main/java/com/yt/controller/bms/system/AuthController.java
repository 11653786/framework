package com.yt.controller.bms.system;

import com.yt.base.ResourceBaseController;
import com.yt.entity.mybatis.Auth;
import com.yt.entity.mybatis.AuthExample;
import com.yt.model.BaseResult;
import com.yt.service.mybatis.system.AuthService;
import com.yt.util.dhqjr.EmptyUtil;
import com.yt.util.dhqjr.page.utils.PageResult;
import com.yt.util.dhqjr.page.utils.PageSearch;
import com.yt.util.sessionutil.EmployeeSessionUtil;
import com.yt.util.yt.annotation.system.ParentSecurity;
import com.yt.util.yt.annotation.system.ResourceAnnotation;
import com.yt.util.yt.annotation.system.UnSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
@ResourceAnnotation(resourceGroup = "系统管理")
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
    @ResourceAnnotation(name = "权限管理", pName = "系统管理", url = "/api/auth/list", remark = "权限管理", resourceType = "2", parentIsRoot = true)
    public String list() {
        return "auth/authlist";
    }

    /**
     * 数据表格的接口
     *
     * @param search
     * @return
     */
    @ParentSecurity("/api/auth/list")
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
    @ResourceAnnotation(name = "添加权限", pName = "权限管理", url = "/api/auth/addOrEdit", remark = "添加权限", resourceType = "2")
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
    @ParentSecurity("/api/auth/saveAddOrEdit")
    @ResponseBody
    public BaseResult saveAddOrEdit(Auth auth, Integer parentId, @RequestParam(value = "isUpdate", defaultValue = "false") boolean isUpdate) {
        if (parentId != null) {
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
     * @return
     */
    @RequestMapping(value = "/getAllTree", method = RequestMethod.POST)
    @UnSecurity
    @ResponseBody
    public List<Auth> getAllTree() {
        AuthExample example = new AuthExample();
        return authService.selectByExample(example);
    }

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResourceAnnotation(name = "删除权限", pName = "权限管理", url = "/api/auth/delete", remark = "删除权限", resourceType = "2")
    @ResponseBody
    public BaseResult delete(Integer id) {
        return authService.deleteAuth(id);
    }


    /**
     * 获取登录用户的session
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/getLoginAuth")
    public List<Auth> getLoginAuth(HttpSession session) {
        List<Auth> list = EmployeeSessionUtil.getEmployeeAuth(session);
        return list;
    }

}
