package com.css.aq.base.eventType.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.css.aq.base.eventType.entity.EventTypes;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Demo class
 * @author lvmenglei
 * @date 2019/9/11
 */
@Service
public interface EventTypesMapper extends BaseMapper<EventTypes> {

    /**
     * 根据系统编码查询投诉类型列表
     * @param systemNo
     * @return
     */
    public List<EventTypes> getEventTypeList(@Param("systemNo") String systemNo);


    /**
     * 根据系统编码和事件类型id删除事件类型
     * @param eventTypeId
     * @param systemNo
     * @return
     */
    public int updEventTypeDelFlag(@Param("eventTypeId") Long eventTypeId,@Param("systemNo") String systemNo);

    /**
     * 判断同一个系统下的事件类型是否重复
     * @param eventTypeId
     * @param systemNo
     * @return
     */
    public int getEventTypeBySystemNoAndId(@Param("eventTypeId") Long eventTypeId,@Param("systemNo") String systemNo);


}
