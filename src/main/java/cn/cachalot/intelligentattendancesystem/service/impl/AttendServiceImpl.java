package cn.cachalot.intelligentattendancesystem.service.impl;

import cn.cachalot.intelligentattendancesystem.common.BaseContext;
import cn.cachalot.intelligentattendancesystem.common.R;
import cn.cachalot.intelligentattendancesystem.entity.Attend;
import cn.cachalot.intelligentattendancesystem.entity.User;
import cn.cachalot.intelligentattendancesystem.entity.UserAttend;
import cn.cachalot.intelligentattendancesystem.mapper.AttendMapper;
import cn.cachalot.intelligentattendancesystem.service.AttendService;
import cn.cachalot.intelligentattendancesystem.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
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

    @Override
    public void checkFirstSign() {
        Date date = new Date(System.currentTimeMillis());
        attendMapper.checkFirstSign(date);
    }

    @Override
    public void checkSecondSign() {
        Date date = new Date(System.currentTimeMillis());
        attendMapper.checkSecondSign(date);
    }

    @Override
    public void checkThirdSign() {
        Date date = new Date(System.currentTimeMillis());
        attendMapper.checkThirdSign(date);
    }

    @Override
    public void checkFourthSign() {
        Date date = new Date(System.currentTimeMillis());
        attendMapper.checkFourthSign(date);
    }

    @Override
    public List<UserAttend> getAttendByUserId(Integer pageNum, Integer pageSize, Long userId, Integer days) {
        PageHelper.startPage(pageNum, pageSize);
        return attendMapper.getAttendByUserId(userId, days);
    }

    @Override
    public List<UserAttend> getAttendByDate(Integer pageNum, Integer pageSize, Long id, Date date) {
        Integer level = userService.getUserLevel(id);
        PageHelper.startPage(pageNum, pageSize);
        if (level.equals(0)) {
            return attendMapper.getAllAttendByDate(date);
        } else if (level.equals(1)) {
            return attendMapper.getAttendByDateAndDepartment(userService.getDepartmentById(id), date);
        } else {
            List<UserAttend> list = new ArrayList<>();
            list.add(attendMapper.getOneAttendByDateAndUserId(id, date));
            return list;
        }
    }

    @Override
    public R<Attend> getAttendDetail(Long attendId) {
        List<Long> managedUserId = userService.getManagedUserId(BaseContext.getId());
        Long userId = attendMapper.getUserIdByAttendId(attendId);
        if (managedUserId.contains(userId)) {
            Attend attend = attendMapper.getAttendDetail(attendId);
            return R.success(attend);
        } else {
            return R.error("无权限查看");
        }
    }
}
