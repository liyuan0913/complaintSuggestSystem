package com.css.aq.base.eventType.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author GaoTF
 * @version V1.0
 * @description: TODO
 * @date 2019/9/11 10:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("EVENT_TYPES")
@ApiModel(value = "投诉类型实体")
public class EventTypes {

    /**
     * 投诉Id
     */
    @TableId("EVENT_TYPE_ID")
    @ApiModelProperty(value = "主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long eventTypeId;

    /**
     * 三方系统编码
     */
    @TableField("SYSTEM_NO")
    @ApiModelProperty(value = "系统编码")
    private String systemNo;

    /**
     * 投诉建议表id
     */
    @TableField("COMPLAINT_TYPE_ID")
    @ApiModelProperty(value = "投诉建议表id")
    private Long complaintTypeId;

    /**
     * 事件类型
     */
    @TableField("EVENT_TYPE")
    @ApiModelProperty(value = "事件类型")
    private String eventType;

    /**
     * 删除标记（1：删除  非1：未删除）
     */
    @TableField("DEL_FLAG")
    @ApiModelProperty(value = "删除标记（1：删除  0：未删除）")
    private Integer delFlag;


    /**
     * 建议时间
     */

    @TableField("CREATE_DATE")
    @ApiModelProperty("创建时间")
    private Date createDate;



}
