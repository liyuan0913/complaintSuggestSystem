package com.css.aq.base.advice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.css.aq.base.Constant;
import com.css.aq.base.advice.entity.Advice;
import com.css.aq.base.advice.mapper.AdviceMapper;
import com.css.aq.base.advice.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Demo class
 *
 * @author lvmenglei
 * @date 2019/9/10
 */
@Service
public class AdviceServiceImpl extends ServiceImpl<AdviceMapper, Advice> implements AdviceService {
    @Autowired
    private AdviceMapper adviceMapper;

    @Override
    public IPage<Advice> getPage(Advice advice,Integer pageNo,Integer pageSize){
        QueryWrapper<Advice> queryWrapper = new QueryWrapper<>();
        //根据系统编码进行查询
        if(advice.getSystemNO() != null && advice.getSystemNO() != ""){
            queryWrapper.eq("SYSTEM_NO",advice.getSystemNO());
        }
        //将删除的标志置为1
        queryWrapper.eq("DEL_FLAG",1);
        IPage<Advice> pageList=page(new Page<>(pageNo,pageSize),queryWrapper);
        return pageList;

    }

    @Override
    public Advice getAdviceById(Long adviceId){
        return adviceMapper.selectById(adviceId);

    }

    @Override
    public int addAdvice(Advice advice){
        isEmpty(advice);
        advice.setAdviceDate(new Date());
        advice.setDelFlag(Constant.NO_DELETE);
        return adviceMapper.insert(advice);
    }

    @Override
    public int delAdvice(String systemNO,Long adviceId){
        QueryWrapper<Advice> queryWrapper = new QueryWrapper<>();
        if(systemNO != null && systemNO != ""){
            queryWrapper.eq("SYSTEM_NO",systemNO);
        }
        if(adviceId != null){
            queryWrapper.eq("ADVICE_ID",adviceId);
        }
        return adviceMapper.delete(queryWrapper);

    }

    @Override
    public int updAdvice(Advice advice){
        return adviceMapper.updateById(advice);
    }

    @Override
    public int delAdviceList(String adviceIds){
        //把string的字符串分割开
        String arr[] = adviceIds.split(",");
        List ids = new ArrayList();
        //把分割好的字符串放到数组里边
        for(int i= 0;i<arr.length;i++){
            ids.add(arr[i]);
        }
        return adviceMapper.deleteBatchIds(ids);
    }


    @Override
    public Advice getAdviceById(Long adviceId,String systemNO){
        return adviceMapper.getAdviceById(adviceId,systemNO);
    }

    @Override
    public int updDelFlag(Long adviceId,String systemNO){
        return adviceMapper.updDelFlag(adviceId,systemNO);
    }

    @Override
    public int updDelFlags(String adviceIds,String systemNO){
        String arr[] = adviceIds.split(",");
        List ids = new ArrayList();
        for(int i=0;i<arr.length;i++){
            ids.add(arr[i]);
        }
        return adviceMapper.delAdviceByIds(ids,systemNO);
    }

    //判空工具类
    private void isEmpty(Advice advice){
        if (!StringUtils.isNotEmpty(advice.getSystemNO())){
            throw new RuntimeException("必填字段不能为空1");
        }
        if(!StringUtils.isNotEmpty(advice.getEventTypeId().toString())){
            throw new RuntimeException("必填字段不能为空2");
        }
        if(!StringUtils.isNotEmpty(advice.getAdviceTitle())){
            throw new RuntimeException("必填字段不能为空3");
        }
        if(!StringUtils.isNotEmpty(advice.getSuggestedContent())){
            throw new RuntimeException("必填字段不能为空4");
        }

    }


}
