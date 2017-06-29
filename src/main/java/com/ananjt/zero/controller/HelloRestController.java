package com.ananjt.zero.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Admin on 2017/5/24.
 */

@RestController
public class HelloRestController {

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

}
