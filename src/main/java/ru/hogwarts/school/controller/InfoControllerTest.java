package ru.hogwarts.school.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
//@Profile("!test")  //might be use without properties file
@RequestMapping("/getPort")
public class InfoControllerTest {
    @Value("${server.port:8080}")
    private String port;

    @GetMapping
    public String getInfoTest () {
        log.debug("Get info test was invoked with test profile on port {}", port);
        log.info("Get info test was invoked with default on port {}", port);
        return port;
    }
}
