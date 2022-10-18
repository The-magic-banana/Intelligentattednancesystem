package cn.cachalot.intelligentattendancesystem.dto.attendDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "根据员工获取考勤信息列表入参")
@Data
public class GetAttendByUserPara implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "查询天数,0表示查询所有", required = true)
    private Integer days;

    @ApiModelProperty(value = "被查询者Id", required = true)
    private Long userId;
}
