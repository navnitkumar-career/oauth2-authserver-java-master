package sddtc.oauth2.authserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sddtc on 2017/4/7.
 */
@RestController
public class SayHelloController {

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        return "hi.";
    }
}
