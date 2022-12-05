package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

@RestController
public class SampleController {

    @Autowired
    private TestRunner testRunner;

    @GetMapping("/execute-tests")
    void executeTests(){
        testRunner.triggerTestFromRequest();
    }
}
