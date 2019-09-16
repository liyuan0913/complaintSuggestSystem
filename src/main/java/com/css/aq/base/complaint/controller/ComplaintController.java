package com.css.aq.base.complaint.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.css.aq.base.Constant;
import com.css.aq.base.Result;
import com.css.aq.base.complaint.entity.Complaint;
import com.css.aq.base.complaint.mapper.ComplaintMapper;
import com.css.aq.base.complaint.service.IComplaintService;
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
 * @Description TODO
 * @Author yuhui
 * @Date 2019/9/10 10:39
 **/
@RestController
@Slf4j
@RequestMapping("/complaint")
@Api(description = "投诉问题controller接口")
public class ComplaintController {


    @Autowired
    private IComplaintService complaintService;




    /**
     * 新增投诉事件
     * @param complaint
     * @return
     */
    @ApiOperation(value = "新增投诉事件")
    @PostMapping("/addComplaintEvent")
    public Map<String,Object> addComplaintEvent(@ApiParam("新增投诉事件") @ModelAttribute Complaint complaint) {
        Map<String,Object> result = new HashMap<>();
        try {
           complaintService.addComplaint(complaint);
           result.put("code", Result.successCode);
           result.put("msg",Result.Msg);
           result.put("data", Result.data);
        } catch (Exception e) {
            log.error("新增失败", e);
            result.put("code",Result.failCode);
            result.put("msg",e.getMessage());
            result.put("data", Result.data);
        }
        return result;
    }
    /**
     * 修改投诉相关信息
     * @param complaint
     * @return
     */
    @ApiOperation(value = "修改投诉相关信息")
    @PostMapping(value = "/updComplaintEvent")
    public Map<String,Object> updComplaintEvent(@ApiParam("修改投诉信息实体") @ModelAttribute Complaint complaint) {
        Map<String,Object> result = new HashMap<>();
        try {
            complaintService.updComplaint(complaint);
            result.put("code", Result.successCode);
            result.put("msg",Result.Msg);
            result.put("data", Result.data);
        } catch (Exception e) {
            log.error("更新失败",e);
            result.put("code",Result.failCode);
            result.put("msg",e.getMessage());
            result.put("data", Result.data);
        }
        return result;
    }


   /**
     * 逻辑删除投诉相关信息
     * @param ids
     * @return
     */
    //advice.setDelFlag(Constant.NO_DELETE);
    @ApiOperation(value = "逻辑删除投诉相关信息")
    @PostMapping(value = "delComplaintEvent/{systemNo}")
    public Map<String,Object> delComplaintEvent(@ApiParam("系统编码")@PathVariable("systemNo") String systemNo,
            @ApiParam("投诉主键ID") @RequestParam("ids") List<Long> ids) {
        Map<String,Object> result = new HashMap<>();
        try {
            complaintService.delComplaint(ids,systemNo);
            result.put("code", Result.successCode);
            result.put("msg",Result.Msg);
            result.put("data", Result.data);
        } catch (Exception e) {
            log.error("删除失败", e);
            result.put("code",Result.failCode);
            result.put("msg",e.getMessage());
            result.put("data", Result.data);
        }
        return result;
    }

    /**
     * 查询投诉记录
     * @param page
     * @param complaint
     * @return
     */
    @ApiParam
    @ApiOperation(value = "查询投诉记录")
    @PostMapping(value = "dirEventComplaintList")
    public Map<String,Object> dirEventComplaintList(@ApiParam("page对象") @ModelAttribute("page") Page page,
        @ApiParam("查询参数") @ModelAttribute("complaint") Complaint complaint){
        Map<String,Object> result = new HashMap<>();
        IPage<Complaint> Ipage= complaintService.dirComplaintEvent(page,complaint);
        if (Ipage !=null){
            result.put("data",Ipage);
            result.put("code", Result.successCode);
        }else {
            result.put("code",Result.failCode);
            result.put("msg","查询失败");
            result.put("data", Result.data);
        }
        return  result;
    }

    /**
     * 查询投诉详细信息
     * @param comlaintId
     * @param systemNo
     * @return
     */
    @ApiOperation("查询投诉详细信息")
    @GetMapping("/getComplaint/{complaintId}")
    public Map<String, Object> getComplaint(@ApiParam("投诉记录主键") @PathVariable("complaintId") long comlaintId,
                                            @ApiParam("系统编码") @RequestParam("systemNo") String systemNo){
        Map<String,Object> result = new HashMap<>();
        Complaint complaint = complaintService.getComplaint(comlaintId,systemNo);
        if (complaint != null){
            result.put("data",complaint);
            result.put("code", Result.successCode);
        }else{
            result.put("code",Result.failCode);
            result.put("msg","查询失败");
            result.put("data", Result.data);
        }
        return result;
    }
}
