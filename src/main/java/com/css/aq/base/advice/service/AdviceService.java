package com.css.aq.base.advice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.css.aq.base.advice.entity.Advice;

import java.util.List;

/**
 * Demo class
 * @author lvmenglei
 * @date 2019/8/12
 */
public interface AdviceService extends IService<Advice> {

    /**
     * 分页查询
     * @param systemNO
     * @param page
     * @return
     */
    public IPage<Advice> getPage(String systemNO, Page page);

    /**
     * 查询意见详情
     * @param adviceId
     * @return
     */
    public Advice getAdviceById(Long adviceId);

    /**
     * 用户填写意见
     * @param advice
     * @return
     */
    public int addAdvice(Advice advice);

    /**
     * 删除某条意见(物理删除)
     * @param systemNO
     * @param adviceId
     * @return
     */
    public int delAdvice(String systemNO,Long adviceId);

    /**
     * 修改建议
     * @param advice
     * @return
     */
    public int updAdvice(Advice advice);

    /**
     * 批量删除建议(物理删除)
     * @param adviceIds
     * @return
     */
    public int delAdviceList(String adviceIds);


    /**
     * 根据建议id和系统编码systemNO查询详情
     * @param adviceId
     * @param systemNO
     * @return
     */
    public Advice getAdviceById(Long adviceId, String systemNO);

    /**
     * 根据建议id和系统编码system进行逻辑删除
     * @param adviceId
     * @param systemNO
     * @return
     */
    public int updDelFlag(Long adviceId, String systemNO);


    /**
     * 批量删除建议（逻辑删除）
     * @param adviceIds
     * @param systemNO
     * @return
     */
    public int updDelFlags(String adviceIds,String systemNO);


}
