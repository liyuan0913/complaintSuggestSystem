package com.css.aq.base.reply.service;

import com.css.aq.base.reply.entity.Reply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuhui
 * @since 2019-09-12
 */
public interface IReplyService extends IService<Reply> {
    /**
     * 根据投诉记录主键和系统编码查询相应回复详情
     * @param systemNo
     * @param reply
     * @return
     */
    List<Reply> getReplyList(Reply reply, String systemNo);

    /**
     * 添加（追加）回复
     * @param reply
     * @return
     */
    int addReply(Reply reply);

    /**
     * 删除回复
     * @param ids
     * @param systemNo
     * @return
     */
    int delReply(List<Long> ids,String systemNo);
}
