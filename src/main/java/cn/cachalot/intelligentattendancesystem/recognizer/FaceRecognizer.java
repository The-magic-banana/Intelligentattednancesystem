package cn.cachalot.intelligentattendancesystem.recognizer;

import cn.cachalot.intelligentattendancesystem.service.AttendService;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

@Component
public class FaceRecognizer implements CommandLineRunner {
    @Value("${photo.path.upload}")
    private String uploadPath;
    @Value("${photo.path.data}")
    private String dataPath;
    @Value("${photo.path.local}")
    private String localPath;

    @Resource
    private AttendService attendService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Process proc;
        proc = Runtime.getRuntime().exec("python face.py");
        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = null;
        while (true) {
            Thread.sleep(50);
            line = in.readLine();
            if (line != null) {
                File dir = new File(dataPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                String userId = line.substring(0, line.indexOf("@"));
                String time = line.substring(line.indexOf("@") + 1);
                LocalDateTime dateTime = LocalDateTime.parse(time);
                long dataId = IdWorker.getId();
                File startFile = new File(uploadPath.concat(time).concat(".jpg"));
                String data = dataPath.concat(Long.toString(dataId)).concat(".jpg");
                File endFile = new File(data);
                startFile.renameTo(endFile);
                attendService.sign(0, Long.parseLong(userId), 2, null, data);
            }
        }
    }
}
