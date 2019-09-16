package com.css.aq.base.reply.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuhui
 * @since 2019-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("REPLY")
@ApiModel(value = "回复实体")
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "回复主键id")
    @TableId("REPLY_ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer replyId;

    /**
     * 系统编码
     */
    @ApiModelProperty(value = "三方系统编码")
    @TableField("SYSTEM_NO")
    private String systemNo;

    /**
     * 建议（投诉）表id
     */
    @ApiModelProperty(value = "建议（投诉）表id")
    @TableField("COMPLAINT_ADVICE_ID")
    private Integer complaintAdviceId;

    /**
     * 回复人
     */
    @ApiModelProperty(value = "回复人")
    @TableField("RESPONDENT")
    private String respondent;

    /**
     * 回复详细
     */
    @ApiModelProperty(value = "回复详情")
    @TableField("REPLY_DETAILS")
    private String replyDetails;

    /**
     * 回复时间
     */
    @ApiModelProperty(value = "回复时间")
    @TableField("REPLY_TIME")
    private Date replyTime;

    /**
     * 删除标识1为为删除，2为删除
     */
    @ApiModelProperty(value = "删除标识1为为删除，2为删除")
    @TableField("DEL_FLAG")
    private Integer delFlag;

    /**
     * 标志位：0为投诉，1为建议
     */
    @ApiModelProperty(value = "标志位：0为投诉，1为建议")
    @TableField("REPLY_FLAG")
    private Integer replyFlag;


}
