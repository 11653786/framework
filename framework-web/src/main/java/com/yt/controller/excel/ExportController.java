package com.yt.controller.lunce;

import com.yt.base.BaseAction;
import com.yt.entity.mybatis.Auth;
import com.yt.entity.mybatis.AuthExample;
import com.yt.service.mybatis.system.AuthService;
import com.yt.util.yt.annotation.system.UnSession;
import com.yt.util.yt.myutils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * export
 *
 * @author zhangsan
 * @version 1.0
 * @package com.yt.controller.lunce
 * @date 2016/5/24 0024 9:55
 * @descption: 疯狂的王麻子团队!
 */
@Controller
public class ExportController extends BaseAction {

    @Autowired
    private AuthService authService;

    /**
     * 导出excel
     *
     * @return
     */
    @UnSession
    @RequestMapping(value = "/export")
    public void exports(HttpServletResponse response) {
        response.setContentType("octets/stream");
        String fileName = "excel导出" + System.currentTimeMillis() + ".xls";
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        AuthExample example = new AuthExample();
        List<Auth> list = authService.selectByExample(example);
        String[] headers = new String[]{"id", "权限名称", "权限类型", "父id", "是否可用", "权限url", "创建人", "创建日期", "修改人", "修改时间", "描述"};
        ExcelUtils.export(response, headers, list);
    }

}

