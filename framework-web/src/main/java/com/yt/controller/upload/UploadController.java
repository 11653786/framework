package com.yt.controller.upload;

import com.yt.base.BaseAction;
import com.yt.util.dhqjr.EmptyUtil;
import com.yt.util.yt.annotation.system.BmsUnSession;
import com.yt.util.yt.myutils.ValidUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.controller.upload
 * @date 2016/5/24 0024 10:16
 * @descption: 疯狂的王麻子团队!
 */
@Controller
public class UploadController extends BaseAction {


    /**
     * 上传图片页面
     *
     * @return
     */
    @BmsUnSession
    @RequestMapping(value = "/upload")
    public String exports() {
        return "upload/upload";
    }

    /**
     * 图片上传,远端服务器
     *
     * @return
     * @throws java.io.IOException
     */
    @BmsUnSession
    @RequestMapping(value = "/uploadPic")
    @ResponseBody
    public String createIndex(@RequestParam(value = "uploadFile", required = false) MultipartFile multipartFile,
                              HttpServletRequest request) {
        try {
            if (EmptyUtil.isNotEmpty(multipartFile)
                    && multipartFile.getSize() > 0) {
                //验证是否是图片
                if (ValidUtils.isPic(multipartFile.getOriginalFilename())) {
                }
            } else {
            }
        } catch (Exception e) {
        }
        return "upload...ok...";
    }


}
