package com.example.ctrl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/test")
public class TestCtrl {

    @PostMapping(path = "/level")
    public ResponseEntity<Void> level() {

        log.error("error");
        log.warn("warn");
        log.info("info");
        log.debug("debug");
        log.trace("trace");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(path = "/echo")
    public ResponseEntity<String> echo(
            @RequestParam(name = "value", required = true) String value) {

        log.info("Se invoca ECHO");
        return ResponseEntity.ok(value.trim().toUpperCase());
    }

}
