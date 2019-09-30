package com.css.aq.base.advice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Demo class
 * @author lvmenglei
 * @date 2019/9/10
 */
@Data
@TableName("ADVICE")
@ApiModel(value = "建议实体")
public class Advice {
    /**
     * 建议id
     */
    @TableId("ADVICE_ID")
    @ApiModelProperty("建议id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long adviceId;

    /**
     * 系统编码
     */
    @TableField("SYSTEM_NO")
    @ApiModelProperty("系统编码")
    private String systemNO;

    /**
     *建议人
     */
    @TableField("ADVICE_PERSON")
    @ApiModelProperty("建议人")
    private String advicePerson;

    /**
     *事件类型id
     */
    @TableField("EVENT_TYPE_ID")
    @ApiModelProperty("事件类型id")
    private Long eventTypeId;

    /**
     *建议标题
     */
    @TableField("ADVICE_TITLE")
    @ApiModelProperty("建议标题")
    private String adviceTitle;

    /**
     *建议内容
     */
    @TableField("SUGGESTED_CONTENT")
    @ApiModelProperty("建议内容")
    private String suggestedContent;

    /**
     * 证明材料附件上传id
     */
    @TableField("NOTIFICATION_PACKAGE_ID")
    @ApiModelProperty("证明材料附件上传id")
    private String notificationPackageId;

    /**
     * 建议时间
     */
    @TableField("ADVICE_DATE")
    @ApiModelProperty("建议时间")
    private Date adviceDate;

    /**
     * 删除标记（1：删除  非1：未删除）
     */
    @TableField("DEL_FLAG")
    @ApiModelProperty(value = "删除标记（1:未删除  2:删除）")
    private Integer delFlag;






}
