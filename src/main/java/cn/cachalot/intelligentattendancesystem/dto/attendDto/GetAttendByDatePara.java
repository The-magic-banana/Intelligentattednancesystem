package cn.cachalot.intelligentattendancesystem.dto.attendDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@ApiModel(description = "根据日期获取考勤信息列表入参")
@Data
public class GetAttendByDatePara implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "查询的日期格式yyyy-MM-dd", required = true)
    private Date date;
}
