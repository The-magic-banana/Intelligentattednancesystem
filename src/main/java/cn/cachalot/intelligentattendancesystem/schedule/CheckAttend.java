package cn.cachalot.intelligentattendancesystem.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CheckAttend {
    @Scheduled(cron = "0 0 8 * * ? ")
    public void firstSign() {

    }

    @Scheduled(cron = "0 0 12 * * ? ")
    public void secondSign() {

    }

    @Scheduled(cron = "0 30 13 * * ? ")
    public void thirdSign() {

    }

    @Scheduled(cron = "0 30 18 * * ? ")
    public void fourthSign() {

    }

}
