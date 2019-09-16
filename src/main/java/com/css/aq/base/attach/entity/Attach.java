package com.css.aq.base.attach.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 附件记录表
 * </p>
 *
 * @author GaoTF
 * @since 2019-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ATTACH")
public class Attach implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 附件记录ID
     */
    @TableId("ATTACH_ID")
    private Long attachId;

    /**
     * 投诉记录主键
     */
    @TableField("COMPLAINT_ID")
    private Long complaintId;

    /**
     * 证明材料附件上传ID
     */
    @TableField("NOTIFICATION_PACKAGE_ID")
    private String notificationPackageId;

    /**
     * 系统编码
     */
    @TableField("SYSTEM_NO")
    private String systemNo;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 是否已删除逻辑标志位
     */
    @TableField("DEL_FLAG")
    private Integer delFlag;

    /**
     * 投诉/建议类型标志位
     */
    @TableField("ATTACH_FLAG")
    private Integer attachFlag;

}
