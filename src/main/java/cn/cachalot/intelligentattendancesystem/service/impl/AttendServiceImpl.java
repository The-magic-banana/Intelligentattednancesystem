package cn.cachalot.intelligentattendancesystem.service.impl;

import cn.cachalot.intelligentattendancesystem.common.BaseContext;
import cn.cachalot.intelligentattendancesystem.common.R;
import cn.cachalot.intelligentattendancesystem.dto.attendDto.GetAttendRes;
import cn.cachalot.intelligentattendancesystem.entity.Attend;
import cn.cachalot.intelligentattendancesystem.entity.User;
import cn.cachalot.intelligentattendancesystem.entity.UserAttend;
import cn.cachalot.intelligentattendancesystem.mapper.AttendMapper;
import cn.cachalot.intelligentattendancesystem.service.AttendService;
import cn.cachalot.intelligentattendancesystem.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    AttendService attendService;

    @Resource
    AttendMapper attendMapper;

    @Override
    @Transactional//事务
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
        //        Date date = new Date(System.currentTimeMillis());
        LocalDate localDate = LocalDate.now();
        attendMapper.creatAttend(attendIds);
        //        attendMapper.creatUserAttend(date, mapList);
        attendMapper.creatUserAttend(localDate, mapList);

    }

    @Override
    @Transactional
    //type==1主动,==0被动
    public R<String> sign(Integer type, Long userId, Integer way, String place, String data) {
        LocalDateTime localDateTime = LocalDateTime.now();
        UserAttend userAttend = attendService.getAttendByUserIdAndDate(userId, localDateTime.toLocalDate());
        if (userAttend.getStatus() == 2 || userAttend.getStatus() == 3) {
            return R.error("今日不用签到!");
        }
        Long attendId = userAttend.getAttendId();
        Attend attend = attendMapper.getAttendDetail(attendId);
        LocalTime localTime = localDateTime.toLocalTime();
        LocalTime firstSignInTimeStart = LocalTime.of(8, 0);
        LocalTime firstSignInTimeEnd = LocalTime.of(8, 30);
        LocalTime firstSignOutTimeStart = LocalTime.of(11, 30);
        LocalTime firstSignOutTimeEnd = LocalTime.of(12, 0);
        LocalTime midTime = LocalTime.of(12, 30);
        LocalTime secondSignInTimeStart = LocalTime.of(13, 30);
        LocalTime secondSignInTimeEnd = LocalTime.of(14, 0);
        LocalTime secondSignOutTimeStart = LocalTime.of(18, 0);
        LocalTime secondSignOutTimeEnd = LocalTime.of(18, 30);
        if (localTime.isBefore(firstSignInTimeStart)) {
            attendMapper.sign(attendId, 1);
            attendMapper.signFirst(attendId, localDateTime, place, way, data);
            return R.success("签到成功!");
        } else if (localTime.isBefore(firstSignInTimeEnd)) {
            if (attend.getFirstTime() == null) {
                attendMapper.sign(attendId, 5);
                attendMapper.signFirst(attendId, localDateTime, place, way, data);
                return R.success("你已被记录为迟到!");
            }
        } else if (localTime.isBefore(firstSignOutTimeStart)) {
            if (attend.getFirstTime() == null) {
                attendMapper.sign(attendId, 4);
                attendMapper.signFirst(attendId, localDateTime, place, way, data);
                return R.success("你已被记录为旷工!");
            }
        } else if (localTime.isBefore(firstSignOutTimeEnd)) {
            if (attend.getSecondTime() == null && type != 1) {
                if (userAttend.getStatus() == 1) {
                    attendMapper.sign(attendId, 5);
                }
                attendMapper.signSecond(attendId, localDateTime, place, way, data);
                return R.success("你已被记录为早退!");
            }
        } else if (localTime.isBefore(midTime)) {
            if (attend.getSecondTime() == null && type != 1) {
                attendMapper.signSecond(attendId, localDateTime, place, way, data);
                return R.success("签退成功!");
            }
        } else if (localTime.isBefore(secondSignInTimeStart)) {
            if (attend.getThirdTime() == null) {
                attendMapper.signThird(attendId, localDateTime, place, way, data);
                return R.success("签到成功!");
            }
        } else if (localTime.isBefore(secondSignInTimeEnd)) {
            if (attend.getThirdTime() == null) {
                if (userAttend.getStatus() == 1) {
                    attendMapper.sign(attendId, 5);
                }
                attendMapper.signThird(attendId, localDateTime, place, way, data);
                return R.success("你已被记录为迟到!");
            }
        } else if (localTime.isBefore(secondSignOutTimeStart)) {
            if (attend.getThirdTime() == null) {
                if (userAttend.getStatus() != 4) {
                    attendMapper.sign(attendId, 4);
                }
                attendMapper.signThird(attendId, localDateTime, place, way, data);
                return R.success("你已被记录为旷工!");
            }
        } else if (localTime.isBefore(secondSignOutTimeEnd)) {
            if (attend.getFourthTime() == null && type != 1) {
                if (userAttend.getStatus() == 1) {
                    attendMapper.sign(attendId, 5);
                }
                attendMapper.signFourth(attendId, localDateTime, place, way, data);
                return R.success("你已被记录为早退!");
            }
        } else {
            if (attend.getFourthTime() == null && type != 1) {
                attendMapper.signFourth(attendId, localDateTime, place, way, data);
                return R.success("签退成功!");
            }
        }
        return R.error("签到失败!");
    }

    @Override
    public void checkFirstSign() {
        LocalDate date = LocalDate.now();
        attendMapper.checkFirstSign(date);
    }

    //    @Override
    //    public void checkSecondSign() {
    //        LocalDate date = LocalDate.now();
    //        attendMapper.checkSecondSign(date);
    //    }

    @Override
    public void checkThirdSign() {
        LocalDate date = LocalDate.now();
        attendMapper.checkThirdSign(date);
    }

    //    @Override
    //    public void checkFourthSign() {
    //        LocalDate date = LocalDate.now();
    //        attendMapper.checkFourthSign(date);
    //    }

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
    public List<GetAttendRes> getAttendByDate(Integer pageNum, Integer pageSize, LocalDate date) {
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
            List<GetAttendRes> list =
                    attendMapper.getAttendByDateAndDepartment(BaseContext.getUser().getDepartment(), date);
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
            GetAttendRes getAttendRes =
                    attendMapper.getOneAttendByDateAndUserId(BaseContext.getUser().getUserId(), date);
            getAttendRes.setUser(BaseContext.getUser());
            list.add(getAttendRes);
            return list;
        }
    }

    @Override
    public UserAttend getAttendByUserIdAndDate(Long userId, LocalDate date) {
        return attendMapper.getOneAttendByDateAndUserId(userId, date);
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
