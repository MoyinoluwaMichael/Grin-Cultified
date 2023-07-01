package com.semicolon.grincultified.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/semicolon/cultify/v1/api")
public class ConsumerController {

    @GetMapping("/test")
    public String test(){
        return "Hello world";
    }
}
