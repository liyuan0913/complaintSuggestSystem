package com.css.aq.base.eventType.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.css.aq.base.eventType.entity.EventTypes;

import java.util.List;

/**
 * Demo class
 * @author lvmenglei
 * @date 2019/9/11
 */
public interface EventTypesService extends IService<EventTypes> {
    /**
     * 管理员添加事件类型
     * @param eventTypes
     * @return
     */
    public int addEventType(EventTypes eventTypes);


    /**
     * 根据系统编码查询投诉类型列表
     * @param systemNo
     * @return ComplaintManage
     */
    public IPage<EventTypes> getEventTypeList(EventTypes eventTypes, Integer pageNo, Integer pageSize);

    /**
     * 根据系统编码和事件类型id删除事件类型
     * @param eventTypeId
     * @param systemNo
     * @return
     */
    public int updEventTypeDelFlag(Long eventTypeId,String systemNo);

    /**
     * 更改事件类型
     * @param eventTypes
     * @return
     */
    public int updEventType(EventTypes eventTypes);

}
