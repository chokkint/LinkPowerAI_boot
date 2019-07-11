package com.ai.ringball.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HelloSpringBoot {

    @Autowired
//    private PeoplePerties peoplePerties;

    @RequestMapping(value="/hello")
    public String say(){
//        return peoplePerties.getName()+"====="+peoplePerties.getAge();
        return "index";
    }
}