package wang.momo.moadmin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/1 22:34
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    private void login(String moAccount,String password){



    }

}
