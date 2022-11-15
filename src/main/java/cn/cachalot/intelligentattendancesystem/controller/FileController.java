package cn.cachalot.intelligentattendancesystem.controller;

import cn.cachalot.intelligentattendancesystem.common.BaseContext;
import cn.cachalot.intelligentattendancesystem.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Base64;


@RestController
@RequestMapping("/file")
@Api(tags = "文件处理相关接口")
public class FileController {
    @Value("${photo.path.upload}")
    private String basePath;

    @ApiOperation("上传照片文件")
    @PostMapping("/uploadPhoto")
    @ApiImplicitParam(name = "file", value = "文件", required = true)
    public R<String> uploadPhoto(String file) {
        String fileName = LocalDateTime.now().toString() + ".jpg";
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            file = file.substring(file.indexOf(",", 1) + 1, file.length());
            byte[] b = decoder.decode(file);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(basePath.concat(fileName));
            out.write(b);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success("上传成功!");
    }

    //    @GetMapping("/download")
    //    public void download(String name, HttpServletResponse response) {
    //        try {
    //            FileInputStream fileInputStream = new FileInputStream(basePath + name);
    //            ServletOutputStream outputStream = response.getOutputStream();
    //            response.setContentType("image/jpeg");
    //            byte[] bytes = new byte[1024];
    //            //            while (fileInputStream.read(bytes) != -1) {
    //            //                outputStream.write(bytes);
    //            //            }
    //            int len = 0;
    //            while ((len = fileInputStream.read(bytes)) != -1) {
    //                outputStream.write(bytes, 0, len);
    //                outputStream.flush();
    //            }
    //            outputStream.close();
    //            fileInputStream.close();
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //
    //    }

}
