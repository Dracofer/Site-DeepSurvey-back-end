package com.example.deepsurvey.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/test")
    public String test() {
        return "Área ADMIN acessada com sucesso!";
    }
}