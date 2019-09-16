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

    @GetMapping(value = "advice/pageadvice")
    public String pageadvice() {
        return "advice/pageadvice.html";
    }

    @GetMapping(value = "advice/advice")
    public String advice() {
        return "advice/advice.html";
    }

    @GetMapping(value = "advice/test")
    public String test() {
        return "advice/test.html";
    }


}
