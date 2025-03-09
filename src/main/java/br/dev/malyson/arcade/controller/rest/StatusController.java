package br.dev.malyson.arcade.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/status")
public class StatusController {
    
    @GetMapping
    public String getStatus() {
        return "O servidor está online!";
    }
    

}
