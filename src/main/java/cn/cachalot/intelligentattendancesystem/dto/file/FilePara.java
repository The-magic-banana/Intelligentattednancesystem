package cn.cachalot.intelligentattendancesystem.dto.file;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "文件")
public class FilePara implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("base64图片")
    private String file;
}
