package com.css.aq.base.eventType.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.css.aq.base.Result;
import com.css.aq.base.eventType.entity.EventTypes;
import com.css.aq.base.eventType.service.EventTypesService;
import com.css.aq.base.util.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demo class
 * @author lvmenglei
 * @date 2019/9/11
 */

@Slf4j
@RestController
@RequestMapping(value = "/type")
@Api(description = "事件类型")
public class EventTypesController {
    @Autowired
    private EventTypesService eventTypesService;

    /**
     * 用户填写意见
     * @param eventTypes
     * @return
     */
    @PostMapping("addEventType")
    @ApiOperation(value = "管理员增加事件类型")
    public Map<String,Object> addEventType(@ApiParam("事件类型") @ModelAttribute EventTypes eventTypes){
        Map<String,Object> result = new HashMap<>();
        try {
            eventTypesService.addEventType(eventTypes);
            result.put("code", Result.successCode);
            result.put("msg",Result.Msg);
            result.put("data", Result.data);
        } catch (Exception e) {
            log.error("更新失败", e);
            result.put("code",Result.failCode);
            result.put("msg",e.getMessage());
        }
        return result;
    }


    /**
     * 查询事件类型列表(测试ok)
     * @param eventTypes
     * @return
     */
    @GetMapping(value = "getEventTypeList")
    @ApiOperation(value = "事件类型列表")
    public Map<String,Object> dirListPage(@ApiParam("系统编码") @ModelAttribute EventTypes eventTypes,
                                          @ApiParam("页码") @RequestParam Integer pageNo,
                                          @ApiParam("每页数量") @RequestParam Integer pageSize) {
        System.out.println("-----------------");
        System.out.println(eventTypes.getSystemNo());

        Map result = new HashMap<String, Object>();
        try {
            if (StringUtils.isEmpty(eventTypes.getSystemNo())) {
                throw new RuntimeException("请您选择需要查询的系统");
            }
            IPage<EventTypes> eventTypeList = eventTypesService.getEventTypeList(eventTypes, pageNo, pageSize);
            result.put("code", Result.successCode);
            /*result.put("msg","查询成功");*/
            result.put("data", eventTypeList);
            return result;
        } catch (Exception e) {
            result.put("code",Result.failCode);
            result.put("msg","查询失败");
            result.put("data", Result.data);
        }
        return result;
    }


    /**
     * 根据建议id和系统编码systemNO删除事件类型
     * @param eventTypeId
     * @param systemNo
     * @return
     */
    @PostMapping("delAdvice/{eventTypeId}/{systemNo}")
    @ApiOperation(value = "删除某条建议(逻辑删除)")
    public Map<String,Object> updDelFlag(@ApiParam("事件类型id") @PathVariable Long eventTypeId,
                          @ApiParam("系统编码") @PathVariable String systemNo){
        Map<String,Object> result = new HashMap<>();
        try {
            eventTypesService.updEventTypeDelFlag(eventTypeId,systemNo);
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
     * 修改事件类型
     * @param eventTypes
     * @return
     */
    @PostMapping("updEventType/{eventTypeId}")
    @ApiOperation("修改事件类型")
    public Map<String,Object> updEventType(@ApiParam("需要修改类型id") @PathVariable Long eventTypeId,
                         @ApiParam("事件类型") @ModelAttribute EventTypes eventTypes){
        Map<String,Object> result = new HashMap<>();

        try {
            //根据id查询出来
            EventTypes eventTypes1 = eventTypesService.getById(eventTypeId);

            if(eventTypes1.getEventType().equals(eventTypes.getEventType())){
                //对象拷贝 ：数据源为Null不拷贝到目标对象
                //第一个必须为数据源，第二个为目的地的
                BeanUtil.copyBeanNotNullToBean(eventTypes,eventTypes1);
                eventTypesService.updEventType(eventTypes1);
                //将结果封装到map集合里边
                result.put("code", Result.successCode);
                result.put("msg",Result.Msg);
                result.put("data", Result.data);
            }else if(!(eventTypes1.getEventType().equals(eventTypes.getEventType()))){
                QueryWrapper<EventTypes> queryWrapper = new QueryWrapper<>();
                if(eventTypes.getSystemNo() != null && eventTypes.getSystemNo() != ""){
                    queryWrapper.eq("SYSTEM_NO",eventTypes.getSystemNo());
                }
                if(eventTypes.getEventType() != null && eventTypes.getEventType() != ""){
                    queryWrapper.eq("EVENT_TYPE",eventTypes.getEventType());
                }
                Integer num = eventTypesService.count(queryWrapper);
                if (num == 0){
                    //对象拷贝 ：数据源为Null不拷贝到目标对象
                    //第一个必须为数据源，第二个为目的地的
                    BeanUtil.copyBeanNotNullToBean(eventTypes,eventTypes1);
                    eventTypesService.updEventType(eventTypes1);
                    //将结果封装到map集合里边
                    result.put("code", Result.successCode);
                    result.put("msg",Result.Msg);

                }else {
                    throw new RuntimeException("事件类型重复");
                }
            }


            //对象拷贝 ：数据源为Null不拷贝到目标对象
            //第一个必须为数据源，第二个为目的地的
            BeanUtil.copyBeanNotNullToBean(eventTypes,eventTypes1);
            eventTypesService.updEventType(eventTypes1);
            //将结果封装到map集合里边
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
}
