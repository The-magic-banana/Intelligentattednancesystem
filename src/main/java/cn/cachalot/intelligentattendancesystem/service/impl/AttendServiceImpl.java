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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Map> mapList = new ArrayList<>();
        Long tempId;
        for (int i = 0; i < userIds.size(); i++) {
            Map map = new HashMap();
            tempId = IdWorker.getId();
            map.put("userId", userIds.get(i));
            map.put("attendId", tempId);
            attendIds.add(tempId);
            mapList.add(map);
        }
        Date date = new Date(System.currentTimeMillis());
        attendMapper.creatAttend(attendIds);
        attendMapper.creatUserAttend(date, mapList);

    }
}
