package wang.momo.moadmin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.momo.annotion.NotMsgHandle;
import wang.momo.moadmin.entity.TestUser;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/1 22:39
 */
@RestController
@RequestMapping("test")
public class TestController {


    @GetMapping("/getUser")
    private TestUser getUser(){
        return new TestUser().setAccount("mo1001").setAge(24).setName("momo");
    }

    @GetMapping("/getError")
    private TestUser getError(){
        int i=1/0;
        return new TestUser().setAccount("mo1001").setAge(24).setName("momo");
    }


    @NotMsgHandle
    @GetMapping("/getNormal")
    private TestUser getNormal(){
        return new TestUser().setAccount("mo1001").setAge(24).setName("momo");
    }
}
