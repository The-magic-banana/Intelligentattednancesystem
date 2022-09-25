package cn.cachalot.intelligentattendancesystem.vo.uservo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("User管理登陆接口入参")
@Data
public class LoginPara implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登陆用户名", required = true)
    private String userName;

    @ApiModelProperty(value = "登陆密码", required = true)
    private String password;
}
