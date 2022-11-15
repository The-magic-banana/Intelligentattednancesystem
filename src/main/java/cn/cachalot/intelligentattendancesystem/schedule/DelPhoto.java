package cn.cachalot.intelligentattendancesystem.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;

@Slf4j
@Component
public class DelPhoto {

    @Value("${photo.path.upload}")
    private String uploadPath;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void creatAttend() {
        File file = new File(uploadPath);
        if (!file.exists()) {
            return;
        }
        String[] fileNames = file.list();
        if (fileNames == null) {
            return;
        }
        for (String child : fileNames) {
            String name = child.substring(0, child.lastIndexOf("."));
            LocalDateTime time = LocalDateTime.parse(name);
            LocalDateTime now = LocalDateTime.now();
            now = now.minusMinutes(5);
            if (time.isBefore(now)) {
                File childFile = new File(uploadPath.concat(child));
                if (childFile.exists()) {
                    childFile.delete();
                }
            }
        }

    }
}
