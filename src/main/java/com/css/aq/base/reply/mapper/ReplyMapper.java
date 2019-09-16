package com.css.aq.base.reply.mapper;

import com.css.aq.base.reply.entity.Reply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuhui
 * @since 2019-09-12
 */
@Repository
public interface ReplyMapper extends BaseMapper<Reply> {
    /**
     * 逻辑删除
     * @param ids
     * @param systemNo
     * @return
     */
    int delReplyById(@Param("ids") List<Long> ids, @Param("systemNo") String systemNo);
}
