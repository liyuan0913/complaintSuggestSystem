package com.css.aq.base.eventType.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.css.aq.base.Constant;
import com.css.aq.base.advice.entity.Advice;
import com.css.aq.base.eventType.entity.EventTypes;
import com.css.aq.base.eventType.mapper.EventTypesMapper;
import com.css.aq.base.eventType.service.EventTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Demo class
 *
 * @author lvmenglei
 * @date 2019/9/11
 */
@Service
public class EventTypesServiceImpl extends ServiceImpl<EventTypesMapper, EventTypes> implements EventTypesService {
    @Autowired
    private EventTypesMapper eventTypesMapper;

    @Override
    public int addEventType(EventTypes eventTypes) {
        QueryWrapper<EventTypes> queryWrapper = new QueryWrapper<>();
        isEmpty(eventTypes);
        if (eventTypes.getSystemNo() != null && eventTypes.getSystemNo() != "") {
            queryWrapper.eq("SYSTEM_NO", eventTypes.getSystemNo());
        }
        if (eventTypes.getEventType() != null && eventTypes.getEventType() != "") {
            queryWrapper.eq("EVENT_TYPE", eventTypes.getEventType());
        }
        Integer num = eventTypesMapper.selectCount(queryWrapper);
        if (num != 0) {
            throw new RuntimeException("事件类型重复");
        }
        eventTypes.setDelFlag(Constant.NO_DELETE);
        eventTypes.setCreateDate(new Date());
        return eventTypesMapper.insert(eventTypes);
    }

    @Override
    public IPage<EventTypes> getEventTypeList(EventTypes eventTypes, Integer pageNo, Integer pageSize) {
        QueryWrapper<EventTypes> queryWrapper = new QueryWrapper<>();

        //根据系统编码进行查询
        if (eventTypes.getSystemNo() != null && eventTypes.getSystemNo() != "") {
            queryWrapper.eq("SYSTEM_NO", eventTypes.getSystemNo());
        }
        //将删除的标志置为1
        queryWrapper.eq("DEL_FLAG", Constant.NO_DELETE);

        IPage<EventTypes> page = page(new Page<>(pageNo, pageSize), queryWrapper);
        return page;
    }

    @Override
    public int updEventTypeDelFlag(Long eventTypeId, String systemNo) {
        if (eventTypeId == null) {
            throw new RuntimeException("请您选择需要删除的信息");
        }
        if (StringUtils.isEmpty(systemNo)) {
            throw new RuntimeException("请您选择需要查询的系统");
        }
        return eventTypesMapper.updEventTypeDelFlag(eventTypeId, systemNo);
    }

    @Override
    public int updEventType(EventTypes eventTypes) {
        isEmpty(eventTypes);
        return eventTypesMapper.updateById(eventTypes);
    }


    //判空工具类
    private void isEmpty(EventTypes eventTypes) {
        if (!StringUtils.isNotEmpty(eventTypes.getSystemNo())) {
            throw new RuntimeException("必填字段系统编码，不能为空");
        }
        if (!StringUtils.isNotEmpty(eventTypes.getComplaintTypeId().toString())) {
            throw new RuntimeException("必填字段投诉类型ID，不能为空");
        }
        if (!StringUtils.isNotEmpty(eventTypes.getEventType())) {
            throw new RuntimeException("必填字段事件类型，不能为空");
        }


    }
}
