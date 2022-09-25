package cn.cachalot.intelligentattendancesystem.vo.uservo;

import cn.cachalot.intelligentattendancesystem.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("User管理登陆接口出参")
public class LoginRes implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("登陆员工个人信息")
    private User user;
}
