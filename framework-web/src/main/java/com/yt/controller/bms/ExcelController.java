package com.yt.controller.bms;

import com.yt.entity.mybatis.Auth;
import com.yt.util.dhqjr.DateUtil;
import com.yt.util.yt.myutils.ExportExcel;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
public class ExcelController {

    @RequestMapping(value = "/export")
    public void exportExcel(HttpServletResponse response) {
        response.setContentType("octets/stream");
        String fileName = "SettleOrder" + System.currentTimeMillis() + ".xls";
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        // List<SettleOrderExportDto> list = settleOrderService.queryExportList(dto);
        List<Auth> list = new ArrayList<Auth>();
        String[] headers = new String[]{"流水号", "交易时间", "交易人类型", "交易人", "交易类型", "项目编号", "交易金额", "使用红包券", "支付金额", "支付渠道", "使用年化券", "实际年化收益", "银行卡号", "银行名称", "交易状态"};
        ExportExcel<Auth> exportExcel = new ExportExcel<Auth>();
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            exportExcel.exportExcel("交易管理列表", headers, list, out, DateUtil.DATETIMESHOWFORMAT);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
