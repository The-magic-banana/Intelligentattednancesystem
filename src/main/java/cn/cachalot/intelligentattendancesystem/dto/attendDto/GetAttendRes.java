package cn.cachalot.intelligentattendancesystem.dto.attendDto;

import cn.cachalot.intelligentattendancesystem.entity.User;
import cn.cachalot.intelligentattendancesystem.entity.UserAttend;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetAttendRes extends UserAttend {
    @ApiModelProperty("用户信息")
    private User user;
}
