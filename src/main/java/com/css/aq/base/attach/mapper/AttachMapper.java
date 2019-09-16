package com.css.aq.base.attach.mapper;

import com.css.aq.base.attach.entity.Attach;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 附件记录表 Mapper 接口
 * </p>
 *
 * @author GaoTF
 * @since 2019-09-12
 */
@Service
public interface AttachMapper extends BaseMapper<Attach> {
    /**
     * 查询上传证明材料
     *
     * @param systemNo
     * @param complaintId
     * @return
     */
    List<Attach> getComplaintEventUplaod(@Param("systemNo") String systemNo, @Param("complaintId") Long complaintId);


    /**
     * 逻辑删除
     *
     * @param ids
     * @param systemNo
     * @return
     */
    int delComplaintEventUplaod(@Param("ids") List<Long> ids, @Param("systemNo") String systemNo);

}
