package com.example.springfox3hidden.controllers

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HelloWorld {


    @RequestMapping("/hello-world")
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun helloWorld(): ResponseEntity<String> {
        return ResponseEntity.ok("hello world!")

    }
}
