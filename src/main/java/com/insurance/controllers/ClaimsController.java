package com.insurance.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/claims")
public class ClaimsController {

    @GetMapping
    public String renderClaims() {
        return "/pages/claims/claims";
    }
}
