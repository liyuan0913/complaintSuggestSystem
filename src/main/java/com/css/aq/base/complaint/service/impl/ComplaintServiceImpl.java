package com.css.aq.base.complaint.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.css.aq.base.Constant;
import com.css.aq.base.complaint.entity.Complaint;
import com.css.aq.base.complaint.mapper.ComplaintMapper;
import com.css.aq.base.complaint.service.IComplaintService;
import com.css.aq.base.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description TODO
 * @Author yuhui
 * @Date 2019/9/10 10:35
 **/
@Service
public class ComplaintServiceImpl extends ServiceImpl<ComplaintMapper, Complaint> implements IComplaintService {

    @Autowired
    ComplaintMapper complaintMapper;


    @Override
    public int addComplaint(Complaint complaint) {
        isEmpty(complaint);
        complaint.setDelFlag(Constant.NO_DELETE);
        complaint.setComplaintDate(DateUtil.parseDateLong());
        int rown = complaintMapper.insert(complaint);
        if (rown < 0){
            throw new RuntimeException("新增失败");
        }
        return rown;
    }

    @Override
    public int updComplaint(Complaint complaint) {
        Complaint old = complaintMapper.selectById(complaint.getComplaintId());
        isEmpty(complaint);
        if (old != null){
            if (!complaint.getSystemNo().equals(old.getSystemNo())){
                throw new RuntimeException("系统编码无法识别");
            }
            old.setComplaintDate(complaint.getComplaintDate());
            old.setNotificationPackageId(complaint.getNotificationPackageId());
            old.setComplaintType(complaint.getComplaintType());
            old.setEnterpriseName(complaint.getEnterpriseName());
            old.setComplaintContent(complaint.getComplaintContent());
            old.setCertNO(complaint.getCertNO());
            old.setCertType(complaint.getCertType());
            old.setCityCode(complaint.getProvinceCode());
            old.setProvinceCode(complaint.getProvinceCode());
            old.setCountyCode(complaint.getCountyCode());
            old.setComplaintEmail(complaint.getComplaintEmail());
            old.setComplaintMobile(complaint.getComplaintMobile());
            old.setComplaintSource(complaint.getComplaintSource());
            old.setEventTypeId(complaint.getEventTypeId());
            if (complaint.getComplaintCheck() != null){
                old.setComplaintCheck(complaint.getComplaintCheck());
            }
            if (complaint.getComplaintStatus()!=null){
                old.setComplaintStatus(complaint.getComplaintStatus());
            }
        }
        int row = complaintMapper.updateById(old);
        if (row<=0){
            throw new RuntimeException("更新失败");
        }
        return row;
    }

    @Override
    public int delComplaint(List<Long> ids, String systemNo) {
        int rown = complaintMapper.delComplaintEvent(ids,systemNo);
        if (rown <= 0){
            throw new RuntimeException("删除失败");
        }
        return rown;
    }
    @Override
    public IPage<Complaint> dirComplaintEvent(Page page,Complaint complaint){
        QueryWrapper<Complaint> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(complaint.getComplaintType())){
            queryWrapper.eq("COMPLAINT_TYPE",complaint.getComplaintType());
        }
        if (StringUtils.isNotEmpty(complaint.getComplaintSource())){
            queryWrapper.eq("COMPLAINT_SOURCE",complaint.getComplaintSource());
        }
        if (complaint.getComplaintId() != null){
            queryWrapper.eq("COMPLAINT_ID",complaint.getComplaintId());
        }
        if (StringUtils.isNotEmpty(complaint.getSystemNo())){
            queryWrapper.eq("SYSTEM_NO",complaint.getSystemNo());
        }
        queryWrapper.eq("DEL_FLAG",2);
        IPage<Complaint> complaintIPageage=page(page,queryWrapper);

        return complaintIPageage;
    }

    @Override
    public Complaint getComplaint(long complaintId, String systemNo) {
        QueryWrapper<Complaint> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("COMPLAINT_ID",complaintId);
        queryWrapper.eq("SYSTEM_NO",systemNo);
        return complaintMapper.selectOne(queryWrapper);
    }

    private void isEmpty(Complaint complaint){
        if (!StringUtils.isNotEmpty(complaint.getSystemNo()))
            throw new RuntimeException("必填字段不能为空");

        if (!StringUtils.isNotEmpty(complaint.getComplaintType()))
            throw new RuntimeException("必填字段不能为空");

        if (complaint.getComplaintType().equals("0")){
            if (!StringUtils.isNotEmpty(complaint.getComplaintName()))
                throw new RuntimeException("必填字段不能为空");
            if (!StringUtils.isNotEmpty(complaint.getCertNO()))
                throw new RuntimeException("必填字段不能为空");
            if (!StringUtils.isNotEmpty(complaint.getCertType()))
                throw new RuntimeException("必填字段不能为空");
        }else if(complaint.getComplaintType().equals("1")){
            if (!StringUtils.isNotEmpty(complaint.getEnterpriseName()))
                throw new RuntimeException("必填字段不能为空");
            if (!StringUtils.isNotEmpty(complaint.getIssueDeptCode()))
                throw new RuntimeException("必填字段不能为空");
        }else{
            throw new RuntimeException("请选择正确的投诉者身份类型");
        }
        if (!StringUtils.isNotEmpty(complaint.getNotificationPackageId())){
            throw new RuntimeException("请上传相关材料");
        }
        if (!StringUtils.isNotEmpty(complaint.getComplaintContent()))
            throw new RuntimeException("必填字段不能为空");

        if (!StringUtils.isNotEmpty(complaint.getProvinceCode()))
            throw new RuntimeException("必填字段不能为空");

        if (!StringUtils.isNotEmpty(complaint.getCityCode()))
            throw new RuntimeException("必填字段不能为空");

        if (!StringUtils.isNotEmpty(complaint.getComplaintSource()))
            throw new RuntimeException("请选择正确的投诉来源");
    }

}

