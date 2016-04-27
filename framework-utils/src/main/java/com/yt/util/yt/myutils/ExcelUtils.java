package com.yt.util.yt.myutils;

import com.yt.util.dhqjr.DateUtil;
import org.apache.poi.ss.formula.functions.T;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.util.yt.myutils
 * @date 2016/4/27 0027 13:30
 * @descption: 疯狂的王麻子团队!
 */
public class ExcelUtils {

    public static void export(HttpServletResponse response, String[] headers, List list) {
        ExportExcel exportExcel = new ExportExcel();
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            exportExcel.exportExcel("交易管理列表", headers, list, out, DateUtil.DATETIMESHOWFORMAT);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            close(out);
        }
    }


    private static void close(ServletOutputStream out) {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

