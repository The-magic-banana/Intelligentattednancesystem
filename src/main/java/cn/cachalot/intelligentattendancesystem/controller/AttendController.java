package cn.cachalot.intelligentattendancesystem.controller;

import cn.cachalot.intelligentattendancesystem.common.R;
import cn.cachalot.intelligentattendancesystem.dto.attendDto.GetSingleAttendPara;
import cn.cachalot.intelligentattendancesystem.entity.User;
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
import java.sql.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/attend")
@Api(tags = "考勤相关接口")
public class AttendController {
    @Resource
    AttendService attendService;

    @ApiOperation("获取个人考勤信息")
    @PostMapping("/getSingleAttend")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "第几页", required = true), @ApiImplicitParam(name = "pageSize", value = "每一页有多少数据", required = true)})
    public R<PageInfo<UserAttend>> getSingleAttend(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestBody GetSingleAttendPara getSingleAttendPara) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserAttend> list = attendService.getSingleAttend(getSingleAttendPara.getUserId(), getSingleAttendPara.getDays());
        PageInfo<UserAttend> pageInfo = new PageInfo<>(list);
        return R.success(pageInfo);
    }
}
