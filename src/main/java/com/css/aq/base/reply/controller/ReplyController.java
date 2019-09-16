package com.css.aq.base.reply.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.css.aq.base.Result;
import com.css.aq.base.reply.entity.Reply;
import com.css.aq.base.reply.mapper.ReplyMapper;
import com.css.aq.base.reply.service.IReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuhui
 * @since 2019-09-12
 */
@RestController
@Slf4j
@RequestMapping("/reply")
@Api(description = "回复投诉或者建议controller接口")
public class ReplyController {

    @Autowired
    IReplyService replyService;

    /**
     * 新增回复
     * @param reply
     * @return
     */
    @ApiOperation("新增回复")
    @PostMapping("/addReply")
    public Map<String,Object> addReply(@ApiParam("回复实体") @ModelAttribute Reply reply){
        Map<String,Object> result = new HashMap<>();
        try {
            replyService.addReply(reply);
            result.put("code", Result.successCode);
            result.put("msg",Result.Msg);
            result.put("data", Result.data);
        }catch (Exception e){
            log.error("新增失败", e);
            result.put("code",Result.failCode);
            result.put("msg",e.getMessage());
            result.put("data", Result.data);
        }

        return result;
    }

    /**
     * 删除回复内容
     * @param ids
     * @param systemNo
     * @return
     */
    @ApiOperation(value = "删除回复内容")
    @PostMapping("/delReply/{systemNo}")
    public Map<String,Object> delReply(@ApiParam("回复主键") @RequestParam("ids")List<Long> ids,
      @ApiParam("系统编码") @PathVariable("systemNo") String systemNo){
        Map<String,Object> result = new HashMap<>();
        try {
            replyService.delReply(ids,systemNo);
            result.put("code", Result.successCode);
            result.put("msg",Result.Msg);
            result.put("data", Result.data);
        }catch (Exception e){
            log.error("删除失败", e);
            result.put("code",Result.failCode);
            result.put("msg",e.getMessage());
            result.put("data", Result.data);
        }
        return result;
    }

    /**
     * 查询回复内容
     * @param reply
     * @return
     */
    @ApiOperation(value = "查询回复内容")
    @GetMapping(value = "/dirReply")
    public Map<String,Object> dirReply(@ApiParam("回复实体") @ModelAttribute Reply reply){
        Map<String,Object> result = new HashMap<>();
        List<Reply> replies = replyService.getReplyList(reply,reply.getSystemNo());
        if (replies!=null){
            result.put("data",replies);
            result.put("code", Result.successCode);
        }else {
            result.put("code",Result.failCode);
            result.put("msg","查询失败");
            result.put("data", Result.data);
        }
        return  result;
    }
}
