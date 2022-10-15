package cn.cachalot.intelligentattendancesystem.schedule;

import cn.cachalot.intelligentattendancesystem.service.AttendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class CheckAttend {
    @Resource
    AttendService attendService;

    @Scheduled(cron = "0 0 8 * * ? ")
    public void checkFirstSign() {
        attendService.checkFirstSign();
    }

    @Scheduled(cron = "0 0 12 * * ? ")
    public void checkSecondSign() {
        attendService.checkSecondSign();
    }

    @Scheduled(cron = "0 30 13 * * ? ")
    public void checkThirdSign() {
        attendService.checkThirdSign();
    }

    @Scheduled(cron = "0 30 18 * * ? ")
    public void checkFourthSign() {
        attendService.checkFourthSign();
    }

}
