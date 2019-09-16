package com.css.aq.base.attach.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.css.aq.base.Result;
import com.css.aq.base.attach.entity.Attach;
import com.css.aq.base.attach.entity.AttachDelEntity;
import com.css.aq.base.attach.service.IAttachService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 附件记录表 前端控制器
 * </p>
 *
 * @author GaoTF
 * @since 2019-09-12
 */
@Slf4j
@RestController
@RequestMapping(value = "/attach")
@Api(description = "附件上传controller接口")
public class AttachController {

    Logger logger = LoggerFactory.getLogger(AttachController.class);

    @Autowired
    private IAttachService attachServiceImpl;

    @GetMapping(value = "/getComplaintEventUplaod")
    @ApiOperation(value = "查询上传附件列表")
    public Map getComplaintEventUplaod(@ApiParam("系统编码") @RequestParam String systemNo,
                                       @ApiParam("投诉管理ID") @RequestParam Long complaintId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            if (StringUtils.isNotEmpty(systemNo) && StringUtils.isNotEmpty(complaintId.toString())) {
                List<Attach> eventTypeList = attachServiceImpl.getComplaintEventUplaod(systemNo, complaintId);
                resultMap.put("code", Result.successCode);
                resultMap.put("msg", Result.Msg);
                resultMap.put("data", eventTypeList);
            }
        } catch (Exception e) {
            log.error("查询上传附件列表", e);
            resultMap.put("code", Result.failCode);
            resultMap.put("msg", e.getMessage());
        }
        return resultMap;
    }

    /**
     * 新增上传附件列表
     *
     * @param attach
     * @return
     */
    @PostMapping(value = "/addComplaintEventUplaod")
    @ApiOperation(value = "新增上传附件列表")
    public Map<String, Object> addComplaintEventUplaod(@ApiParam("上传附件") @ModelAttribute Attach attach) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            attachServiceImpl.addComplaintEventUplaod(attach);
            resultMap.put("code", Result.successCode);
            resultMap.put("msg", Result.Msg);
            resultMap.put("data", Result.data);
        } catch (Exception e) {
            log.error("新增失败", e);
            resultMap.put("code", Result.failCode);
            resultMap.put("msg", e.getMessage());
            resultMap.put("data", Result.data);
        }
        return resultMap;
    }

    /**
     * 逻辑删除投诉附件上传
     * @param attachDelEntity
     * @return
     */
    @ApiOperation(value = "逻辑删除投诉相关信息")
    @PostMapping(value = "delComplaintEventUplaod/{systemNo}")
    public Map<String, Object> delComplaintEventUplaod(@ModelAttribute AttachDelEntity attachDelEntity) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            attachServiceImpl.delComplaintEventUplaod(attachDelEntity);
            resultMap.put("code", Result.successCode);
            resultMap.put("msg", Result.Msg);
            resultMap.put("data", Result.data);
        } catch (Exception e) {
            log.error("删除失败", e);
            resultMap.put("code", Result.failCode);
            resultMap.put("msg", e.getMessage());
            resultMap.put("data", Result.data);
        }
        return resultMap;
    }


}







