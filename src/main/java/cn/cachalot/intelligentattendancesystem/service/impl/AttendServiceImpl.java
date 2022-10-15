package cn.cachalot.intelligentattendancesystem.service.impl;

import cn.cachalot.intelligentattendancesystem.mapper.AttendMapper;
import cn.cachalot.intelligentattendancesystem.service.AttendService;
import cn.cachalot.intelligentattendancesystem.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AttendServiceImpl implements AttendService {
    @Resource
    UserService userService;

    @Resource
    AttendMapper attendMapper;

    @Override

    public void creatAttend() {
        List<Long> userIds = userService.getAllUserId();
        List<Long> attendIds = new ArrayList<>();
        for (int i = 0; i < userIds.size(); i++) {
            attendIds.add(IdWorker.getId());
        }
        Date date = new Date(System.currentTimeMillis());
        attendMapper.creatAttend(attendIds);
//        attendMapper.creatUserAttend(userIds, date, attendIds);

    }
}
