package com.yt.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2015/9/14.
 */
@Controller
public class HelloCnotroller {

    @RequestMapping(value ="/hello")
    @ResponseBody
    public String hello(){
        return "hee";
    }

}
