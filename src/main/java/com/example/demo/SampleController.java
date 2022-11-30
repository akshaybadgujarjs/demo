package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    private TestRunner testRunner;

    @GetMapping("/execute-tests")
    void executeTests(){
        testRunner.triggerTestFromRequest();
    }
}
