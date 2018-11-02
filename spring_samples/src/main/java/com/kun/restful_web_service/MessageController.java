package com.kun.restful_web_service;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController         //标记为Controller
public class MessageController {

    private static final String template = "Hey, %s! You are the NO.%s visitor here! ";
    private final AtomicLong counter = new AtomicLong();

     //for http://localhost:8080/visit
    //@RequestMapping("/visit")                             默认所有HTTP请求
    //@RequestMapping(method=GET, value="/visit")           仅处理GET请求
    @RequestMapping(method={GET, POST}, value="/visit")     //处理GET 或POST请求
    public Message visit(@RequestParam(value="name", defaultValue="Visitor") String name) { //name 取query中的name值，默认值为"Visitors"
        return new Message(counter.incrementAndGet(),
                            String.format(template, name, counter.toString()));
    }
    //for http://localhost:8080
    @RequestMapping("/")
    public Message myGreeting(@RequestParam(value = "name", defaultValue = "kun") String name) {
        
        return new Message(-1, "Guess what? This is HomePage! name = " + name);
    }

}