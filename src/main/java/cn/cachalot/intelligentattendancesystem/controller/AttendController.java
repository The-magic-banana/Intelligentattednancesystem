package cn.cachalot.intelligentattendancesystem.controller;

import cn.cachalot.intelligentattendancesystem.common.BaseContext;
import cn.cachalot.intelligentattendancesystem.common.R;
import cn.cachalot.intelligentattendancesystem.dto.attendDto.GetAttendByDatePara;
import cn.cachalot.intelligentattendancesystem.dto.attendDto.GetAttendByUserPara;
import cn.cachalot.intelligentattendancesystem.entity.UserAttend;
import cn.cachalot.intelligentattendancesystem.service.AttendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/attend")
@Api(tags = "考勤相关接口")
public class AttendController {
    @Resource
    AttendService attendService;

    @ApiOperation("根据员工获取考勤信息列表")
    @PostMapping("/getAttendByUser")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "第几页", required = true), @ApiImplicitParam(name = "pageSize", value = "每一页有多少数据", required = true)})
    public R<PageInfo<UserAttend>> getAttendByUser(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestBody GetAttendByUserPara getAttendByUserPara) {
        List<UserAttend> list = attendService.getAttendByUserId(pageNum, pageSize, getAttendByUserPara.getUserId(), getAttendByUserPara.getDays());
        PageInfo<UserAttend> pageInfo = new PageInfo<>(list);
        return R.success(pageInfo);
    }

    @ApiOperation("根据日期获取考勤信息列表")
    @PostMapping("/getAttendByDate")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "第几页", required = true), @ApiImplicitParam(name = "pageSize", value = "每一页有多少数据", required = true)})
    public R<PageInfo<UserAttend>> getAttendByDate(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestBody GetAttendByDatePara getAttendByDatePara) {
        List<UserAttend> list = attendService.getAttendByDate(pageNum, pageSize, BaseContext.getId(), getAttendByDatePara.getDate());
        PageInfo<UserAttend> pageInfo = new PageInfo<>(list);
        return R.success(pageInfo);
    }


}
