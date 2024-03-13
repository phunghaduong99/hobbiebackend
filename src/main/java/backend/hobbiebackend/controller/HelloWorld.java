package backend.hobbiebackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @GetMapping("/welcome")
    public String welcome(){
        return "hello World";
    }
}
