package com.yt.controller.lunce;

import com.yt.base.BaseAction;
import com.yt.service.search.lunce.LunceService;
import com.yt.util.yt.annotation.system.UnSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * lunce全站搜索
 *
 * @author zhangsan
 * @version 1.0
 * @package com.yt.controller.lunce
 * @date 2016/5/24 0024 9:55
 * @descption: 疯狂的王麻子团队!
 */
@Controller
@RequestMapping(value = "/lunce")
public class LunceController extends BaseAction {

    @Autowired
    private LunceService lunceService;
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    //lunce所在文件位置
    private String searchDir = "E:/lunceDocument";

    /**
     * 创建索引
     *
     * @return
     */
    @UnSession
    @RequestMapping(value = "/createIndex")
    @ResponseBody
    public String createIndex() {
        try {
            conn = JdbcUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "select auth_name,id from t_auth";
            //创建lunce索引的文件夹
            //获取结果
            rs = stmt.executeQuery(sql);
            //创建索引
            lunceService.createIndex(rs, searchDir, "id", "auth_name");
        } catch (Exception e) {
            e.printStackTrace();
            return "no: " + e.getMessage();
        }
        return "ok...";
    }

    /**
     * 搜索
     *
     * @return
     */
    @RequestMapping(value = "/search")
    @UnSession
    public void search() {
        lunceService.createSearch(searchDir, "auth_name", "员工");
    }

}

