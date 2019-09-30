package com.css.aq.base.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("")
public class PageController {

    //------------------------意见相关----------------------------------start

    @RequestMapping(value = "advice/pageAdvice")
    public String pageAdvic() {
        return "advice/pageAdvice";
    }

    @GetMapping(value = "advice/advice")
    public String advice() {
        return "advice/advice";
    }

    @GetMapping(value = "advice/test")
    public String test() {
        return "advice/test";
    }


    //------------------------事件类型相关----------------------------------start

    @GetMapping(value = "eventType/eventType")
    public String eventType(){
        return "eventType/eventType";
    }

    @GetMapping(value = "eventType/pageEventType")
    public String pageEventType(){
        return "eventType/pageEventType";
    }
}
