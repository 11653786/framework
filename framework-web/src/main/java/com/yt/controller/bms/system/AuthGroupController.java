package com.yt.controller.bms.system;

import com.yt.base.ResourceBaseController;
import com.yt.entity.mybatis.AuthGroup;
import com.yt.model.BaseResult;
import com.yt.service.mybatis.system.AuthGroupRelationShipService;
import com.yt.service.mybatis.system.AuthGroupService;
import com.yt.util.dhqjr.EmptyUtil;
import com.yt.util.dhqjr.page.utils.PageResult;
import com.yt.util.dhqjr.page.utils.PageSearch;
import com.yt.util.yt.annotation.system.ParentSecurity;
import com.yt.util.yt.annotation.system.ResourceAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 权限组
 *
 * @author zhangsan
 * @version 1.0
 * @package com.yt.controller.bms.system
 * @date 2016/4/19 0019 10:09
 * @descption: 疯狂的王麻子团队!
 */
@Controller
@ResourceAnnotation(resourceGroup = "系统管理")
@RequestMapping(value = "/api/authGroup")
public class AuthGroupController extends ResourceBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AuthGroupController.class);

    @Autowired
    private AuthGroupService authGroupService;
    @Autowired
    private AuthGroupRelationShipService relationShipService;

    /**
     * 数据表格页面
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResourceAnnotation(name = "权限组管理", pName = "系统管理", url = "/api/authGroup/list", remark = "权限组管理", resourceType = "2")
    public String list() {
        return "authgroup/authgrouplist";
    }

    /**
     * 数据表格的接口
     *
     * @param search
     * @return
     */
    @ParentSecurity("/api/authGroup/list")
    @RequestMapping(value = "/selectByPageList", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<AuthGroup> selectByPageList(PageSearch search) {
        return authGroupService.selectByPageList(search);
    }

    /**
     * @param id 用户列表主键,如果没有主键就是添加页面
     * @return
     */
    @ResourceAnnotation(name = "权限组添加", pName = "权限组管理", url = "/api/authGroup/addOrEdit", remark = "权限组添加", resourceType = "2")
    @RequestMapping(value = "/addOrEdit", method = RequestMethod.GET)
    public String addOrEdit(Integer id, Model model) {
        //不为空修改为空保存
        if (!EmptyUtil.isEmpty(id)) {
            AuthGroup authGroup = authGroupService.selectByPrimaryKey(id);
            if (!EmptyUtil.isEmpty(authGroup)) {
                model.addAttribute("authgroup", authGroup);
                model.addAttribute("isUpdate", true);
            }
        } else {
            model.addAttribute("isUpdate", false);
        }
        return "authgroup/authgroupaddoredit";
    }

    /**
     * 保存或者修改
     *
     * @param isUpdate
     * @return
     */
    @ParentSecurity("/api/authGroup/addOrEdit")
    @RequestMapping(value = "/saveAddOrEdit", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult saveAddOrEdit(AuthGroup authGroup, @RequestParam(value = "isUpdate", defaultValue = "false") boolean isUpdate) {
        if (isUpdate) {
            return authGroupService.updateAuthGroupInfo(authGroup);
        } else {
            return authGroupService.saveAuthGroup(authGroup);
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResourceAnnotation(name = "权限组删除", pName = "权限组管理", url = "/api/authGroup/delete", remark = "权限组删除", resourceType = "2")
    @ResponseBody
    public BaseResult delete(Integer id) {
        return authGroupService.deleteAuthGroup(id);
    }

    /**
     * 授权页面
     *
     * @return
     */
    @RequestMapping(value = "/shouquan", method = RequestMethod.GET)
    @ResourceAnnotation(name = "授权", pName = "权限组管理", url = "/api/authGroup/shouquan", remark = "授权", resourceType = "2")
    public String shouquan(Integer id, Model model) {
        AuthGroup authGroup = authGroupService.selectByPrimaryKey(id);
        model.addAttribute("authgroup", authGroup);
        //获取当前权限组的权限
        String ids = relationShipService.getAuthGroupAuth(id);
        model.addAttribute("ids", ids);
        return "authgroup/shouquan";
    }


    @RequestMapping(value = "/shouquan", method = RequestMethod.POST)
    @ResponseBody
    @ParentSecurity("/api/authGroup/shouquan")
    public BaseResult saveShouQuan(@RequestParam(value = "id", defaultValue = "") Integer id, @RequestParam(value = "ids", defaultValue = "") String ids) {
        return relationShipService.grantAuthorization(id, ids);
    }

}
