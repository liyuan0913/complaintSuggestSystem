package com.css.aq.base.advice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.css.aq.base.Result;
import com.css.aq.base.advice.entity.Advice;
import com.css.aq.base.advice.service.AdviceService;
import com.css.aq.base.util.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo class
 * @author lvmenglei
 * @date 2019/9/10
 */
@Slf4j
@RestController
@RequestMapping(value = "/advice")
@Api(description = "建议实体")
public class AdviceController {

    @Autowired
    private AdviceService adviceService;


    /**
     * 分页查询
     * @param systemNO
     * @param page
     * @return
     */
    @GetMapping("/getPage")
    @ApiOperation(value = "根据系统编码进行分页查询")
    public Map<String,Object> getPage(@ApiParam("系统编码") @RequestParam String systemNO,
                                      @ApiParam("page对象") @ModelAttribute("page") Page page
    ){
        Map<String,Object> result = new HashMap<>();
        IPage<Advice> pages = adviceService.getPage(systemNO,page);
        if (pages!=null){
            result.put("code", Result.successCode);
            /*result.put("msg","查询成功");*/
            result.put("data",pages);
        }else {
            result.put("code",Result.failCode);
            result.put("msg","查询失败");
            result.put("data", Result.data);
        }
        return  result;
    }


    @GetMapping("/getAdviceById/{adviceId}")
    @ApiOperation(value = "查看建议详情")
    public Map<String,Object> getAdviceById(@ApiParam("建议id") @PathVariable Long adviceId){
        Map<String,Object> result = new HashMap<>();
        try {
            Advice advice = adviceService.getAdviceById(adviceId);
            result.put("code", Result.successCode);
            /*result.put("msg",Result.Msg);*/
            result.put("data",advice);
        } catch (Exception e) {
            log.error("更新失败", e);
            result.put("code",Result.failCode);
            result.put("msg",e.getMessage());
        }
        return result;

    }


    /**
     * 用户填写意见
     * @param advice
     * @return
     */
    @PostMapping("/addAdvice")
    @ApiOperation(value = "用户填写建议")
    public Map<String,Object> addAdvice(@ApiParam("建议实体") @ModelAttribute Advice advice){
        Map<String,Object> result = new HashMap<>();
        try {
            adviceService.addAdvice(advice);
            result.put("code", Result.successCode);
            result.put("msg",Result.Msg);
            result.put("data", Result.data);
        } catch (Exception e) {
            log.error("更新失败", e);
            result.put("code",Result.failCode);
            result.put("msg",e.getMessage());
        }
        return result;
    }


    /**
     * 删除建议
     * @param adviceId
     * @return
     */
    @PostMapping("delAdvice/{adviceId}")
    @ApiOperation(value = "删除某条建议(物理删除)")
    public Map<String,Object> delAdvice(@ApiParam("建议id") @PathVariable Long adviceId,
                                        @ApiParam("系统编码") @RequestParam String systemNO){
        Map<String,Object> result = new HashMap<>();
        try {
            adviceService.delAdvice(systemNO,adviceId);
            result.put("code", Result.successCode);
            result.put("msg",Result.Msg);
            result.put("data", Result.data);
        } catch (Exception e) {
            log.error("删除失败", e);
            result.put("code",Result.failCode);
            result.put("msg",e.getMessage());
            result.put("data", Result.data);
        }
        return result;
    }


    /**
     * 修改建议
     * @param adviceId
     * @param advice
     * @return
     */
    @PostMapping("updAdvice/{adviceId}")
    @ApiOperation("修改建议")
    public Map<String,Object> updAdvice(@ApiParam("需要修改建议id") @PathVariable Long adviceId,
                                        @ApiParam("建议实体") @ModelAttribute Advice advice){
        Map<String,Object> result = new HashMap<>();
        try {
            //根据id查询出来
            Advice advice1 = adviceService.getAdviceById(adviceId);
            //对象拷贝 ：数据源为Null不拷贝到目标对象
            //第一个必须为数据源，第二个为目的地的
            BeanUtil.copyBeanNotNullToBean(advice,advice1);
            Integer status = adviceService.updAdvice(advice1);
            result.put("code", Result.successCode);
            result.put("msg",Result.Msg);
            result.put("data", Result.data);
        } catch (Exception e) {
            log.error("更新失败",e);
            result.put("code",Result.failCode);
            result.put("msg",e.getMessage());
            result.put("data", Result.data);
        }
        return result;
    }

    /**
     * 批量删除(物理删除)
     * @param adviceIds
     * @return
     */
    @PostMapping("delAdviceByIds")
    @ApiOperation(value = "批量删除建议(物理删除)")
    public Map<String,Object>  delAdviceByIds(@ApiParam("ids") @RequestParam String adviceIds){
        Map<String,Object> result = new HashMap<>();
        try {
            Integer status = adviceService.delAdviceList(adviceIds);
            result.put("code", Result.successCode);
            result.put("msg",Result.Msg);
            result.put("data", Result.data);
        } catch (Exception e) {
            log.error("删除失败", e);
            result.put("code",Result.failCode);
            result.put("msg",e.getMessage());
            result.put("data", Result.data);
        }
        return result;

    }

    /**
     * 根据系统编码批量删除(逻辑删除)
     * @param adviceIds
     * @return
     */
    @PostMapping("ljDelAdviceByIds")
    @ApiOperation(value = "批量删除建议(逻辑删除)")
    public Map<String,Object>  ljDelAdviceByIds(@ApiParam("ids") @RequestParam String adviceIds,
                                                @ApiParam("系统编码") @RequestParam String systemNO ){
        Map<String,Object> result = new HashMap<>();
        try {
            adviceService.updDelFlags(adviceIds,systemNO);
            result.put("code", Result.successCode);
            result.put("msg",Result.Msg);
            result.put("data", Result.data);
        } catch (Exception e) {
            log.error("删除失败", e);
            result.put("code",Result.failCode);
            result.put("msg",e.getMessage());
            result.put("data", Result.data);
        }
        return result;
    }


    /**
     * 根据建议id和系统编码systemNO查询详情(2019/9/10 下午)
     * @param adviceId
     * @param systemNO
     * @return
     */
    @GetMapping("/getAdviceByIdAndSystemNo/{adviceId}")
    @ApiOperation(value = "根据建议id和系统编码systemNO查询详情")
    public Map<String,Object> getAdviceByIdAndSystemNo(@ApiParam("建议id") @PathVariable Long adviceId,
                                                       @ApiParam("系统编码") @RequestParam("systemNO") String systemNO){
        Map<String,Object> result = new HashMap<>();
        try {
            Advice advice = adviceService.getAdviceById(adviceId,systemNO);
            result.put("code", Result.successCode);
            result.put("msg",Result.Msg);
            result.put("data",advice);
        } catch (Exception e) {
            log.error("更新失败", e);
            result.put("code",Result.failCode);
            result.put("msg",e.getMessage());
        }
        return result;
    }


    /**
     * 根据建议id和系统编码systemNO删除建议(2019/9/10 下午)
     * @param adviceId
     * @param systemNO
     * @return
     */
    @PostMapping("updDelFlag/{adviceId}")
    @ApiOperation(value = "删除某条建议(逻辑删除)")
    public Map<String,Object> updDelFlag(@ApiParam("建议id") @PathVariable Long adviceId,
                                         @ApiParam("系统编码") @RequestParam("systemNO") String systemNO){
        Map<String,Object> result = new HashMap<>();
        try {
            adviceService.updDelFlag(adviceId,systemNO);
            result.put("code", Result.successCode);
            result.put("msg",Result.Msg);
            result.put("data", Result.data);
        } catch (Exception e) {
            log.error("删除失败", e);
            result.put("code",Result.failCode);
            result.put("msg",e.getMessage());
            result.put("data", Result.data);
        }
        return result;

    }






}
