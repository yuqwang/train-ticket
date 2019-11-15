package mq;


import io.opentracing.contrib.jdbc.TracingDriver;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
public class MyRabbitmqController {


    @Autowired
    Sender sender;

    @GetMapping("/hello")
    public String hello(){
        return " hello";
    }

    @GetMapping("/sender/{mesg}")
    public String sender(@PathVariable String mesg) {
        System.out.println("send string:hello world");
        sender.send("hello world :  " + mesg);


        return "sending...";
    }
}
