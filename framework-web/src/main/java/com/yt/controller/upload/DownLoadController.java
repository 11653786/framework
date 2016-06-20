package com.yt.controller.upload;

import com.yt.util.yt.annotation.system.BmsUnSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 断电续传下载实例
 *
 * @author zhangsan
 * @version 1.0
 * @package com.yt.controller.upload
 * @date 2016/6/20 0020 15:03
 * @descption: 疯狂的王麻子团队!
 */
@Controller
@RequestMapping(value = "/down")
public class DownLoadController {

    @RequestMapping(value = "download")
    @BmsUnSession
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //你可以使用你服务器上的文件及其路径
        String s = "D:/tools.rar";
        //String s = "e://tree.mdb";
        //经测试 RandomAccessFile 也可以实现,有兴趣可将注释去掉,并注释掉 FileInputStream 版本的语句
        //<a href="http://lib.csdn.net/base/javaee" class='replace_word' title="Java EE知识库" target='_blank' style='color:#df3434; font-weight:bold;'>Java</a>.io.RandomAccessFile raf = new java.io.RandomAccessFile(s,"r");
        java.io.File f = new java.io.File(s);
        java.io.FileInputStream fis = new java.io.FileInputStream(f);
        response.reset();
        response.setHeader("Server", "playyuer@Microshaoft.com");
        //告诉客户端允许断点续传多线程连接下载
        //响应的格式是:
        //Accept-Ranges: bytes
        response.setHeader("Accept-Ranges", "bytes");
        long p = 0;
        long l = 0;
        l = f.length();
        //如果是第一次下,还没有断点续传,状态是默认的 200,无需显式设置
        //响应的格式是:
        //HTTP/1.1 200 OK
        if (request.getHeader("Range") != null) { //客户端请求的下载的文件块的开始字节

            //如果是下载文件的范围而不是全部,向客户端声明支持并开始文件块下载
            //要设置状态
            //响应的格式是:
            //HTTP/1.1 206 Partial Content
            response.setStatus(javax.servlet.http.HttpServletResponse.SC_PARTIAL_CONTENT);//206

            //从请求中得到开始的字节
            //请求的格式是:
            //Range: bytes=[文件块的开始字节]-
            p = Long.parseLong(request.getHeader("Range").replaceAll("bytes=", "").replaceAll("-", ""));
        }

        //下载的文件(或块)长度
        //响应的格式是:
        //Content-Length: [文件的总大小] - [客户端请求的下载的文件块的开始字节]
        response.setHeader("Content-Length", new Long(l - p).toString());

        if (p != 0) {
            //响应的格式是:
            //Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小]
            response.setHeader("Content-Range", "bytes " + new Long(p).toString() + "-" + new Long(l - 1).toString() + "/" + new Long(l).toString());
        }

        response.setContentType("application/octet-stream");

        //为客户端下载指定默认的下载文件名称
        //响应的格式是:
        //Content-Disposition: attachment;filename="[文件名]"
        //response.setHeader("Content-Disposition", "attachment;filename=/"" + s.substring(s.lastIndexOf("//") + 1) + "/""); //经测试 RandomAccessFile 也可以实现,有兴趣可将注释去掉,并注释掉 FileInputStream 版本的语句
        response.setHeader("Content-Disposition", "attachment;filename=/" + f.getName() + " / ");

        //raf.seek(p);
        fis.skip(p);

        byte[] b = new byte[1024];
        int i;
        while ((i = fis.read(b)) != -1) {
            response.getOutputStream().write(b, 0, i);
        }
        //raf.close();//经测试 RandomAccessFile 也可以实现,有兴趣可将注释去掉,并注释掉 FileInputStream 版本的语句
        fis.close();
    }
}
