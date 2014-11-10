package by.kruglik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kruglik on 10.11.2014.
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String hello(){
        return "home";
    }
}
