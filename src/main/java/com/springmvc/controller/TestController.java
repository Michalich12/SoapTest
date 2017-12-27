package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Igor on 02.11.2017.
 */
@Controller
public class TestController {

    @RequestMapping(value = {"/test", "/"}, method = RequestMethod.GET)
    public String testPage() {
        return "index";
    }
}
