package cn.cachalot.intelligentattendancesystem.schedule;

import cn.cachalot.intelligentattendancesystem.mapper.UserMapper;
import cn.cachalot.intelligentattendancesystem.service.AttendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class CreatAttend {
    @Resource
    AttendService attendService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void creatAttend() {
        attendService.creatAttend();
    }

//    @Scheduled(cron = "*/15 * * * * ?")
//    public void creatAttend() {
//        attendService.creatAttend();
//    }
}
