package cn.cachalot.intelligentattendancesystem.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
@ApiModel("员工信息")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;
    /**
     * 登陆用户名
     */
    @ApiModelProperty(value = "登陆用户名", required = true)
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty("密码(默认123456)")
    private String password;
    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    private String realName;
    /**
     * 级别
     */
    @ApiModelProperty(value = "权限级别,0:可管理所有人,1:可管理所属部门,2:只能管自己", required = true)
    private Integer level;
    /**
     * 所属部门
     */
    @ApiModelProperty(value = "所属部门", required = true)
    private String department;
    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private Integer sex;
    /**
     * 出生日期
     */
    @ApiModelProperty("出生日期")
    private Date birthday;
    /**
     * 电话
     */
    @ApiModelProperty("电话")
    private String phone;
    /**
     * 住址
     */
    @ApiModelProperty("住址")
    private String address;
    /**
     * 面部数据地址
     */
    @ApiModelProperty("面部数据地址")
    private String faceData;
    /**
     * 指纹数据地址
     */
    @ApiModelProperty("指纹数据地址")
    private String fingerData;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String describe;

    @ApiModelProperty("是否被禁用")
    private Boolean isDisable;
}
