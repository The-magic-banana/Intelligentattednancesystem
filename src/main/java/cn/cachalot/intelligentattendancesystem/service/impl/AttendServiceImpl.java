package cn.cachalot.intelligentattendancesystem.service.impl;

import cn.cachalot.intelligentattendancesystem.common.BaseContext;
import cn.cachalot.intelligentattendancesystem.common.R;
import cn.cachalot.intelligentattendancesystem.dto.attendDto.GetAttendRes;
import cn.cachalot.intelligentattendancesystem.entity.Attend;
import cn.cachalot.intelligentattendancesystem.entity.User;
import cn.cachalot.intelligentattendancesystem.mapper.AttendMapper;
import cn.cachalot.intelligentattendancesystem.service.AttendService;
import cn.cachalot.intelligentattendancesystem.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<GetAttendRes> getAttendByUserId(Integer pageNum, Integer pageSize, Long userId, Integer days) {
        PageHelper.startPage(pageNum, pageSize);
        List<GetAttendRes> list = attendMapper.getAttendByUserId(userId, days);
        list = list.stream().map((item) -> {
            GetAttendRes res = new GetAttendRes();
            BeanUtils.copyProperties(item, res);
            User user = userService.getUserById(item.getUserId());
            if (user != null) {
                res.setUser(user);
            }
            return res;
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<GetAttendRes> getAttendByDate(Integer pageNum, Integer pageSize, Date date) {
        Integer level = BaseContext.getUser().getLevel();
        PageHelper.startPage(pageNum, pageSize);
        if (level.equals(0)) {
            List<GetAttendRes> list = attendMapper.getAllAttendByDate(date);
            list = list.stream().map((item) -> {
                GetAttendRes res = new GetAttendRes();
                BeanUtils.copyProperties(item, res);
                User user = userService.getUserById(item.getUserId());
                if (user != null) {
                    res.setUser(user);
                }
                return res;
            }).collect(Collectors.toList());
            return list;

        } else if (level.equals(1)) {
            List<GetAttendRes> list = attendMapper.getAttendByDateAndDepartment(BaseContext.getUser().getDepartment()
                    , date);
            list = list.stream().map((item) -> {
                GetAttendRes res = new GetAttendRes();
                BeanUtils.copyProperties(item, res);
                User user = userService.getUserById(item.getUserId());
                if (user != null) {
                    res.setUser(user);
                }
                return res;
            }).collect(Collectors.toList());
            return list;
        } else {
            List<GetAttendRes> list = new ArrayList<>();
            GetAttendRes getAttendRes = attendMapper.getOneAttendByDateAndUserId(BaseContext.getUser().getUserId(),
                    date);
            getAttendRes.setUser(BaseContext.getUser());
            list.add(getAttendRes);
            return list;
        }
    }

    @Override
    public R<Attend> getAttendDetail(Long attendId) {
        List<Long> managedUserId = userService.getManagedUserId();
        Long userId = attendMapper.getUserIdByAttendId(attendId);
        if (managedUserId.contains(userId)) {
            Attend attend = attendMapper.getAttendDetail(attendId);
            return R.success(attend);
        } else {
            return R.error("无权限查看");
        }
    }
}
