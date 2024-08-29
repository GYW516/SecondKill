package com.gyw.secondkill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Hellow {
    @RequestMapping("/gyw")
    public String hello(){
        return "hellow";
    }

}
