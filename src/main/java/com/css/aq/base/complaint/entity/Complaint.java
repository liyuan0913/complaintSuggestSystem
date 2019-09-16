package com.css.aq.base.complaint.entity;

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
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description TODO
 * @Author yuhui
 * @Date 2019/9/10 10:21
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("COMPLAINT")
@ApiModel(value = "投诉记录实体")
public class Complaint {
    /**
     * 投诉问题Id
     */
    @TableId("COMPLAINT_ID")
    @ApiModelProperty(value = "投诉主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long complaintId;

    /**
     * 三方系统编码
     */
    @TableField("SYSTEM_NO")
    @ApiModelProperty(value = "三方系统编码")
    private String systemNo;

    /**
     * 投诉分类主键
     */
    @TableField("EVENT_TYPE_ID")
    @ApiModelProperty(value = "事件类型id")
    private Long eventTypeId;

    /**
     * 投诉者身份类型：0个人，1企业
     */

    @TableField("COMPLAINT_TYPE")
    @ApiModelProperty(value = "投诉者身份类型：1个人，2企业")
    private String complaintType;


    /**
     * 投诉者姓名
     */
    @TableField("COMPLAINT_NAME")
    @ApiModelProperty(value = "投诉者姓名")
    private String complaintName;

    /**
     * 证件类型
     */
    @TableField("CERT_TYPE")
    @ApiModelProperty(value = "证件类型")
    private String certType;

    /**
     * 证件号码
     */
    @TableField("CERT_NO")
    @ApiModelProperty(value = "证件号码")
    private String certNO;

    /**
     * 企业名称
     */
    @TableField("ENTERPRISE_NAME")
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    /**
     * 证照颁发部门的组织机构代码或者社会统一信用代码
     */
    @TableField("ISSUE_DEPT_CODE")
    @ApiModelProperty(value = "证照颁发部门的组织机构代码或者社会统一信用代码")
    private  String issueDeptCode;

    /**
     * 联系方式
     */
    @TableField("COMPLAINT_MOBILE")
    @ApiModelProperty(value = "联系方式")
    private String complaintMobile;

    /**
     * 邮箱地址
     */
    @TableField("COMPLAINT_EMAIL")
    @ApiModelProperty(value = "邮箱地址")
    private String complaintEmail;

    /**
     * 投诉内容
     */
    @TableField("COMPLAINT_CONTENT")
    @ApiModelProperty(value = "投诉内容")
    private String complaintContent;

    /**
     * 投诉来源，0：web游客，1：web用户，2：web管局，3：微信公众号游客，
     * 4：微信公众号用户，5：微信小程序，6：支付宝小程序，7：百度小程序
     */
    @TableField("COMPLAINT_SOURCE")
    @ApiModelProperty(value = "投诉来源，0：web游客，1：web用户，2：web管局，3：微信公众号游客，" +
            "4：微信公众号用户，5：微信小程序，6：支付宝小程序，7：百度小程序")
    private String complaintSource;

    /**
     * 事件发生地省份代码
     */
    @TableField("PROVINCE_CODE")
    @ApiModelProperty(value = "事件发生地省份代码")
    private String provinceCode;

    /**
     * 事件发生市代码
     */
    @TableField("CITY_CODE")
    @ApiModelProperty(value = "事件发生市代码")
    private String cityCode;

    /**
     * 事件发生县代码
     */
    @TableField("COUNTY_CODE")
    @ApiModelProperty(value = "事件发生县代码")
    private String countyCode;

    /**
     * 证明材料附件上传ID
     */
    @TableField("NOTIFICATION_PACKAGE_ID")
    @ApiModelProperty(value = "证明材料附件上传ID")
    private String notificationPackageId;

    /**
     * 逻辑删除标记位
     */
    @TableField("DEL_FLAG")
    @ApiModelProperty(value = "逻辑删除标记位")
    private Integer delFlag;

    /**
     * 投诉时间
     * @return
     */
    @TableField("COMPLAINT_DATE")
    @ApiModelProperty(value = "投诉时间")
    private Date complaintDate;

    /**
     * 情况核实
     * @return
     */
    @TableField("COMPLAINT_CHECK")
    @ApiModelProperty(value = "情况核实 是否属实 0代表属实，1代表不属实")
    private Integer complaintCheck;

    /**
     * 解决状态
     */
    @TableField("COMPLAINT_STATUS")
    @ApiModelProperty(value = "解决状态0：未解决，1：全部解决，3：部分解决")
    private Integer complaintStatus;

}
