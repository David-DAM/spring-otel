package com.davinchicoder.spring_otel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api")
@RestController
public class HiController {

    @GetMapping("/hi")
    public String hi() {
        log.info("Called method hi controller");
        return "Hi";
    }

    @GetMapping("/bye")
    public String bye() {
        log.debug("Called method bye controller");
        return "Bye";
    }

    @GetMapping("/error")
    public String error() {
        throw new RuntimeException("Error");
    }

}
