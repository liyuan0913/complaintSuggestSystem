package com.css.aq.base.complaint.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.css.aq.base.complaint.entity.Complaint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author yuhui
 * @Date 2019/9/10 10:33
 **/
@Service
public interface ComplaintMapper extends BaseMapper<Complaint> {

    /**
     * 逻辑删除
     * @param ids
     * @param systemNo
     * @return
     */
    int delComplaintEvent(@Param("ids") List<Long> ids, @Param("systemNo") String systemNo);
}
