package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

    @Autowired
    private TestRunner testRunner;

    @GetMapping("/triggerTestFromRequestForFirstRepo")
    void triggerTestFromRequestForFirstRepo(){
        testRunner.triggerTestFromRequestForFirstRepo();
    }

    @GetMapping("/triggerTestFromRequestForSecondRepo")
    void triggerTestFromRequestForSecondRepo(){
        testRunner.triggerTestFromRequestForSecondRepo();
    }

    @GetMapping("/triggerTestFromRequestForThirdRepo")
    void triggerTestFromRequestForThirdRepo(){
        testRunner.triggerTestFromRequestForThirdRepo();
    }
}
