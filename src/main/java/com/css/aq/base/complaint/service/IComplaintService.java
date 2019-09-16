package com.css.aq.base.complaint.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.css.aq.base.complaint.entity.Complaint;


import java.util.List;


/**
 * @Description TODO
 * @Author yuhui
 * @Date 2019/9/10 10:34
 **/
public interface IComplaintService extends IService<Complaint> {

    /**
     * 修改投诉记录相关信息
     */
    int updComplaint(Complaint complaint);

    /**
     * 逻辑删除投诉记录信息
     */
    int delComplaint(List<Long> ids,String systemNo);


    /**
     * 新增投诉记录相关信息
     */
    int addComplaint(Complaint complaint);

    /**
     * 查询投诉事件列表
     * @param page
     * @param complaint
     * @return
     */
    IPage<Complaint> dirComplaintEvent(Page page, Complaint complaint);

    /**
     * 根据主键查询详情
     * @param complaintId
     * @param systemNo
     * @return
     */
    Complaint getComplaint(long complaintId,String systemNo);
}
