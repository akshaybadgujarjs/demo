package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

@RestController
public class SampleController {

    @Autowired
    private TestRunner testRunner;

    @Autowired
    private SimpleTestRunner simpleTestRunner;

    @GetMapping("/execute-tests")
    void executeTests(){
        testRunner.triggerTestFromRequest();
    }

    @PostMapping ("/execute-tests2")
    public void executeTests2(@RequestBody JarRequest jarRequest) throws MalformedURLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        simpleTestRunner.runTestCase(jarRequest);
    }
}
