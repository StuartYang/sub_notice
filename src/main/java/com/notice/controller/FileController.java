package com.notice.controller;

import com.notice.config.UploadConfig;
import com.notice.util.R;
import com.notice.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/file/op")
public class FileController {

    @Autowired
    private UploadConfig uploadConfig;

    @RequestMapping("/index")
    public String index() {
        return "file";
    }


    /*
    上传
     */
    @ResponseBody
    @RequestMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        try {
            FileUtil.uploadFile(file.getBytes(), uploadConfig.getUploadPath(), fileName);
        } catch (Exception e) {
            return R.error();
        }
        return R.ok().put("fileName", fileName);
    }

    /**
     * 下载
     *
     * @param response
     */

    /**
     * 下载
     *
     * @param response
     * @param request
     */
    @RequestMapping("/download")
    public void downloadFile(HttpServletResponse response, HttpServletRequest request) {
        String fileName = request.getParameter("fileName");
        if (fileName != null) {
            //设置文件路径
            File file = new File(uploadConfig.getUploadPath() + fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
