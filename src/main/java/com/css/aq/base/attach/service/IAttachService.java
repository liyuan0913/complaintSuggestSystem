package com.css.aq.base.attach.service;

import com.css.aq.base.attach.entity.Attach;
import com.baomidou.mybatisplus.extension.service.IService;
import com.css.aq.base.attach.entity.AttachDelEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 附件记录表 服务类
 * </p>
 *
 * @author GaoTF
 * @since 2019-09-12
 */
@Service
public interface IAttachService extends IService<Attach> {

    /**
     * 查询上传附件
     * @param systemNo
     * @param complaintId
     * @return
     */
    List<Attach> getComplaintEventUplaod(String systemNo, Long complaintId);

    /**
     * 逻辑删除投诉记录信息
     */
    int delComplaintEventUplaod(AttachDelEntity attachDelEntity);


    /**
     * 新增投诉记录相关信息
     */
    int addComplaintEventUplaod(Attach attach);


}
