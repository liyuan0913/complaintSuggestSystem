package com.css.aq.base.advice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.css.aq.base.advice.entity.Advice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Demo class
 * @author lvmenglei
 * @date 2019/8/12
 */
@Service
public interface AdviceMapper extends BaseMapper<Advice> {

    /**
     * 根据建议id和系统编码systemNO查询详情
     * @param adviceId
     * @param systemNO
     * @return
     */
    public Advice getAdviceById(@Param("adviceId") Long adviceId, @Param("systemNO") String systemNO);

    /**
     * 逻辑删除，将delFlag的值改为1
     * @param adviceId
     * @param systemNO
     * @return
     */
    public int updDelFlag(@Param("adviceId") Long adviceId, @Param("systemNO") String systemNO);

    /**
     * 批量删除（逻辑删除）
     * @param ids
     * @param systemNO
     * @return
     */
    public int delAdviceByIds(@Param("adviceIds") List<Long> ids,@Param("systemNO") String systemNO);





}
