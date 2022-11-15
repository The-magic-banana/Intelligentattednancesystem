package cn.cachalot.intelligentattendancesystem.controller;

import cn.cachalot.intelligentattendancesystem.common.R;
import cn.cachalot.intelligentattendancesystem.dto.file.FilePara;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Base64;


@RestController
@RequestMapping("/file")
@Api(tags = "文件数据相关接口")
public class FileController {
    @Value("${data.path.upload}")
    private String uploadPath;

    @ApiOperation("上传照片文件")
    @PostMapping("/uploadPhoto")
    public R<String> uploadPhoto(@RequestBody FilePara filePara) {
        String file = filePara.getFile();
        String fileName = LocalDateTime.now().toString() + ".jpg";
        File dir = new File(uploadPath);
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
            OutputStream out = new FileOutputStream(uploadPath.concat(fileName));
            out.write(b);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success("上传成功!");
    }
    @ApiOperation("上传指纹数据")
    @PostMapping("/uploadFingerPrint")
    public R<String> uploadFingerPrint(@RequestBody FilePara filePara) {
        String file = filePara.getFile();
        String fileName = LocalDateTime.now().toString() + ".data";
        File dir = new File(uploadPath);
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
            OutputStream out = new FileOutputStream(uploadPath.concat(fileName));
            out.write(b);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success("上传成功!");
    }
    @ApiOperation("上传声音数据")
    @PostMapping("/uploadSound")
    public R<String> uploadSound(@RequestBody FilePara filePara) {
        String file = filePara.getFile();
        String fileName = LocalDateTime.now().toString() + ".mp3";
        File dir = new File(uploadPath);
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
            OutputStream out = new FileOutputStream(uploadPath.concat(fileName));
            out.write(b);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success("上传成功!");
    }


}
