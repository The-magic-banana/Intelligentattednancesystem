package cn.cachalot.intelligentattendancesystem.dto.attendDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "获取个人考勤信息入参")
@Data
public class GetSingleAttendPara implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "查询天数,0表示查询所有", required = true)
    private Integer days;

    @ApiModelProperty(value = "被查询者Id", required = true)
    private Long userId;
}
