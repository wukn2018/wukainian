package com.example.rabbit.web.controller;
import com.example.rabbit.sent.SentTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@RestController
@RequestMapping(value = "rabbit")
public class TestDemoController {

    @Autowired
    private SentTest sentTest;


    @GetMapping(value = "/test")
    @ResponseBody
    public String find() throws Exception{
        for(int i = 0;i<=10;++i) {
            sentTest.send(i);
        }
        return "ok";
    }






}
