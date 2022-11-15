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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/file")
@Api(tags = "文件处理相关接口")
public class FileController {
    @Value("${photo.path.upload}")
    private String basePath;

    @ApiOperation("上传照片文件")
    @PostMapping("/uploadPhoto")
    @ApiImplicitParam(name = "photoFile", value = "文件", required = true)
    public R<String> uploadPhoto(MultipartFile photoFile) {
        String originalFilename = photoFile.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = LocalDateTime.now().toString() + suffix;
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            photoFile.transferTo(new File(basePath + fileName));
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
