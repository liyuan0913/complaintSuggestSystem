package com.css.aq.base.reply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.css.aq.base.Constant;
import com.css.aq.base.advice.entity.Advice;
import com.css.aq.base.advice.mapper.AdviceMapper;
import com.css.aq.base.complaint.entity.Complaint;
import com.css.aq.base.complaint.mapper.ComplaintMapper;
import com.css.aq.base.reply.entity.Reply;
import com.css.aq.base.reply.mapper.ReplyMapper;
import com.css.aq.base.reply.service.IReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.css.aq.base.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuhui
 * @since 2019-09-12
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements IReplyService {

    @Autowired
    ReplyMapper replyMapper;

    @Override
    public List<Reply> getReplyList(Reply reply, String systemNo) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotEmpty(systemNo)) {
            queryWrapper.eq("SYSTEM_NO", systemNo);
        }
        queryWrapper.eq("COMPLAINT_ADVICE_ID",reply.getComplaintAdviceId());
        queryWrapper.eq("DEL_FLAG",Constant.NO_DELETE);
        queryWrapper.eq("REPLY_FLAG",reply.getReplyFlag());
        return replyMapper.selectList(queryWrapper);
    }

    @Override
    public int addReply(Reply reply) {
        if (!StringUtils.isNotEmpty(reply.getRespondent())){
            throw new RuntimeException("必填字段不能为空");
        }
        if (!StringUtils.isNotEmpty(reply.getReplyDetails())){
            throw new RuntimeException("必填字段不能为空");
        }
        if (!StringUtils.isNotEmpty(reply.getSystemNo())){
            throw new RuntimeException("必填字段不能为空");
        }
        if (reply.getReplyFlag()==null){
            throw new RuntimeException("必填字段不能为空");
        }
        reply.setReplyTime(DateUtil.parseDateLong());
        reply.setDelFlag(Constant.NO_DELETE);
        int rown = replyMapper.insert(reply);
        if (rown <= 0){
            throw new RuntimeException("新增回复失败");
        }
        return rown;
    }

    @Override
    public int delReply(List<Long> ids,String systemNo) {
        int rown = replyMapper.delReplyById(ids,systemNo);
        if (rown <= 0){
            throw new RuntimeException("删除失败");
        }
        return 0;
    }
}
