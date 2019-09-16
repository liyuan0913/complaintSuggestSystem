package com.css.aq.base.attach.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.css.aq.base.Constant;
import com.css.aq.base.attach.entity.Attach;
import com.css.aq.base.attach.entity.AttachDelEntity;
import com.css.aq.base.attach.mapper.AttachMapper;
import com.css.aq.base.attach.service.IAttachService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.css.aq.base.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 附件记录表 服务实现类
 * </p>
 *
 * @author GaoTF
 * @since 2019-09-12
 */
@Service
public class AttachServiceImpl extends ServiceImpl<AttachMapper, Attach> implements IAttachService {

    @Autowired
    private AttachMapper attachMapper;

    @Override
    public List<Attach> getComplaintEventUplaod(String systemNo,Long complaintId){
        return attachMapper.getComplaintEventUplaod(systemNo,complaintId);
    }

    @Override
    public int addComplaintEventUplaod(Attach attach){
        isEmpty(attach);
        attach.setCreateTime(new Date());
        attach.setDelFlag(1);
        int rown = attachMapper.insert(attach);
        if (rown < 0){
            throw new RuntimeException("新增失败");
        }
        return rown;
    }

    @Override
    public int delComplaintEventUplaod(AttachDelEntity attachDelEntity){
        int rown = attachMapper.delComplaintEventUplaod(attachDelEntity.getIds(),attachDelEntity.getSystemNo());
        if (rown <= 0){
            throw new RuntimeException("删除失败");
        }
        return rown;
    }

    private void isEmpty(Attach attach){

        if (!StringUtils.isNotEmpty(attach.getSystemNo())){
            throw new RuntimeException("必填字段不能为空");
        }

        if(!StringUtils.isNotEmpty(attach.getComplaintId().toString())){
            throw new RuntimeException("必填字段不能为空");
        }

        if (!StringUtils.isNotEmpty(attach.getNotificationPackageId())){
            throw new RuntimeException("必填字段不能为空");
        }

        if (!StringUtils.isNotEmpty(attach.getAttachFlag().toString())){
            throw new RuntimeException("必填字段不能为空");
        }
    }


}
