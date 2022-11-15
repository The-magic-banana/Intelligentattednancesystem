package cn.cachalot.intelligentattendancesystem;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@ServletComponentScan
@MapperScan("cn.cachalot.intelligentattendancesystem.mapper")
@EnableScheduling //开启定时任务
@EnableAsync
public class IntelligentAttendanceSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntelligentAttendanceSystemApplication.class, args);
        log.info("项目启动成功!");
    }

}
